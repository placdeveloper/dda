package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParticipanteContaDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoRetDto;

/**
 * ContaCorrenteServico
 * 
 * @author George.santos
 */
public interface ContaCorrenteServico extends IntegracaoInternaServico {

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @param numContaCorrente
     * @return
     * @throws ComumException List<ParticipanteContaDTO>
     * 
     */
    List<ParticipanteContaDto> listarParticipanteConta(Integer idInstituicao, Long numContaCorrente) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @param numCpfCnpj
     * @return
     * @throws ComumException List<String>
     * 
     */
    List<String> listarNumContaCorrenteAtiva(Integer idInstituicao, String numCpfCnpj) throws ComumException;

    /**
     * Método responsável por realizar a chamado do CCO para efetivar o lançamento do rateio na conta de convênio da cooperativa
     * 
     * @param LancamentoIntegracaoDto
     * @return LancamentoIntegracaoRetDto
     * @throws ComumException
     * 
     */
    LancamentoIntegracaoRetDto gravarLancamentoIntegracao(LancamentoIntegracaoDto lancamentoIntegracaoDto) throws ComumException;

}
