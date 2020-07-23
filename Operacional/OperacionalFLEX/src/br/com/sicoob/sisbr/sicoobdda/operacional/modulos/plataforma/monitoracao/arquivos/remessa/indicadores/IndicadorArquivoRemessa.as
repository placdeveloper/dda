package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.remessa.indicadores
{
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.canvas.CanvasIndicador;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoArqRemessaDTO;

	public class IndicadorArquivoRemessa extends IndicadorArquivoRemessaView {
		
		public function IndicadorArquivoRemessa() {
			
		}
		
		public function carregarDados(monitoracaoArqRemessa:MonitoracaoArqRemessaDTO):void {
			cvEnviar.carregarDados(monitoracaoArqRemessa.arqEnviar, monitoracaoArqRemessa.alertaEnviar);
			if (monitoracaoArqRemessa.bolAlertaGradeHoraria) {
				cvEnviar.alertar(CanvasIndicador.ALERTA_GRADE_HORARIA);
			} else if (monitoracaoArqRemessa.bolAlertaBloqueioMotorEnvio) {
				cvEnviar.alertar(CanvasIndicador.MOTOR_ENVIO_BLOQUEADO);
			}
			
			cvSemProtocoloCIP.carregarDados(monitoracaoArqRemessa.arqSemProtocolo, monitoracaoArqRemessa.alertaSemProtocoloCIP);
			if (monitoracaoArqRemessa.bolAlertaErroProtocolo){
				cvSemProtocoloCIP.alertar(CanvasIndicador.NIVEL_CRITICO);				
			}
			
			cvSemRetornoCIP.carregarDados(monitoracaoArqRemessa.arqSemRetornoCIP, monitoracaoArqRemessa.alertaSemRetornoCIP);
			cvProcessadosComErro.carregarDados(monitoracaoArqRemessa.arqProcessadoErro, monitoracaoArqRemessa.alertaProcessadoErro);
		}
		
	}
}