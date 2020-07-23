package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoServicoTarifavelVO", TipoServicoTarifavelVO);

	public class TipoServicoTarifavelVO {

		private var _codTipoServicoTarifavel:Number;
		private var _descServicoTarifavel:String;

		public function set codTipoServicoTarifavel(codTipoServicoTarifavel:Number):void {
			this._codTipoServicoTarifavel = codTipoServicoTarifavel;
		}

		public function get codTipoServicoTarifavel():Number {
			return _codTipoServicoTarifavel;
		}

		public function set descServicoTarifavel(descServicoTarifavel:String):void {
			this._descServicoTarifavel = descServicoTarifavel;
		}

		public function get descServicoTarifavel():String {
			return _descServicoTarifavel;
		}

	}
}