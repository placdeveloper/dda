package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem
{
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.CadastroSerie0200DTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesTipoMensagem;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DominioCIP;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class DDA0214 extends DDA0214View implements IMensagemDDA {
		
		private var servicoCadastro:ServicoJava;
		
		public function DDA0214() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
			initServico();
		}
		
		private function initCampos():void {
			cbTipoMensagemArquivo.dataProvider = DominioCIP.TIPO_MENSAGEM_ARQUIVO.slice();
			cbCodTipoMensagem.dataProvider = ConstantesTipoMensagem.TIPO_MENSAGEM_DDA0214.slice();
			cbTipoRetorno.dataProvider = DominioCIP.TIPO_RETORNO.slice();
		}
		
		private function initServico():void {
			servicoCadastro = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, "cadastrarMensagemDDASerie0200");
			servicoCadastro.addEventListener(ResultEvent.RESULT, retornoCadastroMensagem);
		}
		
		public function validarDados():Boolean {
			if (!dtRef.selectedDate) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Data Refência"), dtRef);
				return false;
			} else if (!dtHoraIni.validar() || !dtHoraFim.validar()) {
				MensagensComum.exibirMensagemAlerta("Dados inválidos. Formato de data invalido.", dtHoraIni);
				return false;
			} else if (dtHoraIni.dateTime && dtHoraFim.dateTime && DataUtil.compareData(dtHoraIni.dateTime.data, dtHoraFim.dateTime.data) == 1) {
				MensagensComum.exibirMensagemAlerta("Dados inválidos. Data Início maior que Data Fim.", dtHoraIni);
				return false;
			} else if (cbTipoMensagemArquivo.selectedItem && cbCodTipoMensagem.selectedItem) {
				MensagensComum.exibirMensagemAlerta("Os campos Tipo e Código não podem ser informados ao mesmo tempo.", cbTipoMensagemArquivo);
				return false;
			}
			return true;
		}
		
		public function limparDados(e:Event=null):void {
			cbTipoMensagemArquivo.selectedIndex = 0;
			cbCodTipoMensagem.selectedIndex = -1;
			cbTipoRetorno.selectedIndex = 0;
			dtHoraIni.limpar();
			dtHoraFim.limpar();
			dtRef.selectedDate = new Date();
		}
		
		public function salvar():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["codTipoMensagem"] = ConstantesTipoMensagem.DDA0214;
			dto.dados["cadastroDto"] = configuraDto();
			servicoCadastro.cadastrarMensagemDDASerie0200(dto);
		}
		
		private function configuraDto():CadastroSerie0200DTO {
			var cadastroDto:CadastroSerie0200DTO = new CadastroSerie0200DTO();
			cadastroDto.dataInicioApuracao = dtHoraIni.dateTime;
			cadastroDto.dataFimApuracao = dtHoraFim.dateTime;
			if (cbCodTipoMensagem.selectedItem) {
				cadastroDto.codTipoMensagem = cbCodTipoMensagem.selectedItem.codTipo;
			}
			if (cbTipoMensagemArquivo.selectedItem) {
				cadastroDto.tipoMensagemArquivo = cbTipoMensagemArquivo.selectedItem.data;
			}
			cadastroDto.codTipoRetorno = cbTipoRetorno.selectedItem.data;
			cadastroDto.dataHoraRef = DateTimeBase.getDateTime(dtRef.selectedDate);
			return cadastroDto;
		}
		
		private function retornoCadastroMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG002, "Mensagem"), limparDados);
			limparDados();
		}
	}
}