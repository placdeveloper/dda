/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.servicos
 * Arquivo:         TratamenoPendenciaErroServico.java
 * Data Criação:    Sep 13, 2016
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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DadosTratamentoMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemErroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoPendenciaErroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.DadosTratamentoMensagemDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.IdentificadorDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemErroDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TratamentoMesagemDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TratamentoPendenciaErroDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoTratamentoErroCipVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico;
import br.com.sicoob.tipos.DateTime;

/**
 * TratamenoPendenciaErroServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public class TratamentoPendenciaErroServico extends OperacionalFachada implements ITratamentoPendenciaErroServico {

    private static final String MAX_RESULT = "maxResult";
    private static final String TRATAMENTO_STR = "tratamento";
    private static final String DATA_MOVIMENTO_STR = "dataMovimento";
    private static final String COD_TIPO_MENSAGEM_DDA = "codTipoMensagemDDA";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#listarTratamentoPendenciaErro(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO listarTratamentoPendenciaErro() throws ComumException {
        TratamentoPendenciaErroDto tratamentoDto = getTratamentoPendenciaErroDelegate().listarTratamentoPendenciaErro();

        RetornoDTO dto = new RetornoDTO();
        dto.getDados().put(TRATAMENTO_STR, (TratamentoPendenciaErroDTO) ConversorVO.getInstance().converter(tratamentoDto));
        return dto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#obterDadosTratamentoMensagemPendencia(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterDadosTratamentoMensagemPendencia(RequisicaoReqDTO dto) throws ComumException {
        DateTime dataMovimento = (DateTime) dto.getDados().get(DATA_MOVIMENTO_STR);
        Short codSituacaoMensagemDDA = ((Integer) dto.getDados().get("codSituacao")).shortValue();
        String codTipoMensagemDDA = ((String) dto.getDados().get("codTipoMensagemDDA"));
        Integer maxResult = ((Integer) dto.getDados().get(MAX_RESULT));

        DadosTratamentoMensagemDto dadosTratamentoDto = getTratamentoPendenciaErroDelegate().obterDadosTratamentoMensagemPendencia(dataMovimento, codSituacaoMensagemDDA,
                codTipoMensagemDDA, maxResult);

        return preparaDadosTratamentoRetornoDTO(dadosTratamentoDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#obterDadosTratamentoMensagemErroAgrupado(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterDadosTratamentoMensagemErroAgrupado(RequisicaoReqDTO dto) throws ComumException {
        String codTipoErroCIP = (String) dto.getDados().get("codErro");
        Integer maxResult = ((Integer) dto.getDados().get(MAX_RESULT));
        DadosTratamentoMensagemDto dadosTratamentoDto = getTratamentoPendenciaErroDelegate().obterDadosTratamentoMensagemErroAgrupado(codTipoErroCIP, maxResult);

        return preparaDadosTratamentoRetornoDTO(dadosTratamentoDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#obterDadosTratamentoArquivoErroRetornoCIP(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterDadosTratamentoArquivoErroRetornoCIP(RequisicaoReqDTO dto) throws ComumException {
        DateTime dataMovimento = (DateTime) dto.getDados().get(DATA_MOVIMENTO_STR);
        Integer maxResult = ((Integer) dto.getDados().get(MAX_RESULT));
        DadosTratamentoMensagemDto dadosTratamentoDto = getTratamentoPendenciaErroDelegate().obterDadosTratamentoArquivoErroRetornoCIP(dataMovimento, maxResult);

        return preparaDadosTratamentoRetornoDTO(dadosTratamentoDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#obterDadosTratamentoMensagemErroRetornoCIP(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO obterDadosTratamentoMensagemErroRetornoCIP() throws ComumException {
        List<TipoTratamentoErroCip> listaTratamento = getTratamentoPendenciaErroDelegate().listarTipoTratamentoMensagemContingencia();

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("listaTratamento", (List<TipoTratamentoErroCipVO>) ConversorVO.getInstance().converter(listaTratamento));
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#pesquisarMensagemContingenciaPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarMensagemContingenciaPaginado(SelGeralReqDTO dto) throws BancoobException {
        ConsultaDto<MensagemErroDTO> consulta = montarConsultaDto(dto, MensagemErroDTO.class);
        RequisicaoReqDTO reqDto = (RequisicaoReqDTO) consulta.getFiltro();
        MensagemErroDTO msgErroDto = new MensagemErroDTO();
        msgErroDto.setCodTipoMensagemDDA((String) reqDto.getDados().get(COD_TIPO_MENSAGEM_DDA));
        msgErroDto.setDataMovimento((DateTime) reqDto.getDados().get(DATA_MOVIMENTO_STR));

        consulta.setFiltro(msgErroDto);

        ConsultaDto<MensagemErroDTO> consultaDto = getTratamentoPendenciaErroDelegate().pesquisar(MensagemErroDto.class, consulta,
                PesquisaEnum.PESQUISAR_MENSAGEM_ERRO_CONTINGENCIA);
        consultaDto.setResultado((List<MensagemErroDTO>) ConversorVO.getInstance().converter(consultaDto.getResultado()));
        return montarResultado(consultaDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#executarTratamentoMensagem(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void executarTratamentoMensagem(RequisicaoReqDTO dto) throws BancoobException {
        TratamentoMesagemDTO tratamentoDTO = (TratamentoMesagemDTO) dto.getDados().get(TRATAMENTO_STR);
        TratamentoMesagemDto tratamento = (TratamentoMesagemDto) ConversorVO.getInstance().converter(tratamentoDTO);
        getTratamentoPendenciaErroDelegate().executarTratamentoMensagem(tratamento);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#executarTratamentoAutomatizadoMensagem()
     */
    public void executarTratamentoAutomatizadoMensagem() throws BancoobException {
        getTratamentoPendenciaErroDelegate().executarTratamentoAutomatizadoMensagem();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#obterDetalheMensagemErro(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterDetalheMensagemErro(RequisicaoReqDTO dto) throws BancoobException {
        IdentificadorDTO identificadorDTO = (IdentificadorDTO) dto.getDados().get("identificadorDTO");
        MensagemDDA mensagemDDA = getTratamentoPendenciaErroDelegate().obterDetalheMensagemErro(identificadorDTO.getIdentificadorGeral());
        MensagemDDAVO mensagemDDAVO = (MensagemDDAVO) ConversorVO.getInstance().converter(mensagemDDA);
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("mensagem", mensagemDDAVO);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#listarTipoTratamentoSitMensagemRetornoComErro()
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO listarTipoTratamentoSitMensagemRetornoComErro() throws BancoobException {
        List<TipoTratamentoErroCip> listaTipoTratamento = getTratamentoPendenciaErroDelegate().listarTipoTratamentoSitMensagemRetornoComErro();
        List<TipoTratamentoErroCipVO> listaTipoTratamentoVO = (List<TipoTratamentoErroCipVO>) ConversorVO.getInstance().converter(listaTipoTratamento);
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("listaTipoTratamento", listaTipoTratamentoVO);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#exluirMensagemErro(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void exluirMensagemErro(RequisicaoReqDTO dto) throws BancoobException {
        IdentificadorDTO identificadorDTO = (IdentificadorDTO) dto.getDados().get("identificadorDTO");
        getTratamentoPendenciaErroDelegate().excluirMensagemErro(identificadorDTO.getIdentificadorGeral());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITratamentoPendenciaErroServico#exluirMensagemErro(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public void excluirListaMensagemErro(RequisicaoReqDTO dto) throws BancoobException {
        List<MensagemDDAVO> listaMensagemDDAVO = (List<MensagemDDAVO>) dto.getDados().get("listaMensagemDDA");
        getTratamentoPendenciaErroDelegate().excluirListaMensagemErro((List<MensagemDDA>) ConversorVO.getInstance().converter(listaMensagemDDAVO));
    }

    /**
     * Método responsável por
     * 
     * @param dadosTratamentoDto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    private RetornoDTO preparaDadosTratamentoRetornoDTO(DadosTratamentoMensagemDto dadosTratamentoDto) throws ComumException {
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("dadosTratamento", (DadosTratamentoMensagemDTO) ConversorVO.getInstance().converter(dadosTratamentoDto));
        return retorno;
    }
}
