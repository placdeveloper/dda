package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoPagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;

/**
 * PagadorEletronicoDDAServico é responsável por
 * 
 * @author Rodrigo.Neri
 */
public interface PagadorEletronicoDDAServico extends OperacionalCrudServico<SicoobDDAEntidade> {

    /**
     * Método responsável por verificar se o cliente Aceite/Rejeicao do titulo DDA
     * 
     * Utilizado no DET - Documento Especificação de Transação - DDAACEITE
     * 
     * @param pagadorEletronicoDDADto
     * @return Boolean
     * 
     */
    Boolean indicadorAceiteDDA(PagadorEletronicoDDADto pagadorEletronicoDDADto) throws ComumException;

    /**
     * Método responsável por obter o Termo de Adesao do DDA pelo Tipo
     * 
     * Utilizado no DET - Documento Especificação de Transação - DDATERMO
     * 
     * @param codTipoRetorno
     * 
     * @param pagadorEletronicoDDADto
     * @return TermoAdesaoDDADto
     * 
     */
    TermoPagadorDto obterTermoAdesaoPeloTipo(Short codTipoTermoPagador, Integer numCooperativa, Integer codTipoRetorno) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por obter o Termo de Adesao do DDA pelo Sacado
     * 
     * Utilizado no DET - Documento Especificação de Transação - DDAREIMPRESSAO
     * 
     * @param idHistoricoTermoPagador
     * 
     * @param pagadorEletronicoDDADto
     * @return TermoAdesaoDDADto
     * 
     */
    TermoPagadorDto obterTermoAdesaoPeloSacado(Integer idHistoricoTermoPagador, Long numContaCorrente, Integer numCooperativa, Integer idResponsavelTitular, String numCpfCNpj)
            throws ComumException, ComumNegocioException;

    /**
     * Método responsável por incluir adesao do sacado ao DDA
     * 
     * Utilizado no DET - Documento Especificação de Transação - DDAADESAO
     * 
     * @param adesaoDDA void
     * 
     */
    TermoPagadorDto incluirAdesaoDDA(PagadorEletronicoDDADto pagadorEletronicoDDADto) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por cancelar adesao do sacado ao DDA
     * 
     * Utilizado no DET - Documento Especificação de Transação - DDACANCELAMENTO
     * 
     * @param pagadorEletronicoDDADto void
     * 
     */
    TermoPagadorDto cancelarAdesaoDDA(PagadorEletronicoDDADto pagadorEletronicoDDADto) throws ComumNegocioException, ComumException;

    /**
     * Método responsável por listar os comprovantes (Adesao/Cancelamento) para o Sacado
     * 
     * Utilizado no DET - Documento Especificação de Transação - DDACOMPROVANTE
     * 
     * @param pagadorEletronicoDDADto
     * @return List<PagadorEletronicoDDADto>
     * 
     */
    List<PagadorEletronicoDDADto> listarComprovanteAdesaoDDA(Long numContaCorrente, Integer numCooperativa, Integer idResponsavelTitular, String numCpfCnpj)
            throws ComumException, ComumNegocioException;

    /**
     * Método responsável por verificar se o CPF/CNPJ informado é de um pagador eletrônico.
     * 
     * @param tipoPessoa F - Pessoa Física; J - Pessoa Jurídica
     * @param cpfCnpj cpf ou cnpj
     * @param isPagadorSicoob - Caso True busca os Pagadores somente do Sicoob (os que tem conta), Caso False busca todos os pagadores
     * @return <code>true<code> se for um pagador eletrônico
     * @throws ComumException
     * @throws ComumNegocioException
     */
    boolean isCpfCnpjPagadorEletronico(Character tipoPessoa, String cpfCnpj, boolean isPagadorSicoob) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por incluir/alterar/excluir o DDA terceiro.
     * 
     * Utilizado no DET - Documento Especificação de Transação - DDATERCEIROAUTORIZAD
     * 
     * @param dto
     * @return <code>true</code> se a operação foi realizada.
     */
    boolean manterDDATerceiro(DDATerceiroDto dto) throws ComumException, ComumNegocioException;

    /**
     * 
     * Método responsável por atualizar Sacado Eletronico (SPU_DDA_ATUALIZAR_SACADOELETRONICO)
     * 
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void atualizarSacadoEletronico(Integer numCooperativa) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param idInstituicao
     * @return
     * @throws ComumNegocioException
     * @throws ComumException PagadorDto
     * 
     */
    PagadorDto obterDadosPagador(String numCpfCnpj, Integer idInstituicao) throws ComumNegocioException, ComumException;

    /**
     * Método responsável por
     * 
     * @param idPagador
     * @return
     * @throws ComumNegocioException
     * @throws ComumException PagadorDto
     * 
     */
    PagadorDto obterDadosPagador(String numCpfCnpj) throws ComumNegocioException, ComumException;

    /**
     * Método responsável por
     * 
     * @param pagadorDto
     * @return
     * @throws ComumNegocioException
     * @throws ComumException PagadorDto
     * 
     */
    PagadorDto solicitarAdesao(PagadorDto pagadorDto, Short idCanal, Boolean isRetornoTermo) throws ComumNegocioException, ComumException;

    /**
     * Método responsável por
     * 
     * @param pagadorDto
     * @return
     * @throws ComumNegocioException
     * @throws ComumException PagadorDto
     * 
     */
    PagadorDto solicitarCancelamentoAdesao(PagadorDto pagadorDto, Short idCanal, Boolean isRetornoTermo) throws ComumNegocioException, ComumException;

    /**
     * Método responsável por
     * 
     * @param pagadorAgregadoDto
     * @param numCpfCnpjPagador
     * @param idInstituicao
     * @return
     * @throws ComumException
     * @throws ComumNegocioException PagadorDto
     * 
     */
    PagadorDto solicitarInclusaoPagadorAgregado(PagadorAgregadoDto pagadorAgregadoDto, String numCpfCnpjPagador, Integer idInstituicao, Short idCanal) throws ComumException,
            ComumNegocioException;

    /**
     * Método responsável por
     * 
     * @param listaPagadorAgregado
     * @param numCpfCnpjPagador
     * @param idInstituicao
     * @return
     * @throws ComumException
     * @throws ComumNegocioException PagadorDto
     * 
     */
    PagadorDto solicitarExclusaoPagadorAgregado(PagadorAgregadoDto pagadorAgregadoDto, String numCpfCnpjPagador, Integer idInstituicao, Short idCanal) throws ComumException,
            ComumNegocioException;

    /**
     * @param numCpfCnpjPagador
     * @return HistoricoPagadorEletronicoDto
     * @throws OperacionalException
     */
    List<HistoricoPagadorEletronicoDto> listarHistoricoPagadorEletronico(String numCpfCnpjPagador, Integer idInstituicao) throws OperacionalException;

    /**
     * @param dataInicioVigencia
     * @param dataFimVigencia
     * @param codTipoTermoPagador
     * @param bolFormatoHtml
     * @return TermoPagadorDto
     * @throws OperacionalException
     */
    TermoPagadorDto obterTermoPagadorEletronico(DateTimeDB dataInicioVigencia, DateTimeDB dataFimVigencia, Short codTipoTermoPagador, Boolean bolFormatoHtml, Integer numCooperativa)
            throws OperacionalException;

    /**
     * Método responsável por
     * 
     * @param historicoPagdorEletroDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioTermoPagadorEletronico(HistoricoPagadorEletronicoDto historicoPagdorEletroDto, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioListaPagadorEletronico(Integer numCooperativa, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioDetalhePagadorEletronico(String numCpfCnpj, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param numCooperativa
     * @param codCentral
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioPagadorAgregado(String numCpfCnpj, Integer numCooperativa, Integer codCentral, UsuarioBancoobDTO usuario) throws BancoobException;

}
