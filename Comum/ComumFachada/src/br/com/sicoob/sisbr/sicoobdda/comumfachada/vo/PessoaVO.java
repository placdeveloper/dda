package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.Pessoa")
public class PessoaVO extends BancoobVO {

    private Long id;
    private String celFax;
    private Character codCNAE;
    private String codHashAssinatura;
    private String codHashFoto;
    private Long codPFPJ;
    private DateTime dataCadastramentoPessoa;
    private DateTime dataUltimaAtualizacao;
    private String ddd;
    private String descApelidoPessoa;
    private String descEndInternet;
    private String descNomeCompleto;
    private String descNomePessoa;
    private String descObsPessoa;
    private Long idAtividadeEconomica;
    private Long idGrupoPessoa;
    private String numCGCCPF;
    private Long numCoopOrigemPessoa;
    private Long numPacOrigemPessoa;
    private String ramal;

    /**
     * @return o atributo id
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o atributo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return o atributo celFax
     */
    public String getCelFax() {
        return celFax;
    }

    /**
     * Define o atributo celFax
     */
    public void setCelFax(String celFax) {
        this.celFax = celFax;
    }

    /**
     * @return o atributo codCNAE
     */
    public Character getCodCNAE() {
        return codCNAE;
    }

    /**
     * Define o atributo codCNAE
     */
    public void setCodCNAE(Character codCNAE) {
        this.codCNAE = codCNAE;
    }

    /**
     * @return o atributo codHashAssinatura
     */
    public String getCodHashAssinatura() {
        return codHashAssinatura;
    }

    /**
     * Define o atributo codHashAssinatura
     */
    public void setCodHashAssinatura(String codHashAssinatura) {
        this.codHashAssinatura = codHashAssinatura;
    }

    /**
     * @return o atributo codHashFoto
     */
    public String getCodHashFoto() {
        return codHashFoto;
    }

    /**
     * Define o atributo codHashFoto
     */
    public void setCodHashFoto(String codHashFoto) {
        this.codHashFoto = codHashFoto;
    }

    /**
     * @return o atributo codPFPJ
     */
    public Long getCodPFPJ() {
        return codPFPJ;
    }

    /**
     * Define o atributo codPFPJ
     */
    public void setCodPFPJ(Long codPFPJ) {
        this.codPFPJ = codPFPJ;
    }

    /**
     * @return o atributo dataCadastramentoPessoa
     */
    public DateTime getDataCadastramentoPessoa() {
        return dataCadastramentoPessoa;
    }

    /**
     * Define o atributo dataCadastramentoPessoa
     */
    public void setDataCadastramentoPessoa(DateTime dataCadastramentoPessoa) {
        this.dataCadastramentoPessoa = dataCadastramentoPessoa;
    }

    /**
     * @return o atributo dataUltimaAtualizacao
     */
    public DateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    /**
     * Define o atributo dataUltimaAtualizacao
     */
    public void setDataUltimaAtualizacao(DateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    /**
     * @return o atributo ddd
     */
    public String getDdd() {
        return ddd;
    }

    /**
     * Define o atributo ddd
     */
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    /**
     * @return o atributo descApelidoPessoa
     */
    public String getDescApelidoPessoa() {
        return descApelidoPessoa;
    }

    /**
     * Define o atributo descApelidoPessoa
     */
    public void setDescApelidoPessoa(String descApelidoPessoa) {
        this.descApelidoPessoa = descApelidoPessoa;
    }

    /**
     * @return o atributo descEndInternet
     */
    public String getDescEndInternet() {
        return descEndInternet;
    }

    /**
     * Define o atributo descEndInternet
     */
    public void setDescEndInternet(String descEndInternet) {
        this.descEndInternet = descEndInternet;
    }

    /**
     * @return o atributo descNomeCompleto
     */
    public String getDescNomeCompleto() {
        return descNomeCompleto;
    }

    /**
     * Define o atributo descNomeCompleto
     */
    public void setDescNomeCompleto(String descNomeCompleto) {
        this.descNomeCompleto = descNomeCompleto;
    }

    /**
     * @return o atributo descNomePessoa
     */
    public String getDescNomePessoa() {
        return descNomePessoa;
    }

    /**
     * Define o atributo descNomePessoa
     */
    public void setDescNomePessoa(String descNomePessoa) {
        this.descNomePessoa = descNomePessoa;
    }

    /**
     * @return o atributo descObsPessoa
     */
    public String getDescObsPessoa() {
        return descObsPessoa;
    }

    /**
     * Define o atributo descObsPessoa
     */
    public void setDescObsPessoa(String descObsPessoa) {
        this.descObsPessoa = descObsPessoa;
    }

    /**
     * @return o atributo idAtividadeEconomica
     */
    public Long getIdAtividadeEconomica() {
        return idAtividadeEconomica;
    }

    /**
     * Define o atributo idAtividadeEconomica
     */
    public void setIdAtividadeEconomica(Long idAtividadeEconomica) {
        this.idAtividadeEconomica = idAtividadeEconomica;
    }

    /**
     * @return o atributo idGrupoPessoa
     */
    public Long getIdGrupoPessoa() {
        return idGrupoPessoa;
    }

    /**
     * Define o atributo idGrupoPessoa
     */
    public void setIdGrupoPessoa(Long idGrupoPessoa) {
        this.idGrupoPessoa = idGrupoPessoa;
    }

    /**
     * @return o atributo numCGCCPF
     */
    public String getNumCGCCPF() {
        return numCGCCPF;
    }

    /**
     * Define o atributo numCGCCPF
     */
    public void setNumCGCCPF(String numCGCCPF) {
        this.numCGCCPF = numCGCCPF;
    }

    /**
     * @return o atributo numCoopOrigemPessoa
     */
    public Long getNumCoopOrigemPessoa() {
        return numCoopOrigemPessoa;
    }

    /**
     * Define o atributo numCoopOrigemPessoa
     */
    public void setNumCoopOrigemPessoa(Long numCoopOrigemPessoa) {
        this.numCoopOrigemPessoa = numCoopOrigemPessoa;
    }

    /**
     * @return o atributo numPacOrigemPessoa
     */
    public Long getNumPacOrigemPessoa() {
        return numPacOrigemPessoa;
    }

    /**
     * Define o atributo numPacOrigemPessoa
     */
    public void setNumPacOrigemPessoa(Long numPacOrigemPessoa) {
        this.numPacOrigemPessoa = numPacOrigemPessoa;
    }

    /**
     * @return o atributo ramal
     */
    public String getRamal() {
        return ramal;
    }

    /**
     * Define o atributo ramal
     */
    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

}
