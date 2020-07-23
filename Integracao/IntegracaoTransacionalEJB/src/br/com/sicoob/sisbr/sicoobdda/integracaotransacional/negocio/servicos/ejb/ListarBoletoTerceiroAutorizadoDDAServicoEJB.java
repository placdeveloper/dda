package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoTerceiroAutorizadoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupo;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupos;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Linha;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalNegocioException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.util.RotulosSicoobDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;

/**
 * ListarBoletoTerceiroAutorizadoDDAServicoEJB é responsável por
 * 
 * @author George.santos
 */
@Stateless
@Remote(Transacao.class)
public class ListarBoletoTerceiroAutorizadoDDAServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalSicoobDDAEJB#executar(br.com.bancoob.srtb.dto.Mensagem)
     */
    @Override
    protected Grupos executar(Mensagem mensagem) throws BancoobException {
        debug("### Executando a consulta do boleto terceiro Autorizado DDA - Transacional");

        if (ObjectUtil.isNull(mensagem)) {
            throw new IntegracaoTransacionalNegocioException("integracao.transacional.mensagem.nao.informada");
        }

        String numIdentificadorBoletoCIP = obterValorString(obterParametro(mensagem, RotulosSicoobDDA.NUMERO_IDENTIFICADOR_BOLETO_CIP));
        String numCpfCnpjSolicitante = obterValorString(obterParametro(mensagem, Rotulos.CPF_CNPJ));

        List<BoletoTerceiroAutorizadoDDADto> listaBoletoTerceiroAutorizadoDDADto = OperacionalFabricaDelegates.getInstance().getConsultaBoletoDDADelegate()
                .listarBoletoTerceiroAutorizadoDDA(numIdentificadorBoletoCIP, numCpfCnpjSolicitante);

        return popularGrupo(listaBoletoTerceiroAutorizadoDDADto);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDADto
     * @return
     * @throws IntegracaoTransacionalException
     */
    private Grupos popularGrupo(List<BoletoTerceiroAutorizadoDDADto> listaBoletoTerceiroAutorizadoDDADto) throws IntegracaoTransacionalException {
        debug("### Populando grupo do retorno da consulta de boleto...");

        if (ObjectUtil.isEmpty(listaBoletoTerceiroAutorizadoDDADto)) {
            return new Grupos();
        }
        Grupo grupo = new Grupo();

        for (BoletoTerceiroAutorizadoDDADto boletoTerceiroAutorizadoDDADto : listaBoletoTerceiroAutorizadoDDADto) {
            Linha linha = new Linha();
            grupo.adicionarLinha(linha);

            debug("Adicionando bolComCheck: " + boletoTerceiroAutorizadoDDADto.getBolComCheck());
            linha.adicionarValor(boletoTerceiroAutorizadoDDADto.getBolComCheck());

            debug("Adicionando tipoPessoaTerceiro: " + boletoTerceiroAutorizadoDDADto.getCodTipoPessoaTerceiro());
            linha.adicionarValor(boletoTerceiroAutorizadoDDADto.getCodTipoPessoaTerceiro());

            debug("Adicionando numCpfCnpjTerceiro: " + boletoTerceiroAutorizadoDDADto.getNumCpfCnpjTerceiro());
            linha.adicionarValor(boletoTerceiroAutorizadoDDADto.getNumCpfCnpjTerceiro());

            debug("Adicionando Operacao: " + boletoTerceiroAutorizadoDDADto.getOperacao());
            linha.adicionarValor(boletoTerceiroAutorizadoDDADto.getOperacao());

            debug("Adicionando Situacao: " + boletoTerceiroAutorizadoDDADto.getSituacao());
            linha.adicionarValor(boletoTerceiroAutorizadoDDADto.getSituacao());

            debug("------------------------------------------------------");
        }

        return new Grupos(grupo);
    }
}
