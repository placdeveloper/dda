package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio
{
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.PainelListaRelatorios;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressaoEvent;
	import br.com.bancoob.sisbr.relatorios.dto.RetornoRelatorioDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.util.vo.RelatorioVO;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.RelatorioDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	
	public final class RelatorioDDA extends EventDispatcher {
		
		private static var singleton:RelatorioDDA;
		
		private var _servico:ServicoJava;
		
		private var _contextoRelatorio:String;
		private var _objFiltro:Object;		
		private var _listaDados:ArrayCollection;
		private var _filtroRequisicao:RequisicaoReqDTO;
		
		public static function getInstance() : RelatorioDDA	{
			if (singleton == null) {
				singleton = new RelatorioDDA();
			}
			return singleton;
		}
		
		
		public function gerarRelatorio(contextoRelatorio:String, filtro:Object, listaDados:ArrayCollection = null, codFormato:String = null, filtroRequisicao:RequisicaoReqDTO = null, destino:DestinoVO = null):void {
			_contextoRelatorio = contextoRelatorio;
			_objFiltro = filtro;
			_listaDados = listaDados;
			_filtroRequisicao = filtroRequisicao;
			
			_servico = Funcoes.obterServico(ConstantesComum.SERVICO_RELATORIO_DDA, ConstantesComum.METODO_GERAR_RELATORIO_DDA);
			
			// Configurando o destino por causa da Plataforma CUC
			if (destino) {
				_servico.configurarDestino(destino);
			}
			
			_servico.addEventListener(ResultEvent.RESULT, emitir_onResult);
			_servico.addEventListener(FaultEvent.FAULT, emitir_onFault);
			_servico.mensagemEspera = "Emitindo Relatório...";
			_servico.bloquearOperacao = true;
			
			if (codFormato) {
				executarServico(codFormato);								
			} else {
				abrirJanelaPreImpressao();
			}
		}
		
		private function abrirJanelaPreImpressao():void {	
			var preImpressao:PreImpressaoRelatorio = new PreImpressaoRelatorio();
			preImpressao.addEventListener(PreImpressaoEvent.EVENTO_CONFIRMAR_OPCOES, opcaoConfirmada);
			preImpressao.abrirJanelaPreImpressao();
		}
		
		private function opcaoConfirmada(e:PreImpressaoEvent):void {
			executarServico(e.formato.codFormato);
		}
		
		private function executarServico(codFormato:String):void {
			var filtro:RelatorioDTO = new RelatorioDTO();
			filtro.contextoFlex = _contextoRelatorio;
			filtro.filtro = _objFiltro;
			filtro.listaDados = _listaDados;
			filtro.formato = codFormato;
			
			var dto:RequisicaoReqDTO = null;
			if (_filtroRequisicao) {
				dto = _filtroRequisicao;	
			} else {
				dto =  new RequisicaoReqDTO();
			}
			dto.dados["filtro"] = filtro;
			
			_servico.getOperation(ConstantesComum.METODO_GERAR_RELATORIO_DDA).send(dto);
		}
		
		private function emitir_onResult(event:ResultEvent) : void {
			var item:RetornoRelatorioDTO = null; // registrar o RetornoRelatorioDTO
			var relatorio:RelatorioVO = null;
			var array:ArrayCollection = new ArrayCollection();
			var arrayRelatorios:ArrayCollection = event.result as ArrayCollection;
			
			for each(item in arrayRelatorios) {
				relatorio = new RelatorioVO();
				relatorio.idRelatorioAssincrono = item.idRelatorio;
				relatorio.status = item.status;
				relatorio.dataHoraInicioProcessamento = item.dataHora;
				relatorio.dataHoraFimProcessamento = item.dataHora;
				relatorio.nomeRelatorio = item.nome;
				array.addItem(relatorio);
			}
			array.addItem(relatorio);
			PainelListaRelatorios.getInstance().adicionarRelatorioSolicitado(array);
			
			MensagensComum.exibirMensagemInformacao("Solicitação do Relatório enviada com sucesso.");
		}
		
		private function emitir_onFault(event:FaultEvent) : void {
			var evt:FaultEvent = event.clone() as FaultEvent;
			dispatchEvent(evt);
			MostraCursor.removeBusyCursor();
		}
				
	}
}