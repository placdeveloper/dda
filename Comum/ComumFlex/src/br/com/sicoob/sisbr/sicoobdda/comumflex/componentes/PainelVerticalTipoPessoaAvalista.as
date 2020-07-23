package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes
{
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.utils.StringUtil;

	public class PainelVerticalTipoPessoaAvalista extends PainelVerticalTipoPessoaAvalistaView
	{
		public function PainelVerticalTipoPessoaAvalista ()
		{
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			initEvento();
		}
		
		private function initEvento():void {
			cbTipoPessoaAvalista.addEventListener(Event.CHANGE, selecionarTipoAvalista);
		}
		
		private function selecionarTipoAvalista(e:Event):void {
			if(this.cbTipoPessoaAvalista.selectedItem !=null) {
				var tipo:String = this.cbTipoPessoaAvalista.selectedItem.codTipoPessoaDDAAvalista as String;
			selecionarTipoDocumento(tipo, "");				
			}
		}
		
		public function selecionarTipoDocumento(tipo:String, documento:String):void {
			if(tipo != null) {
				if(tipo == "1") {
					this.labelDocumento.text = "CPF:";
					this.txtCpfAvalista.visible = true;
					this.txtCpfAvalista.text = documento;
					this.txtCnpjAvalista.visible = false;
					this.txtDoc.visible = false;
					this.txtPisPasep.visible = false;
					this.txtCnpjAvalista.text = "";
					this.txtDoc.text = "";
					this.txtPisPasep.text = "";
				} else if(tipo == "2") {
					this.labelDocumento.text = "CNPJ:";
					this.txtCpfAvalista.visible = false;
					this.txtCnpjAvalista.visible = true;
					this.txtCnpjAvalista.text = documento;
					this.txtDoc.visible = false;
					this.txtPisPasep.visible = false;
					this.txtDoc.text = "";
					this.txtCpfAvalista.text = "";
					this.txtPisPasep.text = "";
				} else if(tipo == "3") {
					this.labelDocumento.text = "PIS/PASEP:";
					this.txtCpfAvalista.visible = false;
					this.txtCnpjAvalista.visible = false;
					this.txtDoc.visible = false;
					this.txtPisPasep.visible = true;
					this.txtPisPasep.text = documento;
					this.txtDoc.text = "";
					this.txtCpfAvalista.text = "";
					this.txtCnpjAvalista.text = "";
				} else {
					this.labelDocumento.text = "Documento:";
					this.txtCpfAvalista.visible = false;
					this.txtCnpjAvalista.visible = false;
					this.txtDoc.visible = true;
					this.txtPisPasep.visible = false;
					this.txtDoc.text = "";
					this.txtCpfAvalista.text = "";
					this.txtCnpjAvalista.text = "";
					this.txtPisPasep.text = "";
				}
			} 
		}
		
		private function stringNull(param:String):Boolean {
			var ret:String = StringUtil.trim(param);
			return ret == null || ret.length < 1;
		}
		
		public function retornoValor():String {
			var ret:String = null;
			
			if(!stringNull(this.txtCnpjAvalista.text)) {
				ret = this.txtCnpjAvalista.text
			} else if(!stringNull(this.txtCpfAvalista.text)) {
				ret = this.txtCpfAvalista.text
			} else if(!stringNull(this.txtDoc.text)) {
				ret = this.txtDoc.text
			} else if(!stringNull(this.txtPisPasep.text)) {
				ret = this.txtPisPasep.text
			}
			return ret;
		}
	}
}