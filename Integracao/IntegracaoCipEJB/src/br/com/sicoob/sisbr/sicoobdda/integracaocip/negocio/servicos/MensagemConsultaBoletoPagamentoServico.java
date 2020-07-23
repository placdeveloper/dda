/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemConsultaBoletoPagamentoServico.java
 * Data Cria��o:    Oct 3, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ErroCIPNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;

/**
 * MensagemConsultaBoletoPagamentoServico
 * 
 * @author Rafael.Silva
 */
public interface MensagemConsultaBoletoPagamentoServico extends IntegracaoCipServico {

    /**
     * M�todo respons�vel por obter o boleto DDA consultado na CIP atrav�s da mensagem DDA0110.
     * 
     * @param codigoBarras
     * @param idInstituicao
     * @param idUsuario
     * @param idCanal
     * @return BoletoDDA
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws ErroCIPNegocioException caso n�o encontre o boleto na CIP
     * 
     */
    BoletoDDA obterBoletoDDA(String codigoBarras, Integer idInstituicao, String idUsuario, Short idCanal) throws ComumException, ComumNegocioException, ErroCIPNegocioException;

    /**
     * M�todo respons�vel por incluir as mensagens de consulta de boleto
     * 
     * @param listaNumCodBarras
     * @throws ComumException
     */
    void incluirMensagemDDAConsultaBoleto(List<String> listaNumCodBarras, Integer numCooperativa) throws ComumException;

}
