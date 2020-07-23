package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ViewTempoMensagemVO", ViewTempoMensagemVO);

	public class ViewTempoMensagemVO {

		private var _dataHoraMensagem:IDateTime;
		private var _dataHoraLabel:String;
		private var _bolMensagemConsulta:Boolean;
		private var _qtdMensagem:Number;
		private var _tempoMedio:Number;
		private var _tempoMedioSSPB:Number;

		public function set dataHoraMensagem(dataHoraMensagem:IDateTime):void {
			this._dataHoraMensagem = dataHoraMensagem;
		}
		
		public function get dataHoraMensagem():IDateTime {
			return _dataHoraMensagem;
		}
		
		public function set dataHoraLabel(dataHoraLabel:String):void {
			this._dataHoraLabel = dataHoraLabel;
		}

		public function get dataHoraLabel():String {
			return _dataHoraLabel;
		}

		public function set bolMensagemConsulta(bolMensagemConsulta:Boolean):void {
			this._bolMensagemConsulta = bolMensagemConsulta;
		}

		public function get bolMensagemConsulta():Boolean {
			return _bolMensagemConsulta;
		}

		public function set qtdMensagem(qtdMensagem:Number):void {
			this._qtdMensagem = qtdMensagem;
		}

		public function get qtdMensagem():Number {
			return _qtdMensagem;
		}

		public function set tempoMedio(tempoMedio:Number):void {
			this._tempoMedio = tempoMedio;
		}

		public function get tempoMedio():Number {
			return _tempoMedio;
		}

		public function set tempoMedioSSPB(tempoMedioSSPB:Number):void {
			this._tempoMedioSSPB = tempoMedioSSPB;
		}
		
		public function get tempoMedioSSPB():Number {
			return _tempoMedioSSPB;
		}
	}
}