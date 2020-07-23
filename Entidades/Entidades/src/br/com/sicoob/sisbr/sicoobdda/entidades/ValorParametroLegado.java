package br.com.sicoob.sisbr.sicoobdda.entidades;

/**
 * ValorParametroLegado é responsável por representar a entidade ValorParametroLegado
 * 
 * @author Rafael.Silva
 */
@Deprecated
public class ValorParametroLegado extends SicoobDDAEntidade {

    private static final long serialVersionUID = 717478992138817011L;

    public static final int HORA_LIMITE_PAGAMENTO_TITULO = 1354;
    public static final int HORA_LIMITE_PAGAMENTO_TITULO_CAIXA = 1632;
    public static final int HORA_LIMITE_PAGAMENTO_TITULO_CORRESPONDENTE = 1644;

    private Long id;
    private String valorParametro;
    private String valorSegundoParametro;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * Método responsável por definir o id.
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método responsável por obter o valor
     * 
     * @return String
     */
    public String getValorParametro() {
        return valorParametro;
    }

    /**
     * Método responsável por definir o valor
     * 
     * @param valorParametro
     */
    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    /**
     * Método responsável por obter o valor
     * 
     * @return String
     */
    public String getValorSegundoParametro() {
        return valorSegundoParametro;
    }

    /**
     * Método responsável por definir o valor
     * 
     * @param valorSegundoParametro
     */
    public void setValorSegundoParametro(String valorSegundoParametro) {
        this.valorSegundoParametro = valorSegundoParametro;
    }

}
