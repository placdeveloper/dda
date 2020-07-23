/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         ReplicarBeneficiarioLegadoServicoEJB.java
 * Data Criação:    Aug 28, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDABeneficiarioCooperativa;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDABeneficiarioOperacao;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDAOperacao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ReplicarBeneficiarioLegadoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarBeneficiarioLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;

/**
 * ReplicarBeneficiarioLegadoServicoEJB é responsável por
 * 
 * @author felipe.rosa
 */
@Stateless
@Local({ ReplicarBeneficiarioLegadoServicoLocal.class })
public class ReplicarBeneficiarioLegadoServicoEJB extends IntegracaoCipServicoEJB implements ReplicarBeneficiarioLegadoServicoLocal {

    private static final String ERRO_LISTAR_COOPERATIVA = "integracaocip.erro.listar.cooperativa";
    private static final String ERRO_ALTERAR_BENEFICIARIO_LEGADO = "integracaocip.erro.alterar.beneficiario.legado";
    private static final String ERRO_INCLUIR_BENEFICIARIO_LEGADO = "integracaocip.erro.incluir.beneficiario.legado";
    private static final String ERRO_EXCLUIR_BENEFICIARIO_LEGADO = "integracaocip.erro.excluir.beneficiario.legado";
    private static final String ERRO_REPLICAR_FRAUDE_LEGADO = "integracaocip.erro.replicar.fraude.legado";
    private static final String ERRO_REPLICAR_CIP_LEGADO = "integracaocip.erro.replicar.cip.legado";

    @PersistenceContext(unitName = "emSicoobDDA_sqlServer")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ReplicarBeneficiarioLegadoDao dao;

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
     * @return ReplicarBeneficiarioLegadoDao
     */
    public ReplicarBeneficiarioLegadoDao getDao() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarBeneficiarioLegadoServico#replicarSituacaoBeneficiarioCIPLegado(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA)
     */
    public void replicarSituacaoBeneficiarioCIPLegado(BeneficiarioDDA beneficiario) throws IntegracaoCipException {
        getLogger().debug("********* replicarSituacaoBeneficiarioCIPLegado *********");
        try {
            getLogger().debug("Beneficiario 756: " + beneficiario.getBolOrigemSicoob());
            if (beneficiario.getBolOrigemSicoob()) {
                getLogger().debug("Situacao: " + beneficiario.getSituacaoBeneficiarioDDA().getCodSituacaoBeneficiario());
                DDABeneficiario beneficiarioLegado = getDao().obter(beneficiario.getId());
                if (beneficiario.getSituacaoBeneficiarioDDA().getCodSituacaoBeneficiario().equals(SituacaoBeneficiarioDDA.INAPTO)) {
                    incluirAlterarBeneficiarioInapto(beneficiario, beneficiarioLegado);
                } else {
                    excluirRegistroBeneficiarioLegado(beneficiarioLegado);
                }
            }
        } catch (BancoobException e) {
            tratarException(e, ERRO_REPLICAR_CIP_LEGADO);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarBeneficiarioLegadoServico#replicarSituacaoBeneficiarioFraudeLegado(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA)
     */
    public void replicarSituacaoBeneficiarioFraudeLegado(BeneficiarioDDA beneficiario) throws IntegracaoCipException {
        getLogger().debug("Situacao: " + beneficiario.getSituacaoBeneficiarioDDA().getCodSituacaoBeneficiario());

        DDABeneficiario beneficiarioLegado = obterBeneficiarioLegado(beneficiario);
        if (beneficiario.getSituacaoBeneficiarioDDA().getCodSituacaoBeneficiario().equals(SituacaoBeneficiarioDDA.APTO)) {
            excluirBeneficiario(beneficiario);
        } else if (beneficiario.getSituacaoBeneficiarioDDA().getCodSituacaoBeneficiario().equals(SituacaoBeneficiarioDDA.INAPTO)) {
            incluirAlterarBeneficiarioInapto(beneficiario, beneficiarioLegado);
        } else {
            // TODO - Enquanto não possuímos a view do FRAUDE, nós excluímos o beneficiário com situação EM_ANALISE
            excluirBeneficiario(beneficiario);
        }

    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @return
     * @throws IntegracaoCipException DDABeneficiario
     * 
     */
    private DDABeneficiario obterBeneficiarioLegado(BeneficiarioDDA beneficiario) throws IntegracaoCipException {
        DDABeneficiario ddaBeneficiario = null;
        try {
            ddaBeneficiario = getDao().obter(beneficiario.getId());
        } catch (BancoobException e) {
            tratarException(e, ERRO_REPLICAR_FRAUDE_LEGADO);
        }
        return ddaBeneficiario;
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @throws IntegracaoCipException void
     * 
     */
    private void excluirBeneficiario(BeneficiarioDDA beneficiario) throws IntegracaoCipException {
        try {
            getDao().excluir(beneficiario.getId());
        } catch (BancoobException e) {
            tratarException(e, ERRO_REPLICAR_FRAUDE_LEGADO);
        }
    }

    /**
     * Método responsável por
     * 
     * @param beneficiarioLegado
     * @throws IntegracaoCipException void
     * 
     */
    private void excluirRegistroBeneficiarioLegado(DDABeneficiario beneficiarioLegado) throws IntegracaoCipException {
        getLogger().debug("********* excluirRegistroBeneficiarioLegado *********");
        if (!ObjectUtil.isNull(beneficiarioLegado)) {
            try {
                getDao().excluir(beneficiarioLegado.getId());
            } catch (BancoobException e) {
                tratarException(e, ERRO_EXCLUIR_BENEFICIARIO_LEGADO);
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @param beneficiarioLegado
     * @throws IntegracaoCipException void
     * 
     */
    private void incluirAlterarBeneficiarioInapto(BeneficiarioDDA beneficiario, DDABeneficiario beneficiarioLegado) throws IntegracaoCipException {
        if (ObjectUtil.isNull(beneficiarioLegado)) {
            incluirRegsitroLegadoInapto(beneficiario);
        } else {
            alterarRegistroLegadoInapto(beneficiario, beneficiarioLegado);
        }
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @throws IntegracaoCipException void
     * 
     */
    private void incluirRegsitroLegadoInapto(BeneficiarioDDA beneficiario) throws IntegracaoCipException {
        getLogger().debug("********* incluirRegsitroCIPLegado *********");
        try {
            DDABeneficiario beneficiarioLegado = new DDABeneficiario();
            beneficiarioLegado.setId(beneficiario.getId());
            beneficiarioLegado.setNumCPFCNPJ(beneficiario.getNumCpfCnpj());
            beneficiarioLegado.setCodSituacaoBeneficiarioCIP(beneficiario.getSituacaoBeneficiarioDDA().getCodSituacaoBeneficiario());
            beneficiarioLegado.setDataHoraAtualizacao(beneficiario.getDataHoraUltimaAtualizacao());

            beneficiarioLegado.setListaDDABeneficiarioCooperativa(obtemListaCooperativa(beneficiario, beneficiarioLegado));
            beneficiarioLegado.setListaDDABeneficiarioOperacao(obtemListaOperacaoInapto(beneficiario, beneficiarioLegado));

            getDao().incluir(beneficiarioLegado);
        } catch (BancoobException e) {
            tratarException(e, ERRO_INCLUIR_BENEFICIARIO_LEGADO);
        }
    }

    /**
     * Método responsável por
     * 
     * @param beneficiarioLegado
     * @param novaSituacao
     * @throws IntegracaoCipException void
     * 
     */
    private void alterarRegistroLegadoInapto(BeneficiarioDDA beneficiario, DDABeneficiario beneficiarioLegado) throws IntegracaoCipException {
        getLogger().debug("********* alterarRegistroCIPLegado *********");
        try {
            beneficiarioLegado.setCodSituacaoBeneficiarioCIP(SituacaoBeneficiarioDDA.INAPTO);
            beneficiarioLegado.setDataHoraAtualizacao(beneficiario.getDataHoraUltimaAtualizacao());
            beneficiarioLegado.setListaDDABeneficiarioCooperativa(obtemListaCooperativa(beneficiario, beneficiarioLegado));

            getDao().alterar(beneficiarioLegado);
        } catch (BancoobException e) {
            tratarException(e, ERRO_ALTERAR_BENEFICIARIO_LEGADO);
        }
    }

    /**
     * Método res ponsável por
     * 
     * @param beneficiario
     * @return List<DDABeneficiarioCooperativa>
     * 
     */
    private List<DDABeneficiarioCooperativa> obtemListaCooperativa(BeneficiarioDDA beneficiario, DDABeneficiario beneficiarioLegado) throws IntegracaoCipException {
        getLogger().debug("********* geraListaCooperativa *********");
        List<DDABeneficiarioCooperativa> listaBeneficiarioCooperativa;
        if (ObjectUtil.isEmpty(beneficiarioLegado.getListaDDABeneficiarioCooperativa())) {
            listaBeneficiarioCooperativa = new ArrayList<DDABeneficiarioCooperativa>();
        } else {
            listaBeneficiarioCooperativa = beneficiarioLegado.getListaDDABeneficiarioCooperativa();
        }
        Integer numCoop = null;
        for (BeneficiarioInstituicao beneficiarioInstituicao : beneficiario.getListaBeneficiarioInstituicao()) {
            try {
                numCoop = getNumCooperativa(beneficiarioInstituicao);
                if (validaCooperativaNaoListada(listaBeneficiarioCooperativa, numCoop)) {
                    listaBeneficiarioCooperativa.add(new DDABeneficiarioCooperativa(beneficiarioLegado, getIdPessoaLegado(beneficiario, beneficiarioInstituicao), numCoop));
                }
            } catch (BancoobException e) {
                tratarException(e, ERRO_LISTAR_COOPERATIVA);
            }
        }
        return listaBeneficiarioCooperativa;
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @param beneficiarioInstituicao
     * @return
     * @throws ComumException Integer
     * 
     */
    private Integer getIdPessoaLegado(BeneficiarioDDA beneficiario, BeneficiarioInstituicao beneficiarioInstituicao) throws ComumException {
        PessoaDto dto = getCapesDelegate().obterPessoa(beneficiario.getNumCpfCnpj(), beneficiarioInstituicao.getIdInstituicao());
        if (!ObjectUtil.isNull(dto) && !ObjectUtil.isEmpty(dto.getIdPessoaLegado())) {
            return dto.getIdPessoaLegado();
        } else {
            throw new ComumException("integracaocip.erro.integracao.pessoa.legado.nao.encontrado", beneficiarioInstituicao.getIdInstituicao());
        }
    }

    /**
     * Método responsável por
     * 
     * @param beneficiarioInstituicao
     * @return
     * @throws ComumException Integer
     * 
     */
    private Integer getNumCooperativa(BeneficiarioInstituicao beneficiarioInstituicao) throws ComumException {
        InstituicaoDto dto = sciDelegate.obterInstituicaoCache(beneficiarioInstituicao.getIdInstituicao());
        if (!ObjectUtil.isNull(dto) && !ObjectUtil.isEmpty(dto.getNumero())) {
            return Integer.valueOf(dto.getNumero());
        } else {
            throw new ComumException("integracaocip.erro.integracao.instituicao.nao.encontrada", beneficiarioInstituicao.getIdInstituicao());
        }
    }

    /**
     * Método responsável por
     * 
     * @param listaBeneficiarioCooperativa
     * @param numCoop
     * @return boolean
     * 
     */
    private boolean validaCooperativaNaoListada(List<DDABeneficiarioCooperativa> listaBeneficiarioCooperativa, Integer numCoop) {
        for (DDABeneficiarioCooperativa beneficiarioCooperativa : listaBeneficiarioCooperativa) {
            if (beneficiarioCooperativa.getNumCoop().equals(numCoop)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @param beneficiarioLegado
     * @return
     * @throws IntegracaoCipException List<DDABeneficiarioOperacao>
     * 
     */
    private List<DDABeneficiarioOperacao> obtemListaOperacaoInapto(BeneficiarioDDA beneficiario, DDABeneficiario beneficiarioLegado) throws IntegracaoCipException {
        getLogger().debug("********* obtemListaOperacaoInapto *********");
        List<DDABeneficiarioOperacao> listaBeneficiarioOperacao = new ArrayList<DDABeneficiarioOperacao>();

        for (DDAOperacao operacao : getDao().obtemOperacoesInapto()) {
            listaBeneficiarioOperacao.add(new DDABeneficiarioOperacao(beneficiarioLegado, operacao, new DateTimeDB()));
        }
        return listaBeneficiarioOperacao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarBeneficiarioLegadoServico#replicarExclusaoBeneficiarioLegado(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA)
     */
    public void replicarExclusaoBeneficiarioLegado(Long idBeneficiarioDDA) throws IntegracaoCipException {
        getLogger().debug("********* replicarExclusaoBeneficiarioLegado *********");
        try {
            DDABeneficiario beneficiarioLegado = getDao().obter(idBeneficiarioDDA);
            if (!ObjectUtil.isNull(beneficiarioLegado)) {
                getDao().excluir(beneficiarioLegado.getId());
            }
        } catch (BancoobException e) {
            tratarException(e, ERRO_EXCLUIR_BENEFICIARIO_LEGADO);
        }
    }

    /**
     * Método responsável por
     * 
     * @param e
     * @param msg
     * @throws IntegracaoCipException void
     * 
     */
    private void tratarException(BancoobException e, String msg) throws IntegracaoCipException {
        getLogger().erro(e, msg);
        throw new IntegracaoCipException(e);
    }
}
