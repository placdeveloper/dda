package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {


	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoMensagemDDAVO", TipoMensagemDDAVO);

	public class TipoMensagemDDAVO {

		public static const DDA0501:String = "DDA0501";
		public static const DDA0502:String = "DDA0502";
		public static const DDA0503:String = "DDA0503";
		public static const DDA0505:String = "DDA0505";
		public static const DDA0501E:String = "DDA0501E";
		public static const DDA0502E:String = "DDA0502E";
		public static const DDA0503E:String = "DDA0503E";
		public static const DDA0505E:String = "DDA0505E";
		
		private var _codTipoMensagem:String;
		private var _descTipoMensagem:String;
		
		private var _numPrioridadeEnvio:Number;
		private var _categoriaMensagemDDA:CategoriaMensagemDDAVO;
		private var _bolExigeMensagemRetorno:Boolean;
		private var _tipoGradeHoraria:TipoGradeHorariaVO;
		private var _codTipoArquivoCorrespondente:String;

		public function set codTipoMensagem(codTipoMensagem:String):void {
			this._codTipoMensagem = codTipoMensagem;
		}

		public function get codTipoMensagem():String {
			return _codTipoMensagem;
		}

		public function set descTipoMensagem(descTipoMensagem:String):void {
			this._descTipoMensagem = descTipoMensagem;
		}

		public function get descTipoMensagem():String {
			return _descTipoMensagem;
		}
		
		public function set numPrioridadeEnvio(numPrioridadeEnvio:Number):void {
			this._numPrioridadeEnvio = numPrioridadeEnvio;
		}
		
		public function get numPrioridadeEnvio():Number {
			return _numPrioridadeEnvio;
		}
		
		public function set categoriaMensagemDDA(categoriaMensagemDDA:CategoriaMensagemDDAVO):void {
			this._categoriaMensagemDDA = categoriaMensagemDDA;
		}
		
		public function get categoriaMensagemDDA():CategoriaMensagemDDAVO {
			return _categoriaMensagemDDA;
		}
		
		public function set bolExigeMensagemRetorno(bolExigeMensagemRetorno:Boolean):void {
			this._bolExigeMensagemRetorno = bolExigeMensagemRetorno;
		}
		
		public function get bolExigeMensagemRetorno():Boolean {
			return _bolExigeMensagemRetorno;
		}
		
		public function set tipoGradeHoraria(tipoGradeHoraria:TipoGradeHorariaVO):void {
			this._tipoGradeHoraria = tipoGradeHoraria;
		}
		
		public function get tipoGradeHoraria():TipoGradeHorariaVO {
			return _tipoGradeHoraria;
		}
		
		public function set codTipoArquivoCorrespondente(codTipoArquivoCorrespondente:String):void {
			this._codTipoArquivoCorrespondente = codTipoArquivoCorrespondente;
		}
		
		public function get codTipoArquivoCorrespondente():String {
			return _codTipoArquivoCorrespondente;
		}
		
		
	}
}