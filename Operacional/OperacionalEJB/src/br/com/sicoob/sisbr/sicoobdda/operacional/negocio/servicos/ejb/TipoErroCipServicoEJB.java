/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         TipoErroCipServicoEJB.java
 * Data Criação:    Sep 22, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.TipoErroCipServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoErroCipDao;

/**
 * TipoErroCipServicoEJB é responsável por 
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local(TipoErroCipServicoLocal.class)
public class TipoErroCipServicoEJB extends OperacionalCrudServicoEJB<TipoErroCip> implements TipoErroCipServicoLocal {

    @SuppressWarnings("unused")
    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private TipoErroCipDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected TipoErroCipDao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoErroCipServico#obterTipoErro(java.lang.Long)
     */
    public TipoErroCip obterTipoErro(String codTipoErro) throws OperacionalNegocionException {
        validaCodTipoErro(codTipoErro);
        return getDAO().obterTipoErro(codTipoErro);
    }

    /**
     * {@inheritDoc}
     *
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoErroCipServico#incluirTipoErro(br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip)
     */
    public TipoErroCip incluirTipoErro(TipoErroCip tipoErro) throws BancoobException {
        validaTipoErro(tipoErro);
        validaTipoErroExistente(tipoErro.getCodTipoErro());
        incluir(tipoErro);
        return tipoErro;
    }

    /**
     * {@inheritDoc}
     *
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoErroCipServico#alterarTipoErro(br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip)
     */
    public TipoErroCip alterarTipoErro(TipoErroCip tipoErro) throws BancoobException {
        validaTipoErro(tipoErro);
        alterar(tipoErro);
        return tipoErro;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws BancoobException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoErroCipServico#excluirTipoErro(java.lang.Long)
     */
    public void excluirTipoErro(String codTipoErro) throws BancoobException {
        validaCodTipoErro(codTipoErro);
        validaTipoErroMensagemDDA(codTipoErro);
        excluir(codTipoErro);
    }

    /**
     * Método responsável por
     * 
     * @param codTipoErro
     * @throws OperacionalNegocionException void
     * 
     */
    private void validaCodTipoErro(String codTipoErro) throws OperacionalNegocionException {
        if (ObjectUtil.isEmpty(codTipoErro)) {
            throw new OperacionalNegocionException("integracaocip.erro.codtipoerro.nao.informado");
        }
    }

    /**
     * Método responsável por
     * 
     * @param tipoErro void
     * @throws OperacionalNegocionException
     * 
     */
    private void validaTipoErro(TipoErroCip tipoErro) throws OperacionalNegocionException {
        if (ObjectUtil.isNull(tipoErro)) {
            throw new OperacionalNegocionException("integracaocip.erro.incluir.tipoerro.null");
        } 
        validaCodTipoErro(tipoErro.getCodTipoErro());
        if (ObjectUtil.isEmpty(tipoErro.getDescTipoErro())) {
            throw new OperacionalNegocionException("integracaocip.erro.incluir.desctipoerro.invalida");
        }
    }

    /**
     * Método responsável por
     * 
     * @param codTipoErro
     * @throws OperacionalNegocionException void
     * 
     */
    private void validaTipoErroExistente(String codTipoErro) throws OperacionalNegocionException {
        TipoErroCip tipoErroExistente = null;
        try {
            tipoErroExistente = dao.obterTipoErro(codTipoErro);
        } catch (OperacionalNoResultException e) {
            getLogger().debug(e.getMessage());
        }
        if (!ObjectUtil.isNull(tipoErroExistente)) {
            throw new OperacionalNegocionException("integracaocip.erro.incluir.tipoerro.existente");
        }
    }

    /**
     * Método responsável por
     * 
     * @param codTipoErro
     * @throws OperacionalNegocionException void
     * 
     */
    private void validaTipoErroMensagemDDA(String codTipoErro) throws OperacionalNegocionException {
        if (getDAO().existeMensagemVinculoTipoErro(codTipoErro)) {
            throw new OperacionalNegocionException("integracaocip.erro.excluir.tipoerro.vinculado.mensagem.dda");
        }
    }
}
