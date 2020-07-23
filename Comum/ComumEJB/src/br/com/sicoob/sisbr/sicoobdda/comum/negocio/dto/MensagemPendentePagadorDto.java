/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MensagemPendentePagador.java
 * Data Criação:    Dec 21, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.sql.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;


/**
 * MensagemPendentePagador é responsável por 
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemPendentePagadorDTO")
public class MensagemPendentePagadorDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 6588218425921706698L;

    private DateTime dataHoraCadastro;
    private String descTipoMensagem;
    private String descSituacaoMensagem;
    private String numCpfCnpj;

    /**
     * 
     */
    public MensagemPendentePagadorDto() {
        super();
    }

    /**
     * @param dataHoraCadastro
     * @param descTipoMensagem
     * @param descSituacaoMensagem
     * @param numCpfCnpj
     */
    public MensagemPendentePagadorDto(Date dataHoraCadastro, String descTipoMensagem, String descSituacaoMensagem, String numCpfCnpj) {
        super();
        if (!ObjectUtil.isNull(dataHoraCadastro)) {
            this.dataHoraCadastro = new DateTime(dataHoraCadastro.getTime());
        }
        this.descTipoMensagem = descTipoMensagem;
        this.descSituacaoMensagem = descSituacaoMensagem;
        this.numCpfCnpj = numCpfCnpj;
    }

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
