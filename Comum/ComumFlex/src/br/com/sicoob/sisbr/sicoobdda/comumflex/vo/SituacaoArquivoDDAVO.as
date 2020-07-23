package br.com.sicoob.sisbr.sicoobdda.comumflex.vo
{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoArquivoDDAVO", SituacaoArquivoDDAVO);
	
	public class SituacaoArquivoDDAVO
	{
		
		private var _codSituacaoArquivo:Number;
		private var _descSituacaoArquivo:String;
		
		public function SituacaoArquivoDDAVO()
		{
		}
		

		public function get codSituacaoArquivo():Number {
			return _codSituacaoArquivo;
		}

		public function set codSituacaoArquivo(codSituacaoArquivo:Number):void {
			this._codSituacaoArquivo = codSituacaoArquivo;
		}

		public function get descSituacaoArquivo():String {
			return _descSituacaoArquivo;
		}
		
		public function set descSituacaoArquivo(descSituacaoArquivo:String):void {
			this._descSituacaoArquivo = descSituacaoArquivo;
		}
	}
}