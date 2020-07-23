/**
 * Projeto:         IntegracaoCIP
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.dto
 * Arquivo:         BeneficiarioDto.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoConvenioBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoProdutoConvenioEnum;

/**
 * CargaBeneficiarioDDADto
 * 
 * @author Rafael.Silva
 */
public class CargaBeneficiarioDDADto extends BeneficiarioDDADto {

    private static final long serialVersionUID = 3083502111657797792L;

    private List<ConvenioCargaDDADto> listaConvenioCargaDto;

    private List<RepresentanteBeneficiarioDto> listaRepresentanteBeneficiarioDto;

    public CargaBeneficiarioDDADto() {

    }

    /**
     * @param tipoPessoaBeneficiario
     * @param cnpjCpfBeneficiario
     * @param nomeRazaoSocialBeneficiario
     * @param nomeFantasiaBeneficiario
     * @param situacaoBeneficiario
     * @param situacaoRelacionamentoParticipante
     * @param dataHoraInclusaoBeneficiario
     * @param dataHoraExclusaoBeneficiario
     * @param dataInicioRelacionamentoParticipante
     * @param dataFimRelacionamentoParticipante
     * @param tipoCarteira
     * @param banco
     * @param agencia
     */
    public CargaBeneficiarioDDADto(String tipoPessoaBeneficiario, String cnpjCpfBeneficiario, String nomeRazaoSocialBeneficiario, String nomeFantasiaBeneficiario,
            String situacaoBeneficiario, String situacaoRelacionamentoParticipante, Date dataHoraInclusaoBeneficiario, Date dataHoraExclusaoBeneficiario,
            Date dataInicioRelacionamentoParticipante, Date dataFimRelacionamentoParticipante, String tipoCarteira, String banco, String agencia, Short situacaoConvenio) {

        super(tipoPessoaBeneficiario, cnpjCpfBeneficiario, nomeRazaoSocialBeneficiario, nomeFantasiaBeneficiario, situacaoBeneficiario, situacaoRelacionamentoParticipante,
                dataHoraInclusaoBeneficiario, dataHoraExclusaoBeneficiario, dataInicioRelacionamentoParticipante, dataFimRelacionamentoParticipante, tipoCarteira, banco, agencia);

        ConvenioCargaDDADto convenioCargaDto = new ConvenioCargaDDADto();

        convenioCargaDto.setSitucaoConvenioBeneficiarioEnum(SituacaoConvenioBeneficiarioEnum.getBy(situacaoConvenio));
        convenioCargaDto.setDataInicioRelacionamentoConvenio(dataHoraInclusaoBeneficiario);
        convenioCargaDto.setDataFimRelacionamentoConvenio(dataHoraExclusaoBeneficiario);
        convenioCargaDto.setTipoAgenciaDestino(TipoAgenciaEnum.FISICA);
        convenioCargaDto.setAgenciaDestino(agencia != null ? Integer.valueOf(agencia) : null);
        convenioCargaDto.setTipoContaDestino(null);
        convenioCargaDto.setContaDestino(null);
        convenioCargaDto.setTipoProdutoConvenio(TipoProdutoConvenioEnum.BOLETO_DE_COBRANCA);
        convenioCargaDto.setTipoCarteiraConvenioCobranca(getTipoCarteira());
        convenioCargaDto.setCodClienteConvenio(null);

        getListaConvenioCargaDto().add(convenioCargaDto);

    }

    /**
     * @return the listaCargaConvenioDto
     */
    public List<ConvenioCargaDDADto> getListaConvenioCargaDto() {
        if (listaConvenioCargaDto == null) {
            listaConvenioCargaDto = new ArrayList<ConvenioCargaDDADto>();
        }
        return listaConvenioCargaDto;
    }

    /**
     * @param listaCargaConvenioDto the listaCargaConvenioDto to set
     */
    public void setListaConvenioCargaDto(List<ConvenioCargaDDADto> listaConvenioCargaDto) {
        this.listaConvenioCargaDto = listaConvenioCargaDto;
    }

    /**
     * @return the listaRepresentanteBeneficiarioDto
     */
    public List<RepresentanteBeneficiarioDto> getListaRepresentanteBeneficiarioDto() {
        if (listaRepresentanteBeneficiarioDto == null) {
            listaRepresentanteBeneficiarioDto = new ArrayList<RepresentanteBeneficiarioDto>();
        }
        return listaRepresentanteBeneficiarioDto;
    }

    /**
     * @param listaRepresentanteBeneficiarioDto the listaRepresentanteBeneficiarioDto to set
     */
    public void setListaRepresentanteBeneficiarioDto(List<RepresentanteBeneficiarioDto> listaRepresentanteBeneficiarioDto) {
        this.listaRepresentanteBeneficiarioDto = listaRepresentanteBeneficiarioDto;
    }

}
