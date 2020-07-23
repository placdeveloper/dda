package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoEventoTarifavelVO", TipoEventoTarifavelVO);

	public class TipoEventoTarifavelVO {

		private var _codTipoEventoTarifavel:Number;
		private var _descTipoEventoTarifavel:String;

		public function set codTipoEventoTarifavel(codTipoEventoTarifavel:Number):void {
			this._codTipoEventoTarifavel = codTipoEventoTarifavel;
		}

		public function get codTipoEventoTarifavel():Number {
			return _codTipoEventoTarifavel;
		}

		public function set descTipoEventoTarifavel(descTipoEventoTarifavel:String):void {
			this._descTipoEventoTarifavel = descTipoEventoTarifavel;
		}

		public function get descTipoEventoTarifavel():String {
			return _descTipoEventoTarifavel;
		}

	}
}