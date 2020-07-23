package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiBancoCaf;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.BancoCafDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.ProdutoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;

/**
 * SCIServico
 * 
 * @author Samuell.Ramos
 */
public interface ADMServico extends IntegracaoInternaServico {

    /**
     * Método responsável por obter o nome do banco
     * 
     * @param numBanco
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    String obterNomeBancoCache(short numBanco) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por obter o nome da IF pelo ISPB
     * 
     * @param ispb
     * @return
     * @throws ComumException
     */
    String obterNomeInstituicaoFinanceiraCache(String ispb) throws ComumException;

    /**
     * Método responsável por obter a data de movimento da instituição informada
     * 
     * @param idInstituicao
     * @return
     * @throws IntegracaoInternaException
     */
    Date obterDataMovimento(Integer idInstituicao) throws IntegracaoInternaException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws IntegracaoInternaException Date
     * 
     */
    Date obterDataMovimentoBancoob() throws IntegracaoInternaException;

    /**
     * Método responsável por obter a próxima data de movimento
     * 
     * @return Date
     * @throws IntegracaoInternaException
     */
    Date obterDataProximoMovimentoBancoob() throws IntegracaoInternaException;

    /**
     * 
     * Método responsável por obter o Produto Cobrança na 0001 - DB2
     * 
     * @return ProdutoDto
     * @throws IntegracaoInternaException
     */
    ProdutoDto obterProdutoCobrancaBancoob() throws IntegracaoInternaException;

    /**
     * 
     * Método responsável por verificar se a data é dia util na 0001
     * 
     * @param data
     * @return
     * @throws IntegracaoInternaException
     */
    boolean verificarDiaUtil(DateTimeDB data) throws IntegracaoInternaException;

    /**
     * Método responsável por obter a quantidade de dias úteis da data inicial até a data final.
     * 
     * @param dataInicial
     * @param dataFinal
     * @param idInstituicao
     * @return
     * @throws ComumException
     */
    int recuperarQtdDiasUteis(Date dataInicial, Date dataFinal, int idInstituicao) throws ComumException;

    /**
     * Metodo que lista os BancoCAF que contenham ao menos uma AgenciaCaf ativa.
     * 
     * @param
     * @return List<BancoCaf>
     * @throws ComumException
     */
    public List<IAdmApiBancoCaf> listarBancosCafComAgenciasAtivas() throws ComumException;

    /**
     * Metodo que lista os BancoCafDto que contenham ao menos uma AgenciaCaf ativa.
     * 
     * @param ordenarLista
     * @return List<BancoCafDto>
     * @throws ComumException
     */
    public List<BancoCafDto> listarBancosCafDtoComAgenciasAtivas(Boolean ordenarLista) throws ComumException;

    /**
     * Método responsável por obter a próxima data de movimento
     * 
     * @param idInstituicao
     * @return
     * @throws IntegracaoInternaException
     */
    Date obterDataProximoMovimento(Integer idInstituicao) throws IntegracaoInternaException;

    /**
     * Método responsável por obter o proximo dia UTIL
     * 
     * @param idInstituicao
     * @param dataInicio
     * @return
     * @throws ComumException Date
     * 
     */
    Date obterProximoDiaUtil(Integer idInstituicao, Date dataInicio) throws ComumException;

    /**
     * Método responsável por obter o dia útil. Se a data passada for dia útil retorna ela mesma
     * 
     * @param idInstituicao
     * @param dataInicio
     * @return
     * @throws ComumException
     */
    Date obterPrimeiroDiaUtil(Integer idInstituicao, Date dataInicio) throws ComumException;

}
