/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         BeneficiariosAlertaFiltroDTO.java
 * Data Criação:    Feb 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.io.Serializable;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * BeneficiariosAlertaFiltroDTO é responsável por
 * 
 * @author Danilo.Barros
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto")
public class BeneficiariosAlertaFiltroDTO extends BancoobDTO  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FILTRO_BANCO_ORIGINADOR = "descBancoOriginador";
    public static final String FILTRO_TIPO_PESSOA = "codTipoPessoa";
    public static final String FILTRO_STATUS_ALERTA = "codStatusAlerta";
    public static final String FILTRO_BENEFICIARIO = "cPF_CNPJ";
    private BancoCafDTO bancoCaf;
    private String codTipoPessoa;
    private String codStatusAlerta;
    private String cPF_CNPJ;
    private Boolean isBeneficiariosSicoob;

    /**
     * 
     */
    public BeneficiariosAlertaFiltroDTO() {
    }

    /**
     * @return o atributo bancoCaf
     */
    public BancoCafDTO getBancoCaf() {
        return bancoCaf;
    }

    /**
     * Define o atributo bancoCaf
     */
    public void setBancoCaf(BancoCafDTO bancoCaf) {
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
