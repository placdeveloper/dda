package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.gradehoraria
{
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.GradeHorariaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpIncluirGradeHoraria extends PopUpIncluirGradeHorariaView
	{
		private var listaGradeHorarioTemp:ArrayCollection = new ArrayCollection();
		private var _dataReferenciaTemp:Date = null;
		private var itemGradeHorariaDTO:GradeHorariaDTO;
		private var _atualizaPesquisa:Function;
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpIncluirGradeHoraria(funcaoRetorno:Function) {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this.itemGradeHorariaDTO = itemGradeHorariaDTO as GradeHorariaDTO;
			this._atualizaPesquisa = funcaoRetorno;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			var janela:Janela =super.pegaJanela();
			janela.barraBotoes.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			this.btConfirmar.addEventListener(MouseEvent.CLICK, validarGradeHoraria);
			this.btnRemover.addEventListener(MouseEvent.CLICK, removerItem);
			this.btnIncluir.addEventListener(MouseEvent.CLICK, incluirItemGradeHoraria);
			
			this.dataReferencia.addEventListener(FocusEvent.FOCUS_OUT, limparGrid);
			
			this.listaGradeHoraria.addEventListener(MouseEvent.CLICK, statusBtnOperacao);
			
			iniciarComponentes();
			carregarCampos();

		}
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------		
		private function iniciarComponentes():void {
			this.btFechar.setStyle("icon", ConstantesComum.icoFechar);
			
			this.dataReferencia.compDate.editable = true;
			this.btnIncluir.enabled = false;
			this.btnRemover.enabled = false;
		}
		
		//--------------------------------------------------------------------------
		//  Adiciona o novo item a Grid por meio da janela Filha e o ordena 
		//--------------------------------------------------------------------------		
		public function adicionarNovoItem(itemGrade:GradeHorariaDTO):void {
			if(listaGradeHoraria.dataProvider == null){
				listaGradeHoraria.dataProvider = new ArrayCollection();
			}
			listaGradeHoraria.dataProvider.addItem(itemGrade);
			
			ordernaLista();
		}

		//--------------------------------------------------------------------------
		//  Ordena a lista por data de abertura.
		//--------------------------------------------------------------------------
		private function ordernaLista():void{
			var sortFieldData:SortField = new SortField();
			sortFieldData.name = "orderByDateTime";
			sortFieldData.numeric = true;    
			
			var sort:Sort = new Sort();
			sort.fields = [sortFieldData];
			
			if(listaGradeHoraria.dataProvider != null){
				(listaGradeHoraria.dataProvider as ArrayCollection).sort = sort;
				(listaGradeHoraria.dataProvider as ArrayCollection).refresh();  
			}                          
		}
		
		//--------------------------------------------------------------------------
		//  Abre janela de adição de Grades.
		//--------------------------------------------------------------------------
		private function incluirItemGradeHoraria(event:Event):void {
			var itemGradeHoraria:GradeHorariaDTO = listaGradeHoraria.selectedItem as GradeHorariaDTO;
			var tela:PopUpManterItemGradeHoraria = new PopUpManterItemGradeHoraria(itemGradeHoraria, dataReferencia.selectedDate, ConstantesComum.MODO_INCLUIR);
			JanelaCobranca.abrirJanela(this, tela, "Incluir Grade Horária")
		}
		
		//--------------------------------------------------------------------------
		//  Limpa a grid por meio da data de Referencia 
		//--------------------------------------------------------------------------
		private function limparGrid(event:Event):void {
			if(dataReferencia.selectedDate){
				this.btnIncluir.enabled = true;
				if(this._dataReferenciaTemp){
					var numData:Number = DataUtil.compareData(this._dataReferenciaTemp, dataReferencia.selectedDate);
					if (numData == 0) {
						return;
					}else{
						this._dataReferenciaTemp  = dataReferencia.selectedDate;
					}
				}else{
					this._dataReferenciaTemp  = dataReferencia.selectedDate;
				}
			}else{
				this.btnIncluir.enabled = false;
				this.btnRemover.enabled = false;
			}
			this.listaGradeHoraria.dataProvider = null;	
		}
		
		//--------------------------------------------------------------------------
		//  Limpa a grid por meio do BtnLimpar 
		//--------------------------------------------------------------------------
		private function limparCampos(event:MouseEvent):void {
			this.dataReferencia.dataDefault = null;
			this.dataReferencia.selectedDate = null;
			this._dataReferenciaTemp = null;
			this.cmbTipoGradeHoraria.selectedIndex = 0;
			this.listaGradeHoraria.dataProvider = null;
			this.btnIncluir.enabled = false;
			this.btnRemover.enabled = false;
		}
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		private function validarGradeHoraria(event:MouseEvent):void {
			if(validar()){
				pupularCampos();	
			}
		}
		
		//--------------------------------------------------------------------------
		//  Popula os campos no DTO 
		//--------------------------------------------------------------------------
		private function pupularCampos():void {
			var gradeHorariaDto:GradeHorariaDTO = new GradeHorariaDTO;
			
			gradeHorariaDto.listaGradeHoraria = listaGradeHoraria.dataProvider as ArrayCollection;
			gradeHorariaDto.codTipoGradeHoraria = this.cmbTipoGradeHoraria.selectedItem.codTipoGradeHoraria;
			gradeHorariaDto.dataReferencia = DateTimeBase.getDateTime(this.dataReferencia.selectedDate);
			
			gravarGradeHoraria(gradeHorariaDto);
		}
		
		//--------------------------------------------------------------------------
		//  Chamada do serviço de gravação 
		//--------------------------------------------------------------------------
		private function gravarGradeHoraria(gradeHorariaDto:GradeHorariaDTO):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = gradeHorariaDto;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_GRADE_HORARIA, "incluirGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, retornoGravacao);
			servico.incluirGradeHoraria(dto);						
		}
		
		private function retornoGravacao(event:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso("Gravado com sucesso!", fechar);
		}
		
		//--------------------------------------------------------------------------
		//  Validação 
		//--------------------------------------------------------------------------
		private function validar():Boolean {
			if(this.cmbTipoGradeHoraria.selectedItem == null){
				MensagensComum.exibirMensagemAlerta("Tipo Grade Horária deve ser preenchida");
				return false;
			}else if(this.dataReferencia.selectedDate == null){
				MensagensComum.exibirMensagemAlerta("Data referência deve ser preenchida");
				return false;
			}else{
				return true;
			}
		}
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------		
		private function carregarCampos():void {
			comboTipoGradeHoraria();
		}
		
		//--------------------------------------------------------------------------
		//  Carrega lista Tipo Grade Horária da combo.
		//--------------------------------------------------------------------------
		private function comboTipoGradeHoraria(evt:Event = null):void{
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_GRADE_HORARIA, "listarTipoGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, carregarComboTipoGrade);
			servico.listarTipoGradeHoraria();
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de callback.
		//--------------------------------------------------------------------------
		private function carregarComboTipoGrade(event:ResultEvent):void{
			this.cmbTipoGradeHoraria.dataProvider = event.result.dados["lista"];
		}
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(obj:Object = null):void {
			if(_atualizaPesquisa != null){
				this._atualizaPesquisa();
			}
			super.fecharJanela();
		}
		
		//Remove itens da lista
		public function removerItem(event:Event):void{
			listaGradeHoraria.dataProvider.removeItemAt(listaGradeHoraria.selectedIndex);
			btnRemover.enabled = false;
		}
		
		//--------------------------------------------------------------------------
		//  Define Status dos Botões de Operacao por seleção de item da grid
		//--------------------------------------------------------------------------
		public function statusBtnOperacao(event:Event):void{
				if(listaGradeHoraria.selectedItem){
					btnRemover.enabled = true
				}else{
					btnRemover.enabled = false;
				}
		}
		
	}
}