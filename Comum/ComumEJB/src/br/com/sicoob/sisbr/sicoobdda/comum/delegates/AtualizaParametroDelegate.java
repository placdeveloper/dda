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
     * Método responsável por listar parâmetros
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
     * Método responsável por obter lista de tipo de parametro
     * 
     * @returnw
     * @throws ComumException List<TipoParametro>
     * 
     */
    public List<TipoParametroDDA> obterListaTipoParametro() throws ComumException {
        return localizarServico().obterListaTipoParametro();
    }

    /**
     * Método responsável por obter lista de Instituições conforme a lista informada.
     * 
     * @param listaSingular
     * @return
     * @throws ComumException
     */
    public List<ValorParametroDDA> obterListaInstituicao(List<Integer> listaSingular) throws ComumException {
        return localizarServico().obterListaInstituicao(listaSingular);
    }

    /**
     * Método responsável por atualizar a lista de valor parametro de um ou mais unidades
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
     * Método responsável por obter o valor parametro por instituição
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
     * Método responsável por recuperar lista de parâmetros
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
     * Método responsável por incluir o parâmetro
     * 
     * @param parametro
     * @return
     */
    public ParametroDDA incluirParametro(ParametroDDA parametro) throws ComumException {
        return localizarServico().incluirParametro(parametro);
    }

    /**
     * Método responsável por alterar o parâmetro e atualizar as informações de valor parâmetro no DB2 e legado.
     * 
     * @param parametro
     */
    public void alterarParametro(ParametroDDA parametro) throws ComumException {
        localizarServico().alterarParametro(parametro);
    }

    /**
     * 
     * Método responsável por
     * 
     * @throws ComumException
     */
    public void atualizarParametroInstituicao() throws ComumException {
        localizarServico().atualizarParametroInstituicao();
    }

}
