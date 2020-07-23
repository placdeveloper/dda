package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * CanalEnum é responsável por
 * 
 * @author George.Santos
 */
public enum CanalEnum {

    // Para o Cobrança Bancária, é utilizado o canal "Retaguarda"
    RETAGUARDA((short) 1, "SRET"),
    CAIXA((short) 2, "SCX"),
    ATM((short) 3, "ATM"),
    IB((short) 4, "IB"),
    SICOOB_NET_EMPRESARIAL((short) 5, "SOFC"),
    SICOOB_NET_MOBILE((short) 6, "SWAP"),
    CEDENTE((short) 9, "SCED"),
    CORRESPONDENTE_SICOOB((short) 12, "SCOS"),
    SITE_SICOOB((short) 14, "SSIC"),
    SICOOB_NET_MOBILE_EMPRESARIAL((short) 16, "SWEP"),
    SICOOB_NET_MOBILE_EMPRESARIAL_2((short) 17, "SWEP"),
    CONTA_PAGAMENTO_DIGITAL((short) 53, "SMOC"),
    SICOOB_NET((short) 54, "SNET");

    private short id;
    private String sigla;

    private CanalEnum(short id, String sigla) {
        this.id = id;
        this.sigla = sigla;
    }

    /**
     * @return o atributo id
     */
    public Short getId() {
        return id;
    }

    /**
     * @return o atributo sigla
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Método responsável por verificar se possui o idCanal informado.
     * 
     * @param idCanal
     * @return
     */
    public static boolean possuiCanal(short idCanal) {
        for (CanalEnum canal : values()) {
            if (canal.id == idCanal) {
                return true;
            }
        }
        return false;
    }

}