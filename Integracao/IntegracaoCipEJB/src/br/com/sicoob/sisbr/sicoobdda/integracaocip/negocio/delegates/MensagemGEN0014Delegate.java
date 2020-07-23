/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ArquivoRequisitarArquivoDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroGen0014Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoArquivoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoGEN0014Enum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemGEN0014Servico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * MensagemGEN0014Delegate
 * 
 * @author rafael.silva
 */
public class MensagemGEN0014Delegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements MensagemGEN0014Servico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected MensagemGEN0014Servico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemGEN0014Servico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemGEN0014Servico#incluir(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroGen0014Dto,
     *      br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoGEN0014Enum)
     */
    public void incluir(CadastroGen0014Dto dto, TipoSolicitacaoGEN0014Enum tipoSolicitacao) throws BancoobException {
        localizarServico().incluir(dto, tipoSolicitacao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemGEN0014Servico#processarMensagem(br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoArquivoBeneficiarioEnum,
     *      br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum)
     */
    public void processarMensagem(TipoSolicitacaoArquivoBeneficiarioEnum tipoSolicitacaoArquivoBeneficiario, SituacaoBeneficiarioEnum situacaoBeneficiarioEnum) throws BancoobException {
        localizarServico().processarMensagem(tipoSolicitacaoArquivoBeneficiario, situacaoBeneficiarioEnum);
    }

}
