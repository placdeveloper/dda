/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         AdministrativoServico.java
 * Data Cria��o:    Jul 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * AdministrativoServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface AdministrativoServico extends OperacionalServico {

    /**
     * M�todo respons�vel por atualizar a situa��o do boleto pelo SWS Administrativo.
     * 
     * @throws ComumException void
     * 
     */
    void atualizarSituacaoBoletos() throws ComumException;

}
