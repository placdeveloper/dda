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
 * ConsultaBoletoDDAServico é responsável por
 * 
 * @author Rodrigo.Neri
 */
public interface ConsultaBoletoDDAServico extends OperacionalCrudServico<BoletoDDA> {

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
     * @param isAgendamentoPagamento <code>true</code> se é um agendamento/pagamento, caso contrário é uma efetivação de um agendamento.
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    ConsultaBoletoDDADto obterBoletoParaPagamento(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa,
            Long numContaCorrente, BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento) throws ComumNegocioException, ComumException;

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
    ConsultaBoletoDDADto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente,
            Long numIdentificadorBoletoCIP) throws ComumNegocioException, ComumException;

    /**
     * Método responsável por verificar se o boleto está registrado na CIP
     * 
     * @param idBoleto
     * @return true - se boleto está na CIP; false - se boleto não está na CIP
     * @throws ComumNegocioException
     * @throws ComumException
     */
    Boolean boletoEstaNaCIP(String numCodigoBarra) throws ComumNegocioException, ComumException;

    /**
     * Método responsável por verificar se parametro DDA está ativo
     * 
     * @return true - se parâmetro DDA está ativo false - se está inativo
     * @throws ComumNegocioException
     * @throws ComumException
     */
    Boolean isParametroDDAAtivo() throws ComumNegocioException, ComumException;

    /**
     * Método responsável por listar todos os boletos DDA do Sacado Eletronico
     * 
     * @param dto
     * @return
     * @throws ComumNegocioException
     * @throws ComumException BoletoDDADto
     * 
     */
    List<BoletoDDADto> listarBoletoDDAPorContaCorrente(ConsultaBoletoDDAContaCorrenteDto dto) throws ComumNegocioException, ComumException;

    /**
     * Método responsável por verificar se a consulta de boleto DDA está ativa.
     * 
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean isConsultaBoletoAtivo() throws ComumException;

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
     * @param numCpfCnpjSolicitante
     * @return List<BoletoTerceiroAutorizadoDDADto>
     * @throws ComumNegocioException
     * @throws ComumException
     */
    List<BoletoTerceiroAutorizadoDDADto> listarBoletoTerceiroAutorizadoDDA(String numIdentificadorBoletoCIP, String numCpfCnpjSolicitante)
            throws ComumNegocioException, ComumException;

    /**
     * Método responsável por Listar todas as Situacoes do Boleto DDA.SITUACAOBOLETO
     * 
     * @return List<SituacaoBoleto>
     * 
     */
    List<SituacaoBoleto> listarSituacaoBoletoDDA() throws ComumException;

    /**
     * Método responsável por Consultar o Boleto na CIP por arquivo (ADDA106) de acordo com o Codigo de Barras e todas as situacoes disponiveis.
     * 
     * @param numCodigoBarra void
     * 
     */
    void consultarBoletoCIP(String numCodigoBarra, Short idCanal, Short codSituacaoBoleto) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por obter o nome do Banco
     * 
     * @param numBanco
     * @return
     * @throws ComumException
     * @throws ComumNegocioException String
     * 
     */
    String obterNomeBanco(short numBanco) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por Obter o DDA.BOletoDDA
     * 
     * @param numCodigoBarra
     * @param codSituacaoBoleto
     * @return BoletoDDA
     * 
     */
    BoletoDDA obterBoletoDDA(String numCodigoBarra, Integer codSituacaoBoleto) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por verificar se a instituicao logada esta habilitada a fazer consulta de boleto na CIP.
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
     * Método responsável por
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
     * Método responsável por 
     * @param pesquisaBoletoDDADto
     * @return ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioConsultaBoleto(PesquisaBoletoDDADto pesquisaBoletoDDADto, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param listaHistoricoMensagem106
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioHistoricoMensagem(ListaHistoricoMensagem106Dto listaHistoricoMensagem106, UsuarioBancoobDTO usuario) throws BancoobException;
}
