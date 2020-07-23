package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.GradeHorariaVO", GradeHorariaVO);

	public class GradeHorariaVO {

		private var _id:Number;
		private var _dataReferencia:IDateTime;
		private var _dataHoraAbertura:IDateTime;
		private var _dataHoraFechamento:IDateTime;
		
		private var _tipoGradeHoraria:TipoGradeHorariaVO;
		
		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set descTipoGradeHoraria(dataReferencia:IDateTime):void {
			this._dataReferencia = dataReferencia;
		}

		public function get dataReferencia():IDateTime {
			return _dataReferencia;
		}
		
		public function set dataHoraAbertura(dataHoraAbertura:IDateTime):void {
			this._dataHoraAbertura = dataHoraAbertura;
		}
		
		public function get dataHoraAbertura():IDateTime {
			return _dataHoraAbertura;
		}
		
		public function set dataHoraFechamento(dataHoraFechamento:IDateTime):void {
			this._dataHoraFechamento = dataHoraFechamento;
		}
		
		public function get dataHoraFechamento():IDateTime {
			return _dataHoraFechamento;
		}
		public function set tipoGradeHoraria(tipoGradeHoraria:TipoGradeHorariaVO):void {
			this._tipoGradeHoraria = tipoGradeHoraria;
		}
		
		public function get tipoGradeHoraria():TipoGradeHorariaVO {
			return _tipoGradeHoraria;
		}

	}
}