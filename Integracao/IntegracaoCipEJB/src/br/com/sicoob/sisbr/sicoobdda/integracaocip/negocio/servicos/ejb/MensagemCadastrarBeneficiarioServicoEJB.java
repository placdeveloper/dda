/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  cob-integracao-cip
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Criacao:    May 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoConvenioBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoCarteiraCobrancaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoContaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioConvenio;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioRepresentante;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemCadastrarBeneficiarioServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501.DDA0501ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501.DDA0501R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501.GrupoDDA0501ConvComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501.GrupoDDA0501ReprtteCliBenfcrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;

/**
 * CadastrarBeneficiarioServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ MensagemCadastrarBeneficiarioServicoLocal.class })
public class MensagemCadastrarBeneficiarioServicoEJB extends IntegracaoCipServicoEJB implements MensagemCadastrarBeneficiarioServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BeneficiarioCipDao dao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    private SCIDelegate sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio Classe: MensagemCadastrarBeneficiarioServicoEJB - metodo:processarMensagem(Long idMensagem) para DDA0501.");

        MensagemDDABeneficiario mensagem = dao.obterMensagemDDABeneficiarioAtualizaReferencias(idMensagem);

        DDA0501ComplexType dda0501 = getDDA0501ComplexType(mensagem);
        getLogger().debug("*******INICIO obterXmlEnvio *******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0501, mensagem.getId());
        getLogger().debug("*******FIM obterXmlEnvio *******");
        getLogger().debug("########### Fim Classe: MensagemCadastrarBeneficiarioServicoEJB - metodo:processarMensagem(Long idMensagem) para DDA0501.");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemCadastrarBeneficiarioServico#processarRetornoCadastrarBeneficiarioDDA(java.lang.Long,
     *      java.lang.Long)
     */

    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio Classe: MensagemCadastrarBeneficiarioServicoEJB - metodo:processarRetornoMensagemDDA(Object retornoDDA) - DDA0501R1 ");
        MensagemDDABeneficiario mensagemDDABeneficiario = getDao().obterMensagemDDABeneficiario(retornoDDA.getIdMensagemOrigem());

        DDA0501R1ComplexType beneficiarioRetorno = (DDA0501R1ComplexType) retornoDDA;
        retornoBeneficiarioInclusaoFluxo(mensagemDDABeneficiario, beneficiarioRetorno.getNumIdentcBenfcrio().longValue(),
                ObjectUtil.isNull(beneficiarioRetorno.getNumRefAtlCadBenfcrio()) ? null : beneficiarioRetorno.getNumRefAtlCadBenfcrio().longValue(),
                ObjectUtil.isNull(beneficiarioRetorno.getNumSeqAtlzCadBenfcrio()) ? null : beneficiarioRetorno.getNumSeqAtlzCadBenfcrio().longValue());

        getLogger().debug("########### Fim Classe: MensagemCadastrarBeneficiarioServicoEJB - metodo: processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0501R1 ");
    }

    /**
     * Método responsável por fazer a logica da inclusao quando vem uma Mensagem do fluxo Antigo
     * 
     * @param retornoDDA
     * @param mensagemDDABeneficiario
     * @throws ComumException void
     * 
     */
    private void retornoBeneficiarioInclusaoFluxo(MensagemDDABeneficiario mensagemDDABeneficiario, Long numIdentificadorBeneficiario, Long numRefCadBeneficiario,
            Long numSeqCadBeneficiario) throws ComumException {
        getLogger().info("Inicio do Retorno do Beneficiario Mensagem 0501 Fluxo Antigo");

        BeneficiarioDDA beneficiario = getDao().obterBeneficiario(mensagemDDABeneficiario.getNumCpfCnpjBeneficiario());
        if (!ObjectUtil.isNull(beneficiario) && !ObjectUtil.isEmpty(beneficiario.getId())) {
            getLogger().info("Utilizado quando o Beneficiario na CIP esta Inativo");
            getLogger().debug("Beneficiario existe na base - Atualiza somente o BolOrigem e NumIdentificacao do Beneficiario");
            beneficiario.setBolOrigemSicoob(Boolean.TRUE);
            beneficiario.setNumIdentBeneficiario(numIdentificadorBeneficiario);
            if (!ObjectUtil.isNull(numRefCadBeneficiario)) {
                beneficiario.setNumRefAtualCadBeneficiario(numRefCadBeneficiario);
            }

            if (!ObjectUtil.isNull(numSeqCadBeneficiario)) {
                beneficiario.setNumSeqAtualCadBeneficiario(numSeqCadBeneficiario);
            }

            beneficiario.setSituacaoBeneficiarioDDA(new SituacaoBeneficiarioDDA(SituacaoConvenioBeneficiarioEnum.ATIVO.getCodDominio()));
            beneficiario.setDataHoraUltimaAtualizacao(new DateTimeDB());
            beneficiario.setDataInicioRelacionamento(mensagemDDABeneficiario.getDataInicioRelacionamento());

            for (MensagemDDABeneficiarioConvenio mensagemDDABeneficiarioConvenio : mensagemDDABeneficiario.getListaMensagemDDABeneficiarioConvenio()) {
                setarConveniosBeneficiario(beneficiario, mensagemDDABeneficiarioConvenio);
            }

        } else {
            getLogger().info("Beneficiario nao existe na base");
            beneficiario = new BeneficiarioDDA();

            beneficiario.setBolOrigemSicoob(Boolean.TRUE);
            beneficiario.setSituacaoBeneficiarioDDA(new SituacaoBeneficiarioDDA(mensagemDDABeneficiario.getCodSituacaoBeneficiario()));
            beneficiario.setCodTipoPessoa(mensagemDDABeneficiario.getCodTipoPessoaBeneficiario());
            beneficiario.setIdProduto(Constantes.ID_PRODUTO_COBRANCA);
            beneficiario.setNumCpfCnpj(mensagemDDABeneficiario.getNumCpfCnpjBeneficiario(),
                    mensagemDDABeneficiario.getCodTipoPessoaBeneficiario().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);
            beneficiario.setNumIdentBeneficiario(numIdentificadorBeneficiario);
            beneficiario.setNumRefAtualCadBeneficiario(numRefCadBeneficiario);
            beneficiario.setNumSeqAtualCadBeneficiario(numSeqCadBeneficiario);

            beneficiario.setDataHoraUltimaAtualizacao(new DateTimeDB());
            beneficiario.setDataInicioRelacionamento(mensagemDDABeneficiario.getDataInicioRelacionamento());

            for (MensagemDDABeneficiarioConvenio mensagemDDABeneficiarioConvenio : mensagemDDABeneficiario.getListaMensagemDDABeneficiarioConvenio()) {
                setarConveniosBeneficiario(beneficiario, mensagemDDABeneficiarioConvenio);
            }

            em.persist(beneficiario);
        }
        getLogger().info("Inicio do Retorno do Beneficiario Mensagem 0501 Fluxo Antigo");
    }

    /**
     * Método responsável por setar as cooperativas dos beneficiarios.
     * 
     * @param beneficiario
     * @param mensagemDDABeneficiarioConvenio
     * @throws ComumException void
     * 
     */
    private void setarConveniosBeneficiario(BeneficiarioDDA beneficiario, MensagemDDABeneficiarioConvenio mensagemDDABeneficiarioConvenio) throws ComumException {
        BeneficiarioInstituicao beneficiarioInstituicao = new BeneficiarioInstituicao();
        beneficiarioInstituicao.setDataInicioRelacionamento(mensagemDDABeneficiarioConvenio.getDataInicioRelacionamento());
        beneficiarioInstituicao.setBeneficiarioDDA(beneficiario);
        Integer idInstituicao = sciDelegate.obterInstituicaoPorCooperativaCache(mensagemDDABeneficiarioConvenio.getNumAgencia()).getIdInstituicao();
        if (ObjectUtil.isEmpty(idInstituicao)) {
            throw new ComumException(ERRO_INSTITUICAO_SCI_INVALIDA);
        }
        beneficiarioInstituicao.setIdInstituicao(idInstituicao);
        beneficiario.getListaBeneficiarioInstituicao().add(beneficiarioInstituicao);
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
    private DDA0501ComplexType getDDA0501ComplexType(MensagemDDABeneficiario mensagem) throws ComumException {
        getLogger().debug("*******INICIO getDDA0501ComplexType novo Fluxo*******");
        DDA0501ComplexType dda = new DDA0501ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0501);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);

        dda.setNumIdentcBenfcrio(mensagem.getNumIdentificadorBenficiario());
        dda.setNumRefAtlCadBenfcrio(mensagem.getNumRefAtualCadBeneficiario());

        dda.setTpPessoaBenfcrio(mensagem.getCodTipoPessoaBeneficiario());
        dda.setCNPJCPFBenfcrio(new BigInteger(mensagem.getNumCpfCnpjBeneficiario()));
        dda.setNomRzSocBenfcrio(mensagem.getNomeBeneficiario());
        dda.setNomFantsBenfcrio(mensagem.getNomeFantasiaBeneficiario());
        dda.setSitBenfcrio(mensagem.getCodSituacaoBeneficiario());
        dda.setDtHrSitBenfcrioPart(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(mensagem.getDataHoraSituacao()));
        dda.setSitRelctPart(mensagem.getCodSituacaoRelacionamentoBeneficiario());
        dda.setDtIniRelctPart(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataInicioRelacionamento()));
        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        for (MensagemDDABeneficiarioConvenio convenio : mensagem.getListaMensagemDDABeneficiarioConvenio()) {
            dda.getGrupoDDA0501Conv().add(getDDA0501ConvComplexTypeNPC(convenio));
        }

        if (!ObjectUtil.isEmpty(mensagem.getListaMensagemDDABeneficiarioRepresentante())) {
            for (MensagemDDABeneficiarioRepresentante repDto : mensagem.getListaMensagemDDABeneficiarioRepresentante()) {
                dda.getGrupoDDA0501ReprtteCliBenfcrio().add(getGrupoDDA0501ReprtteCliBenfcrioComplexTypeNPC(repDto));
            }
        }
        getLogger().debug("*******FIM getDDA0501ComplexType novo Fluxo*******");
        return dda;
    }

    /**
     * Método responsável por criar e retornar o GrupoDDA0501ConvComplexType
     * 
     * @param convenio
     * @throws ComumException void
     * 
     */
    private GrupoDDA0501ConvComplexType getDDA0501ConvComplexTypeNPC(MensagemDDABeneficiarioConvenio convenio) throws ComumException {
        GrupoDDA0501ConvComplexType grupoConvenio = new GrupoDDA0501ConvComplexType();
        grupoConvenio.setISPBPartIncorpd(convenio.getCodISPBParticipanteIncorporado());

        grupoConvenio.setSitConvBenfcrioPart(ObjectUtil.isNull(convenio.getCodSituacaoConvenioDDA()) ? null : convenio.getCodSituacaoConvenioDDA());
        grupoConvenio.setDtIniRelctConv(DataCipUtil.dateTimeParaXMLGregorianCalendar(convenio.getDataInicioRelacionamento()));
        grupoConvenio.setTpAgDest(TipoAgenciaEnum.FISICA.getCodDominio());
        grupoConvenio.setAgDest(BigInteger.valueOf(convenio.getNumAgencia()));
        grupoConvenio.setTpCtDest(!ObjectUtil.isEmpty(convenio.getNumConta()) ? TipoContaEnum.CONTA_CORRENTE.getCodDominio() : null);
        grupoConvenio.setCtDest(ObjectUtil.isEmpty(convenio.getNumConta()) ? null : BigInteger.valueOf(convenio.getNumConta()));
        grupoConvenio.setTpProdtConv(convenio.getCodTipoProdutoDDA());
        grupoConvenio.setTpCartConvCobr(TipoCarteiraCobrancaEnum.COM_REGISTRO.getCodDominio());
        grupoConvenio.setCodCliConv(ObjectUtil.isEmpty(convenio.getNumClienteOuConvenio()) ? null : convenio.getNumClienteOuConvenio());

        return grupoConvenio;
    }

    /**
     * Método responsável por
     * 
     * @param representante
     * @return br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501NPC.GrupoDDA0501ReprtteCliBenfcrioComplexType
     * 
     */
    private GrupoDDA0501ReprtteCliBenfcrioComplexType getGrupoDDA0501ReprtteCliBenfcrioComplexTypeNPC(MensagemDDABeneficiarioRepresentante representante) {
        GrupoDDA0501ReprtteCliBenfcrioComplexType rep = new GrupoDDA0501ReprtteCliBenfcrioComplexType();
        rep.setTpPessoaReprtteCliBenfcrio(representante.getCodTipoPessoaRepresentante());
        rep.setCNPJCPFReprtteCliBenfcrio(new BigInteger(representante.getNumCpfCnpjRepresentante()));
        return rep;
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
     * @return o atributo dao
     */
    public BeneficiarioCipDao getDao() {
        return dao;
    }
}
