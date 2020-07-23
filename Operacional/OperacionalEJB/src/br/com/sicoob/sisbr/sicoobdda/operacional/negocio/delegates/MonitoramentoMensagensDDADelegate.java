/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         MonitoramentoMensagensDelegate.java
 * Data Criação:    Aug 13, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroCargaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParametroDDAReprocessamentoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TipoErroCipDto;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * MonitoramentoMensagensDelegate
 * 
 * @author Samuell.Ramos
 */
@SuppressWarnings("rawtypes")
public class MonitoramentoMensagensDDADelegate extends OperacionalCrudDelegate {

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
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    protected MonitoramentoMensagensDDAServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarMonitoramentoMensagensDDAServico();

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MonitoramentoMensagensDDAServico#listarTipoMensagens()
     */
    public List<TipoMensagemDDA> listarTipoMensagens(String origem) throws ComumException {
        return localizarServico().listarTipoMensagens(origem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#recuperaMensagemDDA(java.lang.Long)
     */
    public MensagemDDA recuperaMensagemDDA(Long idMensagem) throws ComumException {
        return localizarServico().recuperaMensagemDDA(idMensagem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#recuperaMensagemOrigemDDA(java.lang.Long)
     */
    public MensagemDDA recuperaMensagemOrigemDDA(Long idMensagem) throws ComumException {
        return localizarServico().recuperaMensagemOrigemDDA(idMensagem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#reenviarMensagemCip(java.util.List, java.lang.Short)
     */
    public void reenviarMensagemCip(List<Integer> listaIdMensagem, Short idCanal) throws IntegracaoCipException, ComumNegocioException {
        localizarServico().reenviarMensagemCip(listaIdMensagem, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#reenviarMensagemCip(java.lang.Long, java.lang.Short)
     */
    public void reenviarMensagemCip(Long idMensagem, Short idCanal) throws IntegracaoCipException, ComumNegocioException {
        localizarServico().reenviarMensagemCip(idMensagem, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#recuperaTipoErroCip()
     */
    public List<TipoErroCipDto> recuperaTipoErroCip() throws ComumException {
        return localizarServico().recuperaTipoErroCip();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#recuperaParametrosReprocessamento(java.lang.Long)
     */
    public ParametroDDAReprocessamentoDto recuperaParametrosReprocessamento(Long idArquivo) throws ComumException {
        return localizarServico().recuperaParametrosReprocessamento(idArquivo);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#recuperaRegistroErroCarga(java.lang.Long)
     */
    public LogErroCargaDto recuperaRegistroErroCarga(Long idLogErroCarga) throws ComumException {
        return localizarServico().recuperaRegistroErroCarga(idLogErroCarga);
    }

    /**
     * Método responsável por
     * 
     * @param idMensagem
     * @param usuarioDTO
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioMonitoramentoMensagem(Long idMensagem, UsuarioBancoobDTO usuarioDTO) throws BancoobException {
        return localizarServico().configurarRelatorioMonitoramentoMensagem(idMensagem, usuarioDTO);
    }    
    

}
