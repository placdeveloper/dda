package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.varredura.indicadores
{
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.canvas.CanvasIndicador;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoArqVarreduraDTO;

	public class IndicadorArquivoVarredura extends IndicadorArquivoVarreduraView {
		
		public function IndicadorArquivoVarredura() {
			
		}
		
		public function carregarDados(monitoracaoArqVarredura:MonitoracaoArqVarreduraDTO):void {
			cvArqDisponiveis.carregarDados(monitoracaoArqVarredura.arqDisponivel, monitoracaoArqVarredura.alertaArqDisponivel);
			cvGEN0015SemArq.carregarDados(monitoracaoArqVarredura.gen0015SemArq, monitoracaoArqVarredura.alertaGEN0015SemArq);
			cvArqSemGEN0015.carregarDados(monitoracaoArqVarredura.arqSemGEN0015, monitoracaoArqVarredura.alertaArqSemGEN0015);
			cvArqEmProcessamento.carregarDados(monitoracaoArqVarredura.arqEmProcessamento, monitoracaoArqVarredura.alertaArqEmProcessamento);
			
			if (monitoracaoArqVarredura.bolAlertaGEN0015SemArq) {
				cvGEN0015SemArq.alertar(CanvasIndicador.NIVEL_CRITICO);
			}
		}
		
	}
}