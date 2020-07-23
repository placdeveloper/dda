/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         ArquivoEnviadoServico.java
 * Data Criação:    Jul 05, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoEnviadoDto;

/**
 * ArquivoEnviadoServico
 * 
 * @author Samuell.Ramos
 */
public interface ArquivoEnviadoServico extends OperacionalCrudServico<LogEnvioArquivoDDA> {
	
    /**
     * Método responsável por 
     * @return
     * @throws ComumException List<TipoMensagemDDA>
     * 
     */
    public List<TipoMensagemDDA> carregarListaTipoMensagem() throws ComumException;	
    /**
     * Método responsável por 
     * @param idLogEnvioArquivodda
     * @return
     * @throws ComumException ArquivoEnviadoDto
     * 
     */
    public ArquivoEnviadoDto obterArquivoEnviado(Long idLogEnvioArquivodda) throws ComumException;
    /**
     * Método responsável por 
     * @param logEnvioArquivoDDA
     * @throws BancoobException void
     * 
     */
    public void alterarArquivo(LogEnvioArquivoDDA logEnvioArquivoDDA) throws BancoobException;

}
