package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemPendentePagadorDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendentePagadorDto")
public class MensagemPendentePagadorDTO extends BancoobDTO {

    private DateTime dataHoraCadastro;
    private String descTipoMensagem;
    private String descSituacaoMensagem;
    private String numCpfCnpj;

    /**
     * @return the dataHoraCadastro
     */
    public DateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    /**
     * @param dataHoraCadastro the dataHoraCadastro to set
     */
    public void setDataHoraCadastro(DateTime dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    /**
     * @return the descTipoMensagem
     */
    public String getDescTipoMensagem() {
        return descTipoMensagem;
    }

    /**
     * @param descTipoMensagem the descTipoMensagem to set
     */
    public void setDescTipoMensagem(String descTipoMensagem) {
        this.descTipoMensagem = descTipoMensagem;
    }

    /**
     * @return the descSituacaoMensagem
     */
    public String getDescSituacaoMensagem() {
        return descSituacaoMensagem;
    }

    /**
     * @param descSituacaoMensagem the descSituacaoMensagem to set
     */
    public void setDescSituacaoMensagem(String descSituacaoMensagem) {
        this.descSituacaoMensagem = descSituacaoMensagem;
    }

    /**
     * @return the numCpfCnpj
     */
    public String getNumCpfCnpj() {
        return numCpfCnpj;
    }

    /**
     * @param numCpfCnpj the numCpfCnpj to set
     */
    public void setNumCpfCnpj(String numCpfCnpj) {
        this.numCpfCnpj = numCpfCnpj;
    }


}
