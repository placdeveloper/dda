/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ConsultarBeneficiarioServico.java
 * Data Cria��o:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;

/**
 * ConsultarBeneficiarioServico
 * 
 * @author Rafael.Silva
 */
public interface ConsultarBeneficiarioServico extends OperacionalServico {

    /**
     * M�todo respons�vel por processar e enviar mensagem a cip de consulta de benefici�rio por cnpjCpfBeneficiario
     * 
     * @param tipoPessoaBeneficiarioEnum
     * @param cnpjCpfBeneficiario
     * @param dataMovimento
     * @throws ComumException void
     * 
     */
    void processarConsultaBeneficiarioPorCnpjCpf(TipoPessoaEnum tipoPessoaBeneficiarioEnum, String cnpjCpfBeneficiario) throws ComumException, ComumNegocioException;

}
