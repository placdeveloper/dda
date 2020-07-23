/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         ContingenciaDaoImpl.java
 * Data Criação:    Jan 3, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.NUM_BANCOOB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoContingencia;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BoletoPagamentoContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;
import br.com.sicoob.tipos.DateTime;

/**
 * ContingenciaDaoImpl
 * 
 * @author Danilo.Barros
 */
public class ContingenciaDaoImpl extends OperacionalCrudDaoDB2<HistoricoContingencia> implements ContingenciaDao {
    private static final String ARQUIVO_QUERIES = "sicoobdda_operacional.queries.xml";
    private static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional";
    private Map<String, Object> parametros = new HashMap<String, Object>();

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public ContingenciaDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, HistoricoContingencia.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao#listarHistoricoContingencias()
     */
    public List<GradeContingenciaDto> listarHistoricoContingencias() throws ComumException {
        List<GradeContingenciaDto> listaGradeContingenciaDtos = new ArrayList<GradeContingenciaDto>();
        GradeContingenciaDto contingenciaDto;
        Comando comando = null;
        Query query = null;
        try {
            comando = getComando("LISTA_HISTORICO_CONTINGENCIAS");
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            query = comando.criarNativeQuery(getEntityManager());
            List<Object[]> listaGradeContingencias = query.getResultList();

            for (Object[] contingencia : listaGradeContingencias) {
                contingenciaDto = new GradeContingenciaDto();
                contingenciaDto.setDescTipoContingencia(contingencia[0].toString());
                contingenciaDto.setSituacaoContingencia(contingencia[1].toString());
                contingenciaDto.setDataHoraSituacaoInicial(new DateTime(((Timestamp) contingencia[2]).getTime()));
                contingenciaDto.setIdUsuarioHabilitacao(contingencia[3].toString());
                contingenciaDto.setDataHoraSituacaoFinal(contingencia[4] == null ? null : new DateTime(((Timestamp) contingencia[4]).getTime()));
                contingenciaDto.setIdUsuarioDesabilitacao(contingencia[5] == null ? null : contingencia[5].toString());

                listaGradeContingenciaDtos.add(contingenciaDto);
            }
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        } catch (IllegalStateException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
        }
        return listaGradeContingenciaDtos;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao#incluirContingencia(br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoContingencia)
     */
    public void incluirContingencia(HistoricoContingencia historicoContingencia) throws BancoobException {
        incluir(historicoContingencia);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao#obterIdUltimaHabilitacaoContingencia()
     */
    public Long obterIdUltimaHabilitacaoContingencia() throws ComumException {
        return obterDados("LISTA_ID_HABILITACAO_CONTINGENCIA");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao#listarBoletosPagamentoContingencia()
     */
    public List<BoletoPagamentoContingenciaDto> listarBoletosPagamentoContingencia() throws ComumException {
        parametros.put("listaMensagensDDA", Arrays.asList(TipoMensagemDDA.ADDA108, TipoMensagemDDA.DDA0108));
        parametros.put("listaNumCodPortador", Arrays.asList(NUM_BANCOOB));
        return listar("LISTA_BOLETOS_PAGAMENTO_CONTINGENCIA", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao#atualizarMensagensBaixaOperacional()
     */
    public void atualizarMensagensBaixaOperacional() throws ComumException {
        executarUpdate("ATUALIZAR_MENSAGENS_BAIXA_OPERACIONAL");
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao#listarMensagemDDAContingencia()
     */
    public Integer obterQtdMensagemDDAContingencia() throws ComumException {
        Integer resultado = Constantes.INTEGER_ZERO;
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        try {
            conn = estabelecerConexao();

            comando = getComando("OBTER_QTD_MENSAGEMDDA_BAIXA_OPERACIONAL_CONTINGENCIA");
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

}
