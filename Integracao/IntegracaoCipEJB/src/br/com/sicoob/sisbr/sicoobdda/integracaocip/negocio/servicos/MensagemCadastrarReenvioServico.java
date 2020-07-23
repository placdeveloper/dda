/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         CadastrarBeneficiarioServico.java
 * Data Cria��o:    May 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ReenvioArquivoMensagemDto;

/**
 * Interface que define o servico padrao do sistema CadastroBeneficiario
 * 
 * @author Sicoob
 */
public interface MensagemCadastrarReenvioServico extends IntegracaoCipServico {
    /**
     * M�todo respons�vel por cadastrar o Reenvio da mensagem - Codigo da Mensagem DDA0215
     * 
     * @param reenvioArquivoMensagemDto
     * @throws ComumException void
     * 
     */
    void cadastrarReenvio(ReenvioArquivoMensagemDto reenvioArquivoMensagemDto) throws ComumException;
}