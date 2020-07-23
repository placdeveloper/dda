/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.servicos
 * Arquivo:         TipoErroCipServico.java
 * Data Criação:    Sep 22, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoErroCipDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoErroCipVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoErroCipServico;

/**
 * TipoErroCipServico é responsável por 
 * 
 * @author Felipe.Rosa
 */
public class TipoErroCipServico extends OperacionalFachada implements ITipoErroCipServico {

    private static final String TIPO_ERRO = "tipoErro";

    /**
     * {@inheritDoc}
     *
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoErroCipServico#pesquisarTipoErroCipPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarTipoErroCipPaginado(SelGeralReqDTO dto) throws BancoobException {
        ConsultaDto<TipoErroCipDTO> consulta = montarConsultaDto(dto, TipoErroCipDTO.class);
        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consulta.getFiltro();
        consulta.setFiltro(requisicaoReqDTO.getDados().get("dto".toString()));
        consulta = getTipoErroCipDelegate().pesquisar(TipoErroCipDTO.class, consulta, PesquisaEnum.PESQUISAR_TIPO_ERRO_CIP);
        consulta.setResultado((List<TipoErroCipDTO>) ConversorVO.getInstance().converter(consulta.getResultado()));
        return montarResultado(consulta);
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    public RetornoDTO obterTipoErro(RequisicaoReqDTO dto) throws BancoobException {
        TipoErroCip tipoErro = getTipoErroCipDelegate().obterTipoErro(obtemCodTipoErro(dto));
        return preparaRetornoTipoErro(tipoErro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoErroCipServico#incluirTipoErro(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO incluirTipoErro(RequisicaoReqDTO dto) throws BancoobException {
        TipoErroCip tipoErro = obtemTipoErroVO(dto);
        tipoErro = getTipoErroCipDelegate().incluirTipoErro(tipoErro);
        return preparaRetornoTipoErro(tipoErro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoErroCipServico#alterarTipoErro(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO alterarTipoErro(RequisicaoReqDTO dto) throws BancoobException {
        TipoErroCip tipoErro = obtemTipoErroVO(dto);
        tipoErro = getTipoErroCipDelegate().alterarTipoErro(tipoErro);
        return preparaRetornoTipoErro(tipoErro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoErroCipServico#excluirTipoErro(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void excluirTipoErro(RequisicaoReqDTO dto) throws BancoobException {
        getTipoErroCipDelegate().excluirTipoErro(obtemCodTipoErro(dto));
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String obtemCodTipoErro(RequisicaoReqDTO dto) {
        return (String) dto.getDados().get("codTipoErro");
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException TipoErroCip
     * 
     */
    private TipoErroCip obtemTipoErroVO(RequisicaoReqDTO dto) throws ComumException {
        TipoErroCipVO tipoErroVO = (TipoErroCipVO) dto.getDados().get(TIPO_ERRO);
        TipoErroCip tipoErro = (TipoErroCip) ConversorVO.getInstance().converter(tipoErroVO);
        return tipoErro;
    }

    /**
     * Método responsável por
     * 
     * @param tipoErro
     * @return RetornoDTO
     * @throws ComumException
     * 
     */
    private RetornoDTO preparaRetornoTipoErro(TipoErroCip tipoErro) throws ComumException {
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put(TIPO_ERRO, (TipoErroCipVO) ConversorVO.getInstance().converter(tipoErro));
        return retorno;
    }

}
