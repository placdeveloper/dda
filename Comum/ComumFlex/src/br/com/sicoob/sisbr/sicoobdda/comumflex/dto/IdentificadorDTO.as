package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.IdentificadorDTO", IdentificadorDTO);

	public class IdentificadorDTO {

		private var _identificadorGeral:Number;

		public function set identificadorGeral(identificadorGeral:Number):void {
			this._identificadorGeral = identificadorGeral;
		}

		public function get identificadorGeral():Number {
			return _identificadorGeral;
		}

	}
}