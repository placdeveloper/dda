package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendentePagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoPagadorDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoTermoPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;

/**
 * PagadorEletronicoDao � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
/**
 * PagadorEletronicoDDADao
 * 
 * @author Samuell.Ramos
 */
public interface PagadorEletronicoDDADao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por
     * 
     * @param idPagadorDDA
     * @return
     * @throws ComumException PagadorDto
     * 
     */
    PagadorDto obterDadosPagador(String numCpfCnpj) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idPagadorDDA
     * @return
     * @throws ComumException List<PagadorAgregadoDto>
     * 
     */
    List<PagadorAgregadoDto> listarAgregadosPorIdPagador(Long idPagadorDDA) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param cpfCnpj
     * @param isPagadoresSicoob - Caso True busca os Pagadores somente do Sicoob (os que tem conta), Caso False busca todos os pagadores
     * @return
     * @throws ComumException boolean
     * 
     */
    boolean isCpfCnpjPagadorEletronico(String cpfCnpj, boolean isPagadoresSicoob) throws ComumException;

    /**
     * M�todo respons�vel por verificar se o CPF/CNPJ informado � de um pagador eletr�nico.
     * 
     * @param tipoPessoa F - Pessoa F�sica; J - Pessoa Jur�dica
     * @param cpfCnpj cpf ou cnpj
     * @param isPagadorSicoob - Caso True busca os Pagadores somente do Sicoob (os que tem conta), Caso False busca todos os pagadores
     * @return <code>true</code> se for um pagador eletr�nico
     * @throws ComumException boolean
     * 
     */
    boolean isCpfCnpjPagadorEletronico(Character tipoPessoa, String cpfCnpj, boolean isPagadorSicoob) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCpfCnpj
     * @return
     * @throws ComumException List<PagadorDDAConta>
     * 
     */
    List<PagadorDDAConta> obterPagadorDDAConta(String numCpfCnpj) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCpfCnpjPagador
     * @return
     * @throws ComumException List<PagadorAgregadoDto>
     * 
     */
    List<PagadorAgregadoDto> obterPagadorAgregadoDDA(String numCpfCnpjPagador) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCpfCnpjPagador
     * @return
     * @throws ComumException List<MensagemPendentePagadorDto>
     * 
     */
    List<MensagemPendentePagadorDto> obterMensagemPendentePagadorDDA(String numCpfCnpjPagador) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCpfCnpj
     * @return
     * @throws OperacionalException Boolean
     * 
     */
    Boolean existeSolicitacaoAdesao(String numCpfCnpj) throws OperacionalException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCpfCnpj
     * @return
     * @throws OperacionalException Boolean
     * 
     */
    Boolean existeSolicitacaoCancelamentoAdesao(String numCpfCnpj) throws OperacionalException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCpfCnpj
     * @param numCpfCnpjAgregado
     * @return
     * @throws OperacionalException Boolean
     * 
     */
    Boolean existeSolicitacaoAgregado(String numCpfCnpj, String numCpfCnpjAgregado) throws OperacionalException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCpfCnpjPagador
     * @param numCpfCnpjAgregado
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean agregadoJaCadastrado(String numCpfCnpjPagador, String numCpfCnpjAgregado) throws ComumException;

    /**
     * @param numCpfCnpjPagador
     * @param idInstituicao
     * @return HistoricoPagadorEletronicoDto
     * @throws OperacionalException
     */
    List<HistoricoPagadorEletronicoDto> listarHistoricoPagadorEletronico(String numCpfCnpjPagador, Integer idInstituicao) throws OperacionalException;


    /**
     * M�todo respons�vel por
     * 
     * @param idHistoricoTermoPagador
     * 
     * @param numCpfCnpjPagador
     * @param idInstituicao
     * @return
     * @throws ComumException HistoricoTermoPagador
     * 
     */
    HistoricoTermoPagador obterHistoricoPagadorEletronicoPeloCpfCnpj(Integer idHistoricoTermoPagador, String numCpfCnpjPagador, Integer idInstituicao) throws ComumException;

    /**
     * @param dataInicioVigencia
     * @param dataFimVigencia
     * @param codTipoTermoPagador
     * @param bolFormatoHtml
     * @return TermoPagadorDto
     * @throws OperacionalException
     */
    TermoPagadorDto obterTermoPagadorEletronico(DateTimeDB dataInicioVigencia, DateTimeDB dataFimVigencia, Short codTipoTermoPagador, Boolean bolFormatoHtml,
            Integer numCooperativa) throws OperacionalException;

    /**
     * M�todo respons�vel por
     * 
     * @param idPagadorDDA
     * @return
     * @throws ComumException List<Integer>
     * 
     */
    List<Integer> listarAgencia(Long idPagadorDDA) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCpfCnpj
     * @param numAgencia
     * @return
     * @throws ComumException List<PagadorDto>
     * 
     */
    List<PagadorAgregadoDto> listarPagadorAgregado(String numCpfCnpj, Integer numAgencia) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param numAgencia
     * @return
     * @throws OperacionalException List<PagadorDto>
     * 
     */
    List<PagadorDto> listarPagadorEletronico(Integer numAgencia) throws OperacionalException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCpfCnpjPagador
     * @return
     * @throws ComumException List<PagadorDDAAgregado>
     * 
     */
    public List<PagadorDDAAgregado> listarPagadorAgregadoDDA(String numCpfCnpjPagador) throws ComumException;

    /**
     * M�todo respons�vel por Listar os Historicos de acordo com o CPFCNPJPagador
     * 
     * @param numCpfCnpjPagador
     * @return List<PagadorEletronicoDDADto>
     * 
     */
    List<PagadorEletronicoDDADto> listarComprovanteAdesaoDDA(String numCpfCnpjPagador);

    /**
     * M�todo respons�vel por verificar a exist�ncia de um PagadorDDA, correntista Sicoob, com CPF/CNPJ informado. Se n�o houver, validar o mesmo CPF/CNPJ como
     * Agregado.
     * 
     * @param codTipoPessoa
     * @param numCpfCnpj
     * @return
     * @throws ComumException boolean
     * 
     */
    boolean isPagadorEletronicoSicoob(Character codTipoPessoa, String numCpfCnpj) throws ComumException;

}
