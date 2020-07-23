package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem
{
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.DominioCadastroMensagemDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesTipoMensagem;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DominioCIP;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.MensagemDDAConsultaBoletoVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class DDA0106 extends DDA0106View implements IMensagemDDA {
		
		private var servicoCadastro:ServicoJava;
		
		private const PROPRIO:String = "P";
		private const TERCEIRO:String = "T";
		
		public function DDA0106() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initServico();
		}
		
		private function initServico():void {
			servicoCadastro = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, "cadastrarMensagemDDAConsultaBoleto");
			servicoCadastro.addEventListener(ResultEvent.RESULT, retornoCadastroMensagem);
			
			cbTipoFiltro.dataProvider = DominioCIP.TIPO_FILTRO_BOLETO.slice();
			cbTipoFiltro.addEventListener(Event.CHANGE, selecionarTipoFiltro);
			cbTipoFiltro.selectedIndex = -1;
			
			obterDominio();				
		}
		
		private function selecionarTipoFiltro(e:Event):void {
			var idTipo:Number = e.target.selectedItem ? e.target.selectedItem.data : -1;
			if (idTipo == 0) {
				canvasNumCodBarras.visible = canvasNumCodBarras.enabled = true;
				canvasNumIdentBoleto.visible = canvasNumIdentBoleto.enabled = false;
				limparNumCodBarras();
			} else if (idTipo == 1) {
				canvasNumIdentBoleto.visible = canvasNumIdentBoleto.enabled = true;
				canvasNumCodBarras.visible = canvasNumCodBarras.enabled = false;
				limparNumIdentBoleto();
			}
		}
		
		private function obterDominio():void {
			var metodo:String = "obterDominioCadastroMensagem";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoObterDominioBoleto);
			servico.mensagemEspera = "Buscando detalhe...";
			servico.obterDominioCadastroMensagem();
		}
		
		private function retornoObterDominioBoleto(e:ResultEvent):void {
			var dominioDTO:DominioCadastroMensagemDTO = e.result.dados.dominioDTO as DominioCadastroMensagemDTO;
			cbTipoSituacaoBoleto.dataProvider = dominioDTO.listaSituacaoBoleto
			
			cbTipoConsultaBoleto.dataProvider = dominioDTO.listaTipoBoletoConsultado;
			cbTipoConsultaBoleto.selectedIndex = -1;
		}
		
		public function validarDados():Boolean {
			if (!cbTipoFiltro.selectedItem) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.MSG032);
				return false;
			} else if (cbTipoFiltro.selectedItem.data == 0) {
				if (txtNumCodigoBarras.text.length == 0 ) {
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "código de barras"));
					return false;
				} else if (!cbTipoSituacaoBoleto.selectedItem) {
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "situação boleto"));
					return false;
				}
			} else if (txtNumIdentBoletoInicial.text.length == 0 || txtNumIdentBoletoFinal.text.length == 0) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.MSG033);
				return false;
			} else if (txtNumIdentBoletoFinal.length < txtNumIdentBoletoInicial.length) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.MSG034);
				return false;
			} else if (!cbTipoConsultaBoleto.selectedItem) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.MSG036);
				return false;
			}
			return true;
		}
		
		public function limparDados(e:Event=null):void {
			cbTipoFiltro.selectedIndex = -1;
			limparNumCodBarras()
			limparNumIdentBoleto();
		}
		
		private function limparNumCodBarras():void {
			txtNumCodigoBarras.text = "";
			cbTipoSituacaoBoleto.selectedIndex = -1;
		}
		
		private function limparNumIdentBoleto():void {
			txtNumIdentBoletoInicial.text = "";
			txtNumIdentBoletoFinal.text = "";
			cbTipoConsultaBoleto.selectedIndex = -1;
		}
		
		public function salvar():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["codTipoMensagem"] = ConstantesTipoMensagem.DDA0106;
			dto.dados["mensagemVO"] = configurarMensagem();
			servicoCadastro.cadastrarMensagemDDAConsultaBoleto(dto);
		}
		
		private function retornoCadastroMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG002, "Mensagem"), limparDados);
		}
		
		private function configurarMensagem():MensagemDDAConsultaBoletoVO {
			var mensagem:MensagemDDAConsultaBoletoVO = new MensagemDDAConsultaBoletoVO();
			if (cbTipoFiltro.selectedItem.data == 0) {
				mensagem.numCodigoBarra = txtNumCodigoBarras.text; 		
				mensagem.codTipoBoletoConsultado = obtemTipoBoletoConsultado(mensagem.numCodigoBarra);
				mensagem.codSituacaoBoleto = cbTipoSituacaoBoleto.selectedItem.codSituacaoBoleto;
			} else {
				mensagem.numIdentBoletoInicialStr = txtNumIdentBoletoInicial.text;
				mensagem.numIdentBoletoFinalStr = txtNumIdentBoletoFinal.text;
				mensagem.codTipoBoletoConsultado = cbTipoConsultaBoleto.selectedItem.codTipoBoletoConsultado;
			}
			return mensagem;
		}
		
		private function obtemTipoBoletoConsultado(numCodigoBarra:String):String {
			var numeroBanco:Number = Number(numCodigoBarra.substr(0,3));
			return numeroBanco == ConstantesComum.BANCO_DEPOSITARIO_SICOOB ? PROPRIO : TERCEIRO;
		}
	}
}