package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {
	
	import flash.net.registerClassAlias;
						
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.CentralSingularVO", CentralSingularVO);
	
	public class CentralSingularVO {
		private var _idInstituicao:Number;
		private var _numeroCooperativa:Number;
		
		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set numeroCooperativa(numeroCooperativa:Number):void {
			this._numeroCooperativa = numeroCooperativa;
		}
		
		public function get numeroCooperativa():Number {
			return _numeroCooperativa;
		}
		
		
	}
}

