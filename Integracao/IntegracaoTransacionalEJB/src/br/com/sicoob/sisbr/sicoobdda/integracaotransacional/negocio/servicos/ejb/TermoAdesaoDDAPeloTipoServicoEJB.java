package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.ValidacaoException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoPagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoTermoPagadorEnum;
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
 * TermoAdesaoDDAPeloTipoServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Remote(Transacao.class)
public class TermoAdesaoDDAPeloTipoServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

    PagadorEletronicoDDADelegate pagadorEletronicoDDADelegate = null;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalSicoobDDAEJB#executar(br.com.bancoob.srtb.dto.Mensagem)
     */
    @Override
    protected Grupos executar(Mensagem mensagem) throws BancoobException {
        validacaoTermoAdesaoPeloTipo(mensagem);

        PagadorEletronicoDDADto pagadorEletronicoDDADto = setPagadorEletronicoDDADto(mensagem);

        TermoPagadorDto termoPagadorDto = getPagadorEletronicoDDADelegate().obterTermoAdesaoPeloTipo(termoDePara(pagadorEletronicoDDADto.getTipoTermo()),
                pagadorEletronicoDDADto.getNumCooperativa(), pagadorEletronicoDDADto.getTipoRetorno());

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

        return grupos;
    }

    /**
     * Método responsável por fazer o compare do legado do termo para o DB2
     * 
     * 14 ou 2 - Inclusao de Adesao 15 ou 3 - Cancelamento de Adesao
     * 
     * @param termoTransacao
     * @return Short
     */
    public Short termoDePara(Integer termoTransacao) {
        if (termoTransacao.equals(14) || termoTransacao.equals(2)) {
            return TipoTermoPagadorEnum.ADESAO_DDA.getValor();
        } else if (termoTransacao.equals(15) || termoTransacao.equals(3)) {
            return TipoTermoPagadorEnum.CANCELAR_ADESAO_DDA.getValor();
        }
        return null;
    }

    /**
     * Método responsável por popular o indicadro de Aceite
     * 
     * @param mensagem
     * @return IndicadorAceiteDDADto
     * @throws IntegracaoTransacionalNegocioException
     * 
     */
    private PagadorEletronicoDDADto setPagadorEletronicoDDADto(Mensagem m) throws IntegracaoTransacionalNegocioException {
        PagadorEletronicoDDADto indicadorAceite = new PagadorEletronicoDDADto();
        indicadorAceite.setNumCoopCartao(obterValorInteger(m.recuperarParametro(Rotulos.COOPERATIVA_CARTAO)));
        indicadorAceite.setNumCooperativa(obterValorInteger(m.recuperarParametro(Rotulos.COOPERATIVA)));
        indicadorAceite.setNumPac(obterValorInteger(m.recuperarParametro(Rotulos.NUMERO_PAC)));

        indicadorAceite.setTipoTermo(obterValorInteger(m.recuperarParametro(RotulosSicoobDDA.TERMO_FAVORECIDO)));
        indicadorAceite.setTipoRetorno(obterValorInteger(m.recuperarParametro(RotulosSicoobDDA.TIPO_RETORNO)));

        indicadorAceite.setCodCanal(obterValorInteger(m.recuperarParametro(Rotulos.CODIGO_CANAL)));
        indicadorAceite.setDescTerminal(ObjectUtil.isNull(m.recuperarParametro(RotulosSicoobDDA.DESCRICAO_TERMINAL)) ? null : (String) m.recuperarParametro(
                RotulosSicoobDDA.DESCRICAO_TERMINAL).getValor());
        indicadorAceite.setBolOuvidoria(obterValorBoolean(m.recuperarParametro(RotulosSicoobDDA.BOL_OUVIDORIA)));

        return indicadorAceite;
    }

    /**
     * Método responsável pela validacao dos parametros recebidos pelo CANAL
     * 
     * @param pagadorEletronicoDDADto
     * @throws IntegracaoTransacionalNegocioException void
     * 
     */
    private void validacaoTermoAdesaoPeloTipo(Mensagem m) throws ValidacaoException {
        if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.COOPERATIVA))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.COOPERATIVA);
        } else if (ObjectUtil.isNull(m.recuperarParametro(RotulosSicoobDDA.TERMO_FAVORECIDO))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", RotulosSicoobDDA.TERMO_FAVORECIDO);
        } else if (ObjectUtil.isNull(m.recuperarParametro(RotulosSicoobDDA.TIPO_RETORNO))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", RotulosSicoobDDA.TIPO_RETORNO);
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
