package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.FiltroRelatorioEventoTarifavelDTO", FiltroRelatorioEventoTarifavelDTO);
	
	public class FiltroRelatorioEventoTarifavelDTO {
		
		private var _codEventoTarifavel:Number;
		private var _codStatus:Number;
		private var _descEventoTarifavel:String;
		
		public function FiltroRelatorioEventoTarifavelDTO() {
		}
		
		public function set codEventoTarifavel(codEventoTarifavel:Number):void {
			this._codEventoTarifavel = codEventoTarifavel;
		}
		
		public function get codEventoTarifavel():Number {
			return _codEventoTarifavel;
		}
		
		public function set codStatus(codStatus:Number):void {
			this._codStatus = codStatus;
		}
		
		public function get codStatus():Number {
			return _codStatus;
		}
		
		public function set descEventoTarifavel(descEventoTarifavel:String):void {
			this._descEventoTarifavel = descEventoTarifavel;
		}
		
		public function get descEventoTarifavel():String {
			return _descEventoTarifavel;
		}
		
		
	}
}