package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.tooltip
{
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;

	public class TooltipMonitoracaoLegenda extends TooltipMonitoracaoLegendaView {

		private var _modo:Number;
		
		public static const MODO_NORMAL:int = 1; 
		public static const MODO_ATENCAO:int = 2;
		public static const MODO_CRITICO:int = 3;
		
		public static const MODO_GRADE_HORARIA:int = 4;
		public static const MODO_MOTOR_BLOQUEADO:int = 5;
		
		public static const MODO_DDA0110_NORMAL:int = 6;
		public static const MODO_DDA0110_ATENCAO:int = 7;
		public static const MODO_DDA0110_CRITICO:int = 8;
		
		
		public function TooltipMonitoracaoLegenda(modo:int = MODO_NORMAL) {
			_modo = modo;
			_exibirInformativo = false;
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}

		private function init(e:FlexEvent = null):void {
			switch (_modo) {
				case MODO_ATENCAO: {
					initModoAlerta();
					break;
				}
				case MODO_CRITICO: {
					initModoCritico();
					break;
				}
				case MODO_GRADE_HORARIA: {
					initModoAlertaGrade();
					break;
				}
				case MODO_MOTOR_BLOQUEADO: {
					initModoMotorbBloqueado();
					break;
				}
				case MODO_DDA0110_NORMAL: {
					initModoDDA0110Normal();
					break;
				}
				case MODO_DDA0110_ATENCAO: {
					initModoDDA0110Atencao();
					break;
				}
				case MODO_DDA0110_CRITICO: {
					initModoDDA0110Critico();
					break;
				}
				default: {
					initModoNormal();
					break;
				}
			}
		}
		
		private function initModoNormal():void {
			_corFundo = ConstantesComum.COR_VERDE;
			_titulo = "Quantidade de registros dentro do esperado";
			_legenda = "Quantidade de registros inferior a 50% do valor do parâmetro";
			_exibirInformativo = false;
			_exibirLegenda = true;			
		}
		
		private function initModoAlerta():void {
			_corFundo = ConstantesComum.COR_AMARELO;
			_titulo = "Quantidade de registros próximo do valor crítico";
			_legenda = "Quantidade de registros entre 50% e 100% do valor do parâmetro";
			_exibirInformativo = false;
			_exibirLegenda = true;
		}
		
		private function initModoCritico():void {
			_corFundo = ConstantesComum.COR_VERMELHO;
			_titulo = "ESTADO CRÍTICO";
			_fontWeightTitulo = "bold";
			_legenda = "Quantidade de registros igual ou superior ao valor do parâmetro";
			_exibirInformativo = true;
			_exibirLegenda = true;
		}
		
		private function initModoAlertaGrade():void {
			_corFundo = ConstantesComum.COR_PRETO;
			_titulo = "REGISTRO FORA DA GRADE";
			_exibirInformativo = true;
		}
		
		private function initModoMotorbBloqueado():void {
			_corFundo = ConstantesComum.COR_ROXO;
			_titulo = "MOTOR DE ENVIO BLOQUEADO";
			_exibirInformativo = true;
		}
		
		private function initModoDDA0110Normal():void {
			_corFundo = ConstantesComum.COR_VERDE;
			_titulo = "Quantidade de registros dentro do esperado";
			_exibirInformativo = false;
			_exibirLegenda = false;				
		}
		
		private function initModoDDA0110Atencao():void {
			_corFundo = ConstantesComum.COR_AMARELO;
			_titulo = "Sem novos cadastros de DDA0110";
			_legenda = "Contingência ligada ou Consulta à CIP desligada.";
			_exibirInformativo = false;
			_exibirLegenda = true		
		}
		
		private function initModoDDA0110Critico():void {
			_corFundo = ConstantesComum.COR_VERMELHO;
			_titulo = "ESTADO CRÍTICO";
			_legenda = "Quantidade de registros superior ao valor do parâmetro";
			_fontWeightTitulo = "bold";
			_exibirInformativo = true;
			_exibirLegenda = true;	
		}
		
		public function set modo(modo:int):void {
			_modo = modo;
		}
		
	}
}