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
     * Método responsável por processar os xmls recebidos da cip através do SSPB
     * 
     * @param xmlRecebido
     * @param eContingencia informa se a mensagem a ser processada é oriunda do mecanísmo de contingência
     * @throws BancoobException void
     * 
     */
    void processarMensagemRecebida(String xmlRecebido) throws BancoobException;

    /**
     * Método responsável por processar o Xml recebido dos registros dos arquivos enviados pela CIP Método responsável por
     * 
     * @param listaDetalhesArquivos
     * @throws BancoobException void
     * 
     */
    void processarMensagemRecebida(List<String> listaDetalhesArquivos) throws BancoobException;

    /**
     * Método responsável por processar a contingência de mensagens.
     * 
     * @param mensagemDDA
     * @throws BancoobException void
     * 
     */
    void processarMensagemRecebidaContingencia(MensagemDDA mensagemDDA) throws BancoobException;

    /**
     * Método responsável por processar mensagens de arquivos.
     * 
     * @param xmlRegistro
     * @param idLogDetalhe void
     * @throws BancoobException
     * 
     */
    void processarMensagemRecebidaArquivo(String xmlRegistro, Long idLogDetalhe) throws BancoobException;
}
