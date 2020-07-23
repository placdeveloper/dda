package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;

/**
 * EnvioArquivoDto é responsável por
 * 
 * @author Felipe.Rosa
 */
public class EnvioArquivoDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 8583186197119832201L;
    private List<Integer> listaIdMensagemDDA;
    private DateTimeDB dataMovimento;

    private String codTipoMensagemDDA;
    private Long idMensagemDDAInicial;
    private Long idMensagemDDAFinal;
    private Integer numSequencial;

    private Integer numAgrupamento;
    private Boolean bolOrigem;

    /**
     * 
     */
    public EnvioArquivoDto() {
        super();
    }

    /**
     * @param idMensagemDDAInicial
     * @param idMensagemDDAFinal
     */
    public EnvioArquivoDto(Long idMensagemDDAInicial, Long idMensagemDDAFinal) {
        super();
        this.idMensagemDDAInicial = idMensagemDDAInicial;
        this.idMensagemDDAFinal = idMensagemDDAFinal;
    }

    /**
     * @param codTipoMensagemDDA
     * @param dataMovimento
     * @param idMensagemDDAInicial
     * @param idMensagemDDAFinal
     * @param numSequencial
     */
    public EnvioArquivoDto(String codTipoMensagemDDA, Date dataMovimento, Long idMensagemDDAInicial, Long idMensagemDDAFinal, Integer numSequencial) {
        this(idMensagemDDAInicial, idMensagemDDAFinal);
        this.codTipoMensagemDDA = codTipoMensagemDDA;
        this.dataMovimento = DateUtil.getDateTimeDB(dataMovimento);
        this.numSequencial = numSequencial;
    }

    /**
     * @return o atributo listaIdMensagemDDA
     */
    public List<Integer> getListaIdMensagemDDA() {
        return listaIdMensagemDDA;
    }

    /**
     * Define o atributo listaIdMensagemDDA
     */
    public void setListaIdMensagemDDA(List<Integer> listaIdMensagemDDA) {
        this.listaIdMensagemDDA = listaIdMensagemDDA;
    }

    /**
     * @return o atributo dataMovimento
     */
    public DateTimeDB getDataMovimento() {
        return dataMovimento;
    }

    /**
     * Define o atributo dataMovimento
     */
    public void setDataMovimento(DateTimeDB dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return o atributo codTipoMensagemDDA
     */
    public String getCodTipoMensagemDDA() {
        return codTipoMensagemDDA;
    }

    /**
     * Define o atributo codTipoMensagemDDA
     */
    public void setCodTipoMensagemDDA(String codTipoMensagemDDA) {
        this.codTipoMensagemDDA = codTipoMensagemDDA;
    }

    /**
     * @return o atributo idMensagemDDAInicial
     */
    public Long getIdMensagemDDAInicial() {
        return idMensagemDDAInicial;
    }

    /**
     * Define o atributo idMensagemDDAInicial
     */
    public void setIdMensagemDDAInicial(Long idMensagemDDAInicial) {
        this.idMensagemDDAInicial = idMensagemDDAInicial;
    }

    /**
     * @return o atributo idMensagemDDAFinal
     */
    public Long getIdMensagemDDAFinal() {
        return idMensagemDDAFinal;
    }

    /**
     * Define o atributo idMensagemDDAFinal
     */
    public void setIdMensagemDDAFinal(Long idMensagemDDAFinal) {
        this.idMensagemDDAFinal = idMensagemDDAFinal;
    }

    /**
     * @return o atributo numSequencial
     */
    public Integer getNumSequencial() {
        return numSequencial;
    }

    /**
     * Define o atributo numSequencial
     */
    public void setNumSequencial(Integer numSequencial) {
        this.numSequencial = numSequencial;
    }

    /**
     * @return numAgrupamento
     */
    public Integer getNumAgrupamento() {
        return numAgrupamento;
    }

    /**
     * @param numAgrupamento
     */
    public void setNumAgrupamento(Integer numAgrupamento) {
        this.numAgrupamento = numAgrupamento;
    }

    /**
     * @return bolOrigem
     */
    public Boolean getBolOrigem() {
        return bolOrigem;
    }

    /**
     * @param bolOrigem
     */
    public void setBolOrigem(Boolean bolOrigem) {
        this.bolOrigem = bolOrigem;
    }
}
