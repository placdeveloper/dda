package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import mx.collections.ArrayCollection;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoTratamentoErroCipVO", TipoTratamentoErroCipVO);

	public class TipoTratamentoErroCipVO {

		private var _codTipoTratamentoErroCip:Number;
		private var _descTipoTratamentoErroCip:String;
		private var _listaSituacaoMensagemDDA:ArrayCollection;

		public function set codTipoTratamentoErroCip(codTipoTratamentoErroCip:Number):void {
			this._codTipoTratamentoErroCip = codTipoTratamentoErroCip;
		}

		public function get codTipoTratamentoErroCip():Number {
			return _codTipoTratamentoErroCip;
		}

		public function set descTipoTratamentoErroCip(descTipoTratamentoErroCip:String):void {
			this._descTipoTratamentoErroCip = descTipoTratamentoErroCip;
		}

		public function get descTipoTratamentoErroCip():String {
			return _descTipoTratamentoErroCip;
		}

		public function set listaSituacaoMensagemDDA(listaSituacaoMensagemDDA:ArrayCollection):void {
			this._listaSituacaoMensagemDDA = listaSituacaoMensagemDDA;
		}

		public function get listaSituacaoMensagemDDA():ArrayCollection {
			return _listaSituacaoMensagemDDA;
		}

	}
}