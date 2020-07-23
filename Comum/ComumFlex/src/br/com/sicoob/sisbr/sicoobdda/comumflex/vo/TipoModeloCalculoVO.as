package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoModeloCalculoVO", TipoModeloCalculoVO);

	public class TipoModeloCalculoVO {

		private var _codTipoModeloCalculo:String;
		private var _descTipoModeloCalculo:String;

		public function set codTipoModeloCalculo(codTipoModeloCalculo:String):void {
			this._codTipoModeloCalculo = codTipoModeloCalculo;
		}

		public function get codTipoModeloCalculo():String {
			return _codTipoModeloCalculo;
		}

		public function set descTipoModeloCalculo(descTipoModeloCalculo:String):void {
			this._descTipoModeloCalculo = descTipoModeloCalculo;
		}

		public function get descTipoModeloCalculo():String {
			return _descTipoModeloCalculo;
		}
		
		public function get getLabelTipoModeloCalculo():String {
			return _codTipoModeloCalculo +" - "+_descTipoModeloCalculo;
		}
			

	}
}