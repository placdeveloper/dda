package br.com.sicoob.sisbr.sicoobdda.comumflex.dto 
{
	
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.GradeContingenciaDTO", GradeContingenciaDTO);
	
	public class GradeContingenciaDTO 
	{
		private var _descTipoContingencia:String;
		private var _dataHoraSituacaoInicial:IDateTime;
		private var _idUsuarioHabilitacao:String;
		private var _dataHoraSituacaoFinal:IDateTime;
		private var _idUsuarioDesabilitacao:String;
		private var _situacaoContingencia:String;
		
		public function get descTipoContingencia():String 
		{
			return _descTipoContingencia;
		}
		
		public function set descTipoContingencia(descTipoContingencia:String):void 
		{
			this._descTipoContingencia = descTipoContingencia;
		}
		
		public function get dataHoraSituacaoInicial():IDateTime 
		{
			return _dataHoraSituacaoInicial;
		}
		
		public function set dataHoraSituacaoInicial(dataHoraSituacaoInicial:IDateTime):void 
		{
			this._dataHoraSituacaoInicial = dataHoraSituacaoInicial;
		}
		
		public function get idUsuarioHabilitacao():String 
		{
			return _idUsuarioHabilitacao;
		}
		
		public function set idUsuarioHabilitacao(idUsuarioHabilitacao:String):void 
		{
			this._idUsuarioHabilitacao = idUsuarioHabilitacao;
		}
		
		public function get dataHoraSituacaoFinal():IDateTime 
		{
			return _dataHoraSituacaoFinal;
		}
		
		public function set dataHoraSituacaoFinal(dataHoraSituacaoFinal:IDateTime):void 
		{
			this._dataHoraSituacaoFinal = dataHoraSituacaoFinal;
		}
		
		public function get idUsuarioDesabilitacao():String 
		{
			return _idUsuarioDesabilitacao;
		}
		
		public function set idUsuarioDesabilitacao(idUsuarioDesabilitacao:String):void 
		{
			this._idUsuarioDesabilitacao = idUsuarioDesabilitacao;
		}
		
		public function get situacaoContingencia():String 
		{
			return _situacaoContingencia;
		}
		
		public function set situacaoContingencia(situacaoContingencia:String):void 
		{
			this._situacaoContingencia = situacaoContingencia;
		}
		
	}
	
}