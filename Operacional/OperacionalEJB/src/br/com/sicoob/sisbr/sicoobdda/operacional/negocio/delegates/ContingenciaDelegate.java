/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         ContingenciaDelegate.java
 * Data Criação:    Jan 2, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoContingencia;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * ContingenciaDelegate
 * 
 * @author Danilo.Barros
 */
public class ContingenciaDelegate extends OperacionalCrudDelegate<HistoricoContingencia, ContingenciaServico> implements ContingenciaServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ContingenciaServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarContingenciaServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    @Override
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico#incluirContingencia(br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto,
     *      java.lang.Short)
     */
    public void incluirContingencia(ContingenciaDto contingenciaDto, Short idCanal) throws BancoobException {
        localizarServico().incluirContingencia(contingenciaDto, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico#listarHistoricoContingencias()
     */
    public List<GradeContingenciaDto> listarHistoricoContingencias() throws ComumException {
        return localizarServico().listarHistoricoContingencias();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico#obterValorInteger(java.lang.Long, java.lang.Integer)
     */
    public Integer obterValorInteger(Long idParametro, Integer idInstituicao) throws ComumException {
        return localizarServico().obterValorInteger(idParametro, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico#configurarRelatorioHistoricoContingencia(br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    public ConfigurarRelatorioJasper configurarRelatorioHistoricoContingencia(UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioHistoricoContingencia(usuario);
    }

}
