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
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.DetalheCusto")
public class DetalheCustoVO extends BancoobVO {

    private Long id;
    private DateTime dataHoraAtualizacao;
    private Short idProduto;
    private CustoVO custo;
    private Short codSistemaCooperativo;
    private BancoDepositarioVO bancoDepositario;
    private List<VigenciaCustoVO> listaVigenciaCusto;

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
     * @return o atributo dataHoraAtualizacao
     */
    public DateTime getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    /**
     * Define o atributo dataHoraAtualizacao
     */
    public void setDataHoraAtualizacao(DateTime dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    /**
     * @return o atributo idProduto
     */
    public Short getIdProduto() {
        return idProduto;
    }

    /**
     * Define o atributo idProduto
     */
    public void setIdProduto(Short idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return o atributo custo
     */
    public CustoVO getCusto() {
        return custo;
    }

    /**
     * Define o atributo custo
     */
    public void setCusto(CustoVO custo) {
        this.custo = custo;
    }

    /**
     * @return o atributo codSistemaCooperativo
     */
    public Short getCodSistemaCooperativo() {
        return codSistemaCooperativo;
    }

    /**
     * Define o atributo codSistemaCooperativo
     */
    public void setCodSistemaCooperativo(Short codSistemaCooperativo) {
        this.codSistemaCooperativo = codSistemaCooperativo;
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
     * @return o atributo listaVigenciaCusto
     */
    public List<VigenciaCustoVO> getListaVigenciaCusto() {
        return listaVigenciaCusto;
    }

    /**
     * Define o atributo listaVigenciaCusto
     */
    public void setListaVigenciaCusto(List<VigenciaCustoVO> listaVigenciaCusto) {
        this.listaVigenciaCusto = listaVigenciaCusto;
    }

}
