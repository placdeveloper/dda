package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {
	
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TermoPagadorDTO", TermoPagadorDTO);
	
	public class TermoPagadorDTO {

		private var _idTermoPagador:Number;
		private var _codTipoTermoPagador:Number;
		private var _dataInicioVigencia:IDateTime;
		private var _dataFimVigencia:IDateTime;
		private var _bolFormatoHtml:Boolean;
		private var _descConteudoTermoPagador:String;
		private var _descTipoTermoPagador:String;
		
		public function get idTermoPagador():Number {
			return _idTermoPagador;
		}
		
		public function set idTermoPagador(idTermoPagador:Number):void {
			this._idTermoPagador = idTermoPagador;
		}
		
		public function get codTipoTermoPagador():Number {
			return _codTipoTermoPagador;
		}
		
		public function set codTipoTermoPagador(codTipoTermoPagador:Number):void {
			this._codTipoTermoPagador = codTipoTermoPagador;
		}
		
		public function get dataInicioVigencia():IDateTime {
			return _dataInicioVigencia;
		}
		
		public function set dataInicioVigencia(dataInicioVigencia:IDateTime):void {
			this._dataInicioVigencia = dataInicioVigencia;
		}
		
		public function get dataFimVigencia():IDateTime {
			return _dataFimVigencia;
		}
		
		public function set dataFimVigencia(dataFimVigencia:IDateTime):void {
			this._dataFimVigencia = dataFimVigencia;
		}

		public function get bolFormatoHtml():Boolean {
			return _bolFormatoHtml;
		}
		
		public function set bolFormatoHtml(bolFormatoHtml:Boolean):void {
			this._bolFormatoHtml = bolFormatoHtml;
		}
		public function get descConteudoTermoPagador():String {
			return _descConteudoTermoPagador;
		}
		
		public function set descConteudoTermoPagador(descConteudoTermoPagador:String):void {
			this._descConteudoTermoPagador = descConteudoTermoPagador;
		}
		
		public function get descTipoTermoPagador():String {
			return _descTipoTermoPagador;
		}
		
		public function set descTipoTermoPagador(descTipoTermoPagador:String):void {
			this._descTipoTermoPagador = descTipoTermoPagador;
		}
		
	}
	
}