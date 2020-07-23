package {
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ControleRateioDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RateioDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.rateiotarifas.ControleRateioView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class ControleRateio extends ControleRateioView {

		private var dto:ControleRateioDTO;
		
		public function ControleRateio() {
			super();
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			obterDadosControleRateio();
			
			iniciarCampos();
		}
		
		private function iniciarCampos ():void {
			txtDesvioPadrao.validarMensagemMaximo = MensagensComum.formatar(Mensagens.MSG025, FormataNumero.formata(txtDesvioPadrao.valorMaximo, txtDesvioPadrao.casasDecimais));
			txtDesvioPadrao.validarMensagemMinimo = MensagensComum.formatar(Mensagens.MSG077, "da multa", FormataNumero.formata(txtDesvioPadrao.valorMinimo, txtDesvioPadrao.casasDecimais));
			txtDesvioPadrao.addEventListener(FocusEvent.FOCUS_OUT, tratarCampoDesvioPadrao);
			
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);

			btnAplicarDesvio.addEventListener(MouseEvent.CLICK, aplicarDesvio);
			btnGravar.addEventListener(MouseEvent.CLICK, gravar);
			
			abaEventosDisponiveis.grid.funcaoAtualizarRateio = atualizarRateio;
			abaEventosDisponiveis.grid.funcaoAtualizarListaSelecionados = abaDetalhesRateio.atualizarListaSelecionados;
			
			abaDetalhesRateio.funcaoAprovarRateio = atualizarAprovarRateio;
			abaDetalhesRateio.grid.funcaoAtualizarRateio = atualizarRateio;
			abaDetalhesRateio.grid.funcaoAtualizarListaSelecionados = abaEventosDisponiveis.atualizarListaSelecionados;
		}
		
		private function tratarCampoDesvioPadrao(event:FocusEvent):void {
			if (txtDesvioPadrao.text == "") {
				txtDesvioPadrao.valor = 0;
			}
		}
		
		private function aplicarDesvio(evt:MouseEvent):void {
			abaDetalhesRateio.atualizar(txtDesvioPadrao.valor);
			abaEventosDisponiveis.atualizar(txtDesvioPadrao.valor);
		}
		
		private function obterDadosControleRateio():void {
			var metodo:String = "obterDadosControleRateio";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoObterDadosControleRateio);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.obterDadosControleRateio(dto);
		}
		
		private function resultadoObterDadosControleRateio(event:ResultEvent):void {
			dto = event.result.dados.dto as ControleRateioDTO;
			
			abaDetalhesRateio.definirLista(dto.listaEventoRateio, dto.desvioPadrao);
			
			abaEventosDisponiveis.cmbEventoTarifavel.dataProvider = dto.listaEventoTarifavelDDA;

			abaEventosDisponiveis.atualizarRateio(dto.idRateio, dto.dataInclusao, dto.descSituacao);
			abaDetalhesRateio.atualizarRateio(dto.idRateio, dto.dataInclusao, dto.descSituacao);
			
			if (dto.desvioPadrao > 0) {
				txtDesvioPadrao.valor = dto.desvioPadrao;
			}
			
			abaEventosDisponiveis.desvioPadrao = dto.desvioPadrao;
			abaDetalhesRateio.desvioPadrao = dto.desvioPadrao;
			
			lblIdentificador.text = dto.idRateio ? dto.idRateio.toString() : "-";
			lblDataInclusao.text = dto.dataInclusao ? FormataData.formataData(dto.dataInclusao.data) : "-";
			lblSituacao.text = dto.descSituacao ? dto.descSituacao : "-";
			
			validarRateio(dto.idRateio, dto.codSituacaoRateio);
		}
		
		private function validarRateio(idRateio:Number, codSituacaoRateio:Number):void {
			if (idRateio && codSituacaoRateio != ConstantesComum.SITUACAO_RATEIO_AGUARDANDO_APROVACAO) {
				MensagensComum.exibirMensagemConfirmacao("O rateio está com a situação \"Aprovado para Efetivação\". Deseja cancelar a aprovação para editar o Rateio?", null,
					cancelarAprovacaoRateio, bloquearCampos);
			} else {
				definirFoco();
			}
		}
		
		private function definirFoco():void {
			abaEventosDisponiveis.txtDataInicial.setFocus();
		}
		
		private function cancelarAprovacaoRateio(evt:Event):void {
			var metodo:String = "cancelarAprovacaoRateio";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoCancelarAprovacaoRateio);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.cancelarAprovacaoRateio(dto);
		}
		
		private function resultadoCancelarAprovacaoRateio(event:ResultEvent):void {
			var rateio:RateioDDAVO = event.result.dados.vo as RateioDDAVO;
			
			atualizarRateio(rateio);
			
			abaDetalhesRateio.grid.setFocus();
		}
		
		private function atualizarRateio(rateio:RateioDDAVO):void {
			atualizarInformacoesRateio(rateio.id, rateio.dataHoraInclusao, rateio.situacaoRateio.descSituacaoRateio);
			
			abaEventosDisponiveis.atualizarRateio(rateio.id, rateio.dataHoraInclusao, rateio.situacaoRateio.descSituacaoRateio);
			abaDetalhesRateio.atualizarRateio(rateio.id, rateio.dataHoraInclusao, rateio.situacaoRateio.descSituacaoRateio);
			
			tab.selectedIndex = 1;
		}
		
		private function atualizarInformacoesRateio(idRateio:Number, dataInclusao:IDateTime, descSituacao:String):void {
			if (idRateio) {
				lblIdentificador.text = idRateio.toString();
			}
			
			if (dataInclusao) {
				lblDataInclusao.text = FormataData.formataData(dataInclusao.data);
			}
			
			if (descSituacao) {
				lblSituacao.text = descSituacao;
			}
		}
		
		private function bloquearCampos(evt:Event = null):void {
			txtDesvioPadrao.enabled = btnAplicarDesvio.enabled = btnGravar.enabled = false;
			
			abaEventosDisponiveis.bloquearCampos();
			abaDetalhesRateio.bloquearCampos();
			
			tab.selectedIndex = 1;
			
			btnFechar.setFocus();
		}
		
		private function gravar(evt:Event):void {
			var metodo:String = "atualizarEventoConsolidado";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.desvioPadrao = txtDesvioPadrao.valor;
			dto.dados.lista = obterListas();
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoAtualizarEventoConsolidado);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.atualizarEventoConsolidado(dto);
		}
		
		private function obterListas():ArrayCollection {
			var lista:ArrayCollection = new ArrayCollection();
			
			if (abaEventosDisponiveis.grid.grid.dataProvider) {
				lista.addAll(abaEventosDisponiveis.grid.grid.dataProvider as ArrayCollection);
			}
			
			if (abaDetalhesRateio.grid.grid.dataProvider) {
				lista.addAll(abaDetalhesRateio.grid.grid.dataProvider as ArrayCollection);
			}
			
			return lista;
		}
		
		private function resultadoAtualizarEventoConsolidado(event:ResultEvent):void {
			MensagensComum.exibirMensagemInformacao(MensagensComum.formatar(MensagensComum.MSG002, "Informação"));
		}
		
		private function atualizarAprovarRateio(rateio:RateioDDAVO):void {
			atualizarRateio(rateio);
			bloquearCampos();
		}
		
		private function fechar(evt:Event):void {
			super.fecharJanela();
		}

	}

}