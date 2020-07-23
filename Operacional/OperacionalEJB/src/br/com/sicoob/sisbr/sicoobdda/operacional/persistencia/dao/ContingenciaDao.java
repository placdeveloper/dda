/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         ContingenciaDao.java
 * Data Criação:    Jan 3, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoContingencia;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BoletoPagamentoContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeContingenciaDto;

/**
 * ContingenciaDao
 * 
 * @author Danilo.Barros
 */
public interface ContingenciaDao extends OperacionalCrudDaoIF<HistoricoContingencia> {

    /**
     * Método
     * 
     * @param
     * @return List<GradeContingenciaDto>
     * @throws ComumException
     */
    List<GradeContingenciaDto> listarHistoricoContingencias() throws ComumException;

    /**
     * Método
     * 
     * @param
     * @return
     * @throws BancoobException
     */
    void incluirContingencia(HistoricoContingencia historicoContingencia) throws BancoobException;

    /**
     * Método
     * 
     * @param
     * @return Long
     * @throws ComumException
     */
    Long obterIdUltimaHabilitacaoContingencia() throws ComumException;

    /**
     * Método
     * 
     * @param
     * @return List
     * @throws ComumException
     */
    List<BoletoPagamentoContingenciaDto> listarBoletosPagamentoContingencia() throws ComumException;

    /**
     * Método
     * 
     * @param listaIdMensagemDDA
     * 
     * @param
     * @return
     * @throws ComumException
     */
    void atualizarMensagensBaixaOperacional() throws ComumException;

    Integer obterQtdMensagemDDAContingencia() throws ComumException;

}
