/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         CentralSingularDTO.java
 * Data Criação:    Oct 15, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.CentralSingularVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.UnidadeVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * CentralSingularDTO é responsável por
 * 
 * @author Samuell.Ramos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto.CentralSingularDto")
public class CentralSingularDTO extends BancoobDTO {

    private List<CentralSingularVO> listaCentral;
    private List<CentralSingularVO> listaSingular;
    private List<UnidadeVO> listaUnidade;
    private Integer codGrauInstituicao;

    /**
     * @return o atributo listaCentral
     */
    public List<CentralSingularVO> getListaCentral() {
        return listaCentral;
    }

    /**
     * Define o atributo listaCentral
     */
    public void setListaCentral(List<CentralSingularVO> listaCentral) {
        this.listaCentral = listaCentral;
    }

    /**
     * @return o atributo listaSingular
     */
    public List<CentralSingularVO> getListaSingular() {
        return listaSingular;
    }

    /**
     * Define o atributo listaSingular
     */
    public void setListaSingular(List<CentralSingularVO> listaSingular) {
        this.listaSingular = listaSingular;
    }

    /**
     * @return o atributo listaUnidade
     */
    public List<UnidadeVO> getListaUnidade() {
        return listaUnidade;
    }

    /**
     * Define o atributo listaUnidade
     */
    public void setListaUnidade(List<UnidadeVO> listaUnidade) {
        this.listaUnidade = listaUnidade;
    }

    /**
     * @return o atributo codGrauInstituicao
     */
    public Integer getCodGrauInstituicao() {
        return codGrauInstituicao;
    }

    /**
     * Define o atributo codGrauInstituicao
     */
    public void setCodGrauInstituicao(Integer codGrauInstituicao) {
        this.codGrauInstituicao = codGrauInstituicao;
    }

}
