package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.EventoTarifavelDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.FiltroRelatorioEventoTarifavelDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.EventoTarifavelDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.eventotarifavel.manter.ManterEventoTarifavelView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.eventotarifavel.manter.PopUpManterEventoTarifavel;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;

	registerClassAlias("RegistroVO", RegistroVO);
	public class ManterEventoTarifavel extends ManterEventoTarifavelView {
		
		public static const TITULO_ALTERAR:String = "Alterar Evento Tarifável";
		
		public function ManterEventoTarifavel() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			iniciarComponentes();
			carregarFiltro();
			pesquisar();	
		}	
		
		private function iniciarComponentes():void {
			this.gridEventoTarifavel.addEventListener(MouseEvent.CLICK, tratarBotoesAcao)
			//Botões Crud
			this.btnProcurar.addEventListener(MouseEvent.CLICK, pesquisar);
			
			this.btnAlterar.addEventListener(MouseEvent.CLICK, popUpEditar);
			this.btnExcluir.addEventListener(MouseEvent.CLICK, excluir);
			this.btnImprimir.addEventListener(MouseEvent.CLICK, imprimir);
			
			this.btnLimpar.addEventListener(MouseEvent.CLICK, limparFiltro);
			this.cmbEventoTarifavel.addEventListener(ListEvent.CHANGE, limparGrid);
			this.cmbStatusVigencia.addEventListener(ListEvent.CHANGE, limparGrid);
			
			this.btFechar.setStyle("icon", ConstantesComum.icoFechar);
			this.btnImprimir.setStyle("icon", ConstantesComum.icoImprimir);
			this.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			this.cmbStatusVigencia.dataProvider = ConstantesComum.CMB_STATUS_VIGENCIA;
			desabilitaBotoesAcao();
		}
		
		//--------------------------------------------------------------------------
		//  Carrega filtros.
		//--------------------------------------------------------------------------
		private function carregarFiltro(evt:Event = null):void{
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MANTER_EVENTO_TARIFAVEL, "listaEventoTarifavelDDA");
			servico.addEventListener(ResultEvent.RESULT, retornoFiltro);
			servico.listaEventoTarifavelDDA();
			
		}
		
		public function retornoFiltro(event:ResultEvent):void{
			cmbEventoTarifavel.dataProvider = event.result.dados["lista"];
		}
		
		//--------------------------------------------------------------------------
		//  Excluir evento tarifavel.
		//--------------------------------------------------------------------------
		private function excluir(event:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao(MensagensComum.formatar(MensagensComum.MSG007, "evento tarifável"), btnExcluir, excluirEventoTarifavel);	
		}
		
		
		private function excluirEventoTarifavel(event:MouseEvent):void {
			desabilitaBotoesAcao();
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = gridEventoTarifavel.selectedItem as EventoTarifavelDTO;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MANTER_EVENTO_TARIFAVEL, "excluirEventoTarifavelDDA");
			servico.addEventListener(ResultEvent.RESULT, retornoExcluir);
			servico.excluirEventoTarifavelDDA(dto);		
		}
		
		public function retornoExcluir(event:ResultEvent):void{
			pesquisar();	
		}
		
		//--------------------------------------------------------------------------
		//  Imprimir evento tarifavel.
		//--------------------------------------------------------------------------
		private function imprimir(event:MouseEvent):void {
			desabilitaBotoesAcao();
			var eventoTarifavel:EventoTarifavelDDAVO = cmbEventoTarifavel.selectedItem as EventoTarifavelDDAVO;
			
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_LISTA_EVENTO_TARIFAVEL, criarFiltroRelatorioEventoTarifavel(eventoTarifavel), null, PreImpressao.FORMATO_PDF);
		}
		
		public function criarFiltroRelatorioEventoTarifavel(eventoTarifavel:EventoTarifavelDDAVO):FiltroRelatorioEventoTarifavelDTO {
			var filtro:FiltroRelatorioEventoTarifavelDTO = new FiltroRelatorioEventoTarifavelDTO();
			if (eventoTarifavel) {
				filtro.codEventoTarifavel = eventoTarifavel.codEventoTarifavel;
				filtro.descEventoTarifavel = eventoTarifavel.descEventoTarifavelExtrato;
			}
			
			if (cmbStatusVigencia.selectedItem) {
				filtro.codStatus = cmbStatusVigencia.selectedItem.data;
			}
			
			return filtro;
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Edição.
		//--------------------------------------------------------------------------
		private function popUpEditar(event:MouseEvent):void {
			var dto:EventoTarifavelDTO =  gridEventoTarifavel.selectedItem as EventoTarifavelDTO;
			var tela:PopUpManterEventoTarifavel = new PopUpManterEventoTarifavel(dto.idEventoTarifavelDDATarifa, pesquisar, false);
			JanelaCobranca.abrirJanela(this, tela, TITULO_ALTERAR);
		}
		
		private function refazerPequisa():void {
		}
		
		//--------------------------------------------------------------------------
		//  Pesquisar evento tarifavel.
		//--------------------------------------------------------------------------		
		private function pesquisar(evt:Event = null):void{
			desabilitaBotoesAcao();
			limparGrid();
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var eventoTarifavel:EventoTarifavelDDAVO = cmbEventoTarifavel.selectedItem as EventoTarifavelDDAVO;
			dto.dados.codEventoTarifavel = eventoTarifavel == null ? null : eventoTarifavel.codEventoTarifavel;
			dto.dados.codStatus = cmbStatusVigencia.selectedItem == null ? null : cmbStatusVigencia.selectedItem.data;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MANTER_EVENTO_TARIFAVEL, "pesquisaEventoTarifavelDDA");
			servico.addEventListener(ResultEvent.RESULT, retornoPesquisa);
			servico.pesquisaEventoTarifavelDDA(dto);
		}
		


		public function retornoPesquisa(event:ResultEvent):void{
			gridEventoTarifavel.dataProvider = event.result.dados["lista"];
			btnImprimir.enabled = true;
			if(gridEventoTarifavel.dataProvider == null){
				btnImprimir.enabled = false;
				MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(Mensagens.MSG076, "Evento Tarifável"));
			}
		}
		
		//--------------------------------------------------------------------------
		//  Limpar campos.
		//--------------------------------------------------------------------------
		private function limparFiltro(e:Event):void {
			this.cmbEventoTarifavel.selectedItem = null;
			this.cmbStatusVigencia.selectedIndex = 0;
			limparGrid();
			tratarBotoesAcao();
		}
		
		private function limparGrid(e:Event=null):void {
			this.gridEventoTarifavel.dataProvider = null;
			btnImprimir.enabled = false;
		}
		
		//--------------------------------------------------------------------------
		//  Trata botões de ação
		//--------------------------------------------------------------------------
		private function tratarBotoesAcao(event:MouseEvent=null):void {
			if(gridEventoTarifavel.selectedItem){
				habilitaBotoesAcao();
			}else{
				desabilitaBotoesAcao();
			}
		}
		private function habilitaBotoesAcao():void {
			this.btnAlterar.enabled = true;
			this.btnExcluir.enabled = true;
		}
		
		private function desabilitaBotoesAcao():void {
			this.btnAlterar.enabled = false;
			this.btnExcluir.enabled = false;
		}
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
	}
}