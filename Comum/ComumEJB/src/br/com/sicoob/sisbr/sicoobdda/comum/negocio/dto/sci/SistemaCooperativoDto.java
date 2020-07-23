/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  integracao-interna-privada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracao.interna.privada.dto.sci
 * Arquivo:         SistemaCooperativoDTO.java
 * Data Cria��o:    02/08/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Data transfer object para informa��es da entidade de institui��o do sistema SCI.
 * 
 * @author Kaio.Rocha
 * 
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.SistemaCooperativoFachadaDTO")
public class SistemaCooperativoDto extends BancoobDto {

    private static final long serialVersionUID = 1L;

    public static final String[] CAMPOS = new String[] { "bolAtivo", "codSistemaCooperativo", "dataCadastro", "idInstituicaoGestora", "nomeSistemaCooperativo" };

    private Boolean bolAtivo;
    private Integer codSistemaCooperativo;
    private Date dataCadastro;
    private Integer idInstituicaoGestora;
    private String nomeSistemaCooperativo;

    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

    public Integer getCodSistemaCooperativo() {
        return codSistemaCooperativo;
    }

    public void setCodSistemaCooperativo(Integer codSistemaCooperativo) {
        this.codSistemaCooperativo = codSistemaCooperativo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getIdInstituicaoGestora() {
        return idInstituicaoGestora;
    }

    public void setIdInstituicaoGestora(Integer idInstituicaoGestora) {
        this.idInstituicaoGestora = idInstituicaoGestora;
    }

    public String getNomeSistemaCooperativo() {
        return nomeSistemaCooperativo;
    }

    public void setNomeSistemaCooperativo(String nomeSistemaCooperativo) {
        this.nomeSistemaCooperativo = nomeSistemaCooperativo;
    }
}