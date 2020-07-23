/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ParametrosDto.java
 * Data Criação:    18 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ParametrosDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ParametroDTO")
public class ParametroDto extends BancoobDto {

    private static final long serialVersionUID = 4514806457413631249L;

    private List<ParametroDDA> listaParametro;
    private boolean permiteAlterarTodos;
    private Integer idInstituicao;

    /**
     * 
     */
    public ParametroDto() {
        super();
    }

    /**
     * @param listaParametro
     * @param permiteAlterarTodos
     */
    public ParametroDto(List<ParametroDDA> listaParametro, boolean permiteAlterarTodos, Integer idInstituicao) {
        super();
        this.listaParametro = listaParametro;
        this.permiteAlterarTodos = permiteAlterarTodos;
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return o atributo listaParametro
     */
    public List<ParametroDDA> getListaParametro() {
        return listaParametro;
    }

    /**
     * Define o atributo listaParametro
     */
    public void setListaParametro(List<ParametroDDA> listaParametro) {
        this.listaParametro = listaParametro;
    }

    /**
     * @return o atributo permiteAlterarTodos
     */
    public boolean isPermiteAlterarTodos() {
        return permiteAlterarTodos;
    }

    /**
     * Define o atributo permiteAlterarTodos
     */
    public void setPermiteAlterarTodos(boolean permiteAlterarTodos) {
        this.permiteAlterarTodos = permiteAlterarTodos;
    }

    /**
     * @return o atributo idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

}
