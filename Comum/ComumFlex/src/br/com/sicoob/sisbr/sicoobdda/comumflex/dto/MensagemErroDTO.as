package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemErroDTO", MensagemErroDTO);

	public class MensagemErroDTO {

		private var _dataMovimento:IDateTime;
		private var _idMensagem:Number;
		private var _dataHoraCadastro:IDateTime;
		private var _dataHoraMensagem:IDateTime;
		private var _codTipoMensagemDDA:String;
		private var _tipoDoc:String;
		private var _identificador:String;
		private var _codTipoErroCIP:String;
		private var _descTipoErroCIP:String;
		private var _idErroMensagemRetornoCIP:Number;
		private var _descSituacaoMensagemDDA:String;
		
		private var _descNomeArquivoRecebido:String;
		private var _idLogRecebimentoArquivoDDA:Number;
		private var _idLogDetRecebimentoArquivoDDA:Number;
		
		private var _descErroProtocolo:String;
		
		private var _selecionado:Boolean;
		
		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}

		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}
		
		public function set dataHoraMensagem(dataHoraMensagem:IDateTime):void {
			this._dataHoraMensagem = dataHoraMensagem;
		}
		
		public function get dataHoraMensagem():IDateTime {
			return _dataHoraMensagem;
		}

		public function set idMensagem(idMensagem:Number):void {
			this._idMensagem = idMensagem;
		}

		public function get idMensagem():Number {
			return _idMensagem;
		}
		
		public function set dataHoraCadastro(dataHoraCadastro:IDateTime):void {
			this._dataHoraCadastro = dataHoraCadastro;
		}
		
		public function get dataHoraCadastro():IDateTime {
			return _dataHoraCadastro;
		}

		public function set codTipoMensagemDDA(codTipoMensagemDDA:String):void {
			this._codTipoMensagemDDA = codTipoMensagemDDA;
		}

		public function get codTipoMensagemDDA():String {
			return _codTipoMensagemDDA;
		}

		public function set tipoDoc(tipoDoc:String):void {
			this._tipoDoc = tipoDoc;
		}

		public function get tipoDoc():String {
			return _tipoDoc;
		}

		public function set identificador(identificador:String):void {
			this._identificador = identificador;
		}

		public function get identificador():String {
			return _identificador;
		}

		public function set codTipoErroCIP(codTipoErroCIP:String):void {
			this._codTipoErroCIP = codTipoErroCIP;
		}

		public function get codTipoErroCIP():String {
			return _codTipoErroCIP;
		}

		public function set descTipoErroCIP(descTipoErroCIP:String):void {
			this._descTipoErroCIP = descTipoErroCIP;
		}

		public function get descTipoErroCIP():String {
			return _descTipoErroCIP;
		}

		public function set idErroMensagemRetornoCIP(idErroMensagemRetornoCIP:Number):void {
			this._idErroMensagemRetornoCIP = idErroMensagemRetornoCIP;
		}

		public function get idErroMensagemRetornoCIP():Number {
			return _idErroMensagemRetornoCIP;
		}
		
		public function set selecionado(selecionado:Boolean):void {
			this._selecionado = selecionado;
		}
		
		public function get selecionado():Boolean {
			return _selecionado;
		}
		
		public function set descSituacaoMensagemDDA(descSituacaoMensagemDDA:String):void {
			this._descSituacaoMensagemDDA = descSituacaoMensagemDDA;
		}
		
		public function get descSituacaoMensagemDDA():String {
			return _descSituacaoMensagemDDA;
		}
		
		public function set descNomeArquivoRecebido(descNomeArquivoRecebido:String):void {
			this._descNomeArquivoRecebido = descNomeArquivoRecebido;
		}
		
		public function get descNomeArquivoRecebido():String {
			return _descNomeArquivoRecebido;
		}

		public function set idLogRecebimentoArquivoDDA(idLogRecebimentoArquivoDDA:Number):void {
			this._idLogRecebimentoArquivoDDA = idLogRecebimentoArquivoDDA;
		}
		
		public function get idLogRecebimentoArquivoDDA():Number {
			return _idLogRecebimentoArquivoDDA;
		}
		
		public function set idLogDetRecebimentoArquivoDDA(idLogDetRecebimentoArquivoDDA:Number):void {
			this._idLogDetRecebimentoArquivoDDA = idLogDetRecebimentoArquivoDDA;
		}
		
		public function get idLogDetRecebimentoArquivoDDA():Number {
			return _idLogDetRecebimentoArquivoDDA;
		}
		
		public function set descErroProtocolo(descErroProtocolo:String):void {
			this._descErroProtocolo = descErroProtocolo;
		}
		
		public function get descErroProtocolo():String {
			return _descErroProtocolo;
		}
		
	}
}