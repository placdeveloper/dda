package
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesTipoMensagem;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.CadastroMensagemView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.AGEN001;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.DDA0002;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.DDA0106;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.DDA0110;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.DDA0200;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.DDA0214;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.DDA0215;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.DDA0401;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.DDA0504;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.GEN0014;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cadastro.mensagem.IMensagemDDA;

	registerClassAlias("RegistroVO", RegistroVO);
	
	public class CadastroMensagem extends CadastroMensagemView	{

		private var _mensagem:IMensagemDDA;
		
		public function CadastroMensagem() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected function init(event:FlexEvent):void {
			initGrid();
			initBotoes();
			initEvento();
		}
		
		private function initGrid():void {
			cbTipoMensagem.dataProvider = ConstantesTipoMensagem.TIPO_MENSAGEM_CADASTRO.slice();
		}
		
		private function initBotoes():void {
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			btnSalvar.addEventListener(MouseEvent.CLICK, confirmarSalvar);
			btnLimpar.addEventListener(MouseEvent.CLICK, limpar);
		}
		
		private function initEvento():void {
			cbTipoMensagem.addEventListener(Event.CHANGE, carregarMensagem);
		}
		
			
		protected function carregarMensagem(e:Event) : void{
			var tipoMensagem:Object = e.target.selectedItem;
			if (tipoMensagem) {
				removeTelaMensagem();
				switch(tipoMensagem.codTipo) {
					
					case ConstantesTipoMensagem.AGEN001:
						_mensagem = new AGEN001();
						break;
					case ConstantesTipoMensagem.GEN0014:
						_mensagem = new GEN0014();
						break;
					case ConstantesTipoMensagem.DDA0002:
						_mensagem = new DDA0002();
						break;
					case ConstantesTipoMensagem.DDA0106:
						_mensagem = new DDA0106();
						break;
					case ConstantesTipoMensagem.DDA0110:
						_mensagem = new DDA0110();
						break;
					case ConstantesTipoMensagem.DDA0200:
						_mensagem = new DDA0200();
						break;
					case ConstantesTipoMensagem.DDA0214:
						_mensagem = new DDA0214();
						break;
					case ConstantesTipoMensagem.DDA0215:
						_mensagem = new DDA0215();
						break;
					case ConstantesTipoMensagem.DDA0401:
						_mensagem = new DDA0401();
						break;
					case ConstantesTipoMensagem.DDA0504:
						_mensagem = new DDA0504();
						break;
					default: 
						MensagensComum.exibirMensagemAlerta("Mensagem ainda não liberada para cadastro!", cbTipoMensagem);
						break;
				}
				addTelaMensagem();
			}
		}
	
		private function removeTelaMensagem():void {
			if (_mensagem) {
				fsCadastroMensagem.removeChild(_mensagem as DisplayObject);
				_mensagem = null;
			}
		}
		
		private function addTelaMensagem():void {
			if (_mensagem) {
				fsCadastroMensagem.addChild(_mensagem as DisplayObject);
			}
		}
		
		private function confirmarSalvar(e:MouseEvent):void {
			if (!_mensagem) {
				MensagensComum.exibirMensagemAlerta("Nenhuma mensagem selecionada!", cbTipoMensagem);
			} else if (_mensagem.validarDados()) {
				_mensagem.salvar();
			}
		}
		
		private function fechar(e:MouseEvent):void {
			this.fecharJanela();
		}
		
		private function limpar(e:MouseEvent):void {
			removeTelaMensagem();
			cbTipoMensagem.selectedIndex = -1;
		}
		
	}
}