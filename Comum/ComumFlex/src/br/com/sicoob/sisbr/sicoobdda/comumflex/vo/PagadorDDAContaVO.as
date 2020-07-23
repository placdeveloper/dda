package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.PagadorDDAContaVO", PagadorDDAContaVO);

	public class PagadorDDAContaVO {

		private var _id:Number;
		private var _pagadorDDA:PagadorDDAVO;
		
		private var _numAgencia:Number;
		
		private var _numContaCorrente:Number;
		
		private var _dataHoraAdesao:IDateTime;


		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}
		
		
		public function set pagadorDDA(pagadorDDA:PagadorDDAVO):void {
			this._pagadorDDA = pagadorDDA;
		}
		
		public function get pagadorDDA():PagadorDDAVO {
			return _pagadorDDA;
		}
		
		public function set numAgencia(numAgencia:Number):void {
			this._numAgencia = numAgencia;
		}
		
		public function get numAgencia():Number {
			return _numAgencia;
		}
		
		public function set numContaCorrente(numContaCorrente:Number):void {
			this._numContaCorrente = numContaCorrente;
		}
		
		public function get numContaCorrente():Number {
			return _numContaCorrente;
		}
		
		public function set dataHoraAdesao(dataHoraAdesao:IDateTime):void {
			this._dataHoraAdesao = dataHoraAdesao;
		}
		
		public function get dataHoraAdesao():IDateTime {
			return _dataHoraAdesao;
		}
		
		
	}
}