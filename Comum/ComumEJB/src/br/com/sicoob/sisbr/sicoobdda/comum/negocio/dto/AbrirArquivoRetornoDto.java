/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         AbrirArquivoRetornoDto.java
 * Data Criação:    Jul 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * AbrirArquivoRetornoDto
 * 
 * @author Danilo.Barros
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.AbrirArquivoRetornoDTO")
public class AbrirArquivoRetornoDto extends BancoobDto {
    private static final long serialVersionUID = 1L;
    private Boolean retorno;
    private String nomeArquivo;

    /**
     * @param nomeArquivoErro
     */
    public AbrirArquivoRetornoDto(String nomeArquivo) {
        this.setNomeArquivo(nomeArquivo);
    }

    /**
     * 
     */
    public AbrirArquivoRetornoDto() {
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
