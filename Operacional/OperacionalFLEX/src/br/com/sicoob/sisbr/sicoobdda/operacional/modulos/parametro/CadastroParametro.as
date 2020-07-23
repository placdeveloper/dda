package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.parametro {
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.controls.DateField;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.dto.ResultadoValidacaoDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.ParametroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoParametroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class CadastroParametro extends CadastroParametroView {
		
		private var modo:int;
		
		private var parametroVO:ParametroVO;
		private var listaTipoParametro:ArrayCollection;
		private var permiteAlterar:Boolean;
		
		public function CadastroParametro(parametroVO:ParametroVO, modo:int, permiteAlterar:Boolean = true) {
			super();
			
			this.parametroVO = parametroVO;
			this.modo = modo;
			this.permiteAlterar = permiteAlterar;
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			if (pegaJanela() != null) {
				pegaJanela().sairComEsc = false;
			}
			
			iniciarCampos();
			iniciarBotoes();
			
			if (modo == FormularioCadastro.MODO_EDICAO) {
				preencherCampos();
			}
		}
		
		private function iniciarCampos():void {
			cmbTipoParametro.addEventListener(ListEvent.CHANGE, defineTipoParametro);
			txtDecimal.addEventListener(FocusEvent.FOCUS_OUT, textFocusOut);
			optFalse.addEventListener(FocusEvent.FOCUS_OUT, textFocusOut);
			optTrue.addEventListener(FocusEvent.FOCUS_OUT, textFocusOut);
			txtData.addEventListener(FocusEvent.FOCUS_OUT, textFocusOut);
			txtNumerico.addEventListener(FocusEvent.FOCUS_OUT, textFocusOut);
			
			obterListaParametro();
			definirMensagens();
			defineTipoParametro(null);
		}
		
		private function iniciarBotoes():void{
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnLimpar.addEventListener(MouseEvent.CLICK, limparTela);
			btnGravar.addEventListener(MouseEvent.CLICK, tratarValidacao);
			
			btnGravar.enabled = btnLimpar.enabled = permiteAlterar;
		}
		
		private function validarCampos():ResultadoValidacaoDTO {
			var dto:ResultadoValidacaoDTO = new ResultadoValidacaoDTO();
			
			if (txtCodParametro.length == 0) {
				dto.adicionarMensagem(MensagensComum.formatar(Mensagens.MSG013, "código do parâmetro"));
				dto.campoFoco = txtCodParametro;
				dto.valido = false;
				
			} else if (txtNome.text == ""){
				dto.adicionarMensagem(MensagensComum.formatar(Mensagens.MSG013, "nome do parâmetro"));
				dto.campoFoco = txtNome;
				dto.valido = false;
				
			} else if (txtDescricao.text == "") {
				dto.adicionarMensagem(MensagensComum.formatar(Mensagens.MSG013, "descrição do parâmetro"));
				dto.campoFoco = txtDescricao;
				dto.valido = false;
				
			} else {
				if (txtNumerico.visible && txtNumerico.text == "") {
					dto.adicionarMensagem(MensagensComum.formatar(Mensagens.MSG013, "valor do parâmetro"));
					dto.campoFoco = txtNumerico;
					dto.valido = false;
				} else if (txtDecimal.visible && txtDecimal.text == "") {
					dto.adicionarMensagem(MensagensComum.formatar(Mensagens.MSG013, "valor do parâmetro"));
					dto.campoFoco = txtDecimal;
					dto.valido = false;
				} else if (txtData.visible && txtData.selectedDate == null) {
					dto.adicionarMensagem(MensagensComum.formatar(Mensagens.MSG013, "valor do parâmetro"));
					dto.campoFoco = txtData.compDate;
					dto.valido = false;
				} else if (txtValor.visible && txtValor.text == "") {
					dto.adicionarMensagem(MensagensComum.formatar(Mensagens.MSG013, "valor do parâmetro"));
					dto.campoFoco = txtValor;
					dto.valido = false;
				} else if (txtValorTexto.visible && txtValorTexto.text == "") {
					dto.adicionarMensagem(MensagensComum.formatar(Mensagens.MSG013, "valor texto"));
					dto.campoFoco = txtValorTexto;
					dto.valido = false;
				}
			}
			
			return dto;
		}
		
		/**
		 * Chama a visualização do boleto.
		 */
		private function tratarValidacao(evt:Event):void {
			var dto:ResultadoValidacaoDTO = validarCampos();
			
			if (dto.valido){
				tratarMostrarMensagem();
			} else {
				exibirErroValidacao(dto);
			}
		}
		
		private function tratarMostrarMensagem():void {
			// Se for global e possuir codLegado informado o valor será atualizado pra todas as cooperativas
			if (optGlobal.selected && txtCodLegado.valor > 0) {
				MensagensComum.exibirMensagemConfirmacao("O valor será atualizado em todas as cooperativas. Deseja continuar?", null, tratarGravar);
			} else {
				tratarGravar();
			}
		}
		
		private function tratarGravar(evt:Event = null):void{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["vo"] = preencherDadosParametro();
			
			var metodo:String;
			var servico:ServicoJava;
			
			if (modo == FormularioCadastro.MODO_INCLUSAO) {
				metodo = "incluirParametro";
				servico = Funcoes.obterServico(Constantes.SERVICO_PARAMETRO, metodo);
				servico.addEventListener(ResultEvent.RESULT, resultadoSalvar);
				servico.incluirParametro(dto);
			} else {
				metodo = "alterarParametro";
				servico = Funcoes.obterServico(Constantes.SERVICO_PARAMETRO, metodo);
				servico.addEventListener(ResultEvent.RESULT, resultadoAlterar);
				servico.alterarParametro(dto);
			}
		}
		
		/**
		 * Trata o retorno após salvar.
		 */
		private function resultadoSalvar(evt:ResultEvent):void {
			MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG001, "Parâmetro"));
			fechar(evt);
		}
		
		/**
		 * Trata o retorno após alterar.
		 */
		private function resultadoAlterar(evt:ResultEvent):void {
			MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG003, "Parâmetro"));
		}
		
		private function preencherCampos():void {
			if(parametroVO.bolAtivo){
				optAtivo.selected = true;
			}else{
				optInativo.selected = true;
			}
			
			if(parametroVO.bolParametroGlobal){
				optGlobal.selected = true;
			}else{
				optInstituicao.selected = true;
			}
			
			optAdministrativoNao.selected = parametroVO.bolVisivelFuncionalidadeAlteracao;
			optAdministrativoSim.selected = !optAdministrativoNao.selected;
			
			txtCodLegado.valor = parametroVO.idParametroLegado == 0 ? NaN : parametroVO.idParametroLegado;
			
			if(parametroVO.bolPermiteAlteracaoPeloUsuario){
				chkAlteracaoUsuario.selected = true;
			}
			
			if(parametroVO.dataCriacao){
				txtDataCriacao.selectedDate = parametroVO.dataCriacao.data;
			}
			
			if(parametroVO.dataHoraUltimaAtualizacao){
				txtDataAlteracao.selectedDate = parametroVO.dataHoraUltimaAtualizacao.data;
			}
			
			txtCodParametro.valor = parametroVO.id;
			txtCodParametro.enabled = false;
			txtNome.text = parametroVO.nomeParametro;
			txtDescricao.text = parametroVO.descParametro;
			
			if(parametroVO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_NUMBER){
				txtNumerico.text = parametroVO.valorParametro
			}else if(parametroVO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_DECIMAL){
				txtDecimal.text = parametroVO.valorParametro
			}else if(parametroVO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_BOOLEAN){
				if(int(parametroVO.valorParametro)==1){
					optTrue.selected = true;
				}else{
					optFalse.selected = true;
				}
			}else if(parametroVO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_STRING){
				txtValor.text = parametroVO.valorParametro
			}else if(parametroVO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_DATA){
				var df:DateFormatter = new DateFormatter();
				df.formatString = "DD/MM/YYYY";
				var valorParametro:String = df.format(parametroVO.valorParametro);
				txtData.selectedDate = DateField.stringToDate(valorParametro, "DD/MM/YYYY");
			}
			
			txtValorTexto.text = parametroVO.valorParametroTexto;
		}
		
		private function obterListaParametro():void{
			var metodo:String = "listarTipoParametro";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_PARAMETRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoObterParametros);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.listarTipoParametro();
		}
		
		private function resultadoObterParametros(evt:ResultEvent):void {
			listaTipoParametro = evt.result.dados.lista as ArrayCollection;
			cmbTipoParametro.enabled = true;
			cmbTipoParametro.dataProvider = listaTipoParametro;
			
			if(modo == FormularioCadastro.MODO_EDICAO){
				cmbTipoParametro.selectedItem = selecionarItem(parametroVO.tipoParametro.id, listaTipoParametro, "id");
			}
		}
		
		private function preencherDadosParametro():ParametroVO{
			var _parametroVO:ParametroVO = new ParametroVO;
			
			_parametroVO.id = txtCodParametro.valor;
			_parametroVO.nomeParametro = txtNome.text;
			_parametroVO.descParametro = txtDescricao.text;
			_parametroVO.tipoParametro = cmbTipoParametro.selectedItem as TipoParametroVO;
			
			_parametroVO.bolPermiteAlteracaoPeloUsuario = chkAlteracaoUsuario.selected;
			_parametroVO.bolAtivo = optAtivo.selected;
			_parametroVO.bolParametroGlobal = optGlobal.selected;
			
			if (_parametroVO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_NUMBER) {
				_parametroVO.valorParametro = txtNumerico.text;
			} else if (_parametroVO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_DECIMAL) {
				_parametroVO.valorParametro = txtDecimal.text;
			} else if (_parametroVO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_BOOLEAN) {
				_parametroVO.valorParametro = optTrue.selected ? "1" : "0";
			} else if (_parametroVO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_STRING) {
				_parametroVO.valorParametro = txtValor.text;
			} else if (_parametroVO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_DATA) {
				_parametroVO.valorParametro = DateField.dateToString(data.selectedDate, "DD/MM/YYYY");
			}
			
			_parametroVO.valorParametroTexto = txtValorTexto.text;
			
			_parametroVO.idParametroLegado = txtCodLegado.valor == 0 ? NaN : txtCodLegado.valor;
			_parametroVO.bolVisivelFuncionalidadeAlteracao = optAdministrativoNao.selected;
			
			if (modo == FormularioCadastro.MODO_EDICAO) {
				_parametroVO.id = parametroVO.id;
				_parametroVO.dataCriacao = parametroVO.dataCriacao;
			}
			
			return _parametroVO;
		}
		
		/**
		 * Percorre o array informado retornando o objeto que seja igual ao index. 
		 */
		private function selecionarItem(index:Number, array:ArrayCollection, campo:String):Object {
			for (var i:Number = 0; i < array.length; i++) {
				if (array[i][campo] == index) {
					return array[i];
				}
			}
			
			return null;
		}
		/**
		 * Fecha a janela.
		 */
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
			var manterParametro:ManterParametro = this.pegaJanela().janelaPai as ManterParametro;
			
			// Atualiza as informações da tela
			manterParametro.obterDados(true);
		}
		
		/**
		 * Define as mensagens para validação dos campos.
		 */
		private function definirMensagens():void {
			txtCodParametro.validarMensagem = MensagensComum.formatar(Mensagens.MSG013, "código do parâmetro");
			txtNome.validarMensagem = MensagensComum.formatar(Mensagens.MSG013, "nome do parâmetro");
			txtDescricao.validarMensagem = MensagensComum.formatar(Mensagens.MSG013, "descrição do parâmetro");
			txtValor.validarMensagem = MensagensComum.formatar(Mensagens.MSG013, "valor base");
		}
		
		private function limparTela(evt:MouseEvent):void {
			txtNome.text = txtDescricao.text = txtValor.text = txtValorTexto.text = "";
		}
		
		/**
		 * Exibe a mensagem de erro de validação.
		 */
		protected function exibirErroValidacao(resultadoValidacao:ResultadoValidacaoDTO):void {
			var mensagem:String = "Formulário com erros de validação.";
			
			if (resultadoValidacao.mensagens.length > 0) {
				mensagem = String(resultadoValidacao.mensagens.getItemAt(0));
			}
			
			MensagensComum.exibirMensagemAlerta(mensagem, resultadoValidacao.campoFoco);
		}
		
		/*
		* Determina o tipo de componente que ira aparecer de acordo com o o evento da combo tipoParametro ou de acrdo com o tipo de objeto a ser editado.
		*/
		public function defineTipoParametro(evt:ListEvent):void{
			var idTipoCampo:Number ;
			
			txtValor.visible = optFalse.visible = optTrue.visible = txtData.visible = txtNumerico.visible = txtDecimal.visible = false;
			limparCamposOcultos();
			
			if(evt!=null || parametroVO != null){
				var tipoParametroVO:TipoParametroVO;
				if(evt!=null){
					tipoParametroVO = evt.currentTarget.selectedItem;
				}else{
					tipoParametroVO = parametroVO.tipoParametro;
				}
				txtData.compMask.editable = false;
				txtData.compDate.editable = false;
				limparCamposOcultos();
				
				switch(tipoParametroVO.id)
				{
					case Constantes.ID_TIPO_PARAMETRO_NUMBER:
					{
						txtNumerico.visible = true;
						optFalse.visible = optTrue.visible = txtData.visible = txtDecimal.visible = txtValor.visible = false;
						break;
					}
						
					case Constantes.ID_TIPO_PARAMETRO_DECIMAL:
					{
						txtDecimal.visible = true;
						optFalse.visible = optTrue.visible = txtData.visible = txtNumerico.visible = txtValor.visible = false;
						break;
					}
						
					case Constantes.ID_TIPO_PARAMETRO_BOOLEAN:
					{
						optTrue.visible = true;
						optFalse.visible = true;
						txtValor.visible = txtData.visible = txtNumerico.visible = txtDecimal.visible = false;
						break;
					}
						
					case Constantes.ID_TIPO_PARAMETRO_STRING:
					{
						txtValor.visible = true;
						optFalse.visible = optTrue.visible = txtData.visible = txtNumerico.visible = txtDecimal.visible = false;
						break;
					}
						
					case Constantes.ID_TIPO_PARAMETRO_DATA:
					{
						txtData.visible = true;
						optFalse.visible = optTrue.visible = txtValor.visible = txtNumerico.visible = txtDecimal.visible = false;
						break;
					}
						
				}
			}else{
				txtNumerico.visible = true;
				optFalse.visible = optTrue.visible = txtData.visible = txtDecimal.visible = txtValor.visible = optFalse.visible =false;
			}
		}
		
		//Obtem os valores de acordo com os tipos de objetos.
		public function textFocusOut(evt:FocusEvent):void{
			if(evt.currentTarget.className == "Texto"){
				this.txtValor.text = evt.currentTarget.text;
			}else if(evt.currentTarget.className == "Data"){
				var df:DateFormatter = new DateFormatter();
				df.formatString = "YYYY-MM-DD";
				this.txtValor.text = df.format(evt.currentTarget.selectedDate);
			}else{
				this.txtValor.text = evt.currentTarget.value;
			}
		}
		
		public function radioFocusOut(evt:FocusEvent):void{
			this.txtValor.text = evt.currentTarget.value;
		}
		
		public function limparCamposOcultos():void{
			if (!optFalse.visible) {
				optFalse.selected = false;
				optTrue.selected = true;
			}
			
			if (!txtDecimal.visible) {
				txtDecimal.text = "";
			}
			
			if (!txtData.visible) {
				txtData.compMask.text = "";
			}
			
			if (!txtValor.visible) {
				txtValor.text = "";
			}
		}
		
	}
	
}