package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.grupo
{
	
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.CadastroGen0014DTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DominioCIP;

	public class GrupoBeneficiarioGEN0014 extends GrupoBeneficiarioGEN0014View implements IGrupoMensagemDDA {
		
		
		public function GrupoBeneficiarioGEN0014() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
		}
		
		private function initCampos():void {
			cbTipoSolBeneficiario.dataProvider = DominioCIP.TIPO_SOL_BENEFICIARIO.slice();
			cbSitBeneficiario.dataProvider = DominioCIP.SIT_BENEFICIARIO.slice();
		}
		
		public function configurarDto():Object {
			var dto:CadastroGen0014DTO = new CadastroGen0014DTO();
			dto.codTipoSolBeneficiario = cbTipoSolBeneficiario.selectedItem.data;
			if (cbSitBeneficiario.selectedIndex) {
				dto.codSituacaoBeneficiario = cbSitBeneficiario.selectedItem.data;
			}
			return dto;
		}
		
		public function validarDados():Boolean {
			return true;
		}
	}
}