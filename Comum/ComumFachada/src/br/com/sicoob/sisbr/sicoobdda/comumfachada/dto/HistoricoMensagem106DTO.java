package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoMensagem106Dto")
public class HistoricoMensagem106DTO extends BancoobDTO {

    private DateTime dataHoraEnvio;
    private DateTime dataHoraRetornoCIP;
    private String numCodigoBarra;
    private String descSituacaoBoleto;
    private String descSituacaoBoletoCIP;

    /**
     * @return o atributo dataHoraEnvio
     */
    public DateTime getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    /**
     * Define o atributo dataHoraEnvio
     */
    public void setDataHoraEnvio(DateTime dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
    }

    /**
     * @return o atributo dataHoraRetornoCIP
     */
    public DateTime getDataHoraRetornoCIP() {
        return dataHoraRetornoCIP;
    }

    /**
     * Define o atributo dataHoraRetornoCIP
     */
    public void setDataHoraRetornoCIP(DateTime dataHoraRetornoCIP) {
        this.dataHoraRetornoCIP = dataHoraRetornoCIP;
    }

    /**
     * @return o atributo numCodigoBarra
     */
    public String getNumCodigoBarra() {
        return numCodigoBarra;
    }

    /**
     * Define o atributo numCodigoBarra
     */
    public void setNumCodigoBarra(String numCodigoBarra) {
        this.numCodigoBarra = numCodigoBarra;
    }

    /**
     * @return o atributo descSituacaoBoleto
     */
    public String getDescSituacaoBoleto() {
        return descSituacaoBoleto;
    }

    /**
     * Define o atributo descSituacaoBoleto
     */
    public void setDescSituacaoBoleto(String descSituacaoBoleto) {
        this.descSituacaoBoleto = descSituacaoBoleto;
    }

    /**
     * @return o atributo descSituacaoBoletoCIP
     */
    public String getDescSituacaoBoletoCIP() {
        return descSituacaoBoletoCIP;
    }

    /**
     * Define o atributo descSituacaoBoletoCIP
     */
    public void setDescSituacaoBoletoCIP(String descSituacaoBoletoCIP) {
        this.descSituacaoBoletoCIP = descSituacaoBoletoCIP;
    }
}
