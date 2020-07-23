package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import mx.collections.ArrayCollection;
	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.RateioDDAVO", RateioDDAVO);

	public class RateioDDAVO {

		private var _id:Number;
		private var _extrato:ExtratoDDAVO;
		private var _descRateio:String;
		private var _situacaoRateio:SituacaoRateioVO;
		private var _dataHoraInclusao:IDateTime;
		private var _dataHoraAprovacao:IDateTime;
		private var _idUsuarioAprovacao:String;
		private var _idInstituicaoUsuarioAprovacao:Number;
		private var _listaEventoConsolidadoDDA:ArrayCollection;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set extrato(extrato:ExtratoDDAVO):void {
			this._extrato = extrato;
		}

		public function get extrato():ExtratoDDAVO {
			return _extrato;
		}

		public function set descRateio(descRateio:String):void {
			this._descRateio = descRateio;
		}

		public function get descRateio():String {
			return _descRateio;
		}

		public function set situacaoRateio(situacaoRateio:SituacaoRateioVO):void {
			this._situacaoRateio = situacaoRateio;
		}

		public function get situacaoRateio():SituacaoRateioVO {
			return _situacaoRateio;
		}

		public function set dataHoraInclusao(dataHoraInclusao:IDateTime):void {
			this._dataHoraInclusao = dataHoraInclusao;
		}

		public function get dataHoraInclusao():IDateTime {
			return _dataHoraInclusao;
		}

		public function set dataHoraAprovacao(dataHoraAprovacao:IDateTime):void {
			this._dataHoraAprovacao = dataHoraAprovacao;
		}

		public function get dataHoraAprovacao():IDateTime {
			return _dataHoraAprovacao;
		}

		public function set idUsuarioAprovacao(idUsuarioAprovacao:String):void {
			this._idUsuarioAprovacao = idUsuarioAprovacao;
		}

		public function get idUsuarioAprovacao():String {
			return _idUsuarioAprovacao;
		}

		public function set idInstituicaoUsuarioAprovacao(idInstituicaoUsuarioAprovacao:Number):void {
			this._idInstituicaoUsuarioAprovacao = idInstituicaoUsuarioAprovacao;
		}

		public function get idInstituicaoUsuarioAprovacao():Number {
			return _idInstituicaoUsuarioAprovacao;
		}

		public function set listaEventoConsolidadoDDA(listaEventoConsolidadoDDA:ArrayCollection):void {
			this._listaEventoConsolidadoDDA = listaEventoConsolidadoDDA;
		}

		public function get listaEventoConsolidadoDDA():ArrayCollection {
			return _listaEventoConsolidadoDDA;
		}

	}
}