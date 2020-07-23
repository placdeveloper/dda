package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * PendenciaDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PendenciaDto")
public class PendenciaDTO extends BancoobDTO {

    private DateTime dataMovimento;
    private Integer qtdEnviar;
    private Integer qtdSemRetornoSSPB;
    private Integer qtdSemRetornoCIP;
    private Integer qtdRetornoErro;
    private String codTipoMensagemDDA;

    public DateTime getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(DateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public Integer getQtdEnviar() {
        return qtdEnviar;
    }

    public void setQtdEnviar(Integer qtdEnviar) {
        this.qtdEnviar = qtdEnviar;
    }

    public Integer getQtdSemRetornoSSPB() {
        return qtdSemRetornoSSPB;
    }

    public void setQtdSemRetornoSSPB(Integer qtdSemRetornoSSPB) {
        this.qtdSemRetornoSSPB = qtdSemRetornoSSPB;
    }

    public Integer getQtdSemRetornoCIP() {
        return qtdSemRetornoCIP;
    }

    public void setQtdSemRetornoCIP(Integer qtdSemRetornoCIP) {
        this.qtdSemRetornoCIP = qtdSemRetornoCIP;
    }

    public Integer getQtdRetornoErro() {
        return qtdRetornoErro;
    }

    public void setQtdRetornoErro(Integer qtdRetornoErro) {
        this.qtdRetornoErro = qtdRetornoErro;
    }

	public String getCodTipoMensagemDDA() {
		return codTipoMensagemDDA;
	}

	public void setCodTipoMensagemDDA(String codTipoMensagemDDA) {
		this.codTipoMensagemDDA = codTipoMensagemDDA;
	}

}
