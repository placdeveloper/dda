package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.tipoerrocip
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoErroCipVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoTratamentoErroCipVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("RegistroVO", RegistroVO);
	
	public class CadastroTipoErroCIP extends CadastroTipoErroCIPView {
		
		public static const MODO_INCLUSAO:int = 0;
		public static const MODO_EDICAO:int = 1;
		public static const MODO_VISUALIZACAO:int = 2;
		
		private var _codTipoErro:String;
		private var _modo:int;
		private var _tipoErroCip:TipoErroCipVO;
		private var _listaTipoTratamento:ArrayCollection;
		
		public function CadastroTipoErroCIP(codTipoErro:String = null, modo:int = MODO_INCLUSAO)	{
			super();
			this._modo = modo;
			this._codTipoErro = codTipoErro;
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected function init(e:FlexEvent): void {
			initBotoes();
			txtDescTipoErro.addEventListener(MouseEvent.MOUSE_OVER, preencherTooTip);	
			txtDescTipoErro.restrict = ConstantesComum.REGEX_CAMPO_DESCRICAO;

			if (_modo == MODO_INCLUSAO) {
				obterListaTipoTratamentoErro();
			} else {
				desbilitarCampos();
				obtemTipoErro();
			}
			
		}
		
		private function initBotoes():void {
			barraBotoesDDA.btnCancelar.label = "Cancelar";
			barraBotoesDDA.btnSalvar.setStyle("icon", null);
			barraBotoesDDA.btnFechar.setStyle("", ConstantesComum.icoFechar);
			barraBotoesDDA.btnCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			barraBotoesDDA.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			barraBotoesDDA.btnSalvar.addEventListener(MouseEvent.CLICK, salvar);
			
		}
		
		private function desbilitarCampos():void {
			if (_modo == MODO_VISUALIZACAO) {
				txtDescTipoErro.editable = false;
				cmbTratamentoAutomatizado.enabled = false;
				barraBotoesDDA.btnSalvar.enabled = false;
				barraBotoesDDA.btnSalvar.visible = false;
				barraBotoesDDA.btnCancelar.enabled = false;
				barraBotoesDDA.btnCancelar.visible = false;
			}
			txtCodTipoErro.editable = false;
			
		}
		
		private function obterListaTipoTratamentoErro():void {
			var metodo:String = "listarTipoTratamentoSitMensagemRetornoComErro";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TRATAMENTO_PENDENCIA_ERRO, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoObterListaTipoTratamentoErro);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.listarTipoTratamentoSitMensagemRetornoComErro();
		}
		
		private function retornoObterListaTipoTratamentoErro(e:ResultEvent):void {
			cmbTratamentoAutomatizado.dataProvider = _listaTipoTratamento = e.result.dados.listaTipoTratamento;
			cmbTratamentoAutomatizado.selectedIndex = 0;
			selecionaTratamentoAutomatizado();			
		}
		
		private function selecionaTratamentoAutomatizado():void {
			var tipoTratamento:TipoTratamentoErroCipVO;
			if (!_modo == MODO_INCLUSAO) {
				for (var i:int = 1; i < _listaTipoTratamento.length; i++) {
					tipoTratamento = _listaTipoTratamento.getItemAt(i) as TipoTratamentoErroCipVO;
					if (_tipoErroCip && _tipoErroCip.tipoTratamentoErroCip) {
						if (_tipoErroCip.tipoTratamentoErroCip.codTipoTratamentoErroCip == tipoTratamento.codTipoTratamentoErroCip) {
							cmbTratamentoAutomatizado.selectedIndex = _listaTipoTratamento.getItemIndex(tipoTratamento);
						}
					} else {
						cmbTratamentoAutomatizado.selectedIndex = 0;
					}
				}
			}
		}
		
		private function obtemTipoErro():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["codTipoErro"] = _codTipoErro;
			var metodo:String = "obterTipoErro";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_ERRO_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoObterTipoErro);
			servico.mensagemEspera = "Buscando definições do componente...";
			servico.obterTipoErro(dto);
		}
		
		private function retornoObterTipoErro(e:ResultEvent):void {
			_tipoErroCip = e.result.dados.tipoErro as TipoErroCipVO;
			preencherDados();
			obterListaTipoTratamentoErro();
		}
		
		private function preencherDados():void {
			txtCodTipoErro.text = _tipoErroCip.codTipoErro;
			txtDescTipoErro.text = _tipoErroCip.descTipoErro;
			txtDescTipoErro.toolTip = _tipoErroCip.descTipoErro;
		}
		
		private function cancelar(e:MouseEvent):void {
			if (!_tipoErroCip) {
				txtCodTipoErro.text = "";
				txtDescTipoErro.text = "";
				cmbTratamentoAutomatizado.selectedIndex = 0;
			} else {
				preencherDados();
				selecionaTratamentoAutomatizado();
			}
		}

		private function preencherTooTip(event:MouseEvent):void {
			txtDescTipoErro.toolTip = txtDescTipoErro.text;
		}
		
		
		private function salvar(e:MouseEvent):void {
			preparaTipoErroCipVO();
			if (_modo == MODO_INCLUSAO) {
				incluirTipoErro();
			} else if (_modo == MODO_EDICAO) {
				alterarTipoErro();	
			}
		}
		
		private function incluirTipoErro():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["tipoErro"] = _tipoErroCip;
			var metodo:String = "incluirTipoErro";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_ERRO_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoIncluir);
			servico.mensagemEspera = "Salvando...";
			servico.incluirTipoErro(dto);
		}
		
		private function retornoIncluir(e:ResultEvent):void {
			retornoServico(e, MensagensComum.MSG001);
		}
		
		private function alterarTipoErro():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["tipoErro"] = _tipoErroCip;
			var metodo:String = "alterarTipoErro";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_TIPO_ERRO_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, retornoAlterar);
			servico.mensagemEspera = "Salvando...";
			servico.alterarTipoErro(dto);
		}
		
		private function retornoAlterar(e:ResultEvent):void {
			retornoServico(e, MensagensComum.MSG003);
		}
		
		private function retornoServico(e:ResultEvent, msg:String):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(msg, "Tipo Erro CIP"));
			fecharJanela();
		}
		
		private function preparaTipoErroCipVO():void {
			if (!_tipoErroCip) {
				_tipoErroCip = new TipoErroCipVO();
			}
			
			_tipoErroCip.codTipoErro = txtCodTipoErro.text;
			_tipoErroCip.descTipoErro = txtDescTipoErro.text;
			
			if (cmbTratamentoAutomatizado.selectedItem) {
				_tipoErroCip.tipoTratamentoErroCip = cmbTratamentoAutomatizado.selectedItem as TipoTratamentoErroCipVO;
			} else {
				_tipoErroCip.tipoTratamentoErroCip = null;
			}
		}
		
		private function fechar(e:MouseEvent):void {
			this.fecharJanela();
		}
	}
}