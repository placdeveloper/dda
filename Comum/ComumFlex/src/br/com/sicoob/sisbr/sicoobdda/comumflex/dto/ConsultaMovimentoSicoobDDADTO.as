package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaMovimentoSicoobDDADTO", ConsultaMovimentoSicoobDDADTO);

	public class ConsultaMovimentoSicoobDDADTO {

		private var _numCodBarras:String;
		private var _nomeBeneficiario:String;
		private var _cpfCnpjBeneficiario:String;
		private var _dataEntrada:IDateTime;
		private var _dataVencimento:IDateTime;
		private var _valor:Number;
		private var _dataEvento:IDateTime;
		private var _idEventoConsolidadoDDA:Number;
		private var _idEventoConsolidadoDDADetalhe:Number;
		private var _numCooperativa:String;

		public function set numCodBarras(numCodBarras:String):void {
			this._numCodBarras = numCodBarras;
		}

		public function get numCodBarras():String {
			return _numCodBarras;
		}

		public function set nomeBeneficiario(nomeBeneficiario:String):void {
			this._nomeBeneficiario = nomeBeneficiario;
		}

		public function get nomeBeneficiario():String {
			return _nomeBeneficiario;
		}

		public function set cpfCnpjBeneficiario(cpfCnpjBeneficiario:String):void {
			this._cpfCnpjBeneficiario = cpfCnpjBeneficiario;
		}

		public function get cpfCnpjBeneficiario():String {
			return _cpfCnpjBeneficiario;
		}

		public function set dataEntrada(dataEntrada:IDateTime):void {
			this._dataEntrada = dataEntrada;
		}

		public function get dataEntrada():IDateTime {
			return _dataEntrada;
		}

		public function set dataVencimento(dataVencimento:IDateTime):void {
			this._dataVencimento = dataVencimento;
		}

		public function get dataVencimento():IDateTime {
			return _dataVencimento;
		}

		public function set valor(valor:Number):void {
			this._valor = valor;
		}

		public function get valor():Number {
			return _valor;
		}

		public function set dataEvento(dataEvento:IDateTime):void {
			this._dataEvento = dataEvento;
		}

		public function get dataEvento():IDateTime {
			return _dataEvento;
		}

		public function set idEventoConsolidadoDDA(idEventoConsolidadoDDA:Number):void {
			this._idEventoConsolidadoDDA = idEventoConsolidadoDDA;
		}

		public function get idEventoConsolidadoDDA():Number {
			return _idEventoConsolidadoDDA;
		}

		public function set idEventoConsolidadoDDADetalhe(idEventoConsolidadoDDADetalhe:Number):void {
			this._idEventoConsolidadoDDADetalhe = idEventoConsolidadoDDADetalhe;
		}

		public function get idEventoConsolidadoDDADetalhe():Number {
			return _idEventoConsolidadoDDADetalhe;
		}

		public function set numCooperativa(numCooperativa:String):void {
			this._numCooperativa = numCooperativa;
		}

		public function get numCooperativa():String {
			return _numCooperativa;
		}

	}
}