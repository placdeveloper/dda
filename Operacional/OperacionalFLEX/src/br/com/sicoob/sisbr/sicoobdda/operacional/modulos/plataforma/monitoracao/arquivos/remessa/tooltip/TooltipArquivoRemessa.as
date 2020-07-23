package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.remessa.tooltip
{
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoArqRemessaDTO;

	public class TooltipArquivoRemessa extends TooltipArquivoRemessaView {
		
		private var _monitoracaoArqRemessa:MonitoracaoArqRemessaDTO;
		
		public function TooltipArquivoRemessa() { 
		}
		
		public function set monitoracaoArqRemessa(monitoracaoArqRemessa:MonitoracaoArqRemessaDTO):void {
			_monitoracaoArqRemessa = monitoracaoArqRemessa;
			if (_monitoracaoArqRemessa) {
				_alertaEnviar = _monitoracaoArqRemessa.alertaEnviar;
				_alertaSemProtocoloCIP = _monitoracaoArqRemessa.alertaSemProtocoloCIP;
				_alertaSemRetornoCIP = _monitoracaoArqRemessa.alertaSemRetornoCIP;
				_alertaProcessadoErro = _monitoracaoArqRemessa.alertaProcessadoErro;
			}
		}
	}
}
