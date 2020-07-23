package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.Date;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * AgendamentoBoletoDao � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
public interface AgendamentoBoletoDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por obter o boleto na base da cooperativa do c�digo de barras.
     * 
     * @param numCooperativaCodBarras
     * @param numCooperativa
     * @param numPA
     * @param codigoBarras
     * @param dataPagamento
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    ConsultaBoletoDDADto obterBoletoPorCodBarras(Integer numCooperativaCodBarras, Integer numCooperativa, Integer numPA, String codigoBarras, Date dataPagamento)
            throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por obter mensagem de alerta, quando houver, para o canal Caixa ou Correspondete pela SPU_COB_VERIFICAR_TITULO_VENCIDO.
     * 
     * @param numCooperativa
     * @param linhaDigitavelCodigoBarras
     * @param idCanal
     * @param numPac
     * @return A mensagem de alerta, se houver.
     * @throws ComumException
     * @throws ComumNegocioException
     */
    String obterAlertaPagamentoBoletoVencidoCaixaOuCorrespondente(Integer numCooperativa, String linhaDigitavelCodigoBarras, Short idCanal, Integer numPac) throws ComumException,
            ComumNegocioException;

    /**
     * M�todo respons�vel por obter a data �til conforme a data informada.
     * 
     * Ex: se a data informada for um feriado nacional ou local, um final de semana, ou for uma data especial, retornar� o pr�ximo dia �til, caso contr�rio
     * retorna a pr�pria data.
     * 
     * @param numCooperativa
     * @param numPA
     * @param data
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    Date obterDataUtil(int numCooperativa, int numPA, Date data) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por obter a data �til conforme a data informada.
     * 
     * Ex: se a data informada for um feriado nacional ou local (s� valida se <code>validarFeriadoLocal = true</code>), um final de semana, ou for uma data
     * especial, retornar� o pr�ximo dia �til, caso contr�rio retorna a pr�pria data.
     * 
     * @param numCooperativa
     * @param numPA
     * @param data
     * @param validarFeriadoLocal
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    Date obterDataUtil(int numCooperativa, int numPA, Date data, boolean validarFeriadoLocal) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por verificar se a data informada � dia �til (valida feriado nacional, datas especiais e finais de semana)
     * 
     * @param numCooperativa
     * @param data
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    boolean isDataUtil(int numCooperativa, Date data) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por obter a quantidade de dias �teis, considerando os feriados locais e nacionais, al�m dos dias especiais.
     * 
     * @param numCooperativa
     * @param numPA
     * @param dataInicial
     * @param dataFinal
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    Integer obterQtdDiasUteis(int numCooperativa, int numPA, Date dataInicial, Date dataFinal) throws ComumException, ComumNegocioException;

}
