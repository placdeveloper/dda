package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.monitoracaocip
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MensagemDDADTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.MensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoMensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class AbaMonitorarMensagens extends AbaMonitorarMensagensView
	{
		private var btnImprimir:Botao = new Botao();
		private var dto:MensagemDDADTO;
		
		public function AbaMonitorarMensagens(){
			super();
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			iniciarCampos();
			iniciarBotoes();
		}
		private function iniciarCampos():void{
			painelListaPainel.funcaoCriacaoDto = instanciarDtoConsulta;
			painelListaPainel.funcaoConfiguracaoDto = configurarDtoConsulta;
			painelFiltro.cmbOrigem.dataProvider = Constantes.TIPO_ORIGEM.slice();
			painelFiltro.cmbStatusEnvio.dataProvider = Constantes.STATUS_ENVIO.slice();
			
			
			painelFiltro.cmbOrigem.addEventListener(ListEvent.CHANGE, limparConsulta);
			painelFiltro.cmbOrigem.addEventListener(ListEvent.CHANGE, filtrarComboMensagem);
			painelFiltro.cmbStatusEnvio.addEventListener(ListEvent.CHANGE, limparConsulta);
			painelFiltro.cmbTipoMensagem.addEventListener(ListEvent.CHANGE, limparConsulta);
			
			painelFiltro.movimentoDataInicial.addEventListener(Event.CHANGE, limparConsulta);
			painelFiltro.movimentoDataFinal.addEventListener(Event.CHANGE, limparConsulta);
			painelFiltro.dataHoraMensagemInicial.addEventListener(Event.CHANGE, limparConsulta);
			painelFiltro.dataHoraMensagemFinal.addEventListener(Event.CHANGE, limparConsulta);
			listaMensagens.grdDados.addEventListener(ListEvent.ITEM_CLICK, tratarSelecionar);
			
			listaMensagens.grdDados.addEventListener(Event.RENDER, handleListaMensagens);
			listaMensagens.doubleClickEnabled = false;
			this.recuperarTipoMensagens();			
		}
		
		protected function tratarSelecionar(event:ListEvent):void
		{
			dto = listaMensagens.grdDados.selectedItem as MensagemDDADTO;
			this.btnDetalharMensagem.enabled = true;			
		}
		
		private function handleListaMensagens(e:Event):void {
			if(listaMensagens.grdDados.selectedItem) {
				this.listaMensagens.barraPaginacao.enabled = true;
			} else {
				this.listaMensagens.barraPaginacao.enabled = false;			
			}
		}
		
		private function iniciarBotoes():void {
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaPainel.procuraSolicitada);
			painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarProcurar);
			painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			
			btnDetalharMensagem.addEventListener(MouseEvent.CLICK, detalharMensagem);
			
			barraBotoesListaCadastro.includeInLayout = false;
			barraBotoesListaCadastro.visible = false;
			barraBotoesListaCadastro.enabled = false;
			
		}
		
		private function contingenciaMensagem(e:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao(Mensagens.MSG035, null, reenviarMensagem);
		}
		
		private function reenviarMensagem(e:MouseEvent):void {
			var listaIdMensagem:ArrayCollection = new ArrayCollection();
			for each (var mensagem:Object in painelLista.tabelaPaginada.grdDados.dataProvider) {
				if(mensagem.selecionado) {
					if(!validarReenvioMensagem(mensagem)) {
						listaIdMensagem.removeAll();
						break;
					}
					else {
						listaIdMensagem.addItem(mensagem.idMensagem);
					}
				}
			}
			if (listaIdMensagem.length > 0) {
				var metodo:String = "reenviarListaMensagemCip";
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados["listaIdMensagem"] = listaIdMensagem;
				var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO_MENSAGEM_DDA, metodo);
				servico.addEventListener(ResultEvent.RESULT, reenviarMensagemResult);
				servico.mensagemEspera = "Buscando definições do componente...";
				servico.reenviarListaMensagemCip(dto);
			}
		}
		
		private function validarReenvioMensagem(msg:Object, exibirMensagemErro:Boolean = true):Boolean {
			if(msg.statusEnvio == 1 || msg.statusEnvio == 2) {
				if(exibirMensagemErro) {
					MensagensComum.exibirMensagemAlerta(Mensagens.MSG037);
				}
				return false;
			} else if (!validarTipoMensagem(msg.codTipoMensagem)) {
				if(exibirMensagemErro) {
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG046, msg.codTipoMensagem));
				}
				return false;
			} else {
				return true;
			}
		}
		
		private function validarTipoMensagem(codTipoMensagem:String):Boolean {
			if (codTipoMensagem == TipoMensagemDDAVO.DDA0501) {
				return true;
			} else if (codTipoMensagem == TipoMensagemDDAVO.DDA0502) {
				return true;
			} else if (codTipoMensagem == TipoMensagemDDAVO.DDA0503) {
				return true;
			} else if (codTipoMensagem == TipoMensagemDDAVO.DDA0505) {
				return true;
			} else if (codTipoMensagem == TipoMensagemDDAVO.DDA0501E) {
				return true;
			} else if (codTipoMensagem == TipoMensagemDDAVO.DDA0502E) {
				return true;
			} else if (codTipoMensagem == TipoMensagemDDAVO.DDA0503E) {
				return true;
			} else if (codTipoMensagem == TipoMensagemDDAVO.DDA0505E) {
				return true;
			} else {
				return false
			}
		}
		
		private function reenviarMensagemResult(e:ResultEvent):void {
			MensagensComum.exibirMensagemInformacao(Mensagens.MSG038);
		}
		
		private function verificaItemSelecionado():Number {
			var selecionados:Number = 0;
			for each(var mensagem:Object in painelLista.tabelaPaginada.grdDados.dataProvider) {
				if(mensagem.selecionado) {
					selecionados++;
				}
				if(selecionados == 2) {
					return selecionados;
				}
			}
			return selecionados;
		}
		
		private function tratarBotaoDetalhar():void {
			if(this.verificaItemSelecionado() == 1) {
				this.btnDetalharMensagem.enabled = true;
			} else {
				this.btnDetalharMensagem.enabled = false;
			}
		}
			
		private function recuperarTipoMensagens():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var metodo:String = "listarTipoMensagens";
			dto.dados["origem"] = painelFiltro.cmbOrigem.selectedIndex;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO_MENSAGEM_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoObterDados);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.listarTipoMensagens(dto);
		}
		
		private function resultadoObterDados(event:ResultEvent):void{
			var listaResultado:ArrayCollection = new ArrayCollection; 
			listaResultado = event.result.dados.lista;
			for each (var tipoMensagem:TipoMensagemDDAVO in listaResultado) {
				tipoMensagem.descTipoMensagem = tipoMensagem.codTipoMensagem + " - " + tipoMensagem.descTipoMensagem; 
			}
			painelFiltro.cmbTipoMensagem.dataProvider = event.result.dados.lista;
			if(painelFiltro.cmbTipoMensagem.dropdown){
				painelFiltro.cmbTipoMensagem.dropdown.dataProvider = painelFiltro.cmbTipoMensagem.dataProvider;
			}
		}
		
		private function validarProcurar(evt:ProcurarEvent):void {
			if ((painelFiltro.dataHoraMensagemInicial.selectedDate == null && painelFiltro.dataHoraMensagemFinal.selectedDate == null)
				&& (painelFiltro.dataHoraCadastroInicial.selectedDate == null && painelFiltro.dataHoraCadastroFinal.selectedDate == null) 
				&& (painelFiltro.movimentoDataInicial.selectedDate == null && painelFiltro.movimentoDataFinal.selectedDate == null)){
				MensagensComum.exibirMensagemAlerta("Pelo menos um período deve ser informado.");
			} else if((!painelFiltro.dataHoraCadastroInicial.selectedDate && painelFiltro.dataHoraCadastroFinal.selectedDate) ||
				(!painelFiltro.movimentoDataInicial.selectedDate && painelFiltro.movimentoDataFinal.selectedDate) ||
				(!painelFiltro.dataHoraMensagemInicial.selectedDate && painelFiltro.dataHoraMensagemFinal.selectedDate)) {
				MensagensComum.exibirMensagemAlerta("A data inicial deve ser preenchida.");
			} else if ((painelFiltro.dataHoraCadastroInicial.selectedDate && !painelFiltro.dataHoraCadastroFinal.selectedDate) ||
						(painelFiltro.movimentoDataInicial.selectedDate && !painelFiltro.movimentoDataFinal.selectedDate) ||
						(painelFiltro.dataHoraMensagemInicial.selectedDate && !painelFiltro.dataHoraMensagemFinal.selectedDate)) {
				MensagensComum.exibirMensagemAlerta("A data final deve ser preenchida.");
			}else if((painelFiltro.dataHoraCadastroInicial.selectedDate && painelFiltro.dataHoraCadastroFinal.selectedDate &&
						painelFiltro.dataHoraCadastroInicial.selectedDate.getTime() > painelFiltro.dataHoraCadastroFinal.selectedDate.getTime()) ||
					  (painelFiltro.movimentoDataInicial.selectedDate && painelFiltro.movimentoDataFinal.selectedDate && 
						  painelFiltro.movimentoDataInicial.selectedDate.getTime() > painelFiltro.movimentoDataFinal.selectedDate.getTime()) ||
					  (painelFiltro.dataHoraMensagemInicial.selectedDate && painelFiltro.dataHoraMensagemFinal.selectedDate &&
						  painelFiltro.dataHoraMensagemInicial.selectedDate.getTime() > painelFiltro.dataHoraMensagemFinal.selectedDate.getTime())) {
				MensagensComum.exibirMensagemAlerta("A data final não pode ser menor do que a data inicial");
			} else if(validarPeriodo(painelFiltro.dataHoraCadastroInicial.selectedDate, painelFiltro.dataHoraCadastroFinal.selectedDate)){
				MensagensComum.exibirMensagemAlerta("O período da data cadastro não deve ser superior a 6 mesess.", painelFiltro.dataHoraCadastroFinal);
			}else if(validarPeriodo(painelFiltro.movimentoDataInicial.selectedDate, painelFiltro.movimentoDataFinal.selectedDate)){
				MensagensComum.exibirMensagemAlerta("O período da data de movimento não deve ser superior a 6 meses.", painelFiltro.movimentoDataFinal);
			}else if(validarPeriodo(painelFiltro.dataHoraMensagemInicial.selectedDate, painelFiltro.dataHoraMensagemFinal.selectedDate)){
				MensagensComum.exibirMensagemAlerta("O período da data da mensagem não deve ser superior a 6 meses.", painelFiltro.dataHoraMensagemFinal);
			}else{
				this.btnDetalharMensagem.enabled = false;
				this.btnImprimir.enabled = false;
				painelListaPainel.procuraSolicitada(evt);
			}
		}
		
		private function validarPeriodo(data1:Date, data2:Date):Boolean{
			if(data1 != null && data2 != null){
				var dataTMP:Date = DataUtil.somarDiasData(data1, 180);
				return !(dataTMP.getTime() > data2.getTime());
			}
			return false;
		}
		
		private function limparConsulta(evt:Event = null):void {
			listaMensagens.limparConteudo();
			listaMensagens.barraPaginacao.totalPaginas = listaMensagens.barraPaginacao.pagina = 0;
			btnImprimir.enabled = false;
			btnDetalharMensagem.enabled = false;
			
		}
		
		private function filtrarComboMensagem(evt:Event = null):void {
			this.recuperarTipoMensagens();
			this.descErro.visible = isOrigemSicoob() ? true : false;
			this.dtHrProtocolo.visible = isOrigemSicoob() ? true : false;
		}
		
		public function limparCampos(evt:Event = null):void {
			limparConsulta(evt);

			this.tratarBotaoDetalhar();
			
			painelFiltro.cmbOrigem.selectedIndex = 0;
			painelFiltro.cmbTipoMensagem.selectedIndex = 0;
			painelFiltro.cmbStatusEnvio.selectedIndex = 0;
			painelFiltro.dataHoraMensagemInicial.selectedDate = null;
			painelFiltro.dataHoraMensagemFinal.selectedDate = null;
			painelFiltro.movimentoDataInicial.selectedDate = null;
			painelFiltro.movimentoDataFinal.selectedDate = null;
			painelFiltro.dataHoraCadastroInicial.selectedDate = null;
			painelFiltro.dataHoraCadastroFinal.selectedDate = null;
			
			this.recuperarTipoMensagens();
		}
		
		private function tratarTab():void {
			var index:int = 1;
			painelFiltro.cmbOrigem.tabIndex = index++;
			painelFiltro.dataHoraMensagemInicial.tabIndex = index++;
			painelFiltro.dataHoraMensagemFinal.tabIndex = index++;
			painelFiltro.movimentoDataInicial.tabIndex = index++;
			painelFiltro.movimentoDataFinal.tabIndex = index++;
			painelFiltro.cmbTipoMensagem.tabIndex = index++;
			painelFiltro.btnProcurar.tabIndex = index++;
			painelFiltro.btnLimpar.tabIndex = index++;
			listaMensagens.grdDados.tabIndex = index++;
			listaMensagens.barraPaginacao.tabIndex = index++;
			btnDetalharMensagem.tabIndex = index++;
			barraBotoesListaCadastro.tabIndex = index++;
		}
		
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		/**
		 * Configura o DTO para executar a pesquisa.
		 */
		private function configurarDtoConsulta(dto:ConsultaDto):void {	
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var mensagemDDADTO:MensagemDDADTO = new MensagemDDADTO();
			var tipoMensagemVO:TipoMensagemDDAVO = painelFiltro.cmbTipoMensagem.selectedItem as TipoMensagemDDAVO;
			
			mensagemDDADTO.bolOrigemSicoob = painelFiltro.cmbOrigem.selectedItem.data;
			
			if (painelFiltro.cmbStatusEnvio.selectedItem) {
				mensagemDDADTO.statusEnvio = painelFiltro.cmbStatusEnvio.selectedItem.data;
			}
			
			if (tipoMensagemVO) {
				mensagemDDADTO.codTipoMensagem = tipoMensagemVO.codTipoMensagem;
			}
			
			if (painelFiltro.dataHoraMensagemInicial.selectedDate){
				mensagemDDADTO.dataHoraMensagemInicial = DateTimeBase.getDateTime(painelFiltro.dataHoraMensagemInicial.selectedDate);
			}

			if (painelFiltro.dataHoraMensagemFinal.selectedDate){
				mensagemDDADTO.dataHoraMensagemFinal = DateTimeBase.getDateTime(painelFiltro.dataHoraMensagemFinal.selectedDate);
			}
			
			if (painelFiltro.movimentoDataInicial.selectedDate){
				mensagemDDADTO.movimentoDataInicial = DateTimeBase.getDateTime(painelFiltro.movimentoDataInicial.selectedDate);
			}
			
			if (painelFiltro.movimentoDataFinal.selectedDate){
				mensagemDDADTO.movimentoDataFinal = DateTimeBase.getDateTime(painelFiltro.movimentoDataFinal.selectedDate);
			}

			if (painelFiltro.dataHoraCadastroInicial.selectedDate){
				mensagemDDADTO.dataHoraCadastroInicial = DateTimeBase.getDateTime(painelFiltro.dataHoraCadastroInicial.selectedDate);
			}

			if (painelFiltro.dataHoraCadastroFinal.selectedDate){
				mensagemDDADTO.dataHoraCadastroFinal = DateTimeBase.getDateTime(painelFiltro.dataHoraCadastroFinal.selectedDate);
			}
			
			reqDto.dados.dto = mensagemDDADTO;
			dto.filtro = reqDto;
		}
		
		private function detalharMensagem(evt:Event):void {
			var metodo:String = "recuperaMensagemDDA";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO_MENSAGEM_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, popUpDetalharMensagem);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.recuperaMensagemDDA(dto);
		}
		
		private function recuperaMensagemSelecionada():MensagemDDADTO {
			for each (var mensagem:Object in painelLista.tabelaPaginada.grdDados.dataProvider) {
				if(mensagem.selecionado) {
					return mensagem as MensagemDDADTO;
				}
			}
			return null;
		}
		
		private function popUpDetalharMensagem(event:ResultEvent):void {
			var	mensagemVO:MensagemDDAVO = event.result.dados.mensagemDDA as MensagemDDAVO;
			var exibirMensagemErro:Boolean = false;
			if(mensagemVO!=null){
				var tela:PopUpDetalharMensagem = new PopUpDetalharMensagem(mensagemVO, validarReenvioMensagem(dto, exibirMensagemErro));
				JanelaCobranca.abrirJanela(this, tela, Constantes.DETALHAR_MENSAGEM);
			}
		}
		
		private function imprimir(event:MouseEvent):void{
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.MONITORAMENTO_MENSAGEM, criarDTORelatorio(), null, PreImpressao.FORMATO_PDF)
		}
		
		/**
		 * Cria o DTO para geração do relatório.
		 */
		protected function criarDTORelatorio():MensagemDDADTO {
			var mensagemDTO:MensagemDDADTO = new MensagemDDADTO();
			mensagemDTO = listaMensagens.grdDados.selectedItem as MensagemDDADTO;
			return mensagemDTO;
		}
		
		public function isOrigemSicoob():Boolean{
			return !painelFiltro.cmbOrigem.selectedIndex == 1;
		}
	}
}