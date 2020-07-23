/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         MonitoracaoDDAServico.java
 * Data Criação:    Nov 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqRemessaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqVarreduraDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDDA0110Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDemaisMensagensDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDto;

/**
 * MonitoracaoDDAServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface MonitoracaoDDAServico extends OperacionalServico {

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException MonitoracaoDto
     * 
     */
    MonitoracaoDto obterDadosMonitoracao() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException MonitoracaoMQDto
     * 
     */
    MonitoracaoDDA0110Dto obterDadosMonitoracaoDDA0110(String dataHoraUltimaAfericao) throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException MonitoracaoDemaisMensagensDto
     * 
     */
    MonitoracaoDemaisMensagensDto obterDadosMonitoracaoDemaisMensagens() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException MonitoracaoArqRemessaDto
     * 
     */
    MonitoracaoArqRemessaDto obterDadosMonitoracaoArquivoRemessa() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException MonitoracaoArqVarreduraDto
     * 
     */
    MonitoracaoArqVarreduraDto obterDadosMonitoracaoArquivoVarredura() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException MonitoracaoDemaisMensagensDto
     * 
     */
    MonitoracaoDemaisMensagensDto obterDetalheMonitoracaoDemaisMensagens() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException MonitoracaoArqRemessaDto
     * 
     */
    MonitoracaoArqRemessaDto obterDetalheMonitoracaoArquivoRemessa() throws ComumException;

    /**
     * Método responsável por verificar se deve habilitar a contingência
     * 
     * @throws ComumException
     */
    void avaliarHabilitarContingencia() throws ComumException;

}
