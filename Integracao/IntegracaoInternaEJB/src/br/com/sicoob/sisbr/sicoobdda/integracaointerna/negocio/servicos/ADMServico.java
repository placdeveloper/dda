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
     * M�todo respons�vel por obter o nome do banco
     * 
     * @param numBanco
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    String obterNomeBancoCache(short numBanco) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por obter o nome da IF pelo ISPB
     * 
     * @param ispb
     * @return
     * @throws ComumException
     */
    String obterNomeInstituicaoFinanceiraCache(String ispb) throws ComumException;

    /**
     * M�todo respons�vel por obter a data de movimento da institui��o informada
     * 
     * @param idInstituicao
     * @return
     * @throws IntegracaoInternaException
     */
    Date obterDataMovimento(Integer idInstituicao) throws IntegracaoInternaException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws IntegracaoInternaException Date
     * 
     */
    Date obterDataMovimentoBancoob() throws IntegracaoInternaException;

    /**
     * M�todo respons�vel por obter a pr�xima data de movimento
     * 
     * @return Date
     * @throws IntegracaoInternaException
     */
    Date obterDataProximoMovimentoBancoob() throws IntegracaoInternaException;

    /**
     * 
     * M�todo respons�vel por obter o Produto Cobran�a na 0001 - DB2
     * 
     * @return ProdutoDto
     * @throws IntegracaoInternaException
     */
    ProdutoDto obterProdutoCobrancaBancoob() throws IntegracaoInternaException;

    /**
     * 
     * M�todo respons�vel por verificar se a data � dia util na 0001
     * 
     * @param data
     * @return
     * @throws IntegracaoInternaException
     */
    boolean verificarDiaUtil(DateTimeDB data) throws IntegracaoInternaException;

    /**
     * M�todo respons�vel por obter a quantidade de dias �teis da data inicial at� a data final.
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
     * M�todo respons�vel por obter a pr�xima data de movimento
     * 
     * @param idInstituicao
     * @return
     * @throws IntegracaoInternaException
     */
    Date obterDataProximoMovimento(Integer idInstituicao) throws IntegracaoInternaException;

    /**
     * M�todo respons�vel por obter o proximo dia UTIL
     * 
     * @param idInstituicao
     * @param dataInicio
     * @return
     * @throws ComumException Date
     * 
     */
    Date obterProximoDiaUtil(Integer idInstituicao, Date dataInicio) throws ComumException;

    /**
     * M�todo respons�vel por obter o dia �til. Se a data passada for dia �til retorna ela mesma
     * 
     * @param idInstituicao
     * @param dataInicio
     * @return
     * @throws ComumException
     */
    Date obterPrimeiroDiaUtil(Integer idInstituicao, Date dataInicio) throws ComumException;

}
