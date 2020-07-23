package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.remessa
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
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoArqRemessaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.remessa.detalhamento.DetArquivoRemessa;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.titulo.TituloMonitoracao;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoArqRemessaDTO", MonitoracaoArqRemessaDTO);
	
	public class MonitoracaoArquivoRemessa extends MonitoracaoArquivoRemessaView {
		
		private var _monitoracaoArqRemessa:MonitoracaoArqRemessaDTO;
		private var _timerPesquisa:Timer;
		private var _segundos:Number = 0; 
		private var _destino:DestinoVO;
		private var _servico:ServicoJava;
		private var _titulo:TituloMonitoracao;
		
		private const OPERACAO_OBTER_DADOS_MONITORACAO_REMESSA:String = "obterDadosMonitoracaoArquivoRemessa";
		
		public function MonitoracaoArquivoRemessa() {
			_titulo = new TituloMonitoracao(TituloMonitoracao.MODO_ARQ_REMESSA);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			btnDetalhar.addEventListener(MouseEvent.CLICK, detalharMonitoracao);
			cvTitulo.addChild(_titulo);
		}
		
		private function detalharMonitoracao(e:MouseEvent):void {
			stopTimer();
			var detalharMonitoracao:DetArquivoRemessa = new DetArquivoRemessa(_destino);
			JanelaCobranca.abrirJanela(this, detalharMonitoracao, "DETALHAMENTO MONITORACAO ARQUIVOS DE REMESSA", true, refazerPesquisa);
		}
		
		public function iniciarMonitoracao(monitoracaoArqRemessa:MonitoracaoArqRemessaDTO):void {
			_monitoracaoArqRemessa = monitoracaoArqRemessa;
			initTimer(_monitoracaoArqRemessa.parametroTempoAtualizacao);
			carregarDados();
		}
		
		public function set destino(destino:DestinoVO):void {
			_destino = destino;
			obterServicoPesquisa();
		}
		
		private function obterServicoPesquisa():void {
			_servico = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO, OPERACAO_OBTER_DADOS_MONITORACAO_REMESSA);
			_servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			_servico.bloquearOperacao = false;
			_servico.showBusyCursor = false;
			_servico.configurarDestino(_destino);
		}
		
		private function retornoPesquisar(e:ResultEvent):void {
			iniciarMonitoracao(e.result.dados.monitoracao as MonitoracaoArqRemessaDTO);
		}
		
		private function carregarDados():void {
			_titulo.atualizacao = _monitoracaoArqRemessa.dataHoraAtualizacao.data;
			_titulo.monitoracaoDTO = _monitoracaoArqRemessa;
			indicadores.carregarDados(_monitoracaoArqRemessa);
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
			stopTimer();
			_servico.obterDadosMonitoracaoArquivoRemessa();
		}
		
		public function stopTimer():void {
			if (_timerPesquisa) {
				_timerPesquisa.stop();
			}
		}
	}
}