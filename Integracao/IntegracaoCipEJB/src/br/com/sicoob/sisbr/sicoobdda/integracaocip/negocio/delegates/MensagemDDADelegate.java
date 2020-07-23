package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DominioCadastroMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;
import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;

/**
 * MensagemDDADelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MensagemDDADelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements MensagemDDAServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected MensagemDDAServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemDDAServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA,
     *      java.lang.String, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, DateTimeDB dataMovimento, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal)
            throws ComumException {
        localizarServico().incluir(mensagem, tipoMensagemDDA, dataMovimento, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA,
     *      java.lang.String, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, DateTimeDB dataMovimento, Integer numPrioridadeEnvio, String usuarioSolicitante,
            Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException {
        localizarServico().incluir(mensagem, tipoMensagemDDA, dataMovimento, numPrioridadeEnvio, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA,
     *      java.lang.String, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException {
        localizarServico().incluir(mensagem, tipoMensagemDDA, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA,
     *      java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, Integer numPrioridadeEnvio, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal)
            throws ComumException {
        localizarServico().incluir(mensagem, tipoMensagemDDA, numPrioridadeEnvio, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType,
     *      br.com.bancoob.persistencia.types.DateTimeDB, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(ComplexType complexType, DateTimeDB dataMovimento, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException {
        localizarServico().incluir(complexType, dataMovimento, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType,
     *      br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(ComplexType complexType, DateTimeDB dataMovimento, Integer numPrioridadeEnvio, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal)
            throws ComumException {
        localizarServico().incluir(complexType, dataMovimento, numPrioridadeEnvio, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#obterDominioCadastroMensagem()
     */
    public DominioCadastroMensagemDto obterDominioCadastroMensagem() throws ComumException {
        return localizarServico().obterDominioCadastroMensagem();
    }

}
