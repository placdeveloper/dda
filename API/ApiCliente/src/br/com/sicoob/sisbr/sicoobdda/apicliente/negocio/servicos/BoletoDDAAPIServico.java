package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.AgendamentoBoletoDDAAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.BoletoDDAAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.BoletoLegadoAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.ConsultaBoletoDDAAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.ConsultaBoletoDDAContaCorrenteAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.MensagemDDABaixaOperacionalAPIDto;
import br.com.sicoob.tipos.DateTime;

/**
 * BoletoDDAAPIServico � respons�vel por prover os servi�os de boletos DDA.
 * 
 * @author Rodrigo.Neri
 */
public interface BoletoDDAAPIServico extends SicoobDDAServico {

    /**
     * M�todo respons�vel por obter o boletoDDA pela linha digit�vel ou o c�digo de barras informado. Realizar� c�lculos de encargos e desconto baseado na data
     * de pagamento.<br>
     * <br>
     * Quando n�o encontrar o boleto retornar� os dados apenas com as informa��es dispon�veis no c�digo de barras preenchido, al�m do nome do banco.
     * 
     * @param linhaDigitavelCodigoBarras linha digit�vel ou c�digo de barras
     * @param dataPagamento data para pagamento do boleto
     * @param idCanal canal de consulta
     * @param numPac n�mero do PA
     * @param numCooperativa cooperativa autenticada
     * @param numContaCorrente conta corrente do pagador
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    ConsultaBoletoDDAAPIDto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente)
            throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * M�todo respons�vel por obter o boletoDDA pela linha digit�vel ou o c�digo de barras informado. Realizar� c�lculos de encargos e desconto baseado na data
     * de pagamento.<br>
     * <br>
     * Quando n�o encontrar o boleto retornar� os dados apenas com as informa��es dispon�veis no c�digo de barras preenchido, al�m do nome do banco.
     * 
     * @param linhaDigitavelCodigoBarras linha digit�vel ou c�digo de barras
     * @param dataPagamento data para pagamento do boleto
     * @param idCanal canal de consulta
     * @param numPac n�mero do PA
     * @param numCooperativa cooperativa autenticada
     * @param numContaCorrente conta corrente do pagador
     * @param numIdentificadorBoletoCIP n�mero do identificador do boleto para obter o boleto correto, caso exista outro CB repetido (par�metro n�o obrigat�rio)
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    ConsultaBoletoDDAAPIDto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente,
            Long numIdentificadorBoletoCIP) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * M�todo respons�vel por obter o boletoDDA pela linha digit�vel ou o c�digo de barras informado. Realizar� c�lculos de encargos e desconto baseado na data
     * de pagamento.<br>
     * <br>
     * Quando n�o encontrar o boleto retornar� os dados apenas com as informa��es dispon�veis no c�digo de barras preenchido, al�m do nome do banco.<br>
     * <br>
     * OBS: Este m�todo deve ser chamado na consulta de boletos para efetuar o pagamento/agendamento.
     * 
     * @param linhaDigitavelCodigoBarras linha digit�vel ou c�digo de barras
     * @param dataPagamento data para pagamento do boleto
     * @param idCanal canal de consulta
     * @param numPac n�mero do PA
     * @param numCooperativa cooperativa autenticada
     * @param numContaCorrente conta corrente do pagador
     * @param valorPagamento valor de pagamento
     * @param numIdentificadorBoletoCIP n�mero do identificador do boleto para obter o boleto correto, caso exista outro CB repetido (par�metro n�o obrigat�rio)
     * @param isAgendamentoPagamento <code>true</code> se � um agendamento/pagamento, caso contr�rio � uma efetiva��o.
     * @return
     * @throws SicoobDDAException
     * @throws NegocioException
     */
    ConsultaBoletoDDAAPIDto obterBoletoParaPagamento(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa,
            Long numContaCorrente, BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento) throws SicoobDDAException, NegocioException;

    /**
     * M�todo respons�vel por verificar se o boleto est� registrado na CIP
     * 
     * @param numCodigoBarra
     * @return true - se boleto est� na CIP false - se boleto n�o est� na CIP
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException
     */
    Boolean boletoEstaNaCIP(String numCodigoBarra) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean isConsultaBoletoAtivo() throws SicoobDDAException;

    /**
     * M�todo respons�vel por inseriri uma mesagem de consulta de boleto(DDA0110) na base. <br>
     * <br>
     * Essa mesangem ser� enviada � CIP pelo motor de envio.
     * 
     * @param listaNumCodBarras
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException void
     * 
     */
    void inserirConsultaBoleto(List<String> listaNumCodBarras, Integer numCooperativa) throws SicoobDDAException, SicoobDDANegocioException;

    void inserirConsultaBoleto(List<String> listaNumCodBarras) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * M�todo respons�vel por fazer o cancelamento da baixa operacional
     * 
     * @param codigoBarras
     * @param numCooperativa
     * @param valorPago
     * @param idCanal
     * @throws SicoobDDANegocioException
     * @throws SicoobDDAException
     */
    void processarCancelamentoBaixaOperacional(String codigoBarras, Integer numCooperativa, BigDecimal valorPago, Short idCanal) throws SicoobDDANegocioException,
            SicoobDDAException;

    void processarCancelamentoBaixaOperacional(String codigoBarras, Integer numCooperativa, BigDecimal valorPago) throws SicoobDDANegocioException, SicoobDDAException;

    /**
     * M�todo respons�vel por incluir a mensagem de baixa operacional
     * 
     * @param dto
     * @param numBanco
     * @param canal
     * @param aceitaPagamentoParcial
     * @param dataMovimento
     * @param idInstituicao
     * @throws SicoobDDAException
     */
    void incluirMensagemDDABaixaOperacional(MensagemDDABaixaOperacionalAPIDto dto, short numBanco, short canal, boolean aceitaPagamentoParcial, DateTime dataMovimento,
            Integer idInstituicao) throws SicoobDDAException;

    /**
     * M�todo respons�vel por incluir a mensagem de baixa operacional
     * 
     * @param dto
     * @param numBanco
     * @param canal
     * @param aceitaPagamentoParcial
     * @param dataMovimento
     * @param idInstituicao
     * @param codMeioPagamento
     * @throws SicoobDDAException
     */
    void incluirMensagemDDABaixaOperacional(MensagemDDABaixaOperacionalAPIDto dto, short numBanco, short canal, boolean aceitaPagamentoParcial, DateTime dataMovimento,
            Integer idInstituicao, short codMeioPagamento) throws SicoobDDAException;

    /**
     * M�todo respons�vel por obter alguns par�metros para o agendamento.
     * 
     * @param idInstituicao
     * @return
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException
     */
    AgendamentoBoletoDDAAPIDto obterParametrosAgendamento(int idInstituicao) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * M�todo respons�vel por listar os boletos DDA da conta corrente informada e de acordo com os par�metros.
     * 
     * @param consultaBoletoDDAContaCorrenteAPIDto
     * @return
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException
     */
    List<BoletoDDAAPIDto> listarBoletoDDAPorContaCorrente(ConsultaBoletoDDAContaCorrenteAPIDto consultaBoletoDDAContaCorrenteAPIDto) throws SicoobDDAException,
            SicoobDDANegocioException;

    /**
     * M�todo respons�vel por inserir mensagem DDA Boleto ou Arquivo depdendo do Parametro 96- Habilitar Envio da Mensagem Gera Boleto Online, caso True envia a
     * mensagem online caso false envia por arquivo
     * 
     * @param geraBoletoLegadoAPIDto
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException void
     * 
     */
    void incluirMensagemDDABoletoGeraBoleto(BoletoLegadoAPIDto boletoLegadoAPIDto) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * M�todo respons�vel por inserir mensagem DDA Boleto ou Arquivo depdendo do Parametro 98- Habilitar Envio da Mensagem Sicoob net Empresarial Online, caso
     * True envia a mensagem online caso false envia por arquivo
     * 
     * @param geraBoletoLegadoAPIDto
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException void
     * 
     */
    void incluirMensagemDDABoletoSicoobNetEmpresarial(BoletoLegadoAPIDto boletoLegadoAPIDto) throws SicoobDDAException, SicoobDDANegocioException;

}
