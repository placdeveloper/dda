package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem
{
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesTipoMensagem;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.MensagemDDAPagadorVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class DDA0002 extends DDA0002View implements IMensagemDDA {
		
		private var servicoCadastro:ServicoJava;
		
		public function DDA0002() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initServico();
		}
		
		private function initServico():void {
			servicoCadastro = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, "cadastrarMensagemDDAPagador");
			servicoCadastro.addEventListener(ResultEvent.RESULT, retornoCadastroMensagem);
		}
		
		public function validarDados():Boolean {
			return pnlPessoa.validarDados();
		}
		
		public function limparDados(e:Event=null):void {
			pnlPessoa.limparDados();
		}
		
		public function salvar():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["codTipoMensagem"] = ConstantesTipoMensagem.DDA0002;
			dto.dados["mensagemVO"] = configurarMensagem();
			servicoCadastro.cadastrarMensagemDDAPagador(dto);
		}
		
		private function retornoCadastroMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG002, "Mensagem"), limparDados);
			limparDados();
		}
		
		private function configurarMensagem():MensagemDDAPagadorVO {
			var mensagem:MensagemDDAPagadorVO = new MensagemDDAPagadorVO();
			mensagem.codTipoPessoaPagador = pnlPessoa.codTipoPessoa;
			mensagem.numCpfCnpjPagador = pnlPessoa.cpfCnpj;
			mensagem.bolPagadorEletronico = true;
			return mensagem;
		}
		
	}
}