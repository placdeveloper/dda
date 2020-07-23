package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MensagemDDAConsultaBoletoVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto")
public class MensagemDDAConsultaBoletoVO extends BancoobVO {

    private Long id;
    private MensagemDDAVO mensagemDDA;
    private String numCodigoBarra;
    private String numCpfCnpjPagador;
    private Short codSituacaoBoleto;
    private Long numIdentBoletoInicial;
    private Long numIdentBoletoFinal;
    private String codTipoBoletoConsultado;

    private String numIdentBoletoInicialStr;
    private String numIdentBoletoFinalStr;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the mensagemDDA
     */
    public MensagemDDAVO getMensagemDDA() {
        return mensagemDDA;
    }

    /**
     * @param mensagemDDA the mensagemDDA to set
     */
    public void setMensagemDDA(MensagemDDAVO mensagemDDA) {
        this.mensagemDDA = mensagemDDA;
    }

    /**
     * @return the numCodigoBarra
     */
    public String getNumCodigoBarra() {
        return numCodigoBarra;
    }

    /**
     * @param numCodigoBarra the numCodigoBarra to set
     */
    public void setNumCodigoBarra(String numCodigoBarra) {
        this.numCodigoBarra = numCodigoBarra;
    }

    /**
     * @return the numCpfCnpjPagador
     */
    public String getNumCpfCnpjPagador() {
        return numCpfCnpjPagador;
    }

    /**
     * @param numCpfCnpjPagador the numCpfCnpjPagador to set
     */
    public void setNumCpfCnpjPagador(String numCpfCnpjPagador) {
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

    /**
     * @return o atributo codSituacaoBoleto
     */
    public Short getCodSituacaoBoleto() {
        return codSituacaoBoleto;
    }

    /**
     * Define o atributo codSituacaoBoleto
     */
    public void setCodSituacaoBoleto(Short codSituacaoBoleto) {
        this.codSituacaoBoleto = codSituacaoBoleto;
    }

    /**
     * @return o atributo numIdentBoletoInicial
     */
    public Long getNumIdentBoletoInicial() {
        return numIdentBoletoInicial;
    }

    /**
     * Define o atributo numIdentBoletoInicial
     */
    public void setNumIdentBoletoInicial(Long numIdentBoletoInicial) {
        this.numIdentBoletoInicial = numIdentBoletoInicial;
    }

    /**
     * @return o atributo numIdentBoletoFinal
     */
    public Long getNumIdentBoletoFinal() {
        return numIdentBoletoFinal;
    }

    /**
     * Define o atributo numIdentBoletoFinal
     */
    public void setNumIdentBoletoFinal(Long numIdentBoletoFinal) {
        this.numIdentBoletoFinal = numIdentBoletoFinal;
    }

    /**
     * @return o atributo codTipoBoletoConsultado
     */
    public String getCodTipoBoletoConsultado() {
        return codTipoBoletoConsultado;
    }

    /**
     * Define o atributo codTipoBoletoConsultado
     */
    public void setCodTipoBoletoConsultado(String codTipoBoletoConsultado) {
        this.codTipoBoletoConsultado = codTipoBoletoConsultado;
    }

    /**
     * @return o atributo numIdentBoletoInicialStr
     */
    public String getNumIdentBoletoInicialStr() {
        return numIdentBoletoInicialStr;
    }

    /**
     * Define o atributo numIdentBoletoInicialStr
     */
    public void setNumIdentBoletoInicialStr(String numIdentBoletoInicialStr) {
        this.numIdentBoletoInicialStr = numIdentBoletoInicialStr;
    }

    /**
     * @return o atributo numIdentBoletoFinalStr
     */
    public String getNumIdentBoletoFinalStr() {
        return numIdentBoletoFinalStr;
    }

    /**
     * Define o atributo numIdentBoletoFinalStr
     */
    public void setNumIdentBoletoFinalStr(String numIdentBoletoFinalStr) {
        this.numIdentBoletoFinalStr = numIdentBoletoFinalStr;
    }

}
