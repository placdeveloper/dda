package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.monitoracaocip
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.RelatorioUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.LogArquivoCargaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataPesquisaUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoMensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("RegistroVO", RegistroVO);
	public class AbaMonitorarArquivos extends AbaMonitorarArquivosView
	{
		
		public function AbaMonitorarArquivos(){
			super();
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			iniciarCampos();
			tratarTab();
			iniciarBotoes();
		}
		private function iniciarCampos():void{
			painelListaArquivo.funcaoCriacaoDto = instanciarDtoConsulta;
			painelListaArquivo.funcaoConfiguracaoDto = configurarDtoConsulta;
			painelFiltro.cmbOrigem.dataProvider = Constantes.TIPO_ORIGEM.slice();
			painelFiltro.cmbStatusEnvio.dataProvider = Constantes.STATUS_ENVIO.slice();
			painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarProcurar);
			painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			
			painelFiltro.cmbOrigem.addEventListener(ListEvent.CHANGE, habilitarComboStatusArquivo);
			painelFiltro.cmbStatusEnvio.addEventListener(ListEvent.CHANGE, limparConsulta);
			painelFiltro.movimentoDataInicial.addEventListener(Event.CHANGE, limparConsulta);
			painelFiltro.movimentoDataFinal.addEventListener(Event.CHANGE, limparConsulta);
			
			this.listaArquivos.grdDados.addEventListener(Event.RENDER, habilitarBotoes);
			
			btnImpArquivo.addEventListener(MouseEvent.CLICK, imprimir);
			//btnDetalharArquivo.addEventListener(MouseEvent.CLICK, detalharArquivo);
			
			barraBotoesListaCadastro.includeInLayout = false;
			barraBotoesListaCadastro.visible = false;
			barraBotoesListaCadastro.enabled = false;
			
		}
		
		private function habilitarComboStatusArquivo(e:ListEvent):void {
			if (!painelFiltro.cmbOrigem.selectedItem.data) {
				limparConsulta();
				painelFiltro.cmbStatusEnvio.enabled = false;
				painelFiltro.cmbStatusEnvio.selectedIndex = 0;
			} else {
				limparConsulta();
				painelFiltro.cmbStatusEnvio.enabled = true;
			}
		}
		
		/**
		 * Instancia DTO de consulta
		 */
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		/**
		 * Realiza pesquisa de arquivos
		 */
		private function validarProcurar(evt:ProcurarEvent):void {
			if(DataPesquisaUtil.validaPeriodoDataPesquisa(painelFiltro.movimentoDataInicial, painelFiltro.movimentoDataFinal)) {
				painelListaArquivo.procuraSolicitada(evt);
			}
		}
		
		/**
		 * Configura o DTO para executar a pesquisa.
		 */
		private function configurarDtoConsulta(dto:ConsultaDto):void {	
			
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var logArquivoDTO:LogArquivoCargaDTO = new LogArquivoCargaDTO();
			var tipoMensagemVO:TipoMensagemDDAVO = null;
			
			logArquivoDTO.bolOrigemSicoob = painelFiltro.cmbOrigem.selectedItem.data;
			
			if (painelFiltro.cmbStatusEnvio.selectedItem) {
				logArquivoDTO.statusArquivo = painelFiltro.cmbStatusEnvio.selectedItem.data;
			}
			
			if (painelFiltro.movimentoDataFinal.selectedDate){
				logArquivoDTO.movimentoDataInicial = DateTimeBase.getDateTime(painelFiltro.movimentoDataInicial.selectedDate);
			}
			
			if (painelFiltro.movimentoDataFinal.selectedDate){
				logArquivoDTO.movimentoDataFinal = DateTimeBase.getDateTime(painelFiltro.movimentoDataFinal.selectedDate);
			}

			reqDto.dados.dto = logArquivoDTO;
			dto.filtro = reqDto;
		}
		/**
		 * Define o indice de tabulacao
		 */
		private function tratarTab():void {
			var index:int = 1;
			painelFiltro.cmbOrigem.tabIndex = index++;
			painelFiltro.movimentoDataInicial.tabIndex = index++;
			painelFiltro.movimentoDataFinal.tabIndex = index++;
			painelFiltro.btnProcurar.tabIndex = index++;
			painelFiltro.btnLimpar.tabIndex = index++;
			btnDetalharArquivo.tabIndex = index++;
			btnImpArquivo.tabIndex = index++;
			listaArquivos.barraPaginacao.tabIndex = index++;
		}
		
		/**
		 * Limpa os campos
		 */
		public function limparCampos(evt:Event = null):void {
			limparConsulta(evt);
			iniciarBotoes();
			painelFiltro.cmbOrigem.selectedIndex = 0;
			painelFiltro.cmbStatusEnvio.selectedIndex = 0;
			painelFiltro.cmbStatusEnvio.enabled = true;
			painelFiltro.movimentoDataInicial.selectedDate = null;
			painelFiltro.movimentoDataFinal.selectedDate = null;
			btnDetalharArquivo.enabled = false;
			btnImpArquivo.enabled = false;
		}
		

		/**
		 * Limpa o resultado da consulta
		 */
		private function limparConsulta(evt:Event = null):void {
			listaArquivos.limparConteudo();
			listaArquivos.barraPaginacao.totalPaginas = listaArquivos.barraPaginacao.pagina = 0;
		}
		
		/**
		 * Imprime PDF do arquivo detalhado
		 */
		private function imprimir(event:MouseEvent):void{
			var metodo:String = "gerarRelatorioMonitoramentoArquivosCIP";
			var idArquivo:Number = this.listaArquivos.grdDados.selectedItem.idArquivo;
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["idArquivo"] = idArquivo;
			
			var relatorioUtil:RelatorioUtil = new RelatorioUtil();
			var exibirPreImpressao:Boolean = true;
/*			relatorioUtil.emitirRelatorio(metodo, Constantes.SERVICO_RELATORIO, dto, ConstantesRelatorios.MONITORAMENTO_ARQUIVO, null,
				"Gerando Impressão...", ConstantesComum.FORMATO_RELATORIO_PDF, exibirPreImpressao);*/
		}
		
		private function habilitarBotoes(e:Event):void {
			if(listaArquivos.grdDados.selectedItem) {
				this.listaArquivos.barraPaginacao.enabled = true;
				this.btnDetalharArquivo.enabled = true;
				this.btnImpArquivo.enabled = true;
			} else {
				this.listaArquivos.barraPaginacao.enabled = false;
				this.btnImpArquivo.enabled = false;
				this.btnDetalharArquivo.enabled = false;				
			}
		}
		
		
		/**
		 * Inicializa os botoes
		 */
		private function iniciarBotoes():void {
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaArquivo.procuraSolicitada);
			
			barraBotoesListaCadastro.includeInLayout = false;
			barraBotoesListaCadastro.visible = false;
			barraBotoesListaCadastro.enabled = false;
			
			listaArquivos.doubleClickEnabled = false;
		}
	}
}