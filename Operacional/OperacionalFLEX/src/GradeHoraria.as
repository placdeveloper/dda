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
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.GradeHorariaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.gradehoraria.GradeHorariaView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.gradehoraria.PopUpEditarGradeHoraria;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.gradehoraria.PopUpIncluirGradeHoraria;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;

	registerClassAlias("RegistroVO", RegistroVO);
	public class GradeHoraria extends GradeHorariaView
	{
		
		public static const TITULO_INCLUIR_GRADE_HORARIA:String = "INCLUIR GRADE HORÁRIA";
		public static const TITULO_EDITAR_GRADE_HORARIA:String = "EDITAR GRADE HORÁRIA";
		public static const TITULO_VISUALIZAR_GRADE_HORARIA:String = "VISUALIZAR GRADE HORÁRIA";
		
		public function GradeHoraria()
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

			this.listaGradeHoraria.doubleClickEnabled = false;
			
			this.btnIncluir.addEventListener(MouseEvent.CLICK, popUpIncluir);
			this.btnAlterar.addEventListener(MouseEvent.CLICK, popUpEditar);
			this.btnDetalhar.addEventListener(MouseEvent.CLICK, popUpVisualizar);
			this.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btnRemover.addEventListener(MouseEvent.CLICK, remover);
			
			comboTipoGradeHoraria();
			this.painelFiltro.cmbTipoGradeHoraria.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.chkGradesEmUso.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.dataReferencia.addEventListener(Event.CHANGE, limparGrid);
			this.listaGradeHoraria.grdDados.addEventListener(MouseEvent.CLICK, tratarBotoesAcao);
			
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaPainel.procuraSolicitada);
			this.painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarPesquisa);
			this.painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Cadastro.
		//--------------------------------------------------------------------------
		private function popUpIncluir(event:MouseEvent):void {
			var tela:PopUpIncluirGradeHoraria = new PopUpIncluirGradeHoraria(refazerPequisa);
				JanelaCobranca.abrirJanela(this, tela, TITULO_INCLUIR_GRADE_HORARIA);
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Edição.
		//--------------------------------------------------------------------------
		private function popUpEditar(event:Event):void {
			var itemGradeHoraria:GradeHorariaDTO = listaGradeHoraria.grdDados.selectedItem as GradeHorariaDTO;
			var tela:PopUpEditarGradeHoraria = new PopUpEditarGradeHoraria(itemGradeHoraria, refazerPequisa, false);
			JanelaCobranca.abrirJanela(this, tela, TITULO_EDITAR_GRADE_HORARIA)
		}
		//--------------------------------------------------------------------------
		//  Abre a Janela de Visualização.
		//--------------------------------------------------------------------------		
		private function popUpVisualizar(event:Event):void {
			var itemGradeHoraria:GradeHorariaDTO = listaGradeHoraria.grdDados.selectedItem as GradeHorariaDTO;
			var tela:PopUpEditarGradeHoraria = new PopUpEditarGradeHoraria(itemGradeHoraria, refazerPequisa,true);
			JanelaCobranca.abrirJanela(this, tela, TITULO_VISUALIZAR_GRADE_HORARIA)
		}
		
		//--------------------------------------------------------------------------
		//  Carrega lista Tipo Grade Horária da combo.
		//--------------------------------------------------------------------------
		private function comboTipoGradeHoraria(evt:Event = null):void{
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_GRADE_HORARIA, "listarTipoGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, carregarComboTipoGrade);
			servico.listarTipoGradeHoraria();
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de callback.
		//--------------------------------------------------------------------------
		private function carregarComboTipoGrade(event:ResultEvent):void{
			this.painelFiltro.cmbTipoGradeHoraria.dataProvider = event.result.dados["lista"];
		}
		
		//--------------------------------------------------------------------------
		//  Validar Procura.
		//--------------------------------------------------------------------------
		private function validarPesquisa(event:ProcurarEvent):void{
				this.limparDadosPesquisa();
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
			painelFiltro.dataReferencia.selectedDate = null;
			painelFiltro.chkGradesEmUso.selected = false;
			this.listaGradeHoraria.grdDados.dataProvider = null;
			listaGradeHoraria.barraPaginacao.totalPaginas = listaGradeHoraria.barraPaginacao.pagina = 0;
			listaGradeHoraria.paginaAtual = 1;
		}
		
		public function limparGrid(evt:Event):void {
			desabilitaBotoesAcao();
			this.listaGradeHoraria.limparConteudo();
			listaGradeHoraria.barraPaginacao.totalPaginas = listaGradeHoraria.barraPaginacao.pagina = 0;
			listaGradeHoraria.paginaAtual = 1;
			this.listaGradeHoraria.grdDados.dataProvider = null;
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
			var gradeHorariaDTO:GradeHorariaDTO = new GradeHorariaDTO();
			
			if (this.painelFiltro.cmbTipoGradeHoraria.selectedItem) {
				gradeHorariaDTO.codTipoGradeHoraria = painelFiltro.cmbTipoGradeHoraria.selectedItem.codTipoGradeHoraria;
			}
			
			if (this.painelFiltro.chkGradesEmUso.selected) {
				gradeHorariaDTO.gradesEmUso = this.painelFiltro.chkGradesEmUso.selected;
			}
			
			if (this.painelFiltro.dataReferencia.selectedDate){
				gradeHorariaDTO.dataReferencia = DateTimeBase.getDateTime(painelFiltro.dataReferencia.selectedDate);
			}
			
			reqDto.dados.dto = gradeHorariaDTO;
			dto.filtro = reqDto;
		}
		
		private function remover(event:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao(Mensagens.MSG054, null, removerGradeHoraria);
		}
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function removerGradeHoraria(event:MouseEvent):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = (listaGradeHoraria.grdDados.selectedItem as GradeHorariaDTO);
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_GRADE_HORARIA, "removerGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, retornoRemover);
			servico.removerGradeHoraria(dto);	

		}
		
		private function retornoRemover(resultado:ResultEvent):void {
			refazerPequisa();
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
			listaGradeHoraria.grdDados.selectedItem = null;
			tratarBotoesAcao(null);
			this.painelListaPainel.funcaoConfiguracaoDto = configurarDtoConsulta
			limparGrid(null);
			painelListaPainel.procuraSolicitada(new ProcurarEvent);
		}
		
		//--------------------------------------------------------------------------
		//  Tratar botões de ação.
		//--------------------------------------------------------------------------
		private function tratarBotoesAcao(event:MouseEvent):void {
			if(listaGradeHoraria.grdDados.selectedItem){
				habilitaBotoesAcao();
			}else{
				desabilitaBotoesAcao();
			}
		}
		
		//--------------------------------------------------------------------------
		//  Habilita os campos do lado da tabela.
		//--------------------------------------------------------------------------
		private function habilitaBotoesAcao():void {
			this.btnDetalhar.enabled = true;
			this.btnAlterar.enabled = true;
			this.btnRemover.enabled = true;
		}
		
		//--------------------------------------------------------------------------
		//  Habilita os campos do lado da tabela.
		//--------------------------------------------------------------------------
		private function desabilitaBotoesAcao():void {
			this.btnDetalhar.enabled = false;
			this.btnAlterar.enabled = false;
			this.btnRemover.enabled = false;
		}
	}
}