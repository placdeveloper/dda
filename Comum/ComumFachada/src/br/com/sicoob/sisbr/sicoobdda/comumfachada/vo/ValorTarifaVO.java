package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.ValorTarifa")
public class ValorTarifaVO extends BancoobVO {

    private Long id;
    private DateTime dataHoraCadastro;
    private DateTime dataAjusteTarifa;
    private PerfilTarifarioVO perfilTarifario;
    private TarifaVO tarifa;
    private BigDecimal valorTarifa;
    private BigDecimal valorAjusteTarifa;
    private TipoAjusteVO tipoAjuste;
    private String idUsuarioLogado;
    private TipoOperacaoAjusteVO tipoOperacaoAjuste;
    private BigDecimal valorTarifaTemp;
    private BancoDepositarioVO bancoDepositario;
    private Boolean selecionado;
    private BigDecimal valorTotalTemp;

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
     * @return o atributo dataHoraCadastro
     */
    public DateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    /**
     * Define o atributo dataHoraCadastro
     */
    public void setDataHoraCadastro(DateTime dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    /**
     * @return o atributo dataAjusteTarifa
     */
    public DateTime getDataAjusteTarifa() {
        return dataAjusteTarifa;
    }

    /**
     * Define o atributo dataAjusteTarifa
     */
    public void setDataAjusteTarifa(DateTime dataAjusteTarifa) {
        this.dataAjusteTarifa = dataAjusteTarifa;
    }

    /**
     * @return o atributo perfilTarifario
     */
    public PerfilTarifarioVO getPerfilTarifario() {
        return perfilTarifario;
    }

    /**
     * Define o atributo perfilTarifario
     */
    public void setPerfilTarifario(PerfilTarifarioVO perfilTarifario) {
        this.perfilTarifario = perfilTarifario;
    }

    /**
     * @return o atributo tarifa
     */
    public TarifaVO getTarifa() {
        return tarifa;
    }

    /**
     * Define o atributo tarifa
     */
    public void setTarifa(TarifaVO tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * @return o atributo valorTarifa
     */
    public BigDecimal getValorTarifa() {
        return valorTarifa;
    }

    /**
     * Define o atributo valorTarifa
     */
    public void setValorTarifa(BigDecimal valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    /**
     * @return o atributo valorAjusteTarifa
     */
    public BigDecimal getValorAjusteTarifa() {
        return valorAjusteTarifa;
    }

    /**
     * Define o atributo valorAjusteTarifa
     */
    public void setValorAjusteTarifa(BigDecimal valorAjusteTarifa) {
        this.valorAjusteTarifa = valorAjusteTarifa;
    }

    /**
     * @return o atributo tipoAjuste
     */
    public TipoAjusteVO getTipoAjuste() {
        return tipoAjuste;
    }

    /**
     * Define o atributo tipoAjuste
     */
    public void setTipoAjuste(TipoAjusteVO tipoAjuste) {
        this.tipoAjuste = tipoAjuste;
    }

    /**
     * @return o atributo idUsuarioLogado
     */
    public String getIdUsuarioLogado() {
        return idUsuarioLogado;
    }

    /**
     * Define o atributo idUsuarioLogado
     */
    public void setIdUsuarioLogado(String idUsuarioLogado) {
        this.idUsuarioLogado = idUsuarioLogado;
    }

    /**
     * @return o atributo tipoOperacaoAjuste
     */
    public TipoOperacaoAjusteVO getTipoOperacaoAjuste() {
        return tipoOperacaoAjuste;
    }

    /**
     * Define o atributo tipoOperacaoAjuste
     */
    public void setTipoOperacaoAjuste(TipoOperacaoAjusteVO tipoOperacaoAjuste) {
        this.tipoOperacaoAjuste = tipoOperacaoAjuste;
    }

    /**
     * @return o atributo valorTarifaTemp
     */
    public BigDecimal getValorTarifaTemp() {
        return valorTarifaTemp;
    }

    /**
     * Define o atributo valorTarifaTemp
     */
    public void setValorTarifaTemp(BigDecimal valorTarifaTemp) {
        this.valorTarifaTemp = valorTarifaTemp;
    }

    /**
     * @return o atributo bancoDepositario
     */
    public BancoDepositarioVO getBancoDepositario() {
        return bancoDepositario;
    }

    /**
     * Define o atributo bancoDepositario
     */
    public void setBancoDepositario(BancoDepositarioVO bancoDepositario) {
        this.bancoDepositario = bancoDepositario;
    }

    /**
     * @return o atributo selecionado
     */
    public Boolean getSelecionado() {
        return selecionado;
    }

    /**
     * Define o atributo selecionado
     */
    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return o atributo valorTotalTemp
     */
    public BigDecimal getValorTotalTemp() {
        return valorTotalTemp;
    }

    /**
     * Define o atributo valorTotalTemp
     */
    public void setValorTotalTemp(BigDecimal valorTotalTemp) {
        this.valorTotalTemp = valorTotalTemp;
    }

}
