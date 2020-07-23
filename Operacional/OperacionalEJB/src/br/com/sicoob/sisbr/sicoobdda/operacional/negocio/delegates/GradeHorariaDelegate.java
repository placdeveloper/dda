/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         GradeHorariaDelegate.java
 * Data Criação:    Aug 14, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.GradeHorariaServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * GradeHorariaDelegate
 * 
 * @author samuell.ramos
 */
@SuppressWarnings("rawtypes")
public class GradeHorariaDelegate extends OperacionalCrudDelegate {

    private GradeHorariaServico gradeHorariaServico;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected GradeHorariaServico localizarServico() {
        if (this.gradeHorariaServico == null) {
            this.gradeHorariaServico = OperacionalServiceLocator.getInstance().localizarGradeHorariaServico();
        }
        return this.gradeHorariaServico;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.GradeHorariaServico#listarTipoGradeHoraria()
     */
    public List<TipoGradeHorariaDto> listarTipoGradeHoraria() throws OperacionalException {
        return localizarServico().listarTipoGradeHoraria();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.GradeHorariaServico#incluirGradeHoraria(br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto)
     */
    public void incluirGradeHoraria(GradeHorariaDto gradeHorariaDto) throws ComumNegocioException, ComumException {
        localizarServico().incluirGradeHoraria(gradeHorariaDto);
    }

    /**
     * @param gradeHorariaDto
     * @return
     * @throws ComumException GradeHorariaDto
     * 
     */
    public GradeHorariaDto obterGradeHoraria(GradeHorariaDto gradeHorariaDto) throws ComumException {
        return localizarServico().obterGradeHoraria(gradeHorariaDto);
    }

    /**
     * @param gradeHoraria
     * @throws ComumException
     * @throws OperacionalNegocionException void
     * 
     */
    public void removerGradeHoraria(GradeHoraria gradeHoraria) throws ComumException, OperacionalNegocionException {
        localizarServico().removerGradeHoraria(gradeHoraria);
    }

    /**
     * 
     * Método responsável por verificar se há tipo de grade personalizada sem grade horaria. Copia a grade padrao CIP
     * 
     * @throws BancoobException void
     * 
     */
    public void cadastrarGradeHorariaSicoob() throws BancoobException {
        localizarServico().cadastrarGradeHoraria();
    }

}
