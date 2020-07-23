package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.varredura.detalhamento
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoArqVarreduraDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoArqVarreduraDTO", MonitoracaoArqVarreduraDTO);
	
	public class DetArquivoVarredura extends DetArquivoVarreduraView {
		
		private var _destino:DestinoVO;
		private var _servico:ServicoJava;
		
		private var _monitoracaoArqVarredura:MonitoracaoArqVarreduraDTO;
		
		private const OPERACAO_OBTER_DADOS_MONITORACAO_VARREDURA:String = "obterDadosMonitoracaoArquivoVarredura";
		
		public function DetArquivoVarredura(destino:DestinoVO, monitoracaoArqVarredura:MonitoracaoArqVarreduraDTO) {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
			_destino = destino;
			_monitoracaoArqVarredura = monitoracaoArqVarredura;
		}
		
		private function init(e:FlexEvent):void {
			carregarDados();
			obterServico();
			btnAtualizar.addEventListener(MouseEvent.CLICK, atualizarMonitoracao);
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
		}
		
		private function atualizarMonitoracao(e:MouseEvent = null):void {
			_servico.obterDadosMonitoracaoArquivoVarredura();
		}
		
		private function obterServico():void {
			_servico = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO, OPERACAO_OBTER_DADOS_MONITORACAO_VARREDURA);
			_servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			_servico.bloquearOperacao = true;
			_servico.showBusyCursor = true;
			_servico.configurarDestino(_destino);
		}
		
		private function retornoPesquisar(e:ResultEvent):void {
			_monitoracaoArqVarredura = e.result.dados.monitoracao as MonitoracaoArqVarreduraDTO;
			carregarDados();
		}
		
		private function carregarDados():void {
			indicadores.carregarDados(_monitoracaoArqVarredura);
			abaGEN0015SemArq.dataProvider = _monitoracaoArqVarredura.listaGEN0015SemArq;
			abaArqSemGEN0015.dataProvider = _monitoracaoArqVarredura.listaArqSemGEN0015;
			abaArqEmProcessamento.dataProvider = _monitoracaoArqVarredura.listaArqEmProcessamento;
			abaArqDisponivel.dataProvider = _monitoracaoArqVarredura.listaArqDisponivel;
		}
		
		private function fechar(e:MouseEvent):void {
			this.fecharJanela()
		}
	}
}