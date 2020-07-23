package br.com.sicoob.sisbr.sicoobdda.operacional.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.mockito.Mockito;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlteraSituacaoBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlterarCadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioAlteracaoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioCadastroDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroCargaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendenteBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RepresentanteBeneficiarioAlteracaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RepresentanteBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.ProdutoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoConvenioBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoRelacionamentoParticipanteEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoProdutoConvenioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.CategoriaMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoContingencia;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.SubTipoGrade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoContingencia;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoModeloCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiarioAlertaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BoletoPagamentoContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;

/**
 * FabricaUtilTest é responsável por
 * 
 * @author felipe.rosa
 */
public class FabricaUtilMockito extends Mockito {

    private StringBuffer msgXml = new StringBuffer().append("<MQEnvio><Header><Cooperativa>1</Cooperativa>").append("<DataHora>2017-06-02T10:06:53</DataHora>")
            .append("<NumSeq>2375251</NumSeq>").append("</Header><MsgSPB><DDA0108>").append("<CodMsg>DDA0108</CodMsg>").append("<NumCtrlPart>2375251</NumCtrlPart>")
            .append("<ISPBPartRecbdrPrincipal>02038232</ISPBPartRecbdrPrincipal>").append("<ISPBPartRecbdrAdmtd>02038232</ISPBPartRecbdrAdmtd>")
            .append("<NumIdentcTit>2017032800000255990</NumIdentcTit>").append("<NumRefCadTitBaixaOperac>1490723503243000328</NumRefCadTitBaixaOperac>")
            .append("<TpBaixaOperac>3</TpBaixaOperac>").append("<ISPBPartRecbdrBaixaOperac>02038232</ISPBPartRecbdrBaixaOperac>")
            .append("<CodPartRecbdrBaixaOperac>756</CodPartRecbdrBaixaOperac>").append("<TpPessoaPort>J</TpPessoaPort>").append("<CNPJ_CPFPort>83009928000164</CNPJ_CPFPort>")
            .append("<DtHrProcBaixaOperac>2017-06-02T10:06:46</DtHrProcBaixaOperac>").append("<DtProcBaixaOperac>2017-06-02</DtProcBaixaOperac>")
            .append("<VlrBaixaOperacTit>2000.50</VlrBaixaOperacTit>").append("<NumCodBarrasBaixaOperac>74598717300002000503100038912011000000001222</NumCodBarrasBaixaOperac>")
            .append("<CanPgto>3</CanPgto>").append("<MeioPgto>2</MeioPgto>").append("<IndrOpContg>N</IndrOpContg>").append("<DtMovto>2017-06-02</DtMovto>")
            .append("</DDA0108></MsgSPB></MQEnvio>");

    // INSTANCE OBJECTS AND LISTS ================================================================
    /**
     * Método responsável por
     * 
     * @return List<TratamentoMesagemDto>
     * 
     */
    protected List<TratamentoMesagemDto> listarTratamentoMesagemDto() {
        List<TratamentoMesagemDto> list = new ArrayList<TratamentoMesagemDto>();
        list.add(recuperarTratamentoMesagemDto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return TratamentoMesagemDto
     * 
     */
    protected TratamentoMesagemDto recuperarTratamentoMesagemDto() {
        TratamentoMesagemDto param = new TratamentoMesagemDto();
        param.setCodTipoTratamento(Constantes.SHORT_UM);
        param.setListaIdErroMensagem(listarLong());
        param.setListaIdLogRecebimento(listarLong());
        param.setListaIdMensagem(listarLong());

        return param;
    }

    /**
     * Método responsável por
     * 
     * @return PagadorDto
     * 
     */
    protected PagadorDto recuperarPagadorDto() {
        List<PagadorAgregadoDto> list = new ArrayList<PagadorAgregadoDto>();
        list.add(recuperarPagadorAgregadoDto());

        PagadorDto ret = new PagadorDto();
        ret.setNumCpfCnpj(Constantes.CPF_AUX);
        ret.setListaPagadorAgregado(list);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABoleto
     * 
     */
    protected MensagemDDABoleto recuperarMensagemDDABoleto() {
        MensagemDDABoleto ret = new MensagemDDABoleto();
        ret.setId(Constantes.LONG_UM);
        // DADOS BENEFICIARIO
        ret.setCodTipoPessoaBeneficiario(TipoPessoaEnum.PF.getCodDominioCip());
        ret.setNumCpfCnpjBeneficiario(Constantes.CPF_AUX);
        ret.setNomeBeneficiario(Constantes.NOME_BANCO);
        // DADOS PAGADOR
        ret.setCodTipoPessoaPagador(TipoPessoaEnum.PF.getCodDominioCip());
        ret.setNumCpfCnpjPagador(Constantes.CPF_AUX);
        ret.setNomePagador(Constantes.NOME_BANCO);
        // DADOS BOLETO
        ret.setNumCodigoBarra(Constantes.COD_BARRAS_39_VALIDO);
        ret.setNumCodBarrasCampoLivre(Constantes.COD_BARRAS_39_VALIDO);
        ret.setNumLinhaDigitavel(Constantes.NUM_LINHA_DIGITAVEL);
        ret.setIdCarteira(Constantes.INTEGER_UM);
        ret.setCodMoeda(Constantes.STRING_NUMERO_1);
        ret.setIdOrgaoMoeda(Constantes.INTEGER_UM);
        ret.setDataVencimento(Constantes.DATE_TIME_DB_AUX);
        ret.setValorBoleto(Constantes.CEM);
        ret.setDataEmissao(Constantes.DATE_TIME_DB_AUX);
        ret.setDataLimitePagamento(Constantes.DATE_TIME_DB_AUX);
        ret.setBolBloqueioPagamento(Boolean.TRUE);
        ret.setBolPagamentoParcial(Boolean.TRUE);
        ret.setBolTituloNegociado(Boolean.TRUE);
        ret.setValorAbatimento(Constantes.CEM);
        ret.setCodTipoModeloCalculo(Constantes.STRING_NUMERO_1);
        ret.setCodAutorizacaoValorDivergente(Constantes.STRING_NUMERO_1);
        ret.setIdEspecieDocumento(Constantes.INTEGER_UM);
        ret.setCodTipoPagamento(Constantes.INTEGER_UM);

        // DADOS FILHOS
        ret.setCodTipoDesconto1(Constantes.STRING_NUMERO_0);
        ret.setCodTipoJuros(Constantes.INTEGER_ZERO);
        ret.setCodTipoMulta(Constantes.INTEGER_ZERO);
        MensagemDDABoletoGrupoCalculo obj4 = new MensagemDDABoletoGrupoCalculo();
        obj4.setMensagemDDABoleto(ret);
        obj4.setId(Constantes.LONG_UM);
        ret.setListaMensagemDDABoletoGrupoCalculo(new ArrayList<MensagemDDABoletoGrupoCalculo>());
        ret.getListaMensagemDDABoletoGrupoCalculo().add(obj4);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDATerceiroDto
     * 
     */
    protected DDATerceiroDto recuperarDDATerceiroDto() {
        DDATerceiroDto ret = new DDATerceiroDto();
        ret.setNumCpfCnpj(Constantes.CPF_AUX);
        ret.setNumIdentDDA(Constantes.LONG_UM);
        ret.setIndTerceiro('E');
        ret.setTipoPessoaTerc('F');
        ret.setCodCanal(Constantes.INTEGER_UM);
        ret.setNumCooperativa(Constantes.INTEGER_UM);
        ret.setNumCoopCartao(Constantes.INTEGER_UM);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return PagadorEletronicoDDADto
     * 
     */
    protected PagadorEletronicoDDADto recuperarPagadorEletronicoDDADto() {
        PagadorEletronicoDDADto ret = new PagadorEletronicoDDADto();
        ret.setNumIdentDDA(new BigDecimal(Constantes.LONG_UM));
        ret.setIndAceite("S");
        ret.setNumCooperativa(Constantes.INTEGER_UM);
        ret.setCodCanal(Constantes.INTEGER_UM);
        ret.setNumCoopCartao(Constantes.INTEGER_DEZ);
        ret.setNumPac(Constantes.INTEGER_UM);
        ret.setNumConta(new BigDecimal(Constantes.BIG_INTEGER_1_AUX));
        ret.setDescTerminal(Constantes.NOME_BANCO);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return LogErroCargaDto
     * 
     */
    protected LogErroCargaDto recuperarLogErroCargaDto() {
        LogErroCargaDto ret = new LogErroCargaDto();
        ret.setIdInstituicao(Constantes.INTEGER_UM);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<LogErroCargaDto>
     * 
     */
    protected List<LogErroCargaDto> listarLogErroCargaDto() {
        List<LogErroCargaDto> list = new ArrayList<LogErroCargaDto>();
        list.add(recuperarLogErroCargaDto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDA
     * 
     */
    protected MensagemDDA recuperarMensagemDDA() {
        MensagemDDA msgOrigem = new MensagemDDA();
        msgOrigem.setId(Constantes.LONG_UM);
        msgOrigem.setXmlMensagem(msgXml.toString());

        MensagemDDA ret = new MensagemDDA();
        ret.setTipoMensagemDDA(recuperarTipoMensagemDDA());
        ret.setMensagemOrigem(msgOrigem);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABoletoGrupoCalculo
     * 
     */
    protected MensagemDDABoletoGrupoCalculo retornarMensagemDDABoletoGrupoCalculo() {
        MensagemDDABoletoGrupoCalculo ret = new MensagemDDABoletoGrupoCalculo();
        ret.setId(Constantes.LONG_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return PagadorAgregadoDto
     * 
     */
    protected PagadorAgregadoDto recuperarPagadorAgregadoDto() {
        PagadorAgregadoDto ret = new PagadorAgregadoDto();

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<PagadorAgregadoDto>
     * 
     */
    protected List<PagadorAgregadoDto> listarPagadorAgregadoDto() {
        List<PagadorAgregadoDto> list = new ArrayList<PagadorAgregadoDto>();
        list.add(recuperarPagadorAgregadoDto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return List<String>
     * 
     */
    protected List<String> listarString() {
        List<String> list = new ArrayList<String>();
        list.add(String.valueOf(Constantes.BIG_INTEGER_1_AUX));
        list.add(String.valueOf(Constantes.BIG_INTEGER_2_AUX));

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return ProdutoDto
     * 
     */
    protected ProdutoDto recuperarProdutoDto() {
        ProdutoDto ret = new ProdutoDto();
        ret.setDataAtualMovimento(Constantes.DATE_AUX);
        ret.setDataProximoMovimento(recuperarDateMaior());

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return TipoGradeHorariaDto
     * 
     */
    protected TipoGradeHorariaDto recuperarTipoGradeHorariaDto() {
        TipoGradeHorariaDto ret = new TipoGradeHorariaDto();
        ret.setCodTipoGradeHoraria(String.valueOf(Constantes.INTEGER_UM));
        ret.setDescTipoGradeHoraria(Constantes.NOME_BANCO);
        ret.setCodSubTipoGrade(SubTipoGrade.GRADE_DDA);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<TipoGradeHorariaDto>
     * 
     */
    protected List<TipoGradeHorariaDto> listarTipoGradeHorariaDto() {
        List<TipoGradeHorariaDto> list = new ArrayList<TipoGradeHorariaDto>();
        list.add(recuperarTipoGradeHorariaDto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return GradeHoraria
     * 
     */
    protected GradeHoraria recuperarGradeHoraria() {
        GradeHoraria ret = new GradeHoraria();
        ret.setId(Constantes.LONG_UM);
        ret.setTipoGradeHoraria(recuperarTipoGradeHoraria());
        ret.setDataReferencia(Constantes.DATE_TIME_DB_AUX);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return TipoGradeHoraria
     * 
     */
    protected TipoGradeHoraria recuperarTipoGradeHoraria() {
        TipoGradeHoraria ghOrigem = new TipoGradeHoraria();
        ghOrigem.setCodTipoGradeHoraria(Constantes.STRING_NUMERO_1);

        TipoGradeHoraria ret = new TipoGradeHoraria();
        ret.setCodTipoGradeHoraria(String.valueOf(Constantes.INTEGER_UM));
        ret.setTipoGradeHorariaOrigem(ghOrigem);
        ret.setSubTipoGrade(recuperarSubTipoGrade());
        ret.setDescTipoGradeHoraria(Constantes.NOME_BANCO);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return SubTipoGrade
     * 
     */
    protected SubTipoGrade recuperarSubTipoGrade() {
        SubTipoGrade ret = new SubTipoGrade();
        ret.setDescSubTipoGrade(Constantes.NOME_BANCO);
        ret.setCodSubTipoGrade(SubTipoGrade.GRADE_TODOS);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return GradeHorariaDto
     * 
     */
    @SuppressWarnings("deprecation")
    protected GradeHorariaDto recuperarGradeHorariaDto() {
        GradeHorariaDto interList1 = new GradeHorariaDto();
        interList1.setDataHoraAbertura(Constantes.DATE_TIME_DB_AUX);
        interList1.setDataHoraFechamento(DateUtil.getDateTimeDB(recuperarDateMaior()));
        interList1.setHoraAbertura(String.valueOf(recuperarDateMaior().getHours()));
        interList1.setHoraFechamento(String.valueOf(recuperarDateMaior().getHours()));
        interList1.setMinutoAbertura(String.valueOf(Constantes.DATE_AUX.getMinutes()));
        interList1.setMinutoFechamento(String.valueOf(recuperarDateMaior().getMinutes()));
        List<GradeHorariaDto> list = new ArrayList<GradeHorariaDto>();
        list.add(interList1);

        GradeHorariaDto ret = new GradeHorariaDto();
        ret.setDataReferencia(Constantes.DATE_AUX);
        ret.setCodTipoGradeHoraria(String.valueOf(Constantes.INTEGER_UM));
        ret.setListaGradeHoraria(list);
        ret.setDataHoraAbertura(Constantes.DATE_TIME_DB_AUX);
        ret.setDataHoraFechamento(DateUtil.getDateTimeDB(recuperarDateMaior()));
        ret.setHoraAbertura(String.valueOf(Constantes.DATE_AUX.getHours()));
        ret.setHoraFechamento(String.valueOf(recuperarDateMaior().getHours()));
        ret.setMinutoAbertura(String.valueOf(Constantes.DATE_AUX.getMinutes()));
        ret.setMinutoFechamento(String.valueOf(recuperarDateMaior().getMinutes()));
        ret.setTipoGradeHorariaDto(recuperarTipoGradeHorariaDto());

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<GradeHorariaDto>
     * 
     */
    protected List<GradeHorariaDto> listarGradeHorariaDto() {
        List<GradeHorariaDto> list = new ArrayList<GradeHorariaDto>();
        list.add(recuperarGradeHorariaDto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return List<TipoGradeHoraria>
     * 
     */
    protected List<TipoGradeHoraria> listarTipoGradeHoraria() {
        List<TipoGradeHoraria> list = new ArrayList<TipoGradeHoraria>();
        list.add(recuperarTipoGradeHoraria());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return BoletoPagamentoContingenciaDto
     * 
     */
    protected BoletoPagamentoContingenciaDto recuperarBoletoPagamentoContingenciaDto() {

        return new BoletoPagamentoContingenciaDto(Constantes.DATE_AUX, Constantes.COD_BARRAS_39_VALIDO);
    }

    /**
     * Método responsável por
     * 
     * @return List<BoletoPagamentoContingenciaDto>
     * 
     */
    protected List<BoletoPagamentoContingenciaDto> listarBoletoPagamentoContingenciaDto() {
        List<BoletoPagamentoContingenciaDto> list = new ArrayList<BoletoPagamentoContingenciaDto>();
        list.add(recuperarBoletoPagamentoContingenciaDto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return ConsultaBoletoDDADto
     * 
     */
    protected ConsultaBoletoDDADto recuperarConsultaBoletoDDADto() {
        ConsultaBoletoDDADto ret = new ConsultaBoletoDDADto();
        ret.setValorMora(new BigDecimal(Constantes.BIG_INTEGER_1_AUX));
        ret.setValorDesconto(new BigDecimal(Constantes.BIG_INTEGER_1_AUX));
        ret.setValorAbatimento(new BigDecimal(Constantes.BIG_INTEGER_1_AUX));
        ret.setValorMulta(new BigDecimal(Constantes.BIG_INTEGER_1_AUX));
        ret.setValorBoleto(new BigDecimal(Constantes.BIG_INTEGER_1_AUX));

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return BoletoDDA
     * 
     */
    protected BoletoDDA recuperarBoletoDDA() {
        BoletoDDA ret = new BoletoDDA();
        ret.setCodIspbPartDestinatario(String.valueOf(Constantes.INTEGER_UM));
        ret.setCodTipoPessoaBeneficiario(String.valueOf(Constantes.INTEGER_UM));
        ret.setNumCpfCnpjBeneficiario(Constantes.CPF_AUX);
        ret.setNomeBeneficiario(Constantes.NOME_BANCO);
        ret.setNomeFantasiaBeneficiario(Constantes.NOME_BANCO);
        ret.setCodTipoPessoaDDAAvalista(String.valueOf(Constantes.INTEGER_UM));
        ret.setNumCpfCnpjAvalista(Constantes.CPF_AUX);
        ret.setNomeAvalista(Constantes.NOME_BANCO);
        ret.setCodTipoPessoaPagador(String.valueOf(Constantes.INTEGER_UM));
        ret.setNumCpfCnpjPagador(Constantes.CPF_AUX);
        ret.setNomePagador(Constantes.NOME_BANCO);
        ret.setNomeFantasiaPagador(Constantes.NOME_BANCO);
        ret.setNumCodigoBarra(Constantes.NOME_BANCO);
        ret.setNumCodBarrasCampoLivre(Constantes.NOME_BANCO);
        ret.setNumLinhaDigitavel(Constantes.NOME_BANCO);
        ret.setDataVencimento(Constantes.DATE_TIME_DB_AUX);
        ret.setNumDiasProtesto(Constantes.INTEGER_UM);
        ret.setDataLimitePagamento(Constantes.DATE_TIME_DB_AUX);
        ret.setBolPagamentoParcial(Boolean.TRUE);
        ret.setNumRefAtualCadBoleto(Constantes.LONG_UM);
        ret.setCodSituacaoBoleto(SituacaoBoleto.ABERTO);
        ret.setValorBoleto(new BigDecimal(Constantes.BIG_INTEGER_1_AUX));
        ret.setCodTipoModeloCalculo(TipoModeloCalculo.INSTITUICAO_DESTINATARIA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS);
        ret.setValorPago(new BigDecimal(Constantes.BIG_INTEGER_1_AUX));
        ret.setQtdPagamentoRestante(Constantes.INTEGER_UM);
        ret.setBolBloqueioPagamento(Boolean.TRUE);
        ret.setNumSeqAtualAceite(Constantes.LONG_UM);
        ret.setNumRefAtualCadAceite(Constantes.LONG_UM);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABaixaOperacional
     * 
     */
    protected MensagemDDABaixaOperacional recuperarMensagemDDABaixaOperacional() {
        MensagemDDABaixaOperacional ret = new MensagemDDABaixaOperacional();
        ret.setValorBaixa(new BigDecimal(Constantes.BIG_INTEGER_1_AUX));
        ret.setNumCodigoBarra(Constantes.NOME_BANCO);
        ret.setNumCodBarrasCampoLivre(Constantes.NOME_BANCO);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return HistoricoContingencia
     * 
     */
    protected HistoricoContingencia recuperarHistoricoContingencia() {
        HistoricoContingencia ret = new HistoricoContingencia();
        ret.setId(Constantes.LONG_UM);
        ret.setTipoContingencia(recuperarTipoContingencia());
        ret.setContingenciaHabilitada(Boolean.TRUE);
        ret.setDataHoraSituacao(Constantes.DATE_TIME_DB_AUX);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<HistoricoContingencia>
     * 
     */
    @SuppressWarnings("deprecation")
    protected List<HistoricoContingencia> listarHistoricoContingencia() {
        List<HistoricoContingencia> list = new ArrayList<HistoricoContingencia>();
        HistoricoContingencia hist2 = recuperarHistoricoContingencia();
        hist2.setContingenciaHabilitada(Boolean.FALSE);
        hist2.setId(hist2.getId() + Constantes.LONG_UM);

        Date date = recuperarDateMaior();
        DateTimeDB dateTimeDB = new DateTimeDB();
        dateTimeDB.setDate(date.getDate());
        dateTimeDB.setMonth(date.getMonth());
        dateTimeDB.setYear(date.getYear());

        hist2.setDataHoraSituacao(dateTimeDB);
        list.add(recuperarHistoricoContingencia());
        list.add(hist2);

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return TipoContingencia
     * 
     */
    protected TipoContingencia recuperarTipoContingencia() {
        TipoContingencia ret = new TipoContingencia();
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return LogDetRecebimentoArquivoDDA
     * 
     */
    protected LogDetRecebimentoArquivoDDA recuperarLogDetRecebimentoArquivoDDA() {
        LogDetRecebimentoArquivoDDA ret = new LogDetRecebimentoArquivoDDA();
        ret.setLogRecebimentoArquivoDDA(recuperarLogRecebimentoArquivoDDA());

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return SituacaoProcessamentoArquivo
     * 
     */
    protected SituacaoProcessamentoArquivo recuperarSituacaoProcessamentoArquivo() {
        SituacaoProcessamentoArquivo ret = new SituacaoProcessamentoArquivo();
        ret.setCodSituacaoProcessamentoArquivo(SituacaoProcessamentoArquivo.ARQUIVO_ABERTO);
        ret.setDescSituacaoProcessamentoArquivo(Constantes.NOME_BANCO);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<SituacaoProcessamentoArquivo>
     * 
     */
    protected List<SituacaoProcessamentoArquivo> listarSituacaoProcessamentoArquivo() {
        List<SituacaoProcessamentoArquivo> list = new ArrayList<SituacaoProcessamentoArquivo>();
        list.add(recuperarSituacaoProcessamentoArquivo());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return ConvenioCadastroDDADto
     * 
     */
    protected ConvenioCadastroDDADto recuperarConvenioCadastroDDADto() {
        ConvenioCadastroDDADto ret = new ConvenioCadastroDDADto();
        ret.setDataInicioConvenio(Constantes.DATE_AUX);
        ret.setTipoAgencia(TipoAgenciaEnum.FISICA);
        ret.setNumAgencia(Constantes.INTEGER_UM);
        ret.setTipoProdutoConvenio(TipoProdutoConvenioEnum.BOLETO_DE_COBRANCA);
        ret.setSitucaoConvenio(SituacaoConvenioBeneficiarioEnum.ATIVO);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<ConvenioCadastroDDADto>
     * 
     */
    protected List<ConvenioCadastroDDADto> listarConvenioCadastroDDADto() {
        List<ConvenioCadastroDDADto> list = new ArrayList<ConvenioCadastroDDADto>();
        list.add(recuperarConvenioCadastroDDADto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return LogRecebimentoArquivoDDA
     * 
     */
    protected LogRecebimentoArquivoDDA recuperarLogRecebimentoArquivoDDA() {
        LogRecebimentoArquivoDDA ret = new LogRecebimentoArquivoDDA();
        ret.setId(Constantes.LONG_UM);
        ret.setDescNomeArquivoRecebido(Constantes.NOME_BANCO);
        ret.setDataMovimento(Constantes.DATE_TIME_DB_AUX);
        ret.setQtdRegistroArquivo(Constantes.INTEGER_UM);
        ret.setDataHoraArquivo(Constantes.DATE_TIME_DB_AUX);
        ret.setTipoMensagemDDA(recuperarTipoMensagemDDA());
        ret.setTipoArquivo(recuperarTipoArquivo());
        ret.setTipoErroCip(recuperarTipoErroCip());
        ret.setSituacaoProcessamentoArquivo(recuperarSituacaoProcessamentoArquivo());
        ret.setLogEnvioArquivoDDA(recuperarLogEnvioArquivoDDA());

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return LogEnvioArquivoDDA
     * 
     */
    protected LogEnvioArquivoDDA recuperarLogEnvioArquivoDDA() {
        LogEnvioArquivoDDA ret = new LogEnvioArquivoDDA();
        ret.setId(Constantes.LONG_UM);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return TipoErroCip
     * 
     */
    protected TipoErroCip recuperarTipoErroCip() {
        TipoErroCip ret = new TipoErroCip();
        ret.setCodTipoErro(String.valueOf(Constantes.INTEGER_UM));
        ret.setDescTipoErro(Constantes.NOME_BANCO);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return TipoMensagemDDA
     * 
     */
    protected TipoMensagemDDA recuperarTipoMensagemDDA() {
        TipoMensagemDDA ret = new TipoMensagemDDA();
        ret.setCodTipoMensagem(TipoMensagemDDA.ADDA001);
        ret.setBolExigeMensagemRetorno(Boolean.TRUE);
        ret.setNumPrioridadeEnvio(Constantes.INTEGER_UM);
        ret.setDescTipoMensagem(Constantes.NOME_BANCO);
        ret.setCodTipoArquivoCorrespondente(Constantes.STRING_NUMERO_1);
        ret.setTipoGradeHoraria(recuperarTipoGradeHoraria());
        ret.setCategoriaMensagemDDA(recuperarCategoriaMensagemDDA());

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return CategoriaMensagemDDA
     * 
     */
    protected CategoriaMensagemDDA recuperarCategoriaMensagemDDA() {
        CategoriaMensagemDDA ret = new CategoriaMensagemDDA();
        ret.setCodCategoriaMensagemDda(Constantes.STRING_NUMERO_1);
        ret.setDescCategoriaMensagemDda(Constantes.NOME_BANCO);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<TipoMensagemDDA>
     * 
     */
    protected List<TipoMensagemDDA> listarTipoMensagemDDA() {
        List<TipoMensagemDDA> list = new ArrayList<TipoMensagemDDA>();
        list.add(recuperarTipoMensagemDDA());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return ContingenciaDto
     * 
     */
    protected ContingenciaDto recuperarContingenciaDto() {

        return new ContingenciaDto(Constantes.DATE_AUX, Constantes.LONG_UM, Constantes.NOME_BANCO, Constantes.LONG_UM, Boolean.TRUE);
    }

    /**
     * Método responsável por
     * 
     * @return TipoArquivo
     * 
     */
    protected TipoArquivo recuperarTipoArquivo() {
        TipoArquivo ret = new TipoArquivo();
        ret.setCodTipoArquivo(TipoArquivo.DISTRIBUICAO);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<TipoArquivo>
     * 
     */
    protected List<TipoArquivo> listarTipoArquivo() {
        List<TipoArquivo> list = new ArrayList<TipoArquivo>();
        list.add(recuperarTipoArquivo());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return InstituicaoDto
     * 
     */
    protected InstituicaoDto recuperarInstituicaoDto() {
        InstituicaoDto ret = new InstituicaoDto();
        ret.setNumCooperativa(Constantes.INTEGER_UM);
        ret.setIdInstituicao(Constantes.INTEGER_UM);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return AlteraSituacaoBeneficiarioDto
     * 
     */
    protected AlteraSituacaoBeneficiarioDto recuperarAlteraSituacaoBeneficiarioDto() {
        AlteraSituacaoBeneficiarioDto ret = new AlteraSituacaoBeneficiarioDto();
        ret.setCnpjCpfBeneficiario(Constantes.CPF_AUX);
        ret.setTipoPessoaBeneficiario(TipoPessoaEnum.PF);
        ret.setSituacaoBeneficiario(SituacaoBeneficiarioEnum.APTO);
        ret.setDataSituacaoBeneficiario(Constantes.DATE_AUX);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return ParametroDDA
     * 
     */
    protected ParametroDDA recuperarParametroDDA() {
        ParametroDDA ret = new ParametroDDA();
        ret.setValorParametro(Constantes.STRING_NUMERO_1);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return ViewMensagemDDAPendente
     * 
     */
    protected MensagemPendenteBeneficiarioDto recuperarViewMensagemDDAPendente() {
        return new MensagemPendenteBeneficiarioDto(Constantes.LONG_UM, Constantes.NOME_TESTE, Constantes.CPF_AUX, Constantes.NOME_TESTE, Constantes.NOME_TESTE);
    }

    /**
     * Método responsável por
     * 
     * @param emptyObject
     * @return List<ViewMensagemDDAPendente>
     * 
     */
    protected List<MensagemPendenteBeneficiarioDto> listarViewMensagemDDAPendente(Boolean emptyObject) {
        List<MensagemPendenteBeneficiarioDto> list = new ArrayList<MensagemPendenteBeneficiarioDto>();
        if (!emptyObject) {
            list.add(recuperarViewMensagemDDAPendente());
            list.add(recuperarViewMensagemDDAPendente());
        } else {
            list.add(new MensagemPendenteBeneficiarioDto(Constantes.LONG_UM, Constantes.NOME_TESTE, Constantes.CPF_AUX, Constantes.NOME_TESTE, Constantes.NOME_TESTE));
        }

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return BeneficiarioDDA
     * 
     */
    protected BeneficiarioDDA recuperarBeneciarioDDA() {

        BeneficiarioDDA ret = new BeneficiarioDDA();
        ret.setId(Constantes.LONG_UM);
        ret.setListaBeneficiarioInstituicao(listarBeneficiarioInstituicao());
        ret.setNumIdentBeneficiario(Constantes.LONG_UM);
        ret.setNumCpfCnpj(Constantes.CPF_AUX, TipoPessoaEnum.PF);
        ret.setSituacaoBeneficiarioDDA(recuperarSituacaoBeneficiarioDDA());
        ret.setDataHoraUltimaAtualizacao(Constantes.DATE_TIME_DB_AUX);
        ret.setBolOrigemSicoob(Boolean.TRUE);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return SituacaoBeneficiarioDDA
     * 
     */
    protected SituacaoBeneficiarioDDA recuperarSituacaoBeneficiarioDDA() {
        SituacaoBeneficiarioDDA ret = new SituacaoBeneficiarioDDA();
        ret.setCodSituacaoBeneficiario("A");
        ret.setDescSituacaoBeneficiario(Constantes.NOME_BANCO);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return AlterarCadastroBeneficiarioDDADto
     * 
     */
    protected AlterarCadastroBeneficiarioDDADto recuperarAlterarCadastroBeneficiarioDDADto() {
        AlterarCadastroBeneficiarioDDADto ret = new AlterarCadastroBeneficiarioDDADto();
        ret.setCnpjCpfBeneficiario(Constantes.CPF_AUX);
        ret.setTipoPessoaBeneficiario(TipoPessoaEnum.PF);
        ret.setNomeFantasiaBeneficiario(Constantes.NOME_BANCO);
        ret.setNomeRazaoSocialBeneficiario(Constantes.NOME_BANCO);
        ret.setDataInicioRelacionamento(Constantes.DATE_AUX);
        ret.setListaConvenioAlteracaoDto(listarConvenioAlteracaoDDADto());

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return RepresentanteBeneficiarioAlteracaoDto
     * 
     */
    protected RepresentanteBeneficiarioAlteracaoDto recuperarRepresentanteBeneficiarioAlteracaoDto() {
        RepresentanteBeneficiarioAlteracaoDto ret = new RepresentanteBeneficiarioAlteracaoDto();
        ret.setCnpjCpfRepresentanteBeneficiario(Constantes.CPF_AUX);
        ret.setTipoPessoaRepresentanteBeneficiario(TipoPessoaEnum.PF);
        ret.setTipoManutencaoRepresentante(TipoManutencaoEnum.ALTERAR);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<RepresentanteBeneficiarioAlteracaoDto>
     * 
     */
    protected List<RepresentanteBeneficiarioAlteracaoDto> listarRepresentanteBeneficiarioAlteracaoDto() {
        List<RepresentanteBeneficiarioAlteracaoDto> list = new ArrayList<RepresentanteBeneficiarioAlteracaoDto>();
        list.add(recuperarRepresentanteBeneficiarioAlteracaoDto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return List<ConvenioAlteracaoDDADto>
     * 
     */
    protected List<ConvenioAlteracaoDDADto> listarConvenioAlteracaoDDADto() {
        List<ConvenioAlteracaoDDADto> list = new ArrayList<ConvenioAlteracaoDDADto>();
        list.add(recuperarConvenioAlteracaoDDADto());
        list.add(recuperarConvenioAlteracaoDDADto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return CadastroBeneficiarioDDADto
     * 
     */
    protected CadastroBeneficiarioDDADto recuperarCadastroBeneficiarioDDADto() {
        CadastroBeneficiarioDDADto ret = new CadastroBeneficiarioDDADto();
        ret.setListaConvenioCadastroDto(listarConvenioCadastroDDADto());
        ret.setListaRepresentanteBeneficiarioDto(listarRepresentanteBeneficiarioDto());
        ret.setDataInicioRelacionamentoParticipante(Constantes.DATE_AUX);
        ret.setTipoPessoaBeneficiario(TipoPessoaEnum.PF);
        ret.setCnpjCpfBeneficiario(Constantes.CPF_AUX);
        ret.setNomeFantasiaBeneficiario(Constantes.NOME_BANCO);
        ret.setNomeRazaoSocialBeneficiario(Constantes.NOME_BANCO);
        ret.setDataHoraSituacaoBeneficiario(Constantes.DATE_AUX);
        ret.setDataInicioRelacionamentoParticipante(Constantes.DATE_AUX);
        ret.setSituacaoRelacionamentoParticipante(SituacaoRelacionamentoParticipanteEnum.ATIVO);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return RepresentanteBeneficiarioDto
     * 
     */
    protected RepresentanteBeneficiarioDto recuperarRepresentanteBeneficiarioDto() {
        RepresentanteBeneficiarioDto ret = new RepresentanteBeneficiarioDto();
        ret.setCnpjCpfRepresentanteBeneficiario(Constantes.CPF_AUX);
        ret.setTipoPessoaRepresentanteBeneficiario(TipoPessoaEnum.PF);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<RepresentanteBeneficiarioDto>
     * 
     */
    protected List<RepresentanteBeneficiarioDto> listarRepresentanteBeneficiarioDto() {
        List<RepresentanteBeneficiarioDto> list = new ArrayList<RepresentanteBeneficiarioDto>();
        list.add(recuperarRepresentanteBeneficiarioDto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return BeneficiarioAlertaDto
     * 
     */
    protected BeneficiarioAlertaDto recuperarBeneficiarioAlertaDto() {
        BeneficiarioAlertaDto ret = new BeneficiarioAlertaDto();

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<BeneficiarioAlertaDto>
     * 
     */
    protected List<BeneficiarioAlertaDto> listarBeneficiarioAlertaDto() {
        List<BeneficiarioAlertaDto> list = new ArrayList<BeneficiarioAlertaDto>();
        list.add(recuperarBeneficiarioAlertaDto());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return List<Long>
     * 
     */
    protected List<Long> listarLong() {
        List<Long> list = new ArrayList<Long>();
        list.add(Constantes.LONG_UM);
        list.add(Constantes.LONG_DOIS);

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return List<Integer>
     * 
     */
    protected List<Integer> listarInteger() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(Constantes.INTEGER_UM);

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return BeneficiariosAlertaFiltroDto
     * 
     */
    protected BeneficiariosAlertaFiltroDto recuperarBeneficiariosAlertaFiltroDto() {
        BeneficiariosAlertaFiltroDto ret = new BeneficiariosAlertaFiltroDto();

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return ConvenioAlteracaoDDADto
     * 
     */
    protected ConvenioAlteracaoDDADto recuperarConvenioAlteracaoDDADto() {
        ConvenioAlteracaoDDADto ret = new ConvenioAlteracaoDDADto();
        ret.setTipoManutencaoConvenio(TipoManutencaoEnum.ALTERAR);
        ret.setDataInicioConvenio(Constantes.DATE_AUX);
        ret.setDataFimConvenio(Constantes.DATE_AUX);
        ret.setTipoAgencia(TipoAgenciaEnum.FISICA);
        ret.setNumAgencia(Constantes.INTEGER_UM);
        ret.setTipoProdutoConvenio(TipoProdutoConvenioEnum.BOLETO_DE_COBRANCA);
        ret.setIspbParticipanteIncorporado(Constantes.STRING_NUMERO_1);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return BeneficiarioInstituicao
     * 
     */
    protected BeneficiarioInstituicao recuperarBeneficiarioInsituicao() {
        BeneficiarioInstituicao ret = new BeneficiarioInstituicao();
        ret.setId(Constantes.LONG_UM);
        ret.setDataInicioRelacionamento(Constantes.DATE_TIME_DB_AUX);

        BeneficiarioDDA ben = new BeneficiarioDDA();
        ben.setNumIdentBeneficiario(Constantes.LONG_UM);

        ret.setBeneficiarioDDA(ben);
        ret.setIdInstituicao(Constantes.INTEGER_UM);

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<BeneficiarioInstituicao>
     * 
     */
    protected List<BeneficiarioInstituicao> listarBeneficiarioInstituicao() {
        List<BeneficiarioInstituicao> list = new ArrayList<BeneficiarioInstituicao>();
        list.add(recuperarBeneficiarioInsituicao());
        list.add(recuperarBeneficiarioInsituicao());

        return list;
    }

    /**
     * Método responsável por
     * 
     * @return Date
     * 
     */
    protected Date recuperaDateMenor() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(Constantes.DATE_AUX);
        gc.add(Calendar.DATE, -1);
        gc.add(Calendar.HOUR, -3);
        Date ret = gc.getTime();

        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return Date
     * 
     */
    protected Date recuperarDateMaior() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(Constantes.DATE_AUX);
        gc.add(Calendar.DATE, 1);
        gc.add(Calendar.HOUR, 3);
        Date ret = gc.getTime();

        return ret;
    }
}
