package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizaParametroServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;

@SuppressWarnings("rawtypes")
public class AtualizaParametroDelegate extends ComumCrudDelegate {

    private AtualizaParametroServico atualizaParametroServico;

    @Override
    protected AtualizaParametroServico localizarServico() {
        if (this.atualizaParametroServico == null) {
            this.atualizaParametroServico = ComumServiceLocator.getInstance().localizarAtualizaParametroServico();
        }
        getLogger().info("SERVICO INICIALIZADO");
        return this.atualizaParametroServico;
    }

    /**
     * M�todo respons�vel por listar par�metros
     * 
     * @param somenteVisiveis
     * @param somenteNaoGlobal
     * @return
     * @throws ComumException
     */
    public List<ParametroDDA> listarParametros(boolean somenteVisiveis, boolean somenteNaoGlobal) throws ComumException {
        return localizarServico().listarParametros(somenteVisiveis, somenteNaoGlobal);
    }

    /**
     * 
     * M�todo respons�vel por obter lista de tipo de parametro
     * 
     * @returnw
     * @throws ComumException List<TipoParametro>
     * 
     */
    public List<TipoParametroDDA> obterListaTipoParametro() throws ComumException {
        return localizarServico().obterListaTipoParametro();
    }

    /**
     * M�todo respons�vel por obter lista de Institui��es conforme a lista informada.
     * 
     * @param listaSingular
     * @return
     * @throws ComumException
     */
    public List<ValorParametroDDA> obterListaInstituicao(List<Integer> listaSingular) throws ComumException {
        return localizarServico().obterListaInstituicao(listaSingular);
    }

    /**
     * M�todo respons�vel por atualizar a lista de valor parametro de um ou mais unidades
     * 
     * @param parametro
     * @param listaValorParametro
     * @param idInstituicao
     * @param listaNumCooperativa
     * @throws ComumException
     */
    public void atualizarListaValorParametro(ParametroDDA parametro, List<ValorParametroDDA> listaValorParametro, Long idInstituicao, List<Integer> listaNumCooperativa)
            throws ComumException {
        localizarServico().atualizarListaValorParametro(parametro, listaValorParametro, idInstituicao, listaNumCooperativa);
    }

    /**
     * 
     * M�todo respons�vel por obter o valor parametro por institui��o
     * 
     * @param idInstituicao
     * @param idParametro
     * @return
     * @throws ComumException String
     * 
     */
    public String obterValorParametroInstituicao(Integer idInstituicao, Long idParametro) throws ComumException {
        return localizarServico().obterValorParametroInstituicao(idInstituicao, idParametro);
    }

    /**
     * M�todo respons�vel por recuperar lista de par�metros
     * 
     * @param idParametro
     * @param nomeParametro
     * @return
     * @throws ComumException
     */
    public List<ParametroDDA> obterDadosParametro(Long idParametro, String nomeParametro) throws ComumException {
        return localizarServico().obterDadosParametro(idParametro, nomeParametro);
    }

    /**
     * M�todo respons�vel por incluir o par�metro
     * 
     * @param parametro
     * @return
     */
    public ParametroDDA incluirParametro(ParametroDDA parametro) throws ComumException {
        return localizarServico().incluirParametro(parametro);
    }

    /**
     * M�todo respons�vel por alterar o par�metro e atualizar as informa��es de valor par�metro no DB2 e legado.
     * 
     * @param parametro
     */
    public void alterarParametro(ParametroDDA parametro) throws ComumException {
        localizarServico().alterarParametro(parametro);
    }

    /**
     * 
     * M�todo respons�vel por
     * 
     * @throws ComumException
     */
    public void atualizarParametroInstituicao() throws ComumException {
        localizarServico().atualizarParametroInstituicao();
    }

}
