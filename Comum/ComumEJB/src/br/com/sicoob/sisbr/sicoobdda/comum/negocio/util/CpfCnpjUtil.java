/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.util
 * Arquivo:         CpfCnpjUtil.java
 * Data Criação:    Aug 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * CpfCnpjUtil é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
public class CpfCnpjUtil {
    
    public static boolean isTipoIgualCNPJ(String tipo, String cnpj) {
        return ((tipo.charAt(0) == Constantes.TIPO_PESSOA_JURIDICA) && (cnpj.trim().length() == Constantes.TAMANHO_CNPJ));
    }
    
    public static boolean isTipoIgualCPF(String tipo, String cpf) {
        return ((tipo.charAt(0) == Constantes.TIPO_PESSOA_FISICA) && (cpf.trim().length() == Constantes.TAMANHO_CPF));
    }
    
    public static boolean isTipoIgualDocumento(String tipo, String doc) {
        boolean ret = true;
        if(tipo.charAt(0) == Constantes.TIPO_PESSOA_FISICA) {
            ret = isTipoIgualCPF(tipo, doc);
        } else if(tipo.charAt(0) == Constantes.TIPO_PESSOA_JURIDICA) {
            ret = isTipoIgualCNPJ(tipo, doc);
        }
        return ret;
    }
}
