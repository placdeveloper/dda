package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDABoletoFiltroDTO", MensagemDDABoletoFiltroDTO);

	public class MensagemDDABoletoFiltroDTO {

		private var _comboTipoMensagem:ArrayCollection;
		private var _tipoMensagem:String;
		private var _dataMovimento:IDateTime;
		private var _numCodigoDeBarras:String;
		private var _listaTipoJuros:ArrayCollection;
		private var _listaTipoModeloCalculo:ArrayCollection;
		private var _listaTipoDesconto:ArrayCollection;
		private var _listaTipoMulta:ArrayCollection;
		private var _listaTipoModeloPagamento:ArrayCollection;
		private var _listaEspecieDocumento:ArrayCollection;
		private var _listaAutorizacaoValorDivergente:ArrayCollection;
		private var _listaIdCarteira:ArrayCollection;
		private var _listaTipoPessoaDDAAvalista:ArrayCollection;
		private var _situacaoMensagem:Number;
		private var _paginaAtual:Number;

		public function set comboTipoMensagem(comboTipoMensagem:ArrayCollection):void {
			this._comboTipoMensagem = comboTipoMensagem;
		}

		public function get comboTipoMensagem():ArrayCollection {
			return _comboTipoMensagem;
		}

		public function set tipoMensagem(tipoMensagem:String):void {
			this._tipoMensagem = tipoMensagem;
		}

		public function get tipoMensagem():String {
			return _tipoMensagem;
		}

		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}

		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}

		public function set numCodigoDeBarras(numCodigoDeBarras:String):void {
			this._numCodigoDeBarras = numCodigoDeBarras;
		}

		public function get numCodigoDeBarras():String {
			return _numCodigoDeBarras;
		}

		public function set listaTipoJuros(listaTipoJuros:ArrayCollection):void {
			this._listaTipoJuros = listaTipoJuros;
		}

		public function get listaTipoJuros():ArrayCollection {
			return _listaTipoJuros;
		}

		public function set listaTipoModeloCalculo(listaTipoModeloCalculo:ArrayCollection):void {
			this._listaTipoModeloCalculo = listaTipoModeloCalculo;
		}

		public function get listaTipoModeloCalculo():ArrayCollection {
			return _listaTipoModeloCalculo;
		}

		public function set listaTipoDesconto(listaTipoDesconto:ArrayCollection):void {
			this._listaTipoDesconto = listaTipoDesconto;
		}

		public function get listaTipoDesconto():ArrayCollection {
			return _listaTipoDesconto;
		}

		public function set listaTipoMulta(listaTipoMulta:ArrayCollection):void {
			this._listaTipoMulta = listaTipoMulta;
		}

		public function get listaTipoMulta():ArrayCollection {
			return _listaTipoMulta;
		}

		public function set listaTipoModeloPagamento(listaTipoModeloPagamento:ArrayCollection):void {
			this._listaTipoModeloPagamento = listaTipoModeloPagamento;
		}

		public function get listaTipoModeloPagamento():ArrayCollection {
			return _listaTipoModeloPagamento;
		}

		public function set listaEspecieDocumento(listaEspecieDocumento:ArrayCollection):void {
			this._listaEspecieDocumento = listaEspecieDocumento;
		}

		public function get listaEspecieDocumento():ArrayCollection {
			return _listaEspecieDocumento;
		}

		public function set listaAutorizacaoValorDivergente(listaAutorizacaoValorDivergente:ArrayCollection):void {
			this._listaAutorizacaoValorDivergente = listaAutorizacaoValorDivergente;
		}

		public function get listaAutorizacaoValorDivergente():ArrayCollection {
			return _listaAutorizacaoValorDivergente;
		}

		public function set listaIdCarteira(listaIdCarteira:ArrayCollection):void {
			this._listaIdCarteira = listaIdCarteira;
		}

		public function get listaIdCarteira():ArrayCollection {
			return _listaIdCarteira;
		}

		public function set listaTipoPessoaDDAAvalista(listaTipoPessoaDDAAvalista:ArrayCollection):void {
			this._listaTipoPessoaDDAAvalista = listaTipoPessoaDDAAvalista;
		}

		public function get listaTipoPessoaDDAAvalista():ArrayCollection {
			return _listaTipoPessoaDDAAvalista;
		}
		
		public function set situacaoMensagem(situacaoMensagem:Number):void {
			this._situacaoMensagem = situacaoMensagem;
		}
		
		public function get situacaoMensagem():Number {
			return _situacaoMensagem;
		}
		
		public function set paginaAtual(paginaAtual:Number):void {
			this._paginaAtual = paginaAtual;
		}
		
		public function get paginaAtual():Number {
			return _paginaAtual;
		}
	}
}