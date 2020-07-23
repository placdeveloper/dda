package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.Custo")
public class CustoVO extends BancoobVO {

    private Long id;
    private Boolean bolAtivo;
    private CriterioCobrancaVO criterioCobranca;
    private DateTime dataCadastroCusto;
    private String descFatoGerador;
    private GrupoCustoVO grupoCusto;
    private List<DetalheCustoVO> listaDetalheCusto;
    private String nomeCusto;

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
     * @return o atributo bolAtivo
     */
    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    /**
     * Define o atributo bolAtivo
     */
    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

    /**
     * @return o atributo criterioCobranca
     */
    public CriterioCobrancaVO getCriterioCobranca() {
        return criterioCobranca;
    }

    /**
     * Define o atributo criterioCobranca
     */
    public void setCriterioCobranca(CriterioCobrancaVO criterioCobranca) {
        this.criterioCobranca = criterioCobranca;
    }

    /**
     * @return o atributo dataCadastroCusto
     */
    public DateTime getDataCadastroCusto() {
        return dataCadastroCusto;
    }

    /**
     * Define o atributo dataCadastroCusto
     */
    public void setDataCadastroCusto(DateTime dataCadastroCusto) {
        this.dataCadastroCusto = dataCadastroCusto;
    }

    /**
     * @return o atributo descFatoGerador
     */
    public String getDescFatoGerador() {
        return descFatoGerador;
    }

    /**
     * Define o atributo descFatoGerador
     */
    public void setDescFatoGerador(String descFatoGerador) {
        this.descFatoGerador = descFatoGerador;
    }

    /**
     * @return o atributo grupoCusto
     */
    public GrupoCustoVO getGrupoCusto() {
        return grupoCusto;
    }

    /**
     * Define o atributo grupoCusto
     */
    public void setGrupoCusto(GrupoCustoVO grupoCusto) {
        this.grupoCusto = grupoCusto;
    }

    /**
     * @return o atributo listaDetalheCusto
     */
    public List<DetalheCustoVO> getListaDetalheCusto() {
        return listaDetalheCusto;
    }

    /**
     * Define o atributo listaDetalheCusto
     */
    public void setListaDetalheCusto(List<DetalheCustoVO> listaDetalheCusto) {
        this.listaDetalheCusto = listaDetalheCusto;
    }

    /**
     * @return o atributo nomeCusto
     */
    public String getNomeCusto() {
        return nomeCusto;
    }

    /**
     * Define o atributo nomeCusto
     */
    public void setNomeCusto(String nomeCusto) {
        this.nomeCusto = nomeCusto;
    }

}
