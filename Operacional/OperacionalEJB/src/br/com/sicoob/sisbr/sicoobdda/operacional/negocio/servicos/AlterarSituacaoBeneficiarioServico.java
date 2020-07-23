/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         AlterarSituacaoBeneficiarioServico.java
 * Data Criação:    May 19, 2015
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
     * Método responsável por criar e enviar o arquivo de alteração de situação de beneficiário. Esta funcionalidade server para avisar a cip que o Sicoob
     * alterou uma situação de um de seus beneficiários.
     * 
     * @param alteraSitBeneficiarioDto
     * @return
     * @throws ComumException void
     * 
     */
    public RetornoAlterarSituacaoBeneficiarioEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioDto alteraSitBeneficiarioDto, Short idCanal)
            throws ComumException, ComumNegocioException;

    /**
     * Método responsável por alterar a situação do beneficiário. Esse serviço serve somente para o caso de reenvio de mensagem, por isso só precisa do CPF ou
     * do CNPJ.
     * 
     * @param numCpfCnpj
     * @throws ComumException void
     * 
     */
    public void processarAlterarSituacaoBeneficiario(String numCpfCnpj, Short idCanal) throws ComumException, ComumNegocioException;

}
