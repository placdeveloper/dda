/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         DDACatalogoMensagem.java
 * Data Criação:    Jul 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoMensagemDDA
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "TIPOMENSAGEMDDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoMensagemDDAVO")
public class TipoMensagemDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = -7451206216292302599L;

    // Pagador - 000
    public static final String DDA0001 = "DDA0001";
    public static final String DDA0001R1 = "DDA0001R1";
    public static final String ADDA001 = "ADDA001";
    public static final String ADDA001RET = "ADDA001RET";
    public static final String ADDA001E = "ADDA001E";

    public static final String DDA0002 = "DDA0002";
    public static final String DDA0002R1 = "DDA0002R1";
    public static final String ADDA002 = "ADDA002";

    public static final String ADDA003 = "ADDA003";

    public static final String DDA0005 = "DDA0005";
    public static final String DDA0005R1 = "DDA0005R1";
    public static final String ADDA005 = "ADDA005";
    public static final String ADDA005RET = "ADDA005RET";

    public static final String DDA0006 = "DDA0006";
    public static final String DDA0006R1 = "DDA0006R1";
    public static final String ADDA006 = "ADDA006";
    public static final String ADDA006RET = "ADDA006RET";

    // Boleto - 100
    public static final String DDA0101 = "DDA0101";
    public static final String DDA0101R1 = "DDA0101R1";
    public static final String DDA0101R2 = "DDA0101R2";
    public static final String ADDA101 = "ADDA101";
    public static final String ADDA101RET = "ADDA101RET";
    public static final String ADDA101RR2 = "ADDA101RR2";

    public static final String DDA0102 = "DDA0102";
    public static final String DDA0102R1 = "DDA0102R1";
    public static final String DDA0102R2 = "DDA0102R2";
    public static final String ADDA102 = "ADDA102";
    public static final String ADDA102RET = "ADDA102RET";
    public static final String ADDA102RR2 = "ADDA102RR2";

    public static final String DDA0104 = "DDA0104";
    public static final String DDA0104R1 = "DDA0104R1";
    public static final String DDA0104R2 = "DDA0104R2";
    public static final String ADDA104RET = "ADDA104RET";
    public static final String ADDA104RR2 = "ADDA104RR2";

    public static final String DDA0106 = "DDA0106";
    public static final String DDA0106R1 = "DDA0106R1";
    public static final String ADDA106 = "ADDA106";

    public static final String DDA0108 = "DDA0108";
    public static final String DDA0108R1 = "DDA0108R1";
    public static final String DDA0108R2 = "DDA0108R2";
    public static final String ADDA108 = "ADDA108";
    public static final String ADDA108RET = "ADDA108RET";
    public static final String ADDA108RR2 = "ADDA108RR2";

    public static final String DDA0110 = "DDA0110";
    public static final String DDA0110R1 = "DDA0110R1";
    public static final String DDA0110E = "DDA0110E";
    public static final String ADDA110 = "ADDA110";
    public static final String ADDA110RET = "ADDA110RET";

    public static final String ADDA114 = "ADDA114";
    public static final String ADDA114RET = "ADDA114RET";
    public static final String ADDA114RR2 = "ADDA114RR2";

    public static final String DDA0115 = "DDA0115";
    public static final String DDA0115R1 = "DDA0115R1";
    public static final String DDA0115R2 = "DDA0115R2";
    public static final String DDA0115E = "DDA0115E";
    public static final String ADDA115RET = "ADDA115RET";
    public static final String ADDA115RR2 = "ADDA115RR2";

    public static final String ADDA117 = "ADDA117";
    public static final String ADDA117RET = "ADDA117RET";

    public static final String DDA0118 = "DDA0118";
    public static final String DDA0118R1 = "DDA0118R1";
    public static final String DDA0118R2 = "DDA0118R2";
    public static final String ADDA118 = "ADDA118";
    public static final String ADDA118RET = "ADDA118RET";
    public static final String ADDA118RR2 = "ADDA118RR2";

    public static final String ADDA120 = "ADDA120";

    public static final String DDA0121 = "DDA0121";
    public static final String DDA0121R1 = "DDA0121R1";
    public static final String DDA0121R2 = "DDA0121R2";
    public static final String DDA0121R3 = "DDA0121R3";
    public static final String ADDA121RET = "ADDA121RET";
    public static final String ADDA121RR2 = "ADDA121RR2";
    public static final String ADDA121RR3 = "ADDA121RR3";

    public static final String DDA0122 = "DDA0122";
    public static final String DDA0122R1 = "DDA0122R1";
    public static final String DDA0122R2 = "DDA0122R2";
    public static final String ADDA122 = "ADDA122";
    public static final String ADDA122RET = "ADDA122RET";
    public static final String ADDA122RR2 = "ADDA122RR2";

    public static final String DDA0127 = "DDA0127";
    public static final String ADDA127 = "ADDA127";

    // Extratos - 200
    public static final String DDA0200 = "DDA0200";
    public static final String ADDA200 = "ADDA200";
    public static final String DDA0200R1 = "DDA0200R1";
    public static final String DDA0200E = "DDA0200E";

    public static final String DDA0214 = "DDA0214";
    public static final String DDA0214R1 = "DDA0214R1";

    public static final String DDA0215 = "DDA0215";
    public static final String DDA0215R1 = "DDA0215R1";

    // Grade - 400
    public static final String DDA0401 = "DDA0401";
    public static final String DDA0401R1 = "DDA0401R1";
    public static final String DDA0402 = "DDA0402";
    public static final String DDA0403 = "DDA0403";
    public static final String DDA0404 = "DDA0404";

    // Beneficiario - 500
    public static final String DDA0501 = "DDA0501";
    public static final String DDA0501E = "DDA0501E";
    public static final String DDA0501R1 = "DDA0501R1";

    public static final String DDA0502 = "DDA0502";
    public static final String DDA0502E = "DDA0502E";
    public static final String DDA0502R1 = "DDA0502R1";

    public static final String DDA0503 = "DDA0503";
    public static final String DDA0503E = "DDA0503E";
    public static final String DDA0503R1 = "DDA0503R1";

    public static final String DDA0504 = "DDA0504";
    public static final String DDA0504E = "DDA0504E";
    public static final String DDA0504R1 = "DDA0504R1";
    public static final String ADDA504 = "ADDA504";

    public static final String DDA0505 = "DDA0505";
    public static final String DDA0505E = "DDA0505E";
    public static final String DDA0505R1 = "DDA0505R1";

    public static final String DDA0506 = "DDA0506";

    // Generico - GEN
    public static final String GEN0014 = "GEN0014";
    public static final String GEN0014E = "GEN0014E";
    public static final String GEN0014R1 = "GEN0014R1";
    public static final String GEN0015 = "GEN0015";
    public static final String GEN0004 = "GEN0004";

    // Erro Personalizado - GEN
    public static final String ERRO001 = "ERRO001";
    public static final String ERRO001RET = "ERRO001RET";
    public static final String AGEN001 = "AGEN001";

    public TipoMensagemDDA() {
    }

    /**
     * @param codTipoMsg
     */
    public TipoMensagemDDA(String codTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
    }
    
    /**
     * @param codTipoMensagem
     * @param descTipoMensagem
     */
    public TipoMensagemDDA(String codTipoMensagem,String descTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
        this.descTipoMensagem = descTipoMensagem;
    }

    @Id
    @Column(name = "CODTIPOMENSAGEMDDA", unique = true, nullable = false)
    private String codTipoMensagem;

    @Column(name = "DESCTIPOMENSAGEMDDA", nullable = false)
    private String descTipoMensagem;

    @Column(nullable = false)
    private Integer numPrioridadeEnvio;

    @ManyToOne
    @JoinColumn(name = "CODCATEGORIAMENSAGEMDDA", nullable = false)
    private CategoriaMensagemDDA categoriaMensagemDDA;

    @Column(nullable = false)
    private Boolean bolExigeMensagemRetorno;

    @ManyToOne
    @JoinColumn(name = "CODTIPOGRADEHORARIA")
    private TipoGradeHoraria tipoGradeHoraria;

    private String codTipoArquivoCorrespondente;

    @OneToMany(mappedBy = "tipoMensagemDDA")
    private List<MensagemDDA> listaMensagensDDA;

    /**
     * @return o atributo codTipoMensagem
     */
    public String getCodTipoMensagem() {
        return codTipoMensagem;
    }

    /**
     * Define o atributo codTipoMensagem
     */
    public void setCodTipoMensagem(String codTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
    }

    /**
     * @return o atributo descTipoMensagem
     */
    public String getDescTipoMensagem() {
        return descTipoMensagem;
    }

    /**
     * Define o atributo descTipoMensagem
     */
    public void setDescTipoMensagem(String descTipoMensagem) {
        this.descTipoMensagem = descTipoMensagem;
    }

    /**
     * @return o atributo numPrioridadeEnvio
     */
    public Integer getNumPrioridadeEnvio() {
        return numPrioridadeEnvio;
    }

    /**
     * Define o atributo numPrioridadeEnvio
     */
    public void setNumPrioridadeEnvio(Integer numPrioridadeEnvio) {
        this.numPrioridadeEnvio = numPrioridadeEnvio;
    }

    /**
     * @return o atributo categoriaMensagemDDA
     */
    public CategoriaMensagemDDA getCategoriaMensagemDDA() {
        return categoriaMensagemDDA;
    }

    /**
     * Define o atributo categoriaMensagemDDA
     */
    public void setCategoriaMensagemDDA(CategoriaMensagemDDA categoriaMensagemDDA) {
        this.categoriaMensagemDDA = categoriaMensagemDDA;
    }

    /**
     * @return o atributo bolExigeMensagemRetorno
     */
    public Boolean getBolExigeMensagemRetorno() {
        return bolExigeMensagemRetorno;
    }

    /**
     * Define o atributo bolExigeMensagemRetorno
     */
    public void setBolExigeMensagemRetorno(Boolean bolExigeMensagemRetorno) {
        this.bolExigeMensagemRetorno = bolExigeMensagemRetorno;
    }

    /**
     * @return o atributo tipoGradeHoraria
     */
    public TipoGradeHoraria getTipoGradeHoraria() {
        return tipoGradeHoraria;
    }

    /**
     * Define o atributo tipoGradeHoraria
     */
    public void setTipoGradeHoraria(TipoGradeHoraria tipoGradeHoraria) {
        this.tipoGradeHoraria = tipoGradeHoraria;
    }

    /**
     * @return the listaMensagensDDA
     */
    public List<MensagemDDA> getListaMensagensDDA() {
        return listaMensagensDDA;
    }

    /**
     * @param listaMensagensDDA the listaMensagensDDA to set
     */
    public void setListaMensagensDDA(List<MensagemDDA> listaMensagensDDA) {
        this.listaMensagensDDA = listaMensagensDDA;
    }

    /**
     * @return the codTipoArquivoCorrespondente
     */
    public String getCodTipoArquivoCorrespondente() {
        return codTipoArquivoCorrespondente;
    }

    /**
     * @param codTipoArquivoCorrespondente the codTipoArquivoCorrespondente to set
     */
    public void setCodTipoArquivoCorrespondente(String codTipoArquivoCorrespondente) {
        this.codTipoArquivoCorrespondente = codTipoArquivoCorrespondente;
    }

    /**
     * Método responsável por verificar se o tipo de mensagem passada é uma mensagem de baixa operacional. Utilizado para encontrar o serviço de processamento
     * de envio e retorno de mensagens de baixa operacional
     * 
     * @param codMsg
     * @return Boolean
     * 
     */
    public static Boolean isMsgBxOperacional(String codMsg) {
        return codMsg.equals(DDA0108) || codMsg.equals(DDA0108R1) || codMsg.equals(DDA0108R2) || codMsg.equals(ADDA108RET) || codMsg.equals(ADDA108RR2)
                || codMsg.equals(ADDA114RR2);
    }

    /**
     * @param codMsg
     * @return Boolean
     * 
     */
    public static Boolean isArquivoBxOperacional(String codMsg) {
        return codMsg.equals(ADDA108) || codMsg.equals(ADDA108RET) || codMsg.equals(ADDA108RR2) || codMsg.equals(ADDA114RET) || codMsg.equals(ADDA114RR2);
    }

    /**
     * Método responsável por verificar se o tipo de mensagem passada é uma mensagem de baixa efetiva. Utilizado para encontrar o serviço de processamento de
     * envio e retorno de mensagens de baixa efetiva
     * 
     * @param codMsg
     * @return Boolean
     * 
     */
    public static Boolean isMsgBxEfetiva(String codMsg) {
        return codMsg.equals(DDA0118) || codMsg.equals(DDA0118R1) || codMsg.equals(DDA0118R2) || codMsg.equals(ADDA118RET) || codMsg.equals(ADDA118RR2) || codMsg.equals(ADDA120);
    }

    /**
     * @param codMsg
     * @return Boolean
     * 
     */
    public static Boolean isArquivoBxEfetiva(String codMsg) {
        return codMsg.equals(ADDA118) || codMsg.equals(ADDA118RET) || codMsg.equals(ADDA118RR2) || codMsg.equals(ADDA120);
    }

    /**
     * Método responsável por
     * 
     * @param codMsg
     * @return Boolean
     * 
     */
    public static Boolean isMsgConsultaBoleto(String codMsg) {
        return codMsg.equals(DDA0106) || codMsg.equals(DDA0106R1) || codMsg.equals(ADDA106);
    }

    /**
     * Método responsável por verificar se o tipo de mensagem passada é uma mensagem de inclusao boleto. Utilizado para encontrar o serviço de processamento de
     * envio e retorno de mensagens de inclusao boleto
     * 
     * @param codMsg
     * @return Boolean
     * 
     */
    public static Boolean isMsgInclusaoBoleto(String codMsg) {
        return codMsg.equals(DDA0101) || codMsg.equals(DDA0101R1) || codMsg.equals(DDA0101R2) || codMsg.equals(ADDA101RET) || codMsg.equals(ADDA101RR2);
    }

    /**
     * Método responsável por verificar se o tipo de mensagem passada é uma mensagem de alteracao boleto. Utilizado para encontrar o serviço de processamento de
     * envio e retorno de mensagens de alteracao boleto
     * 
     * @param codMsg
     * @return Boolean
     * 
     */
    public static Boolean isMsgAlteracaoBoleto(String codMsg) {
        return codMsg.equals(DDA0102) || codMsg.equals(DDA0102R1) || codMsg.equals(DDA0102R2) || codMsg.equals(ADDA102RET) || codMsg.equals(ADDA102RR2);
    }

    /**
     * Método responsável porse o tipo de mensagem passada é uma mensagem de inclusao terceiro autorizado. Utilizado para encontrar o serviço de processamento
     * de envio e retorno de mensagens de inclusao terceiro autorizado
     * 
     * @param codMsg
     * @return boolean
     * 
     */
    public static boolean isMsgInclusaoTerceiroAutorizado(String codMsg) {
        return codMsg.equals(DDA0121) || codMsg.equals(DDA0121R1) || codMsg.equals(DDA0121R2) || codMsg.equals(DDA0121R3) || codMsg.equals(ADDA121RR2) || codMsg.equals(ADDA121RR3);
    }

    /**
     * Método responsável por verificar se o tipo de mensagem passada é uma mensagem de exclusao terceiro autorizado. Utilizado para encontrar o serviço de
     * processamento de envio e retorno de mensagens de exclusao terceiro autorizado
     * 
     * @param codMsg
     * @return Boolean
     * 
     */
    public static Boolean isMsgExclusaoTerceiroAutorizado(String codMsg) {
        return codMsg.equals(DDA0122) || codMsg.equals(DDA0122R1) || codMsg.equals(DDA0122R2) || codMsg.equals(ADDA122RR2);
    }

    /**
     * Método responsável por
     * 
     * @param codMsg
     * @return Boolean
     * 
     */
    public static Boolean isMsgCancelamentoBxOperacional(String codMsg) {
        return codMsg.equals(DDA0115) || codMsg.equals(DDA0115R1) || codMsg.equals(DDA0115R2) || codMsg.equals(ADDA115RET) || codMsg.equals(ADDA115RR2);
    }

    /**
     * Método responsável por retornar se é um tipo de mensagem de alteração de aceite.
     * 
     * @param codTipoMsg
     * @return boolean
     * 
     */
    public static boolean isMsgAlteracaoAceiteBoleto(String codTipoMsg) {
        return codTipoMsg.equals(DDA0104) || codTipoMsg.equals(DDA0104R1) || codTipoMsg.equals(DDA0104R2) || codTipoMsg.equals(ADDA104RET) || codTipoMsg.equals(ADDA104RR2);
    }

    /**
     * Método responsável por verificar se e um beneficiario, utilizado para a tela de tratamento das msgs
     * 
     * @param codTipoMsg
     * @return boolean
     * 
     */
    public static boolean isBeneficiario(String codTipoMsg) {
        return codTipoMsg.equals(DDA0501) || codTipoMsg.equals(DDA0501E) || codTipoMsg.equals(DDA0501R1) || codTipoMsg.equals(DDA0502) || codTipoMsg.equals(DDA0502E)
                || codTipoMsg.equals(DDA0502R1) || codTipoMsg.equals(DDA0503) || codTipoMsg.equals(DDA0503E) || codTipoMsg.equals(DDA0503R1) || codTipoMsg.equals(DDA0504)
                || codTipoMsg.equals(DDA0504E) || codTipoMsg.equals(DDA0504R1) || codTipoMsg.equals(DDA0505) || codTipoMsg.equals(DDA0505E) || codTipoMsg.equals(DDA0505R1)
                || codTipoMsg.equals(DDA0506);
    }

    /**
     * Método responsável por verificar se e um pagador, utilizado para a tela de tratamento das msgs
     * 
     * @param codTipoMsg
     * @return boolean
     * 
     */
    public static boolean isPagador(String codTipoMsg) {
        return codTipoMsg.equals(DDA0001) || codTipoMsg.equals(DDA0001R1) || codTipoMsg.equals(DDA0002) || codTipoMsg.equals(DDA0002R1) || codTipoMsg.equals(DDA0005)
                || codTipoMsg.equals(DDA0005R1) || codTipoMsg.equals(DDA0006) || codTipoMsg.equals(DDA0006R1) || codTipoMsg.equals(ADDA001) || codTipoMsg.equals(ADDA001RET)
                || codTipoMsg.equals(ADDA005) || codTipoMsg.equals(ADDA005RET) || codTipoMsg.equals(ADDA006) || codTipoMsg.equals(ADDA006RET);
    }

    /**
     * Método responsável por verificar se e um pagador, utilizado para a tela de tratamento das msgs
     * 
     * @param codTipoMsg
     * @return boolean
     * 
     */
    public static boolean isArquivoPagador(String codTipoMsg) {
        return codTipoMsg.equals(ADDA001) || codTipoMsg.equals(ADDA001RET) || codTipoMsg.equals(ADDA005) || codTipoMsg.equals(ADDA005RET) || codTipoMsg.equals(ADDA006)
                || codTipoMsg.equals(ADDA006RET);
    }

    /**
     * Método responsável por verificar se e um pagador, utilizado para a tela de tratamento das msgs
     * 
     * @param codTipoMsg
     * @return boolean
     * 
     */
    public static boolean isBoleto(String codTipoMsg) {
        return codTipoMsg.equals(DDA0101) || codTipoMsg.equals(DDA0101R1) || codTipoMsg.equals(DDA0101R2) || codTipoMsg.equals(DDA0102) || codTipoMsg.equals(DDA0102R1)
                || codTipoMsg.equals(DDA0102R2) || codTipoMsg.equals(ADDA101) || codTipoMsg.equals(ADDA101RET) || codTipoMsg.equals(ADDA101RR2) || codTipoMsg.equals(ADDA102)
                || codTipoMsg.equals(ADDA102RET) || codTipoMsg.equals(ADDA102RR2);
    }

    /**
     * @param codTipoMsg
     * @return boolean
     * 
     */
    public static boolean isArquivoBoleto(String codTipoMsg) {
        return codTipoMsg.equals(ADDA101) || codTipoMsg.equals(ADDA101RET) || codTipoMsg.equals(ADDA101RR2) || codTipoMsg.equals(ADDA102) || codTipoMsg.equals(ADDA102RET)
                || codTipoMsg.equals(ADDA102RR2);
    }

    /**
     * @param codTipoMsg
     * @return boolean
     * 
     */
    public static boolean isTerceiroAutorizado(String codTipoMsg) {
        return codTipoMsg.equals(DDA0121) || codTipoMsg.equals(DDA0121R1) || codTipoMsg.equals(DDA0121R2) || codTipoMsg.equals(DDA0121R3) || codTipoMsg.equals(DDA0122)
                || codTipoMsg.equals(DDA0122R1) || codTipoMsg.equals(DDA0122R2) || codTipoMsg.equals(ADDA121RR2) || codTipoMsg.equals(ADDA122RR2);
    }

}
