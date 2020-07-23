package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.CategoriaMensagemDDAVO", CategoriaMensagemDDAVO);

	public class CategoriaMensagemDDAVO {

		private var _codCategoriaMensagemDda:String;
		private var _descCategoriaMensagemDda:String;
		
		public function set codCategoriaMensagemDda(codCategoriaMensagemDda:String):void {
			this._codCategoriaMensagemDda = codCategoriaMensagemDda;
		}

		public function get codCategoriaMensagemDda():String {
			return _codCategoriaMensagemDda;
		}
		
		public function set descCategoriaMensagemDda(descCategoriaMensagemDda:String):void {
			this._descCategoriaMensagemDda = descCategoriaMensagemDda;
		}
		
		public function get descCategoriaMensagemDda():String {
			return _descCategoriaMensagemDda;
		}
	}
}