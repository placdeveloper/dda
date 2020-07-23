package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.CategoriaMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.TipoMensagemServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoMensagemDao;

/**
 * TipoMensagemServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ TipoMensagemServicoLocal.class })
public class TipoMensagemServicoEJB extends OperacionalCrudServicoEJB<TipoMensagemDDA> implements TipoMensagemServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private TipoMensagemDao dao;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private TipoGradeHorariaDao daoTipoGradeHoraria;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected TipoMensagemDao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoMensagemServico#obterTipoMensagem(java.lang.String)
     */
    public TipoMensagemDto obterTipoMensagem(TipoMensagemDDA tipoMensagem) throws ComumException {
        return construirTipoMensagemDto(tipoMensagem.getCodTipoMensagem());
    }

    /**
     * @param codTipoMensagem
     * @return
     * @throws ComumException TipoMensagemDto
     * 
     */
    private TipoMensagemDto construirTipoMensagemDto(String codTipoMensagem) throws ComumException {
        TipoMensagemDto tipoMensagemDto = new TipoMensagemDto();

        carregarListasDto(tipoMensagemDto);
        TipoMensagemDDA tipoMensagemDDA = em.find(TipoMensagemDDA.class, codTipoMensagem);

        tipoMensagemDto.setBolExigeMensagemRetorno(tipoMensagemDDA.getBolExigeMensagemRetorno());
        tipoMensagemDto.setNumPrioridadeEnvio(tipoMensagemDDA.getNumPrioridadeEnvio());
        tipoMensagemDto.setCodTipoMensagem(tipoMensagemDDA.getCodTipoMensagem());
        tipoMensagemDto.setDescTipoMensagem(tipoMensagemDDA.getDescTipoMensagem());

        if (!ObjectUtil.isEmpty(tipoMensagemDDA.getCodTipoArquivoCorrespondente())) {
            tipoMensagemDto.setCodTipoArquivoCorrespondente(tipoMensagemDDA.getCodTipoArquivoCorrespondente());
        }

        if (!ObjectUtil.isNull(tipoMensagemDDA.getTipoGradeHoraria())) {
            tipoMensagemDto.setCodTipoGradeHoraria(tipoMensagemDDA.getTipoGradeHoraria().getCodTipoGradeHoraria());
        }

        if (!ObjectUtil.isNull(tipoMensagemDDA.getCategoriaMensagemDDA())) {
            tipoMensagemDto.setCodCategoriaMensagemDda(tipoMensagemDDA.getCategoriaMensagemDDA().getCodCategoriaMensagemDda());
        }
        return tipoMensagemDto;
    }

    /**
     * @param tipoMensagemDto
     * @throws OperacionalException
     */
    private void carregarListasDto(TipoMensagemDto tipoMensagemDto) throws ComumException {
        List<TipoMensagemDDA> listaArquivoCorrespondente = getDAO().listarTipoMensagemPorCategoria();
        List<TipoGradeHoraria> listaTipoGradeHoraria = daoTipoGradeHoraria.listarCodigosTipoGradeHoraria();
        List<CategoriaMensagemDDA> listaCategoriaMensagem = getDAO().listarCategoriaMensagemDDA();

        tipoMensagemDto.setListaArquivoCorrespondente(listaArquivoCorrespondente);
        tipoMensagemDto.setListaCategoriaMensagem(listaCategoriaMensagem);
        tipoMensagemDto.setListaTipoGradeHoraria(listaTipoGradeHoraria);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoMensagemServico#obterTipoMensagem(java.lang.String)
     */
    public TipoMensagemDto carregarListasTipoMensagem() throws ComumException {
        TipoMensagemDto tipoMensagemDto = new TipoMensagemDto();

        carregarListasDto(tipoMensagemDto);
        return tipoMensagemDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoMensagemServico#listarCategoriaMensagemDDA()
     */
    public List<CategoriaMensagemDDA> listarCategoriaMensagemDDA() throws ComumException {
        return getDAO().listarCategoriaMensagemDDA();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoMensagemServico#incluirTipoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA,
     *      boolean)
     */
    public void incluirTipoMensagemDDA(TipoMensagemDDA tipoMensagemDDA, boolean isAlteracao) throws OperacionalNegocionException, ComumException {
        validarTipoMensagem(tipoMensagemDDA);
        if (getDAO().isExisteEmTipoMensagem(tipoMensagemDDA.getCodTipoMensagem()) && !isAlteracao) {
            throw new OperacionalNegocionException("integracaocip.tipomensagem.jacadastrado", "Cód. Mensagem");
        }
        ajustarDescricao(tipoMensagemDDA);
        em.merge(tipoMensagemDDA);
    }

    /**
     * @param tipoMensagemDDA
     */
    private void ajustarDescricao(TipoMensagemDDA tipoMensagemDDA) {
        tipoMensagemDDA.setDescTipoMensagem(tipoMensagemDDA.getDescTipoMensagem().trim());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoMensagemServico#alterarTipoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA)
     */
    public void alterarTipoMensagemDDA(TipoMensagemDDA tipoMensagemDDA) throws OperacionalNegocionException, ComumException {
        incluirTipoMensagemDDA(tipoMensagemDDA, Boolean.TRUE);
    }

    /**
     * @param tipoMensagemDDA
     * @throws OperacionalException
     * @throws OperacionalNegocionException
     */
    private void validarTipoMensagem(TipoMensagemDDA tipoMensagemDDA) throws OperacionalException, OperacionalNegocionException {
        if (ObjectUtil.isEmpty(tipoMensagemDDA.getCodTipoMensagem())) {
            throw new OperacionalNegocionException("integracaocip.preenchimento.obrigatorio", "Cód. Mensagem");
        } else if (ObjectUtil.isEmpty(tipoMensagemDDA.getDescTipoMensagem())) {
            throw new OperacionalNegocionException("integracaocip.preenchimento.obrigatorio", "Desc. Tipo Mensagem");
        } else if (ObjectUtil.isNull(tipoMensagemDDA.getCategoriaMensagemDDA())) {
            throw new OperacionalNegocionException("integracaocip.preenchimento.obrigatorio", "Categoria");
        } else if (ObjectUtil.isNull(tipoMensagemDDA.getNumPrioridadeEnvio())) {
            throw new OperacionalNegocionException("integracaocip.preenchimento.obrigatorio", "Prioridade");
        } else if (ObjectUtil.isNull(tipoMensagemDDA.getBolExigeMensagemRetorno())) {
            throw new OperacionalNegocionException("integracaocip.preenchimento.obrigatorio", "Exige Retorno");
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoMensagemServico#listarTipoGradeHoraria()
     */
    public List<TipoGradeHoraria> listarTipoGradeHoraria() throws ComumException {
        return daoTipoGradeHoraria.listarCodigosTipoGradeHoraria();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoMensagemServico#removerTipoMensagem(java.lang.String)
     */
    public void removerTipoMensagem(String codTipoMensagemDDA) throws OperacionalNegocionException, ComumException {
        if (dao.isVinculadoArqCorrespondente(codTipoMensagemDDA)) {
            throw new OperacionalNegocionException("integracaocip.tipomensagem.erro.existe.vinculo");
        } else {
            em.remove(em.find(TipoMensagemDDA.class, codTipoMensagemDDA));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoMensagemServico#listarTipoMensagemDDA()
     */
    public List<TipoMensagemDDA> listarTipoMensagemDDA() throws ComumException {
        return getDAO().listarTipoMensagem();
    }

}
