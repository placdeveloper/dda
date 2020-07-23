package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoTerceiroAutorizadoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PesquisaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoletoPagamento;

/**
 * 
 * @author Daniel.Moraes
 * 
 */
public interface BoletoDDADao extends OperacionalCrudDaoIF<BoletoDDA> {

    /**
     * M�todo respons�vel por verificar se boleto est� no DDA
     */
    Boolean boletoEstaNaCIP(String numCodigoBarra) throws ComumNegocioException, ComumException;

    /**
     * M�todo respons�vel por obter o BoletoDDA de acordo com o c�digo de barras e/ou o numIdentificadorBoletoCIP, com os relacionamentos preenchidos.
     * 
     * @param codigoBarras
     * @param numIdentificadorBoletoCIP
     * @param somenteEmAberto
     * @return
     * @throws ComumException
     */
    BoletoDDA obterBoletoDDA(String codigoBarras, Long numIdentificadorBoletoCIP, boolean somenteEmAberto) throws ComumException;

    /**
     * M�todo respons�vel por o BoletoDDA de acordo com o codigo de barras e/ou situacao do boleto
     * 
     * @param codigoBarrasLinhaDigitavel
     * @param codSituacaoBoleto
     * @return
     * @throws ComumException BoletoDDA
     * 
     */
    BoletoDDA obterBoletoDDA(String codigoBarrasLinhaDigitavel, Integer codSituacaoBoleto) throws ComumException;

    /**
     * M�todo respons�vel por listar a baixa operacional para o c�digo de barras na data de pagamento
     * 
     * @param codigoBarras
     * @param dataMovimento
     * @return
     * @throws ComumException
     */
    List<MensagemDDABaixaOperacional> listarMensagemDDABaixaOperacional(String codigoBarras, DateTimeDB dataMovimento) throws ComumException;

    /**
     * M�todo respons�vel por obter a situa��o do boleto
     * 
     * @param codSituacaoBoletoPagamento
     * @return
     * @throws ComumException
     */
    @Deprecated
    SituacaoBoletoPagamento obterSituacaoBoleto(String codSituacaoBoletoPagamento) throws ComumException;

    /**
     * M�todo respons�vel por verificar se o tipo de autoriza��o permite altera��o dos valores.
     * 
     * @param codAutorizacaoValorDivergente
     * @return
     * @throws ComumException
     */
    @Deprecated
    Boolean permiteAlterarValor(String codAutorizacaoValorDivergente) throws ComumException;

    /**
     * M�todo respons�vel por fazer a pesquisa do boleto para a transa�ao do sacado eletronico
     * 
     * @param listaCpfCnpj
     * @param dataInicial
     * @param dataFinal
     * @param codSituacao
     * @param numCooperativa
     * @param numContaCorrente
     * @return
     * @throws ComumException BoletoDDADto
     */
    List<BoletoDDADto> listarBoletoDDATransacaoCanais(Set<String> listaCpfCnpj, DateTimeDB dataInicial, DateTimeDB dataFinal, Integer codSituacao, Integer numCooperativa,
            BigDecimal numContaCorrente) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return List<SituacaoBoleto>
     * 
     */
    List<SituacaoBoleto> listarSituacaoBoleto();

    /**
     * M�todo respons�vel por
     * 
     * @param numIdentificadorBoletoCIP
     * @return List<BoletoTerceiroAutorizadoDDADto>
     * 
     */
    List<BoletoTerceiroAutorizadoDDADto> listarBoletoTerceiroAutorizadoDDA(String numIdentificadorBoletoCIP);

    /**
     * M�todo respons�vel por verificar se existe boletoDDA cadastrado com o c�digo de barras/linha digit�vel informada.
     * 
     * @param codigoBarrasLinhaDigitavel
     * @return
     * @throws ComumException
     */
    boolean possuiBoletoDDA(String codigoBarrasLinhaDigitavel) throws ComumException;

    /**
     * M�todo respons�vel por obter alguns dados complementares do boletoDDA e retornar a c�pia do objeto.
     * 
     * @param boletoDDA
     * @return
     * @throws ComumException
     */
    BoletoDDA complementarDadosBoletoDDA(BoletoDDA boletoDDA) throws ComumException;

    /**
     * M�todo respons�vel por Listar as Situacoes do BOleto - DDA.SITUACAOBOLETO
     * 
     * @return
     * @throws ComumException List<SituacaoBoleto>
     * 
     */
    List<SituacaoBoleto> listarSituacaoBoletoDDA() throws ComumException;

    /**
     * M�todo respons�vel por verificar se o codigo de barras ja foi encaminhado para CIP
     * 
     * @param numCodigoBarra
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean boletoEncaminhadoCIP(String numCodigoBarra, Short codSituacaoBoleto) throws ComumException;

    /**
     * M�todo respons�vel por verificar se o codigo de barras ja foi encaminhado para CIP de acordo com o Parametro, necessario para nao ter varias 106 em um
     * determinado tempo
     * 
     * @param numCodigoBarra
     * @return
     * @throws ComumException java.util.Date
     * 
     */
    DateTimeDB obterDataHoraEncaminhadoParaCIP(String numCodigoBarra, Short codSituacaoBoleto) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException List<PesquisaBoletoDDADto>
     * 
     */
    List<PesquisaBoletoDDADto> listarBoletoDDA(PesquisaBoletoDDADto dto) throws ComumException;

}
