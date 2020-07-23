/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         TipoGradeHorariaDelegate.java
 * Data Criação:    Aug 18, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * TipoGradeHorariaDelegate
 * 
 * @author samuell.ramos
 */
public class TipoGradeHorariaDelegate extends OperacionalDelegate<OperacionalServico> implements TipoGradeHorariaServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected TipoGradeHorariaServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarTipoGradeHorariaServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#listarTipoGradeHorariaPorCodigoSubtipo(java.lang.Short)
     */
    public List<TipoGradeHorariaDto> listarTipoGradeHorariaPorCodigoSubtipo(Short codSubTipoGrade) throws OperacionalException {
        return localizarServico().listarTipoGradeHorariaPorCodigoSubtipo(codSubTipoGrade);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#obterTipoGradeHoraria(java.lang.String)
     */
    public TipoGradeHorariaDto obterTipoGradeHoraria(String codTipoGradeHoraria) throws ComumException {
        return localizarServico().obterTipoGradeHoraria(codTipoGradeHoraria);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalNegocionException
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#incluirTipoGradeHoraria(br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto)
     */
    public Boolean incluirTipoGradeHoraria(TipoGradeHorariaDto tipoGradeHorariaDto) throws OperacionalException, OperacionalNegocionException {
        return localizarServico().incluirTipoGradeHoraria(tipoGradeHorariaDto);

    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#apagarTipoGradeHoraria(br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria)
     */
    public void apagarTipoGradeHoraria(TipoGradeHoraria tipoGradeHoraria) throws OperacionalNegocionException, ComumException {
        localizarServico().apagarTipoGradeHoraria(tipoGradeHoraria);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalNegocionException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#alterarTipoGradeHoraria(br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto)
     */
    public void alterarTipoGradeHoraria(TipoGradeHorariaDto tipoGradeHorariaDto) throws OperacionalException, OperacionalNegocionException {
        localizarServico().alterarTipoGradeHoraria(tipoGradeHorariaDto);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#isExisteTipoGradeHoraria(java.lang.String)
     */
    public boolean isExisteTipoGradeHoraria(String codTipoGradeHoraria) throws ComumException {
        return localizarServico().isExisteTipoGradeHoraria(codTipoGradeHoraria);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico#listarTipoGradeHorariaSubtipoGrade()
     */
    public TipoGradeHorariaDto listarTipoGradeHorariaSubtipoGrade() throws ComumException {
        return localizarServico().listarTipoGradeHorariaSubtipoGrade();
    }

}
