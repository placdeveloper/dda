/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         ContingenciaServicoEJB.java
 * Data Criação:    Jan 3, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ParametroDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoContingencia;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoContingencia;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.ContingenciaServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.OperacionalContextUtil;

/**
 * ContingenciaServicoEJB
 * 
 * @author Danilo.Barros
 */
@Stateless
@Local(ContingenciaServicoLocal.class)
public class ContingenciaServicoEJB extends OperacionalCrudServicoEJB<HistoricoContingencia> implements ContingenciaServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager entityManager;

    @Dao(entityManager = "entityManager", fabrica = OperacionalDaoFactory.class)
    private ContingenciaDao contingenciaDao;

    @Dao(entityManager = "entityManager", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected ContingenciaDao getDAO() {
        return contingenciaDao;
    }

    /**
     * Método
     * 
     * @return ParametroDao @param @throws
     */
    private ParametroDao getParametroDAO() {
        return parametroDao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico#incluirContingencia(br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto)
     */
    public void incluirContingencia(ContingenciaDto contingenciaDto, Short idCanal) throws BancoobException {
        incluirContingencia(contingenciaDto);

        if (!contingenciaDto.getContingenciaHabilitada()) {
            alterarMensagensBaixaOperacionalContingencia();
        }
    }

    /**
     * Método responsável por atualizar as mensagens de baixa operaciona DDA0108/ADDA108 para baixa operacional em contingência ADDA114.
     * 
     * @throws ComumException void
     */
    public void alterarMensagensBaixaOperacionalContingencia() throws ComumException {
        Integer qtdMensagemDDAContingencia = getDAO().obterQtdMensagemDDAContingencia();

        if (!ObjectUtil.isEmpty(qtdMensagemDDAContingencia)) {
            getLogger().debug("qtdMensagemDDAContingencia - " + qtdMensagemDDAContingencia);
            BigDecimal loop = BigDecimal.valueOf(qtdMensagemDDAContingencia).divide(BigDecimal.valueOf(Constantes.INTEGER_20_MIL)).setScale(10, RoundingMode.CEILING);

            for (int i = 0; i < loop.doubleValue(); i++) {
                atualizarMensagemDDA114();
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param contingenciaDto
     * @return
     * @throws ComumException
     * @throws BancoobException Integer
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void incluirContingencia(ContingenciaDto contingenciaDto) throws ComumException, BancoobException {
        incluirHistoricoContingencia(contingenciaDto);

        final String valorParametro = contingenciaDto.getContingenciaHabilitada() ? Constantes.STRING_NUMERO_1 : Constantes.STRING_NUMERO_0;

        // Atualizando a contingência automática
        atualizarParametro(ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP, valorParametro);

        // Atualizando a contingência manual
        atualizarParametro(ParametroDDA.HABILITAR_CONTINGENCIA_MANUAL, valorParametro);
    }

    /**
     * Método responsável por atualizar o parâmetro
     * 
     * @param idParametro
     * @param valorParametro
     * @throws ComumException
     */
    private void atualizarParametro(long idParametro, String valorParametro) throws ComumException {
        ParametroDDA parametroDDA = entityManager.find(ParametroDDA.class, idParametro);
        parametroDDA.setDataHoraUltimaAtualizacao(new DateTimeDB());
        parametroDDA.setValorParametro(valorParametro);
        getParametroDelegate().alterarParametro(parametroDDA);
    }

    /**
     * Método responsável por incluir o histórico da contingência
     * 
     * @param contingenciaDto
     * @throws ComumException
     * @throws BancoobException
     */
    public void incluirHistoricoContingencia(ContingenciaDto contingenciaDto) throws ComumException, BancoobException {
        HistoricoContingencia historicoContingencia;

        if (!contingenciaDto.getContingenciaHabilitada()) {
            HistoricoContingencia habilitacaoContingencia = entityManager.find(HistoricoContingencia.class, obterIdUltimaHabilitacaoContingencia());
            historicoContingencia = new HistoricoContingencia(new DateTimeDB(contingenciaDto.getDataHoraSituacao().getTime()),
                    new TipoContingencia(habilitacaoContingencia.getTipoContingencia().getCodTipoContingencia()), contingenciaDto.getIdUsuario(), habilitacaoContingencia,
                    contingenciaDto.getContingenciaHabilitada());
        } else {
            historicoContingencia = new HistoricoContingencia(new DateTimeDB(contingenciaDto.getDataHoraSituacao().getTime()),
                    new TipoContingencia(contingenciaDto.getTipoContingencia()), contingenciaDto.getIdUsuario(), null, contingenciaDto.getContingenciaHabilitada());
        }

        incluirContingencia(historicoContingencia);
    }

    /**
     * Método responsável por
     * 
     * @param qtdMensagemDDA void
     * @throws ComumException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void atualizarMensagemDDA114() throws ComumException {
        getDAO().atualizarMensagensBaixaOperacional();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico#listarHistoricoContingencias()
     */
    public List<GradeContingenciaDto> listarHistoricoContingencias() throws ComumException {
        return getDAO().listarHistoricoContingencias();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico#obterValorInteger(java.lang.Long, java.lang.Integer)
     */
    public Integer obterValorInteger(Long idParametro, Integer idInstituicao) throws ComumException {
        return getParametroDAO().obterValorInteger(idParametro, idInstituicao);
    }

    /**
     * Método
     * 
     * @return Long
     * @param
     * @throws ComumException
     */
    private Long obterIdUltimaHabilitacaoContingencia() throws ComumException {
        return getDAO().obterIdUltimaHabilitacaoContingencia();
    }

    /**
     * Método
     * 
     * @return
     * @param historicoContingencia
     * @throws BancoobException
     */
    private void incluirContingencia(HistoricoContingencia historicoContingencia) throws BancoobException {
        getDAO().incluirContingencia(historicoContingencia);
    }

    /**
     * Método
     * 
     * @return String @param habilitaContingencia @param desabilitaContingencia @throws
     */
    private String calculaPeriodoIndisponibilidade(HistoricoContingencia habilitaContingencia, HistoricoContingencia desabilitaContingencia) {
        return new CalculaPeriodoContingencia(habilitaContingencia.getDataHoraSituacao(), desabilitaContingencia.getDataHoraSituacao()).toString();
    }

    class CalculaPeriodoContingencia {
        private static final int HORAS_NO_DIA = 24;
        private static final String REGEX_CARACTER = ":";
        private int dias;
        private int horas;
        private final int minutos;

        /**
         * @param dias
         * @param horas
         * @param minutos
         */
        public CalculaPeriodoContingencia(DateTimeDB dataInicial, DateTimeDB dataFinal) {
            final String tempoDecorrido = DataUtil.calcularTempoDecorrido(dataInicial, dataFinal);
            final List<String> parametros = Arrays.asList(tempoDecorrido.split(REGEX_CARACTER));
            horas = Integer.valueOf(parametros.get(0));
            minutos = Integer.valueOf(parametros.get(1));
            if (horas > HORAS_NO_DIA) {
                dias = horas / HORAS_NO_DIA;
                horas = horas - HORAS_NO_DIA;
            }
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return String.format("%s %s %s", formatarPeriodo(dias, " dia(s)"), formatarPeriodo(horas, " hora(s)"), formatarPeriodo(minutos, " minuto(s)"));
        }

        /**
         * Método
         * 
         * @return String @param periodo @param label @throws
         */
        private String formatarPeriodo(int periodo, String label) {
            return periodo > Constantes.INTEGER_ZERO ? periodo + label : "";
        }

    }

    /**
     * @return o atributo usuarioLogado
     */
    @Override
    public String getUsuarioLogado() {
        return ObjectUtil.isNull(getUsuario()) ? null : getUsuario().getLogin();
    }

    /**
     * @return o atributo idInstituicaoLogado
     */
    @Override
    public Integer getIdInstituicaoLogado() {
        return ObjectUtil.isNull(getUsuario()) ? null : Integer.valueOf(getUsuario().getIdInstituicao());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico#configurarRelatorioHistoricoContingencia(br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioHistoricoContingencia(UsuarioBancoobDTO usuario) throws BancoobException {
        List<HistoricoContingencia> listaHistoricoContingencias = getDAO().listar();
        List<HistoricoContingencia> listaContingenciasRemovidas = new ArrayList<>();
        Collections.sort(listaHistoricoContingencias, new ComparadorDeContingencias());

        for (HistoricoContingencia historicoContingencia : listaHistoricoContingencias) {
            if (!historicoContingencia.getContingenciaHabilitada()) {
                int currentIndex = Collections.binarySearch(listaHistoricoContingencias, historicoContingencia, new ComparadorDeContingencias());
                String periodoIndisponibilidade = calculaPeriodoIndisponibilidade(listaHistoricoContingencias.get(currentIndex - 1), historicoContingencia);
                historicoContingencia.setPeriodoIndisponibilidade(Constantes.BRANCO.equals(periodoIndisponibilidade.trim()) ? "0 minuto(s)" : periodoIndisponibilidade.trim());
                listaContingenciasRemovidas.add(historicoContingencia.getHabilitacaoContingencia());
            }
        }

        listaHistoricoContingencias.removeAll(listaContingenciasRemovidas);

        ConfigurarRelatorioJasper configurarRelatorio = new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA503, listaHistoricoContingencias, usuario);
        configurarRelatorio.setParametro("siglaCooperativa", OperacionalContextUtil.getInstituicao(usuario.getIdInstituicao()).getSiglaInstituicao());
        return configurarRelatorio;
    }

    /**
     * Método responsável por
     * 
     * @return ParametroDelegate
     * 
     */
    private ParametroDelegate getParametroDelegate() {
        return ComumFabricaDelegates.getInstance().getParametroDelegate();
    }

}
