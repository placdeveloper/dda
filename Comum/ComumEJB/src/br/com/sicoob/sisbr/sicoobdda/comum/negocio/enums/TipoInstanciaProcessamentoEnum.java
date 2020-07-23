/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.enums
 * Arquivo:         TipoInstanciaSWSEnum.java
 * Data Criação:    Jan 18, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_ABERTURA_ARQUIVO_TIPO_BAX_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_ABERTURA_ARQUIVO_TIPO_BEN_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_ABERTURA_ARQUIVO_TIPO_BOL_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_ABERTURA_ARQUIVO_TIPO_PAG_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_ABERTURA_ARQUIVO_TIPO_RET_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_ABERTURA_ARQUIVO_TIPO_TER_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_RECEBIMENTO_ARQUIVO_TIPO_BAX_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_RECEBIMENTO_ARQUIVO_TIPO_BEN_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_RECEBIMENTO_ARQUIVO_TIPO_BOL_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_RECEBIMENTO_ARQUIVO_TIPO_PAG_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_RECEBIMENTO_ARQUIVO_TIPO_RET_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_RECEBIMENTO_ARQUIVO_TIPO_TER_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.QTD_MAX_REGISTROS_POR_STEP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.QTD_MAX_REGISTROS_POR_STEP_PAG;

/**
 * TipoInstanciaSWSEnum é responsável por
 * 
 * @author Adriano.Pinheiro
 */
public enum TipoInstanciaProcessamentoEnum {
    /* @formatter:off */
    RET("RETORNO ERRO PROTOCOLO", MOTOR_RECEBIMENTO_ARQUIVO_TIPO_RET_EM_EXECUCAO, MOTOR_ABERTURA_ARQUIVO_TIPO_RET_EM_EXECUCAO, "", new String[] { "PRO", "RET", "ERR" }, 
            new String[] { "ADDA001RET", "ADDA001PRO", "ADDA001ERR", "ADDA005RET", "ADDA005PRO", "ADDA005ERR", "ADDA006RET", "ADDA006PRO", 
            "ADDA006ERR", "ADDA101RET", "ADDA101PRO", "ADDA101ERR", "ADDA102PRO", "ADDA102ERR", "ADDA102RET", "ADDA108PRO", "ADDA108RET", 
            "ADDA108ERR", "ADDA110RET", "ADDA110PRO", "ADDA110ERR", "ADDA114RET", "ADDA114PRO", "ADDA114ERR", "ADDA118RET", "ADDA118PRO",
            "ADDA118ERR", "ADDA122RET", "ADDA122PRO", "ADDA122RERR" }, QTD_MAX_REGISTROS_POR_STEP),
    PAG("PAGADOR", MOTOR_RECEBIMENTO_ARQUIVO_TIPO_PAG_EM_EXECUCAO, MOTOR_ABERTURA_ARQUIVO_TIPO_PAG_EM_EXECUCAO, "CNPJ_CPFPagdr", new String[] { "DIS" }, 
            new String[] { "ADDA002", "ADDA003" }, QTD_MAX_REGISTROS_POR_STEP_PAG),
    BOL("BOLETO", MOTOR_RECEBIMENTO_ARQUIVO_TIPO_BOL_EM_EXECUCAO, MOTOR_ABERTURA_ARQUIVO_TIPO_BOL_EM_EXECUCAO, "NumIdentcTit", new String[] { "DIS" }, 
            new String[] { "ADDA106", "ADDA101RR2", "ADDA102RR2", "ADDA104RR2", "ADDA127" }, QTD_MAX_REGISTROS_POR_STEP),
    BEN("BENEFICIARIO", MOTOR_RECEBIMENTO_ARQUIVO_TIPO_BEN_EM_EXECUCAO, MOTOR_ABERTURA_ARQUIVO_TIPO_BEN_EM_EXECUCAO, "CNPJ_CPFBenfcrio", new String[] { "DIS" }, 
            new String[] { "ADDA504" }, QTD_MAX_REGISTROS_POR_STEP),
    BAX("BAIXA", MOTOR_RECEBIMENTO_ARQUIVO_TIPO_BAX_EM_EXECUCAO, MOTOR_ABERTURA_ARQUIVO_TIPO_BAX_EM_EXECUCAO, "NumIdentcTit", new String[] { "DIS" }, 
            new String[] { "ADDA117", "ADDA118RR2", "ADDA120", "ADDA108RR2","ADDA114RR2", "ADDA115RR2" }, QTD_MAX_REGISTROS_POR_STEP),
    TER("TERCEIRO", MOTOR_RECEBIMENTO_ARQUIVO_TIPO_TER_EM_EXECUCAO, MOTOR_ABERTURA_ARQUIVO_TIPO_TER_EM_EXECUCAO, "NumIdentcTit", new String[] { "DIS" }, 
            new String[] { "ADDA121RR2", "ADDA121RR3", "ADDA122RR2" }, QTD_MAX_REGISTROS_POR_STEP);
    /* @formatter:on */

    private enum TipoGrupoEnum {
        ADDA000(""),
        ADDA001RET(""),
        ADDA002("$d/ArqRecebimento/Grupo_ADDA002_Pagdr"),
        ADDA003("$d/ArqRecebimento/Grupo_ADDA003_PagdrDDA"),
        ADDA005RET(""),
        ADDA006RET(""),
        ADDA106("$d/ArqRecebimento/Grupo_ADDA106_Tit"),
        ADDA101RR2("$d/ArqRecebimento/Grupo_ADDA101RR2_Tit"),
        ADDA101RET(""),
        ADDA102RET(""),
        ADDA102RR2("$d/ArqRecebimento/Grupo_ADDA102RR2_Tit"),
        ADDA104RR2("$d/ArqRecebimento/Grupo_ADDA104RR2_Tit"),
        ADDA108RR2("$d/ArqRecebimento/Grupo_ADDA108RR2_Tit"),
        ADDA110RET(""),
        ADDA114RET(""),
        ADDA114RR2("$d/ArqRecebimento/Grupo_ADDA114RR2_Tit"),
        ADDA115RR2("$d/ArqRecebimento/Grupo_ADDA115RR2_Tit"),
        ADDA504("$d/ArqRecebimento/Grupo_ADDA504_Benfcrio"),
        ADDA117("$d/ArqRecebimento/Grupo_ADDA117_Tit"),
        ADDA118RET(""),
        ADDA118RR2("$d/ArqRecebimento/Grupo_ADDA118RR2_Tit"),
        ADDA120("$d/ArqRecebimento/Grupo_ADDA120_Tit"),
        ADDA121RR2("$d/ArqRecebimento/Grupo_ADDA121RR2_Tit"),
        ADDA121RR3("$d/ArqRecebimento/Grupo_ADDA121RR3_Tit"),
        ADDA122RET(""),
        ADDA122RR2("$d/ArqRecebimento/Grupo_ADDA122RR2_Tit"),
        ADDA127("$d/ArqRecebimento/Grupo_ADDA127_Tit");

        private String grupoTagMensagem;

        private TipoGrupoEnum(String grupoTag) {
            this.grupoTagMensagem = grupoTag;
        }

        public String getGrupoTagMensagem() {
            return this.grupoTagMensagem;
        }

    }

    private String descricao = null;
    private String[] tiposArquivo = null;
    private String[] tiposDeMensagens = null;
    private long idParametroMotorRecebimento;
    private long idParametroMotorAbertura;
    private String tagXml;
    private Long idParamRegistrosPorStep;

    /**
     * @param prDescricao
     * @param idParametroMotorRecebimento
     * @param idParametroMotorAbertura
     * @param tag
     * @param tipoArq
     * @param listaTiposMensagens
     * @param idParamRegistrosPorStep
     */
    private TipoInstanciaProcessamentoEnum(String prDescricao, long idParametroMotorRecebimento, long idParametroMotorAbertura, String tag, String[] tipoArq,
            String[] listaTiposMensagens, Long idParamRegistrosPorStep) {
        this.descricao = prDescricao;
        this.idParametroMotorRecebimento = idParametroMotorRecebimento;
        this.idParametroMotorAbertura = idParametroMotorAbertura;
        this.tiposArquivo = tipoArq.clone();
        this.tiposDeMensagens = listaTiposMensagens.clone();
        this.tagXml = tag;
        this.idParamRegistrosPorStep = idParamRegistrosPorStep;
    }

    /**
     * @return the tagXml
     */
    public String getTagXml() {
        return tagXml;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String[] getTiposArquivo() {
        return this.tiposArquivo;
    }

    public String[] getTiposDeMensagens() {
        return this.tiposDeMensagens;
    }

    public String getTipoGrupo(String codMensagem) {
        return TipoGrupoEnum.valueOf(codMensagem).getGrupoTagMensagem();
    }

    public static TipoInstanciaProcessamentoEnum getTipoInstancia(String tipoMensagem) {

        for (TipoInstanciaProcessamentoEnum instancia : TipoInstanciaProcessamentoEnum.values()) {

            for (String mensagem : instancia.getTiposDeMensagens()) {

                if (tipoMensagem.equals(mensagem)) {
                    return instancia;
                }
            }
        }

        return null;

    }

    public long getIdParametroMotorRecebimento() {
        return idParametroMotorRecebimento;
    }

    public long getIdParametroMotorAbertura() {
        return idParametroMotorAbertura;
    }

    public long getIdParamRegistrosPorStep() {
        return idParamRegistrosPorStep;
    }

}
