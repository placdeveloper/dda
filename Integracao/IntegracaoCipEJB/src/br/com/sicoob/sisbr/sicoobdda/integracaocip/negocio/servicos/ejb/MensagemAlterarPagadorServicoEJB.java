package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoContaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarPagadorEletronicoLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemAlterarPagadorServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorPagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0005.DDA0005ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0005.GrupoDDA0005AgrgdDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0005.GrupoDDA0005CtCliPagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemAlterarPagadorServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemAlterarPagadorServicoLocal.class })
public class MensagemAlterarPagadorServicoEJB extends IntegracaoCipServicoEJB implements MensagemAlterarPagadorServicoLocal {
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
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para  DDA0005 - Alteração do Pagador");
        MensagemDDAPagador mensagemDDAPagador = dao.obterMensagemDDAPagadorAtualizaReferencias(idMensagem);

        DDA0005ComplexType dda0005 = getDDA0005ComplexType(mensagemDDAPagador);
        getLogger().debug("*******INICIO obterXmlEnvio*******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0005, mensagemDDAPagador.getId());
        getLogger().debug("xmlEnvio gerado >>> " + xmlEnvio);

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0005 - Alteração do Pagador");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(java.lang.Long,
     *      java.lang.Object)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        getLogger().info("########### Inicio processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0005R1 ");
        MensagemDDAPagador mensagem = dao.obterMensagemDDAPagador(conteudoMsg.getIdMensagemOrigem());

        getLogger().info("Inicio da conversão do Objeto do Pagador");
        PagadorDDA pagador = ConversorPagadorDDA.merge(conteudoMsg, dao.obterPagadorDDA(mensagem.getNumCpfCnpjPagador(), Boolean.FALSE), mensagem);
        getLogger().info("Objeto Convertido com Sucesso");

        getLogger().info("Inicio da atualizaçao do pagador no retorno da mensagem DDA0005R1");
        // Remove todos os agregados/Conta depois inclui o que tem que incluir tanto do agregado quanto conta
        dao.removerAgregado(pagador.getId());
        dao.removerConta(pagador.getId());
        getEm().merge(pagador);
        getLogger().info("Atualização Feita com Sucesso");

        getLogger().debug("###### Replicando alteracao do pagador numCpfCnpj = " + pagador.getNumCpfCnpj() + " no legado.");
        getReplicarPagadorEletronicoLegadoDelegate().replicarPagadorCIPLegado(pagador.getNumCpfCnpj(), pagador.getBolSacadoEletronico(),
                ObjectUtil.isNull(pagador.getListaPagadorDDAConta()) ? null : pagador.getListaPagadorDDAConta().get(0).getNumAgencia());

        getLogger().info("########### Fim processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0005R1 ");
    }

    /**
     * Método responsável por setar os valores do DDA005ComplexType
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0005ComplexType
     * 
     */
    private DDA0005ComplexType getDDA0005ComplexType(MensagemDDAPagador mensagem) throws ComumException {
        DDA0005ComplexType dda = new DDA0005ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0005);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        dda.setTpPessoaPagdr(mensagem.getCodTipoPessoaPagador());
        dda.setCNPJCPFPagdr(new BigInteger(mensagem.getNumCpfCnpjPagador()));

        dda.setNumIdentcPagdr(BigInteger.valueOf(mensagem.getNumIdentificaPagadorCip()));
        dda.setNumRefAtlCadCliPagdr(BigInteger.valueOf(mensagem.getNumRefAtualCadPagador()));

        dda.setDtMovto(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        getGrupoPagadorConta(mensagem, dda);

        getGrupoPagadorAgregado(mensagem, dda);

        return dda;
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void getGrupoPagadorConta(MensagemDDAPagador mensagem, DDA0005ComplexType dda) throws ComumException {
        GrupoDDA0005CtCliPagdrComplexType gpContaDDA;
        for (MensagemDDAPagadorConta mensagemDDAPagadorConta : mensagem.getListaMensagemDDAPagadorConta()) {
            gpContaDDA = new GrupoDDA0005CtCliPagdrComplexType();
            gpContaDDA.setIndrManutCtCliPagdr(mensagemDDAPagadorConta.getCodTipoOperacao());
            gpContaDDA.setTpAgCliPagdr(TipoAgenciaEnum.FISICA.getCodDominio());
            gpContaDDA.setAgCliPagdr(new BigInteger(mensagemDDAPagadorConta.getNumAgencia().toString()));
            gpContaDDA.setTpCtCliPagdr(TipoContaEnum.CONTA_CORRENTE.getCodDominio());
            gpContaDDA.setCtCliPagdr(new BigInteger(mensagemDDAPagadorConta.getNumContaCorrente().toString()));
            gpContaDDA.setDtAdesCliPagdrDDA(ObjectUtil.isNull(mensagemDDAPagadorConta.getDataHoraAdesao()) ? null : DataCipUtil
                    .dateTimeZeroHoraParaXMLGregorianCalendar(mensagemDDAPagadorConta.getDataHoraAdesao()));
            dda.getGrupoDDA0005CtCliPagdr().add(gpContaDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param dda void
     * 
     */
    private void getGrupoPagadorAgregado(MensagemDDAPagador mensagem, DDA0005ComplexType dda) {
        GrupoDDA0005AgrgdDDAComplexType gpAgregadoDDA;
        for (MensagemDDAPagadorAgregado mensagemDDAPagadorAgregado : mensagem.getListaMensagemDDAPagadorAgregado()) {
            gpAgregadoDDA = new GrupoDDA0005AgrgdDDAComplexType();
            gpAgregadoDDA.setIndrManutAgrgd(mensagemDDAPagadorAgregado.getCodTipoOperacao());
            gpAgregadoDDA.setTpPessoaAgrgd(mensagemDDAPagadorAgregado.getCodTipoPessoaAgregado());
            gpAgregadoDDA.setCNPJCPFAgrgd(new BigInteger(mensagemDDAPagadorAgregado.getNumCpfCnpjAgregado()));
            dda.getGrupoDDA0005AgrgdDDA().add(gpAgregadoDDA);
        }
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
