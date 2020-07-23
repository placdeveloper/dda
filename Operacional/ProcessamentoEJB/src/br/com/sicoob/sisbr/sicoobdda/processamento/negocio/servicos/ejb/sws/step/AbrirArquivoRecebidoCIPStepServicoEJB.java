package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoRecebidoCIPProcessadorServicoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * AbrirArquivoRecebidoCIPStepServicoEJB
 */
@Stateless
@Remote(StepServico.class)
public class AbrirArquivoRecebidoCIPStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        Long idArquivoRec = 0L;
        Long idArquivoEnv = 0L;
        int tmpMaxAposUltimaAtualizacao = 0;
        String nmArquivo = null;
        String localDeSalva = null;

        // try {
        getLogger().debug("**** Preparando Step para execução. PROCESSO: [ABERTURA DE ARQUIVO RECEBIDO]");

        idArquivoRec = contexto.getParametrosStep().get("idArquivoREC").getValor();
        idArquivoEnv = contexto.getParametrosStep().get("idArquivoENV").getValor();
        nmArquivo = contexto.getParametrosStep().get("nmArquivo").getValor();
        localDeSalva = contexto.getParametrosStep().get("pathSalvaArq").getValor();
        tmpMaxAposUltimaAtualizacao = contexto.getParametrosStep().get("tmpMAxUltAtual").getValor();

        getLogger().debug("**** DADOS ****");
        getLogger().debug("\n\rID ARQUIVO RECEBIDO:[" + idArquivoRec + "] \n\r" + "ID ARQUIVO ENVIADO:[" + idArquivoEnv + "]\n\r" + "NM ARQUIVO RECEBIDO:[" + nmArquivo + "]\n\r"
                + "LOCAL DE SALVA: [" + localDeSalva + "]\n\r ");

        try {
            getArquivoRecebidoCIPProcessadorServicoDelegate().abrirArquivoParaProcessamento(idArquivoRec, nmArquivo, localDeSalva, tmpMaxAposUltimaAtualizacao);
            return sucesso();
        } catch (BancoobException e) {
            return erro("Falha no PROCESSO DE ABERTURA do ARQUIVO [" + nmArquivo + "] MOTIVO[" + e.getMessage() + "]");
        }

        // } catch (Exception e) {
        // getLogger().erro(e, MensagemUtil.getString("falha.execucao.step", "Job Abrir arquivo | ARQUIVO [" + nmArquivo + "]", e.getMessage()));

        // }
    }

    /**
     * Método responsável por
     * 
     * @return ArquivoRecebidoCIPProcessadorServicoDelegate
     * 
     */
    private ArquivoRecebidoCIPProcessadorServicoDelegate getArquivoRecebidoCIPProcessadorServicoDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getArquivoRecebidoCIPProcessadorServicoDelegate();
    }

    /**
     * Método responsável por obter o logger
     * 
     * @return
     */
    static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(AbrirArquivoRecebidoCIPStepServicoEJB.class);
    }

}
