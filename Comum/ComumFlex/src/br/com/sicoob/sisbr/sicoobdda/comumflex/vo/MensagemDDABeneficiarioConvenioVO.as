package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDABeneficiarioConvenioVO", MensagemDDABeneficiarioConvenioVO);

	public class MensagemDDABeneficiarioConvenioVO {

		private var _id:Number;
		private var _mensagemDDABeneficiario:MensagemDDABeneficiarioVO;
		private var _numAgencia:Number;
		private var _numConta:Number;
		private var _numClienteOuConvenio:String;
		private var _codTipoProdutoDDA:String;
		private var _codSituacaoConvenioDDA:String;
		private var _dataInicioRelacionamento:IDateTime;
		private var _dataFimRelacionamento:IDateTime;
		private var _codISPBParticipanteIncorporado:String;
		private var _codTipoOperacao:String;

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

		public function set numAgencia(numAgencia:Number):void {
			this._numAgencia = numAgencia;
		}

		public function get numAgencia():Number {
			return _numAgencia;
		}

		public function set numConta(numConta:Number):void {
			this._numConta = numConta;
		}

		public function get numConta():Number {
			return _numConta;
		}

		public function set numClienteOuConvenio(numClienteOuConvenio:String):void {
			this._numClienteOuConvenio = numClienteOuConvenio;
		}

		public function get numClienteOuConvenio():String {
			return _numClienteOuConvenio;
		}

		public function set codTipoProdutoDDA(codTipoProdutoDDA:String):void {
			this._codTipoProdutoDDA = codTipoProdutoDDA;
		}

		public function get codTipoProdutoDDA():String {
			return _codTipoProdutoDDA;
		}

		public function set codSituacaoConvenioDDA(codSituacaoConvenioDDA:String):void {
			this._codSituacaoConvenioDDA = codSituacaoConvenioDDA;
		}

		public function get codSituacaoConvenioDDA():String {
			return _codSituacaoConvenioDDA;
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

		public function set codISPBParticipanteIncorporado(codISPBParticipanteIncorporado:String):void {
			this._codISPBParticipanteIncorporado = codISPBParticipanteIncorporado;
		}

		public function get codISPBParticipanteIncorporado():String {
			return _codISPBParticipanteIncorporado;
		}

		public function set codTipoOperacao(codTipoOperacao:String):void {
			this._codTipoOperacao = codTipoOperacao;
		}

		public function get codTipoOperacao():String {
			return _codTipoOperacao;
		}

	}
}