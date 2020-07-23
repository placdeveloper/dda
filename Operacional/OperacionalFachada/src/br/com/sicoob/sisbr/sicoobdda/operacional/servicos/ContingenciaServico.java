/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         ContingenciaServico.java
 * Data Criação:    Jan 4, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.GradeContingenciaDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IContingencia;

/**
 * ContingenciaServico
 * 
 * @author Danilo.Barros
 */
@RemoteService
public class ContingenciaServico extends OperacionalFachada implements IContingencia {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IContingencia#incluirContingencia(br.com.bancoob.sisbrweb.dto.RetornoDTO)
     */
    public RetornoDTO incluirContingencia(RequisicaoReqDTO reqDTO) throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        Map<String, Object> dadosDto = (HashMap<String, Object>) reqDTO.getDados();
        Boolean contingenciaHabilitada = getSituacaoContingencia(dadosDto);
        Integer tipoContingencia = getTipoContingencia(dadosDto);
        getContingenciaDelegate().incluirContingencia(new ContingenciaDto(new DateTimeDB(), tipoContingencia.longValue(), getUsuario().getLogin(), null, contingenciaHabilitada),
                CanalEnum.RETAGUARDA.getId());
        retornoDTO.getDados().put("isContingenciaHabilitada", contingenciaHabilitada);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IContingencia#listarHistoricoContingencias(br.com.bancoob.sisbrweb.dto.RetornoDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO listarHistoricoContingencias() throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        List<GradeContingenciaDTO> listarHistoricoContingencias = (List<GradeContingenciaDTO>) ConversorVO.getInstance()
                .converter(getContingenciaDelegate().listarHistoricoContingencias());
        retornoDTO.getDados().put("listarHistoricoContingencias", listarHistoricoContingencias);

        Integer valorContingencia = obterValorInteger(ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP, null);
        retornoDTO.getDados().put("isContingenciaHabilitada", valorContingencia == Constantes.INTEGER_UM);

        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IContingencia#isContingenciaHabilitada()
     */
    public RetornoDTO isContingenciaHabilitada() throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        Integer valorContingencia = obterValorInteger(ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP, null);
        retornoDTO.getDados().put("isContingenciaHabilitada", valorContingencia == Constantes.INTEGER_UM);
        return retornoDTO;
    }

    /**
     * Método
     * 
     * @param dadosDto @return Boolean @throws
     */
    private Boolean getSituacaoContingencia(Map<String, Object> dadosDto) {
        return (Integer) dadosDto.get("contingenciaHabilitada") == Constantes.INTEGER_UM ? true : false;
    }

    /**
     * Método
     * 
     * @param dadosDto @return Long @throws
     */
    private Integer getTipoContingencia(Map<String, Object> dadosDto) {
        return (Integer) dadosDto.get("tipoContingencia");
    }

    /**
     * Método
     * 
     * @param idParametro
     * @param idInstituicao
     * @return Integer
     * @throws ComumException
     */
    private Integer obterValorInteger(Long idParametro, Integer idInstituicao) throws ComumException {
        return getContingenciaDelegate().obterValorInteger(idParametro, idInstituicao);
    }

}
