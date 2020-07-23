package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarPagadorEletronicoLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemExcluirPagadorServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorPagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0006.DDA0006ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemExcluirPagadorServicoLocal.class })
public class MensagemExcluirPagadorServicoEJB extends IntegracaoCipServicoEJB implements MensagemExcluirPagadorServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private PagadorCipDao dao;

    private ReplicarPagadorEletronicoLegadoDelegate replicarPagadorEletronicoLegadoDelegate = null;

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
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0006 - Participante informa exclusão de pagador");
        MensagemDDAPagador mensagem = dao.obterMensagemDDAPagadorAtualizaReferencias(idMensagem);

        DDA0006ComplexType dda0006 = getDDA0006ComplexType(mensagem);
        getLogger().debug("*******INICIO obterXmlEnvio*******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0006, mensagem.getId());
        getLogger().debug("xmlEnvio gerado >>> " + xmlEnvio);

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0006 - Participante informa exclusão de pagador");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Object retornoDDA) - DDA0006R1 ");
        getLogger().info("Inicio da conversão do Objeto do Pagador");
        PagadorDDA pagadorDDA = ConversorPagadorDDA.remove(conteudoMsg, dao.obterPagadorDDA(conteudoMsg.getIdMensagemOrigem()));
        getLogger().info("Objeto Convertido com Sucesso");

        getLogger().info("Inicio da atualizaçao do pagador no retorno da mensagem DDA0006R1");
        getLogger().info("IDPagador ->" + pagadorDDA.getId());
        getEm().merge(pagadorDDA);
        dao.removerAgregado(pagadorDDA.getId());
        dao.removerConta(pagadorDDA.getId());
        getLogger().info("Atualização Feita com Sucesso");

        getLogger().debug("###### Replicando exclusao do pagador numCpfCnpj = " + pagadorDDA.getNumCpfCnpj() + " no legado.");
        getReplicarPagadorEletronicoLegadoDelegate().excluirPagadorCipLegador(pagadorDDA.getNumCpfCnpj(), pagadorDDA.getQtdAdesaoDDA() > 1);

        getLogger().debug("########### FIM processarRetornoMensagemDDA(Object retornoDDA) - DDA0006R1 ");
    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @param numSeqMsg
     * @param beneficiario
     * @return
     * @throws ComumException DDA0501ComplexType
     * 
     */
    public DDA0006ComplexType getDDA0006ComplexType(MensagemDDAPagador mensagem) throws ComumException {
        DDA0006ComplexType dda = new DDA0006ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0006);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        dda.setTpPessoaPagdr(mensagem.getCodTipoPessoaPagador());
        dda.setCNPJCPFPagdr(mensagem.getNumCpfCnpjPagador());

        dda.setNumIdentcPagdr(ObjectUtil.isNull(mensagem.getNumIdentificaPagadorCip()) ? null : BigInteger.valueOf(mensagem.getNumIdentificaPagadorCip()));
        dda.setNumRefAtlCadCliPagdr(ObjectUtil.isNull(mensagem.getNumRefAtualCadPagador()) ? null : BigInteger.valueOf(mensagem.getNumRefAtualCadPagador()));

        dda.setDtMovto(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        return dda;
    }

    /**
     * @return ReplicarBeneficiarioLegadoDao
     * 
     */
    public ReplicarPagadorEletronicoLegadoDelegate getReplicarPagadorEletronicoLegadoDelegate() {
        if (replicarPagadorEletronicoLegadoDelegate == null) {
            replicarPagadorEletronicoLegadoDelegate = IntegracaoCipFabricaDelegates.getInstance().getReplicarPagadorEletronicoLegadoDelegate();
        }
        return replicarPagadorEletronicoLegadoDelegate;
    }

}
