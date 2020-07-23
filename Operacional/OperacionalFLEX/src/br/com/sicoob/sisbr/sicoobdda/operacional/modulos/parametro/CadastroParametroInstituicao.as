package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.parametro {
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.DateField;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.dto.ResultadoValidacaoDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ConsultaParametroDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.TreeSelecaoCheckCobrancaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.ParametroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoParametroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.ValorParametroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class CadastroParametroInstituicao extends CadastroParametroInstituicaoView {
		
		private var consultaParametroDTO:ConsultaParametroDTO;
		private var listaTipoParametro:ArrayCollection;
		private var listaInstituicaoDisponiveis:ArrayCollection;
		private var listaValorParametro:ArrayCollection;
		private var listaInstituicaoSelecionadas:ArrayCollection;
		private var idInstituicao:String;
		private var permiteAlterar:Boolean;
		private var listaSingular:ArrayCollection;
		
		public function CadastroParametroInstituicao(consultaParametroDTO:ConsultaParametroDTO, permiteAlterar:Boolean, listaSingular:ArrayCollection) {
			super();
			
			this.consultaParametroDTO = consultaParametroDTO;
			this.permiteAlterar = permiteAlterar;
			this.listaSingular = listaSingular;
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			if (pegaJanela() != null) {
				pegaJanela().sairComEsc = false;
			}
			
			iniciarCampos();
			iniciarBotoes();
			initBotoesNavegacao();
		}
		
		private function initBotoesNavegacao():void{
			addSelecao.addEventListener(MouseEvent.CLICK,addCoopDisponivel);
			addTodos.addEventListener(MouseEvent.CLICK,addAllDisponiveis);
			delSelecao.addEventListener(MouseEvent.CLICK,delCoopSelecionada);
			delTodos.addEventListener(MouseEvent.CLICK,delAllSelecionadas);
		}
		
		private function iniciarCampos():void {
			cmbTipoParametro.addEventListener(ListEvent.CHANGE, defineTipoParametro);
			txtDecimal.addEventListener(FocusEvent.FOCUS_OUT, textFocusOut);
			optFalse.addEventListener(FocusEvent.FOCUS_OUT, textFocusOut);
			optTrue.addEventListener(FocusEvent.FOCUS_OUT, textFocusOut);
			txtData.addEventListener(FocusEvent.FOCUS_OUT, textFocusOut);
			txtNumerico.addEventListener(FocusEvent.FOCUS_OUT, textFocusOut);
			
			listaInstituicaoDisponiveis = new ArrayCollection();
			listaInstituicaoSelecionadas = new ArrayCollection();
			listaValorParametro = new ArrayCollection();
			
			obterDados();
			
			defineTipoParametro(null);
			
			descInstituicao.text = consultaParametroDTO.numeroCooperativa + " - " + consultaParametroDTO.descricaoSiglaCooperativa;
			
			if(consultaParametroDTO.bolAtivo == true){
				optAtivo.selected = true;
			}else{
				optInativo.selected = true;
			}
			
			if(consultaParametroDTO.bolPermiteAlteracaoPeloUsuario == true){
				chkAlteracaoUsuario.selected = true;
			}
			if(consultaParametroDTO.dataCriacao !=null){
				txtDataCriacao.selectedDate = consultaParametroDTO.dataCriacao.data;
			}
			if(consultaParametroDTO.dataUltimaAlteracao !=null){
				txtDataAlteracao.selectedDate = consultaParametroDTO.dataUltimaAlteracao.data;
			}
			
			txtCodParametro.valor = consultaParametroDTO.idParametro;
			txtCodParametro.enabled = false;
			txtNome.text = consultaParametroDTO.nomeParametro;
			txtDescricao.text = consultaParametroDTO.descricaoParametro;
		}
		
		private function iniciarBotoes():void{
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnLimpar.addEventListener(MouseEvent.CLICK, limparTela);
			btnGravar.addEventListener(MouseEvent.CLICK, tratarValidacao);
			
			btnGravar.enabled = btnLimpar.enabled = permiteAlterar;
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
			if (consultaParametroDTO.idParametroLegado > 0) {
				MensagensComum.exibirMensagemConfirmacao("O valor será atualizado no parâmetro " + consultaParametroDTO.idParametroLegado +" da base da cooperativa. Deseja continuar?", null, tratarGravar);
			} else {
				tratarGravar();
			}
		}
		
		private function validarCampos():ResultadoValidacaoDTO {
			var dto:ResultadoValidacaoDTO = new ResultadoValidacaoDTO();
			
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
			}
			
			return dto;
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
		
		private function tratarGravar(evt:Event = null):void{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["vo"] = preencherDadosParametro();
			dto.dados["listaSingular"] = listaSingular;
			
			// refator
			var listaValorP:ArrayCollection = new ArrayCollection();
			
			for each(var lst:TreeSelecaoCheckCobrancaDTO in listaInstituicaoSelecionadas){
				var vp:ValorParametroVO = new ValorParametroVO();
				vp = lst.object as ValorParametroVO;
				listaValorP.addItem(vp);
			}
			dto.dados.listaInstitucao = listaValorP;
			dto.dados.idInstituicao = idInstituicao;
			// refator
			
			var metodo:String;
			var servico:ServicoJava;
			
			metodo = "alterarParametroInstituicao";
			servico = Funcoes.obterServico(Constantes.SERVICO_PARAMETRO_INSTITUICAO, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoSalvar);
			servico.alterarParametroInstituicao(dto);
		}
		
		/**
		 * Trata o retorno após salvar.
		 */
		private function resultadoSalvar(evt:ResultEvent):void {
			MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG001, "Parâmetro"));
			fechar(evt);
		}
		
		private function obterDados():void{
			var metodo:String = "obterDados";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idParametro = consultaParametroDTO.idParametro;
			dto.dados.listaSingular = listaSingular;
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_PARAMETRO_INSTITUICAO, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoObterDados);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.obterDados(dto);
		}
		
		private function resultadoObterDados(evt:ResultEvent):void {
			listaTipoParametro = evt.result.dados.lista as ArrayCollection;
			cmbTipoParametro.dataProvider = listaTipoParametro;
			cmbTipoParametro.selectedItem = selecionarItem(consultaParametroDTO.tipoParametro.id, listaTipoParametro, "id");
			
			if (consultaParametroDTO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_NUMBER) {
				txtNumerico.text = evt.result.dados.valorParametroInstituicao;
			} else if (consultaParametroDTO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_DECIMAL) {
				txtDecimal.text = evt.result.dados.valorParametroInstituicao;
			} else if (consultaParametroDTO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_BOOLEAN) {
				if (int(evt.result.dados.valorParametroInstituicao)==1) {
					optTrue.selected = true;
				} else {
					optFalse.selected = true;
				}
			} else if (consultaParametroDTO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_STRING) {
				txtValor.text = evt.result.dados.valorParametroInstituicao;
			} else if (consultaParametroDTO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_DATA) {
				var df:DateFormatter = new DateFormatter();
				df.formatString = "DD/MM/YYYY";
				var valorParametro:String = df.format(evt.result.dados.valorParametroInstituicao);
				txtData.selectedDate = DateField.stringToDate(valorParametro, "DD/MM/YYYY");
			}
			
			listaValorParametro = evt.result.dados.listaValorParametroVO as ArrayCollection;
			initGridDisponiveis(listaValorParametro);
		}
		
		private function preencherDadosParametro():ParametroVO{
			var _parametroVO:ParametroVO = new ParametroVO;
			idInstituicao = consultaParametroDTO.idInstituicao.toString();
			_parametroVO.id = consultaParametroDTO.idParametro;
			_parametroVO.idParametroLegado = consultaParametroDTO.idParametroLegado;
			
			if (consultaParametroDTO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_NUMBER) {
				_parametroVO.valorParametro = txtNumerico.text;
			} else if (consultaParametroDTO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_DECIMAL) {
				_parametroVO.valorParametro = txtDecimal.text;
			} else if (consultaParametroDTO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_BOOLEAN) {
				_parametroVO.valorParametro = optTrue.selected ? "1" : "0";
			} else if (consultaParametroDTO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_STRING) {
				_parametroVO.valorParametro = txtValor.text;
			} else if (consultaParametroDTO.tipoParametro.id == Constantes.ID_TIPO_PARAMETRO_DATA) {
				_parametroVO.valorParametro = DateField.dateToString(data.selectedDate, "DD/MM/YYYY");
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
		
		private function initGridDisponiveis(listaValorParametro:ArrayCollection):void{
			listaInstituicaoDisponiveis = new ArrayCollection;
			
			for each(var vp:ValorParametroVO in listaValorParametro){
				var treeSelecaoCheckCooperativas:TreeSelecaoCheckCobrancaDTO = new TreeSelecaoCheckCobrancaDTO;
				treeSelecaoCheckCooperativas.id = ""+vp.idInstituicao;
				treeSelecaoCheckCooperativas.label = vp.descInstituicao;
				treeSelecaoCheckCooperativas.object = vp;
				treeSelecaoCheckCooperativas.state = "unchecked";
				listaInstituicaoDisponiveis.addItem(treeSelecaoCheckCooperativas);
			}
			
			treeSelecaoDisponivel.tree.dataProvider = listaInstituicaoDisponiveis;
		}
		
		/** Functions Auxiliares */
		private function validarCamposNavegacao():void{
			if(listaInstituicaoSelecionadas.source.length < 1){
				delSelecao.enabled = false;
				delTodos.enabled = false;
			}else{
				delSelecao.enabled = true;
				delTodos.enabled = true;
			}
			if(listaInstituicaoDisponiveis.source.length < 1){
				addTodos.enabled = false;
				addSelecao.enabled = false;
			}else{
				addTodos.enabled = true;
				addSelecao.enabled = true;
			}
		}
		/** ########################  FUNCTIONS DE ACAO DE NAVEGAÇÃO ####################################  */
		
		private function addCoopDisponivel(evt:MouseEvent):void{
			var listaInstituicaoDisponiveisAux:ArrayCollection = new ArrayCollection();
			var dados:ArrayCollection = listaInstituicaoDisponiveis;
			
			for(var i:int = 0; i < dados.length; i++) {
				if(dados[i].state == "checked"){
					dados[i].state = "unchecked";
					listaInstituicaoSelecionadas.addItem(dados[i]);
				}else{
					listaInstituicaoDisponiveisAux.addItem(dados[i]);
				}
			}
			
			listaInstituicaoDisponiveis = listaInstituicaoDisponiveisAux;			
			dataProviderRefresh(listaInstituicaoDisponiveis,listaInstituicaoSelecionadas)
		}
		
		private function dataProviderRefresh(listaDisponivel:ArrayCollection,listaSelecionada:ArrayCollection):void{
			if(listaDisponivel.source.length >= 0){
				arrayCollectionSort(listaDisponivel,"label");
				treeSelecaoDisponivel.tree.dataProvider = listaDisponivel;						
			}
			
			if(listaSelecionada.source.length >= 0){
				arrayCollectionSort(listaSelecionada,"label");
				treeSelecaoSelecionado.tree.dataProvider = listaSelecionada;	
			}
			
			validarCamposNavegacao();
		}
		
		private function arrayCollectionSort(ar:ArrayCollection, fieldName:String):void {
			var dataSortField:SortField = new SortField();
			dataSortField.name = fieldName;
			
			var numericDataSort:Sort = new Sort();
			numericDataSort.fields = [dataSortField];
			ar.sort = numericDataSort;
			ar.refresh();
		}
		
		private function addAllDisponiveis(evt:MouseEvent):void{
			var listaInstituicaoDisponiveisAux:ArrayCollection = new ArrayCollection(listaInstituicaoDisponiveis.source);
			listaInstituicaoDisponiveis.source = null;
			
			if(listaInstituicaoSelecionadas.length > 0){
				listaInstituicaoDisponiveisAux = new ArrayCollection(listaInstituicaoSelecionadas.toArray().concat(listaInstituicaoDisponiveisAux.toArray())); 
			}
			listaInstituicaoSelecionadas = listaInstituicaoDisponiveisAux;
			
			dataProviderRefresh(listaInstituicaoDisponiveis,listaInstituicaoSelecionadas)
			
		}
		
		private function delCoopSelecionada(evt:MouseEvent):void{
			var listaInstituicaoDisponiveisAux:ArrayCollection = new ArrayCollection();
			var dados:ArrayCollection = listaInstituicaoSelecionadas;
			
			for(var i:int = 0; i < dados.length; i++) {
				if(dados[i].state == "checked"){
					dados[i].state = "unchecked";
					listaInstituicaoDisponiveis.addItem(dados[i]);
				}else{
					listaInstituicaoDisponiveisAux.addItem(dados[i]);
				}
			}
			
			listaInstituicaoSelecionadas = listaInstituicaoDisponiveisAux;
			dataProviderRefresh(listaInstituicaoDisponiveis,listaInstituicaoSelecionadas);
		}
		
		private function delAllSelecionadas(evt:MouseEvent):void{
			var listaInstituicaoDisponiveisAux:ArrayCollection = new ArrayCollection(listaInstituicaoSelecionadas.source);
			listaInstituicaoSelecionadas.source = null;			
			
			if(listaInstituicaoDisponiveis.length > 0){
				listaInstituicaoDisponiveisAux = new ArrayCollection(listaInstituicaoDisponiveis.toArray().concat(listaInstituicaoDisponiveisAux.toArray())); 
			}
			
			listaInstituicaoDisponiveis = listaInstituicaoDisponiveisAux;
			
			dataProviderRefresh(listaInstituicaoDisponiveis,listaInstituicaoSelecionadas);
		}
		
		/** ########################  FIM DAS FUNCTIONS DE ACAO DE NAVEGAÇÃO ####################################  */
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
			var manterParametroInstituicao:ManterParametroInstituicao = this.pegaJanela().janelaPai as ManterParametroInstituicao;
			manterParametroInstituicao.pnlLista.pesquisar(1);
		}
		
		private function limparTela(evt:MouseEvent):void{
			txtValor.text = "";
			listaInstituicaoSelecionadas = new ArrayCollection();
			dataProviderRefresh(listaInstituicaoDisponiveis,listaInstituicaoSelecionadas);
		}
		
		//Determina o tipo de componente que ira aparecer de 
		//acordo com o o evento da combo tipoParametro ou de acrdo com o tipo de objeto a ser editado.
		
		public function defineTipoParametro(evt:ListEvent):void{
			txtValor.visible = optFalse.visible = optTrue.visible = txtData.visible = txtNumerico.visible = txtDecimal.visible = false;
			limparCamposOcultos();
			
			if(evt!=null || consultaParametroDTO != null){
				var tipoParametroVO:TipoParametroVO;
				if(evt!=null){
					tipoParametroVO = evt.currentTarget.selectedItem;
				}else{
					tipoParametroVO = consultaParametroDTO.tipoParametro;
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
		
		public function limparCamposOcultos():void{
			optFalse.selected = false;
			optFalse.selected = false;
			txtDecimal.text = "";
			txtData.compMask.text = "";
			txtValor.text = "";
		}
	}
}


