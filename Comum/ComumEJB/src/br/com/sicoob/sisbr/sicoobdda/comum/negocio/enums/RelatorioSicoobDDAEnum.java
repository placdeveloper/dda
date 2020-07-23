/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         RelatorioSicoobDDAEnum.java
 * Data Cria��o:    Abril 06, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * RelatorioCobrancaEnum � respons�vel por catalogar todos relat�rios do Cobran�a Banc�ria. Devem estar id�nticos na tabela SpoolRelatorios DB2 esquema PRT
 * 
 * @author Jesliel.Rocha
 */
public enum RelatorioSicoobDDAEnum {

    // SDDA300 a SDDA499 -> Atendimento
    // SDDA500 a SDDA699 -> Operacional
    // SDDA700 a SDDA899 -> Gest�o
    // SDDA900 a SDDA999 -> Historicos

    // --------------------------------------------------------- ATENDIMENTO ----------------------------------------------------------------------------------
    SDDA300("", "", ""),

    // --------------------------------------------------------- OPERACIONAL ----------------------------------------------------------------------------------
    // @formatter:off
    SDDA501("Monitoramento de Mensagens da CIP", "relatorioDDAMonitoramentoMensagemSDDA.jasper", "Monitoramento_Mensagem"),
    // SDDA502("Monitoramento de Arquivos da CIP", "relatorioDDAMonitoramentoArquivoSDDA.jasper", "Monitoramento_Arquivo"), DESCONTINUADO
    SDDA503("Relat�rio de Conting�ncia", "relatorioHistoricoContingenciasSDDA.jasper", "Relatorio_Historico_Contingencias"),
    SDDA504("Relat�rio Benefici�rios em Alerta", "relatorioBeneficiariosAlertaSDDA.jasper", "Relatorio_Beneficiarios_Alerta"),
    SDDA505("Sicoob - SicoobDDA", "relatorioTermoPagadorEletronicoSDDA.jasper", "Relatorio_Termo_Pagador_Eletronico"),
    SDDA506("Detalhe Boleto", "relatorioDetalheBoletoDDA.jasper", "Relatorio_Detalhe_Boleto"),
    SDDA507("Pagador Eletr�nico DDA", "relatorioListaPagadorEletronicoSDDA.jasper", "Relatorio_Lista_Pagador_Eletronico"),
    SDDA508("Pagador Eletr�nico DDA", "relatorioDetalhadoPagadorEletronicoSDDA.jasper", "Relatorio_Detalhado_Pagador_EletronicoDDA"),
    SDDA509("Pagador DDA - Agregados", "relatorioListaPagadorAgregadoEletronicoSDDA.jasper", "Relatorio_Lista_Pagador_Agregado"),
    SDDA510("Consulta Boleto", "relatorioListaBoletoDDASDDA.jasper", "Relatorio_Consulta_Boleto"),
    SDDA511("Consulta Historico Mensagem", "relatorioHistoricoMensagem106SDDA.jasper", "Relatorio_Historico_Mensagem_106"),
    SDDA512("Consulta Evento Tarif�vel", "relatorioListaEventoTarifavelSDDA.jasper", "Relatorio_Lista_Evento_Tarifavel"),
    SDDA513("Consulta Tarifas DDA Pagas", "relatorioTarifasDDAPagasSDDA.jasper", "Relatorio_Tarifas_DDA_Pagas"),
    SDDA514("Detalhamento de Lan�amentos das Tarifas", "relatorioLancamentosTarifasSDDA.jasper", "Relatorio_Lancamentos_Tarifas"),
    SDDA515("Detalhamento do movimento do SicoobDDA", "relatorioMovimentoSicoobDDA.jasper", "Relatorio_Movimento_SicoobDDA"),
    SDDA516("Eventos Dispon�veis para Rateio", "relatorioEventosDisponiveisSDDA.jasper", "Relatorio_Eventos_Disponiveis"),
    SDDA517("Detalhes dos Eventos para Rateio", "relatorioDetalhesRateioSDDA.jasper", "Relatorio_Detalhes_Rateio"),
    SDDA518("Relat�rio Rateio DDA Lancamentos", "relatorioRateioLancamentoSDDA.jasper", "Relatorio_Lista_Lancamento_DDA"),
    SDDA519("Relat�rio Dados Rateio", "relatorioDadosRateioSDDA.jasper", "Relatorio_Lista_Dados_Rateio"),
    SDDA520("Relat�rio Rateio de Tarifas", "relatorioListaRateioTarifasSDDA.jasper", "Relatorio_Rateio_Tarifas"),
    // @formatter:on

    // ----------------------------------------------------------- GEST�O -------------------------------------------------------------------------------------
    SDDA700("", "", ""),

    // ------------------------------------------------ RELATORIOS DE HISTORICOS GERAIS -----------------------------------------------------------------------
    SDDA900("", "", ""),

    // -----------------------------------------------------------------FIM------------------------------------------------------------------------------------
    SDDA999("", "", "");

    
    private String codigoRelatorio;
    private String nomeRelatorio;
    private String arquivoJasper;
    private String contextoFlex;

    /**
     * 
     * 
     * 
     * @param codigoRelatorio
     * @param nomeRelatorio
     * @param arquivoJasper
     * @param contextoFlex
     */
    private RelatorioSicoobDDAEnum(String nomeRelatorio, String arquivoJasper, String contextoFlex) {
        final String sigla = "SDDA";
        if (name().contains(sigla)) {
            String[] codigoSplit = name().split(sigla);
            this.codigoRelatorio = sigla + "-" + codigoSplit[1];
        } else {
            this.codigoRelatorio = name(); // Codigo para o Rodap�
        }
        this.nomeRelatorio = nomeRelatorio; // T�tulo do relat�rio que ir� para o cabe�alho
        this.arquivoJasper = arquivoJasper;
        this.contextoFlex = contextoFlex;
    }

    /**
     * @return o atributo codigoRelatorio
     */
    public String getCodigoRelatorio() {
        return codigoRelatorio;
    }

    /**
     * @return o atributo nomeRelatorio
     */
    public String getNomeRelatorio() {
        return nomeRelatorio;
    }

    /**
     * @return o atributo arquivoJasper
     */
    public String getArquivoJasper() {
        return arquivoJasper;
    }

    /**
     * 
     * M�todo respons�vel por
     * 
     * @return String
     * 
     */
    public String getContextoFlex() {
        return contextoFlex;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param codigoRelatorio
     * @return RelatorioSicoobDDAEnum
     * 
     */
    public static RelatorioSicoobDDAEnum getByCodigo(String codigoRelatorio) {
        for (RelatorioSicoobDDAEnum relatorio : RelatorioSicoobDDAEnum.values()) {
            if (codigoRelatorio.equals(relatorio.getCodigoRelatorio())) {
                return relatorio;
            }
        }
        return null;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param arquivoJasper
     * @return RelatorioSicoobDDAEnum
     * 
     */
    public static RelatorioSicoobDDAEnum getByArquivoJasper(String arquivoJasper) {
        for (RelatorioSicoobDDAEnum relatorio : RelatorioSicoobDDAEnum.values()) {
            if (arquivoJasper.equals(relatorio.getArquivoJasper())) {
                return relatorio;
            }
        }
        return null;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param contextoFlex
     * @return RelatorioSicoobDDAEnum
     * 
     */
    public static RelatorioSicoobDDAEnum getByContextoFlex(String contextoFlex) {
        for (RelatorioSicoobDDAEnum relatorio : RelatorioSicoobDDAEnum.values()) {
            if (contextoFlex.equals(relatorio.getContextoFlex())) {
                return relatorio;
            }
        }
        return null;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param nomeRelatorio
     * @return RelatorioSicoobDDAEnum
     * 
     */
    public static RelatorioSicoobDDAEnum getByNomeRelatorio(String nomeRelatorio) {
        for (RelatorioSicoobDDAEnum relatorio : RelatorioSicoobDDAEnum.values()) {
            if (nomeRelatorio.equals(relatorio.getNomeRelatorio())) {
                return relatorio;
            }
        }
        return null;
    }

}
