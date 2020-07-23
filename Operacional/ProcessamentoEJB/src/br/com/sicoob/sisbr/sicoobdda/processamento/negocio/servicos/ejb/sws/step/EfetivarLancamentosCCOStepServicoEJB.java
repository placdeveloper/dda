/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         EfetivarLancamentosCCOStepServicoEJB.java
 * Data Criação:    Jan 23, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import java.util.Date;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateioLancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.RateioTarifasCIPDelegate;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * EfetivarLancamentosCCOStepServicoEJB
 * 
 * @author Danilo.Barros
 */
@Stateless
@Remote(StepServico.class)
public class EfetivarLancamentosCCOStepServicoEJB extends StepSicoobServico {
    private static final String ID_RATEIO_DDA = "idRateioDDA";
    private static final String ID_INSTITUICAO = "idInstituicao";
    private static final String NUM_COOPERATIVA = "numCooperativa";
    private static final String DATA_MOVIMENTO_BANCOOB = "dataMovimentoBancoob";

    private Map<String, Parametro> mapParametroStep = null;

    /**
     * 
     */
    public EfetivarLancamentosCCOStepServicoEJB() {
        super();
    }

    /**
     * @return ISicoobLogger
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(EfetivarLancamentosCCOStepServicoEJB.class);
    }

    /**
     * Método responsável por logar as mensagens
     * 
     * @param mensagem
     * @return
     * @throws
     */
    private void log(String mensagem) {
        getLogger().debug(Constantes.STEP + Constantes.STR_SEPARACAO_2 + mensagem + Constantes.STR_SEPARACAO_2);
    }

    /**
     * @return RateioTarifasCIPDelegate
     */
    private RateioTarifasCIPDelegate getEfetivarLancamentosCCODelegate() {
        return OperacionalFabricaDelegates.getInstance().getRateioTarifasCIPDelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        log("Executar " + EfetivarLancamentosCCOStepServicoEJB.class.getSimpleName());
        mapParametroStep = contexto.getParametrosStep();

        try {
            RateioCreditoLancamentoCCODto lancamentosRateioCCODto = getRateioCreditoLancamentoCCODto();

            log(Constantes.STR_SEPARACAO_TRACO + ID_RATEIO_DDA + Constantes.SEPARADOR_DE_DADOS + lancamentosRateioCCODto.getIdRateioDDA());
            log(Constantes.STR_SEPARACAO_TRACO + ID_INSTITUICAO + Constantes.SEPARADOR_DE_DADOS + lancamentosRateioCCODto.getIdInstituicao());
            log(Constantes.STR_SEPARACAO_TRACO + NUM_COOPERATIVA + Constantes.SEPARADOR_DE_DADOS + lancamentosRateioCCODto.getNumCooperativa());
            log(Constantes.STR_SEPARACAO_TRACO + DATA_MOVIMENTO_BANCOOB + Constantes.SEPARADOR_DE_DADOS + lancamentosRateioCCODto.getDataMovimentoBancoob());

            getEfetivarLancamentosCCODelegate().efetivarLancamentosRateioCCO(lancamentosRateioCCODto);
        } catch (BancoobException e) {
            return erro(e.getMessage());
        }

        log("Finalizar " + EfetivarLancamentosCCOStepServicoEJB.class.getSimpleName());
        return sucesso();
    }

    /**
     * Método responsável por retornar RateioCreditoLancamentoCCODto
     * 
     * @param
     * @return RateioCreditoLancamentoCCODto
     * @throws ComumNegocioException
     */
    private RateioCreditoLancamentoCCODto getRateioCreditoLancamentoCCODto() throws ComumNegocioException {
        RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto = new RateioCreditoLancamentoCCODto();
        try {
            rateioCreditoLancamentoCCODto.setIdRateioDDA((Long) getParametrosStep(ID_RATEIO_DDA));
            rateioCreditoLancamentoCCODto.setIdInstituicao((Integer) getParametrosStep(ID_INSTITUICAO));
            rateioCreditoLancamentoCCODto.setNumCooperativa(getParametrosStep(NUM_COOPERATIVA).toString());
            rateioCreditoLancamentoCCODto.setDataMovimentoBancoob((Date) getParametrosStep(DATA_MOVIMENTO_BANCOOB));
            rateioCreditoLancamentoCCODto.setCodSituacaoRateioLancamento(SituacaoRateioLancamento.AGUARDANDO_EFETIVACAO);
        } catch (ComumNegocioException e) {
            throw new ComumNegocioException(MensagemUtil.getString("erro.job.obter.steps", EfetivarLancamentosCCOStepServicoEJB.class.getSimpleName(), e.getMessage()), e);
        }
        return rateioCreditoLancamentoCCODto;
    }

    /**
     * Método responsável por obter o parâmetro
     * 
     * @param nomeParametro
     * @return Object
     * @throws ComumNegocioException
     */
    private Object getParametrosStep(String nomeParametro) throws ComumNegocioException {
        Object parametro = mapParametroStep.get(nomeParametro).getValor();
        if (ObjectUtil.isNull(parametro)) {
            throw new ComumNegocioException("Parâmetro [" + nomeParametro + "] não informado");
        }
        return parametro;
    }

}
