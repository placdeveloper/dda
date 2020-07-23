package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipogradehoraria
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
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
	public class PopUpIncluir extends PopUpIncluirView
	{
		private var _atualizaPesquisa:Function;
		private var _isExisteTipoGradeHoraria:Boolean=false;
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpIncluir(funcaoRetorno:Function) {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this._atualizaPesquisa = funcaoRetorno;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			this.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btIncluir.addEventListener(MouseEvent.CLICK, verificaInclusao);
			this.cmbSubtipo.addEventListener(Event.CHANGE, habilitaComboGradeOrigem);
			this.descTipoGradeHoraria.addEventListener(MouseEvent.MOUSE_OVER, setToolTip);
			this.btLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			var janela:Janela =super.pegaJanela();
			janela.barraBotoes.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.cmbGradeOrigem.enabled = false;
			iniciarComponentes();
			carregarCombos();
			
		}
		
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------		
		private function iniciarComponentes():void {
			this.descTipoGradeHoraria.restrict = ConstantesComum.REGEX_CAMPO_DESCRICAO;
			this.btFechar.setStyle("icon", ConstantesComum.icoFechar);
		}

		//--------------------------------------------------------------------------
		//  Atualizar ToolTip.
		//--------------------------------------------------------------------------		
		private function setToolTip(event:Event):void {
			this.descTipoGradeHoraria.toolTip = descTipoGradeHoraria.text;
		}
		
		//--------------------------------------------------------------------------
		//  Fechar Janela.
		//--------------------------------------------------------------------------		
		private function fechar(event:Event):void {
			super.fecharJanela();
		}
		
		private function fechaRefazPesquisa(obj:Object = null):void {
			if(_atualizaPesquisa != null){
				this._atualizaPesquisa();
			}
			super.fecharJanela();
		}
		
		private function habilitaComboGradeOrigem(event:Event):void {
			if(cmbSubtipo.selectedItem == null || cmbSubtipo.selectedItem.codSubTipoGrade == 1){
				this.cmbGradeOrigem.enabled = false;
				this.cmbGradeOrigem.selectedIndex = 0;
			}else{
				this.cmbGradeOrigem.enabled = true;
				
			}
		}
		private function carregarCombos():void {
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_GRADE_HORARIA, "listarCombos");
			servico.addEventListener(ResultEvent.RESULT, retornoListarCombos);
			servico.listarCombos();
		}
		
		private function retornoListarCombos(event:ResultEvent):void {
			var tipoGradeHorariaDto:TipoGradeHorariaDTO = event.result.dados["retornoListarCombos"] as TipoGradeHorariaDTO;
			this.cmbSubtipo.dataProvider = tipoGradeHorariaDto.listaSubTipoGrade;
			this.cmbGradeOrigem.dataProvider = tipoGradeHorariaDto.listaCodigoTipoGradeHoraria;
		}
		
		private function verificaInclusao (event:Event):void{
			//Verifica se já existe um mesmo código
			if(StringUtil.trim(codTipoGradeHoraria.text).length > 0){
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.codTipoGradeHoraria = codTipoGradeHoraria.text;
				
				var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_GRADE_HORARIA, "isExisteTipoGradeHoraria");
				servico.addEventListener(ResultEvent.RESULT, incluir);
				servico.isExisteTipoGradeHoraria(dto);
			}else{
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Cód. Tipo Grade Horária"));
			}
			
		}
		
		private function incluir (event:ResultEvent):void{
				this._isExisteTipoGradeHoraria = event.result.dados["isExisteTipoGradeHoraria"];
				if(this._isExisteTipoGradeHoraria){
					MensagensComum.exibirMensagemAlerta(Mensagens.MSG052);
					return;
				}else if(StringUtil.trim(descTipoGradeHoraria.text).length <=0){
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Descrição"));
					return;
				}else if(!this._isExisteTipoGradeHoraria){
					var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
					
					var dto:TipoGradeHorariaDTO = new TipoGradeHorariaDTO();
					
					dto.codTipoGradeHoraria = codTipoGradeHoraria.text;
					dto.descTipoGradeHoraria = descTipoGradeHoraria.text;
					var subTipoGrade:SubTipoGradeVO = cmbSubtipo.selectedItem as SubTipoGradeVO
					var tipoGradeHorariaAux:TipoGradeHorariaVO = cmbGradeOrigem.selectedItem as TipoGradeHorariaVO
					
					if(tipoGradeHorariaAux != null){
						dto.codTipoGradeHorariaOrigem = cmbGradeOrigem.selectedLabel;
					}
					
					if(subTipoGrade != null){
						dto.codSubTipoGrade = subTipoGrade.codSubTipoGrade;
						if(subTipoGrade.codSubTipoGrade == 2 && tipoGradeHorariaAux == null){
							MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Grade Origem"));	
							return;
						}
					}else{
						MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Subtipo"));
						return;
					}
					
					reqDTO.dados.dto = dto;
					
					var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_GRADE_HORARIA, "incluirTipoGradeHoraria");
					servico.addEventListener(ResultEvent.RESULT, retorno);
					servico.incluirTipoGradeHoraria(reqDTO);
				}
		}
		
		private function limparCampos (event:MouseEvent):void{
			this.codTipoGradeHoraria.text = null;
			this.descTipoGradeHoraria.text = null;
			this.cmbSubtipo.selectedIndex = 0;
			this.cmbGradeOrigem.selectedIndex = 0;
			this.cmbGradeOrigem.enabled = false;
		}
		
		private function retorno (event:ResultEvent):void{
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(Mensagens.MSG056, "Tipo Grade Horária"),fechaRefazPesquisa);
		}
			
	}
}