/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         ManutencaoMensagemDDABoletoServicoTest.java
 * Data Criação:    Aug 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDescontoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoGrupoCalculoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ManutencaoMensagemDDABoletoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.ServicosTestUtil;

/**
 * ManutencaoMensagemDDABoletoServicoTest é responsável por
 * 
 * @author tayrone.oliveira
 */
@RunWith(PowerMockRunner.class)
public class ManutencaoMensagemDDABoletoServicoTest extends ServicosTestUtil {

    @InjectMocks
    private ManutencaoMensagemDDABoletoServicoEJB ejb;

    // PUBLIC METHODS =========================================================
    /**
     * Método responsável por void
     * 
     */
    @Test
    public void obterMensagemDDABoletoPorId() {
        assertEquals(Constantes.TESTE_SUCESSO, obterMensagemDDABoletoPorId(Boolean.TRUE));
    }

    @Test
    public void listarMensagemDDABoletoDtoPaginado() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(ejb.listarMensagemDDABoletoDtoPaginado(new MensagemDDABoletoFiltroDto())));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumNegocioException void
     * 
     */
    @Test
    public void obterMensagemDtoPorId() throws ComumNegocioException {
        assertEquals(Constantes.TESTE_SUCESSO, obterMensagemDtoPorId(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumNegocioException void
     * 
     */
    @Test
    public void alterarMensagemDDABoleto() throws ComumNegocioException {
        assertEquals(Constantes.TESTE_SUCESSO, alterarMensagemDDABoleto(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
        // assertEquals("integracaocip.erro.tamanho.numero.invalido.db2", alterarMensagemDDABoleto(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE));
        // assertEquals("integracaocip.erro.tamanho.numero.invalido.db2", alterarMensagemDDABoleto(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void listarCombos() {
        assertEquals(Constantes.TESTE_SUCESSO, listarCombos(Boolean.TRUE));
    }

    // PRIVATE METHODS ========================================================
    /**
     * Método responsável por void
     * 
     */
    @Test
    public void validarDadosGrupoCalculo() {
        MensagemDDABoletoGrupoCalculoDto grupo = new MensagemDDABoletoGrupoCalculoDto();
        grupo.setValorTotalCobrado(Constantes.CEM);
        MensagemDDABoletoDto dto = new MensagemDDABoletoDto();
        dto.setListDadosGrupoCalculo(new ArrayList<MensagemDDABoletoGrupoCalculoDto>());
        dto.getListDadosGrupoCalculo().add(grupo);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosGrupoCalculo(dto));
        dto.getListDadosGrupoCalculo().clear();
        grupo.setDataValidadeCalculo(Constantes.DATE_TIME_DB_AUX);
        dto.getListDadosGrupoCalculo().add(grupo);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosGrupoCalculo(dto));
        dto.getListDadosGrupoCalculo().clear();
        grupo.setJuros(Constantes.CEM);
        dto.getListDadosGrupoCalculo().add(grupo);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosGrupoCalculo(dto));
        dto.getListDadosGrupoCalculo().clear();
        grupo.setMulta(Constantes.CEM);
        dto.getListDadosGrupoCalculo().add(grupo);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosGrupoCalculo(dto));
        dto.getListDadosGrupoCalculo().clear();
        grupo.setDesconto(Constantes.CEM);
        grupo.setValorTotalCobrado(null);
        dto.getListDadosGrupoCalculo().add(grupo);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosGrupoCalculo(dto));
        dto.getListDadosGrupoCalculo().clear();
        grupo.setValorTotalCobrado(Constantes.CEM);
        dto.getListDadosGrupoCalculo().add(grupo);
        assertEquals(Constantes.TESTE_SUCESSO, validarDadosGrupoCalculo(dto));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void validarDadosBoleto() {
        MensagemDDABoletoDto dto = new MensagemDDABoletoDto();
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setNumCodigoDeBarras(Constantes.STRING_NUMERO_10);
        assertEquals("integracaocip.linha.digitavel.codigo.barras.tamanho.invalido", validarDadosBoleto(dto));
        dto.setNumCodigoDeBarras(Constantes.COD_BARRAS_39_VALIDO);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setNumLinhaDigitavel(Constantes.STRING_NUMERO_10);
        assertEquals("integracaocip.linha.digitavel.codigo.barras.tamanho.invalido", validarDadosBoleto(dto));
        dto.setNumLinhaDigitavel(Constantes.NUM_LINHA_DIGITAVEL);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setIdCarteira(Constantes.INTEGER_UM);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setCodMoeda(Constantes.STRING_NUMERO_10);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setIdOrgaoMoeda(Constantes.INTEGER_UM);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setDataVencimento(Constantes.DATE_TIME_DB_AUX);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setValorDoBoleto(Constantes.CEM);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setDataEmissao(Constantes.DATE_TIME_DB_AUX);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setDataLimitePgto(Constantes.DATE_TIME_DB_AUX);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setBolTituloNegociado("S");
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setBolBloqueioPagamento("S");
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setBolPagamentoParcial("S");
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setValorAbatimento(Constantes.CEM);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setTipoModeloDeCalculo(Constantes.STRING_NUMERO_1);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setCodAutorizacaoValorDivergente(Constantes.STRING_NUMERO_0);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setIdEspecieDocumento(Constantes.INTEGER_UM);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBoleto(dto));
        dto.setCodTipoPagamento(Constantes.INTEGER_UM);
        assertEquals(Constantes.TESTE_SUCESSO, validarDadosBoleto(dto));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void validarDadosBeneficiario() {
        MensagemDDABoletoDto dto = new MensagemDDABoletoDto();
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBeneficiario(dto));
        dto.setCodTipoPessoaBeneficiario(TipoPessoaEnum.PF.getCodDominioCip());
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBeneficiario(dto));
        dto.setNumCnpjCpfBeneficiario(Constantes.CNPJ_AUX);
        assertEquals(ERRO_TIPO_PESSOA, validarDadosBeneficiario(dto));
        dto.setNumCnpjCpfBeneficiario(Constantes.CPF_AUX);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosBeneficiario(dto));
        dto.setNomeBeneficiario(Constantes.NOME_BANCO);
        assertEquals(Constantes.TESTE_SUCESSO, validarDadosBeneficiario(dto));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void validarDadosBeneficiarioFinal() {
        MensagemDDABoletoDto dto = new MensagemDDABoletoDto();
        dto.setCodTipoPessoaBeneficiarioFinal(TipoPessoaEnum.PF.getCodDominioCip());
        dto.setNumCnpjCpfBeneficiarioFinal(Constantes.CNPJ_AUX);
        assertEquals(ERRO_TIPO_PESSOA, validarDadosBeneficiarioFinal(dto));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void validarDadosPagador() {
        MensagemDDABoletoDto dto = new MensagemDDABoletoDto();
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosPagador(dto));
        dto.setCodTipoPessoaPagador(TipoPessoaEnum.PF.getCodDominioCip());
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosPagador(dto));
        dto.setNumCnpjCpfPagador(Constantes.CNPJ_AUX);
        assertEquals(ERRO_TIPO_PESSOA, validarDadosPagador(dto));
        dto.setNumCnpjCpfPagador(Constantes.CPF_AUX);
        assertEquals(ERRO_PREENCHIMENTO_OBRIGATORIO, validarDadosPagador(dto));
        dto.setNomePagador(Constantes.NOME_BANCO);
    }

    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public void construirObjetoMensagemDDABoleto() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, construirObjetoMensagemDDABoleto(Boolean.TRUE));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void validarDadosMulta() {
        MensagemDDABoletoDto dto = new MensagemDDABoletoDto();
        dto.setValorMulta(Constantes.CEM);
        assertEquals(Constantes.TESTE_FALHA, validarDadosMulta(dto));
        dto.setValorMulta(null);
        dto.setCodTipoMulta(Constantes.INTEGER_UM);
        assertEquals(Constantes.TESTE_FALHA, validarDadosMulta(dto));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void validarDadosJuros() {
        MensagemDDABoletoDto dto = new MensagemDDABoletoDto();
        dto.setValorJuros(Constantes.CEM);
        assertEquals(Constantes.TESTE_FALHA, validarDadosJuros(dto));
        dto.setValorJuros(null);
        dto.setCodTipoJuros(Constantes.INTEGER_UM);
        assertEquals(Constantes.TESTE_FALHA, validarDadosJuros(dto));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void isTipoIgualDocumento() {
        assertEquals(Constantes.TESTE_FALHA, isTipoIgualDocumento("F", Constantes.STRING_NUMERO_10));
        assertEquals(Constantes.TESTE_FALHA, isTipoIgualDocumento("F", Constantes.CPF_INVALIDO_AUX));
        assertEquals(Constantes.TESTE_FALHA, isTipoIgualDocumento("J", Constantes.STRING_NUMERO_10));
        assertEquals(Constantes.TESTE_FALHA, isTipoIgualDocumento("J", Constantes.CNPJ_INVALIDO_AUX));
        assertEquals(Constantes.TESTE_FALHA, isTipoIgualDocumento(null, Constantes.CPF_INVALIDO_AUX));
        assertEquals(Constantes.TESTE_FALHA, isTipoIgualDocumento(null, Constantes.CNPJ_INVALIDO_AUX));
        assertEquals(Constantes.TESTE_FALHA, isTipoIgualDocumento(null, Constantes.STRING_NUMERO_1));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void testValidarDadosMultaMoraDesconto() {
        assertEquals(Constantes.TESTE_FALHA, validarDadosMultaMoraDesconto(Constantes.INTEGER_ZERO, BigDecimal.valueOf(Constantes.INTEGER_CEM), "Juros"));
        assertEquals(Constantes.TESTE_FALHA, validarDadosMultaMoraDesconto(5, null, "Juros"));
        assertEquals(Constantes.TESTE_FALHA, validarDadosMultaMoraDesconto(Constantes.INTEGER_ZERO, BigDecimal.valueOf(Constantes.INTEGER_CEM), "Multa"));
        assertEquals(Constantes.TESTE_FALHA, validarDadosMultaMoraDesconto(3, null, "Multa"));
        assertEquals(Constantes.TESTE_FALHA, validarDadosMultaMoraDesconto(null, BigDecimal.valueOf(Constantes.INTEGER_CEM), "Desconto"));
        assertEquals(Constantes.TESTE_FALHA, validarDadosMultaMoraDesconto(Constantes.INTEGER_ZERO, null, "Desconto"));
    }

    // AUXILIARS ==============================================================
    /**
     * Método responsável por
     * 
     * @param success
     * @return String
     * 
     */
    private String obterMensagemDDABoletoPorId(Boolean success) {
        whenBoletoCipDao(Boolean.TRUE, Boolean.TRUE);
        ejb.obterMensagemDDABoletoPorId(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @return
     * @throws ComumNegocioException String
     * 
     */
    private String obterMensagemDtoPorId(Boolean success) throws ComumNegocioException {
        whenBoletoCipDao(success, success);
        ejb.obterMensagemDtoPorId(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @return
     * @throws ComumNegocioException String
     * 
     */
    private String alterarMensagemDDABoleto(Boolean success, Boolean erroUm, Boolean erroDois) throws ComumNegocioException {
        whenBoletoCipDao(success, success);
        MensagemDDABoletoDto param = ejb.obterMensagemDtoPorId(Constantes.LONG_UM);
        String retorno = null;
        whenEm(Boolean.TRUE);
        if (erroUm) {
            param.setDiasDeProtesto(40000);
            param.setIdOrgaoMoeda(Constantes.INTEGER_UM);
        }
        if (erroDois) {
            param.setDiasDeProtesto(Constantes.INTEGER_UM);
            param.setIdOrgaoMoeda(40000);
        }
        try {
            ejb.alterarMensagemDDABoleto(param);
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String validarDadosGrupoCalculo(MensagemDDABoletoDto dto) {
        try {
            privateMethodsException(ejb, "validarDadosGrupoCalculo", dto);
        } catch (Exception e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String validarDadosBoleto(MensagemDDABoletoDto dto) {
        try {
            privateMethodsException(ejb, "validarDadosBoleto", dto);
        } catch (Exception e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String validarDadosBeneficiario(MensagemDDABoletoDto dto) {
        try {
            privateMethodsException(ejb, "validarDadosBeneficiario", dto);
        } catch (Exception e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String validarDadosBeneficiarioFinal(MensagemDDABoletoDto dto) {
        String retorno = null;
        try {
            privateMethodsException(ejb, "validarDadosBeneficiarioFinal", dto);
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String validarDadosPagador(MensagemDDABoletoDto dto) {
        String retorno = null;
        try {
            privateMethodsException(ejb, "validarDadosPagador", dto);
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @return
     * @throws Exception String
     * 
     */
    private String construirObjetoMensagemDDABoleto(Boolean success) throws Exception {
        whenBoletoCipDao(success, success);
        whenEm(Boolean.TRUE);
        MensagemDDABoletoDto dto = ejb.obterMensagemDtoPorId(Constantes.LONG_UM);
        MensagemDDABoletoDescontoDto desc = new MensagemDDABoletoDescontoDto();
        desc.setCodTipoDesconto(Constantes.STRING_LETRA_S);
        dto.getListDadosDesconto().add(desc);
        dto.setCodTipoJuros(Constantes.INTEGER_UM);
        dto.setCodTipoMulta(Constantes.INTEGER_CEM);
        MensagemDDABoletoGrupoCalculoDto grupo = new MensagemDDABoletoGrupoCalculoDto();
        grupo.setDataValidadeCalculo(Constantes.DATE_TIME_DB_AUX);
        dto.getListDadosGrupoCalculo().add(grupo);

        MensagemDDABoleto msg = Whitebox.invokeMethod(ejb, "construirObjetoMensagemDDABoleto", dto);
        return ObjectUtil.isNull(msg) ? Constantes.TESTE_FALHA : Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @return String
     * 
     */
    private String listarCombos(Boolean success) {
        whenManutencaoMensagemDDABoletoDao(success);
        ejb.listarCombos();

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String validarDadosMulta(MensagemDDABoletoDto dto) {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, "validarDadosMulta", dto);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String validarDadosJuros(MensagemDDABoletoDto dto) {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, "validarDadosJuros", dto);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param codTipoPessoa
     * @param numCpfCnpj
     * @return String
     * 
     */
    private String isTipoIgualDocumento(String codTipoPessoa, String numCpfCnpj) {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, "isTipoIgualDocumento", codTipoPessoa, numCpfCnpj, Constantes.NOME_BANCO);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param codTipo
     * @param valor
     * @param nomeCampo
     * @return String
     * 
     */
    private String validarDadosMultaMoraDesconto(Integer codTipo, BigDecimal valor, String nomeCampo) {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, "validarDadosMultaMoraDesconto", codTipo, valor, nomeCampo);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

}
