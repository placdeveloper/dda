package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.LogArquivoCargaDTO", LogArquivoCargaDTO);
	
	public class LogArquivoCargaDTO
	{
		public function LogArquivoCargaDTO()
		{
		}
		
		private var _idArquivo:Number;
		private var _descNomeArquivoEnviado:String;
		private var _bolOrigemSicoob:Boolean;
		private var _dataHoraDDA:IDateTime;
		private var _dataHoraMovimento:IDateTime;
		private var _descSituacaoArquivo:String;
		private var _statusArquivo:Number;
		private var _selecionado:Boolean;
		
		private var _descNomeArquivoRecebido:String;
		private var _dataHoraArquivoRecebido:IDateTime;
		
		private var _movimentoDataInicial:IDateTime;
		private var _movimentoDataFinal:IDateTime;
		
		public function get idArquivo():Number {
			return _idArquivo;
		}

		public function set idArquivo(idArquivo:Number):void {
			this._idArquivo = idArquivo;
		}

		public function get descNomeArquivoEnviado():String {
			return _descNomeArquivoEnviado;
		}
		
		public function set descNomeArquivoEnviado(descNomeArquivoEnviado:String):void {
			this._descNomeArquivoEnviado = descNomeArquivoEnviado;
		}
		
		public function get bolOrigemSicoob():Boolean {
			return _bolOrigemSicoob;
		}

		public function set bolOrigemSicoob(bolOrigemSicoob:Boolean):void {
			this._bolOrigemSicoob = bolOrigemSicoob;
		}

		public function get dataHoraDDA():IDateTime {
			return _dataHoraDDA;
		}

		public function set dataHoraDDA(dataHoraDDA:IDateTime):void {
			this._dataHoraDDA = dataHoraDDA;
		}

		public function get dataHoraMovimento():IDateTime {
			return _dataHoraMovimento;
		}

		public function set dataHoraMovimento(dataHoraMovimento:IDateTime):void {
			this._dataHoraMovimento = dataHoraMovimento;
		}

		public function get descSituacaoArquivo():String {
			return _descSituacaoArquivo;
		}

		public function set descSituacaoArquivo(descSituacaoArquivo:String):void {
			this._descSituacaoArquivo = descSituacaoArquivo;
		}

		public function get statusArquivo():Number {
			return _statusArquivo;
		}

		public function set statusArquivo(statusArquivo:Number):void {
			this._statusArquivo = statusArquivo;
		}

		public function get selecionado():Boolean {
			return _selecionado;
		}

		public function set selecionado(selecionado:Boolean):void {
			this._selecionado = selecionado;
		}

		public function get descNomeArquivoRecebido():String {
			return _descNomeArquivoRecebido;
		}
		
		public function set descNomeArquivoRecebido(descNomeArquivoRecebido:String):void {
			this._descNomeArquivoRecebido = descNomeArquivoRecebido;
		}
		
		public function get dataHoraArquivoRecebido():IDateTime {
			return _dataHoraArquivoRecebido;
		}
		
		public function set dataHoraArquivoRecebido(dataHoraArquivoRecebido:IDateTime):void {
			this._dataHoraArquivoRecebido = dataHoraArquivoRecebido;
		}
		
		public function get movimentoDataInicial():IDateTime {
			return _movimentoDataInicial;
		}
		
		public function set movimentoDataInicial(movimentoDataInicial:IDateTime):void {
			this._movimentoDataInicial = movimentoDataInicial;
		}
		
		public function get movimentoDataFinal():IDateTime {
			return _movimentoDataFinal;
		}
		
		public function set movimentoDataFinal(movimentoDataFinal:IDateTime):void {
			this._movimentoDataFinal = movimentoDataFinal;
		}
	}
}