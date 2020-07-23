package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo
{
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.CadastroGen0014DTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DominioCIP;

	public class GrupoPagadorGEN0014 extends GrupoPagadorGEN0014View implements IGrupoMensagemDDA {
		
		public function GrupoPagadorGEN0014() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
		}
		
		private function initCampos():void {
			cbTipoSolPagador.dataProvider = DominioCIP.TIPO_SOL_PAGADOR.slice();
		}
		
		public function configurarDto():Object {
			var dto:CadastroGen0014DTO = new CadastroGen0014DTO();
			dto.codTipoSolPagador = cbTipoSolPagador.selectedItem.data;
			return dto;
		}
		
		public function validarDados():Boolean {
			return true;
		}
	}
}