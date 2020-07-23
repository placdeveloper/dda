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
 * BoletoDDAAPIServico é responsável por prover os serviços de boletos DDA.
 * 
 * @author Rodrigo.Neri
 */
public interface BoletoDDAAPIServico extends SicoobDDAServico {

    /**
     * Método responsável por obter o boletoDDA pela linha digitável ou o código de barras informado. Realizará cálculos de encargos e desconto baseado na data
     * de pagamento.<br>
     * <br>
     * Quando não encontrar o boleto retornará os dados apenas com as informações disponíveis no código de barras preenchido, além do nome do banco.
     * 
     * @param linhaDigitavelCodigoBarras linha digitável ou código de barras
     * @param dataPagamento data para pagamento do boleto
     * @param idCanal canal de consulta
     * @param numPac número do PA
     * @param numCooperativa cooperativa autenticada
     * @param numContaCorrente conta corrente do pagador
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    ConsultaBoletoDDAAPIDto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente)
            throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * Método responsável por obter o boletoDDA pela linha digitável ou o código de barras informado. Realizará cálculos de encargos e desconto baseado na data
     * de pagamento.<br>
     * <br>
     * Quando não encontrar o boleto retornará os dados apenas com as informações disponíveis no código de barras preenchido, além do nome do banco.
     * 
     * @param linhaDigitavelCodigoBarras linha digitável ou código de barras
     * @param dataPagamento data para pagamento do boleto
     * @param idCanal canal de consulta
     * @param numPac número do PA
     * @param numCooperativa cooperativa autenticada
     * @param numContaCorrente conta corrente do pagador
     * @param numIdentificadorBoletoCIP número do identificador do boleto para obter o boleto correto, caso exista outro CB repetido (parâmetro não obrigatório)
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    ConsultaBoletoDDAAPIDto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente,
            Long numIdentificadorBoletoCIP) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * Método responsável por obter o boletoDDA pela linha digitável ou o código de barras informado. Realizará cálculos de encargos e desconto baseado na data
     * de pagamento.<br>
     * <br>
     * Quando não encontrar o boleto retornará os dados apenas com as informações disponíveis no código de barras preenchido, além do nome do banco.<br>
     * <br>
     * OBS: Este método deve ser chamado na consulta de boletos para efetuar o pagamento/agendamento.
     * 
     * @param linhaDigitavelCodigoBarras linha digitável ou código de barras
     * @param dataPagamento data para pagamento do boleto
     * @param idCanal canal de consulta
     * @param numPac número do PA
     * @param numCooperativa cooperativa autenticada
     * @param numContaCorrente conta corrente do pagador
     * @param valorPagamento valor de pagamento
     * @param numIdentificadorBoletoCIP número do identificador do boleto para obter o boleto correto, caso exista outro CB repetido (parâmetro não obrigatório)
     * @param isAgendamentoPagamento <code>true</code> se é um agendamento/pagamento, caso contrário é uma efetivação.
     * @return
     * @throws SicoobDDAException
     * @throws NegocioException
     */
    ConsultaBoletoDDAAPIDto obterBoletoParaPagamento(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa,
            Long numContaCorrente, BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento) throws SicoobDDAException, NegocioException;

    /**
     * Método responsável por verificar se o boleto está registrado na CIP
     * 
     * @param numCodigoBarra
     * @return true - se boleto está na CIP false - se boleto não está na CIP
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException
     */
    Boolean boletoEstaNaCIP(String numCodigoBarra) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean isConsultaBoletoAtivo() throws SicoobDDAException;

    /**
     * Método responsável por inseriri uma mesagem de consulta de boleto(DDA0110) na base. <br>
     * <br>
     * Essa mesangem será enviada à CIP pelo motor de envio.
     * 
     * @param listaNumCodBarras
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException void
     * 
     */
    void inserirConsultaBoleto(List<String> listaNumCodBarras, Integer numCooperativa) throws SicoobDDAException, SicoobDDANegocioException;

    void inserirConsultaBoleto(List<String> listaNumCodBarras) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * Método responsável por fazer o cancelamento da baixa operacional
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
     * Método responsável por incluir a mensagem de baixa operacional
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
     * Método responsável por incluir a mensagem de baixa operacional
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
     * Método responsável por obter alguns parâmetros para o agendamento.
     * 
     * @param idInstituicao
     * @return
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException
     */
    AgendamentoBoletoDDAAPIDto obterParametrosAgendamento(int idInstituicao) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * Método responsável por listar os boletos DDA da conta corrente informada e de acordo com os parâmetros.
     * 
     * @param consultaBoletoDDAContaCorrenteAPIDto
     * @return
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException
     */
    List<BoletoDDAAPIDto> listarBoletoDDAPorContaCorrente(ConsultaBoletoDDAContaCorrenteAPIDto consultaBoletoDDAContaCorrenteAPIDto) throws SicoobDDAException,
            SicoobDDANegocioException;

    /**
     * Método responsável por inserir mensagem DDA Boleto ou Arquivo depdendo do Parametro 96- Habilitar Envio da Mensagem Gera Boleto Online, caso True envia a
     * mensagem online caso false envia por arquivo
     * 
     * @param geraBoletoLegadoAPIDto
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException void
     * 
     */
    void incluirMensagemDDABoletoGeraBoleto(BoletoLegadoAPIDto boletoLegadoAPIDto) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * Método responsável por inserir mensagem DDA Boleto ou Arquivo depdendo do Parametro 98- Habilitar Envio da Mensagem Sicoob net Empresarial Online, caso
     * True envia a mensagem online caso false envia por arquivo
     * 
     * @param geraBoletoLegadoAPIDto
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException void
     * 
     */
    void incluirMensagemDDABoletoSicoobNetEmpresarial(BoletoLegadoAPIDto boletoLegadoAPIDto) throws SicoobDDAException, SicoobDDANegocioException;

}
