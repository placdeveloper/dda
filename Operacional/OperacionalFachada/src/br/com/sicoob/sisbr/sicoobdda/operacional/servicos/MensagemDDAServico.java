/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.servicos
 * Arquivo:         MensagemDDAServico.java
 * Data Criação:    Jan 3, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroGen0014Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroSerie0200Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoGEN0014Enum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.CadastroGen0014DTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.CadastroSerie0200DTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.DominioCadastroMensagemDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDABeneficiarioVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAConsultaBoletoVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAPagadorVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMensagemDDAServico;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemDDAServico é responsável por
 * 
 * @author Felipe.Rosa
 */
/**
 * MensagemDDAServico é responsável por
 * 
 * @author george.santos
 */
public class MensagemDDAServico extends OperacionalFachada implements IMensagemDDAServico {

    private static final String CADASTRO_DTO = "cadastroDto";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMensagemDDAServico#cadastrarMensagem(br.com.bancoob.sisbrweb.dto.RequisicaoDTO)
     */
    public void cadastrarMensagemDDAPagador(RequisicaoReqDTO dto) throws BancoobException {
        MensagemDDAPagadorVO mensagemVO = (MensagemDDAPagadorVO) getMensagemDDAVO(dto);
        MensagemDDAPagador mensagem = (MensagemDDAPagador) ConversorVO.getInstance().converter(mensagemVO);

        getMensagemDDADelegate().incluir(mensagem, getCodTipoMensagem(dto), Constantes.INTEGER_ZERO, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMensagemDDAServico#cadastrarMensagemDDABeneficiario(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void cadastrarMensagemDDABeneficiario(RequisicaoReqDTO dto) throws BancoobException {
        MensagemDDABeneficiarioVO mensagemVO = (MensagemDDABeneficiarioVO) getMensagemDDAVO(dto);
        MensagemDDABeneficiario mensagem = (MensagemDDABeneficiario) ConversorVO.getInstance().converter(mensagemVO);

        getMensagemDDADelegate().incluir(mensagem, getCodTipoMensagem(dto), Constantes.INTEGER_ZERO, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMensagemDDAServico#cadastrarMensagemDDAConsultaBoleto(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void cadastrarMensagemDDAConsultaBoleto(RequisicaoReqDTO dto) throws BancoobException {
        MensagemDDAConsultaBoletoVO mensagemVO = (MensagemDDAConsultaBoletoVO) getMensagemDDAVO(dto);
        MensagemDDAConsultaBoleto mensagem = (MensagemDDAConsultaBoleto) ConversorVO.getInstance().converter(mensagemVO);
        // Problemas com a conversão de String para Long no Flex.
        mensagem.converteNumIdentBoletoInicial();
        mensagem.converteNumIdentBoletoFinal();

        if (mensagem.getNumCodigoBarra() != null) {
            mensagem.setNumCodBarrasCampoLivre(LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(mensagem.getNumCodigoBarra()));
        }

        getMensagemDDADelegate().incluir(mensagem, getCodTipoMensagem(dto), Constantes.INTEGER_ZERO, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMensagemDDAServico#cadastrarMensagemDDAGEN0014(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void cadastrarMensagemGEN0014(RequisicaoReqDTO dto) throws BancoobException {
        String codTipoSolicitacao = (String) dto.getDados().get("codTipoSolicitacao");
        CadastroGen0014DTO cadastroDTO = (CadastroGen0014DTO) dto.getDados().get(CADASTRO_DTO);

        CadastroGen0014Dto cadastroDto = (CadastroGen0014Dto) ConversorVO.getInstance().converter(cadastroDTO);
        getMensagemGEN0014Delegate().incluir(cadastroDto, TipoSolicitacaoGEN0014Enum.getBy(codTipoSolicitacao));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMensagemDDAServico#cadastrarMensagemAGEN001(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void cadastrarMensagemAGEN001(RequisicaoReqDTO dto) throws BancoobException {
        String mensagemEco = (String) dto.getDados().get("mensagem");
        getMensagemDDAAGEN001Delegate().incluir(mensagemEco);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMensagemDDAServico#cadastrarMensagemDDASerie0200(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void cadastrarMensagemDDASerie0200(RequisicaoReqDTO dto) throws BancoobException {
        CadastroSerie0200DTO cadastroDTO = (CadastroSerie0200DTO) dto.getDados().get(CADASTRO_DTO);
        CadastroSerie0200Dto cadastroDto = (CadastroSerie0200Dto) ConversorVO.getInstance().converter(cadastroDTO);

        getMensagemSerie0200Delegate().incluir(cadastroDto, getCodTipoMensagem(dto), Constantes.INTEGER_ZERO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMensagemDDAServico#cadastrarMensagemDDA0401(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void cadastrarMensagemDDA0401(RequisicaoReqDTO dto) throws BancoobException {
        String codTipoHorario = (String) dto.getDados().get("codTipoHorario");
        DateTime dataReferencia = (DateTime) dto.getDados().get("dataReferencia");

        getMensagemDDA0401Delegate().incluir(codTipoHorario, new DateTimeDB(dataReferencia.getTime()), Constantes.INTEGER_ZERO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMensagemDDAServico#cadastrarMensagemDDA0110(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void cadastrarMensagemDDA0110(RequisicaoReqDTO dto) throws BancoobException {
        String numCodBarras = (String) dto.getDados().get("numCodBarras");

        getMensagemConsultaBoletoPagamentoDelegate().obterBoletoDDA(numCodBarras, getIdInstituicaoLogado(), getUsuarioLogado(), CanalEnum.RETAGUARDA.getId());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMensagemDDAServico#obterDominioCadastroMensagem(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterDominioCadastroMensagem() throws BancoobException {
        DominioCadastroMensagemDTO dominioDTO = (DominioCadastroMensagemDTO) ConversorVO.getInstance().converter(getMensagemDDADelegate().obterDominioCadastroMensagem());

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("dominioDTO", dominioDTO);
        return retorno;

    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return Object
     * 
     */
    private Object getMensagemDDAVO(RequisicaoReqDTO dto) {
        return dto.getDados().get("mensagemVO");
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String getCodTipoMensagem(RequisicaoReqDTO dto) {
        return (String) dto.getDados().get("codTipoMensagem");
    }

    /**
     * @return o atributo usuarioLogado
     */
    public String getUsuarioLogado() {
    	// TODO ver com o GEORGE se é este mesmo o tratamento   
        return ObjectUtil.isNull(getUsuario()) ? null : getUsuario().getLogin();
    }

    /**
     * @return o atributo idInstituicaoLogado
     */
    public Integer getIdInstituicaoLogado() {
        // TODO ver com o George se é este mesmo o tratamento
        return ObjectUtil.isNull(getUsuario()) && ObjectUtil.isNull(getUsuario().getIdInstituicao()) ? null : Integer.valueOf(getUsuario().getIdInstituicao());

    }

}