package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo
{
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.CadastroSerie0200DTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesTipoMensagem;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;

	public class GrupoArquivoDDA0215 extends GrupoArquivoDDA0215View implements IGrupoMensagemDDA {
		
		public function GrupoArquivoDDA0215() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
		}
		
		private function initCampos():void {
			cbCodTipoMensagem.dataProvider = ConstantesTipoMensagem.TIPO_MENSAGEM_DDA0215_ARQ.slice();
		}
		
		public function configurarDto():Object {
			var cadastroDto:CadastroSerie0200DTO = new CadastroSerie0200DTO();
			cadastroDto.identArquivoc = txtIdentArquivo.text;
			cadastroDto.codTipoMensagem = cbCodTipoMensagem.selectedItem.codTipo;
			return cadastroDto;
		}
		
		public function validarDados():Boolean {
			if (txtIdentArquivo.textWidth == 0) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Identificador Arquivo"), txtIdentArquivo);
				return false;
			} else if (!cbCodTipoMensagem.selectedItem) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Código Mensagem"), cbCodTipoMensagem);
				return false;
			}
			return true;
		}
	}
}