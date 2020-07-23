package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaTipoErroCipDTO", ConsultaTipoErroCipDTO);

	public class ConsultaTipoErroCipDTO {

		private var _codTipoErro:String;
		private var _descTipoErro:String;
		private var _bolErroTratamentoAutomatizado:Boolean;

		public function set codTipoErro(codTipoErro:String):void {
			this._codTipoErro = codTipoErro;
		}

		public function get codTipoErro():String {
			return _codTipoErro;
		}

		public function set descTipoErro(descTipoErro:String):void {
			this._descTipoErro = descTipoErro;
		}

		public function get descTipoErro():String {
			return _descTipoErro;
		}

		public function set bolErroTratamentoAutomatizado(bolErroTratamentoAutomatizado:Boolean):void {
			this._bolErroTratamentoAutomatizado = bolErroTratamentoAutomatizado;
		}

		public function get bolErroTratamentoAutomatizado():Boolean {
			return _bolErroTratamentoAutomatizado;
		}

	}
}