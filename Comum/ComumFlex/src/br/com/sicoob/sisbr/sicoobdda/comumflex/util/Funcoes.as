package br.com.sicoob.sisbr.sicoobdda.comumflex.util {
	
	import br.com.bancoob.componentes.input.Check;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	
	import flash.net.registerClassAlias;
	import flash.utils.ByteArray;
	import flash.utils.getDefinitionByName;
	
	import mx.collections.ArrayCollection;
	
	public class Funcoes {
		
		public static function menorIgual( comparar:Date, verificar:Date ):Boolean {
			return (comparar.getTime() <= verificar.getTime());
		}
 
		/**
		 * Função para comparar a data atual com a data passada na chamada da função. 
		 */
		public static function menorDataAtual(comparar:Date):Boolean {
			var dataAtual:Date = new Date();
			dataAtual.setHours(0, 0, 0, 0);
			var retorno:Boolean = true;
			if (comparar.getTime() >= dataAtual.getTime()) {
				retorno = false;
			}
			
			return retorno; 
		}

		public static function maiorIgual( comparar:Date, verificar:Date ):Boolean {
			return (comparar.getTime() >= verificar.getTime());
		}
 
		public static function intervalo( comparar:Date, inicial:Date, final:Date):Boolean {
			return (comparar.getTime() >= inicial.getTime() && comparar.getTime() <= final.getTime());
		}
		
		public static function obterServico(classeServico:String, metodo:String):ServicoJava {			
			var servico:ServicoJava = new ServicoJava();
			servico.source = classeServico;
			var operacoes:Object = new Object();
			operacoes[metodo];
		    servico.operations = operacoes;
		    servico.bloquearOperacao = true;
		    return servico;
		}
		
		public static function selecionarCheckbox(check:Check, valor:String):void{
			if(check != null){
				if(valor != null && valor != ""){
					if(valor == "1"){
						check.selected = true;
					}else{
						check.selected = false;
					}
				}else{
					check.selected = false;
				}
			}
		}

		public static function mascaraFlexValida(valor:String, tamanhoNumerico:int): Boolean {
			if(valor != null){
				var valorArray:Array = valor.split(",");
				var valorUnidade:String = valorArray[0].toString(); 
				if(valorUnidade.length > tamanhoNumerico) {
					return false;
				}
			}
			return true;
		}
		
		public static function isNumeroNegativo(valor:String):Boolean{
			if(StringUtils.trim(valor) != ""){
				var valorNumerico:Number = retiraMascaraNumero(valor);
				if(valorNumerico < 0){
					return true;
				}
			}
			
			return false;
		}
		
		public static function retiraMascaraNumero(valor:String):Number {
			if(valor == null) {
				return NaN;
			}
			
			if(StringUtils.trim(valor) == "") {
				return NaN;
			}
			
			return parseFloat(valor.replace(".", "").replace(",", "."));
		}

		/**
		 * Percorre o array informado retornando o objeto que seja igual ao index. 
		 */
		public static function selecionarItem(array:ArrayCollection, valor:int, campo:String):Object {
	  		for (var i:int = 0; i < array.length; i++) {
				if (array[i][campo] == valor) {
	  				return array[i];
				}
	  		}

	  		return null;
		}

		/**
		 * Funcao para clonar o objeto .
		 * @param source:Objeto origem 
		 * 		  destClass: Classe do objeto destino
		 * @return * - Objeto do tipo da classe destino
		 */				
		public static function clone(source:Object, destClass:String):* {
			//Instancia da classe
			
			var cls : Class = getDefinitionByName(destClass) as Class;
			//Registra o tipo do objeto  Destino
			registerClassAlias(destClass,cls);
			
			var tempBA:ByteArray = new ByteArray();
			tempBA.writeObject( source );
			tempBA.position = 0;
			return tempBA.readObject() as cls;
		}
		
		/**
		 * retorna um array de anos .
		 * @param anoInicial Primeiro ano a entrar no array.
		 * @return itensComboAnoVigencia
		 */
		public static function obterArrayAnoAteAtual(anoInicial:Number):Array
		{
			var arrayAno:Array = new Array();
			var ANO_ATUAL:int = new Date().fullYear;
			
			while(anoInicial <= ANO_ATUAL)
			{
				arrayAno.push({label:anoInicial.toString(),data:anoInicial});
				anoInicial++
			}
			return arrayAno;
		}
		
		
		/**
		 * Seleciona o item de acordo com o valor informado.
		 *  
		 * @param componente 
		 * @param valor a ser procurado
		 */
		public static function selecionarItemCombo(componente:Combo, valor:Object):void {
			var arrayData:Object = componente.dataProvider;
			
			for (var i:int = 0; i < arrayData.length; i++) {
				var pedaco:String = arrayData[i] ? arrayData[i].toString().toUpperCase() : "";
				
				if ((valor == null ? "" : String(valor).toUpperCase()) == pedaco) {
					componente.selectedIndex = i;
					return;
				}
			}
			
			componente.selectedIndex = componente.inserirItemOpcional ? 0 : -1;
		}

	}
}