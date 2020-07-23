package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.arquivorecebido
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ArquivoRecebidoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.IdentificadorDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.LogDetRecebimentoArquivoDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpDetalharRegistro extends PopUpDetalharRegistroView
	{
		private var arquivoRecebidoDto:ArquivoRecebidoDTO;
		private var idLogDetRecArquivoDDAVO:Number;

		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpDetalharRegistro(idLogDetRecArquivoDDAVO:Number) {
			this.idLogDetRecArquivoDDAVO = idLogDetRecArquivoDDAVO;
			super();
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			this.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			carregarCampos();
		}
		//--------------------------------------------------------------------------
		//  Carregar dados dos campos.
		//--------------------------------------------------------------------------
		private function carregarCampos():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var identificadorDTO:IdentificadorDTO = new IdentificadorDTO();
			identificadorDTO.identificadorGeral = this.idLogDetRecArquivoDDAVO;
			dto.dados.identificadorDTO = identificadorDTO;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_RECEBIDO, "obterLogDetRecebimentoArquivoDDA");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterLogDetRecebimentoArquivoDDA(dto);
		}
		
		private function retornoConsulta(resultEvent:ResultEvent):void {
			var logDetRecArquivoDDAVO:LogDetRecebimentoArquivoDDAVO = resultEvent.result.dados["VO"];
			this.txtIdLogDetRecArquivoDDA.text = logDetRecArquivoDDAVO.id.toString();
			this.txtIdLogRecArquivoDDA.text = logDetRecArquivoDDAVO.logRecebimentoArquivoDDA.id.toString();
			this.txtProcessado.text = logDetRecArquivoDDAVO.bolProcessado? "Processado": "Não Processado";
			txtMensagem.text = this.identaXml(logDetRecArquivoDDAVO.descXMLMensagemRecebida);
		}
		//--------------------------------------------------------------------------
		//  Identa XML.
		//--------------------------------------------------------------------------
		private function identaXml(xml:String):String {
			return xml.split("><").join(">\n<");
		}
		
		//--------------------------------------------------------------------------
		//  Fechar.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
	}
}