package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoTerceiroAutorizadoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDAContaCorrenteDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaHistoricoMensagem106Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PesquisaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;

/**
 * ConsultaBoletoDDAServico � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
public interface ConsultaBoletoDDAServico extends OperacionalCrudServico<BoletoDDA> {

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
     * @param isAgendamentoPagamento <code>true</code> se � um agendamento/pagamento, caso contr�rio � uma efetiva��o de um agendamento.
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    ConsultaBoletoDDADto obterBoletoParaPagamento(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa,
            Long numContaCorrente, BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento) throws ComumNegocioException, ComumException;

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
    ConsultaBoletoDDADto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente,
            Long numIdentificadorBoletoCIP) throws ComumNegocioException, ComumException;

    /**
     * M�todo respons�vel por verificar se o boleto est� registrado na CIP
     * 
     * @param idBoleto
     * @return true - se boleto est� na CIP; false - se boleto n�o est� na CIP
     * @throws ComumNegocioException
     * @throws ComumException
     */
    Boolean boletoEstaNaCIP(String numCodigoBarra) throws ComumNegocioException, ComumException;

    /**
     * M�todo respons�vel por verificar se parametro DDA est� ativo
     * 
     * @return true - se par�metro DDA est� ativo false - se est� inativo
     * @throws ComumNegocioException
     * @throws ComumException
     */
    Boolean isParametroDDAAtivo() throws ComumNegocioException, ComumException;

    /**
     * M�todo respons�vel por listar todos os boletos DDA do Sacado Eletronico
     * 
     * @param dto
     * @return
     * @throws ComumNegocioException
     * @throws ComumException BoletoDDADto
     * 
     */
    List<BoletoDDADto> listarBoletoDDAPorContaCorrente(ConsultaBoletoDDAContaCorrenteDto dto) throws ComumNegocioException, ComumException;

    /**
     * M�todo respons�vel por verificar se a consulta de boleto DDA est� ativa.
     * 
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean isConsultaBoletoAtivo() throws ComumException;

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
     * @param numCpfCnpjSolicitante
     * @return List<BoletoTerceiroAutorizadoDDADto>
     * @throws ComumNegocioException
     * @throws ComumException
     */
    List<BoletoTerceiroAutorizadoDDADto> listarBoletoTerceiroAutorizadoDDA(String numIdentificadorBoletoCIP, String numCpfCnpjSolicitante)
            throws ComumNegocioException, ComumException;

    /**
     * M�todo respons�vel por Listar todas as Situacoes do Boleto DDA.SITUACAOBOLETO
     * 
     * @return List<SituacaoBoleto>
     * 
     */
    List<SituacaoBoleto> listarSituacaoBoletoDDA() throws ComumException;

    /**
     * M�todo respons�vel por Consultar o Boleto na CIP por arquivo (ADDA106) de acordo com o Codigo de Barras e todas as situacoes disponiveis.
     * 
     * @param numCodigoBarra void
     * 
     */
    void consultarBoletoCIP(String numCodigoBarra, Short idCanal, Short codSituacaoBoleto) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por obter o nome do Banco
     * 
     * @param numBanco
     * @return
     * @throws ComumException
     * @throws ComumNegocioException String
     * 
     */
    String obterNomeBanco(short numBanco) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por Obter o DDA.BOletoDDA
     * 
     * @param numCodigoBarra
     * @param codSituacaoBoleto
     * @return BoletoDDA
     * 
     */
    BoletoDDA obterBoletoDDA(String numCodigoBarra, Integer codSituacaoBoleto) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por verificar se a instituicao logada esta habilitada a fazer consulta de boleto na CIP.
     * 
     * Parametro = 82
     * 
     * @param idInstituicao
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean isConsultaBoletoCIPHabilitadoPorInstituicao(Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param boletoDDA
     * @param numCodigoBarra
     * @param codSituacaoBoleto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioDetalheBoleto(BoletoDDA boletoDDA, String numCodigoBarra, Integer codSituacaoBoleto, UsuarioBancoobDTO usuario)
            throws BancoobException;

    /**
     * M�todo respons�vel por 
     * @param pesquisaBoletoDDADto
     * @return ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioConsultaBoleto(PesquisaBoletoDDADto pesquisaBoletoDDADto, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param listaHistoricoMensagem106
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioHistoricoMensagem(ListaHistoricoMensagem106Dto listaHistoricoMensagem106, UsuarioBancoobDTO usuario) throws BancoobException;
}
