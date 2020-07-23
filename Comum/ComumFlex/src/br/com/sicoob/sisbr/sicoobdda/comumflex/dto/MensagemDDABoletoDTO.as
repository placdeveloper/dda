package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDABoletoDTO", MensagemDDABoletoDTO);

	public class MensagemDDABoletoDTO {

		private var _idMensagem:Number;
		private var _tipoMensagem:String;
		private var _dataMovimento:IDateTime;
		private var _numCodigoDeBarras:String;
		private var _dataHoraMensagem:IDateTime;
		private var _dataHoraProtocolo:IDateTime;
		private var _numOperacao:String;
		private var _codTipoPessoaBeneficiario:String;
		private var _numCnpjCpfBeneficiario:String;
		private var _nomeBeneficiario:String;
		private var _nomeFantasiaBeneficiario:String;
		private var _descLogradouroBeneficiario:String;
		private var _descCidadeBeneficiario:String;
		private var _ufBeneficiario:String;
		private var _numCepBeneficiario:String;
		private var _codTipoPessoaBeneficiarioFinal:String;
		private var _numCnpjCpfBeneficiarioFinal:String;
		private var _nomeBeneficiarioFinal:String;
		private var _nomeFantasiaBeneficiarioFinal:String;
		private var _codTipoPessoaPagador:String;
		private var _numCnpjCpfPagador:String;
		private var _nomePagador:String;
		private var _nomeFantasiaPagador:String;
		private var _descLogradouroPagador:String;
		private var _desCidadePagador:String;
		private var _ufPagador:String;
		private var _numCepPagador:String;
		private var _codTipoPessoaAvalista:String;
		private var _numCnpjCpfAvalista:String;
		private var _nomeAvalista:String;
		private var _idCarteira:Number;
		private var _codMoeda:String;
		private var _idOrgaoMoeda:Number;
		private var _numNossoNumero:String;
		private var _numLinhaDigitavel:String;
		private var _dataVencimento:IDateTime;
		private var _valorDoBoleto:Number;
		private var _dataEmissao:IDateTime;
		private var _numDocumento:String;
		private var _dataLimitePgto:IDateTime;
		private var _diasDeProtesto:Number;
		private var _valorAbatimento:Number;
		private var _indValorMinimo:String;
		private var _indValorMaximo:String;
		private var _valorMaximo:Number;
		private var _valorMinimo:Number;
		private var _numeroParcela:Number;
		private var _qtdTotalParcelas:Number;
		private var _bolTituloNegociado:String;
		private var _bolBloqueioPagamento:String;
		private var _bolPagamentoParcial:String;
		private var _qtdPagamentoParcial:Number;
		private var _tipoModeloDeCalculo:String;
		private var _codAutorizacaoValorDivergente:String;
		private var _idEspecieDocumento:Number;
		private var _codTipoPagamento:Number;
		private var _numRefAtualCadBoleto:Number;
		private var _numSeqAtualCadBoleto:Number;
		private var _listDadosDesconto:ArrayCollection;
		private var _idJuros:Number;
		private var _codTipoJuros:Number;
		private var _dataJuros:IDateTime;
		private var _valorJuros:Number;
		private var _idMulta:Number;
		private var _codTipoMulta:Number;
		private var _dataMulta:IDateTime;
		private var _valorMulta:Number;
		private var _listDadosGrupoCalculo:ArrayCollection;

		public function set idMensagem(idMensagem:Number):void {
			this._idMensagem = idMensagem;
		}

		public function get idMensagem():Number {
			return _idMensagem;
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

		public function set dataHoraMensagem(dataHoraMensagem:IDateTime):void {
			this._dataHoraMensagem = dataHoraMensagem;
		}

		public function get dataHoraMensagem():IDateTime {
			return _dataHoraMensagem;
		}

		public function set dataHoraProtocolo(dataHoraProtocolo:IDateTime):void {
			this._dataHoraProtocolo = dataHoraProtocolo;
		}

		public function get dataHoraProtocolo():IDateTime {
			return _dataHoraProtocolo;
		}

		public function set numOperacao(numOperacao:String):void {
			this._numOperacao = numOperacao;
		}

		public function get numOperacao():String {
			return _numOperacao;
		}

		public function set codTipoPessoaBeneficiario(codTipoPessoaBeneficiario:String):void {
			this._codTipoPessoaBeneficiario = codTipoPessoaBeneficiario;
		}

		public function get codTipoPessoaBeneficiario():String {
			return _codTipoPessoaBeneficiario;
		}

		public function set numCnpjCpfBeneficiario(numCnpjCpfBeneficiario:String):void {
			this._numCnpjCpfBeneficiario = numCnpjCpfBeneficiario;
		}

		public function get numCnpjCpfBeneficiario():String {
			return _numCnpjCpfBeneficiario;
		}

		public function set nomeBeneficiario(nomeBeneficiario:String):void {
			this._nomeBeneficiario = nomeBeneficiario;
		}

		public function get nomeBeneficiario():String {
			return _nomeBeneficiario;
		}

		public function set nomeFantasiaBeneficiario(nomeFantasiaBeneficiario:String):void {
			this._nomeFantasiaBeneficiario = nomeFantasiaBeneficiario;
		}

		public function get nomeFantasiaBeneficiario():String {
			return _nomeFantasiaBeneficiario;
		}

		public function set descLogradouroBeneficiario(descLogradouroBeneficiario:String):void {
			this._descLogradouroBeneficiario = descLogradouroBeneficiario;
		}

		public function get descLogradouroBeneficiario():String {
			return _descLogradouroBeneficiario;
		}

		public function set descCidadeBeneficiario(descCidadeBeneficiario:String):void {
			this._descCidadeBeneficiario = descCidadeBeneficiario;
		}

		public function get descCidadeBeneficiario():String {
			return _descCidadeBeneficiario;
		}

		public function set ufBeneficiario(ufBeneficiario:String):void {
			this._ufBeneficiario = ufBeneficiario;
		}

		public function get ufBeneficiario():String {
			return _ufBeneficiario;
		}

		public function set numCepBeneficiario(numCepBeneficiario:String):void {
			this._numCepBeneficiario = numCepBeneficiario;
		}

		public function get numCepBeneficiario():String {
			return _numCepBeneficiario;
		}

		public function set codTipoPessoaBeneficiarioFinal(codTipoPessoaBeneficiarioFinal:String):void {
			this._codTipoPessoaBeneficiarioFinal = codTipoPessoaBeneficiarioFinal;
		}

		public function get codTipoPessoaBeneficiarioFinal():String {
			return _codTipoPessoaBeneficiarioFinal;
		}

		public function set numCnpjCpfBeneficiarioFinal(numCnpjCpfBeneficiarioFinal:String):void {
			this._numCnpjCpfBeneficiarioFinal = numCnpjCpfBeneficiarioFinal;
		}

		public function get numCnpjCpfBeneficiarioFinal():String {
			return _numCnpjCpfBeneficiarioFinal;
		}

		public function set nomeBeneficiarioFinal(nomeBeneficiarioFinal:String):void {
			this._nomeBeneficiarioFinal = nomeBeneficiarioFinal;
		}

		public function get nomeBeneficiarioFinal():String {
			return _nomeBeneficiarioFinal;
		}

		public function set nomeFantasiaBeneficiarioFinal(nomeFantasiaBeneficiarioFinal:String):void {
			this._nomeFantasiaBeneficiarioFinal = nomeFantasiaBeneficiarioFinal;
		}

		public function get nomeFantasiaBeneficiarioFinal():String {
			return _nomeFantasiaBeneficiarioFinal;
		}

		public function set codTipoPessoaPagador(codTipoPessoaPagador:String):void {
			this._codTipoPessoaPagador = codTipoPessoaPagador;
		}

		public function get codTipoPessoaPagador():String {
			return _codTipoPessoaPagador;
		}

		public function set numCnpjCpfPagador(numCnpjCpfPagador:String):void {
			this._numCnpjCpfPagador = numCnpjCpfPagador;
		}

		public function get numCnpjCpfPagador():String {
			return _numCnpjCpfPagador;
		}

		public function set nomePagador(nomePagador:String):void {
			this._nomePagador = nomePagador;
		}

		public function get nomePagador():String {
			return _nomePagador;
		}

		public function set nomeFantasiaPagador(nomeFantasiaPagador:String):void {
			this._nomeFantasiaPagador = nomeFantasiaPagador;
		}

		public function get nomeFantasiaPagador():String {
			return _nomeFantasiaPagador;
		}

		public function set descLogradouroPagador(descLogradouroPagador:String):void {
			this._descLogradouroPagador = descLogradouroPagador;
		}

		public function get descLogradouroPagador():String {
			return _descLogradouroPagador;
		}

		public function set desCidadePagador(desCidadePagador:String):void {
			this._desCidadePagador = desCidadePagador;
		}

		public function get desCidadePagador():String {
			return _desCidadePagador;
		}

		public function set ufPagador(ufPagador:String):void {
			this._ufPagador = ufPagador;
		}

		public function get ufPagador():String {
			return _ufPagador;
		}

		public function set numCepPagador(numCepPagador:String):void {
			this._numCepPagador = numCepPagador;
		}

		public function get numCepPagador():String {
			return _numCepPagador;
		}

		public function set codTipoPessoaAvalista(codTipoPessoaAvalista:String):void {
			this._codTipoPessoaAvalista = codTipoPessoaAvalista;
		}

		public function get codTipoPessoaAvalista():String {
			return _codTipoPessoaAvalista;
		}

		public function set numCnpjCpfAvalista(numCnpjCpfAvalista:String):void {
			this._numCnpjCpfAvalista = numCnpjCpfAvalista;
		}

		public function get numCnpjCpfAvalista():String {
			return _numCnpjCpfAvalista;
		}

		public function set nomeAvalista(nomeAvalista:String):void {
			this._nomeAvalista = nomeAvalista;
		}

		public function get nomeAvalista():String {
			return _nomeAvalista;
		}

		public function set idCarteira(idCarteira:Number):void {
			this._idCarteira = idCarteira;
		}

		public function get idCarteira():Number {
			return _idCarteira;
		}

		public function set codMoeda(codMoeda:String):void {
			this._codMoeda = codMoeda;
		}

		public function get codMoeda():String {
			return _codMoeda;
		}

		public function set idOrgaoMoeda(idOrgaoMoeda:Number):void {
			this._idOrgaoMoeda = idOrgaoMoeda;
		}

		public function get idOrgaoMoeda():Number {
			return _idOrgaoMoeda;
		}

		public function set numNossoNumero(numNossoNumero:String):void {
			this._numNossoNumero = numNossoNumero;
		}

		public function get numNossoNumero():String {
			return _numNossoNumero;
		}

		public function set numLinhaDigitavel(numLinhaDigitavel:String):void {
			this._numLinhaDigitavel = numLinhaDigitavel;
		}

		public function get numLinhaDigitavel():String {
			return _numLinhaDigitavel;
		}

		public function set dataVencimento(dataVencimento:IDateTime):void {
			this._dataVencimento = dataVencimento;
		}

		public function get dataVencimento():IDateTime {
			return _dataVencimento;
		}

		public function set valorDoBoleto(valorDoBoleto:Number):void {
			this._valorDoBoleto = valorDoBoleto;
		}

		public function get valorDoBoleto():Number {
			return _valorDoBoleto;
		}

		public function set dataEmissao(dataEmissao:IDateTime):void {
			this._dataEmissao = dataEmissao;
		}

		public function get dataEmissao():IDateTime {
			return _dataEmissao;
		}

		public function set numDocumento(numDocumento:String):void {
			this._numDocumento = numDocumento;
		}

		public function get numDocumento():String {
			return _numDocumento;
		}

		public function set dataLimitePgto(dataLimitePgto:IDateTime):void {
			this._dataLimitePgto = dataLimitePgto;
		}

		public function get dataLimitePgto():IDateTime {
			return _dataLimitePgto;
		}

		public function set diasDeProtesto(diasDeProtesto:Number):void {
			this._diasDeProtesto = diasDeProtesto;
		}

		public function get diasDeProtesto():Number {
			return _diasDeProtesto;
		}

		public function set valorAbatimento(valorAbatimento:Number):void {
			this._valorAbatimento = valorAbatimento;
		}

		public function get valorAbatimento():Number {
			return _valorAbatimento;
		}

		public function set indValorMinimo(indValorMinimo:String):void {
			this._indValorMinimo = indValorMinimo;
		}

		public function get indValorMinimo():String {
			return _indValorMinimo;
		}

		public function set indValorMaximo(indValorMaximo:String):void {
			this._indValorMaximo = indValorMaximo;
		}

		public function get indValorMaximo():String {
			return _indValorMaximo;
		}

		public function set valorMaximo(valorMaximo:Number):void {
			this._valorMaximo = valorMaximo;
		}

		public function get valorMaximo():Number {
			return _valorMaximo;
		}

		public function set valorMinimo(valorMinimo:Number):void {
			this._valorMinimo = valorMinimo;
		}

		public function get valorMinimo():Number {
			return _valorMinimo;
		}

		public function set numeroParcela(numeroParcela:Number):void {
			this._numeroParcela = numeroParcela;
		}

		public function get numeroParcela():Number {
			return _numeroParcela;
		}

		public function set qtdTotalParcelas(qtdTotalParcelas:Number):void {
			this._qtdTotalParcelas = qtdTotalParcelas;
		}

		public function get qtdTotalParcelas():Number {
			return _qtdTotalParcelas;
		}

		public function set bolTituloNegociado(bolTituloNegociado:String):void {
			this._bolTituloNegociado = bolTituloNegociado;
		}

		public function get bolTituloNegociado():String {
			return _bolTituloNegociado;
		}

		public function set bolBloqueioPagamento(bolBloqueioPagamento:String):void {
			this._bolBloqueioPagamento = bolBloqueioPagamento;
		}

		public function get bolBloqueioPagamento():String {
			return _bolBloqueioPagamento;
		}

		public function set bolPagamentoParcial(bolPagamentoParcial:String):void {
			this._bolPagamentoParcial = bolPagamentoParcial;
		}

		public function get bolPagamentoParcial():String {
			return _bolPagamentoParcial;
		}

		public function set qtdPagamentoParcial(qtdPagamentoParcial:Number):void {
			this._qtdPagamentoParcial = qtdPagamentoParcial;
		}

		public function get qtdPagamentoParcial():Number {
			return _qtdPagamentoParcial;
		}

		public function set tipoModeloDeCalculo(tipoModeloDeCalculo:String):void {
			this._tipoModeloDeCalculo = tipoModeloDeCalculo;
		}

		public function get tipoModeloDeCalculo():String {
			return _tipoModeloDeCalculo;
		}

		public function set codAutorizacaoValorDivergente(codAutorizacaoValorDivergente:String):void {
			this._codAutorizacaoValorDivergente = codAutorizacaoValorDivergente;
		}

		public function get codAutorizacaoValorDivergente():String {
			return _codAutorizacaoValorDivergente;
		}

		public function set idEspecieDocumento(idEspecieDocumento:Number):void {
			this._idEspecieDocumento = idEspecieDocumento;
		}

		public function get idEspecieDocumento():Number {
			return _idEspecieDocumento;
		}

		public function set codTipoPagamento(codTipoPagamento:Number):void {
			this._codTipoPagamento = codTipoPagamento;
		}

		public function get codTipoPagamento():Number {
			return _codTipoPagamento;
		}

		public function set numRefAtualCadBoleto(numRefAtualCadBoleto:Number):void {
			this._numRefAtualCadBoleto = numRefAtualCadBoleto;
		}

		public function get numRefAtualCadBoleto():Number {
			return _numRefAtualCadBoleto;
		}

		public function set numSeqAtualCadBoleto(numSeqAtualCadBoleto:Number):void {
			this._numSeqAtualCadBoleto = numSeqAtualCadBoleto;
		}

		public function get numSeqAtualCadBoleto():Number {
			return _numSeqAtualCadBoleto;
		}

		public function set listDadosDesconto(listDadosDesconto:ArrayCollection):void {
			this._listDadosDesconto = listDadosDesconto;
		}

		public function get listDadosDesconto():ArrayCollection {
			return _listDadosDesconto;
		}

		public function set codTipoJuros(codTipoJuros:Number):void {
			this._codTipoJuros = codTipoJuros;
		}

		public function get codTipoJuros():Number {
			return _codTipoJuros;
		}

		public function set dataJuros(dataJuros:IDateTime):void {
			this._dataJuros = dataJuros;
		}

		public function get dataJuros():IDateTime {
			return _dataJuros;
		}

		public function set valorJuros(valorJuros:Number):void {
			this._valorJuros = valorJuros;
		}

		public function get valorJuros():Number {
			return _valorJuros;
		}

		public function set codTipoMulta(codTipoMulta:Number):void {
			this._codTipoMulta = codTipoMulta;
		}

		public function get codTipoMulta():Number {
			return _codTipoMulta;
		}

		public function set dataMulta(dataMulta:IDateTime):void {
			this._dataMulta = dataMulta;
		}

		public function get dataMulta():IDateTime {
			return _dataMulta;
		}

		public function set valorMulta(valorMulta:Number):void {
			this._valorMulta = valorMulta;
		}

		public function get valorMulta():Number {
			return _valorMulta;
		}

		public function set listDadosGrupoCalculo(listDadosGrupoCalculo:ArrayCollection):void {
			this._listDadosGrupoCalculo = listDadosGrupoCalculo;
		}

		public function get listDadosGrupoCalculo():ArrayCollection {
			return _listDadosGrupoCalculo;
		}
		
		public function set idJuros(idJuros:Number):void {
			this._idJuros = idJuros;
		}
		
		public function get idJuros():Number {
			return _idJuros;
		}
		
		public function set idMulta(idMulta:Number):void {
			this._idMulta = idMulta;
		}
		
		public function get idMulta():Number {
			return _idMulta;
		}

	}
}