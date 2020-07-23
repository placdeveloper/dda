package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * ErroProcessamentoCipDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ErroProcessamentoCipDto")
public class ErroProcessamentoCipDTO extends BancoobDTO {

    private String tipoErro;
    private DateTime dataMovimento;
    private Integer qtdErroProcessamento;

    /**
     * @return the tipoErro
     */
    public String getTipoErro() {
        return tipoErro;
    }

    /**
     * @param tipoErro the tipoErro to set
     */
    public void setTipoErro(String tipoErro) {
        this.tipoErro = tipoErro;
    }

    /**
     * @return the dataMovimento
     */
    public DateTime getDataMovimento() {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento(DateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return the qtdErroProcessamento
     */
    public Integer getQtdErroProcessamento() {
        return qtdErroProcessamento;
    }

    /**
     * @param qtdErroProcessamento the qtdErroProcessamento to set
     */
    public void setQtdErroProcessamento(Integer qtdErroProcessamento) {
        this.qtdErroProcessamento = qtdErroProcessamento;
    }


}
