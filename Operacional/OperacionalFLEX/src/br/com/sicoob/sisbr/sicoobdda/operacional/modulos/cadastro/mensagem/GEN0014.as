package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.CadastroGen0014DTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DominioCIP;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo.GrupoBeneficiarioGEN0014;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo.GrupoPagadorGEN0014;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo.GrupoTituloGEN0014;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo.IGrupoMensagemDDA;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class GEN0014 extends GEN0014View implements IMensagemDDA {
		
		private var _grupoMensagem:IGrupoMensagemDDA;
		private var servicoCadastro:ServicoJava;
		
		public function GEN0014() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
			initEvento();
			initServico();
		}
		
		private function initCampos():void {
			cbTipoSolicitacao.dataProvider = DominioCIP.TIPO_SOLICITACAO.slice();
		}
		
		private function initEvento():void {
			cbTipoSolicitacao.addEventListener(Event.CHANGE, selecionarTipoSolicitacao);
		}
		
		private function initServico():void {
			servicoCadastro = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, "cadastrarMensagemGEN0014");
			servicoCadastro.addEventListener(ResultEvent.RESULT, retornoCadastroMensagem);
		}
		
		private function selecionarTipoSolicitacao(e:Event):void {
			var tipoSolicitacao:Object = e.target.selectedItem;
			if (tipoSolicitacao) {
				removeTelaGrupo();
				switch(tipoSolicitacao.data) {
					case DominioCIP.GRUPO_BENEFICIARIO: {
						_grupoMensagem = new GrupoBeneficiarioGEN0014();
						break;
					}
					case DominioCIP.GRUPO_PAGADOR: {
						_grupoMensagem = new GrupoPagadorGEN0014();
						break;
					}
					case DominioCIP.GRUPO_TITULO: {
						_grupoMensagem = new GrupoTituloGEN0014();
						break;
					}
					default: {
						break;
					}
				}
				addTelaGrupo();
			}
		}
		
		private function removeTelaGrupo():void {
			if (_grupoMensagem) {
				fsCadastroGrupo.removeChild(_grupoMensagem as DisplayObject);
				_grupoMensagem = null;
			}
		}
		
		private function addTelaGrupo():void {
			if (_grupoMensagem) {
				fsCadastroGrupo.addChild(_grupoMensagem as DisplayObject);
			}
		}
		
		
		public function validarDados():Boolean {
			if (!cbTipoSolicitacao.selectedItem) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Tipo Solicitação"), cbTipoSolicitacao);
				return false;
			}
			return true;
		}
		
		public function limparDados(e:Event=null):void {
			removeTelaGrupo();
			if (cbTipoSolicitacao) {
				cbTipoSolicitacao.selectedIndex = 0;
			}
		}
		
		public function salvar():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["codTipoSolicitacao"] = obterTipoSolicitacao();
			dto.dados["cadastroDto"] = configurarCadastroDto();
			servicoCadastro.cadastrarMensagemGEN0014(dto);
		}
		
		private function retornoCadastroMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG002, "Mensagem"), limparDados);
			limparDados();
		}
		
		private function obterTipoSolicitacao():String {
			if (cbTipoSolicitacao.selectedItem) {
				return cbTipoSolicitacao.selectedItem.data;
			}
			return null;
		}
		
		private function configurarCadastroDto():CadastroGen0014DTO {
			if (_grupoMensagem) {
				return _grupoMensagem.configurarDto() as CadastroGen0014DTO;
			}
			return null;
		}
	}
}