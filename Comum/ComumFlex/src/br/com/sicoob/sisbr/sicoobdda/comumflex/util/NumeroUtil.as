package br.com.sicoob.sisbr.sicoobdda.comumflex.util {

	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataNumero;
	
	import mx.formatters.NumberBaseRoundType;

	public class NumeroUtil	{

		private static const MASCARA_CEP:String = "##.###-###";
		
		private static const MASCARA_TELEFONE_8_DIGITOS:String = "####-####";
		private static const MASCARA_TELEFONE_9_DIGITOS:String = "#####-####";
		private static const MASCARA_TELEFONE_10_DIGITOS:String = "(##)####-####";
		private static const MASCARA_TELEFONE_11_DIGITOS:String = "(##)#####-####";
		private static const MASCARA_TELEFONE_12_DIGITOS:String = "##(##)####-####";
		private static const MASCARA_TELEFONE_13_DIGITOS:String = "##(##)#####-####";
		private static const MASCARA_TELEFONE_14_DIGITOS:String = "##(##)#####-#####";
		
		/**
		 * Converte texto para número.
		 */
		public static function converterStringParaNumber(valor:String):Number {
			return new Number(valor);
		}

		/**
		 * Converte número para texto.
		 */
		public static function converterNumberParaString(valor:Number):String {
			return valor.toString();
		}

		/**
		 * Converte valor (String) de moeda para número.
		 */
		public static function converterMoedaParaNumeroDecimal(valor:String):Number {
			// Utiliza RegEx para substituir TODOS os '.'
			return converterStringParaNumber(valor.replace(/\./g, "").replace("\,", "."));
		}

		/**
		 * Converte número decimal para valor de moeda (String).
		 */
		public static function converterNumeroDecimalParaMoeda(valor:Number, casasDecimais:Number = 2):String {
			return FormataNumero.formata(valor, casasDecimais);
		}
		
		/**
		 * Arredonda o valor.
		 */
		public static function arredondar(valor:Number, casasDecimais:int = 2):Number {
			return converterMoedaParaNumeroDecimal(FormataNumero.formata(valor, casasDecimais));
		}
		
		/**
		 * Arredonda o valor.
		 */
		public static function arredondarParaBaixo(valor:Number, casasDecimais:int = 2):Number {
			return converterMoedaParaNumeroDecimal(FormataNumero.formata(valor, casasDecimais, true, NumberBaseRoundType.NONE));
		}

		/**
		 * Formata número decimal para valor de moeda (String) utilizando o 'R$'.
		 */ 
		public static function formatarNumeroDecimalParaMoeda(valor:Number, casasDecimais:Number = 2):String {
			return "R$ " + FormataNumero.formata(valor, casasDecimais);
		}

		/**
		 * Formata número da conta corrente no padrão: 1234567-8.
		 */ 
		public static function formatarNumeroContaCorrente(valor:Number):String {
			return FormatUtil.formataValor(valor, "#-#");
		}
		
		/**
		 * Formata para o padrão do CEP.
		 */
		public static function formatarCEP(numCep:String):String {
			if(!numCep || numCep.length == 0) {
				return numCep;
			}
			
			return FormatUtil.formataValor(numCep.replace(/\./g, "").replace(/\-/g, ""), MASCARA_CEP);
		}
		
		/**
		 * Formata o número de telefone.
		 */
		public static function formatarTelefone(numTelefone:String):String {
			if(!numTelefone) {
				return numTelefone;
			}
			
			var numTelefoneDesformatado:String = numTelefone.replace(/[\-()]/g, "");
			var numTelefoneFormatado:String;
			
			switch (numTelefoneDesformatado.length) {
				case 8:
					numTelefoneFormatado = FormatUtil.formataValor(numTelefoneDesformatado, MASCARA_TELEFONE_8_DIGITOS);
					break;
				case 9:
					numTelefoneFormatado = FormatUtil.formataValor(numTelefoneDesformatado, MASCARA_TELEFONE_9_DIGITOS);
					break;
				case 10:
					numTelefoneFormatado = FormatUtil.formataValor(numTelefoneDesformatado, MASCARA_TELEFONE_10_DIGITOS);
					break;
				case 11:
					numTelefoneFormatado = FormatUtil.formataValor(numTelefoneDesformatado, MASCARA_TELEFONE_11_DIGITOS);
					break;
				case 12:
					numTelefoneFormatado = FormatUtil.formataValor(numTelefoneDesformatado, MASCARA_TELEFONE_12_DIGITOS);
					break;
				case 13:
					numTelefoneFormatado = FormatUtil.formataValor(numTelefoneDesformatado, MASCARA_TELEFONE_13_DIGITOS);
					break;
				case 14:
					numTelefoneFormatado = FormatUtil.formataValor(numTelefoneDesformatado, MASCARA_TELEFONE_14_DIGITOS);
					break;
				default:
					numTelefoneFormatado = numTelefone;
					break;
			}
			
			return numTelefoneFormatado;
		}
		
		private static const correction:Number = Math.pow(10, 10);
		
		/**
		 * Realiza a soma.
		 */
		public static function somar(valor1:Number, valor2:Number, casasDecimais:int = 2):Number {
			return arredondarParaBaixo(Math.round((valor1 + valor2) * correction) / correction, casasDecimais);
		}
		
		/**
		 * Realiza a subtração.
		 */
		public static function subtrair(valor1:Number, valor2:Number, casasDecimais:int = 2):Number {
			return arredondarParaBaixo(Math.round((valor1 - valor2) * correction) / correction, casasDecimais);
		}
		
		/**
		 * Realiza a divisão.
		 */
		public static function dividir(valor1:Number, valor2:Number, casasDecimais:int = 2):Number {
			return arredondarParaBaixo(Math.round((valor1 / valor2) * correction) / correction, casasDecimais);
		}
		
		/**
		 * Realiza a multiplicação.
		 */
		public static function multiplicar(valor1:Number, valor2:Number, casasDecimais:int = 2):Number {
			return arredondarParaBaixo(Math.round((valor1 * valor2) * correction) / correction, casasDecimais);
		}

	}
}