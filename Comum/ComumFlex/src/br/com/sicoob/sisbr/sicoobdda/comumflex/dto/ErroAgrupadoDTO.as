package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ErroAgrupadoDTO", ErroAgrupadoDTO);

	public class ErroAgrupadoDTO {

		private var _erroPrincipal:String;
		private var _erroDependente:String;
		private var _qtdErro:Number;

		public function set erroPrincipal(erroPrincipal:String):void {
			this._erroPrincipal = erroPrincipal;
		}

		public function get erroPrincipal():String {
			return _erroPrincipal;
		}

		public function set erroDependente(erroDependente:String):void {
			this._erroDependente = erroDependente;
		}

		public function get erroDependente():String {
			return _erroDependente;
		}

		public function set qtdErro(qtdErro:Number):void {
			this._qtdErro = qtdErro;
		}

		public function get qtdErro():Number {
			return _qtdErro;
		}

	}
}