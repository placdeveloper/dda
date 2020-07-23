/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         RequisicaoCache.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * RequisicaoCache é responsável por
 * 
 * @author Felipe.Rosa
 */
@Entity
@Table(name = "REQUISICAOCACHE", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.RequisicaoCacheVO")
public class RequisicaoCache extends SicoobDDAEntidade {

    private static final long serialVersionUID = 1790365587860815169L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRequisicaoCache;

    private DateTimeDB dataHoraRequisicao;

    private String descCache;

    @OneToOne
    @JoinColumn(name = "CODSERVIDORDDAREQUISITANTE", nullable = false)
    private ServidorDDA servidorDDARequisitante;

    @OneToOne
    @JoinColumn(name = "CODSERVIDORDDADESTINO", nullable = true)
    private ServidorDDA servidorDDADestino;

    @OneToMany(mappedBy = "requisicaoCache")
    private List<AtualizacaoCache> listaAtualizacaoCacheServidor;

    /**
     * 
     */
    public RequisicaoCache() {
        super();
    }

    /**
     * @param cache
     * @param servidorDDA
     */
    public RequisicaoCache(String descCache) {
        super();
        this.dataHoraRequisicao = new DateTimeDB();
        this.descCache = descCache;
    }

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
    public DateTimeDB getDataHoraRequisicao() {
        return dataHoraRequisicao;
    }

    /**
     * Define o atributo dataHoraRequisicao
     */
    public void setDataHoraRequisicao(DateTimeDB dataHoraRequisicao) {
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
    public ServidorDDA getServidorDDARequisitante() {
        return servidorDDARequisitante;
    }

    /**
     * Define o atributo servidorDDARequisitante
     */
    public void setServidorDDARequisitante(ServidorDDA servidorDDARequisitante) {
        this.servidorDDARequisitante = servidorDDARequisitante;
    }

    /**
     * @return o atributo servidorDDADestino
     */
    public ServidorDDA getServidorDDADestino() {
        return servidorDDADestino;
    }

    /**
     * Define o atributo servidorDDADestino
     */
    public void setServidorDDADestino(ServidorDDA servidorDDADestino) {
        this.servidorDDADestino = servidorDDADestino;
    }

    /**
     * @return o atributo listaAtualizacaoCacheServidor
     */
    public List<AtualizacaoCache> getListaAtualizacaoCacheServidor() {
        if (listaAtualizacaoCacheServidor == null) {
            listaAtualizacaoCacheServidor = new ArrayList<>();
        }
        return listaAtualizacaoCacheServidor;
    }

}
