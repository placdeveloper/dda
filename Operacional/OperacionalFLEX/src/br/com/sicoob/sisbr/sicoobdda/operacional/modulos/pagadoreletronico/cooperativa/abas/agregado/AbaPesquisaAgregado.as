package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.pagadoreletronico.cooperativa.abas.agregado
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.componentes.input.InputCPFCNPJ;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.util.StringUtils;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.PagadorAgregadoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.pagadoreletronico.cooperativa.abas.pagador.PopUpDetalharPagador;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class AbaPesquisaAgregado extends AbaPesquisaAgregadoView
	{
		
		private static const TIPO_CPF:int = 0;
		private static const TIPO_CNPJ:int = 1;
		
		
		// Constantes relacionadas a campos em que a opção pode ser CPNJ | CPF
		public static const CPF_CNPJ:Array = [
			{label:"CPF", data:TIPO_CPF},
			{label:"CNPJ", data:TIPO_CNPJ}
		];
		
		public static const DETALHAMENTO_DO_PAGADOR_ELETRONICO:String = "DETALHAMENTO DO PAGADOR ELETRÔNICO";
		private static const PARAMETRO_COOPERADO:String = "COOPERADO";
		private var _TempFiltroCodCentral:Number;
		private var _TempFiltroNumAgencia:Number;
		private var _TempFiltroNumCpfCnpjAgregado:String;
		
		public function AbaPesquisaAgregado()
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
			this.painelFiltro.compCentralSingular.exibirPorAgenciaLogada = parametros == PARAMETRO_COOPERADO;
			this.painelFiltro.compCentralSingular.carregarCombos();			
			this.listaPagadorAgregado.doubleClickEnabled = false;
			iniciarEventos();
			definirComponentes();
			iniciarBotoes();
		}
		
		private function iniciarEventos():void {
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaBanco.procuraSolicitada);
			this.painelListaBanco.funcaoCriacaoDto = instanciarDtoConsulta;	
			this.painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarPesquisa);
			this.painelFiltro.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, enableSingular);
			this.painelFiltro.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, restaCmbSingular);
			
			this.painelFiltro.compCentralSingular.cmbCentral.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.compCentralSingular.cmbSingular.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			
			this.btnImprimir.addEventListener(MouseEvent.CLICK, relatorioListaPagadorAgregado);
			
			btnDetalhar.addEventListener(MouseEvent.CLICK, popUpDetalhar);
		}
		
		//--------------------------------------------------------------------------
		//  Definição de componentes Posição-Estado-Tamanho-Etc.
		//--------------------------------------------------------------------------
		private function definirComponentes():void {
			barraBotoesListaCadastro.exibirBotaoFechar = barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoExcluir =  false ;
			barraBotoesListaCadastro.setVisible(false);
			barraBotoesListaCadastro.height = 0;
			barraBotoesListaCadastro.width = 0;
			painelFiltro.txtCnpjCpfAgregado.tipoCampo = InputCPFCNPJ.TIPO_CPF; 
			this.painelFiltro.compCentralSingular.hboxCentral.x = -25;
			this.painelFiltro.compCentralSingular.hboxSingular.x = 165;
			
			this.painelFiltro.compCentralSingular.hboxSingular.x = this.painelFiltro.compCentralSingular.hboxCentral.x + 200;
			this.painelFiltro.compCentralSingular.hboxSingular.y = this.painelFiltro.compCentralSingular.hboxCentral.y;
			
			this.painelFiltro.compCentralSingular.hboxCentral.width = 200;
			this.painelFiltro.compCentralSingular.hboxSingular.width = 200;
			
			this.painelFiltro.compCentralSingular.hboxCentral.horizontalScrollPolicy = "off";
			this.painelFiltro.compCentralSingular.hboxSingular.horizontalScrollPolicy = "off";
			
			this.painelFiltro.compCentralSingular.mostrarUnidade = false;
			this.painelFiltro.compCentralSingular.height = 90;
			
			painelFiltro.cmbCnpjCpfPagadorAgregado.dataProvider = ConstantesComum.CPF_CNPJ;
			painelFiltro.cmbCnpjCpfPagadorAgregado.addEventListener(ListEvent.CHANGE, tratarCnpjCpfPagadorAgregado);
			painelFiltro.cmbCnpjCpfPagadorAgregado.selectedItem.data = TIPO_CPF;
			
			
			if(parametros != PARAMETRO_COOPERADO){
				this.painelFiltro.compCentralSingular.cmbSingular.enabled = false;
				this.painelFiltro.compCentralSingular.cmbCentral.inserirItemOpcional = true;
				this.painelFiltro.compCentralSingular.cmbCentral.labelItemOpcional="---";
				
				this.painelFiltro.compCentralSingular.cmbSingular.inserirItemOpcional = true;
				this.painelFiltro.compCentralSingular.cmbSingular.labelItemOpcional="---";
			}
		}
		
		//--------------------------------------------------------------------------
		//  Inicializar botões.
		//--------------------------------------------------------------------------
		private function iniciarBotoes():void {
			
			btnImprimir.enabled = false;
			btnDetalhar.enabled = false;
			
			this.listaPagadorAgregado.grdDados.addEventListener(MouseEvent.CLICK, tratarBotoesAcao);
		}
		
		//--------------------------------------------------------------------------
		//  Habilita e Desabilita Singular.
		//--------------------------------------------------------------------------
		private function enableSingular(e:Event):void {
			if(this.painelFiltro.compCentralSingular.cmbCentral.selectedIndex == 0){
				this.painelFiltro.compCentralSingular.cmbSingular.enabled = false;
			}else{
				this.painelFiltro.compCentralSingular.cmbSingular.enabled = true;
			}
		}
		
		//--------------------------------------------------------------------------
		//  Relatorio - Lista de Agregados.
		//--------------------------------------------------------------------------
		private function relatorioListaPagadorAgregado(e:Event):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.numCpfCnpj = _TempFiltroNumCpfCnpjAgregado;
			dto.dados.numAgencia = _TempFiltroNumAgencia;
			dto.dados.codCentral = _TempFiltroCodCentral;
			dto.dados.contextoRelFlex = ConstantesRelatorios.RELATORIO_LISTA_PAGADOR_AGREGADO;
			
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_LISTA_PAGADOR_AGREGADO, null, null, PreImpressao.FORMATO_PDF, dto);
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Edição.
		//--------------------------------------------------------------------------
		private function popUpDetalhar(event:Event):void {
			var dto:PagadorAgregadoDTO = listaPagadorAgregado.grdDados.selectedItem as PagadorAgregadoDTO;
			var tela:PopUpDetalharPagador = new PopUpDetalharPagador(dto.numCpfCnpjPagador, null);
			JanelaCobranca.abrirJanela(this, tela, DETALHAMENTO_DO_PAGADOR_ELETRONICO);
		}
		
		//--------------------------------------------------------------------------
		//  Validar Procura.
		//--------------------------------------------------------------------------
		private function validarPesquisa(event:ProcurarEvent):void{
			if(painelFiltro.compCentralSingular.cmbCentral.dropdown.selectedItem.numeroCooperativa =="---"){
				if(StringUtils.trim(painelFiltro.txtCnpjCpfAgregado.text) == ""?true:false){
					MensagensComum.exibirMensagemAlerta("Digite um CPF/CNPJ ou Selecione uma Central e Singular");
					return;
				}
			}else if(painelFiltro.compCentralSingular.cmbSingular.dropdown.selectedItem.numeroCooperativa =="---"){
				MensagensComum.exibirMensagemAlerta("Digite um CPF/CNPJ ou Selecione uma Central e Singular");
				return;
			}
			this.painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
			this.painelListaBanco.procuraSolicitada(event);				
		}
		
		//--------------------------------------------------------------------------
		//  Limpar a pesquisa.
		//--------------------------------------------------------------------------
		public function limparCampos(evt:Event):void {
			this.painelFiltro.cmbCnpjCpfPagadorAgregado.selectedIndex = 0;
			this.painelFiltro.txtCnpjCpfAgregado.text = "";
			this.painelFiltro.compCentralSingular.cmbCentral.selectedIndex = 0;
			this.painelFiltro.compCentralSingular.cmbSingular.selectedIndex = 0;
			this.painelFiltro.compCentralSingular.cmbSingular.enabled = false;
			
			if(painelFiltro.cmbCnpjCpfPagadorAgregado.selectedIndex == TIPO_CPF){
				painelFiltro.txtCnpjCpfAgregado.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			}else if(painelFiltro.cmbCnpjCpfPagadorAgregado.selectedIndex == TIPO_CNPJ){
				painelFiltro.txtCnpjCpfAgregado.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
			}
			
			
			this._TempFiltroNumAgencia = null;
			this._TempFiltroCodCentral = null;
			limparGrid();
		}
		
		public function limparGrid(evt:Event = null):void {
			desabilitaBotoesAcao();
			listaPagadorAgregado.barraPaginacao.totalPaginas = listaPagadorAgregado.barraPaginacao.pagina = 0;
			listaPagadorAgregado.paginaAtual = 1;
			this.listaPagadorAgregado.grdDados.dataProvider = null;
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
			var pagadorDto:PagadorAgregadoDTO = new PagadorAgregadoDTO();
			
			reqDto.dados.dto = filtroPesquisa();
			dto.filtro = reqDto;
		}
		
		private function filtroPesquisa():PagadorAgregadoDTO{
			var dto:PagadorAgregadoDTO = new PagadorAgregadoDTO();
			if(painelFiltro.compCentralSingular.cmbSingular.selectedItem){
				dto.numCooperativaSingular = painelFiltro.compCentralSingular.cmbSingular.selectedItem.numeroCooperativa;
				dto.idInstituicao = painelFiltro.compCentralSingular.cmbSingular.selectedItem.idInstituicao;
				this._TempFiltroNumAgencia = painelFiltro.compCentralSingular.cmbSingular.selectedItem.numeroCooperativa;
				this._TempFiltroCodCentral = painelFiltro.compCentralSingular.cmbCentral.selectedItem.numeroCooperativa;
			}
			this._TempFiltroNumCpfCnpjAgregado = StringUtils.trim(painelFiltro.txtCnpjCpfAgregado.text) == ""?null:painelFiltro.txtCnpjCpfAgregado.text;
			dto.numCpfCnpj = _TempFiltroNumCpfCnpjAgregado;
			return dto;
		}
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
		//--------------------------------------------------------------------------
		//  Tratar botões de ação.
		//--------------------------------------------------------------------------
		private function tratarBotoesAcao(event:MouseEvent):void {
			if (listaPagadorAgregado.grdDados.selectedItem) {
				habilitaBotoesAcao();
			} else {
				desabilitaBotoesAcao();
			}
		}
		
		//--------------------------------------------------------------------------
		//  Trata Componente CPF/CNPJ
		//--------------------------------------------------------------------------
		private function tratarCnpjCpfPagadorAgregado(evt:Event = null):void {
			if (painelFiltro.cmbCnpjCpfPagadorAgregado.selectedIndex == TIPO_CPF) {
				painelFiltro.txtCnpjCpfAgregado.tipoCampo = InputCPFCNPJ.TIPO_CPF;
			} else if(painelFiltro.cmbCnpjCpfPagadorAgregado.selectedIndex == TIPO_CNPJ){
				painelFiltro.txtCnpjCpfAgregado.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
			}else{
				painelFiltro.txtCnpjCpfAgregado.tipoCampo = null;
			}
			
			listaPagadorAgregado.barraPaginacao.totalPaginas = listaPagadorAgregado.barraPaginacao.pagina = 0;
			listaPagadorAgregado.grdDados.dataProvider = null;
		}
		
		private function restaCmbSingular(e:Event):void{
			if(this.painelFiltro.compCentralSingular.cmbCentral.selectedIndex == 0){
				this.painelFiltro.compCentralSingular.cmbSingular.selectedIndex = 0;	
			}
		}
		
		//--------------------------------------------------------------------------
		//  Habilita e Desabilita os campos do lado da tabela.
		//--------------------------------------------------------------------------
		private function habilitaBotoesAcao():void {
			this.btnDetalhar.enabled = true;
			this.btnImprimir.enabled = true;
		}
		
		private function desabilitaBotoesAcao():void {
			this.btnDetalhar.enabled = false;
			this.btnImprimir.enabled = false;
		}
	}
}