/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.cache
 * Arquivo:         CacheLoad.java
 * Data Criação:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.cache;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CacheDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * CacheLoad é responsável por
 * 
 * @author Felipe.Rosa
 */
public class CacheLoad implements Serializable {

    private static final String LOG_DDA_CACHE = "[DDA] Cache [";

    private static final long serialVersionUID = -3912964356633135490L;

    private static CacheLoad instance = new CacheLoad();

    private boolean isCarregando;

    private boolean isPrimeiraExecucao = Boolean.TRUE; // Flag para identificar se é a primeira execução do servidor

    /**
     * 
     */
    private CacheLoad() {

    }

    /**
     * Método responsável por
     * 
     * @return CacheLoad
     */
    public static CacheLoad getInstance() {
        return instance;
    }

    /**
     * Faz uma varredura constante em todos os caches a fim de verificar seu status, ou seja, se é necessário efetuar o carregamento do cache junto ao banco de
     * dados
     * 
     * @return
     * @throws SSPBComumException
     */
    public void sondarCache() throws ComumException {
        if (!isCarregando) {
            try {
                isCarregando = Boolean.TRUE;
                verificarCache();
            } finally {
                isCarregando = Boolean.FALSE;
                isPrimeiraExecucao = Boolean.FALSE;
            }
        }
    }

    /**
     * Verifica se é necessário efetuar carregamento de cache
     * 
     * @param aCacheEnum
     * @return
     * @throws ComumException
     */
    private void verificarCache() throws ComumException {
        for (CacheEnum itCacheEnum : CacheEnum.values()) {

            CacheDto cacheDto = getMapCacheCentral(itCacheEnum);

            // Carregando um novo cache
            if (isCarregarCache(itCacheEnum, cacheDto)) {
                carregarCache(itCacheEnum);
            }

        }
    }

    /**
     * Verifica se cache será carregado de acordo com situação das variáveis
     * 
     * @param cacheEnum
     * @param cacheDto
     * @return boolean
     * 
     */
    private boolean isCarregarCache(CacheEnum cacheEnum, CacheDto cacheDto) {
        return ObjectUtil.isNull(cacheDto) || cacheDto.isCarregarCache() || this.isPrimeiraExecucao || isTimeOutCache(cacheEnum);
    }

    /**
     * Verifica se é necessário atualizar o cache pelo período definido para o objeto em questão
     * 
     * @param cacheEnum
     * @return boolean
     * 
     */
    private boolean isTimeOutCache(CacheEnum cacheEnum) {
        CacheDto cacheDto = getMapCacheCentral(cacheEnum);
        Integer timeOut = cacheEnum.getTimeOut();
        if (ObjectUtil.isEmpty(timeOut)) {
            getLogger().debug(LOG_DDA_CACHE + cacheEnum.name() + "] sem timeout parametrizado...");
            return Boolean.FALSE;
        }
        Date dataUltimaAtualizacao = cacheDto.getDataUltimaAtualizacao();
        Date dataProximaAtualizacao = DateUtil.incrementarData(dataUltimaAtualizacao, Calendar.HOUR, timeOut.intValue());
        Boolean isAtualizar = DateUtil.maiorQue(new Date(), dataProximaAtualizacao);
        getLogger().debug(LOG_DDA_CACHE + cacheEnum.name() + "] expirado: " + isAtualizar);
        return isAtualizar;
    }

    /**
     * Efetua o carregamento imediato de um determinado cache junto a classe e método solicitado definidos em CacheEnum
     * 
     * @param cacheEnum
     * @return
     * @throws ComumException CacheDto
     */
    public CacheDto carregarCache(CacheEnum cacheEnum) throws ComumException {
        CacheDto cacheDto = null;
        Object objExe = null;
        try {
            getLogger().debug(LOG_DDA_CACHE + cacheEnum.name() + "] carregando...");
            Class<?> classe = cacheEnum.getClasse();
            Method metodoInstance = classe.getMethod(cacheEnum.getInstance());
            Object objInstance = metodoInstance.invoke(null);

            Method metodoExe = classe.getMethod(cacheEnum.obterMetodoExe());
            objExe = metodoExe.invoke(objInstance);

            cacheDto = addCache(cacheEnum, objExe);
            getLogger().debug(LOG_DDA_CACHE + cacheEnum.name() + "] carregado!");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new ComumException(e);
        }
        return cacheDto;
    }

    /**
     * Adiciona objeto cacheado em DTO específico para gerenciamento do cache
     * 
     * @param cacheEnum
     * @param obj
     */
    private CacheDto addCache(CacheEnum cacheEnum, Object obj) {
        CacheDto cacheDTO = new CacheDto(cacheEnum, obj);
        setMapCacheCentral(cacheEnum, cacheDTO);
        return cacheDTO;
    }

    /**
     * Define objeto obtido em mapa central gerenciado pelo CacheCentral
     * 
     * @param cacheEnum
     * @param cacheDTO
     */
    private void setMapCacheCentral(CacheEnum cacheEnum, CacheDto cacheDTO) {
        CacheCentral.getInstance().setMapCache(cacheEnum, cacheDTO);
    }

    /**
     * Obtêm o objeto em específico do mapa principal de armazenamento de todos os caches
     * 
     * @param cacheEnum
     * @return CacheDto
     * 
     */
    private CacheDto getMapCacheCentral(CacheEnum cacheEnum) {
        return CacheCentral.getInstance().getMapCache(cacheEnum);
    }

    /**
     * Log
     * 
     * @return
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(CacheLoad.class);
    }

}
