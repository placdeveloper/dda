package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoProcessamentoArquivoVO", SituacaoProcessamentoArquivoVO);
	public class SituacaoProcessamentoArquivoVO {

		
		private var _codSituacaoProcessamentoArquivo:Number;
		private var _descSituacaoProcessamentoArquivo:String;

		public function get codSituacaoProcessamentoArquivo():Number {
			return _codSituacaoProcessamentoArquivo;
		}
		
		public function set codSituacaoProcessamentoArquivo(codSituacaoProcessamentoArquivo:Number):void {
			this._codSituacaoProcessamentoArquivo = codSituacaoProcessamentoArquivo;
		}

		public function get descSituacaoProcessamentoArquivo():String {
			return _descSituacaoProcessamentoArquivo;
		}

		public function set descSituacaoProcessamentoArquivo(descSituacaoProcessamentoArquivo:String):void {
			this._descSituacaoProcessamentoArquivo = descSituacaoProcessamentoArquivo;
		}



	}
}