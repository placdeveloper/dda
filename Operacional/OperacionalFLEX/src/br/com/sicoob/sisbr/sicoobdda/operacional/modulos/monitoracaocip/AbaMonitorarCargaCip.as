package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.monitoracaocip
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.LogErroCargaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataPesquisaUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;


	registerClassAlias("RegistroVO", RegistroVO);
	public class AbaMonitorarCargaCip extends AbaMonitorarCargaCipView
	{
		public function AbaMonitorarCargaCip() { 
			super();
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			this.iniciarCampos();
		}
		
		private function iniciarCampos():void{
			this.painelListaCarga.funcaoCriacaoDto = instanciarDtoConsulta;
			this.painelListaCarga.funcaoConfiguracaoDto = configurarDtoConsulta;
			this.painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaCarga.procuraSolicitada);
			this.painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarProcurar);
			this.painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			this.btnDetalharCarga.addEventListener(MouseEvent.CLICK, detalharCarga);
			this.listaErroCarga.grdDados.addEventListener(Event.RENDER, habilitarBotoes);
			this.painelFiltro.cmbTipoPessoa.addEventListener(ListEvent.CHANGE, handleCmbTipoPessoa);
			this.painelFiltro.cmbTipoPessoa.dataProvider = ConstantesComum.CPF_CNPJ.slice();
			
			painelFiltro.dataInicial.addEventListener(Event.CHANGE, limparConsulta);
			painelFiltro.dataFinal.addEventListener(Event.CHANGE, limparConsulta);
			this.habilitaCPF();
			
			barraBotoesListaCadastro.includeInLayout = false;
			barraBotoesListaCadastro.visible = false;
			barraBotoesListaCadastro.enabled = false;
		}
		
		private function detalharCarga(evt:Event):void {
			var logErroCargaDTO:LogErroCargaDTO = this.listaErroCarga.grdDados.selectedItem as LogErroCargaDTO;
			var tela:PopUpDetalharErroCarga = new PopUpDetalharErroCarga(logErroCargaDTO);
			JanelaCobranca.abrirJanela(this, tela, Constantes.DETALHAR_ERRO_CARGA);
		}
		
		private function configurarDtoConsulta(dto:ConsultaDto):void {	
			
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var logErroDTO:LogErroCargaDTO = new LogErroCargaDTO();
			
			if (painelFiltro.cmbTipoPessoa.selectedIndex == 0 && painelFiltro.inputCPF.valor != 0) {
				logErroDTO.numCPFCNPJ = painelFiltro.inputCPF.text;
			} else if (painelFiltro.cmbTipoPessoa.selectedIndex == 1 && painelFiltro.inputCnpj.valor != 0){
				logErroDTO.numCPFCNPJ = painelFiltro.inputCnpj.text;
			} else {
				logErroDTO.numCPFCNPJ = null;	
			}
			
			if (painelFiltro.dataFinal.selectedDate){
				logErroDTO.dataInicio = DateTimeBase.getDateTime(painelFiltro.dataInicial.selectedDate);
			}
			
			if (painelFiltro.dataFinal.selectedDate){
				logErroDTO.dataFim = DateTimeBase.getDateTime(painelFiltro.dataFinal.selectedDate);
			}
			
			reqDto.dados.dto = logErroDTO;
			dto.filtro = reqDto;
		}
		
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		private function validarProcurar(evt:ProcurarEvent):void {
			if(DataPesquisaUtil.validaPeriodoDataPesquisa(painelFiltro.dataInicial, painelFiltro.dataFinal)) {
				painelListaCarga.procuraSolicitada(evt);
			}
		}
		
		public function limparCampos(evt:Event = null):void {
			limparConsulta(evt);
			painelFiltro.dataInicial.selectedDate = null;
			painelFiltro.dataFinal.selectedDate = null;
			painelFiltro.inputCnpj.text = null;
			painelFiltro.inputCPF.text = null;
			btnDetalharCarga.enabled = false;
		}
		
		/**
		 * Limpa o resultado da consulta
		 */
		private function limparConsulta(evt:Event = null):void {
			listaErroCarga.limparConteudo();
			listaErroCarga.barraPaginacao.totalPaginas = listaErroCarga.barraPaginacao.pagina = 0;
		}
		
		
		private function habilitarBotoes(e:Event):void {
			if(listaErroCarga.grdDados.selectedItem) {
				this.listaErroCarga.barraPaginacao.enabled = true;
				this.btnDetalharCarga.enabled = true;
			} else {
				this.listaErroCarga.barraPaginacao.enabled = false;
				this.btnDetalharCarga.enabled = false;				
			}
		}
		
		private function habilitaCPF():void {
			this.painelFiltro.inputCPF.enabled = true;
			this.painelFiltro.inputCPF.visible = true;
			
			this.desabilitaCNPJ();
		}
		
		private function habilitaCNPJ():void {
			this.painelFiltro.inputCnpj.enabled = true;
			this.painelFiltro.inputCnpj.visible = true;
			
			this.desabilitaCPF();
		}
			
		private function desabilitaCPF():void {
			this.painelFiltro.inputCPF.enabled = false;
			this.painelFiltro.inputCPF.visible = false
			this.painelFiltro.inputCPF.text = "";	
		}
		
		private function desabilitaCNPJ():void {
			this.painelFiltro.inputCnpj.enabled = false;
			this.painelFiltro.inputCnpj.visible = false;
			this.painelFiltro.inputCnpj.text = "";
		}
		
		private function handleCmbTipoPessoa(e:ListEvent):void {
			if(this.painelFiltro.cmbTipoPessoa.selectedIndex == 0) {
				limparConsulta();
				this.habilitaCPF();
			} else {
				limparConsulta();
				this.habilitaCNPJ();
			}
		}
	}
}