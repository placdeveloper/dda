package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.boleto
{
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MensagemDDADTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class AbaDadosBoleto extends AbaDadosBoletoView
	{
		private var btnImprimir:Botao = new Botao();
		private var dto:MensagemDDADTO;
		
		public function AbaDadosBoleto(){
			super();
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
		}
	}
}