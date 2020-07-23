/*
 * package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import org.junit.Test; import org.junit.runner.RunWith; import org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.Mockito; import
 * org.mockito.runners.MockitoJUnitRunner;
 * 
 * import br.com.bancoob.excecao.BancoobException; import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException; import
 * br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RangeMensagensStepDto; import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao; import
 * br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA; import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes; import
 * br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job.ProcessarEnvioMensagensJobServicoEJB; import
 * br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao; import br.com.sicoob.sws.api.contexto.ContextoExecucao; import
 * junit.framework.Assert;
 * 
 *//**
    * ProcessarEnvioMensagensJobServicoTest
    * 
    * @author Rafael.Silva
    */
/*
 * @RunWith(MockitoJUnitRunner.class) public class ProcessarEnvioMensagensJobServicoTest extends Mockito {
 * 
 * @InjectMocks private ProcessarEnvioMensagensJobServicoEJB ejb;
 * 
 * @Mock private ParametroDao dao;
 * 
 * @Mock private ProcessamentoSWSDao swsDao;
 * 
 *//**
    * Método responsável por
    * 
    * @throws BancoobException void
    * 
    */
/*
 * @Test public void verificarDependenciasPassou() throws BancoobException { Assert.assertEquals("Passou", verificarDependencias(Constantes.INTEGER_UM,
 * Constantes.LONG_ZERO, Constantes.INTEGER_MIL, Constantes.INTEGER_DEZ)); verificaVerificarDependencias();
 * 
 * }
 * 
 *//**
    * Método responsável por
    * 
    * @throws BancoobException void
    * 
    */
/*
 * @Test public void obterStepsPassou() throws BancoobException { Assert.assertEquals("Passou", obterSteps());
 * 
 * }
 * 
 *//**
    * Método responsável por
    * 
    * @return
    * @throws BancoobException String
    * 
    */
/*
 * private String obterSteps() throws BancoobException { String retorno = null; verificarDependenciasPassou(); List<RangeMensagensStepDto> lista = new
 * ArrayList<RangeMensagensStepDto>(); lista.add(new RangeMensagensStepDto(Constantes.INTEGER_UM, Constantes.INTEGER_MIL)); ejb.obterSteps(new
 * ContextoExecucao()); retorno = "Passou"; return retorno; }
 * 
 *//**
    * Método responsável por
    * 
    * @param qtdMaxRegistrosPermiteEnvioImediato
    * @param qtdRegistrosFila
    * @param qtdMaxRegistrosPorStep
    * @param numPrioridadeEnvio
    * @return
    * @throws ComumException String
    * 
    */
/*
 * private String verificarDependencias(int qtdMaxRegistrosPermiteEnvioImediato, long qtdRegistrosFila, int qtdMaxRegistrosPorStep, int numPrioridadeEnvio)
 * throws ComumException { String retorno = null; try { when(dao.obterValorInteger(ParametroDDA.QTD_MAX_REGISTRO_NA_FILA_PERMITE_ENVIO_IMEDIATO,
 * Constantes.ID_SICOOB)).thenReturn(qtdMaxRegistrosPermiteEnvioImediato); when(dao.obterValorInteger(ParametroDDA.QTD_MAX_REGISTROS_POR_STEP_MOTOR_ENVIO_CIP,
 * Constantes.ID_SICOOB)).thenReturn(1000); when(dao.obterValorInteger(ParametroDDA.NUM_PRIORIDADE_ENVIO_MSG_CIP, Constantes.ID_SICOOB)).thenReturn(10);
 * 
 * ejb.verificarDependencias(new ContextoExecucao()); retorno = "Passou"; } catch (BancoobException e) { retorno = e.getMessage(); } return retorno; }
 * 
 *//**
    * Método responsável por
    * 
    * @throws BancoobException void
    * 
    *//*
       * private void verificaVerificarDependencias() throws BancoobException { verify(dao,
       * times(1)).obterValorInteger(ParametroDDA.QTD_MAX_REGISTRO_NA_FILA_PERMITE_ENVIO_IMEDIATO, Constantes.ID_SICOOB); }
       * 
       * }
       */