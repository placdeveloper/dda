package br.com.sicoob.sisbr.sicoobdda.comumfachada.iface;

import java.sql.SQLException;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * ICentraisSingularesFachada � respons�vel por conter os metodos da CentraisSingularesFachada
 * 
 * @author Daniel.Moraes
 */
public interface ICentraisSingularesFachada {

    /**
     * M�todo respons�vel por listar as singulares
     * 
     * @param id
     * @return
     * @throws ComumException
     * @throws SQLException
     */
    RetornoDTO listarSingulares(RequisicaoReqDTO dto) throws ComumException, SQLException;

    /**
     * M�todo respons�vel por listar as unidades instituicoes
     * 
     * @param id
     * @return
     * @throws ComumException
     * @throws SQLException
     */
    RetornoDTO listarUnidades(RequisicaoReqDTO dto) throws ComumException, SQLException;

    /**
     * M�todo respons�vel por carregar as combos
     * 
     * @return
     * @throws ComumException
     * @throws SQLException
     */
    RetornoDTO listarCentralSingularUnidade(RequisicaoReqDTO dto) throws ComumException, SQLException;
}
