/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.vo
 * Arquivo:         ServidorDDAVO.java
 * Data Criação:    1 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ServidorDDAVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA")
public class ServidorDDAVO extends BancoobVO {

    private String codServidorDDA;
    private String descPerfil;
    private DateTimeDB dataHoraCadastro;
    private Boolean bolAtivo;

    /**
     * @return o atributo codServidorDDA
     */
    public String getCodServidorDDA() {
        return codServidorDDA;
    }

    /**
     * Define o atributo codServidorDDA
     */
    public void setCodServidorDDA(String codServidorDDA) {
        this.codServidorDDA = codServidorDDA;
    }

    /**
     * @return o atributo descPerfil
     */
    public String getDescPerfil() {
        return descPerfil;
    }

    /**
     * Define o atributo descPerfil
     */
    public void setDescPerfil(String descPerfil) {
        this.descPerfil = descPerfil;
    }

    /**
     * @return o atributo dataHoraCadastro
     */
    public DateTimeDB getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    /**
     * Define o atributo dataHoraCadastro
     */
    public void setDataHoraCadastro(DateTimeDB dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    /**
     * @return o atributo bolAtivo
     */
    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    /**
     * Define o atributo bolAtivo
     */
    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

}
