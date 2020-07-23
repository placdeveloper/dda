/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         ManutencaoMensagemDDABoletoDelegate.java
 * Data Criação:    Jul 21, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoLegadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ManutencaoMensagemDDABoletoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * ManutencaoMensagemDDABoletoDelegate é responsável por
 * 
 * @author tayrone.oliveira
 */
@SuppressWarnings("rawtypes")
public class ManutencaoMensagemDDABoletoDelegate extends OperacionalCrudDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ManutencaoMensagemDDABoletoServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarManutencaoMensagemBoletoServico();
    }

    /**
     * Método responsável por
     * 
     * @param filtro
     * @return
     * @throws ComumException List<MensagemDDABoletoDto>
     * 
     */
    public List<MensagemDDABoletoDto> listarMensagemDDABoletoDtoPaginado(MensagemDDABoletoFiltroDto filtro) throws ComumException {
        return localizarServico().listarMensagemDDABoletoDtoPaginado(filtro);
    }

    /**
     * Método responsável por
     * 
     * @param id
     * @return MensagemDDABoleto
     * 
     */
    public MensagemDDABoleto obterMensagemDDABoletoPorId(Long id) {
        return localizarServico().obterMensagemDDABoletoPorId(id);
    }

    /**
     * Método responsável por
     * 
     * @param id
     * @return MensagemDDABoletoDto
     * @throws ComumNegocioException
     * 
     */
    public MensagemDDABoletoDto obterMensagemDtoPorId(Long id) throws ComumNegocioException {
        return localizarServico().obterMensagemDtoPorId(id);
    }

    /**
     * Método responsável por
     * 
     * @param dto void
     * @throws ComumNegocioException
     * 
     */
    public void alterarMensagemDDABoleto(MensagemDDABoletoDto dto) throws ComumNegocioException {
        localizarServico().alterarMensagemDDABoleto(dto);
    }

    /**
     * Método responsável por
     * 
     * @param boletoLegadoDto
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    public void inserirMensagemDDABoletoGeraBoleto(BoletoLegadoDto boletoLegadoDto) throws ComumException, ComumNegocioException {
        localizarServico().inserirMensagemDDABoletoGeraBoleto(boletoLegadoDto);
    }

    /**
     * Método responsável por
     * 
     * @param boletoLegadoDto
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    public void inserirMensagemDDABoletoSicoobNetEmpresarial(BoletoLegadoDto boletoLegadoDto) throws ComumException, ComumNegocioException {
        localizarServico().inserirMensagemDDABoletoSicoobNetEmpresarial(boletoLegadoDto);
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABoletoFiltroDto
     * 
     */
    public MensagemDDABoletoFiltroDto listarCombos() {
        return localizarServico().listarCombos();
    }

}
