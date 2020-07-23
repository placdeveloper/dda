/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         MensagemDDADao.java
 * Data Criação:    27/09/2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroSWSDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBoletoConsultado;

/**
 * MensagemDDADao é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface MensagemDDADao extends IntegracaoCipCrudDaoIF<MensagemDDA> {

    /**
     * Método responsável por recuperar uma mensagemDDA pelo seu numOperacao.
     * 
     * @param numOperacao
     * @return MensagemDDA
     * @throws ComumException
     * 
     */
    MensagemDDA obterMensagemPorNumOperacao(String numOperacao) throws ComumException;

    /**
     * Método responsável por retornar todos os ids das mensgens enviadas por um arquivo
     * 
     * @param idArquivoEnvio identificador do arquivo na base de dados
     * @return List<Long> lista com os ids das mensagens encontradas
     * @throws ComumException exceção que será lançada caso ocorra algum problema na recuperação dos dados
     */

    List<Long> listarIdsMensagensEnviadas(long idArquivoEnvio) throws ComumException;

    /**
     * @param idMensagem
     * @return
     * @throws ComumException MensagemDDA
     * 
     */
    MensagemDDA obterMensagemErroLock(Long idMensagem) throws ComumException;

    /**
     * 
     * Método responsável por excluir grade horaria
     * 
     * @param dataReferencia
     * @param codTipoGradeHoraria
     * @throws ComumException
     */
    void excluirGradeHoraria(Date dataReferencia, String codTipoGradeHoraria) throws ComumException;

    /**
     * 
     * Método responsável por atualizar grade horaria
     * 
     * @param dataReferencia
     * @param codTipoGradeHoraria
     * @param dataAberturaFechamento
     * @throws ComumException void
     * 
     */
    void atualizarGradeHoraria(Date dataReferencia, String codTipoGradeHoraria, Date dataAbertura, Date dataFechamento) throws ComumException;

    /**
     * Método responsável por verificar se possui mensagem DDA para envio
     * 
     * @param idMensagemDDA
     * @return
     * @throws ComumException
     */
    boolean possuiMensagemDDAParaEnvio(Long idMensagemDDA) throws ComumException;

    /**
     * Método responsável por recuperar a quantidade de mensagens pendentes de um detrminado tipo em uma determinada data esta consulta é realizada na
     * 
     * 
     * @param dateTimeDB a data referente à data de movimento da mensagem
     * @param codMensagem o código da mensagem desejada
     * @return long a quantidade de mensagems encontradas
     * 
     */
    long obterQuantidadeMensagemPendente(String prCodigoDaMensagem) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idLogEnvioArquivoDDA
     * @return br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemDDA
     * 
     */
    MensagemDDA obterMensagemDeTesteDeConectividadeAGEN001(Long idLogEnvioArquivoDDA) throws ComumException;

    /**
     * @return
     * @throws ComumException List<SituacaoBoleto>
     * 
     */
    List<SituacaoBoleto> listarTipoSituacaoBoleto() throws ComumException;

    /**
     * @return
     * @throws ComumException List<TipoBoletoConsultado>
     * 
     */
    List<TipoBoletoConsultado> listarTipoBoletoConsultado() throws ComumException;

    /**
     * Método responsável por obter a data de referencia de acordo com o tipo de mensagem Enviada.
     * 
     * @param codTipoMensagemDDA
     * @return Date
     * 
     */
    Date obterDataReferencia(String codTipoMensagemDDA) throws ComumException;

    /**
     * Método responsável por incluir em Lote na tabela DDA.MENSAGEMDDAPAGADOR os dados vindo do legado (SQL)
     * 
     * @param listaMsgPagador
     * @param idInstituicao
     * @param nOME_USUARIO_PAGADOR
     * @param log
     * @return Long
     */
    void incluirListaMsgPagadorBatch(List<MensagemDDAPagador> listaMsgPagador, Integer idInstituicao, String nomeUsuarioPagador, LogErroSWSDto log) throws ComumException;

    /**
     * Método responsável por incluir em Lote na tabela DDA.MENSAGEMDDABOLETO os dados vindo do legado (SQL)
     * 
     * @param listaMensagemDDABoleto
     * @param idInstituicao
     * @param nomeUsuarioBoleto
     * @param log void
     */
    void incluirListaMsgBoletoBatch(List<MensagemDDABoleto> listaMensagemDDABoleto, Integer idInstituicao, String nomeUsuarioBoleto, LogErroSWSDto log);

    /**
     * Método responsável por incluir em Lote na tabela DDA.MENSAGEMDDABAIXAEFETIVA os dados vindo do legado (SQL)
     * 
     * @param listaMsgBaixas
     * @param idInstituicao
     * @param nomeUsuariobaixa
     * @param log void
     */
    void incluirListaMsgBaixaEfetivaBatch(List<MensagemDDABaixaEfetiva> listaMsgBaixas, Integer idInstituicao, String nomeUsuarioBaixaEfetiva, LogErroSWSDto log);

    /**
     * Método responsável por obter o boleto Baixa Operacional
     * 
     * @param valorBaixa
     * @param numCodigoBarra
     * @return BoletoDDABaixaOper
     */
    BoletoDDABaixaOper obterBoletoBaixaOperacional(BigDecimal valorBaixa, String numCodigoBarra) throws ComumException;

}
