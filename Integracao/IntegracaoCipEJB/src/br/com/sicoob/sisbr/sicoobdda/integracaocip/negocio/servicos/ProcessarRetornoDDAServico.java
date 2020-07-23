package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;

/**
 * ProcessarRetornoDDAServico
 * 
 * @author Rafael.Silva
 */
public interface ProcessarRetornoDDAServico extends IntegracaoCipServico {

    /**
     * M�todo respons�vel por processar os xmls recebidos da cip atrav�s do SSPB
     * 
     * @param xmlRecebido
     * @param eContingencia informa se a mensagem a ser processada � oriunda do mecan�smo de conting�ncia
     * @throws BancoobException void
     * 
     */
    void processarMensagemRecebida(String xmlRecebido) throws BancoobException;

    /**
     * M�todo respons�vel por processar o Xml recebido dos registros dos arquivos enviados pela CIP M�todo respons�vel por
     * 
     * @param listaDetalhesArquivos
     * @throws BancoobException void
     * 
     */
    void processarMensagemRecebida(List<String> listaDetalhesArquivos) throws BancoobException;

    /**
     * M�todo respons�vel por processar a conting�ncia de mensagens.
     * 
     * @param mensagemDDA
     * @throws BancoobException void
     * 
     */
    void processarMensagemRecebidaContingencia(MensagemDDA mensagemDDA) throws BancoobException;

    /**
     * M�todo respons�vel por processar mensagens de arquivos.
     * 
     * @param xmlRegistro
     * @param idLogDetalhe void
     * @throws BancoobException
     * 
     */
    void processarMensagemRecebidaArquivo(String xmlRegistro, Long idLogDetalhe) throws BancoobException;
}
