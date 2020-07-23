package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.remessa.detalhamento
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoArqRemessaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.remessa.indicadores.IndicadorArquivoRemessa;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.detalhamento.DetMonitoracaoView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("RegistroVO",RegistroVO)
	
	public class DetArquivoRemessa extends DetMonitoracaoView {
		
		private var _indicadores:IndicadorArquivoRemessa;
		private var _servico:ServicoJava;
		private var _destino:DestinoVO;
		private var _monitoracaoArqRemessa:MonitoracaoArqRemessaDTO;
		
		private const OPERACAO_OBTER_DETALHE_MONITORACAO_ARQ_REMESSA:String = "obterDetalheMonitoracaoArquivoRemessa";
		
		public function DetArquivoRemessa(destino:DestinoVO) {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
			_destino = destino;
			obterServico();
		}
		
		private function init(e:FlexEvent):void {
			adicionarIndicadores();
			btnAtualizar.addEventListener(MouseEvent.CLICK, atualizarMonitoracao);
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			colQtdSemProtocolo.headerText = "Sem Protocolo CIP";
			colQtdSemRetorno.headerText = "Sem Retorno CIP";
			colQtdComErro.headerText = "Processados Com Erro"; 
		}
		
		private function adicionarIndicadores():void {
			_indicadores = new IndicadorArquivoRemessa();
			canvasIndicadores.addChild(_indicadores);
		}
		
		private function obterServico():void {
			_servico = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO, OPERACAO_OBTER_DETALHE_MONITORACAO_ARQ_REMESSA);
			_servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			_servico.bloquearOperacao = true;
			_servico.showBusyCursor = true;
			_servico.configurarDestino(_destino);
			atualizarMonitoracao();
		}
		
		private function retornoPesquisar(e:ResultEvent):void {
			_monitoracaoArqRemessa = e.result.dados.monitoracao as MonitoracaoArqRemessaDTO;
			carregarDados();
		}
		
		private function carregarDados():void {
			_indicadores.carregarDados(_monitoracaoArqRemessa);
			gridDetalhamento.dataProvider = _monitoracaoArqRemessa.listaDetalhamento;
			gridErros.dataProvider = _monitoracaoArqRemessa.listaErro;
		}
		
		private function atualizarMonitoracao(e:MouseEvent = null):void {
			_servico.obterDetalheMonitoracaoArquivoRemessa();
		}
		
		private function fechar(e:MouseEvent):void {
			this.fecharJanela()
		}
	}
}