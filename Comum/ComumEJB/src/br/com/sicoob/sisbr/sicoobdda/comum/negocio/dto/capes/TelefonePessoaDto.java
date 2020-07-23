package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Daniel.Dias
 */
public class TelefonePessoaDto extends BancoobDto {

    private static final long serialVersionUID = -7776861798389233953L;

    public static final String[] CAMPOS = new String[] { "idTelefone", "dataHoraInicio", "ddd", "telefone", "ramal", "codigoTipoTelefone", "descricaoTipoTelefone" };

    // TELEFONE PESSOA
    private Long idTelefone;

    private Date dataHoraInicio;

    private String ddd;

    private String telefone;

    private String ramal;

    // TIPO TELEFONE
    private Short codigoTipoTelefone;

    private String descricaoTipoTelefone;

    public TelefonePessoaDto() {

    }

    public TelefonePessoaDto(Long idTelefone, Date dataHoraInicio, String ddd, String telefone, String ramal, Short codigoTipoTelefone, String descricaoTipoTelefone) {
        this.idTelefone = idTelefone;
        this.dataHoraInicio = dataHoraInicio;
        this.ddd = ddd;
        this.telefone = telefone;
        this.ramal = ramal;
        this.codigoTipoTelefone = codigoTipoTelefone;
        this.descricaoTipoTelefone = descricaoTipoTelefone;
    }

    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public Short getCodigoTipoTelefone() {
        return codigoTipoTelefone;
    }

    public void setCodigoTipoTelefone(Short codigoTipoTelefone) {
        this.codigoTipoTelefone = codigoTipoTelefone;
    }

    public String getDescricaoTipoTelefone() {
        return descricaoTipoTelefone;
    }

    public void setDescricaoTipoTelefone(String descricaoTipoTelefone) {
        this.descricaoTipoTelefone = descricaoTipoTelefone;
    }
}
