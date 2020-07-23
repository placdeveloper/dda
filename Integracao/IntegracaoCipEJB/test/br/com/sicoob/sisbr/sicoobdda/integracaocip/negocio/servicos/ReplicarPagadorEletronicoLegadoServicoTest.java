/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ReplicarBeneficiarioLegadoServicoTest.java
 * Data Criação:    May 24, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDAPagadorEletronico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ReplicarPagadorEletronicoLegadoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarPagadorEletronicoLegadoDao;

/**
 * ReplicarPagadorEletronicoLegadoServicoTest é responsável por
 * 
 * @author George.santos
 */
@RunWith(MockitoJUnitRunner.class)
public class ReplicarPagadorEletronicoLegadoServicoTest extends Mockito {

	@InjectMocks
	private ReplicarPagadorEletronicoLegadoServicoEJB ejb;

	@Mock
	private ReplicarPagadorEletronicoLegadoDao dao;

	@SuppressWarnings("unused")
	@Mock
	private EntityManager em;

	/**
	 * Método responsável por validar o teste do ProcessarMensagem
	 * 
	 * @throws ComumException
	 */
	@Test
	public void replicarPagadorCIPLegadoIncluirPassou() throws ComumException {
		Assert.assertEquals(Constantes.TESTE_SUCESSO, replicarPagadorCIPLegadoIncluir());
	}

	/**
	 * Método responsável por validar o teste do ProcessarMensagem
	 * 
	 * @throws ComumException
	 */
	@Test
	public void replicarPagadorCIPLegadoAlterarPassou() throws ComumException {
		Assert.assertEquals(Constantes.TESTE_SUCESSO, replicarPagadorCIPLegadoAlterar());
	}

	/**
	 * Método responsável por validar o teste do ProcessarMensagem
	 * 
	 * @throws ComumException
	 */
	@Test
	public void replicarPagadorCIPLegadoExcluirPassou() throws ComumException {
		Assert.assertEquals(Constantes.TESTE_SUCESSO, replicarPagadorCIPLegadoExcluir());
	}

	/**
	 * Método responsável por
	 * 
	 * @return Object
	 * @throws ComumException
	 * 
	 */
	private Object replicarPagadorCIPLegadoIncluir() throws ComumException {
		try {
			when(dao.obter(Constantes.CPF_AUX)).thenReturn(null);
		} catch (BancoobException e) {
			throw new ComumException(e);
		}
		ejb.replicarPagadorCIPLegado(Constantes.CPF_AUX, Boolean.TRUE, Constantes.INTEGER_UM);
		return Constantes.TESTE_SUCESSO;
	}

	/**
	 * Método responsável por
	 * 
	 * @return Object
	 * @throws ComumException
	 * 
	 */
	private Object replicarPagadorCIPLegadoAlterar() throws ComumException {
		try {
			when(dao.obter(Constantes.CPF_AUX)).thenReturn(montarDDAPagadorEletronico());
		} catch (BancoobException e) {
			throw new ComumException(e);
		}
		ejb.replicarPagadorCIPLegado(Constantes.CPF_AUX, Boolean.TRUE, Constantes.INTEGER_UM);
		return Constantes.TESTE_SUCESSO;
	}

	/**
	 * Método responsável por
	 * 
	 * @return Object
	 * @throws ComumException
	 * 
	 */
	private Object replicarPagadorCIPLegadoExcluir() throws ComumException {
		ejb.excluirPagadorCipLegador(Constantes.CPF_AUX, Boolean.TRUE);
		return Constantes.TESTE_SUCESSO;
	}

	/**
	 * Método responsável por montar o objeto DDAPagadorEletronico para os teste.
	 * 
	 * @return
	 */
	private DDAPagadorEletronico montarDDAPagadorEletronico() {
		DDAPagadorEletronico retorno = new DDAPagadorEletronico();

		retorno.setNumCPFCNPJ(Constantes.CPF_AUX);
		retorno.setNumCooperativa(Constantes.INTEGER_UM);
		retorno.setBolSacadoEletronico(Boolean.TRUE);

		return retorno;
	}
}
