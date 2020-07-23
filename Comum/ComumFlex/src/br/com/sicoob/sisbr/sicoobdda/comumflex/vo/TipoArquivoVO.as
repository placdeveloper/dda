package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoArquivoVO", TipoArquivoVO);

	public class TipoArquivoVO {

		private var _codTipoArquivo:String;
		private var _descTipoArquivo:String;

		public function set codTipoArquivo(codTipoArquivo:String):void {
			this._codTipoArquivo = codTipoArquivo;
		}

		public function get codTipoArquivo():String {
			return _codTipoArquivo;
		}

		public function set descTipoArquivo(descTipoArquivo:String):void {
			this._descTipoArquivo = descTipoArquivo;
		}

		public function get descTipoArquivo():String {
			return _descTipoArquivo;
		}

	}
}