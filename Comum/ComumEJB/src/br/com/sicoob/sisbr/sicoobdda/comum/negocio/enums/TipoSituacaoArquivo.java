/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         TipoLockArquivo.java
 * Data Cria��o:    Jun 19, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoLockArquivo � respons�vel por
 * 
 * @author Adriano.Pinheiro
 */
public enum TipoSituacaoArquivo {

    ARQUIVO_OK("Arquivo sem locks"),
    ARQUIVO_INEXISTENTE("Arquivo informado n�o existe ou n�o foi encontrado"),
    ARQUIVO_ILEGIVEL("Arquivo informado n�o pode ser lido"),
    ARQUIVO_INALTERAVEL("Arquivo informado n�o pode ser alterado"),
    ARQUIVO_NAO_E_ARQUIVO("Arquivo informado n�o � arquivo"),
    ARQUIVO_EM_TRANSFERENCIA("Arquivo informado acessado antes do prazo definido no par�metro 39 ");

    private String descricao;

    private TipoSituacaoArquivo(String prDescricao) {
        this.descricao = prDescricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
