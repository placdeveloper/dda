package br.com.sicoob.sisbr.sicoobdda.entidades.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;

/**
 * Constantes é responsável por fornecer valores parametrizados.
 * 
 * @author Rafael.Silva
 */
public final class Constantes {

    private Constantes() {
    }

    public static final Long ID_PARAMETRO_DIRETORIO_ARQUIVOS_SDDA = 1947L;

    /**
     * Id da instituição BANCOOB - BANCO COOPERATIVO DO BRASIL
     */
    public static final int ID_BANCOOB = 1;
    public static final String NUMERO_INST_BANCOOB = "0001";

    public static final int ID_SICOOB = 2;

    public static final String TIPO_MOEDA = "R$";

    public static final int ID_PRODUTO_COBRANCA = 9;

    public static final int ID_PRODUTO_CONTA_CORRENTE = 3;

    public static final Long ID_MODALIDADE_PRODUTO_CHEQUE_EM_CUSTODIA = 9L;
    public static final Long ID_TIPO_TARIFA_COBRADA_NO_FECHAMENTO_DIARIO_COBRANCA = 1316L;
    public static final Long ID_PARAMETRO_PRAZO_BAIXA_AUTOMATICA_TITULO_VENCIDO = 20L;

    public static final long ID_PARAMETRO_TERMINO_COBRANCA_SEM_REGISTRO_DATA_INICIO = 1765;
    public static final long ID_PARAMETRO_TERMINO_COBRANCA_SEM_REGISTRO_DATA_FIM = 1763;

    public static final Long COD_LISTA_ITEM_CED_FUNCIONALIDADE = 18L;
    public static final Long COD_LISTA_ITEM_ALTERACAO_ESTRUTURA = 2L;

    public static final Long COD_LISTA_ITEM_CED_DOZE = 12L;

    public static final int LISTA_PERFIS_SEGUNDA_VIA_BOLETO = 1063;
    public static final int LISTA_LEIAUTE_CLIENTE = 1003;
    public static final int LISTA_LEIAUTE_ARQUIVO_CNAB240 = 1118;
    public static final int LISTA_LEIAUTE_LOTE_CNAB240 = 1119;

    public static final String GRUPO_GESTOR = "COBRANCA_GESTOR";

    /**
     * Id da instituição BANCOOB - BANCO COOPERATIVO DO BRASIL
     */
    public static final int TIPO_RELATORIO_SINTETICO = 0;
    public static final int TIPO_RELATORIO_ANALITICO = 1;

    public static final String TIPO_GRAU_COOPERATIVA_CONFEDERACAO = "CONFEDERAÇÃO";
    public static final String TIPO_GRAU_COOPERATIVA_CENTRAL = "CENTRAL";
    public static final String TIPO_GRAU_COOPERATIVA_SINGULAR = "SINGULAR";

    // Constantes relacionadas ao tipo de cooperativa
    public static final int TIPO_CENTRAL = 1;
    public static final int TIPO_SINGULAR = 2;
    public static final int TIPO_SICOOB = 3;

    // Situação de cancelamento do cedente
    public static final Long SITUACAO_CANCELADO = 2L;

    // Situação do pagador DDA adesao excluida
    public static final String SITUACAO_PAGADOR_DDA_ADESAO_EXCLUIDO = "2";

    // Código do sistema cooperativo SICOOB
    public static final short COD_SISTEMA_COOPERATIVO_SICOOB = 1;

    // Códigos para tipo de consulta de Títulos
    public static final int PESQ_EM_ABERTO = 1;
    public static final int PESQ_APONTADOS_CARTORIO = 2;
    public static final int PESQ_SOLICITACAO_APONT_AUTO = 3;

    // Quantidade de dias para baixa automática
    public static final int QTD_DIAS_MIN_BAIXA_AUTOMATICA = 30;
    public static final int QTD_DIAS_MAX_BAIXA_AUTOMATICA = 180;

    // Quantidade de dias para negativação
    public static final int QTD_DIAS_MIN_NEGATIVACAO = 5;
    public static final int QTD_DIAS_MAX_NEGATIVACAO = 99;

    public static final int ID_STATUS_NEGATIVACAO_VENCIDOS = 1;
    public static final int ID_STATUS_NEGATIVACAO_NEGATIVADOS = 2;
    public static final int ID_STATUS_NEGATIVACAO_ENCAMINHADO = 3;
    public static final int ID_STATUS_NEGATIVACAO_AGUARDANDO_ENVIO = 4;
    public static final int ID_STATUS_NEGATIVACAO_RECUSADA = 5;
    public static final int ID_STATUS_NEGATIVACAO_EXCLUIDOS = 6;

    public static final int QTD_DIAS_MIN_PROTESTO = 1;
    public static final int QTD_DIAS_MAX_PROTESTO = 10;

    // Quantidade de dias float
    public static final int QTD_DIAS_MIN_FLOAT = 1;
    public static final int QTD_DIAS_MAX_FLOAT = 3;

    // Valor mínimo de mora e multa
    public static final float VALOR_MINIMO_MULTA_E_MORA = 0.01f;

    // Valor máximo do percentual
    public static final int TOTAL_MAX_PERCENTUAL = 100;

    // Valor máximo do boleto
    public static final double VALOR_MAXIMO_BOLETO = 99999999.99;

    // Id do parametro da base sql para se obter o caminho do repositório de imagens
    public static final int PARAMETRO_REPOSITORIO_IMAGEM = 1678;

    // parametro do SQL utilizado para setar o valor da Data Limite de pagamento das inclusoes do GERA BOLETO ou do SICOOB NEt EMPresaril
    public static final int PARAMETRO_LEGADO_PRAZO_DIAS_BAIXA_AUTOMATICA_TITULO_VENCIDO = 20;

    // Telefone Residencial
    public static final int CODIGO_TIPO_TELEFONE_COMERCIAL = 1;

    public static final String LOCAL_DE_PAGAMENTO = "Pagável em qualquer banco até a data de vencimento";

    public static final String FORMATO_DE_DATA_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String FORMATO_DE_DATA_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMATO_DE_DATA_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMATO_DE_DATA_YYYYMMDD = "yyyyMMdd";

    public static final BigDecimal CEM = new BigDecimal(100);
    public static final BigDecimal TOTAL_DIAS_MES = new BigDecimal(30);
    public static final int SCALE = 10;

    public static final String STRING_ESPACO_EM_BRANCO = " ";
    public static final String STRING_CARACTER_VIRGULA = ",";
    public static final String STRING_PONTO = ".";
    public static final String STRING_PONTO_E_VIRGULA = ";";
    public static final String STRING_QUEBRA_LINHA = "\n";
    public static final String STRING_QUEBRA_LINHA_TXT = "\r\n";
    public static final String STRING_TAB = "\t";
    public static final String STRING_TAB_DUPLO = "\t\t";
    public static final String STRING_SUBLINHADO = "_";
    public static final String STRING_ASPAS_SIMPLES = "\'";
    public static final String STRING_ASPAS_DUPLAS = "\"";

    public static final String MENSAGEM_CAMPO_OBRIGATORIO_NAO_INFORMADO = "campo.obrigatorio.nao.informado";

    public static final String NOME_CAMPO_DATA_MOVIMENTO = "dataMovimento";
    public static final String NOME_CAMPO_VALOR_BOLETO = "valorBoleto";
    public static final String NOME_CAMPO_NUM_COOPERATIVA = "numCooperativa";
    public static final String NOME_CAMPO_NUM_CONTRATO = "numContrato";
    public static final String NOME_CAMPO_NUM_BOLETO = "numBoleto";
    public static final String NOME_CAMPO_IDENTIFICACAO_BANCO = "identificacaoBanco";
    public static final String NOME_CAMPO_CODIGO_MOEDA = "codigoMoeda";
    public static final String NOME_CAMPO_FATOR_VENCIMENTO = "fatorVencimento";

    public static final String STRING_LETRA_S = "s";

    public static final String STRING_PALAVRA_FORAM = "foram";
    public static final String STRING_PALAVRA_FOI = "foi";
    public static final String STRING_PALAVRA_NULL = "null";

    public static final String STRING_NUMERO_0 = "0";
    public static final String STRING_NUMERO_1 = "1";
    public static final String STRING_NUMERO_10 = "10";

    public static final String DATA_FIXA = "07/10/1997";

    public static final int TAMANHO_CPF = 11;
    public static final int TAMANHO_CNPJ = 14;

    public static final short TIPO_PESSOA_CPF = (short) 0;
    public static final short TIPO_PESSOA_CNPJ = (short) 1;

    public static final char TIPO_PESSOA_FISICA = 'F';
    public static final char TIPO_PESSOA_JURIDICA = 'J';

    public static final int TAMANHO_MAX_SEU_NUMERO = 15;
    public static final int TAMANHO_MAX_CONTROLE_EMPRESA = 25;

    public static final int MIN_DIAS_PROTESTO = 0;
    public static final int MAX_DIAS_PROTESTO = 20;
    public static final int MAX_DIAS_LIMITE_IMPRESSAO_TITULO_VENCIDO = 365;

    public static final int MIN_DIAS_FLOAT = 0;
    public static final int MAX_DIAS_FLOAT = 10;

    public static final Long TIPO_PERFIL_TARIFARIO_PADRAO = 1L;

    public static final Character COD_DECLARACAO_PORTADOR_IGUAL_D = 'D';

    // Quantidade minima de caracteres para um texto
    public static final int QTD_CARACTERES = 2;

    public static final int NUM_CEP = 8;

    public static final String JUSTIFICATIVA_DESISTENCIA_PROTESTO = "O Cliente solicitou a Desistência do Apontamento do Título em cartório.";

    // Codigos tipo envio remessas
    public static final long COD_TIPO_HISTORICO_ZERO = 0;
    public static final int COD_TIPO_ENVIO_COBRANCA = 1;
    public static final int COD_TIPO_ENVIO_DEVOLUCAO = 2;
    public static final int COD_TIPO_ENVIO_INCONSISTENCIA = 3;

    // IdParametros das remessas
    public static final int ID_PARAMETRO_COBRANCA = 1605;
    public static final int ID_PARAMETRO_DEVOLUCAO = 1664;
    public static final int ID_PARAMETRO_INCONSISTENCIA = 1663;

    // Teste Unitário e TDD
    public static final String BRANCO = "";
    public static final DateTimeDB DATE_TIME_DB_AUX = new DateTimeDB();
    public static final Date DATE_AUX = new Date();
    public static final java.sql.Date DATE_SQL = new java.sql.Date(DATE_AUX.getTime());
    public static final short SHORT_UM = (short) 1;
    public static final short SHORT_ZERO = (short) 0;
    public static final short SHORT_30 = (short) 30;
    public static final XMLGregorianCalendar XML_GREGORIAN_CALENDAR_AUX = getXMLGregorianCalendar();
    public static final int INTEGER_UM = 1;
    public static final int INTEGER_ZERO = 0;
    public static final int INTEGER_MIL = 1000;
    public static final int INTEGER_20_MIL = 20000;
    public static final int INTEGER_CEM = 100;
    public static final int INTEGER_DEZ = 10;
    public static final long LONG_ZERO = 0L;
    public static final long LONG_UM = 1L;
    public static final long LONG_DOIS = 2L;
    public static final long LONG_MIL = 1000L;
    public static final float FLOAT_UM = 1f;
    public static final String TESTE_SUCESSO = "Passou";
    public static final String TESTE_FALHA = "Falhou";
    public static final String CPF_AUX = "12345678909";
    public static final String CPF_INVALIDO_AUX = "12345678999";
    public static final String URL_STRING_AUX = "http://www.google.com";
    public static final String STRING_QTD_TOTAL_PARCELAS = "99999";
    public static final String NUM_CTRL_DDA = "00001";
    public static final String NOME_RAZAO_SOCIAL_BENEFICIARIO = "BENEFICIÁRIO";
    public static final BigInteger BIG_INTEGER_1_AUX = BigInteger.valueOf(123L);
    public static final BigInteger BIG_INTEGER_2_AUX = BigInteger.valueOf(2L);
    public static final String TIPO_PESSOA_FISICA_TESTE = "F";
    public static final String NOME_TESTE = "TESTE";
    public static final String KEY_CACHE_NUM_BANCO_TESTE = "COBRANCA_CACHE_INSTITUICAO_NUM_BANCO_123";
    public static final short NUM_BANCO = 123;
    public static final int ID_INST = 123;
    public static final short ID_PRODUTO = 1;
    public static final String NUM_LINHA_DIGITAVEL = "75695303840300260340900189000011672370000050000";
    public static final String COD_BARRAS_39_VALIDO = "12345678901234567890123456789012345678901234";
    public static final String COD_BARRAS_INVALIDO = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ09876543";
    public static final String LINHA_DIGITAVEL = "01691111191111111822722222222220571350006000000";
    public static final String COD_BARRAS_BANCOOB = "75697652100000350001306605001953400005425001";
    public static final String COD_BARRAS_INST_FINANCEIRA = "98881888888888888888888888888888888888888888";
    public static final String COD_BARRAS_VALOR_ZERO = "75697652100000000000000000001953400005425001";
    public static final String COD_BARRAS_DT_VENC_BEFORE = "75697756500000350001306605001953400005425001";

    // Troca de Arquivos
    public static final int ID_PARAM_LEGADO_DIRETORIO_TROCA_ARQUIVO_IMPORTACAO = 1737;
    public static final int ID_PARAM_LEGADO_DIRETORIO_PROCESSAMENTO_DIA = 1740;
    public static final int ID_PARAM_LEGADO_DIRETORIO_PROCESSAMENTO_NOTURNO = 1741;
    public static final int ID_PARAM_LEGADO_DIRETORIO_PROCESSAMENTO_ONLINE = 1742;

    public static final String DATA_VENCIMENTO_CONTRA_APRESENTACAO_STR = "99999999";
    public static final String DATA_ZERO = "00000000";
    public static final String PATTERN_DATA = "ddMMyyyy";
    public static final String PATTERN_DATA_HORA = "ddMMyyyyHH:mm";
    public static final String PATTERN_DATA_HORA_BD = "yyyy-MM-dd HH:mm:ss.SSS";

    // Padrões utilizados no retorno das transações
    public static final String PATTERN_DATA_HORA_TRANSACAO = "yyyyMMdd HH:mm:ss.SSS";
    public static final String PATTERN_DATA_TRANSACAO = "yyyyMMdd";

    public static final String HORA_ZERO = "00";
    public static final String MINUTO_ZERO = "00";
    public static final String HORA_UM = "01";
    public static final String MINUTO_UM = "01";

    public static final int POS_INICIO_TRUNCAR_CPF_14_POS = 3;
    public static final int POS_INICIO_TRUNCAR_CNPJ_15_POS = 1;
    public static final int POS_INICIO_TRUNCAR_CPF_15_POS = 4;

    public static final String NOME_BANCO = "SICOOB";
    public static final String SIGLA_INST_BANCOOB = "BANCOOB";
    public static final int COD_REMESSA_RETORNO = 1;
    public static final int NUM_VERSAO_LAYOUT_ARQUIVO = 85;

    public static final String TIPO_OPERACAO_RETORNO = "T";
    public static final int TIPO_OPERACAO_RETORNO_CNAB400 = 2;
    public static final String DESC_TIPO_OPERACAO_RETORNO_CNAB400 = "RETORNO";
    public static final int TIPO_SERVICO_COBRANCA = 1;
    public static final String DESC_TIPO_SERVICO_RETORNO_CNAB400 = "COBRANCA";
    public static final String TIPO_CONSULTA_MENSAGENSDDA = "TODOS";

    public static final int COD_MOEDA_REAL = 9;
    public static final String NOME_DIRETORIO_ARQUIVOS_PROCESSADOS = "processados";

    // IdParametros das remessas
    public static final int ID_PARAMETRO_LIBERAR_FECHAMENTO_COBRANCA = 1583;
    public static final int ID_PARAMETRO_LIBERAR_FECHAMENTO_DEVOLUCAO = 1648;
    public static final int ID_PARAMETRO_LIBERAR_FECHAMENTO_INCONSISTENCIA = 1647;

    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String ENCODING_ISO_8859_1 = "ISO-8859-1";
    public static final String ENCODING_UTF_16BE = "UTF-16BE";

    // Apresentação de Boletos
    public static final int TAMANHO_LINHA_DIGITAVEL = 47;
    public static final int TAMANHO_CODIGO_BARRAS = 44;

    public static final int QUANTIDADE_PESQUISA_POR_PAGINA = 10;

    public static final String USUARIO_MIGRACAO = "USUARIO.MIGRACAO";

    public static final int TIPO_STEP_APONTAR_NEGATIVACAO = 1;
    public static final int TIPO_STEP_ENCAMINHAR_NEGATIVACAO = 2;
    public static final int TIPO_STEP_TRATAR_RETORNO = 3;

    // Processamento Paralelo
    public static final String APLICATIVO_PROCESSAMENTO_PARALELO_PARAMETRO = "AtualizacaoParametroAplicativo";

    public static final String DOM_SIT = "MES01";
    public static final String ISPB_BANCOOB = "02038232";
    public static final String ISPB_CIP_SITRAF = "17423302";
    public static final String BANCOOB = "756";
    public static final int NUM_BANCOOB = 756;
    public static final Integer NUM_COOP_0001 = 0001;
    public static final int TOTAL_POR_ARQUIVO = 50000;

    public static final String DIRETORIO_ENVIO_TESTE_ARQUIVOS_CONNECT = "\\mnt\\CIP-DDA\\ENVIAR";
    public static final String DIRETORIO_RECEBIMENTO_TESTE_ARQUIVOS_CONNECT = "/mnt/CIP-DDA/RECEBIDOS";

    public static final String MENSAGEM_ECO = "Teste de conectividade 756-SICOOB";

    public static final String SUFIXO_ARQUIVO_PROTOCOLO = "_PRO";
    public static final String SUFIXO_ARQUIVO_ERRO = "_ERR";
    public static final String SUFIXO_ARQUIVO_RETORNO = "_RET";

    public static final String CNPJ_AUX = "50852684000128";
    public static final String CNPJ_INVALIDO_AUX = "36107715000190";
    public static final BigInteger CNPJ_AUX_BIGINT = new BigInteger(CNPJ_AUX);

    public static final String NOME_METODO_CODERRO = "getCodErro";
    public static final String NOME_FIELD_CODERRO = "codErro";

    public static final String ITEM_COMBO_TODOS = "todos";
    public static final String VALUE_COMBO_TODOS = "Todos";

    public static final String INDICADOR_ADESAO_PAGADOR_DDA = "S";

    public static final Date DATA_BASE_CALCULAR_ANTIGO_FATOR_VENCIMENTO = DataUtil.converterStringToDate("07/10/1997", FORMATO_DE_DATA_DD_MM_YYYY);
    public static final Date DATA_LIMITE_CALCULAR_ANTIGO_FATOR_VENCIMENTO = DataUtil.converterStringToDate("21/02/2025", FORMATO_DE_DATA_DD_MM_YYYY);

    public static final int NUMERO_COOPERATIVA_DDA = 9277;

    public static final String COD_INSTITUICAO_FINANCEIRA = "988";
    public static final int NUM_INSTITUICAO_FINANCEIRA = 988;

    public static final char TIPO_VALIDADE_DIA = 'D';
    public static final char TIPO_VALIDADE_MINUTO = 'M';

    public static final String TAGRESPOSTAENVIO = "<mqrespostaenvio>";
    public static final String TAGRECEBIMENTO = "<mqrecebimento>";
    public static final String TAGRECEBIMENTOARQ = "ArqRecebimento";
    public static final String TAGARQRECEBIMENTOLISTA = "ArqRecebimentoLista";
    public static final String TAGCODPARTDESTINATARIO = "CodPartDestinatario";
    public static final String TAGNUMCODBARRASBAIXAEFT = "NumCodBarrasBaixaEft";
    public static final String SEPARADOR_DE_DADOS = "::";

    public static final String CABECALHO_ADDA_DOC = "<ADDADOC xmlns=\"http://www.bcb.gov.br/ARQ/CODTIPOMENSAGEM.xsd\">";

    public static final String STR_SEPARACAO_TRACO = " - ";
    public static final String STR_SEPARACAO = "*******************************************";
    public static final String STR_SEPARACAO_2 = " ### ";
    public static final String JOB = "JOB: ";
    public static final String STEP = "STEP ";
    public static final String QTD_STEPS_EXECUTADOS = "Quantidade de STEPS que serão executados: ";
    public static final String PREPARANDO_STEPS = "************ Preparando steps *************";

    public static final int QTD_ARQUIVOS_INSERIDOS_BOLETO_POR_VEZ = 2500;

    public static final String TIPO_HORARIO_PADRAO = "P";
    public static final String TIPO_HORARIO_EVENTUAL = "E";

    public static final String CAMPO_NOME_ARQUIVO = "nomeArquivoCip";

    // Referente a tabela TipoSituacaoBoleto do cobrança
    public static final int SITUACAO_BOLETO_EM_ABERTO = 1;
    public static final int SITUACAO_BOLETO_BAIXADO = 18;
    public static final int SITUACAO_BOLETO_LIQUIDADO = 23;
    public static final int SITUACAO_BOLETO_AGENDADO = 99;

    // Situação Requisição do DDA
    public static final String REQUISIÇÃO_REGISTRADA = "1";
    public static final String REQUISIÇÃO_ATENDIDA_MENSAGEM_COMPLETA = "2";
    public static final String REQUISIÇÃO_SEM_DADOS_ATENDAM_FILTRO = "3";
    public static final String INICIO_PROCESSAMENTO = "4";
    public static final String REQUISIÇÃO_ATENDIDA_MENSAGEM_INCOMPLETA = "5";
    public static final String REQUISIÇÃO_ARQUIVO_COMPLETO = "6";
    public static final String REQUISIÇÃO_ATENDIDA_MENSAGEM_PARCIALMENTE = "7";
    public static final String REQUISIÇÃO_ATENDIDA_ARQUIVO_PARCIALMENTE = "8";

    public static final int LIMITE_DIAS_PESQUISA_BOLETO_DDA = 60;

    public static final String SUFIXO_NOME_ARQUIVO_ABERTO_FLEX = "_MANUAL";

    public static final String SUCESSO_CIP = "Retorno da CIP com Sucesso";
    public static final String SEM_RETORNO_CIP = "Sem Retorno CIP";
    public static final String MENSAGEM_NAO_ENVIADA_PARA_CIP = "Mensagem não enviada para CIP";
    public static final String RETORNO_CIP_COM_ERRO = "Retorno da CIP com ERRO";
    public static final String RETORNO_CIP_NAO_RETORNOU_RESULTADO = "Boleto não encontrado na CIP";

    public static final int EVENTO_TARIFAVEL_VIGENTE = 1;
    public static final int EVENTO_TARIFAVEL_PROGRAMADO = 2;
    public static final int EVENTO_TARIFAVEL_VENCIDO = 3;

    public static final double DOUBLE_VALUE_ZERO = 0.0;

    public static final String RELATORIO_CONTEXTO_FLEX = "CONTEXTO_FLEX";

    /**
     * Método responsável por
     * 
     * @return XMLGregorianCalendar
     */
    private static XMLGregorianCalendar getXMLGregorianCalendar() {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(Constantes.DATE_AUX);
        XMLGregorianCalendar date2 = null;
        try {
            date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return date2;
    }
}
