/**
 * 
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.util;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;

/**
 * @author Lucas.Moura
 * 
 */
public class ComumFachadaUtil {

    private ContextoHttp contextoHttp;

    public ContextoHttp getContextoHttp() {
        if (this.contextoHttp == null) {
            this.contextoHttp = ContextoHttp.getInstance();
        }
        return contextoHttp;
    }

    /**
     * Método responsável por buscar a instituição (cooperativa) autenticada no sistema.
     * 
     * @return InstituicaoDTO
     * @throws NumberFormatException
     */
    public InstituicaoDto getInstituicao() throws ComumException {
        return IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate().obterInstituicaoCache(Integer.valueOf(getUsuario().getIdInstituicao()));
    }

    /**
     * Método responsável por recuperar o usuário da logado.
     * 
     * @return UsuarioBancoob
     */
    public UsuarioBancoob getUsuario() {
        return (UsuarioBancoob) ContextoHttp.getInstance().getUserPrincipal();
    }

}