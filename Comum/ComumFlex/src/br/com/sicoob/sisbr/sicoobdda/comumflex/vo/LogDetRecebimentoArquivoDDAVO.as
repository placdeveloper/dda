package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogDetRecebimentoArquivoDDAVO", LogDetRecebimentoArquivoDDAVO);
	
	public class LogDetRecebimentoArquivoDDAVO {

		private var _id:Number;
		private var _logRecebimentoArquivoDDA:LogRecebimentoArquivoDDAVO;
		private var _descXMLMensagemRecebida:String;
		private var _bolProcessado:Boolean;
		private var _descErro:String;
		private var _numOrdemProcessamento:Number;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set logRecebimentoArquivoDDA(logRecebimentoArquivoDDA:LogRecebimentoArquivoDDAVO):void {
			this._logRecebimentoArquivoDDA = logRecebimentoArquivoDDA;
		}

		public function get logRecebimentoArquivoDDA():LogRecebimentoArquivoDDAVO {
			return _logRecebimentoArquivoDDA;
		}

		public function set descXMLMensagemRecebida(descXMLMensagemRecebida:String):void {
			this._descXMLMensagemRecebida = descXMLMensagemRecebida;
		}

		public function get descXMLMensagemRecebida():String {
			return _descXMLMensagemRecebida;
		}

		public function set bolProcessado(bolProcessado:Boolean):void {
			this._bolProcessado = bolProcessado;
		}

		public function get bolProcessado():Boolean {
			return _bolProcessado;
		}

		public function set descErro(descErro:String):void {
			this._descErro = descErro;
		}

		public function get descErro():String {
			return _descErro;
		}
		
		public function set numOrdemProcessamento(numOrdemProcessamento:Number):void {
			this._numOrdemProcessamento = numOrdemProcessamento;
		}
		
		public function get numOrdemProcessamento():Number {
			return _numOrdemProcessamento;
		}

	}
}