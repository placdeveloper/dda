package br.com.sicoob.sisbr.sicoobdda.integracaocip.enums;

/**
 * TipoArquivoRetornoEnum é responsável por
 * 
 * @author Felipe.Rosa
 */
public enum TipoArquivoRetornoEnum {

    PRO("Arquivo de protocolo"),
    ERR("Arquivo de erro"),
    RET("Arquivo de retorno"),
    DIS("Arquivo de distribuição"),
    ADD("Arquivo de inclusão");

    private String descricao;

    /**
     * @param desc
     */
    private TipoArquivoRetornoEnum(String desc) {
        this.descricao = desc;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricao() {
        return this.descricao;
    }

}
