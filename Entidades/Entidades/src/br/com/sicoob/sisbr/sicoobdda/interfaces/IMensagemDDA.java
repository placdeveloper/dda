package br.com.sicoob.sisbr.sicoobdda.interfaces;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;

/**
 * IMensagemDDA é responsável por
 * 
 * @author Rodrigo.Neri
 */
public interface IMensagemDDA {

    /**
     * Método responsável por
     * 
     * @param mensagemDDA void
     * 
     */
    void setMensagemDDA(MensagemDDA mensagemDDA);

    /**
     * Método responsável por
     * 
     * @return MensagemDDA
     * 
     */
    MensagemDDA getMensagemDDA();

    /**
     * Método responsável por obter idEvento para o processamento de carga.
     * 
     * @return Long
     * 
     */
    Long getIdEventoCadastro();

}
