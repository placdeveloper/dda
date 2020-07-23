package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoModeloCalculo é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Entity
@Table(name = "TipoModeloCalculo", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoModeloCalculoVO")
public class TipoModeloCalculo extends SicoobDDAEntidade {

    private static final long serialVersionUID = -5088900033448140730L;

    public static final String INSTITUICAO_RECEBEDORA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS = "01";
    public static final String INSTITUICAO_DESTINATARIA_CALCULA_TITULOS_VENCIDOS_E_RECEBEDORA_CALCULA_TITULOS_A_VENCER = "02";
    public static final String INSTITUICAO_DESTINATARIA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS = "03";
    public static final String CIP_INSTITUICAO_RECEBEDORA_CALCULA_SALDO_PARCIAL_E_TITULOS_A_VENCER_E_VENCIDOS_NA_CONSULTA = "04";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODTIPOMODELOCALCULO", unique = true, nullable = false)
    private String codTipoModeloCalculo;

    @Column(nullable = false)
    private String descTipoModeloCalculo;

    /**
     * @return o atributo codTipoModeloCalculo
     */
    public String getCodTipoModeloCalculo() {
        return codTipoModeloCalculo;
    }

    /**
     * Define o atributo codTipoModeloCalculo
     */
    public void setCodTipoModeloCalculo(String codTipoModeloCalculo) {
        this.codTipoModeloCalculo = codTipoModeloCalculo;
    }

    /**
     * @return o atributo descTipoModeloCalculo
     */
    public String getDescTipoModeloCalculo() {
        return descTipoModeloCalculo;
    }

    /**
     * Define o atributo descTipoModeloCalculo
     */
    public void setDescTipoModeloCalculo(String descTipoModeloCalculo) {
        this.descTipoModeloCalculo = descTipoModeloCalculo;
    }

}
