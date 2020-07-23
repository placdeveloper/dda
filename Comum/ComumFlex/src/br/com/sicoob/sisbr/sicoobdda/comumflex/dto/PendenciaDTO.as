package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PendenciaDTO", PendenciaDTO);

	public class PendenciaDTO {

		private var _dataMovimento:IDateTime;
		private var _qtdEnviar:Number;
		private var _qtdSemRetornoSSPB:Number;
		private var _qtdSemRetornoCIP:Number;
		private var _qtdRetornoErro:Number;
		private var _codTipoMensagemDDA:String;

		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}

		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}

		public function set qtdEnviar(qtdEnviar:Number):void {
			this._qtdEnviar = qtdEnviar;
		}

		public function get qtdEnviar():Number {
			return _qtdEnviar;
		}

		public function set qtdSemRetornoSSPB(qtdSemRetornoSSPB:Number):void {
			this._qtdSemRetornoSSPB = qtdSemRetornoSSPB;
		}

		public function get qtdSemRetornoSSPB():Number {
			return _qtdSemRetornoSSPB;
		}

		public function set qtdSemRetornoCIP(qtdSemRetornoCIP:Number):void {
			this._qtdSemRetornoCIP = qtdSemRetornoCIP;
		}

		public function get qtdSemRetornoCIP():Number {
			return _qtdSemRetornoCIP;
		}

		public function set qtdRetornoErro(qtdRetornoErro:Number):void {
			this._qtdRetornoErro = qtdRetornoErro;
		}

		public function get qtdRetornoErro():Number {
			return _qtdRetornoErro;
		}
		
		public function set codTipoMensagemDDA(codTipoMensagemDDA:String):void {
			this._codTipoMensagemDDA = codTipoMensagemDDA;
		}
		
		public function get codTipoMensagemDDA():String {
			return _codTipoMensagemDDA;
		}

	}
}