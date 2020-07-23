package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {
	
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ValorParametroVO", ValorParametroVO);
	
	public class ValorParametroVO {
		
		private var _id:Number;
		private var _idInstituicao:Number;
		private var _dataCriacao:IDateTime;
		private var _valorParametro:String;
		private var _dataHoraUltimaAtualizacao:IDateTime;
		private var _idUsuarioResponsavel:String;
		private var _valorParametroCriacao:String;
		private var _parametro:ParametroVO;
		private var _ativo:Boolean;
		private var _descInstituicao:String;
		
		public function set id(id:Number):void {
			this._id = id;
		}
		
		public function get id():Number {
			return _id;
		}
		
		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set dataCriacao(dataCriacao:IDateTime):void {
			this._dataCriacao = dataCriacao;
		}
		
		public function get dataCriacao():IDateTime {
			return _dataCriacao;
		}
		
		public function set valorParametro(valorParametro:String):void {
			this._valorParametro = valorParametro;
		}
		
		public function get valorParametro():String {
			return _valorParametro;
		}
		
		public function set dataHoraUltimaAtualizacao(dataHoraUltimaAtualizacao:IDateTime):void {
			this._dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
		}
		
		public function get dataHoraUltimaAtualizacao():IDateTime {
			return _dataHoraUltimaAtualizacao;
		}
		
		public function set idUsuarioResponsavel(idUsuarioResponsavel:String):void {
			this._idUsuarioResponsavel = idUsuarioResponsavel;
		}
		
		public function get idUsuarioResponsavel():String {
			return _idUsuarioResponsavel;
		}
		
		public function set valorParametroCriacao(valorParametroCriacao:String):void {
			this._valorParametroCriacao = valorParametroCriacao;
		}
		
		public function get valorParametroCriacao():String {
			return _valorParametroCriacao;
		}
		
		public function set parametro(parametro:ParametroVO):void {
			this._parametro = parametro;
		}
		
		public function get parametro():ParametroVO {
			return _parametro;
		}
		
		public function set ativo(ativo:Boolean):void {
			this._ativo = ativo;
		}
		
		public function get ativo():Boolean {
			return _ativo;
		}
		
		public function set descInstituicao(descInstituicao:String):void {
			this._descInstituicao = descInstituicao;
		}
		
		public function get descInstituicao():String {
			return _descInstituicao;
		}
		
	}
}

