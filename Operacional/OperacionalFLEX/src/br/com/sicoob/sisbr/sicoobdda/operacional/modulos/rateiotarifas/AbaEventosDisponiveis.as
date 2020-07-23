package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.rateiotarifas {
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ConsultaEventoRateioDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ControleRateioDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.EventoRateioDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataPesquisaUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.EventoTarifavelDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class AbaEventosDisponiveis extends AbaEventosDisponiveisView {
		
		private const QTD_DIAS_CONSULTA_DDA:Number = 35;
		
		private var lista:ArrayCollection;
		private var _desvioPadrao:Number = 0;
		private var _idRateio:Number;
		private var _dataInclusao:IDateTime;
		private var _descSituacaoRateio:String;

		public function AbaEventosDisponiveis() {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			btnProcurar.addEventListener(MouseEvent.CLICK, validarProcurar);
			btnLimpar.addEventListener(MouseEvent.CLICK, limpar);
			
			txtDataInicial.selectedDate = txtDataFinal.selectedDate = null;
			
			cmbEventoTarifavel.addEventListener(ListEvent.CHANGE, limparGrid);
			txtDataInicial.addEventListener(Event.CHANGE, limparGrid);
			txtDataFinal.addEventListener(Event.CHANGE, limparGrid);
			
			grid.btnRemoverRateio.visible = grid.btnRemoverRateio.includeInLayout = false;
			
			grid.funcaoImprimir = imprimir;
		}
		
		private function imprimir():void{
			var controleRateioDTO:ControleRateioDTO = new ControleRateioDTO();
			controleRateioDTO.idRateio = _idRateio;
			controleRateioDTO.dataInclusao = _dataInclusao;
			controleRateioDTO.descSituacao = _descSituacaoRateio;
			controleRateioDTO.desvioPadrao = _desvioPadrao;
			controleRateioDTO.listaEventoRateio = lista;
		
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.controleRateioDto = controleRateioDTO; 
			dto.dados.consultaDto = criarConsultaDTO();
			
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_EVENTOS_DISPONIVEIS, null, null, PreImpressao.FORMATO_PDF, dto);
		}
		
		private function limpar(event:MouseEvent):void {
			txtDataInicial.selectedDate = txtDataFinal.selectedDate = null;
			
			cmbEventoTarifavel.selectedIndex = 0;
			
			limparGrid();
		}
		
		private function limparGrid(event:Event = null):void {
			grid.limpar();
		}
		
		private function validarProcurar(evt:Event):void {
			if (validarCampos()) {
				procurar();
			}
		}
		
		private function validarCampos():Boolean {
			if (!txtDataInicial.selectedDate && !txtDataFinal.selectedDate) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "período de movimento"));
				return false;
			}
			
			if (txtDataInicial.selectedDate || txtDataFinal.selectedDate) {
				return DataPesquisaUtil.validaPeriodoDataPesquisa(txtDataInicial, txtDataFinal, QTD_DIAS_CONSULTA_DDA, "de movimento ");	
			}
			
			return true;
		}
		
		private function procurar():void {
			var metodo:String = "pesquisarEventosDisponiveis";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["dto"] = criarConsultaDTO();
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoPesquisarEventosDisponiveis);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.pesquisarEventosDisponiveis(dto);
		}
		
		private function criarConsultaDTO():ConsultaEventoRateioDTO {
			var dto:ConsultaEventoRateioDTO = new ConsultaEventoRateioDTO();
			
			dto.dataInicial = txtDataInicial.selectedDate ? DateTimeBase.getDateTime(txtDataInicial.selectedDate) : null;
			dto.dataFinal = txtDataFinal.selectedDate ? DateTimeBase.getDateTime(txtDataFinal.selectedDate) : null;
			if (cmbEventoTarifavel.selectedItem) {
				var evento:EventoTarifavelDDAVO = (cmbEventoTarifavel.selectedItem as EventoTarifavelDDAVO);
				dto.codEventoTarifavel = evento.codEventoTarifavel;
				dto.descEventoTarifavel = evento.descEventoTarifavelExtrato;
			}
			
			return dto;
		}
		
		private function resultadoPesquisarEventosDisponiveis(event:ResultEvent):void {
			lista = event.result.dados.lista as ArrayCollection;
			
			grid.definirLista(lista);
			
			atualizar(_desvioPadrao);
		}
		
		public function atualizar(desvioPadrao:Number):void {
			_desvioPadrao = desvioPadrao;
			
			if (!lista) {
				return;
			}
			
			for each (var dto:EventoRateioDTO in lista) { 
				dto.desvioPadrao = desvioPadrao;
			}
			
			grid.atualizar();
		}
		
		public function set desvioPadrao(desvioPadrao:Number):void {
			_desvioPadrao = desvioPadrao;
		}
		
		public function bloquearCampos():void {
			txtDataFinal.compMask.enabled = txtDataInicial.compMask.enabled = txtDataFinal.compDate.enabled = txtDataInicial.compDate.enabled = false;
			btnLimpar.enabled = btnProcurar.enabled = false;
			cmbEventoTarifavel.enabled = false;
			
			grid.bloquearCampos();
		}
		
		public function atualizarListaSelecionados(listaSelecionados:ArrayCollection):void {
			if (listaSelecionados && lista) {
				lista.addAll(listaSelecionados);
			}
			
			grid.atualizar();
		}
		
		public function atualizarRateio(idRateio:Number, dataInclusao:IDateTime, descSituacaoRateio:String):void {
			_idRateio = idRateio;
			_dataInclusao = dataInclusao;
			_descSituacaoRateio = descSituacaoRateio;
		}
		
	}
	
}