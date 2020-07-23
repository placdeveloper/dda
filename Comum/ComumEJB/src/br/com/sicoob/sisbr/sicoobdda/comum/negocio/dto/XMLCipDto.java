/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         XMLCipDto.java
 * Data Criação:    Oct 28, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * XMLCipDto
 * 
 * @author Rafael.Silva
 */
public class XMLCipDto extends BancoobDto {

    private static final long serialVersionUID = 1L;

    private Long idMensagem;
    private String codTipoMensagem;
    private List<String> listaCodErro;

    /**
     * @return the idMensagem
     */
    public Long getIdMensagem() {
        return idMensagem;
    }

    /**
     * @param idMensagem the idMensagem to set
     */
    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
    }

    /**
     * @return the listaCodErro
     */
    public List<String> getListaCodErro() {
        if (listaCodErro == null) {
            listaCodErro = new ArrayList<String>();
        }
        return listaCodErro;
    }

    /**
     * @param listaCodErro the listaCodErro to set
     */
    public void setListaCodErro(List<String> listaCodErroCIP) {
        this.listaCodErro = listaCodErroCIP;
    }

    /**
     * @return the codTipoMensagem
     */
    public String getCodTipoMensagem() {
        return codTipoMensagem;
    }

    /**
     * @param codTipoMensagem the codTipoMensagem to set
     */
    public void setCodTipoMensagem(String codTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
    }

}
