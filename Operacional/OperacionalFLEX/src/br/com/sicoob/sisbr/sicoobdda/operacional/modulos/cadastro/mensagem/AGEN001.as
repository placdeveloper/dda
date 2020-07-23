package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	public class AGEN001 extends AGEN001View implements IMensagemDDA {
		
		
		private var servicoCadastro:ServicoJava;
		private var msg:String;
		
		private const MSG_PADRAO:String = "IF Requisita teste de conectividade"
		
		
		public function AGEN001() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			servicoCadastro = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, "cadastrarMensagemAGEN001");
			servicoCadastro.addEventListener(ResultEvent.RESULT, retornoCadastroMensagem);
			chPadrao.addEventListener(MouseEvent.CLICK,defineTexto);
			txMensagem.text = MSG_PADRAO;
			txMensagem.enabled = false;
		}
		
		
		private function defineTexto(event:MouseEvent):void{
			if (chPadrao.selected) {
				txMensagem.text = MSG_PADRAO;
				txMensagem.enabled = false;
				
			} else {
				txMensagem.enabled = true;
				txMensagem.text = "";
				txMensagem.setFocus();
			}
		}
		
		public function salvar():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["mensagem"] = txMensagem.text;
			servicoCadastro.cadastrarMensagemAGEN001(dto);
		}
			
		public function limparDados(e:Event=null):void{
			
		}
		
		public function validarDados():Boolean {
			var msg:String = StringUtil.trim(txMensagem.text);
			if (msg.length == 0) {
				MensagensComum.exibirMensagemAlerta("Quando não utilizar a menagem padrão o campo mensagem customizada deve ser preenchido!");	
				txMensagem.setFocus();
				return false;
			}
			return true;
		}
		
		private function retornoCadastroMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG002, "Mensagem"), limparDados);
		}
	}
}


