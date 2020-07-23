package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.PagadorDDAVO", PagadorDDAVO);

	public class PagadorDDAVO {

		private var _id:Number;
		private var _numCpfCnpj:String;
		private var _codTipoPessoaCip:String;
		
		private var _numIdentificaPagadorCip:Number;
		
		private var _numRefAtualCadPagador:Number;
		
		private var _numSeqAtualCadPagador:Number;
		
		private var _qtdAdesaoDDA:Number;
		
		private var _bolSacadoEletronico:Boolean;
		
		private var _dataHoraUltimaAtualizacao:IDateTime;
		
		private var _listaPagadorDDAAgregado:ArrayCollection;
		
		private var _listaPagadorDDAConta:ArrayCollection;


		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}
		
		public function set numCpfCnpj(numCpfCnpj:String):void {
			this._numCpfCnpj = numCpfCnpj;
		}
		
		public function get numCpfCnpj():String {
			return _numCpfCnpj;
		}
		
		public function set codTipoPessoaCip(codTipoPessoaCip:String):void {
			this._codTipoPessoaCip = codTipoPessoaCip;
		}
		
		public function get codTipoPessoaCip():String {
			return _codTipoPessoaCip;
		}
		
		public function set numIdentificaPagadorCip(numIdentificaPagadorCip:Number):void {
			this._numIdentificaPagadorCip = numIdentificaPagadorCip;
		}
		
		public function get numIdentificaPagadorCip():Number {
			return _numIdentificaPagadorCip;
		}
		
		public function set numRefAtualCadPagador(numRefAtualCadPagador:Number):void {
			this._numRefAtualCadPagador = numRefAtualCadPagador;
		}
		
		public function get numRefAtualCadPagador():Number {
			return _numRefAtualCadPagador;
		}
		
		public function set numSeqAtualCadPagador(numSeqAtualCadPagador:Number):void {
			this._numSeqAtualCadPagador = numSeqAtualCadPagador;
		}
		
		public function get numSeqAtualCadPagador():Number {
			return _numSeqAtualCadPagador;
		}
		
		public function set qtdAdesaoDDA(qtdAdesaoDDA:Number):void {
			this._qtdAdesaoDDA = qtdAdesaoDDA;
		}
		
		public function get qtdAdesaoDDA():Number {
			return _qtdAdesaoDDA;
		}
		
		public function set bolSacadoEletronico(bolSacadoEletronico:Boolean):void {
			this._bolSacadoEletronico = bolSacadoEletronico;
		}
		
		public function get bolSacadoEletronico():Boolean {
			return _bolSacadoEletronico;
		}
		
		public function set dataHoraUltimaAtualizacao(dataHoraUltimaAtualizacao:IDateTime):void {
			this._dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
		}
		
		public function get dataHoraUltimaAtualizacao():IDateTime {
			return _dataHoraUltimaAtualizacao;
		}
		
		public function set listaPagadorDDAAgregado(listaPagadorDDAAgregado:ArrayCollection):void {
			this._listaPagadorDDAAgregado = listaPagadorDDAAgregado;
		}
		
		public function get listaPagadorDDAAgregado():ArrayCollection {
			return _listaPagadorDDAAgregado;
		}
		
		public function set listaPagadorDDAConta(listaPagadorDDAConta:ArrayCollection):void {
			this._listaPagadorDDAConta = listaPagadorDDAConta;
		}
		
		public function get listaPagadorDDAConta():ArrayCollection {
			return _listaPagadorDDAConta;
		}
		
	}
}