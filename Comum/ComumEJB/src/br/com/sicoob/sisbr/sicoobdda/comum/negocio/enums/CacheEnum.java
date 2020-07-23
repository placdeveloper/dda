/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         CacheEnum.java
 * Data Criação:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.DominioDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.InstituicaoDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ParametroDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ServidorDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * Enum responsável por originar as informações como classe e método à executar a fim de carregar o cache, onde ao definir um enum apenas com a classe Delegate,
 * o método "listar()" será executado como padrão.
 * 
 * Cuidado ao alterar a ordem de definição dos Enums.
 * 
 * @author Sicoob
 * @version 1.0
 * @since 26/06/2012
 *
 */
public enum CacheEnum {

    SERVIDOR(ServidorDDADelegate.class, "obterServidor"),
    PARAMETRO(ParametroDelegate.class, "listarAtivos", 24),
    INSTITUICAO(InstituicaoDelegate.class, "listarInstituicao", 8),
    DOMINIO_AUTORIZACAO_VALOR_DEVERGENTE(DominioDDADelegate.class, "listarAutorizacaoDivergenteBanco", 24),
    DOMINIO_SITUACAO_BOLETO_PAGAMENTO(DominioDDADelegate.class, "listarSituacaoBoletoPagamentoBanco", 24),
    DOMINIO_GRADE_HORARIA_DDA0110(DominioDDADelegate.class, "obterGradeHorariaReferenciaDDA0110Banco", 24);

    // Obrigatório
    private Class<?> classe;

    // Opcionais
    private String metodo;
    private Integer timeOut; // Período de atualização em horas

    /**
     * @param classe
     */
    private CacheEnum(Class<?> classe) {
        this.classe = classe;
    }

    /**
     * @param classe
     * @param metodo
     */
    private CacheEnum(Class<?> classe, String metodo) {
        this(classe);
        this.metodo = metodo;
    }

    /**
     * @param classe
     * @param timeOut
     * @param metodo
     */
    private CacheEnum(Class<?> classe, String metodo, Integer timeOut) {
        this(classe, metodo);
        this.timeOut = timeOut;
    }

    /**
     * @return classe
     */
    public Class<?> getClasse() {
        return classe;
    }

    /**
     * @return metodoExe
     */
    public String getMetodo() {
        return metodo;
    }

    /**
     * @return timeOut
     */
    public Integer getTimeOut() {
        return timeOut;
    }

    /**
     * Nome do método estático padrão para as classes que não forem definido um método de instância
     * 
     * @return
     */
    public String getInstance() {
        return "getInstance";
    }

    /**
     * Método padrão de execução para obtenção do objeto a fim de carregar o cache
     * 
     * @return
     */
    public String obterMetodoExe() {
        return ObjectUtil.isNull(metodo) ? "listar" : metodo;
    }

}
