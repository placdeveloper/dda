package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;

/**
 * BoletoDDAUtil é responsável por
 * 
 * @author Rodrigo.Neri
 */
public final class BoletoDDAUtil {

    private BoletoDDAUtil() {
    }

    /**
     * Método responsável por criar a instrução de desconto.
     * 
     * @param codTipoDesconto
     * @param valorPercDesconto
     * @param dataLimiteDesconto
     * @return
     */
    public static String obterInstrucao(String codTipoDesconto, BigDecimal valorPercDesconto, Date dataLimiteDesconto) {
        if (codTipoDesconto.equals(TipoDesconto.ISENTO)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Conceder desconto de ");

        sb.append(obterValorPercentualFormatado(codTipoDesconto, valorPercDesconto));

        if (codTipoDesconto.equals(TipoDesconto.VALOR_POR_ANTECIPACAO_DIA_UTIL) || codTipoDesconto.equals(TipoDesconto.PERCENTUAL_POR_ANTECIPACAO_DIA_UTIL)) {
            sb.append(" por dia útil de antecipação");
        } else if (codTipoDesconto.equals(TipoDesconto.VALOR_POR_ANTECIPACAO_DIA_CORRIDO) || codTipoDesconto.equals(TipoDesconto.PERCENTUAL_POR_ANTECIPACAO_DIA_CORRIDO)) {
            sb.append(" por dia corrido de antecipação");
        }

        if (!ObjectUtil.isNull(dataLimiteDesconto)) {
            if (codTipoDesconto.equals(TipoDesconto.PERCENTUAL_ATE_A_DATA_INFORMADA) || codTipoDesconto.equals(TipoDesconto.VALOR_FIXO_ATE_A_DATA_INFORMADA)) {
                sb.append(" até ");
            } else {
                sb.append(" anterior a ");
            }

            sb.append(new SimpleDateFormat(Constantes.FORMATO_DE_DATA_DD_MM_YYYY).format(dataLimiteDesconto));
        }

        sb.append(".");

        return sb.toString();
    }

    /**
     * Método responsável por obter o valor/percentual formatado
     * 
     * @param codTipoDesconto
     * @param valorPercDesconto
     * @return
     */
    private static String obterValorPercentualFormatado(String codTipoDesconto, BigDecimal valorPercDesconto) {
        StringBuilder sb = new StringBuilder();

        if (codTipoDesconto.equals(TipoDesconto.VALOR_FIXO_ATE_A_DATA_INFORMADA) || codTipoDesconto.equals(TipoDesconto.VALOR_POR_ANTECIPACAO_DIA_CORRIDO)
                || codTipoDesconto.equals(TipoDesconto.VALOR_POR_ANTECIPACAO_DIA_UTIL)) {
            sb.append(FormatadorUtil.formatarValorReal(valorPercDesconto.doubleValue()));
        } else {
            sb.append(FormatadorUtil.formatarPorcentagem(valorPercDesconto.doubleValue()));
        }

        return sb.toString();
    }

}
