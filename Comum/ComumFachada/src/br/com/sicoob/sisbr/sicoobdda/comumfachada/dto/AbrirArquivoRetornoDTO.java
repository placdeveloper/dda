/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         AbrirArquivoRetornoDTO.java
 * Data Criação:    Jul 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * AbrirArquivoRetornoDTO
 * 
 * @author Danilo.Barros
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AbrirArquivoRetornoDto")
public class AbrirArquivoRetornoDTO extends BancoobDTO {
    private Boolean retorno;
    private String nomeArquivo;

    /**
     * 
     */
    public AbrirArquivoRetornoDTO() {
    }

    /**
     * @return o atributo retorno
     */
    public Boolean getRetorno() {
        return retorno;
    }

    /**
     * Define o atributo retorno
     */
    public void setRetorno(Boolean retorno) {
        this.retorno = retorno;
    }

    /**
     * @return o atributo nomeArquivo
     */
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    /**
     * Define o atributo nomeArquivo
     */
    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

}
