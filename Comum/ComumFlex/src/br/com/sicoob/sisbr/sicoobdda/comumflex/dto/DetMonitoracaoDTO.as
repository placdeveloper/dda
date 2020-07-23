package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.DetMonitoracaoDTO", DetMonitoracaoDTO);

	public class DetMonitoracaoDTO {

		private var _dataMovimento:IDateTime;
		private var _codTipoMensagem:String;
		private var _qtdEnviar:Number;
		private var _qtdSemProtocolo:Number;
		private var _qtdSemRetorno:Number;
		private var _qtdComErro:Number;

		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}

		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}

		public function set codTipoMensagem(codTipoMensagem:String):void {
			this._codTipoMensagem = codTipoMensagem;
		}

		public function get codTipoMensagem():String {
			return _codTipoMensagem;
		}

		public function set qtdEnviar(qtdEnviar:Number):void {
			this._qtdEnviar = qtdEnviar;
		}

		public function get qtdEnviar():Number {
			return _qtdEnviar;
		}

		public function set qtdSemProtocolo(qtdSemProtocolo:Number):void {
			this._qtdSemProtocolo = qtdSemProtocolo;
		}

		public function get qtdSemProtocolo():Number {
			return _qtdSemProtocolo;
		}

		public function set qtdSemRetorno(qtdSemRetorno:Number):void {
			this._qtdSemRetorno = qtdSemRetorno;
		}

		public function get qtdSemRetorno():Number {
			return _qtdSemRetorno;
		}

		public function set qtdComErro(qtdComErro:Number):void {
			this._qtdComErro = qtdComErro;
		}

		public function get qtdComErro():Number {
			return _qtdComErro;
		}

	}
}