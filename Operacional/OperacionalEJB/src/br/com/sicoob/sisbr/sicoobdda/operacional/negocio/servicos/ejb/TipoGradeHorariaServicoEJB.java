package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SubTipoGrade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.TipoGradeHorariaServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao;

/**
 * TipoGradeHorariaServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ TipoGradeHorariaServicoLocal.class })
public class TipoGradeHorariaServicoEJB extends OperacionalServicoEJB implements TipoGradeHorariaServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private TipoGradeHorariaDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacionalServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#listarTipoGradeHorariaPorCodigoSubtipo(java.lang.Short)
     */
    public List<TipoGradeHorariaDto> listarTipoGradeHorariaPorCodigoSubtipo(Short codSubTipoGrade) throws OperacionalException {
        return dao.listarTipoGradeHorariaPorCodigoSubtipo(codSubTipoGrade);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#obterTipoGradeHoraria(java.lang.String)
     */
    public TipoGradeHorariaDto obterTipoGradeHoraria(String codTipoGradeHoraria) throws ComumException {
        TipoGradeHoraria tipoGradeHoraria = dao.obterTipoGradeHoraria(codTipoGradeHoraria);
        List<TipoGradeHoraria> listaTipoGradeHoraria = dao.listarTipoGradeHorariaDDA();
        List<SubTipoGrade> listaSubTipoGrade = dao.listarSubTipoGrade();

        TipoGradeHorariaDto tipoGradeHorariaDto = new TipoGradeHorariaDto();
        if (!ObjectUtil.isNull(tipoGradeHoraria.getTipoGradeHorariaOrigem())) {
            tipoGradeHorariaDto.setCodTipoGradeHorariaOrigem(tipoGradeHoraria.getTipoGradeHorariaOrigem().getCodTipoGradeHoraria());
        }
        if (!ObjectUtil.isNull(tipoGradeHoraria.getSubTipoGrade())) {
            tipoGradeHorariaDto.setDescSubTipoGrade(tipoGradeHoraria.getSubTipoGrade().getDescSubTipoGrade());
            tipoGradeHorariaDto.setCodSubTipoGrade(tipoGradeHoraria.getSubTipoGrade().getCodSubTipoGrade());
        }
        tipoGradeHorariaDto.setCodTipoGradeHoraria(tipoGradeHoraria.getCodTipoGradeHoraria());
        tipoGradeHorariaDto.setDescTipoGradeHoraria(tipoGradeHoraria.getDescTipoGradeHoraria());
        tipoGradeHorariaDto.setListaCodigoTipoGradeHoraria(listaTipoGradeHoraria);
        tipoGradeHorariaDto.setListaSubTipoGrade(listaSubTipoGrade);

        return tipoGradeHorariaDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalNegocionException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#incluirTipoGradeHoraria(br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto)
     */
    public Boolean incluirTipoGradeHoraria(TipoGradeHorariaDto tipoGradeHorariaDto) throws OperacionalException, OperacionalNegocionException {
        validarTipoGradeHoraria(tipoGradeHorariaDto);
        TipoGradeHoraria tipoGradeHoraria = construirObjetoTipoGradeHoraria(tipoGradeHorariaDto);
        getEm().persist(tipoGradeHoraria);
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalNegocionException
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#apagarTipoGradeHoraria(br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria)
     */
    public void apagarTipoGradeHoraria(TipoGradeHoraria tipoGradeHoraria) throws OperacionalNegocionException, ComumException {
        if (isExisteDependencia(tipoGradeHoraria)) {
            throw new OperacionalNegocionException("integracaocip.tipomensagem.erro.existe.dependencia");
        }
        getEm().remove(getEm().find(TipoGradeHoraria.class, tipoGradeHoraria.getCodTipoGradeHoraria()));
    }

    /**
     * Método responsável por
     * 
     * @param tipoGradeHoraria
     * @return
     * @throws ComumException boolean
     * 
     */
    private boolean isExisteDependencia(TipoGradeHoraria tipoGradeHoraria) throws ComumException {
        Boolean isExisteEmGradeHoraria = dao.isExisteEmGradeHoraria(tipoGradeHoraria.getCodTipoGradeHoraria());
        Boolean isExisteEmTipoMensagem = dao.isExisteEmTipoMensagem(tipoGradeHoraria.getCodTipoGradeHoraria());
        Boolean isExisteEmGradeOrigem = dao.isExisteEmGradeOrigem(tipoGradeHoraria.getCodTipoGradeHoraria());

        return isExisteEmGradeHoraria || isExisteEmTipoMensagem || isExisteEmGradeOrigem;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalNegocionException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#alterarTipoGradeHoraria(br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto)
     */
    public void alterarTipoGradeHoraria(TipoGradeHorariaDto tipoGradeHorariaDto) throws OperacionalException, OperacionalNegocionException {
        validarTipoGradeHoraria(tipoGradeHorariaDto);
        TipoGradeHoraria tipoGradeHoraria = construirObjetoTipoGradeHoraria(tipoGradeHorariaDto);
        getEm().merge(tipoGradeHoraria);
    }

    /**
     * Método responsável por
     * 
     * @param tipoGradeHorariaDto
     * @return
     */
    private TipoGradeHoraria construirObjetoTipoGradeHoraria(TipoGradeHorariaDto tipoGradeHorariaDto) {
        TipoGradeHoraria tipoGradeHoraria = new TipoGradeHoraria();

        tipoGradeHoraria.setCodTipoGradeHoraria(tipoGradeHorariaDto.getCodTipoGradeHoraria());
        tipoGradeHoraria.setDescTipoGradeHoraria(tipoGradeHorariaDto.getDescTipoGradeHoraria());

        SubTipoGrade subTipoGrade = new SubTipoGrade();
        subTipoGrade.setCodSubTipoGrade(tipoGradeHorariaDto.getCodSubTipoGrade());
        tipoGradeHoraria.setSubTipoGrade(subTipoGrade);
        tipoGradeHoraria.setDataHoraAberturaPadrao(new DateTimeDB());
        tipoGradeHoraria.setDataHoraFechamentoPadrao(new DateTimeDB());

        if (!ObjectUtil.isEmpty(tipoGradeHorariaDto.getCodTipoGradeHorariaOrigem())) {
            TipoGradeHoraria tipoGradeHorariaOrigem = new TipoGradeHoraria();
            tipoGradeHorariaOrigem.setCodTipoGradeHoraria(tipoGradeHorariaDto.getCodTipoGradeHorariaOrigem());
            tipoGradeHoraria.setTipoGradeHorariaOrigem(tipoGradeHorariaOrigem);
        }

        return tipoGradeHoraria;
    }

    /**
     * Método responsável por
     * 
     * @param tipoGradeHorariaDto
     * @throws OperacionalException
     */
    private void validarTipoGradeHoraria(TipoGradeHorariaDto tipoGradeHorariaDto) throws OperacionalNegocionException {
        if (ObjectUtil.isEmpty(tipoGradeHorariaDto.getCodTipoGradeHoraria())) {
            throw new OperacionalNegocionException("integracaocip.preenchimento.obrigatorio", "Código Tipo Grade Horária");
        } else if (ObjectUtil.isEmpty(tipoGradeHorariaDto.getDescTipoGradeHoraria())) {
            throw new OperacionalNegocionException("integracaocip.preenchimento.obrigatorio", "Descrição");
        } else if (ObjectUtil.isEmpty(tipoGradeHorariaDto.getCodSubTipoGrade())) {
            throw new OperacionalNegocionException("integracaocip.preenchimento.obrigatorio", "Subtipo");
        } else if (tipoGradeHorariaDto.getCodSubTipoGrade().equals(SubTipoGrade.GRADE_SICOOB) && ObjectUtil.isEmpty(tipoGradeHorariaDto.getCodTipoGradeHorariaOrigem())) {
            throw new OperacionalNegocionException("integracaocip.preenchimento.obrigatorio", "Grade Origem");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#isExisteTipoGradeHoraria(java.lang.String)
     */
    public boolean isExisteTipoGradeHoraria(String codTipoGradeHoraria) throws ComumException {
        return !ObjectUtil.isEmpty(dao.obterCountTipoGradeHoraria(codTipoGradeHoraria));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#listarTipoGradeHorariaSubtipoGrade()
     */
    public TipoGradeHorariaDto listarTipoGradeHorariaSubtipoGrade() throws ComumException {
        TipoGradeHorariaDto tipoGradeHorariaDto = new TipoGradeHorariaDto();
        tipoGradeHorariaDto.setListaSubTipoGrade(dao.listarSubTipoGrade());
        tipoGradeHorariaDto.setListaCodigoTipoGradeHoraria(dao.listarTipoGradeHorariaDDA());
        return tipoGradeHorariaDto;
    }

}
