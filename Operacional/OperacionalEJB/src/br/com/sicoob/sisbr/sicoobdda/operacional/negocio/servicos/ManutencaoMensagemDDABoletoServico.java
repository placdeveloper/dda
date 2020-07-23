package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoLegadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;

/**
 * ManutencaoMensagemDDABoletoServico
 * 
 * @author Tayrone.Oliveira
 */
public interface ManutencaoMensagemDDABoletoServico extends OperacionalCrudServico<MensagemDDABoleto> {

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
     * Método responsável por
     * 
     * @param id
     * @return MensagemDDABoleto
     * 
     */
    MensagemDDABoleto obterMensagemDDABoletoPorId(Long id);

    /**
     * Método responsável por
     * 
     * @param id
     * @return MensagemDDABoletoDto
     * @throws ComumNegocioException
     * 
     */
    MensagemDDABoletoDto obterMensagemDtoPorId(Long id) throws ComumNegocioException;

    /**
     * Método responsável por
     * 
     * @param dto void
     * @throws ComumNegocioException
     * 
     */
    void alterarMensagemDDABoleto(MensagemDDABoletoDto dto) throws ComumNegocioException;

    /**
     * Método responsável por
     * 
     * @return MensagemDDABoletoFiltroDto
     * 
     */
    MensagemDDABoletoFiltroDto listarCombos();

    /**
     * Método responsável por inserir mensagem DDA Boleto ou Arquivo depdendo do Parametro 96- Habilitar Envio da Mensagem Gera Boleto Online, caso True envia a
     * mensagem online caso false envia por arquivo
     * 
     * @param boletoLegadoDto
     * @throws ComumException void
     * 
     */
    void inserirMensagemDDABoletoGeraBoleto(BoletoLegadoDto boletoLegadoDto) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por inserir mensagem DDA Boleto ou Arquivo depdendo do Parametro 98- Habilitar Envio da Mensagem Sicoob net Empresarial Online, caso
     * True envia a mensagem online caso false envia por arquivo
     * 
     * @param boletoLegadoDto
     * @throws ComumException void
     * 
     */
    void inserirMensagemDDABoletoSicoobNetEmpresarial(BoletoLegadoDto boletoLegadoDto) throws ComumException, ComumNegocioException;

}
