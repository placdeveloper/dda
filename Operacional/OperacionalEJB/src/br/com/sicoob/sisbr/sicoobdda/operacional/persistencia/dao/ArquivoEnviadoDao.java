/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         ArquivoRecebidoDao.java
 * Data Criação:    Jan 27, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;

/**
 * ArquivoRecebidoDao
 * 
 * @author Samuell.Ramos
 */
public interface ArquivoEnviadoDao extends OperacionalCrudDaoIF<LogEnvioArquivoDDA> {
	
    /**
     * Método responsável por 
     * @param idLogEnvioArquivodda
     * @return
     * @throws ComumException LogEnvioArquivoDDAz
     * 
     */
    LogEnvioArquivoDDA obterEnvioArquivoDDA(Long idLogEnvioArquivodda) throws ComumException;
    
    /**
     * Método responsável por 
     * @return
     * @throws OperacionalException List<TipoMensagemDDA>
     * @throws ComumException 
     * 
     */
    List<TipoMensagemDDA> listarTipoMensagemAgenAdda() throws ComumException;
}
