package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;

	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.RelatorioDTO", RelatorioDTO);
	
	public class RelatorioDTO {
		
		private var _contextoFlex:String;
		private var _formato:String;
		private var _filtro:Object;
		private var _listaDados:ArrayCollection;
		
		public function RelatorioDTO() {
			
		}
		
		public function get contextoFlex():String {
			return _contextoFlex;
		}
		
		public function set contextoFlex(contextoFlex:String):void {
			this._contextoFlex = contextoFlex;
		}
		
		public function get formato():String {
			return _formato;
		}
		
		public function set formato(formato:String):void {
			this._formato = formato;
		}
		
		public function get filtro():Object {
			return _filtro;
		}
		
		public function set filtro(filtro:Object):void {
			this._filtro = filtro;
		}
		
		public function get listaDados():ArrayCollection {
			return _listaDados;
		}
		
		public function set listaDados(listaDados:ArrayCollection):void {
			this._listaDados = listaDados;
		}
	}
}