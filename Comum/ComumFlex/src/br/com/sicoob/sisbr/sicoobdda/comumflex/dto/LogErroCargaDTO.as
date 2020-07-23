package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.LogErroCargaDTO", LogErroCargaDTO);
	
	
	public class LogErroCargaDTO
	{
		public function LogErroCargaDTO()
		{
		}
		
		private var _idLogErro:Number;
		private var _numCPFCNPJ:String;
		private var _dataInicio:IDateTime;
		private var _dataFim:IDateTime;
		private var _dataHoraCadastro:IDateTime;
		
		private var _codTipoErro:String;
		private var _descTipoErro:String ;
		private var _idInstituicao:Number;
		
		private var _listaErroCarga:ArrayCollection;
		private var _listaCooperativa:ArrayCollection;
		
		public function get idLogErro():Number {
			return _idLogErro;
		}
		
		public function set idLogErro(idLogErro:Number):void {
			this._idLogErro = idLogErro;
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}
		
		public function get numCPFCNPJ():String {
			return _numCPFCNPJ;
		}
		
		public function set numCPFCNPJ(numCPFCNPJ:String):void {
			this._numCPFCNPJ = numCPFCNPJ;
		}
		
		public function get codTipoErro():String {
			return _codTipoErro;
		}
		
		public function set codTipoErro(codTipoErro:String):void {
			this._codTipoErro = codTipoErro;
		}
		
		public function get descTipoErro():String {
			return _descTipoErro;
		}
		
		public function set descTipoErro(descTipoErro:String):void {
			this._descTipoErro = descTipoErro;
		}
			
		public function get dataInicio():IDateTime {
			return _dataInicio;
		}
			
		public function set dataInicio(dataInicio:IDateTime):void {
			this._dataInicio = dataInicio;
		}
		
		public function get dataFim():IDateTime {
			return _dataFim;
		}
		
		public function set dataFim(dataFim:IDateTime):void {
			this._dataFim = dataFim
		}
		
		public function get dataHoraCadastro():IDateTime {
			return _dataHoraCadastro;
		}
		
		public function set dataHoraCadastro(dataHoraCadastro:IDateTime):void {
			this._dataHoraCadastro = dataHoraCadastro;
		}
		
		public function get listaErroCarga():ArrayCollection {
			return _listaErroCarga;
		}
		
		public function set listaErroCarga(listaErroCarga:ArrayCollection):void {
			this._listaErroCarga = listaErroCarga;
		}
		
		public function get listaCooperativa():ArrayCollection {
			return _listaCooperativa;
		}
		
		public function set listaCooperativa(listaCooperativa:ArrayCollection):void {
			this._listaCooperativa = listaCooperativa;
		}
	}
}