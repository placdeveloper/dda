package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * PagadorDDAContaVO é responsável por
 * 
 * @author george.santos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAConta")
public class PagadorDDAContaVO extends BancoobVO {

    private Long id;

    private PagadorDDAVO pagadorDDA;

    private Integer numAgencia;

    private BigDecimal numContaCorrente;

    private DateTime dataHoraAdesao;

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
     * @return o atributo pagadorDDA
     */
    public PagadorDDAVO getPagadorDDA() {
        return pagadorDDA;
    }

    /**
     * Define o atributo pagadorDDA
     */
    public void setPagadorDDA(PagadorDDAVO pagadorDDA) {
        this.pagadorDDA = pagadorDDA;
    }

    /**
     * @return o atributo numAgencia
     */
    public Integer getNumAgencia() {
        return numAgencia;
    }

    /**
     * Define o atributo numAgencia
     */
    public void setNumAgencia(Integer numAgencia) {
        this.numAgencia = numAgencia;
    }

    /**
     * @return o atributo numContaCorrente
     */
    public BigDecimal getNumContaCorrente() {
        return numContaCorrente;
    }

    /**
     * Define o atributo numContaCorrente
     */
    public void setNumContaCorrente(BigDecimal numContaCorrente) {
        this.numContaCorrente = numContaCorrente;
    }

    /**
     * @return o atributo dataHoraAdesao
     */
    public DateTime getDataHoraAdesao() {
        return dataHoraAdesao;
    }

    /**
     * Define o atributo dataHoraAdesao
     */
    public void setDataHoraAdesao(DateTime dataHoraAdesao) {
        this.dataHoraAdesao = dataHoraAdesao;
    }
}
