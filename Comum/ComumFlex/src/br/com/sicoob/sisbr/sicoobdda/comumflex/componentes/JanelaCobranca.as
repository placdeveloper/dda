package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes {

	import flash.display.DisplayObject;
	
	import mx.core.Application;
	import mx.events.CloseEvent;
	
	import br.com.bancoob.componentes.containers.EventoJanela;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	
	public class JanelaCobranca {
		
		public static function abrirJanela(pai:DisplayObject, tela:DisplayObject, titulo:String, modal:Boolean = true, fecharJanela:Function = null):Janela {
	      	var janela:Janela = new Janela();
			janela.icone = ConstantesComum.ICONE_JANELA;
			janela.title = titulo;
			janela.addChild(tela);
			janela.abrir(Application.application as DisplayObject, modal);
			janela.janelaPai = pai;
			janela.centralizarJanela();
			if (fecharJanela != null) {
				janela.addEventListener(CloseEvent.CLOSE, fecharJanela);
				janela.addEventListener(EventoJanela.FECHAR_JANELA, fecharJanela);
			}
			return janela;
		}
				
	}
}