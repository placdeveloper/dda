package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.dda0110.tooltip
{
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoDDA0110DTO;

	public class TooltipDDA0110 extends TooltipDDA0110View {
		
		private var _monitoracaoDDA0110:MonitoracaoDDA0110DTO;
		
		public function TooltipDDA0110() { 
		}
		
		public function set monitoracaoDDA0110(monitoracaoDDA0110:MonitoracaoDDA0110DTO):void {
			_monitoracaoDDA0110 = monitoracaoDDA0110;
			if (_monitoracaoDDA0110) {
				_percentualAceitavel = _monitoracaoDDA0110.percentualAceitavelSLA + "%";
			}
		}
		
	}
}
