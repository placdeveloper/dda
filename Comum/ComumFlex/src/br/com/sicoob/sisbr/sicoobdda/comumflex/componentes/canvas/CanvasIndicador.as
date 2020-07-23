package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.canvas
{
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
			
	public class CanvasIndicador extends CanvasIndicadorView {
		
		public static const NIVEL_NORMAL:int = 1; 
		public static const NIVEL_ATENCAO:int = 2;
		public static const NIVEL_CRITICO:int = 3;
		
		public static const ALERTA_GRADE_HORARIA:int = 4;
		public static const MOTOR_ENVIO_BLOQUEADO:int = 5;
		
		private var _labelRotulo:String;
		
		
		public function CanvasIndicador() {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			labelNomeFila.text = _labelRotulo;
		}
		
		public function carregarDados(qtd:Number, parametroAlerta:Number = NaN):void {
			this.labelNumeroMensagens.text = "" + qtd;
			alertar(obterNivelAlerta(qtd, parametroAlerta));
		}		
			
		public function set labelRotulo(labelRotulo:String):void {
			_labelRotulo = labelRotulo;
		}
		
		private function obterNivelAlerta(qtd:Number, parametroAlerta:Number):Number {
			if (isNaN(parametroAlerta)) {
				return NIVEL_NORMAL;
			} else if (qtd >= parametroAlerta) {
				return NIVEL_CRITICO;
			} else if (qtd >= parametroAlerta/2) {
				return NIVEL_ATENCAO;
			} else {
				return NIVEL_NORMAL;
			}
		}
		
		public function alertar(nivel:int):void {
			
			var cor:Number = ConstantesComum.COR_VERDE;
			
			switch (nivel) {
				case NIVEL_ATENCAO :
					cor = ConstantesComum.COR_AMARELO;
					break;
				case NIVEL_CRITICO :
					cor = ConstantesComum.COR_VERMELHO;
					break;
				case ALERTA_GRADE_HORARIA :
					cor = ConstantesComum.COR_PRETO;
					break;
				case MOTOR_ENVIO_BLOQUEADO :
					cor = ConstantesComum.COR_ROXO;
					break;
				default:
					cor = ConstantesComum.COR_VERDE;
					break;
			}
			
			canvasIndicador.setStyle("backgroundColor", cor);
		}
		
	}
}