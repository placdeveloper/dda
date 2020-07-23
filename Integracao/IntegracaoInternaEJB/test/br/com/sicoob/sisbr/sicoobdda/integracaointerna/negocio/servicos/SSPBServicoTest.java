package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos;

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

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.SSPBServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.util.ConstantesTeste;
import br.com.sicoob.sisbr.sspb.api.negocio.delegates.ExposicaoCriptografiaDelegate;
import br.com.sicoob.sisbr.sspb.api.negocio.delegates.SSPBAPIFabricaDelegates;
import br.com.sicoob.sisbr.sspb.api.negocio.descriptors.EnumCriptCamara;
import br.com.sicoob.sisbr.sspb.api.negocio.descriptors.EnumCriptIndicativo;
import br.com.sicoob.sisbr.sspb.api.negocio.excecao.SSPBAPIException;

/**
 * SSPBServicoTest é responsável por testar todas
 * as funcionalidades dos métodos da classe
 * SSPBServicoEJB
 * 
 * @author Tayrone.Oliveira
 * @since 01/06/2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({
    SSPBAPIFabricaDelegates.class,
    ExposicaoCriptografiaDelegate.class
    })
public class SSPBServicoTest extends Mockito {
    
    //INJECT & MOCKS =======================================================================
    @InjectMocks
    private SSPBServicoEJB ejb;
    
    @Mock
    private SSPBAPIFabricaDelegates fabrica;
    
    @Mock
    private ExposicaoCriptografiaDelegate exposicaoCriptografiaDelegate;
    
    private static byte[] BYTE_ARRAY = null;
    
    //INIT =================================================================================
    /**
     * Método responsável por iniciar todas as 
     * atividades "mockadas" dos delegates que
     * serão usados nos testes decorrentes.
     * 
     */
    @Before
    public void init() {
        PowerMockito.mockStatic(SSPBAPIFabricaDelegates.class);
        when(SSPBAPIFabricaDelegates.getInstance()).thenReturn(fabrica);
        when(fabrica.criarExposicaoCriptografiaDelegate()).thenReturn(exposicaoCriptografiaDelegate);
    }
    
    //TESTS ================================================================================
    /**
     * Método responsável por testar
     * SUCESSO e FALHA do método criptografar
     * 
     */
    @Test
    public void criptografar() {
        assertEquals(Constantes.TESTE_SUCESSO, criptografar(ConstantesTeste.SUCCESS));
        assertEquals(Constantes.TESTE_FALHA, criptografar(ConstantesTeste.FAILED));
    }
    
    /**
     * Método responsável por testar
     * SUCESSO e FALHA do método decriptar
     * 
     */
    @Test
    public void decriptar() {
        assertEquals(Constantes.TESTE_SUCESSO, decriptar(ConstantesTeste.SUCCESS));
        assertEquals(Constantes.TESTE_FALHA, decriptar(ConstantesTeste.FAILED));
    }
    
    //PRIVATE METHODS ======================================================================
   
    /**
     * Método responsável por 
     * facilitar o acesso à classe
     * estática 'Assert' e ao seu 
     * método 'assertEquals'
     * 
     * @param retorno
     * @param metodo void
     * 
     */
    private void assertEquals(String msg, Object method) {
        Assert.assertEquals(msg, method);
    }
    
    /**
     * Método responsável por instanciar
     * todos os métodos e situações (de SUCESSO e FALHA) do serviço
     * 'ExposicaoCriptografiaDelegate'
     * 
     * @param success void
     * 
     */
    private void whenExposicaoCriptografiaDelegate(Boolean success) {
        try {      
            if(success) {
                when(exposicaoCriptografiaDelegate.criptografar(retornarByteArray(), EnumCriptCamara.SPB02_CIP_NPC, EnumCriptIndicativo.I8))
                .thenReturn(retornarByteArray());
                
                when(exposicaoCriptografiaDelegate.decriptar(retornarByteArray(), EnumCriptCamara.SPB02_CIP_NPC))
                .thenReturn(retornarByteArray());
                
            } else {
                when(exposicaoCriptografiaDelegate.criptografar(retornarByteArray(), EnumCriptCamara.SPB02_CIP_NPC, EnumCriptIndicativo.I8))
                .thenThrow(new SSPBAPIException("falhou"));
                
                when(exposicaoCriptografiaDelegate.decriptar(retornarByteArray(), EnumCriptCamara.SPB02_CIP_NPC))
                .thenThrow(new SSPBAPIException("falhou"));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    /**
     * Método responsável por retornar
     * uma instancia estática de um ByteArray populada
     * caso ainda esteja nula
     * 
     * @return byte[]
     * 
     */
    public byte[] retornarByteArray() {
        if(ObjectUtil.isNull(BYTE_ARRAY)) {
            BYTE_ARRAY = new byte[Constantes.INTEGER_DEZ];   
        }
        return BYTE_ARRAY;
    }
    
    /**
     * Método responsável por auxiliar
     * teste do método 'criptografar'
     * retornando SUCESSO e FALHA para o 
     * método de teste.
     * 
     * @param success
     * @return String
     * 
     */
    private String criptografar(Boolean success) {
        try {
            whenExposicaoCriptografiaDelegate(success);
            ejb.criptografar(retornarByteArray());
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }
    
    /**
     * Método responsável por auxiliar
     * teste do método 'decriptar'
     * retornando SUCESSO e FALHA para o 
     * método de teste.
     * 
     * @param success
     * @return String
     * 
     */
    private String decriptar(Boolean success) {
        try {
            whenExposicaoCriptografiaDelegate(success);
            ejb.decriptar(retornarByteArray());
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }
}
