package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaTarifasDDAPagasDTO", ConsultaTarifasDDAPagasDTO);

	public class ConsultaTarifasDDAPagasDTO {

		private var _idRateioDDALancamento:Number;
		private var _idRateioDDA:Number;
		private var _dataConciliacao:IDateTime;
		private var _numCooperativa:String;
		private var _descEventoTarifavel:String;
		private var _quantidade:Number;
		private var _valorUnitario:Number;
		private var _valorTotal:Number;
		private var _numContaCorrenteDebitada:Number;
		private var _numLote:Number;
		private var _idInstituicaoCentral:Number;
		private var _numCooperativaCentral:String;
		private var _idInstituicaoSingular:Number;
		private var _numCooperativaSingular:String;
		private var _dataConciliacaoInicial:IDateTime;
		private var _dataConciliacaoFinal:IDateTime;

		public function set idRateioDDALancamento(idRateioDDALancamento:Number):void {
			this._idRateioDDALancamento = idRateioDDALancamento;
		}

		public function get idRateioDDALancamento():Number {
			return _idRateioDDALancamento;
		}

		public function set idRateioDDA(idRateioDDA:Number):void {
			this._idRateioDDA = idRateioDDA;
		}

		public function get idRateioDDA():Number {
			return _idRateioDDA;
		}

		public function set dataConciliacao(dataConciliacao:IDateTime):void {
			this._dataConciliacao = dataConciliacao;
		}

		public function get dataConciliacao():IDateTime {
			return _dataConciliacao;
		}

		public function set numCooperativa(numCooperativa:String):void {
			this._numCooperativa = numCooperativa;
		}

		public function get numCooperativa():String {
			return _numCooperativa;
		}

		public function set descEventoTarifavel(descEventoTarifavel:String):void {
			this._descEventoTarifavel = descEventoTarifavel;
		}

		public function get descEventoTarifavel():String {
			return _descEventoTarifavel;
		}

		public function set quantidade(quantidade:Number):void {
			this._quantidade = quantidade;
		}

		public function get quantidade():Number {
			return _quantidade;
		}

		public function set valorUnitario(valorUnitario:Number):void {
			this._valorUnitario = valorUnitario;
		}

		public function get valorUnitario():Number {
			return _valorUnitario;
		}

		public function set valorTotal(valorTotal:Number):void {
			this._valorTotal = valorTotal;
		}

		public function get valorTotal():Number {
			return _valorTotal;
		}

		public function set numContaCorrenteDebitada(numContaCorrenteDebitada:Number):void {
			this._numContaCorrenteDebitada = numContaCorrenteDebitada;
		}

		public function get numContaCorrenteDebitada():Number {
			return _numContaCorrenteDebitada;
		}
		
		public function get numContaCorrenteDebitadaString():String {
			return _numContaCorrenteDebitada == 0 ? "": _numContaCorrenteDebitada.toString();
		}

		public function set numLote(numLote:Number):void {
			this._numLote = numLote;
		}

		public function get numLote():Number {
			return _numLote;
		}

		public function set idInstituicaoCentral(idInstituicaoCentral:Number):void {
			this._idInstituicaoCentral = idInstituicaoCentral;
		}

		public function get idInstituicaoCentral():Number {
			return _idInstituicaoCentral;
		}

		public function set numCooperativaCentral(numCooperativaCentral:String):void {
			this._numCooperativaCentral = numCooperativaCentral;
		}

		public function get numCooperativaCentral():String {
			return _numCooperativaCentral;
		}

		public function set idInstituicaoSingular(idInstituicaoSingular:Number):void {
			this._idInstituicaoSingular = idInstituicaoSingular;
		}

		public function get idInstituicaoSingular():Number {
			return _idInstituicaoSingular;
		}

		public function set numCooperativaSingular(numCooperativaSingular:String):void {
			this._numCooperativaSingular = numCooperativaSingular;
		}

		public function get numCooperativaSingular():String {
			return _numCooperativaSingular;
		}

		public function set dataConciliacaoInicial(dataConciliacaoInicial:IDateTime):void {
			this._dataConciliacaoInicial = dataConciliacaoInicial;
		}

		public function get dataConciliacaoInicial():IDateTime {
			return _dataConciliacaoInicial;
		}

		public function set dataConciliacaoFinal(dataConciliacaoFinal:IDateTime):void {
			this._dataConciliacaoFinal = dataConciliacaoFinal;
		}

		public function get dataConciliacaoFinal():IDateTime {
			return _dataConciliacaoFinal;
		}

	}
}