package
{
	import flash.display.DisplayObject;
	import flash.events.ContextMenuEvent;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.system.System;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuItem;
	
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.containers.EventoJanela;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tratamento.DetalharTratamentoContingencia;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tratamento.DetalharTratamentoPendencia;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tratamento.PainelTratamentoPendenciaErroView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class PainelTratamentoPendenciaErro extends PainelTratamentoPendenciaErroView
	{
		private var _bolQtdErroValido:Boolean;
		private var _itemCopy:ContextMenuItem = new ContextMenuItem("Copiar");
		private var _menuGridMensagem:ContextMenu = new ContextMenu();
		
		public function PainelTratamentoPendenciaErro()	{
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected function init(event:FlexEvent):void {
			initBotoes();
			initGrid();
			listarTratamentoPendenciaErro();
			menuContexto();
		}
		
		private function menuContexto():void {
			_itemCopy.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, menuContextoCopiar);
			_menuGridMensagem.customItems.push(_itemCopy);
			gridErrosAgrupados.contextMenu = _menuGridMensagem;
		}
		
		private function validaContexto(e:MouseEvent):void {
			if(gridErrosAgrupados.selectedItem){
				_itemCopy.enabled = true;
			}
		}
		
		private function menuContextoCopiar(e:ContextMenuEvent):void {
			if(gridErrosAgrupados.selectedItem){
				System.setClipboard(gridErrosAgrupados.selectedItem.erroPrincipal);
			}
		}
		
		private function initBotoes():void {
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			btnAtualizar.addEventListener(MouseEvent.CLICK, listarTratamentoPendenciaErro);
			btnExecutarAutomatizado.addEventListener(MouseEvent.CLICK, executarTratamentoAutomatizado);
		}

		private function executarTratamentoAutomatizado(e:MouseEvent):void {
			var metodo:String = "executarTratamentoAutomatizadoMensagem";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoExecutarTratamento);
			servico.mensagemEspera = "Executando tratamento automatizado...";
			servico.executarTratamentoAutomatizadoMensagem();
		}
		
		private function retornoExecutarTratamento(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.MSG026);
			listarTratamentoPendenciaErro();
		}
		
		private function initGrid():void {
			this.gridPendencia.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, detalharTratamentoMensagem);
			this.gridErrosAgrupados.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, tratarErrosAgrupados);
			this.gridErrosProcessamento.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, tratarErrosRetornoProcessamentoCIP);
			this.gridErrosAgrupados.addEventListener(MouseEvent.CLICK, validaContexto);
		}
		
		private function tratarErrosAgrupados(e:ListEvent):void {
			var tela:DetalharTratamentoPendencia = new DetalharTratamentoPendencia(null, DetalharTratamentoPendencia.MODO_ERRO_AGRUPADO, null, DetalharTratamentoPendencia.STATUS_ERRO_AGRUPADO , e.target.selectedItem.erroPrincipal);
			abrirJanelaDetalharTratamentoMensagem("Tratamento de Mensagens Erros Agrupados", tela);
		}
		
		private function tratarErrosRetornoProcessamentoCIP(e:ListEvent):void {
			var tipoMsg:String = e.target.selectedItem.tipoErro;
			var dataMovimento:IDateTime = e.target.selectedItem.dataMovimento;
			var tela:DisplayObject;
			tela = new DetalharTratamentoContingencia(dataMovimento, tipoMsg);
			abrirJanelaDetalharTratamentoMensagem("Erros de Processamento Retorno CIP - " + tipoMsg, tela);
		}
		
		private function detalharTratamentoMensagem(e:ListEvent):void {
			var titulo:String = obtemTituloTelaDetalharTratamentoPendencia((e.columnIndex - 1), e.target.selectedItem);
			if (titulo && _bolQtdErroValido) {
				var dataMovimento:IDateTime = e.target.selectedItem.dataMovimento;
				var codTipoMensagemDDA:String = e.target.selectedItem.codTipoMensagemDDA;
				var tela:DetalharTratamentoPendencia = new DetalharTratamentoPendencia(codTipoMensagemDDA, DetalharTratamentoPendencia.MODO_PENDENCIA,dataMovimento, (e.columnIndex - 1));
				abrirJanelaDetalharTratamentoMensagem(titulo, tela);
			}
		}
		
		private function abrirJanelaDetalharTratamentoMensagem(titulo:String, tela:DisplayObject):void {
			var janela:Janela = new Janela();
			janela.icone = ConstantesComum.ICONE_JANELA;
			janela.title = titulo;
			janela.addChild(tela); 
			janela.janelaPai = this;
			janela.addEventListener(CloseEvent.CLOSE, listarTratamentoPendenciaErro);
			janela.addEventListener(EventoJanela.FECHAR_JANELA, listarTratamentoPendenciaErro);
			janela.abrir(this, true);
			janela.centralizarJanela();
		}
		
		private function obtemTituloTelaDetalharTratamentoPendencia(statusPendencia:Number, obj:Object):String {
			switch(statusPendencia) {
				case 1:	{
					_bolQtdErroValido = (obj.qtdEnviar > 0);
					return "Tratamento de Mensagens a Enviar";
				}
				case 2:	{
					_bolQtdErroValido = (obj.qtdSemRetornoSSPB > 0);
					return "Tratamento de Mensagens Sem Retorno SSPB";
				}
				case 3:	{
					_bolQtdErroValido = (obj.qtdSemRetornoCIP > 0);
					return "Tratamento de Mensagens Sem Retorno CIP";
				}	
				case 4:	{
					_bolQtdErroValido = (obj.qtdRetornoErro > 0);
					return "Tratamento de Mensagens Retorno com Erro";
				}
				default: {
					return null
				}
			}
		}
		
		private function listarTratamentoPendenciaErro(e:Event = null):void {
			var metodo:String = "listarTratamentoPendenciaErro";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoListarPendenciaErro);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.listarTratamentoPendenciaErro();
			_itemCopy.enabled = false;
		}
		
		private function retornoListarPendenciaErro(e:ResultEvent):void {
			this.gridPendencia.dataProvider = e.result.dados.tratamento.listaPendencia;
			this.gridErrosAgrupados.dataProvider = e.result.dados.tratamento.listaErroAgrupado;
			this.gridErrosProcessamento.dataProvider = e.result.dados.tratamento.listaErroProcessamentoCip;
		}
		
		private function fechar(e:MouseEvent):void {
			this.fecharJanela();
		}
			
	}
}