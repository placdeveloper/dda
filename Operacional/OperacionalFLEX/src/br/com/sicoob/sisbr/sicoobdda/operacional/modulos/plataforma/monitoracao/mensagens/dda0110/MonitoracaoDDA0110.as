package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.dda0110
{
	import flash.events.Event;
	import flash.events.TimerEvent;
	import flash.net.registerClassAlias;
	import flash.utils.Timer;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.canvas.CanvasIndicador;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoDDA0110DTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.titulo.TituloMonitoracao;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDDA0110DTO", MonitoracaoDDA0110DTO);
	
	public class MonitoracaoDDA0110 extends MonitoracaoDDA0110View {
		
		private var _monitoracaoDDA0110:MonitoracaoDDA0110DTO;
		private var _timerPesquisa:Timer;
		private var _segundos:Number = 0; 
		private var _destino:DestinoVO;
		private var _servico:ServicoJava;
		private var _dataUltimaAtualizacao:IDateTime;
		private var _titulo:TituloMonitoracao;
		
		private const OPERACAO_OBTER_DADOS_MONITORACAO_DDA0110:String = "obterDadosMonitoracaoDDA0110";
		
		public function MonitoracaoDDA0110() {
			_titulo = new TituloMonitoracao(TituloMonitoracao.MODO_DDA0110);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);	
		}
		
		private function init(e:FlexEvent):void {
			cvTitulo.addChild(_titulo);
			graficoDDA0110.addEventListener(FaultEvent.FAULT, alertaGrafico);
		}
		
		public function iniciarMonitoracao(monitoracaoDDA0110:MonitoracaoDDA0110DTO):void {
			_monitoracaoDDA0110 = monitoracaoDDA0110;
			
			_dataUltimaAtualizacao = _monitoracaoDDA0110.dataHoraAtualizacao;
			dispatchEvent(new ResultEvent(ResultEvent.RESULT));
			_titulo.monitoracaoDTO = monitoracaoDDA0110;
			_titulo.atualizacao = monitoracaoDDA0110.dataHoraAtualizacao.data;
			initTimer(_monitoracaoDDA0110.parametroTempoAtualizacao);
			carregarDados();
		}
		
		public function set destino(destino:DestinoVO):void {
			_destino = destino;
			obterServicoPesquisa();
		}
		
		private function obterServicoPesquisa():void {
			_servico = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO, OPERACAO_OBTER_DADOS_MONITORACAO_DDA0110);
			_servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			_servico.bloquearOperacao = false;
			_servico.showBusyCursor = false;
			_servico.configurarDestino(_destino);
		}
		
		private function retornoPesquisar(e:ResultEvent):void {
			iniciarMonitoracao(e.result.dados.monitoracao as MonitoracaoDDA0110DTO);
		}
		
		private function carregarDados():void {
			cvTotalSucesso.carregarDados(_monitoracaoDDA0110.dda0110SucessoTotal);
			cvTotalRejeitado.carregarDados(_monitoracaoDDA0110.dda0110RejeitadaTotal);
			cvSucesso.carregarDados(_monitoracaoDDA0110.dda0110Sucesso);
			cvRejeitado.carregarDados(_monitoracaoDDA0110.dda0110Rejeitada);
			graficoDDA0110.adicionarDados(_monitoracaoDDA0110);
			verificarAlerta(); 
		}
		
		private function verificarAlerta():void {
			cvConsulta.visible = cvConsulta.includeInLayout = (!_monitoracaoDDA0110.bolConsultaCipHabilitada || (_monitoracaoDDA0110.bolContingenciaHabilitadaManual || _monitoracaoDDA0110.bolContingenciaHabilitadaAutomatica));
			graficoDDA0110.enabled = (_monitoracaoDDA0110.bolConsultaCipHabilitada && (!_monitoracaoDDA0110.bolContingenciaHabilitadaManual || !_monitoracaoDDA0110.bolContingenciaHabilitadaAutomatica));
			if (_monitoracaoDDA0110.bolContingenciaHabilitadaManual) {
				txtConsulta.text = "CONTINGÊNCIA HABILITADA - MANUAL";
				cvSucesso.alertar(CanvasIndicador.NIVEL_ATENCAO);
			} else if (_monitoracaoDDA0110.bolContingenciaHabilitadaAutomatica) {
				txtConsulta.text = "CONTINGÊNCIA HABILITADA - AUTOMÁTICA";
				cvSucesso.alertar(CanvasIndicador.NIVEL_ATENCAO);
			} else if (!_monitoracaoDDA0110.bolConsultaCipHabilitada) {
				txtConsulta.text = "CONSULTA CIP DESLIGADA";
				cvSucesso.alertar(CanvasIndicador.NIVEL_ATENCAO);
			}
		}
		
		private function alertaGrafico(e:FaultEvent):void {
			cvRejeitado.alertar(CanvasIndicador.NIVEL_CRITICO);
		}
		
		private function initTimer(segundos:Number):void {
			if (!_timerPesquisa || _segundos != segundos) {
				_segundos = segundos;
				_timerPesquisa = new Timer(_segundos*1000);
				_timerPesquisa.addEventListener(TimerEvent.TIMER, refazerPesquisa);
			}
			_timerPesquisa.start();
		}
		
		private function refazerPesquisa(e:Event = null):void {
			stopTimer();
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dataHoraUltimaAfericao = _monitoracaoDDA0110.dataHoraAfericao;
			_servico.obterDadosMonitoracaoDDA0110(dto);
		}
		
		public function stopTimer():void {
			if (_timerPesquisa) {
				_timerPesquisa.stop();
			}
		}
		
		public function get dataUltimaAtualizacao():IDateTime {
			return _dataUltimaAtualizacao;
		}
		
	}
}