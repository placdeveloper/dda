package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.LocalImpressao")
public class LocalImpressaoVO extends BancoobVO {

    private Long id;
    private String descLocalImpressao;

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
     * @return o atributo descLocalImpressao
     */
    public String getDescLocalImpressao() {
        return descLocalImpressao;
    }

    /**
     * Define o atributo descLocalImpressao
     */
    public void setDescLocalImpressao(String descLocalImpressao) {
        this.descLocalImpressao = descLocalImpressao;
    }

}
