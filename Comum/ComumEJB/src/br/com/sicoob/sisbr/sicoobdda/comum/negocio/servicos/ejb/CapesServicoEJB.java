package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.CapesServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.CapesDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * CapesServicoEJB é responsável
 * 
 * @author george.santos
 */
@Stateless
@Local(CapesServicoLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CapesServicoEJB extends ComumCrudServicoEJB<SicoobDDAEntidade> implements CapesServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private CapesDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    public CapesDao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CapesServico#obterPessoa(java.lang.String, java.lang.Integer)
     */
    public PessoaDto obterPessoa(String cpfCnpj, Integer idInstituicao) throws ComumException {
        return getDAO().obterPessoa(cpfCnpj, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CapesServico#obterPessoa(java.lang.Long, java.lang.Integer)
     */
    @Override
    public PessoaDto obterPessoa(Long idPessoa, Integer idInstituicao) throws ComumException {

        PessoaDto pessoa = getDAO().obterPessoa(idPessoa, idInstituicao);

        // TODO ADRIANO configurar a mensagem no arquivo de propriedades com a mensagem apropriada
        if (ObjectUtil.isNull(pessoa)) {
            throw new ComumException("Pessoa não encontrada no CAPES");
        }

        return pessoa;
    }

}
