package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.demaismensagens.tooltip
{
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoDemaisMensagensDTO;

	public class TooltipDemaisMensagens extends TooltipDemaisMensagensView {
		
		private var _monitoracaoDemaisMensagens:MonitoracaoDemaisMensagensDTO;
		
		public function TooltipDemaisMensagens() { 
		}
		
		public function set monitoracaoDemaisMensagens(monitoracaoDemaisMensagens:MonitoracaoDemaisMensagensDTO):void {
			_monitoracaoDemaisMensagens = monitoracaoDemaisMensagens;
			if (_monitoracaoDemaisMensagens) {
				_alertaEnviar = _monitoracaoDemaisMensagens.alertaEnviar;
				_alertaSemRetornoSSPB = _monitoracaoDemaisMensagens.alertaSemRetornoSSPB;
				_alertaSemRetornoCIP = _monitoracaoDemaisMensagens.alertaSemRetornoCIP;
				_alertaRetornoErro = _monitoracaoDemaisMensagens.alertaRetornoErro;
			}
		}
		
	}
}
