package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDADTO;

/**
 * ICMonitoramentoMensagensDDA � respons�vel por
 * 
 * @author Samuell.Ramos
 */
public interface IMonitoramentoMensagensDDA {

    /**
     * M�todo respons�vel carregar a lista de tipos de mensagens com todos os parametros
     * 
     * @param dto
     * @return
     * @throws ComumException
     */
    RetornoDTO listarTipoMensagens(RequisicaoReqDTO dto) throws ComumException;

    /**
     * M�todo respons�vel pela pesquisa p�ginada das mensagens DDA.
     * 
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    DadosSelGeralDTO pesquisaPainelMensagensPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por recuperar o objeto MensagemDDA para o detalhamento.
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO recuperaMensagemDDA(MensagemDDADTO dto) throws ComumException;

    /**
     * M�todo respons�vel por metodo responsagel por reenviar as mensagens a CIP
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO reenviarListaMensagemCip(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por renviar mensagem a CIP
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO reenviarMensagemCip(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException DadosSelGeralDTO
     * @throws ComumNegocioException
     * 
     */
    DadosSelGeralDTO pesquisarErroCargaBeneficiarioPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO recuperaRegistroErroCarga(RequisicaoReqDTO dto) throws ComumException;   

}
