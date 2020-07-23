package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.core.Application;
	import mx.core.Container;
	import mx.managers.IFocusManagerContainer;
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.FormatUtil;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno.SelecionarPessoaExternoCAPES;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno.util.ConstantesProcurarPessoaExternoCAPES;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno.vo.ProcurarPessoaExternoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.pagador.BotoesOpcaoPagador;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.pagador.ManterPagadorDDA;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.atendimento.DDATelaPlataformaAtendimentoBase;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class PlataformaPagadorDDA extends DDATelaPlataformaAtendimentoBase {
		
		private var telaPagador:ManterPagadorDDA = new ManterPagadorDDA(obterDadosPagador);
		private var telaSelecionarAgregado:SelecionarPessoaExternoCAPES = new SelecionarPessoaExternoCAPES(); 
		private var botoesOpcoes:BotoesOpcaoPagador = new BotoesOpcaoPagador();
		
		public function PlataformaPagadorDDA() {
			super();
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}
		
		private function init(event:Event):void{
			
			this.textoAcao = "ADESÃO/CANCELAMENTO DDA";
			this.textoModulo = "ADESÃO/CANCELAMENTO DDA";
			this.iconModulo = "br/com/bancoob/imagens/icos/ListaAplicativos/sdda_32.png";
			
			incluirBotoesAdicionais();
			
			this.adicionaTela(telaPagador);
			this.addEventListener(Modulo.REGISTRO_CARREGADO, carregarTelaPagador);
			configurarTelaSelecionarAgregado();
		}
		
		protected function carregarTelaPagador(evt:Event):void {
			telaPagador.pagador = pagador;
			telaPagador.configurarDestinosServicos(destinoDDA());
			exibirTela();
		}   
		
		//--------------------------------------------------------------------------
		//  Listener 
		//--------------------------------------------------------------------------		
		protected override function registroGravado(event:Event):void {}		
		
		protected override function voltarClicado(event:Event=null):void {}
		
		//--------------------------------------------------------------------------
		//  Métodos sobrescritos.
		//--------------------------------------------------------------------------		
		private function exibirTela():void {					
			super.selecionaTela(telaPagador);
			
			habilitarControles(telaPagador as Container);
			systemManager.activate(Application.application as IFocusManagerContainer);			
		}	
		
		//--------------------------------------------------------------------------
		//  Métodos auxiliares
		//--------------------------------------------------------------------------
		// Funcionamento comum da Plataforma de atendimento.				
		protected override function habilitarControles(cont:Container):void {
			super.habilitarControles(cont);
		}
		
		private function incluirBotoesAdicionais(): void {
			this.setBotoesAdicionais(botoesOpcoes);
			this.cvBotoesOpcionais.height= 400;
			botoesOpcoes.btnAtualizar.addEventListener(MouseEvent.CLICK, atualizarDados);
			botoesOpcoes.btnAdesao.addEventListener(MouseEvent.CLICK, confirmarAdesao);
			botoesOpcoes.btnCancelamento.addEventListener(MouseEvent.CLICK, confirmarCancelamento);
			botoesOpcoes.btnExcluirAgregado.addEventListener(MouseEvent.CLICK, confirmarExclusaoAgregado);
			botoesOpcoes.btnIncluirAgregado.addEventListener(MouseEvent.CLICK, exibirProcurarPessoasCAPES);
			botoesOpcoes.btnContratoAdesao.addEventListener(MouseEvent.CLICK, imprimirTermoContrato);
			botoesOpcoes.btnReimpressao.addEventListener(MouseEvent.CLICK, imprimirTermoPagadorEletronico);
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de Impressão
		//--------------------------------------------------------------------------
		private function imprimirTermoContrato(e:MouseEvent):void {
			telaPagador.imprimirTermoContrato(destinoDDA());
		}
		
		private function imprimirTermoPagadorEletronico(e:MouseEvent):void {
			telaPagador.imprimirTermoPagadorEletronico(destinoDDA())
		}
		
		private function atualizarDados(e:MouseEvent):void {
			obterDadosPagador(e);
			telaPagador.abaReimpressao.dadosImpressao = null;
			telaPagador.cancelarSelecao();
		}
		
		private function confirmarAdesao(e:MouseEvent):void {
			if (pagador.listaNumCCO.length == 0) {
				MensagensComum.exibirMensagemAlerta(Mensagens.MSG067);
			} else {
				MensagensComum.exibirMensagemConfirmacao(Mensagens.MSG062, null, telaPagador.enviarMensagemAdesao);
			}
		}
		
		private function confirmarCancelamento(e:MouseEvent):void {
			if (pagador.bolPagadorEletronico) {
				MensagensComum.exibirMensagemConfirmacao(Mensagens.MSG060, null, telaPagador.enviarMensagemCancelarAdesao);
			} else {
				MensagensComum.exibirMensagemAlerta(Mensagens.MSG063);
			}
		}
	
		private function confirmarExclusaoAgregado(e:MouseEvent):void {
			if (telaPagador.verificaSeHaAlgumItemSelecionado()) {
				MensagensComum.exibirMensagemConfirmacao(Mensagens.MSG061, null, telaPagador.enviarMensagemExcluirAgregado);
			} else {
				MensagensComum.exibirMensagemAlerta(Mensagens.MSG064);
			}
		}
		
		private function confirmarInclusaoAgregado(e:ObjetoEvent):void {
			var numCpfCnpj:String = telaPagador.numCpfCnpjAgregado = (e.objeto as ProcurarPessoaExternoVO).cpfCnpj;
			if (numCpfCnpj != pagador.numCpfCnpj) {
				MensagensComum.exibirMensagemConfirmacao(MensagensComum.formatar(Mensagens.MSG065, FormatUtil.formataCPFCNPJ(numCpfCnpj)), null, telaPagador.enviarMensagemIncluirAgregado);
			} else {
				MensagensComum.exibirMensagemAlerta(Mensagens.MSG066);
			}
		}
		
		public function exibirProcurarPessoasCAPES(e:MouseEvent):void {
			if (pagador.bolPagadorEletronico) {
				var janela:Janela = new Janela();
				janela.icone = ConstantesComum.ICONE_JANELA;
				janela.title = "Procurar Agregado";
				janela.addChild(telaSelecionarAgregado); 
				janela.janelaPai = this;
				janela.abrir(this, true);
				janela.centralizarJanela();
			} else {
				MensagensComum.exibirMensagemAlerta(Mensagens.MSG063);
			}
		}
		
		public function configurarTelaSelecionarAgregado():void {
			telaSelecionarAgregado.tipoCliente = ConstantesProcurarPessoaExternoCAPES.PESQUISAR_TODOS;
			telaSelecionarAgregado.configurarDestino(destinoCAPES());
			telaSelecionarAgregado.compartilhadosBancoob = false;
			telaSelecionarAgregado.addEventListener(REGISTRO_SELECIONADO, confirmarInclusaoAgregado);
		}
		
	}
}