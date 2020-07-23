package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.infraestrutura.FabricaServicos;
import br.com.bancoob.infraestrutura.cache.ServicoCache;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.InstituicaoDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.SCIServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.util.ConstantesTeste;

/**
 * SCIServicoTest é responsável por testar todas
 * as funcionalidades dos métodos da classe
 * SCIServicoTest
 * 
 * @author Tayrone.Oliveira
 * @since 01/06/2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ComumFabricaDelegates.class, FabricaServicos.class})
public class SCIServicoTest extends Mockito{
    
    //INJECTIONS & MOCKS ================================================================
    @InjectMocks
    private SCIServicoEJB ejb;
    
    @Mock
    private ComumFabricaDelegates fabrica;
    
    @Mock
    private InstituicaoDelegate instituicaoVIWDelegate;
    
    @Mock
    private ServicoCache servico;
    
    @Mock
    private FabricaServicos fabricaServicos;
    
    private final static String KEY_CACHE_ID_INSTITUICAO = "KEY_CACHE_ID_INSTITUICAO"+Constantes.ID_INST;
    
    //INIT ================================================================================
    /**
     * Método responsável por iniciar todas as 
     * atividades "mockadas" dos delegates que
     * serão usados nos testes decorrentes.
     * 
     */
    @Before
    public void init() {
        PowerMockito.mockStatic(ComumFabricaDelegates.class);
        when(ComumFabricaDelegates.getInstance()).thenReturn(fabrica);
        when(fabrica.getInstituicaoDelegate()).thenReturn(instituicaoVIWDelegate);
        
        PowerMockito.mockStatic(FabricaServicos.class);
        when(FabricaServicos.getInstance()).thenReturn(fabricaServicos);
        when(fabricaServicos.criarServicoCache()).thenReturn(servico);
        
    }
    
    //TEST ================================================================================
    /**
     * Método responsável por testar
     * SUCESSO e FALHA do método obterInstituicaoCache
     * Testa também situações com Objeto vazio.
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void obterInstituicaoCache() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, obterInstituicaoCache(ConstantesTeste.ANULAR_EXCECAO));
        assertEquals(Constantes.TESTE_SUCESSO, obterInstituicaoCache(ConstantesTeste.EMPTY_OBJECT));
    }
    
    /**
     * Método responsável por testar
     * SUCESSO e FALHA do método obterInstituicaoPorCooperativaCache
     * Testa também situações com Objeto vazio.
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void obterInstituicaoPorCooperativaCache() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, obterInstituicaoPorCooperativaCache(ConstantesTeste.ANULAR_EXCECAO));
        assertEquals(Constantes.TESTE_SUCESSO, obterInstituicaoPorCooperativaCache(ConstantesTeste.EMPTY_OBJECT));
    }
    //PRIVATE METHODS =====================================================================
    /**
     * Método responsável por 
     * facilitar o acesso à classe
     * estática 'Assert' e ao seu 
     * método 'assertEquals'
     * 
     * @param msg
     * @param method void
     * 
     */
    private void assertEquals(String msg, Object method) {
        Assert.assertEquals(msg, method);
    }
    
    /**
     * Método responsável por instanciar
     * todos os métodos do serviço
     * 'InstituicaoVIWDelegate'
     * 
     */
    private void whenInstituicaoDelegate() {
        try {
            when(instituicaoVIWDelegate.obterInstituicao(anyInt())).thenReturn(recuperarInstituicaoDTO());

            when(instituicaoVIWDelegate.obterInstituicaoPorCooperativa(anyInt())).thenReturn(recuperarInstituicaoDTO());

            when(instituicaoVIWDelegate.listarInstituicao()).thenReturn(recuperarListaInstituicaoDTO());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    /**
     * Método responsável por instanciar
     * todos os métodos do serviço
     * 'ServicoCache'
     * 
     */
    private void getServico() {
        when(servico.recuperar(KEY_CACHE_ID_INSTITUICAO))
        .thenReturn(recuperarInstituicaoDTO());
    }
    
    /**
     * Método responsável por retornar
     * Objeto 'InstituicaoDto' populado
     * 
     * @return InstituicaoDto
     * 
     */
    private InstituicaoDto recuperarInstituicaoDTO() {
        InstituicaoDto dto = new InstituicaoDto();
        dto.setNumCooperativa(Constantes.INTEGER_UM);
        dto.setIdInstituicao(Constantes.INTEGER_UM);
        
        return dto;
    }
    
    /**
     * Método responsável por retornar
     * uma lista de 'InstituicaoDto' populada
     * 
     * @return List<InstituicaoDto>
     * 
     */
    private List<InstituicaoDto> recuperarListaInstituicaoDTO() {
        List<InstituicaoDto> list = new ArrayList<InstituicaoDto>();
        list.add(recuperarInstituicaoDTO());
        
        return list;
    }
    
    /**
     * Método responsável por auxiliar
     * teste do método 'obterInstituicaoCache'
     * retornando SUCESSO e FALHA para o 
     * método de teste.
     * É ainda responsável por filtrar situações para
     * testar Objeto vazio
     * 
     * @param emptyObject
     * @return
     * @throws ComumException String
     * 
     */
    private String obterInstituicaoCache(int emptyObject) throws ComumException {
        whenInstituicaoDelegate();
        getServico();
       
        if(ConstantesTeste.EMPTY_OBJECT == emptyObject) {
            ejb.obterInstituicaoCache(Constantes.INTEGER_MIL);
        } else {
            ejb.obterInstituicaoCache(Constantes.ID_INST);
        }
        return Constantes.TESTE_SUCESSO;
    }
    
    /**
     * Método responsável por auxiliar
     * teste do método 'obterInstituicaoPorCooperativaCache'
     * retornando SUCESSO e FALHA para o 
     * método de teste.
     * É ainda responsável por filtrar situações para
     * testar Objeto vazio
     * 
     * @param emptyObject
     * @return
     * @throws ComumException String
     * 
     */
    private String obterInstituicaoPorCooperativaCache(int emptyObject) throws ComumException {
        whenInstituicaoDelegate();
        getServico();
        
        if(ConstantesTeste.EMPTY_OBJECT == emptyObject) {
            ejb.obterInstituicaoPorCooperativaCache(Constantes.INTEGER_MIL);
        } else {
            ejb.obterInstituicaoPorCooperativaCache(Constantes.ID_INST);
        }
        return Constantes.TESTE_SUCESSO;
        
    }
}
