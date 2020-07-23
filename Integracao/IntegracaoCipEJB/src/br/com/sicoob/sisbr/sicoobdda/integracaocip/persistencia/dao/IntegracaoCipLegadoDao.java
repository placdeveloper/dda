/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         IntegracaoCipLegadoDao.java
 * Data Cria��o:    Aug 16, 2016
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
     * M�todo respons�vel por chamar a SPU_DDA_CARGA_TITULODDA_INCLUSAO que vai varrer as tabelas do legado recuperando a movimenta��o para gerar a carga de
     * dados no SqlServer.
     * 
     * @param numCooperativa void
     * 
     */
    void gerarCargaDadosLegadoInclusao(Integer numCooperativa);

    /**
     * M�todo respons�vel por chamar a SPU_DDA_CARGA_TITULODDA_ALTERACAO que vai varrer as tabelas do legado recuperando a movimenta��o para gerar a carga de
     * dados no SqlServer.
     * 
     * @param numCooperativa void
     * 
     */
    void gerarCargaDadosLegadoAlteracao(Integer numCooperativa);

    /**
     * M�todo respons�vel por chamar a SPU_DDA_CARGA_TITULODDA_BAIXA que vai varrer as tabelas do legado recuperando a movimenta��o para gerar a carga de dados
     * no SqlServer.
     * 
     * @param numCooperativa void
     * 
     */
    void gerarCargaDadosLegadoBaixa(Integer numCooperativa);

    /**
     * M�todo respons�vel por obter o N�mero de Referencia Atual do Aceite do boleto.
     * 
     * @param numIdentificadorBoletoCip
     * @return Integer
     * @throws ComumException
     * 
     */
    Long obterNumReferenciaAceiteBoleto(Long numIdentificadorBoletoCip) throws ComumException;



    /**
     * M�todo respons�vel por obter o codTipoPessoa e o numCpfCnpj do pagador do boleto.
     * 
     * @param numIdentificadorBoletoCip
     * @return
     * @throws ComumException BoletoDDA
     * 
     */
    BoletoDDA obterPagadorBoleto(Long numIdentificadorBoletoCip) throws ComumException;

    /**
     * M�todo respons�vel por chamar a SPU_DDA_ENVIO_BOLETODDA que vai recuperar os movimentos de Boletos no legado.
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
     * M�todo respons�vel por chamar a SPU_DDA_ENVIO_PAGADORDDA que vai recuperar os movimentos de PagadorDDA do DDA no legado.
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
     * M�todo respons�vel por chamar a SPU_DDA_ENVIO_BAIXAEFETIVA que vai recuperar os movimentos da baixa efetiva do DDA no legado.
     * 
     * @param numCooperativa
     * @param dataReferencia
     * @return List<MensagemDDABaixaEfetiva>
     * 
     */
    List<MensagemDDABaixaEfetiva> listarMensagensBaixaEfetivaDDA(Integer numCooperativa, Date dataReferencia, Integer qtdAgrupamentoCooperativa);

    /**
     * M�todo respons�vel por atualizar a data de movimento de TituloDDA para a pr�xima data de movimento do produto cobran�a.
     * 
     * @param idOperacaoLeg
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void atualizarDataMovimentoTituloDDA(Long idOperacaoLeg, Integer numCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por atualizar a dataEvento de EventoTituloDDA para a pr�xima data de movimento do produto cobran�a.
     * 
     * @param idEventoTituloDDA
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void atualizarDataEventoTituloDDA(Long idEventoTituloDDA, Integer numCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por atualizar a dataEvento de EventoDDA para a pr�xima data de movimento do produto cobran�a.
     * 
     * @param idEventoDDA
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void atualizarDataEventoDDA(Long idEventoDDA, Integer numCooperativa) throws ComumException;



    /**
     * M�todo respons�vel por
     * 
     * @param numCooperativa
     * @return Integer
     */
    Integer obterQtdAgrupamentoCooperativa(Integer numCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCooperativa
     * @param i void
     */
    void atualizarTituloDDAAgrupador(Integer numCooperativa, int agrupador) throws ComumException;

    /**
     * M�todo respons�vel por lista a quantidade de agrupamento de acordo com o tipo de operacao
     * 
     * @param numCooperativa
     * @return List<Integer>
     */
    List<Integer> listaAgrupaCooperativaEspecificaBoleto(Integer numCooperativa);

    /**
     * M�todo respons�vel por lista a quantidade de agrupamento de acordo com o tipo de operacao
     * 
     * @param numCooperativa
     * @return List<Integer>
     */
    List<Integer> listaAgrupaCooperativaEspecificaBaixa(Integer numCooperativa);

    /**
     * M�todo respons�vel por lista a quantidade de agrupamento de acordo com o tipo de operacao
     * 
     * @param numCooperativa
     * @return List<Integer>
     */
    List<Integer> listaAgrupaCooperativaEspecificaPagador(Integer numCooperativa);

}
