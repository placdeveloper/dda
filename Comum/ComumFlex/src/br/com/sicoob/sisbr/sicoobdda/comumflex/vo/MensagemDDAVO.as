package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAVO", MensagemDDAVO);

	public class MensagemDDAVO {
		
		private var _id:Number;
		
		private var _mensagemOrigem:MensagemDDAVO;
		
		private var _tipoMensagemDDA:TipoMensagemDDAVO;

		private var _dataHoraMovimento:IDateTime;
		
		private var _dataHoraMensagem:IDateTime;
		
		private var _qtdTotalRegistrosRecebidos:Number;
		
		private var _dataHoraProtocolo:IDateTime;
		
		private var _numOperacao:String;
		
		private var _descErroProtocolo:String;
		
		private var _xmlMensagem:String;
		
		private var _bolOrigemSicoob:Boolean;
		
		private var _selecionado:Boolean;
		
		public function set id(id:Number):void {
			this._id = id;
		}
		
		public function get id():Number {
			return _id;
		}
		
		public function set mensagemOrigem(mensagemOrigem:MensagemDDAVO):void {
			this._mensagemOrigem = mensagemOrigem;
		}
		
		public function get mensagemOrigem():MensagemDDAVO {
			return _mensagemOrigem;
		}

		public function set tipoMensagemDDA(tipoMensagemDDA:TipoMensagemDDAVO):void {
			this._tipoMensagemDDA = tipoMensagemDDA;
		}

		public function get tipoMensagemDDA():TipoMensagemDDAVO {
			return _tipoMensagemDDA;
		}

		public function set dataHoraMovimento(dataHoraMovimento:IDateTime):void {
			this._dataHoraMovimento = dataHoraMovimento;
		}

		public function get dataHoraMovimento():IDateTime {
			return _dataHoraMovimento;
		}
		
		public function set dataHoraMensagem(dataHoraMensagem:IDateTime):void {
			this._dataHoraMensagem = dataHoraMensagem;
		}
		
		public function get dataHoraMensagem():IDateTime {
			return _dataHoraMensagem;
		}
		
		public function set qtdTotalRegistrosRecebidos(qtdTotalRegistrosRecebidos:Number):void {
			this._qtdTotalRegistrosRecebidos = qtdTotalRegistrosRecebidos;
		}
		
		public function get qtdTotalRegistrosRecebidos():Number {
			return _qtdTotalRegistrosRecebidos;
		}
		

		public function set dataHoraProtocolo(dataHoraProtocolo:IDateTime):void {
			this._dataHoraProtocolo = dataHoraProtocolo;
		}
		
		public function get dataHoraProtocolo():IDateTime {
			return _dataHoraProtocolo;
		}
		
		public function set numOperacao(numOperacao:String):void {
			this._numOperacao = numOperacao;
		}
		
		public function get numOperacao():String {
			return _numOperacao;
		}
		
		public function set descErroProtocolo(descErroProtocolo:String):void {
			this._descErroProtocolo = descErroProtocolo;
		}
		
		public function get descErroProtocolo():String {
			return _descErroProtocolo;
		}
		
		public function set xmlMensagem(xmlMensagem:String):void {
			this._xmlMensagem = xmlMensagem;
		}
		
		public function get xmlMensagem():String {
			return _xmlMensagem;
		}
		
		public function set bolOrigemSicoob(bolOrigemSicoob:Boolean):void {
			this._bolOrigemSicoob = bolOrigemSicoob;
		}
		
		public function get bolOrigemSicoob():Boolean {
			return _bolOrigemSicoob;
		}
		
		public function set selecionado(selecionado:Boolean):void {
			this._selecionado = selecionado;
		}
		
		public function get selecionado():Boolean {
			return _selecionado;
		}
		
	}
}