package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.gradehoraria
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.controls.DateField;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.cadastro.FormularioCadastroView;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.componentes.validadores.ValidadorIgualdadeCampos;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.GradeHorariaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpManterItemGradeHoraria extends PopUpManterItemGradeHorariaView
	{
		private var listaGradeHorario:ArrayCollection = new ArrayCollection();
		private var itemGradeHorariaDTO:GradeHorariaDTO;
		private var _visualizacao:Boolean;
		private var _itemGradeHorariaDTOTemp:GradeHorariaDTO = new GradeHorariaDTO;
		
		private var _dataReferencia:Date;
		private var MODO_OPERACAO:Number;
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpManterItemGradeHoraria(itemGradeHorariaDTO:GradeHorariaDTO, dataReferencia:Date, modoOperacao:Number) {
			super();
			this._itemGradeHorariaDTOTemp = itemGradeHorariaDTO;
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this.itemGradeHorariaDTO = itemGradeHorariaDTO as GradeHorariaDTO;
			this._dataReferencia = dataReferencia;
			this.MODO_OPERACAO = modoOperacao;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			this.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			if(this.MODO_OPERACAO == 1){
				this.btConfirmar.addEventListener(MouseEvent.CLICK, validaTelaIncluir);
			}else{
				this.btConfirmar.addEventListener(MouseEvent.CLICK, validaTelaEditar);
			}
			this.btFechar.setStyle("icon", ConstantesComum.icoFechar);
			
			this.dataHoraAbertura.compDate.selectableRange = rangeDataAbertura();
			this.dataHoraFechamento.compDate.selectableRange = rangeDataFechamento();
			this.dataHoraAbertura.selectedDate = _dataReferencia;
			this.dataHoraFechamento.selectedDate = _dataReferencia;
			
			this.horaAbertura.dataProvider = popularComboHora();
			this.minutoAbertura.dataProvider = popularComboMinuto();
			this.horaFechamento.dataProvider = popularComboHora();
			this.minutoFechamento.dataProvider = popularComboMinuto();
		}
		
		private function validaTelaIncluir(event:Event):void {
			super.executarSeValido(addTelaIncluir);
		}
		
		private function validaTelaEditar(event:Event):void {
			super.executarSeValido(addTelaEdicao);
		}
		
		private function addTelaIncluir():void {
			if (this.pegaJanela().janelaPai is PopUpIncluirGradeHoraria) {
				(this.pegaJanela().janelaPai as PopUpIncluirGradeHoraria).adicionarNovoItem(popularCampos());
				limparCampos();
			}
		}
		
		private function addTelaEdicao():void {
			if (this.pegaJanela().janelaPai is PopUpEditarGradeHoraria) {
				(this.pegaJanela().janelaPai as PopUpEditarGradeHoraria).adicionarNovoItem(popularCampos());
				limparCampos();
			}
		}
		
		private function limparCampos():void {
			this.horaAbertura.selectedIndex = 0;
			this.horaFechamento.selectedIndex = 0;
			
			this.minutoAbertura.selectedIndex = 0;
			this.minutoFechamento.selectedIndex = 0;
		}
		
		private function popularCampos():GradeHorariaDTO { 
			var itemGrade:GradeHorariaDTO = new GradeHorariaDTO();
			itemGrade.dataHoraAberturaTemp = this.dataHoraAbertura.selectedDate;
			itemGrade.dataHoraFechamentoTemp = this.dataHoraAbertura.selectedDate;
			itemGrade.dataHoraAbertura = DateTimeBase.getDateTime(this.dataHoraAbertura.selectedDate);
			itemGrade.dataHoraFechamento = DateTimeBase.getDateTime(this.dataHoraFechamento.selectedDate);
			
				
			itemGrade.horaAbertura = this.horaAbertura.selectedLabel;
			itemGrade.minutoAbertura = this.minutoAbertura.selectedLabel;
			itemGrade.horaFechamento = this.horaFechamento.selectedLabel;
			itemGrade.minutoFechamento = this.minutoFechamento.selectedLabel;

			itemGrade.dataHoraAberturaTemp.hours= parseInt(itemGrade.horaAbertura);
			
			itemGrade.orderByDateTime = itemGrade.dataHoraAberturaTemp.time;
				
			itemGrade.dataHoraAberturaTemp.minutes = parseInt(itemGrade.minutoAbertura);
			itemGrade.dataHoraAbertura.data.minutes = parseInt(itemGrade.minutoAbertura);
			
			return itemGrade;
		}
		
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
		public function convertDataAbertura(obj:Object, dateField:DateField):void{
			var teste:GradeHorariaDTO = obj as GradeHorariaDTO;
			dateField.data = (obj as GradeHorariaDTO).dataHoraAbertura.data;
		}
		
		//--------------------------------------------------------------------------
		//  Determina o range de datas habilitadas
		//--------------------------------------------------------------------------
		public function rangeDataAbertura():Object{
			var dataTemp:Date = _dataReferencia;
			if(!dataTemp){
				dataHoraAbertura.compDate.selectableRange = null;
				dataHoraAbertura.compDate.disabledDays = popularDiasBloqueados();
				return dataHoraAbertura.compDate.selectableRange = null;
			}else{
				dataHoraAbertura.compDate.selectableRange = {rangeStart: new Date(_dataReferencia.getFullYear(),_dataReferencia.getMonth(),_dataReferencia.getDate()), 
					rangeEnd: new Date(_dataReferencia.getFullYear(),_dataReferencia.getMonth(), _dataReferencia.getDate()+1)};
				return dataHoraAbertura.compDate.selectableRange;
			}

		}
		
		public function rangeDataFechamento():Object{
			var dataTemp:Date = _dataReferencia;
			if(!dataTemp){
				dataHoraAbertura.compDate.selectableRange = null;
				dataHoraAbertura.compDate.disabledDays = popularDiasBloqueados();
				return dataHoraAbertura.compDate.selectableRange = null;
			}else{
				dataHoraAbertura.compDate.selectableRange = {rangeStart: new Date(_dataReferencia.getFullYear(),_dataReferencia.getMonth(),_dataReferencia.getDate()), 
					rangeEnd: new Date(_dataReferencia.getFullYear(),_dataReferencia.getMonth(), _dataReferencia.getDate()+1)};
				return dataHoraAbertura.compDate.selectableRange;
			}
			
		}
		
		//--------------------------------------------------------------------------
		//	Popula a quantidade de dias do Mês
		//--------------------------------------------------------------------------
		public function popularDiasBloqueados():Array{
			var minutos:Array  = new Array();
			for (var count:int = 0; count<= 30; count++) 
			{	
				minutos.push(count);
			}
			return minutos;
		}

		//--------------------------------------------------------------------------
		//	Seleciona os indices das combos de Horas/Minutos
		//--------------------------------------------------------------------------
		public function selecionaIndice(combo:Combo, obj:Object):void{
			if(obj!=null){
				combo.procuraItemData(obj);
				combo.editable = false;
				combo.inserirItemOpcional = true;
				combo.labelItemOpcional = "";
			}
		}
		
		//--------------------------------------------------------------------------
		//	Popula a lista de Horas
		//--------------------------------------------------------------------------
		public function popularComboHora():Array{
			var horas:Array  = new Array();
			for (var count:int = 0; count<= 23; count++) 
			{	
				var formataNumero:String = new String();
				if(count.toString().length == 1){
					formataNumero = "0"+count.toString();
				}else{
					formataNumero = count.toString();
				}
				horas.push({label:formataNumero, data:formataNumero});
			}
			return horas;
		}
		
		//--------------------------------------------------------------------------
		//	Popula a lista de Minutos
		//--------------------------------------------------------------------------
		public function popularComboMinuto():Array{
			var minutos:Array  = new Array();
			for (var count:int = 0; count<= 59; count++) 
			{	
				var formataNumero:String = new String();
				if(count.toString().length == 1){
					formataNumero = "0"+count.toString();
				}else{
					formataNumero = count.toString();
				}
				minutos.push({label:formataNumero, data:formataNumero});
			}
			return minutos;
		}
		

	}
}