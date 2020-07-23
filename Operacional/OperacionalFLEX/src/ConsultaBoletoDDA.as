package
{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.componentes.input.InputCPFCNPJ;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.PesquisaBoletoDDADTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataPesquisaUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.boleto.ConsultaBoletoDDAView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.boleto.DetalhamentoBoleto;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class ConsultaBoletoDDA extends ConsultaBoletoDDAView
	{
		private static const TIPO_CPF:int = 0;
		private static const TIPO_CNPJ:int = 1;
		
		private var btnImprimirBoleto:Botao = new Botao();
		
		private var relUtil:RelatorioUtil = RelatorioUtil.create();
		
		private var isCodigoBarras:Boolean = true;
		
		private const QTD_DIAS_CONSULTA_DDA:Number = 35;
		
		private var _filtroConsultaTemp:PesquisaBoletoDDADTO;
		
		private var isConsultaBoletoCIPHabilitada:Boolean = true;
		
		public function ConsultaBoletoDDA()
		{
			super();
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			
			iniciarCampos();
			
			definirBotoes();
			
			funcaoDuploClique = null;
			
			validarTabulacao();
			
		}
		
		private function iniciarCampos ():void{
			
			// this.painelFiltro.inputNumCodBarras.txtCodBarras.text = "74894719600008000001116100540307182803218108"; //SÓ PARA TESTES
			
			barraBotoesListaCadastro.exibirBotaoFechar = true;
			barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoExcluir = false;
			
			painelListaConsultaBoleto.funcaoCriacaoDto = instanciarDtoConsulta;
			painelListaConsultaBoleto.funcaoConfiguracaoDto = configurarDtoConsulta;
			
			painelFiltro.cmbCnpjCPFBeneficiario.dataProvider = ConstantesComum.CPF_CNPJ;
			painelFiltro.cmbCnpjCPFPagador.dataProvider = ConstantesComum.CPF_CNPJ;
			
			painelFiltro.txtCnpjCPFBeneficiario.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			painelFiltro.txtCnpjCPFPagador.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			
			btnImprimirBoleto.addEventListener(MouseEvent.CLICK, imprimirRelatorio);
			
			var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			
			btnImprimirBoleto.label = "Imprimir";
			btnImprimirBoleto.toolTip = "Imprimir Relatorio Boleto";
			btnImprimirBoleto.height = 22;
			
			hBoxBarraBotoes.addChildAt(btnImprimirBoleto, 1);
			
			var btnFechar:Botao = hBoxBarraBotoes.getChildByName("btnFechar") as Botao;
			
			if (btnFechar) {
				// Define o ícone
				btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			}
			
			painelListaConsultaBoleto.callback = tratarCallbackPesquisa;
			
			ajustaTelaLadoEsquerdoFiltro();
			
			painelFiltro.cmbSituacaoBoleto.dataProvider = ConstantesComum.COB_SITUACAO_BOLETO;
			
			obterParametroConsultaBoletoCIP();
		}
		
		private function ajustaTelaLadoEsquerdoFiltro ():void{
			painelFiltro.roturloSituacaoBoleto.y = 67;
			painelFiltro.cmbSituacaoBoleto.y = 65;
			painelFiltro.rotuloSeuNumero.y= 137;
			painelFiltro.txtDescSeuNumero.y= 135;
		}
		
		private function validarTabulacao():void{
			var index:int = 1;
			
			painelFiltro.txtNrBanco.tabIndex = index++;
			painelFiltro.cmbCnpjCPFBeneficiario.tabIndex = index++;
			painelFiltro.txtCnpjCPFBeneficiario.tabIndex = index++;
			painelFiltro.cmbCnpjCPFPagador.tabIndex = index++;
			painelFiltro.txtCnpjCPFPagador.tabIndex = index++;
			painelFiltro.cmbSituacaoBoleto.tabIndex = index++;
			painelFiltro.txtDescSeuNumero.tabIndex = index++;
			painelFiltro.dataVencimentoInicial.tabIndex = index++;
			painelFiltro.dataVencimentoFinal.tabIndex = index++;
			painelFiltro.dataRegistroDDAInicial.tabIndex = index++;
			painelFiltro.dataRegistroDDAFinal.tabIndex = index++;
			painelFiltro.btnProcurar.tabIndex = index++;
			painelFiltro.btnConsultarCip.tabIndex = index++;
			painelFiltro.btnLimpar.tabIndex = index++;
			
			this.painelListaConsultaBoleto.tabelaPaginada.grdDados.tabIndex = index++;
			btnDetalharBoleto.tabIndex = index++;
			this.painelListaConsultaBoleto.tabelaPaginada.barraPaginacao.tabIndex = index++;
			btnImprimirBoleto.tabIndex = index++;
		}
		
		/**
		 * Define os botões.
		 */
		private function definirBotoes():void {
			painelFiltro.cmbCnpjCPFBeneficiario.addEventListener(ListEvent.CHANGE, tratarCnpjCpfBeneficiario);
			painelFiltro.cmbCnpjCPFPagador.addEventListener(ListEvent.CHANGE, tratarCnpjCpfPagador);
			
			painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCamposConsulta);
			
			btnImprimirBoleto.setStyle("icon", ConstantesComum.icoImprimir);
			btnImprimirBoleto.addEventListener(MouseEvent.CLICK, imprimirRelatorio);
			btnImprimirBoleto.enabled = false;
			
			btnDetalharBoleto.addEventListener(MouseEvent.CLICK, visualizarBoleto);
			grdBoleto.grdDados.addEventListener(MouseEvent.DOUBLE_CLICK, visualizarBoleto);
			
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaConsultaBoleto.procuraSolicitada);
			painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarProcurar);
			
			painelFiltro.btnConsultarCip.addEventListener(MouseEvent.CLICK, consultarBoletoCIP);
			
			painelFiltro.txtNrBanco.addEventListener(KeyboardEvent.KEY_UP, obterNomeBanco);
			
			btnDetalharBoleto.enabled = painelFiltro.btnConsultarCip.enabled = false;
			
		}
		
		private function validarProcurar(evt:ProcurarEvent):void{
			if (validarCamposConsultaBoleto()) {
				painelListaConsultaBoleto.procuraSolicitada(evt);
			} 
		}
		
		private function validarCamposConsultaBoleto():Boolean {
			if (!painelFiltro.inputNumCodBarras.validar()) {
				return false;
			} else if (!painelFiltro.inputNumCodBarras.isPreenchido() && painelFiltro.txtCnpjCPFBeneficiario.valor == 0 && painelFiltro.txtCnpjCPFPagador.valor == 0) {
				MensagensComum.exibirMensagemAlerta("É obrigatório o preenchimento ao menos de um dos campos: Cód. de Barras, CPF/CNPJ do Beneficiário ou Pagador. ", painelFiltro.txtCnpjCPFBeneficiario);
				return false;
			} else {
				return validarPeriodoDatas();
			}
			
		}
		
		private function validarPeriodoDatas():Boolean {
			if (!painelFiltro.inputNumCodBarras.isPreenchido() && !painelFiltro.dataVencimentoInicial.selectedDate && !painelFiltro.dataVencimentoFinal.selectedDate
				&& !painelFiltro.dataRegistroDDAInicial.selectedDate && !painelFiltro.dataRegistroDDAFinal.selectedDate) {
				MensagensComum.exibirMensagemAlerta("É obrigatório o preenchimento ao menos de um dos campos: Data de Vencimento ou Data de Registro no DDA.", painelFiltro.dataVencimentoInicial);
				return false;
			} else {
				if (painelFiltro.dataVencimentoInicial.selectedDate || painelFiltro.dataVencimentoFinal.selectedDate) {
					return DataPesquisaUtil.validaPeriodoDataPesquisa(painelFiltro.dataVencimentoInicial, painelFiltro.dataVencimentoFinal, QTD_DIAS_CONSULTA_DDA, "de Vencimento ");	
				}
				
				if (painelFiltro.dataRegistroDDAInicial.selectedDate || painelFiltro.dataRegistroDDAFinal.selectedDate) {
					return DataPesquisaUtil.validaPeriodoDataPesquisa(painelFiltro.dataRegistroDDAInicial, painelFiltro.dataRegistroDDAFinal, QTD_DIAS_CONSULTA_DDA, "de Registro DDA ");
				}
				return true;
			}
			
		}
		
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		/**
		 * Configura o DTO para executar a pesquisa.
		 */
		private function configurarDtoConsulta(dto:ConsultaDto):void {	
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var pesquisaBoletoDDADTO:PesquisaBoletoDDADTO = new PesquisaBoletoDDADTO();
			
			if(painelFiltro.txtNrBanco != null && painelFiltro.txtNrBanco.text != ""){
				pesquisaBoletoDDADTO.numBanco = painelFiltro.txtNrBanco.text;
			}
			
			pesquisaBoletoDDADTO.numCpfCnpjBeneficiario = StringUtil.trim(painelFiltro.txtCnpjCPFBeneficiario.text) == "" ? null : painelFiltro.txtCnpjCPFBeneficiario.text;
			pesquisaBoletoDDADTO.numCpfCnpjPagador = StringUtil.trim(painelFiltro.txtCnpjCPFPagador.text) == "" ? null : painelFiltro.txtCnpjCPFPagador.text;
			
			if (painelFiltro.inputNumCodBarras.isCodigoBarras) {
				pesquisaBoletoDDADTO.numCodigoBarra = painelFiltro.inputNumCodBarras.txtNumCodBarras;
			} else {
				pesquisaBoletoDDADTO.numLinhaDigitavel = painelFiltro.inputNumCodBarras.txtNumCodBarras;
			}
			
			if(painelFiltro.dataVencimentoInicial.selectedDate != null){
				pesquisaBoletoDDADTO.dataVencimentoInicial = DateTimeBase.getDateTime(painelFiltro.dataVencimentoInicial.selectedDate);	
			}
			
			if(painelFiltro.dataVencimentoFinal.selectedDate != null){
				pesquisaBoletoDDADTO.dataVencimentoFinal = DateTimeBase.getDateTime(painelFiltro.dataVencimentoFinal.selectedDate);	
			}
			
			if(painelFiltro.dataRegistroDDAInicial.selectedDate != null){
				pesquisaBoletoDDADTO.dataRegistroDDAInicial = DateTimeBase.getDateTime(painelFiltro.dataRegistroDDAInicial.selectedDate);	
			}
			
			if(painelFiltro.dataRegistroDDAFinal.selectedDate != null){
				pesquisaBoletoDDADTO.dataRegistroDDAFinal = DateTimeBase.getDateTime(painelFiltro.dataRegistroDDAFinal.selectedDate);	
			}
			
			if(painelFiltro.txtDescSeuNumero != null && painelFiltro.txtDescSeuNumero.text  != ""){
				pesquisaBoletoDDADTO.numSeuNumero = painelFiltro.txtDescSeuNumero.text;
			}
			
			if(painelFiltro.cmbSituacaoBoleto.selectedItem != null){
				pesquisaBoletoDDADTO.codSituacaoBoleto = painelFiltro.cmbSituacaoBoleto.selectedItem.data;
			}
			
			_filtroConsultaTemp = pesquisaBoletoDDADTO;
			reqDto.dados.pesquisaBoletoDDADTO = pesquisaBoletoDDADTO;
			dto.filtro = reqDto;
		}
		
		private function tratarCnpjCpfBeneficiario(evt:Event = null):void {
			if (painelFiltro.cmbCnpjCPFBeneficiario.selectedIndex == TIPO_CPF) {
				painelFiltro.txtCnpjCPFBeneficiario.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			} else if(painelFiltro.cmbCnpjCPFBeneficiario.selectedIndex == TIPO_CNPJ){
				painelFiltro.txtCnpjCPFBeneficiario.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
			}
		}
		
		private function tratarCnpjCpfPagador(evt:Event = null):void {
			if (painelFiltro.cmbCnpjCPFPagador.selectedIndex == TIPO_CPF) {
				painelFiltro.txtCnpjCPFPagador.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			} else if(painelFiltro.cmbCnpjCPFPagador.selectedIndex == TIPO_CNPJ){
				painelFiltro.txtCnpjCPFPagador.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
			}
		}
		
		private function imprimirRelatorio(event:MouseEvent):void{
			if (grdBoleto.grdDados.selectedItem) {	
				RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSULTA_BOLETO, _filtroConsultaTemp, null, PreImpressao.FORMATO_PDF);
			}
		}
		
		private function visualizarBoleto(event:MouseEvent):void{
			if (grdBoleto.grdDados.selectedItem) {	
				var tela:DetalhamentoBoleto = new DetalhamentoBoleto(FormularioCadastro.MODO_VISUALIZACAO,grdBoleto.grdDados.selectedItem.numCodigoBarra, grdBoleto.grdDados.selectedItem.codSituacaoBoleto);
				
				JanelaCobranca.abrirJanela(this, tela, Constantes.DETALHAMENTO_BOLETO);
				
			}else{
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG007, "Boleto"));
			}
		}
		
		/**
		 * Limpa os campos da consulta e a tabela.
		 */
		private function limparCamposConsulta(evt:Event):void {
			limparCnpjCpfBeneficiario();
			limparCnpjCpfPagador();
			
			painelFiltro.cmbSituacaoBoleto.selectedIndex = 0;
			
			painelFiltro.dataVencimentoInicial.selectedDate = painelFiltro.dataVencimentoFinal.selectedDate = null;
			painelFiltro.dataRegistroDDAInicial.selectedDate = painelFiltro.dataRegistroDDAFinal.selectedDate = null;
			
			painelFiltro.txtDescSeuNumero.text = painelFiltro.txtNrBanco.text = painelFiltro.txtNomeBanco.text = painelFiltro.txtNomeBanco.toolTip = "";
			
			painelFiltro.inputNumCodBarras.limpar();
			
			
			grdBoleto.barraPaginacao.totalPaginas = grdBoleto.barraPaginacao.pagina = 0;
			grdBoleto.grdDados.dataProvider = null;
			
			painelFiltro.btnConsultarCip.enabled = btnDetalharBoleto.enabled = btnImprimirBoleto.enabled= false;
			_filtroConsultaTemp=null;
			
		}
		
		private function limparCnpjCpfBeneficiario():void {
			if(painelFiltro.cmbCnpjCPFBeneficiario.selectedItem.data == ConstantesComum.CPF){
				painelFiltro.txtCnpjCPFBeneficiario.text = "";
				painelFiltro.txtCnpjCPFBeneficiario.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			}else if(painelFiltro.cmbCnpjCPFBeneficiario.selectedItem.data == ConstantesComum.CNPJ){
				painelFiltro.txtCnpjCPFBeneficiario.text = "";
				painelFiltro.txtCnpjCPFBeneficiario.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
			}
		}
		
		private function limparCnpjCpfPagador():void {
			if(painelFiltro.cmbCnpjCPFPagador.selectedItem.data == ConstantesComum.CPF){
				painelFiltro.txtCnpjCPFPagador.text = "";
				painelFiltro.txtCnpjCPFPagador.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			}else if(painelFiltro.cmbCnpjCPFPagador.selectedItem.data == ConstantesComum.CNPJ){
				painelFiltro.txtCnpjCPFPagador.text = "";
				painelFiltro.txtCnpjCPFPagador.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
			}
		}
		
		
		private function tratarCallbackPesquisa(evt:ResultEvent):void {
			var listaSituacaoBoletoVO:ArrayCollection = evt.result.dados.lista as ArrayCollection;
			if ((!listaSituacaoBoletoVO || !listaSituacaoBoletoVO.length) && isConsultaBoletoCIPHabilitada) {
				MensagensComum.exibirMensagemConfirmacao(MensagensComum.MSG039, null, consultarBoletoCIP);
			}else if(!listaSituacaoBoletoVO || !listaSituacaoBoletoVO.length){
				MensagensComum.exibirMensagemInformacao("Boleto não encontrado em nossa base de dados.");
			}
			
			painelListaConsultaBoleto.pesquisaRealizada(evt);
			btnDetalharBoleto.enabled = btnImprimirBoleto.enabled = true
			painelFiltro.btnConsultarCip.enabled = this.isConsultaBoletoCIPHabilitada;	 
			
		}
		
		private function consultarBoletoCIP(evt:MouseEvent):void{
			if (!painelFiltro.inputNumCodBarras.isPreenchido()) {
				MensagensComum.exibirMensagemErro("É obrigatório o preenchimento do campo Código de Barras/Linha Digitável para consultar na base da CIP.");
			} else {
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.numCodigoBarra = painelFiltro.inputNumCodBarras.txtNumCodBarras;
				dto.dados.codSituacaoBoleto = painelFiltro.cmbSituacaoBoleto.selectedItem != null ? painelFiltro.cmbSituacaoBoleto.selectedItem.data : null;
				
				var metodo:String = "consultarBoletoCIP";
				var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_CONSULTA_BOLETO, metodo);
				servico.addEventListener(ResultEvent.RESULT, resultadoConsultarCIP);
				
				servico.consultarBoletoCIP(dto);
			}
			
		}
		
		private function resultadoConsultarCIP(evt:ResultEvent):void{
			MensagensComum.exibirMensagemSucesso(MensagensComum.MSG040);
		}
		
		private function obterNomeBanco(event:KeyboardEvent = null):void {
			if(event.currentTarget != null && event.currentTarget.text.length == painelFiltro.txtNrBanco.maxChars){
				if(painelFiltro.txtNrBanco.text == "000"){
					painelFiltro.txtNomeBanco.text = "";
				}else{
					var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
					
					dto.dados.numeroBanco = painelFiltro.txtNrBanco.text;
					
					var metodo:String = "obterNomeBanco";
					var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_CONSULTA_BOLETO, metodo);
					servico.addEventListener(ResultEvent.RESULT, resultadoObterNomeBanco);
					
					servico.obterNomeBanco(dto);
				}
				
			}else{
				painelFiltro.txtNomeBanco.text = "";
				
			}
		}
		
		private function resultadoObterNomeBanco(evt:ResultEvent):void{
			painelFiltro.txtNomeBanco.text = evt.result.dados.nomeBanco as String;
			painelFiltro.txtNomeBanco.toolTip = evt.result.dados.nomeBanco as String;
		}
		
		private function obterParametroConsultaBoletoCIP():void{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var metodo:String = "consultaBoletoCIPHabilitadoPorInstituicao";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_CONSULTA_BOLETO, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoObterParametroConsultaBoletoCIP);
			
			servico.consultaBoletoCIPHabilitadoPorInstituicao(dto);
		}
		
		private function resultadoObterParametroConsultaBoletoCIP(evt:ResultEvent):void{
			this.isConsultaBoletoCIPHabilitada = evt.result.dados.consultaHabilitada as Boolean;
		}
		
	}
}