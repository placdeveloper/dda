package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoBoletoVO", SituacaoBoletoVO);

	public class SituacaoBoletoVO {

		private var _codSituacaoBoleto:String;
		private var _descSituacaoBoleto:String;
		private var _idTipoSituacaoBoleto:String;

		public function set codSituacaoBoleto(codSituacaoBoleto:String):void {
			this._codSituacaoBoleto = codSituacaoBoleto;
		}

		public function get codSituacaoBoleto():String {
			return _codSituacaoBoleto;
		}

		public function set descSituacaoBoleto(descSituacaoBoleto:String):void {
			this._descSituacaoBoleto = descSituacaoBoleto;
		}

		public function get descSituacaoBoleto():String {
			return _descSituacaoBoleto;
		}
		
		public function set idTipoSituacaoBoleto(idTipoSituacaoBoleto:String):void {
			this._idTipoSituacaoBoleto = idTipoSituacaoBoleto;
		}
		
		public function get idTipoSituacaoBoleto():String {
			return _idTipoSituacaoBoleto;
		}

	}
}