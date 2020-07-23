package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {
	
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.BeneficiarioAlertaDTO", BeneficiarioAlertaDTO);
	
	public class BeneficiarioAlertaDTO {
		private var _idBeneficiarioDDA:Number;
		private var _descTipoPessoa:String;
		private var _cPF_CNPJ:String;
		private var _descStatusAlerta:String;
		private var _numCodISPB:String;
		private var _nomeBancoConveniado:String;
		private var _dataHoraAlerta:IDateTime;
		private var _bolSicoob:Boolean;
		private var _nomeCooperativa:String;
		private var _numeroCooperativa:String;
		private var _postoAvancCooperativa:String;
		
		public function set idBeneficiarioDDA(idBeneficiarioDDA:Number):void {
			this._idBeneficiarioDDA = idBeneficiarioDDA;
		}
		
		public function get idBeneficiarioDDA():Number {
			return _idBeneficiarioDDA;
		}
		
		public function set descTipoPessoa(descTipoPessoa:String):void {
			this._descTipoPessoa = descTipoPessoa;
		}
		
		public function get descTipoPessoa():String {
			return _descTipoPessoa;
		}
		
		public function set cPF_CNPJ(cPF_CNPJ:String):void {
			this._cPF_CNPJ = cPF_CNPJ;
		}
		
		public function get cPF_CNPJ():String {
			return _cPF_CNPJ;
		}
		
		public function set descStatusAlerta(descStatusAlerta:String):void {
			this._descStatusAlerta = descStatusAlerta;
		}
		
		public function get descStatusAlerta():String {
			return _descStatusAlerta;
		}
		
		public function set numCodISPB(numCodISPB:String):void {
			this._numCodISPB = numCodISPB;
		}
		
		public function get numCodISPB():String {
			return _numCodISPB;
		}
		
		public function set nomeBancoConveniado(nomeBancoConveniado:String):void {
			this._nomeBancoConveniado = nomeBancoConveniado;
		}
		
		public function get nomeBancoConveniado():String {
			return _nomeBancoConveniado;
		}
		
		public function set dataHoraAlerta(dataHoraAlerta:IDateTime):void {
			this._dataHoraAlerta = dataHoraAlerta;
		}
		
		public function get dataHoraAlerta():IDateTime {
			return _dataHoraAlerta;
		}
		
		public function set bolSicoob(bolSicoob:Boolean):void {
			this._bolSicoob = bolSicoob;
		}
		
		public function get bolSicoob():Boolean {
			return _bolSicoob;
		}
		
		public function set nomeCooperativa(nomeCooperativa:String):void {
			this._nomeCooperativa = nomeCooperativa;
		}
		
		public function get nomeCooperativa():String {
			return _nomeCooperativa;
		}
		
		public function set numeroCooperativa(numeroCooperativa:String):void {
			this._numeroCooperativa = numeroCooperativa;
		}
		
		public function get numeroCooperativa():String {
			return _numeroCooperativa;
		}
		
		public function set postoAvancCooperativa(postoAvancCooperativa:String):void {
			this._postoAvancCooperativa = postoAvancCooperativa;
		}
		
		public function get postoAvancCooperativa():String {
			return _postoAvancCooperativa;
		}
	}
}