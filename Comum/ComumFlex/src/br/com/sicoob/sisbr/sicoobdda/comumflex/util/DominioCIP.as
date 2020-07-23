package br.com.sicoob.sisbr.sicoobdda.comumflex.util
{
	public class DominioCIP {
		
		public static const TIPO_PESSOA_FISICA:String = "F";
		public static const TIPO_PESSOA_JURIDICA:String = "J";
		
		public static const GRUPO_BENEFICIARIO:String = "B";
		public static const GRUPO_PAGADOR:String = "P";
		public static const GRUPO_TITULO:String = "T";
			
		public static const TIPO_PESSOA:Array = [
			{data:TIPO_PESSOA_FISICA, label:ConstantesComum.PESSOA_FISICA},
			{data:TIPO_PESSOA_JURIDICA, label:ConstantesComum.PESSOA_JURIDICA}
		];
		
		public static const SIT_REL_PARTICIPANTE:Array = [
			{data:"A", label:"Ativo"},	
			{data:"E", label:"Excluído"}
		];
		
		public static const SIT_BENEFICIARIO:Array = [
			{data:"A", label:"Apto"},
			{data:"E", label:"Em Análise"},
			{data:"I", label:"Inapto"}
		];
		
		public static const TIPO_SOLICITACAO:Array = [
			{data:GRUPO_BENEFICIARIO, label:"Grupo Beneficiário"},
			{data:GRUPO_PAGADOR, label:"Grupo Pagador"},
			{data:GRUPO_TITULO, label:"Grupo Título"}
		];
		
		public static const TIPO_SOL_BENEFICIARIO:Array = [
			{data:"ID", label:"Inventário de Beneficiários Destinatários"},
			{data:"CD", label:"Carga Inicial de Beneficiários Destinatários"}
		];
		
		public static const TIPO_SOL_PAGADOR:Array = [
			{data:"IV", label:"Inventário de Pagadores"},
			{data:"CI", label:"Carga Inicial de Pagadores"}
		];

		public static const TIPO_SOL_TITULO:Array = [
			{data:"IB", label:"Inventário de Bloquetos"},
			{data:"IL", label:"Inventário de Boletos Baixados"}
		];

		public static const TIPO_TITULO_CONSULTADO:Array = [
			{data:"P", label:"Próprio"},
			{data:"T", label:"Terceiros"}
		];
		
		public static const TIPO_TRANSACAO:Array = [
			{data:"01", label:"Bloqueto - Inserção pelo Banco Cedente"},
			{data:"02", label:"Bloqueto - Consulta pelo Banco Sacado"},
			{data:"03", label:"Bloqueto - Baixa por liquidação Interbancária"},
			{data:"04", label:"Bloqueto - Consulta pelo Banco Cedente"},
			{data:"05", label:"Bloqueto - Baixa por solicitação do Banco Cedente"},
			{data:"06", label:"Bloqueto - Baixa por decurso de prazo"},
			{data:"20", label:"Bloqueto - Inserção com erro"},
			{data:"30", label:"Extrato Analítico"},
			{data:"40", label:"Beneficiário – Consulta Sob Demanda"},
			{data:"41", label:"Beneficiário – Consulta Carga Inicial"},
			{data:"42", label:"Beneficiário – Consulta Inventário"}
		];

		public static const TIPO_CONSULTA:Array = [
			{data:"A", label:"Analítica - por ID DDA"},
			{data:"C", label:"Consolidada - por transação DDA"}
		];
		
		public static const TIPO_RETORNO:Array = [
			{data:"M", label:"Mensagem"},
			{data:"X", label:"Arquivo XML"}
		];
		
		public static const TIPO_MENSAGEM_ARQUIVO:Array = [
			{data:"M", label:"Mensagem"},
			{data:"A", label:"Arquivo"}
		];
		
		public static const TIPO_HORARIO:Array = [
			{data:"P", label:"Padrão"},
			{data:"E", label:"Eventual"}
		];
		
		public static const TIPO_FILTRO_BOLETO:Array = [
			{data:0, label:"Número Código de Barras"},
			{data:1, label:"Número Identificador Boleto"}
		];
		
	}
}