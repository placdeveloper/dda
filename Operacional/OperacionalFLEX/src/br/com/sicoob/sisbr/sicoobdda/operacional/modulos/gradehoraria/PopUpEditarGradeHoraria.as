package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.gradehoraria
{
	import flash.events.Event;
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
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.GradeHorariaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpEditarGradeHoraria extends PopUpEditarGradeHorariaView
	{
		private var listaGradeHorario:ArrayCollection = new ArrayCollection();
		private var dataReferenciaTemp:IDateTime;
		private var itemGradeHorariaDTO:GradeHorariaDTO;
		private var _atualizaPesquisa:Function;
		private var _visualizacao:Boolean;
		private var _itemGradeHorariaTemp:GradeHorariaDTO = new GradeHorariaDTO;
		
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			var janela:Janela =super.pegaJanela();
			janela.barraBotoes.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			this.btConfirmar.addEventListener(MouseEvent.CLICK, validarAlterarHoraria);
			this.btnRemover.addEventListener(MouseEvent.CLICK, removerItem);
			this.btnIncluir.addEventListener(MouseEvent.CLICK, incluirItemGradeHoraria);
			
			this.listaGradeHoraria.addEventListener(MouseEvent.CLICK, statusBtnOperacao);

			iniciarComponentes();
			carregarCampos();
		}
		
		
		//--------------------------------------------------------------------------
		//  Construtor edição.
		//--------------------------------------------------------------------------		
		private function iniciarComponentes():void {
			this.btFechar.setStyle("icon", ConstantesComum.icoFechar);
			
			this.btnRemover.enabled = false;
			
			if(this._visualizacao){
				this.listaGradeHoraria.editable;
				this.dataReferencia.enabled = false;
				this.cmbTipoGradeHoraria.enabled =false;
				this.btCancelar.visible = false;
				this.btConfirmar.visible = false;
				this.btnIncluir.visible = false;
				this.btnRemover.visible = false;
			}
		}
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpEditarGradeHoraria(itemGradeHorariaDTO:GradeHorariaDTO, funcaoRetorno:Function,visualizacao:Boolean) {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this.itemGradeHorariaDTO = itemGradeHorariaDTO as GradeHorariaDTO;
			this._atualizaPesquisa = funcaoRetorno;
			this._visualizacao = visualizacao;
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
			var tela:PopUpManterItemGradeHoraria = new PopUpManterItemGradeHoraria(itemGradeHoraria, dataReferencia.selectedDate, ConstantesComum.MODO_EDITAR);
			JanelaCobranca.abrirJanela(this, tela, "Incluir Grade Horária")
		}
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------		
		private function carregarCampos():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.codTipoGradeHoraria = this.itemGradeHorariaDTO.tipoGradeHorariaDto.codTipoGradeHoraria;
			dto.dados.idGradeHoraria = this.itemGradeHorariaDTO.idGradeHoraria;
			dto.dados.dto = this.itemGradeHorariaDTO;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_GRADE_HORARIA, "obterGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterGradeHoraria(dto);
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de callback.
		//--------------------------------------------------------------------------
		private function retornoConsulta(event:ResultEvent):void{
			var gradeHorariaDTO:GradeHorariaDTO = event.result.dados["lista"] as GradeHorariaDTO;
			dataReferencia.selectedDate = gradeHorariaDTO.dataReferencia.data;
			listaGradeHoraria.dataProvider = gradeHorariaDTO.listaGradeHoraria;
			cmbTipoGradeHoraria.dataProvider = gradeHorariaDTO.listaTipoGradeHoraria;
			if(gradeHorariaDTO.tipoGradeHorariaDto){
				cmbTipoGradeHoraria.procuraItemPorNome(gradeHorariaDTO.tipoGradeHorariaDto.codTipoGradeHoraria, "codTipoGradeHoraria");
			}
		}
		
		private function cancelar(event:MouseEvent):void{
			restaurarLista();
		}
		
		private function restaurarLista():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.codTipoGradeHoraria = this.itemGradeHorariaDTO.tipoGradeHorariaDto.codTipoGradeHoraria;
			dto.dados.idGradeHoraria = this.itemGradeHorariaDTO.idGradeHoraria;
			dto.dados.dto = this.itemGradeHorariaDTO;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_GRADE_HORARIA, "obterGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.bloquearOperacao = false;
			servico.showBusyCursor = false;
			servico.obterGradeHoraria(dto);
		}
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		private function validarAlterarHoraria(event:MouseEvent):void {
			if(validar()){
				pupularCampos();	
			}
		}
		
		private function pupularCampos():void {
			var gradeHorariaDto:GradeHorariaDTO = new GradeHorariaDTO;
			
			gradeHorariaDto.listaGradeHoraria = listaGradeHoraria.dataProvider as ArrayCollection;
			gradeHorariaDto.codTipoGradeHoraria = this.cmbTipoGradeHoraria.selectedItem.codTipoGradeHoraria;
			gradeHorariaDto.dataReferencia = DateTimeBase.getDateTime(this.dataReferencia.selectedDate);
			
			alterarHoraria(gradeHorariaDto);
		}
		
		private function alterarHoraria(gradeHorariaDto:GradeHorariaDTO):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = gradeHorariaDto;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_GRADE_HORARIA, "alterarGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, retornoAlteracao);
			servico.alterarGradeHoraria(dto);						
		}
		
		private function retornoAlteracao(event:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso("Alterado com sucesso!", fechar);
		}
		
		private function validar():Boolean {
			if(this.cmbTipoGradeHoraria.selectedItem == null){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Tipo Grade Horária"));
				return false;
			}
			if(this.dataReferencia.selectedDate == null){
				MensagensComum.exibirMensagemAlerta("Data referência deve ser preenchida");
				return false;
			}
			return true;
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
		
		//--------------------------------------------------------------------------
		//	Remove itens da lista
		//--------------------------------------------------------------------------
		public function removerItem(linha:Object):void{
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