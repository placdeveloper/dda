package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoMensagemDTO", TipoMensagemDTO);
	
	public class TipoMensagemDTO
	{
		private var _codTipoMensagem:String;
		private var _descTipoMensagem:String;
		private var _numPrioridadeEnvio:Number;
		private var _bolExigeMensagemRetorno:Boolean;
		private var _exigeMensagemRetorno:Number;
		private var _codTipoGradeHorariaOrigem:String;
		private var _listaTipoGradeHoraria:ArrayCollection;
		private var _listaCategoriaMensagem:ArrayCollection;    
		private var _listaArquivoCorrespondente:ArrayCollection;   
		
		private var _codCategoriaMensagemDda:String;
		private var _codTipoGradeHoraria:String;
		
		private var _codTipoArquivoCorrespondente:String;
		
		public function TipoMensagemDTO()
		{
		}
		
		
		public function get codTipoMensagem():String {
			return _codTipoMensagem;
		}
		
		public function set codTipoMensagem(codTipoMensagem:String):void {
			this._codTipoMensagem = codTipoMensagem;
		}
		
		public function get descTipoMensagem():String {
			return _descTipoMensagem;
		}
		
		public function set descTipoMensagem(descTipoMensagem:String):void {
			this._descTipoMensagem = descTipoMensagem;
		}
		
		public function get numPrioridadeEnvio():Number {
			return _numPrioridadeEnvio;
		}
		
		public function set numPrioridadeEnvio(numPrioridadeEnvio:Number):void {
			this._numPrioridadeEnvio = numPrioridadeEnvio;
		}
		
		public function get bolExigeMensagemRetorno():Boolean {
			return _bolExigeMensagemRetorno;
		}
		
		public function set bolExigeMensagemRetorno(bolExigeMensagemRetorno:Boolean):void {
			this._bolExigeMensagemRetorno = bolExigeMensagemRetorno;
		}
		
		public function get exigeMensagemRetorno():Number {
			return _exigeMensagemRetorno;
		}
		
		public function set exigeMensagemRetorno(exigeMensagemRetorno:Number):void {
			this._exigeMensagemRetorno = exigeMensagemRetorno;
		}
		
		public function get listaTipoGradeHoraria():ArrayCollection {
			return _listaTipoGradeHoraria;
		}
		
		public function set listaTipoGradeHoraria(listaTipoGradeHoraria:ArrayCollection):void {
			this._listaTipoGradeHoraria = listaTipoGradeHoraria;
		}
		
		public function get listaCategoriaMensagem():ArrayCollection {
			return _listaCategoriaMensagem;
		}
		
		public function set listaCategoriaMensagem(listaCategoriaMensagem:ArrayCollection):void {
			this._listaCategoriaMensagem = listaCategoriaMensagem;
		}
		
		public function get listaArquivoCorrespondente():ArrayCollection {
			return _listaArquivoCorrespondente;
		}
		
		public function set listaArquivoCorrespondente(listaArquivoCorrespondente:ArrayCollection):void {
			this._listaArquivoCorrespondente = listaArquivoCorrespondente;
		}
		
		public function get codCategoriaMensagemDda():String {
			return _codCategoriaMensagemDda;
		}
		
		public function set codCategoriaMensagemDda(codCategoriaMensagemDda:String):void {
			this._codCategoriaMensagemDda = codCategoriaMensagemDda;
		}
		
		public function get codTipoGradeHoraria():String {
			return _codTipoGradeHoraria;
		}
		
		public function set codTipoGradeHoraria(codTipoGradeHoraria:String):void {
			this._codTipoGradeHoraria = codTipoGradeHoraria;
		}
		
		public function get codTipoArquivoCorrespondente():String {
			return _codTipoArquivoCorrespondente;
		}
		
		public function set codTipoArquivoCorrespondente(codTipoArquivoCorrespondente:String):void {
			this._codTipoArquivoCorrespondente = codTipoArquivoCorrespondente;
		}
		
	}
}