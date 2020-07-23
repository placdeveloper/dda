package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.PagadorDDAAgregadoVO", PagadorDDAAgregadoVO);

	public class PagadorDDAAgregadoVO {

		private var _id:Number;
		private var _pagadorDDA:PagadorDDAVO;
		
		private var _numCpfCnpjAgregado:String;
		
		private var _codTipoPessoaAgregado:String;


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
		
		public function set numCpfCnpjAgregado(numCpfCnpjAgregado:String):void {
			this._numCpfCnpjAgregado = numCpfCnpjAgregado;
		}
		
		public function get numCpfCnpjAgregado():String {
			return _numCpfCnpjAgregado;
		}
		
		public function set codTipoPessoaAgregado(codTipoPessoaAgregado:String):void {
			this._codTipoPessoaAgregado = codTipoPessoaAgregado;
		}
		
		public function get codTipoPessoaAgregado():String {
			return _codTipoPessoaAgregado;
		}
		
		
		
	}
}