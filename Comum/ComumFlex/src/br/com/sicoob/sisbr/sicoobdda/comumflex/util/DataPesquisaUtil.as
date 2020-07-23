package br.com.sicoob.sisbr.sicoobdda.comumflex.util
{
	import br.com.bancoob.componentes.input.Data;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;

	public class DataPesquisaUtil extends DataUtil
	{
		
		public static const QTD_DIAS_1_MES:Number = 31;
		public static const QTD_DIAS_2_MESES:Number = 61;
		public static const QTD_DIAS_3_MESES:Number = 92;
		public static const QTD_DIAS_4_MESES:Number = 122;
		public static const QTD_DIAS_6_MESES:Number = 183;
		
		
		public static function validaPeriodoDataPesquisa(componenteDataInicio:Data, componenteDataFim:Data, range:Number = QTD_DIAS_6_MESES, texto:String = ""):Boolean {
			if (!componenteDataInicio.selectedDate) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG021, texto), componenteDataInicio);
				return false;
			} else if (!componenteDataFim.selectedDate) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG022, texto), componenteDataFim);
				return false;
			} else if (compareData(componenteDataInicio.selectedDate,componenteDataFim.selectedDate) == 1) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG023, texto), componenteDataFim);
				return false;
			} else if (!validaRangeData(componenteDataInicio.selectedDate, componenteDataFim.selectedDate, range)) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG024, range), componenteDataFim);
				return false;
			} else {
				return true;
			}
		}
		
	}
}