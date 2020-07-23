package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes {

	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.containers.HBoxBancoob;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.BarraBotoesFormularioCadastroCobrancaView;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	
	public class BarraBotoesFormularioCadastroCobranca extends BarraBotoesFormularioCadastroCobrancaView {
		
		static private const TOOLTIP_BTN_HISTORICO:String = "Visualizar Histórico";
		static private const STYLENAME_BTN_HISTORICO:String = "botaoHistorico";
		
		private var _btnHistorico:Botao = new Botao();
		private var hBoxBotaoHistorico:HBoxBancoob = new HBoxBancoob();
		private var _exibirBotaoHistorico:Boolean = false;
		
		public function BarraBotoesFormularioCadastroCobranca() {
			super();
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		[Bindable]
		public  function set btnHistorico(valor:Botao):void {
			_btnHistorico = valor;
		} 
		public function get btnHistorico():Botao {
			return _btnHistorico;
		}

		protected function init(evt:FlexEvent):void {
			try {
				btnAjuda.visible = btnAjuda.includeInLayout = false;

				btnSalvar.setStyle("icon", ConstantesComum.icoSalvar);
				btnFechar.setStyle("icon", ConstantesComum.icoFechar);
				
				if(this._exibirBotaoHistorico){
					criarBotaoHistorico();
				}

				botoesFormularioCadastro.validateNow();
			} catch(error:Error) {
				MensagensComum.exibirMensagemAlerta(error.message);
			}
		}
		protected function criarBotaoHistorico():void{
			btnHistorico.styleName = STYLENAME_BTN_HISTORICO;
			btnHistorico.toolTip = TOOLTIP_BTN_HISTORICO;
			btnHistorico.setStyle("icon", ConstantesComum.icoHistorico);
			
			hBoxBotaoHistorico.percentWidth = 100;
			hBoxBotaoHistorico.addChildAt(btnHistorico, 0);
			
			botoesFormularioCadastro.addChildAt(hBoxBotaoHistorico, 0);
		}
		public function set exibirBotaoHistorico(exibir:Boolean): void{
			this._exibirBotaoHistorico = exibir;
			if(_btnHistorico != null){
				_btnHistorico.setVisible(exibir);
			}
		}		
	}
}