package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogDetalheEnvioArquivoDDAVO", LogDetalheEnvioArquivoDDAVO);

	public class LogDetalheEnvioArquivoDDAVO {

		private var _id:Number;
		private var _logEnvioArquivoDDA:LogEnvioArquivoDDAVO;
		private var _mensagemDDA:MensagemDDAVO;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set logEnvioArquivoDDA(logEnvioArquivoDDA:LogEnvioArquivoDDAVO):void {
			this._logEnvioArquivoDDA = logEnvioArquivoDDA;
		}

		public function get logEnvioArquivoDDA():LogEnvioArquivoDDAVO {
			return _logEnvioArquivoDDA;
		}

		public function set mensagemDDA(mensagemDDA:MensagemDDAVO):void {
			this._mensagemDDA = mensagemDDA;
		}

		public function get mensagemDDA():MensagemDDAVO {
			return _mensagemDDA;
		}

	}
}