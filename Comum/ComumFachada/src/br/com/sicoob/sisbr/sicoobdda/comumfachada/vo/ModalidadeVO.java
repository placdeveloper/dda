package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Rodrigo.Neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.Modalidade")
public class ModalidadeVO extends BancoobVO {

    private Short id;
    private DistribuidorTituloVO distribuidorTitulo;
    private LocalImpressaoVO localImpressao;
    private TipoRegistroModalidadeVO tipoRegistroModalidade;
    private String descCompleta;

    /**
     * @return o atributo id
     */
    public Short getId() {
        return id;
    }

    /**
     * Define o atributo id
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * @return o atributo distribuidorTitulo
     */
    public DistribuidorTituloVO getDistribuidorTitulo() {
        return distribuidorTitulo;
    }

    /**
     * Define o atributo distribuidorTitulo
     */
    public void setDistribuidorTitulo(DistribuidorTituloVO distribuidorTitulo) {
        this.distribuidorTitulo = distribuidorTitulo;
    }

    /**
     * @return o atributo localImpressao
     */
    public LocalImpressaoVO getLocalImpressao() {
        return localImpressao;
    }

    /**
     * Define o atributo localImpressao
     */
    public void setLocalImpressao(LocalImpressaoVO localImpressao) {
        this.localImpressao = localImpressao;
    }

    /**
     * @return o atributo tipoRegistroModalidade
     */
    public TipoRegistroModalidadeVO getTipoRegistroModalidade() {
        return tipoRegistroModalidade;
    }

    /**
     * Define o atributo tipoRegistroModalidade
     */
    public void setTipoRegistroModalidade(TipoRegistroModalidadeVO tipoRegistroModalidade) {
        this.tipoRegistroModalidade = tipoRegistroModalidade;
    }

    /**
     * @return o atributo descCompleta
     */
    public String getDescCompleta() {
        return descCompleta;
    }

    /**
     * Define o atributo descCompleta
     */
    public void setDescCompleta(String descCompleta) {
        this.descCompleta = descCompleta;
    }

}
