package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoGradeHorariaDTO", TipoGradeHorariaDTO);
	
	public class TipoGradeHorariaDTO
	{
		private var _codTipoGradeHoraria:String;
		
		private var _descTipoGradeHoraria:String;
		
		private var _descSubTipoGrade:String;
		
		private var _codTipoGradeHorariaOrigem:String;
		
		private var _codSubTipoGrade:Number;
		
		private var _listaSubTipoGrade:ArrayCollection;
		
		private var _listaCodigoTipoGradeHoraria:ArrayCollection;    
		
		
		public function TipoGradeHorariaDTO()
		{
		}
		
		public function get codTipoGradeHoraria():String {
			return _codTipoGradeHoraria;
		}
		
		public function set codTipoGradeHoraria(codTipoGradeHoraria:String):void {
			this._codTipoGradeHoraria = codTipoGradeHoraria;
		}
		
		public function get descTipoGradeHoraria():String {
			return _descTipoGradeHoraria;
		}
		
		public function set descTipoGradeHoraria(descTipoGradeHoraria:String):void {
			this._descTipoGradeHoraria = descTipoGradeHoraria;
		}
		
		public function get descSubTipoGrade():String {
			return _descSubTipoGrade;
		}
		
		public function set descSubTipoGrade(descSubTipoGrade:String):void {
			this._descSubTipoGrade = descSubTipoGrade;
		}
		
		public function get codTipoGradeHorariaOrigem():String {
			return _codTipoGradeHorariaOrigem;
		}
		
		public function set codTipoGradeHorariaOrigem(codTipoGradeHorariaOrigem:String):void {
			this._codTipoGradeHorariaOrigem = codTipoGradeHorariaOrigem;
		}
		
		public function get listaSubTipoGrade():ArrayCollection {
			return _listaSubTipoGrade;
		}
		
		public function set listaSubTipoGrade(listaSubTipoGrade:ArrayCollection):void {
			this._listaSubTipoGrade = listaSubTipoGrade;
		}
		
		public function get listaCodigoTipoGradeHoraria():ArrayCollection {
			return _listaCodigoTipoGradeHoraria;
		}
		
		public function set listaCodigoTipoGradeHoraria(listaCodigoTipoGradeHoraria:ArrayCollection):void {
			this._listaCodigoTipoGradeHoraria = listaCodigoTipoGradeHoraria;
		}
		
		public function get codSubTipoGrade():Number {
			return _codSubTipoGrade;
		}
		
		public function set codSubTipoGrade(codSubTipoGrade:Number):void {
			this._codSubTipoGrade = codSubTipoGrade;
		}
	
	}
}