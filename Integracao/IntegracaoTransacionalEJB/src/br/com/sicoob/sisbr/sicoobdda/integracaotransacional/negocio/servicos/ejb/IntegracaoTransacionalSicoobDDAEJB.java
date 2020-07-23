package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.negocio.excecao.ValidacaoException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupo;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupos;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Linha;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.MontagemConteudoRetornoGrupos;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.satm.MontagemConteudoRetornoGruposSATM;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.sced.MontagemConteudoRetornoGruposSCED;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.scos.MontagemConteudoRetornoGruposSCOS;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.scx.MontagemConteudoRetornoGruposSCX;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.sibk.MontagemConteudoRetornoGruposSIBK;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.sofc.MontagemConteudoRetornoGruposSOFC;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.swap.MontagemConteudoRetornoGruposSWAP;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.swep.MontagemConteudoRetornoGruposSWEP;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro.ParametroEsperado;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro.ParametroEsperadoEntrada;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro.ValorDefault;

/**
 * Implementacao base de todos os servicos que executam a transa��o.<br>
 * 
 */
public abstract class IntegracaoTransacionalSicoobDDAEJB extends IntegracaoTransacionalServicoEJB {

    /**
     * M�todo que executa a transa��o.
     * 
     * @param Mmensagem - Mensagem enviada pelo SRTB
     */
    public final RetornoMensagem executarTransacao(Mensagem mensagem) throws ExcecaoTransacao {
        final RetornoMensagem retorno = new RetornoMensagem();
        try {
            verificarParametrosEsperados(mensagem);
            Grupos grupos = executar(mensagem);
            MontagemConteudoRetornoGrupos montagem = obterMontagem(mensagem.getCodigoCanal());
            retorno.setConteudoRetorno(montagem.montarConteudoRetorno(grupos));
            retorno.setCodRetorno(RETORNO_SUCESSO);
            retorno.setSucesso(true);
        } catch (ValidacaoException e) {
            gerarRetornoErro(retorno, e);
            retorno.setTipoErro(RetornoMensagem.ERRO_VALIDACAO);
        } catch (NegocioException e) {
            gerarRetornoErro(retorno, e);
            retorno.setTipoErro(RetornoMensagem.ERRO_NEGOCIO);
        } catch (BancoobException e) {
            gerarRetornoErro(retorno, e);
            retorno.setTipoErro(RetornoMensagem.ERRO_EXECUCAO);
        }
        return retorno;
    }

    /**
     * Retorna a montagem de acordo com o canal informado
     * 
     * @param codigoCanal
     * @return
     * @throws CapRemException
     */
    protected MontagemConteudoRetornoGrupos obterMontagem(Short codigoCanal) throws ComumException {
        if (codigoCanal.equals(CANAL_CAIXA)) {
            return new MontagemConteudoRetornoGruposSCX();
        }
        if (codigoCanal.equals(CANAL_ATM)) {
            return new MontagemConteudoRetornoGruposSATM();
        }
        if (codigoCanal.equals(CANAL_IB)) {
            return new MontagemConteudoRetornoGruposSIBK();
        }
        if (codigoCanal.equals(CANAL_OFFICE) || codigoCanal.equals(CANAL_SICOOBNET)) {
            return new MontagemConteudoRetornoGruposSOFC();
        }
        if (codigoCanal.equals(CANAL_CELULAR) || codigoCanal.equals(CANAL_CONTA_PAGAMENTO_DIGITAL)) {
            return new MontagemConteudoRetornoGruposSWAP();
        }
        if (codigoCanal.equals(CANAL_CELULAR_EMPRESARIAL)) {
            return new MontagemConteudoRetornoGruposSWEP();
        }
        if (codigoCanal.equals(CANAL_CORRESPONDENTE_SICOOB)) {
            return new MontagemConteudoRetornoGruposSCOS();
        }
        if (codigoCanal.equals(CANAL_CEDENTE)) {
            return new MontagemConteudoRetornoGruposSCED();
        }

        throw new ComumException("integracao.transacional.cobranca.obter.montagem", codigoCanal);
    }

    /**
     * M�todo gera retorno com erro, e realiza log do erro.
     * 
     * @param retorno
     * @param e
     */
    private void gerarRetornoErro(RetornoMensagem retorno, Exception e) {
        getLogger().erro(e, e.getMessage());
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        retorno.setCodRetorno(RETORNO_ERRO);
        retorno.setMensagem(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        retorno.setDetalheErro(writer.toString());
    }

    /**
     * M�todo que ir� realizar a execu��o negocial da transa��o
     * 
     * @param mensagem
     * @return
     */
    protected abstract Grupos executar(final Mensagem mensagem) throws BancoobException;

    /**
     * Valida se todos os par�metros esperados foram informados.
     * 
     * @param mensagem
     * @throws ValidacaoException Caso algum par�metro n�o tenha sido informado, ser� disparada a exce��o
     */
    private void verificarParametrosEsperados(Mensagem mensagem) throws ValidacaoException {
        List<ParametroEsperado> parametrosEsperados = getParametrosEsperados();
        for (ParametroEsperado parametroEsperado : parametrosEsperados) {
            if (!contemParametro(mensagem, parametroEsperado.getChaveRotulo())) {
                if (parametroEsperado instanceof ValorDefault) {
                    configurarValorDefault(mensagem, parametroEsperado);
                } else {
                    throw new ValidacaoException(MensagemUtil.getString("integracao.transacional.cobranca.validar.parametros.entrada", parametroEsperado.getChaveRotulo()));
                }
            }
        }
    }

    /**
     * Percorre lista de par�metros esperados. E gera os valores padr�es quando o par�metro n�o for informado.
     * 
     * @param mensagem
     */
    private void configurarValorDefault(Mensagem mensagem, ParametroEsperado parametroEsperado) {
        ValorDefault valorDefault = (ValorDefault) parametroEsperado;
        Parametro parametro = new Parametro(parametroEsperado.getChaveRotulo(), parametroEsperado.getTipoParametro(), valorDefault.getValorPadrao(), valorDefault.getTipoJdbc());
        mensagem.getParametros().add(parametro);
    }

    /**
     * Retorna a lista de par�metros esperados de entrada est� na ordem padr�o. A lista deve obedecer a ordem de par�metros esperados pela SP. Sobreescrevar nas
     * transa��es necess�rias
     * 
     * @return
     */
    protected List<ParametroEsperadoEntrada> getParametrosEsperadosEntrada() {
        List<ParametroEsperadoEntrada> lista = new ArrayList<ParametroEsperadoEntrada>();
        lista.add(new ParametroEsperadoEntrada(Rotulos.CODIGO_CANAL));
        return lista;
    }

    /**
     * Aglomera os parametros de saida e entrada em uma �nica lista
     * 
     * @return
     */
    private List<ParametroEsperado> getParametrosEsperados() {
        List<ParametroEsperado> parametrosEsperados = new ArrayList<ParametroEsperado>();
        parametrosEsperados.addAll(getParametrosEsperadosEntrada());
        return parametrosEsperados;
    }

    /**
     * Gera o retorno de sucesso inserindo a mensagem na linha.
     * 
     * @param mensagem
     * @return Grupos
     * 
     */
    public Grupos getGruposRetornoSucesso(String mensagem) {
        Grupos grupos = new Grupos();
        Grupo grupo = new Grupo();
        grupos.add(grupo);
        Linha linha = new Linha();
        grupo.adicionarLinha(linha);
        linha.adicionarValor(mensagem);

        return grupos;
    }

    /**
     * M�todo respons�vel por converter boolean para short.
     * 
     * @param b
     * @return
     */
    protected Short converterBooleanParaShort(Boolean b) {
        if (ObjectUtil.isNull(b)) {
            return null;
        }

        return (short) (b ? 1 : 0);
    }

}