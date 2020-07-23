package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoPadraoLeiauteCobrancaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * LinhaDigitavelCodigoBarrasUtil é responsável obter informações referente à linha digitável e/ou o código de barras do boleto
 * 
 * @author wesley.costa
 */
public final class LinhaDigitavelCodigoBarrasUtil {

    private LinhaDigitavelCodigoBarrasUtil() {
    }

    private static final int PADRAO_NOVO_LEGADO_COD_6 = 6;
    private static final short PADRAO_LEGADO_COD_1 = 1;
    private static final short PADRAO_LEGADO_COD_2 = 2;
    private static final short PADRAO_LEGADO_COD_3 = 3;
    private static final short PADRAO_LEGADO_COD_4 = 4;

    private static final int[] VALORES_LINHA_DIGITAVEL = new int[] { 2, 1, 2, 1, 2, 1, 2, 1, 2, 1 };
    private static final int NUMERO_MODULO_10 = 10;

    private static final int[] VALORES_CODIGO_BARRAS = new int[] { 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6,
            7, 8, 0, 9, 2, 3, 4 };
    private static final int NUMERO_RESTO_DESPRESADO_0 = 0;
    private static final int NUMERO_RESTO_DESPRESADO_1 = 1;
    private static final int NUMERO_RESTO_DESPRESADO_10 = 10;
    private static final int NUMERO_MODULO_11 = 11;

    /**
     * Método responsável por obter tipo de padrão leiaute da linha digitável ou do código de barras do cobrança bancária.<br>
     * <br>
     * 
     * Caso a carteira seja = 1, 2 , 3 tipo de leiaute é o padrão legado.<br>
     * 
     * Caso a carteira seja = 6 é o código identificador de negocio = 3 tipo de leiaute é o novo padrão legado.<br>
     * 
     * Caso a carteira seja = 6 é o código identificador de negócio = 4 tipo de leiaute é o novo cobrança bancaria.<br>
     * 
     * @param linhaDigitavelCodigoBarras
     * @return TipoPadraoLeiauteCobrancaEnum
     * @throws ComumNegocioException
     */
    public static final TipoPadraoLeiauteCobrancaEnum obterTipoPadraoLeiauteCobranca(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = identificarTipoPadraoLeiauteCobrancaCarteira(
                obterCarteiraPorLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras), obterIdentificadorNegocioPorLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras));

        validarTipoPadraoLeiauteCobrancaEnum(tipoPadraoLeiauteCobrancaEnum);

        getLogger().info("TIPO PADRÃO LEIAUTE COBRANÇA: " + tipoPadraoLeiauteCobrancaEnum.getDescricao());

        return tipoPadraoLeiauteCobrancaEnum;
    }

    /**
     * Método responsável por verificar se o parâmetro informado é diferente de nulo e se corresponde a linha digitável ou ao código de barras.
     * 
     * @param linhaDigitavelCodigoBarras
     * @throws ComumNegocioException
     */
    private static void validarLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        if (ObjectUtil.isNull(linhaDigitavelCodigoBarras)) {
            throw new ComumNegocioException("Linha digitável/código de barras não informado");
        }

        int tamanho = linhaDigitavelCodigoBarras.length();

        if (tamanho != Constantes.TAMANHO_LINHA_DIGITAVEL && tamanho != Constantes.TAMANHO_CODIGO_BARRAS) {
            throw new ComumNegocioException("Linha digitável/código de barras com tamanho inválido");
        }
    }

    /**
     * Método responsável por validar se o tipo de padrão leiaute é nulo.
     * 
     * @param tipoPadraoLeiauteCobrancaEnum
     * @throws ComumNegocioException
     */
    private static void validarTipoPadraoLeiauteCobrancaEnum(TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum) throws ComumNegocioException {
        if (ObjectUtil.isNull(tipoPadraoLeiauteCobrancaEnum)) {
            throw new ComumNegocioException("Tipo padrão de leiaute não encontrado");
        }
    }

    /**
     * Método responsável por identificar o tipo padrão leiaute cobrança pela carteira, aplica-se quando a carteira e diferente de 6.
     * 
     * @param carteira
     * @param identificadorNegocio
     * @return TipoPadraoLeiauteCobrancaEnum
     * @throws ApresentacaoBoletoException
     */
    private static TipoPadraoLeiauteCobrancaEnum identificarTipoPadraoLeiauteCobrancaCarteira(short carteira, short identificadorNegocio) {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = null;

        if (carteira == PADRAO_LEGADO_COD_1 || carteira == PADRAO_LEGADO_COD_2 || carteira == PADRAO_LEGADO_COD_3) {
            tipoPadraoLeiauteCobrancaEnum = TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO;
        } else if (carteira == PADRAO_NOVO_LEGADO_COD_6) {
            tipoPadraoLeiauteCobrancaEnum = identificarTipoPadraoLeiauteCobrancaIdentificadorNegocio(identificadorNegocio);
        }

        return tipoPadraoLeiauteCobrancaEnum;
    }

    /**
     * Método responsável por identificar o tipo padrão leiaute cobrança pelo identificador de negócio quando a carteira é 6.
     * 
     * @param identificadorNegocio
     * @return TipoPadraoLeiauteCobrancaEnum
     */
    private static TipoPadraoLeiauteCobrancaEnum identificarTipoPadraoLeiauteCobrancaIdentificadorNegocio(short identificadorNegocio) {
        return identificadorNegocio == PADRAO_LEGADO_COD_3 ? TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO
                : identificadorNegocio == PADRAO_LEGADO_COD_4 ? TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA : null;
    }

    /**
     * Método responsável por obter o número do cliente pelo código de barras
     * 
     * @param codigoBarras
     * @return Long
     * @throws ComumNegocioException
     */
    public static Long obterNumeroClientePorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = obterTipoPadraoLeiauteCobranca(codigoBarras);
        Long numeroCliente = null;

        if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO) {
            numeroCliente = Long.parseLong(codigoBarras.substring(26, 33));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO) {
            numeroCliente = Long.parseLong(codigoBarras.substring(26, 34));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA) {
            getLogger().alerta("NOVO PADRÃO - NOVO COBRANÇA - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(numeroCliente)) {
            throw new ComumNegocioException("Não foi possível recuperar o número do cliente pelo código de barras");
        }

        return numeroCliente;
    }

    /**
     * Método responsável por obter a modalidade pelo código de barras.
     * 
     * @param codigoBarras
     * @return Short
     * @throws ComumNegocioException
     */
    public static Short obterModalidadePorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = obterTipoPadraoLeiauteCobranca(codigoBarras);
        Short modalidade = null;

        if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO) {
            modalidade = Short.parseShort(codigoBarras.substring(24, 26));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO) {
            modalidade = Short.parseShort(codigoBarras.substring(42, 44));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA) {
            getLogger().alerta("NOVO PADRÃO - NOVO COBRANÇA - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(modalidade)) {
            throw new ComumNegocioException("Não foi possível obter a modalidade pelo código de barras");
        }

        return modalidade;
    }

    /**
     * Método responsável por obter o nosso número pelo código de barras
     * 
     * @param codigoBarras
     * @return Long
     * @throws ComumNegocioException
     */
    public static Long obterNossoNumeroPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = obterTipoPadraoLeiauteCobranca(codigoBarras);
        Long nossoNumero = null;

        if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO) {
            nossoNumero = Long.parseLong(codigoBarras.substring(33, 41));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO) {
            nossoNumero = Long.parseLong(codigoBarras.substring(34, 42));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA) {
            getLogger().alerta("NOVO PADRÃO - NOVO COBRANÇA - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(nossoNumero)) {
            throw new ComumNegocioException("Não foi possível recuperar o nosso número pelo código de barras");
        }

        return nossoNumero;
    }

    /**
     * Método responsável por obter o numero cliente pela linha digitavel
     * 
     * @param linhaDigitavel
     * @return numero do cliente
     * @throws ComumNegocioException
     */
    public static Long obterNumeroClientePorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = obterTipoPadraoLeiauteCobranca(linhaDigitavel);
        Long numeroCliente = null;

        if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO) {
            numeroCliente = Long.parseLong(linhaDigitavel.substring(12, 19));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO) {
            numeroCliente = Long.parseLong(linhaDigitavel.substring(12, 20));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA) {
            getLogger().alerta("NOVO PADRÃO - NOVO COBRANÇA - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(numeroCliente)) {
            throw new ComumNegocioException("Não foi possível recuperar o número do cliente pela linha digitável");
        }

        return numeroCliente;
    }

    /**
     * Método responsável por obter a modalidade pela linha digitável
     * 
     * @param linhaDigitavel
     * @return Short
     * @throws ComumNegocioException
     */
    public static Short obterModalidadePorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = obterTipoPadraoLeiauteCobranca(linhaDigitavel);
        Short modalidade = null;

        if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO) {
            modalidade = Short.parseShort(linhaDigitavel.substring(10, 12));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO) {
            modalidade = Short.parseShort(linhaDigitavel.substring(29, 31));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA) {
            getLogger().alerta("NOVO PADRÃO - NOVO COBRANÇA - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(modalidade)) {
            throw new ComumNegocioException("Não foi possível recuperar a modalidade pela linha digitável");
        }

        return modalidade;
    }

    /**
     * Método responsável por obter o nosso número pela linha digitável
     * 
     * @param linhaDigitavel
     * @return Long
     * @throws ComumNegocioException
     */
    public static Long obterNossoNumeroPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = obterTipoPadraoLeiauteCobranca(linhaDigitavel);
        Long nossoNumero = null;

        if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO) {
            nossoNumero = Long.parseLong(linhaDigitavel.substring(21, 28));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO) {
            nossoNumero = Long.parseLong(linhaDigitavel.substring(21, 29));
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA) {
            getLogger().alerta("NOVO PADRÃO - NOVO COBRANÇA - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(nossoNumero)) {
            throw new ComumNegocioException("Não foi possível recuperar o nosso número pela linha digitável");
        }

        return nossoNumero;
    }

    /**
     * Método responsável por obter o número do cliente pela linha digitavel ou pelo código de barras
     * 
     * @param linhaDigitavel
     * @return numero do cliente
     * @throws ComumNegocioException
     */
    public static Long obterNumeroClientePorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            return obterNumeroClientePorLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            return obterNumeroClientePorCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * Método responsável por obter a Modalidade Produto por linha digitavel ou código de barras
     * 
     * @param linhaDigitavel
     * @return Short
     * @throws ComumNegocioException
     */
    public static Short obterModalidadePorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            return obterModalidadePorLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            return obterModalidadePorCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * Método responsável por obter o nosso número por linha digitavel ou código de barras
     * 
     * @param linhaDigitavel
     * @return Long
     * @throws ComumNegocioException
     */
    public static Long obterNossoNumeroPorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            return obterNossoNumeroPorLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            return obterNossoNumeroPorCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * Método responsável por obter o número do banco por linha digitavel
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterNumeroBancoPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        Short numeroBanco = Short.parseShort(linhaDigitavel.substring(0, 3));

        if (ObjectUtil.isEmpty(numeroBanco)) {
            throw new ComumNegocioException("Não foi possível recuperar o número do banco pela linha digitável");
        }

        return numeroBanco;
    }

    /**
     * Método responsável por obter o número do banco por código de barras
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterNumeroBancoPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        Short numeroBanco = Short.parseShort(codigoBarras.substring(0, 3));

        if (ObjectUtil.isNull(numeroBanco)) {
            throw new ComumNegocioException("Não foi possível recuperar o número do banco pelo código de barras");
        } else if (ObjectUtil.isEmpty(numeroBanco)) {
            throw new ComumNegocioException("O número do banco não pode ser igual a zero");
        }

        return numeroBanco;
    }

    /**
     * Método responsável por informar se o código de barras é igual a 756.
     * 
     * @param numCodigoBarras
     * @return
     * @throws ComumNegocioException Boolean
     * 
     */
    public static Boolean isCodigoBarrasSicoob(String numCodigoBarras) throws ComumNegocioException {
        return obterNumeroBancoPorCodigoBarras(numCodigoBarras) == Constantes.NUM_BANCOOB;
    }

    /**
     * Método responsável por obter o número do banco por linha digitável ou por código de barras
     * 
     * @param linhaDigitavelCodigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterNumeroBancoPorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        Short numeroBanco = Short.parseShort(linhaDigitavelCodigoBarras.substring(0, 3));

        if (ObjectUtil.isEmpty(numeroBanco)) {
            throw new ComumNegocioException("Não foi possível recuperar o número do banco pela linha digitável/código de barras");
        }

        return numeroBanco;
    }

    /**
     * Método responsável por obter o fator de vencimento por linha digitável
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static Integer obterFatorVencimentoPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        Integer fatorVencimento = Integer.parseInt(linhaDigitavel.substring(33, 37));

        if (ObjectUtil.isNull(fatorVencimento)) {
            throw new ComumNegocioException("Não foi possível recuperar o fator de vencimento pela linha digitável");
        }

        return fatorVencimento;
    }

    /**
     * Método responsável por obter o fator de vencimento por código de barras
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static Integer obterFatorVencimentoPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        Integer fatorVencimento = Integer.parseInt(codigoBarras.substring(5, 9));

        if (ObjectUtil.isNull(fatorVencimento)) {
            throw new ComumNegocioException("Não foi possível recuperar o fator de vencimento pelo código de barras");
        }

        return fatorVencimento;
    }

    /**
     * Método responsável por obter o fator vencimento por linha digitavel ou código de barras
     * 
     * @param linhaDigitavel
     * @return Integer
     * @throws ComumNegocioException
     */
    public static Integer obterFatorVencimentoPorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            return obterFatorVencimentoPorLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            return obterFatorVencimentoPorCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * Método responsável por obter a data de vencimento pela linha digitavel ou o código de barras.
     * 
     * @param linhaDigitavelCodigoBarras
     * @return Date
     * @throws ComumNegocioException
     */
    public static Date obterDataVencimentoPorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        Integer fatorVencimento = null;

        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            fatorVencimento = obterFatorVencimentoPorLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            fatorVencimento = obterFatorVencimentoPorCodigoBarras(linhaDigitavelCodigoBarras);
        }

        if (fatorVencimento == 0) {
            return null;
        }

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(Constantes.DATA_BASE_CALCULAR_ANTIGO_FATOR_VENCIMENTO);
        calendar.add(Calendar.DATE, fatorVencimento);

        getLogger().debug("Data de vencimento: " + calendar.getTime());

        return calendar.getTime();
    }

    /**
     * Método responsável por obter o carteira por linha digitável
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterCarteiraPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        Short carteira = Short.parseShort(linhaDigitavel.substring(4, 5));

        if (ObjectUtil.isEmpty(carteira)) {
            throw new ComumNegocioException("Não foi possível recuperar a carteira pela linha digitável");
        }

        return carteira;
    }

    /**
     * Método responsável por obter a carteira por código de barras
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterCarteiraPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        Short carteira = Short.parseShort(codigoBarras.substring(19, 20));

        if (ObjectUtil.isEmpty(carteira)) {
            throw new ComumNegocioException("Não foi possível recuperar a carteira pelo código de barras");
        }

        return carteira;
    }

    /**
     * Método responsável por obter o carteira por linha digitavel ou código de barras
     * 
     * @param linhaDigitavel
     * @return Short
     * @throws ComumNegocioException
     */
    public static Short obterCarteiraPorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            return obterCarteiraPorLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            return obterCarteiraPorCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * Método responsável por obter o identificador de negócio por linha digitável
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterIdentificadorNegocioPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        Short identificadorNegocio = Short.parseShort(linhaDigitavel.substring(10, 12));

        if (ObjectUtil.isEmpty(identificadorNegocio)) {
            throw new ComumNegocioException("Não foi possível recuperar o identificador de negócio pela linha digitável");
        }

        return identificadorNegocio;
    }

    /**
     * Método responsável por obter o identificador de negócio por código de barras
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException Short
     */
    public static Short obterIdentificadorNegocioPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        Short identificadorNegocio = Short.parseShort(codigoBarras.substring(24, 26));

        if (ObjectUtil.isEmpty(identificadorNegocio)) {
            throw new ComumNegocioException("Não foi possível recuperar o identificador de negócio pel o código de barras");
        }

        return identificadorNegocio;
    }

    /**
     * Método responsável por obter o identificador de negócio por linha digitavel ou código de barras
     * 
     * @param linhaDigitavel
     * @return Short
     * @throws ComumNegocioException
     */
    public static Short obterIdentificadorNegocioPorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            return obterIdentificadorNegocioPorLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            return obterIdentificadorNegocioPorCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * Método responsável por obter o numero da cooperativa/agencia por linha digitável
     * 
     * Número da cooperativa e o mesmo número da agência
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static int obterNumeroCooperativaPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        int numeroCooperativa = Integer.parseInt(linhaDigitavel.substring(5, 9));

        if (ObjectUtil.isEmpty(numeroCooperativa)) {
            throw new ComumNegocioException("Não foi possível recuperar o número da cooperativa pela linha digitável");
        }

        return numeroCooperativa;
    }

    /**
     * Método responsável por obter o número da cooperativa/agência por código de barras
     * 
     * Número da cooperativa e o mesmo número da agência
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static int obterNumeroCooperativaPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        int numeroCooperativa = Integer.parseInt(codigoBarras.substring(20, 24));

        if (ObjectUtil.isEmpty(numeroCooperativa)) {
            throw new ComumNegocioException("Não foi possível recuperar o número da cooperativa pelo código de barras");
        }

        return numeroCooperativa;
    }

    /**
     * Método responsável por obter o número da cooperativa/agência por linha digitavel ou código de barras
     * 
     * Número da cooperativa e o mesmo número da agência
     * 
     * @param linhaDigitavel
     * @return Integer
     * @throws ComumNegocioException
     */
    public static int obterNumeroCooperativaPorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            return obterNumeroCooperativaPorLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            return obterNumeroCooperativaPorCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * Método responsável por obter o valor do título por linha digitável
     * 
     * @param linhaDigitavel
     * @return double
     * @throws ComumNegocioException
     */
    public static double obterValorTituloPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        double valorTitulo = Double.parseDouble(linhaDigitavel.substring(37, 47)) / 100;

        if (ObjectUtil.isNull(valorTitulo)) {
            throw new ComumNegocioException("Não foi possível recuperar o valor pela linha digitável");
        }

        return valorTitulo;
    }

    /**
     * Método responsável por obter o valor do título por código de barras
     * 
     * @param codigoBarras
     * @return double
     * @throws ComumNegocioException
     */
    public static double obterValorTituloPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        double valorTitulo = Double.parseDouble(codigoBarras.substring(9, 19)) / 100;

        return valorTitulo;
    }

    /**
     * Método responsável por obter o valor do título por linha digitavel ou código de barras
     * 
     * @param linhaDigitavel
     * @return String
     * @throws ComumNegocioException
     */
    public static double obterValorTituloPorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            return obterValorTituloPorLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            return obterValorTituloPorCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * Método responsável por obter o número do contrato por código de barras
     * 
     * Deve-se utilizar esse método apenas quando o leiaute seja novo padrao - novo cobrança
     * 
     * @param codigoBarras
     * @return Long
     * @throws ComumNegocioException
     */
    public static Long obterNumeroContratoPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = obterTipoPadraoLeiauteCobranca(codigoBarras);
        Long numeroContrato = null;

        if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO) {
            getLogger().alerta("NUMERO DO CONTRATO NÃO EXISTE NESSE LEIAUTE");
            return null;
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO) {
            getLogger().alerta("NUMERO DO CONTRATO NÃO EXISTE NESSE LEIAUTE");
            return null;
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA) {
            numeroContrato = Long.parseLong(codigoBarras.substring(26, 35));
        }

        if (ObjectUtil.isEmpty(numeroContrato)) {
            throw new ComumNegocioException("Não foi possível recuperar o número do contrato pelo código de barras");
        }

        return numeroContrato;
    }

    /**
     * Método responsável por obter o número do contrato por linha digitavel
     * 
     * Deve-se utilizar esse método apenas quando o leiaute seja novo padrao - novo cobrança
     * 
     * @param linhaDigitavel
     * @return numero do CONTRATO
     * @throws ComumNegocioException
     */
    public static Long obterNumeroContratoPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = obterTipoPadraoLeiauteCobranca(linhaDigitavel);
        Long numeroContrato = null;

        if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO) {
            getLogger().alerta("NUMERO DO CONTRATO NÃO EXISTE NESSE LEIAUTE");
            return null;
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO) {
            getLogger().alerta("NUMERO DO CONTRATO NÃO EXISTE NESSE LEIAUTE");
            return null;
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA) {
            numeroContrato = Long.parseLong(linhaDigitavel.substring(12, 20));
        }

        if (ObjectUtil.isEmpty(numeroContrato)) {
            throw new ComumNegocioException("Não foi possível recuperar o número do contrato pela linha digitável");
        }

        return numeroContrato;

    }

    /**
     * Método responsável por obter o numero do contrato pela linha digitavel ou código de barras
     * 
     * Deve-se utilizar este método apenas quando o leiaute seja novo padrao - novo cobrança
     * 
     * @param linhaDigitavel
     * @return numero do contrato
     * @throws ComumNegocioException
     */
    public static Long obterNumeroContratoPorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            return obterNumeroContratoPorLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            return obterNumeroContratoPorCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * Método responsável por obter o código de barras pela linha digitável.
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static String obterCodigoBarrasPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(linhaDigitavel);

        StringBuffer codigoBarras = new StringBuffer();

        codigoBarras.append(linhaDigitavel.substring(0, 4));
        codigoBarras.append(linhaDigitavel.substring(32, 47));
        codigoBarras.append(linhaDigitavel.substring(4, 9));
        codigoBarras.append(linhaDigitavel.substring(10, 20));
        codigoBarras.append(linhaDigitavel.substring(21, 31));

        return codigoBarras.toString();
    }

    /**
     * Método responsável por obter a linha digitável pelo código de barras.
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static String obterLinhaDigitavelPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        validarLinhaDigitavelCodigoBarras(codigoBarras);

        StringBuffer linhaDigitavel = new StringBuffer();

        linhaDigitavel.append(codigoBarras.substring(0, 4));
        linhaDigitavel.append(codigoBarras.substring(19, 44));
        linhaDigitavel.append(codigoBarras.substring(4, 19));

        // Adicionando os dígitos
        linhaDigitavel.insert(9, obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(0, 9)));
        linhaDigitavel.insert(20, obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(10, 20)));
        linhaDigitavel.insert(31, obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(21, 31)));

        return linhaDigitavel.toString();
    }

    /**
     * Método responsável por calcular o digito verificador
     * 
     * @param campo
     * @return String
     */
    private static int obterDigitoVerificadorLinhaDigitavel(String campo) {
        int retorno = 0;
        int soma = 0;
        int posic = 0;

        for (int i = campo.length() - 1; i >= 0; i--) {
            Integer valor = Integer.valueOf(campo.substring(i, i + 1)).intValue() * VALORES_LINHA_DIGITAVEL[posic];
            String valorString = valor.toString();

            if (valorString.length() > 1) {
                for (int j = 0; j < valorString.length(); j++) {
                    soma += Integer.valueOf(valorString.substring(j, j + 1)).intValue();
                }
            } else {
                soma += valor;
            }
            posic++;
        }

        int resto = soma % NUMERO_MODULO_10;
        int resultado = NUMERO_MODULO_10 - resto;

        if (resto != 0) {
            retorno = resultado;
        }

        return retorno;
    }

    /**
     * Método responsável por
     * 
     * @param linhaDigitavelCodigoBarras
     * @throws ComumNegocioException
     */
    public static void validarDVLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        getLogger().debug("### Validando DV da linha digitável/código de barras...");
        getLogger().debug("Parâmetro - linhaDigitavelCodigoBarras: " + linhaDigitavelCodigoBarras);

        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        int tamanho = linhaDigitavelCodigoBarras.length();

        if (tamanho == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            validarDVLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            validarDVCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * Método responsável por
     * 
     * @param linhaDigitavelCodigoBarras
     * @throws ComumNegocioException
     */
    private static void validarDVLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        int dv1 = Integer.parseInt(linhaDigitavel.substring(9, 10));
        int dv1Temp = obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(0, 9));

        if (dv1 != dv1Temp) {
            throw new ComumNegocioException("O DV 1 da linha digitável é inválido.");
        }

        int dv2 = Integer.parseInt(linhaDigitavel.substring(20, 21));
        int dv2Temp = obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(10, 20));

        if (dv2 != dv2Temp) {
            throw new ComumNegocioException("O DV 2 da linha digitável é inválido.");
        }

        int dv3 = Integer.parseInt(linhaDigitavel.substring(31, 32));
        int dv3Temp = obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(21, 31));

        if (dv3 != dv3Temp) {
            throw new ComumNegocioException("O DV 3 da linha digitável é inválido.");
        }

        int dv4 = Integer.parseInt(linhaDigitavel.substring(32, 33));

        // Obtém o código de barras para criar o dígito verificador e verificar se o informado na linha digitável está correto
        int dv4Temp = calcularDigitoVerificadorCodigoBarras(obterCodigoBarrasPorLinhaDigitavel(linhaDigitavel));

        if (dv4 != dv4Temp) {
            throw new ComumNegocioException("O DV 4 da linha digitável é inválido.");
        }
    }

    private static void validarDVCodigoBarras(String codigoBarras) throws ComumNegocioException {
        int dv = Integer.parseInt(codigoBarras.substring(4, 5));

        int dvTemp = calcularDigitoVerificadorCodigoBarras(codigoBarras);

        if (dv != dvTemp) {
            getLogger().debug("O dv do CB é: " + dv + " e deveria ser: " + dvTemp);
            throw new ComumNegocioException("O DV do código de barras é inválido.");
        }
    }

    private static int calcularDigitoVerificadorCodigoBarras(String cb) {
        int retorno = 1;

        int soma = 0;
        int posic = 0;

        for (int i = cb.length() - 1; i >= 0; i--) {
            soma += Integer.valueOf(cb.substring(i, i + 1)).intValue() * VALORES_CODIGO_BARRAS[posic];
            posic++;
        }

        int resto = soma % NUMERO_MODULO_11;
        int resultado = NUMERO_MODULO_11 - resto;

        if (resto != NUMERO_RESTO_DESPRESADO_0 && resto != NUMERO_RESTO_DESPRESADO_1 && resto != NUMERO_RESTO_DESPRESADO_10) {
            retorno = resultado;
        }

        return retorno;
    }

    /**
     * Método responsável por obter o log
     * 
     * @return Object
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(LinhaDigitavelCodigoBarrasUtil.class);
    }

    /**
     * Método responsável por obter o código da moeda pelo código de barras.
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static int obterCodigoMoedaPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        getLogger().debug("### Obtendo o código da moeda pelo código de barras...");
        getLogger().debug("Parâmetro - codigoBarras: " + codigoBarras);

        validarLinhaDigitavelCodigoBarras(codigoBarras);

        return Integer.parseInt(codigoBarras.substring(3, 4));
    }

    /**
     * Método responsável por Obter o campo Livre
     * 
     * @param codigoBarras
     * @return String
     */
    public static String obterCampoLivre(String codigoBarras) {
        return codigoBarras.substring(19, 44);
    }
}
