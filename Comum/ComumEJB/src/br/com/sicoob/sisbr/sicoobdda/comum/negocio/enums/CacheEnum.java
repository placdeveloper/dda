/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         CacheEnum.java
 * Data Cria��o:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.DominioDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.InstituicaoDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ParametroDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ServidorDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * Enum respons�vel por originar as informa��es como classe e m�todo � executar a fim de carregar o cache, onde ao definir um enum apenas com a classe Delegate,
 * o m�todo "listar()" ser� executado como padr�o.
 * 
 * Cuidado ao alterar a ordem de defini��o dos Enums.
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

    // Obrigat�rio
    private Class<?> classe;

    // Opcionais
    private String metodo;
    private Integer timeOut; // Per�odo de atualiza��o em horas

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
     * Nome do m�todo est�tico padr�o para as classes que n�o forem definido um m�todo de inst�ncia
     * 
     * @return
     */
    public String getInstance() {
        return "getInstance";
    }

    /**
     * M�todo padr�o de execu��o para obten��o do objeto a fim de carregar o cache
     * 
     * @return
     */
    public String obterMetodoExe() {
        return ObjectUtil.isNull(metodo) ? "listar" : metodo;
    }

}
