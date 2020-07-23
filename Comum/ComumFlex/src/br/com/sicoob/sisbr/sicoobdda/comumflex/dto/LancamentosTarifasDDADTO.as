package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.LancamentosTarifasDDADTO", LancamentosTarifasDDADTO);

	public class LancamentosTarifasDDADTO {

		private var _idEventoConsolidadoDDA:Number;
		private var _idEventoConsolidadoDDADetalhe:Number;
		private var _dataEvento:IDateTime;
		private var _quantidadeApurada:Number;
		private var _valorUnitario:Number;
		private var _valorTotal:Number;

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

		public function set dataEvento(dataEvento:IDateTime):void {
			this._dataEvento = dataEvento;
		}

		public function get dataEvento():IDateTime {
			return _dataEvento;
		}

		public function set quantidadeApurada(quantidadeApurada:Number):void {
			this._quantidadeApurada = quantidadeApurada;
		}

		public function get quantidadeApurada():Number {
			return _quantidadeApurada;
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

	}
}