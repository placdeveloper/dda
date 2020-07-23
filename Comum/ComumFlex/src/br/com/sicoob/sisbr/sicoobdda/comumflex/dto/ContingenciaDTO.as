package br.com.sicoob.sisbr.sicoobdda.comumflex.dto 
{
	
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ContingenciaDTO", ContingenciaDTO);
	
	public class ContingenciaDTO 
	{
		private var _dataHoraSituacao:IDateTime;
		private var _tipoContingencia:Number;
		private var _idUsuario:String;
		private var _idHabilitacaoContingencia:Number;
		private var _contingenciaHabilitada:Boolean;
		private var _descSituacaoContingencia:String;
		
		public function get dataHoraSituacao():IDateTime 
		{
			return _dataHoraSituacao;
		}
		
		public function set dataHoraSituacao(dataHoraSituacao:IDateTime):void 
		{
			this._dataHoraSituacao = dataHoraSituacao;
		}
		
		public function get tipoContingencia():Number 
		{
			return _tipoContingencia;
		}
		
		public function set tipoContingencia(tipoContingencia:Number):void 
		{
			this._tipoContingencia = tipoContingencia;
		}
		
		public function get idUsuario():String 
		{
			return _idUsuario;
		}
		
		public function set idUsuario(idUsuario:String):void 
		{
			this._idUsuario = idUsuario;
		}
		
		public function get idHabilitacaoContingencia():Number 
		{
			return _idHabilitacaoContingencia;
		}
		
		public function set idHabilitacaoContingencia(idHabilitacaoContingencia:Number):void 
		{
			this._idHabilitacaoContingencia =  idHabilitacaoContingencia;
		}
		
		public function get contingenciaHabilitada():Boolean 
		{
			return _contingenciaHabilitada;
		}
		
		public function set contingenciaHabilitada(contingenciaHabilitada:Boolean):void 
		{
			this._contingenciaHabilitada =  contingenciaHabilitada;
		}
		
		public function set descSituacaoContingencia(descSituacaoContingencia:String):void 
		{
			this._descSituacaoContingencia = descSituacaoContingencia;
		}
		
		public function get descSituacaoContingencia():String 
		{
			return _descSituacaoContingencia;
		}
		
	}
	
}