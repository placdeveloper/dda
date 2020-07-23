package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio
{
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.FormatoImpressaoVO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	
	public class PreImpressaoRelatorio extends PreImpressao {
		
		private var _formatosNaPreImpressao:ArrayCollection = null;
		private var _cobFormato:String;
		
		public function PreImpressaoRelatorio() {
			this._formatosNaPreImpressao = preparaFormatosPadraoDDA();
			super(_formatosNaPreImpressao);
		}
		
		private function preparaFormatosPadraoDDA():ArrayCollection {
			var arrayFormatosPredefinidos:ArrayCollection = new ArrayCollection();
			arrayFormatosPredefinidos.addItem(geraFormato(FORMATO_PDF));
			arrayFormatosPredefinidos.addItem(geraFormato(FORMATO_XLSX));
			return arrayFormatosPredefinidos;
		}
		
		private function geraFormato(codFormato:String):FormatoImpressaoVO {
			var formatoItem:FormatoImpressaoVO = new FormatoImpressaoVO();
			formatoItem.codFormato = codFormato;
			return formatoItem;
		}
	}
}