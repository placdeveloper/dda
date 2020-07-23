package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.eventotarifavel.manter
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.EventoTarifavelDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpManterEventoTarifavel extends PopUpManterEventoTarifavelView
	{
		private var _atualizaPesquisa:Function;
		private var _visuzalizar:Boolean;
		private var _eventoTarifarioTemp:EventoTarifavelDTO;
		
		private var _idEventoTarifavelDDATarifa:Number; 
		
		private static const MANTER_EVENTO_TARIFAVEL:String = "Manter Evento Tarifável";
		private static const STATUS_VENCIDO:String = "Vencido";
		
		//--------------------------------------------------------------------------
		//  Construtor.
		//--------------------------------------------------------------------------
		public function PopUpManterEventoTarifavel(idEventoTarifavelDDATarifa:Number, funcaoRetorno:Function, visualizar:Boolean) {
			super();
			this._atualizaPesquisa = funcaoRetorno;
			this._visuzalizar = visualizar;
			this._idEventoTarifavelDDATarifa = idEventoTarifavelDDATarifa;
			
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			/*
			 * Carrega consulta tipo de inclusão
			 * Carrega o o dia do parametro vigencia
			 * Seta a data de vigencia na de vigencia na lebel
			 * Seta o tipo alteração ou inclusao no subtitle
			 */
			this.iniciarEventos();
			this.obterEventoTarifavel();

			
		}
		
		private function iniciarEventos():void {
			this.btnFechar.addEventListener(MouseEvent.CLICK, fechaRefazPesquisa);
			var janela:Janela =super.pegaJanela();
			janela.barraBotoes.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btnCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			this.btnSalvar.addEventListener(MouseEvent.CLICK, validaObrigatorio);
		}
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------		
		private function iniciarComponentes():void {
			this.btnFechar.setStyle("icon", ConstantesComum.icoFechar);
		}

		//--------------------------------------------------------------------------
		//  Carrega campos que definem edição ou alteração.
		//--------------------------------------------------------------------------
		private function carregarCamposDefinacao():void {
		}
		
		private function retornoCarregarCamposDefinacao(e:Event):void {
			this.tituloFiltro.texto = MANTER_EVENTO_TARIFAVEL;
		}
		
		
		//--------------------------------------------------------------------------
		//  Fechar Janela e Atualizar lista.
		//--------------------------------------------------------------------------	
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
			
		}
		
		private function fechaRefazPesquisa(obj:Object = null):void {
			if(_atualizaPesquisa != null){
				this._atualizaPesquisa();
			}
			super.fecharJanela();
		}
		
		//--------------------------------------------------------------------------
		//  Obtem registro para Edição.
		//--------------------------------------------------------------------------	
		private function obterEventoTarifavel():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();

			dto.dados.idEventoTarifavelDDATarifa = _idEventoTarifavelDDATarifa;
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MANTER_EVENTO_TARIFAVEL, "obterEventoTarifavelDDATarifa");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterEventoTarifavelDDATarifa(dto);
		}
		
		private function retornoConsulta(event:ResultEvent):void {
			this._eventoTarifarioTemp = event.result.dados["dto"] as EventoTarifavelDTO;
			carregarCampos(this._eventoTarifarioTemp);
		}
		
		private function carregarCampos(eventoTarifarioDto:EventoTarifavelDTO):void {
			this._eventoTarifarioTemp = eventoTarifarioDto;
			this.descEventoTarifavel.text = eventoTarifarioDto.descEventoTarifavelManual;
			this.vlrCobradoCIP.text = FormataNumero.formata(eventoTarifarioDto.valorCIP, 2, false, "none");
			this.vlrCobradoBancoob.text = FormataNumero.formata(eventoTarifarioDto.valorBancoob, 2, false, "none"); 
			
			this.cmbDia.dataProvider = eventoTarifarioDto.diaVencimento;
			this.cmbMes.dataProvider = ConstantesComum.ARRAY_MESES;
			this.cmbMes.procuraItemData(eventoTarifarioDto.dataInicioVigencia.data.month);
			this.txtAno.valor = eventoTarifarioDto.dataInicioVigencia.data.fullYear;
			this.bloquearCampos(eventoTarifarioDto.status);
		}
		
		private function bloquearCampos(status:String):void {
			if(status == STATUS_VENCIDO){
				this.vlrCobradoCIP.enabled = false;
				this.vlrCobradoBancoob.enabled = false; 
				this.cmbDia.enabled = false;
				this.cmbMes.enabled = false;
				this.txtAno.enabled = false;		
				this.btnSalvar.visible = this.btnSalvar.includeInLayout = false;
				this.btnCancelar.visible= this.btnCancelar.includeInLayout = false;
			}
		}
		
		private function carregarDefinacaoCombos():void {
			this.cmbMes.procuraItemData(this._eventoTarifarioTemp.dataInicioVigencia.data.month);
			this.txtAno.valor = this._eventoTarifarioTemp.dataInicioVigencia.data.fullYear;
		}

		private function cancelar(event:MouseEvent):void {
			carregarCampos(_eventoTarifarioTemp);
		}

		private function salvarAlteracao ():void{
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			var dto:EventoTarifavelDTO = new EventoTarifavelDTO();
			
			dto.idEventoTarifavelDDATarifa = this._idEventoTarifavelDDATarifa;
			dto.valorCIP = this.vlrCobradoCIP.valor;
			dto.valorBancoob = this.vlrCobradoBancoob.valor;
			dto.dataInicioVigencia = DateTimeBase.getDateTime(montaDataInicioVigencia());
			dto.codEventoTarifavel = _eventoTarifarioTemp.codEventoTarifavel;
			reqDTO.dados.dto = dto;

			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MANTER_EVENTO_TARIFAVEL, "manterEventoTarifavel");
			servico.addEventListener(ResultEvent.RESULT, retorno);
			servico.manterEventoTarifavel(reqDTO);	
		}
		
		private function validaObrigatorio(even:MouseEvent):void{
			var validaObrigatorio:Boolean;
			if(!cmbMes.selectedItem || stringIsNull(txtAno.text)){
				//Combos não pode ter datas nulas.
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG013, "Data Vigência"));
				return;
			}else if(stringIsNull(vlrCobradoCIP.text)){
				//Ao menos uma tarifa precisa ser preenchida
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG013, "Valor Cobrado CIP"));
				return;
			}else if(stringIsNull(vlrCobradoBancoob.text)){
				//Ao menos uma tarifa precisa ser preenchida
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG013, "Valor Cobrado Sicoob/Bancoob"));
				return;
			}else{
				salvarAlteracao();
			}
		}
		
		private function stringIsNull(str:String):Boolean{
			return str== null || StringUtil.trim(str) == "" ? true : false;
		}
		
		private function montaDataInicioVigencia():Date{
			var dia:Number = new Number(cmbDia.selectedLabel);
			var mes:Number = cmbMes.selectedItem.data;
			var ano:Number = txtAno.valor;
			var data:Date = new Date(ano, mes, dia);
			return data;
		}
		
		private function retorno (event:ResultEvent):void{
			var tipoOperacao:String = event.result.dados["tipoOperacao"];
			if(tipoOperacao=="I"){
				MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(Mensagens.MSG056, "Evento Tarifável"),fechaRefazPesquisa);
			}else if(tipoOperacao=="A"){
				MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(Mensagens.MSG057, "Evento Tarifável"),fechaRefazPesquisa);	
			}
		}
		
		private function listaAnos():Array{
			var data:Date = new Date();
			var anoInicio:Number = data.getFullYear();
			var anoFim:Number = (data.getFullYear() + 50);
			var countAno:Number = 1;
			var listaAnos:Array = new Array();
			for(anoInicio; anoInicio <= anoFim ; anoInicio++){
				listaAnos.push({label:anoInicio, data:anoInicio});
			}
			return listaAnos;
		}
	}
}