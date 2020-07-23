/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ListaHistoricoMensagem106Dto.java
 * Data Criação:    25 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.GsonUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * ListaHistoricoMensagem106Dto é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ListaHistoricoMensagem106Dto extends ArrayList<HistoricoMensagem106Dto> {

    /**
     * 
     */
    private static final long serialVersionUID = -6180710729687802718L;

    private Gson gson;

    /**
     * 
     */
    public ListaHistoricoMensagem106Dto() {
        super();
        this.gson = GsonUtil.getGson();
    }

    /**
     * @param listaHistorico
     */
    public ListaHistoricoMensagem106Dto(List<HistoricoMensagem106Dto> listaHistorico) {
        this();
        if (!ObjectUtil.isNull(listaHistorico)) {
            this.addAll(listaHistorico);
        }
    }

    /**
     * @param listaBoletoJson
     */
    public ListaHistoricoMensagem106Dto(String listaHistoricoMensagemJson) {
        this();
        this.addAll(this.gson.fromJson(listaHistoricoMensagemJson, ListaHistoricoMensagem106Dto.class));
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
