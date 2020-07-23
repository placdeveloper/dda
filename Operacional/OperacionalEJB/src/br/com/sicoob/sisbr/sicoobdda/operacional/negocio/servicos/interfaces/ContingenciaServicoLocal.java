/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces
 * Arquivo:         ContingenciaServicoLocal.java
 * Data Criação:    Jan 3, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico;

/**
 * ContingenciaServicoLocal
 * 
 * @author Danilo.Barros
 */
public interface ContingenciaServicoLocal extends ContingenciaServico {

    /**
     * Método responsável por incluir o histórico da contingência
     * 
     * @param contingenciaDto
     * @throws ComumException
     * @throws BancoobException
     */
    void incluirHistoricoContingencia(ContingenciaDto contingenciaDto) throws ComumException, BancoobException;

    /**
     * Método responsável por atualizar as mensagens de baixa operacional recebidas durante um processo de contingências para ADDA114 (baixa operacional em
     * contingência).
     * 
     * @throws ComumExceptio
     */
    void alterarMensagensBaixaOperacionalContingencia() throws ComumException;

}
