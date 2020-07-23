package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.beneficiariosalerta
{
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	
	public class BeneficiariosAlertaConstantes
	{
		public static const INAPTO:String = "I";
		public static const EM_ANALISE:String = "E";
		public static const STATUS_INAPTO:String = "Inapto";
		public static const STATUS_EM_ANALISE:String = "Em análise";
		public static const FISICA:String = "Física";
		public static const JURIDICA:String = "Jurídica";
		public static const TIPO_PESSOA_FISICA:String = "F";
		public static const TIPO_PESSOA_JURIDICA:String = "J";
		public static const LABEL_IMPRIMIR:String = "Imprimir";
		public static const LABEL_BOTOES:String = "botoes";
		public static const LABEL_ICON:String = "icon";
		public static const METODO_LISTAR_BANCOS_ORIGINADORES:String = "listarBancosOriginadores";
		public static const LISTA_BANCOS_ORIGINADORES:String = "listaBancosOriginadores";
		public static const GERAR_RELATORIO_BENEFICIARIOS:String = "gerarRelatorioBeneficiariosAlerta";
		
		public static const STATUS_ALERTA:Array = [
			{label:STATUS_INAPTO, data:INAPTO},
			{label:STATUS_EM_ANALISE, data:EM_ANALISE}
		];
		
		public static const TIPO_PESSOA:Array = [
			{label:ConstantesComum.PESSOA_FISICA, data:TIPO_PESSOA_FISICA},
			{label:ConstantesComum.PESSOA_JURIDICA, data:TIPO_PESSOA_JURIDICA}
		];	
		
	}
	
}