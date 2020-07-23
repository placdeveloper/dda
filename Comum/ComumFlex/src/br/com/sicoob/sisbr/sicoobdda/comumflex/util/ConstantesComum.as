package br.com.sicoob.sisbr.sicoobdda.comumflex.util
{
	public class ConstantesComum
	{
		public static const PARAM_DIRETORIO_ARQUIVOS_SDDA:Number = 1947;
		
		// Constantes relacionadas ao combo de Tipo Situação do Cedente.
		public static const ATIVO:String = "ATIVO";
		public static const INATIVO:String = "INATIVO";
		public static const SUSPENSO:String = "SUSPENSO";
		public static const CANCELADO:String = "CANCELADO";
		
		// Constantes relacionadas ao combo de Tipo Perfil Conta do Perfil Tarifario
		public static const PESSOA_FISICA:String = "Pessoa Física";
		public static const PESSOA_JURIDICA:String = "Pessoa Jurídica";
		
		// Constantes relacionadas ao combo de Tipo Pessoa
		public static const TIPO_PESSOA_FISICA:Number = 0;
		public static const TIPO_PESSOA_JURIDICA:Number = 1;
		
		public static const TIPO_SIM:Number = 1;
		public static const TIPO_NAO:Number = 0;
		
		public static const BAIXA_AUTOMATICA_MINIMO:Number = 30;
		public static const BAIXA_AUTOMATICA_MAXIMO:Number = 180;
		public static const BAIXA_AUTOMATICA_INCREMENTO:Number = 30;
		
		// Constantes relacionadas a campos em que a opção pode ser Sim | Não
		public static const SIM:String = "Sim";
		public static const NAO:String = "Não";
		
		public static const NOME_PERCENTUAL:String = "Percentual";
		public static const NOME_VALOR:String = "Valor";
		
		public static const NOME:int = 0;
		public static const CNPJ:int = 1;
		public static const CPF:int = 2;
		
		public static const SINTETICO:int = 1;
		public static const ANALITICO:int = 2;
		
		public static const SOLICITADA:int = 1;
		public static const AGENDADA:int = 2;
		
		public static const LABEL_SOLICITADA:String = "Solicitada";
		public static const LABEL_AGENDADA:String = "Agendada";
		
		// Constantes relacionados com a pesquisa do Beneficiairo de acordo com a situacao do contrato
		public static const SITUACAO_CONTRATO_APROVADO:Number = 1;
		public static const SITUACAO_CONTRATO_ATIVO:Number = 2;
		public static const SITUACAO_CONTRATO_INATIVO:Number = 3;
		public static const SITUACAO_CONTRATO_RESCINDIDO:Number = 4;
		public static const SITUACAO_CONTRATO_SIMULACAO:Number = 5;
		
		public static const MODO_INCLUIR:Number = 1;
		public static const MODO_EDITAR:Number = 2;
		public static const MODO_VISUALIZAR:Number = 3;
		
		public static const HABILITAR:int = 1;
		public static const DESABILITAR:int = 2;
		
		public static const COD_EVENTO_TARIFAVEL_VIGENTE:Number = 1;
		public static const COD_EVENTO_TARIFAVEL_PROGRAMADO:Number = 2;
		public static const COD_EVENTO_TARIFAVEL_VENCIDO:Number = 3;
		
		// Constantes relacionadas a campos em que a opção pode ser CPNJ | CPF
		public static const CPNJ_CPF:Array = [
			{label:"CNPJ", data:CNPJ},
			{label:"CPF", data:CPF}
		];
		
		public static const COB_SITUACAO_BOLETO:Array = [
			{label:"EM ABERTO", data:SITUACAO_BOLETO_EM_ABERTO},
			{label:"LIQUIDADO", data:SITUACAO_BOLETO_LIQUIDADO},
			{label:"BAIXADO", data:SITUACAO_BOLETO_BAIXADO}
		];
		
		public static const CMB_STATUS_VIGENCIA:Array = [
			{label:"PROGRAMADO", data:COD_EVENTO_TARIFAVEL_PROGRAMADO},
			{label:"VIGENTE", data:COD_EVENTO_TARIFAVEL_VIGENTE},
			{label:"VENCIDO", data:COD_EVENTO_TARIFAVEL_VENCIDO}
		];
		
		// Constantes relacionadas a campos em que a opção pode ser CPNJ | CPF
		public static const CPF_CNPJ:Array = [
			{label:"CPF", data:CPF},
			{label:"CNPJ", data:CNPJ}
		];
		
		// Constantes relacionadas a campos em que a opção pode ser CPNJ | CPF
		public static const NOME_CPF_CPNJ:Array = [
			{label:"NOME", data:NOME},
			{label:"CPF", data:CPF},
			{label:"CNPJ", data:CNPJ}
		];
		
		// Constantes relacionadas a campos em que a opção pode ser NOME
		public static const CONSULTA_NOME:Array = [
			{label:"NOME", data:NOME}
		];
		
		// Constantes relacionadas a campos em que a opção pode ser SINTETICO / ANALITICO
		public static const SINTETICO_ANALITICO:Array = [
			{label:"SINTÉTICO", data:SINTETICO},
			{label:"ANALÍTICO", data:ANALITICO}
		];
		
		// Constantes relacionadas a campos em que a opção pode ser SINTETICO
		public static const CONSULTA_SINTETICO:Array = [
			{label:"SINTÉTICO", data:SINTETICO}
		];
		
		// Constantes relacionadas a campos em que a opção pode ser SOLICITADA / AGENDADA
		public static const SOLICITADA_AGENDADA:Array = [
			{label:LABEL_SOLICITADA, data:SOLICITADA},
			{label:LABEL_AGENDADA, data:AGENDADA}
		];
		
		// Constantes relacionadas a campos em que a opção pode ser Habilitar | Desabilitar
		public static const HABILITAR_DESABILITAR:Array = [
			{label:"HABILITAR", data:HABILITAR},
			{label:"DESABILITAR", data:DESABILITAR}
		];
		
		//Constante Array UF
		// UF contem siglaUF e id - Usar com slice()
		public static const ARRAY_UF:Array = [
			{label:"AC",id:1},
			{label:"AL",id:2},
			{label:"AM",id:3},
			{label:"AP",id:4},
			{label:"BA",id:5},
			{label:"CE",id:6},
			{label:"DF",id:7},
			{label:"ES",id:8},
			{label:"GO",id:9},
			{label:"MA",id:10},
			{label:"MG",id:11},
			{label:"MS",id:12},
			{label:"MT",id:13},
			{label:"PA",id:14},
			{label:"PB",id:15},
			{label:"PE",id:16},
			{label:"PI",id:17},
			{label:"PR",id:18},
			{label:"RJ",id:19},
			{label:"RN",id:20},
			{label:"RO",id:21},
			{label:"RR",id:22},
			{label:"RS",id:23},
			{label:"SC",id:24},
			{label:"SE",id:25},
			{label:"SP",id:26},
			{label:"TO",id:27}
		];
		
		// Máscara
		public static const MASCARA_DATA:String = "dd/MM/yyyy";
		
		public static const TIPO_ATIVO:int = 1;
		public static const TIPO_INATIVO:int = 0;
		
		public static const ARRAY_SITUACAO:Array = [{label:ATIVO, data:TIPO_ATIVO}, {label:INATIVO, data:TIPO_INATIVO}];
		
		public static const TIPO_PESSOA:Array = [
			{label:PESSOA_FISICA, data:TIPO_PESSOA_FISICA},
			{label:PESSOA_JURIDICA, data:TIPO_PESSOA_JURIDICA}
		];	
		
		public static const SIM_NAO:Array = [
			{label:SIM, data:TIPO_SIM},
			{label:NAO, data:TIPO_NAO}
		];
		
		public static const ARRAY_MESES:Array = [
			{label:"JANEIRO", data:0},
			{label:"FEVEREIRO", data:1},
			{label:"MARÇO", data:2},
			{label:"ABRIL", data:3},
			{label:"MAIO", data:4},
			{label:"JUNHO", data:5},
			{label:"JULHO", data:6},
			{label:"AGOSTO", data:7},
			{label:"SETEMBRO", data:8},
			{label:"OUTUBRO", data:9},
			{label:"NOVEMBRO", data:10},
			{label:"DEZEMBRO", data:11}
		];
		
		// Constantes relacionadas ao tipo de cooperativa
		public static const TIPO_CENTRAL:int = 1;
		public static const TIPO_SINGULAR:int = 2;
		public static const TIPO_SICOOB:int = 3;
		
		//Ano de start na combo ano de vigencia da funcionalidade condição operacional.
		public static const ANO_INICIAL:int = 2012;
		
		public static const ICONE_JANELA:String = "br/com/bancoob/imagens/icos/modulos/cadastroClientes_peq.png";
		
		public static const FORMATO_RELATORIO_PDF:String = "PDF";
		
		// Ícones
		[Embed(source="/br/com/sicoob/sisbr/sicoobdda/comumflex/imagens/opts_16.png")]
		public static const icoSalvar:Class;
		
		[Embed(source="/br/com/sicoob/sisbr/sicoobdda/comumflex/imagens/close_16.png")]
		public static const icoFechar:Class;
		
		[Embed(source="/br/com/sicoob/sisbr/sicoobdda/comumflex/imagens/icoImpressora.png")]
		public static const icoImprimir:Class;
		
		[Embed(source="/br/com/sicoob/sisbr/sicoobdda/comumflex/imagens/hist_16.png")]
		public static const icoHistorico:Class;
		
		[Embed(source="/br/com/sicoob/sisbr/sicoobdda/comumflex/imagens/confirm.png")]
		public static const icoConfirmar:Class;
		
		// Serviços
		public static const SERVICO_CENTRAIS_SINGULARES:String = "br.com.sicoob.sisbr.sicoobdda.comumfachada.servico.CentraisSingularesServico";
		public static const SERVICO_RELATORIO_DDA:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.RelatorioServico";
		
		// Metodos
		public static const METODO_GERAR_RELATORIO_DDA:String = "gerar";
		
		// Conta corrente
		public static const CONTA_ATIVA:int = 1;
		
		// Tipo perfil tarifário
		public static const TIPO_PERFIL_TARIFARIO_ISENTO:Number = 2;
		public static const TIPO_PERFIL_TARIFARIO_COOPERATIVA:Number = 3;
		
		// Título das janelas
		public static const PERFIL_TARIFARIO_VISUALIZAR:String = "VISUALIZAR PERFIL TARIFÁRIO";
		
		// Modalidade
		public static const MODALIDADE_COM_REGISTRO:int = 1;
		public static const MODALIDADE_SEM_REGISTRO:int = 2;
		
		// Distribuição
		public static const DISTRIBUICAO_COOPERATIVA:int = 1;
		public static const DISTRIBUICAO_BENEFICIARIO:int = 2;
		
		// Local de impressão
		public static const LOCAL_IMPRESSAO_COOPERATIVA:int = 1;
		public static const LOCAL_IMPRESSAO_BENEFICIARIO:int = 2;
		
		// Valor máximo do percentual
		public static const TOTAL_MAX_PERCENTUAL:Number = 100;
		
		public static const LABEL_NAO_INFORMADO:String = "NÃO INFORMADO";
		
		public static const TIPO_PESSOA_DDA_AVALISTA:String = "0 - Isento / Não Informado";
		
		public static const TIPO_DESCONTO_ISENTO:String = "0 - Isento";
		
		// Dias da semana (utilizado no componente de data)
		public static const DIAS_SEMANA:Array = new Array("D", "S", "T", "Q", "Q", "S", "S");
		
		// Meses do ano (utilizado no componente de data)
		public static const MESES_ANO:Array = new Array("JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO");
		
		//Tamanho máximo para arquivo de imagens dos boletos
		public static const TAMANHO_MAX_IMAGE_BOLETO_MSG:String = " 2MB.";
		public static const TAMANHO_MAX_IMAGE_BOLETO_BYTES:Number = 2097152;//=2MB 
		
		public static const REGEX_TAB:RegExp = /(\t)/gi;
		public static const REGEX_NUMBER:RegExp = /\D/g;
		public static const TAB:String = "    ";
		
		//Numero Minimo para a validacao do telefone;
		public static const NUMERO_MINIMO_TELEFONE:Number = 8;
		
		// Situação do processamento
		public static const SITUACAO_PROCESSAMENTO_PENDENTE:int = 1;
		public static const SITUACAO_PROCESSAMENTO_PROCESSANDO:int = 2;
		public static const SITUACAO_PROCESSAMENTO_FINALIZADO:int = 3;
		public static const SITUACAO_PROCESSAMENTO_COM_ERRO:int = 4;
		
		public static const TIPO_ARQUIVO_PAGADOR_IMPORTACAO:int = 1;
		public static const TIPO_ARQUIVO_PAGADOR_EXPORTACAO:int = 2;
		
		public static const MAXIMO_DIAS_FLOAT:int = 5;
		
		public static const DIAS_FLOAT:Array = [
			{label:"D+0", data:0},
			{label:"D+1", data:1},
			{label:"D+2", data:2},
			{label:"D+3", data:3},
			{label:"D+4", data:4},
			{label:"D+5", data:5}
		];
		
		public static const MINIMO_NEGATIVACAO:int = 5;
		public static const MAXIMO_NEGATIVACAO:int = 99;
		
		public static const MINIMO_PROTESTO:int = 1;
		public static const MAXIMO_PROTESTO:int = 10;

		public static const DIA:int = 2;
		public static const PERCENTUAL:int = 3;
		
		// Constantes relacionadas ao tipo de código mora
		public static const TIPO_CODIGO_MORA:Array = [
			{label:"Valor por dia (R$)", data:DIA},
			{label:"Percentual (% a.m.)", data:PERCENTUAL}
		];
		
		public static const IND_VALOR_PERCENTUAL:Array = [
			{label:"Valor Mínimo", data:"V"},
			{label:"Percentual", data:"P"}
		];
		
		public static const IND_VALOR_MAXIMO_PERCENTUAL:Array = [
			{label:"Valor Máximo", data:"V"},
			{label:"Percentual", data:"P"}
		];

		public static const BANCO_DEPOSITARIO_BANCO_DO_BRASIL:int = 1;
		public static const BANCO_DEPOSITARIO_BRADESCO:int = 237;
		public static const BANCO_DEPOSITARIO_SICOOB:int = 756;
		
		// Constantes relacionadas ao tipo de carteira
		public static const CARTEIRA_SIMPLES:Number=1;
		
		// Constatnes relacionadas a situação do boleto
		public static const SITUACAO_BOLETO_EM_ABERTO:Number = 1;
		public static const SITUACAO_BOLETO_CREDITO_LIBERADO_EM_CONTA_BENEFICIARIO:Number = 2;
		public static const SITUACAO_BOLETO_BAIXADO:Number = 18;
		public static const SITUACAO_BOLETO_LIQUIDADO:Number = 23;
		public static const SITUACAO_BOLETO_NEGATIVADO:Number = 4;
		public static const SITUACAO_BOLETO_APONTADO_EM_CARTORIO:Number = 27;
		public static const SITUACAO_BOLETO_PROTESTADO:Number = 6;
		public static const SITUACAO_BOLETO_OPERACAO_DE_CREDITO:Number = 7;
		public static const SITUACAO_BOLETO_VENCIDO:Number = 8;
		
		public static const FILTRO_CARTEIRA:Array = [
			{label:"Simples", data:ConstantesComum.CARTEIRA_SIMPLES},
		];

		public static const TOOL_TIP_SEGUNDA_VIA:String = "Somente serão listados boletos que não contenham as seguintes configurações:\n- Protesto: boletos que serão encaminhados para protesto no dia;\n- Baixa automática: boletos que serão baixados no dia;";

		public static const ID_BANCOOB:int = 1;
		public static const ID_SICOOB:int = 2;

		public static const REGEX_CAMPO_DESCRICAO:String = "a-zA-Z0-9áàâãéèêíïóôõöúçÁÀÂÃÉÈÊÍÏÓÒÖÚÇ\\´\\,\\.\\;\\:\\/\\?\\<\\>\\^\\~\\[\\]\\{\\}\\ª\\º\\'\\!\\@\\#\\$\\%\\&\\*\\(\\)\\-\\_\\+\\=\\*\"\\ ";
		
		
		// Situacao processamento ARQUIVO
		public static const ARQUIVO_DISPONIVEL:Number = 1;
		public static const ARQUIVO_ABERTO:Number = 2;
		public static const REGISTROS_DETALHADOS:Number = 3;
		public static const ARQUIVO_PROCESSADO:Number = 5;
		public static const ARQUIVO_REJEITADO:Number = 6;
		
		//Lengths Padrões
		public static const LENGTH_COD_DE_BARRAS:Number = 44;
		public static const LENGTH_LINHA_DIGITAVEL:Number = 47;
		public static const LENGTH_CNPJ:Number = 14;
		public static const LENGTH_CPF:Number = 11;
		public static const LENGTH_CEP:Number = 8;
		
		// Parametro - 45 -Tempo para resposta da consulta DDA0110 (Em segundos)
		public static const ID_PARAMETRO_CONSULTA_DDA0110:Number = 45;
		
		public static const COR_AMARELO:uint = 0xFFFFCC00;
		public static const COR_VERMELHO:uint = 0xFF990000;
		public static const COR_VERDE:uint = 0xFF00B21D;
		public static const COR_PRETO:uint = 0xFF000000;
		public static const COR_ROXO:uint = 0xFF990099;
		
		public static const SITUACAO_RATEIO_AGUARDANDO_APROVACAO:Number = 1;
		public static const SITUACAO_RATEIO_APROVADO_PARA_EFETIVACAO:Number = 2;
		public static const SITUACAO_RATEIO_PROCESSANDO_EFETIVACAO:Number = 3;
		public static const SITUACAO_RATEIO_EFETIVADO:Number = 4;
		public static const SITUACAO_RATEIO_ERRO_EFETIVACAO:Number = 5;
		
	}
}