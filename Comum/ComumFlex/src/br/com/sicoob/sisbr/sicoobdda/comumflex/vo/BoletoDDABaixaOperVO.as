package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDABaixaOperVO", BoletoDDABaixaOperVO);

	public class BoletoDDABaixaOperVO {

		private var _id:Number;
		private var _boletoDDA:BoletoDDAVO;
		private var _numIdentificadorBaixaOper:Number;
		private var _numRefAtualBaixaOper:Number;
		private var _numSeqAtualBaixaOper:Number;
		private var _dataProcessamentoBaixaOper:IDateTime;
		private var _dataHoraProcessamentoBaixaOper:IDateTime;
		private var _valorBaixaOper:Number;
		private var _numCodBarrasBaixaOper:String;
		private var _codCanalPagamento:Number;
		private var _codMeioPagamento:Number;
		private var _bolOperacaoContingencia:Boolean;
		private var _codSituacaoBaixaOperacional:String;
		private var _codTipoBaixaOperacional:Number;
		private var _codPartRecebedorBaixaOperacional:String;
		private var _codIspbPartRecebedorBaixaOperacional:String;
		private var _codTipoPessoaPortador:String;
		private var _numCnpjCpfPortador:String;
		private var _numRefAtualCadBoleto:Number;
		private var _descTpBaixaOperacional:String;
		private var _descCanalPagamento:String;
		private var _descMeioPagamento:String;
		private var _descSituacaoBxOperacional:String;
		private var _descBanco:String;
		private var _descBancoOpCompleta:String;

		
		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}
		
		public function set boletoDDA(boletoDDA:BoletoDDAVO):void {
			this._boletoDDA = boletoDDA;
		}
		
		public function get boletoDDA():BoletoDDAVO {
			return _boletoDDA;
		}
		
		public function set numIdentificadorBaixaOper(numIdentificadorBaixaOper:Number):void {
			this._numIdentificadorBaixaOper = numIdentificadorBaixaOper;
		}
		
		public function get numIdentificadorBaixaOper():Number {
			return _numIdentificadorBaixaOper;
		}
		
		public function set numRefAtualBaixaOper(numRefAtualBaixaOper:Number):void {
			this._numRefAtualBaixaOper = numRefAtualBaixaOper;
		}
		
		public function get numRefAtualBaixaOper():Number {
			return _numRefAtualBaixaOper;
		}
		
		public function set numSeqAtualBaixaOper(numSeqAtualBaixaOper:Number):void {
			this._numSeqAtualBaixaOper = numSeqAtualBaixaOper;
		}
		
		public function get numSeqAtualBaixaOper():Number {
			return _numSeqAtualBaixaOper;
		}
		
		public function set dataProcessamentoBaixaOper(dataProcessamentoBaixaOper:IDateTime):void {
			this._dataProcessamentoBaixaOper = dataProcessamentoBaixaOper;
		}
		
		public function get dataProcessamentoBaixaOper():IDateTime {
			return _dataProcessamentoBaixaOper;
		}
		
		public function set dataHoraProcessamentoBaixaOper(dataHoraProcessamentoBaixaOper:IDateTime):void {
			this._dataHoraProcessamentoBaixaOper = dataHoraProcessamentoBaixaOper;
		}
		
		public function get dataHoraProcessamentoBaixaOper():IDateTime {
			return _dataHoraProcessamentoBaixaOper;
		}
		
		public function set valorBaixaOper(valorBaixaOper:Number):void {
			this._valorBaixaOper = valorBaixaOper;
		}
		
		public function get valorBaixaOper():Number {
			return _valorBaixaOper;
		}
		
		public function set numCodBarrasBaixaOper(numCodBarrasBaixaOper:String):void {
			this._numCodBarrasBaixaOper = numCodBarrasBaixaOper;
		}
		
		public function get numCodBarrasBaixaOper():String {
			return _numCodBarrasBaixaOper;
		}
		
		public function set codCanalPagamento(codCanalPagamento:Number):void {
			this._codCanalPagamento = codCanalPagamento;
		}
		
		public function get codCanalPagamento():Number {
			return _codCanalPagamento;
		}
		
		public function set codMeioPagamento(codMeioPagamento:Number):void {
			this._codMeioPagamento = codMeioPagamento;
		}
		
		public function get codMeioPagamento():Number {
			return _codMeioPagamento;
		}
		
		public function set bolOperacaoContingencia(bolOperacaoContingencia:Boolean):void {
			this._bolOperacaoContingencia = bolOperacaoContingencia;
		}
		
		public function get bolOperacaoContingencia():Boolean {
			return _bolOperacaoContingencia;
		}
		
		public function set codSituacaoBaixaOperacional(codSituacaoBaixaOperacional:String):void {
			this._codSituacaoBaixaOperacional = codSituacaoBaixaOperacional;
		}
		
		public function get codSituacaoBaixaOperacional():String {
			return _codSituacaoBaixaOperacional;
		}
		
		public function set codTipoBaixaOperacional(codTipoBaixaOperacional:Number):void {
			this._codTipoBaixaOperacional = codTipoBaixaOperacional;
		}
		
		public function get codTipoBaixaOperacional():Number {
			return _codTipoBaixaOperacional;
		}
		
		public function set codPartRecebedorBaixaOperacional(codPartRecebedorBaixaOperacional:String):void {
			this._codPartRecebedorBaixaOperacional = codPartRecebedorBaixaOperacional;
		}
		
		public function get codPartRecebedorBaixaOperacional():String {
			return _codPartRecebedorBaixaOperacional;
		}
		
		public function set codIspbPartRecebedorBaixaOperacional(codIspbPartRecebedorBaixaOperacional:String):void {
			this._codIspbPartRecebedorBaixaOperacional = codIspbPartRecebedorBaixaOperacional;
		}
		
		public function get codIspbPartRecebedorBaixaOperacional():String {
			return _codIspbPartRecebedorBaixaOperacional;
		}
		
		public function set codTipoPessoaPortador(codTipoPessoaPortador:String):void {
			this._codTipoPessoaPortador = codTipoPessoaPortador;
		}
		
		public function get codTipoPessoaPortador():String {
			return _codTipoPessoaPortador;
		}
		
		public function set numCnpjCpfPortador(numCnpjCpfPortador:String):void {
			this._numCnpjCpfPortador = numCnpjCpfPortador;
		}
		
		public function get numCnpjCpfPortador():String {
			return _numCnpjCpfPortador;
		}
		
		public function set numRefAtualCadBoleto(numRefAtualCadBoleto:Number):void {
			this._numRefAtualCadBoleto = numRefAtualCadBoleto;
		}
		
		public function get numRefAtualCadBoleto():Number {
			return _numRefAtualCadBoleto;
		}
		
		public function set descTpBaixaOperacional(descTpBaixaOperacional:String):void {
			this._descTpBaixaOperacional = descTpBaixaOperacional;
		}
		
		public function get descTpBaixaOperacional():String {
			return _descTpBaixaOperacional;
		}
		
		public function set descCanalPagamento(descCanalPagamento:String):void {
			this._descCanalPagamento = descCanalPagamento;
		}
		
		public function get descCanalPagamento():String {
			return _descCanalPagamento;
		}
		
		public function set descMeioPagamento(descMeioPagamento:String):void {
			this._descMeioPagamento = descMeioPagamento;
		}
		
		public function get descMeioPagamento():String {
			return _descMeioPagamento;
		}
		
		public function set descSituacaoBxOperacional(descSituacaoBxOperacional:String):void {
			this._descSituacaoBxOperacional = descSituacaoBxOperacional;
		}
		
		public function get descSituacaoBxOperacional():String {
			return _descSituacaoBxOperacional;
		}
		
		public function set descBanco(descBanco:String):void {
			this._descBanco = descBanco;
		}
		
		public function get descBanco():String {
			return _descBanco;
		}
		
		public function get descricaoBanco():String {
			if(codPartRecebedorBaixaOperacional != null && descBanco != null){
				return codPartRecebedorBaixaOperacional + " - " + descBanco;
			}
			return "";
		}
		
		public function get descBancoOpCompleta():String {
			if(codPartRecebedorBaixaOperacional != null && descBanco != null){
				return this._descBancoOpCompleta = codPartRecebedorBaixaOperacional + " - " + descBanco;
			}
			return "";
		}
	}
}