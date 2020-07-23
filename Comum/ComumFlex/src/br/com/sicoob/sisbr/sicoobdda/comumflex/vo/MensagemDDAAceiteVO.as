package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAAceiteVO", MensagemDDAAceiteVO);

	public class MensagemDDAAceiteVO {

		private var _id:Number;
		private var _idEventoTituloDDA:Number;
		private var _mensagemDDA:MensagemDDAVO;
		private var _bolAceite:Boolean;
		private var _numRefAtualAceite:Number;
		private var _numSeqAtualAceite:Number;
		private var _numIdentificadorBoletoCip:Number;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set idEventoTituloDDA(idEventoTituloDDA:Number):void {
			this._idEventoTituloDDA = idEventoTituloDDA;
		}

		public function get idEventoTituloDDA():Number {
			return _idEventoTituloDDA;
		}

		public function set mensagemDDA(mensagemDDA:MensagemDDAVO):void {
			this._mensagemDDA = mensagemDDA;
		}

		public function get mensagemDDA():MensagemDDAVO {
			return _mensagemDDA;
		}

		public function set bolAceite(bolAceite:Boolean):void {
			this._bolAceite = bolAceite;
		}

		public function get bolAceite():Boolean {
			return _bolAceite;
		}

		public function set numRefAtualAceite(numRefAtualAceite:Number):void {
			this._numRefAtualAceite = numRefAtualAceite;
		}

		public function get numRefAtualAceite():Number {
			return _numRefAtualAceite;
		}

		public function set numSeqAtualAceite(numSeqAtualAceite:Number):void {
			this._numSeqAtualAceite = numSeqAtualAceite;
		}

		public function get numSeqAtualAceite():Number {
			return _numSeqAtualAceite;
		}

		public function set numIdentificadorBoletoCip(numIdentificadorBoletoCip:Number):void {
			this._numIdentificadorBoletoCip = numIdentificadorBoletoCip;
		}

		public function get numIdentificadorBoletoCip():Number {
			return _numIdentificadorBoletoCip;
		}

	}
}