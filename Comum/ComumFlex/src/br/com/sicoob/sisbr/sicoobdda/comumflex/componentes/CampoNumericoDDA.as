package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes {
	
	import mx.events.ValidationResultEvent;
	
	import br.com.bancoob.componentes.input.Texto;
	import br.com.bancoob.componentes.validadores.ValidadorIntervaloDouble;
	import br.com.bancoob.componentes.validadores.ValidadorIntervaloInteger;
	import br.com.bancoob.componentes.validadores.ValidadorIntervaloNumerico;
	import br.com.bancoob.componentes.validadores.ValidadorIntervaloShort;
	
	public class CampoNumericoDDA extends TextoCobranca {
		//--------------------------------------------------------------------------
		//  Obtem registro para Edição.
		// Arredontandomentos rounding="none|up|down|nearest"
		//--------------------------------------------------------------------------	
		
		public static const SHORT		   	:String = "short";
		public static const SHORT_POSITIVO	:String = "pshort";
		public static const INT		   		:String = "int";
		public static const INT_POSITIVO  	:String = "pint";
		public static const DOUBLE  	    :String = "double";
		public static const DOUBLE_POSITIVO	:String = "pdouble";
		
		private var _validadorTipo:ValidadorIntervaloNumerico;
		
		/**
		 * Valor que informa o tipo de validação que deseja utilizar
		 */
		public function set validarTipo(tipo:String):void {
			if (tipo == SHORT || tipo == SHORT_POSITIVO) {
				_validadorTipo = new ValidadorIntervaloShort();
			} else if (tipo == INT || tipo == INT_POSITIVO) {
				_validadorTipo = new ValidadorIntervaloInteger();
			} else if (tipo == DOUBLE || tipo == DOUBLE_POSITIVO) {
				_validadorTipo = new ValidadorIntervaloDouble();
			} else {
				_validadorTipo = null;
			}
			
			if (_validadorTipo != null) {
				_validadorTipo.allowNegative = (tipo == SHORT || tipo == INT || tipo == DOUBLE);
				_validadorTipo.property = "text"; 
				_validadorTipo.source = this;
			}
		}
		
		/**
		 *  Construtor.
		 */
		public function CampoNumericoDDA():void {
			super();
			this.tipoEntrada = Texto.NUMERICO;
			this.aceitaNulos = true;
		}
		
		//--------------------------------------------------------------------------
		//  Métodos Sobrescritos:Texto
		//--------------------------------------------------------------------------
		protected override function validHandler_focusOut(obj:Object):void {
			var texto:String = this.text;
			
			if (texto.length != 0 || !aceitaNulos) {
				super.validHandler_focusOut(obj);
			}
		}
		
		protected override function pegaValor():Number {
			var str:String = this.text;
			
			if (str.length == 0 && aceitaNulos) {
				return NaN;
			} else {
				return super.pegaValor();
			}
		}
		
		protected override function formatarNumero():void{
			var texto:String = this.text;
			
			if (texto.length != 0 || !aceitaNulos) {
				super.formatarNumero();
			} else {
				this.text = '';
			}
		}
		
		/**
		 * Função que realiza validação
		 * 
		 * @return Retorna <code>true</code> caso o campo esteja preenchido ou <code>false</code> caso o campo não esteja preenchido
		 */
		public override function validar():Boolean{
			var valido:Boolean = super.validar();
			
			if (valido && _validadorTipo != null) {
				valido = _validadorTipo.validate().type == ValidationResultEvent.VALID;
			} else if (valido && _validarMinimo != null) {
				valido = _validarMinimo.validate().type == ValidationResultEvent.VALID;
			}
			
			return valido;
		}
		
	}
	
}


