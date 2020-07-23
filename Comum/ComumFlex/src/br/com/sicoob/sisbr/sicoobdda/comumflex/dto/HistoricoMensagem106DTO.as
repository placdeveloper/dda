package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.HistoricoMensagem106DTO", HistoricoMensagem106DTO);
	
	public class HistoricoMensagem106DTO
	{
		
		private var _dataHoraEnvio:IDateTime;
		private var _dataHoraRetornoCIP:IDateTime;
		private var _numCodigoBarra:String;
		
		private var _descSituacaoBoleto:String;
		private var _descSituacaoBoletoCIP:String;
		
		
		public function HistoricoMensagem106DTO()
		{
		}
		
		public function get dataHoraEnvio():IDateTime {
			return _dataHoraEnvio;
		}
		
		public function set dataHoraEnvio(dataHoraEnvio:IDateTime):void {
			this._dataHoraEnvio = dataHoraEnvio;
		}
		
		public function get dataHoraRetornoCIP():IDateTime {
			return _dataHoraRetornoCIP;
		}
		
		public function set dataHoraRetornoCIP(dataHoraRetornoCIP:IDateTime):void {
			this._dataHoraRetornoCIP = dataHoraRetornoCIP;
		}
		
		
		public function get numCodigoBarra():String {
			return _numCodigoBarra;
		}
		
		public function set numCodigoBarra(numCodigoBarra:String):void {
			this._numCodigoBarra = numCodigoBarra;
		}
		
		public function get descSituacaoBoleto():String {
			return _descSituacaoBoleto;
		}
		
		public function set descSituacaoBoleto(descSituacaoBoleto:String):void {
			this._descSituacaoBoleto = descSituacaoBoleto;
		}
		
		public function get descSituacaoBoletoCIP():String {
			return _descSituacaoBoletoCIP;
		}
		
		public function set descSituacaoBoletoCIP(descSituacaoBoletoCIP:String):void {
			this._descSituacaoBoletoCIP = descSituacaoBoletoCIP;
		}
		
	}
}