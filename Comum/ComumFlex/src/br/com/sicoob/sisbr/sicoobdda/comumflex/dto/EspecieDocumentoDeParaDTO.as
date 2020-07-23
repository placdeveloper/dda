package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.EspecieDocumentoDeParaDTO", EspecieDocumentoDeParaDTO);

	public class EspecieDocumentoDeParaDTO {

		private var _siglaEspecieDocumento:String;
		private var _idEspecieDocumentoCip:Number;
		private var _descEspecieDocumento:String;

		public function set siglaEspecieDocumento(siglaEspecieDocumento:String):void {
			this._siglaEspecieDocumento = siglaEspecieDocumento;
		}

		public function get siglaEspecieDocumento():String {
			return _siglaEspecieDocumento;
		}

		public function set idEspecieDocumentoCip(idEspecieDocumentoCip:Number):void {
			this._idEspecieDocumentoCip = idEspecieDocumentoCip;
		}

		public function get idEspecieDocumentoCip():Number {
			return _idEspecieDocumentoCip;
		}
		
		public function set descEspecieDocumento(descEspecieDocumento:String):void {
			this._descEspecieDocumento = descEspecieDocumento;
		}
		
		public function get descEspecieDocumento():String {
			return _descEspecieDocumento;
		}

	}
}