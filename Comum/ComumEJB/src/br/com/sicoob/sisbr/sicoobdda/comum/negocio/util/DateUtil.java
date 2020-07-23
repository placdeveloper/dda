/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.util
 * Arquivo:         DateUtil.java
 * Data Criação:    Nov 12, 2013
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * 
 * DateUtil
 * 
 * @author Lucas.Moura
 */
public final class DateUtil {

    /**
     * 
     */
    private DateUtil() {
        // not called
    }


    private static final int ULTIMO_MILISSEGUNDO_SEGUNDO = 999;
    private static final int ULTIMO_SEGUNDO_MINUTO = 59;
    private static final int ULTIMO_MINUTO_HORA = 59;
    private static final int ULTIMA_HORA_DIA = 23;

    /**
     * Utilizar o método
     * 
     * @param data1
     * @param data2
     * @return long
     */
    public static int calcularDiferencaDias(Date data1, Date data2) {
        return Days.daysBetween(new DateTime(data2), new DateTime(data1)).getDays();
    }

    /**
     * Método responsável por calcular a diferença de dias desconsiderando o timezone, para não ocorrer erro em períodos de entrada/saída do horário de verão.
     *
     * @param dataInicial
     * @param dataFinal
     */
    public static int calcularDiferencaDiasSemTimezone(Date dataFinal, Date dataInicial) {
        LocalDate dtFinal = dataFinal.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        LocalDate dtInicial = dataInicial.toInstant().atZone(ZoneOffset.UTC).toLocalDate();

        return (int) ChronoUnit.DAYS.between(dtInicial.atStartOfDay(), dtFinal.atStartOfDay());
    }

    /**
     * Método responsável por somaDias
     * 
     * @param data
     * @param quantidadeDeDias
     * @return Date
     * 
     */
    public static Date somarDias(Date data, int quantidadeDeDias) {
        return somarCampo(Calendar.DAY_OF_MONTH, data, quantidadeDeDias);
    }

    /**
     * Método responsável adicionar quantidade de dias na data.
     * 
     * @param data
     * @param quantidadeDeDias
     * @return
     */
    public static Date somarDias(Date data, short quantidadeDeDias) {
        return somarCampo(Calendar.DAY_OF_MONTH, data, quantidadeDeDias);
    }

    /**
     * Método responsável por somaCampo
     * 
     * @param campo
     * @param data
     * @param quantidade
     * @return Date
     * 
     */
    public static Date somarCampo(int campo, Date data, int quantidade) {
        Calendar calendar = getCalendarDaData(data);
        calendar.add(campo, quantidade);
        return calendar.getTime();
    }

    /**
     * Método responsável por getCalendarDaData
     * 
     * @param data
     * @return Calendar
     * 
     */
    protected static Calendar getCalendarDaData(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar;
    }

    /**
     * 
     * Método responsável por verifricar se dataA é maior que a dataB
     * 
     * @param dateA
     * @param dateB
     * @return Boolean
     * 
     */
    public static Boolean maiorQue(Date dataA, Date dataB) {
        Boolean retorno = Boolean.FALSE;

        if (datasNaoNulas(dataA, dataB)) {
            retorno = dataA.after(dataB);
        }
        return retorno;
    }

    /**
     * 
     * Método responsável por verifricar se dataA é menor que a dataB
     * 
     * @param dataA
     * @param dataB
     * @return Boolean
     * 
     */
    public static Boolean menorQue(Date dataA, Date dataB) {
        Boolean retorno = Boolean.FALSE;

        if (datasNaoNulas(dataA, dataB)) {
            retorno = dataA.before(dataB);
        }
        return retorno;
    }

    /**
     * 
     * Método responsável por verifricar se dataA é menor ou igual a dataB
     * 
     * @param dataA
     * @param dataB
     * @return Boolean
     * 
     */
    public static Boolean menorOuIgualA(Date dataA, Date dataB) {
        Boolean retorno = Boolean.FALSE;

        if (datasNaoNulas(dataA, dataB) && (menorQue(dataA, dataB) || igualA(dataA, dataB))) {
            retorno = Boolean.TRUE;
        }
        return retorno;
    }

    /**
     * 
     * Método responsável por verifricar se dataA é igual a dataB
     * 
     * @param dataA
     * @param dataB
     * @return Boolean
     * 
     */
    public static Boolean igualA(Date dataA, Date dataB) {
        Boolean retorno = Boolean.FALSE;

        if (datasNaoNulas(dataA, dataB)) {
            retorno = (dataA.compareTo(dataB) == 0);
        }
        return retorno;
    }

    /**
     * 
     * Método responsável por
     * 
     * @param dataA
     * @param dateB
     * @return Boolean
     * 
     */
    private static Boolean datasNaoNulas(Date dataA, Date dateB) {
        return dataA != null && dateB != null;
    }

    /**
     * Método responsável por formatar a data para SQL Server
     * 
     * @param data
     * @return String
     * 
     */
    public static String formatarDataSQL(Date data) {
        return formatarData(data, Constantes.FORMATO_DE_DATA_YYYY_MM_DD);
    }

    /**
     * Método responsável por Formatar a data e hora no padrão brasileiro.
     * 
     * @param data
     * @return String
     * 
     */
    public static String formatarDataLocalePtBr(Date data) {
        return formatarData(data, "dd/MM/yyyy HH:mm:ss");
    }

    /**
     * Método responsável por
     * 
     * @param data
     * @return String
     * 
     */
    public static String formatarDataHoraArquivo(Date data) {
        return formatarData(data, Constantes.FORMATO_DE_DATA_YYYYMMDDHHMMSS);
    }

    /**
     * Método responsável por formatar a data no padrão yyyyMMdd
     * 
     * @param data
     * @return String
     * 
     */
    public static String formatarDataArquivo(Date data) {
        return formatarData(data, Constantes.FORMATO_DE_DATA_YYYYMMDD);
    }

    /**
     * Método responsável por Formatar a data de acordo o pattern. (Ex: dd/MM/yyyy)
     * 
     * @param data
     * @param pattern
     * @return String
     * 
     */
    public static String formatarData(Date data, String pattern) {
        String retorno = "";
        if (data != null && pattern != null) {
            SimpleDateFormat formatar = new SimpleDateFormat(pattern);
            retorno = formatar.format(data);
        }
        return retorno;
    }

    /**
     * Converte String em Date conforme o formato passado.
     * 
     * @param data a string que representa a data.
     * @param formato o formato.
     * @return a data.
     */
    public static Date converterStringToDate(String data, String formato) {

        Date dataRetornada = null;
        try {
            DateFormat df = new SimpleDateFormat(formato);
            dataRetornada = (Date) df.parse(data);
        } catch (ParseException e) {
            throw new BancoobRuntimeException(e);
        }
        return dataRetornada;
    }

    /**
     * Método responsável por Fazer a conversao de BigDecimal para Data
     * 
     * @param data
     * @return
     * @throws SQLException
     * @throws ParseException Date
     * 
     */
    public static Date converterBigDecimalDate(BigDecimal data) throws SQLException, ParseException {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.setLenient(false);
        return (Date) format.parse(data.toString());
    }

    /**
     * Converte String em DateTimeDB conforme o formato passado.
     * 
     * @param data a string que representa a data.
     * @param formato o formato.
     * @return a DateTimeDB.
     */
    public static DateTimeDB converterStringToDateTimeDB(String data, String formato) {
        return new DateTimeDB(converterStringToDate(data, formato).getTime());
    }

    /**
     * Método responsável por recuperar um DateTimeDB apartir de uma java.util.Date.
     * 
     * @param data
     * @return DateTimeDB
     * 
     */
    public static DateTimeDB getDateTimeDB(Date data) {
        if (data != null) {
            return new DateTimeDB(data.getTime());
        }
        return null;
    }

    /**
     * 
     * Método responsável por transformar uma data tipo Date, em long, porém retorna em formato String
     * 
     * @param Date
     * @return String
     * @throws BancoobException
     */
    public static String converterDateToLong(Date data) throws BancoobException {
        String retorno = null;
        if (data != null) {
            long aux = data.getTime();
            retorno = Long.toString(aux);
        }
        return retorno;
    }

    /**
     * Método responsável por
     * 
     * @param data
     * @param stringHora
     * @return Date
     * 
     */
    public static Date incrementarHora(Date data, String stringHora) {

        Date dataHora = converterStringToDate(stringHora, "kk:mm:ss");

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(dataHora);

        int horas = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);
        int segundos = calendar.get(Calendar.SECOND);

        Date novaData = incrementarData(data, Calendar.HOUR_OF_DAY, horas);
        novaData = incrementarData(novaData, Calendar.MINUTE, minutos);
        return incrementarData(novaData, Calendar.SECOND, segundos);

    }

    /**
     * Método responsável por
     * 
     * @param data
     * @param escala
     * @param valor
     * @return Date
     * 
     */
    public static Date decrementarData(Date data, int escala, int valor) {
        return incrementarData(data, escala, valor * -1);
    }

    /**
     * Método responsável por
     * 
     * @param data
     * @param escala
     * @param valor
     * @return Date
     * 
     */
    public static Date incrementarData(Date data, int escala, int valor) {
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        calendario.add(escala, valor);

        return calendario.getTime();
    }

    /**
     * Método responsável por
     * 
     * @param data1
     * @param data2
     * @return List<Date>
     * 
     */
    public static List<Date> listarIntervaloDatas(Date data1, Date data2) {
        List<Date> listaData = new ArrayList<Date>();
        // data1 menor que data2
        long qtdDias = calcularDiferencaDias(data2, data1);
        if (qtdDias > 0) {
            listaData.add(data1);
            for (int i = 1; i <= qtdDias; i++) {
                listaData.add(incrementarData(data1, Calendar.DAY_OF_MONTH, i));
            }
        }
        return listaData;
    }

    /**
     * Método responsável por obter a data sem a hora
     * 
     * @param data
     * @return
     */
    public static Date obterDataSemHora(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * Método responsável por
     * 
     * @return int
     * 
     */
    public static int obterAnoAtual() {
        Calendar calendar = Calendar.getInstance();
        // getTime() returns the current date in default time zone
        return calendar.get(Calendar.YEAR);

    }

    /**
     * Método responsável por
     * 
     * @return int
     * 
     */
    public static int obterMesAtual() {
        Calendar calendar = Calendar.getInstance();
        // getTime() returns the current date in default time zone
        // Note: +1 the month for current month
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * Método responsável por
     * 
     * @return int
     * 
     */
    public static int obterDiaAtual() {
        Calendar calendar = Calendar.getInstance();
        // getTime() returns the current date in default time zone
        return calendar.get(Calendar.DATE);
    }

    /**
     * Verifica se a data informada é maior ou igual a data inicio e menor ou igual a data fim.
     * 
     * @param dataInicio A data de inicio.
     * @param dataFim A data de fim.
     * @param data A data a ser verificada.
     * @return true se a data estiver no intervalo, false caso contrario.
     */
    public static boolean estaNoIntervalo(Date dataInicio, Date dataFim, Date data) {

        Date dataInicioConfigurada = configurarPrimeiraDataIntervalo(dataInicio);
        Date dataFimConfigurada = configurarSegundaDataIntervalo(dataFim);

        return (data.after(dataInicioConfigurada) || data.equals(dataInicioConfigurada)) && (data.before(dataFimConfigurada) || data.equals(dataFimConfigurada));
    }

    /**
     * Configura uma primeira data de intervalo, zerando as informaÃ§Ãµes de hora (00:00:00.000).
     * 
     * @param data a data a ser configurada.
     * @return a nova data configurada.
     */
    public static Date configurarPrimeiraDataIntervalo(Date data) {
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);

        return calendario.getTime();
    }

    /**
     * Configura uma segunda data de intervalo, colocando a hora para ser a ï¿½ltima do dia ( 23:59:59.999).
     * 
     * @param data a data a ser configurada.
     * @return a nova data configurada.
     */
    public static Date configurarSegundaDataIntervalo(Date data) {
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        calendario.set(Calendar.HOUR_OF_DAY, ULTIMA_HORA_DIA);
        calendario.set(Calendar.MINUTE, ULTIMO_MINUTO_HORA);
        calendario.set(Calendar.SECOND, ULTIMO_SEGUNDO_MINUTO);
        calendario.set(Calendar.MILLISECOND, ULTIMO_MILISSEGUNDO_SEGUNDO);

        return calendario.getTime();
    }
}
