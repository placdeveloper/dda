package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemPendentePagadorDTO", MensagemPendentePagadorDTO);

	public class MensagemPendentePagadorDTO {

		private var _dataHoraCadastro:IDateTime;
		private var _descTipoMensagem:String;
		private var _descSituacaoMensagem:String;
		private var _numCpfCnpj:String;

		public function set dataHoraCadastro(dataHoraCadastro:IDateTime):void {
			this._dataHoraCadastro = dataHoraCadastro;
		}

		public function get dataHoraCadastro():IDateTime {
			return _dataHoraCadastro;
		}

		public function set descTipoMensagem(descTipoMensagem:String):void {
			this._descTipoMensagem = descTipoMensagem;
		}

		public function get descTipoMensagem():String {
			return _descTipoMensagem;
		}

		public function set descSituacaoMensagem(descSituacaoMensagem:String):void {
			this._descSituacaoMensagem = descSituacaoMensagem;
		}

		public function get descSituacaoMensagem():String {
			return _descSituacaoMensagem;
		}

		public function set numCpfCnpj(numCpfCnpj:String):void {
			this._numCpfCnpj = numCpfCnpj;
		}

		public function get numCpfCnpj():String {
			return _numCpfCnpj;
		}

	}
}