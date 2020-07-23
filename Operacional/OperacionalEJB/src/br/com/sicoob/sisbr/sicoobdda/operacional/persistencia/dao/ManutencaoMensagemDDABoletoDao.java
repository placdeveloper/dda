package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.MeioPagamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoModeloCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMulta;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoPessoaDDAAvalista;

/**
 * AlterarMensagemBoletoDao � respons�vel por
 * 
 * @author Tayrone.Oliveira
 */
public interface ManutencaoMensagemDDABoletoDao extends OperacionalCrudDaoIF<MensagemDDABoleto> {

    /**
     * M�todo respons�vel por retornar consulta Paginada de MensagemDDABoletoDto
     * 
     * @param filtro
     * @return
     * @throws ComumException List<MensagemDDABoletoDto>
     * 
     */
    List<MensagemDDABoletoDto> listarMensagemDDABoletoDtoPaginado(MensagemDDABoletoFiltroDto filtro) throws ComumException;

    /**
     * M�todo respons�vel por listar os Meios de Pagamento para popular Combo na tela de altera��o de MensagemDDABoleto
     * 
     * @return List<MeioPagamento>
     * 
     */
    List<MeioPagamento> listarMeioPagamento();

    /**
     * M�todo respons�vel por listar os Tipos de Juros para popular Combo na tela de altera��o de MensagemDDABoleto
     * 
     * @return List<TipoJuros>
     * 
     */
    List<TipoJuros> listarTipoJuros();

    /**
     * M�todo respons�vel por listar os Tipos de Desconto para popular Combo na tela de altera��o de MensagemDDABoleto
     * 
     * @return List<TipoDesconto>
     * 
     */
    List<TipoDesconto> listarTipoDesconto();

    /**
     * M�todo respons�vel por listar os Tipos de Multa para popular Combo na tela de altera��o de MensagemDDABoleto
     * 
     * @return List<TipoMulta>
     * 
     */
    List<TipoMulta> listarTipoMulta();

    /**
     * M�todo respons�vel por listar os Tipos de Modelo de C�lculo para popular Combo na tela de altera��o de MensagemDDABoleto
     * 
     * @return List<TipoModeloCalculo>
     * 
     */
    List<TipoModeloCalculo> listarTipoModeloCalculo();

    /**
     * M�todo respons�vel por listar os AutorizacaoValorDivergente para popular Combo na tela de altera��o de MensagemDDABoleto
     * 
     * @return List<AutorizacaoValorDivergente>
     * 
     */
    List<AutorizacaoValorDivergente> listarAutorizacaoValorDivergente();

    /**
     * M�todo respons�vel por listar os TipoPessoaDDAAvalista para popular Combo na tela de altera��o de MensagemDDABoleto
     * 
     * @return List<TipoPessoaDDAAvalista>
     * 
     */
    List<TipoPessoaDDAAvalista> listarTipoPessoaDDAAvalista();

}
