/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         MensagemDDAPagadorDelegate.java
 * Data Criação:    Dec 28, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoOperacaoSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MensagemDDAPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * MensagemDDAPagadorDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MensagemDDAPagadorDelegate extends OperacionalDelegate<MensagemDDAPagadorServico> {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected MensagemDDAPagadorServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarMensagemDDAPagadorServico();
    }

    /**
     * Método responsável por incluir uma MensagemDDAPagador visando a adesão ou cancelmando de um pagador eletrônico. Mensagens: DDA0001 e ADDA006
     * 
     * @param numCpfCnpj
     * @param idInstituicao
     * @param listaNumCCO
     * @param bolPagadorEletronico
     * @param codTipoMensagemDDA
     * @return MensagemDDAPagador
     * @throws ComumException
     */
    public MensagemDDAPagador incluirMensagemPagador(String numCpfCnpj, Integer idInstituicao, List<String> listaNumCCO, Boolean bolPagadorEletronico, String codTipoMensagemDDA,
            Short idCanal) throws ComumException {
        return localizarServico().incluirMensagemPagador(numCpfCnpj, idInstituicao, listaNumCCO, bolPagadorEletronico, codTipoMensagemDDA, idCanal);
    }

    /**
     * Método responsável por incluir uma MensagemDDAPagador visando a inclusão ou exclusão de um pagador agregado. Mensagens: ADDA505
     * 
     * @param listaPagadorAgregado
     * @param numCpfCnpjPagador
     * @param idInstituicao
     * @param tipoOperacao
     * @return MensagemDDAPagador
     * @throws ComumException
     */
    public MensagemDDAPagador incluirMensagemPagadorAgregado(List<PagadorAgregadoDto> listaPagadorAgregado, String numCpfCnpjPagador, Integer idInstituicao,
            TipoOperacaoSicoobDDAEnum tipoOperacao, Short idCanal) throws ComumException {
        return localizarServico().incluirMensagemPagadorAgregado(listaPagadorAgregado, numCpfCnpjPagador, idInstituicao, tipoOperacao, idCanal);
    }
}
