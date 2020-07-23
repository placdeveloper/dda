package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tratamento
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.IdentificadorDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.MensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("RegistroVO", RegistroVO);
	
	public class DetalharErro extends DetalharErroView
	{
		
		private var _dataMovimento:IDateTime;
		private var _idMensagem:Number;
		private var _funcaoRetorno:Function;
		
		public function DetalharErro(dataMovimento:IDateTime, idMensagem:Number, funcaoRetorno:Function)	{
			super();
			this._dataMovimento = dataMovimento;
			this._idMensagem = idMensagem;
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this._funcaoRetorno = funcaoRetorno;
		}
		

		protected function init(event:FlexEvent):void {
			iniciarCampos();
			iniciarBotoes();
		}
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(obj:Object = null):void {
			if(_funcaoRetorno != null){
				this._funcaoRetorno();
			}
			super.fecharJanela();
		}
		
		
		/**
		 * Define estado inicial dos campos do formulário.
		 */
		private function iniciarCampos():void {
			rotDataMovimento.text = FormataData.formataData(_dataMovimento.data);
			obterDetalheErroMensagem();				
		}
		
		private function obterDetalheErroMensagem():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var identificadorDTO:IdentificadorDTO = new IdentificadorDTO();
			identificadorDTO.identificadorGeral = this._idMensagem;
			dto.dados["identificadorDTO"] = identificadorDTO;
			var metodo:String = "obterDetalheMensagemErro";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoObterDetalheErro);
			servico.mensagemEspera = "Buscando detalhe...";
			servico.obterDetalheMensagemErro(dto);
		}
		
		private function retornoObterDetalheErro(e:ResultEvent):void {
			var mensagem:MensagemDDAVO = e.result.dados.mensagem
			txtXmlMensagem.text = identaXml(mensagem.xmlMensagem);
			txtDescErroProtocolo.text = mensagem.descErroProtocolo;
			rotCodTipoMensagem.text = mensagem.tipoMensagemDDA.codTipoMensagem;	
		}
		
		private function iniciarBotoes():void {
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);	
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			btnExcluir.addEventListener(MouseEvent.CLICK, confirmaExcluir);
		}
		
		private function confirmaExcluir(e:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao(MensagensComum.formatar(MensagensComum.MSG008, "mensagem"), btnExcluir, excluirMensagem);
		}
		
		private function excluirMensagem(e:MouseEvent):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var identificadorDTO:IdentificadorDTO = new IdentificadorDTO();
			identificadorDTO.identificadorGeral = this._idMensagem;
			dto.dados["identificadorDTO"] = identificadorDTO;
			var metodo:String = "exluirMensagemErro";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoExcluirMensagem);
			servico.mensagemEspera = "Excluindo Mensagem...";
			servico.exluirMensagemErro(dto);
		}
		
		private function retornoExcluirMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG028, "Mensagem"));
			fechar();
		}
		
		
		private function identaXml(xml:String):String {
			if (xml) {
				return xml.split("><").join(">\n<");
			} else {
				return "";
			}
		}
		
	}
}
