package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoRateioLancamentoVO", SituacaoRateioLancamentoVO);

	public class SituacaoRateioLancamentoVO {

		private var _codSituacaoRateioLancamento:Number;
		private var _descSituacaoRateioLancamento:String;

		public function set codSituacaoRateioLancamento(codSituacaoRateioLancamento:Number):void {
			this._codSituacaoRateioLancamento = codSituacaoRateioLancamento;
		}

		public function get codSituacaoRateioLancamento():Number {
			return _codSituacaoRateioLancamento;
		}

		public function set descSituacaoRateioLancamento(descSituacaoRateioLancamento:String):void {
			this._descSituacaoRateioLancamento = descSituacaoRateioLancamento;
		}

		public function get descSituacaoRateioLancamento():String {
			return _descSituacaoRateioLancamento;
		}

	}
}