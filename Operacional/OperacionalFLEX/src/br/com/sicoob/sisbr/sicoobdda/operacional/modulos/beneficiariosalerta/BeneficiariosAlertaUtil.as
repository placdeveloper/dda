package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.beneficiariosalerta
{
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.FormatoImpressaoVO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	
	public class BeneficiariosAlertaUtil
	{
		
		public static function criarListaPreImpressao():ArrayCollection 
		{
			var listaPreImpressao: ArrayCollection = new ArrayCollection();
			listaPreImpressao.addItem(criarFormatoPDF());
			listaPreImpressao.addItem(criarFormatoXLS());
			listaPreImpressao.addItem(criarFormatoXLSNaoFormatado());

			return listaPreImpressao;
		}
		
		
		private static function criarFormatoPDF():FormatoImpressaoVO 
		{
			return criarFormatoPreImpressao(PreImpressao.FORMATO_PDF, "PDF");
		}
		
		private static function criarFormatoXLS():FormatoImpressaoVO 
		{
			return criarFormatoPreImpressao(PreImpressao.FORMATO_XLSX, "Excel 2007 (xlsx)");	
		}
		
		private static function criarFormatoXLSNaoFormatado():FormatoImpressaoVO 
		{
			return criarFormatoPreImpressao(PreImpressao.FORMATO_XLSX_SEM_FORMATACAO, "Excel 2007 Sem Formatacao (xlsx)");	
		}
		
		private static function criarFormatoPreImpressao(codFormato:String, descricao:String):FormatoImpressaoVO 
		{
			var formato:FormatoImpressaoVO = new FormatoImpressaoVO();
			formato.codFormato = codFormato;
			formato.descricao = descricao;
			return formato;
		}
		
	}
	
}