package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.componentes.input.InputCPFCNPJ;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.BancoCafDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.BeneficiariosAlertaFiltroDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.beneficiariosalerta.BeneficiariosAlertaConstantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.beneficiariosalerta.BeneficiariosAlertaView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.beneficiariosalerta.IBeneficiariosAlerta;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO)
	public class BeneficiariosAlerta extends BeneficiariosAlertaView implements IBeneficiariosAlerta
	{
		private var servicoJava:ServicoJava;
		private var reqDTO:RequisicaoReqDTO;
		private var parDTO:ParametroDTO;
		private var relUtil:RelatorioUtil = RelatorioUtil.create();
		private var beneficiariosAlertaFiltro:BeneficiariosAlertaFiltroDTO = new BeneficiariosAlertaFiltroDTO();
		private var btnImprimir:Botao = new Botao();
		
		public function BeneficiariosAlerta()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(event:FlexEvent):void 
		{
			super.init(event);
			this.inciarEventos();
			this.iniciarComponentes();
		}
		
		protected function inciarEventos():void
		{
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaPainel.procuraSolicitada);
			painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, this.pesquisarBeneficiarios);
			painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, this.limparFormulario);
			btnImprimir.addEventListener(MouseEvent.CLICK, this.gerarRelatorioBeneficiariosEmAlerta);
			painelFiltro.cmbTipoBeneficiario.addEventListener(ListEvent.CHANGE, aplicarMascaraCPFCNPJ);
			this.painelListaPainel.callback = this.tratarCallbackPesquisa;
			funcaoDuploClique = null;
			painelFiltro.textoCPFCNPJ.enabled = false;
			inciarEventosCmbsLimparGrid();
		}
		
		protected function iniciarComponentes():void
		{
			this.painelListaPainel.funcaoCriacaoDto = instanciarDtoConsulta;
			this.carregarCmbBancosOriginadores();
			this.carregarCmbStatusAlerta();
			this.carregarCmbTipoPessoa();
			this.carregarCmbTipoBeneficiario();
			configurarBotaoImprimir();
			configurarBotoesListaCadastro();
		}
		
		public function pesquisarBeneficiarios(event:ProcurarEvent):void
		{
			this.painelListaPainel.funcaoConfiguracaoDto = configurarDtoConsulta;
			this.painelListaPainel.procuraSolicitada(event);
		}
		
		public function gerarRelatorioBeneficiariosEmAlerta(event:MouseEvent):void {
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_BENEFICIARIOS_ALERTA, beneficiariosAlertaFiltro, null, PreImpressao.FORMATO_PDF);
		}
		
		private function carregarCmbBancosOriginadores():void
		{
			this.servicoJava = Funcoes.obterServico(Constantes.SERVICO_BENEFICIARIOSALERTA, BeneficiariosAlertaConstantes.METODO_LISTAR_BANCOS_ORIGINADORES);
			this.servicoJava.addEventListener(ResultEvent.RESULT, this.resultadoListarBancosOriginadores);
			this.servicoJava.listarBancosOriginadores();
		}
		
		private function carregarCmbStatusAlerta():void
		{
			painelFiltro.cmbStatusAlerta.dataProvider = BeneficiariosAlertaConstantes.STATUS_ALERTA;
		}
		
		private function carregarCmbTipoPessoa():void
		{
			painelFiltro.cmbTipoPessoa.dataProvider = BeneficiariosAlertaConstantes.TIPO_PESSOA;
		}
		
		private function carregarCmbTipoBeneficiario():void
		{
			painelFiltro.cmbTipoBeneficiario.dataProvider = ConstantesComum.CPF_CNPJ;
		}
		
		private function resultadoListarBancosOriginadores(event:ResultEvent):void
		{
			painelFiltro.cmbBancoOriginadorAlerta.dataProvider = null;
			painelFiltro.cmbBancoOriginadorAlerta.dataProvider = event.result.dados[BeneficiariosAlertaConstantes.LISTA_BANCOS_ORIGINADORES];
		}
		
		private function configurarDtoConsulta(dto:ConsultaDto):void 
		{	
			this.reqDTO = new RequisicaoReqDTO();
			beneficiariosAlertaFiltro = new BeneficiariosAlertaFiltroDTO(
				getBancoOriginadorAlerta(), 
				getTipoPessoa(), 
				getStatusAlerta(),
				getCPFCNPJ(),
				painelFiltro.checkBeneficiarioSicoob.selected);
			this.reqDTO.dados.pesquisaBeneficiariosFiltro = beneficiariosAlertaFiltro;
			dto.filtro = this.reqDTO;
		}
		
		private function inciarEventosCmbsLimparGrid():void
		{
			painelFiltro.cmbBancoOriginadorAlerta.addEventListener(ListEvent.CHANGE, limparGridFormulario);
			painelFiltro.cmbTipoPessoa.addEventListener(ListEvent.CHANGE, limparGridFormulario);
			painelFiltro.cmbStatusAlerta.addEventListener(ListEvent.CHANGE, limparGridFormulario);
			painelFiltro.cmbTipoBeneficiario.addEventListener(ListEvent.CHANGE, limparGridFormulario);
			painelFiltro.checkBeneficiarioSicoob.addEventListener(Event.CHANGE, limparGridFormulario);
		}
		
		private function aplicarMascaraCPFCNPJ(event:ListEvent):void
		{
			painelFiltro.textoCPFCNPJ.enabled = true;
			painelFiltro.textoCPFCNPJ.tipoCampo = null;
			
			if(painelFiltro.cmbTipoBeneficiario.selectedItem != null && painelFiltro.cmbTipoBeneficiario.selectedItem.data != undefined) {
				if (painelFiltro.cmbTipoBeneficiario.selectedItem.data == ConstantesComum.CPF) {
					painelFiltro.textoCPFCNPJ.tipoCampo = InputCPFCNPJ.TIPO_CPF;
				}else {
					painelFiltro.textoCPFCNPJ.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
				}
			} else {
				painelFiltro.textoCPFCNPJ.enabled = false;
			}
		}
		
		private function configurarBotaoImprimir():void
		{
			var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName(BeneficiariosAlertaConstantes.LABEL_BOTOES) as HBox;
			btnImprimir.label = btnImprimir.toolTip = BeneficiariosAlertaConstantes.LABEL_IMPRIMIR;
			btnImprimir.setStyle(BeneficiariosAlertaConstantes.LABEL_ICON, ConstantesComum.icoImprimir);
			btnImprimir.enabled = false;
			hBoxBarraBotoes.addChildAt(btnImprimir, 2);
		}
		
		private function configurarBotoesListaCadastro():void
		{
			barraBotoesListaCadastro.exibirBotaoVisualizar = false;
			barraBotoesListaCadastro.exibirBotaoAlterar = false;
			barraBotoesListaCadastro.exibirBotaoIncluir = false;
			barraBotoesListaCadastro.exibirBotaoAjuda = false;
			barraBotoesListaCadastro.exibirBotaoExcluir = false;
		}
		
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		private function getBancoOriginadorAlerta():BancoCafDTO 
		{
			return painelFiltro.cmbBancoOriginadorAlerta.selectedItem != null ? 
				painelFiltro.cmbBancoOriginadorAlerta.selectedItem as BancoCafDTO : null;
		}
		
		private function getTipoPessoa():String 
		{
			return painelFiltro.cmbTipoPessoa.selectedItem != null ? 
				painelFiltro.cmbTipoPessoa.selectedItem.data : null;
		}
		
		private function getStatusAlerta():String
		{
			return painelFiltro.cmbStatusAlerta.selectedItem != null ? 
				painelFiltro.cmbStatusAlerta.selectedItem.data : null;	
		}
		private function getCPFCNPJ():String 
		{
			return painelFiltro.textoCPFCNPJ.actualText.length > 0 ? 
				painelFiltro.textoCPFCNPJ.actualText : null;	
		}
		
		private function limparFormulario(event:MouseEvent):void
		{
			painelFiltro.cmbBancoOriginadorAlerta.selectedItem = null;
			painelFiltro.cmbTipoPessoa.selectedItem = null;
			painelFiltro.cmbStatusAlerta.selectedItem = null;
			painelFiltro.cmbTipoBeneficiario.selectedItem = null;
			painelFiltro.textoCPFCNPJ.tipoCampo = null;
			painelFiltro.checkBeneficiarioSicoob.selected = false;
			painelLista.tabelaPaginada.grdDados.dataProvider = null;
			
			tratarHabilitarBtnImprimir();
		}
		
		private function limparGridFormulario(event:Event):void
		{
			painelLista.tabelaPaginada.grdDados.dataProvider = null;
			tratarHabilitarBtnImprimir();
		}
		
		private function tratarCallbackPesquisa(evt:ResultEvent):void {
			painelListaPainel.pesquisaRealizada(evt);
			tratarHabilitarBtnImprimir();
		}
		
		private function tratarHabilitarBtnImprimir():void {
			btnImprimir.enabled = (painelLista.tabelaPaginada.grdDados.dataProvider as ArrayCollection).length > 0;
		}
		
	}
	
}