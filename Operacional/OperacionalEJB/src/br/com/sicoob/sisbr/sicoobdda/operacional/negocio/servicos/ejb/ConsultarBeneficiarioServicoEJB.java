/**
 * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Criacao:    May 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemConsultaBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.ConsultarBeneficiarioServicoLocal;

/**
 * CadastrarBeneficiarioServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ ConsultarBeneficiarioServicoLocal.class })
public class ConsultarBeneficiarioServicoEJB extends OperacionalServicoEJB implements ConsultarBeneficiarioServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoCipDao dao;

    @SuppressWarnings("unused")
    private MensagemConsultaBeneficiarioDelegate mensagemConsultaBeneficiarioDelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemConsultaBeneficiarioDelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.IntegracaoCipServicoEJB.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return the dao
     */
    public ArquivoCipDao getDao() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultarBeneficiarioServico#processarConsultaBeneficiarioPorCnpjCpf(br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum,
     *      java.lang.String)
     */
    public void processarConsultaBeneficiarioPorCnpjCpf(TipoPessoaEnum tipoPessoaBeneficiarioEnum, String cnpjCpfBeneficiario) throws ComumException, ComumNegocioException {
        // TODO: Implementar gravar mensagem de consulta.
    }

}
