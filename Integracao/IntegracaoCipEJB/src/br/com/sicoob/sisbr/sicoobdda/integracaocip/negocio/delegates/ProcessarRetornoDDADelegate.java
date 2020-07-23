package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarRetornoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ProcessarRetornoDDADelegate
 * 
 * @author rafael.silva
 */
public class ProcessarRetornoDDADelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ProcessarRetornoDDAServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ProcessarRetornoDDAServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarProcessarRetornoDDAServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws BancoobException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarRetornoDDAServico#processarMensagemRecebida(java.lang.String)
     */
    public void processarMensagemRecebida(String xmlRecebido) throws BancoobException {
        localizarServico().processarMensagemRecebida(xmlRecebido);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarRetornoDDAServico#processarMensagemRecebidaContingencia(java.util.List)
     */
    public void processarMensagemRecebidaContingencia(MensagemDDA mensagemDDA) throws BancoobException {
        localizarServico().processarMensagemRecebidaContingencia(mensagemDDA);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarRetornoDDAServico#processarMensagemRecebida(java.util.List)
     */
    public void processarMensagemRecebida(List<String> grupoDeRegistros) throws BancoobException {
        localizarServico().processarMensagemRecebida(grupoDeRegistros);
    }

    /**
     * Método responsável por
     * 
     * @param xmlRegistro
     * @param object void
     * @throws BancoobException
     * 
     */
    public void processarMensagemRecebidaArquivo(String xmlRegistro, Long idLogDetalhe) throws BancoobException {
        localizarServico().processarMensagemRecebidaArquivo(xmlRegistro, idLogDetalhe);
    }
}
