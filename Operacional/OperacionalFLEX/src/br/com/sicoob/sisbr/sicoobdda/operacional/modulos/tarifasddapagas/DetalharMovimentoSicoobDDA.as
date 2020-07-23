package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tarifasddapagas {
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.events.TextEvent;
	import flash.events.TimerEvent;
	import flash.net.registerClassAlias;
	import flash.utils.Timer;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.componentes.input.InputCPFCNPJ;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.FormataNumero;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ConsultaMovimentoSicoobDDADTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ConsultaTarifasDDAPagasDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.LancamentosTarifasDDADTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.NumeroUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.boleto.DetalhamentoBoleto;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("RegistroVO", RegistroVO);
	public class DetalharMovimentoSicoobDDA extends DetalharMovimentoSicoobDDAView {

		private var btnImprimir:Botao = new Botao();
		
		private var consultaTarifasDTO:ConsultaTarifasDDAPagasDTO;
		private var lancamentosDTO:LancamentosTarifasDDADTO;
		private var consultaDTO:ConsultaMovimentoSicoobDDADTO = new ConsultaMovimentoSicoobDDADTO();
		
		public function DetalharMovimentoSicoobDDA(consultaTarifasDTO:ConsultaTarifasDDAPagasDTO, lancamentosDTO:LancamentosTarifasDDADTO) {
			this.consultaTarifasDTO = consultaTarifasDTO;
			this.lancamentosDTO = lancamentosDTO;
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			
			iniciarCampos();
			
			preencherCampos();
			
			definirBotoes();
			
			funcaoDuploClique = null;
			
			validarTabulacao();
			
			var t:Timer = new Timer(300, 1);
			t.addEventListener(TimerEvent.TIMER_COMPLETE, definirFoco);
			t.start();
		}
		
		private function preencherCampos():void {
			painelFiltro.lblCooperativa.text = consultaTarifasDTO.numCooperativa ? consultaTarifasDTO.numCooperativa.toString() : "";
			painelFiltro.lblDataConciliacao.text = consultaTarifasDTO.dataConciliacao ? FormataData.formataData(consultaTarifasDTO.dataConciliacao.data) : "";
			painelFiltro.lblDataEvento.text = lancamentosDTO.dataEvento ? FormataData.formataData(lancamentosDTO.dataEvento.data) : "";
			painelFiltro.lblDescEvento.text = consultaTarifasDTO.descEventoTarifavel;
			painelFiltro.lblQuantidade.text = FormataNumero.formata(lancamentosDTO.quantidadeApurada, 0);
			painelFiltro.lblValorUnitario.text = NumeroUtil.formatarNumeroDecimalParaMoeda(lancamentosDTO.valorUnitario);
			painelFiltro.lblValorTotal.text = NumeroUtil.formatarNumeroDecimalParaMoeda(lancamentosDTO.valorTotal);
		}
		
		private function iniciarCampos():void {
			barraBotoesListaCadastro.exibirBotaoFechar = true;
			barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoExcluir = false;
			
			painelFiltro.cmbTipoBeneficiario.dataProvider = ConstantesComum.CPF_CNPJ;
			painelFiltro.cmbTipoBeneficiario.addEventListener(ListEvent.CHANGE, aplicarMascaraCPFCNPJ);
			aplicarMascaraCPFCNPJ();
			
			painelListaTarifas.funcaoCriacaoDto = instanciarDtoConsulta;
			painelListaTarifas.funcaoConfiguracaoDto = configurarDtoConsulta;
			
			btnImprimir.addEventListener(MouseEvent.CLICK, imprimirRelatorio);
			
			painelFiltro.cmbTipoBeneficiario.addEventListener(ListEvent.CHANGE, limparGrid);
			painelFiltro.txtCPFCNPJ.addEventListener(Event.CHANGE, limparGrid);
			painelFiltro.txtCPFCNPJ.addEventListener(TextEvent.TEXT_INPUT, limparGrid);
			
			painelFiltro.txtNumCodBarras.txtCodBarras.addEventListener(Event.CHANGE, limparGrid);
			painelFiltro.txtNumCodBarras.txtLinhaDigitavel1.addEventListener(Event.CHANGE, limparGrid);
			painelFiltro.txtNumCodBarras.txtLinhaDigitavel2.addEventListener(Event.CHANGE, limparGrid);
			painelFiltro.txtNumCodBarras.txtLinhaDigitavel3.addEventListener(Event.CHANGE, limparGrid);
			painelFiltro.txtNumCodBarras.txtLinhaDigitavel4.addEventListener(Event.CHANGE, limparGrid);
			painelFiltro.txtNumCodBarras.txtLinhaDigitavel5.addEventListener(Event.CHANGE, limparGrid);
			
			grid.addEventListener(Event.RENDER, tratarBotao);
			
			var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			
			btnImprimir.label = "Imprimir";
			btnImprimir.toolTip = "Imprimir";
			btnImprimir.height = 22;
			
			hBoxBarraBotoes.addChildAt(btnImprimir, 1);
			
			var btnFechar:Botao = hBoxBarraBotoes.getChildByName("btnFechar") as Botao;
			
			if (btnFechar) {
				// Define o ícone
				btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			}
			
			painelListaTarifas.callback = tratarCallbackPesquisa;
		}
		
		private function aplicarMascaraCPFCNPJ(event:ListEvent = null):void {
			if (!painelFiltro.cmbTipoBeneficiario.selectedItem || painelFiltro.cmbTipoBeneficiario.selectedItem.data == ConstantesComum.CPF) {
				painelFiltro.txtCPFCNPJ.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			} else {
				painelFiltro.txtCPFCNPJ.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
			}
		}

		private function definirFoco(evt:Event = null):void {
			painelFiltro.cmbTipoBeneficiario.setFocus();
		}
		
		private function validarTabulacao():void {
			var index:int = 1;
			
			painelFiltro.cmbTipoBeneficiario.tabIndex = index++;
			painelFiltro.txtCPFCNPJ.tabIndex = index++;
			
			painelFiltro.txtNumCodBarras.txtCodBarras.tabIndex = index++;
			painelFiltro.txtNumCodBarras.txtLinhaDigitavel1.tabIndex = index++;
			painelFiltro.txtNumCodBarras.txtLinhaDigitavel2.tabIndex = index++;
			painelFiltro.txtNumCodBarras.txtLinhaDigitavel3.tabIndex = index++;
			painelFiltro.txtNumCodBarras.txtLinhaDigitavel4.tabIndex = index++;
			painelFiltro.txtNumCodBarras.txtLinhaDigitavel5.tabIndex = index++;
			
			painelFiltro.btnProcurar.tabIndex = index++;
			painelFiltro.btnLimpar.tabIndex = index++;
			
			painelLista.tabelaPaginada.grdDados.tabIndex = index++;
			btnDetalhar.tabIndex = index++;
			painelLista.tabelaPaginada.barraPaginacao.tabIndex = index++;
			
			btnImprimir.tabIndex = index++;
		}
		
		/**
		 * Define os botões.
		 */
		private function definirBotoes():void {
			painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCamposConsulta);
			
			btnImprimir.setStyle("icon", ConstantesComum.icoImprimir);
			btnImprimir.addEventListener(MouseEvent.CLICK, imprimirRelatorio);
			btnImprimir.enabled = false;
			
			btnDetalhar.addEventListener(MouseEvent.CLICK, visualizarBoleto);
			
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaTarifas.procuraSolicitada);
			painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarProcurar);
		}
		
		private function validarProcurar(evt:ProcurarEvent):void {
			if (validarCamposConsulta()) {
				painelListaTarifas.procuraSolicitada(evt);
			} 
		}
		
		private function validarCamposConsulta():Boolean {
			if (!painelFiltro.txtNumCodBarras.validar()) {
				return false;
			}
			
			return true;
		}
		
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		/**
		 * Configura o DTO para executar a pesquisa.
		 */
		private function configurarDtoConsulta(dto:ConsultaDto):void {	
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			consultaDTO.cpfCnpjBeneficiario = consultaDTO.numCodBarras = null;
			
			if (painelFiltro.txtCPFCNPJ.text && StringUtil.trim(painelFiltro.txtCPFCNPJ.text).length > 0){
				consultaDTO.cpfCnpjBeneficiario = StringUtil.trim(painelFiltro.txtCPFCNPJ.text);
			}
			
			var numCodBarras:String = painelFiltro.txtNumCodBarras.txtNumCodBarras;
			
			if (numCodBarras && StringUtil.trim(numCodBarras).length > 0){
				consultaDTO.numCodBarras = StringUtil.trim(numCodBarras);
			}
			
			consultaDTO.dataEvento = lancamentosDTO.dataEvento;
			consultaDTO.idEventoConsolidadoDDA = lancamentosDTO.idEventoConsolidadoDDA;
			consultaDTO.idEventoConsolidadoDDADetalhe = lancamentosDTO.idEventoConsolidadoDDADetalhe;
			consultaDTO.numCooperativa = consultaTarifasDTO.numCooperativa;
			
			reqDto.dados.dto = consultaDTO;
			dto.filtro = reqDto;
		}
		
		private function imprimirRelatorio(event:MouseEvent):void{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["consultaDTO"] = consultaDTO;
			dto.dados["consultaTarifasDTO"] = consultaTarifasDTO;
			dto.dados["lancamentosDTO"] = lancamentosDTO;
			
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_MOVIMENTO_SICOOBDDA, null, null, PreImpressao.FORMATO_PDF, dto);
		}
		
		private function visualizarBoleto(event:MouseEvent):void{
			if (grid.grdDados.selectedItem) {
				var dto:ConsultaMovimentoSicoobDDADTO = grid.grdDados.selectedItem as ConsultaMovimentoSicoobDDADTO;
				
				var tela:DetalhamentoBoleto = new DetalhamentoBoleto(FormularioCadastro.MODO_VISUALIZACAO, dto.numCodBarras);
				
				JanelaCobranca.abrirJanela(this, tela, Constantes.DETALHAMENTO_BOLETO);
			}
		}
		
		/**
		 * Limpa os campos da consulta e a tabela.
		 */
		private function limparCamposConsulta(evt:Event):void {
			aplicarMascaraCPFCNPJ();
			
			painelFiltro.txtNumCodBarras.limpar();
			
			limparGrid();
		}
		
		private function limparGrid(evt:Event = null):void {
			grid.barraPaginacao.totalPaginas = grid.barraPaginacao.pagina = 0;
			grid.grdDados.dataProvider = null;
			
			btnDetalhar.enabled = btnImprimir.enabled = false;
		}
		
		private function tratarBotao(evt:Event = null):void {
			btnDetalhar.enabled = grid.grdDados.selectedItem;
		}
		
		private function tratarCallbackPesquisa(evt:ResultEvent):void {
			var lista:ArrayCollection = evt.result.dados.lista as ArrayCollection;
			
			painelListaTarifas.pesquisaRealizada(evt);
			btnImprimir.enabled = lista && lista.length > 0;
			
			tratarBotao();
		}
		
	}

}