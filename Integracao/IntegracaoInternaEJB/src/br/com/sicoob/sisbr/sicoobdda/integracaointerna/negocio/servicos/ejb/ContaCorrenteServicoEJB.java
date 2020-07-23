package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cco.aberturacontacorrente.api.delegates.AberturaContaCorrenteApiFabricaDelegates;
import br.com.sicoob.cco.aberturacontacorrente.api.delegates.ContaCorrenteDelegate;
import br.com.sicoob.cco.aberturacontacorrente.api.delegates.ParticipanteContaCorrenteDelegate;
import br.com.sicoob.cco.aberturacontacorrente.api.dto.ParticipanteContaDTO;
import br.com.sicoob.cco.aberturacontacorrente.api.dto.retorno.RetContasPorCpfCnpjDTO;
import br.com.sicoob.cco.movimentacao.api.delegates.LancamentoIntegracaoDelegate;
import br.com.sicoob.cco.movimentacao.api.delegates.MovimentacaoApiFabricaDelegates;
import br.com.sicoob.cco.movimentacao.api.dto.LancamentoIntegracaoRetDTO;
import br.com.sicoob.cco.movimentacao.api.dto.filtros.LancamentoIntegracaoDTO;
import br.com.sicoob.cco.movimentacao.api.excecoes.MovimentacaoApiNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParticipanteContaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoRetDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.interfaces.ContaCorrenteServicoLocal;

/**
 * ContaCorrenteServicoEJB
 * 
 * @author George.santos
 */
@Stateless
@Local({ ContaCorrenteServicoLocal.class })
public class ContaCorrenteServicoEJB extends IntegracaoInternaServicoEJB implements ContaCorrenteServicoLocal {

    private static final int CCO_ATIVA = 1;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.integracao.interna.privada.negocio.servicos.ContaCorrenteServico#listarContasCorrentesCliente(java.lang.Integer,
     *      java.lang.Long)
     */
    public List<ParticipanteContaDto> listarParticipanteConta(Integer idInstituicao, Long numContaCorrente) throws ComumException {
        debug("### Listando as contas correntes do cliente...");
        debug("Parâmetro - idInstituicao: " + idInstituicao);
        debug("Parâmetro - numContaCorrente: " + numContaCorrente);

        List<ParticipanteContaDto> listaParticipanteContaDto = new ArrayList<ParticipanteContaDto>();

        try {
            debug("Criando delegate da conta corrente...");

            ParticipanteContaCorrenteDelegate delegate = AberturaContaCorrenteApiFabricaDelegates.getInstance().criarParticipanteContaCorrenteDelegate();

            debug("ContaCorrenteDelegate criado: " + delegate);

            debug("Listando as contas correntes por número de cliente...");
            List<ParticipanteContaDTO> lista = delegate.listarParticipanteContaPorNumeroConta(idInstituicao, numContaCorrente);
            debug("Retorno - lista: " + lista);

            for (ParticipanteContaDTO dto : lista) {
                // Se o participante não estiver ativo não adiciona ele
                if (!ObjectUtil.isNull(dto.getBolAtivo()) && !dto.getBolAtivo().equals(CCO_ATIVA)) {
                    continue;
                }

                debug("Convertendo o participante (idPessoa): " + dto.getIdPessoa());

                ParticipanteContaDto participanteContaDto = new ParticipanteContaDto();

                ObjectUtil.copy(dto, participanteContaDto, ParticipanteContaDto.CAMPOS);

                listaParticipanteContaDto.add(participanteContaDto);
            }

            debug("Processo finalizado");
        } catch (BancoobException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        }

        return listaParticipanteContaDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ContaCorrenteServico#listarNumContaCorrenteAtiva(java.lang.Integer,
     *      java.lang.String)
     */
    public List<String> listarNumContaCorrenteAtiva(Integer idInstituicao, String numCpfCnpj) throws ComumException {
        debug("### Listando as contas correntes ativas do cliente...");
        debug("Parâmetro - idInstituicao: " + idInstituicao);
        debug("Parâmetro - numCpfCnpj: " + numCpfCnpj);

        List<String> listaNumCCO = new ArrayList<>();
        try {
            ContaCorrenteDelegate ccoDelegate = AberturaContaCorrenteApiFabricaDelegates.getInstance().criarContaCorrenteDelegate();

            List<RetContasPorCpfCnpjDTO> listaCCO = ccoDelegate.listarContaCorrentePorCpfCnpj(numCpfCnpj, idInstituicao);
            for (RetContasPorCpfCnpjDTO ccoDTO : listaCCO) {
                if (ccoDTO.getCodSituacaoCCO().equals(CCO_ATIVA)) {
                    listaNumCCO.add(ccoDTO.getNumContaCorrente().toString());
                }
            }
        } catch (BancoobException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        }
        return listaNumCCO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ContaCorrenteServico#gravarLancamentoIntegracao(br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto)
     */
    public LancamentoIntegracaoRetDto gravarLancamentoIntegracao(LancamentoIntegracaoDto lancamentoIntegracaoDto) throws ComumException {
        getLogger().debug(Constantes.STR_SEPARACAO_2 + "Realizando Lançamento Integração CCO...");

        LancamentoIntegracaoRetDto lancamentoIntegracaoRetDto = new LancamentoIntegracaoRetDto();
        try {
            if (!ObjectUtil.isNull(lancamentoIntegracaoDto)) {
                LancamentoIntegracaoDelegate cCOLancamentoIntegracaoDelegate = MovimentacaoApiFabricaDelegates.getInstance().criarLancamentoIntegracaoDelegate();

                LancamentoIntegracaoDTO lancamentoIntegracaoDTO = new LancamentoIntegracaoDTO();
                ObjectUtil.copy(lancamentoIntegracaoDto, lancamentoIntegracaoDTO);

                LancamentoIntegracaoRetDTO lancamentoIntegracaoRetDTO = cCOLancamentoIntegracaoDelegate.gravarLancamentoIntegracao(lancamentoIntegracaoDTO);

                if (!ObjectUtil.isNull(lancamentoIntegracaoRetDTO)) {
                    ObjectUtil.copy(lancamentoIntegracaoRetDTO, lancamentoIntegracaoRetDto);
                }
            }
        } catch (MovimentacaoApiNegocioException e) {
            throw new ComumException(e);
        }

        getLogger().debug(Constantes.STR_SEPARACAO_2 + "Lançamento Integração CCO realizado.");

        return lancamentoIntegracaoRetDto;
    }

}