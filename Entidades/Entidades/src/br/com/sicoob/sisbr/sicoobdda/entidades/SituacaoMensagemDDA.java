/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         SituacaoMensagemDDA.java
 * Data Criação:    Sep 14, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * SituacaoMensagemDDA é responsável por 
 * 
 * @author Felipe.Rosa
 */
@Entity
@Table(name = "SITUACAOMENSAGEMDDA", schema = "DDA")
public class SituacaoMensagemDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = -8367911640986377684L;

    public static final short A_ENVIAR = 1;
    public static final short SEM_RETORNO_SSPB = 2;
    public static final short SEM_RETORNO_CIP = 3;
    public static final short RETORNO_COM_MENSAGEM_DE_ERRO = 4;
    public static final short RETORNO_COM_ERRO_CONTIGENCIA = 5;
    public static final short ARQUIVO_ENVIO_COM_ERRO = 6;

    @Id
    @Column(name = "CODSITUACAOMENSAGEMDDA", unique = true, nullable = false)
    private Short codSituacaoMensagemDDA;

    @Column(name = "DESCSITUACAOMENSAGEMDDA", nullable = false)
    private String descSituacaoMensagemDDA;

    @ManyToMany(mappedBy = "listaSituacaoMensagemDDA", cascade = CascadeType.ALL)
    private List<TipoTratamentoErroCip> listaTipoTratamentoErroCip;

    /**
     * @return the codSituacaoMensagemDDA
     */
    public Short getCodSituacaoMensagemDDA() {
        return codSituacaoMensagemDDA;
    }

    /**
     * @param codSituacaoMensagemDDA the codSituacaoMensagemDDA to set
     */
    public void setCodSituacaoMensagemDDA(Short codSituacaoMensagemDDA) {
        this.codSituacaoMensagemDDA = codSituacaoMensagemDDA;
    }

    /**
     * @return the descSituacaoMensagemDDA
     */
    public String getDescSituacaoMensagemDDA() {
        return descSituacaoMensagemDDA;
    }

    /**
     * @param descSituacaoMensagemDDA the descSituacaoMensagemDDA to set
     */
    public void setDescSituacaoMensagemDDA(String descSituacaoMensagemDDA) {
        this.descSituacaoMensagemDDA = descSituacaoMensagemDDA;
    }

    /**
     * @return the listaTipoTratamentoErroCip
     */
    public List<TipoTratamentoErroCip> getListaTipoTratamentoErroCip() {
        return listaTipoTratamentoErroCip;
    }

    /**
     * @param listaTipoTratamentoErroCip the listaTipoTratamentoErroCip to set
     */
    public void setListaTipoTratamentoErroCip(List<TipoTratamentoErroCip> listaTipoTratamentoErroCip) {
        this.listaTipoTratamentoErroCip = listaTipoTratamentoErroCip;
    }

}
