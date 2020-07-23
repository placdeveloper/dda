package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDABaixaEfetVO", BoletoDDABaixaEfetVO);

	public class BoletoDDABaixaEfetVO {

		private var _id:Number;
		private var _boletoDDA:BoletoDDAVO;
		private var _numIdentificadorBaixaEfet:Number;
		private var _numRefAtualBaixaEfet:Number;
		private var _numSeqAtualBaixaEfet:Number;
		private var _dataProcessamentoBaixaEfet:IDateTime;
		private var _dataHoraProcessamentoBaixaEfet:IDateTime;
		private var _valorBaixaEfet:Number;
		private var _numCodBarrasBaixaEfet:String;
		private var _codCanalPagamento:Number;
		private var _codMeioPagamento:Number;
		private var _numIdentificadorBaixaOperacional:Number;
		private var _codTipoBaixaEfetiva:Number;
		private var _codIspbPartRecebedorBaixaEfetiva:String;
		private var _codPartRecebedorBaixaEfetiva:String;
		private var _descTpBaixaEfetiva:String;
		private var _descCanalPagamento:String;
		private var _descMeioPagamento:String;
		private var _descBanco:String;
		private var _descBancoEfCompleta:String;

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
		
		public function set numIdentificadorBaixaEfet(numIdentificadorBaixaEfet:Number):void {
			this._numIdentificadorBaixaEfet = numIdentificadorBaixaEfet;
		}
		
		public function get numIdentificadorBaixaEfet():Number {
			return _numIdentificadorBaixaEfet;
		}
		
		public function set numRefAtualBaixaEfet(numRefAtualBaixaEfet:Number):void {
			this._numRefAtualBaixaEfet = numRefAtualBaixaEfet;
		}
		
		public function get numRefAtualBaixaEfet():Number {
			return _numRefAtualBaixaEfet;
		}
		
		public function set numSeqAtualBaixaEfet(numSeqAtualBaixaEfet:Number):void {
			this._numSeqAtualBaixaEfet = numSeqAtualBaixaEfet;
		}
		
		public function get numSeqAtualBaixaEfet():Number {
			return _numSeqAtualBaixaEfet;
		}
		
		public function set dataProcessamentoBaixaEfet(dataProcessamentoBaixaEfet:IDateTime):void {
			this._dataProcessamentoBaixaEfet = dataProcessamentoBaixaEfet;
		}
		
		public function get dataProcessamentoBaixaEfet():IDateTime {
			return _dataProcessamentoBaixaEfet;
		}
		
		public function set dataHoraProcessamentoBaixaEfet(dataHoraProcessamentoBaixaEfet:IDateTime):void {
			this._dataHoraProcessamentoBaixaEfet = dataHoraProcessamentoBaixaEfet;
		}
		
		public function get dataHoraProcessamentoBaixaEfet():IDateTime {
			return _dataHoraProcessamentoBaixaEfet;
		}
		
		public function set valorBaixaEfet(valorBaixaEfet:Number):void {
			this._valorBaixaEfet = valorBaixaEfet;
		}
		
		public function get valorBaixaEfet():Number {
			return _valorBaixaEfet;
		}
		
		public function set numCodBarrasBaixaEfet(numCodBarrasBaixaEfet:String):void {
			this._numCodBarrasBaixaEfet = numCodBarrasBaixaEfet;
		}
		
		public function get numCodBarrasBaixaEfet():String {
			return _numCodBarrasBaixaEfet;
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
		
		public function set numIdentificadorBaixaOperacional(numIdentificadorBaixaOperacional:Number):void {
			this._numIdentificadorBaixaOperacional = numIdentificadorBaixaOperacional;
		}
		
		public function get numIdentificadorBaixaOperacional():Number {
			return _numIdentificadorBaixaOperacional;
		}
		
		public function set codTipoBaixaEfetiva(codTipoBaixaEfetiva:Number):void {
			this._codTipoBaixaEfetiva = codTipoBaixaEfetiva;
		}
		
		public function get codTipoBaixaEfetiva():Number {
			return _codTipoBaixaEfetiva;
		}
		
		public function set codIspbPartRecebedorBaixaEfetiva(codIspbPartRecebedorBaixaEfetiva:String):void {
			this._codIspbPartRecebedorBaixaEfetiva = codIspbPartRecebedorBaixaEfetiva;
		}
		
		public function get codIspbPartRecebedorBaixaEfetiva():String {
			return _codIspbPartRecebedorBaixaEfetiva;
		}
		
		public function set codPartRecebedorBaixaEfetiva(codPartRecebedorBaixaEfetiva:String):void {
			this._codPartRecebedorBaixaEfetiva = codPartRecebedorBaixaEfetiva;
		}
		
		public function get codPartRecebedorBaixaEfetiva():String {
			return _codPartRecebedorBaixaEfetiva;
		}
		
		public function set descTpBaixaEfetiva(descTpBaixaEfetiva:String):void {
			this._descTpBaixaEfetiva = descTpBaixaEfetiva;
		}
		
		public function get descTpBaixaEfetiva():String {
			return _descTpBaixaEfetiva;
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
		
		public function set descBanco(descBanco:String):void {
			this._descBanco = descBanco;
		}
		
		public function get descBanco():String {
			return _descBanco;
		}
		
		public function get descricaoBanco():String {
			if(codPartRecebedorBaixaEfetiva != null && descBanco != null){
				return codPartRecebedorBaixaEfetiva + " - " + descBanco;
			}
			return "";
		}
		
		public function get descBancoEfCompleta():String {
			if(codPartRecebedorBaixaEfetiva != null && descBanco != null){
				return this._descBancoEfCompleta = codPartRecebedorBaixaEfetiva + " - " + descBanco;
			}
			return "";
		}
	}
}