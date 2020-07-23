package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import javax.persistence.EntityManager;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemConsultarHorariosDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0401.DDA0401R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0401.GrupoDDA0401R1GrdHrioComplexType;

/**
 * MensagemInclusaoTerceiroAutorizadoServicoTest é responsável por
 * 
 * @author George.santos
 */
@RunWith(MockitoJUnitRunner.class)
public class MensagemConsultarHorariosDDAServicoTest extends Mockito {

    @InjectMocks
    private MensagemConsultarHorariosDDAServicoEJB ejb;

    @Mock
    private EntityManager em;

    @Mock
    private IntegracaoCipDao dao;

    @Mock
    private MensagemDDADao mensagemDDADao;

    /**
     * Método responsável por
     */
    @Test
    public void validarProcessarMensagemPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, ProcessarMensagem(true));
    }

    /**
     * Método responsável por
     */
    @Test
    public void processarMensagemFalhou() {
        Assert.assertEquals("integracaocip.erro.xml.mensagem.invalido", ProcessarMensagem(false));
    }

    /**
     * Método responsável por
     */
    @Test
    public void validarProcessarRetornoMensagemPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDA());
    }

    /**
     * Método responsável por executar as rotinas do teste do processarMensagem
     * 
     * @return
     */
    public String ProcessarMensagem(boolean preencherXML) {
        when(dao.obterMensagemDDA(Constantes.LONG_UM)).thenReturn(getMensagemDDA(preencherXML));

        try {
            ejb.processarMensagem(Constantes.LONG_UM);
        } catch (Exception e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param preencherXML
     * @return
     */
    private MensagemDDA getMensagemDDA(boolean preencherXML) {
        MensagemDDA mensagemDDA = new MensagemDDA();
        mensagemDDA.setId(Constantes.LONG_UM);
        mensagemDDA.setDataMovimento(Constantes.DATE_TIME_DB_AUX);
        if (preencherXML) {
            mensagemDDA.setXmlMensagem("xmlMensagem");
        }
        return mensagemDDA;
    }

    /**
     * Método responsável por executar as rotinas do teste do processarMensagem
     * 
     * @return
     */
    public String processarRetornoMensagemDDA() {
        try {
            ejb.processarRetornoMensagemDDA(getDDA0401R1ComplexType());

            verify(mensagemDDADao, times(1)).excluirGradeHoraria(any(DateTimeDB.class), any(String.class));
            verify(em, times(1)).persist(any(GradeHoraria.class));

            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            e.printStackTrace();
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Método responsável por
     * 
     * @return DDA0401R1ComplexType
     * @throws DatatypeConfigurationException
     * 
     */
    private DDA0401R1ComplexType getDDA0401R1ComplexType() throws DatatypeConfigurationException {
        DDA0401R1ComplexType dDA0401R1ComplexType = new DDA0401R1ComplexType();
        dDA0401R1ComplexType.setCodMsg(TipoMensagemDDA.DDA0401R1);
        dDA0401R1ComplexType.setDtRef(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                DataUtil.converterDateToString(Constantes.DATE_AUX, DataCipUtil.FORMATO_LONGO_DATA_BACEN)));
        dDA0401R1ComplexType.getGrupoDDA0401R1GrdHrio().add(getGrupoDDA0401R1GrdHrioComplexType());

        return dDA0401R1ComplexType;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws DatatypeConfigurationException
     */
    private GrupoDDA0401R1GrdHrioComplexType getGrupoDDA0401R1GrdHrioComplexType() throws DatatypeConfigurationException {
        GrupoDDA0401R1GrdHrioComplexType grupoDDA0401R1GrdHrioComplexType = new GrupoDDA0401R1GrdHrioComplexType();

        grupoDDA0401R1GrdHrioComplexType.setCodGrd(TipoMensagemDDA.DDA0401R1);
        grupoDDA0401R1GrdHrioComplexType.setTpHrio(Constantes.TIPO_HORARIO_EVENTUAL);
        grupoDDA0401R1GrdHrioComplexType.setDtHrAbert(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                DataUtil.converterDateToString(Constantes.DATE_AUX, DataCipUtil.FORMATO_LONGO_DATA_BACEN)));
        grupoDDA0401R1GrdHrioComplexType.setDtHrFcht(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                DataUtil.converterDateToString(Constantes.DATE_AUX, DataCipUtil.FORMATO_LONGO_DATA_BACEN)));

        return grupoDDA0401R1GrdHrioComplexType;
    }
}
