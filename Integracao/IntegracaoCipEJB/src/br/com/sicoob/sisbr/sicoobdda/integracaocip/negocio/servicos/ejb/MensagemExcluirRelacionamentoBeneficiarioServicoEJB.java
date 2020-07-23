/**
 * Projeto:         Cobran√ßa Banc√°ria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Cria√ß√£o:    May 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoRelacionamentoParticipanteEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoIfStatusBeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoStatusBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.IFBeneficiarioAlerta;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarBeneficiarioLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemExcluirRelacionamentoBeneficiarioServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0503.DDA0503ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * CadastrarBeneficiarioServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ MensagemExcluirRelacionamentoBeneficiarioServicoLocal.class })
public class MensagemExcluirRelacionamentoBeneficiarioServicoEJB extends IntegracaoCipServicoEJB implements MensagemExcluirRelacionamentoBeneficiarioServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BeneficiarioCipDao dao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    private ReplicarBeneficiarioLegadoDelegate replicarBeneficiarioLegadoDelegate = IntegracaoCipFabricaDelegates.getInstance().getReplicarBeneficiarioLegadoDelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug(
                "########### Inicio Classe MensagemExcluirRelacionamentoBeneficiarioServicoEJB - metodo:processarMensagem(Long idMensagem) para DDA0503 - Excluir Relacionamento Benefici·rio.");

        MensagemDDABeneficiario mensagem = dao.obterMensagemDDABeneficiarioAtualizaReferencias(idMensagem);

        DDA0503ComplexType dda0503 = getDDA0503ComplexType(mensagem);
        getLogger().debug("*******INICIO obterXmlEnvio*******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0503, mensagem.getId());
        getLogger().debug("*******FIM obterXmlEnvio*******");

        getLogger().debug(
                "########### Fim Classe MensagemExcluirRelacionamentoBeneficiarioServicoEJB - metodo:processarMensagem(Long idMensagem) para DDA0503 - Excluir Relacionamento Benefici·rio.");
        return xmlEnvio;

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug(
                "########### Inicio Classe MensagemExcluirRelacionamentoBeneficiarioServicoEJB - metodo:processarRetornoMensagemDDA(Object retornoDDA) para DDA0503R1 - Excluir Relacionamento Benefici·rio.");

        br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0503.DDA0503R1ComplexType retorno = (br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0503.DDA0503R1ComplexType) retornoDDA;
        BeneficiarioDDA beneficiario = getDao().obterBeneficiario(retorno.getNumIdentcBenfcrio().longValue());
        beneficiario.setNumIdentBeneficiario(retorno.getNumIdentcBenfcrio().longValue());
        if (!ObjectUtil.isNull(retorno.getNumRefAtlCadBenfcrio())) {
            beneficiario.setNumRefAtualCadBeneficiario(retorno.getNumRefAtlCadBenfcrio().longValue());
        }
        if (!ObjectUtil.isNull(retorno.getNumSeqAtlzCadBenfcrio())) {
            beneficiario.setNumSeqAtualCadBeneficiario(retorno.getNumSeqAtlzCadBenfcrio().longValue());
        }

        Boolean excluirBeneficiario = Boolean.TRUE;

        for (IFBeneficiarioAlerta ifBenAlerta : beneficiario.getListaIFBeneficiarioAlerta()) {
            if (!ifBenAlerta.getCodIspbDestinatarioOriginalAlerta().equals(Constantes.ISPB_BANCOOB)) {
                excluirBeneficiario = Boolean.FALSE;
                break;
            }
        }

        if (excluirBeneficiario) {
            Long idBeneficiarioDDA = beneficiario.getId();

            getLogger().debug("###### alterando o  beneficiario id = " + idBeneficiarioDDA + " para " + SituacaoBeneficiarioDDA.SEM_CADASTRO);
            beneficiario.setSituacaoBeneficiarioDDA(em.find(SituacaoBeneficiarioDDA.class, SituacaoBeneficiarioDDA.SEM_CADASTRO));

            getDao().removerBeneficiarioInstituicao(beneficiario.getId());
            getDao().removerIFBeneficiarioAlerta(beneficiario.getId());
            em.merge(beneficiario);

            atualizandoHistoricoStatusBeneficiarioDDA(beneficiario);

            if (this.verificaReplicacaoLegadoAutorizada()) {
                getLogger().debug("###### Replicando exclus„o do beneficiario id = " + idBeneficiarioDDA + " no legado.");
                replicarBeneficiarioLegadoDelegate.replicarExclusaoBeneficiarioLegado(idBeneficiarioDDA);
            }
        } else {
            getLogger().debug("###### Removendo vinculos do beneficiario id = " + beneficiario.getId());
            getDao().removerViculoSicoobBeneficiario(beneficiario.getId());
        }

        getLogger().debug(
                "########### Fim Classe MensagemExcluirRelacionamentoBeneficiarioServicoEJB - metodo:processarRetornoMensagemDDA(Object retornoDDA) para DDA0503R1 - Excluir Relacionamento Benefici·rio.");

    }

    /**
     * MÈtodo respons·vel por
     * 
     * @param beneficiario void
     * 
     */
    private void atualizandoHistoricoStatusBeneficiarioDDA(BeneficiarioDDA beneficiario) {
        beneficiario.getListaHistoricoStatusBeneficiarioDDA();
        beneficiario.getListaHistoricoStatusBeneficiarioDDA().add(new HistoricoStatusBeneficiarioDDA(beneficiario, beneficiario.getSituacaoBeneficiarioDDA(),
                beneficiario.getDataHoraUltimaAtualizacao(), new ArrayList<HistoricoIfStatusBeneficiario>()));
    }

    /**
     * MÈtodo respons·vel por criar objeto padr„o para marshal do xml de envio de exclus„o de relacionamento do benefici·rio.
     * 
     * @param dto
     * @param numSeqMsg
     * @param numIdentBeneficiario
     * @return
     * @throws ComumException DDA0503ComplexType
     * 
     */
    private DDA0503ComplexType getDDA0503ComplexType(MensagemDDABeneficiario mensagem) throws ComumException {
        DDA0503ComplexType dda = new DDA0503ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0503);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);

        dda.setNumIdentcBenfcrio(mensagem.getNumIdentificadorBenficiario());
        dda.setNumRefAtlCadBenfcrio(mensagem.getNumRefAtualCadBeneficiario());

        dda.setTpPessoaBenfcrio(mensagem.getCodTipoPessoaBeneficiario());
        dda.setCNPJCPFBenfcrio(new BigInteger(mensagem.getNumCpfCnpjBeneficiario()));
        dda.setSitRelctPart(SituacaoRelacionamentoParticipanteEnum.EXCLUIDO.getCodDominio());
        dda.setDtFimRelctPart(DataCipUtil.getDataAtualXMLGregorianCalendar());
        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));
        return dda;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.IntegracaoCipServicoEJB.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return the dao
     */
    public BeneficiarioCipDao getDao() {
        return dao;
    }
}
