/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servico
 * Arquivo:         BeneficiariosAlertaServicoTest.java
 * Data Criação:    Apr 7, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.INTEGER_ZERO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.LONG_UM;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiarioAlertaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.BeneficiariosAlertaServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BeneficiariosAlertaDao;

/**
 * BeneficiariosAlertaServicoTest
 * 
 * @author Danilo.Barros
 */
@RunWith(PowerMockRunner.class)
public class BeneficiariosAlertaServicoTest extends Mockito {

    @Mock
    private BeneficiariosAlertaDao beneficiariosAlertaDao;

    @InjectMocks
    private BeneficiariosAlertaServicoEJB beneficiariosAlertaServicoEJB;

    private List<BeneficiarioAlertaDto> listaBeneficiarioAlertaDtos = new ArrayList<BeneficiarioAlertaDto>();

    /**
     * Método responsável por
     * 
     * @throws java.lang.Exception void
     * 
     */
    @Before
    public void setUp() throws Exception {
        BeneficiarioAlertaDto beneficiarioAlertaDto = new BeneficiarioAlertaDto();
        beneficiarioAlertaDto.setIdBeneficiarioDDA(LONG_UM);
        listaBeneficiarioAlertaDtos.add(beneficiarioAlertaDto);

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.BeneficiariosAlertaServicoEJB#obterInstituicaoBeneficiariosAlerta(java.util.List)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterInstituicaoBeneficiariosAlerta() throws ComumException {
        List<Long> listaIdsBeneficiariosDDA = new ArrayList<Long>();
        listaIdsBeneficiariosDDA.add(LONG_UM);

        when(beneficiariosAlertaDao.obterInstituicaoBeneficiariosAlerta(listaIdsBeneficiariosDDA)).thenReturn(listaBeneficiarioAlertaDtos);
        List<BeneficiarioAlertaDto> retornoListaBeneficiarioAlertaDtos = beneficiariosAlertaServicoEJB.obterInstituicaoBeneficiariosAlerta(listaIdsBeneficiariosDDA);

        verify(beneficiariosAlertaDao, atLeastOnce()).obterInstituicaoBeneficiariosAlerta(anyListOf(Long.class));
        Assert.assertEquals(retornoListaBeneficiarioAlertaDtos.get(INTEGER_ZERO).getIdInstituicao(), listaBeneficiarioAlertaDtos.get(INTEGER_ZERO).getIdInstituicao());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.BeneficiariosAlertaServicoEJB#obterBeneficiariosAlerta(br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterBeneficiariosAlerta() throws ComumException {
        BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto = new BeneficiariosAlertaFiltroDto();
        when(beneficiariosAlertaDao.obterBeneficiariosAlerta(beneficiariosAlertaFiltroDto)).thenReturn(listaBeneficiarioAlertaDtos);
        List<BeneficiarioAlertaDto> retornoListaBeneficiarioAlertaDtos = beneficiariosAlertaServicoEJB.obterBeneficiariosAlerta(beneficiariosAlertaFiltroDto);

        verify(beneficiariosAlertaDao, atLeastOnce()).obterBeneficiariosAlerta(beneficiariosAlertaFiltroDto);
        Assert.assertEquals(retornoListaBeneficiarioAlertaDtos.get(INTEGER_ZERO).getIdInstituicao(), listaBeneficiarioAlertaDtos.get(INTEGER_ZERO).getIdInstituicao());
    }

}
