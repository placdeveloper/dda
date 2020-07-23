package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.varredura.tooltip
{
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoArqVarreduraDTO;

	public class TooltipArquivoVarredura extends TooltipArquivoVarreduraView {
		
		private var _monitoracaoArqVarredura:MonitoracaoArqVarreduraDTO;
		
		public function TooltipArquivoVarredura() { 
		}
		
		public function set monitoracaoArqVarredura(monitoracaoArqVarredura:MonitoracaoArqVarreduraDTO):void {
			_monitoracaoArqVarredura = monitoracaoArqVarredura;
			if (_monitoracaoArqVarredura) {
				_alertaArqDisponivel = _monitoracaoArqVarredura.alertaArqDisponivel;
				_alertaGEN0015SemArq = _monitoracaoArqVarredura.alertaGEN0015SemArq;
				_alertaArqSemGEN0015 = _monitoracaoArqVarredura.alertaArqSemGEN0015;
				_alertaArqEmProcessamento = _monitoracaoArqVarredura.alertaArqEmProcessamento;
			}
		}
		
	}
}
