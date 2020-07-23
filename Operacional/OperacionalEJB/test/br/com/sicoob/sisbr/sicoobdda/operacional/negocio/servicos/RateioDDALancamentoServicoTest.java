package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.RateioDDALancamentoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao;
import junit.framework.Assert;

/**
 * RateioDDALancamentoServicoTest é responsável por
 * 
 * @author Rodrigo.Neri
 */
@RunWith(MockitoJUnitRunner.class)
public class RateioDDALancamentoServicoTest extends Mockito {

    @InjectMocks
    private RateioDDALancamentoServicoEJB ejb;

    @Mock
    private RateioDDALancamentoDao dao;

    /**
     * Método responsável por testar
     */
    @Test
    public void listarLancamentosTarifasPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarLancamentosTarifas(Constantes.LONG_DOIS));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void listarLancamentosTarifasIdNulo() {
        Assert.assertEquals("integracaocip.parametro.nao.informado", listarLancamentosTarifas(null));
    }

    /**
     * Método responsável por
     * 
     * @param id
     * @return
     */
    private String listarLancamentosTarifas(Long id) {
        try {
            ejb.listarLancamentosTarifas(id);
            verify(dao, times(1)).listarLancamentosTarifas(id);
        } catch (BancoobException e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por testar
     * 
     * @throws BancoobException
     */
    @Test
    public void pesquisarMovimentoPaginado() throws BancoobException {
        ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto = criarConsultaDto();

        Assert.assertEquals(Constantes.TESTE_SUCESSO, pesquisarMovimentoPaginado(consultaDto));
    }

    /**
     * Método responsável por testar
     * 
     * @throws BancoobException
     */
    @Test
    public void pesquisarMovimentoPaginadoConsultaNula() throws BancoobException {
        Assert.assertEquals("integracaocip.parametro.nao.informado", pesquisarMovimentoPaginado(null));
    }

    /**
     * Método responsável por testar
     * 
     * @throws BancoobException
     */
    @Test
    public void pesquisarMovimentoPaginadoConsultaTamanhoPaginaNulo() throws BancoobException {
        ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto = criarConsultaDto();
        consultaDto.setTamanhoPagina(null);

        Assert.assertEquals("integracaocip.campo.obrigatorio", pesquisarMovimentoPaginado(consultaDto));
    }

    /**
     * Método responsável por testar
     * 
     * @throws BancoobException
     */
    @Test
    public void pesquisarMovimentoPaginadoConsultaTamanhoPaginaZero() throws BancoobException {
        ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto = criarConsultaDto();
        consultaDto.setTamanhoPagina(Constantes.INTEGER_ZERO);

        Assert.assertEquals("integracaocip.campo.obrigatorio", pesquisarMovimentoPaginado(consultaDto));
    }

    /**
     * Método responsável por testar
     * 
     * @throws BancoobException
     */
    @Test
    public void pesquisarMovimentoPaginadoConsultaPaginaNula() throws BancoobException {
        ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto = criarConsultaDto();
        consultaDto.setPagina(null);

        Assert.assertEquals("integracaocip.campo.obrigatorio", pesquisarMovimentoPaginado(consultaDto));
    }

    /**
     * Método responsável por testar
     * 
     * @throws BancoobException
     */
    @Test
    public void pesquisarMovimentoPaginadoConsultaFiltroNulo() throws BancoobException {
        ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto = criarConsultaDto();
        consultaDto.setFiltro(null);

        Assert.assertEquals("integracaocip.campo.obrigatorio", pesquisarMovimentoPaginado(consultaDto));
    }

    /**
     * Método responsável por testar
     * 
     * @throws BancoobException
     */
    @Test
    public void pesquisarMovimentoPaginadoConsultaFiltroInvalido() throws BancoobException {
        ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto = criarConsultaDto();
        consultaDto.setFiltro("");

        Assert.assertEquals("integracaocip.filtro.tipo.invalido", pesquisarMovimentoPaginado(consultaDto));
    }

    /**
     * Método responsável por testar
     * 
     * @throws BancoobException
     */
    @Test
    public void pesquisarMovimentoPaginadoConsultaCodBarrasTamanhoInvalido() throws BancoobException {
        ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto = criarConsultaDto();
        ConsultaMovimentoSicoobDDADto filtro = (ConsultaMovimentoSicoobDDADto) consultaDto.getFiltro();
        filtro.setNumCodBarras(Constantes.STRING_NUMERO_1);

        Assert.assertEquals("integracaocip.linha.digitavel.codigo.barras.tamanho.invalido", pesquisarMovimentoPaginado(consultaDto));
    }

    /**
     * Método responsável por testar
     * 
     * @throws BancoobException
     */
    @Test
    public void pesquisarMovimentoPaginadoConsultaCodBarrasInvalido() throws BancoobException {
        ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto = criarConsultaDto();
        ConsultaMovimentoSicoobDDADto filtro = (ConsultaMovimentoSicoobDDADto) consultaDto.getFiltro();
        filtro.setNumCodBarras("1234567890123456789012345678901234567890AAAA");

        Assert.assertEquals("integracaocip.linha.digitavel.codigo.barras.invalido", pesquisarMovimentoPaginado(consultaDto));
    }

    /**
     * Método responsável por criar o dto
     * 
     * @return
     */
    private ConsultaDto<ConsultaMovimentoSicoobDDADto> criarConsultaDto() {
        ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto = new ConsultaDto<ConsultaMovimentoSicoobDDADto>();
        consultaDto.setFiltro(new ConsultaMovimentoSicoobDDADto());
        consultaDto.setPagina(Constantes.INTEGER_ZERO);
        consultaDto.setTamanhoPagina(Constantes.INTEGER_DEZ);
        return consultaDto;
    }

    /**
     * Método responsável por
     * 
     * @param consultaDto
     */
    private String pesquisarMovimentoPaginado(ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto) {
        try {
            ConsultaMovimentoSicoobDDADto consultaMovimentoSicoobDDADto = null;

            if (!ObjectUtil.isNull(consultaDto) && !ObjectUtil.isNull(consultaDto.getFiltro()) && consultaDto.getFiltro() instanceof ConsultaMovimentoSicoobDDADto) {
                consultaMovimentoSicoobDDADto = (ConsultaMovimentoSicoobDDADto) consultaDto.getFiltro();

                when(dao.countMovimento(consultaMovimentoSicoobDDADto)).thenReturn(Constantes.INTEGER_CEM);
            }

            ejb.pesquisarMovimentoPaginado(consultaDto);

            verify(dao, times(1)).countMovimento(consultaMovimentoSicoobDDADto);
            verify(dao, times(1)).pesquisarMovimentoPaginado(consultaMovimentoSicoobDDADto, consultaDto.getPagina(), consultaDto.getTamanhoPagina());
        } catch (Exception e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

}
