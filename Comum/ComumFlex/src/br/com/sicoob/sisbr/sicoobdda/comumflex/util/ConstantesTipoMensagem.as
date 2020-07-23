package br.com.sicoob.sisbr.sicoobdda.comumflex.util
{
	public class ConstantesTipoMensagem	{
		
		public function ConstantesTipoMensagem() {
		}
		
		public static const ADDA001:String    = 'ADDA001';
		public static const ADDA002:String    = 'ADDA002';
		public static const ADDA003:String    = 'ADDA003';
		public static const ADDA005:String    = 'ADDA005';
		public static const ADDA006:String    = 'ADDA006';
		public static const ADDA101:String    = 'ADDA101';
		public static const ADDA101RR2:String = 'ADDA101RR2';
		public static const ADDA102:String    = 'ADDA102';
		public static const ADDA102RR2:String = 'ADDA102RR2';
		public static const ADDA104:String    = 'ADDA104';
		public static const ADDA104RR2:String = 'ADDA104RR2';
		public static const ADDA106:String    = 'ADDA106';
		public static const ADDA108:String    = 'ADDA108';
		public static const ADDA108RR2:String = 'ADDA108RR2';
		public static const ADDA110:String    = 'ADDA110';
		public static const ADDA114:String    = 'ADDA114';
		public static const ADDA114RR2:String = 'ADDA114RR2';
		public static const ADDA115:String    = 'ADDA115';
		public static const ADDA115RR2:String = 'ADDA115RR2';
		public static const ADDA117:String    = 'ADDA117';
		public static const ADDA118:String    = 'ADDA118';
		public static const ADDA118RR2:String = 'ADDA118RR2';
		public static const ADDA120:String    = 'ADDA120';
		public static const ADDA121:String    = 'ADDA121';
		public static const ADDA121RR2:String = 'ADDA121RR2';
		public static const ADDA121RR3:String = 'ADDA121RR3';
		public static const ADDA122:String    = 'ADDA122';
		public static const ADDA122RR2:String = 'ADDA122RR2';
		public static const ADDA127:String    = 'ADDA127';
		public static const ADDA200:String    = 'ADDA200';
		public static const ADDA500:String    = 'ADDA500';
		public static const ADDA501:String    = 'ADDA501';
		public static const ADDA502:String    = 'ADDA502';
		public static const ADDA503:String    = 'ADDA503';
		public static const ADDA504:String    = 'ADDA504';
		public static const ADDA505:String    = 'ADDA505';
		public static const ADDA506:String    = 'ADDA506';
		public static const DDA0001:String    = 'DDA0001';
		public static const DDA0002:String    = 'DDA0002';
		public static const DDA0005:String    = 'DDA0005';
		public static const DDA0006:String    = 'DDA0006';
		public static const DDA0101:String    = 'DDA0101';
		public static const DDA0101R2:String  = 'DDA0101R2';
		public static const DDA0102:String    = 'DDA0102';
		public static const DDA0102R2:String  = 'DDA0102R2';
		public static const DDA0104:String    = 'DDA0104';
		public static const DDA0104R2:String  = 'DDA0104R2';
		public static const DDA0106:String    = 'DDA0106';
		public static const DDA0108:String    = 'DDA0108';
		public static const DDA0108R2:String  = 'DDA0108R2';
		public static const DDA0110:String    = 'DDA0110';
		public static const DDA0115:String    = 'DDA0115';
		public static const DDA0115R2:String  = 'DDA0115R2';
		public static const DDA0118:String    = 'DDA0118';
		public static const DDA0118R2:String  = 'DDA0118R2';
		public static const DDA0121:String    = 'DDA0121';
		public static const DDA0121R2:String  = 'DDA0121R2';
		public static const DDA0121R3:String  = 'DDA0121R3';
		public static const DDA0122:String    = 'DDA0122';
		public static const DDA0122R2:String  = 'DDA0122R2';
		public static const DDA0127:String    = 'DDA0127';
		public static const DDA0200:String    = 'DDA0200';
		public static const DDA0214:String    = "DDA0214";
		public static const DDA0215:String    = 'DDA0215';
		public static const DDA0401:String    = 'DDA0401';
		public static const DDA0501:String    = 'DDA0501';
		public static const DDA0502:String    = 'DDA0502';
		public static const DDA0503:String    = 'DDA0503';
		public static const DDA0504:String    = 'DDA0504';
		public static const DDA0505:String    = 'DDA0505';
		public static const DDA0506:String    = 'DDA0506';
		public static const GEN0014:String    = "GEN0014";
		public static const AGEN001:String	  = "AGEN001";
		
		public static const TIPO_MENSAGEM_CADASTRO:Array = [
			{codTipo:AGEN001, descTipo:AGEN001 + " - Participante requisita teste de conectividade"},
			{codTipo:GEN0014, descTipo:GEN0014 + " - Participante requisita arquivo"},
			{codTipo:DDA0002, descTipo:DDA0002 + " - Participante consulta cadastro de pagador"},
			{codTipo:DDA0106, descTipo:DDA0106 + " - Participante solicita consulta na base de Títulos"},
			{codTipo:DDA0110, descTipo:DDA0110 + " - Participante consulta boleto de pagamaneto para pagamento"},
			{codTipo:DDA0200, descTipo:DDA0200 + " - Participante consulta extrato de eventos tarifáveis"},
			{codTipo:DDA0214, descTipo:DDA0214 + " - Participante consulta extrato de processamento"},
			{codTipo:DDA0215, descTipo:DDA0215 + " - Participante Solicita reenvio de arquivo ou mensagem"},
			{codTipo:DDA0401, descTipo:DDA0401 + " - Participante consulta Grades de Processamento"},
			{codTipo:DDA0504, descTipo:DDA0504 + " - Consulta de beneficiários"}
		];
	
		public static const TIPO_MENSAGEM_DDA0214:Array = [
			{codTipo:ADDA001},
			{codTipo:ADDA002},
			{codTipo:ADDA003},
			{codTipo:ADDA005},
			{codTipo:ADDA006},
			{codTipo:ADDA101},
			{codTipo:ADDA101RR2},
			{codTipo:ADDA102},
			{codTipo:ADDA102RR2},
			{codTipo:ADDA104},
			{codTipo:ADDA104RR2},
			{codTipo:ADDA106},
			{codTipo:ADDA108},
			{codTipo:ADDA108RR2},
			{codTipo:ADDA110},
			{codTipo:ADDA114},
			{codTipo:ADDA114RR2},
			{codTipo:ADDA115},
			{codTipo:ADDA115RR2},
			{codTipo:ADDA117},
			{codTipo:ADDA118},
			{codTipo:ADDA118RR2},
			{codTipo:ADDA120},
			{codTipo:ADDA121},
			{codTipo:ADDA121RR2},
			{codTipo:ADDA121RR3},
			{codTipo:ADDA122},
			{codTipo:ADDA122RR2},
			{codTipo:ADDA127},
			{codTipo:ADDA200},
			{codTipo:ADDA500},
			{codTipo:ADDA501},
			{codTipo:ADDA502},
			{codTipo:ADDA503},
			{codTipo:ADDA504},
			{codTipo:ADDA505},
			{codTipo:ADDA506},
			{codTipo:DDA0001},
			{codTipo:DDA0002},
			{codTipo:DDA0005},
			{codTipo:DDA0006},
			{codTipo:DDA0101},
			{codTipo:DDA0101R2},
			{codTipo:DDA0102},
			{codTipo:DDA0102R2},
			{codTipo:DDA0104},
			{codTipo:DDA0104R2},
			{codTipo:DDA0106},
			{codTipo:DDA0108},
			{codTipo:DDA0108R2},
			{codTipo:DDA0110},
			{codTipo:DDA0115},
			{codTipo:DDA0115R2},
			{codTipo:DDA0118},
			{codTipo:DDA0118R2},
			{codTipo:DDA0121},
			{codTipo:DDA0121R2},
			{codTipo:DDA0121R3},
			{codTipo:DDA0122},
			{codTipo:DDA0122R2},
			{codTipo:DDA0127},
			{codTipo:DDA0200},
			{codTipo:DDA0215},
			{codTipo:DDA0501},
			{codTipo:DDA0502},
			{codTipo:DDA0503},
			{codTipo:DDA0504},
			{codTipo:DDA0505},
			{codTipo:DDA0506}
		];
		
		public static const TIPO_MENSAGEM_DDA0215_ARQ:Array = [
			{codTipo:ADDA101RR2},
			{codTipo:ADDA102RR2},
			{codTipo:ADDA118RR2}
		];
		
		public static const TIPO_MENSAGEM_DDA0215_MSG:Array = [
			{codTipo:DDA0101R2},
			{codTipo:DDA0102R2},
			{codTipo:DDA0118R2}
		];

	}
}