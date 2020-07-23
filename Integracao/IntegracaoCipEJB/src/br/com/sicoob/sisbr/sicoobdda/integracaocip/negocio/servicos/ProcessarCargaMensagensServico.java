/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ProcessarCargaMensagensServico.java
 * Data Cria��o:    Aug 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * ProcessarCargaMensagensServico
 * 
 * @author Rafael.Silva
 */
public interface ProcessarCargaMensagensServico extends IntegracaoCipServico {

    /**
     * M�todo respons�vel por varrer as tabelas do legado recuperando a movimenta��o (Inclusao) para gerar a carga de dados no SqlServer.
     * 
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void gerarCargaDadosLegadoInclusao(Integer numCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por varrer as tabelas do legado recuperando a movimenta��o (Alteracao) para gerar a carga de dados no SqlServer.
     * 
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void gerarCargaDadosLegadoAlteracao(Integer numCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por varrer as tabelas do legado recuperando a movimenta��o (Baixa) para gerar a carga de dados no SqlServer.
     * 
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void gerarCargaDadosLegadoBaixa(Integer numCooperativa) throws ComumException;


    /**
     * M�todo respons�vel por validar e cadastrar as mensagens dos movimentos de Pagador
     * 
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void processarCadastroMensagensPagador(Integer numCooperativa, Integer qtdAgrupamentoCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por validar e cadastrar as mensagens dos movimentos de Boleto
     * 
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void processarCadastroMensagensBoleto(Integer numCooperativa, Integer qtdAgrupamentoCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por validar e cadastrar as mensagens dos movimentos de Baixa
     * 
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void processarCadastroMensagensBaixaEfetiva(Integer numCooperativa, Integer qtdAgrupamentoCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por 0bter a qtd de agrupamentos por cooperativa
     * 
     * @param numCooperativa
     * @return Integer
     */
    Integer obterQtdAgrupamentoCooperativa(Integer numCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por 0bter a qtd de agrupamentos e o tipo de operacao de acordo com a cooperativa especifica
     * 
     * @param numCooperativa
     * @return Integer
     */
    List<Integer> listaAgrupaCooperativaEspecificaBoleto(Integer numCooperativa);

    /**
     * M�todo respons�vel por 0bter a qtd de agrupamentos e o tipo de operacao de acordo com a cooperativa especifica
     * 
     * @param numCooperativa
     * @return Integer
     */
    List<Integer> listaAgrupaCooperativaEspecificaBaixa(Integer numCooperativa);

    /**
     * M�todo respons�vel por 0bter a qtd de agrupamentos e o tipo de operacao de acordo com a cooperativa especifica
     * 
     * @param numCooperativa
     * @return Integer
     */
    List<Integer> listaAgrupaCooperativaEspecificaPagador(Integer numCooperativa);

    /**
     * M�todo respons�vel por criar as mensagens de acordo com o agrupamento e o tipo de operacao
     * 
     * @param numCooperativa
     * @param nrAgrupamento
     * @param codTipoOperacao void
     */
    void processarCadastroMensagens(Integer numCooperativa, Integer nrAgrupamento, String codTipoOperacao) throws ComumException;

}
