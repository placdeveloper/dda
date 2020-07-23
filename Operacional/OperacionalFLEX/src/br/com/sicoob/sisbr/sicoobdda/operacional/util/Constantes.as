package br.com.sicoob.sisbr.sicoobdda.operacional.util
{

	public class Constantes
	{
		// Tipo Origem Beneficiario - combo origem painel de mensagens
		public static const ORIGEM_SICOOB:String = "SICOOB";
		public static const ORIGEM_CIP:String = "CIP";
		public static const PESQ_SICOOB:Boolean = true;
		public static const PESQ_CIP:Boolean = false;
		public static const PARAMETRO_LOCAL_ARQUIVOS_ABERTOS:Number = 37;
		
		public static const ID_TIPO_PARAMETRO_NUMBER:Number = 1;
		public static const ID_TIPO_PARAMETRO_DECIMAL:Number = 2;
		public static const ID_TIPO_PARAMETRO_BOOLEAN:Number = 3;
		public static const ID_TIPO_PARAMETRO_STRING:Number = 4;
		public static const ID_TIPO_PARAMETRO_DATA:Number = 5;
		
		public static const TIPO_ORIGEM:Array = [
			{label:ORIGEM_SICOOB, data:PESQ_SICOOB},
			{label:ORIGEM_CIP, data:PESQ_CIP}
		];
		
		// Tipo Status Envio Mensagem
		public static const SUCESSO_ENVIO:String = "SUCESSO";
		public static const ERRO_ENVIO:String = "ERRO";
		public static const TRATADO_ENVIO:String = "TRATADO";
		
		public static const STATUS_ENVIO:Array = [
//			{label:TRATADO_ENVIO, data:2}, removido Mantis 11735
			{label:SUCESSO_ENVIO, data:1},
			{label:ERRO_ENVIO, data:0}
		];
		
		public static const CMB_TIPO_DATA:Array = [
			{label:"Data de Inclusão", data:1},
			{label:"Data de Aprovação", data:2},
			{label:"Data dos Eventos", data:3}
		];
		
		public static const CONFIRMA:Array = [
			{label:"NÃO", data:0},
			{label:"SIM", data:1}
		];
		
		// Serviços
		public static const SERVICO_ARQUIVO_RECEBIDO:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.ArquivoRecebidoServico";
		public static const SERVICO_ARQUIVO_ENVIADO:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.ArquivoEnviadoServico";
		public static const SERVICO_MENSAGEM_DDA:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.MensagemDDAServico";
		public static const SERVICO_MONITORACAO_MENSAGEM_DDA:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.MonitoramentoMensagensDDAServico";
		public static const SERVICO_MONITORACAO:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.MonitoracaoServico";
		public static const SERVICO_PARAMETRO:String = "br.com.sicoob.sisbr.sicoobdda.comumfachada.servico.ParametroServico";
		public static const SERVICO_GRADE_HORARIA:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.GradeHorariaServico";
		public static const SERVICO_PARAMETRO_INSTITUICAO:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.ManterParametroInstituicaoServico";
		public static const SERVICO_TIPO_GRADE_HORARIA:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.TipoGradeHorariaServico";	
		public static const SERVICO_TRATAMENTO_PENDENCIA_ERRO:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.TratamentoPendenciaErroServico";
		public static const SERVICO_TIPO_ERRO_CIP:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.TipoErroCipServico";
		public static const SERVICO_TIPO_MENSAGEM:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.TipoMensagemServico";
		public static const SERVICO_PAGADOR_ELETRONICO:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.PagadorEletronicoDDAServico";
		public static const SERVICO_CONTINGENCIA:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.ContingenciaServico";
		public static const SERVICO_BENEFICIARIOSALERTA:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.BeneficiariosAlertaServico";
		public static const SERVICO_PROCESSADOR_ARQUIVO_CIP:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.ArquivoRecebidoCIPProcessadorServico";
		public static const SERVICO_MANUTENCAO_MENSAGEMDDABOLETO:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.ManutencaoMensagemDDABoletoServico";
		public static const SERVICO_CONSULTA_BOLETO:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.ConsultaBoletoDDAServico";
		public static const SERVICO_MANTER_EVENTO_TARIFAVEL:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.ManterEventoTarifavelDDAServico";
		public static const SERVICO_CONSULTA_TARIFAS_DDA:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.ConsultaTarifasDDAServico";
		public static const SERVICO_RATEIO_TARIFAS_CIP:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.RateioTarifasCIPServico";
		public static const SERVICO_MONITORACAO_CACHE:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.MonitoracaoCacheServico";
		public static const SERVICO_SERVIDOR_DDA:String = "br.com.sicoob.sisbr.sicoobdda.operacional.servicos.ServidorDDAServico";
		
		/*public static const SERVICO_RELATORIO_RATEIO_TARIFAS_CIP:String = "sicoobdda_operacional/RateioTarifasCipServicoRemote";
		public static const SERVICO_RELATORIO_PAGADOR_ELETRONICO:String = "sicoobdda_operacional/PagadorEletronicoDDAServicoRemote";
		public static const SERVICO_RELATORIO_CONTINGENCIA:String = "sicoobdda_operacional/ContingenciaServicoRemote";
		public static const SERVICO_RELATORIO_CONSULTA_BOLETO:String = "sicoobdda_operacional/ConsultaBoletoDDAServicoRemote";
		public static const SERVICO_MANTER_EVENTO_TARIFAVEL_REMOTE:String = "sicoobdda_operacional/EventoTarifavelDDAServicoRemote";	
		public static const SERVICO_MONITORACAO_MENSAGEM_DDA_REMOTE:String = "sicoobdda_operacional/MonitoramentoMensagensDDAServicoRemote";
		public static const SERVICO_RELATORIO_BENEFICIARIOSALERTA:String = "sicoobdda_operacional/BeneficiariosAlertaServicoRemote";
		public static const SERVICO_RATEIO_DDA_LANCAMENTO_REMOTE:String = "sicoobdda_operacional/RateioDDALancamentoServicoRemote";*/
		//Titulo painel Monitoracao CIP
		public static const DETALHAR_MENSAGEM:String = "DETALHAR MENSAGEM";
		public static const DETALHAR_ARQUIVO:String = "DETALHAR ARQUIVO";
		public static const DETALHAR_ERRO_CARGA:String = "DETALHAR ERRO CARGA";
		
		//Define abas do painel Monitoracao Mensagens
		public static const ABA_MENSAGENS:String = "MENSAGENS";
		public static const ABA_ARQUIVOS:String = "ARQUIVOS";
		
		public static const OPT_TODOS:int = 0;
		public static const OPT_SICOOB:int = 2;
		public static const OPT_DDA:int = 1;
		
		// Tamanho máximo do campo linha digitável e código de barras
		public static const TAMANHO_MAXIMO_LINHA_DIGITAVEL:int = 47;
		public static const TAMANHO_MAXIMO_CODIGO_BARRAS:int = 44;
		
		public static const DETALHAMENTO_BOLETO:String = "DETALHAMENTO DO BOLETO";
		
		public static const GERAR_RELATORIO:String = "Gerando Impressão...";
		public static const EMITIR_RELATORIO:String = "Emitindo relatório...";
		
		public static const PERFIL_SERVIDORDDA:Array = [
			{label:"BACKOFFICE"},
			{label:"FRONTOFFICE"},
			{label:"PROCESSAMENTO"}
		];
		
		public static const CACHE:Array = [
			{label:"SERVIDOR", data:"SERVIDOR"},
			{label:"PARÂMETRO", data:"PARAMETRO"},
			{label:"INSTITUIÇÃO", data:"INSTITUICAO"},
			{label:"AUTORIZAÇÃO VALOR DIVERGENTE", data:"DOMINIO_AUTORIZACAO_VALOR_DEVERGENTE"},
			{label:"SITUAÇÃO BOLETO PAGAMENTO", data:"DOMINIO_SITUACAO_BOLETO_PAGAMENTO"},
			{label:"GRADE HORÁRIA DDA0110", data:"DOMINIO_GRADE_HORARIA_DDA0110"}
		];
	}
}
