package br.com.sicoob.sisbr.sicoobdda.integracaocip.enums;

/**
 * TipoArquivoRetornoEnum � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public enum TipoArquivoRetornoEnum {

    PRO("Arquivo de protocolo"),
    ERR("Arquivo de erro"),
    RET("Arquivo de retorno"),
    DIS("Arquivo de distribui��o"),
    ADD("Arquivo de inclus�o");

    private String descricao;

    /**
     * @param desc
     */
    private TipoArquivoRetornoEnum(String desc) {
        this.descricao = desc;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return String
     * 
     */
    public String getDescricao() {
        return this.descricao;
    }

}
