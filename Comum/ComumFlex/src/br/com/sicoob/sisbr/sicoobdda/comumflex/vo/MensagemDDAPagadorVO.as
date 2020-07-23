package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAPagadorVO", MensagemDDAPagadorVO);

	public class MensagemDDAPagadorVO {

		private var _id:Number;
		private var _idEventoDDA:Number;
		private var _numIdentificaPagadorCip:Number;
		private var _mensagemDDA:MensagemDDAVO;
		private var _numCpfCnpjPagador:String;
		private var _codTipoPessoaPagador:String;
		private var _bolPagadorEletronico:Boolean;
		private var _numRefAtualCadPagador:Number;
		private var _numSeqAtualCadPagador:Number;
		private var _listaMensagemDDAPagadorAgregado:ArrayCollection;
		private var _listaMensagemDDAPagadorConta:ArrayCollection;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set idEventoDDA(idEventoDDA:Number):void {
			this._idEventoDDA = idEventoDDA;
		}

		public function get idEventoDDA():Number {
			return _idEventoDDA;
		}

		public function set numIdentificaPagadorCip(numIdentificaPagadorCip:Number):void {
			this._numIdentificaPagadorCip = numIdentificaPagadorCip;
		}

		public function get numIdentificaPagadorCip():Number {
			return _numIdentificaPagadorCip;
		}

		public function set mensagemDDA(mensagemDDA:MensagemDDAVO):void {
			this._mensagemDDA = mensagemDDA;
		}

		public function get mensagemDDA():MensagemDDAVO {
			return _mensagemDDA;
		}

		public function set numCpfCnpjPagador(numCpfCnpjPagador:String):void {
			this._numCpfCnpjPagador = numCpfCnpjPagador;
		}

		public function get numCpfCnpjPagador():String {
			return _numCpfCnpjPagador;
		}

		public function set codTipoPessoaPagador(codTipoPessoaPagador:String):void {
			this._codTipoPessoaPagador = codTipoPessoaPagador;
		}

		public function get codTipoPessoaPagador():String {
			return _codTipoPessoaPagador;
		}

		public function set bolPagadorEletronico(bolPagadorEletronico:Boolean):void {
			this._bolPagadorEletronico = bolPagadorEletronico;
		}

		public function get bolPagadorEletronico():Boolean {
			return _bolPagadorEletronico;
		}

		public function set numRefAtualCadPagador(numRefAtualCadPagador:Number):void {
			this._numRefAtualCadPagador = numRefAtualCadPagador;
		}

		public function get numRefAtualCadPagador():Number {
			return _numRefAtualCadPagador;
		}

		public function set numSeqAtualCadPagador(numSeqAtualCadPagador:Number):void {
			this._numSeqAtualCadPagador = numSeqAtualCadPagador;
		}

		public function get numSeqAtualCadPagador():Number {
			return _numSeqAtualCadPagador;
		}

		public function set listaMensagemDDAPagadorAgregado(listaMensagemDDAPagadorAgregado:ArrayCollection):void {
			this._listaMensagemDDAPagadorAgregado = listaMensagemDDAPagadorAgregado;
		}

		public function get listaMensagemDDAPagadorAgregado():ArrayCollection {
			return _listaMensagemDDAPagadorAgregado;
		}

		public function set listaMensagemDDAPagadorConta(listaMensagemDDAPagadorConta:ArrayCollection):void {
			this._listaMensagemDDAPagadorConta = listaMensagemDDAPagadorConta;
		}

		public function get listaMensagemDDAPagadorConta():ArrayCollection {
			return _listaMensagemDDAPagadorConta;
		}

	}
}