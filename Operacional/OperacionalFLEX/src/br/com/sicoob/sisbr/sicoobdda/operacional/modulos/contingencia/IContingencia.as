package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.contingencia
{
	import flash.events.MouseEvent;
	
	public interface IContingencia
	{
		function incluirContingencia():void
		function listarHistoricoContingencias():void;
		function isContingenciaHabilitada(event:MouseEvent):void;
		function gerarRelatorio(event:MouseEvent):void;
	}
	
}