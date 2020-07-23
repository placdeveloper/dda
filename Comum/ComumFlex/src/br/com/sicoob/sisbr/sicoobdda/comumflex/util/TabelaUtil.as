package br.com.sicoob.sisbr.sicoobdda.comumflex.util {

	import mx.controls.dataGridClasses.DataGridColumn;
	
	import br.com.bancoob.componentes.tabelapaginada.TabelaPaginadaUtils;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	
	public class TabelaUtil {

		/**
		 * Método responsável por formatar o valor da coluna em formato de moeda com quatro casas decimais.
		 */
		public static function defaultValorRealLabelFunction(objeto:Object, coluna:DataGridColumn):String {     
			var valor:Object = ReflectionUtils.recuperarPropriedade(objeto, TabelaPaginadaUtils.recuperarNomePropriedade(coluna));

			return ("R$ " + FormataNumero.formata(Number(valor), 4)); 
		}
		
		/**
		 * Método responsável por formatar o valor da coluna em formato de moeda com duas casas decimais.
		 */
		public static function defaultValorRealLabelFunctionDuasCasas(objeto:Object, coluna:DataGridColumn):String {     
			var valor:Object = ReflectionUtils.recuperarPropriedade(objeto, TabelaPaginadaUtils.recuperarNomePropriedade(coluna));
			
			return ("R$ " + FormataNumero.formata(Number(valor))); 
		}

		/**
		 * Método responsável por formatar o valor da coluna Isento. Caso seja 1 retorna 'Sim'.
		 */
		public static function defaultIsentoLabelFunction(objeto:Object, coluna:DataGridColumn):String {     
			var valor:Object = ReflectionUtils.recuperarPropriedade(objeto, TabelaPaginadaUtils.recuperarNomePropriedade(coluna));
			if(valor != null) {
				return (valor == ConstantesComum.TIPO_SIM) ? 'Sim' : 'Não';
			}
			return "";
		}
		
		public static function defaultCEPLabelFunction(objeto:Object, coluna:DataGridColumn):String {
			var valor:Object = ReflectionUtils.recuperarPropriedade(objeto, TabelaPaginadaUtils.recuperarNomePropriedade(coluna));
			return NumeroUtil.formatarCEP(String(valor)); 
		}
		
		public static function defaultCPFCNPJLabelFunction(objeto:Object, coluna:DataGridColumn):String {
			var valor:Object = ReflectionUtils.recuperarPropriedade(objeto, TabelaPaginadaUtils.recuperarNomePropriedade(coluna));
			return FormatUtil.formataCPFCNPJ(valor as String);
		}
		
		/**
		 * Método responsável por formatar o valor da coluna em formato de moeda com quatro casas decimais.
		 */
		public static function defaultValorLabelFunction(objeto:Object, coluna:DataGridColumn):String {     
			var valor:Object = ReflectionUtils.recuperarPropriedade(objeto, TabelaPaginadaUtils.recuperarNomePropriedade(coluna));
			
			return (FormataNumero.formata(Number(valor), 0)); 
		}
		
	}
}