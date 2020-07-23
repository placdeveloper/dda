/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         BeneficiariosAlertaFiltroDto.java
 * Data Criação:    Feb 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.BancoCafDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * BeneficiariosAlertaFiltroDto é responsável por
 * 
 * @author Danilo.Barros
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.BeneficiariosAlertaFiltroDTO")
public class BeneficiariosAlertaFiltroDto extends BancoobDto {
    public static final String PARAM_BANCO_ORIGINADOR = "descBancoOriginador";
    private static final long serialVersionUID = 1L;
    public static final String PARAM_COD_ISPB = "numCodISPB";
    public static final String PARAM_TIPO_PESSOA = "codTipoPessoa";
    public static final String PARAM_STATUS_ALERTA = "codStatusAlerta";
    public static final String PARAM_NUM_CPFCNPJ = "cPF_CNPJ";
    public static final String PARAM_BENEFICIARIOS_SICOOB = "beneficiariosSicoob";
    private BancoCafDto bancoCaf;
    private String codTipoPessoa;
    private String codStatusAlerta;
    private String cPF_CNPJ;
    private Boolean isBeneficiariosSicoob;

    /**
     * 
     */
    public BeneficiariosAlertaFiltroDto() {
    }

    /**
     * @return o atributo bancoCaf
     */
    public BancoCafDto getBancoCaf() {
        return bancoCaf;
    }

    /**
     * Define o atributo bancoCaf
     */
    public void setBancoCaf(BancoCafDto bancoCaf) {
        this.bancoCaf = bancoCaf;
    }

    /**
     * @return o atributo codTipoPessoa
     */
    public String getCodTipoPessoa() {
        return codTipoPessoa;
    }

    /**
     * Define o atributo codTipoPessoa
     */
    public void setCodTipoPessoa(String codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

    /**
     * @return o atributo codStatusAlerta
     */
    public String getCodStatusAlerta() {
        return codStatusAlerta;
    }

    /**
     * Define o atributo codStatusAlerta
     */
    public void setCodStatusAlerta(String codStatusAlerta) {
        this.codStatusAlerta = codStatusAlerta;
    }

    /**
     * @return o atributo cPF_CNPJ
     */
    public String getcPF_CNPJ() {
        return cPF_CNPJ;
    }

    /**
     * Define o atributo cPF_CNPJ
     */
    public void setcPF_CNPJ(String cPF_CNPJ) {
        this.cPF_CNPJ = cPF_CNPJ;
    }

    /**
     * @return o atributo isBeneficiariosSicoob
     */
    public Boolean getIsBeneficiariosSicoob() {
        return isBeneficiariosSicoob;
    }

    /**
     * Define o atributo isBeneficiariosSicoob
     */
    public void setIsBeneficiariosSicoob(Boolean isBeneficiariosSicoob) {
        this.isBeneficiariosSicoob = isBeneficiariosSicoob;
    }

}
