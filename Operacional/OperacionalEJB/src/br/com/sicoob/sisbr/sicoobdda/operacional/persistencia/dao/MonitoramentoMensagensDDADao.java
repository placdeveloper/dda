/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         MonitoramentoMensagensDao.java
 * Data Criação:    Ago 13, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroCargaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TipoErroCipDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;

/**
 * MonitoramentoMensagensDao é responsável por
 * 
 * @author Samuell.Ramos
 */
public interface MonitoramentoMensagensDDADao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por listar os tipos de mensagens DDA
     * 
     * @return
     * @throws ComumException
     */
    List<TipoMensagemDDA> listarTipoMensagensDDA(String origem) throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException
     */
    List<TipoErroCipDto> listarTiposErroCIP() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idLogErro
     * @return
     * @throws ComumException List<LogErroCargaDto>
     * 
     */
    List<LogErroCargaDto> obterLogErroCargaDTO(Long idLogErro) throws ComumException;

    /**
     * Método responsável por contar a quantidade total de registros com erro de um arquivo.
     * 
     * @param idArquivo
     * @return
     * @throws ComumException Long
     * 
     */
    Long obterQtdTotalErrosBeneficiariosPorArquivo(Long idArquivo) throws ComumException;

    /**
     * Método responsável por atualizar a tabela de erroMensagemRetornoCip com o seu tipo de tratamento
     * 
     * @param idMensagemDDA
     * @param tipoTratamentoErroCip
     * @throws ComumException void
     * 
     */
    void atualizarTipoTratamentoMensagemRetornoCip(Long idMensagemDDA, int tipoTratamentoErroCip) throws ComumException;

    /**
     * Método responsável por recuperar mensagemOrigem
     * 
     * @param idMensagem
     * @return
     * @throws ComumException MensagemDDA
     * 
     */
    MensagemDDA recuperaMensagemOrigemDDA(Long idMensagem) throws ComumException;
}