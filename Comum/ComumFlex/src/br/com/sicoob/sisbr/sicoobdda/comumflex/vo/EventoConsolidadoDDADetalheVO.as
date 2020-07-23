package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoConsolidadoDDADetalheVO", EventoConsolidadoDDADetalheVO);

	public class EventoConsolidadoDDADetalheVO {

		private var _id:Number;
		private var _eventoConsolidadoDDA:EventoConsolidadoDDAVO;
		private var _qtdMensagemApurado:Number;
		private var _idInstituicao:Number;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set eventoConsolidadoDDA(eventoConsolidadoDDA:EventoConsolidadoDDAVO):void {
			this._eventoConsolidadoDDA = eventoConsolidadoDDA;
		}

		public function get eventoConsolidadoDDA():EventoConsolidadoDDAVO {
			return _eventoConsolidadoDDA;
		}

		public function set qtdMensagemApurado(qtdMensagemApurado:Number):void {
			this._qtdMensagemApurado = qtdMensagemApurado;
		}

		public function get qtdMensagemApurado():Number {
			return _qtdMensagemApurado;
		}

		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}

		public function get idInstituicao():Number {
			return _idInstituicao;
		}

	}
}