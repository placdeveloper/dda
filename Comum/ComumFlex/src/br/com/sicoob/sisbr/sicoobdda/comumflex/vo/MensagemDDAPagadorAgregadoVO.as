package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAPagadorAgregadoVO", MensagemDDAPagadorAgregadoVO);

	public class MensagemDDAPagadorAgregadoVO {

		private var _id:Number;
		private var _mensagemDDAPagador:MensagemDDAPagadorVO;
		private var _numCpfCnpjAgregado:String;
		private var _codTipoPessoaAgregado:String;
		private var _codTipoOperacao:String;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set mensagemDDAPagador(mensagemDDAPagador:MensagemDDAPagadorVO):void {
			this._mensagemDDAPagador = mensagemDDAPagador;
		}

		public function get mensagemDDAPagador():MensagemDDAPagadorVO {
			return _mensagemDDAPagador;
		}

		public function set numCpfCnpjAgregado(numCpfCnpjAgregado:String):void {
			this._numCpfCnpjAgregado = numCpfCnpjAgregado;
		}

		public function get numCpfCnpjAgregado():String {
			return _numCpfCnpjAgregado;
		}

		public function set codTipoPessoaAgregado(codTipoPessoaAgregado:String):void {
			this._codTipoPessoaAgregado = codTipoPessoaAgregado;
		}

		public function get codTipoPessoaAgregado():String {
			return _codTipoPessoaAgregado;
		}

		public function set codTipoOperacao(codTipoOperacao:String):void {
			this._codTipoOperacao = codTipoOperacao;
		}

		public function get codTipoOperacao():String {
			return _codTipoOperacao;
		}

	}
}