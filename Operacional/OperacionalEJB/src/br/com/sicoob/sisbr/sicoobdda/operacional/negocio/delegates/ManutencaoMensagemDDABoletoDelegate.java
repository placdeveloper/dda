/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         ManutencaoMensagemDDABoletoDelegate.java
 * Data Cria��o:    Jul 21, 2017
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
 * ManutencaoMensagemDDABoletoDelegate � respons�vel por
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
     * M�todo respons�vel por
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
     * M�todo respons�vel por
     * 
     * @param id
     * @return MensagemDDABoleto
     * 
     */
    public MensagemDDABoleto obterMensagemDDABoletoPorId(Long id) {
        return localizarServico().obterMensagemDDABoletoPorId(id);
    }

    /**
     * M�todo respons�vel por
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
     * M�todo respons�vel por
     * 
     * @param dto void
     * @throws ComumNegocioException
     * 
     */
    public void alterarMensagemDDABoleto(MensagemDDABoletoDto dto) throws ComumNegocioException {
        localizarServico().alterarMensagemDDABoleto(dto);
    }

    /**
     * M�todo respons�vel por
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
     * M�todo respons�vel por
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
     * M�todo respons�vel por
     * 
     * @return MensagemDDABoletoFiltroDto
     * 
     */
    public MensagemDDABoletoFiltroDto listarCombos() {
        return localizarServico().listarCombos();
    }

}
