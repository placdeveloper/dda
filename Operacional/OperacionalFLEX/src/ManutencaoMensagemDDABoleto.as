package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MensagemDDABoletoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MensagemDDABoletoFiltroDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.manutencaomensagemddaboleto.ManutencaoMensagemDDABoletoView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.manutencaomensagemddaboleto.PopUpAlterarMensagemBoleto;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);	
	
	public class ManutencaoMensagemDDABoleto extends ManutencaoMensagemDDABoletoView
	{
		
		public static const DADOS_DO_BOLETO:String = "DADOS DO BOLETO";
		private var _mensagemFiltro:MensagemDDABoletoFiltroDTO;
		private var _mensagemDDABoletoDTO:MensagemDDABoletoDTO;
		
		public function ManutencaoMensagemDDABoleto()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaBanco.procuraSolicitada);
			this.painelListaBanco.funcaoCriacaoDto = instanciarDtoConsulta;
			
			this.painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, executarPesquisa);
			this.painelFiltro.cmbTipoMensagem.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.dataMovimento.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.numCodigoBarras.addEventListener(ListEvent.CHANGE, limparGrid);
			
			this.btnAlterar.addEventListener(MouseEvent.CLICK, popUpEditar);
			this.painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			this.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			this.listaMensagemDDABoleto.grdDados.addEventListener(MouseEvent.CLICK, tratarBotoesAcao);
			this.listaMensagemDDABoleto.grdDados.doubleClickEnabled = false;
			
			iniciarComponentes();
		}	
		
		private function iniciarComponentes():void {
			iniciarBotoes();
			carregarFiltros();
		}
		
		//--------------------------------------------------------------------------
		//  Inicializar botões.
		//--------------------------------------------------------------------------
		private function iniciarBotoes():void {
			//Trata botões de ação			
			btnAlterar.enabled = false;
			
			barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoExcluir = false ;
			barraBotoesListaCadastro.includeInLayout = false;
			barraBotoesListaCadastro.visible = false;
			barraBotoesListaCadastro.height = 0;
			barraBotoesListaCadastro.width = 0
			
			/*var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			var btnFechar:Botao = hBoxBarraBotoes.getChildByName('btnFechar') as Botao;*/
			this.btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos filtros de pesquisa.
		//--------------------------------------------------------------------------
		private function carregarFiltros():void {
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MANUTENCAO_MENSAGEMDDABOLETO, "listarCombos");
			servico.addEventListener(ResultEvent.RESULT, retornoCarregarFiltros);
			servico.listarCombos();			
		}
		
		private function retornoCarregarFiltros(resultEvent:ResultEvent):void {
			_mensagemFiltro = resultEvent.result.dados["filtros"] as MensagemDDABoletoFiltroDTO;
			this.painelFiltro.cmbTipoMensagem.dataProvider = carregarComboTipoMensagem();
			this.painelFiltro.cmbSituacaoMensagem.dataProvider = carregarComboSituacaoMensagem();
		}
		
		private function carregarComboSituacaoMensagem():Array {
			var comboSituacaoMensagem:Array = [
				{label: "---", data:0},
				{label: "A Enviar", data:1},
				{label: "Retorno Com Erro", data:4}
			];
			return comboSituacaoMensagem;
		}
		
		private function carregarComboTipoMensagem():Array {
			var comboTipoMensagem:Array = [
				{label: "---"},
				{label: "DDA0101"},
				{label: "DDA0102"},
				{label: "ADDA101"},
				{label: "ADDA102"}
			];
			return comboTipoMensagem;
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Edição.
		//--------------------------------------------------------------------------
		private function popUpEditar(event:Event):void {
			var mensagemDDABoleto:MensagemDDABoletoDTO = listaMensagemDDABoleto.grdDados.selectedItem as MensagemDDABoletoDTO;
			var tela:PopUpAlterarMensagemBoleto = new PopUpAlterarMensagemBoleto(mensagemDDABoleto.idMensagem, refazerPequisa, _mensagemFiltro);
			JanelaCobranca.abrirJanela(this, tela, DADOS_DO_BOLETO);
		}
		
		//--------------------------------------------------------------------------
		//  Validar Procura.
		//--------------------------------------------------------------------------
		private function executarPesquisa(event:ProcurarEvent):void{
			if(validarFiltros()) {
				this.limparDadosPesquisa();
				this.painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
				this.painelListaBanco.procuraSolicitada(event);	
				habilitaBotoesAcao();				
			} else {
				return;
			}
		}
		
		//--------------------------------------------------------------------------
		//  Validar Filtros.
		//--------------------------------------------------------------------------
		private function validarFiltros():Boolean {
			if(this.painelFiltro.cmbTipoMensagem.selectedLabel.length == 0) {
				MensagensComum.exibirMensagemErro("O Filtro Tipo mensagem é de preenchimento obrigatório.");
				return false;
			}
			
			if(this.painelFiltro.cmbSituacaoMensagem.selectedLabel.length == 0) {
				MensagensComum.exibirMensagemErro("O Filtro Situação Mensagem é de preenchimento obrigatório.");
				return false;
			}
			return true;
		}
		
		//--------------------------------------------------------------------------
		//  Limpar a pesquisa.
		//--------------------------------------------------------------------------
		public function limparCampos(evt:Event):void {
			this.painelFiltro.cmbTipoMensagem.selectedIndex = 0;
			painelFiltro.dataMovimento.selectedDate = null;
			painelFiltro.numCodigoBarras.text = "";
			
			limparDadosPesquisa();
			
			this.listaMensagemDDABoleto.grdDados.dataProvider = null;
			listaMensagemDDABoleto.barraPaginacao.totalPaginas = listaMensagemDDABoleto.barraPaginacao.pagina = 0;
			listaMensagemDDABoleto.paginaAtual = 1;
			desabilitaBotoesAcao();
		}
		
		public function limparGrid(evt:Event):void {
			desabilitaBotoesAcao();
			this.listaMensagemDDABoleto.limparConteudo();
			listaMensagemDDABoleto.barraPaginacao.totalPaginas = listaMensagemDDABoleto.barraPaginacao.pagina = 0;
			listaMensagemDDABoleto.paginaAtual = 1;
			this.listaMensagemDDABoleto.grdDados.dataProvider = null;
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
			var filtroDTO:MensagemDDABoletoFiltroDTO = new MensagemDDABoletoFiltroDTO();
			
			if (this.painelFiltro.cmbTipoMensagem.selectedItem) {
				filtroDTO.tipoMensagem = painelFiltro.cmbTipoMensagem.selectedLabel;
			}
			
			if (this.painelFiltro.dataMovimento.selectedDate) {
				filtroDTO.dataMovimento = DateTimeBase.getDateTime(painelFiltro.dataMovimento.selectedDate);			
			}
			
			if(this.painelFiltro.numCodigoBarras.text){
				filtroDTO.numCodigoDeBarras = painelFiltro.numCodigoBarras.text;
			}
			
			if(this.painelFiltro.cmbSituacaoMensagem.selectedItem) {
				filtroDTO.situacaoMensagem = painelFiltro.cmbSituacaoMensagem.selectedItem.data;
			}
			reqDto.dados.dto = filtroDTO;
			dto.filtro = reqDto;
		}
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
		//--------------------------------------------------------------------------
		//  CallBack Edição ou Inclusão.
		//--------------------------------------------------------------------------
		private function refazerPequisa(e:Event = null):void {
			listaMensagemDDABoleto.grdDados.selectedItem = null;
			tratarBotoesAcao(null);
			this.painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
			limparGrid(null);
			painelListaBanco.procuraSolicitada(new ProcurarEvent);
		}
		
		//--------------------------------------------------------------------------
		//  Tratar botões de ação.
		//--------------------------------------------------------------------------
		private function tratarBotoesAcao(event:MouseEvent):void {
			if(listaMensagemDDABoleto.grdDados.selectedItem){
				habilitaBotoesAcao();
			}else{
				desabilitaBotoesAcao();
			}
		}
		
		//--------------------------------------------------------------------------
		//  Habilita e Desabilita os campos do lado da tabela.
		//--------------------------------------------------------------------------
		private function habilitaBotoesAcao():void {
			this.btnAlterar.enabled = true;
		}
		
		private function desabilitaBotoesAcao():void {
			this.btnAlterar.enabled = false;
		}
	}
}