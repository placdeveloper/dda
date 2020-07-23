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

	public class DDA0200 extends DDA0200View implements IMensagemDDA {
		
		private var servicoCadastro:ServicoJava;
		
		public function DDA0200() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
			initServico();
		}
		
		private function initCampos():void {
			cbTipoTransacao.dataProvider = DominioCIP.TIPO_TRANSACAO.slice();
			cbTipoConsulta.dataProvider = DominioCIP.TIPO_CONSULTA.slice();
			cbTipoRetorno.dataProvider = DominioCIP.TIPO_RETORNO.slice();
		}
		
		private function initServico():void {
			servicoCadastro = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, "cadastrarMensagemDDASerie0200");
			servicoCadastro.addEventListener(ResultEvent.RESULT, retornoCadastroMensagem);
		}
		
		public function validarDados():Boolean {
			if (txtNumIdentcLanc.valor != 0 && !cbTipoTransacao.selectedItem) {
				MensagensComum.exibirMensagemAlerta("Tipo Transação não informado.", cbTipoTransacao);
				return false;
			} else if (!dtIniApurc.selectedDate) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Data Início Apuração"), dtIniApurc);
				return false;
			} else if (!dtFimApurc.selectedDate) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Data Fim Apuração"), dtFimApurc);
				return false;	
			} else if (DataUtil.compareData(dtIniApurc.selectedDate, dtFimApurc.selectedDate) == 1) {
				MensagensComum.exibirMensagemAlerta("Dados inválidos. Data Início maior que Data Fim.", dtIniApurc);
				return false;
			} 
			return true;
		}
		
		public function limparDados(e:Event=null):void {
			txtNumIdentcLanc.text = "";
			cbTipoTransacao.selectedIndex = 0;
			cbTipoConsulta.selectedIndex = 0;
			cbTipoRetorno.selectedIndex = 0;
			dtIniApurc.selectedDate = new Date();
			dtFimApurc.selectedDate = new Date();
		}
		
		public function salvar():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["codTipoMensagem"] = ConstantesTipoMensagem.DDA0200;
			dto.dados["cadastroDto"] = configuraDto();
			servicoCadastro.cadastrarMensagemDDASerie0200(dto);
		}
		
		private function configuraDto():CadastroSerie0200DTO {
			var cadastroDto:CadastroSerie0200DTO = new CadastroSerie0200DTO();
			cadastroDto.dataInicioApuracao = DateTimeBase.getDateTime(dtIniApurc.selectedDate);
			cadastroDto.dataFimApuracao = DateTimeBase.getDateTime(dtFimApurc.selectedDate);
			if (txtNumIdentcLanc.valor != 0) {
				cadastroDto.numIdentLancamento = txtNumIdentcLanc.valor;
			}
			if (cbTipoTransacao.selectedItem) {
				cadastroDto.codTipoTransacao = cbTipoTransacao.selectedItem.data;
			}
			cadastroDto.codTipoConsulta = cbTipoConsulta.selectedItem.data;
			cadastroDto.codTipoRetorno = cbTipoRetorno.selectedItem.data;
			return cadastroDto;
		}
		
		private function retornoCadastroMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG002, "Mensagem"),limparDados);
		}
	}
}