/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces
 * Arquivo:         ContingenciaServicoLocal.java
 * Data Cria��o:    Jan 3, 2017
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
     * M�todo respons�vel por incluir o hist�rico da conting�ncia
     * 
     * @param contingenciaDto
     * @throws ComumException
     * @throws BancoobException
     */
    void incluirHistoricoContingencia(ContingenciaDto contingenciaDto) throws ComumException, BancoobException;

    /**
     * M�todo respons�vel por atualizar as mensagens de baixa operacional recebidas durante um processo de conting�ncias para ADDA114 (baixa operacional em
     * conting�ncia).
     * 
     * @throws ComumExceptio
     */
    void alterarMensagensBaixaOperacionalContingencia() throws ComumException;

}
