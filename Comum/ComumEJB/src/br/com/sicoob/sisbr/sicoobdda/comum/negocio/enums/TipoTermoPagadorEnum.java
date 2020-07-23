package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoHistoricoTermoPagadorEnum
 * 
 * @author samuell.ramos
 */
public enum TipoTermoPagadorEnum {

	ID_CANAL((short)1),
	EXCLUIR_AGREGADO((short)22),
	INCLUIR_AGREGADO((short)21),
	ADESAO_DDA((short)11),
	CANCELAR_ADESAO_DDA((short)12),
	CONTRATO_DDA((short)1);

    /**
     *
     */
    private TipoTermoPagadorEnum(Short cod) {
        this.codTipoTermoPagador = cod;
    }

    private Short codTipoTermoPagador;

	/**
	 * @return the codTipoTermoPagador
	 */
	public Short getValor() {
		return codTipoTermoPagador;
	}

}
