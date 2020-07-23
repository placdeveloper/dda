package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {
	
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.HistoricoPagadorEletronicoDTO", HistoricoPagadorEletronicoDTO);
	
	public class HistoricoPagadorEletronicoDTO {

		private var _codTipoTermoPagador:Number;
		private var _descTipoTermoPagador:String;
		private var _dataHoraTermoDDA:IDateTime;
		private var _idCanal:Number;
		private var _numCpfCnpjPagador:String;
		private var _nomePagador:String;
		private var _numCpfCnpjAgregado:String;
		private var _nomeAgregado:String;
		private var _selecionado:Boolean;
		
		public function get codTipoTermoPagador():Number {
			return _codTipoTermoPagador;
		}
		
		public function set codTipoTermoPagador(codTipoTermoPagador:Number):void {
			this._codTipoTermoPagador = codTipoTermoPagador;
		}
		
		public function get descTipoTermoPagador():String {
			return _descTipoTermoPagador;
		}
		
		public function set descTipoTermoPagador(descTipoTermoPagador:String):void {
			this._descTipoTermoPagador = descTipoTermoPagador;
		}
		
		public function get dataHoraTermoDDA():IDateTime {
			return _dataHoraTermoDDA;
		}
		
		public function set dataHoraTermoDDA(dataHoraTermoDDA:IDateTime):void {
			this._dataHoraTermoDDA = dataHoraTermoDDA;
		}
		
		public function get idCanal():Number {
			return _idCanal;
		}
		
		public function set idCanal(idCanal:Number):void {
			this._idCanal = idCanal;
		}

		public function get nomePagador():String {
			return _nomePagador;
		}
		
		public function set nomePagador(nomePagador:String):void {
			this._nomePagador = nomePagador;
		}
		public function get numCpfCnpjPagador():String {
			return _numCpfCnpjPagador;
		}
		
		public function set numCpfCnpjPagador(numCpfCnpjPagador:String):void {
			this._numCpfCnpjPagador = numCpfCnpjPagador;
		}
		
		public function get numCpfCnpjAgregado():String {
			return _numCpfCnpjAgregado;
		}
		
		public function set numCpfCnpjAgregado(numCpfCnpjAgregado:String):void {
			this._numCpfCnpjAgregado = numCpfCnpjAgregado;
		}
		
		public function get nomeAgregado():String {
			return _nomeAgregado;
		}
		
		public function set nomeAgregado(nomeAgregado:String):void {
			this._nomeAgregado = nomeAgregado;
		}
		
		public function get selecionado():Boolean {
			return _selecionado;
		}
		
		public function set selecionado(selecionado:Boolean):void {
			this._selecionado = selecionado;
		}
		
	}
	
}