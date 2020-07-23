/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.servico
 * Arquivo:         ParametroServico.java
 * Data Criação:    18 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.servico;

import java.util.List;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ParametroDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParametroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ParametroDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.iface.IParametroServico;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ParametroVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoParametroVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;

/**
 * ParametroServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ParametroServico extends BancoobFachada implements IParametroServico {

    /**
     * Método responsável por
     * 
     * @return ParametroDelegate
     * 
     */
    private ParametroDelegate getParametroDelegate() {
        return ComumFabricaDelegates.getInstance().getParametroDelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comumfachada.iface.IParametroServico#obterDadosPesquisa(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @Override
    public RetornoDTO obterDadosPesquisa() throws ComumException, ComumNegocioException {
        ParametroDto parametroDto = getParametroDelegate().obterDadosPesquisa();

        ParametroDTO parametroDTO = (ParametroDTO) ConversorVO.getInstance().converter(parametroDto);

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("parametroDTO", parametroDTO);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comumfachada.iface.IParametroServico#obterDadosValorParametroPesquisa()
     */
    public RetornoDTO obterDadosValorParametroPesquisa() throws ComumException, ComumNegocioException {
        ParametroDto parametroDto = getParametroDelegate().obterDadosValorParametroPesquisa();
        ParametroDTO parametroDTO = (ParametroDTO) ConversorVO.getInstance().converter(parametroDto);

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("parametroDTO", parametroDTO);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comumfachada.iface.IParametroServico#obterDadosParametro(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @Override
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO obterDadosParametro(SelGeralReqDTO dto) throws ComumException, ComumNegocioException {
        ConsultaDto<ParametroDDA> consultaDto = montarConsultaDto(dto, ParametroDDA.class);
        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consultaDto.getFiltro();
        ParametroVO vo = (ParametroVO) requisicaoReqDTO.getDados().get("vo");

        ParametroDDA parametro = (ParametroDDA) ConversorVO.getInstance().converter(vo);

        consultaDto.setFiltro(parametro);

        consultaDto = getParametroDelegate().pesquisar(ParametroDDA.class, consultaDto, PesquisaEnum.PESQUISAR_PARAMETRO);

        List<ParametroVO> resultado = (List<ParametroVO>) ConversorVO.getInstance().converter(consultaDto.getResultado());

        ConsultaDto<ParametroVO> consultaDTO = montarConsultaDto(dto, ParametroVO.class);
        consultaDTO.setResultado(resultado);
        consultaDTO.setTotalRegistros(consultaDto.getTotalRegistros());
        return montarResultado(consultaDTO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comumfachada.iface.IParametroServico#listarTipoParametro()
     */
    @Override
    @SuppressWarnings("unchecked")
    public RetornoDTO listarTipoParametro() throws ComumException {
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("lista", (List<TipoParametroVO>) ConversorVO.getInstance().converter(getParametroDelegate().listarTipoParametro()));
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comumfachada.iface.IParametroServico#incluirParametro(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @Override
    public RetornoDTO incluirParametro(RequisicaoReqDTO dto) throws ComumException {
        ParametroVO parametroVO = (ParametroVO) dto.getDados().get("vo");
        ParametroDDA parametro = (ParametroDDA) ConversorVO.getInstance().converter(parametroVO);
        parametro = getParametroDelegate().incluirParametro(parametro);

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("vo", (ParametroVO) ConversorVO.getInstance().converter(parametro));
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comumfachada.iface.IParametroServico#alterarParametro(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @Override
    public RetornoDTO alterarParametro(RequisicaoReqDTO dto) throws ComumException {
        ParametroVO parametroVO = (ParametroVO) dto.getDados().get("vo");
        ParametroDDA parametro = (ParametroDDA) ConversorVO.getInstance().converter(parametroVO);
        parametro.setDataHoraUltimaAtualizacao(new DateTimeDB());

        getParametroDelegate().alterarParametro(parametro);

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("vo", (ParametroVO) ConversorVO.getInstance().converter(parametro));
        return retorno;
    }

}
