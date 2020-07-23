package {
	
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ConsultaParametroDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ParametroDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.CentralSingularVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.ParametroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.parametro.CadastroParametroInstituicao;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.parametro.ManterParametroInstituicaoView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class ManterParametroInstituicao extends ManterParametroInstituicaoView {
		
		private var idInstituicao:int;
		private var permiteAlterarTodos:Boolean;
		
		public function ManterParametroInstituicao() {
			super();
		}
		
		protected override function init(evento:FlexEvent): void {
			super.init(evento);
			
			pnlFiltro.cvInstituicao.exibirPorAgenciaLogada = true;
			pnlFiltro.cvInstituicao.carregarCombos();
			
			// Linha que define as linhas com tamanho variavel
			tblValores.grdDados.variableRowHeight = true;
			tblValores.grdDados.addEventListener(Event.RENDER, tratarBotoesGrid);
			tblValores.addEventListener(KeyboardEvent.KEY_UP, tratarBotoesGrid);
			
			pnlFiltro.cvInstituicao.cmbUnidade.visible = pnlFiltro.cvInstituicao.cmbUnidade.includeInLayout = pnlFiltro.cvInstituicao.lblUnidade.visible = pnlFiltro.cvInstituicao.lblUnidade.includeInLayout =  false;
			
			
			initBotoes();
			
			// Define a funcao do componente como 'null'
			funcaoDuploClique = null;
			
			obterDados();
		}
		
		private function tratarBotoesGrid(event:Event):void {
			btnAlterar.enabled = tblValores.grdDados != null && tblValores.grdDados.selectedItem != null
				&& (idInstituicao == ConstantesComum.ID_SICOOB || idInstituicao == ConstantesComum.ID_BANCOOB
					|| (tblValores.grdDados.selectedItem as ConsultaParametroDTO).bolPermiteAlteracaoPeloUsuario);
		}
		
		private function obterDados():void {
			var metodo:String = "obterDadosValorParametroPesquisa";
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_PARAMETRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoObterDadosPesquisa);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.obterDadosValorParametroPesquisa();
		}
		
		private function resultadoObterDadosPesquisa(event:ResultEvent):void {
			var parametroDTO:ParametroDTO = event.result.dados.parametroDTO as ParametroDTO;
			
			pnlFiltro.cmbParametro.dataProvider = parametroDTO.listaParametro;
			
			idInstituicao = parametroDTO.idInstituicao;
			permiteAlterarTodos = parametroDTO.permiteAlterarTodos;
		}
		
		public function pesquisaRealizada(event:ResultEvent):void {
			pnlLista.pesquisaRealizada(event);
			var lista:ArrayCollection = this.tblValores.grdDados.dataProvider as ArrayCollection;
		}
		
		/** 
		 * Define estado inicial dos botões do formulario
		 */
		private function initBotoes():void {
			barraBotoesListaCadastro.exibirBotaoFechar = true;
			barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoExcluir = false;	
			
			btnAlterar.enabled = false;
			
			btnAlterar.addEventListener(MouseEvent.CLICK, alterarParametro);
			
			pnlLista.funcaoCriacaoDto = instanciarDtoConsulta;
			pnlLista.funcaoConfiguracaoDto = configurarDtoConsulta;
			this.pnlLista.callback = pesquisaRealizada;
		}
		
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		private function alterarParametro(evt:Event):void {
			var consultaParametroDTO:ConsultaParametroDTO = tblValores.grdDados.selectedItem as ConsultaParametroDTO;
			
			var tela:CadastroParametroInstituicao = new CadastroParametroInstituicao(consultaParametroDTO, permiteAlterarTodos || consultaParametroDTO.bolVisivelFuncionalidadeAlteracao,
				pnlFiltro.cvInstituicao.cmbSingular.dataProvider as ArrayCollection);
			
			JanelaCobranca.abrirJanela(this, tela, "MANTER PARÂMETRO - INSTITUIÇÃO");
		}
		
		private function configurarDtoConsulta(dto:ConsultaDto):void {
			var reqDto: RequisicaoReqDTO = new RequisicaoReqDTO();
			var consultaParametroDTO:ConsultaParametroDTO = new ConsultaParametroDTO;
			
			if (pnlFiltro.cvInstituicao.cmbSingular.selectedItem){
				consultaParametroDTO.idInstituicao = (pnlFiltro.cvInstituicao.cmbSingular.selectedItem as CentralSingularVO).idInstituicao; 
			}
			
			if (pnlFiltro.cmbParametro.selectedItem) {
				consultaParametroDTO.idParametro = (pnlFiltro.cmbParametro.selectedItem as ParametroVO).id;
			}
			
			reqDto.dados.dto = consultaParametroDTO;
			dto.filtro = reqDto;
		}
		
	}	
}