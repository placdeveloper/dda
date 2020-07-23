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
     * Método responsável por verificar se boleto está no DDA
     */
    Boolean boletoEstaNaCIP(String numCodigoBarra) throws ComumNegocioException, ComumException;

    /**
     * Método responsável por obter o BoletoDDA de acordo com o código de barras e/ou o numIdentificadorBoletoCIP, com os relacionamentos preenchidos.
     * 
     * @param codigoBarras
     * @param numIdentificadorBoletoCIP
     * @param somenteEmAberto
     * @return
     * @throws ComumException
     */
    BoletoDDA obterBoletoDDA(String codigoBarras, Long numIdentificadorBoletoCIP, boolean somenteEmAberto) throws ComumException;

    /**
     * Método responsável por o BoletoDDA de acordo com o codigo de barras e/ou situacao do boleto
     * 
     * @param codigoBarrasLinhaDigitavel
     * @param codSituacaoBoleto
     * @return
     * @throws ComumException BoletoDDA
     * 
     */
    BoletoDDA obterBoletoDDA(String codigoBarrasLinhaDigitavel, Integer codSituacaoBoleto) throws ComumException;

    /**
     * Método responsável por listar a baixa operacional para o código de barras na data de pagamento
     * 
     * @param codigoBarras
     * @param dataMovimento
     * @return
     * @throws ComumException
     */
    List<MensagemDDABaixaOperacional> listarMensagemDDABaixaOperacional(String codigoBarras, DateTimeDB dataMovimento) throws ComumException;

    /**
     * Método responsável por obter a situação do boleto
     * 
     * @param codSituacaoBoletoPagamento
     * @return
     * @throws ComumException
     */
    @Deprecated
    SituacaoBoletoPagamento obterSituacaoBoleto(String codSituacaoBoletoPagamento) throws ComumException;

    /**
     * Método responsável por verificar se o tipo de autorização permite alteração dos valores.
     * 
     * @param codAutorizacaoValorDivergente
     * @return
     * @throws ComumException
     */
    @Deprecated
    Boolean permiteAlterarValor(String codAutorizacaoValorDivergente) throws ComumException;

    /**
     * Método responsável por fazer a pesquisa do boleto para a transaçao do sacado eletronico
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
     * Método responsável por
     * 
     * @return List<SituacaoBoleto>
     * 
     */
    List<SituacaoBoleto> listarSituacaoBoleto();

    /**
     * Método responsável por
     * 
     * @param numIdentificadorBoletoCIP
     * @return List<BoletoTerceiroAutorizadoDDADto>
     * 
     */
    List<BoletoTerceiroAutorizadoDDADto> listarBoletoTerceiroAutorizadoDDA(String numIdentificadorBoletoCIP);

    /**
     * Método responsável por verificar se existe boletoDDA cadastrado com o código de barras/linha digitável informada.
     * 
     * @param codigoBarrasLinhaDigitavel
     * @return
     * @throws ComumException
     */
    boolean possuiBoletoDDA(String codigoBarrasLinhaDigitavel) throws ComumException;

    /**
     * Método responsável por obter alguns dados complementares do boletoDDA e retornar a cópia do objeto.
     * 
     * @param boletoDDA
     * @return
     * @throws ComumException
     */
    BoletoDDA complementarDadosBoletoDDA(BoletoDDA boletoDDA) throws ComumException;

    /**
     * Método responsável por Listar as Situacoes do BOleto - DDA.SITUACAOBOLETO
     * 
     * @return
     * @throws ComumException List<SituacaoBoleto>
     * 
     */
    List<SituacaoBoleto> listarSituacaoBoletoDDA() throws ComumException;

    /**
     * Método responsável por verificar se o codigo de barras ja foi encaminhado para CIP
     * 
     * @param numCodigoBarra
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean boletoEncaminhadoCIP(String numCodigoBarra, Short codSituacaoBoleto) throws ComumException;

    /**
     * Método responsável por verificar se o codigo de barras ja foi encaminhado para CIP de acordo com o Parametro, necessario para nao ter varias 106 em um
     * determinado tempo
     * 
     * @param numCodigoBarra
     * @return
     * @throws ComumException java.util.Date
     * 
     */
    DateTimeDB obterDataHoraEncaminhadoParaCIP(String numCodigoBarra, Short codSituacaoBoleto) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException List<PesquisaBoletoDDADto>
     * 
     */
    List<PesquisaBoletoDDADto> listarBoletoDDA(PesquisaBoletoDDADto dto) throws ComumException;

}
