package  br.com.sicoob.sisbr.sicoobdda.operacional.modulos.rateiotarifas { 
	
	import mx.controls.Label;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.EventoRateioDTO;
	
	public class RotuloDentroDesvio extends Label {
		
		public override function set data(value:Object):void {
			if (value != null && value is EventoRateioDTO) {
				super.data = value;
				
				var dto:EventoRateioDTO = value as EventoRateioDTO;
				
				super.setStyle("color", dto.dentroDesvioCor);
			}
		}
	}
	
}