package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PagadorAgregadoDTO", PagadorAgregadoDTO);

	public class PagadorAgregadoDTO {

		private var _numCpfCnpj:String;
		private var _nomePessoa:String;
		private var _codTipoPessoa:String;
		private var _idPagadorDDA:Number;
		
		private var _numCpfCnpjPagador:String;
		private var _nomePessoaPagador:String;
		private var _dataAdesaoDDAPagador:IDateTime;
		private var _qtdAdesaoDDAPagador:Number;
		private var _qtdAgregadosPagador:Number;
		private var _numCooperativaSingular:Number;
		private var _numCooperativa:Number;
		private var _idInstituicao:Number;
		
		private var _selecionado:Boolean;

		public function PagadorAgregadoDTO(numCpfCnpj:String = null) {
			this._numCpfCnpj = numCpfCnpj;
		}
		
		public function set numCpfCnpj(numCpfCnpj:String):void {
			this._numCpfCnpj = numCpfCnpj;
		}

		public function get numCpfCnpj():String {
			return _numCpfCnpj;
		}

		public function set nomePessoa(nomePessoa:String):void {
			this._nomePessoa = nomePessoa;
		}

		public function get nomePessoa():String {
			return _nomePessoa;
		}
		
		public function set selecionado(selecionado:Boolean):void {
			this._selecionado = selecionado;
		}
		
		public function get selecionado():Boolean {
			return _selecionado;
		}
		
		public function set codTipoPessoa(codTipoPessoa:String):void {
			this._codTipoPessoa = codTipoPessoa;
		}
		
		public function get codTipoPessoa():String {
			return _codTipoPessoa;
		}
		
		public function set idPagadorDDA(idPagadorDDA:Number):void {
			this._idPagadorDDA = idPagadorDDA;
		}
		
		public function get idPagadorDDA():Number {
			return _idPagadorDDA;
		}
		
		public function set numCpfCnpjPagador(numCpfCnpjPagador:String):void {
			this._numCpfCnpjPagador = numCpfCnpjPagador;
		}
		
		public function get numCpfCnpjPagador():String {
			return _numCpfCnpjPagador;
		}
		
		public function set nomePessoaPagador(nomePessoaPagador:String):void {
			this._nomePessoaPagador = nomePessoaPagador;
		}
		
		public function get nomePessoaPagador():String {
			return _nomePessoaPagador;
		}
		
		public function set dataAdesaoDDAPagador(dataAdesaoDDAPagador:IDateTime):void {
			this._dataAdesaoDDAPagador = dataAdesaoDDAPagador;
		}
		
		public function get dataAdesaoDDAPagador():IDateTime {
			return _dataAdesaoDDAPagador;
		}
		
		public function set qtdAdesaoDDAPagador(qtdAdesaoDDAPagador:Number):void {
			this._qtdAdesaoDDAPagador = qtdAdesaoDDAPagador;
		}
		
		public function get qtdAdesaoDDAPagador():Number {
			return _qtdAdesaoDDAPagador;
		}
		
		public function set qtdAgregadosPagador(qtdAgregadosPagador:Number):void {
			this._qtdAgregadosPagador = qtdAgregadosPagador;
		}
		
		public function get qtdAgregadosPagador():Number {
			return _qtdAgregadosPagador;
		}
		
		public function set numCooperativaSingular(numCooperativaSingular:Number):void {
			this._numCooperativaSingular = numCooperativaSingular;
		}
		
		public function get numCooperativaSingular():Number {
			return _numCooperativaSingular;
		}
		
		
		public function set numCooperativa(numCooperativa:Number):void {
			this._numCooperativa = numCooperativa;
		}
		
		public function get numCooperativa():Number {
			return _numCooperativa;
		}
		
		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
	}
}