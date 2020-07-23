package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoErroCipDTO", TipoErroCipDTO);

	public class TipoErroCipDTO {

		private var _codTipoErro:String;
		private var _descTipoErro:String;
		private var _codTipoTratamentoErroCip:Number;
		private var _descTipoTratamentoErroCip:String;

		private var _qtdErros:Number;
		
		public function set codTipoErro(codTipoErro:String):void {
			this._codTipoErro = codTipoErro;
		}

		public function get codTipoErro():String {
			return _codTipoErro;
		}

		public function set descTipoErro(descTipoErro:String):void {
			this._descTipoErro = descTipoErro;
		}

		public function get descTipoErro():String {
			return _descTipoErro;
		}

		public function set codTipoTratamentoErroCip(codTipoTratamentoErroCip:Number):void {
			this._codTipoTratamentoErroCip = codTipoTratamentoErroCip;
		}

		public function get codTipoTratamentoErroCip():Number {
			return _codTipoTratamentoErroCip;
		}

		public function set descTipoTratamentoErroCip(descTipoTratamentoErroCip:String):void {
			this._descTipoTratamentoErroCip = descTipoTratamentoErroCip;
		}

		public function get descTipoTratamentoErroCip():String {
			return _descTipoTratamentoErroCip;
		}

		public function set qtdErros(qtdErros:Number):void {
			this._qtdErros = qtdErros;
		}
		
		public function get qtdErros():Number {
			return _qtdErros;
		}
	}
}