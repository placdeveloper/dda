/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         MonitoracaoDelegate.java
 * Data Criação:    Nov 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqRemessaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqVarreduraDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDDA0110Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDemaisMensagensDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * MonitoracaoDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MonitoracaoDelegate extends OperacionalDelegate<MonitoracaoDDAServico> implements MonitoracaoDDAServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected MonitoracaoDDAServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarMonitoracaoServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDadosMonitoracao()
     */
    public MonitoracaoDto obterDadosMonitoracao() throws ComumException {
        return localizarServico().obterDadosMonitoracao();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDadosMonitoracaoDDA0110(java.lang.String)
     */
    public MonitoracaoDDA0110Dto obterDadosMonitoracaoDDA0110(String dataHoraUltimaAfericao) throws ComumException {
        return localizarServico().obterDadosMonitoracaoDDA0110(dataHoraUltimaAfericao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDadosMonitoracaoDemaisMensagens()
     */
    public MonitoracaoDemaisMensagensDto obterDadosMonitoracaoDemaisMensagens() throws ComumException {
        return localizarServico().obterDadosMonitoracaoDemaisMensagens();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDadosMonitoracaoArquivoRemessa()
     */
    public MonitoracaoArqRemessaDto obterDadosMonitoracaoArquivoRemessa() throws ComumException {
        return localizarServico().obterDadosMonitoracaoArquivoRemessa();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDadosMonitoracaoArquivoVarredura()
     */
    public MonitoracaoArqVarreduraDto obterDadosMonitoracaoArquivoVarredura() throws ComumException {
        return localizarServico().obterDadosMonitoracaoArquivoVarredura();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDetalheMonitoracaoDemaisMensagens()
     */
    public MonitoracaoDemaisMensagensDto obterDetalheMonitoracaoDemaisMensagens() throws ComumException {
        return localizarServico().obterDetalheMonitoracaoDemaisMensagens();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDetalheMonitoracaoArquivoRemessa()
     */
    public MonitoracaoArqRemessaDto obterDetalheMonitoracaoArquivoRemessa() throws ComumException {
        return localizarServico().obterDetalheMonitoracaoArquivoRemessa();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#avaliarHabilitarContingencia()
     */
    public void avaliarHabilitarContingencia() throws ComumException {
        localizarServico().avaliarHabilitarContingencia();
    }

}
