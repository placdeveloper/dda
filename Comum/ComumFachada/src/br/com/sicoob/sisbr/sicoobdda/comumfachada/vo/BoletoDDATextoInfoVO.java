package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * BoletoDDATextoInfoVO é responsável por
 * 
 * @author george.santos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATextoInfo")
public class BoletoDDATextoInfoVO extends BancoobVO {

    private Long id;

    private BoletoDDAVO boletoDDA;

    private String descTextoInformativo;

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
     * @return o atributo boletoDDA
     */
    public BoletoDDAVO getBoletoDDA() {
        return boletoDDA;
    }

    /**
     * Define o atributo boletoDDA
     */
    public void setBoletoDDA(BoletoDDAVO boletoDDA) {
        this.boletoDDA = boletoDDA;
    }

    /**
     * @return o atributo descTextoInformativo
     */
    public String getDescTextoInformativo() {
        return descTextoInformativo;
    }

    /**
     * Define o atributo descTextoInformativo
     */
    public void setDescTextoInformativo(String descTextoInformativo) {
        this.descTextoInformativo = descTextoInformativo;
    }
}
