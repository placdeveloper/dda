package br.com.sicoob.sisbr.sicoobdda.comumfachada.servico;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.InstituicaoDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto.CentralSingularDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.UnidadeVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.CentralSingularDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.iface.ICentraisSingularesFachada;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.util.ComumFachadaUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.UnidadeVO;

/**
 * CentraisSingularesServico é responsável as chamadas dos servicos
 * 
 * @author Daniel.Moraes
 */
@RemoteService
public class CentraisSingularesServico extends BancoobFachada implements ICentraisSingularesFachada {

    private ComumFachadaUtil comumFachadaUtil = new ComumFachadaUtil();
    private static int ID_TODAS_INSTITUICOES = 1;
    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.fachada.iface.ICentraisSingularesFachada#listarSingulares(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO listarSingulares(RequisicaoReqDTO dto) throws ComumException, SQLException {
        RetornoDTO retorno = new RetornoDTO();

        Integer numeroCooperativa = (Integer) dto.getDados().get("numeroCooperativa");

        Integer idInstituicao = exibirPorAgenciaLogada(dto);

        CentralSingularDto centralSingularDto = getInstituicaoDelegate().listarSingularUnidade(idInstituicao, numeroCooperativa);
        CentralSingularDTO centralSingularDTO = (CentralSingularDTO) converteObjeto(centralSingularDto);

        retorno.getDados().put("instituicoes", centralSingularDTO.getListaSingular());
        retorno.getDados().put("unidades", centralSingularDTO.getListaUnidade());

        return retorno;
    }

    /**
     * Método responsável por
     * 
     * @param dto void
     * 
     */
    private Integer exibirPorAgenciaLogada(RequisicaoReqDTO dto) {
        Boolean exibirPorAgenciaLogada = (Boolean) dto.getDados().get("exibirPorAgenciaLogada");
        Integer exibirCoopEspecifica = (Integer) dto.getDados().get("exibirCoopEspecifica");
        if (exibirPorAgenciaLogada) {
            return Integer.parseInt(getUsuario().getIdInstituicao());
        } else if (!ObjectUtil.isNull(exibirCoopEspecifica)) {
            return exibirCoopEspecifica;
        } else {
            return ID_TODAS_INSTITUICOES;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.fachada.iface.ICentraisSingularesFachada#listarUnidades(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO listarUnidades(RequisicaoReqDTO dto) throws ComumException, SQLException {
        RetornoDTO retorno = new RetornoDTO();
        List<UnidadeVo> listaUnidadeVo = new ArrayList<UnidadeVo>();

        Integer numeroCooperativa = (Integer) dto.getDados().get("numeroCooperativa");
        listaUnidadeVo.addAll(getInstituicaoDelegate().listaUnidades(numeroCooperativa, null));
        List<UnidadeVO> listaUnidadeVO = (List<UnidadeVO>) converteLista(listaUnidadeVo);

        retorno.getDados().put("unidades", listaUnidadeVO);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.fachada.iface.ICentraisSingularesFachada#listarCentralSingularUnidade(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO listarCentralSingularUnidade(RequisicaoReqDTO dto) throws ComumException, SQLException {
        RetornoDTO retorno = new RetornoDTO();

        Integer idInstituicao = exibirPorAgenciaLogada(dto);
        CentralSingularDto centraiSingularDto = getInstituicaoDelegate().listarCentralSingularUnidade(idInstituicao);
        CentralSingularDTO centralSingularDTO = (CentralSingularDTO) converteObjeto(centraiSingularDto);
        retorno.getDados().put("dto", centralSingularDTO);

        return retorno;
    }

    /**
     * Método responsável por buscar o usuario logado.
     * 
     * @return
     */
    private UsuarioBancoob getUsuario() {
        return this.comumFachadaUtil.getUsuario();
    }

    private InstituicaoDelegate getInstituicaoDelegate() {
        return ComumFabricaDelegates.getInstance().getInstituicaoDelegate();
    }

    private Object converteObjeto(Object objeto) throws ComumException {
        return ConversorVO.getInstance().converter(objeto);
    }

    private List<?> converteLista(List<?> list) throws ComumException {
        return ConversorVO.getInstance().converter(list);
    }
}
