package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogRecebimentoArquivoDDAVO", LogRecebimentoArquivoDDAVO);

	public class LogRecebimentoArquivoDDAVO {

		private var _id:Number;
		private var _tipoArquivo:TipoArquivoVO;
		private var _descNomeArquivoRecebido:String;
		private var _dataHoraArquivo:IDateTime;
		private var _dataMovimento:IDateTime;
		private var _tipoErroCip:TipoErroCipVO;
		private var _logEnvioArquivoDDA:LogEnvioArquivoDDAVO;
		private var _situacaoProcessamentoArquivo:SituacaoProcessamentoArquivoVO;
		private var _tipoMensagemDDA:TipoMensagemDDAVO;
		private var _listaLogDetRecebimentoArquivoDDA:ArrayCollection;
		private var _qtdRegistroArquivo:Number;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set tipoArquivo(tipoArquivo:TipoArquivoVO):void {
			this._tipoArquivo = tipoArquivo;
		}

		public function get tipoArquivo():TipoArquivoVO {
			return _tipoArquivo;
		}

		public function set descNomeArquivoRecebido(descNomeArquivoRecebido:String):void {
			this._descNomeArquivoRecebido = descNomeArquivoRecebido;
		}

		public function get descNomeArquivoRecebido():String {
			return _descNomeArquivoRecebido;
		}

		public function set dataHoraArquivo(dataHoraArquivo:IDateTime):void {
			this._dataHoraArquivo = dataHoraArquivo;
		}

		public function get dataHoraArquivo():IDateTime {
			return _dataHoraArquivo;
		}

		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}

		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}

		public function set tipoErroCip(tipoErroCip:TipoErroCipVO):void {
			this._tipoErroCip = tipoErroCip;
		}

		public function get tipoErroCip():TipoErroCipVO {
			return _tipoErroCip;
		}

		public function set logEnvioArquivoDDA(logEnvioArquivoDDA:LogEnvioArquivoDDAVO):void {
			this._logEnvioArquivoDDA = logEnvioArquivoDDA;
		}

		public function get logEnvioArquivoDDA():LogEnvioArquivoDDAVO {
			return _logEnvioArquivoDDA;
		}
		
		public function set situacaoProcessamentoArquivo(id:SituacaoProcessamentoArquivoVO):void {
			this._situacaoProcessamentoArquivo = situacaoProcessamentoArquivo;
		}
		
		public function get situacaoProcessamentoArquivo():SituacaoProcessamentoArquivoVO {
			return _situacaoProcessamentoArquivo;
		}
		
		public function set tipoMensagemDDA(tipoMensagemDDA:TipoMensagemDDAVO):void {
			this._tipoMensagemDDA = tipoMensagemDDA;
		}
		
		public function get tipoMensagemDDA():TipoMensagemDDAVO {
			return _tipoMensagemDDA;
		}

		public function set listaLogDetRecebimentoArquivoDDA(listaLogDetRecebimentoArquivoDDA:ArrayCollection):void {
			this._listaLogDetRecebimentoArquivoDDA = listaLogDetRecebimentoArquivoDDA;
		}
		
		public function get listaLogDetRecebimentoArquivoDDA():ArrayCollection {
			return _listaLogDetRecebimentoArquivoDDA;
		}
		
		public function set qtdRegistroArquivo(qtdRegistroArquivo:Number):void {
			this._qtdRegistroArquivo = qtdRegistroArquivo;
		}
		
		public function get qtdRegistroArquivo():Number {
			return _qtdRegistroArquivo;
		}

	}
}