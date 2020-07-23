package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.rateiotarifas {
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.controls.CheckBox;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ControleRateioDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.EventoRateioDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RateioDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class AbaDetalhesRateio extends AbaDetalhesRateioView {

		private var checkBoxHeader:CheckBox;
		private var lista:ArrayCollection;
		
		private var _desvioPadrao:Number = 0;
		
		private var _funcaoAprovarRateio:Function;
		
		private var _idRateio:Number;
		private var _dataInclusao:IDateTime;
		private var _descSituacaoRateio:String;
		
		private var _bloquearCampos:Boolean = false;
		
		public function AbaDetalhesRateio() {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			grid.btnIncluirRateio.visible = grid.btnIncluirRateio.includeInLayout = false;
			
			grid.funcaoImprimir = imprimir;
			
			grid.addEventListener(Event.RENDER, atualizarBotao);
			
			btnAprovar.addEventListener(MouseEvent.CLICK, aprovarRateio);
		}
		
		private function aprovarRateio(event:MouseEvent):void {
			var metodo:String = "aprovarRateio";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoAprovarRateio);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.aprovarRateio(dto);
		}
		
		private function resultadoAprovarRateio(event:ResultEvent):void {
			var rateio:RateioDDAVO = event.result.dados.vo as RateioDDAVO;
			
			if (_funcaoAprovarRateio != null) {
				_funcaoAprovarRateio(rateio);
			}
			
			MensagensComum.exibirMensagemInformacao("Aprovação do rateio efetuada com sucesso.");
		}
		
		private function imprimir():void {
			var controleRateioDTO:ControleRateioDTO = new ControleRateioDTO();
			controleRateioDTO.idRateio = _idRateio;
			controleRateioDTO.dataInclusao = _dataInclusao;
			controleRateioDTO.descSituacao = _descSituacaoRateio;
			controleRateioDTO.desvioPadrao = _desvioPadrao;
			controleRateioDTO.listaEventoRateio = lista;
			
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_DETALHES_RATEIO, controleRateioDTO, null, PreImpressao.FORMATO_PDF);
		}
		
		public function definirLista(listaParam:ArrayCollection, desvioPadrao:Number):void {
			lista = listaParam;
			
			if (!lista) {
				lista = new ArrayCollection();
			}
			
			for each (var dto:EventoRateioDTO in lista) {
				dto.desvioPadrao = desvioPadrao;
			}
			
			grid.definirLista(lista);
			
			atualizarBotao();
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
			
			atualizarBotao();
		}
		
		private function atualizarBotao(evt:Event = null):void {
			if (btnAprovar && lista) {
				btnAprovar.enabled = !_bloquearCampos && lista.length > 0;
			}
		}
		
		public function set funcaoAprovarRateio(funcao:Function):void {
			_funcaoAprovarRateio = funcao;
		}
		
		public function set desvioPadrao(desvioPadrao:Number):void {
			_desvioPadrao = desvioPadrao;
		}
		
		public function bloquearCampos():void {
			_bloquearCampos = true;
			
			btnAprovar.enabled = false;
			
			grid.bloquearCampos(false);
		}
		
		public function atualizarListaSelecionados(listaSelecionados:ArrayCollection):void {
			if (listaSelecionados) {
				lista.addAll(listaSelecionados);
			}
			
			grid.atualizar();
			
			atualizarBotao();
		}
		
		public function atualizarRateio(idRateio:Number, dataInclusao:IDateTime, descSituacaoRateio:String):void {
			_idRateio = idRateio;
			_dataInclusao = dataInclusao;
			_descSituacaoRateio = descSituacaoRateio;
		}
		
	}
	
}