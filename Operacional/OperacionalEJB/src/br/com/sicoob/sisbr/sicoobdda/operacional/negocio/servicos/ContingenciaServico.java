package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoContingencia;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeContingenciaDto;

public interface ContingenciaServico extends OperacionalCrudServico<HistoricoContingencia> {

    /**
     * M�todo
     * 
     * @param contingenciaDto
     * @return
     * @throws BancoobException
     */
    void incluirContingencia(ContingenciaDto contingenciaDto, Short idCanal) throws BancoobException;

    /**
     * M�todo
     * 
     * @param
     * @return List<GradeContingenciaDto>
     * @throws ComumException
     */
    List<GradeContingenciaDto> listarHistoricoContingencias() throws ComumException;

    /**
     * M�todo
     * 
     * @param idParametro
     * @param idInstituicao
     * @return Integer
     * @throws ComumException
     */
    Integer obterValorInteger(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioHistoricoContingencia(UsuarioBancoobDTO usuario) throws BancoobException;

}