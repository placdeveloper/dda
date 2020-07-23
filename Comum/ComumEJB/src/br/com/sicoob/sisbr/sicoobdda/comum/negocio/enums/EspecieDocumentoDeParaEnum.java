/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         EspecieDocumentoDeParaEnum.java
 * Data Criação:    Aug 29, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * EspecieDocumentoDeParaEnum
 * 
 * @author rafael.silva
 */
public enum EspecieDocumentoDeParaEnum {
    CHEQUE(1, 1, "CH", "Cheque"),
    DUPLICATA_MERCANTIL(2, 2, "DM", "Duplicata Mercantil"),
    DUPLICATA_MERCANTIL_POR_INDICACAO(3, 3, "DMI", "Duplicata Mercantil Por Indicação"),
    DUPLICATA_DE_SERVICO(4, 4, "DS", "Duplicata de Serviço"),
    DUPLICATA_DE_SERVICO_POR_INDICACAO(5, 5, "DSI", "Duplicata de Serviço Por Indicação"),
    DUPLICATA_RURAL(6, 6, "DR", "Duplicata Rural"),
    LETRA_CAMBIO(7, 7, "LC", "Letra de Câmbio"),
    NOTA_CREDITO_COMERCIAL(8, 8, "NCC", "Nota de Crédito Comercial"),
    NOTA_CREDITO_EXPORTACAO(9, 9, "NCE", "Nota de Crédito a Exportação"),
    NOTA_CREDITO_INDUSTRIAL(10, 10, "NCI", "Nota de Crédito Industrial"),
    NOTA_CREDITO_RURAL(11, 11, "NCR", "Notal de Crédito Rural"),
    NOTA_PROMISSORIA(12, 12, "NP", "Nota Promissória"),
    NOTA_PROMISSORIA_RURAL(13, 13, "NPR", "Nota Promissória Rural"),
    TRIPLICATA_MERCANTIL(14, 14, "TM", "Triplicata Mercantil"),
    TRIPLICATA_DE_SERVICO(15, 15, "TS", "Triplicata de Serviço"),
    NOTA_SEGURO(16, 16, "NS", "Nota de Seguro"),
    RECIBO(17, 17, "RC", "Recibo"),
    FATURA(18, 18, "FAT", "Fatura"),
    NOTA_DEBITO(19, 19, "ND", "Nota de Débito"),
    APOLICE_SEGURO(20, 20, "AP", "Apólice de Seguro"),
    MENSALIDADE_ESCOLAR(21, 21, "ME", "Mensalidade Escolar"),
    PARCELA_CONSORCIO(22, 22, "PC", "Parcela de Consórcio"),
    NOTA_FISCAL(23, 23, "NF", "Nota Fiscal"),
    DOCUMENTO_DIVIDA(24, 24, "DD", "Documento de Dívida"),
    CEDULA_PRODUTO_RURAL(25, 25, "CPR", "Cédula de Produto Rural "),
    CARTAO_CREDITO(31, 31, "CC", "Cartão de Crédito"),
    BOLETO_PROPOSTA(32, 32, "BDP", "Boleto de Proposta"),
    OUTROS(99, 99, "OU", "Outros");

    private EspecieDocumentoDeParaEnum(int idEspecieDocumentoCob, int idEspecieDocumentoCip, String siglaEspecieDocumentoCob, String descEspecieDocumentoCob) {
        this.idEspecieDocumentoCob = idEspecieDocumentoCob;
        this.idEspecieDocumentoCip = idEspecieDocumentoCip;
        this.siglaEspecieDocumentoCob = siglaEspecieDocumentoCob;
        this.descEspecieDocumentoCob = descEspecieDocumentoCob;
    }

    private final int idEspecieDocumentoCob;
    private final int idEspecieDocumentoCip;
    private final String siglaEspecieDocumentoCob;
    private final String descEspecieDocumentoCob;

    /**
     * @param siglaEspecieDocumentoCob
     * @return Integer
     */
    public static Integer getIdEspecieDocumentoCip(String siglaEspecieDocumentoCob) {
        EspecieDocumentoDeParaEnum[] lista = values();
        for (EspecieDocumentoDeParaEnum carteira : lista) {
            if (carteira.getSiglaEspecieDocumentoCob().equals(siglaEspecieDocumentoCob)) {
                return carteira.idEspecieDocumentoCip;
            }
        }
        return null;
    }

    /**
     * @param siglaEspecieDocumentoCob
     * @return Integer
     */
    public static String getDescricaoEspecieDocumentoCob(int idEspecieDocumentoCob) {
        EspecieDocumentoDeParaEnum[] lista = values();
        for (EspecieDocumentoDeParaEnum especie : lista) {
            if (especie.getIdEspecieDocumentoCob() == idEspecieDocumentoCob) {
                return especie.descEspecieDocumentoCob;
            }
        }
        return null;
    }

    /**
     * @return the idEspecieDocumentoCob
     */
    public int getIdEspecieDocumentoCob() {
        return idEspecieDocumentoCob;
    }

    /**
     * @return the idEspecieDocumentoCip
     */
    public int getIdEspecieDocumentoCip() {
        return idEspecieDocumentoCip;
    }

    /**
     * @return the siglaEspecieDocumentoCob
     */
    public String getSiglaEspecieDocumentoCob() {
        return siglaEspecieDocumentoCob;
    }

    /**
     * @return o atributo descEspecieDocumentoCob
     */
    public String getDescEspecieDocumentoCob() {
        return descEspecieDocumentoCob;
    }
}
