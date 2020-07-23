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
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemInclusaoPagadorServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorPagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0001.DDA0001ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0001.GrupoDDA0001AgrgdDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0001.GrupoDDA0001CtCliPagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemInclusaoPagadorServicoLocal.class })
public class MensagemInclusaoPagadorServicoEJB extends IntegracaoCipServicoEJB implements MensagemInclusaoPagadorServicoLocal {

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
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0001 - Participante informa inclusão de pagador");
        MensagemDDAPagador mensagem = dao.obterMensagemDDAPagadorAtualizaReferencias(idMensagem);

        DDA0001ComplexType dda0001 = getDDA0001ComplexType(mensagem);
        getLogger().debug("*******INICIO obterXmlEnvio*******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0001, mensagem.getId());
        getLogger().debug("******* XmlEnvio gerado >>> " + xmlEnvio);

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0001 - Participante informa inclusão de pagador");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Object retornoDDA) - DDA0001R1 ");
        MensagemDDAPagador mensagemPagador = null;
        if (!ObjectUtil.isEmpty(conteudoMsg.getIdMensagemOrigem())) {
            mensagemPagador = dao.obterMensagemDDAPagador(conteudoMsg.getIdMensagemOrigem());
            PagadorDDA pagador = dao.obterPagadorDDA(mensagemPagador.getNumCpfCnpjPagador(), Boolean.FALSE);
            if (!ObjectUtil.isNull(pagador) && !ObjectUtil.isEmpty(pagador.getId())) {
                pagador = alterarPagador(conteudoMsg, mensagemPagador, pagador);
            } else {
                pagador = inclusaoPagador(conteudoMsg, mensagemPagador, pagador);
            }

            getLogger().debug("###### Replicando inclusao do pagador numCpfCnpj = " + pagador.getNumCpfCnpj() + " no legado.");

            getReplicarPagadorEletronicoLegadoDelegate().replicarPagadorCIPLegado(pagador.getNumCpfCnpj(), pagador.getBolSacadoEletronico(), getNumCooperativa(pagador));

            getLogger().debug("########### Fim processarRetornoMensagemDDA(Object retornoDDA) - DDA0001R1 ");
        }
    }

    /**
     * Método responsável por setar o numero da cooperativa, quando vier mais de uma conta pega o 1 registro
     * 
     * @param pagador
     * @return Integer
     * 
     */
    private Integer getNumCooperativa(PagadorDDA pagador) {
        return ObjectUtil.isNull(pagador.getListaPagadorDDAConta()) || pagador.getListaPagadorDDAConta().size() == 0 ? null : pagador.getListaPagadorDDAConta().get(0)
                .getNumAgencia();
    }

    /**
     * Método responsável por
     * 
     * @param conteudoMsg
     * @param mensagemPagador
     * @param pagador
     * @return
     * @throws ComumException
     * @throws IntegracaoCipException PagadorDDA
     * 
     */
    private PagadorDDA inclusaoPagador(ConteudoMsgRecebida conteudoMsg, MensagemDDAPagador mensagemPagador, PagadorDDA pagador) throws ComumException, IntegracaoCipException {
        getLogger().info("Inicio da conversão do Objeto da inclusao Pagador");
        pagador = ConversorPagadorDDA.converter(conteudoMsg, mensagemPagador);
        getLogger().info("Objeto Convertido com Sucesso");

        getEm().persist(pagador);

        return pagador;
    }

    /**
     * Método responsável por
     * 
     * @param conteudoMsg
     * @param mensagemPagador
     * @param pagador
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    private PagadorDDA alterarPagador(ConteudoMsgRecebida conteudoMsg, MensagemDDAPagador mensagemPagador, PagadorDDA pagador) throws ComumException {
        getLogger().info("Inicio da conversão do Objeto da alteracao Pagador");
        pagador = ConversorPagadorDDA.merge(conteudoMsg, pagador, mensagemPagador);
        getLogger().info("Objeto Convertido com Sucesso");

        // Remove todos os agregados/Conta depois inclui o que tem que incluir tanto do agregado quanto conta
        dao.removerAgregado(pagador.getId());
        dao.removerConta(pagador.getId());
        getEm().merge(pagador);
        getEm().flush();

        return pagador;
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
    public DDA0001ComplexType getDDA0001ComplexType(MensagemDDAPagador mensagem) throws ComumException {
        DDA0001ComplexType dda = new DDA0001ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0001);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        dda.setNumIdentcPagdr(ObjectUtil.isNull(mensagem.getNumIdentificaPagadorCip()) ? null : BigInteger.valueOf(mensagem.getNumIdentificaPagadorCip()));
        dda.setNumRefAtlCadCliPagdr(ObjectUtil.isNull(mensagem.getNumRefAtualCadPagador()) ? null : BigInteger.valueOf(mensagem.getNumRefAtualCadPagador()));

        dda.setTpPessoaPagdr(mensagem.getCodTipoPessoaPagador());
        dda.setCNPJCPFPagdr(new BigInteger(mensagem.getNumCpfCnpjPagador()));

        dda.setIndrAdesCliPagdrDDA(mensagem.getBolPagadorEletronico());
        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        getGrupoPagadorConta(mensagem, dda);

        getGrupoAgregado(mensagem, dda);

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
    private void getGrupoPagadorConta(MensagemDDAPagador mensagem, DDA0001ComplexType dda) throws ComumException {
        GrupoDDA0001CtCliPagdrComplexType gpContaDDA;
        for (MensagemDDAPagadorConta mensagemDDAPagadorConta : mensagem.getListaMensagemDDAPagadorConta()) {
            gpContaDDA = new GrupoDDA0001CtCliPagdrComplexType();
            gpContaDDA.setAgCliPagdr(new BigInteger(mensagemDDAPagadorConta.getNumAgencia().toString()));
            gpContaDDA.setCtCliPagdr(new BigInteger(mensagemDDAPagadorConta.getNumContaCorrente().toString()));
            gpContaDDA.setDtAdesCliPagdrDDA(ObjectUtil.isNull(mensagemDDAPagadorConta.getDataHoraAdesao()) ? null : DataCipUtil
                    .dateTimeZeroHoraParaXMLGregorianCalendar(mensagemDDAPagadorConta.getDataHoraAdesao()));
            gpContaDDA.setTpAgCliPagdr(TipoAgenciaEnum.FISICA.getCodDominio());
            gpContaDDA.setTpCtCliPagdr(TipoContaEnum.CONTA_CORRENTE.getCodDominio());
            dda.getGrupoDDA0001CtCliPagdr().add(gpContaDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param dda void
     * 
     */
    private void getGrupoAgregado(MensagemDDAPagador mensagem, DDA0001ComplexType dda) {
        GrupoDDA0001AgrgdDDAComplexType gpAgregadoDDA;
        for (MensagemDDAPagadorAgregado mensagemDDAPagadorAgregado : mensagem.getListaMensagemDDAPagadorAgregado()) {
            gpAgregadoDDA = new GrupoDDA0001AgrgdDDAComplexType();
            gpAgregadoDDA.setCNPJCPFAgrgd(new BigInteger(mensagemDDAPagadorAgregado.getNumCpfCnpjAgregado()));
            gpAgregadoDDA.setTpPessoaAgrgd(mensagemDDAPagadorAgregado.getCodTipoPessoaAgregado());
            dda.getGrupoDDA0001AgrgdDDA().add(gpAgregadoDDA);
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
