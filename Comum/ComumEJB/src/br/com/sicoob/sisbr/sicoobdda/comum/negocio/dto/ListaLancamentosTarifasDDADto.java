/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ListaLancamentosTarifasDDADto.java
 * Data Criação:    24 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.GsonUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * ListaLancamentosTarifasDDADto é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ListaLancamentosTarifasDDADto extends ArrayList<LancamentosTarifasDDADto> {

    /**
     * 
     */
    private static final long serialVersionUID = 2611401043924827920L;

    private Gson gson;

    /**
     * 
     */
    public ListaLancamentosTarifasDDADto() {
        super();
        this.gson = GsonUtil.getGson();
    }

    /**
     * @param listaLancamentos
     */
    public ListaLancamentosTarifasDDADto(List<LancamentosTarifasDDADto> listaLancamentos) {
        this();
        if (!ObjectUtil.isNull(listaLancamentos)) {
            this.addAll(listaLancamentos);
        }
    }

    /**
     * @param listaBoletoJson
     */
    public ListaLancamentosTarifasDDADto(String listaLancamentosTarifasDDAJson) {
        this();
        this.addAll(this.gson.fromJson(listaLancamentosTarifasDDAJson, ListaLancamentosTarifasDDADto.class));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getJson() {
        return this.gson.toJson(this);
    }
}
