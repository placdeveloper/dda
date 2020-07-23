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
     * Indica se na mensagem recebida, um par�metro com a chave fornecida.
     * 
     * @param mensagem
     * @param chaveRotulo
     * @return Caso exista o par�metro � retorna <code>true</code>, caso contr�trio � retornado <code>false</code>
     */
    protected final Boolean contemParametro(Mensagem mensagem, String chaveRotulo) {
        debug("### Verificando se cont�m par�metro...");
        debug("Par�metro - mensagem: " + mensagem);
        debug("Par�metro - chaveRotulo: " + chaveRotulo);

        Parametro parametro = mensagem.recuperarParametro(chaveRotulo);
        return !ObjectUtil.isNull(parametro);
    }

    /**
     * Obt�m um valor do tipo {@link Integer} de um par�metro.<br>
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
        debug("### Obtendo valor Integer do par�metro...");
        debug("Par�metro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do par�metro: " + parametro.getValor());

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
        debug("### Obtendo valor Long do par�metro...");
        debug("Par�metro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do par�metro: " + parametro.getValor());

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
     * Obt�m um valor do tipo {@link BigInteger} de um par�metro.<br>
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
        debug("### Obtendo valor BigInteger do par�metro...");
        debug("Par�metro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do par�metro: " + parametro.getValor());

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
     * M�todo respons�vel por obter o valor do par�metro como BigDecimal
     * 
     * @param parametro
     * @return
     */
    protected final BigDecimal obterValorBigDecimal(Parametro parametro) {
        debug("### Obtendo valor BigDecimal do par�metro...");
        debug("Par�metro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do par�metro: " + parametro.getValor());

        if (parametro.getValor() instanceof String && parametro.getValor().toString().equals(Constantes.BRANCO)) {
            return null;
        }
        return NumberUtils.createBigDecimal(parametro.getValor().toString());
    }

    /**
     * M�todo respons�vel por obter o valor do par�metro como short
     * 
     * @param parametro
     * @return
     */
    public Short obterValorShort(Parametro parametro) {
        debug("### Obtendo valor Short do par�metro...");
        debug("Par�metro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do par�metro: " + parametro.getValor());

        if (ObjectUtil.isEmpty(parametro.getValor().toString())) {
            return null;
        }

        return Short.parseShort(parametro.getValor().toString());
    }

    /**
     * M�todo respons�vel por obter o valor do par�metro como double.
     * 
     * @param parametro
     * @return
     */
    public Double obterValorDouble(Parametro parametro) {
        debug("### Obtendo valor Double do par�metro...");
        debug("Par�metro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do par�metro: " + parametro.getValor());

        if (ObjectUtil.isEmpty(parametro.getValor().toString())) {
            return null;
        }
        return Double.parseDouble(parametro.getValor().toString());
    }

    /**
     * M�todo respons�vel por obter o valor do par�metro como DateTimeDB.
     * 
     * @param parametro
     * @return
     */
    public DateTimeDB obterValorDateTimeDB(Parametro parametro) {
        debug("### Obtendo valor DateTimeDB do par�metro...");
        debug("Par�metro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do par�metro: " + parametro.getValor());

        if (parametro.getValor() instanceof Date) {
            return new DateTimeDB(((Date) parametro.getValor()).getTime());
        }
        return null;
    }

    /**
     * M�todo respons�vel por obter o valor do par�metro como String.
     * 
     * @param parametro
     * @return
     */
    public String obterValorString(Parametro parametro) {
        debug("### Obtendo valor String do par�metro...");
        debug("Par�metro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do par�metro: " + parametro.getValor());

        return parametro.getValor().toString();
    }

    /**
     * M�todo respons�vel por obter o valor do par�metro como Date.
     * 
     * @param parametro
     * @return
     */
    public Date obterValorDate(Parametro parametro) {
        debug("### Obtendo valor Date do par�metro...");
        debug("Par�metro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        debug("Valor do par�metro: " + parametro.getValor());

        if (parametro.getValor() instanceof Date) {
            return (Date) parametro.getValor();
        }

        return null;
    }

    /**
     * M�todo respons�vel por obter o valor do par�metro como boolean
     * 
     * @param parametro
     * @return
     * @throws IntegracaoTransacionalNegocioException
     */
    protected final Boolean obterValorBoolean(Parametro parametro) throws IntegracaoTransacionalNegocioException {
        debug("### Obtendo valor Boolean do par�metro...");
        debug("Par�metro - parametro: " + parametro);

        if (ObjectUtil.isNull(parametro) || ObjectUtil.isNull(parametro.getValor())) {
            return null;
        }

        Object valor = parametro.getValor();
        debug("Valor do par�metro: " + valor);

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
     * M�todo respons�vel por obter o par�metro pelo r�tulo informado.
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
     * M�todo respons�vel por obter o par�metro pelo r�tulo informado.
     * 
     * @param mensagem
     * @param rotulo
     * @param obrigatorio
     * @return
     * @throws IntegracaoTransacionalNegocioException
     */
    public Parametro obterParametro(Mensagem mensagem, String rotulo, boolean obrigatorio) throws IntegracaoTransacionalNegocioException {
        debug("### Obtendo par�metro...");
        debug("Par�metro - mensagem: " + mensagem);
        debug("Par�metro - rotulo: " + rotulo);

        Parametro parametro = mensagem.recuperarParametro(rotulo);

        debug("Par�metro recuperado: " + parametro);

        // Se for um par�metro obrigat�rio e o objeto for nulo
        if (obrigatorio && ObjectUtil.isNull(parametro)) {
            throw new IntegracaoTransacionalNegocioException("integracao.transacional.parametro.nao.encontrado", rotulo);
        }

        return parametro;
    }

    /**
     * M�todo respons�vel por formatar a data e hora no padr�o do BD.
     * 
     * @param data
     * @return
     */
    public String obterValorDataHoraFormatada(Date data) {
        debug("### Obtendo a data e hora formatada...");
        debug("Par�metro - data: " + data);

        return ObjectUtil.isNull(data) ? null : formatterDataHora.format(data);
    }

    /**
     * M�todo respons�vel por formatar a data e hora no padr�o do BD.
     * 
     * @param data
     * @return
     */
    public String obterValorDataHoraFormatadaDb2(Date data) {
        debug("### Obtendo a data e hora formatada...");
        debug("Par�metro - data: " + data);

        return ObjectUtil.isNull(data) ? null : formatterDataHoraDB2.format(data);
    }

    /**
     * M�todo respons�vel por formatar a data no padr�o do BD.
     * 
     * @param data
     * @return
     */
    public String obterValorDataFormatada(Date data) {
        debug("### Obtendo a data formatada...");
        debug("Par�metro - data: " + data);

        return ObjectUtil.isNull(data) ? null : formatterData.format(data);
    }

    /**
     * M�todo respons�vel por obter o arredondamento do BigDecimal
     * 
     * @param valor
     * @return
     */
    public final BigDecimal obterValorBigDecimalArredondado(BigDecimal valor) {
        debug("### Obtendo valor BigDecimal arredondado...");
        debug("Par�metro - valor: " + valor);

        return ObjectUtil.isNull(valor) ? null : valor.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

}