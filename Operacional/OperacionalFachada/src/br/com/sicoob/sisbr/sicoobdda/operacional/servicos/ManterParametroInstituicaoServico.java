package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaParametroDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.CentralSingularVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ParametroVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoParametroVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ValorParametroVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IManterParametroInstituicao;

/**
 * ManterParametroInstituicaoServico é responsável por
 * 
 * @author Felipe.Rosa
 */
@RemoteService
@Deprecated
public class ManterParametroInstituicaoServico extends OperacionalFachada implements IManterParametroInstituicao {

    /**
     * 
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.operacionalbackoffice.iface.IManterParametroInstituicao#obterDadosParametroInstituicao(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO obterDadosParametroInstituicao(SelGeralReqDTO dto) throws ComumException {
        ConsultaDto<ConsultaParametroDTO> consultaFiltroDto = montarConsultaDto(dto, ConsultaParametroDTO.class);
        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consultaFiltroDto.getFiltro();
        ConsultaParametroDTO consultaDTO = (ConsultaParametroDTO) requisicaoReqDTO.getDados().get("dto");

        InstituicaoDto instituicao = getInstituicao();
        int idInstituicao = instituicao.getIdInstituicao();

        // Se não for o Sicoob nem o Bancoob, então só busca os parâmetros visíveis.
        consultaDTO.setSomenteVisiveis(isSomenteVisiveis(idInstituicao));

        consultaFiltroDto.setFiltro(consultaDTO);
        consultaFiltroDto = getAtualizaParametroDelegate().pesquisar(ConsultaParametroDTO.class, consultaFiltroDto, PesquisaEnum.PESQUISAR_PARAMETRO_INSTITUICAO);
        List<ConsultaParametroDTO> resultado = (List<ConsultaParametroDTO>) ConversorVO.getInstance().converter(consultaFiltroDto.getResultado());

        consultaFiltroDto.setResultado(resultado);
        return montarResultado(consultaFiltroDto);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.operacionalbackoffice.iface.IManterParametroInstituicao#alterarParametroInstituicao(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */

    @SuppressWarnings("unchecked")
    public RetornoDTO alterarParametroInstituicao(RequisicaoReqDTO dto) throws ComumException {
        RetornoDTO retorno = new RetornoDTO();

        ParametroVO parametroVO = (ParametroVO) dto.getDados().get("vo");
        List<ValorParametroVO> listaValorParametroVO = (List<ValorParametroVO>) dto.getDados().get("listaInstitucao");
        String idInstituicaoString = (String) dto.getDados().get("idInstituicao");
        Long idInstituicao = Long.parseLong(idInstituicaoString);

        List<CentralSingularVO> listaCentralSingularVO = (List<CentralSingularVO>) dto.getDados().get("listaSingular");

        List<Integer> listaNumCooperativa = new ArrayList<Integer>();

        listaNumCooperativa.add(getInstituicao().getNumCooperativa());

        for (ValorParametroVO valorParametro : listaValorParametroVO) {
            for (int i = 0; i < listaCentralSingularVO.size(); i++) {
                CentralSingularVO vo = listaCentralSingularVO.get(i);

                if (vo.getIdInstituicao().equals(valorParametro.getIdInstituicao())) {
                    listaNumCooperativa.add(vo.getNumeroCooperativa());
                    listaCentralSingularVO.remove(vo);
                    break;
                }
            }
        }

        ParametroDDA parametro = (ParametroDDA) ConversorVO.getInstance().converter(parametroVO);
        List<ValorParametroDDA> listaValorParametro = (List<ValorParametroDDA>) ConversorVO.getInstance().converter(listaValorParametroVO);

        getAtualizaParametroDelegate().atualizarListaValorParametro(parametro, listaValorParametro, idInstituicao, listaNumCooperativa);

        retorno.getDados().put("vo", (ParametroVO) ConversorVO.getInstance().converter(parametro));
        return retorno;
    }

    private boolean isSomenteVisiveis(int idInstituicao) {
        return idInstituicao != Constantes.ID_BANCOOB && idInstituicao != Constantes.ID_SICOOB;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.operacionalbackoffice.iface.IManterParametroInstituicao#obterDados(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO obterDados(RequisicaoReqDTO dto) throws ComumException {
        Integer idInstituicao = getInstituicao().getIdInstituicao();

        Long idParametro = Long.parseLong(dto.getDados().get("idParametro").toString());
        List<CentralSingularVO> listaCentralSingularVO = (List<CentralSingularVO>) dto.getDados().get("listaSingular");

        List<Integer> listaSingular = new ArrayList<>(listaCentralSingularVO.size());

        for (CentralSingularVO centralSingularVO : listaCentralSingularVO) {
            if (!centralSingularVO.getIdInstituicao().equals(idInstituicao)) {
                listaSingular.add(centralSingularVO.getIdInstituicao());
            }
        }

        List<ValorParametroDDA> listaValorParametro = null;

        if (!ObjectUtil.isEmpty(listaSingular)) {
            listaValorParametro = getAtualizaParametroDelegate().obterListaInstituicao(listaSingular);
        }

        RetornoDTO retorno = new RetornoDTO();

        retorno.getDados().put("listaValorParametroVO", (List<ValorParametroVO>) ConversorVO.getInstance().converter(listaValorParametro));
        retorno.getDados().put("lista", (List<TipoParametroVO>) ConversorVO.getInstance().converter(getAtualizaParametroDelegate().obterListaTipoParametro()));
        retorno.getDados().put("valorParametroInstituicao", getAtualizaParametroDelegate().obterValorParametroInstituicao(idInstituicao, idParametro));

        return retorno;
    }

}
