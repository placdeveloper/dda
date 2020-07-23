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
 * AlterarMensagemBoletoDao é responsável por
 * 
 * @author Tayrone.Oliveira
 */
public interface ManutencaoMensagemDDABoletoDao extends OperacionalCrudDaoIF<MensagemDDABoleto> {

    /**
     * Método responsável por retornar consulta Paginada de MensagemDDABoletoDto
     * 
     * @param filtro
     * @return
     * @throws ComumException List<MensagemDDABoletoDto>
     * 
     */
    List<MensagemDDABoletoDto> listarMensagemDDABoletoDtoPaginado(MensagemDDABoletoFiltroDto filtro) throws ComumException;

    /**
     * Método responsável por listar os Meios de Pagamento para popular Combo na tela de alteração de MensagemDDABoleto
     * 
     * @return List<MeioPagamento>
     * 
     */
    List<MeioPagamento> listarMeioPagamento();

    /**
     * Método responsável por listar os Tipos de Juros para popular Combo na tela de alteração de MensagemDDABoleto
     * 
     * @return List<TipoJuros>
     * 
     */
    List<TipoJuros> listarTipoJuros();

    /**
     * Método responsável por listar os Tipos de Desconto para popular Combo na tela de alteração de MensagemDDABoleto
     * 
     * @return List<TipoDesconto>
     * 
     */
    List<TipoDesconto> listarTipoDesconto();

    /**
     * Método responsável por listar os Tipos de Multa para popular Combo na tela de alteração de MensagemDDABoleto
     * 
     * @return List<TipoMulta>
     * 
     */
    List<TipoMulta> listarTipoMulta();

    /**
     * Método responsável por listar os Tipos de Modelo de Cálculo para popular Combo na tela de alteração de MensagemDDABoleto
     * 
     * @return List<TipoModeloCalculo>
     * 
     */
    List<TipoModeloCalculo> listarTipoModeloCalculo();

    /**
     * Método responsável por listar os AutorizacaoValorDivergente para popular Combo na tela de alteração de MensagemDDABoleto
     * 
     * @return List<AutorizacaoValorDivergente>
     * 
     */
    List<AutorizacaoValorDivergente> listarAutorizacaoValorDivergente();

    /**
     * Método responsável por listar os TipoPessoaDDAAvalista para popular Combo na tela de alteração de MensagemDDABoleto
     * 
     * @return List<TipoPessoaDDAAvalista>
     * 
     */
    List<TipoPessoaDDAAvalista> listarTipoPessoaDDAAvalista();

}
