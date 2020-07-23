package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.rateiotarifas {
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.controls.CheckBox;
	import mx.controls.TextInput;
	import mx.events.AdvancedDataGridEvent;
	import mx.events.DataGridEvent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.EventoRateioDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.NumeroUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RateioDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	public class TabelaEventoRateio extends TabelaEventoRateioView {
		
		private var checkBoxHeader:CheckBox;

		private var lista:ArrayCollection;
		
		private var _funcaoAtualizarRateio:Function;
		private var _funcaoAtualizarListaSelecionados:Function;
		private var _funcaoImprimir:Function;
		
		public function TabelaEventoRateio() {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			grid.addEventListener(AdvancedDataGridEvent.ITEM_EDIT_BEGIN, tratarColunaEdicao);
			grid.addEventListener(AdvancedDataGridEvent.ITEM_EDIT_END, tratarColunaEditada);
			
			btnIncluirRateio.addEventListener(MouseEvent.CLICK, incluirRateio);
			btnRemoverRateio.addEventListener(MouseEvent.CLICK, validarRemoverRateio);
			
			btnImprimir.addEventListener(MouseEvent.CLICK, imprimir);
			btnImprimir.setStyle("icon", ConstantesComum.icoImprimir);
			
			col9.headerText = "Dif.\nQtd/Valor";
			col10.headerText = "Desvio\nQtd/Valor (+)";
			col11.headerText = "Desvio\nQtd/Valor (-)";
		}
		
		private function validarRemoverRateio(event:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao("Deseja remover o(s) evento(s) tarifável(is) do rateio?", null, removerRateio);
		}
		
		private function removerRateio(evt:Event):void {
			var metodo:String = "removerEventoConsolidadoRateio";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["lista"] = obterSelecionados();
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoRemoverEventoConsolidadoRateio);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.removerEventoConsolidadoRateio(dto);
		}
		
		private function resultadoRemoverEventoConsolidadoRateio(event:ResultEvent):void {
			atualizarListaSelecionados(obterSelecionados());
			
			removerSelecionados();
			
			checkBoxHeader.selected = false;
		}
		
		private function incluirRateio(event:MouseEvent):void {
			var metodo:String = "incluirEventoConsolidadoRateio";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["lista"] = obterSelecionados();
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoIncluirEventoConsolidadoRateio);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.incluirEventoConsolidadoRateio(dto);
		}
		
		private function resultadoIncluirEventoConsolidadoRateio(event:ResultEvent):void {
			var rateio:RateioDDAVO = event.result.dados.vo as RateioDDAVO;
			
			atualizarRateio(rateio);
			atualizarListaSelecionados(obterSelecionados());
			
			removerSelecionados();
			
			checkBoxHeader.selected = false;
		}
		
		/**
		 * Trata o final da edição da coluna.
		 */
		private function tratarColunaEditada(evt:DataGridEvent):void {
			if (evt.dataField == 'qtdCIP') {
				tratarColunaValor(evt);
			}
		}
		
		/**
		 * Trata o valor informado.
		 */
		private function tratarColunaValor(evt:DataGridEvent):void {
			var novoValorStr:String;
			
			if (evt.currentTarget.itemEditorInstance is TextInput) {
				var input:TextInput = TextInput(evt.currentTarget.itemEditorInstance);
				novoValorStr = input.text;
			}
			
			var novoValor:Number;
			
			if (novoValorStr != null && novoValorStr != "") {
				novoValor = NumeroUtil.arredondarParaBaixo(NumeroUtil.converterMoedaParaNumeroDecimal(novoValorStr));
			} else {
				novoValor = 0;
			}
			
			if (!isNaN(novoValor)) {
				var dto:EventoRateioDTO = grid.editedItemRenderer.data as EventoRateioDTO;
				
				dto.qtdCIP = novoValor;
				
				grid.destroyItemEditor();
				grid.dataProvider.itemUpdated(dto);
				
				atualizarTotais();
			} else {
				grid.destroyItemEditor();
				evt.preventDefault();
			}
		}
		
		/**
		 * Verifica se a coluna não pode ser editada.
		 * BUG FIX: se clicar numa coluna na linha das mensagens ocorria um erro pois o flex permitia a edição de um objeto inválido.
		 */
		private function tratarColunaEdicao(evt:DataGridEvent):void {
			if (!(evt.itemRenderer.data is EventoRateioDTO)) {
				evt.preventDefault();
			}
		}
		
		public function definirLista(lista:ArrayCollection):void {
			this.lista = lista;
			
			grid.dataProvider = lista;
			
			atualizarTotais();
			
			atualizarBotoes();
		}
		
		private function atualizarBotoes():void {
			var habilitar:Boolean = lista && lista.length > 0;
			
			habilitarBotao(habilitar);
			habilitarHeader(habilitar);
		}
		
		public function atualizar():void {
			for each (var dto:EventoRateioDTO in lista) {
				lista.itemUpdated(dto);
			}
			
			atualizarTotais();
			
			atualizarBotoes()
		}
		
		private function atualizarTotais():void {
			var qtdTotalSicoob:Number = 0;
			var valorTotalSicoob:Number = 0;
			var qtdTotalCIP:Number = 0;
			var valorTotalCIP:Number = 0;
			
			var valorUnitarioTotal:Number = 0;
			
			if (lista) {
				for each (var dto:EventoRateioDTO in lista) {
					qtdTotalSicoob += dto.qtdApuradaSicoob;
					valorTotalSicoob += dto.valorApuradoSicoob;
					qtdTotalCIP += dto.qtdCIP;
					valorTotalCIP += dto.valorCIP;
					
					valorUnitarioTotal += dto.valorUnitarioCIP;
				}
			}
			
			lblQtdTotalSicoob.text = FormataNumero.formata(qtdTotalSicoob, 0);
			lblValorTotalSicoob.text = NumeroUtil.formatarNumeroDecimalParaMoeda(valorTotalSicoob);
			lblQtdTotalCIP.text = FormataNumero.formata(qtdTotalCIP, 0);
			lblValorTotalCIP.text = NumeroUtil.formatarNumeroDecimalParaMoeda(valorTotalCIP);
			
			var dif:Number = qtdTotalSicoob - qtdTotalCIP;
			
			lblDifQtdValor.text = formatarDifQtdTotal(dif, valorTotalSicoob - valorTotalCIP);
		}
		
		private function formatarDifQtdTotal(dif:Number, difValor:Number):String {
			return FormataNumero.formata(dif, 0) + " / " + NumeroUtil.formatarNumeroDecimalParaMoeda(difValor);
		}
		
		public function obterSelecionados():ArrayCollection {
			var listaSelecionados:ArrayCollection = new ArrayCollection();
			
			for each (var dto:EventoRateioDTO in lista) {
				if (dto.selecionado) {
					listaSelecionados.addItem(dto);
				}
			}
			
			return listaSelecionados;
		}
		
		/**
		 * Trata a seleção da linha da grid.
		 */
		public function tratarSelecionar(dados:Object, selecionado:Boolean):void {
			EventoRateioDTO(dados).selecionado = selecionado;
			
			var selecionados:Number = obterTotalSelecionados();
			
			// Só habilita o botão quando houver registro selecionado.
			habilitarBotaoItemSelecionado(selecionados > 0);
			
			if (!selecionado) {
				selecionarTodos = false;
				
				if (checkBoxHeader) {
					checkBoxHeader.selected = false;
				}
			} else if (lista && selecionados == lista.length) {
				if (checkBoxHeader) {
					checkBoxHeader.selected = true;
				}
			}
		}
		
		private function obterTotalSelecionados():Number {
			var selecionados:Number = 0;
			
			for each (var dto:EventoRateioDTO in lista) {
				if (dto.selecionado) {
					selecionados++;
				}
			}
			
			return selecionados;
		}
		
		private function habilitarBotao(habilitar:Boolean):void {
			btnImprimir.enabled = habilitar && (lista && lista.length > 0);
		}
		
		private function habilitarBotaoItemSelecionado(habilitar:Boolean):void {
			btnIncluirRateio.enabled = habilitar;
			btnRemoverRateio.enabled = habilitar;
		}
		
		/**
		 * Trata se deve selecionar ou não todas as linhas.
		 */
		public function tratarSelecionarTodos():void {
			selecionarTodos = !selecionarTodos;
			
			for each (var dto:EventoRateioDTO in lista) {
				dto.selecionado = selecionarTodos;
				
				lista.itemUpdated(dto);
			}
			
			// Só habilita o botão quando houver registro selecionado.
			habilitarBotaoItemSelecionado(selecionarTodos);
		}
		
		public function definirHeaderCheckBox(cb:Object):void {
			if (cb is CheckBox) {
				checkBoxHeader = (cb as CheckBox);
			}
		}
		
		protected function habilitarHeader(habilitar:Boolean):void {
			if (checkBoxHeader) {
				checkBoxHeader.enabled = habilitar;
			}
		}

		public function limpar():void {
			lblQtdTotalSicoob.text = lblValorTotalSicoob.text = lblQtdTotalCIP.text = lblValorTotalCIP.text = lblDifQtdValor.text = "";
			
			if (lista) {
				lista.removeAll();
			}
			
			checkBoxHeader.selected = false;
			habilitarHeader(false);
			
			habilitarBotao(false);
			habilitarBotaoItemSelecionado(false);
			
			selecionarTodos = false;
		}
		
		public function bloquearCampos(bloquearImprimir:Boolean = true):void {
			btnImprimir.enabled = !bloquearImprimir;
			checkBoxHeader.enabled = btnIncluirRateio.enabled = btnRemoverRateio.enabled = false;
			col7.editable = grid.editable = false;
			col1.visible = false;
			col1.editable = false;
			
			grid.validateNow();
			
			lblTotal.width = col2.width + col3.width + col4.width;
			
			lblQtdTotalSicoob.width = col5.width;
			lblValorTotalSicoob.width = col6.width;
			lblQtdTotalCIP.width = col7.width;
			lblValorTotalCIP.width = col8.width;
			lblDifQtdValor.width = col9.width;
			
			lblTotal.validateNow();
			lblDifQtdValor.validateNow();
			lblQtdTotalCIP.validateNow();
			lblQtdTotalSicoob.validateNow();
			lblValorTotalCIP.validateNow();
			lblValorTotalSicoob.validateNow();
		}
		
		private function removerSelecionados():void {
			for (var i:int = lista.length - 1; i >= 0; i--) {
				var dto:EventoRateioDTO = lista.getItemAt(i) as EventoRateioDTO;
				
				if (dto.selecionado) {
					dto.selecionado = false;
					
					lista.removeItemAt(i);
				}
			}
			
			atualizar();
			
			atualizarBotoes();
			
			habilitarBotaoItemSelecionado(false);
			
			selecionarTodos = false;
		}
		
		public function set funcaoAtualizarRateio(funcao:Function):void {
			_funcaoAtualizarRateio = funcao;
		}
		
		private function atualizarRateio(rateio:RateioDDAVO):void {
			if (_funcaoAtualizarRateio != null) {
				_funcaoAtualizarRateio(rateio);
			}
		}
		
		public function set funcaoAtualizarListaSelecionados(funcao:Function):void {
			_funcaoAtualizarListaSelecionados = funcao;
		}
		
		private function atualizarListaSelecionados(lista:ArrayCollection):void {
			if (_funcaoAtualizarListaSelecionados != null) {
				_funcaoAtualizarListaSelecionados(lista);
			}
		}
		
		public function set funcaoImprimir(funcao:Function):void {
			_funcaoImprimir = funcao;
		}
		
		private function imprimir(evt:Event):void {
			if (_funcaoImprimir != null) {
				_funcaoImprimir();
			}
		}
		
	}
	
}