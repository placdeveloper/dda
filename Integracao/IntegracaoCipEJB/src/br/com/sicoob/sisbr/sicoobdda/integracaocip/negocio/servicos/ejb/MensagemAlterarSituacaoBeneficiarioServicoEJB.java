/**
 * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Criacao:    May 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
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
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarBeneficiarioLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemAlterarSituacaoBeneficiarioServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0505.DDA0505ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0505.DDA0505R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0506.DDA0506ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * AlterarSituacaoBeneficiarioServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ MensagemAlterarSituacaoBeneficiarioServicoLocal.class })
public class MensagemAlterarSituacaoBeneficiarioServicoEJB extends IntegracaoCipServicoEJB implements MensagemAlterarSituacaoBeneficiarioServicoLocal {

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
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0505.");

        MensagemDDABeneficiario mensagem = dao.obterMensagemDDABeneficiarioAtualizaReferencias(idMensagem);

        DDA0505ComplexType dda0505 = getDDA0505ComplexType(mensagem);
        getLogger().debug("*******INICIO obterXmlEnvio*******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0505, mensagem.getId());
        getLogger().debug("*******FIM obterXmlEnvio*******");

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0505.");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(java.lang.Long,
     *      java.lang.Long)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio Classe: MensagemAlteraSituacaoBeneficiarioServicoEJB - metodo:processarRetornoMensagemDDA Beneficiário. - DDA0506");
        if (retornoDDA instanceof DDA0505R1ComplexType) {
            alterarSituacaoClienteBeneficiarioDia(retornoDDA);
        } else if (retornoDDA instanceof DDA0506ComplexType) {
            processarDDAAvisaAlteracaoSituacaoBeneficiario(retornoDDA);
        }
        getLogger().debug("########### Fim Classe: MensagemAlteraSituacaoBeneficiarioServicoEJB - metodo:processarRetornoMensagemDDA Beneficiário. - DDA0506");
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @throws ComumException void
     * 
     */
    private void alterarSituacaoClienteBeneficiarioDia(ConteudoMsgRecebida retornoDDA) throws ComumException {
        DDA0505R1ComplexType retorno = (DDA0505R1ComplexType) retornoDDA;
        BeneficiarioDDA beneficiario = getDao().obterBeneficiario(retorno.getNumIdentcBenfcrio().longValue());
        beneficiario.setNumRefAtualCadBeneficiario(retorno.getNumRefAtlCadBenfcrio().longValue());
        beneficiario.setNumSeqAtualCadBeneficiario(retorno.getNumSeqAtlzCadBenfcrio().longValue());

        MensagemDDABeneficiario msgBeneficiario = dao.obterMensagemDDABeneficiario(retornoDDA.getIdMensagemOrigem());

        alterarBeneficiario(msgBeneficiario, beneficiario);
        em.merge(beneficiario);
    }

    /**
     * Método responsável por processar a mensagem de aviso de alteração de situação de beneficiário por parte da CIP. A CIP enviará a todas as IF uma alteração
     * de situação de qualquer beneficiário, seja ele Sicoob ou não.
     * 
     * 
     * @param dda0506
     * @throws ComumException void
     * 
     */
    private void processarDDAAvisaAlteracaoSituacaoBeneficiario(ConteudoMsgRecebida retorno) throws ComumException {
        getLogger().debug("########### Inicio manutencao Beneficiário. MSG DDA0506");
        DDA0506ComplexType dda0506 = (DDA0506ComplexType) retorno;
        BeneficiarioDDA beneficiario = getDao().obterBeneficiario(dda0506.getCNPJCPFBenfcrio(), TipoPessoaEnum.getBy(dda0506.getTpPessoaBenfcrio()));
        if (ObjectUtil.isNull(beneficiario)) {
            // Incluir novo Benefiario
            incluirNovoBeneficiario(dda0506);
        } else {
            // Alterar a situação do Beneficiário
            alterarBeneficiario(dda0506, beneficiario);
        }
        getLogger().debug("########### FIM manutencao Beneficiário. MSG DDA0506");
    }

    /**
     * Método responsável por criar lista de beneficiarioAlerta para DDA0501R1
     * 
     * @param beneficiario
     * @throws ComumException void
     * 
     */
    private void alterarBeneficiario(MensagemDDABeneficiario msgBeneficiario, BeneficiarioDDA beneficiario) throws ComumException {
        if (msgBeneficiario.getCodSituacaoBeneficiario().equals(SituacaoBeneficiarioDDA.APTO)) {
            moverSituacaoAtualParaHistoricoStatus(beneficiario, Boolean.TRUE);
            beneficiario.setListaIFBeneficiarioAlerta(null);
        } else {
            moverSituacaoAtualParaHistoricoStatus(beneficiario, Boolean.FALSE);
            beneficiario.getListaIFBeneficiarioAlerta().add(getIFBeneficiarioAlerta(Constantes.ISPB_BANCOOB, beneficiario));
        }
        beneficiario.setDataHoraUltimaAtualizacao(new DateTimeDB());
        alterarSituacaoBeneficiario(msgBeneficiario.getCodSituacaoBeneficiario(), beneficiario);
    }

    /**
     * @param dda0506
     * @param beneficiario
     * @throws ComumException void
     * 
     */
    private void alterarBeneficiario(DDA0506ComplexType dda0506, BeneficiarioDDA beneficiario) throws ComumException {
        moverSituacaoAtualParaHistoricoStatus(beneficiario, Boolean.TRUE);
        beneficiario.setListaIFBeneficiarioAlerta(new ArrayList<IFBeneficiarioAlerta>());
        for (String ispbAlerta : dda0506.getISPBPartDestinatarioOrigdrAlert()) {
            beneficiario.getListaIFBeneficiarioAlerta().add(getIFBeneficiarioAlerta(ispbAlerta, beneficiario));
        }

        beneficiario.setDataHoraUltimaAtualizacao(new DateTimeDB());
        alterarSituacaoBeneficiario(dda0506.getSitBenfcrio(), beneficiario);
    }

    /**
     * Método responsável por
     * 
     * @param codSituacaoBeneficiarioNovo
     * @param beneficiario
     * @throws IntegracaoCipException void
     * 
     */
    private void alterarSituacaoBeneficiario(String codSituacaoBeneficiarioNovo, BeneficiarioDDA beneficiario) throws IntegracaoCipException {
        if (!beneficiario.getSituacaoBeneficiarioDDA().getCodSituacaoBeneficiario().equals(codSituacaoBeneficiarioNovo)) {
            beneficiario.setSituacaoBeneficiarioDDA(new SituacaoBeneficiarioDDA(codSituacaoBeneficiarioNovo));
            // TODO: rafael.silva - Se houve alteracao de status, comunicar alteração de status para o fraude.
            if (this.verificaReplicacaoLegadoAutorizada()) {
                replicarBeneficiarioLegadoDelegate.replicarSituacaoBeneficiarioCIPLegado(beneficiario);
            }
        }
    }

    /**
     * Método responsável por setar a ifs origanarias do alerta.
     * 
     * @param listaISPBOriginarioAlerta
     * @param beneficiario void
     * @return
     * 
     */
    private IFBeneficiarioAlerta getIFBeneficiarioAlerta(String ispbAlerta, BeneficiarioDDA beneficiario) {
        IFBeneficiarioAlerta ifAlerta = new IFBeneficiarioAlerta();
        ifAlerta.setBeneficiarioDDA(beneficiario);
        ifAlerta.setCodIspbDestinatarioOriginalAlerta(ispbAlerta);
        ifAlerta.setDataHoraAtualizacao(new DateTimeDB());

        return ifAlerta;
    }

    /**
     * Método responsável por mover a situação atual e as ifs orinadoras da situação atual do beneficiário para tabela de historico.
     * 
     * @param beneficiario void
     * 
     */
    private void moverSituacaoAtualParaHistoricoStatus(BeneficiarioDDA beneficiario, Boolean removerIfBeneficiarioAlerta) {
        moverListaIfBeneficiarioAlertaParaHistoricoStatus(beneficiario, beneficiario.getListaIFBeneficiarioAlerta(), removerIfBeneficiarioAlerta);
    }

    /**
     * Método responsável por mover a situação atual e as ifs orinadoras da situação atual do beneficiário para tabela de historico.
     * 
     * @param beneficiario void
     * 
     */
    private void moverListaIfBeneficiarioAlertaParaHistoricoStatus(BeneficiarioDDA beneficiario, List<IFBeneficiarioAlerta> listaIFBeneficiarioAlerta,
            Boolean removerIfBeneficiarioAlerta) {
        // Move a situacao atual para HistoricoStatus/HistoricoIF
        HistoricoStatusBeneficiarioDDA historicoStatus = new HistoricoStatusBeneficiarioDDA();
        historicoStatus.setBeneficiarioDDA(beneficiario);
        historicoStatus.setDataHoraCadastro(beneficiario.getDataHoraUltimaAtualizacao());
        historicoStatus.setSituacaoBeneficiarioDDA(beneficiario.getSituacaoBeneficiarioDDA());

        historicoStatus.setListaHistoricoIfStatusBeneficiario(new ArrayList<HistoricoIfStatusBeneficiario>());
        for (IFBeneficiarioAlerta ifBeneficiarioAlerta : listaIFBeneficiarioAlerta) {
            HistoricoIfStatusBeneficiario historicoIf = new HistoricoIfStatusBeneficiario();
            historicoIf.setCodISPBPartDestinatarioOrigAlerta(ifBeneficiarioAlerta.getCodIspbDestinatarioOriginalAlerta());
            historicoIf.setDataHoraCadastro(new DateTimeDB());
            historicoIf.setHistoricoStatusBeneficiarioDDA(historicoStatus);

            historicoStatus.getListaHistoricoIfStatusBeneficiario().add(historicoIf);

            if (removerIfBeneficiarioAlerta) {
                getEm().remove(ifBeneficiarioAlerta);
            }
        }
        getEm().persist(historicoStatus);
    }

    /**
     * @param dda0506 void
     * 
     */
    private void incluirNovoBeneficiario(DDA0506ComplexType dda0506) {
        BeneficiarioDDA beneficiario;
        beneficiario = new BeneficiarioDDA();

        beneficiario.setDataHoraUltimaAtualizacao(new DateTimeDB());
        beneficiario.setDataInicioRelacionamento(new DateTimeDB());
        beneficiario.setIdProduto(Constantes.ID_PRODUTO_COBRANCA);
        beneficiario.setCodTipoPessoa(dda0506.getTpPessoaBenfcrio());
        beneficiario.setNumCpfCnpj(dda0506.getCNPJCPFBenfcrio().toString(), TipoPessoaEnum.getBy(dda0506.getTpPessoaBenfcrio()));
        beneficiario.setBolOrigemSicoob(Boolean.FALSE);
        beneficiario.setNumCtrlDDA(dda0506.getNumCtrlDDA());
        beneficiario.setSituacaoBeneficiarioDDA(new SituacaoBeneficiarioDDA(dda0506.getSitBenfcrio()));

        beneficiario.setListaIFBeneficiarioAlerta(new ArrayList<IFBeneficiarioAlerta>());
        for (String ispbAlerta : dda0506.getISPBPartDestinatarioOrigdrAlert()) {
            beneficiario.getListaIFBeneficiarioAlerta().add(getIFBeneficiarioAlerta(ispbAlerta, beneficiario));
        }

        em.persist(beneficiario);
    }

    /**
     * Método responsável por criar o objeto de marshal do xml que sera enviado.
     * 
     * @param dto
     * @param numSeqMsg
     * @return
     * @throws ComumException DDA0505ComplexType
     * 
     */
    private br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0505.DDA0505ComplexType getDDA0505ComplexType(MensagemDDABeneficiario mensagem)
            throws ComumException {
        br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0505.DDA0505ComplexType dda = new br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0505.DDA0505ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0505);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);

        dda.setNumIdentcBenfcrio(mensagem.getNumIdentificadorBenficiario());
        dda.setNumRefAtlCadBenfcrio(mensagem.getNumRefAtualCadBeneficiario());

        dda.setTpPessoaBenfcrio(mensagem.getCodTipoPessoaBeneficiario());
        dda.setCNPJCPFBenfcrio(new BigInteger(mensagem.getNumCpfCnpjBeneficiario()));
        dda.setSitBenfcrio(mensagem.getCodSituacaoBeneficiario());
        dda.setDtHrSitBenfcrioPart(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(mensagem.getDataHoraSituacao()));
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
