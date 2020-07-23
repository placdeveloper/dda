package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.CadastroSerie0200DTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesTipoMensagem;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DominioCIP;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo.GrupoArquivoDDA0215;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo.GrupoMensagemDDA0215;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo.IGrupoMensagemDDA;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	public class DDA0215 extends DDA0215View implements IMensagemDDA {
		
		private var servicoCadastro:ServicoJava;
		private var _grupoMensagem:IGrupoMensagemDDA;
		
		public function DDA0215() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
			initServico();
			initEvento();
		}
		
		private function initCampos():void {
			cbTipoMensagemArquivo.dataProvider = DominioCIP.TIPO_MENSAGEM_ARQUIVO.slice();
		}
		
		private function initServico():void {
			servicoCadastro = Funcoes.obterServico(Constantes.SERVICO_MENSAGEM_DDA, "cadastrarMensagemDDASerie0200");
			servicoCadastro.addEventListener(ResultEvent.RESULT, retornoCadastroMensagem);
		}
		
		private function initEvento():void {
			cbTipoMensagemArquivo.addEventListener(Event.CHANGE, selecionarTipo);
		}
		
		private function selecionarTipo(e:Event):void {
			var tipoSolicitacao:Object = e.target.selectedItem;
			removeTelaGrupo();
			if (tipoSolicitacao) {
				switch(tipoSolicitacao.data) {
					case "M": {
						_grupoMensagem = new GrupoMensagemDDA0215();
						break;
					}
					case "A": {
						_grupoMensagem = new GrupoArquivoDDA0215();
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
			if (!cbTipoMensagemArquivo.selectedItem) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Tipo Solicitação"), cbTipoMensagemArquivo);
				return false;
			} 
			return _grupoMensagem.validarDados();
		}
		
		public function limparDados(e:Event=null):void {
			removeTelaGrupo();
			if (cbTipoMensagemArquivo) {
				cbTipoMensagemArquivo.selectedIndex = 0;
			}
		}
		
		public function salvar():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["codTipoMensagem"] = ConstantesTipoMensagem.DDA0215;
			dto.dados["cadastroDto"] = configuraDto();
			servicoCadastro.cadastrarMensagemDDASerie0200(dto);
		}
		
		private function configuraDto():CadastroSerie0200DTO {
			var cadastroDto:CadastroSerie0200DTO = _grupoMensagem.configurarDto() as CadastroSerie0200DTO;
			cadastroDto.tipoMensagemArquivo = cbTipoMensagemArquivo.selectedItem.data;
			return cadastroDto;
		}
		
		private function retornoCadastroMensagem(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(MensagensComum.MSG002, "Mensagem"), limparDados);
			limparDados();
		}
	}
}