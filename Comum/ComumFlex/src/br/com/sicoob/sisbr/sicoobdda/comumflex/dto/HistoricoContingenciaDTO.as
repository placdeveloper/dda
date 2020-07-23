package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {
	
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.HistoricoContingenciaDTO", HistoricoContingenciaDTO);
	
	public class HistoricoContingenciaDTO {
		private var _dataSituacao:IDateTime;
		private var _situacaoContingencia:String;
		private var _tipoContingencia:String;
		private var _login:String;
		
		public function get dataSituacao():IDateTime {
			return _dataSituacao;
		}
		
		public function set dataSituacao(dataSituacao:IDateTime):void {
			this._dataSituacao = dataSituacao;
		}
		
		public function get situacaoContingencia():String {
			return _situacaoContingencia;
		}
		
		public function set situacaoContingencia(situacaoContingencia:String):void {
			this._situacaoContingencia = situacaoContingencia;
		}
		
		public function get tipoContingencia():String {
			return _tipoContingencia;
		}
		
		public function set tipoContingencia(tipoContingencia:String):void {
			this._tipoContingencia = tipoContingencia;
		}
		
		public function get login():String {
			return _login;
		}
		
		public function set login(login:String):void {
			this._login = login;
		}
		
	}
	
}