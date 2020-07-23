/**
 * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Criacao:    May 8, 2015
 */
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
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarBeneficiarioLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemConsultaBeneficiarioServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA504.GrupoADDA504BenfcrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0504.DDA0504ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0504.DDA0504R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0504.GrupoDDA0504BenfcrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0504.GrupoDDA0504R1BenfcrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemConsultaBeneficiarioServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemConsultaBeneficiarioServicoLocal.class })
public class MensagemConsultaBeneficiarioServicoEJB extends IntegracaoCipServicoEJB implements MensagemConsultaBeneficiarioServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BeneficiarioCipDao dao;

    private MensagemDDADelegate mensagemDDADelegate;

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
     * @return ReplicarBeneficiarioLegadoDelegate
     * 
     */
    public ReplicarBeneficiarioLegadoDelegate getReplicarBeneficiarioLegadoDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getReplicarBeneficiarioLegadoDelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0504 - Consultar Beneficiário.");
        MensagemDDABeneficiario mensagem = dao.obterMensagemDDABeneficiarioAtualizaReferencias(idMensagem);

        DDA0504ComplexType dda0504NPC = getDDA0504ComplexTypeNPC(mensagem);
        getLogger().debug("*******INICIO obterXmlEnvio*******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0504NPC, mensagem.getId());
        getLogger().debug("*******FIM obterXmlEnvio*******");

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0504 - Consultar Beneficiário.");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio Classe: MensagemConsultaBeneficiarioServicoEJB - metodo:processarRetornoMensagemDDA  Beneficiário.");

        if (retornoDDA.getCodMsg().equals(TipoMensagemDDA.ADDA504)) {
            getLogger().debug("########### Inicio manutencao Beneficiario ADDA504");
            processarGrupoBeneficiario((GrupoADDA504BenfcrioComplexType) retornoDDA);
            getLogger().debug("########### manutenção feita com sucesso - ADDA504");
        } else {
            getLogger().debug("########### Inicio manutencao Beneficiario DDA0504");
            processarListaGrupoBeneficiario(((DDA0504R1ComplexType) retornoDDA).getGrupoDDA0504R1Benfcrio());
            getLogger().debug("########### manutenção feita com sucesso - DDA0504");
        }
        getLogger().debug("########### FIM Classe: MensagemConsultaBeneficiarioServicoEJB - metodo: processarRetornoMensagemDDAConsultar Beneficiário.");
    }

    /**
     * @param listaGrupoDDA0504R1Benfcrio
     * @throws ComumException void
     * 
     */
    private void processarListaGrupoBeneficiario(List<GrupoDDA0504R1BenfcrioComplexType> listaGrupoDDA0504R1Benfcrio) throws ComumException {
        for (GrupoDDA0504R1BenfcrioComplexType beneficiario : listaGrupoDDA0504R1Benfcrio) {
            processarGrupoBeneficiario(beneficiario);
        }
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA void
     * @throws ComumException
     * 
     */
    private void processarGrupoBeneficiario(GrupoDDA0504R1BenfcrioComplexType retornoDDA) throws ComumException {
        BeneficiarioDDA beneficiarioDDA = obterBeneficiarioDDALock(retornoDDA.getCNPJCPFBenfcrio(), retornoDDA.getTpPessoaBenfcrio());
        if (validaNumSeqBeneficiarioNulo(beneficiarioDDA, retornoDDA) && retornoDDA.getNumSeqAtlzCadBenfcrio().longValue() < beneficiarioDDA.getNumSeqAtualCadBeneficiario()) {
            getLogger().info("Numero sequencial MENOR que o numero sequencial da base - Gerando mensagem 504");
            MensagemDDABeneficiario mensagem = new MensagemDDABeneficiario();
            mensagem.setNumCpfCnpjBeneficiario(beneficiarioDDA.getNumCpfCnpj());
            mensagem.setCodTipoPessoaBeneficiario(beneficiarioDDA.getCodTipoPessoa());

            getMensagemDDADelegate().incluir(mensagem, TipoMensagemDDA.DDA0504, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());

        } else {
            processarMensagemDDA0504(retornoDDA, beneficiarioDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * Se o sequencial de atualização recebido na mensagem for menor que o do registro na base, gerar uma mensagem DDA0504 e NÃO processar a mensagem recebida.
     * 
     * Se o sequencial da mensagem recebida for igual, DEVE processar.
     * 
     * @param retornoDDA void
     * @throws ComumException
     * 
     */
    private void processarGrupoBeneficiario(GrupoADDA504BenfcrioComplexType retornoDDA) throws ComumException {
        BeneficiarioDDA beneficiarioDDA = obterBeneficiarioDDALock(retornoDDA.getCNPJCPFBenfcrio(), retornoDDA.getTpPessoaBenfcrio());
        if (validaNumSeqBeneficiarioNulo(beneficiarioDDA, retornoDDA) && retornoDDA.getNumSeqAtlzCadBenfcrio().longValue() < beneficiarioDDA.getNumSeqAtualCadBeneficiario()) {
            getLogger().info("Numero sequencial MENOR que o numero sequencial da base - Gerando mensagem 504");
            MensagemDDABeneficiario mensagem = new MensagemDDABeneficiario();
            mensagem.setNumCpfCnpjBeneficiario(beneficiarioDDA.getNumCpfCnpj());
            mensagem.setCodTipoPessoaBeneficiario(beneficiarioDDA.getCodTipoPessoa());

            getMensagemDDADelegate().incluir(mensagem, TipoMensagemDDA.DDA0504, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());

        } else {
            processarMensagemADDA504(retornoDDA, beneficiarioDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @param beneficiarioDDA
     * @throws ComumException void
     * 
     */
    private void processarMensagemADDA504(GrupoADDA504BenfcrioComplexType retornoDDA, BeneficiarioDDA beneficiarioDDA) throws ComumException {
        if (ObjectUtil.isNull(beneficiarioDDA) || ObjectUtil.isNull(beneficiarioDDA.getId())) {
            incluirBeneficiarioDDA(retornoDDA);
        } else {
            alterarBeneficiarioDDA(beneficiarioDDA, retornoDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @param beneficiarioDDA
     * @throws ComumException void
     * 
     */
    private void processarMensagemDDA0504(GrupoDDA0504R1BenfcrioComplexType retornoDDA, BeneficiarioDDA beneficiarioDDA) throws ComumException {
        if (ObjectUtil.isNull(beneficiarioDDA) || ObjectUtil.isNull(beneficiarioDDA.getId())) {
            incluirBeneficiarioDDA(retornoDDA);
        } else {
            alterarBeneficiarioDDA(beneficiarioDDA, retornoDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * @param grupoBeneficiario void
     * @throws ComumException
     * 
     */
    private void incluirBeneficiarioDDA(ConteudoMsgRecebida grupoBeneficiario) throws ComumException {
        getLogger().info("Inicio da conversão do Objeto do Beneficiario");
        BeneficiarioDDA beneficiario = ConversorBeneficiarioDDA.converter(grupoBeneficiario);
        getEm().persist(beneficiario);
        getLogger().info("Objeto Convertido com Sucesso");
        getLogger().info("Inicio da replicacao do beneficiario");
        replicarBeneficiarioLegado(beneficiario, grupoBeneficiario.getCodMsg());
        getLogger().info("fim da replicacao do beneficiario");
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @param grupoBeneficiario
     * @throws ComumException void
     * 
     */
    private void alterarBeneficiarioDDA(BeneficiarioDDA beneficiario, ConteudoMsgRecebida grupoBeneficiario) throws ComumException {
        getLogger().info("Processando alteração do Beneficiario - " + beneficiario.getId());
        removerRelacionamentoBeneficiario(beneficiario);
        BeneficiarioDDA beneficiarioAlterado = ConversorBeneficiarioDDA.merge(grupoBeneficiario, beneficiario);
        getEm().merge(beneficiarioAlterado);

        getLogger().info("Objeto Convertido com Sucesso");
        getLogger().info("Inicio da replicacao do beneficiario");
        replicarBeneficiarioLegado(beneficiarioAlterado, grupoBeneficiario.getCodMsg());
        getLogger().info("fim da replicacao do beneficiario");

    }

    /**
     * @param beneficiario
     * @throws ComumException void
     * 
     */
    private void removerRelacionamentoBeneficiario(BeneficiarioDDA beneficiario) throws ComumException {
        dao.removerBeneficiarioInstituicao(beneficiario.getId());
        dao.removerIFBeneficiarioAlerta(beneficiario.getId());
    }

    /**
     * Método responsável por
     * 
     * @param beneficiarioAlterado void
     * @param codMsg
     * @throws IntegracaoCipException
     * 
     */
    private void replicarBeneficiarioLegado(BeneficiarioDDA beneficiarioAlterado, String codMsg) throws IntegracaoCipException {
        if (!codMsg.equals(TipoMensagemDDA.ADDA504) && verificaReplicacaoLegadoAutorizada()) {
            getReplicarBeneficiarioLegadoDelegate().replicarSituacaoBeneficiarioCIPLegado(beneficiarioAlterado);
        }
    }

    /**
     * Método responsável por
     * 
     * @param numCPFCNPJ
     * @param tipoPessoa
     * @return
     * @throws ComumException BeneficiarioDDA
     * 
     */
    private BeneficiarioDDA obterBeneficiarioDDALock(BigInteger numCPFCNPJ, String tipoPessoa) throws ComumException {
        return dao.obterBeneficiario(DataCipUtil.obterCPFCNPJ(numCPFCNPJ, tipoPessoa), Boolean.TRUE);
    }

    /**
     * 
     * @param consultaBeneficiarioDto
     * @param numSeqMsg
     * @return
     * @throws ComumException DDA0504ComplexType
     * 
     */
    private DDA0504ComplexType getDDA0504ComplexTypeNPC(MensagemDDABeneficiario mensagem) throws ComumException {
        DDA0504ComplexType dda = new DDA0504ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0504);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);

        // RegraCIP: Pode ser passado o (NumiDentcBenfcrio) OU o (GrupoBeneficiario) OU (CNPJBaseBenfcrio) OU (SitBenfcrio, dtInicio e dtFim).
        if (!ObjectUtil.isNull(mensagem.getCodTipoPessoaBeneficiario()) && !ObjectUtil.isNull(mensagem.getNumCpfCnpjBeneficiario())) {
            GrupoDDA0504BenfcrioComplexType grupoBeneficiario = new GrupoDDA0504BenfcrioComplexType();
            grupoBeneficiario.setCNPJCPFBenfcrio(new BigInteger(mensagem.getNumCpfCnpjBeneficiario()));
            grupoBeneficiario.setTpPessoaBenfcrio(mensagem.getCodTipoPessoaBeneficiario());
            dda.setGrupoDDA0504Benfcrio(grupoBeneficiario);
        } else if (!ObjectUtil.isNull(mensagem.getCodSituacaoBeneficiario())) {
            dda.setSitBenfcrio(mensagem.getCodSituacaoBeneficiario());
        }
        // ### Fim filtro
        if (!ObjectUtil.isNull(mensagem.getCodSituacaoRelacionamentoBeneficiario())) {
            dda.setSitRelctPart(mensagem.getCodSituacaoRelacionamentoBeneficiario());
        }
        dda.setTpRet(TipoRetornoEnum.M.getCodDominio());
        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        return dda;
    }

    /**
     * @return the dao
     */
    public BeneficiarioCipDao getDao() {
        return dao;
    }

    /**
     * Método responsável por
     * 
     * @param dao void
     * 
     */
    public void setDao(BeneficiarioCipDao dao) {
        this.dao = dao;
    }

    /**
     * @return o atributo mensagemDDADelegate
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (ObjectUtil.isNull(mensagemDDADelegate)) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }
        return mensagemDDADelegate;
    }

    /**
     * 
     * @param beneficiarioDDA
     * @param retornoDDA
     * @return boolean
     * 
     */
    public boolean validaNumSeqBeneficiarioNulo(BeneficiarioDDA beneficiarioDDA, GrupoDDA0504R1BenfcrioComplexType retornoDDA) {
        return !ObjectUtil.isNull(beneficiarioDDA) && !ObjectUtil.isNull(beneficiarioDDA.getNumSeqAtualCadBeneficiario()) && !ObjectUtil.isNull(retornoDDA)
                && !ObjectUtil.isNull(retornoDDA.getNumSeqAtlzCadBenfcrio());
    }

    /**
     * @param beneficiarioDDA
     * @param retornoDDA
     * @return boolean
     * 
     */
    public boolean validaNumSeqBeneficiarioNulo(BeneficiarioDDA beneficiarioDDA, GrupoADDA504BenfcrioComplexType retornoDDA) {
        return !ObjectUtil.isNull(beneficiarioDDA) && !ObjectUtil.isNull(beneficiarioDDA.getNumSeqAtualCadBeneficiario()) && !ObjectUtil.isNull(retornoDDA)
                && !ObjectUtil.isNull(retornoDDA.getNumSeqAtlzCadBenfcrio());
    }
}
