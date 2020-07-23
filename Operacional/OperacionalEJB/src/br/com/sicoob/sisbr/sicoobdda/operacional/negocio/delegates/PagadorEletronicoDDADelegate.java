package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoPagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * PagadorEletronicoDDADelegate é responsável por
 * 
 * @author George.Santos
 */
@SuppressWarnings("rawtypes")
public class PagadorEletronicoDDADelegate extends OperacionalCrudDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    public PagadorEletronicoDDAServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarPagadorEletronicoDDAServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * Método responsável por verificar se o cliente e um indicador de aceite
     * 
     * @param pagadorEletronicoDDADto
     * @return Boolean
     * @throws OperacionalException
     * 
     */
    public Boolean indicadorAceiteDDA(PagadorEletronicoDDADto pagadorEletronicoDDADto) throws ComumException {
        return localizarServico().indicadorAceiteDDA(pagadorEletronicoDDADto);
    }

    /**
     * Método responsável por obter o Termo de Adesao do DDA pelo TIPO
     * 
     * @param integer
     * 
     * @param pagadorEletronicoDDADto
     * @return TermoAdesaoDDADto
     * @throws OperacionalException
     * 
     */
    public TermoPagadorDto obterTermoAdesaoPeloTipo(Short codTipoTermoPagador, Integer numCooperativa, Integer codTipoRetorno) throws ComumException, ComumNegocioException {
        return localizarServico().obterTermoAdesaoPeloTipo(codTipoTermoPagador, numCooperativa, codTipoRetorno);
    }

    /**
     * Método responsável por incluir a adesao do Sacado ao DDA
     * 
     * @param adesaoDDA void
     * @throws OperacionalException
     * 
     */
    public TermoPagadorDto incluirAdesaoDDA(PagadorEletronicoDDADto pagadorEletronicoDDADto) throws ComumException, ComumNegocioException {
        return localizarServico().incluirAdesaoDDA(pagadorEletronicoDDADto);
    }

    /**
     * Método responsável por obter o Termo de Adesao do DDA pelo SACADO
     * 
     * @param numCpfCNpj
     * 
     * @param idHistoricoTermo
     * 
     * @param pagadorEletronicoDDADto
     * @return TermoAdesaoDDADto
     * @throws OperacionalException
     * 
     */
    public TermoPagadorDto obterTermoAdesaoPeloSacado(Integer idHistoricoTermoPagador, Long numContaCorrente, Integer numCooperativa, Integer idResponsavelTitular,
            String numCpfCNpj)
            throws ComumException, ComumNegocioException {
        return localizarServico().obterTermoAdesaoPeloSacado(idHistoricoTermoPagador, numContaCorrente, numCooperativa, idResponsavelTitular, numCpfCNpj);
    }

    /**
     * Método responsável por cancelar a adesao do Sacado ao DDA
     * 
     * @param pagadorEletronicoDDADto void
     * @throws OperacionalException
     * 
     */
    public TermoPagadorDto cancelarAdesaoDDA(PagadorEletronicoDDADto pagadorEletronicoDDADto) throws ComumNegocioException, ComumException {
        return localizarServico().cancelarAdesaoDDA(pagadorEletronicoDDADto);

    }

    /**
     * Método responsável por listar os comprovantes (Adesao/Cancelamento) para o Sacado
     * 
     * @param pagadorEletronicoDDADto
     * @return PagadorEletronicoDDADto
     * @throws OperacionalException
     * 
     */
    public List<PagadorEletronicoDDADto> listarComprovanteAdesaoDDA(Long numContaCorrente, Integer numCooperativa, Integer idResponsavelTitular, String numCpfCnpj)
            throws ComumException, ComumNegocioException {
        return localizarServico().listarComprovanteAdesaoDDA(numContaCorrente, numCooperativa, idResponsavelTitular, numCpfCnpj);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#isCpfCnpjPagadorEletronico(java.lang.Character,
     *      java.lang.String)
     */
    public boolean isCpfCnpjPagadorEletronico(Character tipoPessoa, String cpfCnpj, boolean isPagadorSicoob) throws ComumException, ComumNegocioException {
        return localizarServico().isCpfCnpjPagadorEletronico(tipoPessoa, cpfCnpj, isPagadorSicoob);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#manterDDATerceiro(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto)
     */
    public boolean manterDDATerceiro(DDATerceiroDto dto) throws ComumException, ComumNegocioException {
        return localizarServico().manterDDATerceiro(dto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#atualizarSacadoEletronico(java.lang.Integer)
     */
    public void atualizarSacadoEletronico(Integer numCooperativa) throws ComumException {
        localizarServico().atualizarSacadoEletronico(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#obterDadosPagador(java.lang.String)
     */
    public PagadorDto obterDadosPagador(String numCpfCnpj, Integer idInstituicao) throws ComumNegocioException, ComumException {
        return localizarServico().obterDadosPagador(numCpfCnpj, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#obterDadosPagador(Long idPagador)
     */
    public PagadorDto obterDadosPagador(String numCpfCnpj) throws ComumNegocioException, ComumException {
        return localizarServico().obterDadosPagador(numCpfCnpj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#solicitarAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto)
     */
    public PagadorDto solicitarAdesao(PagadorDto pagadorDto, Short idCanal, Boolean isRetornoTermo) throws ComumNegocioException, ComumException {
        return localizarServico().solicitarAdesao(pagadorDto, idCanal, isRetornoTermo);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#solicitarCancelamentoAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto)
     */
    public PagadorDto solicitarCancelamentoAdesao(PagadorDto pagadorDto, Short idCanal, Boolean isRetornoTermo) throws ComumNegocioException, ComumException {
        return localizarServico().solicitarCancelamentoAdesao(pagadorDto, idCanal, isRetornoTermo);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#solicitarInclusaoPagadorAgregado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto,
     *      java.lang.String, java.lang.Integer)
     */
    public PagadorDto solicitarInclusaoPagadorAgregado(PagadorAgregadoDto pagadorAgregadoDto, String numCpfCnpjPagador, Integer idInstituicao, Short idCanal)
            throws ComumException, ComumNegocioException {
        return localizarServico().solicitarInclusaoPagadorAgregado(pagadorAgregadoDto, numCpfCnpjPagador, idInstituicao, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#solicitarExclusaoPagadorAgregado(java.util.List,
     *      java.lang.String, java.lang.Integer)
     */
    public PagadorDto solicitarExclusaoPagadorAgregado(PagadorAgregadoDto pagadorAgregadoDto, String numCpfCnpjPagador, Integer idInstituicao, Short idCanal)
            throws ComumException, ComumNegocioException {
        return localizarServico().solicitarExclusaoPagadorAgregado(pagadorAgregadoDto, numCpfCnpjPagador, idInstituicao, idCanal);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#listarHistoricoPagadorEletronico(java.lang.String)
     */
    public List<HistoricoPagadorEletronicoDto> listarHistoricoPagadorEletronico(String numCpfCnpjPagador, Integer idInstituicao) throws OperacionalException {
        return localizarServico().listarHistoricoPagadorEletronico(numCpfCnpjPagador, idInstituicao);
    }

    /**
     * Método responsável por
     * 
     * @param dataInicioVigencia
     * @param dataFimVigencia
     * @param codTipoTermoPagador
     * @param bolFormatoHtml
     * @param numCooperativa
     * @return
     * @throws OperacionalException TermoPagadorDto
     * 
     */
    public TermoPagadorDto obterTermoPagadorEletronico(DateTimeDB dataInicioVigencia, DateTimeDB dataFimVigencia, Short codTipoTermoPagador, Boolean bolFormatoHtml,
            Integer numCooperativa) throws OperacionalException {
        return localizarServico().obterTermoPagadorEletronico(dataInicioVigencia, dataFimVigencia, codTipoTermoPagador, bolFormatoHtml, numCooperativa);
    }

    /**
     * Método responsável por
     * 
     * @param historicoPagdorEletroDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioTermoPagadorEletronico(HistoricoPagadorEletronicoDto historicoPagdorEletroDto, UsuarioBancoobDTO usuario)
            throws BancoobException {
        return localizarServico().configurarRelatorioTermoPagadorEletronico(historicoPagdorEletroDto, usuario);
    }

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioListaPagadorEletronico(Integer numCooperativa, UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioListaPagadorEletronico(numCooperativa, usuario);
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioDetalhePagadorEletronico(String numCpfCnpj, UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioDetalhePagadorEletronico(numCpfCnpj, usuario);
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param numCooperativa
     * @param codCentral
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioPagadorAgregado(String numCpfCnpj, Integer numCooperativa, Integer codCentral, UsuarioBancoobDTO usuario)
            throws BancoobException {
        return localizarServico().configurarRelatorioPagadorAgregado(numCpfCnpj, numCooperativa, codCentral, usuario);
    }

}
