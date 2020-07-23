package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.List;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoMensagem106Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PesquisaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.HistoricoMensagem106DTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PesquisaBoletoDDADTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoBoletoVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaBoletoDDAServico;

/**
 * ConsultaBoletoDDAServico é responsável por
 * 
 * @author george.santos
 */
@RemoteService
public class ConsultaBoletoDDAServico extends OperacionalFachada implements IConsultaBoletoDDAServico {

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaBoletoDDAServico#pesquisarConsultaBoletoDDAPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarConsultaBoletoDDAPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException {
        ConsultaDto<PesquisaBoletoDDADTO> consultaDto = montarConsultaDto(dto, PesquisaBoletoDDADTO.class);
        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consultaDto.getFiltro();

        PesquisaBoletoDDADto pesquisaBoletoDDADto = (PesquisaBoletoDDADto) ConversorVO.getInstance().converter(requisicaoReqDTO.getDados().get("pesquisaBoletoDDADTO"));

        if (ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumCodigoBarra()) && !ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumLinhaDigitavel())) {
            pesquisaBoletoDDADto.setNumCodigoBarra(LinhaDigitavelCodigoBarrasUtil.obterCodigoBarrasPorLinhaDigitavel(pesquisaBoletoDDADto.getNumLinhaDigitavel()));
        }

        pesquisaBoletoDDADto.setNumCodigoBarraCampoLivre(
                !ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumCodigoBarra()) ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(pesquisaBoletoDDADto.getNumCodigoBarra()) : null);

        validaPesquisaBoletoDDADto(pesquisaBoletoDDADto);

        consultaDto.setFiltro(pesquisaBoletoDDADto);

        consultaDto = getConsultaBoletoDDADelegate().pesquisar(PesquisaBoletoDDADto.class, consultaDto, PesquisaEnum.PESQUISAR_BOLETO_PAGINADO);

        List<PesquisaBoletoDDADTO> resultado = (List<PesquisaBoletoDDADTO>) ConversorVO.getInstance().converter(consultaDto.getResultado());

        consultaDto.setResultado(resultado);

        return montarResultado(consultaDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaBoletoDDAServico#pesquisarHistoricoMensagem106Paginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarHistoricoMensagem106Paginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException {
        ConsultaDto<HistoricoMensagem106DTO> consultaDto = montarConsultaDto(dto, HistoricoMensagem106DTO.class);
        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consultaDto.getFiltro();

        String numCodigoBarra = (String) requisicaoReqDTO.getDados().get("numCodigoBarra");

        if (!ObjectUtil.isEmpty(numCodigoBarra) && numCodigoBarra.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            numCodigoBarra = LinhaDigitavelCodigoBarrasUtil.obterCodigoBarrasPorLinhaDigitavel(numCodigoBarra);
        }

        consultaDto.setFiltro(!ObjectUtil.isEmpty(numCodigoBarra) ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(numCodigoBarra) : null);

        consultaDto = getConsultaBoletoDDADelegate().pesquisar(HistoricoMensagem106Dto.class, consultaDto, PesquisaEnum.PESQUISAR_HISTORICO_MENSAGEM_106_PAGINADO);

        List<HistoricoMensagem106DTO> resultado = (List<HistoricoMensagem106DTO>) ConversorVO.getInstance().converter(consultaDto.getResultado());

        consultaDto.setResultado(resultado);

        return montarResultado(consultaDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaBoletoDDAServico#listarSituacoesBoleto(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO listarSituacoesBoleto(RequisicaoReqDTO dto) throws ComumException {
        List<SituacaoBoletoVO> listaSituacaoBoletoVO = (List<SituacaoBoletoVO>) ConversorVO.getInstance().converter(getConsultaBoletoDDADelegate().listarSituacaoBoletoDDA());

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("listaSituacaoBoletoVO", listaSituacaoBoletoVO);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaBoletoDDAServico#consultarBoletoCIP(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO consultarBoletoCIP(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        String numCodigoBarra = (String) dto.getDados().get("numCodigoBarra");
        Integer codSituacaoBoleto = (Integer) dto.getDados().get("codSituacaoBoleto");

        if (!ObjectUtil.isEmpty(numCodigoBarra) && numCodigoBarra.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            numCodigoBarra = LinhaDigitavelCodigoBarrasUtil.obterCodigoBarrasPorLinhaDigitavel(numCodigoBarra);
        }

        getConsultaBoletoDDADelegate().consultarBoletoCIP(numCodigoBarra, CanalEnum.RETAGUARDA.getId(), codSituacaoBoleto != null ? codSituacaoBoleto.shortValue() : null);

        return new RetornoDTO();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaBoletoDDAServico#obterNomeBanco(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterNomeBanco(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        Short numeroBanco = Short.valueOf((String) dto.getDados().get("numeroBanco"));

        String nomeBanco = getConsultaBoletoDDADelegate().obterNomeBanco(numeroBanco);

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("nomeBanco", nomeBanco);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaBoletoDDAServico#obterNomeBanco(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterBoletoDDA(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        String numCodigoBarra = (String) dto.getDados().get("numCodigoBarra");
        Integer codSituacaoBoleto = (Integer) dto.getDados().get("codSituacaoBoleto") != null ? (Integer) dto.getDados().get("codSituacaoBoleto") : null;

        BoletoDDA boletoDDA = getConsultaBoletoDDADelegate().obterBoletoDDA(numCodigoBarra, codSituacaoBoleto);

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("boletoDDAVO", ConversorVO.getInstance().converter(boletoDDA));
        return retorno;
    }

    public RetornoDTO consultaBoletoCIPHabilitadoPorInstituicao(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("consultaHabilitada", getConsultaBoletoDDADelegate().isConsultaBoletoCIPHabilitadoPorInstituicao(Integer.valueOf(getUsuario().getIdInstituicao())));
        return retorno;
    }

    /**
     * Método responsável por fazer a validacao do pesquisaBoletoDDADto
     * 
     * @param pesquisaBoletoDDADto void
     * 
     */
    private void validaPesquisaBoletoDDADto(PesquisaBoletoDDADto pesquisaBoletoDDADto) {
        if (ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumCodigoBarra())) {
            pesquisaBoletoDDADto.setNumCodigoBarra(null);
        }
        if (ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumCpfCnpjBeneficiario())) {
            pesquisaBoletoDDADto.setNumCpfCnpjBeneficiario(null);
        }
        if (ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumCpfCnpjPagador())) {
            pesquisaBoletoDDADto.setNumCpfCnpjPagador(null);
        }
    }
}