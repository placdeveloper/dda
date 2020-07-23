package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.CarteiraDeParaDTO", CarteiraDeParaDTO);

	public class CarteiraDeParaDTO {

		private var _descricaoCarteira:String;
		private var _idCarteiraCip:Number;

		public function set descricaoCarteira(descricaoCarteira:String):void {
			this._descricaoCarteira = descricaoCarteira;
		}

		public function get descricaoCarteira():String {
			return _descricaoCarteira;
		}

		public function set idCarteiraCip(idCarteiraCip:Number):void {
			this._idCarteiraCip = idCarteiraCip;
		}

		public function get idCarteiraCip():Number {
			return _idCarteiraCip;
		}

	}
}