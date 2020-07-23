package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaEventoRateioDTO", ConsultaEventoRateioDTO);

	public class ConsultaEventoRateioDTO {

		private var _codEventoTarifavel:Number;
		private var _descEventoTarifavel:String;
		private var _dataInicial:IDateTime;
		private var _dataFinal:IDateTime;

		public function set codEventoTarifavel(codEventoTarifavel:Number):void {
			this._codEventoTarifavel = codEventoTarifavel;
		}

		public function get codEventoTarifavel():Number {
			return _codEventoTarifavel;
		}

		public function set descEventoTarifavel(descEventoTarifavel:String):void {
			this._descEventoTarifavel = descEventoTarifavel;
		}

		public function get descEventoTarifavel():String {
			return _descEventoTarifavel;
		}

		public function set dataInicial(dataInicial:IDateTime):void {
			this._dataInicial = dataInicial;
		}

		public function get dataInicial():IDateTime {
			return _dataInicial;
		}

		public function set dataFinal(dataFinal:IDateTime):void {
			this._dataFinal = dataFinal;
		}

		public function get dataFinal():IDateTime {
			return _dataFinal;
		}

	}
}