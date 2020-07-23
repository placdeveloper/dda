package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import mx.collections.ArrayCollection;
	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ControleRateioDTO", ControleRateioDTO);

	public class ControleRateioDTO {

		private var _idRateio:Number;
		private var _dataInclusao:IDateTime;
		private var _descSituacao:String;
		private var _codSituacaoRateio:Number;
		private var _desvioPadrao:Number;
		private var _listaEventoTarifavelDDA:ArrayCollection;
		private var _listaEventoRateio:ArrayCollection;

		public function set idRateio(idRateio:Number):void {
			this._idRateio = idRateio;
		}

		public function get idRateio():Number {
			return _idRateio;
		}

		public function set dataInclusao(dataInclusao:IDateTime):void {
			this._dataInclusao = dataInclusao;
		}

		public function get dataInclusao():IDateTime {
			return _dataInclusao;
		}

		public function set descSituacao(descSituacao:String):void {
			this._descSituacao = descSituacao;
		}

		public function get descSituacao():String {
			return _descSituacao;
		}

		public function set codSituacaoRateio(codSituacaoRateio:Number):void {
			this._codSituacaoRateio = codSituacaoRateio;
		}

		public function get codSituacaoRateio():Number {
			return _codSituacaoRateio;
		}

		public function set desvioPadrao(desvioPadrao:Number):void {
			this._desvioPadrao = desvioPadrao;
		}

		public function get desvioPadrao():Number {
			return _desvioPadrao;
		}

		public function set listaEventoTarifavelDDA(listaEventoTarifavelDDA:ArrayCollection):void {
			this._listaEventoTarifavelDDA = listaEventoTarifavelDDA;
		}

		public function get listaEventoTarifavelDDA():ArrayCollection {
			return _listaEventoTarifavelDDA;
		}

		public function set listaEventoRateio(listaEventoRateio:ArrayCollection):void {
			this._listaEventoRateio = listaEventoRateio;
		}

		public function get listaEventoRateio():ArrayCollection {
			return _listaEventoRateio;
		}

	}
}