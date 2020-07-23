package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DominioCadastroMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;
import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;

/**
 * MensagemDDAServico � respons�vel por servi�os das mensagens DDA
 * 
 * @author Rodrigo.Neri
 */
public interface MensagemDDAServico extends IntegracaoCipServico {

    /**
     * M�todo respons�vel por incluir a mensagem.
     * 
     * @param mensagem a ser inclu�da
     * @param tipoMensagemDDA tipo da mensagem a ser inclu�da
     * @param dataMovimento data de movimento
     * @param usuarioSolicitante
     * @param idInstitiucaoSolicitante
     * @param idCanal
     * @throws ComumException
     */
    void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, DateTimeDB dataMovimento, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException;

    /**
     * M�todo respons�vel por incluir a mensagem.
     * 
     * @param mensagem a ser inclu�da
     * @param tipoMensagemDDA tipo da mensagem a ser inclu�da
     * @param dataMovimento data de movimento
     * @param numPrioridadeEnvio indica a prioridade da mensagem. Se enviado <code>null</code> ser� utilizado a prioridade padr�o do tipo de mensagem
     * @param usuarioSolicitante
     * @param idInstitiucaoSolicitante
     * @param idCanal
     * @throws ComumException
     */
    void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, DateTimeDB dataMovimento, Integer numPrioridadeEnvio, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal)
            throws ComumException;

    /**
     * M�todo respons�vel por incluir a mensagem na data movimento do produto.
     * 
     * @param mensagem
     * @param tipoMensagemDDA
     * @param usuarioSolicitante
     * @param idInstitiucaoSolicitante
     * @param idCanal
     * @throws ComumException
     */
    void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException;

    /**
     * M�todo respons�vel por incluir a mensagem na data movimento do produto.
     * 
     * @param mensagem
     * @param tipoMensagemDDA
     * @param numPrioridadeEnvio indica a prioridade da mensagem. Se enviado <code>null</code> ser� utilizado a prioridade padr�o do tipo de mensagem
     * @param usuarioSolicitante
     * @param idInstitiucaoSolicitante
     * @param idCanal
     * @throws ComumException
     */
    void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, Integer numPrioridadeEnvio, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException;

    /**
     * M�todo respons�vel por incluir a mensagem baseado no ComplexType enviado.
     * 
     * @param complexType
     * @param dataMovimento
     * @param usuarioSolicitante
     * @param idInstitiucaoSolicitante
     * @param idCanal
     * @throws ComumException
     */
    void incluir(ComplexType complexType, DateTimeDB dataMovimento, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException;

    /**
     * M�todo respons�vel por incluir a mensagem baseado no ComplexType enviado.
     * 
     * @param complexType
     * @param dataMovimento
     * @param numPrioridadeEnvio indica a prioridade da mensagem. Se enviado <code>null</code> ser� utilizado a prioridade padr�o do tipo de mensagem
     * @param usuarioSolicitante
     * @param idInstitiucaoSolicitante
     * @param idCanal
     * @throws ComumException
     */
    void incluir(ComplexType complexType, DateTimeDB dataMovimento, Integer numPrioridadeEnvio, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException;

    /**
     * M�todo respons�vel por o dom�nio necess�rio, situa��o do boleto e tipo de boleto consultado, para o cadastro de mensagem avulsa.
     * 
     * @return
     * @throws ComumException DominioCadastroMensagemDto
     * 
     */
    DominioCadastroMensagemDto obterDominioCadastroMensagem() throws ComumException;

}
