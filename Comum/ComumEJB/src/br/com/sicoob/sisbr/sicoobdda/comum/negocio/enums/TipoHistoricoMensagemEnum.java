package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoHistoricoMensagemEnum é responsável por
 * 
 * @author felipe.rosa
 */
public enum TipoHistoricoMensagemEnum {

    MENSAGEMDDA((short) 1),
    MENSAGEMDDABOLETO((short) 2),
    MENSAGEMDDARETORNOCIP((short) 3), // Utilizado na copia das mensagens - JOB CopiarMensagensDDAHistoricoJobServicoEJB
    LOGDETALHEMENSAGEMDDA((short) 4),
    MENSAGEMDDAACEITE((short) 5),
    MENSAGEMDDABAIXAEFETIVA((short) 6),
    MENSAGEMDDABAIXAOPERACIONAL((short) 7),
    MENSAGEMDDABENEFICIARIO((short) 8),
    MENSAGEMDDABENEFICIARIOCONVENIO((short) 9),
    MENSAGEMDDABENEFICIARIOREPRESENTANTE((short) 10),
    MENSAGEMDDABOLETODESCONTO((short) 11),
    MENSAGEMDDABOLETOGRUPOCALCULO((short) 12),
    MENSAGEMDDABOLETOJUROS((short) 13),
    MENSAGEMDDABOLETOMULTA((short) 14),
    MENSAGEMDDABOLETONOTAFISCAL((short) 15),
    MENSAGEMDDABOLETOTEXTOINFO((short) 16),
    MENSAGEMDDACONSULTABOLETO((short) 17),
    MENSAGEMDDAPAGADOR((short) 18),
    MENSAGEMDDAPAGADORCONTA((short) 19),
    MENSAGEMDDAPAGADORAGREGADO((short) 20),
    MENSAGEMDDATERCEIROAUTORIZADO((short) 21),
    ERROMENSAGEMRETORNOCIP((short) 22); // Utilizado no expurgo das mensagens - JOB ExpurgarMensagensDDAHistoricoJobServicoEJB

    private short id;

    /**
     * @param id
     * @param sigla
     */
    private TipoHistoricoMensagemEnum(short id) {
        this.id = id;
    }

    /**
     * @return o atributo id
     */
    public short getId() {
        return id;
    }

    /**
     * @param id
     * @return TipoHistoricoExpurgoMensagensEnum
     * 
     */
    public static TipoHistoricoMensagemEnum getBy(short id) {
        for (TipoHistoricoMensagemEnum tipoHistoricoMensagem : values()) {
            if (tipoHistoricoMensagem.getId() == id) {
                return tipoHistoricoMensagem;
            }
        }
        return null;
    }

}