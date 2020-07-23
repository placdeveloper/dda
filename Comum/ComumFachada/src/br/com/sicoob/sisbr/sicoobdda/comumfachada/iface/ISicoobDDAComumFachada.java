/**
 * 
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.iface;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;

/**
 * @author Lucas.Moura
 * 
 */
public interface ISicoobDDAComumFachada {

    /**
     * M�todo respons�vel por buscar a institui��o (cooperativa) autenticada no sistema.
     * 
     * @return InstituicaoDTO
     * @throws ComumException
     */
    InstituicaoDto getInstituicao() throws ComumException;

    /**
     * M�todo respons�vel por recuperar o usu�rio da logado.
     * 
     * @return UsuarioBancoob
     */
    UsuarioBancoob getUsuario();
    
    /**
     * @return ContextoHttp
     */
    ContextoHttp getContextoHttp();

}
