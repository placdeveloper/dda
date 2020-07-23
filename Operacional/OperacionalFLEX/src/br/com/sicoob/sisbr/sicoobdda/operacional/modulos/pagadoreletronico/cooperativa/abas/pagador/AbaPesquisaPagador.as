package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.pagadoreletronico.cooperativa.abas.pagador
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.componentes.input.InputCPFCNPJ;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.PagadorDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class AbaPesquisaPagador extends AbaPesquisaPagadorView
	{
		
		private static const SICOOB:Boolean = true;
		private static const BRASIL:Boolean = false;
		private static const TIPO_CPF:int = 0;
		private static const TIPO_CNPJ:int = 1;
		private static const DETALHAMENTO_DO_PAGADOR_ELETRONICO:String = "DETALHAMENTO DO PAGADOR ELETRÔNICO";
		private static const PARAMETRO_COOPERADO:String = "COOPERADO";
		private static const TOOL_TIP_CENTRA_SINGULAR:String = "Para efetuar uma consulta por Central e Singular, não deve preencher CPF/CNPJ";
		private static const TOOL_TIP_CPF_CNPJ:String = "Para efetuar uma consulta por CPF/CNPJ, não preencher Central e Singular";
		private static const MSG_NAO_ENCONTRADO:String = "Não encontrado Pagador com CPF/CNPJ informado";
		private static const MSG_VALIDA_SINGULAR_CPF:String = "Para efetuar uma consulta é necessário preencher o CPF/CNPJ ou uma Central";

		// Constantes relacionadas a campos em que a opção pode ser CPNJ | CPF
		private static const CPF_CNPJ:Array = [
			{label:"CPF", data:TIPO_CPF},
			{label:"CNPJ", data:TIPO_CNPJ}
		];
		
		
		public function AbaPesquisaPagador()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			iniciarComponentes();
		}	
		
		private function iniciarComponentes():void {
			txtCnpjCpfPagador.tipoCampo = InputCPFCNPJ.TIPO_CPF; 
			this.compCentralSingular.exibirPorAgenciaLogada = parametros == PARAMETRO_COOPERADO;
			this.compCentralSingular.carregarCombos();			
			iniciarEventos();
			definirComponentes();
			iniciarBotoes();
		}
		//--------------------------------------------------------------------------
		//  Inicializar Eventos.
		//--------------------------------------------------------------------------
		private function iniciarEventos():void {
			this.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, restaCmbSingular);

			this.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, enableSingular);
			this.compCentralSingular.cmbSingular.enabled = false;
			
			this.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarPesquisa);
			this.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
		}
		
		
		//--------------------------------------------------------------------------
		//  Definição de componentes Posição-Estado-Tamanho-Etc.
		//--------------------------------------------------------------------------
		private function definirComponentes():void {
			this.compCentralSingular.hboxCentral.x = -25;
			this.compCentralSingular.hboxSingular.x = 165;
			
			this.compCentralSingular.hboxSingular.y = this.compCentralSingular.hboxCentral.y;
			this.compCentralSingular.hboxSingular.x = this.compCentralSingular.hboxCentral.x + 200;
			
			this.compCentralSingular.hboxCentral.width = 200;
			this.compCentralSingular.hboxSingular.width = 200;
			
			this.compCentralSingular.hboxCentral.horizontalScrollPolicy = "off";
			this.compCentralSingular.hboxSingular.horizontalScrollPolicy = "off";
			
			this.compCentralSingular.mostrarUnidade = false;
			this.compCentralSingular.height = 90;
			
			this.compCentralSingular.cmbCentral.inserirItemOpcional = true;
			this.compCentralSingular.cmbCentral.labelItemOpcional="---";
			
			this.compCentralSingular.cmbSingular.inserirItemOpcional = true;
			this.compCentralSingular.cmbSingular.labelItemOpcional="---";
			
			cmbCnpjCpfPagador.dataProvider = ConstantesComum.CPF_CNPJ;
			cmbCnpjCpfPagador.addEventListener(ListEvent.CHANGE, tratarCnpjCpfPagador);
			cmbCnpjCpfPagador.selectedItem.data = TIPO_CPF;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializar botões.
		//--------------------------------------------------------------------------
		private function iniciarBotoes():void {
			this.btnImprimir.addEventListener(MouseEvent.CLICK, relatorioDetalhadoPagador);
			this.btnImprimir.enabled =false;
			btnImprimir.setStyle("icon", ConstantesComum.icoImprimir);
			this.infCentralSingular.toolTip = TOOL_TIP_CENTRA_SINGULAR;
			this.infCpfCnpj.toolTip = TOOL_TIP_CPF_CNPJ;
		}

		//--------------------------------------------------------------------------
		//  Validar Procura.
		//--------------------------------------------------------------------------
		private function validarPesquisa(event:ProcurarEvent):void{
			var bolCpfCnpj:Boolean = StringUtils.trim(txtCnpjCpfPagador.text) == "";
			var bolCentral:Boolean = compCentralSingular.cmbCentral.dropdown.selectedItem.numeroCooperativa == "---";
			var bolSingular:Boolean = compCentralSingular.cmbSingular.dropdown.selectedItem.numeroCooperativa == "---";
			
			if((bolCpfCnpj && bolCentral && bolSingular) || (!bolCpfCnpj && !bolCentral)){
				getMensagemValidaFiltro();
				return;
			}else if(!bolCpfCnpj && (bolCentral && bolSingular)){
				executarSeValido(carregarCampos);
			}else{
				if(bolSingular){
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Singular"));
					return;
				}
				executarSeValido(relatarioExcel);
			}	
		}
		
		private function getMensagemValidaFiltro():void {
			MensagensComum.exibirMensagemAlerta(MSG_VALIDA_SINGULAR_CPF);
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos campos.
		//--------------------------------------------------------------------------
		private function carregarCampos():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.numCpfCnpj = this.txtCnpjCpfPagador.text;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_PAGADOR_ELETRONICO, "obterDetalharPagador");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterDetalharPagador(dto);
		}
		
		private function retornoConsulta(resultEvent:ResultEvent):void {
			var pagadorDto:PagadorDTO = resultEvent.result.dados.pagador as PagadorDTO;
			if(pagadorDto){
				this.btnImprimir.enabled = true;
				txtNomePagador.text = pagadorDto.nomePessoa;
				txtSituaçãoCip.text = pagadorDto.bolPagadorEletronico ? "Ativo":"Inativo";
				txtDataAdesao.text = pagadorDto.dataAdesaoDDA!=null ? FormataData.formataData(pagadorDto.dataAdesaoDDA.data,"dd/MM/yyyy") :null;
				txtQtdIfAdesao.text =  pagadorDto.qtdAdesaoDDA.toString();
				txtPagadorEletronicoSicoob.text = descTxtNomeIfAderente(pagadorDto);
				txtCpfCnpj.text = FormatUtil.formataCPFCNPJ(pagadorDto.numCpfCnpj);
				if(pagadorDto.listaAgencia){
					this.listaAgencia.dataProvider =  pagadorDto.listaAgencia;
				}
				
				this.listaAgregados.dataProvider = pagadorDto.listaPagadorAgregado == null ? null : pagadorDto.listaPagadorAgregado;
			}else{
				limparCampos();
				MensagensComum.exibirMensagemInformacao(MSG_NAO_ENCONTRADO);
			}
			
		}
		
		private function descTxtNomeIfAderente(pagadorDto:PagadorDTO):String{
			if(pagadorDto.qtdAdesaoDDA == 1 && pagadorDto.bolPagadorEletronicoSicoob){
				return "Sicoob";
			}else if(pagadorDto.qtdAdesaoDDA > 1 && pagadorDto.bolPagadorEletronicoSicoob){
				return "Sicoob e outros bancos";
			} else if(pagadorDto.qtdAdesaoDDA >= 1 && pagadorDto.bolPagadorEletronico){
				return "Outros bancos";
			}else{
				return "";
			}
		}
		
		//--------------------------------------------------------------------------
		//  Relatorio detalhado do Pagador PDF.
		//--------------------------------------------------------------------------
		private function relatorioDetalhadoPagador(e:Event):void {
			var textoTratado:String = this.txtCpfCnpj.text.replace(/[^0-9]/g,'');
			
			var filtro:String = StringUtils.trim(textoTratado) == "" ? null : textoTratado;
			
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_DETALHADO_PAGADOR_ELETRONICODDA, filtro, null, PreImpressao.FORMATO_XLSX_SEM_FORMATACAO);
		}

		//--------------------------------------------------------------------------
		//  Relatorio lista de pagadores Excel
		//--------------------------------------------------------------------------
		private function relatarioExcel(e:Event=null):void {
			var filtro:String = compCentralSingular.cmbSingular.dropdown.selectedItem.numeroCooperativa;
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_LISTA_PAGADOR_ELETRONICO, filtro, null, PreImpressao.FORMATO_XLSX_SEM_FORMATACAO);
		}
		
		//--------------------------------------------------------------------------
		//  Limpar a pesquisa.
		//--------------------------------------------------------------------------
		private function limparCampos(evt:Event=null):void {
			this.cmbCnpjCpfPagador.selectedIndex = 0;
			this.txtCnpjCpfPagador.text = "";
			this.compCentralSingular.cmbCentral.selectedIndex = 0;
			this.compCentralSingular.cmbSingular.selectedIndex = 0;
			this.compCentralSingular.cmbSingular.enabled = false;
			
			if(cmbCnpjCpfPagador.selectedIndex == TIPO_CPF){
				txtCnpjCpfPagador.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			}else if(cmbCnpjCpfPagador.selectedIndex == TIPO_CNPJ){
				txtCnpjCpfPagador.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
			}
			this.listaAgencia.dataProvider = null;
			this.btnImprimir.enabled = false;
			txtNomePagador.text = null;
			txtSituaçãoCip.text = null;
			txtDataAdesao.text = null;
			txtQtdIfAdesao.text =  null;
			txtPagadorEletronicoSicoob.text = null;
			txtCpfCnpj.text = null;
			this.listaAgregados.dataProvider = null;
		}

		//--------------------------------------------------------------------------
		//  Reseta comobo Singular e Central.
		//--------------------------------------------------------------------------
		private function restaCmbSingular(e:Event):void{
			if(this.compCentralSingular.cmbCentral.selectedIndex == 0){
				this.compCentralSingular.cmbSingular.selectedIndex = 0;	
			}
		}
		
		//--------------------------------------------------------------------------
		//  Desabilitar combo singular.
		//--------------------------------------------------------------------------
		private function enableSingular(e:Event):void {
			if(this.compCentralSingular.cmbCentral.selectedIndex == 0){
				this.compCentralSingular.cmbSingular.enabled = false;
			}else{
				this.compCentralSingular.cmbSingular.enabled = true;
			}
		}
		
		//--------------------------------------------------------------------------
		//  Instanci DTO de Consulta para Paginação.
		//--------------------------------------------------------------------------
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		
		//--------------------------------------------------------------------------
		//  Configura o DTO para executar a pesquisa.
		//--------------------------------------------------------------------------
		private function configurarDtoConsulta(dto:ConsultaDto):void {	
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var pagadorDto:PagadorDTO = new PagadorDTO();
			
			reqDto.dados.dto = filtroPesquisa();
			dto.filtro = reqDto;
		}
		
		//--------------------------------------------------------------------------
		//  Preencher campos do Filtro
		//--------------------------------------------------------------------------
		private function filtroPesquisa():PagadorDTO{
			var pagadorDTO:PagadorDTO = new PagadorDTO();
			
			if(compCentralSingular.cmbSingular.selectedItem){
				pagadorDTO.idSingular = compCentralSingular.cmbSingular.selectedItem.numeroCooperativa;
				pagadorDTO.idInstituicao = compCentralSingular.cmbSingular.selectedItem.idInstituicao;
			}
			
			pagadorDTO.numCpfCnpj = StringUtils.trim(txtCnpjCpfPagador.text) == ""?null:txtCnpjCpfPagador.text;
			
			return pagadorDTO;
		}
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
		//--------------------------------------------------------------------------
		//  Tratar CPF/CNPJ.
		//--------------------------------------------------------------------------
		private function tratarCnpjCpfPagador(evt:Event = null):void {
			if (cmbCnpjCpfPagador.selectedIndex == TIPO_CPF) {
				txtCnpjCpfPagador.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			} else if(cmbCnpjCpfPagador.selectedIndex == TIPO_CNPJ){
				txtCnpjCpfPagador.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
			}else{
				txtCnpjCpfPagador.tipoCampo = null;
			}
			
		}
		
	}
}
