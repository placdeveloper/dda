package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem
{
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DominioCIP;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class DDA0401 extends DDA0401View implements IMensagemDDA {
		
		private var servicoCadastro:ServicoJava;
		
		public function DDA0401() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
			initServico();
		}
		
		private function initCampos():void {
			cbTipoHorario.dataProvider = DominioCIP.TIPO_HORARIO.slice();
		}
		
		private function initServico():void {
			servicoCadastro = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, "cadastrarMensagemDDA0401");
			servicoCadastro.addEventListener(ResultEvent.RESULT, retornoCadastroMensagem);
		}
		
		public function validarDados():Boolean {
			if (!dtReferencia.selectedDate) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Data Referência"), dtReferencia);
				return false;
			} 
			return true;
		}
		
		public function limparDados(e:Event=null):void {
			cbTipoHorario.selectedIndex = 0;
			dtReferencia.selectedDate = new Date();
		}
		
		public function salvar():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			if (cbTipoHorario.selectedItem) {
				dto.dados["codTipoHorario"] = cbTipoHorario.selectedItem.data;
			}
			dto.dados["dataReferencia"] = DateTimeBase.getDateTime(dtReferencia.selectedDate);
			servicoCadastro.cadastrarMensagemDDA0401(dto);
		}
		
		private function retornoCadastroMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG002, "Mensagem"), limparDados);
			limparDados();
		}
	}
}