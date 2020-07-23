package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.CadastroSerie0200DTO", CadastroSerie0200DTO);

	public class CadastroSerie0200DTO {

		private var _dataInicioApuracao:IDateTime;
		private var _dataFimApuracao:IDateTime;
		private var _numIdentLancamento:Number;
		private var _codTipoTransacao:String;
		private var _codTipoConsulta:String;
		private var _codTipoRetorno:String;
		private var _dataHoraRef:IDateTime;
		private var _tipoMensagemArquivo:String;
		private var _codTipoMensagem:String;
		private var _numOpMensagem:String;
		private var _identArquivoc:String;

		public function set dataInicioApuracao(dataInicioApuracao:IDateTime):void {
			this._dataInicioApuracao = dataInicioApuracao;
		}

		public function get dataInicioApuracao():IDateTime {
			return _dataInicioApuracao;
		}

		public function set dataFimApuracao(dataFimApuracao:IDateTime):void {
			this._dataFimApuracao = dataFimApuracao;
		}

		public function get dataFimApuracao():IDateTime {
			return _dataFimApuracao;
		}

		public function set numIdentLancamento(numIdentLancamento:Number):void {
			this._numIdentLancamento = numIdentLancamento;
		}

		public function get numIdentLancamento():Number {
			return _numIdentLancamento;
		}

		public function set codTipoTransacao(codTipoTransacao:String):void {
			this._codTipoTransacao = codTipoTransacao;
		}

		public function get codTipoTransacao():String {
			return _codTipoTransacao;
		}

		public function set codTipoConsulta(codTipoConsulta:String):void {
			this._codTipoConsulta = codTipoConsulta;
		}

		public function get codTipoConsulta():String {
			return _codTipoConsulta;
		}

		public function set codTipoRetorno(codTipoRetorno:String):void {
			this._codTipoRetorno = codTipoRetorno;
		}

		public function get codTipoRetorno():String {
			return _codTipoRetorno;
		}

		public function set dataHoraRef(dataHoraRef:IDateTime):void {
			this._dataHoraRef = dataHoraRef;
		}

		public function get dataHoraRef():IDateTime {
			return _dataHoraRef;
		}

		public function set tipoMensagemArquivo(tipoMensagemArquivo:String):void {
			this._tipoMensagemArquivo = tipoMensagemArquivo;
		}

		public function get tipoMensagemArquivo():String {
			return _tipoMensagemArquivo;
		}

		public function set codTipoMensagem(codTipoMensagem:String):void {
			this._codTipoMensagem = codTipoMensagem;
		}

		public function get codTipoMensagem():String {
			return _codTipoMensagem;
		}

		public function set numOpMensagem(numOpMensagem:String):void {
			this._numOpMensagem = numOpMensagem;
		}

		public function get numOpMensagem():String {
			return _numOpMensagem;
		}

		public function set identArquivoc(identArquivoc:String):void {
			this._identArquivoc = identArquivoc;
		}

		public function get identArquivoc():String {
			return _identArquivoc;
		}

	}
}