/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IBeneficiariosAlerta.java
 * Data Cria��o:    Feb 17, 2017
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
     * M�todo respons�vel por retornar lista dos Bancos Originadores ativos
     * 
     * @param
     * @return RetornoDTO
     * @throws ComumException
     */
    RetornoDTO listarBancosOriginadores() throws ComumException;

    /**
     * M�todo respons�vel por retornar lista de Benefici�rios em Alerta, conforme par�metros passados
     * 
     * @param reqDTO
     * @return DadosSelGeralDTO
     * @throws ComumException
     * @throws ComumNegocioException
     */
    DadosSelGeralDTO pesquisarBeneficiariosAlertaPaginada(SelGeralReqDTO reqDTO) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por gerar o relat�rio SDDA504
     * 
     * @param reqDTO
     * @return RetornoDTO
     * @throws BancoobException
     */
//    /RetornoDTO gerarRelatorioBeneficiariosAlerta(RequisicaoReqDTO reqDTO) throws BancoobException;

}
