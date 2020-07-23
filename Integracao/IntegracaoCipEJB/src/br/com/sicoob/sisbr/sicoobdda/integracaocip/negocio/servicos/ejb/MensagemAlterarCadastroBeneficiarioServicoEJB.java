/**
 * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Criação:    May 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoCarteiraCobrancaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoContaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioConvenio;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioRepresentante;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemAlterarCadastroBeneficiarioServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.DDA0502ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.GrupoDDA0502ReprtteCliBenfcrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;

/**
 * AlterarCadastroBeneficiarioServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ MensagemAlterarCadastroBeneficiarioServicoLocal.class })
public class MensagemAlterarCadastroBeneficiarioServicoEJB extends IntegracaoCipServicoEJB implements MensagemAlterarCadastroBeneficiarioServicoLocal {

    protected static final String ERRO_TP_MANUTENCAO_CONVENIO_INVALIDA = "integracaocip.erro.tp.manutencao.convenio.invalida";
    protected static final String ERRO_TP_MANUTENCAO_REPRESENTANTE_INVALIDA = "integracaocip.erro.tp.manutencao.representante.invalida";

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
     * @see br.com.sicoob.sisbr.IntegracaoCipServicoEJB.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
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
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0502.");
        MensagemDDABeneficiario mensagem = dao.obterMensagemDDABeneficiarioAtualizaReferencias(idMensagem);

        getLogger().debug("*******INICIO obterXmlEnvio*******");
        DDA0502ComplexType dda0502 = getDDA0502ComplexType(mensagem);
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0502, mensagem.getId());
        getLogger().debug("*******FIM obterXmlEnvio*******");

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0502.");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarCadastroBeneficiarioServico#processarRetornoAlterarBeneficiarioDDA(java.lang.Long)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug(
                "########### Inicio classe: MensagemAlterarCadastroBeneficiarioServicoEJB - metodo:processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) para DDA0502.");
        MensagemDDABeneficiario mensagem = dao.obterMensagemDDABeneficiario(retornoDDA.getIdMensagemOrigem());

        BeneficiarioDDA beneficiario = getDao().obterBeneficiarioDiferenteDeSemCadastro(mensagem.getNumCpfCnpjBeneficiario());

        if (!ObjectUtil.isNull(beneficiario)) {
            getLogger().debug("Beneficiario Nao esta setado com a situacao de Sem Cadastro (Excluido)");

            br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.DDA0502R1ComplexType beneficiarioRetorno = (br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.DDA0502R1ComplexType) retornoDDA;
            beneficiario.setNumIdentBeneficiario(beneficiarioRetorno.getNumIdentcBenfcrio().longValue());
            if (!ObjectUtil.isNull(beneficiarioRetorno.getNumRefAtlCadBenfcrio())) {
                beneficiario.setNumRefAtualCadBeneficiario(beneficiarioRetorno.getNumRefAtlCadBenfcrio().longValue());
            }

            if (!ObjectUtil.isNull(beneficiarioRetorno.getNumSeqAtlzCadBenfcrio())) {
                beneficiario.setNumSeqAtualCadBeneficiario(beneficiarioRetorno.getNumSeqAtlzCadBenfcrio().longValue());
            }

            for (MensagemDDABeneficiarioConvenio mensagemDDABeneficiarioConvenio : mensagem.getListaMensagemDDABeneficiarioConvenio()) {
                alterarBeneficiarioInstituicao(beneficiario, mensagem, mensagemDDABeneficiarioConvenio);
            }
        }
        getLogger().debug(
                "########### Fim classe: MensagemAlterarCadastroBeneficiarioServicoEJB - metodo:processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) para DDA0502.");

    }

    /**
     * Método responsável por alterar BeneficiarioInstituicao
     * 
     * @param beneficiario
     * @param mensagemDDABeneficiario
     * @param mensagemDDABeneficiarioConvenio
     * @throws ComumException void
     * 
     */
    private void alterarBeneficiarioInstituicao(BeneficiarioDDA beneficiario, MensagemDDABeneficiario mensagemDDABeneficiario,
            MensagemDDABeneficiarioConvenio mensagemDDABeneficiarioConvenio) throws ComumException {
        if (mensagemDDABeneficiarioConvenio.getCodTipoOperacao().equals(TipoManutencaoEnum.INCLUSAO.getCodIndicador())) {
            setarConveniosBeneficiario(beneficiario, mensagemDDABeneficiario, mensagemDDABeneficiarioConvenio);
        } else if (mensagemDDABeneficiarioConvenio.getCodTipoOperacao().equals(TipoManutencaoEnum.EXCLUSAO.getCodIndicador())) {
            BeneficiarioInstituicao beneficiarioInstituicao = getBeneficiarioInstituicao(mensagemDDABeneficiarioConvenio.getNumAgencia(),
                    beneficiario.getListaBeneficiarioInstituicao());
            beneficiario.getListaBeneficiarioInstituicao().remove(beneficiarioInstituicao);
            beneficiario.setDataHoraUltimaAtualizacao(new DateTimeDB());
            em.remove(beneficiarioInstituicao);
        }
    }

    /**
     * Método responsável por setar ConveniosBeneficiario
     * 
     * @param beneficiario
     * @param mensagemDDABeneficiario
     * @param mensagemDDABeneficiarioConvenio
     * @throws ComumException void
     * 
     */
    private void setarConveniosBeneficiario(BeneficiarioDDA beneficiario, MensagemDDABeneficiario mensagemDDABeneficiario,
            MensagemDDABeneficiarioConvenio mensagemDDABeneficiarioConvenio) throws ComumException {

        // Nao insere a instituicao novamente no caso de contingencia.
        BeneficiarioInstituicao beneficiarioInstituicao = getBeneficiarioInstituicao(mensagemDDABeneficiarioConvenio.getNumAgencia(),
                beneficiario.getListaBeneficiarioInstituicao());
        if (ObjectUtil.isNull(beneficiarioInstituicao)) {
            beneficiarioInstituicao = new BeneficiarioInstituicao();

            beneficiario.setDataHoraUltimaAtualizacao(new DateTimeDB());
            // atualizar com a data mais antiga do relacionamento
            if (mensagemDDABeneficiario.getDataInicioRelacionamento().before(beneficiario.getDataInicioRelacionamento())) {
                beneficiario.setDataInicioRelacionamento(mensagemDDABeneficiario.getDataInicioRelacionamento());
            }
            beneficiarioInstituicao.setDataInicioRelacionamento(mensagemDDABeneficiarioConvenio.getDataInicioRelacionamento());
            beneficiarioInstituicao.setBeneficiarioDDA(beneficiario);
            Integer idInstituicao = sciDelegate.obterInstituicaoPorCooperativaCache(mensagemDDABeneficiarioConvenio.getNumAgencia()).getIdInstituicao();
            if (ObjectUtil.isEmpty(idInstituicao)) {
                throw new ComumException(ERRO_INSTITUICAO_SCI_INVALIDA);
            }
            beneficiarioInstituicao.setIdInstituicao(idInstituicao);
            beneficiario.getListaBeneficiarioInstituicao().add(beneficiarioInstituicao);
        }
    }

    /**
     * Método responsável por trazer BeneficiarioInstituicao
     * 
     * @param numAgencia
     * @param listaBeneficiarioInst
     * @return
     * @throws ComumException BeneficiarioInstituicao
     * 
     */
    private BeneficiarioInstituicao getBeneficiarioInstituicao(Integer numAgencia, List<BeneficiarioInstituicao> listaBeneficiarioInst) throws ComumException {
        Integer idInstituicao = sciDelegate.obterInstituicaoPorCooperativaCache(numAgencia).getIdInstituicao();
        if (ObjectUtil.isEmpty(idInstituicao)) {
            throw new ComumException(ERRO_INSTITUICAO_SCI_INVALIDA);
        }
        for (BeneficiarioInstituicao benInst : listaBeneficiarioInst) {
            if (benInst.getIdInstituicao().equals(idInstituicao)) {
                return benInst;
            }
        }
        return null;
    }

    /**
     * Método responsável por trazer DDA0502ComplexType
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0502ComplexType
     * 
     */
    private DDA0502ComplexType getDDA0502ComplexType(MensagemDDABeneficiario mensagem) throws ComumException {
        DDA0502ComplexType dda = new DDA0502ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0502);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);

        dda.setNumIdentcBenfcrio(mensagem.getNumIdentificadorBenficiario());
        dda.setNumRefAtlCadBenfcrio(mensagem.getNumRefAtualCadBeneficiario());

        dda.setTpPessoaBenfcrio(mensagem.getCodTipoPessoaBeneficiario());
        dda.setCNPJCPFBenfcrio(new BigInteger(mensagem.getNumCpfCnpjBeneficiario()));
        dda.setNomRzSocBenfcrio(mensagem.getNomeBeneficiario());
        dda.setNomFantsBenfcrio(mensagem.getNomeFantasiaBeneficiario());
        dda.setDtIniRelctPart(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataInicioRelacionamento()));
        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        for (MensagemDDABeneficiarioConvenio convenio : mensagem.getListaMensagemDDABeneficiarioConvenio()) {
            dda.getGrupoDDA0502Conv().add(getDDA0502ConvComplexTypeNPC(convenio));
        }

        if (!ObjectUtil.isEmpty(mensagem.getListaMensagemDDABeneficiarioRepresentante())) {
            for (MensagemDDABeneficiarioRepresentante repDto : mensagem.getListaMensagemDDABeneficiarioRepresentante()) {
                GrupoDDA0502ReprtteCliBenfcrioComplexType representante = new GrupoDDA0502ReprtteCliBenfcrioComplexType();
                representante.setTpManutReprtteCliBenfcrio(TipoManutencaoEnum.INCLUSAO.getCodIndicador());
                representante.setTpPessoaReprtteCliBenfcrio(repDto.getCodTipoPessoaRepresentante());
                representante.setCNPJCPFReprtteCliBenfcrio(new BigInteger(repDto.getNumCpfCnpjRepresentante()));

                dda.getGrupoDDA0502ReprtteCliBenfcrio().add(representante);
            }
        }
        return dda;
    }

    /**
     * Método responsável por trazer GrupoDDA0502ConvComplexType
     * 
     * @param mensagem
     * @return
     * @throws ComumException br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502NPC.GrupoDDA0502ConvComplexType
     * 
     */
    private br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.GrupoDDA0502ConvComplexType getDDA0502ConvComplexTypeNPC(
            MensagemDDABeneficiarioConvenio mensagem) throws ComumException {
        br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.GrupoDDA0502ConvComplexType grupoConvenio = new br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.GrupoDDA0502ConvComplexType();
        grupoConvenio.setTpManutConv(mensagem.getCodTipoOperacao());

        grupoConvenio.setISPBPartIncorpd(mensagem.getCodISPBParticipanteIncorporado());
        grupoConvenio.setSitConvBenfcrioPart(ObjectUtil.isNull(mensagem.getCodSituacaoConvenioDDA()) ? null : mensagem.getCodSituacaoConvenioDDA());
        grupoConvenio.setDtIniRelctConv(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataInicioRelacionamento()));
        grupoConvenio.setDtFimRelctConv(
                ObjectUtil.isNull(mensagem.getDataFimRelacionamento()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataFimRelacionamento()));
        grupoConvenio.setTpAgDest(TipoAgenciaEnum.FISICA.getCodDominio());
        grupoConvenio.setAgDest(BigInteger.valueOf(mensagem.getNumAgencia()));
        grupoConvenio.setTpCtDest(!ObjectUtil.isEmpty(mensagem.getNumConta()) ? TipoContaEnum.CONTA_CORRENTE.getCodDominio() : null);
        grupoConvenio.setCtDest(ObjectUtil.isEmpty(mensagem.getNumConta()) ? null : BigInteger.valueOf(mensagem.getNumConta()));
        grupoConvenio.setTpProdtConv(mensagem.getCodTipoProdutoDDA());
        grupoConvenio.setTpCartConvCobr(TipoCarteiraCobrancaEnum.COM_REGISTRO.getCodDominio());
        grupoConvenio.setCodCliConv(ObjectUtil.isEmpty(mensagem.getNumClienteOuConvenio()) ? null : mensagem.getNumClienteOuConvenio());
        return grupoConvenio;
    }

    /**
     * Método responsável por
     * 
     * @return BeneficiarioCipDao
     * 
     */
    public BeneficiarioCipDao getDao() {
        return this.dao;
    }
}
