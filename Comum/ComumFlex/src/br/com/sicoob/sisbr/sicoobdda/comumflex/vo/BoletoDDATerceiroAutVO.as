package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDATerceiroAutVO", BoletoDDATerceiroAutVO);

	public class BoletoDDATerceiroAutVO {

		private var _id:Number;
		
		private var _boletoDDA:BoletoDDAVO;
		
		private var _codTipoPessoaAutorizador:String;
		
		private var _numCpfCnpjAutorizador:String;
		
		private var _codTipoPessoaTerceiro:String;
		
		private var _numCpfCnpjTerceiro:String;
		
		private var _numIdentificadorTerceiro:Number;
		
		private var _numRefAtualTerceiro:Number;
		
		private var _bolAtivo:Boolean;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}
		
		public function set boletoDDA(boletoDDA:BoletoDDAVO):void {
			this._boletoDDA = boletoDDA;
		}
		
		public function get boletoDDA():BoletoDDAVO {
			return _boletoDDA;
		}
		
		public function set codTipoPessoaAutorizador(codTipoPessoaAutorizador:String):void {
			this._codTipoPessoaAutorizador = codTipoPessoaAutorizador;
		}
		
		public function get codTipoPessoaAutorizador():String {
			return _codTipoPessoaAutorizador;
		}
		
		public function set numCpfCnpjAutorizador(numCpfCnpjAutorizador:String):void {
			this._numCpfCnpjAutorizador = numCpfCnpjAutorizador;
		}
		
		public function get numCpfCnpjAutorizador():String {
			return _numCpfCnpjAutorizador;
		}
		
		public function set codTipoPessoaTerceiro(codTipoPessoaTerceiro:String):void {
			this._codTipoPessoaTerceiro = codTipoPessoaTerceiro;
		}
		
		public function get codTipoPessoaTerceiro():String {
			return _codTipoPessoaTerceiro;
		}
		
		public function set numCpfCnpjTerceiro(numCpfCnpjTerceiro:String):void {
			this._numCpfCnpjTerceiro = numCpfCnpjTerceiro;
		}
		
		public function get numCpfCnpjTerceiro():String {
			return _numCpfCnpjTerceiro;
		}
		
		public function set numIdentificadorTerceiro(numIdentificadorTerceiro:Number):void {
			this._numIdentificadorTerceiro = numIdentificadorTerceiro;
		}
		
		public function get numIdentificadorTerceiro():Number {
			return _numIdentificadorTerceiro;
		}
		
		public function set numRefAtualTerceiro(numRefAtualTerceiro:Number):void {
			this._numRefAtualTerceiro = numRefAtualTerceiro;
		}
		
		public function get numRefAtualTerceiro():Number {
			return _numRefAtualTerceiro;
		}
		
		public function set bolAtivo(bolAtivo:Boolean):void {
			this._bolAtivo = bolAtivo;
		}
		
		public function get bolAtivo():Boolean {
			return _bolAtivo;
		}
	}
}