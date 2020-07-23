package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupo;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupos;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Linha;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalNegocioException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.util.RotulosSicoobDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;

/**
 * ManterDDATerceiroServicoEJB é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Remote(Transacao.class)
public class ManterDDATerceiroServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalSicoobDDAEJB#executar(br.com.bancoob.srtb.dto.Mensagem)
     */
    @Override
    protected Grupos executar(Mensagem mensagem) throws BancoobException {
        debug("### Executando o processo de manutenção do DDA terceiro - Transacional");

        if (ObjectUtil.isNull(mensagem)) {
            throw new IntegracaoTransacionalNegocioException("integracao.transacional.mensagem.nao.informada");
        }

        DDATerceiroDto dto = obterDTO(mensagem);

        boolean sucesso = OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate().manterDDATerceiro(dto);

        return popularGrupo(sucesso);
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @return
     * @throws IntegracaoTransacionalNegocioException
     */
    private DDATerceiroDto obterDTO(Mensagem mensagem) throws IntegracaoTransacionalNegocioException {
        DDATerceiroDto dto = new DDATerceiroDto();

        dto.setCodCanal(obterValorInteger(obterParametro(mensagem, Rotulos.CODIGO_CANAL)));
        dto.setNumConta(obterValorBigDecimal(obterParametro(mensagem, Rotulos.NUMERO_CONTA_CORRENTE)));
        dto.setNumCooperativa(obterValorInteger(obterParametro(mensagem, Rotulos.COOPERATIVA)));
        dto.setNumCoopCartao(obterValorInteger(obterParametro(mensagem, Rotulos.COOPERATIVA_CARTAO)));
        dto.setNumCpfCnpj(obterParametro(mensagem, Rotulos.CPF_CNPJ).getValor().toString());
        dto.setNumIdentDDA(obterValorLong(obterParametro(mensagem, RotulosSicoobDDA.NUMERO_IDENTIFICACAO_DDA)));
        dto.setNumPac(obterValorInteger(obterParametro(mensagem, Rotulos.NUMERO_PAC)));
        dto.setDescTerminal(obterValorString(obterParametro(mensagem, RotulosSicoobDDA.DESCRICAO_TERMINAL, false)));
        dto.setTipoPessoaTerc(obterValorInteger(obterParametro(mensagem, RotulosSicoobDDA.TIPO_PESSOA)) == 0 ? 'F' : 'J');
        String indTerceiro = obterValorString(obterParametro(mensagem, RotulosSicoobDDA.IDENTIFICADOR_TERCEIRO));
        dto.setIndTerceiro(ObjectUtil.isEmpty(indTerceiro) ? null : indTerceiro.charAt(0));

        return dto;
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDADto
     * @return
     * @throws IntegracaoTransacionalException
     */
    private Grupos popularGrupo(boolean sucesso) throws IntegracaoTransacionalException {
        debug("### Populando grupo...");
        debug("Parâmetro - sucesso: " + sucesso);

        Grupos grupos = new Grupos();

        Grupo grupo = new Grupo();
        grupos.add(grupo);

        Linha linha = new Linha();
        grupo.adicionarLinha(linha);

        linha.adicionarValor(sucesso ? 1 : 0);

        return grupos;
    }

}
