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
 * TermoAdesaoDDAPeloSacadoServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Remote(Transacao.class)
public class TermoAdesaoDDAPeloSacadoServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

    PagadorEletronicoDDADelegate pagadorEletronicoDDADelegate = null;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalSicoobDDAEJB#executar(br.com.bancoob.srtb.dto.Mensagem)
     */
    @Override
    protected Grupos executar(Mensagem mensagem) throws BancoobException {
        validacaoTermoAdesaoPeloSacado(mensagem);

        PagadorEletronicoDDADto pagadorEletronicoDDADto = setPagadorEletronicoDDADto(mensagem);

        // IdEventoDDA equivale ao IDHISTORICOTERMOPAGADOR do DB2

        TermoPagadorDto termoPagadorDto = getPagadorEletronicoDDADelegate().obterTermoAdesaoPeloSacado(pagadorEletronicoDDADto.getIdEventoDDA(),
                pagadorEletronicoDDADto.getNumConta().longValue(),
                pagadorEletronicoDDADto.getNumCooperativa(), pagadorEletronicoDDADto.getIdRespTitular(), pagadorEletronicoDDADto.getNumCpfCnpj());

        return populaTermoAdesao(termoPagadorDto);
    }

    /**
     * Método responsável por
     * 
     * @param TermoAdesaoDDADto
     * @return
     * @throws IntegracaoTransacionalException
     */
    private Grupos populaTermoAdesao(TermoPagadorDto termoPagadorDto) throws IntegracaoTransacionalException {
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
        linha.adicionarValor(obterValorDataHoraFormatadaDb2(termoPagadorDto.getDataInicioVigencia()));
        linha.adicionarValor("");

        return grupos;
    }

    /**
     * Método responsável por popular o indicadro de Aceite
     * 
     * @param mensagem
     * @return IndicadorAceiteDDADto
     * @throws IntegracaoTransacionalNegocioException
     * 
     */
    private PagadorEletronicoDDADto setPagadorEletronicoDDADto(Mensagem mensagem) throws IntegracaoTransacionalNegocioException {
        PagadorEletronicoDDADto indicadorAceite = new PagadorEletronicoDDADto();

        indicadorAceite.setNumCliente(obterValorInteger(mensagem.recuperarParametro(RotulosSicoobDDA.NUMERO_CLIENTE)));
        indicadorAceite.setNumConta(BigDecimal.valueOf(obterValorLong(mensagem.recuperarParametro(RotulosSicoobDDA.NUMERO_CONTA))));

        indicadorAceite.setNumCoopCartao(obterValorInteger(mensagem.recuperarParametro(Rotulos.COOPERATIVA_CARTAO)));
        indicadorAceite.setNumCooperativa(obterValorInteger(mensagem.recuperarParametro(Rotulos.COOPERATIVA)));
        indicadorAceite.setNumPac(obterValorInteger(mensagem.recuperarParametro(Rotulos.NUMERO_PAC)));
        indicadorAceite.setCodCanal(obterValorInteger(mensagem.recuperarParametro(Rotulos.CODIGO_CANAL)));
        indicadorAceite.setDescTerminal(ObjectUtil.isNull(mensagem.recuperarParametro(RotulosSicoobDDA.DESCRICAO_TERMINAL)) ? null : (String) mensagem.recuperarParametro(
                RotulosSicoobDDA.DESCRICAO_TERMINAL).getValor());
        indicadorAceite.setIdEventoDDA(obterValorInteger(mensagem.recuperarParametro(RotulosSicoobDDA.ID_EVENTO_DDA)));

        indicadorAceite.setBolControlaTransacao(obterValorBoolean(mensagem.recuperarParametro(RotulosSicoobDDA.BOL_CONTROLA_TRANS)));

        indicadorAceite.setIdRespTitular(ObjectUtil.isNull(mensagem.recuperarParametro(RotulosSicoobDDA.ID_RESP_TITULAR)) ? null
                : obterValorInteger(mensagem.recuperarParametro(RotulosSicoobDDA.ID_RESP_TITULAR)));

        indicadorAceite.setNumCpfCnpj(ObjectUtil.isNull(mensagem.recuperarParametro(Rotulos.CPF_CNPJ)) ? null : obterValorString(mensagem.recuperarParametro(Rotulos.CPF_CNPJ)));

        return indicadorAceite;
    }

    /**
     * Método responsável pela validacao dos parametros recebidos pelo CANAL
     * 
     * @param pagadorEletronicoDDADto
     * @throws IntegracaoTransacionalNegocioException void
     * 
     */
    private void validacaoTermoAdesaoPeloSacado(Mensagem m) throws ValidacaoException {
      if (ObjectUtil.isNull(m.recuperarParametro(RotulosSicoobDDA.NUMERO_CONTA))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", RotulosSicoobDDA.NUMERO_CONTA);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.COOPERATIVA_CARTAO))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.COOPERATIVA_CARTAO);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.COOPERATIVA))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.COOPERATIVA);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.NUMERO_PAC))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.NUMERO_PAC);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.CODIGO_CANAL))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.CODIGO_CANAL);
        } else if (ObjectUtil.isNull(m.recuperarParametro(RotulosSicoobDDA.ID_EVENTO_DDA))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", RotulosSicoobDDA.ID_EVENTO_DDA);
        } 
    }

    /**
     * @return o atributo pagadorEletronicoDDADelegate
     */
    public PagadorEletronicoDDADelegate getPagadorEletronicoDDADelegate() {
        if (pagadorEletronicoDDADelegate == null) {
            pagadorEletronicoDDADelegate = OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate();
        }
        return pagadorEletronicoDDADelegate;
    }

    /**
     * Define o atributo pagadorEletronicoDDADelegate
     */
    public void setPagadorEletronicoDDADelegate(PagadorEletronicoDDADelegate pagadorEletronicoDDADelegate) {
        this.pagadorEletronicoDDADelegate = pagadorEletronicoDDADelegate;
    }
}
