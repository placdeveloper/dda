package br.com.sicoob.sisbr.sicoobdda.comum.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * ParametroNaoEncontradoException � respons�vel por
 * 
 * @author George.Santos
 */
@ApplicationException(rollback = true)
public class ParametroNaoEncontradoException extends ComumNegocioException {

    private static final long serialVersionUID = -1053700890110185995L;

    /**
     * @param idParametro
     * @param numCooperativa
     */
    public ParametroNaoEncontradoException(Integer idParametro, Integer numCooperativa) {
        // O texto n�o � lido quando est� no arquivo de mensagens
        super(MensagemUtil.getString("Par�metro {1} n�o encontrado na cooperativa {2}.", idParametro, numCooperativa));
    }

    /**
     * @param idParametro
     * @param idInstituicao
     */
    public ParametroNaoEncontradoException(Long idParametro, Integer idInstituicao) {
        // O texto n�o � lido quando est� no arquivo de mensagens
        super(MensagemUtil.getString("Par�metro {1} n�o encontrado na institui��o {2}.", idParametro, idInstituicao));
    }

}
