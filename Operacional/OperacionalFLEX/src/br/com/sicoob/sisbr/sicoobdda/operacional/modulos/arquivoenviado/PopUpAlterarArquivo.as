package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.arquivoenviado
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ArquivoEnviadoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DataUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.LogEnvioArquivoDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoMensagemDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpAlterarArquivo extends PopUpAlterarArquivoView
	{

		private var arquivoEnviadoDto:ArquivoEnviadoDTO;
		private var idLogEnvioArquivodda:Number;
		private var situacaoAtual:String = new String();
		private var _atualizaPesquisa:Function;
		private var _arquivoEnviadoDTO:ArquivoEnviadoDTO;
		private var _horaEnvioTemp:String = null;
		private var _horaArquivoTemp:String = null;
		private var formataDt:DateFormatter = new DateFormatter();
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpAlterarArquivo(idLogEnvioArquivodda:Number,funcaoRetorno:Function) {
			super();
			this._atualizaPesquisa = funcaoRetorno;
			this.idLogEnvioArquivodda = idLogEnvioArquivodda;
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
			this.dataMovimento.dataDefault = null;
			carregarCampos();
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos campos.
		//--------------------------------------------------------------------------
		private function carregarCampos():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idLogEnvioArquivodda = idLogEnvioArquivodda;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_ENVIADO, "obterArquivoEnviado");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterArquivoEnviado(dto);
		}
		
		private function retornoConsulta(resultEvent:ResultEvent):void {
			var arquivoEnviadoDto:ArquivoEnviadoDTO = resultEvent.result.dados["dto"];
			this.arquivoEnviadoDto = arquivoEnviadoDto;
			carregarDadosDetalharArquivoEnviado(arquivoEnviadoDto);
		}
		
		private function carregarDadosDetalharArquivoEnviado(dto:ArquivoEnviadoDTO):void {
			this.txtDescNomeArquivo.text = dto.logEnvioArquivoDDA.descNomeArquivoEnviado;
			
			this.dataHoraArquivo.dataSelecionada = dto.logEnvioArquivoDDA.dataHoraArquivo==null ? null : dto.logEnvioArquivoDDA.dataHoraArquivo.data;
			this.dataHoraEnvio.dataSelecionada = dto.logEnvioArquivoDDA.dataHoraEnvio == null ? null : dto.logEnvioArquivoDDA.dataHoraEnvio.data;
			
			var horaArquivo:String = dto.logEnvioArquivoDDA.dataHoraArquivo == null ? null : DataUtil.formataHora(dto.logEnvioArquivoDDA.dataHoraArquivo.data,"");
			var horaEnvio:String = dto.logEnvioArquivoDDA.dataHoraEnvio == null ? null : DataUtil.formataHora(dto.logEnvioArquivoDDA.dataHoraEnvio.data,"");
			
			this._horaArquivoTemp = this._horaArquivoTemp == null ? horaArquivo : this._horaArquivoTemp;
			this._horaEnvioTemp = this._horaEnvioTemp == null ? horaEnvio : this._horaEnvioTemp;
			
			this.dataHoraArquivo.horaSelecionada = this._horaArquivoTemp;
			this.dataHoraEnvio.horaSelecionada = this._horaEnvioTemp;
			
			this.dataMovimento.selectedDate = dto.logEnvioArquivoDDA.dataMovimento.data
			
			this.cmbTipoMensagem.dataProvider = dto.listaTipoMensagem;
			this.cmbTipoMensagem.procuraItemPorNome(dto.logEnvioArquivoDDA.tipoMensagem.codTipoMensagem,"codTipoMensagem");
		}
		
		//--------------------------------------------------------------------------
		//  Validar Comobo.
		//--------------------------------------------------------------------------
		private function validaCombo(event:Event):void {
			
			if(StringUtil.trim(this.txtDescNomeArquivo.text) == "" || this.txtDescNomeArquivo.text == null){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG013, "Nome Arquivo"));
				return;	
			}else if(!this.dataMovimento.selectedDate){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG013, "Data Movimento"));
				return;	
			}else if(!this.cmbTipoMensagem.selectedItem){
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG013, "Tipo Mensagem"));
				return;	
			}else{			
				alterarArquivoEnvio();
				MensagensComum.exibirMensagemSucesso(Mensagens.MSG072, fecharMsgSucesso);
			}
		}
		
		//--------------------------------------------------------------------------
		//  Alterar Arquivo.
		//--------------------------------------------------------------------------		
		private function alterarArquivoEnvio():void {
			var logEnviadoArquivoDDA:LogEnvioArquivoDDAVO = new LogEnvioArquivoDDAVO;
			logEnviadoArquivoDDA.id = this.idLogEnvioArquivodda;
			logEnviadoArquivoDDA.descNomeArquivoEnviado = StringUtil.trim(this.txtDescNomeArquivo.text);
			logEnviadoArquivoDDA.tipoMensagem = this.cmbTipoMensagem.selectedItem as TipoMensagemDDAVO;
			logEnviadoArquivoDDA.dataMovimento = DateTimeBase.getDateTime(this.dataMovimento.selectedDate);

			logEnviadoArquivoDDA.dataHoraArquivo = this.dataHoraArquivo.campoData.selectedDate == null ? null : DateTimeBase.getDateTime(this.dataHoraArquivo.dataSelecionada);
			logEnviadoArquivoDDA.dataHoraEnvio = this.dataHoraEnvio.campoData.selectedDate == null ? null : DateTimeBase.getDateTime(this.dataHoraEnvio.dataSelecionada);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.vo = logEnviadoArquivoDDA;
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_ARQUIVO_ENVIADO, "alterarArquivo");

			servico.alterarArquivo(dto);
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
			carregarDadosDetalharArquivoEnviado(this.arquivoEnviadoDto);
		}
		
		
	}
}