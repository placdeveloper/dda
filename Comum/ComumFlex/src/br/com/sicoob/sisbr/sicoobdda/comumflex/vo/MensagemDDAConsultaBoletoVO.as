package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAConsultaBoletoVO", MensagemDDAConsultaBoletoVO);

	public class MensagemDDAConsultaBoletoVO {

		private var _id:Number;
		private var _mensagemDDA:MensagemDDAVO;
		private var _numCodigoBarra:String;
		private var _numCpfCnpjPagador:String;
		private var _codSituacaoBoleto:Number;
		private var _numIdentBoletoInicial:Number;
		private var _numIdentBoletoFinal:Number;
		private var _numIdentBoletoInicialStr:String;
		private var _numIdentBoletoFinalStr:String;

		private var _codTipoBoletoConsultado:String;
		
		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set mensagemDDA(mensagemDDA:MensagemDDAVO):void {
			this._mensagemDDA = mensagemDDA;
		}

		public function get mensagemDDA():MensagemDDAVO {
			return _mensagemDDA;
		}

		public function set numCodigoBarra(numCodigoBarra:String):void {
			this._numCodigoBarra = numCodigoBarra;
		}

		public function get numCodigoBarra():String {
			return _numCodigoBarra;
		}

		public function set numCpfCnpjPagador(numCpfCnpjPagador:String):void {
			this._numCpfCnpjPagador = numCpfCnpjPagador;
		}

		public function get numCpfCnpjPagador():String {
			return _numCpfCnpjPagador;
		}
		
		public function set codSituacaoBoleto(codSituacaoBoleto:Number):void {
			this._codSituacaoBoleto = codSituacaoBoleto;
		}
		
		public function get codSituacaoBoleto():Number {
			return _codSituacaoBoleto;
		}

		public function set numIdentBoletoInicial(numIdentBoletoInicial:Number):void {
			this._numIdentBoletoInicial = numIdentBoletoInicial;
		}
		
		public function get numIdentBoletoInicial():Number {
			return _numIdentBoletoInicial;
		}
		
		public function set numIdentBoletoFinal(numIdentBoletoFinal:Number):void {
			this._numIdentBoletoFinal = numIdentBoletoFinal;
		}
		
		public function get numIdentBoletoFinal():Number {
			return _numIdentBoletoFinal;
		}
		
		public function set codTipoBoletoConsultado(codTipoBoletoConsultado:String):void {
			this._codTipoBoletoConsultado = codTipoBoletoConsultado;
		}
		
		public function get codTipoBoletoConsultado():String {
			return _codTipoBoletoConsultado;
		}
		
		
		public function set numIdentBoletoInicialStr(numIdentBoletoInicialStr:String):void {
			this._numIdentBoletoInicialStr = numIdentBoletoInicialStr;
		}
		
		public function get numIdentBoletoInicialStr():String {
			return _numIdentBoletoInicialStr;
		}
		
		public function set numIdentBoletoFinalStr(numIdentBoletoFinalStr:String):void {
			this._numIdentBoletoFinalStr = numIdentBoletoFinalStr;
		}
		
		public function get numIdentBoletoFinalStr():String {
			return _numIdentBoletoFinalStr;
		}
	}
}