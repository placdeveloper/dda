/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         TipoGradeHorariaServico.java
 * Data Criação:    19 08, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.CategoriaMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;

/**
 * TipoGradeHorariaServico
 * 
 * @author Samuell.Ramos
 */
public interface TipoMensagemServico extends OperacionalCrudServico<TipoMensagemDDA> {

    /**
     * @param tipoMensagem
     * @return
     * @throws ComumException TipoMensagemDto
     * 
     */
    TipoMensagemDto obterTipoMensagem(TipoMensagemDDA tipoMensagem) throws ComumException;

    /**
     * @param codTipoMensagemDDA
     * @throws OperacionalNegocionException
     * @throws ComumException void
     * 
     */
    void removerTipoMensagem(String codTipoMensagemDDA) throws OperacionalNegocionException, ComumException;

    /**
     * @return
     * @throws ComumException List<CategoriaMensagemDDA>
     * 
     */
    List<CategoriaMensagemDDA> listarCategoriaMensagemDDA() throws ComumException;

    /**
     * @return
     * @throws ComumException List<TipoGradeHoraria>
     * 
     */
    List<TipoGradeHoraria> listarTipoGradeHoraria() throws ComumException;

    /**
     * @param tipoMensagemDDA
     * @param isAlteracao
     * @throws OperacionalException
     * @throws OperacionalNegocionException
     * @throws ComumException void
     * 
     */
    void incluirTipoMensagemDDA(TipoMensagemDDA tipoMensagemDDA, boolean isAlteracao) throws OperacionalException, OperacionalNegocionException, ComumException;

    /**
     * @return
     * @throws ComumException TipoMensagemDto
     * 
     */
    TipoMensagemDto carregarListasTipoMensagem() throws ComumException;

    /**
     * @param tipoMensagemDDA
     * @throws OperacionalException
     * @throws OperacionalNegocionException
     * @throws ComumException void
     * 
     */
    void alterarTipoMensagemDDA(TipoMensagemDDA tipoMensagemDDA) throws OperacionalException, OperacionalNegocionException, ComumException;

    /**
     * 
     * Método responsável por retornar lista com todos TipoMensagemDDA
     * 
     * @return
     * @throws ComumException List<TipoMensagemDDA>
     * 
     */
    List<TipoMensagemDDA> listarTipoMensagemDDA() throws ComumException;

}
