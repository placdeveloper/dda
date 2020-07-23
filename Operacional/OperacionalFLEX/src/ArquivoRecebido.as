package
{
	import flash.events.ContextMenuEvent;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	import flash.system.System;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuItem;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.containers.EventoJanela;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ArquivoRecebidoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataPesquisaUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.arquivorecebido.ArquivoRecebidoView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.arquivorecebido.PopUpAlterarSituacaoArquivo;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.arquivorecebido.PopUpDetalharArquivoRecebido;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("RegistroVO", RegistroVO);
	public class ArquivoRecebido extends ArquivoRecebidoView
	{
		
		public static const ALTERAR_SITUACAO_ARQUIVO:String = "ALTERAR SITUAÇÃO ARQUIVO";
		public static const DETALHAMENTO_DO_ARQUIVO:String = "DETALHAMENTO DO ARQUIVO";
		private var _itemCopy:ContextMenuItem = new ContextMenuItem("Copiar Nome Arquivo");
		
		private var _listaSituacaoArquivo:ArrayCollection;
		private var _menuGridMensagem:ContextMenu = new ContextMenu();
		
		public function ArquivoRecebido()
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
			
			this.painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarPesquisa);
			this.painelFiltro.cmbTipoArquivo.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.cmbTipoMensagem.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.cmbSituacaoArquivo.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.dtHoraInicio.campoData.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dtHoraFim.campoData.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dtHoraInicio.campoHora.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dtHoraFim.campoHora.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dtMovimento.addEventListener(Event.CHANGE, limparGrid);
			
			this.btnAlterar.addEventListener(MouseEvent.CLICK, popUpEditar);
			this.btnDetalhar.addEventListener(MouseEvent.CLICK, popUpVisualizar);
			this.painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			
			this.listaArquivoRecebido.grdDados.addEventListener(MouseEvent.CLICK, tratarBotoesAcao);
			this.listaArquivoRecebido.grdDados.doubleClickEnabled = false;
			
			iniciarComponentes();
			menuContexto();
		}	
		
		private function iniciarComponentes():void {
			this.painelFiltro.dtHoraInicio.campoHora.width = 90;
			this.painelFiltro.dtHoraFim.campoHora.width = 90;
			iniciarBotoes();
			carregarFiltros();
		}
		
		//--------------------------------------------------------------------------
		//  Inicializar botões.
		//--------------------------------------------------------------------------
		private function iniciarBotoes():void {
			//Trata botões de ação			
			btnDetalhar.enabled = false;
			btnAlterar.enabled = false;
			
			barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoExcluir = false ;
			var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			var btnFechar:Botao = hBoxBarraBotoes.getChildByName('btnFechar') as Botao;
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos filtros de pesquisa.
		//--------------------------------------------------------------------------
		private function carregarFiltros():void {
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_RECEBIDO, "carregarFiltrosArquivoRecebido");
			servico.addEventListener(ResultEvent.RESULT, retornoCarregarFiltros);
			servico.carregarFiltrosArquivoRecebido();			
		}
		
		private function retornoCarregarFiltros(resultEvent:ResultEvent):void {
			var arquivoRecebidoDto:ArquivoRecebidoDTO = resultEvent.result.dados["filtros"];
			this.painelFiltro.cmbTipoArquivo.dataProvider = arquivoRecebidoDto.listaTipoArquivo;
			this.painelFiltro.cmbTipoMensagem.dataProvider = arquivoRecebidoDto.listaTipoMensagem;
			this.painelFiltro.cmbSituacaoArquivo.dataProvider = _listaSituacaoArquivo = arquivoRecebidoDto.listaSituacaoArquivo;
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Edição.
		//--------------------------------------------------------------------------
		private function popUpEditar(event:Event):void {
			var arquivoRecebido:ArquivoRecebidoDTO = listaArquivoRecebido.grdDados.selectedItem as ArquivoRecebidoDTO;
			var tela:PopUpAlterarSituacaoArquivo = new PopUpAlterarSituacaoArquivo(arquivoRecebido.idLogRecebimentoArqDDA, _listaSituacaoArquivo, refazerPequisa);
			JanelaCobranca.abrirJanela(this, tela, ALTERAR_SITUACAO_ARQUIVO);
		}
		//--------------------------------------------------------------------------
		//  Abre a Janela de Visualização.
		//--------------------------------------------------------------------------		
		private function popUpVisualizar(event:Event):void {
			var arquivoRecebido:ArquivoRecebidoDTO = listaArquivoRecebido.grdDados.selectedItem as ArquivoRecebidoDTO;
			var tela:PopUpDetalharArquivoRecebido = new PopUpDetalharArquivoRecebido(arquivoRecebido.idLogRecebimentoArqDDA);
			
			var janela:Janela = JanelaCobranca.abrirJanela(this, tela, DETALHAMENTO_DO_ARQUIVO);
			janela.addEventListener(CloseEvent.CLOSE, refazerPequisa);
			janela.addEventListener(EventoJanela.FECHAR_JANELA, refazerPequisa);
		}
		
		//--------------------------------------------------------------------------
		//  Validar Procura.
		//--------------------------------------------------------------------------
		private function validarPesquisa(event:ProcurarEvent):void{
			if (validaData()) {
				return;
			} else {
				this.limparDadosPesquisa();
				this.painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
				this.painelListaBanco.procuraSolicitada(event);				
			}
		}
		
		//--------------------------------------------------------------------------
		//  Valida datas por evento de tecla e mouse.
		//--------------------------------------------------------------------------
		public function validaData():Boolean {
			var retorno:Boolean;
			
			var bolDataArquivoNull:Boolean = (this.painelFiltro.dtHoraInicio.campoData.selectedDate == null && this.painelFiltro.dtHoraFim.campoData.selectedDate == null);
			
			if (bolDataArquivoNull) {
				retorno = false;
			} else {
				if (!bolDataArquivoNull) {
					if (!DataPesquisaUtil.validaPeriodoDataPesquisa(this.painelFiltro.dtHoraInicio.campoData, this.painelFiltro.dtHoraFim.campoData)) {
						retorno = true;	 
					} else {
						//Colocoar para quando as horas forem nulas nao entrar aqui
						if (validaDatasIguais(this.painelFiltro.dtHoraInicio.dateTime, this.painelFiltro.dtHoraFim.dateTime) && !(this.painelFiltro.dtHoraInicio.campoHora.text == "" && this.painelFiltro.dtHoraFim.campoHora.text == "")) {
							MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Data Hora Arquivo Inicial"));
							retorno = true;
						} else {
							if ((this.painelFiltro.dtHoraInicio.campoHora.text == "" && this.painelFiltro.dtHoraFim.campoHora.text == "")) {
								this.painelFiltro.dtHoraFim.dataSelecionada.setHours(23);
								this.painelFiltro.dtHoraFim.dataSelecionada.setMinutes(59);
								this.painelFiltro.dtHoraFim.dataSelecionada.setSeconds(59);
								
								this.painelFiltro.dtHoraInicio.dataSelecionada.setHours(00);
								this.painelFiltro.dtHoraInicio.dataSelecionada.setMinutes(00);
								this.painelFiltro.dtHoraInicio.dataSelecionada.setSeconds(00);
							}
						}
					}
				}
			}
			return retorno;
		}
		
		//--------------------------------------------------------------------------
		//  Valida período de 6 mêses.
		//--------------------------------------------------------------------------
		public function validaDatasIguais(data1:IDateTime, data2:IDateTime):Boolean {
			var valorGrandeza:Number = DataUtil.compareDataTime(data1, data2);
			var isGrandeza:Boolean = valorGrandeza==0 || valorGrandeza == 1 ? true : false;			
			return isGrandeza; 
		}
		
		//--------------------------------------------------------------------------
		//  Valida período de 6 mêses.
		//--------------------------------------------------------------------------
		public function validarPeriodo():Boolean {
			return DataPesquisaUtil.validaPeriodoDataPesquisa(this.painelFiltro.dtHoraInicio.campoData, this.painelFiltro.dtHoraFim.campoData); 
		}
		
		//--------------------------------------------------------------------------
		//  Limpar a pesquisa.
		//--------------------------------------------------------------------------
		public function limparCampos(evt:Event):void {
			this.painelFiltro.cmbTipoArquivo.selectedIndex = 0;
			this.painelFiltro.cmbTipoMensagem.selectedIndex = 0;
			this.painelFiltro.cmbSituacaoArquivo.selectedIndex = 0;
			
			painelFiltro.dtHoraInicio.dataSelecionada = null;
			painelFiltro.dtHoraFim.dataSelecionada = null;

			painelFiltro.dtHoraInicio.horaSelecionada = "";
			painelFiltro.dtHoraFim.horaSelecionada = "";
			
			this.painelFiltro.dtMovimento.selectedDate = null;
			this.painelFiltro.dtMovimento.compDate.text = "";
			this.painelFiltro.descNomeArquivoRecebido.text = "";
			
			limparDadosPesquisa();
			
			this.listaArquivoRecebido.grdDados.dataProvider = null;
			listaArquivoRecebido.barraPaginacao.totalPaginas = listaArquivoRecebido.barraPaginacao.pagina = 0;
			listaArquivoRecebido.paginaAtual = 1;
			desabilitaBotoesAcao();
		}
		
		public function limparGrid(evt:Event):void {
			desabilitaBotoesAcao();
			this.listaArquivoRecebido.limparConteudo();
			listaArquivoRecebido.barraPaginacao.totalPaginas = listaArquivoRecebido.barraPaginacao.pagina = 0;
			listaArquivoRecebido.paginaAtual = 1;
			this.listaArquivoRecebido.grdDados.dataProvider = null;
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
			var arquivoRecebidoDto:ArquivoRecebidoDTO = new ArquivoRecebidoDTO();
			
			if (this.painelFiltro.cmbTipoArquivo.selectedItem) {
				arquivoRecebidoDto.codTipoArquivo = painelFiltro.cmbTipoArquivo.selectedItem.codTipoArquivo;
			}
			
			if (this.painelFiltro.cmbTipoMensagem.selectedItem) {
				arquivoRecebidoDto.codTipoMensagem = painelFiltro.cmbTipoMensagem.selectedItem.codTipoMensagem;
			}
			
			if (this.painelFiltro.cmbSituacaoArquivo.selectedItem) {
				arquivoRecebidoDto.codSituacaoProcessamentoArquivo = painelFiltro.cmbSituacaoArquivo.selectedItem.codSituacaoProcessamentoArquivo;
			}
			
			if (this.painelFiltro.dtHoraInicio.dateTime) {
				arquivoRecebidoDto.dataArquivoInicio = painelFiltro.dtHoraInicio.dateTime;
			}
			
			if (this.painelFiltro.dtHoraFim.dateTime) {
				arquivoRecebidoDto.dataArquivoFim = painelFiltro.dtHoraFim.dateTime;
			}
			
			if (this.painelFiltro.dtMovimento.selectedDate) {
				arquivoRecebidoDto.dataMovimento = DateTimeBase.getDateTime(painelFiltro.dtMovimento.selectedDate);
			}
			
			if(StringUtil.trim(this.painelFiltro.descNomeArquivoRecebido.text)){
				arquivoRecebidoDto.descNomeArquivoRecebido = this.painelFiltro.descNomeArquivoRecebido.text;
			}
			
			reqDto.dados.dto = arquivoRecebidoDto;
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
			listaArquivoRecebido.grdDados.selectedItem = null;
			tratarBotoesAcao(null);
			this.painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
			limparGrid(null);
			painelListaBanco.procuraSolicitada(new ProcurarEvent);
		}
		
		//--------------------------------------------------------------------------
		//  Tratar botões de ação.
		//--------------------------------------------------------------------------
		private function tratarBotoesAcao(event:MouseEvent):void {
			if (listaArquivoRecebido.grdDados.selectedItem) {
				habilitaBotoesAcao();
			} else {
				desabilitaBotoesAcao();
			}
		}
		
		//--------------------------------------------------------------------------
		//  Habilita e Desabilita os campos do lado da tabela.
		//--------------------------------------------------------------------------
		private function habilitaBotoesAcao():void {
			this.btnDetalhar.enabled = true;
			this.btnAlterar.enabled = true;
		}
		
		private function desabilitaBotoesAcao():void {
			this.btnDetalhar.enabled = false;
			this.btnAlterar.enabled = false;
		}
		
		//--------------------------------------------------------------------------
		//  FUNÇÃO MENU DE CONTEXTO COPIAR.
		//--------------------------------------------------------------------------
		private function menuContexto():void {
			_itemCopy.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, menuContextoCopiar);
			_menuGridMensagem.customItems.push(_itemCopy);
			listaArquivoRecebido.contextMenu = _menuGridMensagem;
		}
		
		private function validaContexto(e:MouseEvent):void {
			if(listaArquivoRecebido.grdDados.selectedItem){
				_itemCopy.enabled = true;
			}
		}
		
		private function menuContextoCopiar(e:ContextMenuEvent):void {
			if(listaArquivoRecebido.grdDados.selectedItem){
				System.setClipboard(listaArquivoRecebido.grdDados.selectedItem.descNomeArquivoRecebido);
			}
		}
	}
}