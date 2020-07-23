/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         AtualizarSituacaoRateioCCOStepServicoEJB.java
 * Data Criação:    Jan 29, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.SituacaoRateioCreditoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateio;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.RateioTarifasCIPDelegate;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * AtualizarSituacaoRateioCCOStepServicoEJB
 * 
 * @author Danilo.Barros
 */
@Stateless
@Remote(StepServico.class)
public class AtualizarSituacaoRateioCCOStepServicoEJB extends StepSicoobServico {
    private static final String ID_RATEIO_DDA = "idRateioDDA";
    private static final String QTD_AGUARDANDO_EFETIVACAO = "qtdAguardandoEfetivacao";
    private static final String QTD_EFETIVADO = "qtdEfetivado";
    private static final String QTD_ERRO_EFETIVACAO = "qtdErroEfetivacao";
    private static final String QTD_EFETIVADO_MANUALMENTE = "qtdEfetivadoManualmente";
    private static final String QTD_EFETIVADO_COM_AJUSTE = "qtdEfetivadoComAjuste";

    private Map<String, Parametro> mapParametroStep = null;

    /**
     * 
     */
    public AtualizarSituacaoRateioCCOStepServicoEJB() {
        super();
    }

    /**
     * @return ISicoobLogger
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(AtualizarSituacaoRateioCCOStepServicoEJB.class);
    }

    /**
     * Método responsável por logar as mensagens
     * 
     * @param mensagem
     * @return
     */
    private void log(String mensagem) {
        getLogger().debug(Constantes.STEP + Constantes.STR_SEPARACAO_2 + mensagem + Constantes.STR_SEPARACAO_2);
    }

    /**
     * @return RateioTarifasCIPDelegate
     */
    private RateioTarifasCIPDelegate getAtualizarSituacaoRateioCCODelegate() {
        return OperacionalFabricaDelegates.getInstance().getRateioTarifasCIPDelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        log("Executar " + AtualizarSituacaoRateioCCOStepServicoEJB.class.getSimpleName());
        mapParametroStep = contexto.getParametrosStep();

        try {

            SituacaoRateioCreditoCCODto situacaoRateioCreditoCCODto = getSituacaoRateioCreditoCCODto();

            log(Constantes.STR_SEPARACAO_TRACO + ID_RATEIO_DDA + Constantes.SEPARADOR_DE_DADOS + situacaoRateioCreditoCCODto.getIdRateioDDA());
            log(Constantes.STR_SEPARACAO_TRACO + QTD_AGUARDANDO_EFETIVACAO + Constantes.SEPARADOR_DE_DADOS + situacaoRateioCreditoCCODto.getQtdAguardandoEfetivacao());
            log(Constantes.STR_SEPARACAO_TRACO + QTD_EFETIVADO + Constantes.SEPARADOR_DE_DADOS + situacaoRateioCreditoCCODto.getQtdEfetivado());
            log(Constantes.STR_SEPARACAO_TRACO + QTD_ERRO_EFETIVACAO + Constantes.SEPARADOR_DE_DADOS + situacaoRateioCreditoCCODto.getQtdErroEfetivacao());
            log(Constantes.STR_SEPARACAO_TRACO + QTD_EFETIVADO_MANUALMENTE + Constantes.SEPARADOR_DE_DADOS + situacaoRateioCreditoCCODto.getQtdEfetivadoManualmente());
            log(Constantes.STR_SEPARACAO_TRACO + QTD_EFETIVADO_COM_AJUSTE + Constantes.SEPARADOR_DE_DADOS + situacaoRateioCreditoCCODto.getQtdEfetivadoComAjuste());

            if (situacaoRateioCreditoCCODto.getQtdAguardandoEfetivacao() > Constantes.INTEGER_ZERO) {
                throw new ComumNegocioException("Existe(m) lançamento(s) aguardando efetivação para o rateio de crédito" + Constantes.SEPARADOR_DE_DADOS
                        + situacaoRateioCreditoCCODto.getIdRateioDDA());
            } else if (situacaoRateioCreditoCCODto.getQtdErroEfetivacao() > Constantes.INTEGER_ZERO) {
                getAtualizarSituacaoRateioCCODelegate().atualizarSituacaoRateioCCO(situacaoRateioCreditoCCODto.getIdRateioDDA(), SituacaoRateio.ERRO_EFETIVACAO);
            } else {
                getAtualizarSituacaoRateioCCODelegate().atualizarSituacaoRateioCCO(situacaoRateioCreditoCCODto.getIdRateioDDA(), SituacaoRateio.EFETIVADO);
            }
        } catch (BancoobException e) {
            return erro(e.getMessage());
        }

        log("Finalizar " + AtualizarSituacaoRateioCCOStepServicoEJB.class.getSimpleName());
        return sucesso();
    }

    /**
     * Método responsável por retornar SituacaoRateioCreditoCCODto
     * 
     * @param
     * @return SituacaoRateioCreditoCCODto
     * @throws ComumNegocioException
     */
    private SituacaoRateioCreditoCCODto getSituacaoRateioCreditoCCODto() throws ComumNegocioException {
        SituacaoRateioCreditoCCODto situacaoRateioCreditoCCODto = new SituacaoRateioCreditoCCODto();
        try {
            situacaoRateioCreditoCCODto.setIdRateioDDA((Long) getParametrosStep(ID_RATEIO_DDA));
            situacaoRateioCreditoCCODto.setQtdAguardandoEfetivacao((Integer) getParametrosStep(QTD_AGUARDANDO_EFETIVACAO));
            situacaoRateioCreditoCCODto.setQtdEfetivado((Integer) getParametrosStep(QTD_EFETIVADO));
            situacaoRateioCreditoCCODto.setQtdErroEfetivacao((Integer) getParametrosStep(QTD_ERRO_EFETIVACAO));
            situacaoRateioCreditoCCODto.setQtdEfetivadoManualmente((Integer) getParametrosStep(QTD_EFETIVADO_MANUALMENTE));
            situacaoRateioCreditoCCODto.setQtdEfetivadoComAjuste((Integer) getParametrosStep(QTD_EFETIVADO_COM_AJUSTE));
        } catch (ComumNegocioException e) {
            throw new ComumNegocioException(MensagemUtil.getString("erro.job.obter.steps", AtualizarSituacaoRateioCCOStepServicoEJB.class.getSimpleName(), e.getMessage()), e);
        }
        return situacaoRateioCreditoCCODto;
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
