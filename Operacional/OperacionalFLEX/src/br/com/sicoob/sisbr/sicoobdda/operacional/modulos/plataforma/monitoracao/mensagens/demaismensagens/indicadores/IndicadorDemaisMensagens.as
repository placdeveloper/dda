package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.demaismensagens.indicadores
{
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.canvas.CanvasIndicador;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoDemaisMensagensDTO;

	public class IndicadorDemaisMensagens extends IndicadorDemaisMensagensView {
		
		public function IndicadorDemaisMensagens() {
			
		}
		
		public function carregarDados(monitoracao:MonitoracaoDemaisMensagensDTO):void {
			cvEnviar.carregarDados(monitoracao.msgEnviar, monitoracao.alertaEnviar);
			if (monitoracao.bolAlertaGradeHoraria) {
				cvEnviar.alertar(CanvasIndicador.ALERTA_GRADE_HORARIA);
			} else if (monitoracao.bolAlertaBloqueioMotorEnvio) {
				cvEnviar.alertar(CanvasIndicador.MOTOR_ENVIO_BLOQUEADO);
			}
			
			cvSemRetornoSSPB.carregarDados(monitoracao.msgSemRetornoSSPB, monitoracao.alertaSemRetornoSSPB);
			if (monitoracao.bolAlertaErroProtocolo){
				cvSemRetornoSSPB.alertar(CanvasIndicador.NIVEL_CRITICO);				
			}
			
			cvSemRetornoCIP.carregarDados(monitoracao.msgSemRetornoCIP, monitoracao.alertaSemRetornoCIP);
			cvRetornoComErro.carregarDados(monitoracao.msgRetornoErro, monitoracao.alertaRetornoErro);
		}
		
	}
}