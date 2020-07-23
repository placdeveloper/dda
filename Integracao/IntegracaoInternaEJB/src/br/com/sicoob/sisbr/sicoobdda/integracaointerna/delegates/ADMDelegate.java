package br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiBancoCaf;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.BancoCafDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.ProdutoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.IntegracaoInternaServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.locator.IntegracaoInternaServiceLocator;

/**
 * ADMDelegate
 * 
 * @author Samuell.Ramos
 */
public class ADMDelegate extends IntegracaoInternaDelegate<IntegracaoInternaServico> implements ADMServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ADMServico localizarServico() {
        return IntegracaoInternaServiceLocator.getInstance().localizarADMServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.integracao.interna.privada.negocio.servicos.ADMServico#obterNomeBancoCache(short)
     */
    public String obterNomeBancoCache(short numBanco) throws ComumException, ComumNegocioException {
        return localizarServico().obterNomeBancoCache(numBanco);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterNomeInstituicaoFinanceiraCache(java.lang.String)
     */
    public String obterNomeInstituicaoFinanceiraCache(String ispb) throws ComumException {
        return localizarServico().obterNomeInstituicaoFinanceiraCache(ispb);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterDataMovimento(java.lang.Integer)
     */
    public Date obterDataMovimento(Integer idInstituicao) throws IntegracaoInternaException {
        return localizarServico().obterDataMovimento(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterDataProximoMovimento(java.lang.Integer)
     */
    public Date obterDataProximoMovimento(Integer idInstituicao) throws IntegracaoInternaException {
        return localizarServico().obterDataProximoMovimento(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterDataMovimentoBancoob()
     */
    public Date obterDataMovimentoBancoob() throws IntegracaoInternaException {
        return localizarServico().obterDataMovimentoBancoob();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterDataProximoMovimentoBancoob()
     */
    public Date obterDataProximoMovimentoBancoob() throws IntegracaoInternaException {
        return localizarServico().obterDataProximoMovimentoBancoob();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterProdutoCobrancaBancoob()
     */
    public ProdutoDto obterProdutoCobrancaBancoob() throws IntegracaoInternaException {
        return localizarServico().obterProdutoCobrancaBancoob();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#verificarDiaUtil(br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public boolean verificarDiaUtil(DateTimeDB data) throws IntegracaoInternaException {
        return localizarServico().verificarDiaUtil(data);

    }

    /**
     * Método responsável por obter a quantidade de dias úteis da data inicial até a data final.
     * 
     * @param dataVencimento
     * @param dataAtual
     * @param idInstituicao
     * @return
     * @throws ComumException
     */
    public int recuperarQtdDiasUteis(Date dataInicial, Date dataFinal, int idInstituicao) throws ComumException {
        return localizarServico().recuperarQtdDiasUteis(dataInicial, dataFinal, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#listarBancosCafComAgenciasAtivas()
     */
    public List<IAdmApiBancoCaf> listarBancosCafComAgenciasAtivas() throws ComumException {
        return localizarServico().listarBancosCafComAgenciasAtivas();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#listarBancosCafDtoComAgenciasAtivas(java.lang.Boolean)
     */
    public List<BancoCafDto> listarBancosCafDtoComAgenciasAtivas(Boolean ordenarLista) throws ComumException {
        return localizarServico().listarBancosCafDtoComAgenciasAtivas(ordenarLista);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterProximoDiaUtil(java.lang.Integer, java.util.Date)
     */
    public Date obterProximoDiaUtil(Integer idInstituicao, Date dataInicio) throws ComumException {
        return localizarServico().obterProximoDiaUtil(idInstituicao, dataInicio);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterPrimeiroDiaUtil(java.lang.Integer, java.util.Date)
     */
    public Date obterPrimeiroDiaUtil(Integer idInstituicao, Date dataInicio) throws ComumException {
        return localizarServico().obterPrimeiroDiaUtil(idInstituicao, dataInicio);
    }

}
