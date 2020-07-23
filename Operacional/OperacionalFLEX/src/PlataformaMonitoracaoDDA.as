package
{
	
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.sisbr.componentes.plataformas.IModuloPlataformaMonitoracao;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.PlataformaMonitoracaoDDAView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDTO", MonitoracaoDTO);
	
	public class PlataformaMonitoracaoDDA extends PlataformaMonitoracaoDDAView implements IModuloPlataformaMonitoracao {
		
		private var _destino:DestinoVO;
		
		private static const DESTINO_SERVICO_DDA:String = "servicosJavaSDDA";
		private static const OPERACAO_OBTER_DADOS: String = "obterDadosMonitoracao";
		
		public function PlataformaMonitoracaoDDA() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected function init(event:FlexEvent):void {
			PortalModel.portal.obterDefinicoesDestino(DESTINO_SERVICO_DDA, destinoRecuperado);
		}
		
		private function destinoRecuperado(destino:DestinoVO):void {
			_destino = destino;
			monitoracaoDDA0110.destino = destino;
			monitoracaoDemaisMensagens.destino = destino;
			monitoracaoRemessa.destino = destino;
			monitoracaoVarredura.destino = destino;
			
			pesquisar();
		}
		
		private function pesquisar():void {
			if (_destino) {
				var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO, OPERACAO_OBTER_DADOS);
				servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
				servico.bloquearOperacao = false;
				servico.showBusyCursor = false;
				servico.configurarDestino(_destino);
				servico.obterDadosMonitoracao();
			}
		}
		
		private function retornoPesquisar(e:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			
			var monitoracao:MonitoracaoDTO = e.result.dados.monitoracao as MonitoracaoDTO;
			
			monitoracaoDDA0110.iniciarMonitoracao(monitoracao.monitoracaoDDA0110);
			monitoracaoDemaisMensagens.iniciarMonitoracao(monitoracao.monitoracaoDemaisMensagens);
			monitoracaoRemessa.iniciarMonitoracao(monitoracao.monitoracaoArqRemessa);
			monitoracaoVarredura.iniciarMonitoracao(monitoracao.monitoracaoArqVarredura);
		}
		
		// Metodos necessarios para a plataforma (IModuloPlataformaMonitoracao)
		public function setWidget(tela:Object):void {
		}
		
		public function iniciarTempo():void {
		}
		
		public function pararTempo():void {
			monitoracaoDDA0110.stopTimer();
			monitoracaoDemaisMensagens.stopTimer();
			monitoracaoRemessa.stopTimer();
			monitoracaoVarredura.stopTimer();
		}
		
		public function mostraResumo():void	{
		}
		
		public function someResumo():void {
		} 
		
		public function mostraFiltro():void	{
		}
		
		public function someFiltro():void	{
		}
		// Fim métodos necessários para a plataforma  
	}
}