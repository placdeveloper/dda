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

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoExclusaoTerceiroAutorizadoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;

/**
 * ArquivoExclusaoTerceiroAutorizadoServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class ArquivoExclusaoTerceiroAutorizadoServicoTest extends Mockito {

    @InjectMocks
    private ArquivoExclusaoTerceiroAutorizadoServicoEJB ejb;

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
        List<MensagemDDATerceiroAut> lista = new ArrayList<MensagemDDATerceiroAut>();

        lista.add(getMensagemDDATerceiroAutorizado());

        when(dao.listarMensagemDDATerceiroAutorizadoLogEnvioArquivo(Constantes.LONG_UM)).thenReturn(lista);
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

    private MensagemDDATerceiroAut getMensagemDDATerceiroAutorizado() {
        MensagemDDATerceiroAut mensagemDDATerceiroAut = new MensagemDDATerceiroAut();
        mensagemDDATerceiroAut.setId(Constantes.LONG_UM);
        mensagemDDATerceiroAut.setNumIdentificadorBoletoCip(Constantes.LONG_UM);

        mensagemDDATerceiroAut.setNumIdentificadorTerceiro(Constantes.LONG_UM);
        mensagemDDATerceiroAut.setNumRefAtualTerceiro(Constantes.LONG_UM);

        mensagemDDATerceiroAut.setCodTipoPessoaAutorizador(TipoPessoaEnum.PF.getCodDominioCip());
        mensagemDDATerceiroAut.setNumCpfCnpjAutorizador(Constantes.CPF_AUX);

        mensagemDDATerceiroAut.setCodTipoPessoaTerceiro(TipoPessoaEnum.PF.getCodDominioCip());
        mensagemDDATerceiroAut.setNumCpfCnpjTerceiro(Constantes.CPF_AUX);
        return mensagemDDATerceiroAut;
    }
}
