/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.servicos
 * Arquivo:         ArquivoRecebidoCIPProcessadorServico.java
 * Data Criação:    Apr 19, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.Arrays;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.AbrirArquivoErroCIPNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.AbrirArquivoRetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebidoCIPProcessadorServico;

/**
 * ArquivoRecebidoCIPProcessadorServico é responsável por
 * 
 * @author Adriano.Pinheiro
 */

public class ArquivoRecebidoCIPProcessadorServico extends OperacionalFachada implements IArquivoRecebidoCIPProcessadorServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebidoCIPProcessadorServico#abrirArquivoCIP(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    // TODO - Seu Adriano - refatorar
    public RetornoDTO abrirArquivoCIP(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        String arquivos = (String) dto.getDados().get("listaArquivos");

        if (ObjectUtil.isEmpty(arquivos)) {
            throw new ComumNegocioException("Nenhum arquivo foi informado");
        }

        RetornoDTO retornoDTO = new RetornoDTO();
        AbrirArquivoRetornoDTO abrirArquivoRetornoDTO = new AbrirArquivoRetornoDTO();

        try {
            abrirArquivoRetornoDTO = (AbrirArquivoRetornoDTO) ConversorVO.getInstance()
                    .converter(getArquivoRecebidoCIPProcessadorServicoDelegate().abrirArquivoCIP(Arrays.asList(arquivos.split(";"))));
        } catch (AbrirArquivoErroCIPNegocioException erroCIPNegocioException) {
            abrirArquivoRetornoDTO = (AbrirArquivoRetornoDTO) ConversorVO.getInstance().converter(erroCIPNegocioException.getAbrirArquivoRetornoDto());
            LOG.debug("Falha na abertura do arquivo da CIP [" + abrirArquivoRetornoDTO.getNomeArquivo() + "] Motivo:[" + erroCIPNegocioException.getMessage() + "]");
        } catch (BancoobException bancoobException) {
            LOG.debug("Falha na abertura do arquivo da CIP [" + bancoobException.getMessage() + "]");
        }

        retornoDTO.getDados().put("retorno", abrirArquivoRetornoDTO);

        return retornoDTO;
    }
}
