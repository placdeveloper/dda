/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         StatusEventoTarifavelEnum.java
 * Data Criação:    Jan 04, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * StatusEventoTarifavelEnum
 * 
 * @author samuell.ramos
 */
public enum StatusEventoTarifavelEnum {
    VIGENTE("Vigente", 1),
    PROGRAMADO("Programado", 2),
    VENCIDO("Vencido", 3);

    private StatusEventoTarifavelEnum(String descricaoStatus, int codStatus) {
        this.descricaoStatus = descricaoStatus;
        this.codStatus = codStatus;
    }

    private final String descricaoStatus;
    private final int codStatus;

    /**
     * @param idCarteiraCob
     * @return Integer
     */
    public static Integer getCodStatus(int codStatus) {
        StatusEventoTarifavelEnum[] lista = values();
        for (StatusEventoTarifavelEnum eventoTarEnum : lista) {
            if (eventoTarEnum.getCodStatus() == codStatus) {
                return eventoTarEnum.codStatus;
            }
        }
        return null;
    }

    /**
     * @param idCarteiraCob
     * @return Integer
     */
    public static String getDescricaoStatus(int codStatus) {
        StatusEventoTarifavelEnum[] lista = values();
        for (StatusEventoTarifavelEnum eventoTarEnum : lista) {
            if (eventoTarEnum.getCodStatus() == codStatus) {
                return eventoTarEnum.descricaoStatus;
            }
        }
        return null;
    }

    public static StatusEventoTarifavelEnum getBy(String descStatus) {
        StatusEventoTarifavelEnum[] lista = values();
        for (StatusEventoTarifavelEnum eventoTarEnum : lista) {
            if (eventoTarEnum.getDescricaoStatus().equals(descStatus)) {
                return eventoTarEnum;
            }
        }
        return null;
    }
    
    public static StatusEventoTarifavelEnum getBy(int codStatus) {
        StatusEventoTarifavelEnum[] lista = values();
        for (StatusEventoTarifavelEnum eventoTarEnum : lista) {
            if (eventoTarEnum.getCodStatus() == codStatus) {
                return eventoTarEnum;
            }
        }
        return null;
    }

    /**
     * @return o atributo descricaoStatus
     */
    public String getDescricaoStatus() {
        return descricaoStatus;
    }

    /**
     * @return o atributo codStatus
     */
    public int getCodStatus() {
        return codStatus;
    }
}
