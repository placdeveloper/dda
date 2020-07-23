/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         BeneficiarioCipDaoImpl.java
 * Data Criação:    Nov 1, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoStatusBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.IFBeneficiarioAlerta;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioConvenio;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioRepresentante;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.SDDAStringUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;

/**
 * BeneficiarioCipDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class BeneficiarioCipDaoImpl extends IntegracaoCipCrudDaoDB2<BeneficiarioDDA> implements BeneficiarioCipDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_beneficiariocip.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-beneficiariocip";

    private static final String ID_BENEFICIARIO_DDA = "idBeneficiarioDDA";

    /**
     * 
     */
    public BeneficiarioCipDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, BeneficiarioDDA.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao#obterBeneficiario(java.lang.String)
     */
    public BeneficiarioDDA obterBeneficiarioDiferenteDeSemCadastro(String numCpfCnpj) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numCpfCnpj", numCpfCnpj);
        return obterDados("OBTER_BENEFICIARIODDA_POR_CPF_CNPJ_DIFERENTE_DE_S", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao#obterBeneficiario(java.lang.String)
     */
    public BeneficiarioDDA obterBeneficiario(String numCpfCnpj) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numCpfCnpj", numCpfCnpj);
        return obterDados("OBTER_BENEFICIARIODDA_POR_CPF_CNPJ", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao#obterBeneficiario(java.lang.String)
     */
    public BeneficiarioDDA obterBeneficiario(String numCpfCnpj, Boolean isLocarTabela) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        BeneficiarioDDA beneficiarioDDA = new BeneficiarioDDA();
        try {
            comando = getComando("OBTER_BENEFICIARIODDA_POR_CPF_CNPJ_LOCK");
            comando.adicionarVariavel("numCpfCnpj", numCpfCnpj);
            comando.adicionarVariavel("isLocarTabela", isLocarTabela);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                beneficiarioDDA = setBeneficiarioDDA(rs);
                beneficiarioDDA.setListaBeneficiarioInstituicao(listarBeneficiarioInstituicao(beneficiarioDDA.getId()));
                beneficiarioDDA.setListaHistoricoStatusBeneficiarioDDA(listarHistoricoStatusBeneficiarioDDA(beneficiarioDDA.getId()));
                beneficiarioDDA.setListaIFBeneficiarioAlerta(listarIFBeneficiarioAlerta(beneficiarioDDA.getId()));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return beneficiarioDDA;
    }

    /**
     * Método responsável por
     * 
     * @param idBeneficiarioDDA
     * @param isLocarTabela
     * @return
     * @throws ComumException List<BeneficiarioInstituicao>
     * 
     */
    private List<BeneficiarioInstituicao> listarBeneficiarioInstituicao(Long idBeneficiarioDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<BeneficiarioInstituicao> listaBeneficiarioInstituicao = new ArrayList<BeneficiarioInstituicao>();
        try {
            comando = getComando("LISTAR_BENEFICIARIO_INSTITUICAO");
            comando.adicionarVariavel("idBeneficiarioDDA", idBeneficiarioDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaBeneficiarioInstituicao.add(setBeneficiarioInstituicao(rs));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaBeneficiarioInstituicao;
    }

    /**
     * Método responsável por
     * 
     * @param idBeneficiarioDDA
     * @return
     * @throws ComumException List<HistoricoStatusBeneficiarioDDA>
     * 
     */
    private List<HistoricoStatusBeneficiarioDDA> listarHistoricoStatusBeneficiarioDDA(Long idBeneficiarioDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<HistoricoStatusBeneficiarioDDA> listaHistoricoStatusBeneficiarioDDA = new ArrayList<HistoricoStatusBeneficiarioDDA>();
        try {
            comando = getComando("LISTAR_HISTORICO_STATUS_BENEFICIARIO");
            comando.adicionarVariavel("idBeneficiarioDDA", idBeneficiarioDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaHistoricoStatusBeneficiarioDDA.add(setHistoricoStatusBeneficiarioDDA(rs));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaHistoricoStatusBeneficiarioDDA;
    }

    /**
     * Método responsável por
     * 
     * @param idBeneficiarioDDA
     * @return
     * @throws ComumException List<IFBeneficiarioAlerta>
     * 
     */
    private List<IFBeneficiarioAlerta> listarIFBeneficiarioAlerta(Long idBeneficiarioDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<IFBeneficiarioAlerta> listaIFBeneficiarioAlerta = new ArrayList<IFBeneficiarioAlerta>();
        try {
            comando = getComando("LISTAR_IF_BENEFICIARIO_ALERTA");
            comando.adicionarVariavel("idBeneficiarioDDA", idBeneficiarioDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaIFBeneficiarioAlerta.add(setIFBeneficiarioAlerta(rs));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaIFBeneficiarioAlerta;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException IFBeneficiarioAlerta
     * 
     */
    private IFBeneficiarioAlerta setIFBeneficiarioAlerta(ResultSet rs) throws SQLException {
        IFBeneficiarioAlerta iFBeneficiarioAlerta = new IFBeneficiarioAlerta();
        iFBeneficiarioAlerta.setId(rs.getLong("idIfBeneficiarioAlerta"));
        iFBeneficiarioAlerta.setBeneficiarioDDA(new BeneficiarioDDA(rs.getLong("idbeneficiarioDDA")));
        iFBeneficiarioAlerta.setCodIspbDestinatarioOriginalAlerta(rs.getString("codIspbPartDestinatarioOrigDRAlert"));
        iFBeneficiarioAlerta.setDataHoraAtualizacao(rs.getDate("dataHoraAtualizacao") != null ? new DateTimeDB(rs.getDate("dataHoraAtualizacao").getTime()) : null);
        return iFBeneficiarioAlerta;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException HistoricoStatusBeneficiarioDDA
     * 
     */
    private HistoricoStatusBeneficiarioDDA setHistoricoStatusBeneficiarioDDA(ResultSet rs) throws SQLException {
        HistoricoStatusBeneficiarioDDA historicoStatusBeneficiarioDDA = new HistoricoStatusBeneficiarioDDA();
        historicoStatusBeneficiarioDDA.setId(rs.getLong("idHistoricoStatusBeneficiario"));
        historicoStatusBeneficiarioDDA.setBeneficiarioDDA(new BeneficiarioDDA(rs.getLong("idBeneficiarioDDA")));
        historicoStatusBeneficiarioDDA.setSituacaoBeneficiarioDDA(new SituacaoBeneficiarioDDA(rs.getString("codSituacaoBeneficiario")));
        historicoStatusBeneficiarioDDA.setDataHoraCadastro(rs.getDate("dataHoraCadastro") != null ? new DateTimeDB(rs.getDate("dataHoraCadastro").getTime()) : null);
        return historicoStatusBeneficiarioDDA;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return BeneficiarioInstituicao
     * @throws SQLException
     * 
     */
    private BeneficiarioInstituicao setBeneficiarioInstituicao(ResultSet rs) throws SQLException {
        BeneficiarioInstituicao beneficiarioInstituicao = new BeneficiarioInstituicao();
        beneficiarioInstituicao.setId(rs.getLong("idBeneficiarioInstituicao"));
        beneficiarioInstituicao.setBeneficiarioDDA(new BeneficiarioDDA(rs.getLong("idBeneficiarioDDA")));
        beneficiarioInstituicao.setIdInstituicao(rs.getInt("idInstituicao"));
        beneficiarioInstituicao.setDataInicioRelacionamento(rs.getDate("dataInicioRelacionamento") != null ? new DateTimeDB(rs.getDate("dataInicioRelacionamento").getTime()) : null);
        return beneficiarioInstituicao;
    }

    /**
     * Método responsável por setar os dados do DDA.BeneficiarioDDA
     * 
     * @param rs
     * @return
     * @throws SQLException BeneficiarioDDA
     * 
     */
    private BeneficiarioDDA setBeneficiarioDDA(ResultSet rs) throws SQLException {
        BeneficiarioDDA beneficiarioDDA = new BeneficiarioDDA();
        beneficiarioDDA.setId(rs.getLong("idBeneficiarioDDA"));
        beneficiarioDDA.setNumIdentBeneficiario(getNullableLong("numIdentificaBeneficiario", rs));
        beneficiarioDDA.setNumCtrlDDA(rs.getString("numCtrlDDA"));
        beneficiarioDDA.setBolOrigemSicoob(rs.getLong("BolOrigemSicoob") == 1);
        beneficiarioDDA.setCodTipoPessoa(rs.getString("codTipoPessoa"));
        beneficiarioDDA.setNumCpfCnpj(rs.getString("numCpfCnpj"), TipoPessoaEnum.getBy(rs.getString("codTipoPessoa")));
        beneficiarioDDA.setSituacaoBeneficiarioDDA(new SituacaoBeneficiarioDDA(rs.getString("codSituacaoBeneficiario")));
        beneficiarioDDA.setIdProduto(rs.getInt("idProduto"));
        beneficiarioDDA.setDataHoraUltimaAtualizacao(rs.getDate("dataHoraUltimaAtualizacao") != null ? new DateTimeDB(rs.getDate("dataHoraUltimaAtualizacao").getTime()) : null);
        beneficiarioDDA.setDataInicioRelacionamento(rs.getDate("dataInicioRelacionamento") != null ? new DateTimeDB(rs.getDate("dataInicioRelacionamento").getTime()) : null);
        beneficiarioDDA.setNumRefAtualCadBeneficiario(getNullableLong("numRefAtualCadBeneficiario", rs));
        beneficiarioDDA.setNumSeqAtualCadBeneficiario(getNullableLong("numSeqAtualCadBeneficiario", rs));
        return beneficiarioDDA;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao#obterBeneficiario(java.math.BigInteger, br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum)
     */
    public BeneficiarioDDA obterBeneficiario(BigInteger numCpfCnpj, TipoPessoaEnum tipoPessoaEnum) throws ComumException {
        if (tipoPessoaEnum.equals(TipoPessoaEnum.PF)) {
            return obterBeneficiario(SDDAStringUtil.incluirZerosCPF(numCpfCnpj));
        } else {
            return obterBeneficiario(SDDAStringUtil.incluirZerosCNPJ(numCpfCnpj));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao#obterBeneficiario(java.lang.Long)
     */
    public BeneficiarioDDA obterBeneficiario(Long numIdentBeneficiario) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numIdentBeneficiario", numIdentBeneficiario);
        return obterDados("OBTER_BENEFICIARIODDA_POR_NUM_IDENT", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao#removerViculoSicoobBeneficiario(java.lang.Number)
     */
    public void removerViculoSicoobBeneficiario(Long idBeneficiarioDDA) throws ComumException {
        remover(idBeneficiarioDDA, "REMOVER_VINCULO_BENEFICIARIO_COM_SICOOB");
        remover(idBeneficiarioDDA, "REMOVER_VINCULO_BENEFICIARIO_INSTITUICAO_COM_SICOOB");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao#removerBeneficiarioInstituicao(java.lang.Long)
     */
    public void removerBeneficiarioInstituicao(Long idBeneficiarioDDA) throws ComumException {
        remover(idBeneficiarioDDA, "REMOVER_BENEFICIARIO_INSTITUICAO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao#removerIFBeneficiarioAlerta(java.lang.Long)
     */
    public void removerIFBeneficiarioAlerta(Long idBeneficiarioDDA) throws ComumException {
        remover(idBeneficiarioDDA, "REMOVER_IF_BENEFICIARIO_ALERTA");
    }

    /**
     * @param idBeneficiarioDDA
     * @param nomeComando
     * @throws ComumException void
     * 
     */
    private void remover(Long idBeneficiarioDDA, String nomeComando) throws ComumException {
        executarUpdate(nomeComando, setParametroId(idBeneficiarioDDA, ID_BENEFICIARIO_DDA));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao#obterMensagemDDABeneficiario(java.lang.Long)
     */
    public MensagemDDABeneficiario obterMensagemDDABeneficiario(Long idMensagem) throws ComumException {
        getLogger().debug("*******INICIO obterMensagemDDABeneficiario*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDABeneficiario mensagemDDABeneficiario = null;
        try {
            comando = getComando("OBTER_MENSAGEMDDA_BENEFICIARIO");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDABeneficiario = montarMensagemDDABeneficiario(rs, Boolean.FALSE);
                mensagemDDABeneficiario.getListaMensagemDDABeneficiarioConvenio().addAll(listarMensagemDDABeneficiarioConvenio(idMensagem));
                mensagemDDABeneficiario.getListaMensagemDDABeneficiarioRepresentante().addAll(listarMensagemDDABeneficiarioRepresentante(idMensagem));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        getLogger().debug("*******FIM obterMensagemDDABeneficiario*******");
        return mensagemDDABeneficiario;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao#obterMensagemDDABeneficiarioAtualizaReferencias(java.lang.Long)
     */
    public MensagemDDABeneficiario obterMensagemDDABeneficiarioAtualizaReferencias(Long idMensagem) throws ComumException {
        getLogger().debug("Inicio Classe: BeneficiarioCipDaoImpl Metodo: obterMensagemDDABeneficiarioAtualizaReferencias IdMensagemDDA = " + idMensagem);
        MensagemDDABeneficiario mensagem = obterMensagemDDABeneficiarioReferencias(idMensagem);
        getLogger().debug("Busca feita com Sucesso - obterMensagemDDABeneficiarioReferencias");
        if (!ObjectUtil.isNull(mensagem) && !ObjectUtil.isEmpty(mensagem.getNumRefAtualCadBeneficiario()) && !ObjectUtil.isEmpty(mensagem.getNumSeqAtualCadBeneficiario())) {
            atualizaMensagemDDABeneficiario(mensagem.getId(), mensagem.getNumRefAtualCadBeneficiario(), mensagem.getNumSeqAtualCadBeneficiario());
        }
        getLogger().debug("FIM Classe: BeneficiarioCipDaoImpl Metodo: obterMensagemDDABeneficiarioAtualizaReferencias");
        return mensagem;
    }

    /**
     * Método responsável por
     * 
     * @param idMensagem
     * @return
     * @throws ComumException MensagemDDABeneficiario
     * 
     */
    private MensagemDDABeneficiario obterMensagemDDABeneficiarioReferencias(Long idMensagem) throws ComumException {
        getLogger().debug("*******INICIO obterMensagemDDABeneficiario*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDABeneficiario mensagemDDABeneficiario = null;
        try {
            comando = getComando("OBTER_MENSAGEMDDA_BENEFICIARIO_REFERENCIAS");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDABeneficiario = montarMensagemDDABeneficiario(rs, Boolean.TRUE);
                mensagemDDABeneficiario.getListaMensagemDDABeneficiarioConvenio().addAll(listarMensagemDDABeneficiarioConvenio(idMensagem));
                mensagemDDABeneficiario.getListaMensagemDDABeneficiarioRepresentante().addAll(listarMensagemDDABeneficiarioRepresentante(idMensagem));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        getLogger().debug("*******FIM obterMensagemDDABeneficiario*******");
        return mensagemDDABeneficiario;
    }

    /**
     * Método responsável por
     * 
     * @param idMensagem
     * @return
     * @throws ComumException List<MensagemDDABeneficiarioConvenio>
     * 
     */
    private List<MensagemDDABeneficiarioConvenio> listarMensagemDDABeneficiarioConvenio(Long idMensagem) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MensagemDDABeneficiarioConvenio> listaMensagemDDABeneficiarioConvenio = new ArrayList<MensagemDDABeneficiarioConvenio>();
        try {
            getLogger().debug("*******INICIO listarMensagemDDABeneficiarioConvenio*******");
            comando = getComando("OBTER_MENSAGEMDDA_BENEFICIARIO_CONVENIO");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            conn = estabelecerConexao();
            getLogger().debug(comando.getSqlEmUso());
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaMensagemDDABeneficiarioConvenio.add(montarMensagemDDABeneficiarioConvenio(rs));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        getLogger().debug("*******FIM listarMensagemDDABeneficiarioConvenio*******");
        return listaMensagemDDABeneficiarioConvenio;
    }

    /**
     * Método responsável por
     * 
     * @param idMensagem
     * @return
     * @throws ComumException List<MensagemDDABeneficiarioRepresentante>
     * 
     */
    private List<MensagemDDABeneficiarioRepresentante> listarMensagemDDABeneficiarioRepresentante(Long idMensagem) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MensagemDDABeneficiarioRepresentante> listaMensagemDDABeneficiarioRepresentante = new ArrayList<MensagemDDABeneficiarioRepresentante>();
        try {
            getLogger().debug("*******INICIO listarMensagemDDABeneficiarioRepresentante*******");
            comando = getComando("OBTER_MENSAGEMDDA_BENEFICIARIO_REPRESENTANTE");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaMensagemDDABeneficiarioRepresentante.add(montarMensagemDDABeneficiarioRepresentante(rs));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        getLogger().debug("*******FIM listarMensagemDDABeneficiarioRepresentante*******");
        return listaMensagemDDABeneficiarioRepresentante;
    }

    /**
     * Método responsável por
     * 
     * @param idMensagemDDABeneficiario
     * @param numRefAtualCadBeneficiario
     * @param numSeqAtualCadBeneficiario
     * @throws ComumException void
     * 
     */
    private void atualizaMensagemDDABeneficiario(Long idMensagemDDABeneficiario, Long numRefAtualCadBeneficiario, Long numSeqAtualCadBeneficiario) throws ComumException {
        getLogger().debug("Inicio Classe: PagadorCipDaoImpl Metodo: atualizaMensagemDDABeneficiario");
        Map<String, Object> parametros = getMapaParametro(idMensagemDDABeneficiario, "idMensagemDDABeneficiario");
        parametros.put("numRefAtualCadBeneficiario", numRefAtualCadBeneficiario);
        parametros.put("numSeqAtualCadBeneficiario", numSeqAtualCadBeneficiario);
        executarUpdate("ATUALIZAR_MENSAGEMDDA_BENEFICIARIO", parametros);
        getLogger().debug("FIM Classe: BeneficiarioCipDaoImpl Metodo: atualizaMensagemDDABeneficiario");

    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDABeneficiario
     * 
     */
    private MensagemDDABeneficiario montarMensagemDDABeneficiario(ResultSet rs, Boolean existeNumIdentificador) throws SQLException {
        MensagemDDABeneficiario mensagem = new MensagemDDABeneficiario(rs.getLong("idMensagemDDA"), rs.getString("numCpfCnpjBeneficiario"), rs.getString("codTipoPessoaBeneficiario"),
                ObjectUtil.isEmpty(rs.getString("nomeBeneficiario")) ? null : rs.getString("nomeBeneficiario"), ObjectUtil.isEmpty(rs.getString("nomeFantasiaBeneficiario")) ? null
                        : rs.getString("nomeFantasiaBeneficiario"), ObjectUtil.isEmpty(rs.getString("codSituacaoBeneficiario")) ? null : rs.getString("codSituacaoBeneficiario"), ObjectUtil.isNull(rs
                        .getDate("dataHoraSituacao")) ? null : DateUtil.getDateTimeDB(rs.getDate("dataHoraSituacao")), ObjectUtil.isEmpty(rs.getString("codSituacaoRelacionamentoBeneficiario")) ? null
                        : rs.getString("codSituacaoRelacionamentoBeneficiario"), ObjectUtil.isNull(rs.getDate("dataInicioRelacionamento")) ? null : rs.getDate("dataInicioRelacionamento"),
                rs.getDate("dataFimRelacionamento"), getNullableLong("numRefAtualCadBeneficiario", rs), getNullableLong("numSeqAtualCadBeneficiario", rs));

        if (existeNumIdentificador) {
            mensagem.setNumIdentificadorBenficiario(getNullableLong("numIdentificaBeneficiario", rs));
        }

        if (ObjectUtil.isNull(mensagem.getMensagemDDA())) {
            mensagem.setMensagemDDA(obterMensagemDDA(mensagem.getId()));
        }
        return mensagem;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDABeneficiarioConvenio
     * 
     */
    private MensagemDDABeneficiarioConvenio montarMensagemDDABeneficiarioConvenio(ResultSet rs) throws SQLException {
        MensagemDDABeneficiarioConvenio mensagem = new MensagemDDABeneficiarioConvenio(rs.getLong("idMensagemDDABeneficiarioConvenio"), rs.getInt("numAgencia"), rs.getLong("numConta"),
                rs.getString("numClienteOuConvenio"), rs.getString("codTipoProdutoDDA"), rs.getString("codSituacaoConvenioDDA"), rs.getDate("dataInicioRelacionamento"),
                rs.getDate("dataFimRelacionamento"), rs.getString("codISPBParticipanteIncorporado"), rs.getString("codTipoOperacao"));

        return mensagem;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDABeneficiarioRepresentante
     * 
     */
    private MensagemDDABeneficiarioRepresentante montarMensagemDDABeneficiarioRepresentante(ResultSet rs) throws SQLException {
        return new MensagemDDABeneficiarioRepresentante(rs.getString("numCpfCnpjRepresentante"), rs.getString("codTipoPessoaRepresentante"));
    }

}
