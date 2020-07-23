package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem
{
	import flash.events.Event;

	public interface IMensagemDDA {
		
		function validarDados():Boolean;
		
		function limparDados(e:Event=null):void;
		
		function salvar():void;
		
	}
}