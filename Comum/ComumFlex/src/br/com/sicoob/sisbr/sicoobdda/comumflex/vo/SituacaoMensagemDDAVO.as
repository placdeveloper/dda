package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import mx.collections.ArrayCollection;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoMensagemDDAVO", SituacaoMensagemDDAVO);

	public class SituacaoMensagemDDAVO {

		private var _codSituacaoMensagemDDA:Number;
		private var _descSituacaoMensagemDDA:String;
		private var _listaTipoTratamentoErroCip:ArrayCollection;

		public function set codSituacaoMensagemDDA(codSituacaoMensagemDDA:Number):void {
			this._codSituacaoMensagemDDA = codSituacaoMensagemDDA;
		}

		public function get codSituacaoMensagemDDA():Number {
			return _codSituacaoMensagemDDA;
		}

		public function set descSituacaoMensagemDDA(descSituacaoMensagemDDA:String):void {
			this._descSituacaoMensagemDDA = descSituacaoMensagemDDA;
		}

		public function get descSituacaoMensagemDDA():String {
			return _descSituacaoMensagemDDA;
		}

		public function set listaTipoTratamentoErroCip(listaTipoTratamentoErroCip:ArrayCollection):void {
			this._listaTipoTratamentoErroCip = listaTipoTratamentoErroCip;
		}

		public function get listaTipoTratamentoErroCip():ArrayCollection {
			return _listaTipoTratamentoErroCip;
		}

	}
}