package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tarifasddapagas {
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ConsultaTarifasDDAPagasDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.LancamentosTarifasDDADTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.NumeroUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class DetalharLancamentosTarifas extends DetalharLancamentosTarifasView {

		private var consultaDTO:ConsultaTarifasDDAPagasDTO;
		
		private var listaLancamentosTarifasDDADTO:ArrayCollection;
		
		private var relUtil:RelatorioUtil = RelatorioUtil.create();
		
		private var parDTO:ParametroDTO;
		
		public function DetalharLancamentosTarifas(consultaDTO:ConsultaTarifasDDAPagasDTO) {
			this.consultaDTO = consultaDTO;
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnDetalhar.addEventListener(MouseEvent.CLICK, detalhar);
			
			btnImprimir.addEventListener(MouseEvent.CLICK, imprimir);
			btnImprimir.setStyle("icon", ConstantesComum.icoImprimir);
			
			grid.addEventListener(Event.RENDER, tratarBotao);
			grid.addEventListener(KeyboardEvent.KEY_UP, tratarBotao);
			
			preencherCampos();
			
			listarLancamentosTarifas();
		}
		
		private function tratarBotao(evt:Event):void {
			btnDetalhar.enabled = grid.selectedItem;
		}
		
		private function listarLancamentosTarifas():void {
			var metodo:String = "listarLancamentosTarifas";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idRateioDDALancamento = consultaDTO.idRateioDDALancamento;
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_CONSULTA_TARIFAS_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoLancamentosTarifas);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.listarLancamentosTarifas(dto);
		}
		
		private function resultadoLancamentosTarifas(event:ResultEvent):void {
			listaLancamentosTarifasDDADTO = event.result.dados.lista;
			
			grid.dataProvider = listaLancamentosTarifasDDADTO;
			
			var valorTotal:Number = 0;
			var qtdTotal:Number = 0;
			
			for each (var dto:LancamentosTarifasDDADTO in listaLancamentosTarifasDDADTO) {
				qtdTotal += dto.quantidadeApurada;
				valorTotal += dto.valorTotal;
			}
			
			lblQtdTotal.text = FormataNumero.formata(qtdTotal, 0);
			lblValorTotal.text = NumeroUtil.formatarNumeroDecimalParaMoeda(valorTotal);
			
			grid.selectedIndex = 0;
		}
		
		private function preencherCampos():void {
			lblIdRateio.text = consultaDTO.idRateioDDA ? consultaDTO.idRateioDDA.toString() : "";
			lblCooperativa.text = consultaDTO.numCooperativa ? consultaDTO.numCooperativa.toString() : "";
			lblDataConciliacao.text = consultaDTO.dataConciliacao ? FormataData.formataData(consultaDTO.dataConciliacao.data) : "";
			lblNumContaCorrente.text = consultaDTO.numContaCorrenteDebitada ? consultaDTO.numContaCorrenteDebitada.toString() : "";
			lblDescEvento.text = consultaDTO.descEventoTarifavel;
		}
		
		private function detalhar(evt:Event):void {
			if (grid.selectedItem) {	
				var tela:DetalharMovimentoSicoobDDA = new DetalharMovimentoSicoobDDA(consultaDTO, grid.selectedItem as LancamentosTarifasDDADTO);
				
				JanelaCobranca.abrirJanela(this, tela, "Detalhar Movimento Sicoob DDA");
			}
		}
		
		private function imprimir(evt:Event):void {
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_LANCAMENTOS_TARIFAS, consultaDTO, listaLancamentosTarifasDDADTO, PreImpressao.FORMATO_PDF);
		}
		
		private function fechar(evt:Event):void {
			super.fecharJanela();
		}
		
	}
	
}