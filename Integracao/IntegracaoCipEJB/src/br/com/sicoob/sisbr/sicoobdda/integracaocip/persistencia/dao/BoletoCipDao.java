package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAAceite;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * BoletoCipDao
 * 
 * @author George.Santos
 */
public interface BoletoCipDao extends IntegracaoCipCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por obter a mensagem do boleto para compor a mensagem de postagem do motor de envio de mensagens.
     * 
     * @param idMensagem
     * @return MensagemDDABoleto
     * 
     */
    MensagemDDABoleto obterMensagemDDABoleto(Long idMensagem);

    /**
     * Método responsável por obter a mensagem do aceite para compor a mensagem de postagem do motor de envio de mensagens.
     * 
     * @param idMensagem
     * @return MensagemDDAAceite
     * 
     */
    MensagemDDAAceite obterMensagemDDAAceite(Long idMensagem);

    /**
     * Método responsável por obter a mensagem do terceiro autorizador para compor a mensagem de postagem do motor de envio de mensagens.
     * 
     * @param idMensagem
     * @return MensagemDDATerceiroAut
     * 
     */
    MensagemDDATerceiroAut obterMensagemDDATerceiroAutorizado(Long idMensagem);

    /**
     * Método responsável por obter o BoletoDDA Baixa Operacional de acordo com o codigo de barras
     * 
     * @param numCodigoBarra
     * @return
     * @throws ComumException BoletoDDABaixaOper
     * 
     */
    List<BoletoDDABaixaOper> listarBoletoDDABaixaOperacional(String numCodigoBarra, BigDecimal valorBaixa, DateTimeDB dataHoraMovimento) throws ComumException;

    /**
     * Método responsável por obter o BoletoDDA Baixa Operacional de acordo com o numIdentificacaoBaixaOperacional e NumeroIdentificacaoTitulo
     * 
     * @param numCodigoBarra
     * @return
     * @throws ComumException BoletoDDABaixaOper
     * 
     */
    BoletoDDABaixaOper obterBoletoDDABaixaOperacional(Long numIdentcTit, Long numIdentcBaixaOperac) throws ComumException;

    /**
     * Método responsável por obter o BoletoDDA Baixa Operacional de acordo com o numIdentificacaoBaixaOperacional
     * 
     * @param numIdentBaixaOperacional
     * @return
     * @throws ComumException BoletoDDABaixaOper
     * 
     */
    BoletoDDABaixaOper obterBoletoDDABaixaOperacional(Long numIdentBaixaOperacional) throws ComumException;

    /**
     * Método responsável por obter o BoletoDDA de acordo com o codigo de barras e a situação
     * 
     * @param numCodBarras
     * @param situacaoBoleto
     * @return BoletoDDA
     * @throws ComumException BoletoDDA
     * 
     */
    BoletoDDA obterBoletoDDA(String numCodBarras, Integer situacaoBoleto) throws ComumException;

    /**
     * Método responsável por obter o BoletoDDA de acordo com o numero de identificacao do boleto
     * 
     * @param numIdentcTit
     * @return
     * @throws ComumException BoletoDDA
     * 
     */
    BoletoDDA obterBoletoDDA(Long numIdentcTit) throws ComumException;

    /**
     * Método responsável por obter o BoletoDDA de acordo com o numero de identificacao do boleto Lockado
     * 
     * @param numIdentcTit
     * @return
     * @throws ComumException BoletoDDA
     * 
     */
    BoletoDDA obterBoletoDDALock(Long numIdentcTit) throws ComumException;

    /**
     * Método responsável por obter a Lista de MensagemDDABoleto
     * 
     * @param idLogEnvioArquivoDDA
     * @return List<MensagemDDABoleto>
     * 
     */
    List<MensagemDDABoleto> listarMensagemDDABoletoLogEnvioArquivo(Long idLogEnvioArquivoDDA);

    /**
     * Método responsável por o BoletoDDA Baixa Efetiva de acordo com o codigo de barras
     * 
     * @param numCodigoBarra
     * @return
     * @throws ComumException BoletoDDABaixaEfet
     * 
     */
    BoletoDDABaixaEfet obterBoletoDDABaixaEfetiva(String numCodigoBarra) throws ComumException;

    /**
     * Método responsável por o BoletoDDA Baixa Efetiva de acordo com o Numero Identificacao Boleto e Numero Identificacao Baixa Efetiva
     * 
     * @param numIdentcTit
     * @param numIdentcBaixaEft
     * @return
     * @throws ComumException BoletoDDABaixaEfet
     * 
     */
    BoletoDDABaixaEfet obterBoletoDDABaixaEfetiva(Long numIdentcTit, Long numIdentcBaixaEft) throws ComumException;

    /**
     * Método responsável por obter o boletoDDA Terceiro Autorizador pelo numero de identificação do terceiro
     * 
     * @param numIdentificadorTerceiro
     * @param numIdentificadorTitulo
     * @return
     * @throws ComumException BoletoDDATerceiroAut
     * 
     */
    BoletoDDATerceiroAut obterBoletoDDATerceiroAutorizado(Long numIdentificadorTerceiro, Long numIdentificadorTitulo) throws ComumException;

    /**
     * Método responsável por atualizar o numRef e numSeq da MensagemDDABoleto e retorna-la.
     * 
     * @param idMensagem
     * @return
     * @throws ComumException MensagemDDABoleto
     * 
     */
    MensagemDDABoleto obterMensagemDDABoletoAtualizaReferencias(Long idMensagem) throws ComumException;

    /**
     * Método responsável por o numRef e numSeq da MensagemDDAAceite e retorna-la.
     * 
     * @param idMensagem
     * @return MensagemDDAAceite
     * 
     */
    MensagemDDAAceite obterMensagemDDAAceiteAtualizaReferencias(Long idMensagem) throws ComumException;

    /**
     * Método responsável por o numRef e numSeq da MensagemDDATerceiroAut e retorna-la.
     * 
     * @param idMensagem
     * @return MensagemDDATerceiroAut
     * 
     */
    MensagemDDATerceiroAut obterMensagemDDATerceiroAutorizadoAtualizaReferencias(Long idMensagem) throws ComumException;

    /**
     * Método responsável por atualizar a situação da baixa operacional
     * 
     * @param numIdentificadorBaixaOper
     * @param codSituacaoBaixaOperacional void
     * 
     */
    void atualizarSituacaoBoletoDDABaixaOper(Long numIdentificadorBaixaOper, String codSituacaoBaixaOperacional) throws ComumException;

    /**
     * @param idBoletoDDA
     * @throws ComumException void
     * 
     */
    void removerBaixaEfetiva(Long idBoletoDDA) throws ComumException;

    /**
     * @param idBoletoDDA
     * @throws ComumException void
     * 
     */
    void removerBaixaOperacional(Long idBoletoDDA) throws ComumException;

    /**
     * @param idBoletoDDA
     * @throws ComumException void
     * 
     */
    void removerGrupoCalculo(Long idBoletoDDA) throws ComumException;

    /**
     * @param idBoletoDDA
     * @throws ComumException void
     * 
     */
    void removerTerceiroAutorizado(Long idBoletoDDA) throws ComumException;

    /**
     * Método responsável por obter a mensagem consulta boleto para compor a mensagem de postagem do motor de envio de mensagens.
     * 
     * @param idMensagem
     * @return MensagemDDABoleto
     * 
     */
    MensagemDDAConsultaBoleto obterMensagemDDAConsultaBoleto(Long idMensagem) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idLogEnvioArquivoDDA
     * @return List<MensagemDDAConsultaBoleto>
     * 
     */
    List<MensagemDDAConsultaBoleto> listarMensagemDDAConsultaBoleto(Long idLogEnvioArquivoDDA);

    /**
     * Método responsável por atualizar a situação para pagamento do bleto.
     * 
     * @param codSituacaoBoletoPagamento
     * @param numRefAtualTit
     * @param numIdentificadorBoleto void
     * 
     */
    void atualizarSituacaoBoletoPagamentoDDA(String codSituacaoBoletoPagamento, BigInteger numRefAtualTit, String numIdentificadorBoleto) throws ComumException;

    /**
     * Método responsável por incluir a lista de mensagens de consulta
     * 
     * @param codTipoMensagem
     * @param dataMovimento
     * @param bolOrigemSicoob
     * @param dataHoraCadastro
     * @param numPrioridadeEnvio
     * @param listaNumCodBarras
     * @throws ComumException
     */
    void incluirMensagemDDAConsultaBoletoEmLote(String codTipoMensagem, DateTimeDB dataMovimento, Boolean bolOrigemSicoob, DateTimeDB dataHoraCadastro, Integer numPrioridadeEnvio,
            List<String> listaNumCodBarras, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por obter a Lista de MensagemDDATerceiroAut
     * 
     * @param idLogEnvioArquivoDDA
     * @return List<MensagemDDATerceiroAut>
     * 
     */
    List<MensagemDDATerceiroAut> listarMensagemDDATerceiroAutorizadoLogEnvioArquivo(Long idLogEnvioArquivoDDA);

    /**
     * Método responsável por verificar um Terceiro Autorizado ativo para o boleto.
     * 
     * @param numCpfCnpjTerceiro
     * @param numIdentTitulo
     * @return
     * @throws ComumException boolean
     * 
     */
    boolean existeTerceiroAutorizadoAtivo(String numCpfCnpjTerceiro, Long numIdentTitulo) throws ComumException;

    /**
     * Método responsável por se já existe uma solicitação para esse Terceiro Autorizado que esteja "em andamento".
     * 
     * @param numCpfCnpjTerceiro
     * @param numIdentTitulo
     * @return
     * @throws ComumException boolean
     * 
     */
    boolean existeSolicitacaoTerceiroAutorizadoEmAndamento(String numCpfCnpjTerceiro, Long numIdentTitulo) throws ComumException;

    /**
     * Método responsável por verificar se o boleto de terceiro está vinculado a outro terceiro.
     * 
     * @param numCpfCnpjTerceiro
     * @param numIdentTitulo
     * @return
     * @throws ComumException boolean
     * 
     */
    boolean isBoletoVinculadoTerceiro(String numCpfCnpjTerceiro, Long numIdentTitulo) throws ComumException;

    /**
     * Método responsável por atualizar o boletoDDA Aceite
     * 
     * @param boletoDDA
     * @throws ComumException void
     * 
     */
    void atualizaBoletoDDAAceite(BoletoDDA boletoDDA) throws ComumException;

    /**
     * Método responsável por gravar os dados do boleto selecionando dentro do XML do arquivo ADDA106 gravado na tabela LogDetRecebimentoArquivo.
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetFinal void
     * 
     */
    void gravarBoletoDDAXmlADDA106(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetFinal);

    /**
     * Método responsável por obter o CPF/CNPJ do pagador eletrônico
     * 
     * @param numIdentcTit
     * @return
     * @throws ComumException
     */
    String obterCpfCnpjPagadorEletronico(Long numIdentcTit) throws ComumException;

    /**
     * Método responsável por executar script no banco de dados efetuando o processamento de registros do arquivo ADDA110RET
     * 
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetRecebimentoInicial identificador inicial do range de registros a serem processados
     * @param idLogDetRecebimentoFinal identificador final do range de registros a serem processados
     * 
     */
    void processarArquivoRetornoConsultaBoletoDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal);

    /**
     * Método responsável por
     * 
     * @param idLogRecebArq
     * @param idLogDetRecebimentoInicial
     * @param idLogDetRecebimentoFinal void
     * 
     */
    void processarArquivoRetornoBaixaEfetivaDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal);

    /**
     * Método responsável por atualizar o bolExcedeuSLA da mensagemDDA
     * 
     * @param idMensagemDDA
     * @param excedeuSLA
     * @throws ComumException
     */
    void atualizarBolExcedeuSLA(Long idMensagemDDA, Boolean excedeuSLA) throws ComumException;

    /**
     * Método responsável por obter a mensagem do DDA
     * 
     * @param idMensagem
     * @return
     */
    MensagemDDA obterMensagemDDA(Long idMensagem);

}
