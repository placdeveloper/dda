package
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.pagadoreletronico.cooperativa.PesquisaPagadorEletronicoView;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class PesquisaPagadorEletronico extends PesquisaPagadorEletronicoView
	{
		
		
		public function PesquisaPagadorEletronico() {
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init)
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected function init(event:FlexEvent):void {
			this.btnFechar.addEventListener(MouseEvent.CLICK,fechar);
			this.btnFechar.setStyle("icon", ConstantesComum.icoFechar);
		}	
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
	}
}