package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoTratamentoErroCip
 * 
 * @author GEORGE.SANTOS
 */
@Entity
@Table(name = "TIPOTRATAMENTOERROCIP", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoTratamentoErroCipVO")
public class TipoTratamentoErroCip extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = 5167502239245186020L;

    public static final short ATUALIZAR_DATA_MOVIMENTO = 1;
    public static final short REENVIAR_PARA_SSPB = 2;
    public static final short REENVIAR_PARA_CIP = 3;
    public static final short FINALIZAR_SEM_ACAO = 4;
    public static final short FINALIZAR_REENVIAR_NOVA_MENSAGEM = 5;
    public static final short FINALIZAR_REENVIAR_APOS_ATUALIZACAO_DADOS = 6;
    public static final short REPROCESSAR_MENSAGEM_CONTINGENCIA = 7;
    public static final short PREPARAR_REENVIO_ARQUIVO_CIP = 8;
    public static final short ATUALIZAR_DATA_PROXIMO_MOVIMENTO = 9;

    @Id
    @Column(name = "CODTIPOTRATAMENTOERROCIP", unique = true, nullable = false)
    private Short codTipoTratamentoErroCip;

    @Column(name = "DESCTIPOTRATAMENTOERROCIP", nullable = false)
    private String descTipoTratamentoErroCip;

    @ManyToMany
    @JoinTable(name = "SITUACAOMENSAGEMTIPOTRATAMENTO", schema = "DDA", joinColumns = { @JoinColumn(name = "CODTIPOTRATAMENTOERROCIP") }, inverseJoinColumns = { @JoinColumn(name = "CODSITUACAOMENSAGEMDDA") })
    private List<SituacaoMensagemDDA> listaSituacaoMensagemDDA;

    /**
     * @return o atributo codTipoTratamentoErroCip
     */
    public Short getCodTipoTratamentoErroCip() {
        return codTipoTratamentoErroCip;
    }

    /**
     * Define o atributo codTipoTratamentoErroCip
     */
    public void setCodTipoTratamentoErroCip(Short codTipoTratamentoErroCip) {
        this.codTipoTratamentoErroCip = codTipoTratamentoErroCip;
    }

    /**
     * @return o atributo descTipoTratamentoErroCip
     */
    public String getDescTipoTratamentoErroCip() {
        return descTipoTratamentoErroCip;
    }

    /**
     * Define o atributo descTipoTratamentoErroCip
     */
    public void setDescTipoTratamentoErroCip(String descTipoTratamentoErroCip) {
        this.descTipoTratamentoErroCip = descTipoTratamentoErroCip;
    }

    /**
     * @return the listaSituacaoMensagemDDA
     */
    public List<SituacaoMensagemDDA> getListaSituacaoMensagemDDA() {
        return listaSituacaoMensagemDDA;
    }

    /**
     * @param listaSituacaoMensagemDDA the listaSituacaoMensagemDDA to set
     */
    public void setListaSituacaoMensagemDDA(List<SituacaoMensagemDDA> listaSituacaoMensagemDDA) {
        this.listaSituacaoMensagemDDA = listaSituacaoMensagemDDA;
    }

}
