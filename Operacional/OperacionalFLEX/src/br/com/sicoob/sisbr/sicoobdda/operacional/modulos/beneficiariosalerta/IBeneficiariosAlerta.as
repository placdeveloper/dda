package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.beneficiariosalerta
{
	import flash.events.MouseEvent;
	
	import br.com.bancoob.componentes.eventos.ProcurarEvent;
	
	public interface IBeneficiariosAlerta
	{
		function pesquisarBeneficiarios(event:ProcurarEvent):void;
		function gerarRelatorioBeneficiariosEmAlerta(event:MouseEvent):void;
	}
	
}