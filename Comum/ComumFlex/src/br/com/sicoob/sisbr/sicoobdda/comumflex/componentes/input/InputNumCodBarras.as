package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.input
{
	import flash.events.KeyboardEvent;
	import flash.events.TextEvent;
	import flash.ui.Keyboard;
	
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	
	public class InputNumCodBarras extends InputNumCodBarrasView {
		
		private var _isCodigoBarras:Boolean = true;
		
		public function InputNumCodBarras() {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			txtCodBarras.addEventListener(KeyboardEvent.KEY_UP, tecladoPressionado);
			
			txtLinhaDigitavel1.addEventListener(KeyboardEvent.KEY_UP, tecladoPressionado);
			txtLinhaDigitavel2.addEventListener(KeyboardEvent.KEY_UP, tecladoPressionado);
			txtLinhaDigitavel3.addEventListener(KeyboardEvent.KEY_UP, tecladoPressionado);
			txtLinhaDigitavel4.addEventListener(KeyboardEvent.KEY_UP, tecladoPressionado);
			txtLinhaDigitavel5.addEventListener(KeyboardEvent.KEY_UP, tecladoPressionado);
			
			
			txtCodBarras.addEventListener(TextEvent.TEXT_INPUT, textoInserido);
			txtLinhaDigitavel1.addEventListener(TextEvent.TEXT_INPUT, textoInserido);
		}
		
		private function textoInserido(e:TextEvent):void {
			var textoTratado:String = e.text.replace(/[-.\s]/g,'');
			if (textoTratado.length == TAMANHO_MAXIMO_LINHA_DIGITAVEL) {
				preencherLinhaDigitavel(textoTratado);
			} else if (textoTratado.length == TAMANHO_MAXIMO_CODIGO_BARRAS) {
				preencherCodigoBarras(textoTratado);
			}
		}
		
		private function preencherLinhaDigitavel(linhaDigitavel:String):void {
			txtLinhaDigitavel1.text = linhaDigitavel.slice(0, 10);
			txtLinhaDigitavel2.text = linhaDigitavel.slice(10, 21);
			txtLinhaDigitavel3.text = linhaDigitavel.slice(21, 32);
			txtLinhaDigitavel4.text = linhaDigitavel.charAt(32);
			txtLinhaDigitavel5.text = linhaDigitavel.slice(33, 47);
			
			habilitarLinhaDigitavel();
			
			_isCodigoBarras = false;
			txtLinhaDigitavel1.setFocus();
			lblRotulo.text = LABEL_LINHA_DIGITAVEL;
		}
		
		private function habilitarLinhaDigitavel():void {
			txtCodBarras.visible = _isCodigoBarras = _isCodigoBarras = false;
			txtCodBarras.text = ""
			hboxLinhaDigitavel.visible = true;
		}
		
		private function preencherCodigoBarras(codigoBarras:String):void {
			txtCodBarras.text = codigoBarras;
			habilitarCodigoBarras();			
		}
		
		private function habilitarCodigoBarras():void {
			txtCodBarras.visible = _isCodigoBarras = true;
			hboxLinhaDigitavel.visible = false;
			txtLinhaDigitavel1.text = txtLinhaDigitavel2.text = txtLinhaDigitavel3.text = txtLinhaDigitavel4.text = txtLinhaDigitavel5.text = "";
		}
		
		private function tecladoPressionado(event:KeyboardEvent):void {
			if (event.keyCode == Keyboard.F12 || event.keyCode == Keyboard.F2) {
				tratarAlterarCampo();
			} 
			
			if (!_isCodigoBarras) {
				if (event.currentTarget == txtLinhaDigitavel1) {
					if (txtLinhaDigitavel1.text.length == 10) {
						txtLinhaDigitavel2.setFocus();
					}
				} else if (event.currentTarget == txtLinhaDigitavel2) {
					if (txtLinhaDigitavel2.text.length == 11) {
						txtLinhaDigitavel3.setFocus();
					}
				} else if (event.currentTarget == txtLinhaDigitavel3) {
					if (txtLinhaDigitavel3.text.length == 11) {
						txtLinhaDigitavel4.setFocus();
					}
				} else if (event.currentTarget == txtLinhaDigitavel4) {
					if (txtLinhaDigitavel4.text.length == 1) {
						txtLinhaDigitavel5.setFocus();
					}
				}
			}
		}
		
		private function tratarAlterarCampo():void {
			txtLinhaDigitavel1.text = txtLinhaDigitavel2.text = txtLinhaDigitavel3.text = txtLinhaDigitavel4.text = txtLinhaDigitavel5.text = txtCodBarras.text = "";
			
			txtCodBarras.visible = txtCodBarras.includeInLayout = !txtCodBarras.visible;
			hboxLinhaDigitavel.visible =!hboxLinhaDigitavel.visible
			
			
			if (txtCodBarras.visible) {
				_isCodigoBarras = true;
				txtCodBarras.setFocus();
				lblRotulo.text = LABEL_CODIGO_BARRAS;
			} else {
				_isCodigoBarras = false;
				txtLinhaDigitavel1.setFocus();
				lblRotulo.text = LABEL_LINHA_DIGITAVEL;
			}
		}
		
		public function limpar():void {
			txtLinhaDigitavel1.text = txtLinhaDigitavel2.text = txtLinhaDigitavel3.text = txtLinhaDigitavel4.text = txtLinhaDigitavel5.text = txtCodBarras.text = "";
			txtCodBarras.visible = _isCodigoBarras = true;
			hboxLinhaDigitavel.visible = false;
			txtCodBarras.setFocus();
			lblRotulo.text = LABEL_CODIGO_BARRAS;
		}
		
		public function get txtNumCodBarras():String {
			if (isCodigoBarras) {
				return txtCodBarras.text;
			} else {
				return txtLinhaDigitavel1.text + txtLinhaDigitavel2.text + txtLinhaDigitavel3.text + txtLinhaDigitavel4.text + txtLinhaDigitavel5.text;
			}
		}
	
		public function validar():Boolean {
			if (isCodigoBarras && isPreenchido() && txtNumCodBarras.length != TAMANHO_MAXIMO_CODIGO_BARRAS) {
				MensagensComum.exibirMensagemAlerta("Código de barras inválido.", txtCodBarras);
				return false;
			} else if (!isCodigoBarras && isPreenchido() && txtNumCodBarras.length != TAMANHO_MAXIMO_LINHA_DIGITAVEL) {
				MensagensComum.exibirMensagemAlerta("Linha digitável inválido.", txtCodBarras);
				return false;
			} 	
			return true;
		}
		
		public function isPreenchido():Boolean {
			return txtNumCodBarras.length > 0;
		}
		
		public function get isCodigoBarras():Boolean {
			return _isCodigoBarras;
		}
	}
}