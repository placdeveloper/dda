package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MeioPagamentoVO", MeioPagamentoVO);

	public class MeioPagamentoVO {

		private var _codMeioPagamento:Number;
		private var _descMeioPagamento:String;

		public function set codMeioPagamento(codMeioPagamento:Number):void {
			this._codMeioPagamento = codMeioPagamento;
		}

		public function get codMeioPagamento():Number {
			return _codMeioPagamento;
		}

		public function set descMeioPagamento(descMeioPagamento:String):void {
			this._descMeioPagamento = descMeioPagamento;
		}

		public function get descMeioPagamento():String {
			return _descMeioPagamento;
		}

	}
}