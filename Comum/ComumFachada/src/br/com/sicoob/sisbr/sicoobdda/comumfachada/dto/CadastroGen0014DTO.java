package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * CadastroGen0014DTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroGen0014Dto")
public class CadastroGen0014DTO extends BancoobDTO {

    private String codTipoSolBeneficiario;
    private String codSituacaoBeneficiario;
    private String codTipoSolPagador;
    private String codTipoSolBoleto;
    private String codTipoBoletoConsultado;

    /**
     * @return the codTipoSolBeneficiario
     */
    public String getCodTipoSolBeneficiario() {
        return codTipoSolBeneficiario;
    }

    /**
     * @param codTipoSolBeneficiario the codTipoSolBeneficiario to set
     */
    public void setCodTipoSolBeneficiario(String codTipoSolBeneficiario) {
        this.codTipoSolBeneficiario = codTipoSolBeneficiario;
    }

    /**
     * @return the codSituacaoBeneficiario
     */
    public String getCodSituacaoBeneficiario() {
        return codSituacaoBeneficiario;
    }

    /**
     * @param codSituacaoBeneficiario the codSituacaoBeneficiario to set
     */
    public void setCodSituacaoBeneficiario(String codSituacaoBeneficiario) {
        this.codSituacaoBeneficiario = codSituacaoBeneficiario;
    }

    /**
     * @return the codTipoSolPagador
     */
    public String getCodTipoSolPagador() {
        return codTipoSolPagador;
    }

    /**
     * @param codTipoSolPagador the codTipoSolPagador to set
     */
    public void setCodTipoSolPagador(String codTipoSolPagador) {
        this.codTipoSolPagador = codTipoSolPagador;
    }

    /**
     * @return the codTipoSolBoleto
     */
    public String getCodTipoSolBoleto() {
        return codTipoSolBoleto;
    }

    /**
     * @param codTipoSolBoleto the codTipoSolBoleto to set
     */
    public void setCodTipoSolBoleto(String codTipoSolBoleto) {
        this.codTipoSolBoleto = codTipoSolBoleto;
    }

    /**
     * @return the codTipoBoletoConsultado
     */
    public String getCodTipoBoletoConsultado() {
        return codTipoBoletoConsultado;
    }

    /**
     * @param codTipoBoletoConsultado the codTipoBoletoConsultado to set
     */
    public void setCodTipoBoletoConsultado(String codTipoBoletoConsultado) {
        this.codTipoBoletoConsultado = codTipoBoletoConsultado;
    }


}
