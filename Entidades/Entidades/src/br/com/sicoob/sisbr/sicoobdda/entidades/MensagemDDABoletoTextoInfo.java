/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDABoletoDesconto.java
 * Data Cria��o:    Jun 1, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MensagemDDABoletoNotaFiscal
 * 
 * @author george.santos
 */
@Entity
@Table(name = "MensagemDDABoletoTextoInfo", schema = "DDA")
public class MensagemDDABoletoTextoInfo extends SicoobDDAEntidade {
    private static final long serialVersionUID = -1463531203355301738L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMensagemDDABoletoTextoInfo", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMensagemDDA", nullable = false)
    private MensagemDDABoleto mensagemDDABoleto;

    @Column(nullable = false)
    private String descTextoInformativo;

    public MensagemDDABoletoTextoInfo() {
    }

    public MensagemDDABoletoTextoInfo(Long id, Long idMensagemDDABoleto, String descTextoInformativo) {
        super();
        this.id = id;
        this.mensagemDDABoleto = new MensagemDDABoleto(idMensagemDDABoleto);
        this.descTextoInformativo = descTextoInformativo;
    }

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
     * @return o atributo mensagemDDABoleto
     */
    public MensagemDDABoleto getMensagemDDABoleto() {
        return mensagemDDABoleto;
    }

    /**
     * Define o atributo mensagemDDABoleto
     */
    public void setMensagemDDABoleto(MensagemDDABoleto mensagemDDABoleto) {
        this.mensagemDDABoleto = mensagemDDABoleto;
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
