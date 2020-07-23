package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoOperacaoLogVO", TipoOperacaoLogVO);

	public class TipoOperacaoLogVO {

		private var _codTipoOperacaoLog:String;
		private var _descTipoOperacaoLog:String;

		public function set codTipoOperacaoLog(codTipoOperacaoLog:String):void {
			this._codTipoOperacaoLog = codTipoOperacaoLog;
		}

		public function get codTipoOperacaoLog():String {
			return _codTipoOperacaoLog;
		}

		public function set descTipoOperacaoLog(descTipoOperacaoLog:String):void {
			this._descTipoOperacaoLog = descTipoOperacaoLog;
		}

		public function get descTipoOperacaoLog():String {
			return _descTipoOperacaoLog;
		}

	}
}