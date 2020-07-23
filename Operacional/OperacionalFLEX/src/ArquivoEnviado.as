package
{
	import flash.events.ContextMenuEvent;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	import flash.system.System;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuItem;
	
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ArquivoEnviadoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataPesquisaUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.arquivoenviado.ArquivoEnviadoView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.arquivoenviado.PopUpAlterarArquivo;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("RegistroVO", RegistroVO);
	public class ArquivoEnviado extends ArquivoEnviadoView
	{
		
		public static const ALTERAR_ARQUIVO:String = "ALTERAR ARQUIVO";
		
		private var _itemCopy:ContextMenuItem = new ContextMenuItem("Copiar Nome Arquivo");
		private var _menuGridMensagem:ContextMenu = new ContextMenu();
		
		public function ArquivoEnviado()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			painelFiltro.removeEventListener(ProcurarEvent.PROCURAR, painelListaBanco.procuraSolicitada);
			this.painelListaBanco.funcaoCriacaoDto = instanciarDtoConsulta;
			
			this.painelFiltro.btnProcurar.addEventListener(ProcurarEvent.PROCURAR, validarPesquisa);
			this.painelFiltro.cmbTipoMensagem.addEventListener(ListEvent.CHANGE, limparGrid);
			this.painelFiltro.descNomeArquivoEnviado.addEventListener(MouseEvent.CLICK, limparGrid);
			
			this.painelFiltro.dtHoraInicio.campoData.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dtHoraFim.campoData.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dtHoraInicio.campoHora.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dtHoraFim.campoHora.addEventListener(Event.CHANGE, limparGrid);
			
			this.painelFiltro.dtHoraEnvioInicio.campoData.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dtHoraEnvioFim.campoData.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dtHoraEnvioInicio.campoHora.addEventListener(Event.CHANGE, limparGrid);
			this.painelFiltro.dtHoraEnvioFim.campoHora.addEventListener(Event.CHANGE, limparGrid);
			
			this.painelFiltro.dataMovimento.addEventListener(Event.CHANGE, limparGrid);
			
			this.btnAlterar.addEventListener(MouseEvent.CLICK, popUpEditar);
			this.painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, limparCampos);
			
			this.listaArquivoEnviado.grdDados.addEventListener(MouseEvent.CLICK, tratarBotoesAcao);
			this.listaArquivoEnviado.grdDados.doubleClickEnabled = false;
			
			this.painelFiltro.dataMovimento.dataDefault = null;
			iniciarComponentes();
		}	
		
		private function iniciarComponentes():void {
			iniciarBotoes();
			carregarFiltros();
			menuContexto();
			
		}
		
		//--------------------------------------------------------------------------
		//  Inicializar botões.
		//--------------------------------------------------------------------------
		private function iniciarBotoes():void {
			//Trata botões de ação			
			btnAlterar.enabled = false;
			barraBotoesListaCadastro.exibirBotaoVisualizar = barraBotoesListaCadastro.exibirBotaoAlterar = barraBotoesListaCadastro.exibirBotaoIncluir = barraBotoesListaCadastro.exibirBotaoAjuda = barraBotoesListaCadastro.exibirBotaoExcluir = false ;
			var hBoxBarraBotoes:HBox = barraBotoesListaCadastro.getChildByName("botoes") as HBox;
			var btnFechar:Botao = hBoxBarraBotoes.getChildByName('btnFechar') as Botao;
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			this.listaArquivoEnviado.addEventListener(MouseEvent.CLICK, validaContexto);
			
		}
		//--------------------------------------------------------------------------
		//  FUNÇÃO MENU DE CONTEXTO COPIAR.
		//--------------------------------------------------------------------------
		private function menuContexto():void {
			_itemCopy.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, menuContextoCopiar);
			_menuGridMensagem.customItems.push(_itemCopy);
			listaArquivoEnviado.contextMenu = _menuGridMensagem;
		}
		
		private function validaContexto(e:MouseEvent):void {
			if(listaArquivoEnviado.grdDados.selectedItem){
				_itemCopy.enabled = true;
			}
		}
		
		private function menuContextoCopiar(e:ContextMenuEvent):void {
			if(listaArquivoEnviado.grdDados.selectedItem){
				System.setClipboard(listaArquivoEnviado.grdDados.selectedItem.descNomeArquivoEnviado);
			}
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos filtros de pesquisa.
		//--------------------------------------------------------------------------
		private function carregarFiltros():void {
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_ENVIADO, "carregarFiltrosArquivoEnviado");
			servico.addEventListener(ResultEvent.RESULT, retornoCarregarFiltros);
			servico.carregarFiltrosArquivoEnviado();			
		}
		
		private function retornoCarregarFiltros(resultEvent:ResultEvent):void {
			this.painelFiltro.cmbTipoMensagem.dataProvider = resultEvent.result.dados["lista"];
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Edição.
		//--------------------------------------------------------------------------
		private function popUpEditar(event:Event):void {
			var arquivoEnviadoDto:ArquivoEnviadoDTO = listaArquivoEnviado.grdDados.selectedItem as ArquivoEnviadoDTO;
			var tela:PopUpAlterarArquivo = new PopUpAlterarArquivo(arquivoEnviadoDto.idLogEnvioArquivodda, refazerPequisa);
			JanelaCobranca.abrirJanela(this, tela, ALTERAR_ARQUIVO);
		}
		//--------------------------------------------------------------------------
		//  Validar Procura.
		//--------------------------------------------------------------------------
		private function validarPesquisa(event:ProcurarEvent):void{
				if(validarPeriodo()){
					return;
				}else{
						this.limparGrid();
						this.painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
						this.painelListaBanco.procuraSolicitada(event);				
				}
		}
		
		//--------------------------------------------------------------------------
		//  Valida período de 6 mêses.
		//--------------------------------------------------------------------------
		public function validaDatasIguais(data1:IDateTime, data2:IDateTime):Boolean {
			var valorGrandeza:Number = DataUtil.compareDataTime(data1, data2);
			var isGrandeza:Boolean = valorGrandeza==0 || valorGrandeza == 1 ? true : false;			
			return isGrandeza; 
		}
		
		//--------------------------------------------------------------------------
		//  Valida período de 6 mêses e datas iguais.
		//--------------------------------------------------------------------------
		public function validarPeriodo():Boolean {
			var retorno:Boolean;
			
			var bolDataArquivoNull:Boolean = (this.painelFiltro.dtHoraInicio.campoData.selectedDate == null && this.painelFiltro.dtHoraFim.campoData.selectedDate == null);
			var bolDataEnvioNull:Boolean = (this.painelFiltro.dtHoraEnvioInicio.campoData.selectedDate == null && this.painelFiltro.dtHoraEnvioFim.campoData.selectedDate == null);
			
			if(bolDataArquivoNull && bolDataEnvioNull){
				retorno = false;
			}else{
				if(!bolDataArquivoNull){
					if(!DataPesquisaUtil.validaPeriodoDataPesquisa(this.painelFiltro.dtHoraInicio.campoData, this.painelFiltro.dtHoraFim.campoData)){
						retorno = true;	 
					}else{
						//Colocoar para quando as horas forem nulas nao entrar aqui
						if(validaDatasIguais(this.painelFiltro.dtHoraInicio.dateTime, this.painelFiltro.dtHoraFim.dateTime) && !(this.painelFiltro.dtHoraInicio.campoHora.text == "" && this.painelFiltro.dtHoraFim.campoHora.text == "")){
							MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Data Hora Arquivo Inicial"));
							retorno = true;
						} else{
							if((this.painelFiltro.dtHoraInicio.campoHora.text == "" && this.painelFiltro.dtHoraFim.campoHora.text == "")){
								this.painelFiltro.dtHoraFim.dataSelecionada.setHours(23);
								this.painelFiltro.dtHoraFim.dataSelecionada.setHours(23);
								this.painelFiltro.dtHoraFim.dataSelecionada.setMinutes(59);
								this.painelFiltro.dtHoraFim.dataSelecionada.setSeconds(59);
								
								this.painelFiltro.dtHoraInicio.dataSelecionada.setHours(00);
								this.painelFiltro.dtHoraInicio.dataSelecionada.setMinutes(00);
								this.painelFiltro.dtHoraInicio.dataSelecionada.setSeconds(00);
							}
						}
					}
				}
				if(!bolDataEnvioNull && !retorno){
					if(!DataPesquisaUtil.validaPeriodoDataPesquisa(this.painelFiltro.dtHoraEnvioInicio.campoData, this.painelFiltro.dtHoraEnvioFim.campoData)){
						retorno = true;	 
					}else{
						//Colocoar para quando as horas forem nulas nao entrar aqui
						if(validaDatasIguais(this.painelFiltro.dtHoraEnvioInicio.dateTime,this.painelFiltro.dtHoraEnvioFim.dateTime) && !(this.painelFiltro.dtHoraEnvioInicio.campoHora.text == "" && this.painelFiltro.dtHoraEnvioFim.campoHora.text == "")){
							MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Data Hora Envio Inicial"));
							retorno = true;
						}else{
							if((this.painelFiltro.dtHoraEnvioInicio.campoHora.text == "" && this.painelFiltro.dtHoraEnvioFim.campoHora.text == "")){
								this.painelFiltro.dtHoraEnvioFim.dataSelecionada.setHours(23);
								this.painelFiltro.dtHoraEnvioFim.dataSelecionada.setHours(23);
								this.painelFiltro.dtHoraEnvioFim.dataSelecionada.setMinutes(59);
								this.painelFiltro.dtHoraEnvioFim.dataSelecionada.setSeconds(59);
								
								this.painelFiltro.dtHoraEnvioInicio.dataSelecionada.setHours(00);
								this.painelFiltro.dtHoraEnvioInicio.dataSelecionada.setMinutes(00);
								this.painelFiltro.dtHoraEnvioInicio.dataSelecionada.setSeconds(00);
							}
						}
					}
				}
			}
			return retorno;
		}
		
		//--------------------------------------------------------------------------
		//  Limpar a pesquisa.
		//--------------------------------------------------------------------------
		public function limparCampos(evt:Event):void {
			this.painelFiltro.cmbTipoMensagem.selectedIndex = 0;
			this.painelFiltro.descNomeArquivoEnviado.text = null;
			
			painelFiltro.dtHoraInicio.dataSelecionada = null;
			painelFiltro.dtHoraFim.dataSelecionada = null;

			painelFiltro.dtHoraInicio.horaSelecionada = "";
			painelFiltro.dtHoraFim.horaSelecionada = "";
			
			painelFiltro.dtHoraEnvioInicio.dataSelecionada = null;
			painelFiltro.dtHoraEnvioFim.dataSelecionada = null;
			
			painelFiltro.dtHoraEnvioInicio.horaSelecionada = "";
			painelFiltro.dtHoraEnvioFim.horaSelecionada = "";
			
			painelFiltro.dataMovimento.dataDefault = null;
			painelFiltro.dataMovimento.selectedDate = null;
			
			painelFiltro.descNomeArquivoEnviado.text = null;
			
			limparDadosPesquisa();
			
			this.listaArquivoEnviado.grdDados.dataProvider = null;
			listaArquivoEnviado.barraPaginacao.totalPaginas = listaArquivoEnviado.barraPaginacao.pagina = 0;
			listaArquivoEnviado.paginaAtual = 1;
			desabilitaBotoesAcao();
		}
		
		public function limparGrid(evt:Event =null):void {
			desabilitaBotoesAcao();
			this.listaArquivoEnviado.limparConteudo();
			listaArquivoEnviado.barraPaginacao.totalPaginas = listaArquivoEnviado.barraPaginacao.pagina = 0;
			listaArquivoEnviado.paginaAtual = 1;
			this.listaArquivoEnviado.grdDados.dataProvider = null;
		}
		
		//--------------------------------------------------------------------------
		//  Instanci DTO de Consulta para Paginação.
		//--------------------------------------------------------------------------
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		
		//--------------------------------------------------------------------------
		//  Configura o DTO para executar a pesquisa.
		//--------------------------------------------------------------------------
		private function configurarDtoConsulta(dto:ConsultaDto):void {	
			var reqDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var arquivoEnviadoDto:ArquivoEnviadoDTO = new ArquivoEnviadoDTO();
			
			
			if (this.painelFiltro.cmbTipoMensagem.selectedItem) {
				arquivoEnviadoDto.codTipoMensagem = painelFiltro.cmbTipoMensagem.selectedItem.codTipoMensagem;
			}
			
			if(this.painelFiltro.dtHoraInicio.dateTime){
				arquivoEnviadoDto.dataArquivoInicio = painelFiltro.dtHoraInicio.dateTime;
			}
			
			if(this.painelFiltro.dtHoraFim.dateTime){
				arquivoEnviadoDto.dataArquivoFim = painelFiltro.dtHoraFim.dateTime;
			}
			
			if(this.painelFiltro.dtHoraEnvioInicio.dateTime){
				arquivoEnviadoDto.dataEnvioInicio = painelFiltro.dtHoraEnvioInicio.dateTime;
			}
			
			if(this.painelFiltro.dtHoraEnvioFim.dateTime){
				arquivoEnviadoDto.dataEnvioFim = painelFiltro.dtHoraEnvioFim.dateTime;
			}
			
			if(this.painelFiltro.dataMovimento.selectedDate){
				arquivoEnviadoDto.dataMovimento = DateTimeBase.getDateTime(painelFiltro.dataMovimento.selectedDate);
			}
			
			if(StringUtil.trim(this.painelFiltro.descNomeArquivoEnviado.text)){
				arquivoEnviadoDto.descNomeArquivoEnviado = this.painelFiltro.descNomeArquivoEnviado.text;
			}
			
			reqDto.dados.dto = arquivoEnviadoDto;
			dto.filtro = reqDto;
		}
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
		//--------------------------------------------------------------------------
		//  CallBack Edição ou Inclusão.
		//--------------------------------------------------------------------------
		private function refazerPequisa():void {
			listaArquivoEnviado.grdDados.selectedItem = null;
			tratarBotoesAcao(null);
			this.painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
			limparGrid(null);
			painelListaBanco.procuraSolicitada(new ProcurarEvent);
		}
		
		//--------------------------------------------------------------------------
		//  Tratar botões de ação.
		//--------------------------------------------------------------------------
		private function tratarBotoesAcao(event:MouseEvent):void {
			if(listaArquivoEnviado.grdDados.selectedItem){
				habilitaBotoesAcao();
			}else{
				desabilitaBotoesAcao();
			}
		}
		
		//--------------------------------------------------------------------------
		//  Habilita e Desabilita os campos do lado da tabela.
		//--------------------------------------------------------------------------
		private function habilitaBotoesAcao():void {
			this.btnAlterar.enabled = true;
		}
		
		private function desabilitaBotoesAcao():void {
			this.btnAlterar.enabled = false;
		}
	}
}