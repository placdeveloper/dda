package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogRecebimentoArquivoDDAVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqVarreduraDto")
public class MonitoracaoArqVarreduraDTO extends BancoobDTO {

    private Long arqDisponivel;
    private Long gen0015SemArq;
    private Long arqSemGEN0015;
    private Long arqEmProcessamento;
    private DateTime dataHoraAtualizacao;
    private Integer parametroTempoAtualizacao;
    private Integer alertaArqDisponivel;
    private Integer alertaGEN0015SemArq;
    private Integer alertaArqSemGEN0015;
    private Integer alertaArqEmProcessamento;
    private Boolean bolAlertaGEN0015SemArq;
    private List<LogRecebimentoArquivoDDAVO> listaArqDisponivel;
    private List<LogRecebimentoArquivoDDAVO> listaGEN0015SemArq;
    private List<LogRecebimentoArquivoDDAVO> listaArqSemGEN0015;
    private List<LogRecebimentoArquivoDDAVO> listaArqEmProcessamento;

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
    public DateTime getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    /**
     * Define o atributo dataHoraAtualizacao
     */
    public void setDataHoraAtualizacao(DateTime dataHoraAtualizacao) {
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
    public List<LogRecebimentoArquivoDDAVO> getListaArqDisponivel() {
        return listaArqDisponivel;
    }

    /**
     * Define o atributo listaArqDisponivel
     */
    public void setListaArqDisponivel(List<LogRecebimentoArquivoDDAVO> listaArqDisponivel) {
        this.listaArqDisponivel = listaArqDisponivel;
    }

    /**
     * @return o atributo listaGEN0015SemArq
     */
    public List<LogRecebimentoArquivoDDAVO> getListaGEN0015SemArq() {
        return listaGEN0015SemArq;
    }

    /**
     * Define o atributo listaGEN0015SemArq
     */
    public void setListaGEN0015SemArq(List<LogRecebimentoArquivoDDAVO> listaGEN0015SemArq) {
        this.listaGEN0015SemArq = listaGEN0015SemArq;
    }

    /**
     * @return o atributo listaArqSemGEN0015
     */
    public List<LogRecebimentoArquivoDDAVO> getListaArqSemGEN0015() {
        return listaArqSemGEN0015;
    }

    /**
     * Define o atributo listaArqSemGEN0015
     */
    public void setListaArqSemGEN0015(List<LogRecebimentoArquivoDDAVO> listaArqSemGEN0015) {
        this.listaArqSemGEN0015 = listaArqSemGEN0015;
    }

    /**
     * @return o atributo listaArqEmProcessamento
     */
    public List<LogRecebimentoArquivoDDAVO> getListaArqEmProcessamento() {
        return listaArqEmProcessamento;
    }

    /**
     * Define o atributo listaArqEmProcessamento
     */
    public void setListaArqEmProcessamento(List<LogRecebimentoArquivoDDAVO> listaArqEmProcessamento) {
        this.listaArqEmProcessamento = listaArqEmProcessamento;
    }

}
