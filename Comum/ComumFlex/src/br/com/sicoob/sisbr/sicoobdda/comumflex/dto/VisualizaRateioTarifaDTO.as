package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.VisualizaRateioTarifaDTO", VisualizaRateioTarifaDTO);
	public class VisualizaRateioTarifaDTO
	{
		private var _selecionado:Boolean;
		private var _selecionadoTodos:Boolean;
		private var _listaIdRateio:ArrayCollection;
		private var _idRateioDDA:Number;
		private var _dataConciliacao:IDateTime;
		
		private var _descSituacaoRateio:String;
		private var _qtdApuradoBancoob:Number;
		private var _qtdApuradoCIP:Number;
		
		private var _valorTarifaBancoob:Number;
		private var _valorTarifaCIP:Number;
		private var _valorTotal:Number;
		private var _qtdTotalApuarado:Number;
		
		private var _idInstituicaoCentral:Number;
		private var _idInstituicaoSingular:Number;
		
		private var _dataHoraInclusao:IDateTime;
		private var _dataHoraAprovacao:IDateTime;
		private var _dataMovimento:IDateTime;
		
		private var _dataInicio:IDateTime;
		private var _dataFim:IDateTime;
		private var _codSituacaoRateio:Number;
		private var _codTipoDataEvento:Number;
		private var _dataInicioEventos:IDateTime;
		private var _dataFimEventos:IDateTime;
		
		private var _codEventoTarifavel:Number;
		private var _listaDadosRateio:ArrayCollection;
		private var _listaLancamentoRateios:ArrayCollection;
		private var _totalDadosRateio:VisualizaRateioTarifaDTO;
		private var _descEventoTarifavel:String;
		
		private var _valorApuradoBancoob:Number;
		private var _valorApuradoCIP:Number;
		private var _difValor:Number;
		private var _difQuantidade:Number;
		private var _valorRateioCoop:Number;
		
		private var _numCooperativa:Number;
		private var _dataMovimentoLoteLancamentoCCO:IDateTime;
		private var _numContaLancamentoCCO:Number;
		private var _numSeqLancamentoCCO:Number;
		private var _valorTotalRateio:Number;
		private var _descSituacaoRateioLancamento:String;
		
		private var _idRateioDDALancamento:Number;
		private var _dataHoraLancamentoCCO:IDateTime;
		private var _idUsuarioAprovacao:String;
		private var _codErroLancamentoCCO:String;
		private var _descCampoErroLancamentoCCO:String;
		private var _codSituacaoRateioLancamento:Number;
		private var _descErroLancamentoCCO:String;
		private var _listaSituacaoLancamento:ArrayCollection;
		private var _idInstituicaoTransfDebito:Number;
		private var _idInstituicaoRateio:Number;
		private var _qtdMensagemTotalRateio:Number;
		
		public function VisualizaRateioTarifaDTO(){}
		
		public function get idRateioDDA():Number {
			return _idRateioDDA;
		}
		
		public function set idRateioDDA(idRateioDDA:Number):void {
			this._idRateioDDA = idRateioDDA;
		}
		
		public function get dataConciliacao():IDateTime {
			return _dataConciliacao;
		}
		
		public function set dataConciliacao(dataConciliacao:IDateTime):void {
			this._dataConciliacao = dataConciliacao;
		}
		
		public function get descSituacaoRateio():String {
			return _descSituacaoRateio;
		}
		
		public function set descSituacaoRateio(descSituacaoRateio:String):void {
			this._descSituacaoRateio = descSituacaoRateio;
		}
		
		
		public function get qtdApuradoBancoob():Number {
			return _qtdApuradoBancoob;
		}
		
		public function set qtdApuradoBancoob(qtdApuradoBancoob:Number):void {
			this._qtdApuradoBancoob = qtdApuradoBancoob;
		}
		
		public function get qtdApuradoCIP():Number {
			return _qtdApuradoCIP;
		}
		
		public function set qtdApuradoCIP(qtdApuradoCIP:Number):void {
			this._qtdApuradoCIP = qtdApuradoCIP;
		}
		
		public function get valorTarifaBancoob():Number {
			return _valorTarifaBancoob;
		}
		
		public function set valorTarifaBancoob(valorTarifaBancoob:Number):void {
			this._valorTarifaBancoob = valorTarifaBancoob;
		}
		
		public function get valorTarifaCIP():Number {
			return _valorTarifaCIP;
		}
		
		public function set valorTarifaCIP(valorTarifaCIP:Number):void {
			this._valorTarifaCIP = valorTarifaCIP;
		}
		
		public function get valorTotal():Number {
			return _valorTotal;
		}
		
		public function set valorTotal(valorTotal:Number):void {
			this._valorTotal = valorTotal;
		}
		
		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}
		
		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}
		
		public function get qtdTotalApuarado():Number {
			return _qtdTotalApuarado;
		}
		
		public function set qtdTotalApuarado(qtdTotalApuarado:Number):void {
			this._qtdTotalApuarado = qtdTotalApuarado;
		}
		
		public function get idInstituicaoCentral():Number {
			return _idInstituicaoCentral;
		}
		
		public function set idInstituicaoCentral(idInstituicaoCentral:Number):void {
			this._idInstituicaoCentral = idInstituicaoCentral;
		}
		
		public function get idInstituicaoSingular():Number {
			return _idInstituicaoSingular;
		}
		
		public function set idInstituicaoSingular(idInstituicaoSingular:Number):void {
			this._idInstituicaoSingular = idInstituicaoSingular;
		}
		
		public function get dataHoraInclusao():IDateTime {
			return _dataHoraInclusao;
		}
		
		public function set dataHoraInclusao(dataHoraInclusao:IDateTime):void {
			this._dataHoraInclusao = dataHoraInclusao;
		}
		
		public function get dataHoraAprovacao():IDateTime {
			return _dataHoraAprovacao;
		}
		
		public function set dataHoraAprovacao(dataHoraAprovacao:IDateTime):void {
			this._dataHoraAprovacao = dataHoraAprovacao;
		}
		
		public function get dataInicio():IDateTime {
			return _dataInicio;
		}
		
		public function set dataInicio(dataInicio:IDateTime):void {
			this._dataInicio = dataInicio;
		}
		
		public function get dataFim():IDateTime {
			return _dataFim;
		}
		
		public function set dataFim(dataFim:IDateTime):void {
			this._dataFim = dataFim;
		}
		
		public function get codTipoDataEvento():Number {
			return _codTipoDataEvento;
		}
		
		public function set codTipoDataEvento(codTipoDataEvento:Number):void {
			this._codTipoDataEvento = codTipoDataEvento;
		}
		
		public function get codSituacaoRateio():Number {
			return _codSituacaoRateio;
		}
		
		public function set codSituacaoRateio(codSituacaoRateio:Number):void {
			this._codSituacaoRateio = codSituacaoRateio;
		}
		
		public function get selecionadoTodos():Boolean {
			return _selecionadoTodos;
		}
		
		public function set selecionadoTodos(selecionadoTodos:Boolean):void {
			this._selecionadoTodos = selecionadoTodos;
		}
		
		public function set selecionado(selecionado:Boolean):void {
			this._selecionado = selecionado;
		}
		
		public function get selecionado():Boolean {
			return _selecionado;
		}
		
		public function get listaIdRateio():ArrayCollection {
			return _listaIdRateio;
		}
		
		public function set listaIdRateio(listaIdRateio:ArrayCollection):void {
			this._listaIdRateio = listaIdRateio;
		}
		
		public function get dataInicioEventos():IDateTime {
			return _dataInicioEventos;
		}
		
		public function set dataInicioEventos(dataInicioEventos:IDateTime):void {
			this._dataInicioEventos = dataInicioEventos;
		}
		
		public function get dataFimEventos():IDateTime {
			return _dataFimEventos;
		}
		
		public function set dataFimEventos(dataFimEventos:IDateTime):void {
			this._dataFimEventos = dataFimEventos;
		}
		
		public function get codEventoTarifavel():Number {
			return _codEventoTarifavel;
		}
		
		public function set codEventoTarifavel(codEventoTarifavel:Number):void {
			this._codEventoTarifavel = codEventoTarifavel;
		}
		
		public function get listaDadosRateio():ArrayCollection {
			return _listaDadosRateio;
		}
		
		public function set listaDadosRateio(listaDadosRateio:ArrayCollection):void {
			this._listaDadosRateio = listaDadosRateio;
		}
		
		public function get listaLancamentoRateios():ArrayCollection {
			return _listaLancamentoRateios;
		}

		public function set listaLancamentoRateios(listaLancamentoRateios:ArrayCollection):void {
			this._listaLancamentoRateios = listaLancamentoRateios;
		}
		
		public function get totalDadosRateio():VisualizaRateioTarifaDTO {
			return _totalDadosRateio;
		}
		
		public function set totalDadosRateio(totalDadosRateio:VisualizaRateioTarifaDTO):void {
			this._totalDadosRateio = totalDadosRateio;
		}
		
		public function get descEventoTarifavel():String {
			return _descEventoTarifavel;
		}
		
		public function set descEventoTarifavel(descEventoTarifavel:String):void {
			this._descEventoTarifavel = descEventoTarifavel;
		}
		
		public function get valorApuradoBancoob():Number {
			return _valorApuradoBancoob;
		}
		
		public function set valorApuradoBancoob(valorApuradoBancoob:Number):void {
			this._valorApuradoBancoob = valorApuradoBancoob;
		}
		
		public function get valorApuradoCIP():Number {
			return _valorApuradoCIP;
		}
		
		public function set valorApuradoCIP(valorApuradoCIP:Number):void {
			this._valorApuradoCIP = valorApuradoCIP;
		}
		
		public function get difQuantidade():Number {
			return _difQuantidade;
		}
		
		public function set difQuantidade(difQuantidade:Number):void {
			this._difQuantidade = difQuantidade;
		}
		
		public function get difValor():Number {
			return _difValor;
		}
		
		public function set difValor(difValor:Number):void {
			this._difValor = difValor;
		}
		
		public function get valorRateioCoop():Number {
			return _valorRateioCoop;
		}
		
		public function set valorRateioCoop(valorRateioCoop:Number):void {
			this._valorRateioCoop = valorRateioCoop;
		}
		
		public function get numCooperativa():Number {
			return _numCooperativa;
		}
		
		public function set numCooperativa(numCooperativa:Number):void {
			this._numCooperativa = numCooperativa;
		}
		
		public function get dataMovimentoLoteLancamentoCCO():IDateTime {
			return _dataMovimentoLoteLancamentoCCO;
		}
		
		public function set dataMovimentoLoteLancamentoCCO(dataMovimentoLoteLancamentoCCO:IDateTime):void {
			this._dataMovimentoLoteLancamentoCCO = dataMovimentoLoteLancamentoCCO;
		}
		
		public function get numContaLancamentoCCO():Number {
			return _numContaLancamentoCCO;
		}
		
		public function set numContaLancamentoCCO(numContaLancamentoCCO:Number):void {
			this._numContaLancamentoCCO = numContaLancamentoCCO;
		}
		
		public function get numSeqLancamentoCCO():Number {
			return _numSeqLancamentoCCO;
		}
		
		public function set numSeqLancamentoCCO(numSeqLancamentoCCO:Number):void {
			this._numSeqLancamentoCCO = numSeqLancamentoCCO;
		}
		
		public function get valorTotalRateio():Number {
			return _valorTotalRateio;
		}
		
		public function set valorTotalRateio(valorTotalRateio:Number):void {
			this._valorTotalRateio = valorTotalRateio;
		}
		
		public function get idRateioDDALancamento():Number {
			return _idRateioDDALancamento;
		}
		
		public function set idRateioDDALancamento(idRateioDDALancamento:Number):void {
			this._idRateioDDALancamento = idRateioDDALancamento;
		}
		
		public function get dataHoraLancamentoCCO():IDateTime {
			return _dataHoraLancamentoCCO;
		}
		
		public function set dataHoraLancamentoCCO(dataHoraLancamentoCCO:IDateTime):void {
			this._dataHoraLancamentoCCO = dataHoraLancamentoCCO;
		}
		public function get idUsuarioAprovacao():String {
			return _idUsuarioAprovacao;
		}
		
		public function set idUsuarioAprovacao(idUsuarioAprovacao:String):void {
			this._idUsuarioAprovacao = idUsuarioAprovacao;
		}
		public function get codErroLancamentoCCO():String {
			return _codErroLancamentoCCO;
		}
		
		public function set codErroLancamentoCCO(codErroLancamentoCCO:String):void {
			this._codErroLancamentoCCO = codErroLancamentoCCO;
		}
		
		public function get descCampoErroLancamentoCCO():String {
			return _descCampoErroLancamentoCCO;
		}
		
		public function set descCampoErroLancamentoCCO(descCampoErroLancamentoCCO:String):void {
			this._descCampoErroLancamentoCCO = descCampoErroLancamentoCCO;
		}
		public function get codSituacaoRateioLancamento():Number {
			return _codSituacaoRateioLancamento;
		}
		
		public function set codSituacaoRateioLancamento(codSituacaoRateioLancamento:Number):void {
			this._codSituacaoRateioLancamento = codSituacaoRateioLancamento;
		}
		public function get descErroLancamentoCCO():String {
			return _descErroLancamentoCCO;
		}
		
		public function set descErroLancamentoCCO(descErroLancamentoCCO:String):void {
			this._descErroLancamentoCCO = descErroLancamentoCCO;
		}
		
		public function get listaSituacaoLancamento():ArrayCollection {
			return _listaSituacaoLancamento;
		}
		
		public function set listaSituacaoLancamento(listaSituacaoLancamento:ArrayCollection):void {
			this._listaSituacaoLancamento = listaSituacaoLancamento;
		}
		
		public function get descSituacaoRateioLancamento():String {
			return _descSituacaoRateioLancamento;
		}
		
		public function set descSituacaoRateioLancamento(descSituacaoRateioLancamento:String):void {
			this._descSituacaoRateioLancamento = descSituacaoRateioLancamento;
		}
		
		public function get idInstituicaoTransfDebito():Number {
			return _idInstituicaoTransfDebito;
		}
		
		public function set idInstituicaoTransfDebito(idInstituicaoTransfDebito:Number):void {
			this._idInstituicaoTransfDebito = idInstituicaoTransfDebito;
		}
		
		public function get idInstituicaoRateio():Number {
			return _idInstituicaoRateio;
		}
		
		public function set idInstituicaoRateio(idInstituicaoRateio:Number):void {
			this._idInstituicaoRateio = idInstituicaoRateio;
		}
		public function get qtdMensagemTotalRateio():Number {
			return _qtdMensagemTotalRateio;
		}
		
		public function set qtdMensagemTotalRateio(qtdMensagemTotalRateio:Number):void {
			this._qtdMensagemTotalRateio = qtdMensagemTotalRateio;
		}
	}
}