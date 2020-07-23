package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {
	
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
						
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.CentralSingularDTO", CentralSingularDTO);
	
	public class CentralSingularDTO {
		
		private var _listaCentral:ArrayCollection;
		private var _listaSingular:ArrayCollection;
		private var _listaUnidade:ArrayCollection;
		private var _codGrauInstituicao:Number;
		
		public function set listaCentral(listaCentral:ArrayCollection):void {
			this._listaCentral = listaCentral;
		}
		
		public function get listaCentral():ArrayCollection {
			return _listaCentral;
		}
		
		public function set listaSingular(listaSingular:ArrayCollection):void {
			this._listaSingular = listaSingular;
		}
		
		public function get listaSingular():ArrayCollection {
			return _listaSingular;
		}
		
		public function set listaUnidade(listaUnidade:ArrayCollection):void {
			this._listaUnidade = listaUnidade;
		}
		
		public function get listaUnidade():ArrayCollection {
			return _listaUnidade;
		}
		
		public function set codGrauInstituicao(codGrauInstituicao:Number):void {
			this._codGrauInstituicao = codGrauInstituicao;
		}
		
		public function get codGrauInstituicao():Number {
			return _codGrauInstituicao;
		}
		
	}
}

