package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRelatorioEventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.StatusEventoTarifavelEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDATarifa;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoOperacaoLog;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.SCIServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.locator.IntegracaoInternaServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.EventoTarifavelDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * EventoTarifavelServicoEJB
 * 
 * @author Samuell.Ramos
 */
@Stateless
@Local(EventoTarifavelDDAServicoLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EventoTarifavelDDAServicoEJB extends OperacionalCrudServicoEJB<EventoTarifavelDDATarifa> implements EventoTarifavelDDAServicoLocal {

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private EventoTarifavelDDADao dao;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private ParametroDao parametroDao;

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    private static final String INCLUSAO = "I";
    private static final String ALTERACAO = "A";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected EventoTarifavelDDADao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.EventoTarifavelDDAServico#listarEventoTarifavelDDA()
     */
    public List<EventoTarifavelDDA> listarEventoTarifavelDDA() throws ComumException {
        return getDAO().listarEventoTarifavelDDA();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.EventoTarifavelDDAServico#pesquisaEventoTarifavelDDA(java.lang.Integer,
     *      java.lang.Integer)
     */
    public List<EventoTarifavelDto> pesquisaEventoTarifavelDDA(Integer codEventoTarifavel, Integer codStatus) throws ComumException {
        return getDAO().pesquisaEventoTarifavelDDA(codEventoTarifavel, codStatus);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.EventoTarifavelDDAServico#obterEventoTarifavelDDATarifa(java.lang.Long)
     */
    public EventoTarifavelDto obterEventoTarifavelDDATarifa(Long idEventoTarifavelDDATarifa) throws ComumException {
        EventoTarifavelDto eventoTarifavelDto = getDAO().obterEventoTarifavelDDATarifa(idEventoTarifavelDDATarifa);
        eventoTarifavelDto.setDiaVencimento(Integer.parseInt(parametroDao.obterParametro(ParametroDDA.DIA_VENCIMENTO_FATURA_EVENTO_TARIFAVEL_CIP, null).getValorParametro()));
        return eventoTarifavelDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.EventoTarifavelDDAServico#manterEventoTarifavel(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)
     */
    public String manterEventoTarifavel(EventoTarifavelDto novoEventoTar) throws BancoobException {
        String tipoOperacao = null;
        EventoTarifavelDto antigoEventoTar = dao.obterEventoTarifavelDDATarifa(novoEventoTar.getIdEventoTarifavelDDATarifa());
        if (StatusEventoTarifavelEnum.getBy(antigoEventoTar.getStatus()).equals(StatusEventoTarifavelEnum.PROGRAMADO)) {
            if (DateUtil.menorQue(novoEventoTar.getDataInicioVigencia(), new Date())) {
                // Data inicio menor que a data inicio antiga, não permite alterações
                throw new ComumNegocioException("operacional.eventotarifavel.incluir.erro.data.inferior.atual");
            } else if (validaValoresIguaisVigente(novoEventoTar, obterStatus(novoEventoTar.getCodEventoTarifavel(), Constantes.EVENTO_TARIFAVEL_VIGENTE))) {
                throw new ComumNegocioException("operacional.eventotarifavel.incluir.erro.valor.igual.vigente");
            } else {
                tipoOperacao = alterarEventoTarifavel(novoEventoTar, tipoOperacao);
                alterarEventoTarifavelVigente(novoEventoTar);
            }
        } else if (StatusEventoTarifavelEnum.getBy(antigoEventoTar.getStatus()).equals(StatusEventoTarifavelEnum.VIGENTE)) {
            if (DateUtil.igualA(novoEventoTar.getDataInicioVigencia(), antigoEventoTar.getDataInicioVigencia())) {
                if (dao.existeProgramada(novoEventoTar.getCodEventoTarifavel())) {
                    if (validaValoresIguaisVigente(novoEventoTar, obterStatus(novoEventoTar.getCodEventoTarifavel(), Constantes.EVENTO_TARIFAVEL_PROGRAMADO))) {
                        throw new ComumNegocioException("operacional.eventotarifavel.incluir.erro.valor.igual.programado");
                    } else {
                        tipoOperacao = alterarEventoTarifavel(novoEventoTar, tipoOperacao);
                    }
                }
                tipoOperacao = alterarEventoTarifavel(novoEventoTar, tipoOperacao);
            } else if (DateUtil.menorQue(novoEventoTar.getDataInicioVigencia(), new Date())) {
                // Data inicio menor que a data inicio antiga, não permite alterações
                throw new ComumNegocioException("operacional.eventotarifavel.incluir.erro.data.inferior.atual");
            } else {
                tipoOperacao = incluirEventoTarifavel(novoEventoTar, tipoOperacao, antigoEventoTar);
            }
        }
        return tipoOperacao;
    }

    /**
     * Método responsável por
     * 
     * @param novoEventoTar
     * @param antigoEventoTar
     * @return Boolean
     * 
     */
    private Boolean validaValoresIguaisVigente(EventoTarifavelDto novoEventoTar, EventoTarifavelDto antigoEventoTar) {
        return ((novoEventoTar.getValorBancoob().compareTo(antigoEventoTar.getValorBancoob()) == Constantes.INTEGER_ZERO)
                && (novoEventoTar.getValorCIP().compareTo(antigoEventoTar.getValorCIP()) == Constantes.INTEGER_ZERO));
    }

    /**
     * Método responsável por
     * 
     * @param novoEventoTar
     * @param tipoOperacao
     * @param antigoEventoTar
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws BancoobException String
     * 
     */
    private String incluirEventoTarifavel(EventoTarifavelDto novoEventoTar, String tipoOperacao, EventoTarifavelDto antigoEventoTar)
            throws ComumException, ComumNegocioException, BancoobException {
        if (ObjectUtil.isNull(antigoEventoTar.getDataFimVigencia()) && DateUtil.maiorQue(novoEventoTar.getDataInicioVigencia(), new Date())) {
            if (validaValoresIguaisVigente(novoEventoTar, obterStatus(novoEventoTar.getCodEventoTarifavel(), Constantes.EVENTO_TARIFAVEL_VIGENTE))) {
                throw new ComumNegocioException("operacional.eventotarifavel.incluir.erro.valor.igual.vigente");
            } else {
                try {
                    EventoTarifavelDDATarifa eventoTarifavelDDATarifa = obter(novoEventoTar.getIdEventoTarifavelDDATarifa());
                    eventoTarifavelDDATarifa.setDataFimVigencia(obterDataFimVigente(novoEventoTar));
                    debug("### Incluindo evento tarifavel");
                    debug("Parâmetro - EventoTarifavelDto: novoEventoTarDto");
                    incluir(montaEventoTarifavelDDAProgramado(novoEventoTar, TipoOperacaoLog.INCLUSAO));
                    tipoOperacao = INCLUSAO;
                } catch (BancoobException e) {
                    throw new BancoobException("operacional.eventotarifavel.incluir.erro", e);
                }
            }
        } else {
            throw new ComumNegocioException("operacional.eventotarifavel.incluir.erro.ja.existe.programacao");
        }
        return tipoOperacao;
    }

    /**
     * Método responsável por
     * 
     * @param novoEventoTar
     * @return DateTimeDB
     * 
     */
    private DateTimeDB obterDataFimVigente(EventoTarifavelDto novoEventoTar) {
        return new DateTimeDB(DateUtil.decrementarData(novoEventoTar.getDataInicioVigencia(), Calendar.DATE, 1).getTime());
    }

    /**
     * Método responsável por
     * 
     * @param novoEventoTar
     * @param tipoOperacao
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws BancoobException String
     * 
     */
    private String alterarEventoTarifavel(EventoTarifavelDto novoEventoTar, String tipoOperacao) throws BancoobException {
        if (dao.existeRateio(novoEventoTar.getIdEventoTarifavelDDATarifa(), novoEventoTar.getCodEventoTarifavel())) {
            throw new ComumNegocioException("operacional.eventotarifavel.alterar.alerta.possui.rateio");
        } else {
            try {
                debug("### Alterando evento tarifavel");
                debug("Parâmetro - EventoTarifavelDto: novoEventoTarDto");

                EventoTarifavelDDATarifa eventoTarifavelDDATarifa = obter(novoEventoTar.getIdEventoTarifavelDDATarifa());
                eventoTarifavelDDATarifa.setValorBancoob(novoEventoTar.getValorBancoob());
                eventoTarifavelDDATarifa.setValorCIP(novoEventoTar.getValorCIP());
                eventoTarifavelDDATarifa.setDataInicioVigencia(novoEventoTar.getDataInicioVigencia());
                eventoTarifavelDDATarifa.setIdInstituicao(Integer.parseInt(getUsuario().getIdInstituicao()));
                eventoTarifavelDDATarifa.setIdUsuario(getUsuarioLogado());
                TipoOperacaoLog tipoOperacaoLog = new TipoOperacaoLog();
                tipoOperacaoLog.setCodTipoOperacaoLog(TipoOperacaoLog.ALTERACAO);
                eventoTarifavelDDATarifa.setTipoOperacaoLog(tipoOperacaoLog);
                alterar(eventoTarifavelDDATarifa);
                tipoOperacao = ALTERACAO;
            } catch (ComumException e) {
                throw new ComumNegocioException("operacional.eventotarifavel.alterar.erro", e);
            }
        }
        return tipoOperacao;
    }

    /**
     * Método responsável por
     * 
     * @param novoEventoTar
     * @throws BancoobException
     * 
     */
    private void alterarEventoTarifavelVigente(EventoTarifavelDto novoEventoTar) throws BancoobException {
        if (dao.existeVigente(novoEventoTar.getCodEventoTarifavel())) {
            EventoTarifavelDto eventoTarifavelVigente = obterStatus(novoEventoTar.getCodEventoTarifavel(), Constantes.EVENTO_TARIFAVEL_VIGENTE);
            EventoTarifavelDDATarifa eventoTarifavelDDATarifa = obter(eventoTarifavelVigente.getIdEventoTarifavelDDATarifa());
            eventoTarifavelDDATarifa.setDataFimVigencia(obterDataFimVigente(novoEventoTar));
            TipoOperacaoLog tipoOperacaoLog = new TipoOperacaoLog();
            tipoOperacaoLog.setCodTipoOperacaoLog(TipoOperacaoLog.ALTERACAO);
            eventoTarifavelDDATarifa.setTipoOperacaoLog(tipoOperacaoLog);
            alterar(eventoTarifavelDDATarifa);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.EventoTarifavelDDAServico#excluirEventoTarifavelDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)
     */
    public void excluirEventoTarifavelDDA(EventoTarifavelDto eventoTarDto) throws BancoobException {
        if ((StatusEventoTarifavelEnum.getBy(eventoTarDto.getStatus()).getCodStatus() != Constantes.EVENTO_TARIFAVEL_VENCIDO)) {
            excluirEventoTarifavelProgramado(eventoTarDto);
            excluirEventoTarifavelVigente(eventoTarDto);
        } else {
            throw new ComumNegocioException("operacional.eventotarifavel.excluir.erro");
        }
    }

    /**
     * Método responsável por
     * 
     * @param eventoTarDto
     * @throws ComumException
     * @throws BancoobException void
     * 
     */
    private void excluirEventoTarifavelProgramado(EventoTarifavelDto eventoTarDto) throws ComumException, BancoobException {
        if (StatusEventoTarifavelEnum.getBy(eventoTarDto.getStatus()).equals(StatusEventoTarifavelEnum.PROGRAMADO)) {
            EventoTarifavelDto eventoTarifavelDtoVigente = obterStatus(eventoTarDto.getCodEventoTarifavel(), Constantes.EVENTO_TARIFAVEL_VIGENTE);
            if (!ObjectUtil.isNull(eventoTarifavelDtoVigente)) {
                // Exclusao da programada
                excluir(eventoTarDto.getIdEventoTarifavelDDATarifa());
                // Altero a data da vigencia para nula e salva pela transação
                EventoTarifavelDDATarifa eventoTarifavelVigente = dao.obter(eventoTarifavelDtoVigente.getIdEventoTarifavelDDATarifa());
                eventoTarifavelVigente.setDataFimVigencia(null);
            } else {
                throw new ComumNegocioException("operacional.eventotarifavel.excluir.programado.unico.erro");
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param eventoTarDto
     * @throws ComumException
     * @throws BancoobException void
     * 
     */
    private void excluirEventoTarifavelVigente(EventoTarifavelDto eventoTarDto) throws ComumException, BancoobException {
        if (StatusEventoTarifavelEnum.getBy(eventoTarDto.getStatus()).equals(StatusEventoTarifavelEnum.VIGENTE)) {
            // verifico se possui rateio
            if (dao.existeRateio(eventoTarDto.getIdEventoTarifavelDDATarifa(), eventoTarDto.getCodEventoTarifavel())) {
                throw new ComumNegocioException("operacional.eventotarifavel.excluir.alerta.possui.rateio");
            } else if (dao.existeProgramada(eventoTarDto.getCodEventoTarifavel())) {
                throw new ComumNegocioException("operacional.eventotarifavel.excluir.programado.erro");
            } else {
                EventoTarifavelDto eventoTarifavelDtoVencido = obterStatus(eventoTarDto.getCodEventoTarifavel(), Constantes.EVENTO_TARIFAVEL_VENCIDO);
                if (!ObjectUtil.isNull(eventoTarifavelDtoVencido)) {
                    EventoTarifavelDDATarifa eventoTarifavelVencido = dao.obter(eventoTarifavelDtoVencido.getIdEventoTarifavelDDATarifa());
                    excluir(eventoTarDto.getIdEventoTarifavelDDATarifa());
                    eventoTarifavelVencido.setDataFimVigencia(null);
                } else {
                    throw new ComumNegocioException("operacional.eventotarifavel.excluir.vigente.unico.erro");
                }
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param novoEventoTar
     * @param operacao
     * @return
     * @throws ComumNegocioException EventoTarifavelDDATarifa
     * 
     */
    private EventoTarifavelDDATarifa montaEventoTarifavelDDAProgramado(EventoTarifavelDto novoEventoTar, String operacao) throws ComumNegocioException {
        EventoTarifavelDDATarifa eventoTar = new EventoTarifavelDDATarifa();
        eventoTar.setValorBancoob(novoEventoTar.getValorBancoob());
        eventoTar.setValorCIP(novoEventoTar.getValorCIP());
        eventoTar.setDataInicioVigencia(novoEventoTar.getDataInicioVigencia());
        eventoTar.setIdInstituicao(Integer.parseInt(getUsuario().getIdInstituicao()));
        eventoTar.setIdUsuario(getUsuarioLogado());

        EventoTarifavelDDA eventoTarDDA = new EventoTarifavelDDA();
        eventoTarDDA.setCodEventoTarifavel(novoEventoTar.getCodEventoTarifavel());
        eventoTar.setEventoTarifavelDDA(eventoTarDDA);

        TipoOperacaoLog tipoOperacaoLog = new TipoOperacaoLog();
        tipoOperacaoLog.setCodTipoOperacaoLog(operacao);
        eventoTar.setTipoOperacaoLog(tipoOperacaoLog);

        return eventoTar;
    }

    /**
     * @param codEventoTarifavel
     * @param codStatus
     * @return
     * @throws ComumException
     */
    private EventoTarifavelDto obterStatus(Integer codEventoTarifavel, Integer codStatus) throws ComumException {
        return dao.obterUltimoStatusEventoTarifavel(codEventoTarifavel, codStatus);
    }

    /**
     * Método responsável por
     * 
     * @return SCIServico
     * 
     */
    private SCIServico getSciServico() {
        return IntegracaoInternaServiceLocator.getInstance().localizarSCIServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO,
     *      br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioEventoTarifavel(FiltroRelatorioEventoTarifavelDto filtroRelatorio, UsuarioBancoobDTO usuario) throws BancoobException {
        List<EventoTarifavelDto> listaEventoTarifavel = getDAO().pesquisaEventoTarifavelDDA(filtroRelatorio.getCodEventoTarifavel(), filtroRelatorio.getCodStatus());

        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA512, listaEventoTarifavel, usuario, getParametrosEventoTarifavel(filtroRelatorio, usuario));
    }
    
    /**
     * Método responsável por
     * 
     * @param filtroRelatorio
     * @param usuario
     * @return
     * @throws BancoobException Map<String,Object>
     * 
     */
    private Map<String, Object> getParametrosEventoTarifavel(FiltroRelatorioEventoTarifavelDto filtroRelatorio, UsuarioBancoobDTO usuario) throws BancoobException {
        Map<String, Object> parametros = null;

        parametros = new HashMap<>();
        parametros.put("siglaCooperativa", getSiglaInstituicao(usuario));
        parametros.put("descEventoTarifavel", filtroRelatorio.getDescEventoTarifavel());
        parametros.put("status", (filtroRelatorio.getCodStatus() == null ? 0 : filtroRelatorio.getCodStatus()));

        return parametros;
    }

    /**
     * Método responsável por
     * 
     * @param usuario
     * @return
     * @throws BancoobException String
     * 
     */
    private String getSiglaInstituicao(UsuarioBancoobDTO usuario) throws BancoobException {
        InstituicaoDto instituicaoDto = getSciServico().obterInstituicaoPorCooperativaCache(Integer.parseInt(usuario.getIdInstituicao()));
        return ObjectUtil.isNull(instituicaoDto) ? null : instituicaoDto.getSiglaInstituicao();
    }
}
