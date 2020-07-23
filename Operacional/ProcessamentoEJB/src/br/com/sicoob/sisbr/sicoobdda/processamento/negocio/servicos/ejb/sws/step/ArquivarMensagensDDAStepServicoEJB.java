/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         CadastrarMensagensCargaAceiteStepServicoEJB.java
 * Data Criação:    Aug 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.HistoricoMensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * ArquivarMensagensDDAStepServicoEJB
 * 
 * @author George.santos
 */
@Stateless
@Remote(StepServico.class)
public class ArquivarMensagensDDAStepServicoEJB extends StepSicoobServico {

    private HistoricoMensagemDDADelegate historicoMensagemDDADelegate;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        getLogger().debug(Constantes.STR_SEPARACAO);
        getLogger().debug("************ INICIO EXECUCAO STEP ARQUIVAMENTO DE MENSAGENS *************");
        getLogger().debug(Constantes.STR_SEPARACAO);
        try {

            getHistoricoMensagemDDaDelegate().arquivarMensagensDDA();
        } catch (ComumException e) {
            return erro(e.getMessage());
        }
        return sucesso();
    }

    /**
     * @return o atributo ExpurgarMensagensDDAHistoricoDelegate
     */
    public HistoricoMensagemDDADelegate getHistoricoMensagemDDaDelegate() {
        if (ObjectUtil.isNull(historicoMensagemDDADelegate)) {
            historicoMensagemDDADelegate = OperacionalFabricaDelegates.getInstance().getHistoricoMensagemDDADelegate();
        }
        return historicoMensagemDDADelegate;
    }

    /**
     * @return ISicoobLogger
     * 
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(ArquivarMensagensDDAStepServicoEJB.class);
    }

}
