package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import mx.collections.ArrayCollection;
	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoConsolidadoDDAVO", EventoConsolidadoDDAVO);

	public class EventoConsolidadoDDAVO {

		private var _id:Number;
		private var _eventoTarifavelDDA:EventoTarifavelDDAVO;
		private var _dataMovimento:IDateTime;
		private var _qtdApuradoSicoob:Number;
		private var _qtdApuradoCIP:Number;
		private var _valorTarifaCIP:Number;
		private var _valorTarifaBancoob:Number;
		private var _rateioDDA:RateioDDAVO;
		private var _idUsuarioInclusaoRateio:String;
		private var _idInstituicaoUsuarioInclusaoRateio:Number;
		private var _dataHoraInclusaoRateio:IDateTime;
		private var _listaEventoConsolidadoDDADetalhe:ArrayCollection;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set eventoTarifavelDDA(eventoTarifavelDDA:EventoTarifavelDDAVO):void {
			this._eventoTarifavelDDA = eventoTarifavelDDA;
		}

		public function get eventoTarifavelDDA():EventoTarifavelDDAVO {
			return _eventoTarifavelDDA;
		}

		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}

		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}

		public function set qtdApuradoSicoob(qtdApuradoSicoob:Number):void {
			this._qtdApuradoSicoob = qtdApuradoSicoob;
		}

		public function get qtdApuradoSicoob():Number {
			return _qtdApuradoSicoob;
		}

		public function set qtdApuradoCIP(qtdApuradoCIP:Number):void {
			this._qtdApuradoCIP = qtdApuradoCIP;
		}

		public function get qtdApuradoCIP():Number {
			return _qtdApuradoCIP;
		}

		public function set valorTarifaCIP(valorTarifaCIP:Number):void {
			this._valorTarifaCIP = valorTarifaCIP;
		}

		public function get valorTarifaCIP():Number {
			return _valorTarifaCIP;
		}

		public function set valorTarifaBancoob(valorTarifaBancoob:Number):void {
			this._valorTarifaBancoob = valorTarifaBancoob;
		}

		public function get valorTarifaBancoob():Number {
			return _valorTarifaBancoob;
		}

		public function set rateioDDA(rateioDDA:RateioDDAVO):void {
			this._rateioDDA = rateioDDA;
		}

		public function get rateioDDA():RateioDDAVO {
			return _rateioDDA;
		}

		public function set idUsuarioInclusaoRateio(idUsuarioInclusaoRateio:String):void {
			this._idUsuarioInclusaoRateio = idUsuarioInclusaoRateio;
		}

		public function get idUsuarioInclusaoRateio():String {
			return _idUsuarioInclusaoRateio;
		}

		public function set idInstituicaoUsuarioInclusaoRateio(idInstituicaoUsuarioInclusaoRateio:Number):void {
			this._idInstituicaoUsuarioInclusaoRateio = idInstituicaoUsuarioInclusaoRateio;
		}

		public function get idInstituicaoUsuarioInclusaoRateio():Number {
			return _idInstituicaoUsuarioInclusaoRateio;
		}

		public function set dataHoraInclusaoRateio(dataHoraInclusaoRateio:IDateTime):void {
			this._dataHoraInclusaoRateio = dataHoraInclusaoRateio;
		}

		public function get dataHoraInclusaoRateio():IDateTime {
			return _dataHoraInclusaoRateio;
		}

		public function set listaEventoConsolidadoDDADetalhe(listaEventoConsolidadoDDADetalhe:ArrayCollection):void {
			this._listaEventoConsolidadoDDADetalhe = listaEventoConsolidadoDDADetalhe;
		}

		public function get listaEventoConsolidadoDDADetalhe():ArrayCollection {
			return _listaEventoConsolidadoDDADetalhe;
		}

	}
}