/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         IntegracaoCipDaoFactory.java
 * Data Cria��o:    Jul 6, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.ArquivoCipDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AdministrativoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoEnviadoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiarioLegadoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiariosAlertaDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BoletoDDADaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BoletoLegadoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ContingenciaDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.EventoTarifavelDDADaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.GradeHorariaDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.HistoricoMensagemDDADaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ManutencaoMensagemDDABoletoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.MensagemDDADaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.MensagemDDAPagadorDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.MonitoracaoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.MonitoramentoMensagensDDADaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.OperacaoBeneficiarioDDADaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.PagadorEletronicoDDADaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.PagadorEletronicoDDALegadoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.RateioDDALancamentoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.RateioDDALancamentoLegadoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.RateioTarifasCipDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.TipoErroCipDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.TipoGradeHorariaDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.TipoMensagemDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.TratamentoPendenciaErroDaoImpl;

/**
 * IntegracaoCipDaoFactory
 * 
 * @author Jesliel.Rocha
 */
public class OperacionalDaoFactory extends ComumDaoFactory {

    private static OperacionalDaoFactory factory;

    /**
     * M�todo respons�vel por
     * 
     * @return IntegracaoCipDaoFactory
     * 
     */
    public static OperacionalDaoFactory getInstance() {
        if (factory == null) {
            factory = new OperacionalDaoFactory();
            SicoobLoggerPadrao.getInstance(OperacionalDaoFactory.class).debug("************ Instanciou IntegracaoCipDaoFactory ****************");
        }
        return factory;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return ManutencaoMensagemDDABoletoDao
     * 
     */
    public ManutencaoMensagemDDABoletoDao criarManutencaoCipDao() {
        return new ManutencaoMensagemDDABoletoDaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return ArquivoCipDao
     * 
     */
    public ArquivoCipDao criarArquivoCipDao() {
        return new ArquivoCipDaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return
     */
    public MonitoramentoMensagensDDADao criarMonitoramentoMensagensDDADao() {
        return new MonitoramentoMensagensDDADaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return MonitoracaoDao
     * 
     */
    public MonitoracaoDao criarMonitoracaoDao() {
        return new MonitoracaoDaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return BeneficiarioLegadoDao
     * 
     */
    public BeneficiarioLegadoDao criarBeneficiarioLegadoDao() {
        return new BeneficiarioLegadoDaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return OperacaoBeneficiarioDDADao
     * 
     */
    public OperacaoBeneficiarioDDADao criarOperacaoBeneficiarioDDADao() {
        return new OperacaoBeneficiarioDDADaoImpl();
    }

    /**
     * M�todo respons�vel por recuperar a DAO.
     * 
     * @return AgendamentoBoletoDao
     */
    public AgendamentoBoletoDao criarAgendamentoBoletoDao() {
        return new AgendamentoBoletoDaoImpl();
    }

    /**
     * M�todo respons�vel por recuperar a DAO.
     * 
     * @return PagadorEletronicoDDALegadoDao
     */
    public PagadorEletronicoDDALegadoDao criarPagadorEletronicoDDALegadoDao() {
        return new PagadorEletronicoDDALegadoDaoImpl();
    }

    /**
     * M�todo respons�vel por recuperar a DAO.
     * 
     * @return PagadorEletronicoDDADao
     */
    public PagadorEletronicoDDADao criarPagadorEletronicoDDADao() {
        return new PagadorEletronicoDDADaoImpl();
    }

    /**
     * M�todo respons�vel por recuperar a DAO.
     * 
     * @return BoletoDDADaoImpl
     */
    public BoletoDDADao criarBoletoDDADao() {
        return new BoletoDDADaoImpl();
    }

    /**
     * M�todo respons�vel por recuperar a DAO.
     * 
     * @return GradeHorariaDao
     */
    public GradeHorariaDao criarGradeHorariaDao() {
        return new GradeHorariaDaoImpl();
    }

    /**
     * M�todo respons�vel por recuperar a DAO.
     * 
     * @return TipoMensagemDao
     */
    public TipoMensagemDao criarTipoMensagemDao() {
        return new TipoMensagemDaoImpl();
    }

    /**
     * M�todo respons�vel por recuperar a DAO.
     * 
     * @return TipoGradeHorariaDao
     */
    public TipoGradeHorariaDao criarTipoGradeHorariaDao() {
        return new TipoGradeHorariaDaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return TratamentoPendenciaErroDao
     * 
     */
    public TratamentoPendenciaErroDao criaTratamentoPendenciaErroDao() {
        return new TratamentoPendenciaErroDaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return TipoErroCipDao
     * 
     */
    public TipoErroCipDao criaTipoErroCipDao() {
        return new TipoErroCipDaoImpl();
    }

    /**
     * M�todo respons�vel por instanciar a DAO.
     */
    public MensagemDDADao criaMensagemDDADao() {
        return new MensagemDDADaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDAPagadorDao
     * 
     */
    public MensagemDDAPagadorDao criaMensagemDDAPagadorDao() {
        return new MensagemDDAPagadorDaoImpl();
    }

    /**
     * M�todo respons�vel por instanciar a DAO.
     * 
     * @return ContingenciaDao
     * 
     */
    public ContingenciaDao criaContingenciaDao() {
        return new ContingenciaDaoImpl();
    }

    /**
     * @return ArquivoRecebidoDao
     */
    public ArquivoRecebidoDao criaArquivoRecebidoDao() {
        return new ArquivoRecebidoDaoImpl();
    }

    /**
     * @return BeneficiariosAlertaDao
     */
    public BeneficiariosAlertaDao criaBeneficiariosAlertaDao() {
        return new BeneficiariosAlertaDaoImpl();
    }

    /**
     * @return HistoricoMensagemDDADao
     */
    public HistoricoMensagemDDADao criaHistoricoMensagemDDADao() {
        return new HistoricoMensagemDDADaoImpl();
    }

    /**
     * @return ArquivoRecebidoDao
     */
    public ArquivoEnviadoDao criaArquivoEnviadoDao() {
        return new ArquivoEnviadoDaoImpl();
    }

    /**
     * @return AdministrativoDao
     */
    public AdministrativoDao criaAdministrativoDao() {
        return new AdministrativoDaoImpl();
    }

    /**
     * @return EventoTarifavelDDADao
     */
    public EventoTarifavelDDADao criaEventoTarifavelDDADao() {
        return new EventoTarifavelDDADaoImpl();
    }

    /**
     * M�todo respons�vel por retornar a DAO.
     * 
     * @return
     */
    public RateioDDALancamentoDao criaRateioDDALancamentoDao() {
        return new RateioDDALancamentoDaoImpl();
    }

    /**
     * @return RateioTarifasCipDao
     */
    public RateioTarifasCipDao criarRateioTarifasCipDao() {
        return new RateioTarifasCipDaoImpl();
    }

    /**
     * @return RateioTarifasCipDao
     */
    public BoletoLegadoDao criarBoletoLegadoDao() {
        return new BoletoLegadoDaoImpl();
    }

    /**
     * @return RateioDDALancamentoLegadoDao
     */
    public RateioDDALancamentoLegadoDao criarRateioDDALancamentoLegadoDao() {
        return new RateioDDALancamentoLegadoDaoImpl();
    }
}
