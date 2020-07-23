/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         TipoLockArquivo.java
 * Data Criação:    Jun 19, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoLockArquivo é responsável por
 * 
 * @author Adriano.Pinheiro
 */
public enum TipoSituacaoArquivo {

    ARQUIVO_OK("Arquivo sem locks"),
    ARQUIVO_INEXISTENTE("Arquivo informado não existe ou não foi encontrado"),
    ARQUIVO_ILEGIVEL("Arquivo informado não pode ser lido"),
    ARQUIVO_INALTERAVEL("Arquivo informado não pode ser alterado"),
    ARQUIVO_NAO_E_ARQUIVO("Arquivo informado não é arquivo"),
    ARQUIVO_EM_TRANSFERENCIA("Arquivo informado acessado antes do prazo definido no parâmetro 39 ");

    private String descricao;

    private TipoSituacaoArquivo(String prDescricao) {
        this.descricao = prDescricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
