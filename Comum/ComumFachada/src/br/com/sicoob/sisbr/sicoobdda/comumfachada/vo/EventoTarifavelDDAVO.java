package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoConsolidadoDDAVO;
import java.util.List;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoTarifavelDDATarifaVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Rodrigo.Neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDA")
public class EventoTarifavelDDAVO extends BancoobVO {

    private Integer codEventoTarifavel;
    private Boolean bolAtivo;
    private Boolean bolTarifavel;
    private String descEventoTarifavelExtrato;
    private String descEventoTarifavelManual;
    private Long idInstituicao;
    private Long idTipoHistoricoLancamentoCCO;
    private String idUsuario;
    private Integer numLoteLancamentoCCO;
    private List<EventoTarifavelDDATarifaVO> listaEventoTarifavelDDATarifa;
    private List<EventoConsolidadoDDAVO> listaEventoConsolidadoDDA;
    private TipoEventoTarifavelVO tipoEventoTarifavel;
    private TipoOperacaoLogVO tipoOperacaoLog;
    private TipoServicoTarifavelVO tipoServicoTarifavel;

    /**
     * @return o atributo codEventoTarifavel
     */
    public Integer getCodEventoTarifavel() {
        return codEventoTarifavel;
    }

    /**
     * Define o atributo codEventoTarifavel
     */
    public void setCodEventoTarifavel(Integer codEventoTarifavel) {
        this.codEventoTarifavel = codEventoTarifavel;
    }

    /**
     * @return o atributo bolAtivo
     */
    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    /**
     * Define o atributo bolAtivo
     */
    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

    /**
     * @return o atributo bolTarifavel
     */
    public Boolean getBolTarifavel() {
        return bolTarifavel;
    }

    /**
     * Define o atributo bolTarifavel
     */
    public void setBolTarifavel(Boolean bolTarifavel) {
        this.bolTarifavel = bolTarifavel;
    }

    /**
     * @return o atributo descEventoTarifavelExtrato
     */
    public String getDescEventoTarifavelExtrato() {
        return descEventoTarifavelExtrato;
    }

    /**
     * Define o atributo descEventoTarifavelExtrato
     */
    public void setDescEventoTarifavelExtrato(String descEventoTarifavelExtrato) {
        this.descEventoTarifavelExtrato = descEventoTarifavelExtrato;
    }

    /**
     * @return o atributo descEventoTarifavelManual
     */
    public String getDescEventoTarifavelManual() {
        return descEventoTarifavelManual;
    }

    /**
     * Define o atributo descEventoTarifavelManual
     */
    public void setDescEventoTarifavelManual(String descEventoTarifavelManual) {
        this.descEventoTarifavelManual = descEventoTarifavelManual;
    }

    /**
     * @return o atributo idInstituicao
     */
    public Long getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return o atributo idTipoHistoricoLancamentoCCO
     */
    public Long getIdTipoHistoricoLancamentoCCO() {
        return idTipoHistoricoLancamentoCCO;
    }

    /**
     * Define o atributo idTipoHistoricoLancamentoCCO
     */
    public void setIdTipoHistoricoLancamentoCCO(Long idTipoHistoricoLancamentoCCO) {
        this.idTipoHistoricoLancamentoCCO = idTipoHistoricoLancamentoCCO;
    }

    /**
     * @return o atributo idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Define o atributo idUsuario
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return o atributo numLoteLancamentoCCO
     */
    public Integer getNumLoteLancamentoCCO() {
        return numLoteLancamentoCCO;
    }

    /**
     * Define o atributo numLoteLancamentoCCO
     */
    public void setNumLoteLancamentoCCO(Integer numLoteLancamentoCCO) {
        this.numLoteLancamentoCCO = numLoteLancamentoCCO;
    }

    /**
     * @return o atributo listaEventoTarifavelDDATarifa
     */
    public List<EventoTarifavelDDATarifaVO> getListaEventoTarifavelDDATarifa() {
        return listaEventoTarifavelDDATarifa;
    }

    /**
     * Define o atributo listaEventoTarifavelDDATarifa
     */
    public void setListaEventoTarifavelDDATarifa(List<EventoTarifavelDDATarifaVO> listaEventoTarifavelDDATarifa) {
        this.listaEventoTarifavelDDATarifa = listaEventoTarifavelDDATarifa;
    }

    /**
     * @return o atributo listaEventoConsolidadoDDA
     */
    public List<EventoConsolidadoDDAVO> getListaEventoConsolidadoDDA() {
        return listaEventoConsolidadoDDA;
    }

    /**
     * Define o atributo listaEventoConsolidadoDDA
     */
    public void setListaEventoConsolidadoDDA(List<EventoConsolidadoDDAVO> listaEventoConsolidadoDDA) {
        this.listaEventoConsolidadoDDA = listaEventoConsolidadoDDA;
    }

    /**
     * @return o atributo tipoEventoTarifavel
     */
    public TipoEventoTarifavelVO getTipoEventoTarifavel() {
        return tipoEventoTarifavel;
    }

    /**
     * Define o atributo tipoEventoTarifavel
     */
    public void setTipoEventoTarifavel(TipoEventoTarifavelVO tipoEventoTarifavel) {
        this.tipoEventoTarifavel = tipoEventoTarifavel;
    }

    /**
     * @return o atributo tipoOperacaoLog
     */
    public TipoOperacaoLogVO getTipoOperacaoLog() {
        return tipoOperacaoLog;
    }

    /**
     * Define o atributo tipoOperacaoLog
     */
    public void setTipoOperacaoLog(TipoOperacaoLogVO tipoOperacaoLog) {
        this.tipoOperacaoLog = tipoOperacaoLog;
    }

    /**
     * @return o atributo tipoServicoTarifavel
     */
    public TipoServicoTarifavelVO getTipoServicoTarifavel() {
        return tipoServicoTarifavel;
    }

    /**
     * Define o atributo tipoServicoTarifavel
     */
    public void setTipoServicoTarifavel(TipoServicoTarifavelVO tipoServicoTarifavel) {
        this.tipoServicoTarifavel = tipoServicoTarifavel;
    }

}
