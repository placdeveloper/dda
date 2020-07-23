package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.MeioPagamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoModeloCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMulta;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoPessoaDDAAvalista;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;
import br.com.sicoob.tipos.DateTime;

/**
 * ManutencaoMensagemDDABoletoDaoImpl é responsável por
 * 
 * @author tayrone.oliveira
 */
/**
 * ManutencaoMensagemDDABoletoDaoImpl é responsável por
 * 
 * @author Tayrone.Oliveira
 */
/**
 * ManutencaoMensagemDDABoletoDaoImpl é responsável por
 * 
 * @author Tayrone.Oliveira
 */
public class ManutencaoMensagemDDABoletoDaoImpl extends OperacionalCrudDaoDB2<MensagemDDABoleto> implements ManutencaoMensagemDDABoletoDao {

    public final static Integer NUM_PAGINACAO = 10;
    public final static String ARQUIVO_QUERIES = "sicoobdda_operacional.mensagemddaboleto.xml";
    public final static String NOME_COLECAO_COMANDO = "comandos-sicoobdda-operacional-mensagemddaboleto";
    public final static String COMANDOS = "PESQUISAR_MENSAGEMDDABOLETODTO_TELA_PESQUISA";
    public final static String COMANDOS_QTD = "OBTER_QTD_MENSAGEMDDABOLETODTO_TELA_PESQUISA";

    /**
     * 
     */
    public ManutencaoMensagemDDABoletoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDO, null, COMANDOS, COMANDOS_QTD);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao#listarMensagemDDABoletoDtoPaginado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoFiltroDto)
     */
    @SuppressWarnings("deprecation")
    public List<MensagemDDABoletoDto> listarMensagemDDABoletoDtoPaginado(MensagemDDABoletoFiltroDto filtro) throws ComumException {
        List<MensagemDDABoletoDto> listaRetorno = new ArrayList<MensagemDDABoletoDto>();
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        try {
            conn = estabelecerConexao();

            comando = getComando("PESQUISAR_MENSAGEMDDABOLETODTO_TELA_PESQUISA_JDBC");
            comando.adicionarVariavel("tipoMensagem", filtro.getTipoMensagem());
            comando.adicionarVariavel("dataMovimento", filtro.getDataMovimento());
            comando.adicionarVariavel("numCodigoDeBarras", filtro.getNumCodigoDeBarras());
            comando.adicionarVariavel("situacaoMensagem", filtro.getSituacaoMensagem());
            comando.adicionarVariavel("inicio", filtro.getPaginaAtual());
            comando.configurar();

            getLogger().info(comando.getSqlEmUso());
            rs = comando.executarConsulta(conn);

            while (rs.next()) {
                Date date = rs.getDate("DATAMOVIMENTO");
                DateTime dateTime = new DateTime();
                dateTime.setDate(date.getDate());
                dateTime.setMonth(date.getMonth());
                dateTime.setYear(date.getYear());
                dateTime.setTime(date.getTime());
                listaRetorno.add(new MensagemDDABoletoDto(rs.getLong("IDMENSAGEMDDA"), rs.getString("CODTIPOMENSAGEMDDA"), dateTime, rs.getString("NUMCODIGOBARRA")));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        // MANTIS 17461 (Pós homologação): Para evitar uma manutenção bem mais custosa, fiz dessa forma para obter resultado TOTAL para o perfeito funcionamento
        // da PAGINAÇÃO
        if (!ObjectUtil.isEmpty(listaRetorno)) {
            listaRetorno.get(Constantes.INTEGER_ZERO).setQtdPesquisa(obterQTDMensagemDDABoletoPaginado(filtro));
        }
        return listaRetorno;
    }

    /**
     * Método responsável por
     * 
     * @param filtro
     * @return
     * @throws ComumException Long
     * 
     */
    private Integer obterQTDMensagemDDABoletoPaginado(MensagemDDABoletoFiltroDto filtro) throws ComumException {
        Integer resultado = Constantes.INTEGER_ZERO;
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        try {
            conn = estabelecerConexao();

            comando = getComando("OBTER_QTD_MENSAGEMDDABOLETODTO_TELA_PESQUISA_JDBC");
            comando.adicionarVariavel("tipoMensagem", filtro.getTipoMensagem());
            comando.adicionarVariavel("dataMovimento", filtro.getDataMovimento());
            comando.adicionarVariavel("numCodigoDeBarras", filtro.getNumCodigoDeBarras());
            comando.adicionarVariavel("situacaoMensagem", filtro.getSituacaoMensagem());
            comando.configurar();

            getLogger().info(comando.getSqlEmUso());
            rs = comando.executarConsulta(conn);

            while (rs.next()) {
                resultado = rs.getInt("QTD");
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return resultado;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao#listarMeioPagamento()
     */
    public List<MeioPagamento> listarMeioPagamento() {
        return listar(MeioPagamento.class);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao#listarTipoPessoaDDAAvalista()
     */
    public List<TipoPessoaDDAAvalista> listarTipoPessoaDDAAvalista() {
        return listar(TipoPessoaDDAAvalista.class);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao#listarTipoJuros()
     */
    public List<TipoJuros> listarTipoJuros() {
        return listar(TipoJuros.class);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao#listarTipoDesconto()
     */
    public List<TipoDesconto> listarTipoDesconto() {
        return listar(TipoDesconto.class);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao#listarTipoMulta()
     */
    public List<TipoMulta> listarTipoMulta() {
        return listar(TipoMulta.class);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao#listarTipoModeloCalculo()
     */
    public List<TipoModeloCalculo> listarTipoModeloCalculo() {
        return listar(TipoModeloCalculo.class);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao#listarAutorizacaoValorDivergente()
     */
    public List<AutorizacaoValorDivergente> listarAutorizacaoValorDivergente() {
        return listar(AutorizacaoValorDivergente.class);
    }
}
