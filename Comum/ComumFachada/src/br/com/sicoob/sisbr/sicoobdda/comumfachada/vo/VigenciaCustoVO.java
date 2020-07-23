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
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.VigenciaCusto")
public class VigenciaCustoVO extends BancoobVO {

    private Long id;
    private DetalheCustoVO detalheCusto;
    private BigDecimal valorCustoExterno;
    private BigDecimal valorCustoInterno;
    private DateTime dataHoraCadastroVigencia;
    private DateTime dataHoraAlteracaoVigencia;
    private DateTime dataInicioVigencia;
    private String idUsuarioLogado;

    private transient Long numTemp;

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
     * @return o atributo detalheCusto
     */
    public DetalheCustoVO getDetalheCusto() {
        return detalheCusto;
    }

    /**
     * Define o atributo detalheCusto
     */
    public void setDetalheCusto(DetalheCustoVO detalheCusto) {
        this.detalheCusto = detalheCusto;
    }

    /**
     * @return o atributo valorCustoExterno
     */
    public BigDecimal getValorCustoExterno() {
        return valorCustoExterno;
    }

    /**
     * Define o atributo valorCustoExterno
     */
    public void setValorCustoExterno(BigDecimal valorCustoExterno) {
        this.valorCustoExterno = valorCustoExterno;
    }

    /**
     * @return o atributo valorCustoInterno
     */
    public BigDecimal getValorCustoInterno() {
        return valorCustoInterno;
    }

    /**
     * Define o atributo valorCustoInterno
     */
    public void setValorCustoInterno(BigDecimal valorCustoInterno) {
        this.valorCustoInterno = valorCustoInterno;
    }

    /**
     * @return o atributo dataHoraCadastroVigencia
     */
    public DateTime getDataHoraCadastroVigencia() {
        return dataHoraCadastroVigencia;
    }

    /**
     * Define o atributo dataHoraCadastroVigencia
     */
    public void setDataHoraCadastroVigencia(DateTime dataHoraCadastroVigencia) {
        this.dataHoraCadastroVigencia = dataHoraCadastroVigencia;
    }

    /**
     * @return o atributo dataHoraAlteracaoVigencia
     */
    public DateTime getDataHoraAlteracaoVigencia() {
        return dataHoraAlteracaoVigencia;
    }

    /**
     * Define o atributo dataHoraAlteracaoVigencia
     */
    public void setDataHoraAlteracaoVigencia(DateTime dataHoraAlteracaoVigencia) {
        this.dataHoraAlteracaoVigencia = dataHoraAlteracaoVigencia;
    }

    /**
     * @return o atributo dataInicioVigencia
     */
    public DateTime getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    /**
     * Define o atributo dataInicioVigencia
     */
    public void setDataInicioVigencia(DateTime dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
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

    public Long getNumTemp() {
        return numTemp;
    }

    public void setNumTemp(Long numTemp) {
        this.numTemp = numTemp;
    }

    /**
     * Método responsável por somar os custos interno e de terceiros.
     * 
     * @return BigDecimal valor total
     */
    public BigDecimal getCustoTotal() {
        return getValorCustoInterno().add(getValorCustoExterno());
    }

}
