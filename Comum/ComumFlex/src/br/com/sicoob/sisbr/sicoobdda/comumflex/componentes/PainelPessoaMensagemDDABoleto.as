package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes
{
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;

	public class PainelPessoaMensagemDDABoleto extends PainelPessoaMensagemDDABoletoView
	{
		public function PainelPessoaMensagemDDABoleto()
		{
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}

		protected function init(event:FlexEvent):void {		
			iniciarComboUF();
		}
		
		private function iniciarComboUF():void {
			this.cmbUf.dataProvider = ConstantesComum.ARRAY_UF;
		}
		
		//SETTERS AND GETTERS =====================================
		public function set xFieldLeft(xFieldLeft:Number):void {
			_xFieldLeft = xFieldLeft;
		}
		
		public function get xFieldLeft():Number {
			return _xFieldLeft;
		}
		
		public function set xLabelsRight(xLabelsRight:Number):void {
			_xLabelsRight = xLabelsRight;
		}
		
		public function set includeEndereco (includeEndereco:Boolean):void {
			_includeEndereco = includeEndereco;
		}
		
		public function set includeNomeFantasia (includeNomeFantasia:Boolean):void {
			_includeNomeFantasia = includeNomeFantasia;
		}
	
		
		
	}
}