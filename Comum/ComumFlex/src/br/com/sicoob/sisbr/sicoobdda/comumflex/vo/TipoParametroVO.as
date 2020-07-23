package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {
	
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoParametroVO", TipoParametroVO);
	
	public class TipoParametroVO {
		
		private var _id:Number;
		private var _descTipoParametro:String;
		
		public function set id(id:Number):void {
			this._id = id;
		}
		
		public function get id():Number {
			return _id;
		}
		
		public function set descTipoParametro(descTipoParametro:String):void {
			this._descTipoParametro = descTipoParametro;
		}
		
		public function get descTipoParametro():String {
			return _descTipoParametro;
		}
		
	}
}