/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.vo
 * Arquivo:         RequisicaoCacheVO.java
 * Data Criação:    1 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * RequisicaoCacheVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache")
public class RequisicaoCacheVO extends BancoobVO {

    private Long idRequisicaoCache;
    private DateTime dataHoraRequisicao;
    private String descCache;
    private ServidorDDAVO servidorDDARequisitante;
    private ServidorDDAVO servidorDDADestino;
    private List<AtualizacaoCacheVO> listaAtualizacaoCacheServidor;

    /**
     * @return o atributo idRequisicaoCache
     */
    public Long getIdRequisicaoCache() {
        return idRequisicaoCache;
    }

    /**
     * Define o atributo idRequisicaoCache
     */
    public void setIdRequisicaoCache(Long idRequisicaoCache) {
        this.idRequisicaoCache = idRequisicaoCache;
    }

    /**
     * @return o atributo dataHoraRequisicao
     */
    public DateTime getDataHoraRequisicao() {
        return dataHoraRequisicao;
    }

    /**
     * Define o atributo dataHoraRequisicao
     */
    public void setDataHoraRequisicao(DateTime dataHoraRequisicao) {
        this.dataHoraRequisicao = dataHoraRequisicao;
    }

    /**
     * @return o atributo descCache
     */
    public String getDescCache() {
        return descCache;
    }

    /**
     * Define o atributo descCache
     */
    public void setDescCache(String descCache) {
        this.descCache = descCache;
    }

    /**
     * @return o atributo servidorDDARequisitante
     */
    public ServidorDDAVO getServidorDDARequisitante() {
        return servidorDDARequisitante;
    }

    /**
     * Define o atributo servidorDDARequisitante
     */
    public void setServidorDDARequisitante(ServidorDDAVO servidorDDARequisitante) {
        this.servidorDDARequisitante = servidorDDARequisitante;
    }

    /**
     * @return o atributo servidorDDADestino
     */
    public ServidorDDAVO getServidorDDADestino() {
        return servidorDDADestino;
    }

    /**
     * Define o atributo servidorDDADestino
     */
    public void setServidorDDADestino(ServidorDDAVO servidorDDADestino) {
        this.servidorDDADestino = servidorDDADestino;
    }

    /**
     * @return o atributo listaAtualizacaoCacheServidor
     */
    public List<AtualizacaoCacheVO> getListaAtualizacaoCacheServidor() {
        return listaAtualizacaoCacheServidor;
    }

    /**
     * Define o atributo listaAtualizacaoCacheServidor
     */
    public void setListaAtualizacaoCacheServidor(List<AtualizacaoCacheVO> listaAtualizacaoCacheServidor) {
        this.listaAtualizacaoCacheServidor = listaAtualizacaoCacheServidor;
    }

}
