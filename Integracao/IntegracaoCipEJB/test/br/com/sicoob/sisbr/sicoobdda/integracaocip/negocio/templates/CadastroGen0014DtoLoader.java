/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         CadastroGen0014DtoLoader.java
 * Data Cria��o:    Jan 12, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroGen0014Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoArquivoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoArquivoBoletoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoArquivoPagadorEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBoletoConsultado;

/**
 * CadastroGen0014DtoLoader � respons�vel por
 * 
 * @author felipe.rosa
 */
public final class CadastroGen0014DtoLoader {

    /**
     * 
     */
    private CadastroGen0014DtoLoader() {

    }

    /**
     * M�todo respons�vel por
     * 
     * @return CadastroGen0014Dto
     * 
     */
    public static CadastroGen0014Dto geraCadastroBeneficiario() {
        return geraCadastroGen0014Dto(TipoSolicitacaoArquivoBeneficiarioEnum.CARGA_INICIAL.getCodDominio(), SituacaoBeneficiarioEnum.APTO.getCodDominio(), null, null, null);
    }

    /**
     * M�todo respons�vel por
     * 
     * @return CadastroGen0014Dto
     * 
     */
    public static CadastroGen0014Dto geraCadastroPagador() {
        return geraCadastroGen0014Dto(null, null, TipoSolicitacaoArquivoPagadorEnum.CARGA_INICIAL.getCodDominio(), null, null);
    }

    /**
     * M�todo respons�vel por
     * 
     * @return CadastroGen0014Dto
     * 
     */
    public static CadastroGen0014Dto geraCadastroTitulo() {
        return geraCadastroGen0014Dto(null, null, null, TipoSolicitacaoArquivoBoletoEnum.INVENTARIO_BLOQUETOS.getCodDominio(), TipoBoletoConsultado.PROPRIO);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param codTipoSolBeneficiario
     * @param codSituacaoBeneficiario
     * @param codTipoSolPagador
     * @param codTipoSolBoleto
     * @param codTipoBoletoConsultado
     * @return CadastroGen0014Dto
     * 
     */
    public static CadastroGen0014Dto geraCadastroGen0014Dto(String codTipoSolBeneficiario, String codSituacaoBeneficiario, String codTipoSolPagador, String codTipoSolBoleto,
            String codTipoBoletoConsultado) {
        CadastroGen0014Dto dto = new CadastroGen0014Dto();
        dto.setCodTipoSolBeneficiario(codTipoSolBeneficiario);
        dto.setCodSituacaoBeneficiario(codSituacaoBeneficiario);
        dto.setCodTipoSolPagador(codTipoSolPagador);
        dto.setCodTipoSolBoleto(codTipoSolBoleto);
        dto.setCodTipoBoletoConsultado(codTipoBoletoConsultado);
        return dto;
    }
}
