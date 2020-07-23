package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ParametroDTO", ParametroDTO);
	
	public class ParametroDTO {

		private var _listaParametro:ArrayCollection;
		private var _permiteAlterarTodos:Boolean;
		private var _idInstituicao:Number;
		
		public function set listaParametro(listaParametro:ArrayCollection):void {
			this._listaParametro = listaParametro;
		}
		
		public function get listaParametro():ArrayCollection {
			return _listaParametro;
		}
		
		public function set permiteAlterarTodos(permiteAlterarTodos:Boolean):void {
			this._permiteAlterarTodos = permiteAlterarTodos;
		}
		
		public function get permiteAlterarTodos():Boolean {
			return _permiteAlterarTodos;
		}
		
		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
	}
}