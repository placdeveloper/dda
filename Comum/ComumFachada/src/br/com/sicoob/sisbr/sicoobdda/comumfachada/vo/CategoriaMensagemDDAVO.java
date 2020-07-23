/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         TipoMensagemDDAVO.java
 * Data Criação:    Aug 13, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * CategoriaMensagemDDAVO é responsável por
 * 
 * @author Samuell.Ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.CategoriaMensagemDDA")
public class CategoriaMensagemDDAVO extends BancoobVO {

    private String codCategoriaMensagemDda;

    private String descCategoriaMensagemDda;

    /**
     * @return o atributo codCategoriaMensagemDda
     */
    public String getCodCategoriaMensagemDda() {
        return codCategoriaMensagemDda;
    }

    /**
     * Define o atributo codCategoriaMensagemDda
     */
    public void setCodCategoriaMensagemDda(String codCategoriaMensagemDda) {
        this.codCategoriaMensagemDda = codCategoriaMensagemDda;
    }

    /**
     * @return o atributo descCategoriaMensagemDda
     */
    public String getDescCategoriaMensagemDda() {
        return descCategoriaMensagemDda;
    }

    /**
     * Define o atributo descCategoriaMensagemDda
     */
    public void setDescCategoriaMensagemDda(String descCategoriaMensagemDda) {
        this.descCategoriaMensagemDda = descCategoriaMensagemDda;
    }
}
