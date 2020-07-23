/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         RequisitarArquivoServico.java
 * Data Criação:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroGen0014Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoArquivoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoGEN0014Enum;

/**
 * RequisitarArquivoServico
 * 
 * @author Rafael.Silva
 */
public interface MensagemGEN0014Servico extends IntegracaoCipServico {

    /**
     * Método responsável por incluir e postar na fila uma mensagem GEN0014
     * 
     * @param dto
     * @param tipoSolicitacao
     * @throws BancoobException
     */
    void incluir(CadastroGen0014Dto dto, TipoSolicitacaoGEN0014Enum tipoSolicitacao) throws BancoobException;

    /**
     * Método responsável por criar o xml, persistir os dados da mensagem e postar na fila apropriada da CIP.
     * 
     * @param tipoSolicitacaoArquivoBeneficiario
     * @param situacaoBeneficiarioEnum
     * @throws BancoobException void
     * 
     */
    void processarMensagem(TipoSolicitacaoArquivoBeneficiarioEnum tipoSolicitacaoArquivoBeneficiario, SituacaoBeneficiarioEnum situacaoBeneficiarioEnum) throws BancoobException;
}
