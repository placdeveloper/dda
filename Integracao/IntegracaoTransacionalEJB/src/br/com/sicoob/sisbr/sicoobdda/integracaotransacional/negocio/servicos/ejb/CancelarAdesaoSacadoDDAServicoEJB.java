package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import java.math.BigDecimal;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.ValidacaoException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoPagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupo;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupos;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Linha;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalNegocioException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.util.RotulosSicoobDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.PagadorEletronicoDDADelegate;

/**
 * CancelarAdesaoSacadoDDAServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Remote(Transacao.class)
public class CancelarAdesaoSacadoDDAServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

    PagadorEletronicoDDADelegate pagadorEletronicoDDADelegate = OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalSicoobDDAEJB#executar(br.com.bancoob.srtb.dto.Mensagem)
     */
    @Override
    protected Grupos executar(Mensagem mensagem) throws BancoobException {
        validacaoAdesaoDDA(mensagem);

        PagadorEletronicoDDADto pagadorEletronicoDDADto = setPagadorEletronicoDDADto(mensagem);

        TermoPagadorDto termoPagadorDto = pagadorEletronicoDDADelegate.cancelarAdesaoDDA(pagadorEletronicoDDADto);

        return populaCancelamentoAdesaoDDA(termoPagadorDto);
    }

    /**
     * Método responsável por
     * 
     * @param termoAdesaoDDADto
     * @return
     * @throws IntegracaoTransacionalException
     */
    private Grupos populaCancelamentoAdesaoDDA(TermoPagadorDto termoPagadorDto) throws IntegracaoTransacionalException {
        if (ObjectUtil.isNull(termoPagadorDto)) {
            return new Grupos();
        }

        Grupos grupos = new Grupos();

        Grupo grupo = new Grupo();
        grupos.add(grupo);

        Linha linha = new Linha();
        grupo.adicionarLinha(linha);
        linha.adicionarValor(termoPagadorDto.getDescConteudoTermoPagador());
        linha.adicionarValor(termoPagadorDto.getDescOuvidoria());
        linha.adicionarValor(termoPagadorDto.getDescTerminal());

        return grupos;
    }

    /**
     * Método responsável por popular o a adesao ao sacado
     * 
     * @param mensagem
     * @return IndicadorAceiteDDADto
     * @throws IntegracaoTransacionalNegocioException
     * 
     */
    private PagadorEletronicoDDADto setPagadorEletronicoDDADto(Mensagem mensagem) throws IntegracaoTransacionalNegocioException {
        PagadorEletronicoDDADto pagadorEletronicoDDADto = new PagadorEletronicoDDADto();

        pagadorEletronicoDDADto.setNumCliente(obterValorInteger(mensagem.recuperarParametro(RotulosSicoobDDA.NUMERO_CLIENTE)));
        pagadorEletronicoDDADto.setNumConta(BigDecimal.valueOf(obterValorLong(mensagem.recuperarParametro(RotulosSicoobDDA.NUMERO_CONTA))));
        pagadorEletronicoDDADto.setCodCanal(obterValorInteger(mensagem.recuperarParametro(Rotulos.CODIGO_CANAL)));
        pagadorEletronicoDDADto.setDescTerminal(ObjectUtil.isNull(mensagem.recuperarParametro(RotulosSicoobDDA.DESCRICAO_TERMINAL)) ? null
                : (String) mensagem.recuperarParametro(
                RotulosSicoobDDA.DESCRICAO_TERMINAL).getValor());
        pagadorEletronicoDDADto.setTipoRetorno(obterValorInteger(mensagem.recuperarParametro(RotulosSicoobDDA.TIPO_RETORNO)));
        pagadorEletronicoDDADto.setNumCoopCartao(obterValorInteger(mensagem.recuperarParametro(Rotulos.COOPERATIVA_CARTAO)));
        pagadorEletronicoDDADto.setNumCooperativa(obterValorInteger(mensagem.recuperarParametro(Rotulos.COOPERATIVA)));
        pagadorEletronicoDDADto.setNumPac(obterValorInteger(mensagem.recuperarParametro(Rotulos.NUMERO_PAC)));
        pagadorEletronicoDDADto.setBolOuvidoria(obterValorBoolean(mensagem.recuperarParametro(RotulosSicoobDDA.BOL_OUVIDORIA)));
        pagadorEletronicoDDADto.setBolControlaTransacao(obterValorBoolean(mensagem.recuperarParametro(RotulosSicoobDDA.BOL_CONTROLA_TRANS)));

        pagadorEletronicoDDADto.setIdRespTitular(ObjectUtil.isNull(mensagem.recuperarParametro(RotulosSicoobDDA.ID_RESP_TITULAR)) ? null
                : obterValorInteger(mensagem.recuperarParametro(RotulosSicoobDDA.ID_RESP_TITULAR)));
        pagadorEletronicoDDADto
                .setNumCpfCnpj(ObjectUtil.isNull(mensagem.recuperarParametro(Rotulos.CPF_CNPJ)) ? null : obterValorString(mensagem.recuperarParametro(Rotulos.CPF_CNPJ)));

        return pagadorEletronicoDDADto;
    }

    /**
     * Método responsável pela validacao dos parametros recebidos pelo CANAL
     * 
     * @param pagadorEletronicoDDADto
     * @throws IntegracaoTransacionalNegocioException void
     * 
     */
    private void validacaoAdesaoDDA(Mensagem m) throws ValidacaoException {
        if (ObjectUtil.isNull(m.recuperarParametro(RotulosSicoobDDA.NUMERO_CONTA))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", RotulosSicoobDDA.NUMERO_CONTA);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.CODIGO_CANAL))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.CODIGO_CANAL);
        } else if (ObjectUtil.isNull(m.recuperarParametro(RotulosSicoobDDA.TIPO_RETORNO))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", RotulosSicoobDDA.TIPO_RETORNO);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.COOPERATIVA_CARTAO))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.COOPERATIVA_CARTAO);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.COOPERATIVA))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.COOPERATIVA);
        }
    }
}
