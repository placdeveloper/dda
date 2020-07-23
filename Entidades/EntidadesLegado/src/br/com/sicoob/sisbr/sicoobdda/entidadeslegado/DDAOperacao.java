/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades-legado
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidadeslegado.negocio.entidades.dda
 * Arquivo:         DDAOperacao.java
 * Data Criação:    Aug 25, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidadeslegado;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * DDAOperacao é responsável por
 * 
 * @author felipe.rosa
 */
@Entity
@Table(schema = "dbo", name = "DDAOPERACAO")
public class DDAOperacao extends SicoobDDAEntidade {

    private static final long serialVersionUID = 3660021491081422350L;

    @Id
    @Column(name = "CODOPERACAO", unique = true, nullable = false)
    private Short codOperacao;

    @Column(name = "DESCNOMEOPERACAO", nullable = false)
    private String descNomeOperacao;

    @Column(name = "DESCMENSAGEM")
    private String descMensagem;

    @OneToMany(mappedBy = "operacao")
    private List<DDABeneficiarioOperacao> listaDDABeneficiarioOperacao;

    /**
     * @return o atributo codOperacao
     */
    public Short getCodOperacao() {
        return codOperacao;
    }

    /**
     * Define o atributo codOperacao
     */
    public void setCodOperacao(Short codOperacao) {
        this.codOperacao = codOperacao;
    }

    /**
     * @return o atributo descNomeOperacao
     */
    public String getDescNomeOperacao() {
        return descNomeOperacao;
    }

    /**
     * Define o atributo descNomeOperacao
     */
    public void setDescNomeOperacao(String descNomeOperacao) {
        this.descNomeOperacao = descNomeOperacao;
    }

    /**
     * @return o atributo descMensagem
     */
    public String getDescMensagem() {
        return descMensagem;
    }

    /**
     * Define o atributo descMensagem
     */
    public void setDescMensagem(String descMensagem) {
        this.descMensagem = descMensagem;
    }

    /**
     * @return o atributo listaDDABeneficiarioOperacao
     */
    public List<DDABeneficiarioOperacao> getListaDDABeneficiarioOperacao() {
        return listaDDABeneficiarioOperacao;
    }

    /**
     * Define o atributo listaDDABeneficiarioOperacao
     */
    public void setListaDDABeneficiarioOperacao(List<DDABeneficiarioOperacao> listaDDABeneficiarioOperacao) {
        this.listaDDABeneficiarioOperacao = listaDDABeneficiarioOperacao;
    }

}
