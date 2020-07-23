package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem
{
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class DDA0110 extends DDA0110View implements IMensagemDDA {
		
		private var servicoCadastro:ServicoJava;
		
		public function DDA0110() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initServico();
		}
		
		private function initServico():void {
			servicoCadastro = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, "cadastrarMensagemDDA0110");
			servicoCadastro.addEventListener(ResultEvent.RESULT, retornoCadastroMensagem);
		}
		
		public function validarDados():Boolean {
			if (txtNumCodigoBarras.length != 44) {
				MensagensComum.exibirMensagemAlerta("Código de Barras inválido.", txtNumCodigoBarras);
				return false; 
			} 
			return true;
		}
		
		public function limparDados(e:Event=null):void {
			txtNumCodigoBarras.text = "";
		}
		
		public function salvar():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["numCodBarras"] = txtNumCodigoBarras.text;
			servicoCadastro.cadastrarMensagemDDA0110(dto);
		}
		
		private function retornoCadastroMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG002, "Mensagem"), limparDados);
		}
	}
}