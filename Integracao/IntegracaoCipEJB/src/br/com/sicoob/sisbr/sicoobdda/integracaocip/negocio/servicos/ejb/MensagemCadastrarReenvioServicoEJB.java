/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  cob-integracao-cip
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Criacao:    May 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ReenvioArquivoMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemCadastrarReenvioServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0215.DDA0215ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0215.GrupoDDA0215DettComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;

/**
 * MensagemCadastrarReenvioArquivoOuMensagemServicoEJB
 * 
 * @author George.santos
 */
@Stateless
@Local({ MensagemCadastrarReenvioServicoLocal.class })
public class MensagemCadastrarReenvioServicoEJB extends IntegracaoCipServicoEJB implements MensagemCadastrarReenvioServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private IntegracaoCipDao integracaoCipDao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoCipDao arquivoCipDao;

    private SCIDelegate sciDelegate;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemCadastrarReenvioServico#cadastrarReenvio(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ReenvioArquivoMensagemDto)
     */
    public void cadastrarReenvio(ReenvioArquivoMensagemDto reenvioArquivoMensagemDto) throws ComumException {
        MensagemDDA mensagemDDA = criarMensagemDDA(TipoMensagemDDA.DDA0215, reenvioArquivoMensagemDto.getDataMovimento());

        if (!ObjectUtil.isEmpty(reenvioArquivoMensagemDto.getListaNumeroOperacao())) {
            for (String numOperacao : reenvioArquivoMensagemDto.getListaNumeroOperacao()) {
                mensagemDDA.setNumOperacao(numOperacao);
                em.persist(mensagemDDA);
                DDA0215ComplexType dDA0215ComplexType = getDDA0215ComplexType(reenvioArquivoMensagemDto, mensagemDDA.getId().toString());
                String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dDA0215ComplexType, mensagemDDA.getId());

                postarMensagemFilaSSPBDDA(xmlEnvio);
                getIntegracaoCipDao().atualizarMensagemEnviada(mensagemDDA.getId(), xmlEnvio);
            }
        }

    }

    /**
     * Método responsável por
     * 
     * @param codTipoMensagem
     * @param dataMovimento
     * @return MensagemDDA
     * 
     */
    private MensagemDDA criarMensagemDDA(String codTipoMensagem, Date dataMovimento) {
        MensagemDDA msgDDA = new MensagemDDA();
        TipoMensagemDDA tipoMsg = getEm().find(TipoMensagemDDA.class, codTipoMensagem);
        msgDDA.setTipoMensagemDDA(tipoMsg);
        msgDDA.setDataMovimento(DateUtil.getDateTimeDB(dataMovimento));
        msgDDA.setBolOrigemSicoob(Boolean.TRUE);
        msgDDA.setDataHoraCadastro(new DateTimeDB());
        msgDDA.setNumPrioridadeEnvio(tipoMsg.getNumPrioridadeEnvio());
        return msgDDA;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemCadastrarBeneficiarioServico#processarRetornoCadastrarBeneficiarioDDA(java.lang.Long, java.lang.Long)
     */

    public void processarRetornoMensagemDDA(Long idMensagemDDA, Object retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0501R1 ");

        getLogger().debug("########### Fim processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0501R1 ");
    }

    private DDA0215ComplexType getDDA0215ComplexType(ReenvioArquivoMensagemDto reenvioArquivoMensagemDto, String idMensagem) throws ComumException {
        getLogger().debug("*******INICIO getDDA0215ComplexType*******");
        DDA0215ComplexType dda = new DDA0215ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0215);
        dda.setNumCtrlPart(idMensagem);
        dda.setISPBPartPrincipal(Constantes.ISPB_BANCOOB);
        dda.setTpMsgArq(reenvioArquivoMensagemDto.getCodTipoMensagemArquivo().equals("A") ? "A" : "M");
        dda.setCodMsgArq(reenvioArquivoMensagemDto.getCodMensagemArquivo());

        for (String numOperacao : reenvioArquivoMensagemDto.getListaNumeroOperacao()) {
            GrupoDDA0215DettComplexType grupoDDA0215Det = new GrupoDDA0215DettComplexType();
            grupoDDA0215Det.setNUOpOr(numOperacao);
            dda.getGrupoDDA0215Dett().add(grupoDDA0215Det);
        }
        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(reenvioArquivoMensagemDto.getDataMovimento()));

        getLogger().debug("*******FIM getDDA0215ComplexType*******");
        return dda;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * Método responsável por
     * 
     * @return IntegracaoCipDao
     * 
     */
    public IntegracaoCipDao getIntegracaoCipDao() {
        return integracaoCipDao;
    }

    /**
     * @return o atributo arquivoCipDao
     */
    public ArquivoCipDao getArquivoCipDao() {
        return arquivoCipDao;
    }

    /**
     * @return o atributo sciDelegate
     */
    public SCIDelegate getSciDelegate() {
        if (sciDelegate == null) {
            sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();
        }
        return sciDelegate;
    }

}
