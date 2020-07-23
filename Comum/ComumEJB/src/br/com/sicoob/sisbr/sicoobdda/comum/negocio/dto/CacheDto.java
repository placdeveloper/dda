/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         CacheDto.java
 * Data Criação:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;

/**
 * CacheDto é responsável por 
 * 
 * @author Felipe.Rosa
 */
public class CacheDto extends BancoobDto {

    private static final long serialVersionUID = 1249565182605177242L;

    private CacheEnum cacheEnum;

    private Date dataUltimaAtualizacao;

    private boolean carregarCache;

    private Object objCache;

    /**
     * @param cacheEnum
     * @param carregarCache
     * @param objCache
     */
    public CacheDto(CacheEnum cacheEnum, Object objCache) {
        super();
        this.cacheEnum = cacheEnum;
        this.objCache = objCache;
        this.dataUltimaAtualizacao = new Date();
    }

    /**
     * @return cacheEnum
     */
    public CacheEnum getCacheEnum() {
        return cacheEnum;
    }

    /**
     * @param cacheEnum
     */
    public void setCacheEnum(CacheEnum cacheEnum) {
        this.cacheEnum = cacheEnum;
    }

    /**
     * @return dataUltimaAtualizacao
     */
    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    /**
     * @param dataUltimaAtualizacao
     */
    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    /**
     * @return carregarCache
     */
    public boolean isCarregarCache() {
        return carregarCache;
    }

    /**
     * @param carregarCache
     */
    public void setCarregarCache(boolean carregarCache) {
        this.carregarCache = carregarCache;
    }

    /**
     * @return objCache
     */
    public Object getObjCache() {
        return objCache;
    }

    /**
     * @param objCache
     */
    public void setObjCache(Object objCache) {
        this.objCache = objCache;
    }

}
