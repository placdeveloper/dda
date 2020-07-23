package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoPessoaDDAAvalistaVO", TipoPessoaDDAAvalistaVO);

	public class TipoPessoaDDAAvalistaVO {

		private var _codTipoPessoaDDAAvalista:String;
		private var _descTipoPessoaDDAAvalista:String;

		public function set codTipoPessoaDDAAvalista(codTipoPessoaDDAAvalista:String):void {
			this._codTipoPessoaDDAAvalista = codTipoPessoaDDAAvalista;
		}

		public function get codTipoPessoaDDAAvalista():String {
			return _codTipoPessoaDDAAvalista;
		}

		public function set descTipoPessoaDDAAvalista(descTipoPessoaDDAAvalista:String):void {
			this._descTipoPessoaDDAAvalista = descTipoPessoaDDAAvalista;
		}

		public function get descTipoPessoaDDAAvalista():String {
			return _descTipoPessoaDDAAvalista;
		}

	}
}