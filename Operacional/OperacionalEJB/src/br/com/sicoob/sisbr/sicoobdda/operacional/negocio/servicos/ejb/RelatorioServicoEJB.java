/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb-2.3.4.30-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         RelatorioServicoEJB.java
 * Data Criação:    9 de nov de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaTarifasDDAPagasDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ControleRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRelatorioEventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaHistoricoMensagem106Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaLancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PesquisaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.GsonUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.RelatorioServicoRemote;

/**
 * RelatorioServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Remote(RelatorioServicoRemote.class)
public class RelatorioServicoEJB extends OperacionalServicoEJB implements RelatorioServicoRemote {

    private static final String FILTRO = "filtro";
    private static final String JSON_FILTRO = "jsonFiltro";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO,
     *      br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {

        RelatorioSicoobDDAEnum relatorio = RelatorioSicoobDDAEnum.getByContextoFlex((String) dto.getDados().get(Constantes.RELATORIO_CONTEXTO_FLEX));

        ConfigurarRelatorioJasper configuracaoRelatorio = null;

        switch (relatorio) {
        case SDDA501:
            configuracaoRelatorio = configurarRelatorioMonitoramentoMensagem(dto, rDto);
            break;
        case SDDA503:
            configuracaoRelatorio = configurarRelatorioHistoricoContingencia(rDto);
            break;
        case SDDA504:
            configuracaoRelatorio = configurarRelatorioBenficiariosAlerta(dto, rDto);
            break;
        case SDDA505:
            configuracaoRelatorio = configurarRelatorioTermoPagadorEletronico(dto, rDto);
            break;
        case SDDA506:
            configuracaoRelatorio = configurarRelatorioDetalheBoleto(dto, rDto);
            break;
        case SDDA507:
            configuracaoRelatorio = configurarRelatorioListaPagadorEletronico(dto, rDto);
            break;
        case SDDA508:
            configuracaoRelatorio = configurarRelatorioDetalhePagadorEletronico(dto, rDto);
            break;
        case SDDA509:
            configuracaoRelatorio = configurarRelatorioPagadorAgregado(dto, rDto);
            break;
        case SDDA510:
            configuracaoRelatorio = configurarRelatorioConsultaBoleto(dto, rDto);
            break;
        case SDDA511:
            configuracaoRelatorio = configurarRelatorioHistoricoMensagem(dto, rDto);
            break;
        case SDDA512:
            configuracaoRelatorio = configurarRelatorioEventoTarifavel(dto, rDto);
            break;
        case SDDA513:
            configuracaoRelatorio = configurarRelatorioTarifasDDAPagas(dto, rDto);
            break;
        case SDDA514:
            configuracaoRelatorio = configurarRelatorioLancamentosTarifas(dto, rDto);
            break;
        case SDDA515:
            configuracaoRelatorio = configurarRelatorioMovimentoSicoobDDA(dto, rDto);
            break;
        case SDDA516:
            configuracaoRelatorio = configurarRelatorioEventosDisponiveis(dto, rDto);
            break;
        case SDDA517:
            configuracaoRelatorio = configurarRelatorioEventosRateio(dto, rDto);
            break;
        case SDDA518:
            configuracaoRelatorio = configurarRelatorioRateioLancamento(dto, rDto);
            break;
        case SDDA519:
            configuracaoRelatorio = configurarRelatorioDadosRateio(dto, rDto);
            break;
        case SDDA520:
            configuracaoRelatorio = configurarRelatorioRateioTarifa(dto, rDto);
            break;
        default:
            throw new BancoobException("Relatório não cadastrado: " + relatorio.getContextoFlex());
        }

        return configuracaoRelatorio.gerarRelatorio(rDto);
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioMonitoramentoMensagem(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        Long idMensagem = (Long) dto.getDados().get(FILTRO);
        return OperacionalFabricaDelegates.getInstance().getMonitoramentoMensagensDDADelegate().configurarRelatorioMonitoramentoMensagem(idMensagem, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioHistoricoContingencia(RelatorioDadosDTO rDto) throws BancoobException {
        return OperacionalFabricaDelegates.getInstance().getContingenciaDelegate().configurarRelatorioHistoricoContingencia(rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioBenficiariosAlerta(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);

        BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto = GsonUtil.getGson().fromJson(filtroJSON, BeneficiariosAlertaFiltroDto.class);

        return OperacionalFabricaDelegates.getInstance().getBeneficiariosAlertaDelegate().configurarRelatorioBenficiariosAlerta(beneficiariosAlertaFiltroDto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioTermoPagadorEletronico(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);

        HistoricoPagadorEletronicoDto historicoPagdorEletroDto = GsonUtil.getGson().fromJson(filtroJSON, HistoricoPagadorEletronicoDto.class);
        return OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate().configurarRelatorioTermoPagadorEletronico(historicoPagdorEletroDto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioDetalheBoleto(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String numCodigoBarra = (String) dto.getDados().get("numCodigoBarra");
        Integer codSituacaoBoleto = Integer.valueOf((String) dto.getDados().get("codSituacaoBoleto"));

        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);

        BoletoDDA boletoDDA = GsonUtil.getGson().fromJson(filtroJSON, BoletoDDA.class);

        return OperacionalFabricaDelegates.getInstance().getConsultaBoletoDDADelegate().configurarRelatorioDetalheBoleto(boletoDDA, numCodigoBarra, codSituacaoBoleto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioListaPagadorEletronico(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        Integer numCooperativa = Integer.parseInt((String) dto.getDados().get(FILTRO));

        return OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate().configurarRelatorioListaPagadorEletronico(numCooperativa, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioDetalhePagadorEletronico(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String numCpfCnpj = (String) dto.getDados().get(FILTRO);

        return OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate().configurarRelatorioDetalhePagadorEletronico(numCpfCnpj, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioPagadorAgregado(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        Integer numCooperativa = (Integer) dto.getDados().get("numAgencia");
        String numCpfCnpj = (String) dto.getDados().get("numCpfCnpj");
        Integer codCentral = (Integer) dto.getDados().get("codCentral");

        return OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate().configurarRelatorioPagadorAgregado(numCpfCnpj, numCooperativa, codCentral, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioConsultaBoleto(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);

        PesquisaBoletoDDADto pesquisaBoletoDDADto = GsonUtil.getGson().fromJson(filtroJSON, PesquisaBoletoDDADto.class);
        return OperacionalFabricaDelegates.getInstance().getConsultaBoletoDDADelegate().configurarRelatorioConsultaBoleto(pesquisaBoletoDDADto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioHistoricoMensagem(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String jsonListaDados = (String) dto.getDados().get("jsonListaDados");

        return OperacionalFabricaDelegates.getInstance().getConsultaBoletoDDADelegate().configurarRelatorioHistoricoMensagem(new ListaHistoricoMensagem106Dto(jsonListaDados), rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioEventoTarifavel(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);

        FiltroRelatorioEventoTarifavelDto filtroRelatorio = GsonUtil.getGson().fromJson(filtroJSON, FiltroRelatorioEventoTarifavelDto.class);

        return OperacionalFabricaDelegates.getInstance().getEventoTarifavelDDADelegate().configurarRelatorioEventoTarifavel(filtroRelatorio, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioTarifasDDAPagas(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);
        ConsultaTarifasDDAPagasDto consulta = GsonUtil.getGson().fromJson(filtroJSON, ConsultaTarifasDDAPagasDto.class);

        return OperacionalFabricaDelegates.getInstance().getRateioDDALancamentoDelegate().configurarRelatorioTarifasDDAPagas(consulta, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioLancamentosTarifas(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);
        ConsultaTarifasDDAPagasDto consulta = GsonUtil.getGson().fromJson(filtroJSON, ConsultaTarifasDDAPagasDto.class);

        String jsonListaDados = (String) dto.getDados().get("jsonListaDados");
        ListaLancamentosTarifasDDADto listaLancamentosTarifasDDADto = new ListaLancamentosTarifasDDADto(jsonListaDados);
        return OperacionalFabricaDelegates.getInstance().getRateioDDALancamentoDelegate().configurarRelatorioLancamentosTarifas(consulta, listaLancamentosTarifasDDADto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioMovimentoSicoobDDA(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String jsonConsultaDto = (String) dto.getDados().get("jsonConsultaDto");
        String jsonConsultaTarifasDDAPagasDto = (String) dto.getDados().get("jsonConsultaTarifasDDAPagasDto");
        String jsonLancamentosTarifasDDADto = (String) dto.getDados().get("jsonLancamentosTarifasDDADto");

        ConsultaMovimentoSicoobDDADto consultaDto = GsonUtil.getGson().fromJson(jsonConsultaDto, ConsultaMovimentoSicoobDDADto.class);
        ConsultaTarifasDDAPagasDto consultaTarifasDDAPagasDto = GsonUtil.getGson().fromJson(jsonConsultaTarifasDDAPagasDto, ConsultaTarifasDDAPagasDto.class);
        LancamentosTarifasDDADto lancamentosTarifasDDADto = GsonUtil.getGson().fromJson(jsonLancamentosTarifasDDADto, LancamentosTarifasDDADto.class);
        return OperacionalFabricaDelegates.getInstance().getRateioDDALancamentoDelegate().configurarRelatorioMovimentoSicoobDDA(consultaDto, consultaTarifasDDAPagasDto, lancamentosTarifasDDADto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioEventosDisponiveis(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String jsonControleRateioDto = (String) dto.getDados().get("jsonControleRateioDto");

        ControleRateioDto controleRateioDto = GsonUtil.getGson().fromJson(jsonControleRateioDto, ControleRateioDto.class);

        String jsonConsultaDto = (String) dto.getDados().get("jsonConsultaDto");
        ConsultaEventoRateioDto consultaDto = GsonUtil.getGson().fromJson(jsonConsultaDto, ConsultaEventoRateioDto.class);

        return OperacionalFabricaDelegates.getInstance().getRateioTarifasCIPDelegate().configurarRelatorioEventosDisponiveis(controleRateioDto, consultaDto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioEventosRateio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);

        ControleRateioDto controleRateioDto = GsonUtil.getGson().fromJson(filtroJSON, ControleRateioDto.class);

        return OperacionalFabricaDelegates.getInstance().getRateioTarifasCIPDelegate().configurarRelatorioEventosRateio(controleRateioDto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioRateioLancamento(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);

        VisualizaRateioTarifaDto visualizaRateioTarifaDto = GsonUtil.getGson().fromJson(filtroJSON, VisualizaRateioTarifaDto.class);

        return OperacionalFabricaDelegates.getInstance().getRateioTarifasCIPDelegate().configurarRelatorioRateioLancamento(visualizaRateioTarifaDto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioDadosRateio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);

        VisualizaRateioTarifaDto visualizaRateioTarifaDto = GsonUtil.getGson().fromJson(filtroJSON, VisualizaRateioTarifaDto.class);

        return OperacionalFabricaDelegates.getInstance().getRateioTarifasCIPDelegate().configurarRelatorioDadosRateio(visualizaRateioTarifaDto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param rDto
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    private ConfigurarRelatorioJasper configurarRelatorioRateioTarifa(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
        String filtroJSON = (String) dto.getDados().get(JSON_FILTRO);

        VisualizaRateioTarifaDto visualizaRateioTarifaDto = GsonUtil.getGson().fromJson(filtroJSON, VisualizaRateioTarifaDto.class);

        return OperacionalFabricaDelegates.getInstance().getRateioTarifasCIPDelegate().configurarRelatorioRateioTarifa(visualizaRateioTarifaDto, rDto.getUsuarioBancoobDTO());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacionalServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return null;
    }

}
