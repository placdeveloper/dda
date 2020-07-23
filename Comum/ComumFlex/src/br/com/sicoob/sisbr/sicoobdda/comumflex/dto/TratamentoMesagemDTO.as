package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TratamentoMesagemDTO", TratamentoMesagemDTO);

	public class TratamentoMesagemDTO {

		private var _listaIdMensagem:ArrayCollection;
		private var _listaIdErroMensagem:ArrayCollection;
		private var _listaIdLogRecebimento:ArrayCollection;
		private var _codTipoTratamento:Number;

		public function set listaIdMensagem(listaIdMensagem:ArrayCollection):void {
			this._listaIdMensagem = listaIdMensagem;
		}

		public function get listaIdMensagem():ArrayCollection {
			return _listaIdMensagem;
		}
		
		public function set listaIdErroMensagem(listaIdErroMensagem:ArrayCollection):void {
			this._listaIdErroMensagem = listaIdErroMensagem;
		}
		
		public function get listaIdErroMensagem():ArrayCollection {
			return _listaIdErroMensagem;
		}

		public function set codTipoTratamento(codTipoTratamento:Number):void {
			this._codTipoTratamento = codTipoTratamento;
		}

		public function get codTipoTratamento():Number {
			return _codTipoTratamento;
		}

		public function set listaIdLogRecebimento(listaIdLogRecebimento:ArrayCollection):void {
			this._listaIdLogRecebimento = listaIdLogRecebimento;
		}
		
		public function get listaIdLogRecebimento():ArrayCollection {
			return _listaIdLogRecebimento;
		}
	}
}