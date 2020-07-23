package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * MensagemDDAErroPendenteDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class MensagemDDAErroPendenteDto extends BancoobDto {

    private static final long serialVersionUID = -695198459756353351L;

    private Long idMensagemDDA;
    private String msgErro;

    public MensagemDDAErroPendenteDto(Long idMensagemDDA, String msgErro, String tipoErro) {
        this.idMensagemDDA = idMensagemDDA;
        this.msgErro = ObjectUtil.isNull(msgErro) ? null : (msgErro + (ObjectUtil.isEmpty(tipoErro) ? "" : " (" + tipoErro + ")."));
    }

    /**
     * @return o atributo idMensagemDDA
     */
    public Long getIdMensagemDDA() {
        return idMensagemDDA;
    }

    /**
     * Define o atributo idMensagemDDA
     */
    public void setIdMensagemDDA(Long idMensagemDDA) {
        this.idMensagemDDA = idMensagemDDA;
    }

    /**
     * @return o atributo msgErro
     */
    public String getMsgErro() {
        return msgErro;
    }

    /**
     * Define o atributo msgErro
     */
    public void setMsgErro(String msgErro) {
        this.msgErro = msgErro;
    }

}
