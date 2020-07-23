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
 * LinhaDigitavelCodigoBarrasUtil � respons�vel obter informa��es referente � linha digit�vel e/ou o c�digo de barras do boleto
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
     * M�todo respons�vel por obter tipo de padr�o leiaute da linha digit�vel ou do c�digo de barras do cobran�a banc�ria.<br>
     * <br>
     * 
     * Caso a carteira seja = 1, 2 , 3 tipo de leiaute � o padr�o legado.<br>
     * 
     * Caso a carteira seja = 6 � o c�digo identificador de negocio = 3 tipo de leiaute � o novo padr�o legado.<br>
     * 
     * Caso a carteira seja = 6 � o c�digo identificador de neg�cio = 4 tipo de leiaute � o novo cobran�a bancaria.<br>
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

        getLogger().info("TIPO PADR�O LEIAUTE COBRAN�A: " + tipoPadraoLeiauteCobrancaEnum.getDescricao());

        return tipoPadraoLeiauteCobrancaEnum;
    }

    /**
     * M�todo respons�vel por verificar se o par�metro informado � diferente de nulo e se corresponde a linha digit�vel ou ao c�digo de barras.
     * 
     * @param linhaDigitavelCodigoBarras
     * @throws ComumNegocioException
     */
    private static void validarLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        if (ObjectUtil.isNull(linhaDigitavelCodigoBarras)) {
            throw new ComumNegocioException("Linha digit�vel/c�digo de barras n�o informado");
        }

        int tamanho = linhaDigitavelCodigoBarras.length();

        if (tamanho != Constantes.TAMANHO_LINHA_DIGITAVEL && tamanho != Constantes.TAMANHO_CODIGO_BARRAS) {
            throw new ComumNegocioException("Linha digit�vel/c�digo de barras com tamanho inv�lido");
        }
    }

    /**
     * M�todo respons�vel por validar se o tipo de padr�o leiaute � nulo.
     * 
     * @param tipoPadraoLeiauteCobrancaEnum
     * @throws ComumNegocioException
     */
    private static void validarTipoPadraoLeiauteCobrancaEnum(TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum) throws ComumNegocioException {
        if (ObjectUtil.isNull(tipoPadraoLeiauteCobrancaEnum)) {
            throw new ComumNegocioException("Tipo padr�o de leiaute n�o encontrado");
        }
    }

    /**
     * M�todo respons�vel por identificar o tipo padr�o leiaute cobran�a pela carteira, aplica-se quando a carteira e diferente de 6.
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
     * M�todo respons�vel por identificar o tipo padr�o leiaute cobran�a pelo identificador de neg�cio quando a carteira � 6.
     * 
     * @param identificadorNegocio
     * @return TipoPadraoLeiauteCobrancaEnum
     */
    private static TipoPadraoLeiauteCobrancaEnum identificarTipoPadraoLeiauteCobrancaIdentificadorNegocio(short identificadorNegocio) {
        return identificadorNegocio == PADRAO_LEGADO_COD_3 ? TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO
                : identificadorNegocio == PADRAO_LEGADO_COD_4 ? TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA : null;
    }

    /**
     * M�todo respons�vel por obter o n�mero do cliente pelo c�digo de barras
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
            getLogger().alerta("NOVO PADR�O - NOVO COBRAN�A - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(numeroCliente)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o n�mero do cliente pelo c�digo de barras");
        }

        return numeroCliente;
    }

    /**
     * M�todo respons�vel por obter a modalidade pelo c�digo de barras.
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
            getLogger().alerta("NOVO PADR�O - NOVO COBRAN�A - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(modalidade)) {
            throw new ComumNegocioException("N�o foi poss�vel obter a modalidade pelo c�digo de barras");
        }

        return modalidade;
    }

    /**
     * M�todo respons�vel por obter o nosso n�mero pelo c�digo de barras
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
            getLogger().alerta("NOVO PADR�O - NOVO COBRAN�A - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(nossoNumero)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o nosso n�mero pelo c�digo de barras");
        }

        return nossoNumero;
    }

    /**
     * M�todo respons�vel por obter o numero cliente pela linha digitavel
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
            getLogger().alerta("NOVO PADR�O - NOVO COBRAN�A - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(numeroCliente)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o n�mero do cliente pela linha digit�vel");
        }

        return numeroCliente;
    }

    /**
     * M�todo respons�vel por obter a modalidade pela linha digit�vel
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
            getLogger().alerta("NOVO PADR�O - NOVO COBRAN�A - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(modalidade)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar a modalidade pela linha digit�vel");
        }

        return modalidade;
    }

    /**
     * M�todo respons�vel por obter o nosso n�mero pela linha digit�vel
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
            getLogger().alerta("NOVO PADR�O - NOVO COBRAN�A - CONSULTE O CONTRATO ");
            return null;
        }

        if (ObjectUtil.isEmpty(nossoNumero)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o nosso n�mero pela linha digit�vel");
        }

        return nossoNumero;
    }

    /**
     * M�todo respons�vel por obter o n�mero do cliente pela linha digitavel ou pelo c�digo de barras
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
     * M�todo respons�vel por obter a Modalidade Produto por linha digitavel ou c�digo de barras
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
     * M�todo respons�vel por obter o nosso n�mero por linha digitavel ou c�digo de barras
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
     * M�todo respons�vel por obter o n�mero do banco por linha digitavel
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterNumeroBancoPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        Short numeroBanco = Short.parseShort(linhaDigitavel.substring(0, 3));

        if (ObjectUtil.isEmpty(numeroBanco)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o n�mero do banco pela linha digit�vel");
        }

        return numeroBanco;
    }

    /**
     * M�todo respons�vel por obter o n�mero do banco por c�digo de barras
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterNumeroBancoPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        Short numeroBanco = Short.parseShort(codigoBarras.substring(0, 3));

        if (ObjectUtil.isNull(numeroBanco)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o n�mero do banco pelo c�digo de barras");
        } else if (ObjectUtil.isEmpty(numeroBanco)) {
            throw new ComumNegocioException("O n�mero do banco n�o pode ser igual a zero");
        }

        return numeroBanco;
    }

    /**
     * M�todo respons�vel por informar se o c�digo de barras � igual a 756.
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
     * M�todo respons�vel por obter o n�mero do banco por linha digit�vel ou por c�digo de barras
     * 
     * @param linhaDigitavelCodigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterNumeroBancoPorLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        Short numeroBanco = Short.parseShort(linhaDigitavelCodigoBarras.substring(0, 3));

        if (ObjectUtil.isEmpty(numeroBanco)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o n�mero do banco pela linha digit�vel/c�digo de barras");
        }

        return numeroBanco;
    }

    /**
     * M�todo respons�vel por obter o fator de vencimento por linha digit�vel
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static Integer obterFatorVencimentoPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        Integer fatorVencimento = Integer.parseInt(linhaDigitavel.substring(33, 37));

        if (ObjectUtil.isNull(fatorVencimento)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o fator de vencimento pela linha digit�vel");
        }

        return fatorVencimento;
    }

    /**
     * M�todo respons�vel por obter o fator de vencimento por c�digo de barras
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static Integer obterFatorVencimentoPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        Integer fatorVencimento = Integer.parseInt(codigoBarras.substring(5, 9));

        if (ObjectUtil.isNull(fatorVencimento)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o fator de vencimento pelo c�digo de barras");
        }

        return fatorVencimento;
    }

    /**
     * M�todo respons�vel por obter o fator vencimento por linha digitavel ou c�digo de barras
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
     * M�todo respons�vel por obter a data de vencimento pela linha digitavel ou o c�digo de barras.
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
     * M�todo respons�vel por obter o carteira por linha digit�vel
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterCarteiraPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        Short carteira = Short.parseShort(linhaDigitavel.substring(4, 5));

        if (ObjectUtil.isEmpty(carteira)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar a carteira pela linha digit�vel");
        }

        return carteira;
    }

    /**
     * M�todo respons�vel por obter a carteira por c�digo de barras
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterCarteiraPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        Short carteira = Short.parseShort(codigoBarras.substring(19, 20));

        if (ObjectUtil.isEmpty(carteira)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar a carteira pelo c�digo de barras");
        }

        return carteira;
    }

    /**
     * M�todo respons�vel por obter o carteira por linha digitavel ou c�digo de barras
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
     * M�todo respons�vel por obter o identificador de neg�cio por linha digit�vel
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static Short obterIdentificadorNegocioPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        Short identificadorNegocio = Short.parseShort(linhaDigitavel.substring(10, 12));

        if (ObjectUtil.isEmpty(identificadorNegocio)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o identificador de neg�cio pela linha digit�vel");
        }

        return identificadorNegocio;
    }

    /**
     * M�todo respons�vel por obter o identificador de neg�cio por c�digo de barras
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException Short
     */
    public static Short obterIdentificadorNegocioPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        Short identificadorNegocio = Short.parseShort(codigoBarras.substring(24, 26));

        if (ObjectUtil.isEmpty(identificadorNegocio)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o identificador de neg�cio pel o c�digo de barras");
        }

        return identificadorNegocio;
    }

    /**
     * M�todo respons�vel por obter o identificador de neg�cio por linha digitavel ou c�digo de barras
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
     * M�todo respons�vel por obter o numero da cooperativa/agencia por linha digit�vel
     * 
     * N�mero da cooperativa e o mesmo n�mero da ag�ncia
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumNegocioException
     */
    public static int obterNumeroCooperativaPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        int numeroCooperativa = Integer.parseInt(linhaDigitavel.substring(5, 9));

        if (ObjectUtil.isEmpty(numeroCooperativa)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o n�mero da cooperativa pela linha digit�vel");
        }

        return numeroCooperativa;
    }

    /**
     * M�todo respons�vel por obter o n�mero da cooperativa/ag�ncia por c�digo de barras
     * 
     * N�mero da cooperativa e o mesmo n�mero da ag�ncia
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static int obterNumeroCooperativaPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        int numeroCooperativa = Integer.parseInt(codigoBarras.substring(20, 24));

        if (ObjectUtil.isEmpty(numeroCooperativa)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o n�mero da cooperativa pelo c�digo de barras");
        }

        return numeroCooperativa;
    }

    /**
     * M�todo respons�vel por obter o n�mero da cooperativa/ag�ncia por linha digitavel ou c�digo de barras
     * 
     * N�mero da cooperativa e o mesmo n�mero da ag�ncia
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
     * M�todo respons�vel por obter o valor do t�tulo por linha digit�vel
     * 
     * @param linhaDigitavel
     * @return double
     * @throws ComumNegocioException
     */
    public static double obterValorTituloPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        double valorTitulo = Double.parseDouble(linhaDigitavel.substring(37, 47)) / 100;

        if (ObjectUtil.isNull(valorTitulo)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o valor pela linha digit�vel");
        }

        return valorTitulo;
    }

    /**
     * M�todo respons�vel por obter o valor do t�tulo por c�digo de barras
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
     * M�todo respons�vel por obter o valor do t�tulo por linha digitavel ou c�digo de barras
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
     * M�todo respons�vel por obter o n�mero do contrato por c�digo de barras
     * 
     * Deve-se utilizar esse m�todo apenas quando o leiaute seja novo padrao - novo cobran�a
     * 
     * @param codigoBarras
     * @return Long
     * @throws ComumNegocioException
     */
    public static Long obterNumeroContratoPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = obterTipoPadraoLeiauteCobranca(codigoBarras);
        Long numeroContrato = null;

        if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO) {
            getLogger().alerta("NUMERO DO CONTRATO N�O EXISTE NESSE LEIAUTE");
            return null;
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO) {
            getLogger().alerta("NUMERO DO CONTRATO N�O EXISTE NESSE LEIAUTE");
            return null;
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA) {
            numeroContrato = Long.parseLong(codigoBarras.substring(26, 35));
        }

        if (ObjectUtil.isEmpty(numeroContrato)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o n�mero do contrato pelo c�digo de barras");
        }

        return numeroContrato;
    }

    /**
     * M�todo respons�vel por obter o n�mero do contrato por linha digitavel
     * 
     * Deve-se utilizar esse m�todo apenas quando o leiaute seja novo padrao - novo cobran�a
     * 
     * @param linhaDigitavel
     * @return numero do CONTRATO
     * @throws ComumNegocioException
     */
    public static Long obterNumeroContratoPorLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        TipoPadraoLeiauteCobrancaEnum tipoPadraoLeiauteCobrancaEnum = obterTipoPadraoLeiauteCobranca(linhaDigitavel);
        Long numeroContrato = null;

        if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.PADRAO_LEGADO) {
            getLogger().alerta("NUMERO DO CONTRATO N�O EXISTE NESSE LEIAUTE");
            return null;
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_COBRANCA_LEGADO) {
            getLogger().alerta("NUMERO DO CONTRATO N�O EXISTE NESSE LEIAUTE");
            return null;
        } else if (tipoPadraoLeiauteCobrancaEnum == TipoPadraoLeiauteCobrancaEnum.NOVO_PADRAO_NOVO_COBRANCA) {
            numeroContrato = Long.parseLong(linhaDigitavel.substring(12, 20));
        }

        if (ObjectUtil.isEmpty(numeroContrato)) {
            throw new ComumNegocioException("N�o foi poss�vel recuperar o n�mero do contrato pela linha digit�vel");
        }

        return numeroContrato;

    }

    /**
     * M�todo respons�vel por obter o numero do contrato pela linha digitavel ou c�digo de barras
     * 
     * Deve-se utilizar este m�todo apenas quando o leiaute seja novo padrao - novo cobran�a
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
     * M�todo respons�vel por obter o c�digo de barras pela linha digit�vel.
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
     * M�todo respons�vel por obter a linha digit�vel pelo c�digo de barras.
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

        // Adicionando os d�gitos
        linhaDigitavel.insert(9, obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(0, 9)));
        linhaDigitavel.insert(20, obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(10, 20)));
        linhaDigitavel.insert(31, obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(21, 31)));

        return linhaDigitavel.toString();
    }

    /**
     * M�todo respons�vel por calcular o digito verificador
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
     * M�todo respons�vel por
     * 
     * @param linhaDigitavelCodigoBarras
     * @throws ComumNegocioException
     */
    public static void validarDVLinhaDigitavelCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        getLogger().debug("### Validando DV da linha digit�vel/c�digo de barras...");
        getLogger().debug("Par�metro - linhaDigitavelCodigoBarras: " + linhaDigitavelCodigoBarras);

        validarLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);

        int tamanho = linhaDigitavelCodigoBarras.length();

        if (tamanho == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            validarDVLinhaDigitavel(linhaDigitavelCodigoBarras);
        } else {
            validarDVCodigoBarras(linhaDigitavelCodigoBarras);
        }
    }

    /**
     * M�todo respons�vel por
     * 
     * @param linhaDigitavelCodigoBarras
     * @throws ComumNegocioException
     */
    private static void validarDVLinhaDigitavel(String linhaDigitavel) throws ComumNegocioException {
        int dv1 = Integer.parseInt(linhaDigitavel.substring(9, 10));
        int dv1Temp = obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(0, 9));

        if (dv1 != dv1Temp) {
            throw new ComumNegocioException("O DV 1 da linha digit�vel � inv�lido.");
        }

        int dv2 = Integer.parseInt(linhaDigitavel.substring(20, 21));
        int dv2Temp = obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(10, 20));

        if (dv2 != dv2Temp) {
            throw new ComumNegocioException("O DV 2 da linha digit�vel � inv�lido.");
        }

        int dv3 = Integer.parseInt(linhaDigitavel.substring(31, 32));
        int dv3Temp = obterDigitoVerificadorLinhaDigitavel(linhaDigitavel.substring(21, 31));

        if (dv3 != dv3Temp) {
            throw new ComumNegocioException("O DV 3 da linha digit�vel � inv�lido.");
        }

        int dv4 = Integer.parseInt(linhaDigitavel.substring(32, 33));

        // Obt�m o c�digo de barras para criar o d�gito verificador e verificar se o informado na linha digit�vel est� correto
        int dv4Temp = calcularDigitoVerificadorCodigoBarras(obterCodigoBarrasPorLinhaDigitavel(linhaDigitavel));

        if (dv4 != dv4Temp) {
            throw new ComumNegocioException("O DV 4 da linha digit�vel � inv�lido.");
        }
    }

    private static void validarDVCodigoBarras(String codigoBarras) throws ComumNegocioException {
        int dv = Integer.parseInt(codigoBarras.substring(4, 5));

        int dvTemp = calcularDigitoVerificadorCodigoBarras(codigoBarras);

        if (dv != dvTemp) {
            getLogger().debug("O dv do CB �: " + dv + " e deveria ser: " + dvTemp);
            throw new ComumNegocioException("O DV do c�digo de barras � inv�lido.");
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
     * M�todo respons�vel por obter o log
     * 
     * @return Object
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(LinhaDigitavelCodigoBarrasUtil.class);
    }

    /**
     * M�todo respons�vel por obter o c�digo da moeda pelo c�digo de barras.
     * 
     * @param codigoBarras
     * @return
     * @throws ComumNegocioException
     */
    public static int obterCodigoMoedaPorCodigoBarras(String codigoBarras) throws ComumNegocioException {
        getLogger().debug("### Obtendo o c�digo da moeda pelo c�digo de barras...");
        getLogger().debug("Par�metro - codigoBarras: " + codigoBarras);

        validarLinhaDigitavelCodigoBarras(codigoBarras);

        return Integer.parseInt(codigoBarras.substring(3, 4));
    }

    /**
     * M�todo respons�vel por Obter o campo Livre
     * 
     * @param codigoBarras
     * @return String
     */
    public static String obterCampoLivre(String codigoBarras) {
        return codigoBarras.substring(19, 44);
    }
}
