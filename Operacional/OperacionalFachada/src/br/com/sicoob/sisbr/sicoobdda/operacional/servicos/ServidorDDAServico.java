/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.servicos
 * Arquivo:         ServidorDDAServico.java
 * Data Criação:    2 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ServidorDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ServidorDDAVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IServidorDDAServico;

/**
 * ServidorDDAServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ServidorDDAServico extends OperacionalFachada implements IServidorDDAServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IServidorDDAServico#listarServidorAtivo()
     */
    @Override
    @SuppressWarnings("unchecked")
    public RetornoDTO listarServidorAtivo() throws ComumException {
        List<ServidorDDA> listaServidor = getServidorDDADelegate().listarAtivo();
        List<ServidorDDAVO> listaServidorVO = (List<ServidorDDAVO>) ConversorVO.getInstance().converter(listaServidor);

        return montarListaRetorno(listaServidorVO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IServidorDDAServico#listarServidorDDA()
     */
    @Override
    @SuppressWarnings("unchecked")
    public RetornoDTO listarServidorDDA() throws ComumException {
        List<ServidorDDA> listaServidor = getServidorDDADelegate().listarServidorDDA();
        List<ServidorDDAVO> listaServidorVO = (List<ServidorDDAVO>) ConversorVO.getInstance().converter(listaServidor);

        return montarListaRetorno(listaServidorVO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IServidorDDAServico#pesquisarServidorDDA(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @Override
    @SuppressWarnings("unchecked")
    public RetornoDTO pesquisarServidorDDA(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        ServidorDDAVO servidorVO = (ServidorDDAVO) dto.getDados().get("vo");
        ServidorDDA servidor = (ServidorDDA) ConversorVO.getInstance().converter(servidorVO);

        List<ServidorDDA> listaServidor = getServidorDDADelegate().pesquisarServidorDDA(servidor);
        List<ServidorDDAVO> listaServidorVO = (List<ServidorDDAVO>) ConversorVO.getInstance().converter(listaServidor);

        return montarListaRetorno(listaServidorVO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IServidorDDAServico#alterarSevidorDDA(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @Override
    public void alterarSevidorDDA(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        ServidorDDAVO servidorVO = (ServidorDDAVO) dto.getDados().get("vo");
        ServidorDDA servidor = (ServidorDDA) ConversorVO.getInstance().converter(servidorVO);

        getServidorDDADelegate().alterarServidorDDA(servidor);
    }

    /**
     * Método responsável por
     * 
     * @return ServidorDDADelegate
     * 
     */
    private ServidorDDADelegate getServidorDDADelegate() {
        return ComumFabricaDelegates.getInstance().getServidorDDADelegate();
    }

}
