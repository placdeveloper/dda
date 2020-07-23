package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.ExtratoDDA")
public class ExtratoDDAVO extends BancoobVO {

    private Long id;
    private LogRecebimentoArquivoDDAVO logRecebimentoArquivoDDA;
    private DateTime dataInicioApuracao;
    private DateTime dataFimApuracao;
    private Integer numControleDDA;
    private DateTime dataHoraApuracao;
    private DateTime dataHoraInclusao;

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
     * @return o atributo logRecebimentoArquivoDDA
     */
    public LogRecebimentoArquivoDDAVO getLogRecebimentoArquivoDDA() {
        return logRecebimentoArquivoDDA;
    }

    /**
     * Define o atributo logRecebimentoArquivoDDA
     */
    public void setLogRecebimentoArquivoDDA(LogRecebimentoArquivoDDAVO logRecebimentoArquivoDDA) {
        this.logRecebimentoArquivoDDA = logRecebimentoArquivoDDA;
    }

    /**
     * @return o atributo dataInicioApuracao
     */
    public DateTime getDataInicioApuracao() {
        return dataInicioApuracao;
    }

    /**
     * Define o atributo dataInicioApuracao
     */
    public void setDataInicioApuracao(DateTime dataInicioApuracao) {
        this.dataInicioApuracao = dataInicioApuracao;
    }

    /**
     * @return o atributo dataFimApuracao
     */
    public DateTime getDataFimApuracao() {
        return dataFimApuracao;
    }

    /**
     * Define o atributo dataFimApuracao
     */
    public void setDataFimApuracao(DateTime dataFimApuracao) {
        this.dataFimApuracao = dataFimApuracao;
    }

    /**
     * @return o atributo numControleDDA
     */
    public Integer getNumControleDDA() {
        return numControleDDA;
    }

    /**
     * Define o atributo numControleDDA
     */
    public void setNumControleDDA(Integer numControleDDA) {
        this.numControleDDA = numControleDDA;
    }

    /**
     * @return o atributo dataHoraApuracao
     */
    public DateTime getDataHoraApuracao() {
        return dataHoraApuracao;
    }

    /**
     * Define o atributo dataHoraApuracao
     */
    public void setDataHoraApuracao(DateTime dataHoraApuracao) {
        this.dataHoraApuracao = dataHoraApuracao;
    }

    /**
     * @return o atributo dataHoraInclusao
     */
    public DateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    /**
     * Define o atributo dataHoraInclusao
     */
    public void setDataHoraInclusao(DateTime dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

}
