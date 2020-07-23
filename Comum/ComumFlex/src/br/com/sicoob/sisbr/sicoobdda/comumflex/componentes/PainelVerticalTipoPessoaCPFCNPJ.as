package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes
{
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.DominioCIP;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	
	public class PainelVerticalTipoPessoaCPFCNPJ extends PainelVerticalTipoPessoaCPFCNPJView {
		
		public function PainelVerticalTipoPessoaCPFCNPJ() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initCampos();
			initEvento();
		}
		
		private function initCampos():void {
			cbTipoPessoa.dataProvider = DominioCIP.TIPO_PESSOA.slice();
		}
		
		private function initEvento():void {
			cbTipoPessoa.addEventListener(Event.CHANGE, selecionarTipoPessoa);
		}

		private function selecionarTipoPessoa(e:Event):void {
			var tipoPessoa:Object = e.target.selectedItem;
			if (tipoPessoa && tipoPessoa.data == DominioCIP.TIPO_PESSOA_FISICA) {
				habilitaCPF();
			} else if (tipoPessoa && tipoPessoa.data == DominioCIP.TIPO_PESSOA_JURIDICA) {
				habilitaCNPJ();
			} else {
				limparDados();
			}
		}
		
		private function set includeLayout(includeL:Boolean):void {
			_includeInLayout = includeL;
		}
		
		private function habilitaCPF():void {
			labelCPF.visible = true;
			labelCNPJ.visible = false;
			
			txtCpf.enabled = true;
			txtCpf.visible = true;
			
			txtCnpj.enabled = false;
			txtCnpj.visible = false;
		}
		
		private function habilitaCNPJ():void {
			labelCPF.visible = false;
			labelCNPJ.visible = true;
			
			txtCpf.enabled = false;
			txtCpf.visible = false;
			
			txtCnpj.enabled = true;
			txtCnpj.visible = true;
		}
		
		private function desabilitaTxt():void {
			txtCpf.enabled = false;
			txtCpf.visible = false;
			
			txtCnpj.enabled = false;
			txtCnpj.visible = false;
		}
		
		public function get codTipoPessoa():String {
			if (!cbTipoPessoa.selectedItem) {
				return null;
			}
			return cbTipoPessoa.selectedItem.data;
		}
		
		public function get cpfCnpj():String {
			if (!cbTipoPessoa.selectedItem) {
				return null;
			} else if (cbTipoPessoa.selectedItem.data == DominioCIP.TIPO_PESSOA_FISICA) {
				return txtCpf.text;
			} else {
				return txtCnpj.text;
			}
		}
		
		public function validarDados():Boolean {
			if (!codTipoPessoa) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "Tipo Pessoa"), cbTipoPessoa);
				return false;
			} else if (codTipoPessoa == DominioCIP.TIPO_PESSOA_FISICA) {
				if (txtCpf.valor == 0) {
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "CPF"), cbTipoPessoa);
					return false;
				} else if (!txtCpf.validar()) {
					MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG030, "CPF"), cbTipoPessoa);
					return false;
				}
			} else if (txtCnpj.valor == 0) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG017, "CNPJ"), cbTipoPessoa);
				return false;
			} else if (!txtCnpj.validar()) {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG030, "CNPJ"), cbTipoPessoa);
				return false;
			}
			return true;
		}
		
		public function limparDados():void {
			txtCnpj.text = "";
			txtCpf.text = "";
			cbTipoPessoa.selectedIndex = 0;
			desabilitaTxt();
		}
		
	}
}


