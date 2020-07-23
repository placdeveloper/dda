package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.BancoCafDTO", BancoCafDTO);

	public class BancoCafDTO {

		private var _numBanco:Number;
		private var _descBanco:String;
		private var _numCodISPB:String;
		private var _bolAtividade:Boolean;

		public function set numBanco(numBanco:Number):void {
			this._numBanco = numBanco;
		}

		public function get numBanco():Number {
			return _numBanco;
		}

		public function set descBanco(descBanco:String):void {
			this._descBanco = descBanco;
		}

		public function get descBanco():String {
			return _descBanco;
		}

		public function set numCodISPB(numCodISPB:String):void {
			this._numCodISPB = numCodISPB;
		}

		public function get numCodISPB():String {
			return _numCodISPB;
		}

		public function set bolAtividade(bolAtividade:Boolean):void {
			this._bolAtividade = bolAtividade;
		}

		public function get bolAtividade():Boolean {
			return _bolAtividade;
		}

	}
}