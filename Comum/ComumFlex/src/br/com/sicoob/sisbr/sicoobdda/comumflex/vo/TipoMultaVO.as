package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoMultaVO", TipoMultaVO);

	public class TipoMultaVO {
		public  static const VALOR_FIXO:Number = 1;
		public  static const PERCENTUAL:Number = 2;
		public  static const ISENTO:Number = 3;
		
		private var _id:Number;
		private var _descTipoMulta:String;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set descTipoMulta(descTipoMulta:String):void {
			this._descTipoMulta = descTipoMulta;
		}

		public function get descTipoMulta():String {
			return _descTipoMulta;
		}

	}
}