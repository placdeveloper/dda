package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.monitoracaocip
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MensagemDDADTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ObjectUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.MensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpDetalharMensagem extends PopUpDetalharMensagemView
	{
		
		private var mensagemDDAVO:MensagemDDAVO;
		private var _bolReenviarMensagem:Boolean;
		
		public function PopUpDetalharMensagem(mensagemVO:MensagemDDAVO, bolReenviarMensagem:Boolean)
		{
			super();
			this.mensagemDDAVO = mensagemVO;
			this._bolReenviarMensagem = bolReenviarMensagem;
		}
		

		protected override function init(event:FlexEvent):void {
			super.init(event);
			iniciarCampos();
			iniciarBotoes();
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
		private function iniciarCampos():void	{
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			lblNome.text = this.mensagemDDAVO.tipoMensagemDDA.codTipoMensagem;
			txtMensagem.text = ObjectUtil.isNullOrBlank(this.mensagemDDAVO.xmlMensagem) ? "" :this.identaXml(this.mensagemDDAVO.xmlMensagem) ;
			if(mensagemDDAVO.mensagemOrigem){
				txtMensagemOrigem.text =  this.identaXml(this.mensagemDDAVO.mensagemOrigem.xmlMensagem);
			}
		}
		
		private function iniciarBotoes():void {		
			btnImprimir.addEventListener(MouseEvent.CLICK, imprimir);
		}
		
		private function contingenciaMensagem(e:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao(Mensagens.MSG039, null, reenviarMensagem);
		}
		
		
		private function reenviarMensagem(e:MouseEvent):void {
			var metodo:String = "reenviarMensagemCip";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["idMensagem"] = mensagemDDAVO.id;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO_MENSAGEM_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoContingencia);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.reenviarMensagemCip(dto);
		}
		
		private function retornoContingencia(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG044);
		}
		
		private function identaXml(xml:String):String {
			return xml.split("><").join(">\n<");
		}
		
		private function imprimir(event:MouseEvent):void {
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.MONITORAMENTO_MENSAGEM, criarDTORelatorio(), null, PreImpressao.FORMATO_PDF)
		}
		
		/**
		 * Cria o DTO para geração do relatório.
		 */
		protected function criarDTORelatorio():MensagemDDADTO {
			var mensagemDTO:MensagemDDADTO = new MensagemDDADTO();
			mensagemDTO.idMensagem = mensagemDDAVO.id;
			return mensagemDTO;
		}
		
	}
}
