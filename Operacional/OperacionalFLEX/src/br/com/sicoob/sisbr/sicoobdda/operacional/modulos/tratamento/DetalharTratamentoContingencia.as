package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tratamento
{
	import flash.events.ContextMenuEvent;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.system.System;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuItem;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.controls.CheckBox;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.containers.EventoJanela;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.TratamentoMesagemDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class DetalharTratamentoContingencia extends DetalharTratamentoContingenciaView {
		
		private var _dataMovimento:IDateTime;
		private var _listaMensagemDto:ArrayCollection;
		private var _checkBoxHeaderTodos:CheckBox;
		private var _bolErroArquivo:Boolean;
		
		private static const ERRO_CONTINGENCIA:Boolean = false;
		private static const ERRO_ARQUIVO:Boolean = true;
		
		private var _itemCopy:ContextMenuItem = new ContextMenuItem("Copiar");
		private var _menuGridMensagem:ContextMenu = new ContextMenu();
		private var _codTipoMensagem:String;
		
		public function DetalharTratamentoContingencia(dataMovimento:IDateTime, tipoMsg:String)	{
			super();
			_dataMovimento = dataMovimento;
			_codTipoMensagem = tipoMsg;
		}
		
		public function definirHeaderCheckBox(cb:Object):void {
			if (cb is CheckBox) {
				_checkBoxHeaderTodos = (cb as CheckBox);
			}
		}
		
		protected override function init(e:FlexEvent):void {
			removeEventListener(ProcurarEvent.PROCURAR, painelListaMsg.procuraSolicitada);
			painelListaMsg.funcaoCriacaoDto = instanciarDtoConsulta;
			super.init(e);
			initCampos();
			initBotoes();
		}
		
		private function initCampos():void {
			txtDataMovimento.text = FormataData.formataData(_dataMovimento.data);
			obterDadosTratamentoMensagemErroRetornoCIP();
			listaMensagens.grdDados.addEventListener(MouseEvent.CLICK, validaContexto);
			menuContexto();
		}
		
		private function menuContexto():void {
			_itemCopy.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, menuContextoCopiar);
			_menuGridMensagem.customItems.push(_itemCopy);
			listaMensagens.grdDados.contextMenu = _menuGridMensagem;
		}
		
		private function validaContexto(e:MouseEvent):void {
			if(listaMensagens.grdDados.selectedItem){
				_itemCopy.enabled = true;
			}
		}
		
		private function menuContextoCopiar(e:ContextMenuEvent):void {
			if(listaMensagens.grdDados.selectedItem){
				System.setClipboard(listaMensagens.grdDados.selectedItem.idMensagem);
			}
		}
		
		private function initBotoes():void {
			barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoExcluir = barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = false;
			var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			(hBoxBarraBotoes.getChildByName('btnVisualizar') as Botao).label = "DETALHAR";
			
			cmbTratamento.addEventListener(Event.RENDER, tratarBtnExecutar);
			btnExecutar.addEventListener(MouseEvent.CLICK, executarTratamento);
		}
		
		protected override function botaoVisualizarPressionado():void {
			var tela:DetalharErro = new DetalharErro(_dataMovimento, listaMensagens.grdDados.selectedItem.idMensagem, funcaoRetorno);
			var janela:Janela = new Janela();
			janela.icone = ConstantesComum.ICONE_JANELA;
			janela.title = "Detalhamento do Erro";
			janela.addChild(tela); 
			janela.addEventListener(CloseEvent.CLOSE, retornoDetalharMensagem);
			janela.addEventListener(EventoJanela.FECHAR_JANELA, retornoDetalharMensagem);
			janela.janelaPai = this;
			janela.abrir(this, true);
			janela.centralizarJanela();
			
		}
		
		private function retornoDetalharMensagem(e:Event):void {
			selecionarTodos = true;
			if (_checkBoxHeaderTodos) {
				_checkBoxHeaderTodos.selected = false;
			}
			listarMensagensContingencia();
			tratarSelecionarTodos();
		}
		
		private function funcaoRetorno():void {
			listarMensagensContingencia();
			_itemCopy.enabled = false;
		}
		
		private function obterDadosTratamentoMensagemErroRetornoCIP():void {
			var metodo:String = "obterDadosTratamentoMensagemErroRetornoCIP";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoObterDadosTratamento);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.obterDadosTratamentoMensagemErroRetornoCIP();
		}
		
		private function retornoObterDadosTratamento(e:ResultEvent):void {
			if (_checkBoxHeaderTodos) {
				_checkBoxHeaderTodos.selected = false;
			}
			cmbTratamento.dataProvider = e.result.dados.listaTratamento;
			cmbTratamento.selectedIndex = 0;
			cmbTratamento.enabled = true;
			_itemCopy.enabled = false;
			listarMensagensContingencia();
		}
		
		private function executarTratamento(e:MouseEvent):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["tratamento"] = preparaTratamentoMensagemDTO();
			var metodo:String = "executarTratamentoMensagem";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoExecutarTratamento);
			servico.addEventListener(FaultEvent.FAULT, retornoFalhaExecutarTratamento);
			servico.mensagemEspera = "Executando tratamento...";
			servico.executarTratamentoMensagem(dto);
		}
		
		private function preparaTratamentoMensagemDTO():TratamentoMesagemDTO {
			var tratamento:TratamentoMesagemDTO = new TratamentoMesagemDTO();
			tratamento.codTipoTratamento = cmbTratamento.selectedItem.codTipoTratamentoErroCip;
			
			tratamento.listaIdMensagem = preparaListaIds();
			return tratamento;
		}
		
		private function preparaListaIds():ArrayCollection {
			var listaIdMensagem:ArrayCollection = new ArrayCollection();
			for each(var mensagem:Object in  listaMensagens.grdDados.dataProvider) {
				if(mensagem.selecionado) {
					listaIdMensagem.addItem(mensagem.idMensagem);
				}
			}
			return listaIdMensagem;
		}
		
		private function retornoExecutarTratamento(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.MSG026);
			_itemCopy.enabled = false;
			listarMensagensContingencia();
		}
		
		
		private function retornoFalhaExecutarTratamento(e:FaultEvent):void {
			_itemCopy.enabled = false;
			listarMensagensContingencia();
		}
		
		private function tratarBtnExecutar(e:Event = null):void {
			if (cmbTratamento.selectedItem && verificaSeHaAlgumItemSelecionado()) {
				btnExecutar.enabled = true;
			} else {
				btnExecutar.enabled = false;
			}
		}
		
		public function tratarSelecionar(dados:Object, selecionado:Boolean):void {
			dados.selecionado = selecionado;
			
			if (!selecionado) {
				selecionarTodos = false;
				
				if (_checkBoxHeaderTodos) {
					_checkBoxHeaderTodos.selected = false;
				}
			} else if (!verificaSeTodosItensSelecionados()) {
				selecionarTodos = true;
				
				if (_checkBoxHeaderTodos) {
					_checkBoxHeaderTodos.selected = true;
				}
			}
			tratarBtnExecutar();  
		}
		
		public function tratarSelecionarTodos(c:CheckBox = null):void {
			if(c && c.selected){
				selecionarTodos = !selecionarTodos;
			}else{
				selecionarTodos = false;
			}
			
			for each(var mensagem:Object in listaMensagens.grdDados.dataProvider) {
				mensagem.selecionado = selecionarTodos;
				(listaMensagens.grdDados.dataProvider as ArrayCollection ).itemUpdated(mensagem);
			}
			tratarBtnExecutar();
		}
		
		private function verificaSeHaAlgumItemSelecionado():Boolean {
			for each(var mensagem:Object in  listaMensagens.grdDados.dataProvider) {
				if(mensagem.selecionado) {
					return true;
				}
			}
			return false;
		}
		
		private function verificaSeTodosItensSelecionados():Boolean {
			for each(var mensagem:Object in  listaMensagens.grdDados.dataProvider) {
				if(!mensagem.selecionado) {
					return true;
				}
			}
			return false;
		}
		
		private function fechar(e:MouseEvent):void {
			this.fecharJanela();
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
			reqDto.dados.codTipoMensagemDDA = _codTipoMensagem;
			reqDto.dados.dataMovimento = _dataMovimento;
			selecionarTodos = false;
			dto.filtro = reqDto;
		}
		
		private function listarMensagensContingencia():void {
			painelListaMsg.funcaoConfiguracaoDto = configurarDtoConsulta;
			painelListaMsg.procuraSolicitada(new ProcurarEvent());	
		}
	}
}