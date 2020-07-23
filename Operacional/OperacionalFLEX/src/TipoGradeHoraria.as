package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	import flash.utils.Timer;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.TipoGradeHorariaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipogradehoraria.PopUpEditar;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipogradehoraria.PopUpIncluir;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipogradehoraria.TipoGradeHorariaView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;

	registerClassAlias("RegistroVO", RegistroVO);
	public class TipoGradeHoraria extends TipoGradeHorariaView
	{
		
		private var tmp:Timer;
		private var telaWidget:Object;
		private var destinoVO:DestinoVO;
		
		public static const TITULO_INCLUIR:String = "Incluir Tipo Grade Horária";
		public static const TITULO_ALTERAR:String = "Alterar Tipo Grade Horária";
		public static const TITULO_VISUALIZAR:String = "Visualizar Tipo Grade Horária";
		
		public function TipoGradeHoraria()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			pesquisar(Constantes.OPT_TODOS, null);	
			iniciarComponentes();
		}	
		
		private function iniciarComponentes():void {
			// Options Filtro
			this.optGradeDDA.value = Constantes.OPT_DDA;
			this.optGradeSICOOB.value = Constantes.OPT_SICOOB;
			this.optGradeTodos.value = Constantes.OPT_TODOS;
			this.optGradeTodos.selected = true;
			this.optGradeSICOOB.addEventListener(MouseEvent.CLICK, tratarEventoSubTipos);
			this.gridTipoGradeHoraria.addEventListener(MouseEvent.CLICK, tratarBotoesAcao)
			//Botões Crud
			this.btnDetalhar.addEventListener(MouseEvent.CLICK, popUpDetalhar);
			this.btnAlterar.addEventListener(MouseEvent.CLICK, popUpEditar);
			this.btnRemover.addEventListener(MouseEvent.CLICK, remover);
			this.btFechar.setStyle("icon", ConstantesComum.icoFechar);
			desabilitaBotoesAcao();

			this.btIncluir.addEventListener(MouseEvent.CLICK, popUpIncluir);
			this.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.optGradeTodos.addEventListener(MouseEvent.CLICK, tratarEventoSubTipos);
			this.optGradeDDA.addEventListener(MouseEvent.CLICK, tratarEventoSubTipos);
			this.optGradeSICOOB.addEventListener(MouseEvent.CLICK, tratarEventoSubTipos);
			
		}
		
		private function tratarEventoSubTipos(event:MouseEvent):void {
			switch(event.currentTarget.value)
			{
				case Constantes.OPT_TODOS:
				{
					pesquisar(Constantes.OPT_TODOS, null);
					break;
				}
				case Constantes.OPT_SICOOB:
				{
					pesquisar(Constantes.OPT_SICOOB, null);
					break;
				}
				case Constantes.OPT_DDA:
				{
					pesquisar(Constantes.OPT_DDA, null);
					break;
				}
			}
		}
		//--------------------------------------------------------------------------
		//  Abre a Janela de Cadastro.
		//--------------------------------------------------------------------------
		private function popUpIncluir(event:MouseEvent):void {
			var tela:PopUpIncluir = new PopUpIncluir(refazerPequisa);
				JanelaCobranca.abrirJanela(this, tela, TITULO_INCLUIR);
		}

		//--------------------------------------------------------------------------
		//  Abre a Janela de Edição.
		//--------------------------------------------------------------------------
		private function popUpEditar(event:MouseEvent):void {
			var dto:TipoGradeHorariaDTO =  gridTipoGradeHoraria.selectedItem as TipoGradeHorariaDTO;
			var tela:PopUpEditar = new PopUpEditar(dto.codTipoGradeHoraria, refazerPequisa, false);
			JanelaCobranca.abrirJanela(this, tela, TITULO_ALTERAR);
			pesquisar(Constantes.OPT_TODOS);	
		}
		private function popUpDetalhar(event:MouseEvent):void {
			var dto:TipoGradeHorariaDTO =  gridTipoGradeHoraria.selectedItem as TipoGradeHorariaDTO;
			var tela:PopUpEditar = new PopUpEditar(dto.codTipoGradeHoraria, refazerPequisa, true);
			JanelaCobranca.abrirJanela(this, tela, TITULO_VISUALIZAR);
			pesquisar(Constantes.OPT_TODOS);	
		}
		
		private function remover(event:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao(Mensagens.MSG049, null, removerTipoGradeHoraria);
		}
		
		private function removerTipoGradeHoraria(event:Event):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var tipoGradeHorariadto:TipoGradeHorariaDTO =  gridTipoGradeHoraria.selectedItem as TipoGradeHorariaDTO;
			dto.dados.codTipoGradeHoraria = tipoGradeHorariadto.codTipoGradeHoraria;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_GRADE_HORARIA, "apagarTipoGradeHoraria");
			servico.addEventListener(ResultEvent.RESULT, resultadoExclusao);
			servico.apagarTipoGradeHoraria(dto);
		}
		
		private function refazerPequisa():void {
			this.grpSubTipos.selectedValue = 0;
			this.pesquisar(Constantes.OPT_TODOS)
		}
		private function resultadoExclusao(event:ResultEvent):void {
			refazerPequisa();
		}
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------		

		private function pesquisar(idSubTipo:int, evt:Event = null):void{
			desabilitaBotoesAcao();
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idSubTipo = idSubTipo;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_GRADE_HORARIA, "listarTipoGradeHorariaPorCodigoSubtipo");
			servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			servico.listarTipoGradeHorariaPorCodigoSubtipo(dto);
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de callback.
		//--------------------------------------------------------------------------
		public function retornoPesquisar(event:ResultEvent):void{
			gridTipoGradeHoraria.dataProvider = event.result.dados["lista"];
		}
		
		private function tratarBotoesAcao(event:MouseEvent):void {
			if(gridTipoGradeHoraria.selectedItem){
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
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
	}
}