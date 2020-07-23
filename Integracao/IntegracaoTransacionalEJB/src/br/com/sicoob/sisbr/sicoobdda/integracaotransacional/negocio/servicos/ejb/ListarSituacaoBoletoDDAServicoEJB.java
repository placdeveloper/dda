package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupo;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupos;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Linha;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalNegocioException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;

/**
 * SituacaoBoletoDDAServicoEJB é responsável por
 * 
 * @author George.santos
 */
@Stateless
@Remote(Transacao.class)
public class ListarSituacaoBoletoDDAServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalSicoobDDAEJB#executar(br.com.bancoob.srtb.dto.Mensagem)
     */
    @Override
    protected Grupos executar(Mensagem mensagem) throws BancoobException {
        debug("### Executando a consulta da Situacao boleto DDA - Transacional");

        if (ObjectUtil.isNull(mensagem)) {
            throw new IntegracaoTransacionalNegocioException("integracao.transacional.mensagem.nao.informada");
        }

        List<SituacaoBoleto> listaSituacaoBoleto = OperacionalFabricaDelegates.getInstance().getConsultaBoletoDDADelegate().listarSituacaoBoleto();

        return popularGrupo(listaSituacaoBoleto);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDADto
     * @return
     * @throws IntegracaoTransacionalException
     */
    private Grupos popularGrupo(List<SituacaoBoleto> listaSituacaoBOleto) throws IntegracaoTransacionalException {
        debug("### Populando grupo do retorno da consulta da Situacao do boleto...");

        if (ObjectUtil.isEmpty(listaSituacaoBOleto)) {
            return new Grupos();
        }
        Grupo grupo = new Grupo();

        for (SituacaoBoleto situacaoBoleto : listaSituacaoBOleto) {
            Linha linha = new Linha();
            grupo.adicionarLinha(linha);

            debug("Adicionando CodSituacaoBoleto: " + situacaoBoleto.getCodSituacaoBoleto());
            linha.adicionarValor(situacaoBoleto.getCodSituacaoBoleto());

            debug("Adicionando DescSituacaoBoleto: " + situacaoBoleto.getDescSituacaoBoleto());
            linha.adicionarValor(situacaoBoleto.getDescSituacaoBoleto());

            debug("------------------------------------------------------");
        }

        return new Grupos(grupo);
    }
}
