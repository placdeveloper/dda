package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.EventoTarifavelDTO", EventoTarifavelDTO);
	
	public class EventoTarifavelDTO
	{
		private var _idEventoTarifavelDDATarifa:Number;
		private var _codEventoTarifavel:Number;
		private var _descEventoTarifavelExtrato:String;
		private var _descEventoTarifavelManual:String;
		private var _descTipoEventoTarifavel:String;
		private var _dataInicioVigencia:IDateTime;
		private var _dataFimVigencia:IDateTime;
		private var _valorCIP:Number;
		private var _valorBancoob:Number;
		private var _status:String;
		private var _diaVencimento:Number;
		private var _bolTarifavel:Boolean;
		
		
		public function EventoTarifavelDTO()
		{
		}
		
		public function get codEventoTarifavel():Number {
			return _codEventoTarifavel;
		}
		
		public function set codEventoTarifavel(codEventoTarifavel:Number):void {
			this._codEventoTarifavel = codEventoTarifavel;
		}
		
		public function get descEventoTarifavelManual():String {
			return _descEventoTarifavelManual;
		}
		
		public function set descEventoTarifavelManual(descEventoTarifavelManual:String):void {
			this._descEventoTarifavelManual = descEventoTarifavelManual;
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
		
		public function get valorCIP():Number {
			return _valorCIP;
		}
		
		public function set valorCIP(valorCIP:Number):void {
			this._valorCIP = valorCIP;
		}
		
		public function get valorBancoob():Number {
			return _valorBancoob;
		}
		
		public function set valorBancoob(valorBancoob:Number):void {
			this._valorBancoob = valorBancoob;
		}
		
		public function get status():String {
			return _status;
		}
		
		public function set status(status:String):void {
			this._status = status;
		}
		
		public function get idEventoTarifavelDDATarifa():Number {
			return _idEventoTarifavelDDATarifa;
		}
		
		public function set idEventoTarifavelDDATarifa(idEventoTarifavelDDATarifa:Number):void {
			this._idEventoTarifavelDDATarifa = idEventoTarifavelDDATarifa;
		}
		
		public function get diaVencimento():Number {
			return _diaVencimento;
		}
		
		public function set diaVencimento(diaVencimento:Number):void {
			this._diaVencimento = diaVencimento;
		}
		
		public function get descTipoEventoTarifavel():String {
			return _descTipoEventoTarifavel;
		}
		
		public function set descTipoEventoTarifavel(descTipoEventoTarifavel:String):void {
			this._descTipoEventoTarifavel = descTipoEventoTarifavel;
		}
		
		public function get bolTarifavel():Boolean {
			return _bolTarifavel;
		}
		
		public function set bolTarifavel(bolTarifavel:Boolean):void {
			this._bolTarifavel = bolTarifavel;
		}
		
		public function get descEventoTarifavelExtrato():String {
			return _descEventoTarifavelExtrato;
		}
		
		public function set descEventoTarifavelExtrato(descEventoTarifavelExtrato:String):void {
			this._descEventoTarifavelExtrato = descEventoTarifavelExtrato;
		}
	}
}