/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         OperacaoBeneficiarioDDASevicoEJB.java
 * Data Criação:    Aug 6, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.OperacaoBeneficiarioDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacaoBeneficiarioDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * OperacaoBeneficiarioDDASevicoEJB
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ OperacaoBeneficiarioDDAServicoLocal.class })
public class OperacaoBeneficiarioDDAServicoEJB extends OperacionalServicoEJB implements OperacaoBeneficiarioDDAServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private OperacaoBeneficiarioDDADao dao;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * 
     * @return OperacaoBeneficiarioDDADao
     * 
     */
    public OperacaoBeneficiarioDDADao getDao() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacaoBeneficiarioDDAServico#validaSituacaoBeneficiarioCIP(java.lang.String)
     */
    public void validaSituacaoBeneficiarioCIP(String numCpfCnpj) throws ComumException, ComumNegocioException {
        debug("********* Validando a situação do beneficiário *********");
        debug("Parâmetro - numCpfCnpj: " + numCpfCnpj);

        if (ObjectUtil.isEmpty(numCpfCnpj)) {
            throw new ComumException("sicoobdda.parametro.nao.informado", "numCpfCnpj");
        } else if (numCpfCnpj.length() != Constantes.TAMANHO_CPF && numCpfCnpj.length() != Constantes.TAMANHO_CNPJ) {
            throw new ComumException("sicoobdda.cpf.cnpj.invalido", Constantes.TAMANHO_CPF, Constantes.TAMANHO_CNPJ);
        }

        if (!permiteRealizarOperacoesCobranca(numCpfCnpj)) {
            throw new ComumNegocioException(MensagemUtil.getString("sicoobdda.alerta.beneficiario.status.alerta"));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacaoBeneficiarioDDAServico#permiteRealizarOperacoesCobranca(java.lang.String)
     */
    public boolean permiteRealizarOperacoesCobranca(String numCPFCNPJ) throws ComumException {
        debug("********* permiteRealizarOperacoesCobranca *********");
        debug("CPF do Beneficiário: " + numCPFCNPJ);
        SituacaoBeneficiarioDDA situacao = getDao().obterSituacaoBeneficiario(numCPFCNPJ);

        // Se a situação for nula ou não estiver inapto;
        return (ObjectUtil.isNull(situacao) || (!situacao.getCodSituacaoBeneficiario().equals(SituacaoBeneficiarioDDA.INAPTO)));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacaoBeneficiarioDDAServico#beneficiarioEstaNaCip(java.lang.String)
     */
    public boolean beneficiarioEstaNaCip(String numCpfCnpj) throws ComumException {
        return getDao().beneficiarioEstaNaCip(numCpfCnpj);
    }

}
