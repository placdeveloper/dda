package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoArquivoVO", SituacaoArquivoVO);

	public class SituacaoArquivoVO {

		private var _codSituacaoArquivo:String;
		private var _descSituacaoArquivo:String;

		public function set codSituacaoArquivo(codSituacaoArquivo:String):void {
			this._codSituacaoArquivo = codSituacaoArquivo;
		}

		public function get codSituacaoArquivo():String {
			return _codSituacaoArquivo;
		}

		public function set descSituacaoArquivo(descSituacaoArquivo:String):void {
			this._descSituacaoArquivo = descSituacaoArquivo;
		}

		public function get descSituacaoArquivo():String {
			return _descSituacaoArquivo;
		}

	}
}