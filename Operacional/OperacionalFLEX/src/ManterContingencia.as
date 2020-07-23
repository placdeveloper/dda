package 
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.contingencia.ContingenciaConstantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.contingencia.ContingenciaView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.contingencia.IContingencia;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO)
	public class ManterContingencia extends ContingenciaView implements IContingencia
	{
		private var servicoJava:ServicoJava;
		private var habilitada:Boolean;
		private var tipoContingencia:Number;
		private var reqDTO:RequisicaoReqDTO;
		
		public function ManterContingencia() 
		{
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(event:FlexEvent):void 
		{
			super.init(event);
			this.inciarEventos();
			this.iniciarComponentes();
		}
		
		private function inciarEventos():void 
		{
			btnConfirmar.addEventListener(MouseEvent.CLICK, this.isContingenciaHabilitada);
			btnImprimirRelatorio.addEventListener(MouseEvent.CLICK, this.gerarRelatorio);
			btnFechar.addEventListener(MouseEvent.CLICK, this.fechar);
		}
		
		private function fechar(event:MouseEvent):void 
		{
			pegaJanela().fecharJanela();
		}
		
		private function iniciarComponentes():void 
		{
			btnConfirmar.setStyle("icon", ConstantesComum.icoConfirmar);
			btnImprimirRelatorio.setStyle("icon", ConstantesComum.icoImprimir);
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			this.carregarComboStatusContingencia();
			this.listarHistoricoContingencias();
		}
		
		private function carregarComboStatusContingencia():void 
		{
			cmbStatusContingencia.dataProvider = ConstantesComum.HABILITAR_DESABILITAR;
		}
		
		public function incluirContingencia():void
		{
			if(this.validarOperacaoContingencia()) 
			{
				this.reqDTO = new RequisicaoReqDTO();
				this.reqDTO.dados.contingenciaHabilitada = cmbStatusContingencia.selectedItem.data;
				this.reqDTO.dados.tipoContingencia = this.tipoContingencia;
				this.servicoJava = Funcoes.obterServico(Constantes.SERVICO_CONTINGENCIA, ContingenciaConstantes.METODO_INCLUIR);
				this.servicoJava.addEventListener(ResultEvent.RESULT, this.resultadoIncluirContingencia); 
				this.servicoJava.incluirContingencia(reqDTO);
			}	
		}
		
		private function resultadoIncluirContingencia(event:ResultEvent):void 
		{
			MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG017, ContingenciaConstantes.CONTINGENCIA));
			cmbStatusContingencia.selectedIndex = 0;
			this.habilitada = event.result.dados.isContingenciaHabilitada;
			setLabelRotulo();
			rdTipoContingencia.selectedValue = null;
			rdTipoContingencia.selection = null;
			this.listarHistoricoContingencias();
		}
		
		public function listarHistoricoContingencias():void 
		{
			this.servicoJava = Funcoes.obterServico(Constantes.SERVICO_CONTINGENCIA, ContingenciaConstantes.METODO_LISTAR);
			this.servicoJava.addEventListener(ResultEvent.RESULT, this.resultadoListarHistoricoContingencias);
			this.servicoJava.listarHistoricoContingencias();
		}
		
		private function resultadoListarHistoricoContingencias(event:ResultEvent):void 
		{
			gridContingencia.dataProvider = null;
			gridContingencia.dataProvider = event.result.dados[ContingenciaConstantes.METODO_LISTAR];
			
			this.habilitada = event.result.dados.isContingenciaHabilitada;
			setLabelRotulo();
		}
		
		public function isContingenciaHabilitada(event:MouseEvent):void 
		{ 
			if(this.validarSelecaoCmbStatusContingencia() && validarSelecaoRadioContingencia()) 
			{
				this.servicoJava = Funcoes.obterServico(Constantes.SERVICO_CONTINGENCIA, ContingenciaConstantes.METODO_CONTINGENCIA_HABILITADA);
				this.servicoJava.addEventListener(ResultEvent.RESULT, this.resultadoIsContingenciaHabilitada);
				this.servicoJava.isContingenciaHabilitada();
			}
		}		
		
		private function resultadoIsContingenciaHabilitada(event:ResultEvent):void 
		{
			this.habilitada = event.result.dados.isContingenciaHabilitada;
			setLabelRotulo();
			incluirContingencia();
		}
		
		public function setLabelRotulo():void
		{
			if(this.habilitada) {
				rotulo.text = "Situação atual: Contingência Habilitada";
			} else {
				rotulo.text = "Situação atual: Desabilitada";
			}
		}
		
		public function gerarRelatorio(event:MouseEvent):void {
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_HISTORICO_CONTINGENCIAS, null, null, PreImpressao.FORMATO_PDF);
		}
		
		private function validarSelecaoCmbStatusContingencia():Boolean 
		{
			if(cmbStatusContingencia.selectedItem == null) 
			{
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, ContingenciaConstantes.STATUS));
				return false;
			}
			return true;
		}
		
		private function validarSelecaoRadioContingencia():Boolean 
		{
			if(rdTipoContingencia.selectedValue == null) 
			{
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, ContingenciaConstantes.CONTINGENCIA));
				return false;
			}
			
			if(rdTipoContingencia.selectedValue == 'CIP')
			{
				this.tipoContingencia = 1;
				
			} else {
				this.tipoContingencia = 2;	
			}
			
			return true;
		}
		
		private function validarOperacaoContingencia():Boolean 
		{
			if(cmbStatusContingencia.selectedItem.data == ContingenciaConstantes.HABILITAR && this.habilitada) 
			{
				MensagensComum.exibirMensagemAlerta(Mensagens.MSG068);
				return false;
			} 
			if(cmbStatusContingencia.selectedItem.data == ContingenciaConstantes.DESABILITAR && !this.habilitada) 
			{
				MensagensComum.exibirMensagemAlerta(Mensagens.MSG069);
				return false;
			}
			return true;
		}
		
	}
	
}