package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoDecursoPrazoBaixaOperacionalServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoDecursoPrazoBaixaOperacionalDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;

/**
 * ArquivoDecursoPrazoBaixaOperacionalServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoDecursoPrazoBaixaOperacionalServicoLocal.class })
public class ArquivoDecursoPrazoBaixaOperacionalServicoEJB extends IntegracaoCipServicoEJB implements ArquivoDecursoPrazoBaixaOperacionalServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoDecursoPrazoBaixaOperacionalDao incluirDecursoDao;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoDecursoPrazoBaixaOperacionalServico#processarRetornoMensagemDDA(long, long,
     *      long)
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {
        getLogger().debug("###### Registrando as mensagens ADDA117...");
        getIncluirDecursoDao().incluirADDA117(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);
        getLogger().debug("###### Mensagens ADDA117 registradas.");
    }

    /**
     * @return o atributo incluirDecursoDao
     */
    public ArquivoDecursoPrazoBaixaOperacionalDao getIncluirDecursoDao() {
        return incluirDecursoDao;
    }

}
