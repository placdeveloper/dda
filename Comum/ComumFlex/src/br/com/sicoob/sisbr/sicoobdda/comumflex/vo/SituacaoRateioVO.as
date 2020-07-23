package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoRateioVO", SituacaoRateioVO);

	public class SituacaoRateioVO {

		private var _codSituacaoRateio:Number;
		private var _descSituacaoRateio:String;

		public function set codSituacaoRateio(codSituacaoRateio:Number):void {
			this._codSituacaoRateio = codSituacaoRateio;
		}

		public function get codSituacaoRateio():Number {
			return _codSituacaoRateio;
		}

		public function set descSituacaoRateio(descSituacaoRateio:String):void {
			this._descSituacaoRateio = descSituacaoRateio;
		}

		public function get descSituacaoRateio():String {
			return _descSituacaoRateio;
		}

	}
}