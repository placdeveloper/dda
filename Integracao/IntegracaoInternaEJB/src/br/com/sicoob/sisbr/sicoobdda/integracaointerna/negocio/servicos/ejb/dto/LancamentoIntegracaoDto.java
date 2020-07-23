/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto
 * Arquivo:         LancamentoIntegracaoDto.java
 * Data Criação:    Nov 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.util.DataUtil;

/**
 * LancamentoIntegracaoDto é responsável por
 * 
 * @author samuell.ramos
 */
public class LancamentoIntegracaoDto extends ContaCorrenteApiDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String[] CAMPOS = new String[] { "idAplicativo", "aplicativoEnum", "idInstituicao", "dataLote", "numLoteLanc", "descNumDocumento", "numContaCorrente",
            "valorLanc", "idProduto", "idTipoHistoricoLanc", "idProdutoEstorno", "idTipoHistoricoEstorno", "idUsuarioResp", "bolVerificaSaldo", "codOrigemLote", "numSeqLanc",
            "bolVerificaContaAnt", "bolConsideraLimite", "descInfComplementar", "bolConsSaldoResgAuto", "idInstituicaoTransferenciaDebito"};

    private Integer idInstituicao;
    private Date dataLote;
    private Integer numLoteLanc;
    private String descNumDocumento;
    private Long numContaCorrente;
    private BigDecimal valorLanc;
    private Integer idProduto;
    private Integer idTipoHistoricoLanc;
    private Integer idProdutoEstorno;
    private Integer idTipoHistoricoEstorno;
    private String idUsuarioResp;
    private Boolean bolVerificaSaldo;
    private Integer codOrigemLote;
    private Integer numSeqLanc;
    private Boolean bolVerificaContaAnt;
    private Boolean bolConsideraLimite;
    private String descInfComplementar;
    private Boolean bolConsSaldoResgAuto = false;
    private Integer idInstituicaoTransferenciaDebito;

    /**
     * 
     */
    public LancamentoIntegracaoDto() {
        super();
    }

    /**
     * @param dataLote
     * @param numLoteLanc
     * @param descNumDocumento
     * @param numContaCorrente
     * @param valorLanc
     * @param idTipoHistoricoLanc
     * @param idUsuarioResp
     * @param numLoteLanc
     * @param idRateioDDALancamento
     */
    public LancamentoIntegracaoDto(Date dataMovimentoLoteLancamentoCCO, Integer numLoteLanc, String descNumDocumento, Long numContaCorrente, BigDecimal valorLanc,
            Long idTipoHistoricoLanc, String idUsuarioResp, Long idRateioDDALancamento) {
        super();
        this.dataLote = dataMovimentoLoteLancamentoCCO;
        this.numLoteLanc = numLoteLanc;
        this.descNumDocumento = descNumDocumento + String.valueOf(idRateioDDALancamento);
        this.numContaCorrente = numContaCorrente;
        this.valorLanc = valorLanc;
        this.idTipoHistoricoLanc = idTipoHistoricoLanc.intValue();
        this.idUsuarioResp = idUsuarioResp;
        this.numSeqLanc = idRateioDDALancamento.intValue();
    }

    public Date getDataLote() {
        if (dataLote != null) {
            return new Date(dataLote.getTime());
        }
        return null;
    }

    public void setDataLote(Date dataLote) {
        if (dataLote != null) {
            this.dataLote = new Date(dataLote.getTime());
        }
    }

    public String getDataLoteDB() {
        return DataUtil.converterDateToString(dataLote, "yyyy-MM-dd");
    }

    public Integer getNumLoteLanc() {
        return numLoteLanc;
    }

    public void setNumLoteLanc(Integer numLoteLanc) {
        this.numLoteLanc = numLoteLanc;
    }

    public String getDescNumDocumento() {
        return descNumDocumento;
    }

    public void setDescNumDocumento(String descNumDocumento) {
        this.descNumDocumento = descNumDocumento;
    }

    public Long getNumContaCorrente() {
        return numContaCorrente;
    }

    public void setNumContaCorrente(Long numContaCorrente) {
        this.numContaCorrente = numContaCorrente;
    }

    public BigDecimal getValorLanc() {
        return valorLanc;
    }

    public void setValorLanc(BigDecimal valorLanc) {
        this.valorLanc = valorLanc;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdTipoHistoricoLanc() {
        return idTipoHistoricoLanc;
    }

    public void setIdTipoHistoricoLanc(Integer idTipoHistoricoLanc) {
        this.idTipoHistoricoLanc = idTipoHistoricoLanc;
    }

    public Integer getIdProdutoEstorno() {
        return idProdutoEstorno;
    }

    public void setIdProdutoEstorno(Integer idProdutoEstorno) {
        this.idProdutoEstorno = idProdutoEstorno;
    }

    public Integer getIdTipoHistoricoEstorno() {
        return idTipoHistoricoEstorno;
    }

    public void setIdTipoHistoricoEstorno(Integer idTipoHistoricoEstorno) {
        this.idTipoHistoricoEstorno = idTipoHistoricoEstorno;
    }

    public String getIdUsuarioResp() {
        return idUsuarioResp;
    }

    public void setIdUsuarioResp(String idUsuarioResp) {
        this.idUsuarioResp = idUsuarioResp;
    }

    public Boolean getBolVerificaSaldo() {
        return bolVerificaSaldo;
    }

    public void setBolVerificaSaldo(Boolean bolVerificaSaldo) {
        this.bolVerificaSaldo = bolVerificaSaldo;
    }

    public Integer getBolVerificaSaldoDB() {
        return bolVerificaSaldo ? 1 : 0;
    }

    public Integer getCodOrigemLote() {
        return codOrigemLote;
    }

    public void setCodOrigemLote(Integer codOrigemLote) {
        this.codOrigemLote = codOrigemLote;
    }

    public Integer getNumSeqLanc() {
        return numSeqLanc;
    }

    public void setNumSeqLanc(Integer numSeqLanc) {
        this.numSeqLanc = numSeqLanc;
    }

    public Boolean getBolVerificaContaAnt() {
        return bolVerificaContaAnt;
    }

    public void setBolVerificaContaAnt(Boolean bolVerificaContaAnt) {
        this.bolVerificaContaAnt = bolVerificaContaAnt;
    }

    public Integer getBolVerificaContaAntDB() {
        return bolVerificaContaAnt ? 1 : 0;
    }

    public Boolean getBolConsideraLimite() {
        return bolConsideraLimite;
    }

    public void setBolConsideraLimite(Boolean bolConsideraLimite) {
        this.bolConsideraLimite = bolConsideraLimite;
    }

    public Integer getBolConsideraLimiteDB() {
        return bolConsideraLimite ? 1 : 0;
    }

    public String getDescInfComplementar() {
        return descInfComplementar;
    }

    public void setDescInfComplementar(String descInfComplementar) {
        this.descInfComplementar = descInfComplementar;
    }

    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public Boolean getBolConsSaldoResgAuto() {
        return bolConsSaldoResgAuto;
    }

    public void setBolConsSaldoResgAuto(Boolean bolConsSaldoResgAuto) {
        this.bolConsSaldoResgAuto = bolConsSaldoResgAuto;
    }

	/**
	 * @return the idInstituicaoTransferenciaDebito
	 */
	public Integer getIdInstituicaoTransferenciaDebito() {
		return idInstituicaoTransferenciaDebito;
	}

	/**
	 * @param idInstituicaoTransferenciaDebito the idInstituicaoTransferenciaDebito to set
	 */
	public void setIdInstituicaoTransferenciaDebito(
			Integer idInstituicaoTransferenciaDebito) {
		this.idInstituicaoTransferenciaDebito = idInstituicaoTransferenciaDebito;
	}
    
}
