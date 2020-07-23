package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoBoletoVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoBoletoConsultadoVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DominioCadastroMensagemDto")
public class DominioCadastroMensagemDTO extends BancoobDTO {

    private List<SituacaoBoletoVO> listaSituacaoBoleto;
    private List<TipoBoletoConsultadoVO> listaTipoBoletoConsultado;

    /**
     * @return o atributo listaSituacaoBoleto
     */
    public List<SituacaoBoletoVO> getListaSituacaoBoleto() {
        return listaSituacaoBoleto;
    }

    /**
     * Define o atributo listaSituacaoBoleto
     */
    public void setListaSituacaoBoleto(List<SituacaoBoletoVO> listaSituacaoBoleto) {
        this.listaSituacaoBoleto = listaSituacaoBoleto;
    }

    /**
     * @return o atributo listaTipoBoletoConsultado
     */
    public List<TipoBoletoConsultadoVO> getListaTipoBoletoConsultado() {
        return listaTipoBoletoConsultado;
    }

    /**
     * Define o atributo listaTipoBoletoConsultado
     */
    public void setListaTipoBoletoConsultado(List<TipoBoletoConsultadoVO> listaTipoBoletoConsultado) {
        this.listaTipoBoletoConsultado = listaTipoBoletoConsultado;
    }

}
