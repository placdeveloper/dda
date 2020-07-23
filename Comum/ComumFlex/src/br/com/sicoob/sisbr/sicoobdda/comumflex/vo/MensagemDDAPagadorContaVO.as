package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAPagadorContaVO", MensagemDDAPagadorContaVO);

	public class MensagemDDAPagadorContaVO {

		private var _id:Number;
		private var _mensagemDDAPagador:MensagemDDAPagadorVO;
		private var _numAgencia:Number;
		private var _numContaCorrente:Number;
		private var _codTipoOperacao:String;
		private var _dataHoraAdesao:IDateTime;

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

		public function set numAgencia(numAgencia:Number):void {
			this._numAgencia = numAgencia;
		}

		public function get numAgencia():Number {
			return _numAgencia;
		}

		public function set numContaCorrente(numContaCorrente:Number):void {
			this._numContaCorrente = numContaCorrente;
		}

		public function get numContaCorrente():Number {
			return _numContaCorrente;
		}

		public function set codTipoOperacao(codTipoOperacao:String):void {
			this._codTipoOperacao = codTipoOperacao;
		}

		public function get codTipoOperacao():String {
			return _codTipoOperacao;
		}

		public function set dataHoraAdesao(dataHoraAdesao:IDateTime):void {
			this._dataHoraAdesao = dataHoraAdesao;
		}

		public function get dataHoraAdesao():IDateTime {
			return _dataHoraAdesao;
		}

	}
}