package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoAjusteVO", TipoAjusteVO);

	public class TipoAjusteVO {

		private var _id:Number;
		private var _descTipoAjuste:String;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set descTipoAjuste(descTipoAjuste:String):void {
			this._descTipoAjuste = descTipoAjuste;
		}

		public function get descTipoAjuste():String {
			return _descTipoAjuste;
		}

	}
}