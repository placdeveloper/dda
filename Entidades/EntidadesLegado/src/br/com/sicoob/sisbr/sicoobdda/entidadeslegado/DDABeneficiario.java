/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades-legado
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidadeslegado.negocio.entidades.dda
 * Arquivo:         DDABeneficiario.java
 * Data Criação:    Aug 25, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidadeslegado;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * DDABeneficiario é responsável por
 * 
 * @author felipe.rosa
 */
@Entity
@Table(schema = "dbo", name = "DDABENEFICIARIO")
public class DDABeneficiario extends SicoobDDAEntidade {

    /**
     * 
     */
    private static final long serialVersionUID = 5252599829463758824L;

    @Id
    @Column(name = "IDBENEFICIARIO", unique = true, nullable = false)
    private Long id;

    @Column(name = "NUMCPFCNPJ", unique = true, nullable = false)
    private String numCPFCNPJ;

    @Column(name = "CODSITUACAOBENEFICARIOCIP", nullable = false)
    private String codSituacaoBeneficiarioCIP;

    @Column(name = "DATAHORAATUALIZACAO", nullable = false)
    private DateTimeDB dataHoraAtualizacao;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    private List<DDABeneficiarioCooperativa> listaDDABeneficiarioCooperativa;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    private List<DDABeneficiarioOperacao> listaDDABeneficiarioOperacao;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the numCPFCNPJ
     */
    public String getNumCPFCNPJ() {
        return numCPFCNPJ;
    }

    /**
     * @param numCPFCNPJ the numCPFCNPJ to set
     */
    public void setNumCPFCNPJ(String numCPFCNPJ) {
        this.numCPFCNPJ = numCPFCNPJ;
    }

    /**
     * @return the codSituacaoBeneficiarioCIP
     */
    public String getCodSituacaoBeneficiarioCIP() {
        return codSituacaoBeneficiarioCIP;
    }

    /**
     * @param codSituacaoBeneficiarioCIP the codSituacaoBeneficiarioCIP to set
     */
    public void setCodSituacaoBeneficiarioCIP(String codSituacaoBeneficiarioCIP) {
        this.codSituacaoBeneficiarioCIP = codSituacaoBeneficiarioCIP;
    }

    /**
     * @return the dataHoraAtualizacao
     */
    public DateTimeDB getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    /**
     * @param dataHoraAtualizacao the dataHoraAtualizacao to set
     */
    public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    /**
     * @return the listaDDABeneficiarioCooperativa
     */
    public List<DDABeneficiarioCooperativa> getListaDDABeneficiarioCooperativa() {
        return listaDDABeneficiarioCooperativa;
    }

    /**
     * @param listaDDABeneficiarioCooperativa the listaDDABeneficiarioCooperativa to set
     */
    public void setListaDDABeneficiarioCooperativa(List<DDABeneficiarioCooperativa> listaDDABeneficiarioCooperativa) {
        this.listaDDABeneficiarioCooperativa = listaDDABeneficiarioCooperativa;
    }

    /**
     * @return the listaDDABeneficiarioOperacao
     */
    public List<DDABeneficiarioOperacao> getListaDDABeneficiarioOperacao() {
        return listaDDABeneficiarioOperacao;
    }

    /**
     * @param listaDDABeneficiarioOperacao the listaDDABeneficiarioOperacao to set
     */
    public void setListaDDABeneficiarioOperacao(List<DDABeneficiarioOperacao> listaDDABeneficiarioOperacao) {
        this.listaDDABeneficiarioOperacao = listaDDABeneficiarioOperacao;
    }

}
