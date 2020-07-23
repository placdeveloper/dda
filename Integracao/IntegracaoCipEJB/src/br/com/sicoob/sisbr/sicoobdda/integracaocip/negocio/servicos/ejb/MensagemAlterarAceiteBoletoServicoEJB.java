package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAAceite;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemAlterarAceiteBoletoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA104.GrupoADDA104RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBoletoAceiteDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemAlterarAceiteBoletoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemAlterarAceiteBoletoServicoLocal.class })
public class MensagemAlterarAceiteBoletoServicoEJB extends IntegracaoCipServicoEJB implements MensagemAlterarAceiteBoletoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0104 - Participante recebedor informa a alteracao do aceite do Boleto");
        MensagemDDAAceite mensagem = getDao().obterMensagemDDAAceiteAtualizaReferencias(idMensagem);

        getLogger().debug("*******INICIO obterXmlEnvio*******");
        DDA0104ComplexType dda0104 = getDDA0104ComplexType(mensagem);
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0104, mensagem.getId());
        getLogger().debug("xmlEnvio gerado >>> " + xmlEnvio);

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0104 - Participante recebedor informa a alteracao do aceite do Boleto");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0104 ");
        MensagemDDAAceite mensagem = null;
        if (!ObjectUtil.isEmpty(retornoDDA.getIdMensagemOrigem())) {
            mensagem = getDao().obterMensagemDDAAceite(retornoDDA.getIdMensagemOrigem());
        }
        BoletoDDA boletoDDA = getBoletoDDA(retornoDDA);

        getLogger().info("Inicio da conversão do Objeto do Boleto");
        ConversorBoletoAceiteDDA.converter(retornoDDA, boletoDDA, mensagem);
        getLogger().info("Objeto Convertido com Sucesso");

        if (boletoDDA.getBolProcessarMensagemRecebida()) {
            getLogger().info("Sequencial recebido MAIOR, processando a mensagem recebida");
            getDao().atualizaBoletoDDAAceite(boletoDDA);
        }

        getLogger().debug("########### Fim processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0104 ");
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @return
     * @throws ComumException BoletoDDA
     * 
     */
    private BoletoDDA getBoletoDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        BoletoDDA boletoDDABanco = null;
        if (retornoDDA instanceof DDA0104R1ComplexType) {
            DDA0104R1ComplexType dda0104R1 = (DDA0104R1ComplexType) retornoDDA;
            boletoDDABanco = getDao().obterBoletoDDALock(dda0104R1.getNumIdentcTit().longValue());
        } else if (retornoDDA instanceof DDA0104R2ComplexType) {
            DDA0104R2ComplexType dda0104R2 = (DDA0104R2ComplexType) retornoDDA;
            boletoDDABanco = getDao().obterBoletoDDALock(dda0104R2.getNumIdentcTit().longValue());
        } else if (retornoDDA instanceof GrupoADDA104RR2TitComplexType) {
            GrupoADDA104RR2TitComplexType dda0104R2 = (GrupoADDA104RR2TitComplexType) retornoDDA;
            boletoDDABanco = getDao().obterBoletoDDALock(dda0104R2.getNumIdentcTit().longValue());
        }
        return boletoDDABanco;
    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0104ComplexType
     * 
     */
    private DDA0104ComplexType getDDA0104ComplexType(MensagemDDAAceite mensagem) throws ComumException {
        DDA0104ComplexType dda = new DDA0104ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0104);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        dda.setNumIdentcTit(BigInteger.valueOf(mensagem.getNumIdentificadorBoletoCip()));

        dda.setNumRefAtlActe(ObjectUtil.isEmpty(mensagem.getNumRefAtualAceite()) ? null : BigInteger.valueOf(mensagem.getNumRefAtualAceite()));

        dda.setIndrActe(mensagem.getBolAceite());

        dda.setDtMovto(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

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
     * @return the BoletoCipDao
     */
    public BoletoCipDao getDao() {
        return dao;
    }
}
