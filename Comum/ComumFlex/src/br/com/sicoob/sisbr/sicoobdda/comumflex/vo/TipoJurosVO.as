package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoJurosVO", TipoJurosVO);

	public class TipoJurosVO {
		
		public  static const VALOR_DIAS_CORRIDOS:Number = 1;
		public  static const PERCENTUAL_AO_DIA_DIAS_CORRIDOS:Number = 2;
		public  static const PERCENTUAL_AO_MES_DIAS_CORRIDOS:Number = 3;
		public  static const PERCENTUAL_AO_ANO_DIAS_CORRIDOS:Number = 4;
		public  static const ISENTO:Number = 5;
		public  static const VALOR_DIA_UTIL:Number = 6;
		public  static const PERCENTUAL_AO_DIA_DIAS_UTEIS:Number = 7;
		public  static const PERCENTUAL_AO_MES_DIAS_UTEIS:Number = 8;
		public  static const PERCENTUAL_AO_ANO_DIAS_UTEIS:Number = 9;
		
		private var _id:Number;
		private var _descTipoJuros:String;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set descTipoJuros(descTipoJuros:String):void {
			this._descTipoJuros = descTipoJuros;
		}

		public function get descTipoJuros():String {
			return _descTipoJuros;
		}

	}
}