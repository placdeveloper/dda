package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import mx.collections.ArrayCollection;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.DadosTratamentoMensagemDTO", DadosTratamentoMensagemDTO);

	public class DadosTratamentoMensagemDTO {

		private var _listaMensagens:ArrayCollection;
		private var _listaTipoTratamento:ArrayCollection;

		public function set listaMensagens(listaMensagens:ArrayCollection):void {
			this._listaMensagens = listaMensagens;
		}

		public function get listaMensagens():ArrayCollection {
			return _listaMensagens;
		}

		public function set listaTipoTratamento(listaTipoTratamento:ArrayCollection):void {
			this._listaTipoTratamento = listaTipoTratamento;
		}

		public function get listaTipoTratamento():ArrayCollection {
			return _listaTipoTratamento;
		}

	}
}