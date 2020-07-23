package br.com.sicoob.sisbr.sicoobdda.entidades.util;

import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.BRANCO;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.MaskFormatter;

/**
 * FormatadorUtil � respons�vel por formatar o texto de acordo com a m�scara informada.
 * 
 * @author Rodrigo.Neri
 */
public final class FormatadorUtil {

    static Logger lg = Logger.getLogger("FormatadorUtil");

    private FormatadorUtil() {
        // not called
    }

    public static final String MASCARA_CNPJ = "##.###.###/####-##";
    public static final String MASCARA_CPF = "###.###.###-##";

    public static final String MASCARA_CEP = "#####-###";

    public static final String MASCARA_TELEFONE_8_DIGITOS = "####-####";
    public static final String MASCARA_TELEFONE_9_DIGITOS = "#####-####";
    public static final String MASCARA_TELEFONE_10_DIGITOS = "(##)####-####";
    public static final String MASCARA_TELEFONE_11_DIGITOS = "(##)#####-####";
    public static final String MASCARA_TELEFONE_12_DIGITOS = "##(##)####-####";
    public static final String MASCARA_TELEFONE_13_DIGITOS = "##(##)#####-####";
    public static final String MASCARA_TELEFONE_14_DIGITOS = "##(##)#####-#####";

    public static final String MASCARA_NUMERO_CONTA = "#-#";

    public static final String MASCARA_LINHA_DIGITAVEL = "#####.##### #####.###### #####.###### # ##############";

    public static final String PATTERN_SOMENTE_NUMEROS = "\\D";

    /**
     * M�todo respons�vel por formatar o texto de acordo com a m�scara informada.
     * 
     * @param texto
     * @param mascara
     * @return String
     */
    public static String formatar(String texto, String mascara) {

        return formatar(texto, mascara, false);
    }

    /**
     * M�todo respons�vel por formatar o texto de acordo com a m�scara informada.
     * 
     * @param texto
     * @param mascara
     * @return String
     */
    public static String formatar(Number texto, String mascara) {
        return formatar(texto, mascara, false);
    }

    /**
     * M�todo respons�vel por formatar o texto de acordo com a m�scara informada.
     * 
     * @param texto
     * @param mascara
     * @param direitaPraEsquerda indica se a formata��o deve ser feita da direita pra esquerda.
     * @return String
     */
    public static String formatar(String texto, String mascara, boolean direitaPraEsquerda) {
        if (texto == null || mascara == null) {
            return "";
        }

        if (direitaPraEsquerda) {
            return formatarDireitaParaEsquerda(texto, mascara);
        } else {
            return formatarEsquerdaParaDireita(texto, mascara);
        }
    }

    /**
     * M�todo respons�vel por formatar o texto de acordo com a m�scara informada.
     * 
     * @param texto
     * @param mascara
     * @param direitaPraEsquerda indica se a formata��o deve ser feita da direita pra esquerda.
     * @return String
     */
    public static String formatar(Number texto, String mascara, boolean direitaPraEsquerda) {
        if (texto == null || mascara == null) {
            return null;
        }

        if (direitaPraEsquerda) {
            return formatarDireitaParaEsquerda(texto.toString(), mascara);
        } else {
            return formatarEsquerdaParaDireita(texto.toString(), mascara);
        }
    }

    /**
     * M�todo respons�vel por formatar o texto de acordo com a m�scara informada.<br>
     * Exemplo:<br>
     * <p>
     * 1) m�scara = ###-#, texto = 1234, resultado = 123-4
     * <p>
     * 2) m�scara = #-#, texto = 1234, resultado = 1-2
     * <p>
     * 3) m�scara = ###-#, texto = 123, resultado = 123-
     * <p>
     * 4) m�scara = #.##-#, texto = 1234, resultado = 1.23-4
     * 
     * @param texto
     * @param mascara
     * @return String
     */
    private static String formatarEsquerdaParaDireita(String texto, String mascara) {
        try {
            MaskFormatter mf = new MaskFormatter(mascara);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(texto);
        } catch (ParseException e) {
            lg.logp(Level.SEVERE, FormatadorUtil.class.getName(), "FormatarEsquerdaParaDireita", "Falha na Formata��o", e);
            return texto;
        }
    }

    /**
     * M�todo respons�vel por formatar o texto de acordo com a m�scara informada.<br>
     * Exemplo:<br>
     * <p>
     * 1) m�scara = #-#, texto = 123456, resultado = 12345-6
     * <p>
     * 2) m�scara = ####-#, texto = 123, resultado = 12-3
     * <p>
     * 3) m�scara = ##.##-#, texto = 1234, resultado = 1.23-4
     * 
     * @param texto
     * @param mascara
     * @return String
     */
    private static String formatarDireitaParaEsquerda(String texto, String mascara) {
        StringBuffer valor = new StringBuffer();

        for (int i = mascara.length() - 1, j = texto.length() - 1; i >= 0 && j >= 0; i--, j--) {
            char c = mascara.charAt(i);
            if (c == '#') {
                valor.append(texto.charAt(j));
            } else {
                valor.append(c);
                j++;
            }
        }

        StringBuffer resultado = new StringBuffer("");

        // como o valor fica invertido, neste momento � feita a invers�o para que fique corretamente
        for (int i = valor.length() - 1; i >= 0; i--) {
            resultado.append(valor.charAt(i));
        }

        int length = resultado.toString().replaceAll("[,/.-]", "").length();

        // Se houver texto excedente ir� utilizar no in�cio do texto
        if (length < texto.length()) {
            return texto.subSequence(0, texto.length() - length) + resultado.toString();
        }

        return resultado.toString();
    }

    /**
     * M�todo respons�vel por formatar o valor informado conforme o padr�o monet�rio utilizando at� 4 casas decimais.
     * 
     * @param valor
     * @return String no formato R$ 0,00
     */
    public static String formatarValorReal(Double valor) {
        String retorno = null;

        if (valor != null) {
            NumberFormat formatoReal = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setCurrencySymbol("R$");
            dfs.setGroupingSeparator('.');
            dfs.setMonetaryDecimalSeparator(',');
            ((DecimalFormat) formatoReal).setDecimalFormatSymbols(dfs);

            formatoReal.setMaximumFractionDigits(4);
            retorno = formatoReal.format(valor);
        }

        return retorno;
    }

    /**
     * M�todo respons�vel por formatar o valor informado conforme o padr�o monet�rio utilizando at� 4 casas decimais.
     * 
     * @param valor
     * @return String no formato R$ 0,00
     */
    public static String formatarValorReal(Float valor) {
        return formatarValorReal(valor.doubleValue());
    }

    /**
     * M�todo respons�vel por formatar o valor informado para porcentagem.
     * 
     * @param valor
     * @return String no formato 0,00%
     */
    public static String formatarPorcentagem(Double valor) {
        String retorno = null;

        if (valor != null) {
            NumberFormat formato = NumberFormat.getPercentInstance();
            formato.setMaximumFractionDigits(4);
            retorno = formato.format(valor / 100);
        }

        return retorno;
    }

    /**
     * M�todo respons�vel por formatar o valor informado para porcentagem.
     * 
     * @param valor
     * @return String no formato 0,00%
     */
    public static String formatarPorcentagem(Integer valor) {
        return formatarPorcentagem(valor.doubleValue());
    }

    /**
     * M�todo respons�vel por formatar o valor informado para porcentagem.
     * 
     * @param valor
     * @return String no formato 0,00%
     */
    public static String formatarPorcentagem(Float valor) {
        return formatarPorcentagem(valor.doubleValue());
    }

    public static String formatarNumTelefone(Long numTelefone) {
        if (numTelefone != null) {
            return formatarNumTelefone(numTelefone.toString());
        } else {
            return null;
        }
    }

    /**
     * M�todo respons�vel por aplicar a mascara ao telefone.
     * 
     * @param numTelefone
     * @return String telefone formatado
     */
    public static String formatarNumTelefone(String numTelefone) {
        if (numTelefone != null) {
            if (numTelefone.trim().length() == 8) {
                numTelefone = FormatadorUtil.formatar(numTelefone, FormatadorUtil.MASCARA_TELEFONE_8_DIGITOS);
            } else if (numTelefone.trim().length() == 9) {
                numTelefone = FormatadorUtil.formatar(numTelefone, FormatadorUtil.MASCARA_TELEFONE_9_DIGITOS);
            } else if (numTelefone.trim().length() == 10) {
                numTelefone = FormatadorUtil.formatar(numTelefone, FormatadorUtil.MASCARA_TELEFONE_10_DIGITOS);
            } else if (numTelefone.trim().length() == 11) {
                numTelefone = FormatadorUtil.formatar(numTelefone, FormatadorUtil.MASCARA_TELEFONE_11_DIGITOS);
            } else if (numTelefone.trim().length() == 12) {
                numTelefone = FormatadorUtil.formatar(numTelefone, FormatadorUtil.MASCARA_TELEFONE_12_DIGITOS);
            } else if (numTelefone.trim().length() == 13) {
                numTelefone = FormatadorUtil.formatar(numTelefone, FormatadorUtil.MASCARA_TELEFONE_13_DIGITOS);
            } else if (numTelefone.trim().length() == 14) {
                numTelefone = FormatadorUtil.formatar(numTelefone, FormatadorUtil.MASCARA_TELEFONE_14_DIGITOS);
            }
        }

        return numTelefone;
    }

    public static String removerFormatacao(String pattern, String string) {
        if (string != null && !BRANCO.equalsIgnoreCase(string)) {
            string = string.replaceAll(pattern, BRANCO);
        }
        return string;
    }

    /**
     * M�todo respons�vel por formatar o valor com pontua��o
     * 
     * @param valor
     * @param casasDecimais
     * @return String
     */
    public static String formatarValor(Integer valor, int casasDecimais) {
        String retorno = null;

        if (valor != null) {
            NumberFormat formato = NumberFormat.getIntegerInstance();
            formato.setMaximumFractionDigits(casasDecimais);
            retorno = formato.format(valor);
        }

        return retorno;
    }

}
