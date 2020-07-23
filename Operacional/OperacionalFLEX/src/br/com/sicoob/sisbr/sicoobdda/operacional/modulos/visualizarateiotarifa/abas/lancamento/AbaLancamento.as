package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.visualizarateiotarifa.abas.lancamento
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.VisualizaRateioTarifaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class AbaLancamento extends AbaLancamentoView
	{
		public var _listaLancamentos:ArrayCollection;
		public var _funcaoRetetornoImprimir:Function;
		
		private static const DETALHE_LANCAMENTO:String = "DETALHE DO LANÇAMENTO";

		
		public var _visualRateioTarifaDtoTemp:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO();

		public function AbaLancamento()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			iniciarComponentes();
		}	
		
		private function iniciarComponentes():void {
			this.compCentralSingular.carregarCombos();			
			iniciarEventos();
			definirComponentes();
			iniciarBotoes();
		}
		//--------------------------------------------------------------------------
		//  Inicializar Eventos.
		//--------------------------------------------------------------------------
		private function iniciarEventos():void {
			this.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, restaCmbSingular);
			this.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, enableSingular);
			this.compCentralSingular.cmbSingular.enabled = false;
			this.listaLancamento.addEventListener(MouseEvent.CLICK, tratarBotoesAcao);
		}
		
		public function funcaoRetetornoImprimir(funcao:Function):void {
			this._funcaoRetetornoImprimir = funcao;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializar botões.
		//--------------------------------------------------------------------------
		private function iniciarBotoes():void {
			this.btnDetalhar.enabled = false;
			this.btnAlterar.enabled = false;
			this.btnEfetivar.enabled = false;
		}
		
		
		//--------------------------------------------------------------------------
		//  Definição de componentes Posição-Estado-Tamanho-Etc.
		//--------------------------------------------------------------------------
		private function definirComponentes():void {
			this.compCentralSingular.hboxCentral.x = -25;
			this.compCentralSingular.hboxSingular.x = 165;
			
			this.compCentralSingular.hboxSingular.y = this.compCentralSingular.hboxCentral.y;
			this.compCentralSingular.hboxSingular.x = this.compCentralSingular.hboxCentral.x + 200;
			
			this.compCentralSingular.hboxCentral.width = 200;
			this.compCentralSingular.hboxSingular.width = 200;
			
			this.compCentralSingular.hboxCentral.horizontalScrollPolicy = "off";
			this.compCentralSingular.hboxSingular.horizontalScrollPolicy = "off";
			
			this.compCentralSingular.mostrarUnidade = false;
			this.compCentralSingular.height = 90;
			
			this.compCentralSingular.cmbCentral.inserirItemOpcional = true;
			this.compCentralSingular.cmbCentral.labelItemOpcional="---";
			
			this.compCentralSingular.cmbSingular.inserirItemOpcional = true;
			this.compCentralSingular.cmbSingular.labelItemOpcional="---";
		}

		//--------------------------------------------------------------------------
		//  Limpar a pesquisa.
		//--------------------------------------------------------------------------
		public function limparCampos(evt:Event=null):void {
			this.compCentralSingular.cmbCentral.selectedIndex = 0;
			this.compCentralSingular.cmbSingular.selectedIndex = 0;
			this.compCentralSingular.cmbSingular.enabled = false;
			this.cmbSituacaoLancamento.selectedIndex = 0;
		}
		
		private function limparGrid(evt:Event=null):void {
			this.listaLancamento.dataProvider = null;
		}

		//--------------------------------------------------------------------------
		//  Reseta comobo Singular e Central.
		//--------------------------------------------------------------------------
		private function restaCmbSingular(e:Event):void{
			if(this.compCentralSingular.cmbCentral.selectedIndex == 0){
				this.compCentralSingular.cmbSingular.selectedIndex = 0;	
			}
		}
		
		//--------------------------------------------------------------------------
		//  Desabilitar combo singular.
		//--------------------------------------------------------------------------
		private function enableSingular(e:Event):void {
			if(this.compCentralSingular.cmbCentral.selectedIndex == 0){
				this.compCentralSingular.cmbSingular.enabled = false;
			}else{
				this.compCentralSingular.cmbSingular.enabled = true;
			}
		}
		
		//--------------------------------------------------------------------------
		//  Tratar botões de ação.
		//--------------------------------------------------------------------------
		private function tratarBotoesAcao(event:MouseEvent=null):void {
			if (listaLancamento.selectedItem) {
				habilitaBotoesAcao();
			} else {
				desabilitaBotoesAcao();
			}
		}
		
		//--------------------------------------------------------------------------
		//  Habilita e Desabilita os campos do lado da tabela.
		//--------------------------------------------------------------------------
		private function habilitaBotoesAcao():void {
			this.btnDetalhar.enabled = true;
			this.btnAlterar.enabled = true;
			this.btnEfetivar.enabled = true;	
		}
		
		public function desabilitaBotoesAcao():void {
			this.btnDetalhar.enabled = false;
			this.btnAlterar.enabled = false;
			this.btnEfetivar.enabled = false;
		}
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
	}
}
