package br.com.sicoob.sisbr.sicoobdda.comumflex.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ServidorDDAVO", ServidorDDAVO);
	
	public class ServidorDDAVO {
		
		private var _codServidorDDA:String ;
		private var _descPerfil:String ;
		private var _dataHoraCadastro:IDateTime;
		private var _bolAtivo:Boolean;
		
		public function set codServidorDDA(codServidorDDA:String):void {
			this._codServidorDDA = codServidorDDA;
		}
		
		public function get codServidorDDA():String {
			return _codServidorDDA;
		}
		
		public function set descPerfil(descPerfil:String):void {
			this._descPerfil = descPerfil;
		}
		
		public function get descPerfil():String {
			return _descPerfil;
		}
		
		public function set dataHoraCadastro(dataHoraCadastro:IDateTime):void {
			this._dataHoraCadastro = dataHoraCadastro;
		}
		
		public function get dataHoraCadastro():IDateTime {
			return _dataHoraCadastro;
		}
		
		public function set bolAtivo(bolAtivo:Boolean):void {
			this._bolAtivo = bolAtivo;
		}
		
		public function get bolAtivo():Boolean {
			return _bolAtivo;
		}
	}
}