/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-atendimento-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.atendimentoprocessamento.negocio.servicos
 * Arquivo:         InstituicaoServico.java
 * Data Criação:    Oct 01, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import java.sql.SQLException;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.EnderecoInstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto.CentralSingularDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto.InstituicaoCooperativaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.CentralSingularVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.InstituicaoVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.UnidadeVo;

/**
 * InstituicaoServico é responsável por declarar os serviços auxiliares de domínio do Cobrança Bancária Sicoob que poderão ser consumidos pelos demais Produtos
 * sistêmicos do Sicoob;
 * 
 * @author samuell.ramos
 */
public interface InstituicaoServico extends ComumServico {

    /**
     * Método responsável por buscar as Centrais pelo id do usuario logado
     * 
     * @param idInstituicao
     * @return
     * @throws SQLException
     */
    List<CentralSingularVo> listaCentrais(Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por Buscar buscar as Singulares (Instituicoes) pelo id do usuario logado
     * 
     * @param idInstituicao
     * @return
     * @throws SQLException
     */
    List<CentralSingularVo> listaSingulares(Integer idInstituicao, Integer numCooperativa) throws ComumException;

    /**
     * Método responsável por obter a lista de unidades por numero de cooperativa ou por idInstituicao. preencha um parametro e set null para o outro.
     * 
     * @param idInstituicao
     * @return
     * @throws SQLException
     */
    List<UnidadeVo> listaUnidades(Integer numCooperativa, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por buscar a instituicao pelo id do usuario logado
     * 
     * @param idInstituicao
     * @return
     */
    InstituicaoDto obterIdInstituicaoPai(Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por obter o tipo de grau cooperativo
     * 
     * @param idInstituicao
     * @return
     * @throws SQLException
     */
    InstituicaoVo obterTipoGrauCooperativo(Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @return
     * @throws SQLException
     */
    Integer obterCooperativaPorInstituicao(Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws SQLException
     */
    List<InstituicaoCooperativaDto> listaCentrais() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @return
     * @throws SQLException
     */
    List<InstituicaoCooperativaDto> listaSingulares(Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por recuperar uma lista de instituições SCI através da VIW_INSTITUICAO.
     * 
     * @param idInstituicao
     * @return InstituicaoDTO
     * 
     */
    List<InstituicaoDto> listarInstituicao() throws ComumException;

    /**
     * Método responsável por recuperar a lista de instituições no Cache Central;
     * 
     * @return
     * @throws ComumException List<InstituicaoDto>
     */
    List<InstituicaoDto> listarInstituicaoCache() throws ComumException;

    /**
     * Método responsável por recuperar obter instituição SCI através da VIW_INSTITUICAO.
     * 
     * @param idInstituicao
     * @return InstituicaoDTO
     * 
     */
    InstituicaoDto obterInstituicao(Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @return
     * @throws ComumException InstituicaoDto
     * 
     */
    @Deprecated
    InstituicaoDto obterInstituicaoView(Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por recuperar obter instituição SCI através da VIW_INSTITUICAO.
     * 
     * @param numeroCooperativa
     * @return InstituicaoDTO
     * 
     */
    InstituicaoDto obterInstituicaoPorCooperativa(Integer numeroCooperativa) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param numeroCooperativa
     * @return
     * @throws ComumException InstituicaoDto
     * 
     */
    @Deprecated
    InstituicaoDto obterInstituicaoPorCooperativaView(Integer numeroCooperativa) throws ComumException;

    /**
     * Método responsável por recuperar obter instituição SCI através da VIW_INSTITUICAO.
     * 
     * @param listaIdInstituicao
     * @return List<InstituicaoDTO>
     * 
     */
    List<InstituicaoDto> listarInstituicao(List<Integer> listaIdInstituicao) throws ComumException;

    /**
     * Método responsável por recuperar um endereço de uma unidadeInstituicao(PA)
     * 
     * @param idInstituicao
     * @param idUnidadeInstituicao
     * @return EnderecoInstituicaoDTO
     * 
     */
    EnderecoInstituicaoDto obterEnderecoUnidadeInstituicao(Integer idInstituicao, Integer idUnidadeInstituicao) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idInstituicaoLogada
     * 
     * @return
     * @throws SQLException
     */
    CentralSingularDto listarCentralSingularUnidade(Integer idInstituicaoLogada) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idInstituicaoLogada
     * @param numeroCooperativa
     * @return
     * @throws SQLException
     */
    CentralSingularDto listarSingularUnidade(Integer idInstituicaoLogada, Integer numeroCooperativa) throws ComumException;
}
