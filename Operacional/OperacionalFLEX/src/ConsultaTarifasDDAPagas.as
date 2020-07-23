package {
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ConsultaTarifasDDAPagasDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataPesquisaUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.CentralSingularVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tarifasddapagas.ConsultaTarifasDDAPagasView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tarifasddapagas.DetalharLancamentosTarifas;

	registerClassAlias("RegistroVO", RegistroVO);
	public class ConsultaTarifasDDAPagas extends ConsultaTarifasDDAPagasView {

		private var btnImprimir:Botao = new Botao();
		
		private const QTD_DIAS_CONSULTA_DDA:Number = 35;
		
		private var consulta:ConsultaTarifasDDAPagasDTO = new ConsultaTarifasDDAPagasDTO();
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			
			iniciarCampos();
			
			definirBotoes();
			
			funcaoDuploClique = null;
			
			validarTabulacao();
		}
		
		private function iniciarCampos ():void {
			barraBotoesListaCadastro.exibirBotaoFechar = true;
			barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoExcluir = false;
			
			painelFiltro.cvInstituicao.exibirPorAgenciaLogada = true;
			painelFiltro.cvInstituicao.carregarCombos();
			painelFiltro.cvInstituicao.funcaoRetorno = definirFoco;
			
			painelListaTarifas.funcaoCriacaoDto = instanciarDtoConsulta;
			painelListaTarifas.funcaoConfiguracaoDto = configurarDtoConsulta;
			
			painelFiltro.cvInstituicao.cmbCentral.validarObrigatorio = true;
			painelFiltro.cvInstituicao.cmbCentral.validarMensagem = MensagensComum.formatar(MensagensComum.MSG017, "Central");

			painelFiltro.cvInstituicao.cmbSingular.validarObrigatorio = true;
			painelFiltro.cvInstituicao.cmbSingular.validarMensagem = MensagensComum.formatar(MensagensComum.MSG017, "Singular");
			
			painelFiltro.cvInstituicao.cmbCentral.addEventListener(ListEvent.CHANGE, limparGrid);
			painelFiltro.cvInstituicao.cmbSingular.addEventListener(ListEvent.CHANGE, limparGrid);
			painelFiltro.dataInicial.addEventListener(Event.CHANGE, limparGrid);
			painelFiltro.dataFinal.addEventListener(Event.CHANGE, limparGrid);
			
			btnImprimir.addEventListener(MouseEvent.CLICK, imprimirRelatorio);
			
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
			
			grid.addEventListener(Event.RENDER, tratarBotao);
		}
		
		private function definirFoco():void {
			painelFiltro.cvInstituicao.cmbCentral.setFocus();
		}
		
		private function validarTabulacao():void {
			var index:int = 1;
			
			painelFiltro.cvInstituicao.cmbCentral.tabIndex = index++;
			painelFiltro.cvInstituicao.cmbSingular.tabIndex = index++;
			
			painelFiltro.dataInicial.tabIndex = index++;
			painelFiltro.dataFinal.tabIndex = index++;
			
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
			if (!painelFiltro.dataInicial.selectedDate && !painelFiltro.dataFinal.selectedDate) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "período de conciliação"));
				return false;
			}
			
			if (painelFiltro.dataInicial.selectedDate || painelFiltro.dataFinal.selectedDate) {
				return DataPesquisaUtil.validaPeriodoDataPesquisa(painelFiltro.dataInicial, painelFiltro.dataFinal, QTD_DIAS_CONSULTA_DDA, "de conciliação ");	
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
			
			if (painelFiltro.dataInicial.selectedDate) {
				consulta.dataConciliacaoInicial = DateTimeBase.getDateTime(painelFiltro.dataInicial.selectedDate);	
			} else {
				consulta.dataConciliacaoInicial = null;
			}
			
			if (painelFiltro.dataFinal.selectedDate) {
				consulta.dataConciliacaoFinal = DateTimeBase.getDateTime(painelFiltro.dataFinal.selectedDate);	
			} else {
				consulta.dataConciliacaoFinal = null;
			}
			
			if (painelFiltro.cvInstituicao.cmbCentral.selectedItem) {
				consulta.idInstituicaoCentral = (painelFiltro.cvInstituicao.cmbCentral.selectedItem as CentralSingularVO).idInstituicao;
				consulta.numCooperativaCentral = (painelFiltro.cvInstituicao.cmbCentral.selectedItem as CentralSingularVO).numeroCooperativa.toString();
			} else {
				consulta.idInstituicaoCentral = NaN;
				consulta.numCooperativaCentral = null;
			}
			
			if (painelFiltro.cvInstituicao.cmbSingular.selectedItem) {
				consulta.idInstituicaoSingular = (painelFiltro.cvInstituicao.cmbSingular.selectedItem as CentralSingularVO).idInstituicao;
				consulta.numCooperativaSingular = (painelFiltro.cvInstituicao.cmbSingular.selectedItem as CentralSingularVO).numeroCooperativa.toString();
			} else {
				consulta.idInstituicaoSingular = NaN;
				consulta.numCooperativaSingular = null;
			}
			
			reqDto.dados["dto"] = consulta;
			dto.filtro = reqDto;
		}
		
		private function imprimirRelatorio(event:MouseEvent):void{
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_TARIFAS_DDA_PAGAS, consulta, null, PreImpressao.FORMATO_PDF);
		}
		
		private function visualizarBoleto(event:MouseEvent):void{
			if (grid.grdDados.selectedItem) {
				var tela:DetalharLancamentosTarifas = new DetalharLancamentosTarifas(grid.grdDados.selectedItem as ConsultaTarifasDDAPagasDTO);
				
				JanelaCobranca.abrirJanela(this, tela, "Detalhar Lançamentos de Tarifas");
			}
		}
		
		/**
		 * Limpa os campos da consulta e a tabela.
		 */
		private function limparCamposConsulta(evt:Event):void {
			painelFiltro.cvInstituicao.limparCombos();
			
			painelFiltro.dataInicial.selectedDate = painelFiltro.dataFinal.selectedDate = null;
			
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
			btnImprimir.enabled = lista.length > 0;
			
			tratarBotao();
		}
		
	}

}