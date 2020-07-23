package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes {
	
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.CentralSingularDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	
	/**
	 * Componente utilizado geralmente nas telas de relatório para selecionar as instituicoes (Centrais, singulares e unidades).
	 **/
	registerClassAlias("RegistroVO",RegistroVO)
	public class CentraisSingulares extends CentraisSingularesView {
		
		private var _funcaoRetorno:Function;
		private var _funcaoAtualizacao:Function;
		
		private var _mostrarUnidade:Boolean;
		private var _bolCentralChange:Boolean;
		private var _mostrarSingular:Boolean = true;
		private var _mostrarCentral:Boolean = true;
		
		private var _cmbProcurarCoopCentral:Number;
		private var _cmbProcurarCoopSingular:Number;
		
		private var _cmbProcurarInstCentral:Number;
		private var _cmbProcurarInstSingular:Number;
		
		private var _exibirCoopEspecifica:Number;
		
		
		/**
		 * Método Construtor
		 **/
		public function CentraisSingulares() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);	
		}
		
		private function init(event:FlexEvent):void {
			this.iniciarCampos();
			this.cmbCentral.addEventListener(ListEvent.CHANGE,listarSingularesChange); 
			this.cmbSingular.addEventListener(ListEvent.CHANGE,listarUnidades);
		}
		
		private function iniciarCampos():void {
			this.cmbCentral.visible = cmbCentral.includeInLayout = _mostrarCentral;
			this.cmbSingular.visible = cmbSingular.includeInLayout = _mostrarSingular;
			this.cmbUnidade.visible = cmbUnidade.includeInLayout = _mostrarUnidade;
			this.lblUnidade.visible = lblUnidade.includeInLayout = _mostrarUnidade;
		}
		
		public function carregarCombos():void {
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				var metodo:String = "listarCentralSingularUnidade";
					
				dto.dados.exibirPorAgenciaLogada = exibirPorAgenciaLogada;
				dto.dados.exibirCoopEspecifica = exibirCoopEspecifica; 
				var servico:ServicoJava = Funcoes.obterServico(ConstantesComum.SERVICO_CENTRAIS_SINGULARES, metodo);
				servico.addEventListener(ResultEvent.RESULT, resultadoCombos);
				servico.mensagemEspera = "Buscando definições do componente...";
				servico.listarCentralSingularUnidade(dto);
		}
		
		private function resultadoCombos(event:ResultEvent):void {
			var dto:CentralSingularDTO = event.result.dados.dto as CentralSingularDTO;
			
			definirCmbCentral(dto.listaCentral);
			definirCmbSingular(dto.listaSingular);
			definirCmbUnidade(dto.listaUnidade);
			
			if(!isNaN(cmbProcurarCoopCentral)){
				this.cmbCentral.procuraItemPorNome(cmbProcurarCoopCentral,"numeroCooperativa");
				this._exibirCoopEspecifica = this.cmbCentral.selectedItem.idInstituicao;
				this.listarSingulares();
			}
			
			if(!isNaN(cmbProcurarInstCentral)){
				this.cmbCentral.procuraItemPorNome(cmbProcurarInstCentral,"idInstituicao");
				this._exibirCoopEspecifica = this.cmbCentral.selectedItem.idInstituicao;
				this.listarSingulares();				
			}
			
			if (funcaoRetorno != null) {
				funcaoRetorno();
			}
		}
		
		private function procurarInstSingular(e:Event):void {
			if(!isNaN(cmbProcurarInstSingular)){
				this.cmbSingular.procuraItemPorNome(cmbProcurarInstSingular,"idInstituicao");
			}
		}
		
		private function definirCmbCentral(lista:ArrayCollection):void {
			definirCombo(cmbCentral, lista);
		}
		
		private function definirCmbSingular(lista:ArrayCollection):void {
			definirCombo(cmbSingular, lista);
		}
		
		private function definirCmbUnidade(lista:ArrayCollection):void {
			definirCombo(cmbUnidade, lista);
		}
		
		private function definirCombo(cmb:Combo, lista:ArrayCollection):void {
			cmb.dataProvider = lista;
			if (cmb.dropdown) {
				cmb.dropdown.dataProvider = lista;
			}
		}
		
		private function listarSingularesChange(evt:ListEvent = null):void {
			this._bolCentralChange = true;
			listarSingulares();
		}
		private function listarSingulares(evt:ListEvent = null):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			if(cmbCentral.selectedItem && _mostrarSingular){
				dto.dados.numeroCooperativa = cmbCentral.selectedItem.numeroCooperativa;
				dto.dados.exibirPorAgenciaLogada = exibirPorAgenciaLogada;
				if(!this._bolCentralChange){
					dto.dados.exibirCoopEspecifica = exibirCoopEspecifica;
				}
				var metodo:String = "listarSingulares";
				var servico:ServicoJava = Funcoes.obterServico(ConstantesComum.SERVICO_CENTRAIS_SINGULARES, metodo);
				servico.addEventListener(ResultEvent.RESULT, resultadoListInstituicoes);
				servico.mensagemEspera = "Buscando definições do componente...";
				servico.listarSingulares(dto);				
			}
		}
		
		private function resultadoListInstituicoes(event:ResultEvent):void {
			definirCmbSingular(event.result.dados.instituicoes);
			
			if(!isNaN(cmbProcurarCoopSingular) && !this._bolCentralChange){
				this.cmbSingular.procuraItemPorNome(cmbProcurarCoopSingular,"numeroCooperativa");
			}
			if(!isNaN(cmbProcurarInstSingular) && !this._bolCentralChange){
				this.cmbSingular.procuraItemPorNome(cmbProcurarInstSingular,"idInstituicao");
			}
			
			resultadoListPA(event);
			
			if (funcaoAtualizacao != null) {
				funcaoAtualizacao();
			}
		}
		
		private function listarUnidades(evt:Event = null):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			if(cmbSingular.selectedItem && _mostrarUnidade){
				dto.dados.numeroCooperativa = cmbSingular.selectedItem.numeroCooperativa;
				var metodo:String = "listarUnidades";
				var servico:ServicoJava = Funcoes.obterServico(ConstantesComum.SERVICO_CENTRAIS_SINGULARES, metodo);
				servico.addEventListener(ResultEvent.RESULT, resultadoListPA);
				servico.mensagemEspera = "Buscando definições do componente...";
				servico.listarUnidades(dto);
			}
		}
		
		private function resultadoListPA(event:ResultEvent):void {
			definirCmbUnidade(event.result.dados.unidades);
			
			if (funcaoAtualizacao != null) {
				funcaoAtualizacao();
			}
		}
		
		public function limparCombos():void {
			cmbCentral.selectedIndex = cmbSingular.selectedIndex = cmbUnidade.selectedIndex = 0;
			
			if (cmbCentral.dataProvider.length > 1) {
				listarSingulares();
			} else if (cmbSingular.dataProvider.length > 1 && _mostrarUnidade) {
				listarUnidades();
			}
		}
		
		public function set funcaoRetorno(funcaoRetorno:Function):void {
			this._funcaoRetorno = funcaoRetorno;
		}
		
		public function get funcaoRetorno():Function {
			return _funcaoRetorno;
		}
		
		public function set funcaoAtualizacao(funcaoAtualizacao:Function):void {
			this._funcaoAtualizacao = funcaoAtualizacao;
		}
		
		public function get funcaoAtualizacao():Function {
			return _funcaoAtualizacao;
		}
		
		public function set mostrarUnidade(mostrarUnidade:Boolean):void {
			this._mostrarUnidade = mostrarUnidade;
		}

		public function get cmbProcurarCoopCentral():Number {
			return _cmbProcurarCoopCentral;
		}
		
		public function set cmbProcurarCoopCentral(cmbProcurarCoopCentral:Number):void {
			this._cmbProcurarCoopCentral = cmbProcurarCoopCentral;
		}
		
		public function get cmbProcurarCoopSingular():Number {
			return _cmbProcurarCoopSingular;
		}
		
		public function set cmbProcurarCoopSingular(cmbProcurarCoopSingular:Number):void {
			this._cmbProcurarCoopSingular = cmbProcurarCoopSingular;
		}
		
		public function get cmbProcurarInstCentral():Number {
			return _cmbProcurarInstCentral;
		}
		
		public function set cmbProcurarInstCentral(cmbProcurarInstCentral:Number):void {
			this._cmbProcurarInstCentral = cmbProcurarInstCentral;
		}
		
		public function get cmbProcurarInstSingular():Number {
			return _cmbProcurarInstSingular;
		}
		
		public function set cmbProcurarInstSingular(cmbProcurarInstSingular:Number):void {
			this._cmbProcurarInstSingular = cmbProcurarInstSingular;
		}
		
		public function get exibirCoopEspecifica():Number {
			return _exibirCoopEspecifica;
		}
		
		public function set exibirCoopEspecifica(exibirCoopEspecifica:Number):void {
			this._exibirCoopEspecifica = exibirCoopEspecifica;
		}
		
	}
}