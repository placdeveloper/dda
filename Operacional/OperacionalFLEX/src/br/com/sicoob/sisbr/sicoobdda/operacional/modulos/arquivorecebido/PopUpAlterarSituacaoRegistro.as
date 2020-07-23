package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.arquivorecebido
{
	import flash.events.Event;
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
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.LogDetRecebimentoArquivoDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
		
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class PopUpAlterarSituacaoRegistro extends PopUpAlterarSituacaoRegistroView
	{

		private var _arquivoRecebidoDto:ArquivoRecebidoDTO;
		private var _idLogDetRecArquivoDDAVO:Number;
		private var _situacaoAtual:String = new String();
		private var _atualizaPesquisa:Function;
		private var _logDetRecArquivoDDAVO:LogDetRecebimentoArquivoDDAVO = new LogDetRecebimentoArquivoDDAVO();
		
		public static const LISTA_SITUACAO_REGISTRO:Array = [
			{label:"PROCESSADO", data:1},
			{label:"NÃO PROCESSADO", data:0}
		];
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpAlterarSituacaoRegistro(idLogDetRecArquivoDDAVO:Number, funcaoRetorno:Function) {
			super();
			this._atualizaPesquisa = funcaoRetorno;
			this._idLogDetRecArquivoDDAVO = idLogDetRecArquivoDDAVO;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			this.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			this.btnCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			this.btnConfirmar.addEventListener(MouseEvent.CLICK, alterarSituacao);
			
			carregarCampos();
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos campos.
		//--------------------------------------------------------------------------
		private function carregarCampos():void {
			this.cmbSituacaoRegistro.dataProvider = LISTA_SITUACAO_REGISTRO;
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var identificadorDTO:IdentificadorDTO = new IdentificadorDTO();
			identificadorDTO.identificadorGeral = this._idLogDetRecArquivoDDAVO;
			dto.dados.identificadorDTO = identificadorDTO;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_RECEBIDO, "obterLogDetRecebimentoArquivoDDA");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterLogDetRecebimentoArquivoDDA(dto);
		}
		
		private function retornoConsulta(resultEvent:ResultEvent):void {
			this._logDetRecArquivoDDAVO = resultEvent.result.dados["VO"];
			
			this.cmbSituacaoRegistro.procuraItemData(_logDetRecArquivoDDAVO.bolProcessado == true ? 1 : 0);
		}
		
		//--------------------------------------------------------------------------
		//  Alterar Situação.
		//--------------------------------------------------------------------------		
		private function alterarSituacao(e:Event):void {
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			this._logDetRecArquivoDDAVO.bolProcessado = this.cmbSituacaoRegistro.selectedItem.data;
			dto.dados.vo = _logDetRecArquivoDDAVO;
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_RECEBIDO, "alterarSituacaoRegistro");
			servico.addEventListener(ResultEvent.RESULT, retornoAlterarSituacao);
			servico.alterarSituacaoRegistro(dto);
		}
		
		private function retornoAlterarSituacao(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG071, fecharMsgSucesso);
		}
		
		//--------------------------------------------------------------------------
		//  Fechar.
		//--------------------------------------------------------------------------
		private function fechar(event:Event):void {
			super.fecharJanela();
		}
		
		private function fecharMsgSucesso(event:Event):void {
			if(_atualizaPesquisa != null){
				this._atualizaPesquisa();
			}
			super.fecharJanela();
		}
		//--------------------------------------------------------------------------
		//  Cancelar.
		//--------------------------------------------------------------------------
		private function cancelar(event:Event):void {
			carregarCampos();
		}
		
		
	}
}