package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ExtratoDDAVO", ExtratoDDAVO);

	public class ExtratoDDAVO {

		private var _id:Number;
		private var _logRecebimentoArquivoDDA:LogRecebimentoArquivoDDAVO;
		private var _dataInicioApuracao:IDateTime;
		private var _dataFimApuracao:IDateTime;
		private var _numControleDDA:Number;
		private var _dataHoraApuracao:IDateTime;
		private var _dataHoraInclusao:IDateTime;

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}

		public function set logRecebimentoArquivoDDA(logRecebimentoArquivoDDA:LogRecebimentoArquivoDDAVO):void {
			this._logRecebimentoArquivoDDA = logRecebimentoArquivoDDA;
		}

		public function get logRecebimentoArquivoDDA():LogRecebimentoArquivoDDAVO {
			return _logRecebimentoArquivoDDA;
		}

		public function set dataInicioApuracao(dataInicioApuracao:IDateTime):void {
			this._dataInicioApuracao = dataInicioApuracao;
		}

		public function get dataInicioApuracao():IDateTime {
			return _dataInicioApuracao;
		}

		public function set dataFimApuracao(dataFimApuracao:IDateTime):void {
			this._dataFimApuracao = dataFimApuracao;
		}

		public function get dataFimApuracao():IDateTime {
			return _dataFimApuracao;
		}

		public function set numControleDDA(numControleDDA:Number):void {
			this._numControleDDA = numControleDDA;
		}

		public function get numControleDDA():Number {
			return _numControleDDA;
		}

		public function set dataHoraApuracao(dataHoraApuracao:IDateTime):void {
			this._dataHoraApuracao = dataHoraApuracao;
		}

		public function get dataHoraApuracao():IDateTime {
			return _dataHoraApuracao;
		}

		public function set dataHoraInclusao(dataHoraInclusao:IDateTime):void {
			this._dataHoraInclusao = dataHoraInclusao;
		}

		public function get dataHoraInclusao():IDateTime {
			return _dataHoraInclusao;
		}

	}
}