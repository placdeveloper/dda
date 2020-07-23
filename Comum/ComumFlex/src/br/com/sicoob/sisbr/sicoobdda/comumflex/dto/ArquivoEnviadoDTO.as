package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.LogEnvioArquivoDDAVO;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ArquivoEnviadoDTO", ArquivoEnviadoDTO);
	public class ArquivoEnviadoDTO
	{
		private var _idLogEnvioArquivodda:Number;
		private var _dataHoraArquivo:IDateTime;
		private var _dataHoraEnvio:IDateTime;
		private var _descNomeArquivoEnviado:String;
		private var _dataMovimento:IDateTime;
		
		private var _codTipoMensagem:String;

		private var _listaTipoMensagem:ArrayCollection;
		
		private var _dataArquivoInicio:IDateTime;
		private var _dataArquivoFim:IDateTime;
		
		private var _dataEnvioInicio:IDateTime;
		private var _dataEnvioFim:IDateTime;
		
		private var _logEnvioArquivoDDA:LogEnvioArquivoDDAVO;
		
		public function ArquivoEnviadoDTO(){}
		
		public function get idLogEnvioArquivodda():Number {
			return _idLogEnvioArquivodda;
		}
		
		public function set idLogEnvioArquivodda(idLogEnvioArquivodda:Number):void {
			this._idLogEnvioArquivodda = idLogEnvioArquivodda;
		}
		
		
		public function get dataHoraArquivo():IDateTime {
			return _dataHoraArquivo;
		}
		
		public function set dataHoraArquivo(dataHoraArquivo:IDateTime):void {
			this._dataHoraArquivo = dataHoraArquivo;
		}
		
		public function get dataHoraEnvio():IDateTime {
			return _dataHoraEnvio;
		}
		
		public function set dataHoraEnvio(dataHoraEnvio:IDateTime):void {
			this._dataHoraEnvio = dataHoraEnvio;
		}
		
		public function get codTipoMensagem():String {
			return _codTipoMensagem;
		}
		
		public function set codTipoMensagem(codTipoMensagem:String):void {
			this._codTipoMensagem = codTipoMensagem;
		}
		
		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}
		
		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}
		
		public function get descNomeArquivoEnviado():String {
			return _descNomeArquivoEnviado;
		}
		
		public function set descNomeArquivoEnviado(descNomeArquivoEnviado:String):void {
			this._descNomeArquivoEnviado = descNomeArquivoEnviado;
		}
		
		public function get listaTipoMensagem():ArrayCollection {
			return _listaTipoMensagem;
		}
		
		public function set listaTipoMensagem(listaTipoMensagem:ArrayCollection):void {
			this._listaTipoMensagem = listaTipoMensagem;
		}
		
		public function get dataArquivoInicio():IDateTime {
			return _dataArquivoInicio;
		}
		
		public function set dataArquivoInicio(dataArquivoInicio:IDateTime):void {
			this._dataArquivoInicio = dataArquivoInicio;
		}
		
		public function get dataArquivoFim():IDateTime {
			return _dataArquivoFim;
		}
		
		public function set dataArquivoFim(dataArquivoFim:IDateTime):void {
			this._dataArquivoFim = dataArquivoFim;
		}
		
		public function get logEnvioArquivoDDA():LogEnvioArquivoDDAVO {
			return _logEnvioArquivoDDA;
		}
		
		public function set logEnvioArquivoDDA(logEnvioArquivoDDA:LogEnvioArquivoDDAVO):void {
			this._logEnvioArquivoDDA = logEnvioArquivoDDA;
		}
		
		public function get dataEnvioInicio():IDateTime {
			return _dataEnvioInicio;
		}
		
		public function set dataEnvioInicio(dataEnvioInicio:IDateTime):void {
			this._dataEnvioInicio = dataEnvioInicio;
		}
		
		public function get dataEnvioFim():IDateTime {
			return _dataEnvioFim;
		}
		
		public function set dataEnvioFim(dataEnvioFim:IDateTime):void {
			this._dataEnvioFim = dataEnvioFim;
		}
	}
}