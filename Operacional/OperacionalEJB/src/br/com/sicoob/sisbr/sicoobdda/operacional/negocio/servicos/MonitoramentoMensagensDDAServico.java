/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         MonitoramentoMensagensDDAServico.java
 * Data Cria��o:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

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
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;

/**
 * MonitoramentoMensagensDDAServico � respons�vel por
 * 
 * @author Samuell.Ramos
 */

public interface MonitoramentoMensagensDDAServico extends OperacionalCrudServico<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por listar os tipos de mensagens
     * 
     * @return
     * @throws ComumException
     */
    List<TipoMensagemDDA> listarTipoMensagens(String origem) throws ComumException;

    /**
     * M�todo respons�vel por recuperar a mensagemDDA por id
     * 
     * @param idMensagem
     * @return
     * @throws ComumException
     */
    MensagemDDA recuperaMensagemDDA(Long idMensagem) throws ComumException;

    /**
     * M�todo respons�vel por recuperar a mensagemOrigem
     * 
     * @param idMensagem
     * @return mensagemOrigem
     * @throws ComumException
     */
    MensagemDDA recuperaMensagemOrigemDDA(Long idMensagem) throws ComumException;

    /**
     * M�todo respons�vel por reenviar mensagens a cip
     * 
     * @param listaIdMensagem
     * @throws IntegracaoCipException void
     * 
     */
    void reenviarMensagemCip(List<Integer> listaIdMensagem, Short idCanal) throws IntegracaoCipException, ComumNegocioException;

    /**
     * M�todo respons�vel por reenviar mensagem a cip
     * 
     * @param idMensagem
     * @throws IntegracaoCipException void
     * 
     */
    void reenviarMensagemCip(Long idMensagem, Short idCanal) throws IntegracaoCipException, ComumNegocioException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException
     */
    List<TipoErroCipDto> recuperaTipoErroCip() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException
     */
    ParametroDDAReprocessamentoDto recuperaParametrosReprocessamento(Long idArquivo) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idLogErroCarga
     * @return
     * @throws ComumException LogErroCargaDto
     * 
     */
    LogErroCargaDto recuperaRegistroErroCarga(Long idLogErroCarga) throws ComumException;
    
    /**
     * M�todo respons�vel por
     * 
     * @param idMensagem
     * @param usuarioDTO
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioMonitoramentoMensagem(Long idMensagem, UsuarioBancoobDTO usuarioDTO) throws BancoobException;

}
