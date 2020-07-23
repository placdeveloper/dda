/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         MonitoracaoDDAServico.java
 * Data Cria��o:    Nov 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqRemessaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqVarreduraDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDDA0110Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDemaisMensagensDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDto;

/**
 * MonitoracaoDDAServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface MonitoracaoDDAServico extends OperacionalServico {

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException MonitoracaoDto
     * 
     */
    MonitoracaoDto obterDadosMonitoracao() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException MonitoracaoMQDto
     * 
     */
    MonitoracaoDDA0110Dto obterDadosMonitoracaoDDA0110(String dataHoraUltimaAfericao) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException MonitoracaoDemaisMensagensDto
     * 
     */
    MonitoracaoDemaisMensagensDto obterDadosMonitoracaoDemaisMensagens() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException MonitoracaoArqRemessaDto
     * 
     */
    MonitoracaoArqRemessaDto obterDadosMonitoracaoArquivoRemessa() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException MonitoracaoArqVarreduraDto
     * 
     */
    MonitoracaoArqVarreduraDto obterDadosMonitoracaoArquivoVarredura() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException MonitoracaoDemaisMensagensDto
     * 
     */
    MonitoracaoDemaisMensagensDto obterDetalheMonitoracaoDemaisMensagens() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException MonitoracaoArqRemessaDto
     * 
     */
    MonitoracaoArqRemessaDto obterDetalheMonitoracaoArquivoRemessa() throws ComumException;

    /**
     * M�todo respons�vel por verificar se deve habilitar a conting�ncia
     * 
     * @throws ComumException
     */
    void avaliarHabilitarContingencia() throws ComumException;

}
