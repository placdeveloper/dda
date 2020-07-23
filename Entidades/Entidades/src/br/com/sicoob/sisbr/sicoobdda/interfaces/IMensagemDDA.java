package br.com.sicoob.sisbr.sicoobdda.interfaces;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;

/**
 * IMensagemDDA � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
public interface IMensagemDDA {

    /**
     * M�todo respons�vel por
     * 
     * @param mensagemDDA void
     * 
     */
    void setMensagemDDA(MensagemDDA mensagemDDA);

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDA
     * 
     */
    MensagemDDA getMensagemDDA();

    /**
     * M�todo respons�vel por obter idEvento para o processamento de carga.
     * 
     * @return Long
     * 
     */
    Long getIdEventoCadastro();

}
