package br.com.sicoob.sisbr.sicoobdda.comumflex.vo
{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoErroCipVO", TipoErroCipVO);
	
	public class TipoErroCipVO
	{
		
		private var _codTipoErro:String;
		private var _descTipoErro:String;
		private var _tipoTratamentoErroCip:TipoTratamentoErroCipVO;
		
		public function TipoErroCipVO()
		{
		}
		

		public function get codTipoErro():String {
			return _codTipoErro;
		}

		public function set codTipoErro(codTipoErro:String):void {
			this._codTipoErro = codTipoErro;
		}

		public function get descTipoErro():String {
			return _descTipoErro;
		}
		
		public function set descTipoErro(descTipoErro:String):void {
			this._descTipoErro = descTipoErro;
		}
		
		public function get tipoTratamentoErroCip():TipoTratamentoErroCipVO {
			return _tipoTratamentoErroCip;
		}
		
		public function set tipoTratamentoErroCip(tipoTratamentoErroCip:TipoTratamentoErroCipVO):void {
			this._tipoTratamentoErroCip = tipoTratamentoErroCip;
		}
	}
}