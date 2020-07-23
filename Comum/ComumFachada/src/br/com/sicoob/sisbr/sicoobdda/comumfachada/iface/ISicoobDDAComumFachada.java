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
     * Método responsável por buscar a instituição (cooperativa) autenticada no sistema.
     * 
     * @return InstituicaoDTO
     * @throws ComumException
     */
    InstituicaoDto getInstituicao() throws ComumException;

    /**
     * Método responsável por recuperar o usuário da logado.
     * 
     * @return UsuarioBancoob
     */
    UsuarioBancoob getUsuario();
    
    /**
     * @return ContextoHttp
     */
    ContextoHttp getContextoHttp();

}
