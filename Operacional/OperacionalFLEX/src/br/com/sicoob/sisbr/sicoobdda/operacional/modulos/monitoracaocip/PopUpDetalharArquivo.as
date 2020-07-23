package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.monitoracaocip
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.LogArquivoCargaVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoAjusteVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;

	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpDetalharArquivo extends PopUpDetalharArquivoView
	{

		private var logArquivoVO:LogArquivoCargaVO;
		private var _statusArquivo:Boolean;
		private var _listaErroBeneFiciario:ArrayCollection;
		private var _tipoErroCip:TipoAjusteVO;
		private var _codTipoErroCip:String;

		public function PopUpDetalharArquivo(logArquivoVO:LogArquivoCargaVO, statusArquivo:Boolean)
		{
			super();
			this.logArquivoVO = logArquivoVO;
			this._statusArquivo = statusArquivo;
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
		private function iniciarCampos():void	{
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnImpArquivo.addEventListener(MouseEvent.CLICK, imprimir);
			btnFiltrar.addEventListener(MouseEvent.CLICK, recuperaRegistroBeneficiarioErroTipoCip);
			lblNumeroArquivo.data = this.logArquivoVO.id;
			lblNomeArquivoEnviado.data = this.logArquivoVO.descNomeArquivoEnviado;
			lblNomeArquivoRecebido.data = this.logArquivoVO.descNomeArquivoRecebido;
			lblDataProtocolo.data = this.logArquivoVO.dataHoraProtocolo ? FormataData.formataData(this.logArquivoVO.dataHoraProtocolo.data) : "";
			lblDataRecebimento.data = this.logArquivoVO.dataHoraArqRecebido ? FormataData.formataData(this.logArquivoVO.dataHoraArqRecebido.data) : "";
			lblDataEnvio.data = this.logArquivoVO.dataHoraDDA ? FormataData.formataData(this.logArquivoVO.dataHoraDDA.data) : "";
			lblDataDdda.data = this.logArquivoVO.dataHoraDDA ? FormataData.formataData(this.logArquivoVO.dataHoraDDA.data) : "";
			lblCodigoSituacao.data = this.logArquivoVO.situacaoArquivoDDA ? this.logArquivoVO.situacaoArquivoDDA.descSituacaoArquivo : "";
			lblDescricaoErro.data = this.logArquivoVO.tipoErroCip ? this.logArquivoVO.tipoErroCip.descTipoErro : "";
			
			if(!this._statusArquivo) {
				this.recuperaRegistroBeneficiarioErro();
			} else {
				this.btnReenviar.enabled = false;
			}
		}
		
		private function reenviarMensagemContingencia(e:MouseEvent):void {
			MensagensComum.exibirMensagemConfirmacao(Mensagens.MSG040, null, realizarContingencia); 
		}
		
		private function realizarContingencia(e:MouseEvent):void {
			this.btnReenviar.enabled = false;
			this.emExecucao.visible = true;
			this.totEnviados.visible = true;
			this.totProcessados.visible = true;
			this.lblTotEnviados.visible = true;
			this.lblTotProcessados.visible = true;
			this.totQtdMaxima.visible = true;
			this.lblQtdMaxProcessamento.visible = true;
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["idArquivo"] = this.lblNumeroArquivo.data;
			if(this.lstErroCIP.selectedItem != null) {
				dto.dados["codTipoErroCip"] = this.lstErroCIP.selectedItem.codTipoErro;
			}
			dto.dados["qtdTotalErros"] = this.lblTotRegistros.data;
			var metodo:String = "reenviarArquivoCipPorMensagem";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MENSAGENS_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoContingencia);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.reenviarArquivoCipPorMensagem(dto);
		}
		
		private function retornoContingencia(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG044);
		}
		
		private function recuperaRegistroBeneficiarioErro():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["idArquivo"] = this.logArquivoVO.id;
			var metodo:String = "recuperaRegistroBeneficiarioErro";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MENSAGENS_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoBeneficiarioErro);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.recuperaRegistroBeneficiarioErro(dto);
		}
		
		private function recuperaRegistroBeneficiarioErroTipoCip(e:MouseEvent):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["idArquivo"] = this.lblNumeroArquivo.data;
			if(this.lstErroCIP.selectedItem != null) {
				this._codTipoErroCip = this.lstErroCIP.selectedItem.codTipoErro;
			} else {
				this._codTipoErroCip = null;
			}
			dto.dados["codTipoErroCip"] = this._codTipoErroCip;
			var metodo:String = "recuperaRegistroBeneficiarioErro";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MENSAGENS_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoBeneficiarioErro);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.recuperaRegistroBeneficiarioErro(dto);
		}
		
		private function retornoBeneficiarioErro(e:ResultEvent):void {
			this.gridBeneficiarioErro.dataProvider = e.result.dados.listaBeneficiarioErro;
			this.lstErroCIP.dataProvider = e.result.dados.listaTipoErroCIP;
			this.lstErroCIP.procuraItemPorNome(_codTipoErroCip, "codTipoErro");
			this.lblTotEnviados.data = e.result.dados.parametrosProcessamento.qtdErrosEnviados;
			this.lblTotProcessados.data = e.result.dados.parametrosProcessamento.qtdTotalErrosExecucao;
			this.lblQtdMaxProcessamento.data = e.result.dados.parametrosProcessamento.qtdMaximaErros;
			this.btnReenviar.enabled = e.result.dados.parametrosProcessamento.emExecucao ? false : true;
			this.emExecucao.visible = e.result.dados.parametrosProcessamento.emExecucao ? true : false;
			this.totProcessados.visible = e.result.dados.parametrosProcessamento.emExecucao ? true : false;
			this.lblTotProcessados.visible = e.result.dados.parametrosProcessamento.emExecucao ? true : false;
			this.totEnviados.visible = e.result.dados.parametrosProcessamento.emExecucao ? true : false;
			this.lblTotEnviados.visible = e.result.dados.parametrosProcessamento.emExecucao ? true : false;
			this.totQtdMaxima.visible = e.result.dados.parametrosProcessamento.emExecucao ? true : false;
			this.lblQtdMaxProcessamento.visible = e.result.dados.parametrosProcessamento.emExecucao ? true : false;
			this._listaErroBeneFiciario = e.result.dados.listaBeneficiarioErro;
			this.lblTotRegistros.data = e.result.dados.listaBeneficiarioErro.length;
			if(e.result.dados.listaBeneficiarioErro == null || e.result.dados.listaBeneficiarioErro.length == 0) {
				this.btnReenviar.enabled = false
			}
			if(this.btnReenviar.enabled){
				btnReenviar.addEventListener(MouseEvent.CLICK, reenviarMensagemContingencia);
			}
		}
		
		
		/**
		 * Imprimi detalhes.
		 */
		private function imprimir(event:MouseEvent):void{
			var metodo:String = "gerarRelatorioMonitoramentoArquivosCIP";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["idArquivo"] = this.logArquivoVO.id;
			
			var relatorioUtil:RelatorioUtil = new RelatorioUtil();
			var exibirPreImpressao:Boolean = true;
			relatorioUtil.emitirRelatorio(metodo, Constantes.SERVICO_RELATORIO, dto, ConstantesRelatorios.MONITORAMENTO_ARQUIVO, null,
				"Gerando Impressão...", ConstantesComum.FORMATO_RELATORIO_PDF, exibirPreImpressao);
		}	
	}
}
