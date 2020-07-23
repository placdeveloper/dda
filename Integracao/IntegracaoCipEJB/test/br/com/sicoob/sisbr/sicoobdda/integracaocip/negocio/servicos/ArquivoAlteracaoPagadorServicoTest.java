/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         TratamentoPendenciaErroServicoTest.java
 * Data Criação:    Sep 20, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoAlteracaoPagadorServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import junit.framework.Assert;

/**
 * ArquivoAlteraPagadorServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class ArquivoAlteracaoPagadorServicoTest extends Mockito {

    @InjectMocks
    private ArquivoAlteracaoPagadorServicoEJB ejb;

    @Mock
    private PagadorCipDao dao;

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     * 
     * @throws OperacionalException
     * 
     */
    @Test
    public void obterSISARQPassou() throws ComumNegocioException, ComumException {
        List<MensagemDDAPagador> lista = new ArrayList<MensagemDDAPagador>();

        lista.add(getMensagemDDAPagador());

        when(dao.listarMensagemDDAPagadorLogEnvioArquivo(Constantes.LONG_UM)).thenReturn(lista);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterSISARQ(Constantes.LONG_UM));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws OperacionalException
     * 
     */
    private String obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumNegocioException, ComumException {
        ejb.obterSISARQ(idLogEnvioArquivoDDA);
        return Constantes.TESTE_SUCESSO;
    }

    private MensagemDDAPagador getMensagemDDAPagador() {
        MensagemDDAPagador mensagemDDAPagador = new MensagemDDAPagador();
        mensagemDDAPagador.setId(Constantes.LONG_UM);
        mensagemDDAPagador.setCodTipoPessoaPagador(TipoPessoaEnum.PF.getDescTipoPessoa());
        mensagemDDAPagador.setNumCpfCnpjPagador(Constantes.CPF_AUX);

        MensagemDDAPagadorConta mensagemDDAPagadorConta = new MensagemDDAPagadorConta();
        mensagemDDAPagadorConta.setNumAgencia(Constantes.INTEGER_UM);
        mensagemDDAPagadorConta.setNumContaCorrente(BigDecimal.ONE);
        mensagemDDAPagadorConta.setDataHoraAdesao(Constantes.DATE_TIME_DB_AUX);
        mensagemDDAPagador.setListaMensagemDDAPagadorConta(new ArrayList<MensagemDDAPagadorConta>());
        mensagemDDAPagador.getListaMensagemDDAPagadorConta().add(mensagemDDAPagadorConta);
        return mensagemDDAPagador;
    }
}
