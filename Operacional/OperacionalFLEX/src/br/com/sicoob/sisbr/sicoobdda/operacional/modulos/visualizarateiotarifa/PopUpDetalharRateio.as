package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.visualizarateiotarifa
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.VisualizaRateioTarifaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.visualizarateiotarifa.abas.lancamento.PopUpDetalharLancamento;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpDetalharRateio extends PopUpDetalharRateioView
	{
		private var _funcaoRetorno:Function;
		private var _visualRateioTarifaDto:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO();
		
		private static const DETALHE_LANCAMENTO:String = "DETALHE DO LANÇAMENTO";
		private static const TRANSFERIR_DEBITO:String = "TRANSFERÊNCIA DE DÉBITO";
		private static const COD_EVENTO_TARIFAVEL_DEFAULT:Number= 0;
		
		public static const EFETIVADO_COM_AJUSTE:Number = 5;
		public static const EFETIVADO_MANUALMENTE:Number = 4;
		public static const ERRO_EFETIVACAO:Number = 3;
		
		public function PopUpDetalharRateio(visualRateioTarifaDto:VisualizaRateioTarifaDTO, funcaoRetorno:Function) {
			visualRateioTarifaDto.codEventoTarifavel=COD_EVENTO_TARIFAVEL_DEFAULT;
			this._visualRateioTarifaDto = visualRateioTarifaDto;
			this._funcaoRetorno = funcaoRetorno;
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init)
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected function init(event:FlexEvent):void {
			this.abaLancamento.btnAlterar.addEventListener(MouseEvent.CLICK, popUpEditarLancamento);
			this.abaLancamento.btnDetalhar.addEventListener(MouseEvent.CLICK, popUpDetalharLancamento);
			this.abaLancamento.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validaConsultaLancamento);
			this.abaLancamento.btnLimpar.addEventListener(MouseEvent.CLICK, limparCamposLancamento);
			
			this.abaLancamento.cmbSituacaoLancamento.addEventListener(ListEvent.CHANGE, limparGridLancamento);
			this.abaLancamento.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, limparGridLancamento);
			this.abaLancamento.compCentralSingular.cmbSingular.addEventListener(ListEvent.CHANGE, limparGridLancamento);
			this.abaLancamento.btnEfetivar.addEventListener(MouseEvent.CLICK, confirmacaoRealizarEfetivacaoManual);
			
			this.abaRateio.cmbEventoTarifavel.addEventListener(ListEvent.CHANGE, limparCamposDadosRateio);
			this.abaRateio.btnProcurar.addEventListener(MouseEvent.CLICK, pesquisarDetalheRateio);
			this.abaRateio.btnLimpar.addEventListener(MouseEvent.CLICK, limparFiltros);
			
			this.tabDetalharRateio.addEventListener(Event.CHANGE, verificaAbaSelecionada);
			this.btnFechar.addEventListener(MouseEvent.CLICK,fechar);
			this.btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			this.btnImprimirAba.setStyle("icon", ConstantesComum.icoImprimir);
			
			carregarDetalheRateio();
		}
		
		private function carregarDetalheRateio():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = this._visualRateioTarifaDto;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, "detalharRateio");
			servico.addEventListener(ResultEvent.RESULT, retornoCarregarDetalheRateio);
			servico.detalharRateio(dto);
		}
		
		private function retornoCarregarDetalheRateio(resultEvent:ResultEvent):void {
			var visualRateioTarifaDTO:VisualizaRateioTarifaDTO = resultEvent.result.dados["dto"];
			
			/*this.abaRateio._visualRateioTarifaDtoTemp.listaDadosRateio = visualRateioTarifaDTO.listaDadosRateio;
			this.abaRateio._visualRateioTarifaDtoTemp.totalDadosRateio = visualRateioTarifaDTO.totalDadosRateio;*/
			this.abaRateio.listaRateioTarifa.dataProvider = visualRateioTarifaDTO.listaDadosRateio;
			this.abaRateio.totalizadores(visualRateioTarifaDTO);
		
			this.abaLancamento._listaLancamentos = visualRateioTarifaDTO.listaLancamentoRateios
			this.abaLancamento.listaLancamento.dataProvider = visualRateioTarifaDTO.listaLancamentoRateios;
			this.abaLancamento.cmbSituacaoLancamento.dataProvider = visualRateioTarifaDTO.listaSituacaoLancamento;
			this.abaLancamento.funcaoRetetornoImprimir(verificaAbaSelecionada);
			verificaAbaSelecionada();
		}
//----------------------------------------------------------------------------------------------------------------------------------------------------
//  FUNÇÕES PESQUISA ABA DADOS RATEIO
//----------------------------------------------------------------------------------------------------------------------------------------------------
		private function pesquisarDetalheRateio(e:Event=null):void {
			var visualRateioTarifaDto:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO();
			visualRateioTarifaDto = this._visualRateioTarifaDto;
			
			if(this.abaRateio.cmbEventoTarifavel.selectedItem){
				visualRateioTarifaDto.codEventoTarifavel = this.abaRateio.cmbEventoTarifavel.selectedItem.codEventoTarifavel;
			}else{
				visualRateioTarifaDto.codEventoTarifavel = COD_EVENTO_TARIFAVEL_DEFAULT;
			}
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = visualRateioTarifaDto;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, "pesquisarDadosRateio");
			servico.addEventListener(ResultEvent.RESULT, retornoPesquisarDadosRateio);
			servico.pesquisarDadosRateio(dto);
		}
		
		private function retornoPesquisarDadosRateio(resultEvent:ResultEvent):void {
			var visualRateioTarifaDTOReturn:VisualizaRateioTarifaDTO = resultEvent.result.dados["dto"];
			this.abaRateio.listaRateioTarifa.dataProvider = visualRateioTarifaDTOReturn.listaDadosRateio;
			this.abaRateio.totalizadores(visualRateioTarifaDTOReturn);
			verificaAbaSelecionada();
		}
		
		//--------------------------------------------------------------------------
		//  Relatorio detalhado do Pagador PDF.
		//--------------------------------------------------------------------------
		public function imprimirDadosRateio(e:Event=null):void {
			//var dto:RequisicaoReqDTO = new RequisicaoReqDTO();//Removendo para otimizacao WebSphere
			var visualRateioTarifaDto:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO();
			visualRateioTarifaDto = this._visualRateioTarifaDto;
			
			if(this.abaRateio.cmbEventoTarifavel.selectedItem){
				visualRateioTarifaDto.codEventoTarifavel = this.abaRateio.cmbEventoTarifavel.selectedItem.codEventoTarifavel;
			}else{
				visualRateioTarifaDto.codEventoTarifavel = COD_EVENTO_TARIFAVEL_DEFAULT;
			}
				
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_LISTA_DADOS_RATEIO, visualRateioTarifaDto, null, PreImpressao.FORMATO_PDF); 
		}
		
//----------------------------------------------------------------------------------------------------------------------------------------------------
//  FUNÇÕES PESQUISA ABA LANCAMENTO
//----------------------------------------------------------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------
		//  Pesquisa Lançamentos
		//--------------------------------------------------------------------------		
		private function pesquisaLancamentos(visualRateioTarDto:VisualizaRateioTarifaDTO=null):void {
			this.abaLancamento.listaLancamento.dataProvider =null;
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = visualRateioTarDto;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, "pesquisarLancamentos");
			servico.addEventListener(ResultEvent.RESULT, retornoPesquisaLancamentos);
			servico.pesquisarLancamentos(dto);
		}
		private function retornoPesquisaLancamentos(event:ResultEvent):void {
			var lista:ArrayCollection = event.result.dados["lista"];
			this.abaLancamento.listaLancamento.dataProvider = lista;
			this.abaLancamento.desabilitaBotoesAcao();
			verificaAbaSelecionada();
		}
		
		private function limparCamposLancamento(event:Event=null):void {
			var visualRateioTarDto:VisualizaRateioTarifaDTO = new  VisualizaRateioTarifaDTO();
			this.abaLancamento.limparCampos();
			this.abaLancamento.listaLancamento.dataProvider = null;
			this.abaLancamento.desabilitaBotoesAcao();
			this.verificaAbaSelecionada();
		}
		
		private function limparGridLancamento(event:Event=null):void {
			this.abaLancamento.listaLancamento.dataProvider = null;
			this.abaLancamento.desabilitaBotoesAcao();
			this.verificaAbaSelecionada();
		}
		
		private function retornoValidaLancamento():void {
			validaConsultaLancamento();
		}
		
		private function validaConsultaLancamento(event:Event=null):void {
			var visualRateioTarDto:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO;
			visualRateioTarDto.listaIdRateio = _visualRateioTarifaDto.listaIdRateio;

			if(this.abaLancamento.compCentralSingular.cmbCentral.selectedItem && !this.abaLancamento.compCentralSingular.cmbSingular.selectedItem){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Cooperativa"));
				return;
			}
			if(this.abaLancamento.compCentralSingular.cmbSingular.selectedItem){
				visualRateioTarDto.idInstituicaoSingular = this.abaLancamento.compCentralSingular.cmbSingular.selectedItem.idInstituicao;
			}
			if(this.abaLancamento.cmbSituacaoLancamento.selectedItem){
				visualRateioTarDto.codSituacaoRateioLancamento = this.abaLancamento.cmbSituacaoLancamento.selectedItem.codSituacaoRateioLancamento;
			}
			limparGridLancamento();
			this.pesquisaLancamentos(visualRateioTarDto);
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Edição.
		//--------------------------------------------------------------------------		
		private function popUpEditarLancamento(event:Event):void {
			var dto:VisualizaRateioTarifaDTO = this.abaLancamento.listaLancamento.selectedItem as VisualizaRateioTarifaDTO;
			var tela:PopUpDetalharLancamento = new PopUpDetalharLancamento(dto.idRateioDDALancamento, true, validaConsultaLancamento);
			JanelaCobranca.abrirJanela(this, tela, TRANSFERIR_DEBITO);
		}

		//--------------------------------------------------------------------------
		//  Abre a Janela de Detalhar.
		//--------------------------------------------------------------------------
		private function popUpDetalharLancamento(event:Event):void {
			var dto:VisualizaRateioTarifaDTO = this.abaLancamento.listaLancamento.selectedItem as VisualizaRateioTarifaDTO;
			var tela:PopUpDetalharLancamento = new PopUpDetalharLancamento(dto.idRateioDDALancamento, false, validaConsultaLancamento);
			JanelaCobranca.abrirJanela(this, tela, DETALHE_LANCAMENTO);
		}

		//--------------------------------------------------------------------------
		//  Relatorio detalhado do Pagador PDF.
		//--------------------------------------------------------------------------
		public function imprimirLancamentos(e:Event=null):void {
			//var dto:RequisicaoReqDTO = new RequisicaoReqDTO(); //Removendo para otimizacao WebSphere 
			var visualizaRT:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO;
			visualizaRT.listaIdRateio = _visualRateioTarifaDto.listaIdRateio;

			if(this.abaLancamento.compCentralSingular.cmbCentral.selectedItem && !this.abaLancamento.compCentralSingular.cmbSingular.selectedItem){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Cooperativa"));
				return;
			}
			if(this.abaLancamento.cmbSituacaoLancamento.selectedItem != null){
				visualizaRT.codSituacaoRateioLancamento = this.abaLancamento.cmbSituacaoLancamento.selectedItem.codSituacaoRateioLancamento;
			}
			
			if(this.abaLancamento.compCentralSingular.cmbSingular.selectedItem){
				visualizaRT.idInstituicaoCentral = this.abaLancamento.compCentralSingular.cmbCentral.selectedItem.idInstituicao;
				visualizaRT.idInstituicaoSingular = this.abaLancamento.compCentralSingular.cmbSingular.selectedItem.idInstituicao;
			}
			
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_LISTA_LANCAMENTO_DDA, visualizaRT, null, PreImpressao.FORMATO_PDF);
		}
		
		//--------------------------------------------------------------------------
		//  Efetivar Lançamento.
		//--------------------------------------------------------------------------
		private function realizarEfetivacaoManual(e:Event):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.id = this.abaLancamento.listaLancamento.selectedItem.idRateioDDALancamento;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, "efetivacaoManual");
			servico.addEventListener(ResultEvent.RESULT, retornoRealizarEfetivacaoManual);
			servico.efetivacaoManual(dto);
		}
		
		private function confirmacaoRealizarEfetivacaoManual(event:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao("Deseja efetivar manualmente o rateio selecionado?", null, realizarEfetivacaoManual);
		}
		
		private function retornoRealizarEfetivacaoManual(resultEvent:ResultEvent):void {
			var codMsgRetorno:Number = resultEvent.result.dados["codMsgRetorno"];
			if(ERRO_EFETIVACAO == codMsgRetorno){
				validaConsultaLancamento();
				MensagensComum.exibirMensagemAlerta("O rateio não pode ser efetivado manualmente!");
			}else if(EFETIVADO_MANUALMENTE == codMsgRetorno){
				validaConsultaLancamento();
				MensagensComum.exibirMensagemSucesso("Rateio efetivado manualmente!");
			}
		}
		
//----------------------------------------------------------------------------------------------------------------------------------------------------
//  
//----------------------------------------------------------------------------------------------------------------------------------------------------
		private function verificaAbaSelecionada(e:Event=null):void{
			if(this.tabDetalharRateio.selectedIndex == 0){
				if(this.abaRateio.listaRateioTarifa.dataProvider.length > 0){
					this.btnImprimirAba.addEventListener(MouseEvent.CLICK, imprimirDadosRateio);
					this.btnImprimirAba.removeEventListener(MouseEvent.CLICK, imprimirLancamentos);
					this.btnImprimirAba.enabled =  true;
				}else{
					this.btnImprimirAba.enabled =  false;
				}
			}else{
				if(this.abaLancamento.listaLancamento.dataProvider.length > 0){
					this.btnImprimirAba.removeEventListener(MouseEvent.CLICK, imprimirDadosRateio);
					this.btnImprimirAba.addEventListener(MouseEvent.CLICK, imprimirLancamentos);
					this.btnImprimirAba.enabled =  true;
				}else{
					this.btnImprimirAba.enabled =  false;
				}				
			}
		}
		
		public function limparFiltros(evt:Event):void {
			this.abaRateio.cmbEventoTarifavel.selectedItem = null;
			this.limparCamposDadosRateio();
		}
		
		//--------------------------------------------------------------------------
		//  Limpar a pesquisa.
		//--------------------------------------------------------------------------
		public function limparCamposDadosRateio(evt:Event=null):void {
			this.abaRateio.listaRateioTarifa.dataProvider = null;
			var visualRateioTarifaDto:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO();

			visualRateioTarifaDto.totalDadosRateio = new VisualizaRateioTarifaDTO();
			visualRateioTarifaDto.totalDadosRateio.qtdApuradoBancoob = 0;
			visualRateioTarifaDto.totalDadosRateio.valorApuradoBancoob = 0;
			visualRateioTarifaDto.totalDadosRateio.qtdApuradoCIP = 0;
			visualRateioTarifaDto.totalDadosRateio.valorApuradoCIP = 0;
			visualRateioTarifaDto.totalDadosRateio.difValor = 0;
			visualRateioTarifaDto.totalDadosRateio.valorRateioCoop = 0;
			visualRateioTarifaDto.totalDadosRateio.difQuantidade = 0;
			
			this.abaRateio.totalizadores(visualRateioTarifaDto);
			this.verificaAbaSelecionada();
		}
		
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			if(_funcaoRetorno != null){
				this._funcaoRetorno();
			}
			super.fecharJanela();
		}
	}
}