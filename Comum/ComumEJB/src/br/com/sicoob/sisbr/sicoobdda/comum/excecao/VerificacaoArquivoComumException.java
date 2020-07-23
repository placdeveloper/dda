/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.excecao
 * Arquivo:         VerificacaoArquivoComumException.java
 * Data Criação:    Jun 20, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.excecao;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSituacaoArquivo;

/**
 * VerificacaoArquivoComumException é responsável por
 * 
 * @author Adriano.Pinheiro
 */
public class VerificacaoArquivoComumException extends ComumException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public VerificacaoArquivoComumException(TipoSituacaoArquivo prTipoSituacao) {
        this(prTipoSituacao.getDescricao());
    }

    /**
     * @param chave
     */
    public VerificacaoArquivoComumException(String chave) {
        super(chave);
    }

    public VerificacaoArquivoComumException(String chave, Object... parametros) {
        super(chave, parametros);
    }

    public VerificacaoArquivoComumException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    public VerificacaoArquivoComumException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    public VerificacaoArquivoComumException(Throwable excecao) {
        super(excecao);
    }

}
