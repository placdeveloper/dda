package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoGradeHorariaVO", TipoGradeHorariaVO);

	public class TipoGradeHorariaVO {

		private var _codTipoGradeHoraria:String;
		private var _descTipoGradeHoraria:String;
		
		private var _listaGradeHoraria:ArrayCollection;
		private var _tipoGradeHorariaOrigem:TipoGradeHorariaVO;
		private var _subTipoGrade:GradeHorariaVO;

		public function set codTipoGradeHoraria(codTipoGradeHoraria:String):void {
			this._codTipoGradeHoraria = codTipoGradeHoraria;
		}

		public function get codTipoGradeHoraria():String {
			return _codTipoGradeHoraria;
		}

		public function set descTipoGradeHoraria(descTipoGradeHoraria:String):void {
			this._descTipoGradeHoraria = descTipoGradeHoraria;
		}

		public function get descTipoGradeHoraria():String {
			return _descTipoGradeHoraria;
		}
		
		public function set tipoGradeHorariaOrigem(tipoGradeHorariaOrigem:TipoGradeHorariaVO):void {
			this._tipoGradeHorariaOrigem = tipoGradeHorariaOrigem;
		}
		
		public function get tipoGradeHorariaOrigem():TipoGradeHorariaVO {
			return _tipoGradeHorariaOrigem;
		}
		
		public function set listaGradeHoraria(listaGradeHoraria:ArrayCollection):void {
			this._listaGradeHoraria = listaGradeHoraria;
		}
		
		public function get listaGradeHoraria():ArrayCollection {
			return _listaGradeHoraria;
		}
		
		public function set subTipoGrade(subTipoGrade:GradeHorariaVO):void {
			this._subTipoGrade = subTipoGrade;
		}
		
		public function get subTipoGrade():GradeHorariaVO {
			return _subTipoGrade;
		}

	}
}