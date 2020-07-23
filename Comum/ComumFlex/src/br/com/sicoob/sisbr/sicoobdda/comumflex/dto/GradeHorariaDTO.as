package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.GradeHorariaDTO", GradeHorariaDTO);
	
	public class GradeHorariaDTO
	{
		private var _idGradeHoraria:Number;
		private var _codTipoGradeHoraria:String;
		private var _descricaoTipoGrade:String;
		
		private var _dataReferencia:IDateTime;
		
		private var _dataHoraAbertura:IDateTime;
		private var _horaAbertura:String;
		private var _minutoAbertura:String;

		private var _dataHoraFechamento:IDateTime;
		private var _horaFechamento:String;
		private var _minutoFechamento:String;
		
		private var _gradesEmUso:Boolean;
		private var _tipoGradeHorariaDto:TipoGradeHorariaDTO;
		
		private var _listaGradeHoraria:ArrayCollection;
		private var _listaTipoGradeHoraria:ArrayCollection;
		
		private var _dataHoraAberturaTemp:Date;
		private var _dataHoraFechamentoTemp:Date;
		
		private var _orderByDateTime:Number;
		
		public function GradeHorariaDTO()
		{
		}
		
		public function get codTipoGradeHoraria():String {
			return _codTipoGradeHoraria;
		}
		
		public function set codTipoGradeHoraria(codTipoGradeHoraria:String):void {
			this._codTipoGradeHoraria = codTipoGradeHoraria;
		}
		
		public function get registroInicial():String {
			return _descricaoTipoGrade;
		}
		
		public function set descricaoTipoGrade(descricaoTipoGrade:String):void {
			this._descricaoTipoGrade = descricaoTipoGrade;
		}
		
		public function get dataHoraAbertura():IDateTime {
			return _dataHoraAbertura;
		}
		
		public function set dataHoraAbertura(dataHoraAbertura:IDateTime):void {
			this._dataHoraAbertura = dataHoraAbertura;
			if(dataHoraAbertura != null){
				this.dataHoraAberturaTemp = dataHoraAbertura.data;
			}
		}
		
		public function get dataHoraAberturaTemp():Date {
			return _dataHoraAberturaTemp;
		}
		
		public function set dataHoraAberturaTemp(dataHoraAberturaTemp:Date):void {
			this._dataHoraAberturaTemp = dataHoraAberturaTemp;
		}
		
		public function get horaAbertura():String {
			return _horaAbertura;
		}
		
		public function set horaAbertura(horaAbertura:String):void {
			this._horaAbertura = horaAbertura;
		}
		
		public function get minutoAbertura():String {
			return _minutoAbertura;
		}
		
		public function set minutoAbertura(minutoAbertura:String):void {
			this._minutoAbertura = minutoAbertura;
		}
		
		public function get dataHoraFechamento():IDateTime {
			return _dataHoraFechamento;
		}
		
		public function set dataHoraFechamento(dataHoraFechamento:IDateTime):void {
			this._dataHoraFechamento = dataHoraFechamento;
			if(dataHoraFechamento!= null){
				this.dataHoraFechamentoTemp = dataHoraFechamento.data;
			}
		}
		
		public function get dataHoraFechamentoTemp():Date {
			return _dataHoraFechamentoTemp;
		}
		
		public function set dataHoraFechamentoTemp(dataHoraFechamentoTemp:Date):void {
			this._dataHoraFechamentoTemp = dataHoraFechamentoTemp;
		}
		
		public function get horaFechamento():String {
			return _horaFechamento;
		}
		
		public function set horaFechamento(horaFechamento:String):void {
			this._horaFechamento = horaFechamento;
		}
		
		public function get minutoFechamento():String {
			return _minutoFechamento;
		}
		
		public function set minutoFechamento(minutoFechamento:String):void {
			this._minutoFechamento = minutoFechamento;
		}
	
		public function get gradesEmUso():Boolean {
			return _gradesEmUso;
		}
		
		public function set gradesEmUso(gradesEmUso:Boolean):void {
			this._gradesEmUso = gradesEmUso;
		}
		
		public function get dataReferencia():IDateTime {
			return _dataReferencia;
		}
		
		public function set dataReferencia(dataReferencia:IDateTime):void {
			this._dataReferencia = dataReferencia;
		}
		
		public function get tipoGradeHorariaDto():TipoGradeHorariaDTO {
			return _tipoGradeHorariaDto;
		}
		
		public function set tipoGradeHorariaDto(tipoGradeHorariaDto:TipoGradeHorariaDTO):void {
			this._tipoGradeHorariaDto = tipoGradeHorariaDto;
		}
		
		public function get idGradeHoraria():Number {
			return _idGradeHoraria;
		}
		
		public function set idGradeHoraria(idGradeHoraria:Number):void {
			this._idGradeHoraria = idGradeHoraria;
		}
		
		public function get listaGradeHoraria():ArrayCollection {
			return _listaGradeHoraria;
		}
		
		public function set listaGradeHoraria(listaGradeHoraria:ArrayCollection):void {
			this._listaGradeHoraria = listaGradeHoraria;
		}
		
		public function get listaTipoGradeHoraria():ArrayCollection {
			return _listaTipoGradeHoraria;
		}
		
		public function set listaTipoGradeHoraria(listaTipoGradeHoraria:ArrayCollection):void {
			this._listaTipoGradeHoraria = listaTipoGradeHoraria;
		}
		
		public function get orderByDateTime():Number {
			return _orderByDateTime;
		}
		
		public function set orderByDateTime(orderByDateTime:Number):void {
			this._orderByDateTime = orderByDateTime;
		}
		
	}
}