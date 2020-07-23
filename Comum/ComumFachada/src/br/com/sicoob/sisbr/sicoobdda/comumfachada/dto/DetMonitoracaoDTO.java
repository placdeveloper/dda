package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author felipe.rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DetMonitoracaoDto")
public class DetMonitoracaoDTO extends BancoobDTO {

    private DateTime dataMovimento;
    private String codTipoMensagem;
    private Long qtdEnviar;
    private Long qtdSemProtocolo;
    private Long qtdSemRetorno;
    private Long qtdComErro;

    /**
     * @return o atributo dataMovimento
     */
    public DateTime getDataMovimento() {
        return dataMovimento;
    }

    /**
     * Define o atributo dataMovimento
     */
    public void setDataMovimento(DateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return o atributo codTipoMensagem
     */
    public String getCodTipoMensagem() {
        return codTipoMensagem;
    }

    /**
     * Define o atributo codTipoMensagem
     */
    public void setCodTipoMensagem(String codTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
    }

    /**
     * @return o atributo qtdEnviar
     */
    public Long getQtdEnviar() {
        return qtdEnviar;
    }

    /**
     * Define o atributo qtdEnviar
     */
    public void setQtdEnviar(Long qtdEnviar) {
        this.qtdEnviar = qtdEnviar;
    }

    /**
     * @return o atributo qtdSemProtocolo
     */
    public Long getQtdSemProtocolo() {
        return qtdSemProtocolo;
    }

    /**
     * Define o atributo qtdSemProtocolo
     */
    public void setQtdSemProtocolo(Long qtdSemProtocolo) {
        this.qtdSemProtocolo = qtdSemProtocolo;
    }

    /**
     * @return o atributo qtdSemRetorno
     */
    public Long getQtdSemRetorno() {
        return qtdSemRetorno;
    }

    /**
     * Define o atributo qtdSemRetorno
     */
    public void setQtdSemRetorno(Long qtdSemRetorno) {
        this.qtdSemRetorno = qtdSemRetorno;
    }

    /**
     * @return o atributo qtdComErro
     */
    public Long getQtdComErro() {
        return qtdComErro;
    }

    /**
     * Define o atributo qtdComErro
     */
    public void setQtdComErro(Long qtdComErro) {
        this.qtdComErro = qtdComErro;
    }

}
