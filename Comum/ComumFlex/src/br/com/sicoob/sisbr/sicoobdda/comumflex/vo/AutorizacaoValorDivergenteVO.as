package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.AutorizacaoValorDivergenteVO", AutorizacaoValorDivergenteVO);

	public class AutorizacaoValorDivergenteVO {

		private var _codAutorizacaoValorDivergente:String;
		private var _descAutorizacaoValorDivergente:String;
		private var _bolPermiteAlterarValor:Boolean;

		public function set codAutorizacaoValorDivergente(codAutorizacaoValorDivergente:String):void {
			this._codAutorizacaoValorDivergente = codAutorizacaoValorDivergente;
		}

		public function get codAutorizacaoValorDivergente():String {
			return _codAutorizacaoValorDivergente;
		}

		public function set descAutorizacaoValorDivergente(descAutorizacaoValorDivergente:String):void {
			this._descAutorizacaoValorDivergente = descAutorizacaoValorDivergente;
		}

		public function get descAutorizacaoValorDivergente():String {
			return _descAutorizacaoValorDivergente;
		}

		public function set bolPermiteAlterarValor(bolPermiteAlterarValor:Boolean):void {
			this._bolPermiteAlterarValor = bolPermiteAlterarValor;
		}

		public function get bolPermiteAlterarValor():Boolean {
			return _bolPermiteAlterarValor;
		}

	}
}