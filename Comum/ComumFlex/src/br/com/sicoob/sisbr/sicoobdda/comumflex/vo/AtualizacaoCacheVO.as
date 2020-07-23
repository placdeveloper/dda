package br.com.sicoob.sisbr.sicoobdda.comumflex.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.AtualizacaoCacheVO", AtualizacaoCacheVO);
	
	public class AtualizacaoCacheVO extends BancoobVO {
		
		private var _idAtualizacaoCache:Number;
		private var _dataHoraAtualizacao:IDateTime;
		private var _requisicaoCache:RequisicaoCacheVO;
		private var _servidorDDA:ServidorDDAVO;
		
		public function set idAtualizacaoCache(idAtualizacaoCache:Number):void {
			this._idAtualizacaoCache = idAtualizacaoCache;
		}
		
		public function get idAtualizacaoCache():Number {
			return _idAtualizacaoCache;
		}
		
		public function set dataHoraAtualizacao(dataHoraAtualizacao:IDateTime):void {
			this._dataHoraAtualizacao = dataHoraAtualizacao;
		}
		
		public function get dataHoraAtualizacao():IDateTime {
			return _dataHoraAtualizacao;
		}
		
		public function set requisicaoCache(requisicaoCache:RequisicaoCacheVO):void {
			this._requisicaoCache = requisicaoCache;
		}
		
		public function get requisicaoCache():RequisicaoCacheVO {
			return _requisicaoCache;
		}
		
		public function set servidorDDA(servidorDDA:ServidorDDAVO):void {
			this._servidorDDA = servidorDDA;
		}
		
		public function get servidorDDA():ServidorDDAVO {
			return _servidorDDA;
		}
		
	}
}