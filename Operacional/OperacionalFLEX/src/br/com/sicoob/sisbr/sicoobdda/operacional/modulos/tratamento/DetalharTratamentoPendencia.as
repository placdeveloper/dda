package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tratamento
{
	import flash.events.ContextMenuEvent;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.system.System;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuItem;
	
	import mx.collections.ArrayCollection;
	import mx.controls.CheckBox;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.containers.EventoJanela;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.TratamentoMesagemDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.MensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoMensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class DetalharTratamentoPendencia extends DetalharTratamentoPendenciaView {
		
		private var _dataMovimento:IDateTime;
		private var _codErro:String; 
		private var _statusPendencia:Number;
		private var _listaMensagemDto:ArrayCollection;
		private var _modo:Number;
		private var _checkBoxHeaderTodos:CheckBox;
		private var _codTipoMensagemDDA:String;
		
		private var _listaIdMensagem:ArrayCollection;
		private var _listaIdErroMensagem:ArrayCollection;
		private var _listaIdLogRecebimento:ArrayCollection;
		
		private static const STATUS_A_ENVIAR:Number = 1;
		private static const STATUS_SEM_RETORNO_SSP:Number = 2;
		private static const STATUS_SEM_RETORNO_CIP:Number = 3;
		private static const STATUS_RETORNO_COM_ERRO:Number = 4;
		public static const STATUS_ERRO_AGRUPADO:Number = 5;
		

		private static const ERRO_CONTINGENCIA:Boolean = false;
		private static const ERRO_ARQUIVO:Boolean = true;
		
		public static const MODO_PENDENCIA:Number = 0;
		public static const MODO_ERRO_AGRUPADO:Number = 1;
		public static const MODO_RETORNO_CIP:Number = 2;
		
		private static const MAX_RESULT_DEFAULT:Number = 1000;
		
		private var _itemCopy:ContextMenuItem = new ContextMenuItem("Copiar");
		private var _menuGridMensagem:ContextMenu = new ContextMenu();
		
		public function DetalharTratamentoPendencia(codTipoMsgDDA:String, modo:Number, dataMovimento:IDateTime, statusPendencia:Number = 0, codErro:String = "")	{
			super();
			_dataMovimento = dataMovimento;
			_statusPendencia = statusPendencia;
			_modo = modo;
			_codErro = codErro;
			_codTipoMensagemDDA = codTipoMsgDDA;
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		public function definirHeaderCheckBox(cb:Object):void {
			if (cb is CheckBox) {
				_checkBoxHeaderTodos = (cb as CheckBox);
			}
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
			initBotoes();
		}

		private function initCampos():void {
			if (_dataMovimento) {
				txtDataMovimento.text = FormataData.formataData(_dataMovimento.data);
			}
			txtCodErro.text = _codErro;
			txtMaxResults.text = ""+MAX_RESULT_DEFAULT;
			tratarDadosTratamento();
			gridMensagens.addEventListener(MouseEvent.CLICK, validaContexto);
			menuContexto();
		}
		
		private function menuContexto():void {
			_itemCopy.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, menuContextoCopiar);
			_menuGridMensagem.customItems.push(_itemCopy);
			gridMensagens.contextMenu = _menuGridMensagem;
		}
		
		private function validaContexto(e:MouseEvent):void {
			if(gridMensagens.selectedItem){
				_itemCopy.enabled = true;
			}
		}
		
		private function menuContextoCopiar(e:ContextMenuEvent):void {
			if(gridMensagens.selectedItem){
				System.setClipboard(gridMensagens.selectedItem.idMensagem);
			}
		}
		private function tratarDadosTratamento(retornoTratamento:Boolean = false):void {
			switch(_modo)
			{
				case MODO_PENDENCIA: {
					if (!retornoTratamento) {
						ativarModoPendencia();
					}
					obterDadosTratamentoMensagemPendencia();
					break;
				}
				case MODO_ERRO_AGRUPADO: {
					if (!retornoTratamento) {
						ativarModoErroAgrupado();
					}
					obterDadosTratamentoMensagemErroAgrupado();
					break;
				}
				case MODO_RETORNO_CIP: {
					if (!retornoTratamento) {
						ativarModoRetornoCIP();
					}
					obterDadosTratamentoArquivoErroRetornoCIP();
					break;
				}
				default: {
					break;
				}
			}
		}
		
		private function ativarModoPendencia():void {
			desabilitaCodErro();
			desabilitarbtnDetalharMensagem();
			desabilitarColDescNomeArquivoRecebido();
			desabilitarColDescErroProtocolo();
			if (_statusPendencia == STATUS_A_ENVIAR) {
				desabilitarColCodTipoErroCIP();
				desabilitarColDescTipoErroCIP();
				desabilitarColDescErroProtocolo();
				desabilitarColDataHoraMensagem();
			} 
			btnAtualizar.addEventListener(MouseEvent.CLICK, obterDadosTratamentoMensagemPendencia);
		}
		
		private function ativarModoErroAgrupado():void {
			desabilitaDataMovimento();
			desabilitarbtnDetalharMensagem();
			desabilitarColDescNomeArquivoRecebido();
			desabilitarColDescErroProtocolo();
			btnAtualizar.addEventListener(MouseEvent.CLICK, obterDadosTratamentoMensagemErroAgrupado);
		}
		
		private function ativarModoRetornoCIP():void {
			desabilitarbtnDetalharMensagem();
			desabilitarColDescErroProtocolo();
			desabilitarColCodTipoMensagemDDA();
			desabilitarColIdMensagem();
			
			desabilitaCodErro();
			desabilitaStatusRetorno();
			desabilitarColErroProcessamentoRetorno();
			gridMensagens.addEventListener(ListEvent.ITEM_CLICK, tratarBtnDetalharMensagem);
		}
		
		private function initBotoes():void {
			cmbTratamento.addEventListener(Event.RENDER, tratarBtnExecutar);
			btnExecutar.addEventListener(MouseEvent.CLICK, executarTratamento);
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			btnDetalharMensagem.addEventListener(MouseEvent.CLICK, detalharMensagemErro);
			btnExcluir.addEventListener(MouseEvent.CLICK, confirmaExcluir);
		}
		
		private function tratarSelecionadoErroArquivo(e:MouseEvent):void {
			obterDadosTratamentoArquivoErroRetornoCIP();
		}
		
		private function tratarBtnDetalharMensagem(e:ListEvent):void {
			if (gridMensagens.selectedItem) {
				btnDetalharMensagem.enabled = true;
			}
		}
			
		private function detalharMensagemErro(e:MouseEvent):void {
			var tela:DetalharErro = new DetalharErro(_dataMovimento, gridMensagens.selectedItem.idMensagem, funcaoRetorno);
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
		
		private function funcaoRetorno():void {
			_itemCopy.enabled = false;
			btnDetalharMensagem.enabled = false;
		}
		private function retornoDetalharMensagem(e:Event = null):void {
			tratarDadosTratamento(true);
		}
		
		private function obterDadosTratamentoMensagemPendencia(e:MouseEvent = null):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["dataMovimento"] = _dataMovimento;
			dto.dados["codSituacao"] = _statusPendencia;
			dto.dados["codTipoMensagemDDA"] = _codTipoMensagemDDA;
			dto.dados["maxResult"] = obterMaxResult();
			var metodo:String = "obterDadosTratamentoMensagemPendencia";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoListarPendenciaErro);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.obterDadosTratamentoMensagemPendencia(dto);
		}
		
		private function obterDadosTratamentoMensagemErroAgrupado(e:MouseEvent = null):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["codErro"] = _codErro;
			dto.dados["maxResult"] = obterMaxResult();
			var metodo:String = "obterDadosTratamentoMensagemErroAgrupado";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoListarPendenciaErro);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.obterDadosTratamentoMensagemErroAgrupado(dto);
		}
		
		private function obterDadosTratamentoArquivoErroRetornoCIP():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["dataMovimento"] = _dataMovimento;
			dto.dados["maxResult"] = obterMaxResult();
			var metodo:String = "obterDadosTratamentoArquivoErroRetornoCIP";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoListarPendenciaErro);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.obterDadosTratamentoArquivoErroRetornoCIP(dto);
		}
		
		private function obterMaxResult():Number {
			if (!txtMaxResults.valor) {
				txtMaxResults.text = ""+0;
			}
			return txtMaxResults.valor;
		}
		
		private function retornoListarPendenciaErro(e:ResultEvent):void {
			gridMensagens.dataProvider = _listaMensagemDto = e.result.dados.dadosTratamento.listaMensagens;
			if (_listaMensagemDto.length > 0) {
				txtStatusRetorno.text = _listaMensagemDto.getItemAt(0).descSituacaoMensagemDDA;
				if(txtStatusRetorno.text == 'A ENVIAR'){
					btnExcluir.visible = true;
					btnExcluir.includeInLayout = true;
					btnExcluir.enabled = true;
				}
			}
			_checkBoxHeaderTodos.selected = false;
			cmbTratamento.dataProvider = e.result.dados.dadosTratamento.listaTipoTratamento;
			cmbTratamento.selectedIndex = 0;
			cmbTratamento.enabled = true;
			_itemCopy.enabled = false;
		}
		
		private function confirmaExcluir(e:MouseEvent):void {
			if (verificaSeHaAlgumItemSelecionado()){
				MensagensComum.exibirMensagemConfirmacao(MensagensComum.formatar(MensagensComum.MSG029, "mensagens"), btnExcluir, excluirMensagem);
			} else {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG012, "Mensagem"));
			}
		}
		
		private function excluirMensagem(evt:MouseEvent):void{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var listaMensagemDDA:ArrayCollection = new ArrayCollection;
			
			for each(var grid:Object in  gridMensagens.dataProvider) {
				if(grid.selecionado && grid.idMensagem != null && grid.codTipoMensagemDDA != null) {
					var mensagemDDA:MensagemDDAVO = new MensagemDDAVO;
					mensagemDDA.id = grid.idMensagem;
					mensagemDDA.tipoMensagemDDA = new TipoMensagemDDAVO;
					mensagemDDA.tipoMensagemDDA.codTipoMensagem = grid.codTipoMensagemDDA;
					listaMensagemDDA.addItem(mensagemDDA);
				}
			}
			
			dto.dados["listaMensagemDDA"] = listaMensagemDDA;
			var metodo:String = "excluirListaMensagemErro";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoExcluir);
			servico.mensagemEspera = "Excluindo Mensagem...";
			servico.excluirListaMensagemErro(dto);
		}
		
		private function resultadoExcluir(result:ResultEvent):void{
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG028, "Mensagem"));
			_itemCopy.enabled = false;
			tratarDadosTratamento(true);
		}
		
		private function executarTratamento(e:MouseEvent):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["tratamento"] = preparaTratamentoMensagemDTO();
			var metodo:String = "executarTratamentoMensagem";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoExecutarTratamento);
			servico.mensagemEspera = "Executando tratamento...";
			servico.executarTratamentoMensagem(dto);
		}
		
		private function preparaTratamentoMensagemDTO():TratamentoMesagemDTO {
			var tratamento:TratamentoMesagemDTO = new TratamentoMesagemDTO();
			tratamento.codTipoTratamento = cmbTratamento.selectedItem.codTipoTratamentoErroCip;
			preparaListaIds();
			tratamento.listaIdMensagem = _listaIdMensagem;
			tratamento.listaIdErroMensagem = _listaIdErroMensagem;
			tratamento.listaIdLogRecebimento = _listaIdLogRecebimento;
			return tratamento;
		}
		
		private function preparaListaIds():void {
			limparListas();
			for each(var mensagem:Object in  gridMensagens.dataProvider) {
				if(mensagem.selecionado) {
					if (mensagem.idMensagem) {
						if (!_listaIdMensagem) {
							_listaIdMensagem = new ArrayCollection();
						}
						_listaIdMensagem.addItem(mensagem.idMensagem);
					}
					
					if (mensagem.idErroMensagemRetornoCIP) {
						if (!_listaIdErroMensagem) {
							_listaIdErroMensagem = new ArrayCollection();
						}
						_listaIdErroMensagem.addItem(mensagem.idErroMensagemRetornoCIP);
					}
					
					if (mensagem.idLogRecebimentoArquivoDDA) {
						if (!_listaIdLogRecebimento) {
							_listaIdLogRecebimento = new ArrayCollection();
						}
						_listaIdLogRecebimento.addItem(mensagem.idLogRecebimentoArquivoDDA);
					}
				}
			}
		}
		
		private function retornoExecutarTratamento(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.MSG026);
			tratarDadosTratamento(true);
			_itemCopy.enabled = false;
			if (_checkBoxHeaderTodos) {
				_checkBoxHeaderTodos.selected = false;
			}
		}
		
		private function limparListas():void {
			if (_listaIdMensagem) {
				_listaIdMensagem.removeAll();
			}
			if (_listaIdErroMensagem) {
				_listaIdErroMensagem.removeAll();
			}
			if (_listaIdLogRecebimento) {
				_listaIdLogRecebimento.removeAll();
			}
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
		
		public function tratarSelecionarTodos():void {
			selecionarTodos = !selecionarTodos;
			
			for each(var mensagem:Object in  _listaMensagemDto) {
				mensagem.selecionado = selecionarTodos;
				_listaMensagemDto.itemUpdated(mensagem);
			}
			tratarBtnExecutar();
		}
		
		private function verificaSeHaAlgumItemSelecionado():Boolean {
			for each(var mensagem:Object in  gridMensagens.dataProvider) {
				if(mensagem.selecionado) {
					return true;
				}
			}
			return false;
		}
		
		private function verificaSeTodosItensSelecionados():Boolean {
			for each(var mensagem:Object in  gridMensagens.dataProvider) {
				if(!mensagem.selecionado) {
					return true;
				}
			}
			return false;
		}
		
		private function desabilitarColDescNomeArquivoRecebido():void {
			colDescNomeArquivoRecebido.visible = false;
			colDescNomeArquivoRecebido.width = 0;
		}
		
		private function desabilitarColDescErroProtocolo():void {
			colDescErroProtocolo.visible = false;
			colDescErroProtocolo.width = 0;
		}
		
		private function desabilitarColDataHoraMensagem():void {
			colDataHoraMensagem.visible = false;
			colDataHoraMensagem.width = 0;
		}
		
		private function desabilitarColErroProcessamentoRetorno():void {
			colTipoDoc.visible = false;
			colTipoDoc.width = 0;
			colIdentificador.visible = false;
			colIdentificador.width = 0;
		}
		
		private function desabilitarColIdMensagem():void {
			colIdMensagem.visible = false;
			colIdMensagem.width = 0;
		}
		
		private function desabilitarColCodTipoErroCIP():void {
			colCodTipoErroCIP.visible = false;
			colCodTipoErroCIP.width = 0;
		}
		
		private function desabilitarColDescTipoErroCIP():void {
			colDescTipoErroCIP.visible = false;
			colDescTipoErroCIP.width = 0;
		}
		
		private function desabilitarColCodTipoMensagemDDA():void {
			colCodTipoMensagemDDA.visible = false;
			colCodTipoMensagemDDA.width = 0;
		}
		
		
		private function desabilitaCodErro():void {
			labelCodErro.visible = false;
			txtCodErro.visible = false;
		}
		
		
		private function desabilitaDataMovimento():void {
			labelDataMovimento.visible = false;
			txtDataMovimento.visible = false;
		}
		
		
		private function desabilitaStatusRetorno():void {
			labelStatusRetorno.visible = false;
			txtStatusRetorno.visible = false;
		}

		private function desabilitarbtnDetalharMensagem():void {
			btnDetalharMensagem.visible = false;
			btnDetalharMensagem.enabled = false;
		}
		
		private function fechar(e:MouseEvent):void {
			this.fecharJanela();
		}
	}
}