package
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.monitoracaocip.PainelMonitoracaoCipView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("RegistroVO", RegistroVO);
	public class PainelMonitoracaoCip extends PainelMonitoracaoCipView
	{
		
		public function PainelMonitoracaoCip()
		{
			super();
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			tratarAbas();
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.abaMensagem.btnDetalharMensagem.enabled = false;
			
			this.abaMensagem.painelFiltro.btnLimpar.addEventListener(MouseEvent.CLICK, this.abaMensagem.limparCampos);
		}
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
		/**
		 * Define estado das abas do formulário.
		 */
		private function tratarAbas():void	{
			
			switch(parametros)
			{
				case Constantes.ABA_MENSAGENS:
				{
					tabBeneficiarioCip.getTabAt(0).visible = tabBeneficiarioCip.getTabAt(1).visible = tabBeneficiarioCip.getTabAt(2).visible = true;
					break;
				}
								
			}
			
		}
		
	}
}