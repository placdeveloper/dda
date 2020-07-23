package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SubTipoGradeVO", SubTipoGradeVO);

	public class SubTipoGradeVO {

		private var _codSubTipoGrade:Number;
		private var _descSubTipoGrade:String;
		
		private var _listaTipoGradeHoraria:ArrayCollection;

		public function set codSubTipoGrade(codSubTipoGrade:Number):void {
			this._codSubTipoGrade = codSubTipoGrade;
		}

		public function get codSubTipoGrade():Number {
			return _codSubTipoGrade;
		}

		public function set descSubTipoGrade(descSubTipoGrade:String):void {
			this._descSubTipoGrade = descSubTipoGrade;
		}

		public function get descSubTipoGrade():String {
			return _descSubTipoGrade;
		}
		
		public function set listaTipoGradeHoraria(listaTipoGradeHoraria:ArrayCollection):void {
			this._listaTipoGradeHoraria = listaTipoGradeHoraria;
		}
		
		public function get listaTipoGradeHoraria():ArrayCollection {
			return _listaTipoGradeHoraria;
		}

	}
}