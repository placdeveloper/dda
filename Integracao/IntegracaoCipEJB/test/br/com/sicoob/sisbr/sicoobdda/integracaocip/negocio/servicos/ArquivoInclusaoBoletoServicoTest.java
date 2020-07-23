/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         TratamentoPendenciaErroServicoTest.java
 * Data Criação:    Sep 20, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

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
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoInclusaoBoletoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import junit.framework.Assert;

/**
 * TratamentoPendenciaErroServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class ArquivoInclusaoBoletoServicoTest extends Mockito {

    @InjectMocks
    private ArquivoInclusaoBoletoServicoEJB ejb;

    @Mock
    private BoletoCipDao dao;

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
        List<MensagemDDABoleto> lista = new ArrayList<MensagemDDABoleto>();

        lista.add(getMensagemDDABoleto());

        when(dao.listarMensagemDDABoletoLogEnvioArquivo(Constantes.LONG_UM)).thenReturn(lista);
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

    private MensagemDDABoleto getMensagemDDABoleto() {
        MensagemDDABoleto mensagemDDABoleto = new MensagemDDABoleto();
        mensagemDDABoleto.setId(Constantes.LONG_UM);
        mensagemDDABoleto.setCodTipoPessoaPagador(TipoPessoaEnum.PF.getDescTipoPessoa());
        mensagemDDABoleto.setNumCpfCnpjBeneficiario(Constantes.CPF_AUX);
        mensagemDDABoleto.setNumCpfCnpjPagador(Constantes.CPF_AUX);
        mensagemDDABoleto.setIdCarteira(Constantes.INTEGER_DEZ);
        mensagemDDABoleto.setIdEspecieDocumento(Constantes.INTEGER_DEZ);
        mensagemDDABoleto.setDataEmissao(Constantes.DATE_TIME_DB_AUX);
        mensagemDDABoleto.setCodTipoPagamento(Constantes.INTEGER_DEZ);
        mensagemDDABoleto.setBolTituloNegociado(Boolean.TRUE);
        mensagemDDABoleto.setBolBloqueioPagamento(Boolean.TRUE);
        mensagemDDABoleto.setBolPagamentoParcial(Boolean.TRUE);

        return mensagemDDABoleto;
    }
}
