package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupo;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupos;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Linha;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalNegocioException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.util.RotulosSicoobDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;

/**
 * VerificaPagadorEletronicoDDAServicoEJB é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Remote(Transacao.class)
public class VerificaPagadorEletronicoDDAServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

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

        Character tipoPessoa = obterValorInteger(obterParametro(mensagem, RotulosSicoobDDA.TIPO_PESSOA)) == 0 ? 'F' : 'J';
        String cpfCnpj = obterParametro(mensagem, Rotulos.CPF_CNPJ).getValor().toString();

        boolean isPagadorEletronico = OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate().isCpfCnpjPagadorEletronico(tipoPessoa, cpfCnpj, Boolean.TRUE);

        return popularGrupo(isPagadorEletronico);
    }

    /**
     * Método responsável por popular o grupo
     * 
     * @param isPagadorEletronico
     * @return
     */
    private Grupos popularGrupo(boolean isPagadorEletronico) {
        debug("### Populando grupo...");
        debug("Parâmetro - isPagadorEletronico: " + isPagadorEletronico);

        Grupos grupos = new Grupos();

        Grupo grupo = new Grupo();
        grupos.add(grupo);

        Linha linha = new Linha();
        grupo.adicionarLinha(linha);

        linha.adicionarValor(isPagadorEletronico ? 1 : 0);

        return grupos;
    }
}
