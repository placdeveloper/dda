package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoTarifavelDDATarifaVO", EventoTarifavelDDATarifaVO);

	public class EventoTarifavelDDATarifaVO {
		
		private var _id:Number;
		private var _dataFimVigencia:IDateTime;
		private var _dataInicioVigencia:IDateTime;
		private var _idInstituicao:Number;
		private var _idUsuario:String;
		private var _valorBancoob:Number;
		private var _valorCIP:Number;
		private var _eventoTarifavelDDA:EventoTarifavelDDAVO;
		private var _tipoOperacaoLog:TipoOperacaoLogVO;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set dataFimVigencia(dataFimVigencia:IDateTime):void {
			this._dataFimVigencia = dataFimVigencia;
		}

		public function get dataFimVigencia():IDateTime {
			return _dataFimVigencia;
		}
		
		public function set dataInicioVigencia(dataInicioVigencia:IDateTime):void {
			this._dataInicioVigencia = dataInicioVigencia;
		}
		
		public function get dataInicioVigencia():IDateTime {
			return _dataInicioVigencia;
		}
		
		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set idUsuario(idUsuario:String):void {
			this._idUsuario = idUsuario;
		}
		
		public function get idUsuario():String {
			return _idUsuario;
		}
		
		public function set valorBancoob(valorBancoob:Number):void {
			this._valorBancoob = valorBancoob;
		}
		
		public function get valorBancoob():Number {
			return _valorBancoob;
		}
		
		public function set valorCIP(valorCIP:Number):void {
			this._valorCIP = valorCIP;
		}
		
		public function get valorCIP():Number {
			return _valorCIP;
		}

		public function set eventoTarifavelDDA(eventoTarifavelDDA:EventoTarifavelDDAVO):void {
			this._eventoTarifavelDDA = eventoTarifavelDDA;
		}
		
		public function get eventoTarifavelDDA():EventoTarifavelDDAVO {
			return _eventoTarifavelDDA;
		}
		
		public function set tipoOperacaoLog(tipoOperacaoLog:TipoOperacaoLogVO):void {
			this._tipoOperacaoLog = tipoOperacaoLog;
		}
		
		public function get tipoOperacaoLog():TipoOperacaoLogVO {
			return _tipoOperacaoLog;
		}

	}
}