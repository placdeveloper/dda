package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * IManterParametroInstituicao � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface IManterParametroInstituicao {

    /**
     * M�todo respons�vel por obter os dados do parametro
     * 
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    DadosSelGeralDTO obterDadosParametroInstituicao(SelGeralReqDTO dto) throws ComumException;

    /**
     * 
     * M�todo respons�vel por alterar parametro
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO alterarParametroInstituicao(RequisicaoReqDTO dto) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException
     */
    RetornoDTO obterDados(RequisicaoReqDTO dto) throws ComumException;

}
