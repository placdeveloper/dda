package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem
{
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesTipoMensagem;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DominioCIP;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.MensagemDDABeneficiarioVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class DDA0504 extends DDA0504View implements IMensagemDDA {
		
		private var servicoCadastro:ServicoJava;
		
		public function DDA0504() {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		
		private function init(e:FlexEvent):void {
			initServico();
			initCampos();
		}
		
		private function initCampos():void {
			cbSitRelParticipante.dataProvider = DominioCIP.SIT_REL_PARTICIPANTE.slice();
		}
		
		private function initServico():void {
			servicoCadastro = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, "cadastrarMensagemDDABeneficiario");
			servicoCadastro.addEventListener(ResultEvent.RESULT, retornoCadastroMensagem);
		}
		
		public function validarDados():Boolean {
			return pnlPessoa.validarDados();
		}
		
		public function limparDados(e:Event=null):void {
			pnlPessoa.limparDados();
			cbSitRelParticipante.selectedIndex = 0;
		}
		
		public function salvar():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["codTipoMensagem"] = ConstantesTipoMensagem.DDA0504;
			dto.dados["mensagemVO"] = configurarMensagem();
			servicoCadastro.cadastrarMensagemDDABeneficiario(dto);
		}
		
		private function retornoCadastroMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG002, "Mensagem"), limparDados);
			limparDados();
		}
		
		private function configurarMensagem():MensagemDDABeneficiarioVO {
			var mensagem:MensagemDDABeneficiarioVO = new MensagemDDABeneficiarioVO();
			if (cbSitRelParticipante.selectedItem) {
				mensagem.codSituacaoRelacionamentoBeneficiario = cbSitRelParticipante.selectedItem.data;
			}
			mensagem.codTipoPessoaBeneficiario = pnlPessoa.codTipoPessoa;
			mensagem.numCpfCnpjBeneficiario = pnlPessoa.cpfCnpj;
			return mensagem;
		}
	}
}