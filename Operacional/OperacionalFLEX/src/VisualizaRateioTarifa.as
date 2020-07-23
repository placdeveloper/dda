package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.controls.CheckBox;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.VisualizaRateioTarifaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataPesquisaUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.visualizarateiotarifa.PopUpDetalharRateio;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.visualizarateiotarifa.VisualizaRateioTarifaView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class VisualizaRateioTarifa extends VisualizaRateioTarifaView
	{
		private var btnImprimir:Botao = new Botao();
		
		public static const DETALHAR_RATEIO_TARIFA:String = "DETALHAR RATEIO DE TARIFAS";
		private var _listaCallBack:ArrayCollection; 
		private const QTD_DIAS_CONSULTA:Number = 90;
		public function VisualizaRateioTarifa()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			this.painelListaBanco.callback = tratarCallbackPesquisa;
			this.painelListaBanco.funcaoCriacaoDto = instanciarDtoConsulta;
			
			this.painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarPesquisa);
			this.painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			this.painelFiltro.cmbSituacaoRateio.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.cmbTipoData.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.dataFim.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dataInicio.addEventListener(Event.CHANGE, limparGrid);
			
			this.painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaBanco.procuraSolicitada);
			this.painelLista.doubleClickEnabled =false;
			this.btnImprimir.addEventListener(MouseEvent.CLICK, imprimirRateioTarifas);
			this.btnDetalhar.addEventListener(MouseEvent.CLICK, popUpVisualizar);
			iniciarComponentes();
		}	
		
		private function iniciarComponentes():void {
			iniciarBotoes();
			carregarFiltros();
		}
		
		//--------------------------------------------------------------------------
		//  Inicializar botões.
		//--------------------------------------------------------------------------
		private function iniciarBotoes():void {
			//Trata botões de ação			
			btnDetalhar.enabled = false;
			
			barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoExcluir = false ;
			var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			
			btnImprimir.label = "Imprimir";
			btnImprimir.toolTip = "Imprimir";
			btnImprimir.height = 22;
			btnImprimir.enabled = false;
			hBoxBarraBotoes.addChildAt(btnImprimir, 1);
			btnImprimir.setStyle("icon", ConstantesComum.icoImprimir);
			
			var btnFechar:Botao = hBoxBarraBotoes.getChildByName('btnFechar') as Botao;
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			
		}
		//--------------------------------------------------------------------------
		//  Relatorio detalhado do Pagador PDF.
		//--------------------------------------------------------------------------
		public function imprimirRateioTarifas(e:Event=null):void {
			//var dto:RequisicaoReqDTO = new RequisicaoReqDTO(); //Removendo para otimizacao WebSphere
			if (validaData()) {
				return;
			} else {
				var visualRateioTarifaDto:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO();
				
				if (this.painelFiltro.cmbSituacaoRateio.selectedItem) {
					visualRateioTarifaDto.codSituacaoRateio = painelFiltro.cmbSituacaoRateio.selectedItem.codSituacaoRateio;
					visualRateioTarifaDto.descSituacaoRateio = painelFiltro.cmbSituacaoRateio.selectedItem.descSituacaoRateio;
				}
				
				if (this.painelFiltro.cmbTipoData.selectedItem) {
					visualRateioTarifaDto.codTipoDataEvento = painelFiltro.cmbTipoData.selectedItem.data;
				}
				
				if (this.painelFiltro.dataInicio.selectedDate) {
					visualRateioTarifaDto.dataInicio = DateTimeBase.getDateTime(painelFiltro.dataInicio.selectedDate);
				}
				
				if (this.painelFiltro.dataFim.selectedDate) {
					visualRateioTarifaDto.dataFim = DateTimeBase.getDateTime(painelFiltro.dataFim.selectedDate);
				}
	
				
				RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_RATEIO_TARIFAS, visualRateioTarifaDto, null, PreImpressao.FORMATO_PDF); 
			}
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos filtros de pesquisa.
		//--------------------------------------------------------------------------
		private function carregarFiltros():void {
			this.painelFiltro.cmbTipoData.dataProvider = Constantes.CMB_TIPO_DATA;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, "listaSituacaoRateio");
			servico.addEventListener(ResultEvent.RESULT, retornoCarregarFiltros);
			servico.listaSituacaoRateio();		
		}
		
		private function retornoCarregarFiltros(resultEvent:ResultEvent):void {
			painelFiltro.cmbSituacaoRateio.dataProvider = resultEvent.result.dados["lista"];
		}
		
		public function tratarCallbackPesquisa(event : ResultEvent) : void {
			painelListaBanco.pesquisaRealizada(event);
			this._listaCallBack = this.gridRateioTarifas.grdDados.dataProvider as ArrayCollection;
			if(this._listaCallBack.length > 0){
				this.btnImprimir.enabled = true;
				this.selecionarTodos = false;
			}
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Visualização.
		//--------------------------------------------------------------------------		
		private function popUpVisualizar(event:Event):void {
			var visualRateioTarifaDto:VisualizaRateioTarifaDTO=  new VisualizaRateioTarifaDTO();
			
			if(gridRateioTarifas.grdDados.dataProvider){
				var listaIdRateio:ArrayCollection = new ArrayCollection();
				for each (var obj:VisualizaRateioTarifaDTO in gridRateioTarifas.grdDados.dataProvider) 
				{
					if(obj.selecionado){
						listaIdRateio.addItem(obj.idRateioDDA);
					}
				}
				visualRateioTarifaDto.listaIdRateio = listaIdRateio;
			}
			visualRateioTarifaDto.selecionadoTodos = this.selecionarTodos;
			var tela:PopUpDetalharRateio = new PopUpDetalharRateio(visualRateioTarifaDto, retornoConsulta);
			JanelaCobranca.abrirJanela(this, tela, DETALHAR_RATEIO_TARIFA);
		}
		private function retornoConsulta():void{
			validarPesquisa(new ProcurarEvent());
		}
		//--------------------------------------------------------------------------
		//  Validar Procura.
		//--------------------------------------------------------------------------
		private function validarPesquisa(event:ProcurarEvent):void{
				var bolTipoData:Boolean = this.painelFiltro.cmbTipoData.selectedItem;
				var bolDataPreenchida:Boolean = this.painelFiltro.dataInicio.selectedDate == null && this.painelFiltro.dataFim.selectedDate == null
				
				if (this.painelFiltro.dataInicio.selectedDate == null && this.painelFiltro.dataFim.selectedDate == null) {
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Período"));
					return;
				}else if(validarPeriodo()){
					return;
				}else if(!this.painelFiltro.cmbTipoData.selectedItem && (!bolDataPreenchida)){
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Tipo de Data"));
					return;
				}
				limparGrid();
				this.limparDadosPesquisa();
				this.painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
				this.painelListaBanco.procuraSolicitada(event);				
		}
		
		//--------------------------------------------------------------------------
		//  Valida datas por evento de tecla e mouse.
		//--------------------------------------------------------------------------
		public function validaData():Boolean {
			var retorno:Boolean;
			var bolTipoData:Boolean = this.painelFiltro.cmbTipoData.selectedItem;
			var bolDataPreenchida:Boolean = this.painelFiltro.dataInicio.selectedDate == null && this.painelFiltro.dataFim.selectedDate == null
			if(bolTipoData){
				if (this.painelFiltro.dataInicio.selectedDate == null && this.painelFiltro.dataFim.selectedDate == null) {
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Período"));
					return true;
				} 
			}else{
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Tipo de Data"));
				return true 
			}
			if(!this.painelFiltro.cmbTipoData.selectedItem && (bolDataPreenchida)){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Situação do rateio"));
				return true;
			}
			
			return retorno;
		}
		
		//--------------------------------------------------------------------------
		//  Valida período de 6 mêses.
		//--------------------------------------------------------------------------
		public function validaDatasIguais(data1:IDateTime, data2:IDateTime):Boolean {
			var valorGrandeza:Number = DataUtil.compareDataTime(data1, data2);
			var isGrandeza:Boolean = valorGrandeza==0 || valorGrandeza == 1 ? true : false;			
			return isGrandeza; 
		}
		
		//--------------------------------------------------------------------------
		//  Valida período de 6 mêses.
		//--------------------------------------------------------------------------
		public function validarPeriodo():Boolean {
			return !DataPesquisaUtil.validaPeriodoDataPesquisa(this.painelFiltro.dataInicio, this.painelFiltro.dataFim, QTD_DIAS_CONSULTA);	
		}
		
		//--------------------------------------------------------------------------
		//  Limpar a pesquisa.
		//--------------------------------------------------------------------------
		public function limparCampos(evt:Event):void {
			this.painelFiltro.cmbSituacaoRateio.selectedIndex = 0;
			this.painelFiltro.cmbTipoData.selectedIndex = 0;
			
			painelFiltro.dataInicio.selectedDate = null;
			painelFiltro.dataFim.selectedDate = null;
			
			painelFiltro.dataInicio.compDate.text = "";
			painelFiltro.dataFim.compDate.text = "";
			
			this.btnImprimir.enabled = false;
			this.btnDetalhar.enabled = false;
			
			limparDadosPesquisa();
			
			this.gridRateioTarifas.grdDados.dataProvider = null;
			gridRateioTarifas.barraPaginacao.totalPaginas = gridRateioTarifas.barraPaginacao.pagina = 0;
			gridRateioTarifas.paginaAtual = 1;
			desabilitaDetalhar();
			desabilitaImprimir();
		}
		
		public function limparGrid(evt:Event=null):void {
			desabilitaDetalhar();
			desabilitaImprimir();
			this.gridRateioTarifas.limparConteudo();
			gridRateioTarifas.barraPaginacao.totalPaginas = gridRateioTarifas.barraPaginacao.pagina = 0;
			gridRateioTarifas.paginaAtual = 1;
			this.gridRateioTarifas.grdDados.dataProvider = null;
		}
		
		//--------------------------------------------------------------------------
		//  Instanci DTO de Consulta para Paginação.
		//--------------------------------------------------------------------------
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		
		//--------------------------------------------------------------------------
		//  Configura o DTO para executar a pesquisa.
		//--------------------------------------------------------------------------
		private function configurarDtoConsulta(dto:ConsultaDto):void {	
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var rateioDto:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO();
			
			if (this.painelFiltro.cmbSituacaoRateio.selectedItem) {
				rateioDto.codSituacaoRateio = painelFiltro.cmbSituacaoRateio.selectedItem.codSituacaoRateio;
			}
			
			if (this.painelFiltro.cmbTipoData.selectedItem) {
				rateioDto.codTipoDataEvento = painelFiltro.cmbTipoData.selectedItem.data;
			}
			
			if (this.painelFiltro.dataInicio.selectedDate) {
				rateioDto.dataInicio = DateTimeBase.getDateTime(painelFiltro.dataInicio.selectedDate);
			}
			
			if (this.painelFiltro.dataFim.selectedDate) {
				rateioDto.dataFim = DateTimeBase.getDateTime(painelFiltro.dataFim.selectedDate);
			}
			
			rateioDto.selecionadoTodos = this.selecionarTodos;
			
			
			reqDto.dados.dto = rateioDto;
			dto.filtro = reqDto;
		}
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
		//--------------------------------------------------------------------------
		//  CallBack Edição ou Inclusão.
		//--------------------------------------------------------------------------
		private function refazerPequisa(e:Event = null):void {
			gridRateioTarifas.grdDados.selectedItem = null;
			tratarBotoesAcao(null);
			this.painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
			limparGrid(null);
			painelListaBanco.procuraSolicitada(new ProcurarEvent);
		}
		
		//--------------------------------------------------------------------------
		//  Tratar botões de ação.
		//--------------------------------------------------------------------------
		private function tratarBotoesAcao(event:MouseEvent =null):void {
			if (gridRateioTarifas.grdDados.selectedItem) {
				habilitaDetalhar();
				habilitaImprimir();
			} else {
				desabilitaDetalhar();
				desabilitaImprimir();
			}
		}
		
		private function tratarBotoesAcaoCheckBox(selecionado:Boolean):void {
			if (selecionado && gridRateioTarifas.grdDados.dataProvider.length > 0) {
				habilitaDetalhar();
			} else {
				desabilitaDetalhar();
			}
		}
		
		//--------------------------------------------------------------------------
		//  Habilita e Desabilita os campos do lado da tabela.
		//--------------------------------------------------------------------------
		private function habilitaDetalhar():void {
			this.btnDetalhar.enabled = true;
		}
		
		private function desabilitaDetalhar():void {
			this.btnDetalhar.enabled = false;
		}
		
		private function habilitaImprimir():void {
			this.btnImprimir.enabled = true;
		}
		
		private function desabilitaImprimir():void {
			this.btnImprimir.enabled = false;
		}
		
		private function tratarBtnExecutar(e:Event = null):void {
			if (verificaSeHaAlgumItemSelecionado()) {
				btnDetalhar.enabled = true;
			} else {
				btnDetalhar.enabled = false;
			}
		}
		
		//--------------------------------------------------------------------------
		//  Habilita e Desabilita os campos do lado da tabela.
		//--------------------------------------------------------------------------		
		
		private var _checkBoxHeaderTodos:CheckBox;
		private var _visualizarRateioDto:ArrayCollection = new ArrayCollection();
		
		public function definirHeaderCheckBox(cb:Object):void {
			if (cb is CheckBox) {
				_checkBoxHeaderTodos = (cb as CheckBox);
			}
		}
		
		public function tratarSelecionar(dados:Object, selecionado:Boolean):void {
			dados.selecionado = selecionado;
			
			if (!selecionado) {
				selecionarTodos = false;
				
				if (_checkBoxHeaderTodos) {
					_checkBoxHeaderTodos.selected = false;
				}
			} 
			tratarBotoesAcaoCheckBox(verificaSeHaAlgumItemSelecionado());  
		}
		
		public function tratarSelecionarTodos():void {
			selecionarTodos = !selecionarTodos;
			for each(var rateio:Object in  _listaCallBack) {
				rateio.selecionado = selecionarTodos;
				this._listaCallBack.itemUpdated(rateio);
			}
			tratarBotoesAcaoCheckBox(selecionarTodos);
		}
		
		private function verificaSeTodosItensSelecionados():Boolean {
			for each(var rateio:Object in  gridRateioTarifas.grdDados.dataProvider) {
				if(!rateio.selecionado) {
					return true;
				}
			}
			return false;
		}
		
		private function verificaSeHaAlgumItemSelecionado():Boolean {
			for each(var rateio:Object in  gridRateioTarifas.grdDados.dataProvider) {
				if(rateio.selecionado) {
					return true;
				}
			}
			return false;
		}
		
	}
}