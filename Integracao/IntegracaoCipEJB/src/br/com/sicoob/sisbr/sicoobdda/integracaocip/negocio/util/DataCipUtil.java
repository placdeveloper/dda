package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.SDDAStringUtil;

/**
 * DataCipUtil
 */
public abstract class DataCipUtil {

    private static final int INDICE_MAX_BASE_CNPJ = 8;

    /**
     * Formato longo utilizado pelo Bacen para datas
     */
    public static final String FORMATO_LONGO_DATA_BACEN = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Formato curto utilizado pelo Bacen para datas
     */
    public static final String FORMATO_CURTO_DATA_BACEN = "yyyy-MM-dd";

    /**
     * Formato utilizado pelo Bacen para horas
     */
    public static final String FORMATO_HORA_BACEN = "HH:mm:ss";

    /**
     * Hora zero.
     */
    public static final String ZERO_HORA_BACEN = "00:00:00";

    /**
     * Método responsável por recuperar a dataAtual formato Bacen
     * 
     * @return Date
     * 
     */
    public static String obterStringDataAtualBacen() {
        return DataUtil.converterDateToString(new Date(), FORMATO_LONGO_DATA_BACEN);
    }

    /**
     * Método responsável por converter a java.util.Date para javax.xml.datatype.XMLGregorianCalendar
     * 
     * @param data
     * @return
     * @throws ComumException XMLGregorianCalendar
     * 
     */
    public static XMLGregorianCalendar dateTimeHoraParaXMLGregorianCalendar(Date data) throws ComumException {
        XMLGregorianCalendar xmlGregCalendar;
        try {
            xmlGregCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(DataUtil.converterDateToString(data, FORMATO_LONGO_DATA_BACEN));
        } catch (DatatypeConfigurationException e) {
            throw new ComumException("Erro ao converter DateTime para XMLGregorianCalendar", e);
        }
        return xmlGregCalendar;
    }

    /*   *//**
     * Método responsável por converter a java.util.Date para javax.xml.datatype.XMLGregorianCalendar
     * 
     * @param data
     * @return
     * @throws ComumException XMLGregorianCalendar
     * 
     */

    public static XMLGregorianCalendar dateTimeParaXMLGregorianCalendar(Date data) throws ComumException {
        XMLGregorianCalendar xmlGregCalendar;
        try {
            xmlGregCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(DataUtil.converterDateToString(data, FORMATO_CURTO_DATA_BACEN));
        } catch (DatatypeConfigurationException e) {
            throw new ComumException("Erro ao converter DateTime para XMLGregorianCalendar", e);
        }
        return xmlGregCalendar;
    }

    /**
     * Método responsável por converter a java.util.Date para javax.xml.datatype.XMLGregorianCalendar com a hora 00:00:00
     * 
     * @param data
     * @return
     * @throws ComumException XMLGregorianCalendar
     * 
     */
    public static XMLGregorianCalendar dateTimeZeroHoraParaXMLGregorianCalendar(Date data) throws ComumException {
        XMLGregorianCalendar xmlGregCalendar;
        try {
            xmlGregCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(DataUtil.converterDateToString(DataUtil.configurarHora(data, ZERO_HORA_BACEN), FORMATO_LONGO_DATA_BACEN));
        } catch (DatatypeConfigurationException e) {
            throw new ComumException("Erro ao converter DateTime para XMLGregorianCalendar", e);
        }
        return xmlGregCalendar;
    }

    /**
     * Método responsável por converter a javax.xml.datatype.XMLGregorianCalendar para java.util.Date
     * 
     * @param calendar
     * @return DateTimeDB
     * 
     */
    public static DateTimeDB xMLGregorianCalendarParaDateTime(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return new DateTimeDB(calendar.toGregorianCalendar().getTimeInMillis());
    }

    /**
     * Método responsável por recupera data atual no formato javax.xml.datatype.XMLGregorianCalendar
     * 
     * @return
     * 
     */

    public static XMLGregorianCalendar getDataAtualXMLGregorianCalendar() throws ComumException {
        return dateTimeParaXMLGregorianCalendar(new Date());
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException XMLGregorianCalendar
     * 
     */
    public static XMLGregorianCalendar getDataHoraAtualXMLGregorianCalendar() throws ComumException {
        return dateTimeHoraParaXMLGregorianCalendar(new Date());
    }

    /**
     * Método responsável por
     * 
     * @param numCPFCNPJ
     * @param tipoPessoa
     * @return String
     * 
     */
    public static String obterCPFCNPJ(Number numCPFCNPJ, String tipoPessoa) {
        if (TipoPessoaEnum.PF.getCodDominioCip().equalsIgnoreCase(tipoPessoa)) {
            return SDDAStringUtil.incluirZerosCPF(numCPFCNPJ);
        } else {
            return SDDAStringUtil.incluirZerosCNPJ(numCPFCNPJ);
        }
    }

    /**
     * Metodo responsavel por recuperar o CNPJBase, ou seja, os primeiros 8 digitos do CNPJ
     * 
     * @param cnpj
     * @return String
     * 
     */
    public static String obterBaseCNPJ(String cnpj) {
        return cnpj.substring(0, INDICE_MAX_BASE_CNPJ);
    }

    /**
     * 
     * @param data
     * @return
     */
    public static String formatadorDataArquivo(Date data) {
        return new SimpleDateFormat("yyyyMMdd").format(data);
    }

}