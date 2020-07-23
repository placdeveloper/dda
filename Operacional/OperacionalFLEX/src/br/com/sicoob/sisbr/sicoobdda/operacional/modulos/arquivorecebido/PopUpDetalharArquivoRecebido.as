package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.arquivorecebido
{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	import flash.ui.Keyboard;
	
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ArquivoRecebidoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.IdentificadorDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.LogDetRecebimentoArquivoDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpDetalharArquivoRecebido extends PopUpDetalharArquivoRecebidoView
	{
		public static const DETALHAMENTO_DO_REGISTRO:String = "Detalhamento Registro";
		public static const ALTERAR_SITUACAO_DO_REGISTRO:String = "Alterar Registro";
		
		private var _idLogReceArquivoDDA:Number;
		private var _arquivoRecebidoDto:ArquivoRecebidoDTO;

		private var _btnFechar:Botao;
		private var _btnDescriptografar:Botao = new Botao();
		private var _btnGravarDetalhes:Botao = new Botao();
		private var _btnProcessar:Botao = new Botao();		
		private var _msgSucesso:Alerta;
		private var _msgAlerta:Alerta;
		private static const OPT_TODOS:Number = 1;
		private static const OPT_PROCESSADOS:Number = 1;
		private static const OPT_NAO_PROCESSADOS:Number =0;
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpDetalharArquivoRecebido(idLogReceArquivoDDA:Number) {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this._idLogReceArquivoDDA = idLogReceArquivoDDA;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			removeEventListener(ProcurarEvent.PROCURAR, painelListaBanco.procuraSolicitada);
			
			addEventListener(KeyboardEvent.KEY_UP, tratarTeclaPressionada);
			
			painelListaBanco.funcaoCriacaoDto = instanciarDtoConsulta;
			btnDetalhar.addEventListener(MouseEvent.CLICK, popUpVisualizar);
			btnAlterar.addEventListener(MouseEvent.CLICK, popUpAlterar);
			listaLogDetRecArquivoDDA.grdDados.addEventListener(MouseEvent.CLICK, tratarBotoesAcao);
			listaLogDetRecArquivoDDA.grdDados.doubleClickEnabled = false;
			
			painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, pesquisaPagianada);
			painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			
			painelFiltro.optNaoProcessado.value = OPT_NAO_PROCESSADOS;
			painelFiltro.optProcessado.value = OPT_PROCESSADOS;
			painelFiltro.optTodos.value = OPT_TODOS;
			
			painelFiltro.optTodos.selected = true;
			
			iniciarBotoes();
			carregarCampos();
		}
		
		//--------------------------------------------------------------------------
		//  Inicializar botões.
		//--------------------------------------------------------------------------
		private function iniciarBotoes():void {
			
			//Trata botões de ação			
			btnDetalhar.enabled = false;
			btnAlterar.enabled = false;
			
			barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoExcluir = barraBotoesListaCadastro.exibirBotaoAlterar 
				= barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoIncluir = false;
			 
			
			barraBotoesListaCadastro.autoLayout = true;
			var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			_btnFechar = hBoxBarraBotoes.getChildByName('btnFechar') as Botao;
			_btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			
			_btnDescriptografar.label = "DESCRIPTOGRAFAR";
			_btnDescriptografar.width = 120;
			_btnDescriptografar.height = 22;
			_btnDescriptografar.enabled = false;
			_btnDescriptografar.addEventListener(MouseEvent.CLICK, descriptografar);
			_btnDescriptografar.addEventListener(KeyboardEvent.KEY_UP, tratarTeclaPressionada);
			
			_btnGravarDetalhes.label = "GRAVAR DETALHES";
			_btnGravarDetalhes.width = 120;
			_btnGravarDetalhes.height = 22;
			_btnGravarDetalhes.enabled = false;
			_btnGravarDetalhes.addEventListener(MouseEvent.CLICK, gravarDetalhes);
			_btnGravarDetalhes.addEventListener(KeyboardEvent.KEY_UP, tratarTeclaPressionada);
			
			_btnProcessar.label = "PROCESSAR";
			_btnProcessar.enabled = false;
			_btnProcessar.height = 22;
			_btnProcessar.addEventListener(MouseEvent.CLICK, processarArquivo);
			_btnProcessar.addEventListener(KeyboardEvent.KEY_UP, tratarTeclaPressionada);
			
			hBoxBarraBotoes.addChildAt(_btnDescriptografar, 0);
			hBoxBarraBotoes.addChildAt(_btnGravarDetalhes, 1);
			hBoxBarraBotoes.addChildAt(_btnProcessar, 2);
		}
		
		//--------------------------------------------------------------------------
		//  BOTAO DESCRIPTOGRAFAR PRESSIONADO
		//--------------------------------------------------------------------------
		protected function descriptografar(e:MouseEvent = null):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = _arquivoRecebidoDto;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_RECEBIDO, "descriptografarArquivo");
			servico.addEventListener(ResultEvent.RESULT, retornoDescriptografarArquivo);
			servico.descriptografarArquivo(dto);
		}
		
		private function retornoDescriptografarArquivo(e:ResultEvent):void {
			_arquivoRecebidoDto = e.result.dados.dto as ArquivoRecebidoDTO;
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG073, carregarDadosDetalharArquivoRecebido);
		}
		
		//--------------------------------------------------------------------------
		//  BOTAO GRAVAR DETALHES PRESSIONADO
		//--------------------------------------------------------------------------
		protected function gravarDetalhes(e:MouseEvent = null):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = _arquivoRecebidoDto;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_RECEBIDO, "gravarDetalheArquivo");
			servico.addEventListener(ResultEvent.RESULT, retornoGravarDetalheArquivo);
			servico.gravarDetalheArquivo(dto);
		}
		
		private function retornoGravarDetalheArquivo(e:ResultEvent):void {
			_arquivoRecebidoDto = e.result.dados.dto as ArquivoRecebidoDTO;
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG074, carregarDadosDetalharArquivoRecebido);
		}
		
		
		//--------------------------------------------------------------------------
		//  BOTAO PROCESSAR PRESSIONADO
		//--------------------------------------------------------------------------
		protected function processarArquivo(e:MouseEvent = null):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = _arquivoRecebidoDto;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_RECEBIDO, "processarArquivo");
			servico.addEventListener(ResultEvent.RESULT, retornoProcessarArquivo);
			servico.processarArquivo(dto);
		}
		
		private function retornoProcessarArquivo(e:ResultEvent):void {
			_arquivoRecebidoDto = e.result.dados.dto as ArquivoRecebidoDTO;
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG075, carregarDadosDetalharArquivoRecebido);
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos campos.
		//--------------------------------------------------------------------------
		private function carregarCampos():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var identificadorDTO:IdentificadorDTO = new IdentificadorDTO();
			identificadorDTO.identificadorGeral = this._idLogReceArquivoDDA;
			dto.dados.identificadorDTO = identificadorDTO;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_RECEBIDO, "obterArquivoRecebido");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterArquivoRecebido(dto);
		}
		
		private function retornoConsulta(resultEvent:ResultEvent):void {
			_arquivoRecebidoDto = resultEvent.result.dados.dto as ArquivoRecebidoDTO;
			carregarDadosDetalharArquivoRecebido();
		}
		
		private function carregarDadosDetalharArquivoRecebido(e:Event = null):void {
			this.painelFiltro.txtIdLogRecebimentoArquivoDDA.text = _arquivoRecebidoDto.idLogRecebimentoArqDDA.toString();
			this.painelFiltro.txtDescNomeArquivo.text = _arquivoRecebidoDto.descNomeArquivoRecebido;
			this.painelFiltro.txtTipoMensagem.text = _arquivoRecebidoDto.codTipoMensagem;
			this.painelFiltro.txtDataMovimento.text = FormataData.formataData(_arquivoRecebidoDto.dataMovimento.data);
			
			this.painelFiltro.txtDescTipoArquivo.text = _arquivoRecebidoDto.codTipoArquivo;
			this.painelFiltro.txtCodErroCip.text = _arquivoRecebidoDto.codTipoErroCip;
			this.painelFiltro.txtQtdRegistro.text = _arquivoRecebidoDto.qtdRegistros.toString();
			
			this.painelFiltro.txtDataHoraArquivo.text = !_arquivoRecebidoDto.dataMovimento ? "" : FormataData.formataDataHora(_arquivoRecebidoDto.dataHoraArquivo.data).toString();
			this.painelFiltro.txtSituacao.text = _arquivoRecebidoDto.descSituacaoProcessamentoArquivo;
			this.painelFiltro.txtIdLogEnvioArquivoDDA.text = _arquivoRecebidoDto.idLogEnvioArqDDA.toString();
			validaBotoes();
			pesquisaPagianada();
		}
		
		private function pesquisaPagianada(event:ProcurarEvent = null):void {
			this.painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
			this.painelListaBanco.procuraSolicitada(event);	
		}
		
		private function validaBotoes():void {
			_btnDescriptografar.enabled = _btnGravarDetalhes.enabled = _btnProcessar.enabled = false;
			if (_arquivoRecebidoDto) {
				switch(_arquivoRecebidoDto.codSituacaoProcessamentoArquivo) {
					case ConstantesComum.ARQUIVO_DISPONIVEL: {
						_btnDescriptografar.enabled = true;
						break;
					}
					case ConstantesComum.ARQUIVO_ABERTO: {
						_btnGravarDetalhes.enabled = true;
						break;
					}
					case ConstantesComum.REGISTROS_DETALHADOS: {
						_btnProcessar.enabled = true;
						break;
					}
					default: {
						break;
					}
				}
			}
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Visualização.
		//--------------------------------------------------------------------------		
		private function popUpVisualizar(event:Event):void {
			var logDetRecArquivoDDAVO:LogDetRecebimentoArquivoDDAVO = this.listaLogDetRecArquivoDDA.grdDados.selectedItem as LogDetRecebimentoArquivoDDAVO;
			var tela:PopUpDetalharRegistro = new PopUpDetalharRegistro(logDetRecArquivoDDAVO.id);
			JanelaCobranca.abrirJanela(this, tela, DETALHAMENTO_DO_REGISTRO)
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Alteração.
		//--------------------------------------------------------------------------		
		private function popUpAlterar(event:Event):void {
			var logDetRecArquivoDDAVO:LogDetRecebimentoArquivoDDAVO = this.listaLogDetRecArquivoDDA.grdDados.selectedItem as LogDetRecebimentoArquivoDDAVO;
			var tela:PopUpAlterarSituacaoRegistro = new PopUpAlterarSituacaoRegistro(logDetRecArquivoDDAVO.id, carregarCampos);
			JanelaCobranca.abrirJanela(this, tela, ALTERAR_SITUACAO_DO_REGISTRO)
		}
		
		//--------------------------------------------------------------------------
		//  Fechar.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
		private function tratarTeclaPressionada(e:KeyboardEvent):void {
			switch (e.keyCode) {
				case Keyboard.ESCAPE: {
					super.fecharJanela();
					break;
				}
				case Keyboard.F6: {
					if (_btnDescriptografar.enabled) {
						descriptografar();
					} else {
						exibirMensagemErro("O arquivo não pode ser descriptografado. Situação inválida.");
					}
					break;
				}
				case Keyboard.F7: {
					if (_btnGravarDetalhes.enabled) {
						gravarDetalhes();
					} else {
						exibirMensagemErro("Os registros não podem ser gravados. Situação inválida.");
					}
					break;
				}
				case  Keyboard.F8: {
					if (_btnProcessar.enabled) {
						processarArquivo();
					} else {
						exibirMensagemErro("O arquivo não pode ser processado. Situação inválida.");
					}
					break;
				}
				default: {
					break;
				}
			}
		}
		
		//--------------------------------------------------------------------------
		//  Tratar botões de ação.
		//--------------------------------------------------------------------------
		private function tratarBotoesAcao(event:MouseEvent):void {
			if (listaLogDetRecArquivoDDA.grdDados.selectedItem) {
				this.btnDetalhar.enabled = true;
				this.btnAlterar.enabled	= true;
			} else {
				this.btnDetalhar.enabled = false;
				this.btnAlterar.enabled	= false;
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
			var arquivoRecebidoDto:ArquivoRecebidoDTO = new ArquivoRecebidoDTO();
			
			if(painelFiltro.optTodos.selected){
				arquivoRecebidoDto.bolTodos = painelFiltro.optTodos.value;
			}else{
				arquivoRecebidoDto.bolTodos = false;
			}
			if(painelFiltro.optProcessado.selected){
				arquivoRecebidoDto.bolProcessado = painelFiltro.optProcessado.value;
			}else if(painelFiltro.optNaoProcessado.selected){
				arquivoRecebidoDto.bolProcessado = painelFiltro.optNaoProcessado.value;
			}
			
			if(StringUtil.trim(painelFiltro.txtIdLogDetRecebimentoArqDDA.text)){
				arquivoRecebidoDto.idLogDetRecebimentoArqDDA = Number(painelFiltro.txtIdLogDetRecebimentoArqDDA.text);
			}
			arquivoRecebidoDto.idLogRecebimentoArqDDA = this._idLogReceArquivoDDA;
			reqDto.dados.dto = arquivoRecebidoDto;
			
			dto.filtro = reqDto;
		}
		
		private function exibirMensagemErro(msg:String):void {
			_msgAlerta = MensagensComum.criarAlerta(msg, "Atenção", Alerta.ALERTA_OK);
			_msgAlerta.addEventListener(KeyboardEvent.KEY_UP, fecharAlerta);
			_msgAlerta.setFocus();
		}
		
		private function fecharAlerta(e:KeyboardEvent):void {
			if (e.keyCode == Keyboard.ESCAPE || e.keyCode == Keyboard.ENTER) {
				PopUpManager.removePopUp(_msgAlerta);
			}
		}
		
		//--------------------------------------------------------------------------
		//  Limpar a pesquisa.
		//--------------------------------------------------------------------------
		public function limparCampos(evt:Event):void {
			this.painelFiltro.txtIdLogDetRecebimentoArqDDA.text = "";
			this.painelFiltro.optTodos.selected = true;
			pesquisaPagianada();
		}
	}
}