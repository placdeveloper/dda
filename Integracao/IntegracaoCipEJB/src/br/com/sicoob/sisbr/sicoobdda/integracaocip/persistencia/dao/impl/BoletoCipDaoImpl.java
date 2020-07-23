/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         IntegracaoCipDaoImpl.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDAGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAAceite;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMulta;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;

/**
 * BoletoCipDaoImpl
 * 
 * @author Rafael.Silva
 */
public class BoletoCipDaoImpl extends IntegracaoCipCrudDaoDB2<SicoobDDAEntidade> implements BoletoCipDao {

    private static final String LOG_GRAVAR_BOLETO_ADDA106 = "Gravar Boleto ADDA106";

    private static final String ID_BOLETO_DDA = "idBoletoDDA";

    private static final String ATIVO = "ATIVO";
    private static final String EM_ANDAMENTO = "EM ANDAMENTO";

    public static final String ARQUIVO_QUERIES = "sicoobdda_boletocip.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-boletocip";
    private static final String CONSULTA_BOLETO_PAGAMENTO = "CONSULTA_BOLETO_PAGAMENTO";

    /**
     * IntegracaoCipDaoImpl
     */
    public BoletoCipDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, "", "");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterMensagemDDABoleto(java.lang.Long)
     */
    public MensagemDDABoleto obterMensagemDDABoleto(Long idMensagem) {
        getLogger().debug("*******INICIO obterMensagemDDABoleto()*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDABoleto mensagemDDABoleto = null;

        try {
            comando = getComando("OBTER_MENSAGEMDDA_BOLETO");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug("idMensagem = " + idMensagem);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDABoleto = setMensagemDDABoletoCompleto(rs, Boolean.FALSE);
            }

            return obterMensagemDDABoleto(idMensagem, conn, mensagemDDABoleto);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterMensagemDDAConsultaBoleto(java.lang.Long)
     */
    public MensagemDDAConsultaBoleto obterMensagemDDAConsultaBoleto(Long idMensagem) throws ComumException {
        getLogger().debug("*******INICIO obterMensagemDDAConsultaBoleto()*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDAConsultaBoleto mensagemDDAConsultaBoleto = new MensagemDDAConsultaBoleto();

        try {
            comando = getComando("OBTER_MENSAGEMDDA_CONSULTA_BOLETO");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug("idMensagem = " + idMensagem);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDAConsultaBoleto.setId(rs.getLong(1));
                mensagemDDAConsultaBoleto.setNumCodigoBarra(rs.getString(2));
                mensagemDDAConsultaBoleto.setNumCodBarrasCampoLivre(!ObjectUtil.isEmpty(mensagemDDAConsultaBoleto.getNumCodigoBarra())
                        ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(mensagemDDAConsultaBoleto.getNumCodigoBarra())
                        : null);
                mensagemDDAConsultaBoleto.setCodSituacaoBoleto(getNullableShort(3, rs));
                mensagemDDAConsultaBoleto.setNumIdentBoletoInicial(getNullableLong(4, rs));
                mensagemDDAConsultaBoleto.setNumIdentBoletoFinal(getNullableLong(5, rs));
                mensagemDDAConsultaBoleto.setCodTipoBoletoConsultado(rs.getString(6));
                mensagemDDAConsultaBoleto.setMensagemDDA(obterMensagemDDA(idMensagem));
            }

        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return mensagemDDAConsultaBoleto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterMensagemDDAAceite(java.lang.Long)
     */
    public MensagemDDAAceite obterMensagemDDAAceite(Long idMensagem) {
        getLogger().debug("*******INICIO obterMensagemDDAAceite()*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDAAceite mensagemDDAAceite = null;

        try {
            comando = getComando("OBTER_MENSAGEMDDA_ACEITE");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug("idMensagem = " + idMensagem);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDAAceite = setMensagemDDAAceite(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return mensagemDDAAceite;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterMensagemDDATerceiroAutorizado(java.lang.Long)
     */
    public MensagemDDATerceiroAut obterMensagemDDATerceiroAutorizado(Long idMensagem) {
        getLogger().debug("*******INICIO obterMensagemDDATerceiroAut()*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDATerceiroAut mensagemDDATerceiroAut = null;

        try {
            comando = getComando("OBTER_MENSAGEMDDA_TERCEIRO_AUTORIZADO");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug("idMensagem = " + idMensagem);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDATerceiroAut = setMensagemDDATerceiroAut(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return mensagemDDATerceiroAut;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterBoletoDDABaixaOperacional(java.lang.Long)
     */
    public BoletoDDABaixaOper obterBoletoDDABaixaOperacional(Long numIdentificadorBaixaOper) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numIdentificadorBaixaOper", numIdentificadorBaixaOper);
        return obterDados("OBTER_BOLETO_DDA_BAIXA_OPERACIONAL_NUM_IDENTIFICACAO_BAIXA_OPERACIONAL", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterBoletoDDABaixaOperacional(java.lang.Long, java.lang.Long)
     */
    public BoletoDDABaixaOper obterBoletoDDABaixaOperacional(Long numIdentificadorBoleto, Long numIdentificadorBaixaOper) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numIdentificadorBoleto", numIdentificadorBoleto);
        parametros.put("numIdentificadorBaixaOper", numIdentificadorBaixaOper);
        return obterDados("OBTER_BOLETO_DDA_BAIXA_OPER_NUM_IDENT_BAIXA_OPER_E_BOLETO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterBoletoDDABaixaOperacional(java.lang.String, java.math.BigDecimal,
     *      br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public List<BoletoDDABaixaOper> listarBoletoDDABaixaOperacional(String numCodigoBarra, BigDecimal valorBaixa, DateTimeDB dataHoraMovimento) throws ComumException {
        Map<String, Object> parametros = setParametroNumCodBarra(numCodigoBarra);
        parametros.put("valorBaixa", valorBaixa);
        parametros.put("dataHoraMovimento", dataHoraMovimento);
        return listar("OBTER_BOLETO_DDA_BAIXA_OPERACIONAL", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterBoletoDDABaixaEfetiva(java.lang.String)
     */
    public BoletoDDABaixaEfet obterBoletoDDABaixaEfetiva(String numCodigoBarra) throws ComumException {
        Map<String, Object> parametros = setParametroNumCodBarra(numCodigoBarra);
        return obterDados("OBTER_BOLETO_DDA_BAIXA_EFETIVAL_CODIGO_BARRA", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterBoletoDDABaixaEfetiva(java.lang.Long, java.lang.Long)
     */
    public BoletoDDABaixaEfet obterBoletoDDABaixaEfetiva(Long numIdentificadorBoletoCip, Long numIdentificadorBaixaEfet) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numIdentificadorBaixaEfet", numIdentificadorBaixaEfet);
        parametros.put("numIdentificadorBoletoCip", numIdentificadorBoletoCip);
        return obterDados("OBTER_BOLETO_DDA_BAIXA_EFETIVA_NUM_IDENTIFICADOR_BOL_E_BAIXA_EFETIVA", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterBoletoDDA(java.lang.String, java.lang.Integer)
     */
    public BoletoDDA obterBoletoDDA(String numCodigoBarra, Integer situacaoBoleto) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numCodigoBarra", numCodigoBarra);

        if (!ObjectUtil.isNull(situacaoBoleto)) {
            parametros.put("codSituacaoBoleto", situacaoBoleto);
        }

        return obterDados("OBTER_BOLETO_DDA_CODIGO_BARRA_NUM_IDENTIFICACAO", parametros);
    }

    /**
     * Método responsável por
     * 
     * @param idBoletoDDA
     * @return
     * @throws ComumException List<BoletoDDABaixaEfet>
     * 
     */
    private List<BoletoDDABaixaEfet> listarBoletoDDABaixaEfet(Long idBoletoDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<BoletoDDABaixaEfet> listaBoletoDDABaixaEfet = new ArrayList<BoletoDDABaixaEfet>();
        try {
            comando = getComando("OBTER_BOLETO_DDA_BAIXA_EFETIVA");
            comando.adicionarVariavel(ID_BOLETO_DDA, idBoletoDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                BoletoDDABaixaEfet boletoDDABaixaEfet = setBoletoDDABaixaEfet(rs);
                listaBoletoDDABaixaEfet.add(boletoDDABaixaEfet);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaBoletoDDABaixaEfet;
    }

    /**
     * Método responsável por
     * 
     * @param idBoletoDDA
     * @return
     * @throws ComumException List<BoletoDDABaixaOper>
     * 
     */
    private List<BoletoDDABaixaOper> listarBoletoDDABaixaOper(Long idBoletoDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<BoletoDDABaixaOper> listaBoletoDDABaixaOper = new ArrayList<BoletoDDABaixaOper>();
        try {
            comando = getComando("OBTER_BOLETO_DDA_BAIXA_OPER");
            comando.adicionarVariavel(ID_BOLETO_DDA, idBoletoDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                BoletoDDABaixaOper boletoDDABaixaOper = setBoletoDDABaixaOper(rs);
                listaBoletoDDABaixaOper.add(boletoDDABaixaOper);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaBoletoDDABaixaOper;
    }

    /**
     * Método responsável por
     * 
     * @param idBoletoDDA
     * @return
     * @throws ComumException List<BoletoDDAGrupoCalculo>
     * 
     */
    private List<BoletoDDAGrupoCalculo> listarBoletoDDAGrupoCalculo(Long idBoletoDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<BoletoDDAGrupoCalculo> listaBoletoDDAGrupoCalculo = new ArrayList<BoletoDDAGrupoCalculo>();
        try {
            comando = getComando("OBTER_BOLETO_DDA_GRUPO_CALCULO");
            comando.adicionarVariavel(ID_BOLETO_DDA, idBoletoDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                BoletoDDAGrupoCalculo boletoDDAGrupoCalculo = setBoletoDDAGrupoCalculo(rs);
                listaBoletoDDAGrupoCalculo.add(boletoDDAGrupoCalculo);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaBoletoDDAGrupoCalculo;
    }

    /**
     * Método responsável por
     * 
     * @param idBoletoDDA
     * @return
     * @throws ComumException List<BoletoDDATerceiroAut>
     * 
     */
    private List<BoletoDDATerceiroAut> listarBoletoDDATerceiroAutorizado(Long idBoletoDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<BoletoDDATerceiroAut> listaBoletoDDATerceiroAut = new ArrayList<BoletoDDATerceiroAut>();
        try {
            comando = getComando("OBTER_BOLETO_DDA_TERCEIRO_AUTORIZADO");
            comando.adicionarVariavel(ID_BOLETO_DDA, idBoletoDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                BoletoDDATerceiroAut boletoDDATerceiroAut = setBoletoDDATerceiroAut(rs);
                listaBoletoDDATerceiroAut.add(boletoDDATerceiroAut);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaBoletoDDATerceiroAut;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterBoletoDDA(java.lang.Long)
     */
    public BoletoDDA obterBoletoDDA(Long numIdentcTit) throws ComumException {
        debug("### Obtendo boletoDDA...");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numIdentcTit", numIdentcTit);
        return obterDados("OBTER_BOLETO_DDA_NUMERO_IDENTIFICAO_BOLETO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterBoletoDDA(java.lang.Long)
     */
    public BoletoDDA obterBoletoDDALock(Long numIdentcTit) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        BoletoDDA boletoDDA = null;
        try {
            comando = getComando("OBTER_BOLETO_DDA_NUMERO_IDENTIFICAO_BOLETO_LOCK");
            comando.adicionarVariavel("numIdentcTit", numIdentcTit);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                boletoDDA = setBoletoDDA(rs);
                boletoDDA.setListaBoletoDDABaixaEfet(listarBoletoDDABaixaEfet(boletoDDA.getId()));
                boletoDDA.setListaBoletoDDABaixaOper(listarBoletoDDABaixaOper(boletoDDA.getId()));
                boletoDDA.setListaBoletoDDAGrupoCalculo(listarBoletoDDAGrupoCalculo(boletoDDA.getId()));
                boletoDDA.setListaBoletoDDATerceiroAutorizado(listarBoletoDDATerceiroAutorizado(boletoDDA.getId()));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return boletoDDA;
    }

    /**
     * @param rs
     * @return
     * @throws SQLException BoletoDDA
     * 
     */
    private BoletoDDA setBoletoDDA(ResultSet rs) throws SQLException {
        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setId(rs.getLong("idboletoDDA"));
        boletoDDA.setNumIdentificadorBoletoCip(getNullableLong("numIdentificadorBoletoCip", rs));
        boletoDDA.setNumRefAtualCadBoleto(getNullableLong("numRefAtualCadBoleto", rs));
        boletoDDA.setNumSeqAtualCadBoleto(getNullableLong("numSeqAtualCadBoleto", rs));
        boletoDDA.setNumRefAtualCadAceite(getNullableLong("numRefAtualCadAceite", rs));
        boletoDDA.setNumSeqAtualAceite(getNullableLong("numSeqAtualAceite", rs));
        boletoDDA.setDataHoraSituacaoBoleto(rs.getDate("dataHoraSituacaoBoleto") != null ? new DateTimeDB(rs.getDate("dataHoraSituacaoBoleto").getTime()) : null);
        boletoDDA.setCodIspbPartDestinatario(rs.getString("codIspbPartDestinatario"));
        boletoDDA.setCodPartDestinatario(rs.getString("codPartDestinatario"));
        boletoDDA.setCodTipoPessoaBeneficiario(rs.getString("codTipoPessoaBeneficiario"));
        boletoDDA.setNumCpfCnpjBeneficiario(rs.getString("numCpfCnpjBeneficiario"));
        boletoDDA.setNomeBeneficiario(rs.getString("nomeBeneficiario"));
        boletoDDA.setNomeFantasiaBeneficiario(rs.getString("nomeFantasiaBeneficiario"));
        boletoDDA.setDescLogradouroBeneficiario(rs.getString("descLogradouroBeneficiario"));
        boletoDDA.setDescCidadeBeneficiario(rs.getString("descCidadeBeneficiario"));
        boletoDDA.setSiglaUfBeneficiario(rs.getString("siglaUfBeneficiario"));
        boletoDDA.setNumCepBeneficiario(rs.getString("numCepBeneficiario"));
        boletoDDA.setCodTipoPessoaBeneficiarioFinal(rs.getString("codTipoPessoaBeneficiarioFinal"));
        boletoDDA.setNumCpfCnpjBeneficiarioFinal(rs.getString("numCpfCnpjBeneficiarioFinal"));
        boletoDDA.setNomeBeneficiarioFinal(rs.getString("nomeBeneficiarioFinal"));
        boletoDDA.setNomeFantasiaBeneficiarioFinal(rs.getString("nomeFantasiaBeneficiarioFinal"));
        boletoDDA.setCodTipoPessoaPagador(rs.getString("codTipoPessoaPagador"));
        boletoDDA.setNumCpfCnpjPagador(rs.getString("numCpfCnpjPagador"));
        boletoDDA.setNomePagador(rs.getString("nomePagador"));
        boletoDDA.setNomeFantasiaPagador(rs.getString("nomeFantasiaPagador"));
        boletoDDA.setDescLogradouroPagador(rs.getString("descLogradouroPagador"));
        boletoDDA.setDescCidadePagador(rs.getString("descCidadePagador"));
        boletoDDA.setSiglaUfPagador(rs.getString("siglaUfPagador"));
        boletoDDA.setNumCepPagador(rs.getString("numCepPagador"));
        boletoDDA.setCodTipoPessoaDDAAvalista(rs.getString("codTipoPessoaDDAAvalista"));
        boletoDDA.setNumCpfCnpjAvalista(rs.getString("numCpfCnpjAvalista"));
        boletoDDA.setNomeAvalista(rs.getString("nomeAvalista"));
        boletoDDA.setIdCarteira(getNullableInt("idCarteira", rs));
        boletoDDA.setCodMoeda(rs.getString("codMoeda"));
        boletoDDA.setNumNossoNumero(rs.getString("numNossoNumero"));
        boletoDDA.setNumCodigoBarra(rs.getString("numCodigoBarra"));
        boletoDDA.setNumCodBarrasCampoLivre(rs.getString("numCodigoBarra") != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(rs.getString("numCodigoBarra")) : null);
        boletoDDA.setNumLinhaDigitavel(rs.getString("numLinhaDigitavel"));
        boletoDDA.setDataVencimento(rs.getDate("dataVencimento") != null ? new DateTimeDB(rs.getDate("dataVencimento").getTime()) : null);
        boletoDDA.setValorBoleto(rs.getBigDecimal("valorBoleto"));
        boletoDDA.setNumDocumento(rs.getString("numDocumento"));
        boletoDDA.setIdEspecieDocumento(getNullableInt("idEspecieDocumento", rs));
        boletoDDA.setDataEmissao(rs.getDate("dataEmissao") != null ? new DateTimeDB(rs.getDate("dataEmissao").getTime()) : null);
        boletoDDA.setNumDiasProtesto(getNullableInt("numDiasProtesto", rs));
        boletoDDA.setDataLimitePagamento(rs.getDate("dataLimitePagamento") != null ? new DateTimeDB(rs.getDate("dataLimitePagamento").getTime()) : null);
        boletoDDA.setCodTipoPagamento(getNullableInt("codTipoPagamento", rs));
        boletoDDA.setNumParcela(getNullableInt("numParcela", rs));
        boletoDDA.setQtdTotalParcela(getNullableInt("qtdTotalParcela", rs));
        boletoDDA.setBolTituloNegociado(rs.getBoolean("bolTituloNegociado"));
        boletoDDA.setBolBloqueioPagamento(rs.getBoolean("bolBloqueioPagamento"));
        boletoDDA.setBolPagamentoParcial(rs.getBoolean("bolPagamentoParcial"));
        boletoDDA.setValorAbatimento(rs.getBigDecimal("valorAbatimento"));
        boletoDDA.setCodIndicadorValorMinimo(rs.getString("codIndicadorValorMinimo"));
        boletoDDA.setValorMinimo(rs.getBigDecimal("valorMinimo"));
        boletoDDA.setCodIndicadorValorMaximo(rs.getString("codIndicadorValorMaximo"));
        boletoDDA.setValorMaximo(rs.getBigDecimal("valorMaximo"));
        boletoDDA.setCodTipoModeloCalculo(rs.getString("codTipoModeloCalculo"));
        boletoDDA.setCodAutorizacaoValorDivergente(rs.getString("codAutorizacaoValorDivergente"));
        boletoDDA.setQtdPagamentoParcialReg(getNullableInt("qtdPagamentoParcialReg", rs));
        boletoDDA.setValorSaldoPagamento(rs.getBigDecimal("valorSaldoPagamento"));
        boletoDDA.setCodSituacaoBoletoPagamento(rs.getString("codSituacaoBoletoPagamento"));
        boletoDDA.setQtdPagamentoParcial(getNullableInt("qtdPagamentoParcial", rs));
        boletoDDA.setBolAceite(rs.getBoolean("bolAceite"));
        boletoDDA.setDataHoraSituacaoAceite(rs.getDate("dataHoraSituacaoAceite") != null ? new DateTimeDB(rs.getDate("dataHoraSituacaoAceite").getTime()) : null);
        boletoDDA.setDataHoraUltimaAtualizacao(rs.getDate("dataHoraUltimaAtualizacao") != null ? new DateTimeDB(rs.getDate("dataHoraUltimaAtualizacao").getTime()) : null);
        boletoDDA.setDataHoraInclusao(rs.getDate("dataHoraInclusao") != null ? new DateTimeDB(rs.getDate("dataHoraInclusao").getTime()) : null);
        boletoDDA.setCodSituacaoBoleto(getNullableInt("codSituacaoBoleto", rs));

        boletoDDA.setDataJuros(ObjectUtil.isNull(rs.getDate("dataJuros")) ? null : new DateTimeDB(rs.getDate("dataJuros").getTime()));

        TipoJuros tipoJuros = new TipoJuros();
        tipoJuros.setId(rs.getShort("codTipoJuros"));
        boletoDDA.setTipoJuros(tipoJuros);

        boletoDDA.setValorPercentualJuros(rs.getBigDecimal("valorPercentualJuros"));

        boletoDDA.setDataMulta(ObjectUtil.isNull(rs.getDate("dataMulta")) ? null : new DateTimeDB(rs.getDate("dataMulta").getTime()));

        TipoMulta tipoMulta = new TipoMulta();
        tipoMulta.setId(rs.getShort("codTipoMulta"));
        boletoDDA.setTipoMulta(tipoMulta);

        boletoDDA.setValorPercentualMulta(rs.getBigDecimal("valorPercentualMulta"));

        boletoDDA.setDataDesconto1(ObjectUtil.isNull(rs.getDate("dataDesconto1")) ? null : new DateTimeDB(rs.getDate("dataDesconto1").getTime()));

        TipoDesconto tipoDesconto1 = new TipoDesconto();
        tipoDesconto1.setCodTipoDesconto(rs.getString("codTipoDesconto1"));
        boletoDDA.setTipoDesconto1(tipoDesconto1);

        boletoDDA.setValorPercentualDesconto1(rs.getBigDecimal("valorPercentualDesconto1"));

        if (!ObjectUtil.isEmpty(rs.getString("codTipoDesconto2"))) {
            boletoDDA.setDataDesconto2(ObjectUtil.isNull(rs.getDate("dataDesconto2")) ? null : new DateTimeDB(rs.getDate("dataDesconto2").getTime()));

            TipoDesconto tipoDesconto2 = new TipoDesconto();
            tipoDesconto2.setCodTipoDesconto(rs.getString("codTipoDesconto2"));
            boletoDDA.setTipoDesconto2(tipoDesconto2);

            boletoDDA.setValorPercentualDesconto2(rs.getBigDecimal("valorPercentualDesconto2"));
        }

        if (!ObjectUtil.isEmpty(rs.getString("codTipoDesconto3"))) {
            boletoDDA.setDataDesconto3(ObjectUtil.isNull(rs.getDate("dataDesconto3")) ? null : new DateTimeDB(rs.getDate("dataDesconto3").getTime()));

            TipoDesconto tipoDesconto3 = new TipoDesconto();
            tipoDesconto3.setCodTipoDesconto(rs.getString("codTipoDesconto3"));
            boletoDDA.setTipoDesconto3(tipoDesconto3);

            boletoDDA.setValorPercentualDesconto3(rs.getBigDecimal("valorPercentualDesconto3"));
        }

        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException BoletoDDABaixaEfet
     * 
     */
    private BoletoDDABaixaEfet setBoletoDDABaixaEfet(ResultSet rs) throws SQLException {
        BoletoDDABaixaEfet boletoDDABaixaEfet = new BoletoDDABaixaEfet();
        boletoDDABaixaEfet.setId(rs.getLong("idboletoDDABaixaEfet"));
        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setId(rs.getLong(ID_BOLETO_DDA));
        boletoDDABaixaEfet.setBoletoDDA(boletoDDA);
        boletoDDABaixaEfet.setNumIdentificadorBaixaEfet(getNullableLong("numIdentificadorBaixaEfet", rs));
        boletoDDABaixaEfet.setNumRefAtualBaixaEfet(getNullableLong("numRefAtualBaixaEfet", rs));
        boletoDDABaixaEfet.setNumSeqAtualBaixaEfet(getNullableLong("numSeqAtualBaixaEfet", rs));
        boletoDDABaixaEfet
                .setDataProcessamentoBaixaEfet(rs.getDate("dataProcessamentoBaixaEfet") != null ? new DateTimeDB(rs.getDate("dataProcessamentoBaixaEfet").getTime()) : null);
        boletoDDABaixaEfet.setDataHoraProcessamentoBaixaEfet(
                rs.getDate("dataHoraProcessamentoBaixaEfet") != null ? new DateTimeDB(rs.getDate("dataHoraProcessamentoBaixaEfet").getTime()) : null);
        boletoDDABaixaEfet.setValorBaixaEfet(rs.getBigDecimal("valorBaixaEfet"));
        boletoDDABaixaEfet.setNumCodBarrasBaixaEfet(rs.getString("numCodBarrasBaixaEfet"));
        boletoDDABaixaEfet.setNumCodBarrasCampoLivre(
                rs.getString("numCodBarrasBaixaEfet") != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(rs.getString("numCodBarrasBaixaEfet")) : null);
        boletoDDABaixaEfet.setCodCanalPagamento(getNullableInt("codCanalPagamento", rs));
        boletoDDABaixaEfet.setCodMeioPagamento(getNullableInt("codMeioPagamento", rs));
        boletoDDABaixaEfet.setNumIdentificadorBaixaOperacional(getNullableLong("numIdentificadorBaixaOperacional", rs));
        boletoDDABaixaEfet.setCodTipoBaixaEfetiva(getNullableInt("codTipoBaixaEfetiva", rs));
        boletoDDABaixaEfet.setCodIspbPartRecebedorBaixaEfetiva(rs.getString("codIspbPartRecebedorBaixaEfetiva"));
        boletoDDABaixaEfet.setCodPartRecebedorBaixaEfetiva(rs.getString("codPartRecebedorBaixaEfetiva"));
        return boletoDDABaixaEfet;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException BoletoDDABaixaOper
     * 
     */
    private BoletoDDABaixaOper setBoletoDDABaixaOper(ResultSet rs) throws SQLException {
        BoletoDDABaixaOper boletoDDABaixaOper = new BoletoDDABaixaOper();
        boletoDDABaixaOper.setId(rs.getLong("idboletoDDABaixaOper"));
        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setId(rs.getLong(ID_BOLETO_DDA));
        boletoDDABaixaOper.setBoletoDDA(boletoDDA);
        boletoDDABaixaOper.setNumIdentificadorBaixaOper(getNullableLong("numIdentificadorBaixaOper", rs));
        boletoDDABaixaOper.setNumRefAtualBaixaOper(getNullableLong("numRefAtualBaixaOper", rs));
        boletoDDABaixaOper.setNumSeqAtualBaixaOper(getNullableLong("numSeqAtualBaixaOper", rs));
        boletoDDABaixaOper
                .setDataProcessamentoBaixaOper(rs.getDate("dataProcessamentoBaixaOper") != null ? new DateTimeDB(rs.getDate("dataProcessamentoBaixaOper").getTime()) : null);
        boletoDDABaixaOper.setDataHoraProcessamentoBaixaOper(
                rs.getDate("dataHoraProcessamentoBaixaOper") != null ? new DateTimeDB(rs.getDate("dataHoraProcessamentoBaixaOper").getTime()) : null);
        boletoDDABaixaOper.setValorBaixaOper(rs.getBigDecimal("valorBaixaOper"));
        boletoDDABaixaOper.setNumCodBarrasBaixaOper(rs.getString("numCodBarrasBaixaOper"));
        boletoDDABaixaOper.setNumCodBarrasCampoLivre(
                rs.getString("numCodBarrasBaixaOper") != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(rs.getString("numCodBarrasBaixaOper")) : null);
        boletoDDABaixaOper.setCodCanalPagamento(getNullableShort("codCanalPagamento", rs));
        boletoDDABaixaOper.setCodMeioPagamento(getNullableShort("codMeioPagamento", rs));
        boletoDDABaixaOper.setBolOperacaoContingencia(rs.getBoolean("bolOperacaoContingencia"));
        boletoDDABaixaOper.setCodSituacaoBaixaOperacional(rs.getString("codSituacaoBaixaOperacional"));
        boletoDDABaixaOper.setCodTipoBaixaOperacional(getNullableInt("codTipoBaixaOperacional", rs));
        boletoDDABaixaOper.setCodPartRecebedorBaixaOperacional(rs.getString("codPartRecebedorBaixaOperacional"));
        boletoDDABaixaOper.setCodIspbPartRecebedorBaixaOperacional(rs.getString("codIspbPartRecebedorBaixaOperacional"));
        boletoDDABaixaOper.setCodTipoPessoaPortador(rs.getString("codTipoPessoaPortador"));
        boletoDDABaixaOper.setNumCnpjCpfCodTipoPessoaPortador(rs.getString("numCnpjCpfPortador"), rs.getString("codTipoPessoaPortador"));
        boletoDDABaixaOper.setNumRefAtualCadBoleto(getNullableLong("numRefAtualCadBoleto", rs));

        return boletoDDABaixaOper;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException BoletoDDAGrupoCalculo
     * 
     */
    private BoletoDDAGrupoCalculo setBoletoDDAGrupoCalculo(ResultSet rs) throws SQLException {
        BoletoDDAGrupoCalculo boletoDDAGrupoCalculo = new BoletoDDAGrupoCalculo();
        boletoDDAGrupoCalculo.setId(rs.getLong("idboletoDDAGrupoCalculo"));
        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setId(rs.getLong(ID_BOLETO_DDA));
        boletoDDAGrupoCalculo.setBoletoDDA(boletoDDA);
        boletoDDAGrupoCalculo.setValorCalculadoJuros(rs.getBigDecimal("valorCalculadoJuros"));
        boletoDDAGrupoCalculo.setValorCalculadoMulta(rs.getBigDecimal("valorCalculadoMulta"));
        boletoDDAGrupoCalculo.setValorCalculadoDesconto(rs.getBigDecimal("valorCalculadoDesconto"));
        boletoDDAGrupoCalculo.setValorTotalCobrado(rs.getBigDecimal("valorTotalCobrado"));
        boletoDDAGrupoCalculo.setDataValidadeCalculo(rs.getDate("dataValidadeCalculo") != null ? new DateTimeDB(rs.getDate("dataValidadeCalculo").getTime()) : null);
        return boletoDDAGrupoCalculo;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException BoletoDDATerceiroAut
     * 
     */
    private BoletoDDATerceiroAut setBoletoDDATerceiroAut(ResultSet rs) throws SQLException {
        BoletoDDATerceiroAut boletoDDATerceiroAut = new BoletoDDATerceiroAut();
        boletoDDATerceiroAut.setId(rs.getLong("idboletoDDATerceiroAut"));
        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setId(rs.getLong(ID_BOLETO_DDA));
        boletoDDATerceiroAut.setBoletoDDA(boletoDDA);
        boletoDDATerceiroAut.setCodTipoPessoaAutorizador(rs.getString("codTipoPessoaAutorizador"));
        boletoDDATerceiroAut.setNumCpfCnpjAutorizador(rs.getString("numCpfCnpjAutorizador"), TipoPessoaEnum.getBy(rs.getString("codTipoPessoaAutorizador")));
        boletoDDATerceiroAut.setCodTipoPessoaTerceiro(rs.getString("codTipoPessoaTerceiro"));
        boletoDDATerceiroAut.setNumCpfCnpjTerceiro(rs.getString("numCpfCnpjTerceiro"), TipoPessoaEnum.getBy(rs.getString("codTipoPessoaTerceiro")));
        boletoDDATerceiroAut.setNumIdentificadorTerceiro(getNullableLong("numIdentificadorTerceiro", rs));
        boletoDDATerceiroAut.setNumRefAtualTerceiro(getNullableLong("numRefAtualTerceiro", rs));
        boletoDDATerceiroAut.setBolAtivo(rs.getBoolean("bolAtivo"));
        return boletoDDATerceiroAut;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#listarMensagemDDATerceiroAutorizadoLogEnvioArquivo(java.lang.Long)
     */
    public List<MensagemDDATerceiroAut> listarMensagemDDATerceiroAutorizadoLogEnvioArquivo(Long idLogEnvioArquivoDDA) {
        getLogger().debug("*******INICIO listarMensagemDDABoletoLogEnvioArquivo()*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MensagemDDATerceiroAut> listarMensagemDDATerceiroAut = new ArrayList<MensagemDDATerceiroAut>();

        try {
            comando = getComando("LISTAR_MENSAGEMDDA_TERCEIRO_AUTORIZADO_BOLETO_LOG_ENVIO_ARQUIVO");
            comando.adicionarVariavel("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA);
            comando.configurar();
            getLogger().debug("idLogEnvioArquivoDDA = " + idLogEnvioArquivoDDA);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listarMensagemDDATerceiroAut.add(setMensagemDDATerceiroNumIdentificador(rs));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listarMensagemDDATerceiroAut;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#listarMensagemDDABoletoLogEnvioArquivo(java.lang.Long)
     */
    public List<MensagemDDABoleto> listarMensagemDDABoletoLogEnvioArquivo(Long idLogEnvioArquivoDDA) {
        getLogger().debug("*******INICIO listarMensagemDDABoletoLogEnvioArquivo()*******");
        // TODO - IMPACTO NUMERO CODIGO DE BARRAS
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MensagemDDABoleto> listarMensagemDDABoleto = new ArrayList<MensagemDDABoleto>();

        try {
            comando = getComando("LISTAR_MENSAGEMDDA_BOLETO");
            comando.adicionarVariavel("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA);
            comando.configurar();
            getLogger().debug("idLogEnvioArquivoDDA = " + idLogEnvioArquivoDDA);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                MensagemDDABoleto mensagemDDABoleto = setMensagemDDABoleto(rs, Boolean.TRUE);
                listarMensagemDDABoleto.add(mensagemDDABoleto);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listarMensagemDDABoleto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterMensagemDDABoletoAtualizaReferencias(java.lang.Long)
     */
    public MensagemDDABoleto obterMensagemDDABoletoAtualizaReferencias(Long idMensagem) throws ComumException {
        MensagemDDABoleto mensagem = obterMensagemDDABoletoReferencia(idMensagem);
        if (!ObjectUtil.isNull(mensagem) && !ObjectUtil.isEmpty(mensagem.getNumRefAtualCadBoleto()) && !ObjectUtil.isEmpty(mensagem.getNumSeqAtualCadBoleto())) {
            atualizaMensagemDDABoleto(mensagem.getId(), mensagem.getNumRefAtualCadBoleto(), mensagem.getNumSeqAtualCadBoleto());
        }
        getLogger().debug("FIM Classe: BoletoCipDaoImpl Metodo: obterMensagemDDABoletoAtualizaReferencias");
        return mensagem;
    }

    /**
     * Método responsável por
     * 
     * @param idMensagem
     * @return MensagemDDABoleto
     * 
     */
    private MensagemDDABoleto obterMensagemDDABoletoReferencia(Long idMensagem) {
        getLogger().debug("*******INICIO obterMensagemDDABoleto()*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDABoleto mensagemDDABoleto = null;

        try {
            comando = getComando("OBTER_MENSAGEMDDA_BOLETO_REFERENCIAS");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug("idMensagem = " + idMensagem);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDABoleto = setMensagemDDABoletoCompleto(rs, Boolean.TRUE);
            }

            return obterMensagemDDABoleto(idMensagem, conn, mensagemDDABoleto);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

    /**
     * @param rs
     * @param idMensagem
     * @param conn
     * @param mensagemDDABoleto
     * @return
     * @throws SQLException MensagemDDABoleto
     * 
     */
    private MensagemDDABoleto obterMensagemDDABoleto(Long idMensagem, Connection conn, MensagemDDABoleto mensagemDDABoleto) throws SQLException {
        Comando comando = null;
        ResultSet rs = null;
        try {
            comando = getComando("LISTAR_MENSAGEMDDA_BOLETO_GRUPO_CALCULO");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                mensagemDDABoleto.getListaMensagemDDABoletoGrupoCalculo().add(setMensagemDDABoletoGrupoCalculo(rs));
            }
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
        }

        return mensagemDDABoleto;
    }

    /**
     * @param idMensagemDDABoleto
     * @param numRefAtualCadBoleto
     * @param numSeqAtualCadBoleto
     * @throws ComumException void
     * 
     */
    private void atualizaMensagemDDABoleto(Long idMensagemDDABoleto, Long numRefAtualCadBoleto, Long numSeqAtualCadBoleto) throws ComumException {
        getLogger().debug("Inicio Classe: BoletoCipDaoImpl Metodo: atualizaMensagemDDABoleto");
        Map<String, Object> parametros = getMapaParametro(idMensagemDDABoleto, "idMensagemDDABoleto");
        parametros.put("numRefAtualCadBoleto", numRefAtualCadBoleto);
        parametros.put("numSeqAtualCadBoleto", numSeqAtualCadBoleto);
        executarUpdate("ATUALIZAR_MENSAGEMDDA_BOLETO", parametros);
        getLogger().debug("FIM Classe: BoletoCipDaoImpl Metodo: atualizaMensagemDDABoleto");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterMensagemDDAAceiteAtualizaReferencias(java.lang.Long)
     */
    public MensagemDDAAceite obterMensagemDDAAceiteAtualizaReferencias(Long idMensagem) throws ComumException {
        getLogger().debug("Inicio Classe: BoletoCipDaoImpl Metodo: obterMensagemDDAAceiteAtualizaReferencias IdMensagemDDA = " + idMensagem);
        MensagemDDAAceite mensagem = obterMensagemDDAAceiteReferencia(idMensagem);
        getLogger().debug("Busca feita com Sucesso - obterMensagemDDABoletoReferencia");
        if (!ObjectUtil.isNull(mensagem) && !ObjectUtil.isEmpty(mensagem.getNumRefAtualAceite()) && !ObjectUtil.isEmpty(mensagem.getNumSeqAtualAceite())) {
            atualizaMensagemDDAAceite(mensagem.getId(), mensagem.getNumRefAtualAceite(), mensagem.getNumSeqAtualAceite());
        }
        getLogger().debug("FIM Classe: BoletoCipDaoImpl Metodo: obterMensagemDDAAceiteAtualizaReferencias");
        return mensagem;
    }

    /**
     * @param idMensagem
     * @return MensagemDDAAceite
     * 
     */
    private MensagemDDAAceite obterMensagemDDAAceiteReferencia(Long idMensagem) {
        getLogger().debug("*******INICIO obterMensagemDDAAceiteReferencia()*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDAAceite mensagemDDAAceite = null;

        try {
            comando = getComando("OBTER_MENSAGEMDDA_ACEITE_REFERENCIA");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug("idMensagem = " + idMensagem);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDAAceite = setMensagemDDAAceite(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return mensagemDDAAceite;
    }

    /**
     * 
     * @param idMensagemDDA
     * @param numRefAtualAceite
     * @param numSeqAtualAceite
     * @throws ComumException void
     * 
     */
    private void atualizaMensagemDDAAceite(Long idMensagemDDA, Long numRefAtualAceite, Long numSeqAtualAceite) throws ComumException {
        getLogger().debug("Inicio Classe: BoletoCipDaoImpl Metodo: atualizaMensagemDDAAceite ");
        Map<String, Object> parametros = getMapaParametro(idMensagemDDA, "idMensagemDDA");
        parametros.put("numRefAtualAceite", numRefAtualAceite);
        parametros.put("numSeqAtualAceite", numSeqAtualAceite);
        executarUpdate("ATUALIZAR_MENSAGEMDDA_ACEITE", parametros);
        getLogger().debug("FIM Classe: BoletoCipDaoImpl Metodo: atualizaMensagemDDAAceite");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#atualizaBoletoDDAAceite(br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA)
     */
    public void atualizaBoletoDDAAceite(BoletoDDA boletoDDA) throws ComumException {
        getLogger().debug("Inicio Classe: BoletoCipDaoImpl Metodo: atualizaBoletoDDAAceite");
        Map<String, Object> parametros = getMapaParametro(boletoDDA.getId(), "idBoletoDDA");
        parametros.put("numRefAtualCadAceite", boletoDDA.getNumRefAtualCadAceite());
        parametros.put("numSeqAtualAceite", boletoDDA.getNumSeqAtualAceite());
        parametros.put("bolAceite", boletoDDA.getBolAceite() ? 1 : 0);
        parametros.put("dataHoraSituacaoAceite", boletoDDA.getDataHoraSituacaoAceite());
        executarUpdate("ATUALIZAR_BOLETODDA_ACEITE", parametros);
        getLogger().debug("FIM Classe: BoletoCipDaoImpl Metodo: atualizaBoletoDDAAceite");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterMensagemDDATerceiroAutorizadoAtualizaReferencias(java.lang.Long)
     */
    public MensagemDDATerceiroAut obterMensagemDDATerceiroAutorizadoAtualizaReferencias(Long idMensagem) throws ComumException {
        getLogger().debug("Inicio Classe: BoletoCipDaoImpl Metodo: obterMensagemDDATerceiroAutorizadoAtualizaReferencias IdMensagemDDA = " + idMensagem);
        MensagemDDATerceiroAut mensagem = obterMensagemDDATerceiroAutReferencia(idMensagem);
        getLogger().debug("Busca feita com Sucesso - obterMensagemDDABoletoReferencia");
        if (!ObjectUtil.isNull(mensagem) && !ObjectUtil.isEmpty(mensagem.getNumRefAtualTerceiro())) {
            atualizaMensagemDDATerceiroAut(mensagem.getId(), mensagem.getNumRefAtualTerceiro());
        }
        getLogger().debug("FIM Classe: BoletoCipDaoImpl Metodo: obterMensagemDDATerceiroAutorizadoAtualizaReferencias");
        return mensagem;
    }

    /**
     * @param idMensagem
     * @return MensagemDDATerceiroAut
     * 
     */
    private MensagemDDATerceiroAut obterMensagemDDATerceiroAutReferencia(Long idMensagem) {
        getLogger().debug("*******INICIO obterMensagemDDATerceiroAut()*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDATerceiroAut mensagemDDATerceiroAut = null;

        try {
            comando = getComando("OBTER_MENSAGEMDDA_TERCEIRO_AUTORIZADO_REFERENCIA");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug("idMensagem = " + idMensagem);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDATerceiroAut = setMensagemDDATerceiroNumIdentificador(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return mensagemDDATerceiroAut;
    }

    /**
     * @param idMensagemDDA
     * @param numRefAtualTerceiro
     * @throws ComumException void
     * 
     */
    private void atualizaMensagemDDATerceiroAut(Long idMensagemDDA, Long numRefAtualTerceiro) throws ComumException {
        getLogger().debug("Inicio Classe: BoletoCipDaoImpl Metodo: atualizaMensagemDDATerceiroAut");
        Map<String, Object> parametros = getMapaParametro(idMensagemDDA, "idMensagemDDA");
        parametros.put("numRefAtualTerceiro", numRefAtualTerceiro);
        executarUpdate("ATUALIZAR_MENSAGEMDDA_TERCEIRO_AUT", parametros);
        getLogger().debug("FIM Classe: BoletoCipDaoImpl Metodo: atualizaMensagemDDATerceiroAut");

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#atualizarSituacaoBoletoDDABaixaOper(java.lang.Long, java.lang.String)
     */
    public void atualizarSituacaoBoletoDDABaixaOper(Long numIdentificadorBaixaOper, String codSituacaoBaixaOperacional) throws ComumException {
        getLogger().debug("*******INICIO atualizarSituacaoBoletoDDABaixaOper()*******");
        Map<String, Object> parametros = getMapaParametro(codSituacaoBaixaOperacional, "codSituacaoBaixaOperacional");
        parametros.put("numIdentificadorBaixaOper", numIdentificadorBaixaOper);
        executarUpdate("ATUALIZAR_SITUACAO_BAIXA_OPERACIONAL", parametros);
    }

    /**
     * @param rs
     * @return
     * @throws SQLException MensagemDDABoleto
     * 
     */
    private MensagemDDABoleto setMensagemDDABoletoCompleto(ResultSet rs, Boolean existeNumIdentificadorBoletoCip) throws SQLException {
        MensagemDDABoleto mb = setMensagemDDABoleto(rs, existeNumIdentificadorBoletoCip);
        mb.setMensagemDDA(obterMensagemDDA(mb.getId()));
        return mb;
    }

    /**
     * Método responsável por popular o objeto DDA.MensagemDDABoleto
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDABoleto
     * 
     */
    private MensagemDDABoleto setMensagemDDABoleto(ResultSet rs, Boolean existeNumIdentificadorBoletoCip) throws SQLException {
        MensagemDDABoleto mb = new MensagemDDABoleto();
        mb.setId(rs.getLong("idMensagemDDA"));

        mb.setNumRefAtualCadBoleto(getNullableLong("numRefAtualCadBoleto", rs));
        mb.setNumSeqAtualCadBoleto(getNullableLong("numSeqAtualCadBoleto", rs));
        if (existeNumIdentificadorBoletoCip) {
            mb.setNumIdentificadorBoletoCip(getNullableLong("numIdentificadorBoletoCip", rs));
        }

        mb.setCodTipoPessoaBeneficiario(rs.getString("codTipoPessoaBeneficiario"));
        mb.setNumCpfCnpjBeneficiario(rs.getString("numCpfCnpjBeneficiario"));
        mb.setNomeBeneficiario(rs.getString("nomeBeneficiario"));
        mb.setNomeFantasiaBeneficiario(rs.getString("nomeFantasiaBeneficiario"));
        mb.setDescLogradouroBeneficiario(rs.getString("descLogradouroBeneficiario"));
        mb.setDescCidadeBeneficiario(rs.getString("descCidadeBeneficiario"));
        mb.setSiglaUfBeneficiario(rs.getString("siglaUfBeneficiario"));
        mb.setNumCepBeneficiario(rs.getString("numCepBeneficiario"));

        mb.setCodTipoPessoaBeneficiarioFinal(rs.getString("codTipoPessoaBeneficiarioFinal"));
        mb.setNumCpfCnpjBeneficiarioFinal(rs.getString("numCpfCnpjBeneficiarioFinal"));
        mb.setNomeBeneficiarioFinal(rs.getString("nomeBeneficiarioFinal"));
        mb.setNomeFantasiaBeneficiarioFinal(rs.getString("nomeFantasiaBeneficiarioFinal"));

        mb.setCodTipoPessoaPagador(rs.getString("codTipoPessoaPagador"));
        mb.setNumCpfCnpjPagador(rs.getString("numCpfCnpjPagador"));
        mb.setNomePagador(rs.getString("nomePagador"));
        mb.setNomeFantasiaPagador(rs.getString("nomeFantasiaPagador"));
        mb.setDescLogradouroPagador(rs.getString("descLogradouroPagador"));
        mb.setDescCidadePagador(rs.getString("descCidadePagador"));
        mb.setSiglaUfPagador(rs.getString("siglaUfPagador"));
        mb.setNumCepPagador(rs.getString("numCepPagador"));

        mb.setCodTipoPessoaDDAAvalista(rs.getString("codTipoPessoaDDAAvalista"));
        mb.setNumCpfCnpjAvalista(rs.getString("numCpfCnpjAvalista"));
        mb.setNomeAvalista(rs.getString("nomeAvalista"));

        // Necessario a validaçao, pois no legado o nome avalista não é obrigatorio, e para cip quando o tipoPessoaDDA for 1 ou 2 o nome se torna obrigatorio.
        if ((mb.getCodTipoPessoaDDAAvalista().equals("1") || mb.getCodTipoPessoaDDAAvalista().equals("2")) && ObjectUtil.isNull(mb.getNomeAvalista())) {
            mb.setNomeAvalista("-");
        }

        mb.setIdCarteira(rs.getInt("idCarteira"));
        mb.setCodMoeda(rs.getString("codMoeda"));
        mb.setIdOrgaoMoeda(rs.getInt("idOrgaoMoeda"));
        mb.setNumNossoNumero(rs.getString("numNossoNumero"));
        mb.setNumCodigoBarra(rs.getString("numCodigoBarra"));

        mb.setNumCodBarrasCampoLivre(!ObjectUtil.isEmpty(mb.getNumCodigoBarra()) ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(rs.getString("numCodigoBarra")) : null);

        mb.setNumLinhaDigitavel(rs.getString("numLinhaDigitavel"));
        mb.setDataVencimento(ObjectUtil.isNull(rs.getDate("dataVencimento")) ? null : new DateTimeDB(rs.getDate("dataVencimento").getTime()));
        mb.setValorBoleto(rs.getBigDecimal("valorBoleto"));
        mb.setNumDocumento(rs.getString("numDocumento"));
        mb.setDataEmissao(new DateTimeDB(rs.getDate("dataEmissao").getTime()));

        mb.setNumDiasProtesto(rs.getInt("numDiasProtesto"));
        mb.setDataLimitePagamento(ObjectUtil.isNull(rs.getDate("dataLimitePagamento")) ? null : new DateTimeDB(rs.getDate("dataLimitePagamento").getTime()));

        mb.setCodTipoPagamento(getNullableInt("codTipoPagamento", rs));

        mb.setNumParcela(rs.getInt("numParcela"));
        mb.setQtdTotalParcela(rs.getInt("qtdTotalParcela"));
        mb.setBolTituloNegociado(rs.getBoolean("bolTituloNegociado"));
        mb.setBolBloqueioPagamento(rs.getBoolean("bolBloqueioPagamento"));
        mb.setBolPagamentoParcial(rs.getBoolean("bolPagamentoParcial"));

        mb.setQtdPagamentoParcial(rs.getInt("qtdPagamentoParcial"));
        mb.setValorAbatimento(rs.getBigDecimal("valorAbatimento"));
        mb.setCodIndicadorValorMinimo(rs.getString("codIndicadorValorMinimo"));
        mb.setValorMinimo(rs.getBigDecimal("valorMinimo"));
        mb.setCodIndicadorValorMaximo(rs.getString("codIndicadorValorMaximo"));
        mb.setValorMaximo(rs.getBigDecimal("valorMaximo"));
        mb.setCodTipoModeloCalculo(rs.getString("codTipoModeloCalculo"));
        mb.setCodAutorizacaoValorDivergente(rs.getString("codAutorizacaoValorDivergente"));

        mb.setIdEspecieDocumento(rs.getInt("idEspecieDocumento"));

        mb.setDataJuros(ObjectUtil.isNull(rs.getDate("dataJuros")) ? null : new DateTimeDB(rs.getDate("dataJuros").getTime()));
        mb.setCodTipoJuros(rs.getInt("codTipoJuros"));
        mb.setValorPercentualJuros(rs.getBigDecimal("valorPercentualJuros"));

        mb.setDataMulta(ObjectUtil.isNull(rs.getDate("dataMulta")) ? null : new DateTimeDB(rs.getDate("dataMulta").getTime()));
        mb.setCodTipoMulta(rs.getInt("codTipoMulta"));
        mb.setValorPercentualMulta(rs.getBigDecimal("valorPercentualMulta"));

        mb.setDataDesconto1(ObjectUtil.isNull(rs.getDate("dataDesconto1")) ? null : new DateTimeDB(rs.getDate("dataDesconto1").getTime()));
        mb.setCodTipoDesconto1(rs.getString("codTipoDesconto1"));
        mb.setValorPercentualDesconto1(rs.getBigDecimal("valorPercentualDesconto1"));

        if (!ObjectUtil.isEmpty(rs.getString("codTipoDesconto2"))) {
            mb.setDataDesconto2(ObjectUtil.isNull(rs.getDate("dataDesconto2")) ? null : new DateTimeDB(rs.getDate("dataDesconto2").getTime()));
            mb.setCodTipoDesconto2(rs.getString("codTipoDesconto2"));
            mb.setValorPercentualDesconto2(rs.getBigDecimal("valorPercentualDesconto2"));
        }

        if (!ObjectUtil.isEmpty(rs.getString("codTipoDesconto3"))) {
            mb.setDataDesconto3(ObjectUtil.isNull(rs.getDate("dataDesconto3")) ? null : new DateTimeDB(rs.getDate("dataDesconto3").getTime()));
            mb.setCodTipoDesconto3(rs.getString("codTipoDesconto3"));
            mb.setValorPercentualDesconto3(rs.getBigDecimal("valorPercentualDesconto3"));
        }

        return mb;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#listarMensagemDDAConsultaBoleto(java.lang.Long)
     */
    public List<MensagemDDAConsultaBoleto> listarMensagemDDAConsultaBoleto(Long idLogEnvioArquivoDDA) {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MensagemDDAConsultaBoleto> listarMensagemDDAConsultaBoletoBoleto = new ArrayList<MensagemDDAConsultaBoleto>();

        try {
            comando = getComando("LISTAR_MENSAGEMDDA_CONSULTA_BOLETO");
            comando.adicionarVariavel("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA);
            comando.configurar();
            getLogger().debug("idLogEnvioArquivoDDA = " + idLogEnvioArquivoDDA);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listarMensagemDDAConsultaBoletoBoleto.add(setMensagemDDAConsultaBoleto(rs));
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listarMensagemDDAConsultaBoletoBoleto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterBoletoDDATerceiroAutPorIdBoleto(java.lang.Long, java.lang.Long)
     */
    public BoletoDDATerceiroAut obterBoletoDDATerceiroAutorizado(Long numIdentificadorTerceiro, Long numIdentificadorTitulo) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numIdentificadorTerceiro", numIdentificadorTerceiro);
        parametros.put("numIdentificadorTitulo", numIdentificadorTitulo);
        return obterDados("OBTER_BOLETO_DDA_TERCEIRO_AUT_POR_BOLETO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#removerBaixaEfetiva(java.lang.Long)
     */
    public void removerBaixaEfetiva(Long idBoletoDDA) throws ComumException {
        remover(idBoletoDDA, "REMOVER_BAIXA_EFETIVA_BOLETO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#removerBaixaOperacional(java.lang.Long)
     */
    public void removerBaixaOperacional(Long idBoletoDDA) throws ComumException {
        remover(idBoletoDDA, "REMOVER_BAIXA_OPERACIONAL_BOLETO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#removerGrupoCalculo(java.lang.Long)
     */
    public void removerGrupoCalculo(Long idBoletoDDA) throws ComumException {
        remover(idBoletoDDA, "REMOVER_GRUPO_CALCULO_BOLETO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#removerTerceiroAutorizado(java.lang.Long)
     */
    public void removerTerceiroAutorizado(Long idBoletoDDA) throws ComumException {
        remover(idBoletoDDA, "REMOVER_TERCEIROAUTORIZADO_BOLETO");
    }

    /**
     * @param idBoletoDDA
     * @param nomeComando
     * @throws ComumException void
     * 
     */
    private void remover(Long idBoletoDDA, String nomeComando) throws ComumException {
        executarUpdate(nomeComando, setParametroIdBoleto(idBoletoDDA));
    }

    /**
     * Método responsável por popular o objeto DDA.MensagemDDABoletoGrupoCalculo
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDABoletoGrupoCalculo
     * 
     */
    private MensagemDDABoletoGrupoCalculo setMensagemDDABoletoGrupoCalculo(ResultSet rs) throws SQLException {
        return new MensagemDDABoletoGrupoCalculo(rs.getLong("idMensagemDDABoletoGrupoCalculo"), rs.getLong("idMensagemDDA"), rs.getBigDecimal("valorCalculadoJuros"),
                rs.getBigDecimal("valorCalculadoMulta"), rs.getBigDecimal("valorCalculadoDesconto"), rs.getBigDecimal("valorTotalCobrado"),
                new DateTimeDB(rs.getDate("dataValidadeCalculo").getTime()));
    }

    /**
     * Método responsável por popular o objeto DDA.MensagemDDAAceite
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDAAceite
     * 
     */
    private MensagemDDAAceite setMensagemDDAAceite(ResultSet rs) throws SQLException {
        MensagemDDAAceite mensagem = new MensagemDDAAceite(rs.getLong("idMensagemDDA"), rs.getBoolean("bolAceite"), rs.getLong("numRefAtualAceite"),
                rs.getLong("numSeqAtualAceite"), rs.getLong("numIdentificadorBoletoCip"));

        if (ObjectUtil.isNull(mensagem.getMensagemDDA())) {
            mensagem.setMensagemDDA(obterMensagemDDA(mensagem.getId()));
        }
        return mensagem;
    }

    /**
     * Método responsável por popular o objeto DDA.MensagemDDATerceiroAut com o campo numIdentificadorTerceiro.
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDATerceiroAut
     * 
     */
    private MensagemDDATerceiroAut setMensagemDDATerceiroNumIdentificador(ResultSet rs) throws SQLException {
        MensagemDDATerceiroAut m = setMensagemDDATerceiroAut(rs);
        m.setNumIdentificadorTerceiro(getNullableLong("numIdentificadorTerceiro", rs));
        return m;
    }

    /**
     * Método responsável por popular o objeto DDA.MensagemDDATerceiroAut
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDATerceiroAut
     * 
     */
    private MensagemDDATerceiroAut setMensagemDDATerceiroAut(ResultSet rs) throws SQLException {
        MensagemDDATerceiroAut m = new MensagemDDATerceiroAut(rs.getLong("idMensagemDDA"), rs.getLong("numIdentificadorBoletoCip"), rs.getString("codTipoPessoaAutorizador"),
                rs.getString("numCpfCnpjAutorizador"), rs.getString("codTipoPessoaTerceiro"), rs.getString("numCpfCnpjTerceiro"), getNullableLong("numRefAtualTerceiro", rs));
        if (ObjectUtil.isNull(m.getMensagemDDA())) {
            m.setMensagemDDA(obterMensagemDDA(m.getId()));
        }
        return m;
    }

    /**
     * Método responsável por
     * 
     * @param numCodigoBarra
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> setParametroNumCodBarra(String numCodigoBarra) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numCodigoBarra", numCodigoBarra);
        return parametros;
    }

    /**
     * Método responsável por
     * 
     * @param idBoletoDDA
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> setParametroIdBoleto(Long idBoletoDDA) {
        return setParametroId(idBoletoDDA, "idBoleto");
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDAConsultaBoleto
     * 
     */
    private MensagemDDAConsultaBoleto setMensagemDDAConsultaBoleto(ResultSet rs) throws SQLException {
        MensagemDDAConsultaBoleto mensagemDDAConsultaBoleto = new MensagemDDAConsultaBoleto();
        mensagemDDAConsultaBoleto.setId(rs.getLong("idMensagemDDA"));

        mensagemDDAConsultaBoleto.setNumCodigoBarra(rs.getString("numCodigoBarra"));
        mensagemDDAConsultaBoleto.setNumCodBarrasCampoLivre(
                !ObjectUtil.isEmpty(mensagemDDAConsultaBoleto.getNumCodigoBarra()) ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(mensagemDDAConsultaBoleto.getNumCodigoBarra())
                        : null);
        mensagemDDAConsultaBoleto.setNumCpfCnpjPagador(rs.getString("numcpfCnpjPagador"));

        mensagemDDAConsultaBoleto.setMensagemDDA(obterMensagemDDA(mensagemDDAConsultaBoleto.getId()));

        return mensagemDDAConsultaBoleto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#atualizarSituacaoBoletoPagamentoDDA(java.lang.String, java.lang.String)
     */
    public void atualizarSituacaoBoletoPagamentoDDA(String codSituacaoBoletoPagamento, BigInteger numRefAtualTit, String numIdentificadorBoleto) throws ComumException {
        getLogger().debug("*******INICIO atualizarSituacaoPagamentoBoletoDDA(String codSituacaoBoletoPagamento, String numIdentificadorBoleto)*******");
        Map<String, Object> parametros = getMapaParametro(codSituacaoBoletoPagamento, "codSituacaoBoletoPagamento");
        parametros.put("numRefAtualTit", numRefAtualTit);
        parametros.put("numIdentificadorBoleto", numIdentificadorBoleto);
        executarUpdate("ATUALIZAR_SITUACAO_BOLETO_PAGAMENTO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#incluirMensagemDDAConsultaBoletoEmLote(java.lang.String,
     *      br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Boolean, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer, java.util.List)
     */
    public void incluirMensagemDDAConsultaBoletoEmLote(String codTipoMensagem, DateTimeDB dataMovimento, Boolean bolOrigemSicoob, DateTimeDB dataHoraCadastro,
            Integer numPrioridadeEnvio, List<String> listaNumCodBarras, Integer idInstituicao) throws ComumException {
        debug("### Incluíndo a mensagem DDA de consulta de boleto em lote...");
        debug("Parâmetro - codTipoMensagem: " + codTipoMensagem);
        debug("Parâmetro - dataMovimento: " + dataMovimento);
        debug("Parâmetro - bolOrigemSicoob: " + bolOrigemSicoob);
        debug("Parâmetro - dataHoraCadastro: " + dataHoraCadastro);
        debug("Parâmetro - numPrioridadeEnvio: " + numPrioridadeEnvio);
        debug("Parâmetro - listaNumCodBarras: " + listaNumCodBarras.size());
        debug("Parâmetro - idInstituicao: " + idInstituicao);

        Comando comando = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            debug("Estabelecendo a conexão...");
            conn = estabelecerConexao();

            final String sql = "INSERT INTO dda.MensagemDDA (codTipoMensagemDDA, dataMovimento, bolOrigemSicoob, dataHoraCadastro, numPrioridadeEnvio, idInstituicaoSolicitante, idUsuarioSolicitante, idCanal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            debug("SQL da Mensagem DDA: " + sql);

            // Cria o statement com retorno do id incluído na mensagem
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            List<Object[]> lista = new ArrayList<Object[]>(listaNumCodBarras.size());

            debug("Incluíndo as mensagens DDA...");

            for (String numCodBarras : listaNumCodBarras) {
                ps.setString(1, codTipoMensagem);
                ps.setDate(2, new java.sql.Date(dataMovimento.getTime()));
                ps.setBoolean(3, bolOrigemSicoob);
                ps.setDate(4, new java.sql.Date(dataHoraCadastro.getTime()));
                ps.setInt(5, numPrioridadeEnvio);
                ps.setInt(6, idInstituicao);
                ps.setString(7, CONSULTA_BOLETO_PAGAMENTO);
                ps.setShort(8, CanalEnum.RETAGUARDA.getId());

                ps.executeUpdate();

                // Obtém o resultset com o ID gerado na inclusão
                rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    // Id gerado na inclusão
                    long id = rs.getLong(1);

                    // Cria a lista com id e código de barras para inclusão em lote das mensagens de consulta
                    lista.add(new Object[] { id, numCodBarras, numCodBarras });

                    fecharResultSet(rs);
                }
            }

            comando = getComando("INCLUIR_MENSAGEM_CONSULTA_BOLETO_EM_LOTE");
            comando.configurar();
            debug("SQL da mensagem de consulta: " + comando.getSqlEmUso());

            // Inclui as mensagens de consulta em lote
            comando.executarAtualizacaoEmLote(lista, conn);
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
            fecharResultSet(rs);
            fecharStatement(ps);
            fecharConexao(conn);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#existeSolicitacaoTerceiroAutorizadoEmAndamento(java.lang.String,
     *      java.lang.Long)
     */
    public boolean existeSolicitacaoTerceiroAutorizadoEmAndamento(String numCpfCnpjTerceiro, Long numIdentTitulo) throws ComumException {
        return existeSolicitacaoTerceiroAutorizado(numCpfCnpjTerceiro, numIdentTitulo, EM_ANDAMENTO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#existeTerceiroAutorizadoAtivo(java.lang.String, java.lang.Long)
     */
    public boolean existeTerceiroAutorizadoAtivo(String numCpfCnpjTerceiro, Long numIdentTitulo) throws ComumException {
        return existeSolicitacaoTerceiroAutorizado(numCpfCnpjTerceiro, numIdentTitulo, ATIVO);
    }

    /**
     * @param numCpfCnpjTerceiro
     * @param numIdentTitulo
     * @param situacao
     * @return
     * @throws ComumException boolean
     * 
     */
    private boolean existeSolicitacaoTerceiroAutorizado(String numCpfCnpjTerceiro, Long numIdentTitulo, String situacao) throws ComumException {
        Map<String, Object> parametros = getMapaParametro(numCpfCnpjTerceiro, numIdentTitulo);
        parametros.put("situacao", situacao);
        return existeRegistroNativo("EXISTE_SOLICITACAO_TERCEIRO_AUTORIZADO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#isBoletoVinculadoTerceiro(java.lang.String, java.lang.Long)
     */
    public boolean isBoletoVinculadoTerceiro(String numCpfCnpjTerceiro, Long numIdentTitulo) throws ComumException {
        return existeRegistroNativo("EXISTE_BOLETO_VINCULADO_TERCEIRO_AUTORIZADO", getMapaParametro(numCpfCnpjTerceiro, numIdentTitulo));
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpjTerceiro
     * @param numIdentTitulo
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getMapaParametro(String numCpfCnpjTerceiro, Long numIdentTitulo) {
        Map<String, Object> parametros = getMapaParametro(numCpfCnpjTerceiro, "numCpfCnpjTerceiro");
        parametros.put("numIdentTitulo", numIdentTitulo);
        return parametros;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#gravarBoletoDDAXmlADDA106(long, long, long)
     */
    public void gravarBoletoDDAXmlADDA106(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) {
        Connection conn = null;
        try {
            conn = estabelecerConexao();
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_GRAVAR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_MULTA_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_GRAVAR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_DESCONTO_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_GRAVAR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_JUROS_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_GRAVAR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_GRUPO_CALCULO_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal,
                    LOG_GRAVAR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_BAIXA_OPERACIONAL_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal,
                    LOG_GRAVAR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_BAIXA_EFETIVA_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal,
                    LOG_GRAVAR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_TERCEIRO_AUTORIZADO_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal,
                    LOG_GRAVAR_BOLETO_ADDA106);

        } finally {
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#obterCpfCnpjPagadorEletronico(java.lang.Long)
     */
    public String obterCpfCnpjPagadorEletronico(Long numIdentcTit) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        String cpfCnpj = null;

        try {
            comando = getComando("OBTER_CPF_CNPJ_PAGADOR_ELETRONICO");
            comando.adicionarVariavel("numIdentcTit", numIdentcTit);
            comando.configurar();

            debug(comando.getSqlEmUso());

            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                cpfCnpj = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        return cpfCnpj;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#processarArquivoRetornoConsultaBoletoDDA(long, long, long)
     */
    public void processarArquivoRetornoConsultaBoletoDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        Connection conn = null;

        try {
            conn = estabelecerConexao();
            executarAtualizacaoArquivoDDA(conn, "PROCESSAR_ALTERACAO_BOLETO_ADDA110RET", idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal,
                    "PROCESSANDO ALTERAÇÕES EM BOLETO DO ARQUIVO ADDA110RET");
            executarScriptEmXML(conn, "PROCESSAR_ALTERACOES_ADDA110RET", idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal,
                    "EXECUTANDO PROCESSAMENTO DE ALTERAÇÕES EM BOLETO DO ARQUIVO ADDA110RET");
        } finally {
            fecharConexao(conn);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#processarArquivoRetornoBaixaEfetivaDDA(long, long, long)
     */
    public void processarArquivoRetornoBaixaEfetivaDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        gravarBoletoADDA118RET(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        executarScriptEmXML("PROCESSAR_INCLUSAO_BAIXAEFETIVA_ADDA118RET", idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal, "INCLUSÃO BAIXA EFETIVA");
    }

    /**
     * Método responsável por
     * 
     * @param idLogRecebArq
     * @param idLogDetRecebimentoInicial
     * @param idLogDetRecebimentoFinal void
     * 
     */
    private void gravarBoletoADDA118RET(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        Connection conn = null;

        try {
            conn = estabelecerConexao();
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_CAMPOS_BOLETO_ADDA118RET", idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal,
                    "GRAVANDO ALTERAÇÕES EM BOLETO DO ARQUIVO ADDA118RET");
        } finally {
            fecharConexao(conn);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao#atualizarBolExcedeuSLA(java.lang.Long, java.lang.Boolean)
     */
    public void atualizarBolExcedeuSLA(Long idMensagemDDA, Boolean excedeuSLA) throws ComumException {
        debug("### Atualizando bolExcedeuSLA...");
        debug("Parâmetro - idMensagemDDA: " + idMensagemDDA);
        debug("Parâmetro - excedeuSLA: " + excedeuSLA);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("excedeuSLA", excedeuSLA ? 1 : 0);
        parametros.put("idMensagemDDA", idMensagemDDA);

        executarUpdate("ATUALIZAR_MENSAGEMDDA_BOLEXCEDEUSLA", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2#obterMensagemDDA(java.lang.Long)
     */
    public MensagemDDA obterMensagemDDA(Long idMensagem) {
        return super.obterMensagemDDA(idMensagem);
    }

}
