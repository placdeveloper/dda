package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoDescontoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoJurosVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoMultaVO;
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDAVO", BoletoDDAVO);

	public class BoletoDDAVO {

		private var _id:Number;
		private var _numIdentificadorBoletoCip:Number;
		private var _numRefAtualCadBoleto:Number;
		private var _numSeqAtualCadBoleto:Number;
		private var _numRefAtualCadAceite:Number;
		private var _numSeqAtualAceite:Number;
		private var _dataHoraSituacaoBoleto:IDateTime;
		private var _codIspbPartDestinatario:String;
		private var _codPartDestinatario:String;
		private var _codTipoPessoaBeneficiario:String;
		private var _numCpfCnpjBeneficiario:String;
		private var _nomeBeneficiario:String;
		private var _nomeFantasiaBeneficiario:String;
		private var _descLogradouroBeneficiario:String;
		private var _descCidadeBeneficiario:String;
		private var _siglaUfBeneficiario:String;
		private var _numCepBeneficiario:String;
		private var _codTipoPessoaBeneficiarioFinal:String;
		private var _numCpfCnpjBeneficiarioFinal:String;
		private var _nomeBeneficiarioFinal:String;
		private var _nomeFantasiaBeneficiarioFinal:String;
		private var _codTipoPessoaPagador:String;
		private var _numCpfCnpjPagador:String;
		private var _nomePagador:String;
		private var _nomeFantasiaPagador:String;
		private var _descLogradouroPagador:String;
		private var _descCidadePagador:String;
		private var _siglaUfPagador:String;
		private var _numCepPagador:String;
		private var _codTipoPessoaDDAAvalista:String;
		private var _numCpfCnpjAvalista:String;
		private var _nomeAvalista:String;
		private var _idCarteira:Number;
		private var _codMoeda:String;
		private var _numNossoNumero:String;
		private var _numCodigoBarra:String;
		private var _numLinhaDigitavel:String;
		private var _dataVencimento:IDateTime;
		private var _valorBoleto:Number;
		private var _numDocumento:String;
		private var _idEspecieDocumento:Number;
		private var _dataEmissao:IDateTime;
		private var _numDiasProtesto:Number;
		private var _dataLimitePagamento:IDateTime;
		private var _codTipoPagamento:Number;
		private var _numParcela:Number;
		private var _qtdTotalParcela:Number;
		private var _bolTituloNegociado:Boolean;
		private var _bolBloqueioPagamento:Boolean;
		private var _bolPagamentoParcial:Boolean;
		private var _valorAbatimento:Number;
		private var _codIndicadorValorMinimo:String;
		private var _valorMinimo:Number;
		private var _codIndicadorValorMaximo:String;
		private var _valorMaximo:Number;
		private var _codTipoModeloCalculo:String;
		private var _codAutorizacaoValorDivergente:String;
		private var _qtdPagamentoParcialReg:Number;
		private var _valorSaldoPagamento:Number;
		private var _codSituacaoBoletoPagamento:String;
		private var _qtdPagamentoParcial:Number;
		private var _bolAceite:Boolean;
		private var _dataHoraSituacaoAceite:IDateTime;
		private var _dataHoraUltimaAtualizacao:IDateTime;
		private var _codSituacaoBoleto:Number;
		private var _dataHoraInclusao:IDateTime;
		private var _descBanco:String;
		private var _descModeloCalculo:String;
		private var _descAutorizacaoValorDivergente:String;
		private var _descSituacaoBoleto:String;
		private var _descSituacaoBoletoPagamento:String;
		private var _descTipoPessoaAvalista:String;
		private var _listaBoletoDDATerceiroAutorizado:ArrayCollection;
		private var _listaBoletoDDATextoInfo:ArrayCollection;
		private var _listaBoletoDDABaixaEfet:ArrayCollection;
		private var _listaBoletoDDABaixaOper:ArrayCollection;
		private var _descTextoInformativoSemEspaco:String;
		private var _descTipoPagamento:String;
		private var _descCarteira:String;
		private var _descEspecie:String;
		private var _listaPagadorDDAAgregado:ArrayCollection;
		private var _numRefAtualCadBoletoStr:String;
		private var _numIdentificadorBoletoCipStr:String;
		
		// BoletoDDAJuros
		private var _dataJuros:IDateTime;		
		private var _tipoJuros:TipoJurosVO;
		private var _valorPercentualJuros:Number;
		
		// BoletoDDAMulta
		private var _dataMulta:IDateTime;
		private var _tipoMulta:TipoMultaVO;
		private var _valorPercentualMulta:Number;
		
		// BoletoDDADesconto
		private var _dataDesconto1:IDateTime;
		private var _tipoDesconto1:TipoDescontoVO;		
		private var _valorPercentualDesconto1:Number;
		
		private var _dataDesconto2:IDateTime;
		private var _tipoDesconto2:TipoDescontoVO;
		private var _valorPercentualDesconto2:Number;
		
		private var _dataDesconto3:IDateTime;
		private var _tipoDesconto3:TipoDescontoVO;
		private var _valorPercentualDesconto3:Number;
		
		private var _numCodBarrasCampoLivre:String;



		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}
		
		public function set numIdentificadorBoletoCip(numIdentificadorBoletoCip:Number):void {
			this._numIdentificadorBoletoCip = numIdentificadorBoletoCip;
		}
		
		public function get numIdentificadorBoletoCip():Number {
			return _numIdentificadorBoletoCip;
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
		
		public function set numRefAtualCadAceite(numRefAtualCadAceite:Number):void {
			this._numRefAtualCadAceite = numRefAtualCadAceite;
		}
		
		public function get numRefAtualCadAceite():Number {
			return _numRefAtualCadAceite;
		}
		
		public function set numSeqAtualAceite(numSeqAtualAceite:Number):void {
			this._numSeqAtualAceite = numSeqAtualAceite;
		}
		
		public function get numSeqAtualAceite():Number {
			return _numSeqAtualAceite;
		}
		
		public function set dataHoraSituacaoBoleto(dataHoraSituacaoBoleto:IDateTime):void {
			this._dataHoraSituacaoBoleto = dataHoraSituacaoBoleto;
		}
		
		public function get dataHoraSituacaoBoleto():IDateTime {
			return _dataHoraSituacaoBoleto;
		}
		
		public function set codIspbPartDestinatario(codIspbPartDestinatario:String):void {
			this._codIspbPartDestinatario = codIspbPartDestinatario;
		}
		
		public function get codIspbPartDestinatario():String {
			return _codIspbPartDestinatario;
		}
		
		public function set codPartDestinatario(codPartDestinatario:String):void {
			this._codPartDestinatario = codPartDestinatario;
		}
		
		public function get codPartDestinatario():String {
			return _codPartDestinatario;
		}
		
		public function set codTipoPessoaBeneficiario(codTipoPessoaBeneficiario:String):void {
			this._codTipoPessoaBeneficiario = codTipoPessoaBeneficiario;
		}
		
		public function get codTipoPessoaBeneficiario():String {
			return _codTipoPessoaBeneficiario;
		}
		
		public function set numCpfCnpjBeneficiario(numCpfCnpjBeneficiario:String):void {
			this._numCpfCnpjBeneficiario = numCpfCnpjBeneficiario;
		}
		
		public function get numCpfCnpjBeneficiario():String {
			return _numCpfCnpjBeneficiario;
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
		
		public function set siglaUfBeneficiario(siglaUfBeneficiario:String):void {
			this._siglaUfBeneficiario = siglaUfBeneficiario;
		}
		
		public function get siglaUfBeneficiario():String {
			return _siglaUfBeneficiario;
		}
		
		public function set numCepBeneficiario(numCepBeneficiario:String):void {
			this._numCepBeneficiario = numCepBeneficiario;
		}
		
		public function get numCepBeneficiario():String {
			return _numCepBeneficiario;
		}
		
		public function set codTipoPessoaBeneficiarioFinal(codTipoPessoaBeneficiarioFinal:String):void {
			this._codTipoPessoaBeneficiarioFinal = numCepBeneficiario;
		}
		
		public function get codTipoPessoaBeneficiarioFinal():String {
			return _codTipoPessoaBeneficiarioFinal;
		}
		
		public function set numCpfCnpjBeneficiarioFinal(numCpfCnpjBeneficiarioFinal:String):void {
			this._numCpfCnpjBeneficiarioFinal = numCpfCnpjBeneficiarioFinal;
		}
		
		public function get numCpfCnpjBeneficiarioFinal():String {
			return _numCpfCnpjBeneficiarioFinal;
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
		
		public function set numCpfCnpjPagador(numCpfCnpjPagador:String):void {
			this._numCpfCnpjPagador = numCpfCnpjPagador;
		}
		
		public function get numCpfCnpjPagador():String {
			return _numCpfCnpjPagador;
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
		
		public function set descCidadePagador(descCidadePagador:String):void {
			this._descCidadePagador = descCidadePagador;
		}
		
		public function get descCidadePagador():String {
			return _descCidadePagador;
		}
		
		public function set siglaUfPagador(siglaUfPagador:String):void {
			this._siglaUfPagador = siglaUfPagador;
		}
		
		public function get siglaUfPagador():String {
			return _siglaUfPagador;
		}
		
		public function set numCepPagador(numCepPagador:String):void {
			this._numCepPagador = numCepPagador;
		}
		
		public function get numCepPagador():String {
			return _numCepPagador;
		}
		
		public function set codTipoPessoaDDAAvalista(codTipoPessoaDDAAvalista:String):void {
			this._codTipoPessoaDDAAvalista = codTipoPessoaDDAAvalista;
		}
		
		public function get codTipoPessoaDDAAvalista():String {
			return _codTipoPessoaDDAAvalista;
		}
		
		public function set numCpfCnpjAvalista(numCpfCnpjAvalista:String):void {
			this._numCpfCnpjAvalista = numCpfCnpjAvalista;
		}
		
		public function get numCpfCnpjAvalista():String {
			return _numCpfCnpjAvalista;
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
		
		public function set numNossoNumero(numNossoNumero:String):void {
			this._numNossoNumero = numNossoNumero;
		}
		
		public function get numNossoNumero():String {
			return _numNossoNumero;
		}
		
		public function set numCodigoBarra(numCodigoBarra:String):void {
			this._numCodigoBarra = numCodigoBarra;
		}
		
		public function get numCodigoBarra():String {
			return _numCodigoBarra;
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
		
		public function set valorBoleto(valorBoleto:Number):void {
			this._valorBoleto = valorBoleto;
		}
		
		public function get valorBoleto():Number {
			return _valorBoleto;
		}
		
		public function set numDocumento(numDocumento:String):void {
			this._numDocumento = numDocumento;
		}
		
		public function get numDocumento():String {
			return _numDocumento;
		}
		
		public function set idEspecieDocumento(idEspecieDocumento:Number):void {
			this._idEspecieDocumento = idEspecieDocumento;
		}
		
		public function get idEspecieDocumento():Number {
			return _idEspecieDocumento;
		}
		
		public function set dataEmissao(dataEmissao:IDateTime):void {
			this._dataEmissao = dataEmissao;
		}
		
		public function get dataEmissao():IDateTime {
			return _dataEmissao;
		}
		
		public function set numDiasProtesto(numDiasProtesto:Number):void {
			this._numDiasProtesto = numDiasProtesto;
		}
		
		public function get numDiasProtesto():Number {
			return _numDiasProtesto;
		}
		
		public function set dataLimitePagamento(dataLimitePagamento:IDateTime):void {
			this._dataLimitePagamento = dataLimitePagamento;
		}
		
		public function get dataLimitePagamento():IDateTime {
			return _dataLimitePagamento;
		}
		
		public function set codTipoPagamento(codTipoPagamento:Number):void {
			this._codTipoPagamento = codTipoPagamento;
		}
		
		public function get codTipoPagamento():Number {
			return _codTipoPagamento;
		}
		
		public function set numParcela(numParcela:Number):void {
			this._numParcela = numParcela;
		}
		
		public function get numParcela():Number {
			return _numParcela;
		}
		
		public function set qtdTotalParcela(qtdTotalParcela:Number):void {
			this._qtdTotalParcela = qtdTotalParcela;
		}
		
		public function get qtdTotalParcela():Number {
			return _qtdTotalParcela;
		}
		
		public function set bolTituloNegociado(bolTituloNegociado:Boolean):void {
			this._bolTituloNegociado = bolTituloNegociado;
		}
		
		public function get bolTituloNegociado():Boolean {
			return _bolTituloNegociado;
		}
		
		public function set bolBloqueioPagamento(bolBloqueioPagamento:Boolean):void {
			this._bolBloqueioPagamento = bolBloqueioPagamento;
		}
		
		public function get bolBloqueioPagamento():Boolean {
			return _bolBloqueioPagamento;
		}
		
		public function set bolPagamentoParcial(bolPagamentoParcial:Boolean):void {
			this._bolPagamentoParcial = bolPagamentoParcial;
		}
		
		public function get bolPagamentoParcial():Boolean {
			return _bolPagamentoParcial;
		}
		
		public function set valorAbatimento(valorAbatimento:Number):void {
			this._valorAbatimento = valorAbatimento;
		}
		
		public function get valorAbatimento():Number {
			return _valorAbatimento;
		}
		
		public function set codIndicadorValorMinimo(codIndicadorValorMinimo:String):void {
			this._codIndicadorValorMinimo = codIndicadorValorMinimo;
		}
		
		public function get codIndicadorValorMinimo():String {
			return _codIndicadorValorMinimo;
		}
		
		public function set valorMinimo(valorMinimo:Number):void {
			this._valorMinimo = valorMinimo;
		}
		
		public function get valorMinimo():Number {
			return _valorMinimo;
		}
		
		public function set codIndicadorValorMaximo(codIndicadorValorMaximo:String):void {
			this._codIndicadorValorMaximo = codIndicadorValorMaximo;
		}
		
		public function get codIndicadorValorMaximo():String {
			return _codIndicadorValorMaximo;
		}
		
		public function set valorMaximo(valorMaximo:Number):void {
			this._valorMaximo = valorMaximo;
		}
		
		public function get valorMaximo():Number {
			return _valorMaximo;
		}
		
		public function set codTipoModeloCalculo(codTipoModeloCalculo:String):void {
			this._codTipoModeloCalculo = codTipoModeloCalculo;
		}
		
		public function get codTipoModeloCalculo():String {
			return _codTipoModeloCalculo;
		}
		
		public function set codAutorizacaoValorDivergente(codAutorizacaoValorDivergente:String):void {
			this._codAutorizacaoValorDivergente = codAutorizacaoValorDivergente;
		}
		
		public function get codAutorizacaoValorDivergente():String {
			return _codAutorizacaoValorDivergente;
		}
		
		public function set qtdPagamentoParcialReg(qtdPagamentoParcialReg:Number):void {
			this._qtdPagamentoParcialReg = qtdPagamentoParcialReg;
		}
		
		public function get qtdPagamentoParcialReg():Number {
			return _qtdPagamentoParcialReg;
		}
		
		public function set valorSaldoPagamento(valorSaldoPagamento:Number):void {
			this._valorSaldoPagamento = valorSaldoPagamento;
		}
		
		public function get valorSaldoPagamento():Number {
			return _valorSaldoPagamento;
		}
		
		public function set codSituacaoBoletoPagamento(codSituacaoBoletoPagamento:String):void {
			this._codSituacaoBoletoPagamento = codSituacaoBoletoPagamento;
		}
		
		public function get codSituacaoBoletoPagamento():String {
			return _codSituacaoBoletoPagamento;
		}
		
		public function set qtdPagamentoParcial(qtdPagamentoParcial:Number):void {
			this._qtdPagamentoParcial = qtdPagamentoParcial;
		}
		
		public function get qtdPagamentoParcial():Number {
			return _qtdPagamentoParcial;
		}
		
		public function set bolAceite(bolAceite:Boolean):void {
			this._bolAceite = bolAceite;
		}
		
		public function get bolAceite():Boolean {
			return _bolAceite;
		}
		
		public function set dataHoraSituacaoAceite(dataHoraSituacaoAceite:IDateTime):void {
			this._dataHoraSituacaoAceite = dataHoraSituacaoAceite;
		}
		
		public function get dataHoraSituacaoAceite():IDateTime {
			return _dataHoraSituacaoAceite;
		}
		
		public function set dataHoraUltimaAtualizacao(dataHoraUltimaAtualizacao:IDateTime):void {
			this._dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
		}
		
		public function get dataHoraUltimaAtualizacao():IDateTime {
			return _dataHoraUltimaAtualizacao;
		}
		
		public function set codSituacaoBoleto(codSituacaoBoleto:Number):void {
			this._codSituacaoBoleto = codSituacaoBoleto;
		}
		
		public function get codSituacaoBoleto():Number {
			return _codSituacaoBoleto;
		}
		
		public function set dataHoraInclusao(dataHoraInclusao:IDateTime):void {
			this._dataHoraInclusao = dataHoraInclusao;
		}
		
		public function get dataHoraInclusao():IDateTime {
			return _dataHoraInclusao;
		}
		
		public function set descBanco(descBanco:String):void {
			this._descBanco = descBanco;
		}
		
		public function get descBanco():String {
			return _descBanco;
		}
		
		public function set descModeloCalculo(descModeloCalculo:String):void {
			this._descModeloCalculo = descModeloCalculo;
		}
		
		public function get descModeloCalculo():String {
			return _descModeloCalculo;
		}
		
		public function set descAutorizacaoValorDivergente(descAutorizacaoValorDivergente:String):void {
			this._descAutorizacaoValorDivergente = descAutorizacaoValorDivergente;
		}
		
		public function get descAutorizacaoValorDivergente():String {
			return _descAutorizacaoValorDivergente;
		}
		
		public function set descSituacaoBoleto(descSituacaoBoleto:String):void {
			this._descSituacaoBoleto = descSituacaoBoleto;
		}
		
		public function get descSituacaoBoleto():String {
			return _descSituacaoBoleto;
		}
		
		public function set descSituacaoBoletoPagamento(descSituacaoBoletoPagamento:String):void {
			this._descSituacaoBoletoPagamento = descSituacaoBoletoPagamento;
		}
		
		public function get descSituacaoBoletoPagamento():String {
			return _descSituacaoBoletoPagamento;
		}
		
		public function set descTipoPessoaAvalista(descTipoPessoaAvalista:String):void {
			this._descTipoPessoaAvalista = descTipoPessoaAvalista;
		}
		
		public function get descTipoPessoaAvalista():String {
			return _descTipoPessoaAvalista;
		}
		
		public function set listaBoletoDDATerceiroAutorizado(listaBoletoDDATerceiroAutorizado:ArrayCollection):void {
			this._listaBoletoDDATerceiroAutorizado = listaBoletoDDATerceiroAutorizado;
		}
		
		public function get listaBoletoDDATerceiroAutorizado():ArrayCollection {
			return _listaBoletoDDATerceiroAutorizado;
		}
		
		public function set listaBoletoDDATextoInfo(listaBoletoDDATextoInfo:ArrayCollection):void {
			this._listaBoletoDDATextoInfo = listaBoletoDDATextoInfo;
		}
		
		public function get listaBoletoDDATextoInfo():ArrayCollection {
			return _listaBoletoDDATextoInfo;
		}
		
		public function set listaBoletoDDABaixaEfet(listaBoletoDDABaixaEfet:ArrayCollection):void {
			this._listaBoletoDDABaixaEfet = listaBoletoDDABaixaEfet;
		}
		
		public function get listaBoletoDDABaixaEfet():ArrayCollection {
			return _listaBoletoDDABaixaEfet;
		}
		
		public function set listaBoletoDDABaixaOper(listaBoletoDDABaixaOper:ArrayCollection):void {
			this._listaBoletoDDABaixaOper = listaBoletoDDABaixaOper;
		}
		
		public function get listaBoletoDDABaixaOper():ArrayCollection {
			return _listaBoletoDDABaixaOper;
		}
		
		public function set descTextoInformativoSemEspaco(descTextoInformativoSemEspaco:String):void {
			this._descTextoInformativoSemEspaco = descTextoInformativoSemEspaco;
		}
		
		public function get descTextoInformativoSemEspaco():String {
			return _descTextoInformativoSemEspaco;
		}
		
		public function set descTipoPagamento(descTipoPagamento:String):void {
			this._descTipoPagamento = descTipoPagamento;
		}
		
		public function get descTipoPagamento():String {
			return _descTipoPagamento;
		}
		
		public function set descCarteira(descCarteira:String):void {
			this._descCarteira = descCarteira;
		}
		
		public function get descCarteira():String {
			return _descCarteira;
		}
		
		public function set descEspecie(descEspecie:String):void {
			this._descEspecie = descEspecie;
		}
		
		public function get descEspecie():String {
			return _descEspecie;
		}
		
		
		
		public function get getDescricaoBanco():String {
			return codPartDestinatario + " - " + descBanco;
		}
		
		public function get getDescricaoModeloCalculo():String {
			return codTipoModeloCalculo + " - " + descModeloCalculo;
		}
		
		public function get getDescricaoAutorizacaoValorDivergente():String {
			return codAutorizacaoValorDivergente + " - " + descAutorizacaoValorDivergente;
		}
		
		public function get getDescricaoSituacaoBoleto():String {
			return codSituacaoBoleto + " - " + descSituacaoBoleto;
		}
		
		public function get getDescricaoSitBoletoPagamento():String {
			if(codSituacaoBoletoPagamento != null && descSituacaoBoletoPagamento != null){
				return codSituacaoBoletoPagamento + " - " + descSituacaoBoletoPagamento;
			}
			return "";
		}
		
		public function get getDescricaoJuros():String {
			return  tipoJuros.id + " - " + tipoJuros.descTipoJuros;
		}
		
		public function get getDescricaoMulta():String {
			return tipoMulta.id + " - " + tipoMulta.descTipoMulta;
		}
		
		public function get getDescricaoTipoPagamento():String {
			return codTipoPagamento + " - " + descTipoPagamento;
		}
		
		public function get getDescricaoCarteira():String {
			return idCarteira + " - " + descCarteira;
		}
		
		public function get getDescricaoEspecie():String {
			return idEspecieDocumento + " - " + descEspecie;
		}
		
		public function set listaPagadorDDAAgregado(listaPagadorDDAAgregado:ArrayCollection):void {
			this._listaPagadorDDAAgregado = listaPagadorDDAAgregado;
		}
		
		public function get listaPagadorDDAAgregado():ArrayCollection {
			return _listaPagadorDDAAgregado;
		}
		
		public function set numRefAtualCadBoletoStr(numRefAtualCadBoletoStr:String):void {
			this._numRefAtualCadBoletoStr = numRefAtualCadBoletoStr;
		}
		
		public function get numRefAtualCadBoletoStr():String {
			return _numRefAtualCadBoletoStr;
		}
		public function get getDescricaSacadorAvalista():String {
			if(codTipoPessoaDDAAvalista != null){
				return codTipoPessoaDDAAvalista + " - " + descTipoPessoaAvalista;
			}else{
				return ConstantesComum.TIPO_PESSOA_DDA_AVALISTA;
			}
		}
		
		public function get numIdentificadorBoletoCipStr():String {
			return _numIdentificadorBoletoCipStr;
		}
		
		public function set numIdentificadorBoletoCipStr(numIdentificadorBoletoCipStr:String):void {
			this._numIdentificadorBoletoCipStr = numIdentificadorBoletoCipStr;
		}
		
		public function get dataJuros():IDateTime {
			return _dataJuros;
		}
		
		public function set dataJuros(dataJuros:IDateTime):void {
			this._dataJuros = dataJuros;
		}
		
		public function get tipoJuros():TipoJurosVO {
			return _tipoJuros;
		}
		
		public function set tipoJuros(tipoJuros:TipoJurosVO):void {
			this._tipoJuros = tipoJuros;
		}
		
		public function get valorPercentualJuros():Number {
			return _valorPercentualJuros;
		}
		
		public function set valorPercentualJuros(valorPercentualJuros:Number):void {
			this._valorPercentualJuros = valorPercentualJuros;
		}		
		
		public function get dataMulta():IDateTime {
			return _dataMulta;
		}
		
		public function set dataMulta(dataMulta:IDateTime):void {
			this._dataMulta = dataMulta;
		}
		
		public function get tipoMulta():TipoMultaVO {
			return this._tipoMulta;
		}
		
		public function set tipoMulta(tipoMulta:TipoMultaVO):void {
			this._tipoMulta = tipoMulta;
		}
		
		public function get valorPercentualMulta():Number {
			return _valorPercentualMulta;
		}
		
		public function set valorPercentualMulta(valorPercentualMulta:Number):void {
			this._valorPercentualMulta = valorPercentualMulta;
		}
		
		public function get dataDesconto1():IDateTime {
			return _dataDesconto1;
		}
		
		public function set dataDesconto1(dataDesconto1:IDateTime):void {
			this._dataDesconto1 = dataDesconto1;
		}		
		
		public function get tipoDesconto1():TipoDescontoVO{
			return this._tipoDesconto1
		}
		
		public function set tipoDesconto1(tipoDesconto1:TipoDescontoVO):void {
			this._tipoDesconto1 = tipoDesconto1;
		}
		
		public function get tipoDesconto2():TipoDescontoVO{
			return this._tipoDesconto2
		}
		
		public function set tipoDesconto2(tipoDesconto2:TipoDescontoVO):void {
			this._tipoDesconto2 = tipoDesconto2;
		}
		
		public function get tipoDesconto3():TipoDescontoVO{
			return this._tipoDesconto3
		}
		
		public function set tipoDesconto3(tipoDesconto3:TipoDescontoVO):void {
			this._tipoDesconto3 = tipoDesconto3;
		}
		
		public function get valorPercentualDesconto1():Number {
			return _valorPercentualDesconto1;
		}
		
		public function set valorPercentualDesconto1(valorPercentualDesconto1:Number):void {
			this._valorPercentualDesconto1 = valorPercentualDesconto1;
		}
		public function get descricaoDesconto1():String {
			return this._tipoDesconto1.codTipoDesconto + " - " + this._tipoDesconto1.descTipoDesconto;
		}
		
		public function get dataDesconto2():IDateTime {
			return _dataDesconto2;
		}
		
		public function set dataDesconto2(dataDesconto2:IDateTime):void {
			this._dataDesconto2 = dataDesconto2;
		}		
		
		public function get valorPercentualDesconto2():Number {
			return _valorPercentualDesconto2;
		}
		
		public function set valorPercentualDesconto2(valorPercentualDesconto2:Number):void {
			this._valorPercentualDesconto2 = valorPercentualDesconto2;
		}		
		
		
		public function get descricaoDesconto2():String {
			return this._tipoDesconto2.codTipoDesconto + " - " + this._tipoDesconto2.descTipoDesconto;
		}
		
		public function get dataDesconto3():IDateTime {
			return _dataDesconto3;
		}
		
		public function set dataDesconto3(dataDesconto3:IDateTime):void {
			this._dataDesconto3 = dataDesconto3;
		}
		
		public function get valorPercentualDesconto3():Number {
			return _valorPercentualDesconto3;
		}
		
		public function set valorPercentualDesconto3(valorPercentualDesconto3:Number):void {
			this._valorPercentualDesconto3 = valorPercentualDesconto3;
		}		
		
		public function get descricaoDesconto3():String {
			return this._tipoDesconto3.codTipoDesconto + " - " + this._tipoDesconto3.descTipoDesconto;
		}
		
		public function get numCodBarrasCampoLivre():String {
			return _numCodBarrasCampoLivre;
		}
		
		public function set numCodBarrasCampoLivre(numCodBarrasCampoLivre:String):void {
			this._numCodBarrasCampoLivre = numCodBarrasCampoLivre;
		}
	}
}