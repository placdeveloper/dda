package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.AbrirArquivoRetornoDTO", AbrirArquivoRetornoDTO);

	public class AbrirArquivoRetornoDTO {

		private var _retorno:Boolean;
		private var _nomeArquivo:String;

		public function set retorno(retorno:Boolean):void {
			this._retorno = retorno;
		}

		public function get retorno():Boolean {
			return _retorno;
		}

		public function set nomeArquivo(nomeArquivo:String):void {
			this._nomeArquivo = nomeArquivo;
		}

		public function get nomeArquivo():String {
			return _nomeArquivo;
		}

	}
}