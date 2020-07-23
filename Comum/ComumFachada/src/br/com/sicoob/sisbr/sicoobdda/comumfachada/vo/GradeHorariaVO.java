/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         TipoMensagemDDAVO.java
 * Data Criação:    Aug 13, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * TipoMensagemDDAVO é responsável por
 * 
 * @author Samuell.Ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria")
public class GradeHorariaVO extends BancoobVO {

    private Long id;

    private DateTime dataReferencia;

    private DateTime dataHoraAbertura;

    private DateTime dataHoraFechamento;

    private TipoGradeHoraria tipoGradeHoraria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(DateTime dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public DateTime getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    public void setDataHoraAbertura(DateTime dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    public DateTime getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    public void setDataHoraFechamento(DateTime dataHoraFechamento) {
        this.dataHoraFechamento = dataHoraFechamento;
    }

    public TipoGradeHoraria getTipoGradeHoraria() {
        return tipoGradeHoraria;
    }

    public void setTipoGradeHoraria(TipoGradeHoraria tipoGradeHoraria) {
        this.tipoGradeHoraria = tipoGradeHoraria;
    }

}
