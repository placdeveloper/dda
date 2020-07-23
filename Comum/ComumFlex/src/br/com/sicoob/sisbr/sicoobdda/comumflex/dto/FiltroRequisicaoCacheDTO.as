package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.FiltroRequisicaoCacheDTO", FiltroRequisicaoCacheDTO);
	
	public class FiltroRequisicaoCacheDTO {
		
		private var _codServidorDestino:String;
		private var _descCache:String;
		private var _descPerfilDestino:String;
		private var _dataRequisicao:IDateTime;
		
		public function set codServidorDestino(codServidorDestino:String):void {
			this._codServidorDestino = codServidorDestino;
		}
		
		public function get codServidorDestino():String {
			return _codServidorDestino;
		}
		
		public function set descCache(descCache:String):void {
			this._descCache = descCache;
		}
		
		public function get descCache():String {
			return _descCache;
		}
		
		public function set descPerfilDestino(descPerfilDestino:String):void {
			this._descPerfilDestino = descPerfilDestino;
		}
		
		public function get descPerfilDestino():String {
			return _descPerfilDestino;
		}
		
		public function set dataRequisicao(dataRequisicao:IDateTime):void {
			this._dataRequisicao = dataRequisicao;
		}
		
		public function get dataRequisicao():IDateTime {
			return _dataRequisicao;
		}
		
	}
}