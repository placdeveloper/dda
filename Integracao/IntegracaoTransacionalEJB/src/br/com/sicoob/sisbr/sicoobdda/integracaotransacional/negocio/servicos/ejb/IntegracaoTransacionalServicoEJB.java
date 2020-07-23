package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalNegocioException;

/**
 * Implementacao base de todos os servicos do sistema Integracao Transacional
 * 
 * @author george.santos
 */
public abstract class IntegracaoTransacionalServicoEJB extends ComumServicoEJB implements Transacao {

    /**
     * SCX - SICOOB CAIXA
     */
    public static final short CANAL_CAIXA = 2;

    /**
     * SATM - SICOOB ATM
     */
    public static final short CANAL_ATM = 3;
    /**
     * SIBK - SICOOBNET PESSOAL
     */
    public static final short CANAL_IB = 4;
    /**
     * SOFC - SICOOBNET EMPRESARIAL
     */
    public static final short CANAL_OFFICE = 5;
    /**
     * SWAP - SICOOBNET CELULAR
     */
    public static final short CANAL_CELULAR = 6;
    /**
     * SCED - CEDENTE
     */
    public static final short CANAL_CEDENTE = 9;
    /**
     * SCOS - CORRESPONDENTE SICOOB
     */
    public static final short CANAL_CORRESPONDENTE_SICOOB = 12;
    /**
     * SWEP - SICOOBNET CELULAR EMPRESARIAL
     */
    public static final short CANAL_CELULAR_EMPRESARIAL = 17;

    /**
     * SWAP - SICOOBNET CONTA PAGAMENTO DIGITAL
     */
    public static final short CANAL_CONTA_PAGAMENTO_DIGITAL = 53;

    /**
     * SNET - SICOOBNET (Novo)
     */
    public static final short CANAL_SICOOBNET = 54;

    public static final Integer RETORNO_SUCESSO = 1;
    public static final Integer RETORNO_ERRO = 0;

    private SimpleDateFormat formatterDataHora = new SimpleDateFormat(Constantes.PATTERN_DATA_HORA_TRANSACAO);
    private SimpleDateFormat formatterData = new SimpleDateFormat(Constantes.PATTERN_DATA_TRANSACAO);
    private SimpleDateFormat formatterDataHoraDB2 = new SimpleDateFormat(Constantes.PATTERN_DATA_HORA_BD);

    /**
     * Indica se na mensagem recebida, um parâmetro com a chave fornecida.
     * 
     * @param mensagem
     * @param chaveRotulo
     * @return Caso exista o parâmetro é retorna <code>true</code>, caso contrátrio é retornado <code>false</code>
     */
    protected final Boolean contemParametro(Mensagem mensagem, String chaveRotulo) {
        debug("### Verificando se contém parâmetro...");
        debug("Parâmetro - mensagem: " + mensagem);
        debug("Parâmetro - chaveRotulo: " + chaveRotulo);

        Parametro parametro = mensagem.recuperarParametro(chaveRotulo);
        return !ObjectUtil.isNull(parametro);
    }

    /**
     * Obtêm um valor do tipo {@link Integer} de um parâmetro.<br>
     * Caso seja valor {@link Object} seja:
     * <ul>
     * <li>{@link String} - {@link Integer#valueOf(String)}</li>
     * <li>{@link Long} - {@link Long#intValue()}</li>
     * <li>Outros - Cast direto para {@link Integer}</li>
     * </ul>
     * 
     * @param parametro
     * @return
     */
    protected final Integer obterValorInteger(Parametro parametro) {
        debug("### Obtendo valor Integer do parâmetro...");
        debug("Parâmetro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do parâmetro: " + parametro.getValor());

        if (parametro.getValor() instanceof Byte) {
            return ((Byte) parametro.getValor()).intValue();
        }
        if (parametro.getValor() instanceof Short) {
            return ((Short) parametro.getValor()).intValue();
        }
        if (parametro.getValor() instanceof String) {
            if (parametro.getValor().toString().equals(Constantes.BRANCO)) {
                return null;
            }
            return Integer.valueOf(parametro.getValor().toString());
        }
        if (parametro.getValor() instanceof Long) {
            return ((Long) parametro.getValor()).intValue();
        }
        if (parametro.getValor() instanceof BigDecimal) {
            return ((BigDecimal) parametro.getValor()).intValue();
        }
        return (Integer) parametro.getValor();
    }

    protected final Long obterValorLong(Parametro parametro) {
        debug("### Obtendo valor Long do parâmetro...");
        debug("Parâmetro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do parâmetro: " + parametro.getValor());

        if (parametro.getValor() instanceof Short) {
            return ((Long) parametro.getValor()).longValue();
        }
        if (parametro.getValor() instanceof String) {
            if (parametro.getValor().toString().equals(Constantes.BRANCO)) {
                return null;
            }
            return Long.valueOf(parametro.getValor().toString());
        }
        if (parametro.getValor() instanceof Long) {
            return ((Long) parametro.getValor()).longValue();
        }
        if (parametro.getValor() instanceof BigDecimal) {
            return ((BigDecimal) parametro.getValor()).longValue();
        }
        if (parametro.getValor() instanceof Integer) {
            return Long.valueOf(parametro.getValor().toString());
        }
        return (Long) parametro.getValor();
    }

    /**
     * Obtêm um valor do tipo {@link BigInteger} de um parâmetro.<br>
     * Caso seja valor {@link Object} seja:
     * <ul>
     * <li>{@link String} - {@link BigInteger#BigInteger(String)}</li>
     * <li>{@link Long} - {@link BigInteger#valueOf(long)}</li>
     * <li>{@link Integer} - {@link NumberUtils#createBigInteger(String)}</li>
     * <li>Outros - Cast direto para {@link BigInteger}</li>
     * </ul>
     * 
     * @param parametro
     * @return
     */
    protected final BigInteger obterValorBigInteger(Parametro parametro) {
        debug("### Obtendo valor BigInteger do parâmetro...");
        debug("Parâmetro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do parâmetro: " + parametro.getValor());

        if (parametro.getValor() instanceof String) {
            if (parametro.getValor().toString().equals(Constantes.BRANCO)) {
                return null;
            }
            return new BigInteger(parametro.getValor().toString());
        }
        if (parametro.getValor() instanceof Long) {
            return BigInteger.valueOf((Long) parametro.getValor());
        }
        if (parametro.getValor() instanceof Integer) {
            return NumberUtils.createBigInteger(parametro.getValor().toString());
        }
        return (BigInteger) parametro.getValor();
    }

    /**
     * Método responsável por obter o valor do parâmetro como BigDecimal
     * 
     * @param parametro
     * @return
     */
    protected final BigDecimal obterValorBigDecimal(Parametro parametro) {
        debug("### Obtendo valor BigDecimal do parâmetro...");
        debug("Parâmetro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do parâmetro: " + parametro.getValor());

        if (parametro.getValor() instanceof String && parametro.getValor().toString().equals(Constantes.BRANCO)) {
            return null;
        }
        return NumberUtils.createBigDecimal(parametro.getValor().toString());
    }

    /**
     * Método responsável por obter o valor do parâmetro como short
     * 
     * @param parametro
     * @return
     */
    public Short obterValorShort(Parametro parametro) {
        debug("### Obtendo valor Short do parâmetro...");
        debug("Parâmetro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do parâmetro: " + parametro.getValor());

        if (ObjectUtil.isEmpty(parametro.getValor().toString())) {
            return null;
        }

        return Short.parseShort(parametro.getValor().toString());
    }

    /**
     * Método responsável por obter o valor do parâmetro como double.
     * 
     * @param parametro
     * @return
     */
    public Double obterValorDouble(Parametro parametro) {
        debug("### Obtendo valor Double do parâmetro...");
        debug("Parâmetro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do parâmetro: " + parametro.getValor());

        if (ObjectUtil.isEmpty(parametro.getValor().toString())) {
            return null;
        }
        return Double.parseDouble(parametro.getValor().toString());
    }

    /**
     * Método responsável por obter o valor do parâmetro como DateTimeDB.
     * 
     * @param parametro
     * @return
     */
    public DateTimeDB obterValorDateTimeDB(Parametro parametro) {
        debug("### Obtendo valor DateTimeDB do parâmetro...");
        debug("Parâmetro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do parâmetro: " + parametro.getValor());

        if (parametro.getValor() instanceof Date) {
            return new DateTimeDB(((Date) parametro.getValor()).getTime());
        }
        return null;
    }

    /**
     * Método responsável por obter o valor do parâmetro como String.
     * 
     * @param parametro
     * @return
     */
    public String obterValorString(Parametro parametro) {
        debug("### Obtendo valor String do parâmetro...");
        debug("Parâmetro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do parâmetro: " + parametro.getValor());

        return parametro.getValor().toString();
    }

    /**
     * Método responsável por obter o valor do parâmetro como Date.
     * 
     * @param parametro
     * @return
     */
    public Date obterValorDate(Parametro parametro) {
        debug("### Obtendo valor Date do parâmetro...");
        debug("Parâmetro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do parâmetro: " + parametro.getValor());

        if (parametro.getValor() instanceof Date) {
            return (Date) parametro.getValor();
        }

        return null;
    }

    /**
     * Método responsável por obter o valor do parâmetro como boolean
     * 
     * @param parametro
     * @return
     * @throws IntegracaoTransacionalNegocioException
     */
    protected final Boolean obterValorBoolean(Parametro parametro) throws IntegracaoTransacionalNegocioException {
        debug("### Obtendo valor Boolean do parâmetro...");
        debug("Parâmetro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        Object valor = parametro.getValor();
        debug("Valor do parâmetro: " + valor);

        String valorStr = "";

        if (!ObjectUtil.isNull(valor)) {
            valorStr = valor.toString();
            debug("Valor str: " + valorStr);
        }

        if (valorStr.equals("1")) {
            return true;
        } else if (valorStr.equals("0")) {
            return false;
        } else {
            throw new IntegracaoTransacionalNegocioException("integracao.transacional.parametro.valor.invalido", parametro.getNome());
        }
    }

    /**
     * Método responsável por obter o parâmetro pelo rótulo informado.
     * 
     * @param mensagem
     * @param rotulo
     * @return
     * @throws IntegracaoTransacionalNegocioException
     */
    public Parametro obterParametro(Mensagem mensagem, String rotulo) throws IntegracaoTransacionalNegocioException {
        return obterParametro(mensagem, rotulo, true);
    }

    /**
     * Método responsável por obter o parâmetro pelo rótulo informado.
     * 
     * @param mensagem
     * @param rotulo
     * @param obrigatorio
     * @return
     * @throws IntegracaoTransacionalNegocioException
     */
    public Parametro obterParametro(Mensagem mensagem, String rotulo, boolean obrigatorio) throws IntegracaoTransacionalNegocioException {
        debug("### Obtendo parâmetro...");
        debug("Parâmetro - mensagem: " + mensagem);
        debug("Parâmetro - rotulo: " + rotulo);

        Parametro parametro = mensagem.recuperarParametro(rotulo);

        debug("Parâmetro recuperado: " + parametro);

        // Se for um parâmetro obrigatório e o objeto for nulo
        if (obrigatorio && ObjectUtil.isNull(parametro)) {
            throw new IntegracaoTransacionalNegocioException("integracao.transacional.parametro.nao.encontrado", rotulo);
        }

        return parametro;
    }

    /**
     * Método responsável por formatar a data e hora no padrão do BD.
     * 
     * @param data
     * @return
     */
    public String obterValorDataHoraFormatada(Date data) {
        debug("### Obtendo a data e hora formatada...");
        debug("Parâmetro - data: " + data);

        return ObjectUtil.isNull(data) ? null : formatterDataHora.format(data);
    }

    /**
     * Método responsável por formatar a data e hora no padrão do BD.
     * 
     * @param data
     * @return
     */
    public String obterValorDataHoraFormatadaDb2(Date data) {
        debug("### Obtendo a data e hora formatada...");
        debug("Parâmetro - data: " + data);

        return ObjectUtil.isNull(data) ? null : formatterDataHoraDB2.format(data);
    }

    /**
     * Método responsável por formatar a data no padrão do BD.
     * 
     * @param data
     * @return
     */
    public String obterValorDataFormatada(Date data) {
        debug("### Obtendo a data formatada...");
        debug("Parâmetro - data: " + data);

        return ObjectUtil.isNull(data) ? null : formatterData.format(data);
    }

    /**
     * Método responsável por obter o arredondamento do BigDecimal
     * 
     * @param valor
     * @return
     */
    public final BigDecimal obterValorBigDecimalArredondado(BigDecimal valor) {
        debug("### Obtendo valor BigDecimal arredondado...");
        debug("Parâmetro - valor: " + valor);

        return ObjectUtil.isNull(valor) ? null : valor.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

}