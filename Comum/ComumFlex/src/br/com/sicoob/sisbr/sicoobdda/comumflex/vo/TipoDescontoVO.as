package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoDescontoVO", TipoDescontoVO);

	public class TipoDescontoVO {
		
		public  static const ISENTO:String = "0";
		public  static const VALOR_FIXO_ATE_A_DATA_INFORMADA:String = "1";
		public  static const PERCENTUAL_ATE_A_DATA_INFORMADA:String = "2";
		public  static const VALOR_POR_ANTECIPACAO_DIA_CORRIDO:String = "3";
		public  static const VALOR_POR_ANTECIPACAO_DIA_UTIL:String = "4";
		public  static const PERCENTUAL_POR_ANTECIPACAO_DIA_CORRIDO:String = "5";
		public  static const PERCENTUAL_POR_ANTECIPACAO_DIA_UTIL:String = "6";

		private var _codTipoDesconto:String;
		private var _descTipoDesconto:String;

		public function set codTipoDesconto(codTipoDesconto:String):void {
			this._codTipoDesconto = codTipoDesconto;
		}

		public function get codTipoDesconto():String {
			return _codTipoDesconto;
		}

		public function set descTipoDesconto(descTipoDesconto:String):void {
			this._descTipoDesconto = descTipoDesconto;
		}

		public function get descTipoDesconto():String {
			return _descTipoDesconto;
		}

	}
}