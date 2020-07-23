package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import mx.collections.ArrayCollection;
	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDABeneficiarioVO", MensagemDDABeneficiarioVO);

	public class MensagemDDABeneficiarioVO {

		private var _id:Number;
		private var _mensagemDDA:MensagemDDAVO;
		private var _numCpfCnpjBeneficiario:String;
		private var _codTipoPessoaBeneficiario:String;
		private var _nomeBeneficiario:String;
		private var _nomeFantasiaBeneficiario:String;
		private var _codSituacaoBeneficiario:String;
		private var _dataHoraSituacao:IDateTime;
		private var _codSituacaoRelacionamentoBeneficiario:String;
		private var _dataInicioRelacionamento:IDateTime;
		private var _dataFimRelacionamento:IDateTime;
		private var _numRefAtualCadBeneficiario:Number;
		private var _numSeqAtualCadBeneficiario:Number;
		private var _listaMensagemDDABeneficiarioConvenio:ArrayCollection;
		private var _listaMensagemDDABeneficiarioRepresentante:ArrayCollection;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set mensagemDDA(mensagemDDA:MensagemDDAVO):void {
			this._mensagemDDA = mensagemDDA;
		}

		public function get mensagemDDA():MensagemDDAVO {
			return _mensagemDDA;
		}

		public function set numCpfCnpjBeneficiario(numCpfCnpjBeneficiario:String):void {
			this._numCpfCnpjBeneficiario = numCpfCnpjBeneficiario;
		}

		public function get numCpfCnpjBeneficiario():String {
			return _numCpfCnpjBeneficiario;
		}

		public function set codTipoPessoaBeneficiario(codTipoPessoaBeneficiario:String):void {
			this._codTipoPessoaBeneficiario = codTipoPessoaBeneficiario;
		}

		public function get codTipoPessoaBeneficiario():String {
			return _codTipoPessoaBeneficiario;
		}

		public function set nomeBeneficiario(nomeBeneficiario:String):void {
			this._nomeBeneficiario = nomeBeneficiario;
		}

		public function get nomeBeneficiario():String {
			return _nomeBeneficiario;
		}

		public function set nomeFantasiaBeneficiario(nomeFantasiaBeneficiario:String):void {
			this._nomeFantasiaBeneficiario = nomeFantasiaBeneficiario;
		}

		public function get nomeFantasiaBeneficiario():String {
			return _nomeFantasiaBeneficiario;
		}

		public function set codSituacaoBeneficiario(codSituacaoBeneficiario:String):void {
			this._codSituacaoBeneficiario = codSituacaoBeneficiario;
		}

		public function get codSituacaoBeneficiario():String {
			return _codSituacaoBeneficiario;
		}

		public function set dataHoraSituacao(dataHoraSituacao:IDateTime):void {
			this._dataHoraSituacao = dataHoraSituacao;
		}

		public function get dataHoraSituacao():IDateTime {
			return _dataHoraSituacao;
		}

		public function set codSituacaoRelacionamentoBeneficiario(codSituacaoRelacionamentoBeneficiario:String):void {
			this._codSituacaoRelacionamentoBeneficiario = codSituacaoRelacionamentoBeneficiario;
		}

		public function get codSituacaoRelacionamentoBeneficiario():String {
			return _codSituacaoRelacionamentoBeneficiario;
		}

		public function set dataInicioRelacionamento(dataInicioRelacionamento:IDateTime):void {
			this._dataInicioRelacionamento = dataInicioRelacionamento;
		}

		public function get dataInicioRelacionamento():IDateTime {
			return _dataInicioRelacionamento;
		}

		public function set dataFimRelacionamento(dataFimRelacionamento:IDateTime):void {
			this._dataFimRelacionamento = dataFimRelacionamento;
		}

		public function get dataFimRelacionamento():IDateTime {
			return _dataFimRelacionamento;
		}

		public function set numRefAtualCadBeneficiario(numRefAtualCadBeneficiario:Number):void {
			this._numRefAtualCadBeneficiario = numRefAtualCadBeneficiario;
		}

		public function get numRefAtualCadBeneficiario():Number {
			return _numRefAtualCadBeneficiario;
		}

		public function set numSeqAtualCadBeneficiario(numSeqAtualCadBeneficiario:Number):void {
			this._numSeqAtualCadBeneficiario = numSeqAtualCadBeneficiario;
		}

		public function get numSeqAtualCadBeneficiario():Number {
			return _numSeqAtualCadBeneficiario;
		}

		public function set listaMensagemDDABeneficiarioConvenio(listaMensagemDDABeneficiarioConvenio:ArrayCollection):void {
			this._listaMensagemDDABeneficiarioConvenio = listaMensagemDDABeneficiarioConvenio;
		}

		public function get listaMensagemDDABeneficiarioConvenio():ArrayCollection {
			return _listaMensagemDDABeneficiarioConvenio;
		}

		public function set listaMensagemDDABeneficiarioRepresentante(listaMensagemDDABeneficiarioRepresentante:ArrayCollection):void {
			this._listaMensagemDDABeneficiarioRepresentante = listaMensagemDDABeneficiarioRepresentante;
		}

		public function get listaMensagemDDABeneficiarioRepresentante():ArrayCollection {
			return _listaMensagemDDABeneficiarioRepresentante;
		}

	}
}