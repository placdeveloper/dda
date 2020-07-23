package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.ProdutoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.SubTipoGrade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDA0401Delegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.GradeHorariaServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao;

/**
 * GradeHorariaServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ GradeHorariaServicoLocal.class })
public class GradeHorariaServicoEJB extends OperacionalCrudServicoEJB<GradeHoraria> implements GradeHorariaServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private GradeHorariaDao dao;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private TipoGradeHorariaDao tipoGradeHorariaDao;

    private ADMDelegate admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();

    private MensagemDDA0401Delegate mensagemDDA0401Delegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDA0401Delegate();

    private DateTimeDB dataTemp;
    private static final int NUM_PRIORIDADE_MSG = 0;
    private static final String CAMPO_OBRIGATORIO = "integracaocip.campo.obrigatorio";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected GradeHorariaDao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.GradeHorariaServico#removerGradeHoraria(br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria)
     */
    public void removerGradeHoraria(GradeHoraria gradeHoraria) throws ComumException {
        gradeHoraria = em.find(GradeHoraria.class, gradeHoraria.getId());
        getDAO().removerGrades(gradeHoraria.getTipoGradeHoraria().getCodTipoGradeHoraria(), gradeHoraria.getDataReferencia());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.GradeHorariaServico#listarTipoGradeHoraria()
     */
    public List<TipoGradeHorariaDto> listarTipoGradeHoraria() throws OperacionalException {
        return tipoGradeHorariaDao.listarTipoGradeHorariaPorCodigoSubtipo(SubTipoGrade.GRADE_TODOS);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.GradeHorariaServico#incluirGradeHoraria(br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto)
     */
    public void incluirGradeHoraria(GradeHorariaDto gradeHorariaDto) throws ComumNegocioException, ComumException {
        validarGradeHoraria(gradeHorariaDto);
        getDAO().removerGrades(gradeHorariaDto.getCodTipoGradeHoraria(), new DateTimeDB(gradeHorariaDto.getDataReferencia().getTime()));

        for (GradeHorariaDto itemGradeHoraria : gradeHorariaDto.getListaGradeHoraria()) {
            preparaDataHoraMinuto(itemGradeHoraria);
            GradeHoraria gradeHoraria = popularGradeHoraria(itemGradeHoraria, gradeHorariaDto);
            em.persist(gradeHoraria);
        }

    }

    /**
     * @param gradeHorariaDto
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private void validarGradeHoraria(GradeHorariaDto gradeHorariaDto) throws ComumNegocioException, ComumException {
        if (ObjectUtil.isNull(gradeHorariaDto.getDataReferencia())) {
            throw new ComumNegocioException(CAMPO_OBRIGATORIO, "Data Referência");
        } else if (ObjectUtil.isNull(gradeHorariaDto.getCodTipoGradeHoraria())) {
            throw new ComumNegocioException(CAMPO_OBRIGATORIO, "Cód. Tipo Grade");
        } else if (!existeEmTipoGradeHoraria(gradeHorariaDto)) {
            throw new ComumNegocioException("Cód. Tipo Grade não cadastrado");
        }

        dataTemp = null;
        int linha = 1;

        for (GradeHorariaDto itemGradeHoraria : gradeHorariaDto.getListaGradeHoraria()) {
            validarCamposLista(itemGradeHoraria);
            preparaDataHoraMinuto(itemGradeHoraria);
        }

        Collections.sort(gradeHorariaDto.getListaGradeHoraria(), new OrdenarDataAbertura());

        for (GradeHorariaDto itemGradeHoraria : gradeHorariaDto.getListaGradeHoraria()) {
            validarDatas(itemGradeHoraria.getDataHoraAbertura(), linha);
            validarDatas(itemGradeHoraria.getDataHoraFechamento(), linha);

            linha++;
        }

    }

    /**
     * @param gradeHorariaDto
     * @return
     * @throws ComumException boolean
     * 
     */
    private boolean existeEmTipoGradeHoraria(GradeHorariaDto gradeHorariaDto) throws ComumException {
        return !ObjectUtil.isEmpty(tipoGradeHorariaDao.obterCountTipoGradeHoraria(gradeHorariaDto.getCodTipoGradeHoraria()));
    }

    /**
     * @author Samuell.Ramos Ordena Grade por Data Abertura.
     */
    private class OrdenarDataAbertura implements Comparator<GradeHorariaDto> {
        public int compare(GradeHorariaDto gradeA, GradeHorariaDto gradeB) {
            Long valorData1 = gradeA.getDataHoraAbertura().getTime();
            Long valorData2 = gradeB.getDataHoraAbertura().getTime();
            return valorData1.compareTo(valorData2);
        }
    }

    /**
     * @param data
     * @param linha
     * @throws ComumNegocioException
     */
    private void validarDatas(DateTimeDB data, int linha) throws ComumNegocioException {
        if (!ObjectUtil.isNull(dataTemp)) {
            if (DateUtil.maiorQue(data, dataTemp)) {
                dataTemp = data;
            } else {
                throw new ComumNegocioException("integracaocip.gradehoraria.invalida", +linha);
            }
        } else {
            dataTemp = data;
        }
    }

    /**
     * @param gradeHorariaDto
     * @throws ComumNegocioException
     */
    private void validarCamposLista(GradeHorariaDto gradeHorariaDto) throws ComumNegocioException {
        if (ObjectUtil.isNull(gradeHorariaDto.getDataHoraAbertura())) {
            throw new ComumNegocioException(CAMPO_OBRIGATORIO, "Data/Hora Abertura");
        } else if (ObjectUtil.isNull(gradeHorariaDto.getDataHoraFechamento())) {
            throw new ComumNegocioException(CAMPO_OBRIGATORIO, "Data/Hora Fechamento");
        } else if (ObjectUtil.isEmpty(gradeHorariaDto.getHoraAbertura())) {
            throw new ComumNegocioException(CAMPO_OBRIGATORIO, "Data/Hora Abertura");
        } else if (ObjectUtil.isEmpty(gradeHorariaDto.getHoraFechamento())) {
            throw new ComumNegocioException(CAMPO_OBRIGATORIO, "Data/Hora Fechamento");
        } else if (ObjectUtil.isEmpty(gradeHorariaDto.getMinutoAbertura())) {
            throw new ComumNegocioException(CAMPO_OBRIGATORIO, "Data/Hora Abertura");
        } else if (ObjectUtil.isEmpty(gradeHorariaDto.getMinutoFechamento())) {
            throw new ComumNegocioException(CAMPO_OBRIGATORIO, "Data/Hora Fechamento");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.GradeHorariaServico#obterGradeHoraria(br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto)
     */
    public GradeHorariaDto obterGradeHoraria(GradeHorariaDto gradeHorariaDto) throws ComumException {

        List<GradeHorariaDto> resultListaGradeHorariaDto = getDAO().listarGrades(gradeHorariaDto.getTipoGradeHorariaDto().getCodTipoGradeHoraria(),
                new DateTimeDB(gradeHorariaDto.getDataReferencia().getTime()));
        List<TipoGradeHorariaDto> listaTipoGradeHorariaDto = tipoGradeHorariaDao.listarTipoGradeHorariaPorCodigoSubtipo(SubTipoGrade.GRADE_TODOS);

        List<GradeHorariaDto> listaGradeHorariaDto = new ArrayList<GradeHorariaDto>();

        for (GradeHorariaDto itemGradeHoraria : resultListaGradeHorariaDto) {
            converteDataEmHoraMinuto(itemGradeHoraria);
            listaGradeHorariaDto.add(itemGradeHoraria);
        }

        gradeHorariaDto.setListaGradeHoraria(listaGradeHorariaDto);
        gradeHorariaDto.setListaTipoGradeHoraria(listaTipoGradeHorariaDto);

        return gradeHorariaDto;
    }

    /**
     * @param itemGradeHoraria
     */
    private void converteDataEmHoraMinuto(GradeHorariaDto itemGradeHoraria) {
        formatarHoraMinutoDuasCasas(itemGradeHoraria);
    }

    /**
     * @param itemGradeHoraria
     * @return
     */
    private GradeHorariaDto preparaDataHoraMinuto(GradeHorariaDto itemGradeHoraria) {
        Date dataAbertura = removerTimeDate(itemGradeHoraria.getDataHoraAbertura());
        String horaAbertura = itemGradeHoraria.getHoraAbertura();
        String minutoAbertura = itemGradeHoraria.getMinutoAbertura();
        String segundoAbertura = "00";

        DateTimeDB novaDataHoraAbertura = DateUtil.getDateTimeDB(DateUtil.incrementarHora(dataAbertura, horaAbertura + ":" + minutoAbertura + ":" + segundoAbertura));

        Date dataFechamento = removerTimeDate(itemGradeHoraria.getDataHoraFechamento());
        String horaFechamento = itemGradeHoraria.getHoraFechamento();
        String minutoFechamento = itemGradeHoraria.getMinutoFechamento();
        String segundoFechamento = "00";

        DateTimeDB novaDataHoraFechamento = DateUtil.getDateTimeDB(DateUtil.incrementarHora(dataFechamento, horaFechamento + ":" + minutoFechamento + ":" + segundoFechamento));

        itemGradeHoraria.setDataHoraAbertura(novaDataHoraAbertura);
        itemGradeHoraria.setDataHoraFechamento(novaDataHoraFechamento);
        return itemGradeHoraria;
    }

    /**
     * @param data
     * @return
     */
    private Date removerTimeDate(DateTimeDB data) {
        String stringData = DataUtil.converterDateToString(data, "dd/MM/yyyy");
        Date dataFormatada = DataUtil.converterStringToDate(stringData, "dd/MM/yyyy");
        return dataFormatada;
    }

    /**
     * @param itemGradeHoraria
     * @param gradeHorariaDto
     * @return
     */
    private GradeHoraria popularGradeHoraria(GradeHorariaDto itemGradeHoraria, GradeHorariaDto gradeHorariaDto) {
        GradeHoraria gradeHoraria = new GradeHoraria();
        gradeHoraria.setDataReferencia(DateUtil.getDateTimeDB(gradeHorariaDto.getDataReferencia()));
        gradeHoraria.setDataHoraAbertura(DateUtil.getDateTimeDB(itemGradeHoraria.getDataHoraAbertura()));
        gradeHoraria.setDataHoraFechamento(DateUtil.getDateTimeDB(itemGradeHoraria.getDataHoraFechamento()));
        gradeHoraria.setTipoGradeHoraria(new TipoGradeHoraria(gradeHorariaDto.getCodTipoGradeHoraria()));
        return gradeHoraria;
    }

    /**
     * @param itemGradeHoraria
     */
    private void formatarHoraMinutoDuasCasas(GradeHorariaDto itemGradeHoraria) {
        Calendar calendarAbertura = Calendar.getInstance();
        Calendar calendarFechamento = Calendar.getInstance();
        calendarAbertura.setTime(itemGradeHoraria.getDataHoraAbertura());
        calendarFechamento.setTime(itemGradeHoraria.getDataHoraFechamento());

        SimpleDateFormat horaFormat = new SimpleDateFormat("kk");
        SimpleDateFormat minutoFormat = new SimpleDateFormat("mm");

        itemGradeHoraria.setMinutoAbertura(minutoFormat.format(calendarAbertura.getTime()));

        itemGradeHoraria.setMinutoFechamento(minutoFormat.format(calendarFechamento.getTime()));
        if (horaFormat.format(calendarAbertura.getTime()).equals("24")) {
            itemGradeHoraria.setHoraAbertura("00");
        } else {
            itemGradeHoraria.setHoraAbertura(horaFormat.format(calendarAbertura.getTime()));
        }
        if (horaFormat.format(calendarFechamento.getTime()).equals("24")) {
            itemGradeHoraria.setHoraFechamento("00");
        } else {
            itemGradeHoraria.setHoraFechamento(horaFormat.format(calendarFechamento.getTime()));
        }
        itemGradeHoraria.setDataHoraAberturaTemp(itemGradeHoraria.getDataHoraAbertura());
        itemGradeHoraria.setDataHoraFechamentoTemp(itemGradeHoraria.getDataHoraFechamento());

    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.GradeHorariaServico#verificarTipoGradePersonalizada()
     */
    public void cadastrarGradeHoraria() throws BancoobException {
        getLogger().debug("###### Iniciando cadastrarGradeHoraria()...");
        ProdutoDto produtoCobranca = admDelegate.obterProdutoCobrancaBancoob();
        getLogger().debug("###### Data Movimento Atual de Referencia para verificação: " + produtoCobranca.getDataAtualMovimento());
        getLogger().debug("###### Proxima Data Movimento de Referencia para verificação: " + produtoCobranca.getDataProximoMovimento());

        mensagemDDA0401Delegate.incluir(null, new DateTimeDB(produtoCobranca.getDataAtualMovimento().getTime()), NUM_PRIORIDADE_MSG);

        for (Date dataMovimento : DateUtil.listarIntervaloDatas(produtoCobranca.getDataAtualMovimento(), produtoCobranca.getDataProximoMovimento())) {
            incluirGradeHorariaSicoob(dataMovimento);
            incluirGradeHorariaCIP(dataMovimento);
        }

        getLogger().debug("###### Finalizando cadastrarGradeHoraria()...");
    }

    /**
     * 
     * Método responsável por verificar se há grade personalizada em sem grade para o proximo movimento
     * 
     * @param dataReferencia
     * @throws BancoobException
     */
    private void incluirGradeHorariaSicoob(Date dataReferencia) throws BancoobException {
        getLogger().debug("###### Iniciando incluirGradeHorariaSicoob(Date dataReferencia)");
        List<TipoGradeHorariaDto> listaTipoGradeHorariaDtos = tipoGradeHorariaDao.listarTipoGradeHorariaPersonalizadaSemGradeHoraria(new DateTimeDB(dataReferencia.getTime()));
        if (!ObjectUtil.isEmpty(listaTipoGradeHorariaDtos)) {
            getLogger().debug("###### Total de grades personalizadas sem grade horaria: " + listaTipoGradeHorariaDtos.size());
            for (TipoGradeHorariaDto tipoGradeHorariaDto : listaTipoGradeHorariaDtos) {
                getLogger().debug("###### CodTipoGradeHoraria...: " + tipoGradeHorariaDto.getCodTipoGradeHoraria());
                getLogger().debug("###### DataUltimaGradeHoraria: " + tipoGradeHorariaDto.getDataUltimaGrade());
                getLogger().debug("###### DataReferencia: " + dataReferencia);
                getDAO().incluirGradeHorariaPersonalizada(dataReferencia, tipoGradeHorariaDto.getDataUltimaGrade(), tipoGradeHorariaDto.getCodTipoGradeHoraria());
            }
        } else {
            getLogger().debug("###### Nenhuma Grade Horaria Personalizada sem Grade");
        }
        getLogger().debug("###### Finalizando incluirGradeHorariaSicoob(Date dataReferencia)");
        removerGradesHorariasAntigas();

    }

    /**
     * Método responsável por
     * 
     * @throws OperacionalException void
     * 
     */
    private void incluirGradeHorariaCIP(Date dataReferencia) throws ComumException {
        getLogger().debug("###### Iniciando incluirGradeHorariaCIP(Date dataReferencia)");
        dao.incluirGradeHorariaCIP(dataReferencia);
        getLogger().debug("###### Finalizando incluirGradeHorariaCIP(Date dataReferencia)");
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    private void removerGradesHorariasAntigas() throws ComumException {
        try {
            getDAO().excluirGradesHorariasAntigas();
        } catch (ComumException e) {
            throw new ComumException("Erro ao excluir mensagens antigas", e);
        }
    }

}
