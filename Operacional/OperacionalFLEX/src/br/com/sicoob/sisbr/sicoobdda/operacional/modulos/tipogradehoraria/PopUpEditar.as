package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipogradehoraria
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.TipoGradeHorariaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.SubTipoGradeVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoGradeHorariaVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpEditar extends PopUpEditarView
	{
		private var _atualizaPesquisa:Function;
		private var _visuzalizar:Boolean;
		private var tipoGradeHorariaDtoTemp:TipoGradeHorariaDTO;
		
		//--------------------------------------------------------------------------
		//  Construtor.
		//--------------------------------------------------------------------------
		public function PopUpEditar(codTipoGradeHoraria:String, funcaoRetorno:Function, visualizar:Boolean) {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this.obterTipoGradeHoraria(codTipoGradeHoraria);
			this._atualizaPesquisa = funcaoRetorno;
			this._visuzalizar = visualizar;
			
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			this.tipoGradeHorariaDtoTemp = new TipoGradeHorariaDTO;
			this.btFechar.addEventListener(MouseEvent.CLICK, fechaRefazPesquisa);
			this.descTipoGradeHoraria.addEventListener(Event.CHANGE, setToolTip);
			this.cmbSubtipo.addEventListener(Event.CHANGE, habilitaComboGradeOrigem);
			var janela:Janela =super.pegaJanela();
			codTipoGradeHoraria.editable = false;
			cmbSubtipo.enabled = false;
			janela.barraBotoes.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			if(_visuzalizar){
				descTipoGradeHoraria.editable = false;
				cmbGradeOrigem.enabled = false;
				this.btEditar.visible = false;
				this.btCancelar.visible = false;
			}
			this.btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			iniciarComponentes();
		}
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------		
		private function iniciarComponentes():void {
			this.btFechar.setStyle("icon", ConstantesComum.icoFechar);
			this.btEditar.addEventListener(MouseEvent.CLICK, salvarAlteracao);
			this.descTipoGradeHoraria.restrict = ConstantesComum.REGEX_CAMPO_DESCRICAO;
		}
		
		//--------------------------------------------------------------------------
		//  Atualizar ToolTip.
		//--------------------------------------------------------------------------		
		private function setToolTip(event:Event):void {
			this.descTipoGradeHoraria.toolTip = descTipoGradeHoraria.text;
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
		private function obterTipoGradeHoraria(codTipoGradeHoraria:String):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();

			dto.dados.codTipoGradeHoraria = codTipoGradeHoraria;
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_GRADE_HORARIA, "obterTipoGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterTipoGradeHoraria(dto);
		}
		
		private function retornoConsulta(event:ResultEvent):void {
			var tipoGradeHorariaDto:TipoGradeHorariaDTO = event.result.dados["retornoObterTipoGradeHoraria"] as TipoGradeHorariaDTO;
			this.tipoGradeHorariaDtoTemp = tipoGradeHorariaDto;
			codTipoGradeHoraria.text = tipoGradeHorariaDto.codTipoGradeHoraria;
			descTipoGradeHoraria.text = tipoGradeHorariaDto.descTipoGradeHoraria;
			cmbGradeOrigem.dataProvider = tipoGradeHorariaDto.listaCodigoTipoGradeHoraria;
			cmbSubtipo.dataProvider = tipoGradeHorariaDto.listaSubTipoGrade;
			
			this.descTipoGradeHoraria.toolTip = descTipoGradeHoraria.text;
			
			//Código tipo grade Origem igual != null carrega origem
			if(tipoGradeHorariaDto.codTipoGradeHorariaOrigem){
				cmbGradeOrigem.procuraItemPorNome(tipoGradeHorariaDto.codTipoGradeHorariaOrigem, "codTipoGradeHoraria"); 
			}
			//CodSubtipo != null carrega subtipo
			if(tipoGradeHorariaDto.codSubTipoGrade){
				//Codsubtipo == 1 combo origem null
				if(tipoGradeHorariaDto.codSubTipoGrade == 1){
					this.cmbGradeOrigem.enabled = false;
				}
				cmbSubtipo.procuraItemPorNome(tipoGradeHorariaDto.codSubTipoGrade, "codSubTipoGrade"); 
			}
		}
		
		private function cancelar(event:MouseEvent):void {
			var tipoGradeHorariaDto:TipoGradeHorariaDTO = this.tipoGradeHorariaDtoTemp;
			
			codTipoGradeHoraria.text = tipoGradeHorariaDto.codTipoGradeHoraria;
			descTipoGradeHoraria.text = tipoGradeHorariaDto.descTipoGradeHoraria;
			cmbGradeOrigem.dataProvider = tipoGradeHorariaDto.listaCodigoTipoGradeHoraria;
			cmbSubtipo.dataProvider = tipoGradeHorariaDto.listaSubTipoGrade;
			//Código tipo grade Origem igual != null carrega origem
			if(tipoGradeHorariaDto.codTipoGradeHorariaOrigem){
				cmbGradeOrigem.procuraItemPorNome(tipoGradeHorariaDto.codTipoGradeHorariaOrigem, "codTipoGradeHoraria"); 
			}
			//CodSubtipo != null carrega subtipo
			if(tipoGradeHorariaDto.codSubTipoGrade){
				//Codsubtipo == 1 combo origem null
				if(tipoGradeHorariaDto.codSubTipoGrade == 1){
					this.cmbGradeOrigem.enabled = false;
				}else{
					this.cmbGradeOrigem.enabled = true;
				}
				cmbSubtipo.procuraItemPorNome(tipoGradeHorariaDto.codSubTipoGrade, "codSubTipoGrade"); 
			}
		}
		
		private function habilitaComboGradeOrigem(event:Event):void {
			if(cmbSubtipo.selectedItem == null || cmbSubtipo.selectedItem.codSubTipoGrade == 1){
				this.cmbGradeOrigem.enabled = false;
				this.cmbGradeOrigem.selectedIndex = 0;
			}else{
				this.cmbGradeOrigem.enabled = true;
			}
		}
		
		private function carregarComboOrigem():void {
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_GRADE_HORARIA, "listarComboOrigem");
			servico.addEventListener(ResultEvent.RESULT, retornoConsultaCmbOrigem);
			servico.listarComboOrigem();
		}
		
		private function retornoConsultaCmbOrigem(event:ResultEvent):void {
			var dtoComboOrigem:TipoGradeHorariaDTO = event.result.dados["dto"] as TipoGradeHorariaDTO;
			this.cmbGradeOrigem.dataProvider = dtoComboOrigem.listaCodigoTipoGradeHoraria;
		}
		
		private function salvarAlteracao (event:Event):void{
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var dto:TipoGradeHorariaDTO = new TipoGradeHorariaDTO();
			
			dto.codTipoGradeHoraria = codTipoGradeHoraria.text;
			dto.descTipoGradeHoraria = descTipoGradeHoraria.text;
			var subTipoGrade:SubTipoGradeVO = cmbSubtipo.selectedItem as SubTipoGradeVO
			var tipoGradeHorariaAux:TipoGradeHorariaVO = cmbGradeOrigem.selectedItem as TipoGradeHorariaVO

			if(subTipoGrade != null){
				dto.codSubTipoGrade = subTipoGrade.codSubTipoGrade;
				if(subTipoGrade.codSubTipoGrade == 2 && tipoGradeHorariaAux == null){
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Grade Origem"));
					return;
				}
				if(subTipoGrade.codSubTipoGrade == 2){
					dto.codTipoGradeHorariaOrigem = tipoGradeHorariaAux.codTipoGradeHoraria;
				}
			}else{
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Subtipo"));
				return;
			}
			
			reqDTO.dados.dto = dto;
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_GRADE_HORARIA, "alterarTipoGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, retorno);
			servico.alterarTipoGradeHoraria(reqDTO);	
		}
		
		private function retorno (event:ResultEvent):void{
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(Mensagens.MSG057, "Tipo Grade Horária"),fechaRefazPesquisa);
		}
		
		
	}
}