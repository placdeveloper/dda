package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.Tarifa")
public class TarifaVO extends BancoobVO {

    private Long id;
    private DateTime dataHoraCadastroTarifa;
    private String descFatoGeradorTarifa;
    private String nomeTarifa;
    private GrupoTarifaVO grupoTarifa;
    private List<CustoVO> listaCusto;

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
     * @return o atributo dataHoraCadastroTarifa
     */
    public DateTime getDataHoraCadastroTarifa() {
        return dataHoraCadastroTarifa;
    }

    /**
     * Define o atributo dataHoraCadastroTarifa
     */
    public void setDataHoraCadastroTarifa(DateTime dataHoraCadastroTarifa) {
        this.dataHoraCadastroTarifa = dataHoraCadastroTarifa;
    }

    /**
     * @return o atributo descFatoGeradorTarifa
     */
    public String getDescFatoGeradorTarifa() {
        return descFatoGeradorTarifa;
    }

    /**
     * Define o atributo descFatoGeradorTarifa
     */
    public void setDescFatoGeradorTarifa(String descFatoGeradorTarifa) {
        this.descFatoGeradorTarifa = descFatoGeradorTarifa;
    }

    /**
     * @return o atributo nomeTarifa
     */
    public String getNomeTarifa() {
        return nomeTarifa;
    }

    /**
     * Define o atributo nomeTarifa
     */
    public void setNomeTarifa(String nomeTarifa) {
        this.nomeTarifa = nomeTarifa;
    }

    /**
     * @return o atributo grupoTarifa
     */
    public GrupoTarifaVO getGrupoTarifa() {
        return grupoTarifa;
    }

    /**
     * Define o atributo grupoTarifa
     */
    public void setGrupoTarifa(GrupoTarifaVO grupoTarifa) {
        this.grupoTarifa = grupoTarifa;
    }

    /**
     * @return o atributo listaCusto
     */
    public List<CustoVO> getListaCusto() {
        return listaCusto;
    }

    /**
     * Define o atributo listaCusto
     */
    public void setListaCusto(List<CustoVO> listaCusto) {
        this.listaCusto = listaCusto;
    }

    /**
     * Método responsável por calcular o valor total da tarifa conforme os custos que ela possui.
     */
    public BigDecimal getValorTotal() {
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (CustoVO custo : listaCusto) {
            for (DetalheCustoVO detalheCusto : custo.getListaDetalheCusto()) {
                for (VigenciaCustoVO vigencia : detalheCusto.getListaVigenciaCusto()) {
                    valorTotal = valorTotal.add(vigencia.getCustoTotal());
                }
            }
        }

        return valorTotal;
    }

}
