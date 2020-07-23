package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ReenvioArquivoMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemCadastrarReenvioServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * MensagemCadastrarReenvioArquivoOuMensagemDelegate é responsável por
 * 
 * @author george.Santos
 */
public class MensagemCadastrarReenvioDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements MensagemCadastrarReenvioServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected MensagemCadastrarReenvioServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemCadastrarReenvioServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemCadastrarReenvioServico#cadastrarReenvio(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ReenvioArquivoMensagemDto)
     */
    public void cadastrarReenvio(ReenvioArquivoMensagemDto reenvioArquivoMensagemDto) throws ComumException {
        localizarServico().cadastrarReenvio(reenvioArquivoMensagemDto);

    }

}
