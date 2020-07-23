/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         ParametroDTO.java
 * Data Criação:    19 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ParametroVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ParametroDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParametroDto")
public class ParametroDTO extends BancoobDTO {

    private List<ParametroVO> listaParametro;
    private boolean permiteAlterarTodos;
    private Integer idInstituicao;

    /**
     * @return o atributo listaParametro
     */
    public List<ParametroVO> getListaParametro() {
        return listaParametro;
    }

    /**
     * Define o atributo listaParametro
     */
    public void setListaParametro(List<ParametroVO> listaParametro) {
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
