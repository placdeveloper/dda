/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.relatorio
 * Arquivo:         ConfiguracaoRelatorio.java
 * Data Criação:    Sep 6, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.relatorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ComumContextUtil;

/**
 * ConfiguracaoRelatorio é responsável por 
 * 
 * @author Samuell.Ramos
 */
public class ConfiguraRelatorio {

    private ComumContextUtil comumContextUtil = new ComumContextUtil();

    private boolean bolCabecalho = true;
    private List<?> listaGenerica;
    private RelatorioSicoobDDAEnum relatorioEnum;
    private Map<String, Object> map = new HashMap<String, Object>();
    
    /**
     * 
     */
    public ConfiguraRelatorio() {
        super();
    }
    
    /**
     * Define a lista do relatorio.
     * @param relatorioEnum
     * @param lista
     * @throws ComumException
     */
    public ConfiguraRelatorio(RelatorioSicoobDDAEnum relatorioEnum, List<?> lista) throws ComumException {
        this.relatorioEnum = relatorioEnum;
        this.listaGenerica = lista;
    }
    
    /**
     * Define enum.
     * @param relatorioEnum
     * @throws ComumException
     */
    public ConfiguraRelatorio(RelatorioSicoobDDAEnum relatorioEnum) throws ComumException {
        this.relatorioEnum = relatorioEnum;
    }
    


    /**
     * Método responsável por adicionar os parametros do relatório. 
     * @param nome
     * @param objeto void
     * 
     */
    public void setParametro(String nome, Object objeto){
        map.put(nome, objeto);
    }
    
    /**
     * Método responsável por gerar o relatório.
     * @throws ComumException void
     */
    public void gerarRelatorioSinc() throws ComumException {
        comumContextUtil.gerarRelatorioSincronamente(new RelatorioSicoobDDA(getRelatorioEnum(), getListaGenerica(), this.map));
    }

    /**
     * @return o atributo listaGenerica
     */
    private List<?> getListaGenerica() {
        return listaGenerica;
    }

    /**
     * Define o atributo listaGenerica
     */
    public void setListaGenerica(List<?> listaGenerica) {
        this.listaGenerica = listaGenerica;
    }

    /**
     * @return o atributo relatorioEnum
     */
    private RelatorioSicoobDDAEnum getRelatorioEnum() {
        return relatorioEnum;
    }

    /**
     * Define o atributo relatorioEnum
     */
    public void setRelatorioEnum(RelatorioSicoobDDAEnum relatorioEnum) {
        this.relatorioEnum = relatorioEnum;
    }

    /**
     * @return o atributo bolCabecalho
     */
    public boolean getBolCabecalho() {
        return bolCabecalho;
    }
}
