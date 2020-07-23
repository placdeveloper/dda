package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AbrirArquivoRetornoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSituacaoArquivo;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;

/**
 * ArquivoRecebidoCIPProcessadorServicoDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ArquivoRecebidoCIPProcessadorServicoDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ArquivoRecebidoCIPProcessadorServico {

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
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoRecebidoCIPProcessadorServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoRecebidoCIPProcessadorServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#abrirArquivoParaProcessamento(long,
     *      java.lang.String, java.lang.String, int)
     */
    public TipoSituacaoArquivo abrirArquivoParaProcessamento(long idArquivoRec, String nmArquivo, String localDeSalva, int tmpAposAtualizacao) throws BancoobException {
        return localizarServico().abrirArquivoParaProcessamento(idArquivoRec, nmArquivo, localDeSalva, tmpAposAtualizacao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#gravarDetalhes(br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO,
     *      java.lang.String, int)
     */
    public void gravarDetalhes(ArquivoProcessamentoVO prArquivo, String prlclArquivo, int prQuantidade) throws BancoobException {
        localizarServico().gravarDetalhes(prArquivo, prlclArquivo, prQuantidade);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#conciliarDetalhesGravados(long)
     */
    public void conciliarDetalhesGravados(long idArquivoRecebido) throws BancoobException {
        localizarServico().conciliarDetalhesGravados(idArquivoRecebido);
    }

    /**
     * {@inheritDoc}
     * 
     * @param codSituacaoProcessamentoArquivo
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#atualizarSituacaoProcessamentoArquivoRecebido(long)
     */
    public void atualizarSituacaoProcessamentoArquivoRecebido(long idArquivoRecebido, short codSituacaoProcessamentoArquivo) throws ComumException {
        localizarServico().atualizarSituacaoProcessamentoArquivoRecebido(idArquivoRecebido, codSituacaoProcessamentoArquivo);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#processarDetalhes(br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum,
     *      long, long, long, java.lang.String, int)
     */
    public void processarDetalhes(TipoInstanciaProcessamentoEnum tipoInstancia, long idArquivo, long idDetLogRecebimentoInicial, long idDetLogRecebimentoFinal, String codMensagem,
            int regPorTrans) throws BancoobException {
        localizarServico().processarDetalhes(tipoInstancia, idArquivo, idDetLogRecebimentoInicial, idDetLogRecebimentoFinal, codMensagem, regPorTrans);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#abrirArquivoCIP(java.util.List)
     */
    public AbrirArquivoRetornoDto abrirArquivoCIP(List<String> listaArquivos) throws BancoobException {
        return this.localizarServico().abrirArquivoCIP(listaArquivos);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#salvarMensagemProtocoloErro(java.lang.String,
     *      long, br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum)
     */
    public void atualizarMensagemProtocoloErro(String nmArquivo, long idArquivoEnvio, TipoArquivoRetornoEnum tipoArquivoENUM) throws ComumException {
        localizarServico().atualizarMensagemProtocoloErro(nmArquivo, idArquivoEnvio, tipoArquivoENUM);
    }

}
