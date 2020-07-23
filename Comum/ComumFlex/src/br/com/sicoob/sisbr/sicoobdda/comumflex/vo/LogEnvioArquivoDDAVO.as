package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogEnvioArquivoDDAVO", LogEnvioArquivoDDAVO);

	public class LogEnvioArquivoDDAVO {

		private var _id:Number;
		private var _descNomeArquivoEnviado:String;
		private var _dataHoraArquivo:IDateTime;
		private var _dataMovimento:IDateTime;
		private var _dataHoraEnvio:IDateTime;
		private var _tipoMensagem:TipoMensagemDDAVO;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set descNomeArquivoEnviado(descNomeArquivoEnviado:String):void {
			this._descNomeArquivoEnviado = descNomeArquivoEnviado;
		}

		public function get descNomeArquivoEnviado():String {
			return _descNomeArquivoEnviado;
		}

		public function set dataHoraArquivo(dataHoraArquivo:IDateTime):void {
			this._dataHoraArquivo = dataHoraArquivo;
		}

		public function get dataHoraArquivo():IDateTime {
			return _dataHoraArquivo;
		}

		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}

		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}

		public function set dataHoraEnvio(dataHoraEnvio:IDateTime):void {
			this._dataHoraEnvio = dataHoraEnvio;
		}

		public function get dataHoraEnvio():IDateTime {
			return _dataHoraEnvio;
		}

		public function set tipoMensagem(tipoMensagem:TipoMensagemDDAVO):void {
			this._tipoMensagem = tipoMensagem;
		}

		public function get tipoMensagem():TipoMensagemDDAVO {
			return _tipoMensagem;
		}

	}
}