package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {
	
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ParametroVO", ParametroVO);
	
	public class ParametroVO {
		
		private var _id:Number;
		private var _nomeParametro:String;
		private var _tipoParametro:TipoParametroVO;
		private var _bolVisivelFuncionalidadeAlteracao:Boolean;
		private var _bolPermiteAlteracaoPeloUsuario:Boolean;
		private var _bolAtivo:Boolean;
		private var _dataCriacao:IDateTime;
		private var _dataHoraUltimaAtualizacao:IDateTime;
		private var _descParametro:String;
		private var _valorParametro:String;
		private var _valorParametroTexto:String;
		private var _nomeTabelaDominio:String;
		private var _bolParametroGlobal:Boolean;
		private var _idParametroLegado:Number;
		private var _listaValorParametro:ArrayCollection;
		
		public function set id(id:Number):void {
			this._id = id;
		}
		
		public function get id():Number {
			return _id;
		}
		
		public function set nomeParametro(nomeParametro:String):void {
			this._nomeParametro = nomeParametro;
		}
		
		public function get nomeParametro():String {
			return _nomeParametro;
		}
		
		public function set tipoParametro(tipoParametro:TipoParametroVO):void {
			this._tipoParametro = tipoParametro;
		}
		
		public function get tipoParametro():TipoParametroVO {
			return _tipoParametro;
		}
		
		public function set bolVisivelFuncionalidadeAlteracao(bolVisivelFuncionalidadeAlteracao:Boolean):void {
			this._bolVisivelFuncionalidadeAlteracao = bolVisivelFuncionalidadeAlteracao;
		}
		
		public function get bolVisivelFuncionalidadeAlteracao():Boolean {
			return _bolVisivelFuncionalidadeAlteracao;
		}
		
		public function set bolPermiteAlteracaoPeloUsuario(bolPermiteAlteracaoPeloUsuario:Boolean):void {
			this._bolPermiteAlteracaoPeloUsuario = bolPermiteAlteracaoPeloUsuario;
		}
		
		public function get bolPermiteAlteracaoPeloUsuario():Boolean {
			return _bolPermiteAlteracaoPeloUsuario;
		}
		
		public function set bolAtivo(bolAtivo:Boolean):void {
			this._bolAtivo = bolAtivo;
		}
		
		public function get bolAtivo():Boolean {
			return _bolAtivo;
		}
		
		public function set dataCriacao(dataCriacao:IDateTime):void {
			this._dataCriacao = dataCriacao;
		}
		
		public function get dataCriacao():IDateTime {
			return _dataCriacao;
		}
		
		public function set dataHoraUltimaAtualizacao(dataHoraUltimaAtualizacao:IDateTime):void {
			this._dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
		}
		
		public function get dataHoraUltimaAtualizacao():IDateTime {
			return _dataHoraUltimaAtualizacao;
		}
		
		public function set descParametro(descParametro:String):void {
			this._descParametro = descParametro;
		}
		
		public function get descParametro():String {
			return _descParametro;
		}
		
		public function set valorParametro(valorParametro:String):void {
			this._valorParametro = valorParametro;
		}
		
		public function get valorParametro():String {
			return _valorParametro;
		}
		
		public function set valorParametroTexto(valorParametroTexto:String):void {
			this._valorParametroTexto = valorParametroTexto;
		}
		
		public function get valorParametroTexto():String {
			return _valorParametroTexto;
		}
		
		public function set nomeTabelaDominio(nomeTabelaDominio:String):void {
			this._nomeTabelaDominio = nomeTabelaDominio;
		}
		
		public function get nomeTabelaDominio():String {
			return _nomeTabelaDominio;
		}
		
		public function set bolParametroGlobal(bolParametroGlobal:Boolean):void {
			this._bolParametroGlobal = bolParametroGlobal;
		}
		
		public function get bolParametroGlobal():Boolean {
			return _bolParametroGlobal;
		}
		
		public function set idParametroLegado(idParametroLegado:Number):void {
			this._idParametroLegado = idParametroLegado;
		}
		
		public function get idParametroLegado():Number {
			return _idParametroLegado;
		}
		
		public function set listaValorParametro(listaValorParametro:ArrayCollection):void {
			this._listaValorParametro = listaValorParametro;
		}
		
		public function get listaValorParametro():ArrayCollection {
			return _listaValorParametro;
		}
		
		public function get descricaoIdNome():String {
			return id + " - " + nomeParametro;
		}
		
	}
}
