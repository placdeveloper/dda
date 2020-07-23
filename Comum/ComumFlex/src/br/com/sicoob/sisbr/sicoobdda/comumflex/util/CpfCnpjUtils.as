package br.com.sicoob.sisbr.sicoobdda.comumflex.util
{
	

	public class CpfCnpjUtils
	{
		private static const CPF_LENGTH:int = 11;
		private static const CNPJ_LENGTH:int = 14;
		
		public function CpfCnpjUtils()
		{
		}
		
		public static function isTipoIgualCNPJ(tipo:String, cnpj:String):Boolean {
			return (tipo == "J" && cnpj.length == CNPJ_LENGTH);
		}
		
		public static function isTipoIgualCPF(tipo:String, cpf:String):Boolean {
			return (tipo == "F" && cpf.length == CPF_LENGTH);
		}
		
		public static function isTipoIgualDocumento(tipo:String, doc:String):Boolean {
			var ret:Boolean = true;
			if(tipo == "F") {
				ret = isTipoIgualCPF(tipo, doc);
			}
			
			if(tipo == "J") { 
				ret = isTipoIgualCNPJ(tipo, doc);
			}
			return ret;
		}
	}
}