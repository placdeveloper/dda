package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.visualizarateiotarifa.abas.lancamento
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.VisualizaRateioTarifaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.NumeroUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpDetalharLancamento extends PopUpDetalharLancamentoView
	{
		private var _funcaoRetorno:Function;
		private var _idLancamento:Number;
		private var _codSituacaoLancamento:Number;
		private var _bolEditar:Boolean;
		public static const EFETIVADO_COM_AJUSTE:Number = 5;
		public static const EFETIVADO_MANUALMENTE:Number = 4;
		public static const ERRO_EFETIVACAO:Number = 3;
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpDetalharLancamento(idLancamento:Number, bolEditar:Boolean,funcaoRetorno:Function) {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this._idLancamento = idLancamento;
			this._funcaoRetorno = funcaoRetorno;
			this._bolEditar = bolEditar;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			
			this.btnTransferir.addEventListener(MouseEvent.CLICK, confirmacaoRealizaTransferenciaDebito);
			this.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, restaCmbSingular);
			this.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, enableSingular);
			this.compCentralSingular.cmbSingular.addEventListener(ListEvent.CHANGE, enableTransferir);
			
			this.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, desabilitaCooperativa);

			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			iniciarBotoes();
			carregarDetalheRateio();
		}
		
		private function desabilitaCooperativa(event:Event):void {
			if(this.compCentralSingular.cmbCentral.selectedItem != null){
				if(this.compCentralSingular.cmbCentral.selectedItem.idInstituicao == 1 || this.compCentralSingular.cmbCentral.selectedItem.idInstituicao == 2){
					this.compCentralSingular.cmbSingular.enabled = false;
				}
			}
		}
		
		//--------------------------------------------------------------------------
		//  Inicializar botões.
		//--------------------------------------------------------------------------
		private function iniciarBotoes():void {
			this.btnFechar.setStyle("icon", ConstantesComum.icoFechar);
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos campos.
		//--------------------------------------------------------------------------
		private function carregarDetalheRateio():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.id = this._idLancamento;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, "obterDadosLancamentoRateioDDA");
			servico.addEventListener(ResultEvent.RESULT, retornoCarregarDetalheRateio);
			servico.obterDadosLancamentoRateioDDA(dto);
		}
		private function retornoCarregarDetalheRateio(resultEvent:ResultEvent):void {
			var dto:VisualizaRateioTarifaDTO = resultEvent.result.dados["dto"];
			
			this.txtCooperativa.text = formataNumero(dto.numCooperativa);
			this.txtContaCorrente.text = FormatUtil.formataValor(dto.numContaLancamentoCCO, "##.###.##-#");
			this.txtIdRateioDDA.text = formataNumero(dto.idRateioDDA);
			this.txtSeqLancamento.text = dto.numSeqLancamentoCCO == 0 ? "": NumeroUtil.converterNumberParaString(dto.numSeqLancamentoCCO);
			this.txtValorTotal.text = new String("R$ "+NumeroUtil.converterNumeroDecimalParaMoeda(dto.valorTotalRateio));
			this.txtUsuário.text = dto.idUsuarioAprovacao;
			this.txtDataMovimento.text = dto.dataMovimentoLoteLancamentoCCO == null? "" : FormataData.formataData(dto.dataMovimentoLoteLancamentoCCO.data,"dd/MM/yyyy");
			this.txtDataLancamento.text = dto.dataHoraLancamentoCCO == null? "" :FormataData.formataDataHora(dto.dataHoraLancamentoCCO.data,"dd/MM/yyyy JJ:NN:SS");
			this.txtDescSituacaoLanc.text = dto.descSituacaoRateioLancamento;
			this.txtDescEventoTarifavel.text = dto.descEventoTarifavel;
			this.txtDescErroLancamento.text = dto.descErroLancamentoCCO==null ? "" :dto.codErroLancamentoCCO +" - " + dto.descErroLancamentoCCO;
			this._codSituacaoLancamento = dto.codSituacaoRateioLancamento;
			
			if(!_bolEditar && dto.idInstituicaoTransfDebito!=0){
				this.compCentralSingular.exibirCoopEspecifica = isNaN(dto.idInstituicaoTransfDebito) ? dto.idInstituicaoRateio : dto.idInstituicaoTransfDebito;
			}else{
				this.compCentralSingular.cmbProcurarCoopCentral = dto.idInstituicaoCentral;
				this.compCentralSingular.cmbProcurarInstSingular = isNaN(dto.idInstituicaoTransfDebito) || dto.idInstituicaoTransfDebito ==0 ? dto.idInstituicaoRateio : dto.idInstituicaoTransfDebito;
				
				this.compCentralSingular.cmbCentral.inserirItemOpcional = true;
				this.compCentralSingular.cmbCentral.labelItemOpcional="---";
				
				this.compCentralSingular.cmbSingular.inserirItemOpcional = true;
				this.compCentralSingular.cmbSingular.labelItemOpcional="---";
			}
			carregarDefinicaoComponente();
			
			if(dto.idInstituicaoRateio == 1 || dto.idInstituicaoRateio == 2){
				this.compCentralSingular.cmbCentral.enabled = false;
				this.compCentralSingular.cmbSingular.enabled = false;
			}
			
		}
		
		private function carregarDefinicaoComponente():void {
			this.compCentralSingular.carregarCombos();
			this.compCentralSingular.enabled = _bolEditar;
			this.compCentralSingular.cmbSingular.enabled = true;
			
			this.compCentralSingular.hboxCentral.x = -25;
			this.compCentralSingular.hboxSingular.x = 165;
			
			this.compCentralSingular.hboxSingular.y = this.compCentralSingular.hboxCentral.y;
			this.compCentralSingular.hboxSingular.x = this.compCentralSingular.hboxCentral.x + 200;
			
			this.compCentralSingular.hboxCentral.width = 200;
			this.compCentralSingular.hboxSingular.width = 200;
			
			this.compCentralSingular.hboxCentral.horizontalScrollPolicy = "off";
			this.compCentralSingular.hboxSingular.horizontalScrollPolicy = "off";
			
			this.compCentralSingular.mostrarUnidade = false;
			this.compCentralSingular.height = 30;
			
		}
		
		private function confirmacaoRealizaTransferenciaDebito(event:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao("Deseja transferir o débito da Cooperativa "+ this.txtCooperativa.text +" para a Cooperativa " +this.compCentralSingular.cmbSingular.selectedItem.numeroCooperativa + " ?", null, realizaTransferenciaDebito);
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos campos.
		//--------------------------------------------------------------------------
		private function realizaTransferenciaDebito(e:Event):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.id = this._idLancamento;
			dto.dados.numCooperativa = this.compCentralSingular.cmbSingular.selectedItem.numeroCooperativa;
			dto.dados.codSituacaoLancamento = this._codSituacaoLancamento;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_RATEIO_TARIFAS_CIP, "transferenciaDebito");
			servico.addEventListener(ResultEvent.RESULT, retornoRealizaTransferenciaDebito);
			servico.transferenciaDebito(dto);
		}

		private function retornoRealizaTransferenciaDebito(resultEvent:ResultEvent):void {
			var codMsgRetorno:Number = resultEvent.result.dados["codMsgRetorno"];
			if(ERRO_EFETIVACAO == codMsgRetorno){
				MensagensComum.exibirMensagemAlerta("O rateio não pode ser Efetivado com ajuste!");
				fechar();
			}else if(EFETIVADO_COM_AJUSTE == codMsgRetorno){
				MensagensComum.exibirMensagemSucesso("Rateio efetivado com ajuste!");
				fechar();
			}
		}

		//--------------------------------------------------------------------------
		//  Reseta comobo Singular e Central.
		//--------------------------------------------------------------------------
		private function restaCmbSingular(e:Event):void{
			if(this.compCentralSingular.cmbCentral.selectedIndex == 0){
				this.compCentralSingular.cmbSingular.selectedIndex = 0;	
			}
		}
		
		//--------------------------------------------------------------------------
		//  Desabilitar combo singular.
		//--------------------------------------------------------------------------
		private function enableSingular(e:Event):void {
			if(this.compCentralSingular.cmbCentral.selectedIndex == 0){
				this.compCentralSingular.cmbSingular.enabled = false;
			}else{
				this.compCentralSingular.cmbSingular.enabled = true;
			}
			enableTransferir();
		}
		//--------------------------------------------------------------------------
		//  Habilita e desabilita Transferir.
		//--------------------------------------------------------------------------		
		private function enableTransferir(e:Event = null):void {
			if(this.compCentralSingular.cmbSingular.selectedItem){
				this.btnTransferir.enabled = true;
			}else{
				this.btnTransferir.enabled = false;
			}
		}
		//--------------------------------------------------------------------------
		//  Habilita e desabilita Transferir.
		//--------------------------------------------------------------------------	
		public static function formataNumero(obj:Number):String{
			return zeroParaEsquerda(obj.toString(), 4);	
		}
		
		public static function zeroParaEsquerda (number:String, width:int):String {
			if (number.length < width)
				return "0" + zeroParaEsquerda(number, width-1);
			return number;
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