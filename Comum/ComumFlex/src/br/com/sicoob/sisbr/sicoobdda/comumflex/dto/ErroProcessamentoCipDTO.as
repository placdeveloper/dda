package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ErroProcessamentoCipDTO", ErroProcessamentoCipDTO);

	public class ErroProcessamentoCipDTO {

		private var _tipoErro:String;
		private var _dataMovimento:IDateTime;
		private var _qtdErroProcessamento:Number;

		public function set tipoErro(tipoErro:String):void {
			this._tipoErro = tipoErro;
		}

		public function get tipoErro():String {
			return _tipoErro;
		}

		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}
		
		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}

		
		public function set qtdErroProcessamento(qtdErroProcessamento:Number):void {
			this._qtdErroProcessamento = qtdErroProcessamento;
		}

		public function get qtdErroProcessamento():Number {
			return _qtdErroProcessamento;
		}

	}
}