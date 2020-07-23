package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Parametro é responsável por
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "PARAMETRO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ParametroVO")
public class ParametroDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = 4982693920708926367L;

    // TODO - PARAMETROS DEPRECIADOS ===================
    public static final long VERSAO_ATUAL_CATALOGO_CIP = 63;
    // =================================================

    public static final long DIRETORIO_ENVIO_ARQUIVOS_CIP = 2;
    public static final long DIRETORIO_ARQUIVOS_RECEBIDOS_CIP = 3;
    public static final long DIRETORIO_CARGATMP_ARQUIVOS_CIP = 4;
    public static final long QTD_REGISTROS_GRAFICO_MONITORACAO = 5;
    public static final long REPLICAR_BENEFICIARIO_LEGADO = 6;
    public static final long QTD_REGISTROS_BLOCO_EXPURGO = 7;
    public static final long QTD_DIAS_LIMITE_EXPURGO_MENSAGENS = 8;
    public static final long CONTINGENCIA_CARGA_BENEFICIARIOS_POR_MENSAGEM_EM_EXECUCAO = 12;
    public static final long QTD_MAXIMA_ERROS_CONTINGENCIA_POR_MENSAGEM = 13;
    public static final long QTD_TOTAL_ERROS_CONTINGENCIA_POR_MENSAGEM_EM_EXECUCAO = 14;

    // Monitoração Demais Mensagens;
    public static final long MON_DEMAIS_MSG_NIVEL_CRITICO_ENVIAR = 15;
    public static final long MON_DEMAIS_MSG_NIVEL_CRITICO_SEM_RETORNO_SSPB = 16;
    public static final long MON_DEMAIS_MSG_NIVEL_CRITICO_SEM_RETORNO_CIP = 17;
    public static final long MON_DEMAIS_MSG_NIVEL_CRITICO_RETORNO_ERRO = 18;

    public static final long QTD_MENSAGEM_ENVIADA_POR_RAJADA = 20;
    public static final long VALIDADE_CONSULTA_CIP = 21;
    public static final long QTD_MAX_REGISTROS_POR_STEP_MOTOR_ENVIO_CIP = 22;
    public static final long QTD_MAX_REGISTRO_NA_FILA_PERMITE_ENVIO_IMEDIATO = 23;
    public static final long NUM_PRIORIDADE_ENVIO_MSG_CIP = 24;
    public static final long MOTOR_ENVIO_MSGS_EM_EXECUCAO = 25;
    public static final long CONTINGENCIA_HABILITADA_CONSULTA_CIP = 26;
    public static final long SICOOBDDA_CONSULTA_BOLETO_ATIVO = 27;
    public static final long VALOR_MAXIMO_PERMITIDO_RECEBIMENTO_EM_CONTINGENCIA = 29;

    // Indica o valor dos boletos obrigatoriamente já estão na CIP
    public static final long VALOR_MAX_CONSULTA_PILOTO_CIP = 30;
    // Indica o valor dos boletos que estão sendo enviados para CIP (estão na onda)
    public static final long VALOR_MIN_BOLETO_ENVIO_A_CIP = 31;

    public static final long MOTOR_ENVIO_ARQUIVO_EM_EXECUCAO = 33;
    public static final long QTD_MAX_REGISTROS_ARQUIVOS_ENVIO = 34;
    public static final long MOTOR_RECEBIMENTO_ARQUIVO_TIPO_RET_EM_EXECUCAO = 35;
    public static final long DIRETORIO_ENVIO_ARQUIVOS_ANTES_DA_CIP = 36;
    public static final long DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO = 37;
    public static final long QTD_MAX_REGISTROS_POR_STEP = 38;
    public static final long TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO = 39;
    public static final long MOTOR_RECEBIMENTO_ARQUIVO_TIPO_PAG_EM_EXECUCAO = 40;
    public static final long MOTOR_RECEBIMENTO_ARQUIVO_TIPO_BOL_EM_EXECUCAO = 41;
    public static final long MOTOR_RECEBIMENTO_ARQUIVO_TIPO_BEN_EM_EXECUCAO = 42;
    public static final long MOTOR_RECEBIMENTO_ARQUIVO_TIPO_BAX_EM_EXECUCAO = 43;
    public static final long MOTOR_RECEBIMENTO_ARQUIVO_TIPO_TER_EM_EXECUCAO = 44;
    public static final long TEMPO_MAX_RETORNO_CONSULTA_DDA0110 = 45;
    public static final long LOCAL_ARQUIVO_TEMPORARIOS = 46;
    public static final long DIRETORIO_ARQUIVOS_ERRO_CONTINGENCIA_MENSAGEM = 47;
    public static final long MOTOR_ABERTURA_ARQUIVO_TIPO_RET_EM_EXECUCAO = 48;
    public static final long MOTOR_ABERTURA_ARQUIVO_TIPO_PAG_EM_EXECUCAO = 49;
    public static final long MOTOR_ABERTURA_ARQUIVO_TIPO_BOL_EM_EXECUCAO = 50;
    public static final long MOTOR_ABERTURA_ARQUIVO_TIPO_BEN_EM_EXECUCAO = 51;
    public static final long MOTOR_ABERTURA_ARQUIVO_TIPO_BAX_EM_EXECUCAO = 52;
    public static final long MOTOR_ABERTURA_ARQUIVO_TIPO_TER_EM_EXECUCAO = 53;
    public static final long QTD_MAXIMA_PROCESSAMENTO_ARQUIVOS = 54;
    public static final long EMAIL_REMETENTE = 55;
    public static final long EMAIL_DESTINATARIO = 56;
    public static final long EMAIL_ASSUNTO_ERRO_MSG_CONTINGENCIA = 57;
    public static final long EMAIL_DDA_HABILITADO = 58;
    public static final long TEMPO_SEGUNDOS_REUTILIZAR_DDA0110E = 59;
    public static final long QTD_MAX_REGISTROS_POR_STEP_PAG = 61;
    public static final long CONSULTAR_BOLETODDA_APOS_TIMEOUT = 62;

    // Monitoração DDA0110
    public static final long MON_PERCENTUAL_ACEITAVEL_SLA_DDA0110 = 64;
    public static final long MON_TEMPO_ATUALIZACAO_MQ_DDA0110 = 65;

    // Monitoração Arquivo Remessa
    public static final long MON_ARQ_REMESSA_NIVEL_CRITICO_ENVIAR = 66;
    public static final long MON_ARQ_REMESSA_NIVEL_CRITICO_SEM_PROTOCOLO_CIP = 67;
    public static final long MON_ARQ_REMESSA_NIVEL_CRITICO_SEM_RETORNO_CIP = 68;
    public static final long MON_ARQ_REMESSA_NIVEL_CRITICO_PROCESSADO_ERRO = 69;
    public static final long MON_TEMPO_ATUALIZACAO_ARQ_REMESSA = 70;

    // Monitoração Arquivo Varredura
    public static final long MON_ARQ_VARREDURA_NIVEL_CRITICO_GEN0015_SEM_ARQ = 71;
    public static final long MON_ARQ_VARREDURA_NIVEL_CRITICO_ARQ_SEM_GEN0015 = 72;
    public static final long MON_ARQ_VARREDURA_NIVEL_CRITICO_DISPONIVEL = 73;
    public static final long MON_ARQ_VARREDURA_NIVEL_CRITICO_EM_PROCESSAMENTO = 74;
    public static final long MON_TEMPO_ATUALIZACAO_ARQ_VARREDURA = 75;
    public static final long LISTA_COOPERATIVAS_IGNORAR = 76;

    public static final long MON_TEMPO_ALERTA_GEN0015_SEM_ARQ = 77;

    public static final long TEMPO_MINUTOS_CONSULTA_BOLETO106 = 78;

    public static final long MON_TEMPO_ATUALIZACAO_MQ_DEMAIS_MSG = 79;
    public static final long MON_TEMPO_ALERTA_BLOQUEIO_MOTOR_ENVIO_ARQ = 80;
    public static final long MON_TEMPO_ALERTA_BLOQUEIO_MOTOR_ENVIO_MSG = 81;

    public static final long HABILITA_CONSULTA_BOLETO_CIP_POR_INSTITUICAO = 82;

    public static final long DIA_VENCIMENTO_FATURA_EVENTO_TARIFAVEL_CIP = 83;

    public static final long DESVIO_PADRAO_RATEIO_TARIFAS = 84;

    /**
     * Parametros do 85 a 92, utilizado nas queries do evento tarifavel
     */
    public static final long FAIXA_ROLLOUT_MAIOR_OU_IGUAL_240000 = 85;
    public static final long FAIXA_ROLLOUT_MAIOR_OU_IGUAL_50000 = 86;
    public static final long FAIXA_ROLLOUT_MAIOR_OU_IGUAL_4000 = 87;
    public static final long FAIXA_ROLLOUT_MAIOR_OU_IGUAL_2000 = 88;
    public static final long FAIXA_ROLLOUT_MAIOR_OU_IGUAL_800 = 89;
    public static final long FAIXA_ROLLOUT_MAIOR_OU_IGUAL_400 = 90;
    public static final long FAIXA_ROLLOUT_MAIOR_OU_IGUAL_001 = 91;
    public static final long FAIXA_ROLLOUT_IGUAL_A_0 = 92;

    /**
     * Parametros - 32,121, 93 e 122 Utilizados no SWS do motor de carga de mensagens CIP
     */
    public static final long MOTOR_CARGA_MSGS_TODOS_EM_EXECUCAO = 32;
    public static final long MOTOR_CARGA_MSGS_ESPECIFICAS_EM_EXECUCAO = 121;
    public static final long QTDE_REGISTROS_AGRUPAMENTO_SWS_CARGA_TODOS = 93;
    public static final long QTDE_REGISTROS_AGRUPAMENTO_SWS_CARGA_ESPECIFICAS = 122;
    public static final long HORA_MOTOR_CARGA_BAIXA = 103;

    public static final long QTDE_MAXIMA_ARQUIVOS_PROCESSAMENTO_SWS_ENVIO_ARQUIVO = 94;
    public static final long HABILITAR_ENVIO_MENSAGEM_GERA_BOLETO_ONLINE = 96;
    public static final long HABILITAR_MODELO_CALCULO_01 = 97;
    public static final long HABILITAR_ENVIO_MENSAGEM_SICOOB_NET_EMPRESARIAL_ONLINE = 98;

    public static final long SICOOBDDA_ATIVADO = 100;



    public static final long QTDE_MAXIMA_ARQUIVOS_PROCESSADOS_POR_INTERACAO = 104;

    public static final long CACHE_HABILITADO = 105;
    public static final long CARACTERES_REMOVER_INCLUSAO_BOLETO = 106;

    public static final long EMAIL_CACHE_ASSUNTO = 107;
    public static final long EMAIL_CACHE_HABILITADO = 108;

    public static final long HABILITAR_TIMER = 109;
    public static final long SERVIDOR_EXECUCAO_TIMER = 110;
    public static final long TEMPO_AFERICAO_MENSAGENS_DDA0110_PELO_TIMER = 111;
    public static final long PERCENTUAL_ACEITAVEL_DDA0110_QUE_EXTRAPOLARAM_SLA = 112;
    public static final long TEMPO_DESABILITAR_CONTINGENCIA_AUTOMATICA = 113;
    public static final long DATA_HORA_HABILITACAO_CONTINGENCIA_AUTOMATICA = 114;
    public static final long QTDE_TENTATIVAS_CONTINGENCIA_AUTOMATICA = 115;
    public static final long QTDE_MAXIMA_TENTATIVAS_ACEITAVEIS_HABILITAR_CONTINGENCIA_MANUAL = 116;
    public static final long HABILITAR_CONTINGENCIA_MANUAL = 117;

    public static final long TEMPO_MINUTOS_BLOQUEAR_MOTOR_ENVIO_ARQUIVO = 118;
    public static final long TIPO_GRADE_HORARIA_MOTOR_ENVIO_ARQUIVO = 119;

    public static final long PERMITE_PAGAMENTO_BOLETO_SEM_REGISTRO = 120;

    public static final long QTDE_DIAS_VENCIMENTO_BOLETO_ENVIAR_ONLINNE = 123;

    public static final long ENCONDING_ENVIO_ARQUIVO = 124;
    // Parâmetro criado pra cada CANAL
    public static final long OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_ATM = 125; // Canal 3
    public static final long OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_IB = 126;// Canal 4
    public static final long OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_SICOOB_NET = 127;// Canal 54
    public static final long OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_SICOOB_NET_EMPRESARIAL = 128;// Canal 5
    public static final long OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_SICOOB_NET_MOBILE = 129;// Canal 6
    public static final long OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_SICOOB_NET_MOBILE_EMPRESARIAL = 130;// Canal 17
    public static final long OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_CONTA_PAGAMENTO_DIGITAL = 131;// Canal 53
    public static final long OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_RETAGUARDA = 133;// Canal 1

    public static final long QTDE_MINIMA_MENSAGENS_VALIDACAO_CONTINGENCIA = 132;

    @Id
    @Column(name = "IDPARAMETRO", unique = true, nullable = false)
    private Long id;

    @Column(name = "NOMEPARAMETRO", nullable = false, length = 255)
    private String nomeParametro;

    @ManyToOne
    @JoinColumn(name = "IDTIPOPARAMETRO", nullable = false)
    private TipoParametroDDA tipoParametro;

    @Column(name = "BOLVISIVELFUNCIONALIDADEALTERACAO", nullable = false)
    private Boolean bolVisivelFuncionalidadeAlteracao;

    @Column(name = "BOLPERMITEALTERACAOPELOUSUARIO", nullable = false)
    private Boolean bolPermiteAlteracaoPeloUsuario;

    @Column(name = "BOLATIVO", nullable = false)
    private Boolean bolAtivo;

    @Column(name = "DATACRIACAO", nullable = false)
    private DateTimeDB dataCriacao;

    @Column(name = "DATAHORAULTIMAATUALIZACAO", nullable = false)
    private DateTimeDB dataHoraUltimaAtualizacao;

    @Column(name = "DESCPARAMETRO", nullable = true)
    private String descParametro;

    @Column(name = "VALORBASEPARAMETRO", nullable = false)
    private String valorParametro;

    @Column(name = "VALORBASEPARAMETROTEXTO", nullable = true)
    private String valorParametroTexto;

    @Column(name = "NOMETABELADOMINIO")
    private String nomeTabelaDominio;

    @Column(name = "BOLPARAMETROGLOBAL", nullable = false)
    private Boolean bolParametroGlobal;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<ValorParametroDDA> listaValorParametro;

    @Transient
    private boolean ativo;

    @Transient
    private String descInstituicao;

    @Transient
    private int numPac;

    /**
     * @param id
     * @param nomeParametro
     */
    public ParametroDDA(Long id, String nomeParametro) {
        this.id = id;
        this.nomeParametro = nomeParametro;
    }

    /**
     * 
     */
    public ParametroDDA() {
    }

    /**
     * @param id
     * @param bolAtivo
     * @param valorParametro
     */
    public ParametroDDA(Long id, Boolean bolAtivo, String valorParametro) {
        super();
        this.id = id;
        this.bolAtivo = bolAtivo;
        this.valorParametro = valorParametro;
    }

    /**
     * @param id
     * @param nomeParametro
     * @param tipoParametro
     * @param valorParametro
     */
    public ParametroDDA(Long id, String nomeParametro, String valorParametro) {
        super();
        this.id = id;
        this.nomeParametro = nomeParametro;
        this.valorParametro = valorParametro;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the nomeParametro
     */
    public String getNomeParametro() {
        return nomeParametro;
    }

    /**
     * @param nomeParametro the nomeParametro to set
     */
    public void setNomeParametro(String nomeParametro) {
        this.nomeParametro = nomeParametro;
    }

    /**
     * @return the descParametro
     */
    public String getDescParametro() {
        return descParametro;
    }

    /**
     * @param descParametro the descParametro to set
     */
    public void setDescParametro(String descParametro) {
        this.descParametro = descParametro;
    }

    /**
     * @return the tipoParametro
     */
    public TipoParametroDDA getTipoParametro() {
        return tipoParametro;
    }

    /**
     * @param tipoParametro the tipoParametro to set
     */
    public void setTipoParametro(TipoParametroDDA tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    /**
     * @return the bolVisivelFuncionalidadeAlteracao
     */
    public Boolean getBolVisivelFuncionalidadeAlteracao() {
        return bolVisivelFuncionalidadeAlteracao;
    }

    /**
     * @param bolVisivelFuncionalidadeAlteracao the bolVisivelFuncionalidadeAlteracao to set
     */
    public void setBolVisivelFuncionalidadeAlteracao(Boolean bolVisivelFuncionalidadeAlteracao) {
        this.bolVisivelFuncionalidadeAlteracao = bolVisivelFuncionalidadeAlteracao;
    }

    /**
     * @return the bolPermiteAlteracaoPeloUsuario
     */
    public Boolean getBolPermiteAlteracaoPeloUsuario() {
        return bolPermiteAlteracaoPeloUsuario;
    }

    /**
     * @param bolPermiteAlteracaoPeloUsuario the bolPermiteAlteracaoPeloUsuario to set
     */
    public void setBolPermiteAlteracaoPeloUsuario(Boolean bolPermiteAlteracaoPeloUsuario) {
        this.bolPermiteAlteracaoPeloUsuario = bolPermiteAlteracaoPeloUsuario;
    }

    /**
     * @return the bolAtivo
     */
    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    /**
     * @param bolAtivo the bolAtivo to set
     */
    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

    /**
     * @return the dataCriacao
     */
    public DateTimeDB getDataCriacao() {
        return dataCriacao;
    }

    /**
     * @param dataCriacao the dataCriacao to set
     */
    public void setDataCriacao(DateTimeDB dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     * @return the dataHoraUltimaAtualizacao
     */
    public DateTimeDB getDataHoraUltimaAtualizacao() {
        return dataHoraUltimaAtualizacao;
    }

    /**
     * @param dataHoraUltimaAtualizacao the dataHoraUltimaAtualizacao to set
     */
    public void setDataHoraUltimaAtualizacao(DateTimeDB dataHoraUltimaAtualizacao) {
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the valorParametro
     */
    public String getValorParametro() {
        return valorParametro;
    }

    /**
     * @param valorParametro the valorParametro to set
     */
    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    /**
     * @return the valorParametroTexto
     */
    public String getValorParametroTexto() {
        return valorParametroTexto;
    }

    /**
     * @param valorParametroTexto the valorParametroTexto to set
     */
    public void setValorParametroTexto(String valorParametroTexto) {
        this.valorParametroTexto = valorParametroTexto;
    }

    /**
     * @return the nomeTabelaDominio
     */
    public String getNomeTabelaDominio() {
        return nomeTabelaDominio;
    }

    /**
     * @param nomeTabelaDominio the nomeTabelaDominio to set
     */
    public void setNomeTabelaDominio(String nomeTabelaDominio) {
        this.nomeTabelaDominio = nomeTabelaDominio;
    }

    /**
     * @return the bolParametroGlobal
     */
    public Boolean getBolParametroGlobal() {
        return bolParametroGlobal;
    }

    /**
     * @param bolParametroGlobal the bolParametroGlobal to set
     */
    public void setBolParametroGlobal(Boolean bolParametroGlobal) {
        this.bolParametroGlobal = bolParametroGlobal;
    }

    /**
     * @return the listaValorParametro
     */
    public List<ValorParametroDDA> getListaValorParametro() {
        return listaValorParametro;
    }

    /**
     * @param listaValorParametro the listaValorParametro to set
     */
    public void setListaValorParametro(List<ValorParametroDDA> listaValorParametro) {
        this.listaValorParametro = listaValorParametro;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the descInstituicao
     */
    public String getDescInstituicao() {
        return descInstituicao;
    }

    /**
     * @param descInstituicao the descInstituicao to set
     */
    public void setDescInstituicao(String descInstituicao) {
        this.descInstituicao = descInstituicao;
    }

    /**
     * @return the numPac
     */
    public int getNumPac() {
        return numPac;
    }

    /**
     * @param numPac the numPac to set
     */
    public void setNumPac(int numPac) {
        this.numPac = numPac;
    }

}
