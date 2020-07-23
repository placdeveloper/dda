/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-interna-privada-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracao.interna.privada.negocio.servicos
 * Arquivo:         SCIServico.java
 * Data Criação:    Oct 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;

/**
 * SCIServico
 * 
 * @author Samuell.Ramos
 */
@Deprecated
public interface SCIServico extends IntegracaoInternaServico {

    /**
     * Método responsável por obter instituicao do cache por id instituicao
     * 
     * @param idInstituicao
     * @return
     * @throws ComumException
     */
    InstituicaoDto obterInstituicaoCache(Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por obter instituicao do cache por numero de cooperativa
     * 
     * @param numeroCooperativa
     * @return
     * @throws ComumException
     */
    InstituicaoDto obterInstituicaoPorCooperativaCache(Integer numeroCooperativa) throws ComumException;

}
