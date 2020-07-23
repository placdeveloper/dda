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

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cco.aberturacontacorrente.api.delegates.AberturaContaCorrenteApiFabricaDelegates;
import br.com.sicoob.cco.aberturacontacorrente.api.delegates.ContaCorrenteDelegate;
import br.com.sicoob.cco.aberturacontacorrente.api.delegates.ParticipanteContaCorrenteDelegate;
import br.com.sicoob.cco.aberturacontacorrente.api.dto.ParticipanteContaDTO;
import br.com.sicoob.cco.aberturacontacorrente.api.dto.retorno.RetContasPorCpfCnpjDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.ContaCorrenteServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.util.ConstantesTeste;

/**
 * ContaCorrenteServicoTest � respons�vel por 
 * testar Unitariamente todos os m�todos da Classe
 * ContaCorrenteServicoEJB
 * 
 * @author Tayrone.Oliveira
 * @since 01/06/2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AberturaContaCorrenteApiFabricaDelegates.class, 
                 ContaCorrenteDelegate.class, 
                 ParticipanteContaCorrenteDelegate.class
                })
public class ContaCorrenteServicoTest extends Mockito {
    
    //INJECTS & MOCKS =======================================================================
    @InjectMocks
    private ContaCorrenteServicoEJB ejb;
    
    @Mock
    private AberturaContaCorrenteApiFabricaDelegates fabrica;

    @Mock
    private ContaCorrenteDelegate ccoDelegate;
    
    @Mock
    private ParticipanteContaCorrenteDelegate participanteCcDelegate;
    
    //INIT ==================================================================================
    /**
     * M�todo respons�vel por iniciar todas as 
     * atividades "mockadas" dos delegates que
     * ser�o usados nos testes decorrentes.
     * 
     */
    @Before
    public void init() {
        PowerMockito.mockStatic(AberturaContaCorrenteApiFabricaDelegates.class);
        when(AberturaContaCorrenteApiFabricaDelegates.getInstance()).thenReturn(fabrica);
        when(fabrica.criarContaCorrenteDelegate()).thenReturn(ccoDelegate);
        
        PowerMockito.mockStatic(ParticipanteContaCorrenteDelegate.class);
        when(ParticipanteContaCorrenteDelegate.getInstance()).thenReturn(participanteCcDelegate);
    }
    
    //TESTS =================================================================================
    /**
     * M�todo respons�vel por testar
     * SUCESSO e FALHA do m�todo listarParticipanteConta
     * 
     */
    @Test
    public void listarParticipanteConta() {
        // assertEquals(Constantes.TESTE_SUCESSO, listarParticipanteConta(ConstantesTeste.SUCCESS));
        assertEquals(Constantes.TESTE_FALHA, listarParticipanteConta(ConstantesTeste.FAILED));
    }
    
    /**
     * M�todo respons�vel por testar
     * SUCESSO e FALHA do m�todo listarNumContaCorrenteAtiva
     * Testa tamb�m situa��es com Conta Ativa/Inativa.
     * 
     */
    @Test
    public void listarNumContaCorrenteAtiva() {
        assertEquals(Constantes.TESTE_SUCESSO, listarNumContaCorrenteAtiva(ConstantesTeste.SUCCESS, ConstantesTeste.SUCCESS));
        assertEquals(Constantes.TESTE_SUCESSO, listarNumContaCorrenteAtiva(ConstantesTeste.SUCCESS, ConstantesTeste.FAILED));
        assertEquals(Constantes.TESTE_FALHA, listarNumContaCorrenteAtiva(ConstantesTeste.FAILED, ConstantesTeste.SUCCESS));
    }
    
    //PRIVATE METHODS =======================================================================
    
    /**
     * M�todo respons�vel por 
     * facilitar o acesso � classe
     * est�tica 'Assert' e ao seu 
     * m�todo 'assertEquals'
     * 
     * @param retorno
     * @param metodo void
     * 
     */
    private void assertEquals(String msg, Object method) {
        Assert.assertEquals(msg, method);
    }
    
    /**
     * M�todo respons�vel por instanciar todos os m�todos e situa��es (de SUCESSO e FALHA) do servi�o 'ContaCorrenteDelegate'. Ainda filtra por Contas
     * ATIVAS/INATIVAS
     * 
     * @param success
     * @param ativa void
     * @throws BancoobException
     * 
     */
    private void whenCcoDelegate(Boolean success, Boolean ativa) throws BancoobException {
        if (success) {
            when(ccoDelegate.listarContaCorrentePorCpfCnpj(Constantes.CPF_AUX, Constantes.ID_INST)).thenReturn(recuperarListaCcDTO(ativa));
        } else {
            when(ccoDelegate.listarContaCorrentePorCpfCnpj(Constantes.CPF_AUX, Constantes.ID_INST)).thenThrow(new BancoobException("falhou"));
        }
    }
    
    /**
     * M�todo respons�vel por instanciar todos os m�todos e situa��es (de SUCESSO e FALHA) do servi�o 'ParticipanteContaCorrenteDelegate'
     * 
     * @param success void
     * @throws BancoobException
     * 
     */
    private void whenParticipanteCcDelegate(Boolean success) throws BancoobException {
        if (success) {
            when(participanteCcDelegate.listarParticipanteContaPorNumeroConta(Constantes.INTEGER_UM, Constantes.LONG_UM)).thenReturn(recuperarListaParticipanteContaDTO());
        } else {
            when(participanteCcDelegate.listarParticipanteContaPorNumeroConta(Constantes.INTEGER_UM, Constantes.LONG_UM)).thenThrow(new BancoobException("falhou"));
        }
    }
    
    /**
     * M�todo respons�vel por retornar
     * uma lista de 'ParticipanteContaDTO' populada
     * 
     * @return List<ParticipanteContaDTO>
     * 
     */
    private List<ParticipanteContaDTO> recuperarListaParticipanteContaDTO() {
        List<ParticipanteContaDTO> list = new ArrayList<ParticipanteContaDTO>();
        list.add(new ParticipanteContaDTO());
        
        return list;
    }
    
    /**
     * M�todo respons�vel por retornar uma lista de 'ContaCorrenteDTO' populada. Filtrando por contas ATIVAS/INATIVAS
     * 
     * @param ativa
     * @return List<RetContasPorCpfCnpjDTO>
     * 
     */
    private List<RetContasPorCpfCnpjDTO> recuperarListaCcDTO(Boolean ativa) {
        List<RetContasPorCpfCnpjDTO> list = new ArrayList<RetContasPorCpfCnpjDTO>();
        list.add(recuperarCcDTO(ativa));
        
        return list;
    }
    
    /**
     * M�todo respons�vel por retornar
     * Objeto 'ContaCorrenteDTO' populado
     * 
     * @param ativa
     * @return ContaCorrenteDTO
     * 
     */
    private RetContasPorCpfCnpjDTO recuperarCcDTO(Boolean ativa) {
        
        RetContasPorCpfCnpjDTO dto = new RetContasPorCpfCnpjDTO();
        dto.setNumContaCorrente(Constantes.LONG_UM);
        dto.setCodSituacaoCCO(ativa ? Constantes.INTEGER_UM : Constantes.INTEGER_ZERO);
        
        return dto;
    }
    
    /**
     * M�todo respons�vel por auxiliar
     * teste do m�todo 'listarParticipanteConta'
     * retornando SUCESSO e FALHA para o 
     * m�todo de teste
     * 
     * @param success
     * @return String
     * 
     */
    private String listarParticipanteConta(Boolean success) {
        try {
            whenParticipanteCcDelegate(success);
            ejb.listarParticipanteConta(Constantes.INTEGER_UM, Constantes.LONG_UM);
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }
    
    /**
     * M�todo respons�vel por auxiliar
     * teste do m�todo 'listarNumContaCorrenteAtiva'
     * retornando SUCESSO e FALHA para o 
     * m�todo de teste.
     * Filtra por contas ATIVAS/INATIVAS
     * 
     * @param success
     * @param ativa
     * @return String
     * 
     */
    private String listarNumContaCorrenteAtiva(Boolean success, Boolean ativa) {
        try {
            whenCcoDelegate(success, ativa);
            ejb.listarNumContaCorrenteAtiva(Constantes.ID_INST, Constantes.CPF_AUX);
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }
}
