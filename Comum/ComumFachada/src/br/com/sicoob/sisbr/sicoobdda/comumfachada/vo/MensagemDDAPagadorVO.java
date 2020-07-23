package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MensagemDDAPagadorVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador")
public class MensagemDDAPagadorVO extends BancoobVO {

    private Long id;
    private Long idEventoDDA;
    private Long numIdentificaPagadorCip;
    private MensagemDDAVO mensagemDDA;
    private String numCpfCnpjPagador;
    private String codTipoPessoaPagador;
    private Boolean bolPagadorEletronico;
    private Long numRefAtualCadPagador;
    private Long numSeqAtualCadPagador;
    private List<MensagemDDAPagadorAgregadoVO> listaMensagemDDAPagadorAgregado;
    private List<MensagemDDAPagadorContaVO> listaMensagemDDAPagadorConta;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the idEventoDDA
     */
    public Long getIdEventoDDA() {
        return idEventoDDA;
    }

    /**
     * @param idEventoDDA the idEventoDDA to set
     */
    public void setIdEventoDDA(Long idEventoDDA) {
        this.idEventoDDA = idEventoDDA;
    }

    /**
     * @return the numIdentificaPagadorCip
     */
    public Long getNumIdentificaPagadorCip() {
        return numIdentificaPagadorCip;
    }

    /**
     * @param numIdentificaPagadorCip the numIdentificaPagadorCip to set
     */
    public void setNumIdentificaPagadorCip(Long numIdentificaPagadorCip) {
        this.numIdentificaPagadorCip = numIdentificaPagadorCip;
    }

    /**
     * @return the mensagemDDA
     */
    public MensagemDDAVO getMensagemDDA() {
        return mensagemDDA;
    }

    /**
     * @param mensagemDDA the mensagemDDA to set
     */
    public void setMensagemDDA(MensagemDDAVO mensagemDDA) {
        this.mensagemDDA = mensagemDDA;
    }

    /**
     * @return the numCpfCnpjPagador
     */
    public String getNumCpfCnpjPagador() {
        return numCpfCnpjPagador;
    }

    /**
     * @param numCpfCnpjPagador the numCpfCnpjPagador to set
     */
    public void setNumCpfCnpjPagador(String numCpfCnpjPagador) {
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

    /**
     * @return the codTipoPessoaPagador
     */
    public String getCodTipoPessoaPagador() {
        return codTipoPessoaPagador;
    }

    /**
     * @param codTipoPessoaPagador the codTipoPessoaPagador to set
     */
    public void setCodTipoPessoaPagador(String codTipoPessoaPagador) {
        this.codTipoPessoaPagador = codTipoPessoaPagador;
    }

    /**
     * @return the bolPagadorEletronico
     */
    public Boolean getBolPagadorEletronico() {
        return bolPagadorEletronico;
    }

    /**
     * @param bolPagadorEletronico the bolPagadorEletronico to set
     */
    public void setBolPagadorEletronico(Boolean bolPagadorEletronico) {
        this.bolPagadorEletronico = bolPagadorEletronico;
    }

    /**
     * @return the numRefAtualCadPagador
     */
    public Long getNumRefAtualCadPagador() {
        return numRefAtualCadPagador;
    }

    /**
     * @param numRefAtualCadPagador the numRefAtualCadPagador to set
     */
    public void setNumRefAtualCadPagador(Long numRefAtualCadPagador) {
        this.numRefAtualCadPagador = numRefAtualCadPagador;
    }

    /**
     * @return the numSeqAtualCadPagador
     */
    public Long getNumSeqAtualCadPagador() {
        return numSeqAtualCadPagador;
    }

    /**
     * @param numSeqAtualCadPagador the numSeqAtualCadPagador to set
     */
    public void setNumSeqAtualCadPagador(Long numSeqAtualCadPagador) {
        this.numSeqAtualCadPagador = numSeqAtualCadPagador;
    }

    /**
     * @return the listaMensagemDDAPagadorAgregado
     */
    public List<MensagemDDAPagadorAgregadoVO> getListaMensagemDDAPagadorAgregado() {
        return listaMensagemDDAPagadorAgregado;
    }

    /**
     * @param listaMensagemDDAPagadorAgregado the listaMensagemDDAPagadorAgregado to set
     */
    public void setListaMensagemDDAPagadorAgregado(List<MensagemDDAPagadorAgregadoVO> listaMensagemDDAPagadorAgregado) {
        this.listaMensagemDDAPagadorAgregado = listaMensagemDDAPagadorAgregado;
    }

    /**
     * @return the listaMensagemDDAPagadorConta
     */
    public List<MensagemDDAPagadorContaVO> getListaMensagemDDAPagadorConta() {
        return listaMensagemDDAPagadorConta;
    }

    /**
     * @param listaMensagemDDAPagadorConta the listaMensagemDDAPagadorConta to set
     */
    public void setListaMensagemDDAPagadorConta(List<MensagemDDAPagadorContaVO> listaMensagemDDAPagadorConta) {
        this.listaMensagemDDAPagadorConta = listaMensagemDDAPagadorConta;
    }


}
