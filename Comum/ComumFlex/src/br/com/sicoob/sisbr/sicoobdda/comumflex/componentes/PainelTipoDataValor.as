package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes
{
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;

	public class PainelTipoDataValor extends PainelTipoDataValorView
	{
		public function PainelTipoDataValor()
		{
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected function init(event:FlexEvent):void {
			
		}
		
		public function set rotuloTipo(rotuloTipo:String):void {
			this._rotuloTipo = rotuloTipo;
		}
		
		public function set rotuloData(rotuloData:String):void {
			this._rotuloData = rotuloData;
		}
		
		public function set rotuloValor(rotuloValor:String):void {
			this._rotuloValor = rotuloValor;
		}
		
		public function set labelField(param:String):void {
			this._labelField = param;
		}
		
		public function set labelItemOpcional(param:String):void {
			this._labelItemOpcional = param;
		}
	}
}