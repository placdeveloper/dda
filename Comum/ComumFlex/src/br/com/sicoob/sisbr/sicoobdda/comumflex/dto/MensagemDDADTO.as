package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDADTO", MensagemDDADTO);
	
	public class MensagemDDADTO
	{
		
		private var _idMensagem:Number;
		private var _codTipoMensagem:String;
		private var _numOperacao:String;
		private var _bolOrigemSicoob:Boolean;
		private var _dataHoraMensagem:IDateTime;
		private var _dataHoraMensagemInicial:IDateTime;
		private var _dataHoraMensagemFinal:IDateTime;
		private var _dataHoraProtocolo:IDateTime;
		private var _dataHoraCadastroInicial:IDateTime;
		private var _dataHoraCadastroFinal:IDateTime;
		private var _statusEnvio:Number;
		private var _selecionado:Boolean;
		
		private var _descTipoTratamentoErroCip:String;
		
		private var _movimentoDataInicial:IDateTime;
		private var _movimentoDataFinal:IDateTime;
		
		
		public function MensagemDDADTO()
		{
		}
		
		
		public function get idMensagem():Number {
			return _idMensagem;
		}
		
		public function set idMensagem(idMensagem:Number):void {
			this._idMensagem = idMensagem;
		}
		
		public function get codTipoMensagem():String {
			return _codTipoMensagem;
		}
		
		public function set codTipoMensagem(codTipoMensagem:String):void {
			this._codTipoMensagem = codTipoMensagem;
		}
		
		public function get numOperacao():String {
			return _numOperacao;
		}
		
		public function set numOperacao(numOperacao:String):void {
			this._numOperacao = numOperacao;
		}
		
		public function get bolOrigemSicoob():Boolean {
			return _bolOrigemSicoob;
		}
		
		public function set bolOrigemSicoob(bolOrigemSicoob:Boolean):void {
			this._bolOrigemSicoob = bolOrigemSicoob;
		}

		public function get dataHoraMensagem():IDateTime {
			return _dataHoraMensagem;
		}
		
		public function set dataHoraMensagem(dataHoraMensagem:IDateTime):void {
			this._dataHoraMensagem = dataHoraMensagem;
		}
		public function get dataHoraMensagemInicial():IDateTime {
			return _dataHoraMensagemInicial;
		}
		
		public function set dataHoraMensagemInicial(dataHoraMensagemInicial:IDateTime):void {
			this._dataHoraMensagemInicial = dataHoraMensagemInicial;
		}

		public function get dataHoraMensagemFinal():IDateTime {
			return _dataHoraMensagemFinal;
		}
		
		public function set dataHoraMensagemFinal(dataHoraMensagemFinal:IDateTime):void {
			this._dataHoraMensagemFinal = dataHoraMensagemFinal;
		}

		public function get dataHoraCadastroInicial():IDateTime {
			return _dataHoraCadastroInicial;
		}
		
		public function set dataHoraCadastroInicial(dataHoraCadastroInicial:IDateTime):void {
			this._dataHoraCadastroInicial = dataHoraCadastroInicial;
		}

		public function get dataHoraCadastroFinal():IDateTime {
			return _dataHoraCadastroFinal;
		}
		
		public function set dataHoraCadastroFinal(dataHoraCadastroFinal:IDateTime):void {
			this._dataHoraCadastroFinal = dataHoraCadastroFinal;
		}
		
		public function get dataHoraProtocolo():IDateTime {
			return _dataHoraProtocolo;
		}
		
		public function set dataHoraProtocolo(dataHoraProtocolo:IDateTime):void {
			this._dataHoraProtocolo = dataHoraProtocolo;
		}
		
		public function get statusEnvio():Number {
			return _statusEnvio;
		}
		
		public function set statusEnvio(statusEnvio:Number):void {
			this._statusEnvio = statusEnvio;
		}
		
		public function get selecionado():Boolean {
			return _selecionado;
		}
		
		public function set selecionado(selecionado:Boolean):void {
			this._selecionado = selecionado;
		}
		
		public function get descTipoTratamentoErroCip():String {
			return _descTipoTratamentoErroCip;
		}
		
		public function set descTipoTratamentoErroCip(descTipoTratamentoErroCip:String):void {
			this._descTipoTratamentoErroCip = descTipoTratamentoErroCip;
		}
		
		public function get movimentoDataInicial():IDateTime {
			return _movimentoDataInicial;
		}
		
		public function set movimentoDataInicial(movimentoDataInicial:IDateTime):void {
			this._movimentoDataInicial = movimentoDataInicial;
		}
		
		public function get movimentoDataFinal():IDateTime {
			return _movimentoDataFinal;
		}
		
		public function set movimentoDataFinal(movimentoDataFinal:IDateTime):void {
			this._movimentoDataFinal = movimentoDataFinal;
		}
	}
}