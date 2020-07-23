package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDABeneficiarioRepresentanteVO", MensagemDDABeneficiarioRepresentanteVO);

	public class MensagemDDABeneficiarioRepresentanteVO {

		private var _id:Number;
		private var _mensagemDDABeneficiario:MensagemDDABeneficiarioVO;
		private var _numCpfCnpjRepresentante:String;
		private var _codTipoPessoaRepresentante:String;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set mensagemDDABeneficiario(mensagemDDABeneficiario:MensagemDDABeneficiarioVO):void {
			this._mensagemDDABeneficiario = mensagemDDABeneficiario;
		}

		public function get mensagemDDABeneficiario():MensagemDDABeneficiarioVO {
			return _mensagemDDABeneficiario;
		}

		public function set numCpfCnpjRepresentante(numCpfCnpjRepresentante:String):void {
			this._numCpfCnpjRepresentante = numCpfCnpjRepresentante;
		}

		public function get numCpfCnpjRepresentante():String {
			return _numCpfCnpjRepresentante;
		}

		public function set codTipoPessoaRepresentante(codTipoPessoaRepresentante:String):void {
			this._codTipoPessoaRepresentante = codTipoPessoaRepresentante;
		}

		public function get codTipoPessoaRepresentante():String {
			return _codTipoPessoaRepresentante;
		}

	}
}