package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.varredura
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
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoArqVarreduraDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.varredura.detalhamento.DetArquivoVarredura;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.titulo.TituloMonitoracao;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoArqVarreduraDTO", MonitoracaoArqVarreduraDTO);
	
	public class MonitoracaoArquivoVarredura extends MonitoracaoArquivoVarreduraView {
		
		private var _monitoracaoArqVarredura:MonitoracaoArqVarreduraDTO;
		private var _timerPesquisa:Timer;
		private var _segundos:Number = 0; 
		private var _destino:DestinoVO;
		private var _servico:ServicoJava;
		private var _titulo:TituloMonitoracao;
		
		private const OPERACAO_OBTER_DADOS_MONITORACAO_VARREDURA:String = "obterDadosMonitoracaoArquivoVarredura";
		
		public function MonitoracaoArquivoVarredura() {
			_titulo = new TituloMonitoracao(TituloMonitoracao.MODO_ARQ_VARREDURA);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			btnDetalhar.addEventListener(MouseEvent.CLICK, detalharMonitoracao);
			cvTitulo.addChild(_titulo);
		}
		
		public function iniciarMonitoracao(monitoracaoArqVarredura:MonitoracaoArqVarreduraDTO):void {
			_monitoracaoArqVarredura = monitoracaoArqVarredura;
			initTimer(_monitoracaoArqVarredura.parametroTempoAtualizacao);
			carregarDados();
		}
		
		public function set destino(destino:DestinoVO):void {
			_destino = destino;
			obterServicoPesquisa();
		}
		
		private function detalharMonitoracao(e:MouseEvent):void {
			var detalharMonitoracao:DetArquivoVarredura = new DetArquivoVarredura(_destino, _monitoracaoArqVarredura);
			JanelaCobranca.abrirJanela(this, detalharMonitoracao, "DETALHAMENTO MONITORACAO ARQUIVOS RECEBIDOS", true, refazerPesquisa);
		}
		
		private function obterServicoPesquisa():void {
			_servico = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO, OPERACAO_OBTER_DADOS_MONITORACAO_VARREDURA);
			_servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			_servico.bloquearOperacao = false;
			_servico.showBusyCursor = false;
			_servico.configurarDestino(_destino);
		}
		
		private function retornoPesquisar(e:ResultEvent):void {
			iniciarMonitoracao(e.result.dados.monitoracao as MonitoracaoArqVarreduraDTO);
		}
		
		private function carregarDados():void {
			indicadores.carregarDados(_monitoracaoArqVarredura);
			_titulo.atualizacao = _monitoracaoArqVarredura.dataHoraAtualizacao.data;
			_titulo.monitoracaoDTO = _monitoracaoArqVarredura;
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
			_servico.obterDadosMonitoracaoArquivoVarredura();
		}
		
		public function stopTimer():void {
			if (_timerPesquisa) {
				_timerPesquisa.stop();
			}
		}
										  
	}
}