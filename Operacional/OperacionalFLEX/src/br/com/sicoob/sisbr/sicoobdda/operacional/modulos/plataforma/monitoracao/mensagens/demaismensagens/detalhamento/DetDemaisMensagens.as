package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.demaismensagens.detalhamento
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoDemaisMensagensDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.detalhamento.DetMonitoracaoView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.demaismensagens.indicadores.IndicadorDemaisMensagens;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO",RegistroVO)
	
	public class DetDemaisMensagens extends DetMonitoracaoView {
		
		private var _indicadores:IndicadorDemaisMensagens;
		private var _servico:ServicoJava;
		private var _destino:DestinoVO;
		private var _monitoracaoDemaisMensagens:MonitoracaoDemaisMensagensDTO;
		
		private const OPERACAO_OBTER_DETALHE_MONITORACAO_DEMAIS_MSG:String = "obterDetalheMonitoracaoDemaisMensagens";
		
		public function DetDemaisMensagens(destino:DestinoVO) {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
			_destino = destino;
			obterServico();
		}
		
		private function init(e:FlexEvent):void {
			adicionarIndicadores();
			btnAtualizar.addEventListener(MouseEvent.CLICK, atualizarMonitoracao);
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			colQtdSemProtocolo.headerText = "Sem Protocolo SSPB";
			colQtdSemRetorno.headerText = "Sem Retorno CIP";
			colQtdComErro.headerText = "Retorno Com Erro";
		}
		
		private function adicionarIndicadores():void {
			_indicadores = new IndicadorDemaisMensagens();
			canvasIndicadores.addChild(_indicadores);
		}
		
		private function obterServico():void {
			_servico = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO, OPERACAO_OBTER_DETALHE_MONITORACAO_DEMAIS_MSG);
			_servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			_servico.bloquearOperacao = true;
			_servico.showBusyCursor = true;
			_servico.configurarDestino(_destino);
			atualizarMonitoracao();
		}
		
		private function retornoPesquisar(e:ResultEvent):void {
			_monitoracaoDemaisMensagens = e.result.dados.monitoracao as MonitoracaoDemaisMensagensDTO;
			carregarDados();
		}
		
		private function carregarDados():void {
			_indicadores.carregarDados(_monitoracaoDemaisMensagens);
			gridDetalhamento.dataProvider = _monitoracaoDemaisMensagens.listaDetalhamento;
			gridErros.dataProvider = _monitoracaoDemaisMensagens.listaErro;
		}
		
		private function atualizarMonitoracao(e:MouseEvent = null):void {
			_servico.obterDetalheMonitoracaoDemaisMensagens();
		}
		
		private function fechar(e:MouseEvent):void {
			this.fecharJanela()
		}
	}
}