/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IBeneficiariosAlerta.java
 * Data Criação:    Feb 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * IBeneficiariosAlerta
 * 
 * @author Danilo.Barros
 */
public interface IBeneficiariosAlerta {

    /**
     * Método responsável por retornar lista dos Bancos Originadores ativos
     * 
     * @param
     * @return RetornoDTO
     * @throws ComumException
     */
    RetornoDTO listarBancosOriginadores() throws ComumException;

    /**
     * Método responsável por retornar lista de Beneficiários em Alerta, conforme parâmetros passados
     * 
     * @param reqDTO
     * @return DadosSelGeralDTO
     * @throws ComumException
     * @throws ComumNegocioException
     */
    DadosSelGeralDTO pesquisarBeneficiariosAlertaPaginada(SelGeralReqDTO reqDTO) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por gerar o relatório SDDA504
     * 
     * @param reqDTO
     * @return RetornoDTO
     * @throws BancoobException
     */
//    /RetornoDTO gerarRelatorioBeneficiariosAlerta(RequisicaoReqDTO reqDTO) throws BancoobException;

}
