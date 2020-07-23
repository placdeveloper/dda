package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {
	
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoParametroVO;
	
	registerClassAlias("br.com.sicoob.sisbr.cobrancabancaria.comum.fachada.dto.ConsultaParametroDTO", ConsultaParametroDTO);
	
	public class ConsultaParametroDTO {
		
		private var _idParametro:Number;
		private var _nomeParametro:String;
		private var _descricaoParametro:String;
		private var _idInstituicao:Number;
		private var _tipoParametro:TipoParametroVO;
		private var _bolPermiteAlteracaoPeloUsuario:Boolean;
		private var _bolVisivelFuncionalidadeAlteracao:Boolean;
		private var _bolAtivo:Boolean;
		private var _bolParametroGlobal:Boolean;
		private var _valorBaseParametro:String;
		private var _valorBaseParametroTexto:String;
		private var _dataCriacao:IDateTime;
		private var _dataUltimaAlteracao:IDateTime;
		private var _numeroCooperativa:String;
		private var _descricaoSiglaCooperativa:String;
		private var _somenteVisiveis:Boolean;
		private var _idParametroLegado:Number;
		
		public function set idParametro(idParametro:Number):void {
			this._idParametro = idParametro;
		}
		
		public function get idParametro():Number {
			return _idParametro;
		}
		
		public function set nomeParametro(nomeParametro:String):void {
			this._nomeParametro = nomeParametro;
		}
		
		public function get nomeParametro():String {
			return _nomeParametro;
		}
		
		public function set descricaoParametro(descricaoParametro:String):void {
			this._descricaoParametro = descricaoParametro;
		}
		
		public function get descricaoParametro():String {
			return _descricaoParametro;
		}
		
		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set tipoParametro(tipoParametro:TipoParametroVO):void {
			this._tipoParametro = tipoParametro;
		}
		
		public function get tipoParametro():TipoParametroVO {
			return _tipoParametro;
		}
		
		public function set bolPermiteAlteracaoPeloUsuario(bolPermiteAlteracaoPeloUsuario:Boolean):void {
			this._bolPermiteAlteracaoPeloUsuario = bolPermiteAlteracaoPeloUsuario;
		}
		
		public function get bolPermiteAlteracaoPeloUsuario():Boolean {
			return _bolPermiteAlteracaoPeloUsuario;
		}
		
		public function set bolVisivelFuncionalidadeAlteracao(bolVisivelFuncionalidadeAlteracao:Boolean):void {
			this._bolVisivelFuncionalidadeAlteracao = bolVisivelFuncionalidadeAlteracao;
		}
		
		public function get bolVisivelFuncionalidadeAlteracao():Boolean {
			return _bolVisivelFuncionalidadeAlteracao;
		}
		
		public function set bolAtivo(bolAtivo:Boolean):void {
			this._bolAtivo = bolAtivo;
		}
		
		public function get bolAtivo():Boolean {
			return _bolAtivo;
		}
		
		public function set bolParametroGlobal(bolParametroGlobal:Boolean):void {
			this._bolParametroGlobal = bolParametroGlobal;
		}
		
		public function get bolParametroGlobal():Boolean {
			return _bolParametroGlobal;
		}
		
		public function set valorBaseParametro(valorBaseParametro:String):void {
			this._valorBaseParametro = valorBaseParametro;
		}
		
		public function get valorBaseParametro():String {
			return _valorBaseParametro;
		}
		
		public function set valorBaseParametroTexto(valorBaseParametroTexto:String):void {
			this._valorBaseParametroTexto = valorBaseParametroTexto;
		}
		
		public function get valorBaseParametroTexto():String {
			return _valorBaseParametroTexto;
		}
		
		public function set dataCriacao(dataCriacao:IDateTime):void {
			this._dataCriacao = dataCriacao;
		}
		
		public function get dataCriacao():IDateTime {
			return _dataCriacao;
		}
		
		public function set dataUltimaAlteracao(dataUltimaAlteracao:IDateTime):void {
			this._dataUltimaAlteracao = dataUltimaAlteracao;
		}
		
		public function get dataUltimaAlteracao():IDateTime {
			return _dataUltimaAlteracao;
		}
		
		public function set numeroCooperativa(numeroCooperativa:String):void {
			this._numeroCooperativa = numeroCooperativa;
		}
		
		public function get numeroCooperativa():String {
			return _numeroCooperativa;
		}
		
		public function set descricaoSiglaCooperativa(descricaoSiglaCooperativa:String):void {
			this._descricaoSiglaCooperativa = descricaoSiglaCooperativa;
		}
		
		public function get descricaoSiglaCooperativa():String {
			return _descricaoSiglaCooperativa;
		}
		
		public function set somenteVisiveis(somenteVisiveis:Boolean):void {
			this._somenteVisiveis = somenteVisiveis;
		}
		
		public function get somenteVisiveis():Boolean {
			return _somenteVisiveis;
		}
		
		public function set idParametroLegado(idParametroLegado:Number):void {
			this._idParametroLegado = idParametroLegado;
		}
		
		public function get idParametroLegado():Number {
			return _idParametroLegado;
		}
		
	}
}
