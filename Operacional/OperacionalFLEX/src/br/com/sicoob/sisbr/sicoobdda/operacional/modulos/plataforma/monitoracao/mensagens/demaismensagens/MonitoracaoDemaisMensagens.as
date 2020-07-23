package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.demaismensagens
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.net.registerClassAlias;
	import flash.utils.Timer;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoDemaisMensagensDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.demaismensagens.detalhamento.DetDemaisMensagens;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.titulo.TituloMonitoracao;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDemaisMensagensDTO", MonitoracaoDemaisMensagensDTO);	
	
	public class MonitoracaoDemaisMensagens extends MonitoracaoDemaisMensagensView {
		
		private var _monitoracao:MonitoracaoDemaisMensagensDTO;
		private var _timerPesquisa:Timer;
		private var _segundos:Number = 0; 
		private var _destino:DestinoVO;
		private var _servico:ServicoJava;
		private var _titulo:TituloMonitoracao;
		
		private const OPERACAO_OBTER_DADOS_MONITORACAO_DEMAIS_MENSAGENS:String = "obterDadosMonitoracaoDemaisMensagens";
		
		public function MonitoracaoDemaisMensagens() {
			_titulo = new TituloMonitoracao(TituloMonitoracao.MODO_DEMAIS_MSG);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			btnDetalhar.addEventListener(MouseEvent.CLICK, detalharMonitoracao);
			cvTitulo.addChild(_titulo);
		}
		
		private function detalharMonitoracao(e:MouseEvent):void {
			stopTimer();
			var detalharMonitoracao:DetDemaisMensagens = new DetDemaisMensagens(_destino);
			JanelaCobranca.abrirJanela(this, detalharMonitoracao, "DETALHAMENTO MONITORACAO DEMAIS MENSAGENS", true, refazerPesquisa);
		}
		
		public function iniciarMonitoracao(monitoracaoDemaisMensagens:MonitoracaoDemaisMensagensDTO):void {
			_monitoracao = monitoracaoDemaisMensagens;
			
			_titulo.monitoracaoDTO = _monitoracao;
			_titulo.atualizacao = monitoracaoDemaisMensagens.dataHoraAtualizacao.data;
			initTimer(_monitoracao.parametroTempoAtualizacao);
			carregarDados();
		}
		
		public function set destino(destino:DestinoVO):void {
			_destino = destino;
			obterServicoPesquisa();
		}
		
		private function obterServicoPesquisa():void {
			_servico = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO, OPERACAO_OBTER_DADOS_MONITORACAO_DEMAIS_MENSAGENS);
			_servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			_servico.bloquearOperacao = false;
			_servico.showBusyCursor = false;
			_servico.configurarDestino(_destino);
		}
		
		private function retornoPesquisar(e:ResultEvent):void {
			iniciarMonitoracao(e.result.dados.monitoracao as MonitoracaoDemaisMensagensDTO);
		}
		
		private function carregarDados():void {
			indicadores.carregarDados(_monitoracao);
			btnDetalhar.enabled = true;
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
			_servico.obterDadosMonitoracaoDemaisMensagens();
		}
		
		public function stopTimer():void {
			if (_timerPesquisa) {
				_timerPesquisa.stop();
			}
		}
		
	}
}