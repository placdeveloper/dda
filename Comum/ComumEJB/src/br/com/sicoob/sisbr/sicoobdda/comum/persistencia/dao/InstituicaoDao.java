/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         InstituicaoDao.java
 * Data Cria��o:    30/09/2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.EnderecoInstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto.InstituicaoCooperativaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.CentralSingularVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.InstituicaoVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.UnidadeVo;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * {@link InstituicaoDao} � respons�vel por incluir e recuperar cooperativas.
 * 
 * @author samuell.ramos
 */
public interface InstituicaoDao extends ComumCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por lista de centrais
     * 
     * @param idInstituicao
     * @return
     * @throws SQLException
     */
    List<CentralSingularVo> listaCentrais(Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por lista de singulares
     * 
     * @param idInstituicao
     * @return
     * @throws SQLException
     */
    List<CentralSingularVo> listaSingulares(Integer idInstituicao, Integer numCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por obter a lista de unidades por numero de cooperativa ou por idInstituicao. Preencha um parametro e set null para o outro.
     * 
     * @param idInstituicao
     * @return
     * @throws SQLException
     */
    List<UnidadeVo> listaUnidades(Integer numeroCooperativa, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idInstituicao
     * @return
     * @throws ComumException
     * @throws SQLException InstituicaoDto
     * 
     */
    InstituicaoDto obterIdInstituicaoPai(Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por obter o numero da cooperativa
     * 
     * @param idInstituicao
     * @return
     */
    Integer obterCooperativaPorInstituicao(Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por buscar o tipo grau cooperativo.
     * 
     * @param idInstituicao
     * @return
     * @throws SQLException
     */
    InstituicaoVo obterTipoGrauCooperativo(Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por recuperar as Centrais ativas
     * 
     * @return
     * 
     */
    List<InstituicaoCooperativaDto> listaCentrais() throws ComumException;

    /**
     * M�todo respons�vel por recuperar as cooperativas sigulares de uma instituicao
     * 
     * @param idInstituicao
     * @return
     * 
     */
    List<InstituicaoCooperativaDto> listaSingulares(Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por recuperar uma lista de instituicao SCI atrav�s da VIW_INSTITUICAO.
     * 
     * @param idInstituicao
     * @return InstituicaoDTO
     * 
     */
    List<InstituicaoDto> listarInstituicao() throws ComumException;

    /**
     * M�todo respons�vel por recuperar uma instituicao SCI atrav�s da VIW_INSTITUICAO.
     * 
     * @param idInstituicao
     * @return InstituicaoDTO
     * 
     */
    InstituicaoDto obterInstituicao(Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por recuperar uma instituicao SCI atrav�s da VIW_INSTITUICAO.
     * 
     * @param numeroCooperativa
     * @return InstituicaoDTO
     * 
     */
    InstituicaoDto obterInstituicaoPorCooperativa(Integer numeroCooperativa) throws ComumException;

    /**
     * M�todo respons�vel por recuperar uma instituicao SCI atrav�s da VIW_INSTITUICAO.
     * 
     * @param listaIdInstituicao
     * @return List<InstituicaoDTO>
     * 
     */
    List<InstituicaoDto> listarInstituicao(List<Integer> listaIdInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por recuperar um endere�o de uma unidadeInstituicao(PA)
     * 
     * @param idInstituicao
     * @param idUnidadeInstituicao
     * @return EnderecoInstituicaoDTO
     * 
     */
    EnderecoInstituicaoDto obterEnderecoUnidadeInstituicao(Integer idInstituicao, Integer idUnidadeInstituicao) throws ComumException;

}
