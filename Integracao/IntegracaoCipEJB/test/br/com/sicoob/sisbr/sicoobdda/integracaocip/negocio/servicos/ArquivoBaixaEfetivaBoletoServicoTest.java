/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         TratamentoPendenciaErroServicoTest.java
 * Data Cria��o:    Sep 20, 2016
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
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoBaixaEfetivaBoletoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaEfetivaDao;
import junit.framework.Assert;

/**
 * TratamentoPendenciaErroServicoTest � respons�vel por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class ArquivoBaixaEfetivaBoletoServicoTest extends Mockito {

    @InjectMocks
    private ArquivoBaixaEfetivaBoletoServicoEJB ejb;

    @Mock
    private BoletoCipDao dao;

    @SuppressWarnings("unused")
    @Mock
    private MensagemBaixaEfetivaDao mensagemDao;

    /**
     * M�todo respons�vel por void
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

        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setNumIdentificadorBoletoCip(Constantes.LONG_UM);
        boletoDDA.setNumRefAtualCadBoleto(Constantes.LONG_UM);

        when(dao.listarMensagemDDABoletoLogEnvioArquivo(Constantes.LONG_UM)).thenReturn(lista);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterSISARQ(Constantes.LONG_UM));
    }

    /**
     * M�todo respons�vel por
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

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDABoleto
     * 
     */
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
        mensagemDDABoleto.setNumCodigoBarra(Constantes.STRING_NUMERO_10);
        mensagemDDABoleto.setNumCodBarrasCampoLivre(Constantes.STRING_NUMERO_10);
        return mensagemDDABoleto;
    }
}
