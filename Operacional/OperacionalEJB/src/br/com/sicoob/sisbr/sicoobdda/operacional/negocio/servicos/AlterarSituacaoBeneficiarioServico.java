/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         AlterarSituacaoBeneficiarioServico.java
 * Data Cria��o:    May 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlteraSituacaoBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RetornoAlterarSituacaoBeneficiarioEnum;

/**
 * AlterarSituacaoBeneficiarioServico
 * 
 * @author Rafael.Silva
 */
public interface AlterarSituacaoBeneficiarioServico extends OperacionalServico {

    /**
     * M�todo respons�vel por criar e enviar o arquivo de altera��o de situa��o de benefici�rio. Esta funcionalidade server para avisar a cip que o Sicoob
     * alterou uma situa��o de um de seus benefici�rios.
     * 
     * @param alteraSitBeneficiarioDto
     * @return
     * @throws ComumException void
     * 
     */
    public RetornoAlterarSituacaoBeneficiarioEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioDto alteraSitBeneficiarioDto, Short idCanal)
            throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por alterar a situa��o do benefici�rio. Esse servi�o serve somente para o caso de reenvio de mensagem, por isso s� precisa do CPF ou
     * do CNPJ.
     * 
     * @param numCpfCnpj
     * @throws ComumException void
     * 
     */
    public void processarAlterarSituacaoBeneficiario(String numCpfCnpj, Short idCanal) throws ComumException, ComumNegocioException;

}
