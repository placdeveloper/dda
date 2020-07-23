/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         MonitoracaoDao.java
 * Data Cria��o:    Nov 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.Date;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DetMonitoracaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqRemessaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDDA0110Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDemaisMensagensDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TipoErroCipDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * MonitoracaoDao � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface MonitoracaoDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por
     * 
     * @param dataHoraUltimaAfericao
     * @return
     * @throws ComumException MonitoracaoDDA0110Dto
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
     * @return MonitoracaoArqRemessaDto
     * 
     */
    MonitoracaoArqRemessaDto obterDadosMonitoracaoArquivoRemessa() throws ComumException;

    /**
     * M�todo respons�vel por listar as GEN0015 com a dataMovimento maior ou igual a data informada.
     * 
     * @param dataMovimento
     * @return
     * @throws ComumException List<LogRecebimentoArquivoDDA>
     * 
     */
    List<LogRecebimentoArquivoDDA> listarGEN0015(Date dataMovimento) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return List<DetMonitoracaoDto>
     * 
     */
    List<DetMonitoracaoDto> obterDetalheMonitoracaoDemaisMensagens() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return List<TipoErroCipDto>
     * 
     */
    List<TipoErroCipDto> obterDadosMonitoracaoErroDemaisMensagens() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return List<DetMonitoracaoDto>
     * 
     */
    List<DetMonitoracaoDto> obterDetalheMonitoracaoArquivoRemessa() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return List<TipoErroCipDto>
     * 
     */
    List<TipoErroCipDto> obterDadosMonitoracaoErroArquivoRemessa() throws ComumException;

    /**
     * M�todo respons�vel por obter os dados da DDA110
     * 
     * @return
     * @throws ComumException
     */
    MonitoracaoDDA0110Dto obterDadosMonitoracaoDDA0110Contingencia() throws ComumException;

}
