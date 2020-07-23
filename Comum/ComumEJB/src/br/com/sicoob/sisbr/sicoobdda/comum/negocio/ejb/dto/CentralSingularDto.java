/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         CentralSingularDTO.java
 * Data Criação:    Nov 05, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.CentralSingularVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.UnidadeVo;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * CentralSingularDTO é responsável por
 * 
 * @author Samuell.Ramos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.CentralSingularDTO")
public class CentralSingularDto extends BancoobDto {

    private List<CentralSingularVo> listaCentral;
    private List<CentralSingularVo> listaSingular;
    private List<UnidadeVo> listaUnidade;
    private Integer codGrauInstituicao;

    /**
     * @return o atributo listaCentral
     */
    public List<CentralSingularVo> getListaCentral() {
        return listaCentral;
    }

    /**
     * Define o atributo listaCentral
     */
    public void setListaCentral(List<CentralSingularVo> listaCentral) {
        this.listaCentral = listaCentral;
    }

    /**
     * @return o atributo listaSingular
     */
    public List<CentralSingularVo> getListaSingular() {
        return listaSingular;
    }

    /**
     * Define o atributo listaSingular
     */
    public void setListaSingular(List<CentralSingularVo> listaSingular) {
        this.listaSingular = listaSingular;
    }

    /**
     * @return o atributo listaUnidade
     */
    public List<UnidadeVo> getListaUnidade() {
        return listaUnidade;
    }

    /**
     * Define o atributo listaUnidade
     */
    public void setListaUnidade(List<UnidadeVo> listaUnidade) {
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
