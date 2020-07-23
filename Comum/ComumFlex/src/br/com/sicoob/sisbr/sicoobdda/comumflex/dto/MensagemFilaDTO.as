package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemFilaDTO", MensagemFilaDTO);
	
	public class MensagemFilaDTO
	{
		
		private var _qtdAguardandoEnvio:Number;
		private var _qtdRetornoComErro:Number;
		private var _qtdSemRetornoCIP:Number;
		private var _qtdSemRetornoSSPB:Number;
		
		private var _alertaAguardandoEnvio:Number;
		private var _alertaRetornoComErro:Number;
		private var _alertaSemRetornoCIP:Number;
		private var _alertaSemRetornoSSPB:Number;
		
		private var _dataMovimento:IDateTime;

		private var _codTipoErroCip:String;
		private var _descTipoErroCip:String;
		private var _total:Number;
		private var _codTipoMensagemDDA:String;
		
		
		public function MensagemFilaDTO()
		{
		}
		
		
		public function get qtdAguardandoEnvio():Number {
			return _qtdAguardandoEnvio;
		}
		
		public function set qtdAguardandoEnvio(qtdAguardandoEnvio:Number):void {
			this._qtdAguardandoEnvio = qtdAguardandoEnvio;
		}
		
		public function get qtdRetornoComErro():Number {
			return _qtdRetornoComErro;
		}
		
		public function set qtdRetornoComErro(qtdRetornoComErro:Number):void {
			this._qtdRetornoComErro = qtdRetornoComErro;
		}
		
		public function get qtdSemRetornoCIP():Number {
			return _qtdSemRetornoCIP;
		}
		
		public function set qtdSemRetornoCIP(qtdSemRetornoCIP:Number):void {
			this._qtdSemRetornoCIP = qtdSemRetornoCIP;
		}
		
		public function get qtdSemRetornoSSPB():Number {
			return _qtdSemRetornoSSPB;
		}
		
		public function set qtdSemRetornoSSPB(qtdSemRetornoSSPB:Number):void {
			this._qtdSemRetornoSSPB = qtdSemRetornoSSPB;
		}
		
		public function get alertaSemRetornoSSPB():Number {
			return _alertaSemRetornoSSPB;
		}
		
		public function set alertaSemRetornoSSPB(alertaSemRetornoSSPB:Number):void {
			this._alertaSemRetornoSSPB = alertaSemRetornoSSPB;
		}

		public function get alertaAguardandoEnvio():Number {
			return _alertaAguardandoEnvio;
		}
		
		public function set alertaAguardandoEnvio(alertaAguardandoEnvio:Number):void {
			this._alertaAguardandoEnvio = alertaAguardandoEnvio;
		}
		
		public function get alertaRetornoComErro():Number {
			return _alertaRetornoComErro;
		}
		
		public function set alertaRetornoComErro(alertaRetornoComErro:Number):void {
			this._alertaRetornoComErro = alertaRetornoComErro;
		}
		
		public function get alertaSemRetornoCIP():Number {
			return _alertaSemRetornoCIP;
		}
		
		public function set alertaSemRetornoCIP(alertaSemRetornoCIP:Number):void {
			this._alertaSemRetornoCIP = alertaSemRetornoCIP;
		}

		public function get codTipoErroCip():String {
			return _codTipoErroCip;
		}
		
		public function set codTipoErroCip(codTipoErroCip:String):void {
			this._codTipoErroCip = codTipoErroCip;
		}
		
		public function get descTipoErroCip():String {
			return _descTipoErroCip;
		}
		
		public function set descTipoErroCip(descTipoErroCip:String):void {
			this._descTipoErroCip = descTipoErroCip;
		}
		
		public function get total():Number {
			return _total;
		}
		
		public function set total(total:Number):void {
			this._total = total;
		}
		
		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}
		
		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}
		
		public function get codTipoMensagemDDA():String {
			return _codTipoMensagemDDA;
		}
		
		public function set codTipoMensagemDDA(codTipoMensagemDDA:String):void {
			this._codTipoMensagemDDA = codTipoMensagemDDA;
		}
		
	}
}