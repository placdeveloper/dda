package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comum.fachada.dto.MensagemDDABoletoDescontoDTO", MensagemDDABoletoDescontoDTO);

	public class MensagemDDABoletoDescontoDTO {

		private var _idMensagemDDABoletoDesconto:Number;
		private var _idMensagemDDA:Number;
		private var _codTipoDesconto:String;
		private var _dataDesconto:IDateTime;
		private var _valorDesconto:Number;

		public function set idMensagemDDABoletoDesconto(idMensagemDDABoletoDesconto:Number):void {
			this._idMensagemDDABoletoDesconto = idMensagemDDABoletoDesconto;
		}

		public function get idMensagemDDABoletoDesconto():Number {
			return _idMensagemDDABoletoDesconto;
		}

		public function set idMensagemDDA(idMensagemDDA:Number):void {
			this._idMensagemDDA = idMensagemDDA;
		}

		public function get idMensagemDDA():Number {
			return _idMensagemDDA;
		}

		public function set codTipoDesconto(codTipoDesconto:String):void {
			this._codTipoDesconto = codTipoDesconto;
		}

		public function get codTipoDesconto():String {
			return _codTipoDesconto;
		}

		public function set dataDesconto(dataDesconto:IDateTime):void {
			this._dataDesconto = dataDesconto;
		}

		public function get dataDesconto():IDateTime {
			return _dataDesconto;
		}

		public function set valorDesconto(valorDesconto:Number):void {
			this._valorDesconto = valorDesconto;
		}

		public function get valorDesconto():Number {
			return _valorDesconto;
		}

	}
}