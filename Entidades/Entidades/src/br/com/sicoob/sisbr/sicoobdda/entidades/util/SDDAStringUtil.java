/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.util
 * Arquivo:         CobStringUtil.java
 * Data Criação:    Oct 16, 2013
 */
package br.com.sicoob.sisbr.sicoobdda.entidades.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.sicoob.tipos.DateTime;

/**
 * SDDAStringUtil é responsável por manipular strings, ou seja, conterá métodos para manipulação de string
 * 
 * @author Lucas.Moura
 */
public final class SDDAStringUtil {

    private SDDAStringUtil() {
        // not called
    }

    /**
     * Método responsável por verificar se o conteúdo da string informado no parâmetro é numérico.
     * 
     * @param conteudo a string a ser analisada.
     * @return <code>true</code> se a string for numerica, caso contrário <code>false</code>.
     * 
     */
    public static Boolean isNumeric(String conteudo) {
        Boolean condicao = Boolean.TRUE;

        if (conteudo != null && !conteudo.trim().equals("")) {
            for (int i = 0; i < conteudo.length(); i++) {
                Character caracterParametro = conteudo.charAt(i);

                if (!Character.isDigit(caracterParametro)) {
                    condicao = Boolean.FALSE;
                    break;
                }
            }
        } else {
            condicao = Boolean.FALSE;
        }

        return condicao;
    }

    /**
     * 
     * Método responsável por substituir caracteres especiais por escapes HTML.
     * 
     * @param texto - String a ser substituída com escapes HTML
     * @return String - String com escapes HTML
     * 
     */
    public static String escaparValorInvalidoXML(String texto) {
        StringBuffer out = new StringBuffer();
        char current;

        for (int i = 0; i < texto.length(); i++) {
            current = texto.charAt(i);

            // Adiciona apenas os campos válidos
            if ((current == 0x9) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF)) || ((current >= 0xE000) && (current <= 0xFFFD))
                    || ((current >= 0x10000) && (current <= 0x10FFFF))) {
                out.append("&#" + (int) current + ";");
            }
        }

        return out.toString();
    }

    /**
     * Método responsável por trocar o ENTER(\n) dado diretamento no banco por vazio("").
     * 
     * @param texto
     * @return String
     * 
     */
    public static String escaparEnterBDInvalido(String texto) {
        return texto.replaceAll("\n", "");
    }

    /**
     * Método responsável por converter um numero <code>long</code> e preenche a esquerda com casas decimais. <br>
     * Ex: 1006 --> 01006
     * 
     * @param valor O valor a ser formatado.
     * @param tamanho String a ser gerada.
     * @return o número formatado.
     */
    public static String longToString(long valor) {
        return Long.toString(valor);
    }

    /**
     * Retorna uma string preenchida com zeros de acordo com o tamanho do CPF.
     * 
     * @param numeroCPF
     * @return String
     * 
     */
    public static String incluirZerosCPF(String numeroCPF) {
        return incluirZeros(numeroCPF, 0, Constantes.TAMANHO_CPF);
    }

    /**
     * Retorna uma string preenchida com zeros de acordo com o tamanho do CNPJ.
     * 
     * @param numeroCNPJ
     * @return String
     * 
     */
    public static String incluirZerosCNPJ(String numeroCNPJ) {
        return incluirZeros(numeroCNPJ, 0, Constantes.TAMANHO_CNPJ);
    }

    /**
     * 
     * Retorna uma string preenchida com zeros de acordo com o tamanho passado no segundo parâmetro.
     * 
     * @param conteudo
     * @param indice
     * @param futuroTamanho
     * @return String
     * 
     */
    public static String incluirZeros(String conteudo, int indice, int futuroTamanho) {
        if (conteudo == null || conteudo.length() == 0) {
            return conteudo;
        }

        StringBuffer sb = new StringBuffer();
        if ((indice >= 0 && indice <= conteudo.length()) && futuroTamanho > 0) {
            sb.append(conteudo);

            if (indice == conteudo.length()) {
                while (sb.length() < futuroTamanho) {
                    sb.append('0');
                }
            } else {
                while (sb.length() < futuroTamanho) {
                    sb.insert(0, '0');
                }
            }
        }

        return sb.toString();
    }

    /**
     * Retorna uma string preenchida com zeros de acordo com o tamanho do CPF.
     * 
     * @param numeroCPF
     * @return String
     * 
     */
    public static String incluirZerosCPF(Number numeroCPF) {
        return incluirZeros(numeroCPF, 0, Constantes.TAMANHO_CPF);
    }

    /**
     * Retorna uma string preenchida com zeros de acordo com o tamanho do CNPJ.
     * 
     * @param numeroCNPJ
     * @return String
     * 
     */
    public static String incluirZerosCNPJ(Number numeroCNPJ) {
        return incluirZeros(numeroCNPJ, 0, Constantes.TAMANHO_CNPJ);
    }

    /**
     * Retorna uma string preenchida com zeros de acordo com o tamanho passado no segundo parâmetro.
     * 
     * @param valor Valor que terá caracteres <i>zeros</i> concatenados à esquerda. Se for passado <b>null</b> como parâmetro uma nova string vazia será criada.
     * @param tamanho número de zeros máximo a serem concatenados.
     * @return uma String com zeros vazios a sua esquerda.
     */
    public static String incluirZeros(Number conteudo, int indice, int tamanho) {
        if (conteudo != null) {
            return incluirZeros(conteudo.toString(), indice, tamanho);
        }

        return null;
    }

    /**
     * Método responsável por converter uma data do tipo <code>String</code> no formato informado para uma data do tipo <code>DateTime</code>.
     * 
     * @param data
     * @return <code>DateTime</code> retorna um objeto convertido em <code>DateTime</code> ou null se a String passada como parâmetro não estiver no formato
     *         informado.
     */
    public static DateTime stringToDateTime(String data, String formato) {
        DateTime dateTimeAuxiliar = null;

        try {
            if (data != null && formato != null) {
                dateTimeAuxiliar = new DateTime(new SimpleDateFormat(formato).parse(data).getTime());
            }
        } catch (ParseException e) {
            System.out.println("FALHA:" + e.getMessage());
            return null;
        }

        return dateTimeAuxiliar;
    }
}
