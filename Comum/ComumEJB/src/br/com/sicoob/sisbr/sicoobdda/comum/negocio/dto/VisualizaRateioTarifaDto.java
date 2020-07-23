package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateioLancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.tipos.DateTime;

/**
 * VisualizaRateioTarifaDTO é responsável por
 * 
 * @author samuell.ramos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.VisualizaRateioTarifaDTO")
public class VisualizaRateioTarifaDto extends BancoobDto {


    /**
     * 
     */
    private static final long serialVersionUID = -1573833585206887645L;
    private Boolean selecionado;
    private Boolean selecionadoTodos;
    private List<Long> listaIdRateio;
    private Long idRateioDDA;
    private DateTime dataConciliacao;

    private String descSituacaoRateio;
    private Integer qtdApuradoBancoob;
    private Integer qtdApuradoCIP;

    private BigDecimal valorTarifaBancoob;
    private BigDecimal valorTarifaCIP;
    private BigDecimal valorTotal;
    private Long qtdTotalApuarado;

    private Integer idInstituicaoCentral;
    private Integer idInstituicaoSingular;

    private DateTimeDB dataHoraInclusao;
    private DateTimeDB dataHoraAprovacao;
    private DateTimeDB dataMovimento;
    
    private DateTimeDB dataInicio;
    private DateTimeDB dataFim;
    private Integer codSituacaoRateio;
    private Integer codTipoDataEvento;
    private DateTimeDB dataInicioEventos;
    private DateTimeDB dataFimEventos;
    
    private Integer codEventoTarifavel;
    private List<VisualizaRateioTarifaDto> listaDadosRateio;
    private List<VisualizaRateioTarifaDto> listaLancamentoRateios;
    private VisualizaRateioTarifaDto totalDadosRateio;
    private String descEventoTarifavel;

    private BigDecimal valorApuradoBancoob;
    private BigDecimal valorApuradoCIP;
    private BigDecimal difValor;
    private Integer difQuantidade;
    private BigDecimal valorRateioCoop;

    private Integer numCooperativa;
    private DateTimeDB dataMovimentoLoteLancamentoCCO;
    private Long numContaLancamentoCCO;
    private Long numSeqLancamentoCCO;
    private BigDecimal valorTotalRateio;
    private String descSituacaoRateioLancamento;

    private Long idRateioDDALancamento;
    private DateTimeDB dataHoraLancamentoCCO;
    private String idUsuarioAprovacao;
    private String codErroLancamentoCCO;
    private String descCampoErroLancamentoCCO;
    private Long codSituacaoRateioLancamento;
    private String descErroLancamentoCCO;

    private Integer idInstituicaoRateio;
    private Integer idInstituicaoTransfDebito;
    private Integer qtdMensagemTotalRateio;

    private List<SituacaoRateioLancamento> listaSituacaoLancamento;
    /**
     * 
     */
    public VisualizaRateioTarifaDto() {
        super();
    }

    /**
     * DETALHAR LANCAMENTO
     * 
     * @param idRateioDDALancamento
     * @param idInstituicaoTransfDebito
     * @param idInstituicaoRateio
     * @param cooperativa
     * @param numSeqLancamentoCCO
     * @param dataMovimento
     * @param numContaLancamentoCCO
     * @param descEventoTarifavelExtrato
     * @param valorTotalRateio
     * @param dataHoraLancamentoCCO
     * @param descSituacaoRateioLancamento
     * @param idRateioDDA
     * @param idUsuarioAprovacao
     * @param codSituacaoRateioLancamento
     * @param descErroLancamentoCCO
     */
    public VisualizaRateioTarifaDto(Long idRateioDDALancamento, Integer idInstituicaoTransfDebito, Integer idInstituicaoRateio, Integer cooperativa, Long numSeqLancamentoCCO,
            Date dataMovimento,
            Long numContaLancamentoCCO,
            String descEventoTarifavelExtrato, BigDecimal valorTotalRateio, Date dataHoraLancamentoCCO, String descSituacaoRateioLancamento, Long idRateioDDA,
            String idUsuarioAprovacao, Long codSituacaoRateioLancamento, String descErroLancamentoCCO, String codErroLancamentoCCO) {
        this.idRateioDDALancamento = idRateioDDALancamento;
        this.numCooperativa = cooperativa;
        this.numSeqLancamentoCCO = numSeqLancamentoCCO;
        this.dataMovimentoLoteLancamentoCCO = ObjectUtil.isNull(dataMovimento) ? null : new DateTimeDB(dataMovimento.getTime()); 
        this.numContaLancamentoCCO = numContaLancamentoCCO;
        this.descEventoTarifavel = descEventoTarifavelExtrato;
        this.valorTotalRateio = valorTotalRateio;
        this.dataHoraLancamentoCCO = ObjectUtil.isNull(dataHoraLancamentoCCO) ? null : new DateTimeDB(dataHoraLancamentoCCO.getTime());
        this.descSituacaoRateioLancamento = descSituacaoRateioLancamento;
        this.idRateioDDA = idRateioDDA;
        this.idUsuarioAprovacao = idUsuarioAprovacao;
        this.codSituacaoRateioLancamento = codSituacaoRateioLancamento;
        this.codErroLancamentoCCO = ObjectUtil.isNull(codErroLancamentoCCO)? null : codErroLancamentoCCO.trim();
        this.descErroLancamentoCCO = ObjectUtil.isNull(descErroLancamentoCCO)? null : descErroLancamentoCCO.trim();
        this.idInstituicaoTransfDebito = idInstituicaoTransfDebito;
        this.idInstituicaoRateio = idInstituicaoRateio;

    }

    /**
     * LISTA LANCAMENTOS
     * 
     * @param numCooperativa
     * @param dataMovimentoLoteLancamentoCCO
     * @param idRateioDDA
     * @param numContaLancamentoCCO
     * @param numSeqLancamentoCCO
     * @param descEventoTarifavelExtrato
     * @param valorTotalRateio
     * @param descSituacaoRateioLancamento
     * @param idRateioDDALancamento
     * @param idInstituicao
     */
    public VisualizaRateioTarifaDto(Integer numCooperativa, Date dataMovimentoLoteLancamentoCCO, Long idRateioDDA, Long numContaLancamentoCCO, Long numSeqLancamentoCCO,
            String descEventoTarifavelExtrato, BigDecimal valorTotalRateio, String descSituacaoRateioLancamento, Long idRateioDDALancamento, Number idInstituicao,
            Number idInstituicaoTransfDebito, Integer qtdMensagemTotalRateio) {
        this.idRateioDDA = idRateioDDA;
        this.numCooperativa = numCooperativa;
        this.dataMovimentoLoteLancamentoCCO = ObjectUtil.isNull(dataMovimentoLoteLancamentoCCO) ? null : new DateTimeDB(dataMovimentoLoteLancamentoCCO.getTime());
        this.numContaLancamentoCCO = numContaLancamentoCCO;
        this.numSeqLancamentoCCO = numSeqLancamentoCCO;
        this.descEventoTarifavel = descEventoTarifavelExtrato;
        this.valorTotalRateio = valorTotalRateio;
        this.descSituacaoRateioLancamento = descSituacaoRateioLancamento;
        this.idRateioDDALancamento = idRateioDDALancamento;
        this.qtdMensagemTotalRateio = qtdMensagemTotalRateio;
    }

    /**
     * TOTALIZADORES DADOS RATEIO
     * 
     * @param qtdApuradoSicoob
     * @param valorApuradoSicoob
     * @param qtdApuradoCIP
     * @param valorApuradoCIP
     * @param difValor
     * @param difQuantidade
     * @param valorRateioCoop
     */
    public VisualizaRateioTarifaDto(Integer qtdApuradoSicoob, Integer qtdApuradoCIP, BigDecimal valorTarifaCIP, BigDecimal valorTarifaBancoob) {
        this.qtdApuradoBancoob = qtdApuradoSicoob;
        this.qtdApuradoCIP = qtdApuradoCIP;
        this.valorTarifaCIP = valorTarifaCIP;
        this.valorTarifaBancoob = valorTarifaBancoob;
    }



    /**
     * LISTA DADOS RATEIO
     * 
     * @param idRateioDDA
     * @param dataMovimento
     * @param descEventoTarifavel
     * @param valorTarifaCIP
     * @param qtdApuradoSicoob
     * @param valorApuradoSicoob
     * @param qtdApuradoCIP
     * @param valorApuradoCIP
     * @param difValor
     * @param difQuantidade
     * @param valorTarifaBancoob
     * @param valorRateioCoop
     */
    public VisualizaRateioTarifaDto(Long idRateioDDA, Date dataMovimento, String descEventoTarifavel, BigDecimal valorTarifaCIP, Integer qtdApuradoSicoob,
            BigDecimal valorApuradoSicoob, Integer qtdApuradoCIP, BigDecimal valorApuradoCIP, BigDecimal valorTarifaBancoob,
            BigDecimal valorRateioCoop) {
    	
    	valorApuradoCIP = ObjectUtil.isNull(valorApuradoCIP) ? BigDecimal.ZERO : valorApuradoCIP;
    	valorApuradoSicoob = ObjectUtil.isNull(valorApuradoSicoob) ? BigDecimal.ZERO : valorApuradoSicoob;
    	qtdApuradoSicoob = ObjectUtil.isNull(qtdApuradoSicoob) ? Constantes.INTEGER_ZERO : qtdApuradoSicoob;
    	qtdApuradoCIP = ObjectUtil.isNull(qtdApuradoCIP) ? Constantes.INTEGER_ZERO : qtdApuradoCIP;
    	
        this.idRateioDDA = idRateioDDA;
        this.dataMovimento = ObjectUtil.isNull(dataMovimento) ? null : new DateTimeDB(dataMovimento.getTime());
        this.descEventoTarifavel = descEventoTarifavel;
        this.valorTarifaCIP = valorTarifaCIP;
        this.qtdApuradoBancoob = qtdApuradoSicoob;
        this.valorApuradoBancoob = valorApuradoSicoob;
        this.qtdApuradoCIP = qtdApuradoCIP;
        this.valorApuradoCIP  = valorApuradoCIP;
        this.difValor = valorApuradoBancoob.subtract(valorApuradoCIP);
        this.difQuantidade = (qtdApuradoSicoob - qtdApuradoCIP);
        this.valorTarifaBancoob = valorTarifaBancoob;
        this.valorRateioCoop = valorRateioCoop;
    }

    /**
     * LISTA RATEIO DE TARIFAS
     * 
     * @param idRateioDDA
     * @param dataHoraInclusao
     * @param descSituacaoRateio
     * @param dataHoraAprovacao
     * @param qtdTotalApuarado
     * @param valorTotal
     * @param dataInicioEventos
     * @param dataFimEventos
     */
    public VisualizaRateioTarifaDto(Long idRateioDDA, Date dataHoraInclusao, String descSituacaoRateio, Date dataHoraAprovacao, Long qtdTotalApuarado,
            BigDecimal valorTotal, Date dataInicioEventos, Date dataFimEventos) {
        this.idRateioDDA = idRateioDDA;
        this.descSituacaoRateio = descSituacaoRateio;
        this.valorTotal = valorTotal;
        this.qtdTotalApuarado = qtdTotalApuarado;
        this.dataHoraInclusao = !ObjectUtil.isNull(dataHoraInclusao) ? new DateTimeDB(dataHoraInclusao.getTime()) : null;
        this.dataHoraAprovacao = !ObjectUtil.isNull(dataHoraAprovacao) ? new DateTimeDB(dataHoraAprovacao.getTime()) : null;
        this.dataInicioEventos = !ObjectUtil.isNull(dataInicioEventos) ? new DateTimeDB(dataInicioEventos.getTime()) : null;
        this.dataFimEventos = !ObjectUtil.isNull(dataFimEventos) ? new DateTimeDB(dataFimEventos.getTime()) : null;
    }

    /**
     * @return o atributo idRateioDDA
     */
    public Long getIdRateioDDA() {
        return idRateioDDA;
    }

    /**
     * Define o atributo idRateioDDA
     */
    public void setIdRateioDDA(Long idRateioDDA) {
        this.idRateioDDA = idRateioDDA;
    }

    /**
     * @return o atributo dataConciliacao
     */
    public DateTime getDataConciliacao() {
        return dataConciliacao;
    }

    /**
     * Define o atributo dataConciliacao
     */
    public void setDataConciliacao(DateTime dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
    }

    /**
     * @return o atributo descSituacaoRateio
     */
    public String getDescSituacaoRateio() {
        return descSituacaoRateio;
    }

    /**
     * Define o atributo descSituacaoRateio
     */
    public void setDescSituacaoRateio(String descSituacaoRateio) {
        this.descSituacaoRateio = descSituacaoRateio;
    }

    /**
     * @return o atributo qtdApuradoBancoob
     */
    public Integer getQtdApuradoBancoob() {
        return qtdApuradoBancoob;
    }

    /**
     * Define o atributo qtdApuradoBancoob
     */
    public void setQtdApuradoBancoob(Integer qtdApuradoBancoob) {
        this.qtdApuradoBancoob = qtdApuradoBancoob;
    }

    /**
     * @return o atributo qtdApuradoCIP
     */
    public Integer getQtdApuradoCIP() {
        return qtdApuradoCIP;
    }

    /**
     * Define o atributo qtdApuradoCIP
     */
    public void setQtdApuradoCIP(Integer qtdApuradoCIP) {
        this.qtdApuradoCIP = qtdApuradoCIP;
    }

    /**
     * @return o atributo valorTarifaBancoob
     */
    public BigDecimal getValorTarifaBancoob() {
        return valorTarifaBancoob;
    }

    /**
     * Define o atributo valorTarifaBancoob
     */
    public void setValorTarifaBancoob(BigDecimal valorTarifaBancoob) {
        this.valorTarifaBancoob = valorTarifaBancoob;
    }

    /**
     * @return o atributo valorTarifaCIP
     */
    public BigDecimal getValorTarifaCIP() {
        return valorTarifaCIP;
    }

    /**
     * Define o atributo valorTarifaCIP
     */
    public void setValorTarifaCIP(BigDecimal valorTarifaCIP) {
        this.valorTarifaCIP = valorTarifaCIP;
    }

    /**
     * @return o atributo valorTotal
     */
    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    /**
     * Define o atributo valorTotal
     */
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return o atributo qtdTotalApuarado
     */
    public Long getQtdTotalApuarado() {
        return qtdTotalApuarado;
    }


    /**
     * Define o atributo qtdTotalApuarado
     */
    public void setQtdTotalApuarado(Long qtdTotalApuarado) {
        this.qtdTotalApuarado = qtdTotalApuarado;
    }

    /**
     * @return o atributo idInstituicaoCentral
     */
    public Integer getIdInstituicaoCentral() {
        return idInstituicaoCentral;
    }

    /**
     * Define o atributo idInstituicaoCentral
     */
    public void setIdInstituicaoCentral(Integer idInstituicaoCentral) {
        this.idInstituicaoCentral = idInstituicaoCentral;
    }

    /**
     * @return o atributo idInstituicaoSingular
     */
    public Integer getIdInstituicaoSingular() {
        return idInstituicaoSingular;
    }

    /**
     * Define o atributo idInstituicaoSingular
     */
    public void setIdInstituicaoSingular(Integer idInstituicaoSingular) {
        this.idInstituicaoSingular = idInstituicaoSingular;
    }

    /**
     * @return o atributo dataHoraInclusao
     */
    public DateTimeDB getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    /**
     * Define o atributo dataHoraInclusao
     */
    public void setDataHoraInclusao(DateTimeDB dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    /**
     * @return o atributo dataHoraAprovacao
     */
    public DateTimeDB getDataHoraAprovacao() {
        return dataHoraAprovacao;
    }

    /**
     * Define o atributo dataHoraAprovacao
     */
    public void setDataHoraAprovacao(DateTimeDB dataHoraAprovacao) {
        this.dataHoraAprovacao = dataHoraAprovacao;
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
     * @return o atributo dataInicio
     */
    public DateTimeDB getDataInicio() {
        return dataInicio;
    }

    /**
     * Define o atributo dataInicio
     */
    public void setDataInicio(DateTimeDB dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return o atributo dataFim
     */
    public DateTimeDB getDataFim() {
        return dataFim;
    }

    /**
     * Define o atributo dataFim
     */
    public void setDataFim(DateTimeDB dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return o atributo codSituacaoRateio
     */
    public Integer getCodSituacaoRateio() {
        return codSituacaoRateio;
    }

    /**
     * Define o atributo codSituacaoRateio
     */
    public void setCodSituacaoRateio(Integer codSituacaoRateio) {
        this.codSituacaoRateio = codSituacaoRateio;
    }

    /**
     * @return o atributo codTipoDataEvento
     */
    public Integer getCodTipoDataEvento() {
        return codTipoDataEvento;
    }

    /**
     * Define o atributo codTipoDataEvento
     */
    public void setCodTipoDataEvento(Integer codTipoDataEvento) {
        this.codTipoDataEvento = codTipoDataEvento;
    }

    /**
     * @return o atributo selecionado
     */
    public Boolean getSelecionado() {
        return selecionado;
    }

    /**
     * Define o atributo selecionado
     */
    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return o atributo selecionadoTodos
     */
    public Boolean getSelecionadoTodos() {
        return selecionadoTodos;
    }

    /**
     * Define o atributo selecionadoTodos
     */
    public void setSelecionadoTodos(Boolean selecionadoTodos) {
        this.selecionadoTodos = selecionadoTodos;
    }

    /**
     * @return o atributo listaIdRateio
     */
    public List<Long> getListaIdRateio() {
        return listaIdRateio;
    }


    /**
     * @return o atributo dataInicioEventos
     */
    public DateTimeDB getDataInicioEventos() {
        return dataInicioEventos;
    }

    /**
     * Define o atributo dataInicioEventos
     */
    public void setDataInicioEventos(DateTimeDB dataInicioEventos) {
        this.dataInicioEventos = dataInicioEventos;
    }

    /**
     * @return o atributo dataFimEventos
     */
    public DateTimeDB getDataFimEventos() {
        return dataFimEventos;
    }

    /**
     * Define o atributo dataFimEventos
     */
    public void setDataFimEventos(DateTimeDB dataFimEventos) {
        this.dataFimEventos = dataFimEventos;
    }

    /**
     * Define o atributo listaIdRateio
     */
    public void setListaIdRateio(List<Long> listaIdRateio) {
        this.listaIdRateio = listaIdRateio;
    }

    /**
     * @return o atributo listaDadosRateio
     */
    public List<VisualizaRateioTarifaDto> getListaDadosRateio() {
        return listaDadosRateio;
    }

    /**
     * Define o atributo listaDadosRateio
     */
    public void setListaDadosRateio(List<VisualizaRateioTarifaDto> listaDadosRateio) {
        this.listaDadosRateio = listaDadosRateio;
    }

    /**
     * @return o atributo listaLancamentoRateios
     */
    public List<VisualizaRateioTarifaDto> getListaLancamentoRateios() {
        return listaLancamentoRateios;
    }

    /**
     * Define o atributo listaLancamentoRateios
     */
    public void setListaLancamentoRateios(List<VisualizaRateioTarifaDto> listaLancamentoRateios) {
        this.listaLancamentoRateios = listaLancamentoRateios;
    }

    /**
     * @return o atributo totalDadosRateio
     */
    public VisualizaRateioTarifaDto getTotalDadosRateio() {
        return totalDadosRateio;
    }

    /**
     * Define o atributo totalDadosRateio
     */
    public void setTotalDadosRateio(VisualizaRateioTarifaDto totalDadosRateio) {
        this.totalDadosRateio = totalDadosRateio;
    }

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
     * @return o atributo descEventoTarifavel
     */
    public String getDescEventoTarifavel() {
        return descEventoTarifavel;
    }

    /**
     * Define o atributo descEventoTarifavel
     */
    public void setDescEventoTarifavel(String descEventoTarifavel) {
        this.descEventoTarifavel = descEventoTarifavel;
    }

    /**
     * @return o atributo valorApuradoBancoob
     */
    public BigDecimal getValorApuradoBancoob() {
        return valorApuradoBancoob;
    }

    /**
     * Define o atributo valorApuradoBancoob
     */
    public void setValorApuradoBancoob(BigDecimal valorApuradoBancoob) {
        this.valorApuradoBancoob = valorApuradoBancoob;
    }

    /**
     * @return o atributo valorApuradoCIP
     */
    public BigDecimal getValorApuradoCIP() {
        return valorApuradoCIP;
    }

    /**
     * Define o atributo valorApuradoCIP
     */
    public void setValorApuradoCIP(BigDecimal valorApuradoCIP) {
        this.valorApuradoCIP = valorApuradoCIP;
    }

    /**
     * @return o atributo difValor
     */
    public BigDecimal getDifValor() {
        return difValor;
    }

    /**
     * Define o atributo difValor
     */
    public void setDifValor(BigDecimal difValor) {
        this.difValor = difValor;
    }

    /**
     * @return o atributo difQuantidade
     */
    public Integer getDifQuantidade() {
        return difQuantidade;
    }

    /**
     * Define o atributo difQuantidade
     */
    public void setDifQuantidade(Integer difQuantidade) {
        this.difQuantidade = difQuantidade;
    }

    /**
     * @return o atributo valorRateioCoop
     */
    public BigDecimal getValorRateioCoop() {
        return valorRateioCoop;
    }

    /**
     * Define o atributo valorRateioCoop
     */
    public void setValorRateioCoop(BigDecimal valorRateioCoop) {
        this.valorRateioCoop = valorRateioCoop;
    }

    /**
     * @return o atributo numCooperativa
     */
    public Integer getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(Integer numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

    /**
     * @return o atributo dataMovimentoLoteLancamentoCCO
     */
    public DateTimeDB getDataMovimentoLoteLancamentoCCO() {
        return dataMovimentoLoteLancamentoCCO;
    }

    /**
     * Define o atributo dataMovimentoLoteLancamentoCCO
     */
    public void setDataMovimentoLoteLancamentoCCO(DateTimeDB dataMovimentoLoteLancamentoCCO) {
        this.dataMovimentoLoteLancamentoCCO = dataMovimentoLoteLancamentoCCO;
    }

    /**
     * @return o atributo idRateioDDALancamento
     */
    public Long getIdRateioDDALancamento() {
        return idRateioDDALancamento;
    }

    /**
     * Define o atributo idRateioDDALancamento
     */
    public void setIdRateioDDALancamento(Long idRateioDDALancamento) {
        this.idRateioDDALancamento = idRateioDDALancamento;
    }

    /**
     * @return o atributo dataHoraLancamentoCCO
     */
    public DateTimeDB getDataHoraLancamentoCCO() {
        return dataHoraLancamentoCCO;
    }

    /**
     * Define o atributo dataHoraLancamentoCCO
     */
    public void setDataHoraLancamentoCCO(DateTimeDB dataHoraLancamentoCCO) {
        this.dataHoraLancamentoCCO = dataHoraLancamentoCCO;
    }

    /**
     * @return o atributo idUsuarioAprovacao
     */
    public String getIdUsuarioAprovacao() {
        return idUsuarioAprovacao;
    }

    /**
     * Define o atributo idUsuarioAprovacao
     */
    public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
        this.idUsuarioAprovacao = idUsuarioAprovacao;
    }

    /**
     * @return o atributo numContaLancamentoCCO
     */
    public Long getNumContaLancamentoCCO() {
        return numContaLancamentoCCO;
    }

    /**
     * Define o atributo numContaLancamentoCCO
     */
    public void setNumContaLancamentoCCO(Long numContaLancamentoCCO) {
        this.numContaLancamentoCCO = numContaLancamentoCCO;
    }

    /**
     * @return o atributo numSeqLancamentoCCO
     */
    public Long getNumSeqLancamentoCCO() {
        return numSeqLancamentoCCO;
    }

    /**
     * Define o atributo numSeqLancamentoCCO
     */
    public void setNumSeqLancamentoCCO(Long numSeqLancamentoCCO) {
        this.numSeqLancamentoCCO = numSeqLancamentoCCO;
    }

    /**
     * @return o atributo valorTotalRateio
     */
    public BigDecimal getValorTotalRateio() {
        return valorTotalRateio;
    }

    /**
     * Define o atributo valorTotalRateio
     */
    public void setValorTotalRateio(BigDecimal valorTotalRateio) {
        this.valorTotalRateio = valorTotalRateio;
    }

    /**
     * @return o atributo descSituacaoRateioLancamento
     */
    public String getDescSituacaoRateioLancamento() {
        return descSituacaoRateioLancamento;
    }

    /**
     * Define o atributo descSituacaoRateioLancamento
     */
    public void setDescSituacaoRateioLancamento(String descSituacaoRateioLancamento) {
        this.descSituacaoRateioLancamento = descSituacaoRateioLancamento;
    }

    /**
	 * @return the codErroLancamentoCCO
	 */
	public String getCodErroLancamentoCCO() {
		return codErroLancamentoCCO;
	}

	/**
	 * @param codErroLancamentoCCO the codErroLancamentoCCO to set
	 */
	public void setCodErroLancamentoCCO(String codErroLancamentoCCO) {
		this.codErroLancamentoCCO = codErroLancamentoCCO;
	}

	/**
     * @return o atributo descCampoErroLancamentoCCO
     */
    public String getDescCampoErroLancamentoCCO() {
        return descCampoErroLancamentoCCO;
    }

    /**
     * Define o atributo descCampoErroLancamentoCCO
     */
    public void setDescCampoErroLancamentoCCO(String descCampoErroLancamentoCCO) {
        this.descCampoErroLancamentoCCO = descCampoErroLancamentoCCO;
    }

    /**
     * @return o atributo codSituacaoRateioLancamento
     */
    public Long getCodSituacaoRateioLancamento() {
        return codSituacaoRateioLancamento;
    }

    /**
     * Define o atributo codSituacaoRateioLancamento
     */
    public void setCodSituacaoRateioLancamento(Long codSituacaoRateioLancamento) {
        this.codSituacaoRateioLancamento = codSituacaoRateioLancamento;
    }

    /**
     * @return o atributo descErroLancamentoCCO
     */
    public String getDescErroLancamentoCCO() {
        return descErroLancamentoCCO;
    }

    /**
     * Define o atributo descErroLancamentoCCO
     */
    public void setDescErroLancamentoCCO(String descErroLancamentoCCO) {
        this.descErroLancamentoCCO = descErroLancamentoCCO;
    }

    /**
     * @return o atributo listaSituacaoLancamento
     */
    public List<SituacaoRateioLancamento> getListaSituacaoLancamento() {
        return listaSituacaoLancamento;
    }

    /**
     * Define o atributo listaSituacaoLancamento
     */
    public void setListaSituacaoLancamento(List<SituacaoRateioLancamento> listaSituacaoLancamento) {
        this.listaSituacaoLancamento = listaSituacaoLancamento;
    }

    /**
     * @return o atributo idInstituicaoTransfDebito
     */
    public Integer getIdInstituicaoTransfDebito() {
        return idInstituicaoTransfDebito;
    }

    /**
     * Define o atributo idInstituicaoTransfDebito
     */
    public void setIdInstituicaoTransfDebito(Integer idInstituicaoTransfDebito) {
        this.idInstituicaoTransfDebito = idInstituicaoTransfDebito;
    }

    /**
     * @return o atributo idInstituicaoRateio
     */
    public Integer getIdInstituicaoRateio() {
        return idInstituicaoRateio;
    }

    /**
     * Define o atributo idInstituicaoRateio
     */
    public void setIdInstituicaoRateio(Integer idInstituicaoRateio) {
        this.idInstituicaoRateio = idInstituicaoRateio;
    }

	/**
	 * @return the qtdMensagemTotalRateio
	 */
	public Integer getQtdMensagemTotalRateio() {
		return qtdMensagemTotalRateio;
	}

	/**
	 * @param qtdMensagemTotalRateio the qtdMensagemTotalRateio to set
	 */
	public void setQtdMensagemTotalRateio(Integer qtdMensagemTotalRateio) {
		this.qtdMensagemTotalRateio = qtdMensagemTotalRateio;
	}

}

