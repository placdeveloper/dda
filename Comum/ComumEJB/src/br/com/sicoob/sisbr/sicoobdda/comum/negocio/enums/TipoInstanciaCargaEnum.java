/**
] * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.enums
 * Arquivo:         TipoInstanciaSWSEnum.java
 * Data Criação:    Jan 18, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_CARGA_MSGS_ESPECIFICAS_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_CARGA_MSGS_TODOS_EM_EXECUCAO;

import java.util.ArrayList;
import java.util.List;

/**
 * TipoInstanciaCargaEnum é responsável por
 * 
 * @author George.santos
 */
public enum TipoInstanciaCargaEnum {
    /* @formatter:off */
    ZERO1("0001", MOTOR_CARGA_MSGS_ESPECIFICAS_EM_EXECUCAO),
    TODOS("TODOS", MOTOR_CARGA_MSGS_TODOS_EM_EXECUCAO);
    /* @formatter:on */



    private String descricao = null;
    private long idParametroMotorCarga;

    /**
     * @param prDescricao
     * @param idParametroMotorRecebimento
     * @param idParametroMotorAbertura
     * @param tag
     * @param tipoArq
     * @param listaTiposMensagens
     * @param idParamRegistrosPorStep
     */
    private TipoInstanciaCargaEnum(String prDescricao, long idParametroMotorCarga) {
        this.descricao = prDescricao;
        this.idParametroMotorCarga = idParametroMotorCarga;
    }

    public String getDescricao() {
        return this.descricao;
    }

    /**
     * @return idParametroMotorCarga
     */
    public long getIdParametroMotorCarga() {
        return idParametroMotorCarga;
    }

    public static List<Integer> getListaCooperativasEspecificas() {
        List<Integer> listaCooperativasEspecificas = new ArrayList<>();
        for (TipoInstanciaCargaEnum instancia : TipoInstanciaCargaEnum.values()) {
            if (!instancia.getDescricao().equals(TipoInstanciaCargaEnum.TODOS.descricao)) {
                listaCooperativasEspecificas.add(Integer.valueOf(instancia.getDescricao()));
            }
        }
        return listaCooperativasEspecificas;
    }
}
