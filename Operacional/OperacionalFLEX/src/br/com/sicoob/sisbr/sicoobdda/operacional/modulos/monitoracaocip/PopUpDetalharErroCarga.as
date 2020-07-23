package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.monitoracaocip
{
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.LogErroCargaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;

	public class PopUpDetalharErroCarga extends PopUpDetalharErroCargaView 	{
		
		private var _logErroCargaDTO:LogErroCargaDTO;
		
		public function PopUpDetalharErroCarga(logErroCargaDTO:LogErroCargaDTO)
		{
			super();
			this._logErroCargaDTO = logErroCargaDTO;
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			iniciarCampos();
		}
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
		/**
		 * Define estado inicial dos campos do formulário.
		 */
		private function iniciarCampos():void {
			this.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btnReenviar.addEventListener(MouseEvent.CLICK, reenviarMensagemContingencia);
			
			this.lblDataCadastro.text = this._logErroCargaDTO.dataHoraCadastro ? FormataData.formataData(this._logErroCargaDTO.dataHoraCadastro.data) : "";
			this.lblNumCPFCNPJ.text = this._logErroCargaDTO.numCPFCNPJ ? FormatUtil.formataCPFCNPJ(this._logErroCargaDTO.numCPFCNPJ) : "";
			
			this.recuperaRegistroBeneficiarioErro();
		}
		
		private function reenviarMensagemContingencia(e:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao(Mensagens.MSG043, null, realizarContingencia); 
		}
		
		private function realizarContingencia(e:MouseEvent):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["idLogErro"] = this._logErroCargaDTO.idLogErro;
			dto.dados["numCPFCNPJ"] = this._logErroCargaDTO.numCPFCNPJ;
			dto.dados["listaCooperativa"] = this._logErroCargaDTO.listaCooperativa;
			var metodo:String = "reenviarBeneficarioPorMensagem";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MENSAGENS_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoContingencia);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.reenviarBeneficarioPorMensagem(dto);
		}
		
		private function retornoContingencia(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG044);
			this.btnReenviar.enabled = false;
		}
		
		private function recuperaRegistroBeneficiarioErro():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["idLogErro"] = this._logErroCargaDTO.idLogErro;
		
			var metodo:String = "recuperaRegistroErroCarga";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MENSAGENS_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoRegistroLogErro);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.recuperaRegistroErroCarga(dto);
		}
		
		private function retornoRegistroLogErro(e:ResultEvent):void {
			var logDTO:LogErroCargaDTO = e.result.dados.logErroDTO as LogErroCargaDTO;
			if(logDTO.listaErroCarga != null) {
				this.gridErroCip.dataProvider = logDTO.listaErroCarga;
			}
			if(logDTO.listaCooperativa != null) {
				this.gridCooperativa.dataProvider = logDTO.listaCooperativa;
				this._logErroCargaDTO.listaCooperativa = logDTO.listaCooperativa;
			}
		}
		
		private function retornoBeneficiarioErro(e:ResultEvent):void {
			this.btnReenviar.addEventListener(MouseEvent.CLICK, reenviarMensagemContingencia);
			this.btnReenviar.enabled = false;
		}
		
	}
}