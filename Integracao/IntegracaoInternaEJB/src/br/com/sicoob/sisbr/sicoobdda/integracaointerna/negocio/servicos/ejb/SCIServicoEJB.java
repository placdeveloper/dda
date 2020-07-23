package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.infraestrutura.FabricaServicos;
import br.com.bancoob.infraestrutura.cache.ServicoCache;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.InstituicaoDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.interfaces.SCIServicoLocal;

/**
 * SCIServicoEJB
 * 
 * @author Samuell.Ramos
 */
@Stateless
@Local({ SCIServicoLocal.class })
public class SCIServicoEJB extends IntegracaoInternaServicoEJB implements SCIServicoLocal {

    private static final String KEY_CACHE_ID_INSTITUICAO = "SICOOBDDA_CACHE_INSTITUICAO_ID_INSTITUICAO_";
    private static final String KEY_CACHE_NUM_COOPERATIVA = "SICOOBDDA_CACHE_INSTITUICAO_NUM_COOPERATIVA_";
    private static final int CACHE_TIMEOUT = 12 * DateUtils.MILLIS_IN_HOUR;

    /**
     * Obter instituicao do cache por id instituicao.
     * 
     * @param idInstituicao the id instituicao
     * @return instituicao
     */
    public InstituicaoDto obterInstituicaoCache(Integer idInstituicao) throws ComumException {
        ServicoCache servicoCache = FabricaServicos.getInstance().criarServicoCache();
        carregarCache(servicoCache);

        String chaveIdInstituicao = gerarChaveCacheIdInstituicao(idInstituicao);
        InstituicaoDto instituicaoDTO = (InstituicaoDto) servicoCache.recuperar(chaveIdInstituicao);

        InstituicaoDelegate instituicaoVIWDelegate = ComumFabricaDelegates.getInstance().getInstituicaoDelegate();

        if (ObjectUtil.isNull(instituicaoDTO)) {
            instituicaoDTO = instituicaoVIWDelegate.obterInstituicaoView(idInstituicao);
            if (!ObjectUtil.isNull(instituicaoDTO)) {
                armazenarCache(servicoCache, chaveIdInstituicao, gerarChaveCacheNumCooperativa(instituicaoDTO.getNumCooperativa()), instituicaoDTO);
            }
        }
        return instituicaoDTO;
    }

    /**
     * Obter instituicao do cache por numero cooperativa.
     * 
     * @param numCooperativa the num cooperativa
     * @return instituicao
     */
    public InstituicaoDto obterInstituicaoPorCooperativaCache(Integer numeroCooperativa) throws ComumException {
        ServicoCache servicoCache = FabricaServicos.getInstance().criarServicoCache();
        carregarCache(servicoCache);

        String chaveNumCoop = gerarChaveCacheNumCooperativa(numeroCooperativa);
        InstituicaoDto instituicaoDTO = (InstituicaoDto) servicoCache.recuperar(chaveNumCoop);

        if (ObjectUtil.isNull(instituicaoDTO)) {
            InstituicaoDelegate instituicaoVIWDelegate = ComumFabricaDelegates.getInstance().getInstituicaoDelegate();

            instituicaoDTO = instituicaoVIWDelegate.obterInstituicaoPorCooperativaView(numeroCooperativa);
            if (!ObjectUtil.isNull(instituicaoDTO)) {
                armazenarCache(servicoCache, gerarChaveCacheIdInstituicao(instituicaoDTO.getIdInstituicao()), chaveNumCoop, instituicaoDTO);
            }
        }
        return instituicaoDTO;
    }

    /**
     * Método responsável por verificar se o Cache está carregado com as instituiçõs principais. Se não estiver carrega tudo.
     * 
     * @param servicoCache
     * 
     */
    private void carregarCache(ServicoCache servicoCache) throws ComumException {
        InstituicaoDto instituicaoSicoob = (InstituicaoDto) servicoCache.recuperar(gerarChaveCacheIdInstituicao(Constantes.ID_SICOOB));
        if (ObjectUtil.isNull(instituicaoSicoob)) {
            InstituicaoDelegate instituicaoVIWDelegate = ComumFabricaDelegates.getInstance().getInstituicaoDelegate();

            for (InstituicaoDto dto : instituicaoVIWDelegate.listarInstituicao()) {
                armazenarCache(servicoCache, gerarChaveCacheIdInstituicao(dto.getIdInstituicao()), gerarChaveCacheNumCooperativa(dto.getNumCooperativa()), dto);
            }
        }
    }

    /**
     * Método responsável por armazenar instituição no cache
     * 
     * @param servicoCache
     * @param chaveIdInstituicao, String chaveNumCooperativa
     * @param instituicaoDTO void
     * 
     */
    private void armazenarCache(ServicoCache servicoCache, String chaveIdInstituicao, String chaveNumCooperativa, InstituicaoDto instituicaoDTO) {
        servicoCache.armazenar(chaveIdInstituicao, instituicaoDTO, CACHE_TIMEOUT);
        servicoCache.armazenar(chaveNumCooperativa, instituicaoDTO, CACHE_TIMEOUT);
    }

    /**
     * Gerar chave do cache por id instituicao.
     * 
     * @param idInstituicao the id instituicao
     * @return string
     */
    private String gerarChaveCacheIdInstituicao(Integer idInstituicao) {
        return KEY_CACHE_ID_INSTITUICAO + idInstituicao;
    }

    private String gerarChaveCacheNumCooperativa(Integer numCooperativa) {
        return KEY_CACHE_NUM_COOPERATIVA + numCooperativa;
    }
}
