package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipomensagem
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
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
	public class PopUpEditarTipoMensagem extends PopUpEditarTipoMensagemView
	{
		private var dataReferenciaTemp:IDateTime;
		private var itemTipoMensagemVO:TipoMensagemDDAVO;
		private var _atualizaPesquisa:Function;
		private var _visualizacao:Boolean;
		private var _tipoMensagemDtoTemp:TipoMensagemDTO;
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpEditarTipoMensagem(itemTipoMensagemVO:TipoMensagemDDAVO, funcaoRetorno:Function,visualizacao:Boolean) {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this.itemTipoMensagemVO = itemTipoMensagemVO as TipoMensagemDDAVO;
			this._atualizaPesquisa = funcaoRetorno;
			this._visualizacao = visualizacao;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			this._tipoMensagemDtoTemp = new TipoMensagemDTO();
			var janela:Janela =super.pegaJanela();
			janela.barraBotoes.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btConfirmar.addEventListener(MouseEvent.CLICK, validarAlterarHoraria);
			this.descTipoMensagem.addEventListener(MouseEvent.MOUSE_OVER, setToolTip);
			this.codTipoMensagem.editable = false;
			if(this._visualizacao){
				this.cmbArquivoCorrespondente.enabled = false;
				this.cmbCategoria.enabled = false;
				this.cmbTipoGradeHoraria.enabled = false;
				
				this.codTipoMensagem.editable = false;
				this.descTipoMensagem.editable = false;
				this.numPrioridadeEnvio.editable = false;
				this.grpExigeRetorno.enabled = false;
				this.btConfirmar.visible = false;
				this.btCancelar.visible = false;
			}
			this.btCancelar.addEventListener(MouseEvent.CLICK,cancelar);
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
		//  Carregar Campos.
		//--------------------------------------------------------------------------		
		private function carregarCampos():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = this.itemTipoMensagemVO;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_MENSAGEM, "obterTipoMensagem");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterTipoMensagem(dto);
		}
		
		//--------------------------------------------------------------------------
		//  Seta tooltip.
		//--------------------------------------------------------------------------		
		private function setToolTip(event:Event):void {
			this.descTipoMensagem.toolTip = this.descTipoMensagem.text; 
			this.cmbTipoGradeHoraria.toolTip = this.cmbTipoGradeHoraria.selectedLabel;
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de callback.
		//--------------------------------------------------------------------------
		private function retornoConsulta(event:ResultEvent):void{
			var tipoMensagemDto:TipoMensagemDTO = event.result.dados["lista"] as TipoMensagemDTO;
			this._tipoMensagemDtoTemp = tipoMensagemDto;
			//Carrega combos
			this.codTipoMensagem.text = tipoMensagemDto.codTipoMensagem;
			this.descTipoMensagem.text = tipoMensagemDto.descTipoMensagem;
			this.descTipoMensagem.toolTip = tipoMensagemDto.descTipoMensagem; 
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
				var codTipoMensagem:String = tipoMensagemDto.codTipoArquivoCorrespondente;
				cmbArquivoCorrespondente.procuraItemPorNome(codTipoMensagem, "codTipoMensagem");
			}
			if(tipoMensagemDto.codTipoGradeHoraria != null){
				cmbTipoGradeHoraria.procuraItemPorNome(tipoMensagemDto.codTipoGradeHoraria,"codTipoGradeHoraria");
			}
			if(tipoMensagemDto.codCategoriaMensagemDda != null){
				cmbCategoria.procuraItemPorNome(tipoMensagemDto.codCategoriaMensagemDda, "codCategoriaMensagemDda");
			}
		}
		
		//--------------------------------------------------------------------------
		//  Reverte Alteracoes Tipo Mensagem.
		//--------------------------------------------------------------------------
		private function cancelar(event:MouseEvent):void{
			//Carrega combos
			this.codTipoMensagem.text = _tipoMensagemDtoTemp.codTipoMensagem;
			this.descTipoMensagem.text = _tipoMensagemDtoTemp.descTipoMensagem;
			this.numPrioridadeEnvio.text  = new String(_tipoMensagemDtoTemp.numPrioridadeEnvio);
			this.descTipoMensagem.toolTip = _tipoMensagemDtoTemp.descTipoMensagem; 
			
			cmbArquivoCorrespondente.dataProvider = _tipoMensagemDtoTemp.listaArquivoCorrespondente;
			cmbTipoGradeHoraria.dataProvider = _tipoMensagemDtoTemp.listaTipoGradeHoraria;
			cmbCategoria.dataProvider = _tipoMensagemDtoTemp.listaCategoriaMensagem;
			
			if(_tipoMensagemDtoTemp.bolExigeMensagemRetorno){
				this.optSim.selected = true;
			}else{
				this.optNao.selected = true;
			}
			
			//Determina o indice da combo
			if(_tipoMensagemDtoTemp.codTipoArquivoCorrespondente != null){
				var codTipoMensagem:String = _tipoMensagemDtoTemp.codTipoArquivoCorrespondente;
				cmbArquivoCorrespondente.procuraItemPorNome(codTipoMensagem, "codTipoMensagem");
			}
			if(_tipoMensagemDtoTemp.codTipoGradeHoraria != null){
				cmbTipoGradeHoraria.procuraItemPorNome(_tipoMensagemDtoTemp.codTipoGradeHoraria,"codTipoGradeHoraria");
			}
			if(_tipoMensagemDtoTemp.codCategoriaMensagemDda != null){
				cmbCategoria.procuraItemPorNome(_tipoMensagemDtoTemp.codCategoriaMensagemDda, "codCategoriaMensagemDda");
			}
		}
		
		//--------------------------------------------------------------------------
		//  Alteracao Tipo Mensagem.
		//--------------------------------------------------------------------------
		private function validarAlterarHoraria(event:MouseEvent):void {
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
		
		private function alterarHoraria(tipoMensagemDto:TipoMensagemDDAVO):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = tipoMensagemDto;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_MENSAGEM, "alterarTipoMensagem");
			servico.addEventListener(ResultEvent.RESULT, retornoAlteracao);
			servico.alterarTipoMensagem(dto);						
		}
		
		private function retornoAlteracao(event:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(Mensagens.MSG057, "Tipo Mensagem"), fechaRefazPesquisa);
		}
		
		//--------------------------------------------------------------------------
		//  Validar alteracao.
		//--------------------------------------------------------------------------
		private function validar():Boolean {
			var isValido:Boolean = false;
			if(this.cmbTipoGradeHoraria.selectedItem == null){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Cód. Tipo Grade"));
				isValido = false;
			}else if(this.cmbCategoria.selectedItem == null){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Categoria"));
				isValido = false;
			}else if(this.codTipoMensagem.text == null){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Cód. Mensagem"));
				isValido = false;
			}else if(this.descTipoMensagem.text== null){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Desc. Tipo Mensagem"));
				isValido = false;
			}else if(this.numPrioridadeEnvio.text== null){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Prioridade"));
				isValido = false;
			}else if(this.grpExigeRetorno.selectedValue == null){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG048, "Exige Retorno"));
				isValido = false;
			}else{
				isValido = true;
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
		
		//--------------------------------------------------------------------------
		//  Limpar campos.
		//--------------------------------------------------------------------------
		public function limparCampos(evt:Event):void {
			this.cmbTipoGradeHoraria.selectedIndex = 0;
			this.cmbCategoria.selectedIndex = 0;
			this.cmbArquivoCorrespondente.selectedIndex = 0;
			this.numPrioridadeEnvio.text = null;
			this.codTipoMensagem.text = null;;
			this.descTipoMensagem.text = null;
			this.grpExigeRetorno.selection.selected = false;
		}
		
	}
}