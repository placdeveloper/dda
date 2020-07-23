package {
	
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ParametroDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.ParametroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.parametro.CadastroParametro;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.parametro.ManterParametroView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class ManterParametro extends ManterParametroView {
		
		private var btnIncluir:Botao = new Botao();
		private var permiteAlterarTodos:Boolean;
		private var atualizarPesquisa:Boolean = false;
		
		public function ManterParametro() {
			super();
		}
	
		protected override function init(evento:FlexEvent): void {
			super.init(evento);
			
			// Linha que define as linhas com tamanho variavel
			tblValores.grdDados.variableRowHeight = true;
			tblValores.grdDados.addEventListener(Event.RENDER, tratarBotoesGrid);
			tblValores.addEventListener(KeyboardEvent.KEY_UP, tratarBotoesGrid);
			
			initBotoes();
			
			// Define a funcao do componente como 'null'
			funcaoDuploClique = null;
			
			obterDados();
		}
		
		private function tratarBotoesGrid(event:Event):void {
			btnAlterar.enabled = tblValores.grdDados != null && tblValores.grdDados.selectedItem != null;
		}
		
		public function obterDados(atualizarPesquisa:Boolean = false):void {
			this.atualizarPesquisa = atualizarPesquisa;
			
			var metodo:String = "obterDadosPesquisa";
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_PARAMETRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoObterDadosPesquisa);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.obterDadosPesquisa();
		}
		
		private function resultadoObterDadosPesquisa(event:ResultEvent):void {
			var parametroDTO:ParametroDTO = event.result.dados.parametroDTO as ParametroDTO;
			
			pnlFiltro.cmbParametro.dataProvider = parametroDTO.listaParametro;
			
			if (pnlFiltro.cmbParametro.dropdown) {
				pnlFiltro.cmbParametro.dropdown.dataProvider = pnlFiltro.cmbParametro.dataProvider;
			}
			
			permiteAlterarTodos = parametroDTO.permiteAlterarTodos;
			
			btnIncluir.enabled = pnlFiltro.btnProcurar.enabled = true;
			
			// Quando a tela de parâmetro é fechada será refeita a pesquisa
			if (atualizarPesquisa) {
				atualizarPesquisa = false;
				pnlLista.refazerPesquisa();
			}
		}
		
		public function pesquisaRealizada(event : ResultEvent) : void {
			pnlLista.pesquisaRealizada(event);
			var lista:ArrayCollection = this.tblValores.grdDados.dataProvider as ArrayCollection;
		}
		
		/** 
		 * Define estado inicial dos botões do formulario
		 */
		private function initBotoes():void {
			barraBotoesListaCadastro.exibirBotaoFechar = true;
			barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoExcluir = false;	
			
			var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			
			btnIncluir.label = btnIncluir.toolTip = "Incluir Parâmetro";
			btnIncluir.height = 22;
			btnIncluir.enabled = true;
			
			btnAlterar.enabled = pnlFiltro.btnProcurar.enabled = btnIncluir.enabled = false;
			
			hBoxBarraBotoes.addChildAt(btnIncluir, 1);
			
			btnIncluir.addEventListener(MouseEvent.CLICK, cadastroParametro);
			btnAlterar.addEventListener(MouseEvent.CLICK, alterarParametro);
			
			pnlLista.funcaoCriacaoDto = instanciarDtoConsulta;
			pnlLista.funcaoConfiguracaoDto = configurarDtoConsulta;
			this.pnlLista.callback = pesquisaRealizada;
		}
		
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		private function cadastroParametro(evt:Event):void {
			var tela:CadastroParametro = new CadastroParametro(null, FormularioCadastro.MODO_INCLUSAO);
			
			JanelaCobranca.abrirJanela(this, tela, "CADASTRO DE PARÂMETRO");
		}
		
		private function alterarParametro(evt:Event):void {
			var parametroVO:ParametroVO = tblValores.grdDados.selectedItem as ParametroVO;
			
			var tela:CadastroParametro = new CadastroParametro(parametroVO, FormularioCadastro.MODO_EDICAO, permiteAlterarTodos || parametroVO.bolVisivelFuncionalidadeAlteracao);
			
			JanelaCobranca.abrirJanela(this, tela, "ALTERAÇÃO DE PARÂMETRO");
		}
		
		private function configurarDtoConsulta(dto:ConsultaDto):void {
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			if (pnlFiltro.cmbParametro.selectedItem != null){
				reqDto.dados["vo"] = pnlFiltro.cmbParametro.selectedItem as ParametroVO;
			}
			
			dto.filtro = reqDto;
		}
		
	}	
}