package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo
{
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.CadastroGen0014DTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DominioCIP;

	public class GrupoTituloGEN0014 extends GrupoTituloGEN0014View implements IGrupoMensagemDDA {
		
		public function GrupoTituloGEN0014() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
		}
		
		private function initCampos():void {
			cbTipoSolTitulo.dataProvider = DominioCIP.TIPO_SOL_TITULO.slice();
			cbTipoTitConsultado.dataProvider = DominioCIP.TIPO_TITULO_CONSULTADO.slice();
		}
		
		public function configurarDto():Object {
			var dto:CadastroGen0014DTO = new CadastroGen0014DTO();
			dto.codTipoSolBoleto = cbTipoSolTitulo.selectedItem.data;
			dto.codTipoBoletoConsultado = cbTipoTitConsultado.selectedItem.data;
			return dto;
		}
		
		public function validarDados():Boolean {
			return true;
		}
	}
}