package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataNumero;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.NumeroUtil;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.EventoRateioDTO", EventoRateioDTO);

	public class EventoRateioDTO {

		private var _idEventoConsolidado:Number;
		private var _dataEvento:IDateTime;
		private var _descEventoTarifavel:String;
		private var _valorUnitarioBancoob:Number;
		private var _valorUnitarioCIP:Number;
		private var _qtdApuradaSicoob:Number;
		private var _qtdCIP:Number;
		private var _desvioPadrao:Number;
		private var _selecionado:Boolean;

		public function set idEventoConsolidado(idEventoConsolidado:Number):void {
			this._idEventoConsolidado = idEventoConsolidado;
		}

		public function get idEventoConsolidado():Number {
			return _idEventoConsolidado;
		}

		public function set dataEvento(dataEvento:IDateTime):void {
			this._dataEvento = dataEvento;
		}

		public function get dataEvento():IDateTime {
			return _dataEvento;
		}

		public function set descEventoTarifavel(descEventoTarifavel:String):void {
			this._descEventoTarifavel = descEventoTarifavel;
		}

		public function get descEventoTarifavel():String {
			return _descEventoTarifavel;
		}

		public function set valorUnitarioBancoob(valorUnitarioBancoob:Number):void {
			this._valorUnitarioBancoob = valorUnitarioBancoob;
		}

		public function get valorUnitarioBancoob():Number {
			return _valorUnitarioBancoob;
		}

		public function set valorUnitarioCIP(valorUnitarioCIP:Number):void {
			this._valorUnitarioCIP = valorUnitarioCIP;
		}

		public function get valorUnitarioCIP():Number {
			return _valorUnitarioCIP;
		}

		public function set qtdApuradaSicoob(qtdApuradaSicoob:Number):void {
			this._qtdApuradaSicoob = qtdApuradaSicoob;
		}

		public function get qtdApuradaSicoob():Number {
			return _qtdApuradaSicoob;
		}

		public function set qtdCIP(qtdCIP:Number):void {
			this._qtdCIP = qtdCIP;
		}

		public function get qtdCIP():Number {
			return _qtdCIP;
		}

		public function set desvioPadrao(desvioPadrao:Number):void {
			this._desvioPadrao = desvioPadrao;
		}

		public function get desvioPadrao():Number {
			return _desvioPadrao;
		}

		public function set selecionado(selecionado:Boolean):void {
			this._selecionado = selecionado;
		}

		public function get selecionado():Boolean {
			return _selecionado;
		}
		
		public function get valorApuradoSicoob():Number {
			return valorUnitarioCIP * qtdApuradaSicoob;
		}
		
		public function get valorCIP():Number {
			return qtdCIP > 0 ? valorUnitarioCIP * qtdCIP : 0;
		}
		
		private function get dif():Number {
			return qtdCIP > 0 ? qtdApuradaSicoob - qtdCIP : 0;
		}
		
		public function get diferencaCor():uint {
			return (isNaN(qtdCIP) || qtdCIP == 0) ? ConstantesComum.COR_PRETO : (dif == 0 ? ConstantesComum.COR_VERDE : ConstantesComum.COR_VERMELHO);
		}
		
		public function get difQtdValor():String {
			var resultado:String;
			
			if (qtdCIP > 0) {
				resultado = formatar(dif, valorUnitarioCIP);
			} else {
				resultado = "-";
			}
			
			return resultado;
		}
		
		public function get desvioQtdValorMaior():String {
			var resultado:String;
			
			if (qtdCIP > 0 && desvioPadrao > 0) {
				resultado = formatar(desvioPadraoMaior, valorUnitarioCIP);
			} else {
				resultado = "-";
			}
			
			return resultado;
		}
		
		private function get desvioPadraoMaior():Number {
			return (qtdApuradaSicoob + (qtdApuradaSicoob * (desvioPadrao > 0 ? desvioPadrao : 0) / 100));
		}
		
		public function get desvioQtdValorMenor():String {
			var resultado:String;
			
			if (qtdCIP > 0 && desvioPadrao > 0) {
				resultado = formatar(desvioPadraoMenor, valorUnitarioCIP);
			} else {
				resultado = "-";
			}
			
			return resultado;
		}
		
		private function get desvioPadraoMenor():Number {
			return (qtdApuradaSicoob - (qtdApuradaSicoob * (desvioPadrao > 0 ? desvioPadrao : 0) / 100));
		}
		
		public function get dentroDesvioCor():uint {
			return (isNaN(qtdCIP) || qtdCIP == 0) ? ConstantesComum.COR_PRETO : (dentroDesvio ? ConstantesComum.COR_VERDE : ConstantesComum.COR_VERMELHO);
		}
		
		public function get dentroDesvio():Boolean {
			return qtdCIP <= desvioPadraoMaior && qtdCIP >= desvioPadraoMenor;
		}
		
		public function formatar(dif:Number, valorUnitario:Number):String {
			return FormataNumero.formata(NumeroUtil.arredondarParaBaixo(dif, 0), 0) + " / " + (NumeroUtil.formatarNumeroDecimalParaMoeda(NumeroUtil.arredondarParaBaixo(dif * valorUnitario)));
		}
		
	}
}