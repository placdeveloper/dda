package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.boleto.HistoricoMensagem106View;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class HistoricoMensagem106 extends HistoricoMensagem106View
	{
		private static const TIPO_CPF:int = 0;
		private static const TIPO_CNPJ:int = 1;
		
		private var isCodigoBarras:Boolean = true;
		private var _btnImprimir:Botao = new Botao();
		private var relUtil:RelatorioUtil = RelatorioUtil.create();
		
		public function HistoricoMensagem106()
		{
			super();
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			
			iniciarCampos();
			
			definirBotoes();
			
			funcaoDuploClique = null;
			
			validarTabulacao();
			
		}
		
		private function iniciarCampos():void {
			barraBotoesListaCadastro.exibirBotaoFechar = true;
			barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoExcluir = false;
			
			var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			
			_btnImprimir.label = "Imprimir";
			_btnImprimir.toolTip = "Imprimir Relatorio Boleto";
			_btnImprimir.height = 22;
			hBoxBarraBotoes.addChildAt(_btnImprimir, 1);
			var btnFechar:Botao = hBoxBarraBotoes.getChildByName("btnFechar") as Botao;
			_btnImprimir.setStyle("icon", ConstantesComum.icoImprimir);
			if (btnFechar) {
				// Define o ícone
				btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			}
			
			painelListaHistoricoMensagem106.funcaoCriacaoDto = instanciarDtoConsulta;
			painelListaHistoricoMensagem106.funcaoConfiguracaoDto = configurarDtoConsulta;
			
			painelListaHistoricoMensagem106.callback = tratarCallbackPesquisa;
		}
		
		private function tratarCallbackPesquisa(evt:ResultEvent):void {
			var lista:ArrayCollection = evt.result.dados.lista as ArrayCollection;
			if (!lista || !lista.length) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG018, "resultado"));
			}
			painelListaHistoricoMensagem106.pesquisaRealizada(evt);
		}
		
		private function validarTabulacao():void {
			var index:int = 1;
		}
		
		/**
		 * Define os botões.
		 */
		private function definirBotoes():void {
			painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCamposConsulta);
			_btnImprimir.addEventListener(MouseEvent.CLICK, imprimir);
			
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaHistoricoMensagem106.procuraSolicitada);
			painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarProcurar);
			
		}
		
		private function validarProcurar(evt:ProcurarEvent):void{
			if(painelFiltro.inputNumCodBarras.txtNumCodBarras == ""){
				MensagensComum.exibirMensagemErro("É obrigatório o preenchimento do campo Código de Barras/Linha Digitável.", painelFiltro.inputNumCodBarras);
			}else if (painelFiltro.inputNumCodBarras.validar()) {
				painelListaHistoricoMensagem106.procuraSolicitada(evt);
			} 
		}
		
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		/**
		 * Configura o DTO para executar a pesquisa.
		 */
		private function configurarDtoConsulta(dto:ConsultaDto):void {	
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDto.dados.numCodigoBarra = painelFiltro.inputNumCodBarras.txtNumCodBarras;
			dto.filtro = reqDto;
		}
		/**
		 * Imprimir relatório
		 */
		private function imprimir(evt:Event):void {
			if (grdHistoricoMensagem.grdDados.selectedItem) {	
				RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_HISTORICO_MENSAGEM_106, null, grdHistoricoMensagem.grdDados.dataProvider as (ArrayCollection), PreImpressao.FORMATO_PDF);
			}
		}
		
		/**
		 * Limpa os campos da consulta e a tabela.
		 */
		private function limparCamposConsulta(evt:Event):void {
			painelFiltro.inputNumCodBarras.limpar();
			
			grdHistoricoMensagem.barraPaginacao.totalPaginas = grdHistoricoMensagem.barraPaginacao.pagina = 0;
			grdHistoricoMensagem.grdDados.dataProvider = null;
		}
		
	}
}