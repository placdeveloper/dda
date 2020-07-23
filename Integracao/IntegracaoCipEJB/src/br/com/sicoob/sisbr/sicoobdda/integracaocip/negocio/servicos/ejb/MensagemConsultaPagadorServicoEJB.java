package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarPagadorEletronicoLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemConsultaPagadorServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002.GrupoADDA002PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorPagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.DDA0002ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.DDA0002R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.GrupoDDA0002PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.GrupoDDA0002R1PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemConsultarPagadorServicoEJB é responsável por
 * 
 * @author felipe.rosa
 */
@Stateless
@Local({ MensagemConsultaPagadorServicoLocal.class })
public class MensagemConsultaPagadorServicoEJB extends IntegracaoCipServicoEJB implements MensagemConsultaPagadorServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private PagadorCipDao dao;

    private ReplicarPagadorEletronicoLegadoDelegate replicarPagadorEletronicoLegadoDelegate = null;

    private MensagemDDADelegate mensagemDDADelegate;

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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaPagadorServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) {
        dao.gravarPagadorDDAXmlADDA002(idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para  DDA0002 - Participante informa inclusão de pagador");
        MensagemDDAPagador mensagem = dao.obterMensagemDDAPagador(idMensagem);

        DDA0002ComplexType dda0002 = getDDA0002ComplexType(mensagem);
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0002, mensagem.getId());

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0002 - Participante informa inclusão de pagador");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Object retornoDDA) - " + retornoDDA.getCodMsg());
        if (retornoDDA.getCodMsg().equals(TipoMensagemDDA.ADDA002)) {
            processarGrupoPagador((GrupoADDA002PagdrComplexType) retornoDDA);
        } else {
            processarListaGrupoPagador(((DDA0002R1ComplexType) retornoDDA).getGrupoDDA0002R1Pagdr());
        }

        getLogger().debug("########### Fim processarRetornoMensagemDDA(Object retornoDDA) - " + retornoDDA.getCodMsg());
    }

    /**
     * Método responsável por
     * 
     * @param listaGrupoPagador
     * @throws ComumException void
     * 
     */
    private void processarListaGrupoPagador(List<GrupoDDA0002R1PagdrComplexType> listaGrupoPagador) throws ComumException {
        for (GrupoDDA0002R1PagdrComplexType grupoPagador : listaGrupoPagador) {
            processarGrupoPagador(grupoPagador);
        }
    }

    /**
     * Método responsável por
     * 
     * Se o sequencial de atualização recebido na mensagem for menor que o do registro na base, gerar uma mensagem DDA0002 e NÃO processar a mensagem recebida. Se o sequencial da mensagem recebida for igual, DEVE processar.
     * 
     * @param grupoPagador
     * @throws ComumException void
     * 
     */
    private void processarGrupoPagador(GrupoADDA002PagdrComplexType grupoPagador) throws ComumException {
        PagadorDDA pagador = obterPagadorDDALock(grupoPagador.getCNPJCPFPagdr(), grupoPagador.getTpPessoaPagdr());

        if (validaNumSeqPagadorNulo(pagador) && !ObjectUtil.isNull(grupoPagador.getGrupoADDA002PagdrProprio())
                && grupoPagador.getGrupoADDA002PagdrProprio().getNumSeqAtlzCadCliPagdr().longValue() < pagador.getNumSeqAtualCadPagador()) {
            getLogger().info("Numero sequencial MENOR que o numero sequencial da base - Gerando mensagem 002");
            MensagemDDAPagador mensagem = new MensagemDDAPagador();
            mensagem.setNumCpfCnpjPagador(pagador.getNumCpfCnpj());
            mensagem.setCodTipoPessoaPagador(pagador.getCodTipoPessoaCip());

            getMensagemDDADelegate().incluir(mensagem, TipoMensagemDDA.DDA0002, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());

        } else {

            processarMensagemADDA002(grupoPagador, pagador);
        }
    }

    /**
     * Método responsável por
     * 
     * Se o sequencial de atualização recebido na mensagem for menor que o do registro na base, gerar uma mensagem DDA0002 e NÃO processar a mensagem recebida. Se o sequencial da mensagem recebida for igual, DEVE processar.
     * 
     * @param grupoPagador
     * @throws ComumException void
     * 
     */
    private void processarGrupoPagador(GrupoDDA0002R1PagdrComplexType grupoPagador) throws ComumException {
        PagadorDDA pagador = obterPagadorDDALock(grupoPagador.getCNPJCPFPagdr(), grupoPagador.getTpPessoaPagdr());

        if (validaNumSeqPagadorNulo(pagador) && !ObjectUtil.isNull(grupoPagador.getGrupoDDA0002R1PagdrProprio())
                && grupoPagador.getGrupoDDA0002R1PagdrProprio().getNumSeqAtlzCadCliPagdr().longValue() < pagador.getNumSeqAtualCadPagador()) {
            getLogger().info("Numero sequencial MENOR que o numero sequencial da base - Gerando mensagem 002");
            MensagemDDAPagador mensagem = new MensagemDDAPagador();
            mensagem.setNumCpfCnpjPagador(pagador.getNumCpfCnpj());
            mensagem.setCodTipoPessoaPagador(pagador.getCodTipoPessoaCip());
            mensagem.setBolPagadorEletronico(pagador.getBolSacadoEletronico());

            getMensagemDDADelegate().incluir(mensagem, TipoMensagemDDA.DDA0002, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());

        } else {
            processarMensagemDDA0002(grupoPagador, pagador);
        }
    }

    /**
     * @param grupoPagador
     * @param pagador
     * @throws ComumException
     * @throws IntegracaoCipException void
     * 
     */
    private void processarMensagemDDA0002(GrupoDDA0002R1PagdrComplexType grupoPagador, PagadorDDA pagador) throws ComumException, IntegracaoCipException {
        if (ObjectUtil.isEmpty(pagador.getId())) {
            pagador = incluirPagadorDDA(grupoPagador);
        } else {
            pagador = alterarPagadorDDA(pagador, grupoPagador);
        }

        getLogger().debug("###### Replicando a Consulta do pagador numCpfCnpj = " + pagador.getNumCpfCnpj() + " no legado.");

        getReplicarPagadorEletronicoLegadoDelegate().replicarPagadorCIPLegado(pagador.getNumCpfCnpj(), pagador.getBolSacadoEletronico(), getNumCooperativa(pagador));
    }

    /**
     * @param grupoPagador
     * @param pagador
     * @throws ComumException
     * @throws IntegracaoCipException void
     * 
     */
    private void processarMensagemADDA002(GrupoADDA002PagdrComplexType grupoPagador, PagadorDDA pagador) throws ComumException, IntegracaoCipException {
        if (ObjectUtil.isEmpty(pagador.getId())) {
            pagador = incluirPagadorDDA(grupoPagador);
        } else {
            pagador = alterarPagadorDDA(pagador, grupoPagador);
        }

        getLogger().debug("###### Replicando a Consulta do pagador numCpfCnpj = " + pagador.getNumCpfCnpj() + " no legado.");

        getReplicarPagadorEletronicoLegadoDelegate().replicarPagadorCIPLegado(pagador.getNumCpfCnpj(), pagador.getBolSacadoEletronico(), getNumCooperativa(pagador));
    }

    /**
     * Método responsável por setar o numero da cooperativa, quando vier mais de uma conta pega o 1 registro
     * 
     * @param pagador
     * @return Integer
     * 
     */
    private Integer getNumCooperativa(PagadorDDA pagador) {
        return ObjectUtil.isNull(pagador.getListaPagadorDDAConta()) || pagador.getListaPagadorDDAConta().size() == 0 ? null : pagador.getListaPagadorDDAConta().get(0).getNumAgencia();
    }

    /**
     * Método responsável por
     * 
     * @param numCPFCNPJ
     * @param tipoPessoa
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    private PagadorDDA obterPagadorDDALock(BigInteger numCPFCNPJ, String tipoPessoa) throws ComumException {
        return dao.obterPagadorDDA(DataCipUtil.obterCPFCNPJ(numCPFCNPJ, tipoPessoa), Boolean.TRUE);
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador void
     * @throws ComumException
     * 
     */
    private PagadorDDA incluirPagadorDDA(ConteudoMsgRecebida grupoPagador) throws ComumException {

        PagadorDDA pagador = null;
        getLogger().info("Inicio da conversão do Objeto do Pagador");
        pagador = ConversorPagadorDDA.converter(grupoPagador);
        getEm().persist(pagador);
        getLogger().info("Objeto Convertido com Sucesso");

        return pagador;

    }

    /**
     * Método responsável por
     * 
     * @param pagador
     * @param grupoPagador
     * @throws ComumException void
     * 
     */
    private PagadorDDA alterarPagadorDDA(PagadorDDA pagador, ConteudoMsgRecebida grupoPagador) throws ComumException {

        getLogger().info("Processando alteração do Pagador - " + pagador.getId());
        removerRelacionamentoPagadorDDA(pagador);
        PagadorDDA pagadorAlterado = ConversorPagadorDDA.merge(grupoPagador, pagador);
        getEm().merge(pagadorAlterado);
        getLogger().info("Objeto Convertido com Sucesso");

        return pagadorAlterado;

    }

    /**
     * @param pagador
     * @throws ComumException void
     * 
     */
    private void removerRelacionamentoPagadorDDA(PagadorDDA pagador) throws ComumException {
        dao.removerAgregado(pagador.getId());
        dao.removerConta(pagador.getId());
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @return DDA0002ComplexType
     * @throws ComumException
     * 
     */
    private DDA0002ComplexType getDDA0002ComplexType(MensagemDDAPagador mensagem) throws ComumException {
        DDA0002ComplexType dda = new DDA0002ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0002);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartAdmtd(Constantes.ISPB_BANCOOB);

        if (!ObjectUtil.isNull(mensagem) && !ObjectUtil.isEmpty(mensagem.getNumCpfCnpjPagador())) {
            dda.setGrupoDDA0002Pagdr(new GrupoDDA0002PagdrComplexType());
            dda.getGrupoDDA0002Pagdr().setTpPessoaPagdr(mensagem.getCodTipoPessoaPagador());
            dda.getGrupoDDA0002Pagdr().setCNPJCPFPagdr(new BigInteger(mensagem.getNumCpfCnpjPagador()));
        }

        dda.setTpRet(TipoRetornoEnum.M.getCodDominio());

        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        return dda;
    }

    /**
     * @return ReplicarBeneficiarioLegadoDao
     * 
     */
    private ReplicarPagadorEletronicoLegadoDelegate getReplicarPagadorEletronicoLegadoDelegate() {
        if (replicarPagadorEletronicoLegadoDelegate == null) {
            replicarPagadorEletronicoLegadoDelegate = IntegracaoCipFabricaDelegates.getInstance().getReplicarPagadorEletronicoLegadoDelegate();
        }
        return replicarPagadorEletronicoLegadoDelegate;
    }

    /**
     * @return o atributo mensagemDDADelegate
     */
    private MensagemDDADelegate getMensagemDDADelegate() {
        if (ObjectUtil.isNull(mensagemDDADelegate)) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }
        return mensagemDDADelegate;
    }

    /**
     * Método responsável por
     * 
     * @param pagador
     * @return boolean
     * 
     */
    private boolean validaNumSeqPagadorNulo(PagadorDDA pagador) {
        return !ObjectUtil.isNull(pagador) && !ObjectUtil.isNull(pagador.getNumSeqAtualCadPagador());
    }
}
