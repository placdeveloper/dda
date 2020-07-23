/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         DominioCadastroMensagemDto.java
 * Data Criação:    Sep 15, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBoletoConsultado;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * DominioCadastroMensagemDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.DominioCadastroMensagemDTO")
public class DominioCadastroMensagemDto extends BancoobDto {

    private static final long serialVersionUID = 1840112911126309722L;

    private List<SituacaoBoleto> listaSituacaoBoleto;
    private List<TipoBoletoConsultado> listaTipoBoletoConsultado;

    /**
     * @param listaSituacaoBoleto
     * @param listaTipoBoletoConsultado
     */
    public DominioCadastroMensagemDto(List<SituacaoBoleto> listaSituacaoBoleto, List<TipoBoletoConsultado> listaTipoBoletoConsultado) {
        super();
        this.listaSituacaoBoleto = listaSituacaoBoleto;
        this.listaTipoBoletoConsultado = listaTipoBoletoConsultado;
    }

    /**
     * @return o atributo listaSituacaoBoleto
     */
    public List<SituacaoBoleto> getListaSituacaoBoleto() {
        return listaSituacaoBoleto;
    }

    /**
     * Define o atributo listaSituacaoBoleto
     */
    public void setListaSituacaoBoleto(List<SituacaoBoleto> listaSituacaoBoleto) {
        this.listaSituacaoBoleto = listaSituacaoBoleto;
    }

    /**
     * @return o atributo listaTipoBoletoConsultado
     */
    public List<TipoBoletoConsultado> getListaTipoBoletoConsultado() {
        return listaTipoBoletoConsultado;
    }

    /**
     * Define o atributo listaTipoBoletoConsultado
     */
    public void setListaTipoBoletoConsultado(List<TipoBoletoConsultado> listaTipoBoletoConsultado) {
        this.listaTipoBoletoConsultado = listaTipoBoletoConsultado;
    }

}
