/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.vo
 * Arquivo:         AtualizacaoCacheVO.java
 * Data Criação:    1 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * AtualizacaoCacheVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.AtualizacaoCache")
public class AtualizacaoCacheVO extends BancoobVO {

    private Long idAtualizacaoCache;
    private DateTime dataHoraAtualizacao;
    private RequisicaoCacheVO requisicaoCache;
    private ServidorDDAVO servidorDDA;

    /**
     * @return o atributo idAtualizacaoCache
     */
    public Long getIdAtualizacaoCache() {
        return idAtualizacaoCache;
    }

    /**
     * Define o atributo idAtualizacaoCache
     */
    public void setIdAtualizacaoCache(Long idAtualizacaoCache) {
        this.idAtualizacaoCache = idAtualizacaoCache;
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
     * @return o atributo requisicaoCache
     */
    public RequisicaoCacheVO getRequisicaoCache() {
        return requisicaoCache;
    }

    /**
     * Define o atributo requisicaoCache
     */
    public void setRequisicaoCache(RequisicaoCacheVO requisicaoCache) {
        this.requisicaoCache = requisicaoCache;
    }

    /**
     * @return o atributo servidorDDA
     */
    public ServidorDDAVO getServidorDDA() {
        return servidorDDA;
    }

    /**
     * Define o atributo servidorDDA
     */
    public void setServidorDDA(ServidorDDAVO servidorDDA) {
        this.servidorDDA = servidorDDA;
    }

}
