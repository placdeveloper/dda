package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoBoletoConsultadoVO", TipoBoletoConsultadoVO);

	public class TipoBoletoConsultadoVO {

		private var _codTipoBoletoConsultado:String;
		private var _descTipoBoletoConsultado:String;

		public function set codTipoBoletoConsultado(codTipoBoletoConsultado:String):void {
			this._codTipoBoletoConsultado = codTipoBoletoConsultado;
		}

		public function get codTipoBoletoConsultado():String {
			return _codTipoBoletoConsultado;
		}

		public function set descTipoBoletoConsultado(descTipoBoletoConsultado:String):void {
			this._descTipoBoletoConsultado = descTipoBoletoConsultado;
		}

		public function get descTipoBoletoConsultado():String {
			return _descTipoBoletoConsultado;
		}

	}
}