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
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.TipoMensagemDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.CategoriaMensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoGradeHorariaVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoMensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipomensagem.PopUpEditarTipoMensagem;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipomensagem.PopUpIncluirTipoMensagem;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipomensagem.PopUpIncluirTipoMensagemView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipomensagem.TipoMensagemView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;

	registerClassAlias("RegistroVO", RegistroVO);
	public class TipoMensagem extends TipoMensagemView
	{
		
		public static const TITULO_INCLUIR_TIPO_MENSAGEM:String = "INCLUIR TIPO MENSAGEM";
		public static const TITULO_EDITAR_TIPO_MENSAGEM:String = "EDITAR TIPO MENSAGEM";
		public static const TITULO_VISUALIZAR_TIPO_MENSAGEM:String = "VISUALIZAR TIPO MENSAGEM";
		
		public function TipoMensagem()
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
			this.painelListaPainel.funcaoCriacaoDto = instanciarDtoConsulta;
			
			this.btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			//Desabilita barra botões
			barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoFechar = barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoExcluir = false ;
			barraBotoesListaCadastro.includeInLayout = false;
			barraBotoesListaCadastro.visible = false;
			barraBotoesListaCadastro.height = 0;
			barraBotoesListaCadastro.width = 0
			
			//Trata botões de ação			
			btnDetalhar.enabled = false;
			btnAlterar.enabled = false;
			btnRemover.enabled = false;

			
			this.btnIncluir.addEventListener(MouseEvent.CLICK, popUpIncluir);
			this.btnAlterar.addEventListener(MouseEvent.CLICK, popUpEditar);
			this.btnDetalhar.addEventListener(MouseEvent.CLICK, popUpVisualizar);
			this.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btnRemover.addEventListener(MouseEvent.CLICK, remover);
			
			
			carregarFiltros();
			this.painelFiltro.cmbExigeRetorno.dataProvider = Constantes.CONFIRMA.slice();

			this.painelFiltro.cmbExigeRetorno.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.cmbTipoGradeHoraria.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.cmbCategoria.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.cmbTipoMsg.addEventListener(ListEvent.CHANGE, limparGrid);
			
			this.listaTipoMensagem.grdDados.addEventListener(Event.RENDER, tratarBotoesAcao);
			this.listaTipoMensagem.grdDados.doubleClickEnabled = false;
			this.painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaPainel.procuraSolicitada);
			this.painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, pesquisarTipoMensagem);
			
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Cadastro.
		//--------------------------------------------------------------------------
		private function popUpIncluir(event:MouseEvent):void {
			var tela:PopUpIncluirTipoMensagemView = new PopUpIncluirTipoMensagem(refazerPequisa);
				JanelaCobranca.abrirJanela(this, tela, TITULO_INCLUIR_TIPO_MENSAGEM);
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Edição.
		//--------------------------------------------------------------------------
		private function popUpEditar(event:Event):void {
			var itemTipoMensagem:TipoMensagemDDAVO = listaTipoMensagem.grdDados.selectedItem as TipoMensagemDDAVO;
			var tela:PopUpEditarTipoMensagem = new PopUpEditarTipoMensagem(itemTipoMensagem, refazerPequisa, false);
			JanelaCobranca.abrirJanela(this, tela, TITULO_EDITAR_TIPO_MENSAGEM)
		}
		
		private function popUpVisualizar(event:Event):void {
			var itemTipoMensagem:TipoMensagemDDAVO = listaTipoMensagem.grdDados.selectedItem as TipoMensagemDDAVO;
			var tela:PopUpEditarTipoMensagem = new PopUpEditarTipoMensagem(itemTipoMensagem, refazerPequisa, true);
			JanelaCobranca.abrirJanela(this, tela, TITULO_VISUALIZAR_TIPO_MENSAGEM)
		}
		
		//--------------------------------------------------------------------------
		//  Carrega lista das combo.
		//--------------------------------------------------------------------------
		private function carregarFiltros():void{
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_MENSAGEM, "carregarFiltrosTipoMensagem");
			servico.addEventListener(ResultEvent.RESULT, resultFiltros);
			servico.carregarFiltrosTipoMensagem();
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de callback.
		//--------------------------------------------------------------------------
		private function resultFiltros(event:ResultEvent):void{
			var tipoMensagemDTO:TipoMensagemDTO =  event.result.dados["lista"] as TipoMensagemDTO;
			this.painelFiltro.cmbTipoGradeHoraria.dataProvider = tipoMensagemDTO.listaTipoGradeHoraria;
			this.painelFiltro.cmbCategoria.dataProvider = tipoMensagemDTO.listaCategoriaMensagem;
			this.painelFiltro.cmbTipoMsg.dataProvider = tipoMensagemDTO.listaArquivoCorrespondente;
		}
		
		//--------------------------------------------------------------------------
		//  Validar Procura.
		//--------------------------------------------------------------------------
		private function pesquisarTipoMensagem(event:ProcurarEvent):void{
				this.painelListaPainel.funcaoConfiguracaoDto = configurarDtoConsulta;
				this.painelListaPainel.procuraSolicitada(event);
		}
		
		//--------------------------------------------------------------------------
		//  Carrega lista de Grade Horária.
		//--------------------------------------------------------------------------
		private function pesquisar(evt:Event = null):void{
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_GRADE_HORARIA, "");
			servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			servico.bloquearOperacao = false;
			servico.showBusyCursor = false;
			servico.obterDadosMonitoramentoFila();
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de callback.
		//--------------------------------------------------------------------------
		private function retornoPesquisar(event:ResultEvent):void{
			
		}
		
		//--------------------------------------------------------------------------
		//  Limpar a pesquisa.
		//--------------------------------------------------------------------------
		public function limparCampos(evt:Event):void {
			this.painelFiltro.cmbTipoGradeHoraria.selectedIndex = 0;
			this.painelFiltro.cmbExigeRetorno.selectedIndex = 0;
			this.painelFiltro.cmbCategoria.selectedIndex = 0;
			this.painelFiltro.cmbTipoMsg.selectedIndex = 0;
			this.listaTipoMensagem.grdDados.dataProvider = null;
			limparGrid(null);
		}
		
		public function limparGrid(evt:Event):void {
			desabilitaBotoesAcao();
			this.listaTipoMensagem.grdDados.dataProvider = null;
			this.listaTipoMensagem.limparConteudo();
			listaTipoMensagem.barraPaginacao.totalPaginas = listaTipoMensagem.barraPaginacao.pagina = 0;
			listaTipoMensagem.paginaAtual = 1;
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
			var tipoMensagemDDAVO:TipoMensagemDTO = new TipoMensagemDTO();
			
			if (this.painelFiltro.cmbTipoGradeHoraria.selectedItem) {
				var tipoGradeHoraria:TipoGradeHorariaVO = painelFiltro.cmbTipoGradeHoraria.selectedItem as TipoGradeHorariaVO;
				tipoMensagemDDAVO.codTipoGradeHoraria = tipoGradeHoraria.codTipoGradeHoraria
			}
			
			if (this.painelFiltro.cmbCategoria.selectedItem) {
				var categoriaMensagemDDA:CategoriaMensagemDDAVO = painelFiltro.cmbCategoria.selectedItem as CategoriaMensagemDDAVO;
				tipoMensagemDDAVO.codCategoriaMensagemDda = categoriaMensagemDDA.codCategoriaMensagemDda;
			}
			
			if (this.painelFiltro.cmbExigeRetorno.selectedItem) {
				tipoMensagemDDAVO.exigeMensagemRetorno = painelFiltro.cmbExigeRetorno.selectedItem.data;
			}

			if (this.painelFiltro.cmbTipoMsg.selectedItem) {
				tipoMensagemDDAVO.codTipoMensagem = painelFiltro.cmbTipoMsg.selectedItem.codTipoMensagem;
			}
			
			reqDto.dados.dto = tipoMensagemDDAVO;
			dto.filtro = reqDto;
		}
		
		private function remover(event:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao(Mensagens.MSG055, null, removerGradeHoraria);
		}
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function removerGradeHoraria(event:MouseEvent):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.codTipoMensagem = (listaTipoMensagem.grdDados.selectedItem as TipoMensagemDDAVO).codTipoMensagem;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_MENSAGEM, "removerGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, retornoRemover);
			servico.removerGradeHoraria(dto);	
		}
		
		private function retornoRemover(resultado:ResultEvent):void {
			listaTipoMensagem.grdDados.dataProvider = null;
			painelListaPainel.refazerPesquisa();
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
		private function refazerPequisa():void {
			painelListaPainel.refazerPesquisa();
		}
		
		//--------------------------------------------------------------------------
		//  Tratar botões de ação.
		//--------------------------------------------------------------------------
		private function tratarBotoesAcao(event:Event):void {
			if(listaTipoMensagem.grdDados.selectedItem){
				habilitaBotoesAcao();
			}else{
				desabilitaBotoesAcao();
			}
		}
		
		private function habilitaBotoesAcao():void {
			this.btnDetalhar.enabled = true;
			this.btnAlterar.enabled = true;
			this.btnRemover.enabled = true;
		}
		
		private function desabilitaBotoesAcao():void {
			this.btnDetalhar.enabled = false;
			this.btnAlterar.enabled = false;
			this.btnRemover.enabled = false;
		}
	}
}