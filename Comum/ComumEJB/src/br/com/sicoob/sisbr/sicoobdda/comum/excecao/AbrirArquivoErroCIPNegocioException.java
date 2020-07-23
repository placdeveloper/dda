/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.excecao
 * Arquivo:         AbrirArquivoErroCIPNegocioException.java
 * Data Criação:    Jul 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.excecao;

import javax.ejb.ApplicationException;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AbrirArquivoRetornoDto;

@ApplicationException(rollback = false)
public class AbrirArquivoErroCIPNegocioException extends ComumNegocioException {
    private static final long serialVersionUID = 1L;
    private AbrirArquivoRetornoDto abrirArquivoRetornoDto;

    /**
     * @param chave
     */
    public AbrirArquivoErroCIPNegocioException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public AbrirArquivoErroCIPNegocioException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param parametros
     */
    public AbrirArquivoErroCIPNegocioException(String chave, Object... parametros) {
        super(chave, parametros);
    }

    /**
     * @param chave
     * @param excecao
     */
    public AbrirArquivoErroCIPNegocioException(String chave, Throwable excecao, AbrirArquivoRetornoDto abrirArquivoRetornoDto) {
        super(chave, excecao);
        this.abrirArquivoRetornoDto = abrirArquivoRetornoDto;
    }

    /**
     * @param excecao
     */
    public AbrirArquivoErroCIPNegocioException(Throwable excecao) {
        super(excecao);
    }

    /**
     * @return o atributo abrirArquivoRetornoDto
     */
    public AbrirArquivoRetornoDto getAbrirArquivoRetornoDto() {
        return abrirArquivoRetornoDto;
    }

}
