package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.containers.HBox;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.containers.EventoJanela;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ConsultaTipoErroCipDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipoerrocip.CadastroTipoErroCIP;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipoerrocip.ManterTipoErroCIPView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class ManterTipoErroCIP extends ManterTipoErroCIPView
	{
		
		private var _bolRefazerPesquisa:Boolean = true;
		
		public function ManterTipoErroCIP() {
			super();
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			initBotoes();
			painelListaTipoErro.funcaoCriacaoDto = instanciarDtoConsulta;
			painelListaTipoErro.funcaoConfiguracaoDto = configurarDtoConsulta;
			listaTipoErro.grdDados.addEventListener(Event.RENDER, tratarBotoes);
			listaTipoErro.grdDados.percentHeight = 90;
		}
		
		private function tratarBotoes(e:Event):void {
			if (listaTipoErro.grdDados.selectedItem) {
				btnDetalhar.enabled = true;
				btnAlterar.enabled = true;
				btnExcluir.enabled = true;
			} else {
				btnDetalhar.enabled = false;
				btnAlterar.enabled = false;
				btnExcluir.enabled = false;
			}
		}
		
		private function initBotoes():void {
			barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoExcluir = barraBotoesListaCadastro.exibirBotaoAjuda = false;
			var hbBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			if (hbBotoes) {
				var btnFechar:Botao = hbBotoes.getChildByName("btnFechar") as Botao;
				
				if (btnFechar) {
					btnFechar.setStyle("icon", ConstantesComum.icoFechar);
				}
			}
			btnDetalhar.addEventListener(MouseEvent.CLICK, detalharTipoErro);
			btnAlterar.addEventListener(MouseEvent.CLICK, alterarTipoErro);
			btnExcluir.addEventListener(MouseEvent.CLICK, confirmaExcluirTipoErro);
			painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparConsulta);
		}
		
		private function limparConsulta(e:MouseEvent):void {
			listaTipoErro.limparConteudo();
			listaTipoErro.barraPaginacao.totalPaginas = listaTipoErro.barraPaginacao.pagina = 0;
			listaTipoErro.paginaAtual = 1;
			painelFiltro.checkErroTratamentoAuto.selected = false;
			_bolRefazerPesquisa = false;
		}
		
		protected override function botaoIncluirPressionado():void {
			var tela:CadastroTipoErroCIP = new CadastroTipoErroCIP();
			abrirJanelaCadastro("Incluir", tela);
		}

		protected function detalharTipoErro(e:MouseEvent):void {
			var tela:CadastroTipoErroCIP = new CadastroTipoErroCIP(listaTipoErro.grdDados.selectedItem.codTipoErro, CadastroTipoErroCIP.MODO_VISUALIZACAO);
			abrirJanelaCadastro("Visualizar", tela);
		}
		
		protected function alterarTipoErro(e:MouseEvent):void {
			var tela:CadastroTipoErroCIP = new CadastroTipoErroCIP(listaTipoErro.grdDados.selectedItem.codTipoErro, CadastroTipoErroCIP.MODO_EDICAO);
			abrirJanelaCadastro("Alterar", tela);
		}
		
		protected function confirmaExcluirTipoErro(e:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao(MensagensComum.formatar(MensagensComum.MSG007, "Tipo Erro"), null, excluirTipoErro);
		}
		
		protected function excluirTipoErro(e:Event):void {
			var metodo:String = "excluirTipoErro";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["codTipoErro"] = listaTipoErro.grdDados.selectedItem.codTipoErro;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_ERRO_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoExcluiTipoErro);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.excluirTipoErro(dto);
		}
		
		protected function retornoExcluiTipoErro(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG027, "Tipo Erro"));
			refazerUltimaPesqusia();
		}
		
		private function abrirJanelaCadastro(titulo:String, tela:CadastroTipoErroCIP):void {
			var janela:Janela = new Janela();
			janela.icone = ConstantesComum.ICONE_JANELA;
			janela.title = titulo + " Tipo Erro CIP";
			janela.addChild(tela); 
			janela.janelaPai = this;
			janela.addEventListener(CloseEvent.CLOSE, refazerUltimaPesqusia);
			janela.addEventListener(EventoJanela.FECHAR_JANELA, refazerUltimaPesqusia);
			janela.abrir(this, true);
		}
		
		private function refazerUltimaPesqusia(e:Event = null):void {
			if (_bolRefazerPesquisa) {
				refazerPesquisa();
			} else {
				listaTipoErro.grdDados.dataProvider = null;
			}
		}
		
		/**
		 * Configura o DTO para executar a pesquisa.
		 */
		private function instanciarDtoConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		/**
		 * Configura o DTO para executar a pesquisa.
		 */
		private function configurarDtoConsulta(dto: ConsultaDto): void{	
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var consulta:ConsultaTipoErroCipDTO = new ConsultaTipoErroCipDTO();
			
			consulta.bolErroTratamentoAutomatizado = painelFiltro.checkErroTratamentoAuto.selected;
			if (painelFiltro.txtCodigo.text.length > 0) {
				consulta.codTipoErro = painelFiltro.txtCodigo.text;
			}
			if (painelFiltro.txtDescricao.text.length > 0) {
				consulta.descTipoErro = painelFiltro.txtDescricao.text;
			}
			
			reqDto.dados.dto = consulta;
			dto.filtro = reqDto;
			_bolRefazerPesquisa = true;
		}
		
	}
}