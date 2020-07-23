/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         ContingenciaDao.java
 * Data Cria��o:    Jan 3, 2017
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
     * M�todo
     * 
     * @param
     * @return List<GradeContingenciaDto>
     * @throws ComumException
     */
    List<GradeContingenciaDto> listarHistoricoContingencias() throws ComumException;

    /**
     * M�todo
     * 
     * @param
     * @return
     * @throws BancoobException
     */
    void incluirContingencia(HistoricoContingencia historicoContingencia) throws BancoobException;

    /**
     * M�todo
     * 
     * @param
     * @return Long
     * @throws ComumException
     */
    Long obterIdUltimaHabilitacaoContingencia() throws ComumException;

    /**
     * M�todo
     * 
     * @param
     * @return List
     * @throws ComumException
     */
    List<BoletoPagamentoContingenciaDto> listarBoletosPagamentoContingencia() throws ComumException;

    /**
     * M�todo
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
