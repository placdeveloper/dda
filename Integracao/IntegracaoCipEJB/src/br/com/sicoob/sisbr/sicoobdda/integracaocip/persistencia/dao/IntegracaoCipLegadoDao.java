/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         IntegracaoCipLegadoDao.java
 * Data Criação:    Aug 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.util.Date;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * IntegracaoCipLegadoDao
 * 
 * @author Rafael.Silva
 */
public interface IntegracaoCipLegadoDao extends IntegracaoCipCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por chamar a SPU_DDA_CARGA_TITULODDA_INCLUSAO que vai varrer as tabelas do legado recuperando a movimentação para gerar a carga de
     * dados no SqlServer.
     * 
     * @param numCooperativa void
     * 
     */
    void gerarCargaDadosLegadoInclusao(Integer numCooperativa);

    /**
     * Método responsável por chamar a SPU_DDA_CARGA_TITULODDA_ALTERACAO que vai varrer as tabelas do legado recuperando a movimentação para gerar a carga de
     * dados no SqlServer.
     * 
     * @param numCooperativa void
     * 
     */
    void gerarCargaDadosLegadoAlteracao(Integer numCooperativa);

    /**
     * Método responsável por chamar a SPU_DDA_CARGA_TITULODDA_BAIXA que vai varrer as tabelas do legado recuperando a movimentação para gerar a carga de dados
     * no SqlServer.
     * 
     * @param numCooperativa void
     * 
     */
    void gerarCargaDadosLegadoBaixa(Integer numCooperativa);

    /**
     * Método responsável por obter o Número de Referencia Atual do Aceite do boleto.
     * 
     * @param numIdentificadorBoletoCip
     * @return Integer
     * @throws ComumException
     * 
     */
    Long obterNumReferenciaAceiteBoleto(Long numIdentificadorBoletoCip) throws ComumException;



    /**
     * Método responsável por obter o codTipoPessoa e o numCpfCnpj do pagador do boleto.
     * 
     * @param numIdentificadorBoletoCip
     * @return
     * @throws ComumException BoletoDDA
     * 
     */
    BoletoDDA obterPagadorBoleto(Long numIdentificadorBoletoCip) throws ComumException;

    /**
     * Método responsável por chamar a SPU_DDA_ENVIO_BOLETODDA que vai recuperar os movimentos de Boletos no legado.
     * 
     * @param numCooperativa
     * @param dataReferencia
     * @param qtdAgrupamentoCooperativa
     * @return List<MensagemDDABoleto>
     * @throws ComumException
     * 
     */
    List<MensagemDDABoleto> listarMensagensBoleto(Integer numCooperativa, Date dataReferencia, Boolean bolHabilitarModeloCalculo01, Integer qtdAgrupamentoCooperativa)
            throws ComumException;

    /**
     * Método responsável por chamar a SPU_DDA_ENVIO_PAGADORDDA que vai recuperar os movimentos de PagadorDDA do DDA no legado.
     * 
     * @param numCooperativa
     * @param dataReferencia
     * @param qtdAgrupamentoCooperativa
     * @param qtdMaximaRegistros
     * @return List<CargaMensagemPagadorDto>
     * 
     */
    List<MensagemDDAPagador> listarMensagensPagadorDDA(Integer numCooperativa, Date dataReferencia, Integer qtdAgrupamentoCooperativa)
            throws ComumException;

    /**
     * Método responsável por chamar a SPU_DDA_ENVIO_BAIXAEFETIVA que vai recuperar os movimentos da baixa efetiva do DDA no legado.
     * 
     * @param numCooperativa
     * @param dataReferencia
     * @return List<MensagemDDABaixaEfetiva>
     * 
     */
    List<MensagemDDABaixaEfetiva> listarMensagensBaixaEfetivaDDA(Integer numCooperativa, Date dataReferencia, Integer qtdAgrupamentoCooperativa);

    /**
     * Método responsável por atualizar a data de movimento de TituloDDA para a próxima data de movimento do produto cobrança.
     * 
     * @param idOperacaoLeg
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void atualizarDataMovimentoTituloDDA(Long idOperacaoLeg, Integer numCooperativa) throws ComumException;

    /**
     * Método responsável por atualizar a dataEvento de EventoTituloDDA para a próxima data de movimento do produto cobrança.
     * 
     * @param idEventoTituloDDA
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void atualizarDataEventoTituloDDA(Long idEventoTituloDDA, Integer numCooperativa) throws ComumException;

    /**
     * Método responsável por atualizar a dataEvento de EventoDDA para a próxima data de movimento do produto cobrança.
     * 
     * @param idEventoDDA
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void atualizarDataEventoDDA(Long idEventoDDA, Integer numCooperativa) throws ComumException;



    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @return Integer
     */
    Integer obterQtdAgrupamentoCooperativa(Integer numCooperativa) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @param i void
     */
    void atualizarTituloDDAAgrupador(Integer numCooperativa, int agrupador) throws ComumException;

    /**
     * Método responsável por lista a quantidade de agrupamento de acordo com o tipo de operacao
     * 
     * @param numCooperativa
     * @return List<Integer>
     */
    List<Integer> listaAgrupaCooperativaEspecificaBoleto(Integer numCooperativa);

    /**
     * Método responsável por lista a quantidade de agrupamento de acordo com o tipo de operacao
     * 
     * @param numCooperativa
     * @return List<Integer>
     */
    List<Integer> listaAgrupaCooperativaEspecificaBaixa(Integer numCooperativa);

    /**
     * Método responsável por lista a quantidade de agrupamento de acordo com o tipo de operacao
     * 
     * @param numCooperativa
     * @return List<Integer>
     */
    List<Integer> listaAgrupaCooperativaEspecificaPagador(Integer numCooperativa);

}
