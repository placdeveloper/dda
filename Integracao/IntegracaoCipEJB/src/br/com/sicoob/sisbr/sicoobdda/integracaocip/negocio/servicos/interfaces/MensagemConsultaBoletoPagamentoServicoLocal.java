package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ErroCIPNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaBoletoPagamentoServico;

/**
 * MensagemConsultaBoletoPagamentoServicoLocal
 * 
 * @author Rafael.Silva
 */
public interface MensagemConsultaBoletoPagamentoServicoLocal extends MensagemConsultaBoletoPagamentoServico {

    /**
     * Método responsável por
     * 
     * @param numCodBarras
     * @param idInstituicao
     * @param idUsuario
     * @param idCanal
     * @return
     * @throws ComumException MensagemDDA
     * 
     */
    MensagemDDA enviarConsultaBoleto(String numCodBarras, Integer idInstituicao, String idUsuario, Short idCanal) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param numOpMsg
     * @param xmlRecebido
     * @param msgOrigem
     * @param numControleDDA
     * @param idInstituicao
     * @param idUsuario
     * @param idCanal
     * @throws ComumException void
     * 
     */
    void gravarMensagemRetornoSucessoDDA(String numOpMsg, String xmlRecebido, MensagemDDA msgOrigem, String numControleDDA, Integer idInstituicao, String idUsuario, Short idCanal)
            throws ComumException;

    /**
     * Método responsável por
     * 
     * @param numOpMsg
     * @param xmlRecebido
     * @param msgOrigem
     * @param idInstituicao
     * @param idUsuario
     * @param idCanal
     * @return
     * @throws ComumException String
     * 
     */
    String gravarMensagemRetornoErroDDA(String numOpMsg, String xmlRecebido, MensagemDDA msgOrigem, Integer idInstituicao, String idUsuario, Short idCanal) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param msg
     * @throws ComumException void
     * 
     */
    void atualizarBolExcedeuSLA(MensagemDDA msg) throws ComumException;

    /**
     * Método responsável por processar o xml recebido convertendo-o em DDA0110R1ComplexType, e após conversão, gravar ou atualizar o boleto recebido da CIP.
     * 
     * @param xmlRecebido
     * @param msgOrigem
     * @param idInstituicao
     * @param idUsuario
     * @param idCanal
     * @return
     * @throws ComumException
     * @throws ErroCIPNegocioException BoletoDDA
     * 
     */
    BoletoDDA processarMensagemRetorno(String xmlRecebido, MensagemDDA msgOrigem, Integer idInstituicao, String idUsuario, Short idCanal)
            throws ComumException, ErroCIPNegocioException;
}
