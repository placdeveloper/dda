package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.arquivorecebido
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ArquivoRecebidoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.IdentificadorDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.SituacaoProcessamentoArquivoVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
		
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class PopUpAlterarSituacaoArquivo extends PopUpAlterarSituacaoArquivoView
	{

		private var _arquivoRecebidoDto:ArquivoRecebidoDTO;
		private var _idLogReceArquivoDDA:Number;
		private var _situacaoAtual:String = new String();
		private var _atualizaPesquisa:Function;
		private var _listaSituacao:ArrayCollection;
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpAlterarSituacaoArquivo(idLogReceArquivoDDA:Number, listaSituacao:ArrayCollection ,funcaoRetorno:Function) {
			super();
			this._atualizaPesquisa = funcaoRetorno;
			this._idLogReceArquivoDDA = idLogReceArquivoDDA;
			_listaSituacao = listaSituacao;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			this.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			this.btnConfirmar.addEventListener(MouseEvent.CLICK, validaCombo);
			this.btnCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			this.cmbSituacaoArquivo.dataProvider = _listaSituacao;
			carregarCampos();
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos campos.
		//--------------------------------------------------------------------------
		private function carregarCampos():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var identificadorDTO:IdentificadorDTO = new IdentificadorDTO();
			identificadorDTO.identificadorGeral = _idLogReceArquivoDDA;
			dto.dados.identificadorDTO = identificadorDTO;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_RECEBIDO, "obterArquivoRecebido");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterArquivoRecebido(dto);
		}
		
		private function retornoConsulta(resultEvent:ResultEvent):void {
			_arquivoRecebidoDto = resultEvent.result.dados.dto as ArquivoRecebidoDTO;
			carregarDadosDetalharArquivoRecebido();
		}
		
		private function carregarDadosDetalharArquivoRecebido():void {
			this.txtIdLogRecebimentoArquivoDDA.text = _arquivoRecebidoDto.idLogRecebimentoArqDDA.toString();
			this.txtDescNomeArquivo.text = _arquivoRecebidoDto.descNomeArquivoRecebido;
			this.txtSituacao.text = _arquivoRecebidoDto.descSituacaoProcessamentoArquivo;
			
			this.cmbSituacaoArquivo.procuraItemPorNome(_arquivoRecebidoDto.descSituacaoProcessamentoArquivo, "descSituacaoProcessamentoArquivo");
		}
		
		//--------------------------------------------------------------------------
		//  Validar Comobo.
		//--------------------------------------------------------------------------
		private function validaCombo(event:Event):void {
			var sitaucao:SituacaoProcessamentoArquivoVO = cmbSituacaoArquivo.selectedItem as SituacaoProcessamentoArquivoVO
			
			if (sitaucao.descSituacaoProcessamentoArquivo == _arquivoRecebidoDto.descSituacaoProcessamentoArquivo) {
				MensagensComum.exibirMensagemInformacao(Mensagens.MSG070);
			} else {
				alterarSituacao(sitaucao);
			}
			
		}	
		
		//--------------------------------------------------------------------------
		//  Alterar Situação.
		//--------------------------------------------------------------------------		
		private function alterarSituacao(sitaucao:SituacaoProcessamentoArquivoVO):void {
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idLogRecebimentoArqDDA = _arquivoRecebidoDto.idLogRecebimentoArqDDA;;
			dto.dados.codSituacao = sitaucao.codSituacaoProcessamentoArquivo;
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_RECEBIDO, "alterarSituacaoArquivoRecebido");
			servico.addEventListener(ResultEvent.RESULT, retornoAlterarSituacao);
			servico.alterarSituacaoArquivoRecebido(dto);
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
			carregarDadosDetalharArquivoRecebido();
		}
		
		
	}
}