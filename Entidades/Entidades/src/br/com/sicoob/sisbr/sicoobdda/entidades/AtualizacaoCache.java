/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         AtualizacaoServidorCache.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * AtualizacaoServidorCache é responsável por
 * 
 * @author Felipe.Rosa
 */
@Entity
@Table(name = "ATUALIZACAOCACHE", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.AtualizacaoCacheVO")
public class AtualizacaoCache extends SicoobDDAEntidade {

    /**
     * 
     */
    private static final long serialVersionUID = 2693098630262730055L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtualizacaoCache;

    private DateTimeDB dataHoraAtualizacao;

    @ManyToOne
    @JoinColumn(name = "IDREQUISICAOCACHE", nullable = false)
    private RequisicaoCache requisicaoCache;

    @OneToOne
    @JoinColumn(name = "CODSERVIDORDDAATUALIZADO", nullable = false)
    private ServidorDDA servidorDDA;

    /**
     * 
     */
    public AtualizacaoCache() {
        super();
    }

    /**
     * @param requisicaoCache
     * @param servidorDDA
     */
    public AtualizacaoCache(RequisicaoCache requisicaoCache, ServidorDDA servidorDDA) {
        super();
        this.dataHoraAtualizacao = new DateTimeDB();
        this.requisicaoCache = requisicaoCache;
        this.servidorDDA = servidorDDA;
    }

    /**
     * @return o atributo idAtualizacaoCacheServidor
     */
    public Long getIdAtualizacaoCache() {
        return idAtualizacaoCache;
    }

    /**
     * Define o atributo idAtualizacaoCacheServidor
     */
    public void setIdAtualizacaoCacheServidor(Long idAtualizacaoCacheServidor) {
        this.idAtualizacaoCache = idAtualizacaoCacheServidor;
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
     * @return o atributo requisicaoCache
     */
    public RequisicaoCache getRequisicaoCache() {
        return requisicaoCache;
    }

    /**
     * Define o atributo requisicaoCache
     */
    public void setRequisicaoCache(RequisicaoCache requisicaoCache) {
        this.requisicaoCache = requisicaoCache;
    }

    /**
     * @return o atributo servidorDDA
     */
    public ServidorDDA getServidorDDA() {
        return servidorDDA;
    }

    /**
     * Define o atributo servidorDDA
     */
    public void setServidorDDA(ServidorDDA servidorDDA) {
        this.servidorDDA = servidorDDA;
    }

}
