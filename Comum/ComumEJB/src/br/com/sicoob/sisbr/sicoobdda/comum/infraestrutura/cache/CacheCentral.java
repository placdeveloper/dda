/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.cache
 * Arquivo:         CacheCentral.java
 * Data Criação:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CacheDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * CacheCentral é responsável por
 * 
 * @author Felipe.Rosa
 */
public class CacheCentral implements Serializable {

    private static final long serialVersionUID = 6619573643953731221L;

    private static Map<CacheEnum, CacheDto> mapCache = new HashMap<>();

    private static CacheCentral instance = new CacheCentral();

    private CacheCentral() {

    }

    /**
     * @return A instância única de CacheCentral
     */
    public static CacheCentral getInstance() {
        return instance;
    }

    /**
     * Obtêm Objeto do cache
     * 
     * @param cacheEnum
     * @return
     */
    public CacheDto getCache(CacheEnum cacheEnum) {
        return getMapCache(cacheEnum);
    }

    /**
     * Retornar estado atual do objeto junto ao mapa
     * 
     * @param aCacheEnum
     * @return
     * @throws ComumException
     */
    public CacheDto getMapCache(CacheEnum aCacheEnum) {
        return mapCache.get(aCacheEnum);
    }

    /**
     * Adiciona objeto cacheado em DTO específico para gerenciamento do cache
     * 
     * @param aCacheEnum
     * @param aObj
     */
    public void setMapCache(CacheEnum aCacheEnum, CacheDto aCacheDto) {
        mapCache.put(aCacheEnum, aCacheDto);
    }

    /**
     * @return
     */
    public boolean isCarregado(CacheEnum cacheEnum) {
        return !ObjectUtil.isNull(CacheCentral.getInstance().getMapCache(cacheEnum));
    }

    /**
     * Verifica se o cache está carregado
     * 
     * @return
     */
    public boolean isCarregado() {
        for (CacheEnum cacheEnum : CacheEnum.values()) {
            if (!isCarregado(cacheEnum)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

}
