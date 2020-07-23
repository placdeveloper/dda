/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         TipoArquivo.java
 * Data Criação:    Sep 19, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoArquivo é responsável por
 * 
 * @author Felipe.Rosa
 */
@Entity
@Table(name = "TIPOARQUIVO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoArquivoVO")
public class TipoArquivo extends SicoobDDAEntidade {

    private static final long serialVersionUID = -571351753403613775L;

    public static final String ERRO = "ERR";
    public static final String RETORNO = "RET";
    public static final String PROTOCOLO = "PRO";
    public static final String DISTRIBUICAO = "DIS";

    /**
     * 
     */
    public TipoArquivo() {
    }

    /**
     * @param codTipoArquivo
     */
    public TipoArquivo(String codTipoArquivo) {
        this.codTipoArquivo = codTipoArquivo;
    }

    @Id
    @Column(name = "CODTIPOARQUIVO", unique = true, nullable = false)
    private String codTipoArquivo;

    private String descTipoArquivo;

    /**
     * @return the codTipoArquivo
     */
    public String getCodTipoArquivo() {
        return codTipoArquivo;
    }

    /**
     * @param codTipoArquivo the codTipoArquivo to set
     */
    public void setCodTipoArquivo(String codTipoArquivo) {
        this.codTipoArquivo = codTipoArquivo;
    }

    /**
     * @return the descTipoArquivo
     */
    public String getDescTipoArquivo() {
        return descTipoArquivo;
    }

    /**
     * @param descTipoArquivo the descTipoArquivo to set
     */
    public void setDescTipoArquivo(String descTipoArquivo) {
        this.descTipoArquivo = descTipoArquivo;
    }

}
