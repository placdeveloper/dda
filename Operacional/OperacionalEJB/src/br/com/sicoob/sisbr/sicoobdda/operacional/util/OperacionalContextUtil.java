/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.util
 * Arquivo:         ComumContextUtil.java
 * Data Cria��o:    4 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.util;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;

/**
 * ComumContextUtil � respons�vel por
 * 
 * @author Danilo.Barros
 */
public class OperacionalContextUtil {

    private OperacionalContextUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * M�todo respons�vel por
     * 
     * @param
     * @return UsuarioBancoob
     * @throws
     */
    public static UsuarioBancoob getUsuario() {
        return (UsuarioBancoob) ContextoHttp.getInstance().getUserPrincipal();
    }

    /**
     * M�todo respons�vel por
     * 
     * @param IdInstituicao
     * @return InstituicaoDto
     * @throws ComumException
     */
    public static InstituicaoDto getInstituicao(String IdInstituicao) throws ComumException {
        return IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate().obterInstituicaoCache(Integer.parseInt(IdInstituicao));
    }

}
