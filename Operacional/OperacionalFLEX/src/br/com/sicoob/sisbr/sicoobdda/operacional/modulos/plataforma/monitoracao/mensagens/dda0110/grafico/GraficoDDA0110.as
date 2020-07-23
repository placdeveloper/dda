package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.dda0110.grafico
{
	import mx.collections.ArrayCollection;
	import mx.effects.Fade;
	import mx.rpc.events.FaultEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoDDA0110DTO;

	public class GraficoDDA0110 extends GraficoDDA0110View {
		
		private static const QTD_REGISTROS:Number = 6;
		private static const MAX_REGISTROS:Number = 20;
		
		private var _percentualAceitavelSLA:Number;
		
		public function GraficoDDA0110() {
			
		}
		
		public function adicionarDados(monitoracao:MonitoracaoDDA0110DTO):void {
			_percentualAceitavelSLA = monitoracao.percentualAceitavelSLA;
			preparaListaDados(monitoracao);
			iniciarFade();	
		}
		
		private function preparaListaDados(monitoracao:MonitoracaoDDA0110DTO):void {
			if (_listaDadosMonitoracao.length == MAX_REGISTROS) {
				_listaDadosMonitoracao.removeItemAt(0);
			}
			_listaDadosMonitoracao.addItem(monitoracao);
		}
		
		private function iniciarFade():void {
			if (obterPorcentagemFalha(_listaDadosMonitoracao) > _percentualAceitavelSLA) {
				this.canvasGrafico.styleName = "alerta";
				dispatchEvent(new FaultEvent(FaultEvent.FAULT));
			} else {
				this.canvasGrafico.styleName = "";
			}
			var fadePainel1:Fade = new Fade(this.canvasAlerta);
			fadePainel1.alphaFrom = 1;
			fadePainel1.alphaTo = 0.5;
			fadePainel1.play();				
		}
		
		//Pega o últmo da lista, se lista vazia retorna 0
		private function obterPorcentagemFalha(lista:ArrayCollection):Number {
			var qtdFalhaSLA:Number = 0;
			var qtdTotalMensagens:Number = 0;
			if (lista.length) {
				for (var i:int = _listaDadosMonitoracao.length - 1; i >= _listaDadosMonitoracao.length - QTD_REGISTROS ; i--) {
					var monitoracao:MonitoracaoDDA0110DTO = _listaDadosMonitoracao.getItemAt(i) as MonitoracaoDDA0110DTO;
					qtdFalhaSLA+= monitoracao.dda0110Rejeitada;
					qtdTotalMensagens+= monitoracao.dda0110Sucesso;
					
					if (!i) {
						break;
					}
				}
			}
			return qtdFalhaSLA*100/qtdTotalMensagens;
		}
	}
}