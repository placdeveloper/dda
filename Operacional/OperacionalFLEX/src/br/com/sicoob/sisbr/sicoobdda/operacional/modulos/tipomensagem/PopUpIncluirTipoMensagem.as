package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipomensagem
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.TipoMensagemDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.CategoriaMensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoGradeHorariaVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoMensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpIncluirTipoMensagem extends PopUpIncluirTipoMensagemView
	{
		private var dataReferenciaTemp:IDateTime;
		private var itemTipoMensagemVO:TipoMensagemDDAVO;
		private var _atualizaPesquisa:Function;
		private var _visualizacao:Boolean;
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpIncluirTipoMensagem(funcaoRetorno:Function) {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this._atualizaPesquisa = funcaoRetorno;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			var janela:Janela =super.pegaJanela();
			janela.barraBotoes.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.descTipoMensagem.addEventListener(Event.CHANGE, setToolTip);
			this.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btConfirmar.addEventListener(MouseEvent.CLICK, validarAlterarTipoMensagem);
			btLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			if(this._visualizacao){
				
			}
			iniciarComponentes();
			carregarCampos();
		}
		
		//--------------------------------------------------------------------------
		//  Setar icone no botao fechar.
		//--------------------------------------------------------------------------		
		private function iniciarComponentes():void {
			this.descTipoMensagem.restrict = ConstantesComum.REGEX_CAMPO_DESCRICAO;
			this.btFechar.setStyle("icon", ConstantesComum.icoFechar);
			
		}
		
		//--------------------------------------------------------------------------
		//  Carregar campos.
		//--------------------------------------------------------------------------		
		private function carregarCampos():void {
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_MENSAGEM, "carregarListasTipoMensagem");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.carregarListasTipoMensagem();
		}
		
		//--------------------------------------------------------------------------
		//  Seta tooltip.
		//--------------------------------------------------------------------------		
		private function setToolTip(event:Event):void {
			this.descTipoMensagem.toolTip = this.descTipoMensagem.text; 
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de callback.
		//--------------------------------------------------------------------------
		private function retornoConsulta(event:ResultEvent):void{
			var tipoMensagemDto:TipoMensagemDTO = event.result.dados["lista"] as TipoMensagemDTO;
			//Carrega combos
			this.codTipoMensagem.text = tipoMensagemDto.codTipoMensagem;
			this.descTipoMensagem.text = tipoMensagemDto.descTipoMensagem;
			this.numPrioridadeEnvio.text  = new String(tipoMensagemDto.numPrioridadeEnvio);

			cmbArquivoCorrespondente.dataProvider = tipoMensagemDto.listaArquivoCorrespondente;
			cmbTipoGradeHoraria.dataProvider = tipoMensagemDto.listaTipoGradeHoraria;
			cmbCategoria.dataProvider = tipoMensagemDto.listaCategoriaMensagem;
			
			if(tipoMensagemDto.bolExigeMensagemRetorno){
				this.optSim.selected = true;
			}else{
				this.optNao.selected = true;
			}
			
			//Determina o indice da combo
			if(tipoMensagemDto.codTipoArquivoCorrespondente != null){
				cmbArquivoCorrespondente.procuraItemPorNome(tipoMensagemDto.codTipoArquivoCorrespondente, "codTipoMensagem");
			}
			if(tipoMensagemDto.codTipoGradeHoraria != null){
				cmbTipoGradeHoraria.procuraItemPorNome(tipoMensagemDto.codTipoGradeHoraria,"codTipoGradeHoraria");
			}
			if(tipoMensagemDto.codCategoriaMensagemDda != null){
				cmbCategoria.procuraItemPorNome(tipoMensagemDto.codCategoriaMensagemDda, "codCategoriaMensagemDda");
			}
		}
		
		//--------------------------------------------------------------------------
		//  Valida Alteracao.
		//--------------------------------------------------------------------------
		private function validarAlterarTipoMensagem(event:MouseEvent):void {
			if(validar()){
				pupularCampos();	
			}
		}
		
		private function pupularCampos():void {
			var tipoMensagem:TipoMensagemDDAVO = new TipoMensagemDDAVO;
			tipoMensagem.tipoGradeHoraria = this.cmbTipoGradeHoraria.selectedItem as TipoGradeHorariaVO;
			tipoMensagem.categoriaMensagemDDA = this.cmbCategoria.selectedItem as CategoriaMensagemDDAVO;
			tipoMensagem.numPrioridadeEnvio = parseInt(this.numPrioridadeEnvio.text);
			if(this.cmbArquivoCorrespondente.selectedItem){
				tipoMensagem.codTipoArquivoCorrespondente = (this.cmbArquivoCorrespondente.selectedItem as TipoMensagemDDAVO).codTipoMensagem;
			}
			tipoMensagem.codTipoMensagem = this.codTipoMensagem.text;
			tipoMensagem.descTipoMensagem  = this.descTipoMensagem.text;
			tipoMensagem.bolExigeMensagemRetorno = this.grpExigeRetorno.selectedValue;
			alterarHoraria(tipoMensagem);
		}
		
		//--------------------------------------------------------------------------
		//  Alteracao do Tipo Mensagem.
		//--------------------------------------------------------------------------
		private function alterarHoraria(tipoMensagemDto:TipoMensagemDDAVO):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = tipoMensagemDto;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_MENSAGEM, "incluirTipoMensagem");
			servico.addEventListener(ResultEvent.RESULT, retornoAlteracao);
			servico.incluirTipoMensagem(dto);						
		}
		
		private function retornoAlteracao(event:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(Mensagens.MSG056, "Tipo Mensagem"), fechaRefazPesquisa);
		}
		
		//--------------------------------------------------------------------------
		//  Valida Gravacao.
		//--------------------------------------------------------------------------
		private function validar():Boolean {
			var isValido:Boolean = false;
			if(this.codTipoMensagem.text.length == 0){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Cód. Mensagem"));
				return isValido = false;
			}else if(this.descTipoMensagem.text == null || StringUtil.trim(this.descTipoMensagem.text).length == 0){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Desc. Tipo Mensagem"));
				return isValido = false;
			}else if(this.cmbCategoria.selectedItem == null){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Categoria"));
				return isValido = false;
			}else  if(this.numPrioridadeEnvio.text== null || StringUtil.trim(this.numPrioridadeEnvio.text).length == 0){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Prioridade"));
				return isValido = false;
			}else if(this.grpExigeRetorno.selectedValue == null){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Exige Retorno"));
				return isValido = false;
			}else{
				return isValido = true;
			}
			return isValido;
		}
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		private function fechaRefazPesquisa(obj:Object = null):void {
			if(_atualizaPesquisa != null){
				this._atualizaPesquisa();
			}
			super.fecharJanela();
		}
		
		//--------------------------------------------------------------------------
		//  Retorno de metodo para tela.
		//--------------------------------------------------------------------------
		private function retorno (event:ResultEvent):Function{
			return this._atualizaPesquisa;
		}
		
		public function limparCampos(evt:Event):void {
			this.cmbTipoGradeHoraria.selectedIndex = 0;
			this.cmbCategoria.selectedIndex = 0;
			this.cmbArquivoCorrespondente.selectedIndex = 0;
			this.numPrioridadeEnvio.text = null;
			this.codTipoMensagem.text = null;;
			this.descTipoMensagem.text = null;
		}
		
	}
}