package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LocalidadeDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;
import br.com.sicoob.tipos.DateTime;

@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PessoaDTO")
public class PessoaDto extends BancoobDto {

    private static final long serialVersionUID = -197604114217933580L;

    public static final String[] CAMPOS = new String[] { "idPessoa", "cpfCnpj", "codTipoPessoa", "codigoCompartilhamentoCadastro", "nomePessoa", "nomeCompleto", "nomeApelido",
            "descricaoObservacaoPessoa", "codigoAtividadeEconomica", "codigoCnaeFiscal", "dataInclusaoSistema", "autorizaConsultaBacen", "idPessoaLegado", "idInstituicao" };

    public static final String[] CAMPOS_2 = new String[] { "idPessoa", "idInstituicao", "cpfCnpj", "nomePessoa", "nomeCompleto", "nomeApelido" };

    // PESSOA
    private Integer idPessoa;

    private String cpfCnpj;

    private Short codTipoPessoa;

    // PESSOA COMPARTILHAMENTO
    private Short codigoCompartilhamentoCadastro;

    private String nomePessoa;

    private String nomeCompleto;

    private String nomeApelido;

    private String descricaoObservacaoPessoa;

    private Short codigoAtividadeEconomica;

    private String codigoCnaeFiscal;

    private Date dataInclusaoSistema;

    private Boolean autorizaConsultaBacen;

    // Transição
    private Integer idPessoaLegado;

    private Integer idInstituicao;

    private Integer idUnidadeInst;

    private DateTime dataUltimaAtualizacao;

    private EnderecoPessoaDto enderecoPessoaDto;
    private LocalidadeDto localidadeDto;
    private TelefonePessoaDto telefonePessoaDto;

    private Date dataRenovacaoCadastral;

    public PessoaDto() {

    }

    public PessoaDto(Integer idPessoa, String cpfCnpj, Short codTipoPessoa, Short codigoCompartilhamentoCadastro, String nomePessoa, String nomeCompleto, String nomeApelido,
            String descricaoObservacaoPessoa, Short codigoAtividadeEconomica, String codigoCnaeFiscal, Date dataInclusaoSistema, Boolean autorizaConsultaBacen) {
        this.idPessoa = idPessoa;
        this.cpfCnpj = cpfCnpj;
        this.codTipoPessoa = codTipoPessoa;
        this.codigoCompartilhamentoCadastro = codigoCompartilhamentoCadastro;
        this.nomePessoa = nomePessoa;
        this.nomeCompleto = nomeCompleto;
        this.nomeApelido = nomeApelido;
        this.descricaoObservacaoPessoa = descricaoObservacaoPessoa;
        this.codigoAtividadeEconomica = codigoAtividadeEconomica;
        this.codigoCnaeFiscal = codigoCnaeFiscal;
        this.dataInclusaoSistema = dataInclusaoSistema;
        this.autorizaConsultaBacen = autorizaConsultaBacen;
    }

    /**
     * Construtor
     * 
     * @param idCliente
     * @param nomeCompleto
     * @param cnpjCpf
     * @param idInstiuicao
     */
    public PessoaDto(Long idCliente, String nomeCompleto, String cnpjCpf, Integer idInstiuicao) {
        this.idPessoa = idCliente.intValue();
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cnpjCpf;
        this.idInstituicao = idInstiuicao;
    }

    public PessoaDto(Integer idPessoa, String cpfCnpj, Short codTipoPessoa, Short codigoCompartilhamentoCadastro, String nomePessoa, String nomeCompleto, String nomeApelido,
            String descricaoObservacaoPessoa, Short codigoAtividadeEconomica, String codigoCnaeFiscal, Date dataInclusaoSistema, Boolean autorizaConsultaBacen,
            Integer idPessoaLegado, Integer idInstituicao) {

        this(idPessoa, cpfCnpj, codTipoPessoa, codigoCompartilhamentoCadastro, nomePessoa, nomeCompleto, nomeApelido, descricaoObservacaoPessoa, codigoAtividadeEconomica,
                codigoCnaeFiscal, dataInclusaoSistema, autorizaConsultaBacen);
        this.idPessoaLegado = idPessoaLegado;
        this.idInstituicao = idInstituicao;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Short getCodTipoPessoa() {
        return codTipoPessoa;
    }

    public void setCodTipoPessoa(Short codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

    public Short getCodigoCompartilhamentoCadastro() {
        return codigoCompartilhamentoCadastro;
    }

    public void setCodigoCompartilhamentoCadastro(Short codigoCompartilhamentoCadastro) {
        this.codigoCompartilhamentoCadastro = codigoCompartilhamentoCadastro;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeApelido() {
        return nomeApelido;
    }

    public void setNomeApelido(String nomeApelido) {
        this.nomeApelido = nomeApelido;
    }

    public String getDescricaoObservacaoPessoa() {
        return descricaoObservacaoPessoa;
    }

    public void setDescricaoObservacaoPessoa(String descricaoObservacaoPessoa) {
        this.descricaoObservacaoPessoa = descricaoObservacaoPessoa;
    }

    public Short getCodigoAtividadeEconomica() {
        return codigoAtividadeEconomica;
    }

    public void setCodigoAtividadeEconomica(Short codigoAtividadeEconomica) {
        this.codigoAtividadeEconomica = codigoAtividadeEconomica;
    }

    public String getCodigoCnaeFiscal() {
        return codigoCnaeFiscal;
    }

    public void setCodigoCnaeFiscal(String codigoCnaeFiscal) {
        this.codigoCnaeFiscal = codigoCnaeFiscal;
    }

    public Date getDataInclusaoSistema() {
        return dataInclusaoSistema;
    }

    public void setDataInclusaoSistema(Date dataInclusaoSistema) {
        this.dataInclusaoSistema = dataInclusaoSistema;
    }

    public Boolean getAutorizaConsultaBacen() {
        return autorizaConsultaBacen;
    }

    public void setAutorizaConsultaBacen(Boolean autorizaConsultaBacen) {
        this.autorizaConsultaBacen = autorizaConsultaBacen;
    }

    /**
     * @return the idPessoaLegado
     */
    public Integer getIdPessoaLegado() {
        return idPessoaLegado;
    }

    public DateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    /**
     * @param idPessoaLegado the idPessoaLegado to set
     */
    public void setIdPessoaLegado(Integer idPessoaLegado) {
        this.idPessoaLegado = idPessoaLegado;
    }

    /**
     * @return the idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * @param idInstituicao the idInstituicao to set
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return o atributo idUnidadeInst
     */
    public Integer getIdUnidadeInst() {
        return idUnidadeInst;
    }

    /**
     * Define o atributo idUnidadeInst
     */
    public void setIdUnidadeInst(Integer idUnidadeInst) {
        this.idUnidadeInst = idUnidadeInst;
    }

    /**
     * @return o atributo enderecoPessoaDto
     */
    public EnderecoPessoaDto getEnderecoPessoaDto() {
        return enderecoPessoaDto;
    }

    /**
     * Define o atributo enderecoPessoaDto
     */
    public void setEnderecoPessoaDto(EnderecoPessoaDto enderecoPessoaDto) {
        this.enderecoPessoaDto = enderecoPessoaDto;
    }

    /**
     * @return o atributo localidadeDto
     */
    public LocalidadeDto getLocalidadeDto() {
        return localidadeDto;
    }

    /**
     * Define o atributo localidadeDto
     */
    public void setLocalidadeDto(LocalidadeDto localidadeDto) {
        this.localidadeDto = localidadeDto;
    }

    /**
     * @return o atributo telefonePessoaDto
     */
    public TelefonePessoaDto getTelefonePessoaDto() {
        return telefonePessoaDto;
    }

    /**
     * Define o atributo telefonePessoaDto
     */
    public void setTelefonePessoaDto(TelefonePessoaDto telefonePessoaDto) {
        this.telefonePessoaDto = telefonePessoaDto;
    }

    /**
     * Método responsável por r
     * 
     * @return String
     * 
     */
    public String getCpfCnpjFormatado() {
        return FormatadorUtil.formatar(getCpfCnpj(), getCpfCnpj().length() == 11 ? FormatadorUtil.MASCARA_CPF : FormatadorUtil.MASCARA_CNPJ);
    }

    public void setDataUltimaAtualizacao(DateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    /**
     * @return o atributo dataRenovacaoCadastral
     */
    public Date getDataRenovacaoCadastral() {
        return dataRenovacaoCadastral;
    }

    /**
     * Define o atributo dataRenovacaoCadastral
     */
    public void setDataRenovacaoCadastral(Date dataRenovacaoCadastral) {
        this.dataRenovacaoCadastral = dataRenovacaoCadastral;
    }
}
