/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MonitoracaoArqVarreduraDto.java
 * Data Criação:    Nov 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MonitoracaoArqVarreduraDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoArqVarreduraDTO")
public class MonitoracaoArqVarreduraDto extends BancoobDto {

    private static final long serialVersionUID = -6326790659236259581L;

    private Long arqDisponivel;
    private Long gen0015SemArq;
    private Long arqSemGEN0015;
    private Long arqEmProcessamento;

    private DateTimeDB dataHoraAtualizacao;

    private Integer parametroTempoAtualizacao;

    private Integer alertaArqDisponivel;
    private Integer alertaGEN0015SemArq;
    private Integer alertaArqSemGEN0015;
    private Integer alertaArqEmProcessamento;

    private Boolean bolAlertaGEN0015SemArq;

    // DETALHE
    private List<LogRecebimentoArquivoDDA> listaArqDisponivel;
    private List<LogRecebimentoArquivoDDA> listaGEN0015SemArq;
    private List<LogRecebimentoArquivoDDA> listaArqSemGEN0015;
    private List<LogRecebimentoArquivoDDA> listaArqEmProcessamento;

    /**
     * 
     */
    public MonitoracaoArqVarreduraDto() {
        super();
        this.dataHoraAtualizacao = new DateTimeDB();
    }

    /**
     * @return o atributo arqDisponivel
     */
    public Long getArqDisponivel() {
        return arqDisponivel;
    }

    /**
     * Define o atributo arqDisponivel
     */
    public void setArqDisponivel(Long arqDisponivel) {
        this.arqDisponivel = arqDisponivel;
    }

    /**
     * @return o atributo gen0015SemArq
     */
    public Long getGen0015SemArq() {
        return gen0015SemArq;
    }

    /**
     * Define o atributo gen0015SemArq
     */
    public void setGen0015SemArq(Long gen0015SemArq) {
        this.gen0015SemArq = gen0015SemArq;
    }

    /**
     * @return o atributo arqSemGEN0015
     */
    public Long getArqSemGEN0015() {
        return arqSemGEN0015;
    }

    /**
     * Define o atributo arqSemGEN0015
     */
    public void setArqSemGEN0015(Long arqSemGEN0015) {
        this.arqSemGEN0015 = arqSemGEN0015;
    }

    /**
     * @return o atributo arqEmProcessamento
     */
    public Long getArqEmProcessamento() {
        return arqEmProcessamento;
    }

    /**
     * Define o atributo arqEmProcessamento
     */
    public void setArqEmProcessamento(Long arqEmProcessamento) {
        this.arqEmProcessamento = arqEmProcessamento;
    }

    /**
     * @return o atributo dataHoraAtualizacao
     */
    public DateTimeDB getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    /**
     * Define o atributo dataHoraAtualizacao
     */
    public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    /**
     * @return o atributo parametroTempoAtualizacao
     */
    public Integer getParametroTempoAtualizacao() {
        return parametroTempoAtualizacao;
    }

    /**
     * Define o atributo parametroTempoAtualizacao
     */
    public void setParametroTempoAtualizacao(Integer parametroTempoAtualizacao) {
        this.parametroTempoAtualizacao = parametroTempoAtualizacao;
    }

    /**
     * @return o atributo alertaArqDisponivel
     */
    public Integer getAlertaArqDisponivel() {
        return alertaArqDisponivel;
    }

    /**
     * Define o atributo alertaArqDisponivel
     */
    public void setAlertaArqDisponivel(Integer alertaArqDisponivel) {
        this.alertaArqDisponivel = alertaArqDisponivel;
    }

    /**
     * @return o atributo alertaGEN0015SemArq
     */
    public Integer getAlertaGEN0015SemArq() {
        return alertaGEN0015SemArq;
    }

    /**
     * Define o atributo alertaGEN0015SemArq
     */
    public void setAlertaGEN0015SemArq(Integer alertaGEN0015SemArq) {
        this.alertaGEN0015SemArq = alertaGEN0015SemArq;
    }

    /**
     * @return o atributo alertaArqSemGEN0015
     */
    public Integer getAlertaArqSemGEN0015() {
        return alertaArqSemGEN0015;
    }

    /**
     * Define o atributo alertaArqSemGEN0015
     */
    public void setAlertaArqSemGEN0015(Integer alertaArqSemGEN0015) {
        this.alertaArqSemGEN0015 = alertaArqSemGEN0015;
    }

    /**
     * @return o atributo alertaArqEmProcessamento
     */
    public Integer getAlertaArqEmProcessamento() {
        return alertaArqEmProcessamento;
    }

    /**
     * Define o atributo alertaArqEmProcessamento
     */
    public void setAlertaArqEmProcessamento(Integer alertaArqEmProcessamento) {
        this.alertaArqEmProcessamento = alertaArqEmProcessamento;
    }

    /**
     * @return o atributo bolAlertaGEN0015SemArq
     */
    public Boolean getBolAlertaGEN0015SemArq() {
        return bolAlertaGEN0015SemArq;
    }

    /**
     * Define o atributo bolAlertaGEN0015SemArq
     */
    public void setBolAlertaGEN0015SemArq(Boolean bolAlertaGEN0015SemArq) {
        this.bolAlertaGEN0015SemArq = bolAlertaGEN0015SemArq;
    }

    /**
     * @return o atributo listaArqDisponivel
     */
    public List<LogRecebimentoArquivoDDA> getListaArqDisponivel() {
        return listaArqDisponivel;
    }

    /**
     * Define o atributo listaArqDisponivel
     */
    public void setListaArqDisponivel(List<LogRecebimentoArquivoDDA> listaArqDisponivel) {
        if (!ObjectUtil.isNull(listaArqDisponivel)) {
            this.listaArqDisponivel = listaArqDisponivel;
            this.arqDisponivel = (long) listaArqDisponivel.size();
        }
    }

    /**
     * @return o atributo listaGEN0015SemARq
     */
    public List<LogRecebimentoArquivoDDA> getListaGEN0015SemArq() {
        return listaGEN0015SemArq;
    }

    /**
     * Define o atributo listaGEN0015SemARq
     */
    public void setListaGEN0015SemArq(List<LogRecebimentoArquivoDDA> listaGEN0015SemArq) {
        if (!ObjectUtil.isNull(listaGEN0015SemArq)) {
            this.listaGEN0015SemArq = listaGEN0015SemArq;
            this.gen0015SemArq = (long) listaGEN0015SemArq.size();
        }
    }

    /**
     * @return o atributo listaArqSemGEN0015
     */
    public List<LogRecebimentoArquivoDDA> getListaArqSemGEN0015() {
        return listaArqSemGEN0015;
    }

    /**
     * Define o atributo listaArqSemGEN0015
     */
    public void setListaArqSemGEN0015(List<LogRecebimentoArquivoDDA> listaArqSemGEN0015) {
        if (!ObjectUtil.isNull(listaArqSemGEN0015)) {
            this.listaArqSemGEN0015 = listaArqSemGEN0015;
            this.arqSemGEN0015 = (long) listaArqSemGEN0015.size();
        }
    }

    /**
     * @return o atributo listaArqEmProcessamento
     */
    public List<LogRecebimentoArquivoDDA> getListaArqEmProcessamento() {
        return listaArqEmProcessamento;
    }

    /**
     * Define o atributo listaArqEmProcessamento
     */
    public void setListaArqEmProcessamento(List<LogRecebimentoArquivoDDA> listaArqEmProcessamento) {
        if (!ObjectUtil.isNull(listaArqEmProcessamento)) {
            this.listaArqEmProcessamento = listaArqEmProcessamento;
            this.arqEmProcessamento = (long) listaArqEmProcessamento.size();
        }
    }

}
