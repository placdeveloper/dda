package br.com.sicoob.sisbr.sicoobdda.comumflex.vo
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.RequisicaoCacheVO", RequisicaoCacheVO);

	public class RequisicaoCacheVO {
		
		private var _idRequisicaoCache:Number;
		private var _dataHoraRequisicao:IDateTime;
		private var _descCache:String;
		private var _servidorDDARequisitante:ServidorDDAVO;
		private var _servidorDDADestino:ServidorDDAVO;
		private var _listaAtualizacaoCacheServidor:ArrayCollection;
		
		public function set idRequisicaoCache(idRequisicaoCache:Number):void {
			this._idRequisicaoCache = idRequisicaoCache;
		}
		
		public function get idRequisicaoCache():Number {
			return _idRequisicaoCache;
		}

		public function set dataHoraRequisicao(dataHoraRequisicao:IDateTime):void {
			this._dataHoraRequisicao = dataHoraRequisicao;
		}
		
		public function get dataHoraRequisicao():IDateTime {
			return _dataHoraRequisicao;
		}
		
		public function set descCache(descCache:String):void {
			this._descCache = descCache;
		}
		
		public function get descCache():String {
			return _descCache;
		}
		
		public function set servidorDDARequisitante(servidorDDARequisitante:ServidorDDAVO):void {
			this._servidorDDARequisitante = servidorDDARequisitante;
		}
		
		public function get servidorDDARequisitante():ServidorDDAVO {
			return _servidorDDARequisitante;
		}
		
		public function set servidorDDADestino(servidorDDADestino:ServidorDDAVO):void {
			this._servidorDDADestino = servidorDDADestino;
		}
		
		public function get servidorDDADestino():ServidorDDAVO {
			return _servidorDDADestino;
		}
		
		public function set listaAtualizacaoCacheServidor(listaAtualizacaoCacheServidor:ArrayCollection):void {
			this._listaAtualizacaoCacheServidor = listaAtualizacaoCacheServidor;
		}
		
		public function get listaAtualizacaoCacheServidor():ArrayCollection {
			return _listaAtualizacaoCacheServidor;
		}
		
	}
}