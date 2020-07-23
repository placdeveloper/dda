package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.RateioDDALancamentoVO", RateioDDALancamentoVO);

	public class RateioDDALancamentoVO {

		private var _id:Number;
		private var _rateioDDA:RateioDDAVO;
		private var _idInstituicao:Number;
		private var _eventoTarifavelDDA:EventoTarifavelDDAVO;
		private var _qtdMensagemTotalRateio:Number;
		private var _valorTotalRateio:Number;
		private var _situacaoRateioLancamento:SituacaoRateioLancamentoVO;
		private var _idInstituicaoUsuarioLogado:Number;
		private var _idUsuarioLogado:String;
		private var _dataMovimentoLoteLancamentoCCO:IDateTime;
		private var _dataHoraLancamentoCCO:IDateTime;
		private var _numContaLancamentoCCO:Number;
		private var _numSeqLancamentoCCO:Number;
		private var _codErroLancamentoCCO:String;
		private var _descErroLancamentoCCO:String;
		private var _codRetornoLancamentoCCO:Number;
		private var _descCampoErroLancamentoCCO:String;
		private var _idInstituicaoTransferenciaDebito:Number;
		private var _tipoOperacaoLog:TipoOperacaoLogVO;
		
		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set rateioDDA(rateioDDA:RateioDDAVO):void {
			this._rateioDDA = rateioDDA;
		}

		public function get rateioDDA():RateioDDAVO {
			return _rateioDDA;
		}

		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}

		public function get idInstituicao():Number {
			return _idInstituicao;
		}

		public function set eventoTarifavelDDA(eventoTarifavelDDA:EventoTarifavelDDAVO):void {
			this._eventoTarifavelDDA = eventoTarifavelDDA;
		}

		public function get eventoTarifavelDDA():EventoTarifavelDDAVO {
			return _eventoTarifavelDDA;
		}

		public function set qtdMensagemTotalRateio(qtdMensagemTotalRateio:Number):void {
			this._qtdMensagemTotalRateio = qtdMensagemTotalRateio;
		}

		public function get qtdMensagemTotalRateio():Number {
			return _qtdMensagemTotalRateio;
		}

		public function set valorTotalRateio(valorTotalRateio:Number):void {
			this._valorTotalRateio = valorTotalRateio;
		}

		public function get valorTotalRateio():Number {
			return _valorTotalRateio;
		}

		public function set situacaoRateioLancamento(situacaoRateioLancamento:SituacaoRateioLancamentoVO):void {
			this._situacaoRateioLancamento = situacaoRateioLancamento;
		}

		public function get situacaoRateioLancamento():SituacaoRateioLancamentoVO {
			return _situacaoRateioLancamento;
		}

		public function set idInstituicaoUsuarioLogado(idInstituicaoUsuarioLogado:Number):void {
			this._idInstituicaoUsuarioLogado = idInstituicaoUsuarioLogado;
		}

		public function get idInstituicaoUsuarioLogado():Number {
			return _idInstituicaoUsuarioLogado;
		}

		public function set idUsuarioLogado(idUsuarioLogado:String):void {
			this._idUsuarioLogado = idUsuarioLogado;
		}

		public function get idUsuarioLogado():String {
			return _idUsuarioLogado;
		}

		public function set dataMovimentoLoteLancamentoCCO(dataMovimentoLoteLancamentoCCO:IDateTime):void {
			this._dataMovimentoLoteLancamentoCCO = dataMovimentoLoteLancamentoCCO;
		}

		public function get dataMovimentoLoteLancamentoCCO():IDateTime {
			return _dataMovimentoLoteLancamentoCCO;
		}

		public function set dataHoraLancamentoCCO(dataHoraLancamentoCCO:IDateTime):void {
			this._dataHoraLancamentoCCO = dataHoraLancamentoCCO;
		}

		public function get dataHoraLancamentoCCO():IDateTime {
			return _dataHoraLancamentoCCO;
		}

		public function set numContaLancamentoCCO(numContaLancamentoCCO:Number):void {
			this._numContaLancamentoCCO = numContaLancamentoCCO;
		}

		public function get numContaLancamentoCCO():Number {
			return _numContaLancamentoCCO;
		}

		public function set numSeqLancamentoCCO(numSeqLancamentoCCO:Number):void {
			this._numSeqLancamentoCCO = numSeqLancamentoCCO;
		}

		public function get numSeqLancamentoCCO():Number {
			return _numSeqLancamentoCCO;
		}

		public function set codErroLancamentoCCO(codErroLancamentoCCO:String):void {
			this._codErroLancamentoCCO = codErroLancamentoCCO;
		}

		public function get codErroLancamentoCCO():String {
			return _codErroLancamentoCCO;
		}

		public function set descErroLancamentoCCO(descErroLancamentoCCO:String):void {
			this._descErroLancamentoCCO = descErroLancamentoCCO;
		}

		public function get descErroLancamentoCCO():String {
			return _descErroLancamentoCCO;
		}

		public function set codRetornoLancamentoCCO(codRetornoLancamentoCCO:Number):void {
			this._codRetornoLancamentoCCO = codRetornoLancamentoCCO;
		}

		public function get codRetornoLancamentoCCO():Number {
			return _codRetornoLancamentoCCO;
		}

		public function set descCampoErroLancamentoCCO(descCampoErroLancamentoCCO:String):void {
			this._descCampoErroLancamentoCCO = descCampoErroLancamentoCCO;
		}

		public function get descCampoErroLancamentoCCO():String {
			return _descCampoErroLancamentoCCO;
		}
		
		public function set idInstituicaoTransferenciaDebito(idInstituicaoTransferenciaDebito:Number):void {
			this._idInstituicaoTransferenciaDebito = idInstituicaoTransferenciaDebito;
		}
		
		public function get idInstituicaoTransferenciaDebito():Number {
			return _idInstituicaoTransferenciaDebito;
		}
		
		public function set tipoOperacaoLog(tipoOperacaoLog:TipoOperacaoLogVO):void {
			this._tipoOperacaoLog = tipoOperacaoLog;
		}
		
		public function get tipoOperacaoLog():TipoOperacaoLogVO {
			return _tipoOperacaoLog;
		}

	}
}