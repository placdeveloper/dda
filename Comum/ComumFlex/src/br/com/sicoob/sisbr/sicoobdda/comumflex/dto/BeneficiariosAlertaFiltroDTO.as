package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {
	
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.BeneficiariosAlertaFiltroDTO", BeneficiariosAlertaFiltroDTO);
	
	public class BeneficiariosAlertaFiltroDTO {
		
		private var _bancoCaf:BancoCafDTO;
		private var _codTipoPessoa:String;
		private var _codStatusAlerta:String;
		private var _cPF_CNPJ:String;
		private var _isBeneficiariosSicoob:Boolean;
		
		public function BeneficiariosAlertaFiltroDTO(bancoCaf:BancoCafDTO = null, codTipoPessoa:String = null, codStatusAlerta:String = null, cPF_CNPJ:String = null, 
				isBeneficiariosSicoob = false) {
			this._bancoCaf = bancoCaf;
			this._codTipoPessoa = codTipoPessoa;
			this._codStatusAlerta = codStatusAlerta;
			this._cPF_CNPJ = cPF_CNPJ;
			this._isBeneficiariosSicoob = isBeneficiariosSicoob;
		}
		
		public function set bancoCaf(bancoCaf:BancoCafDTO):void {
			this._bancoCaf = _bancoCaf;
		}
		
		public function get bancoCaf():BancoCafDTO {
			return _bancoCaf;
		}
		
		public function set codTipoPessoa(codTipoPessoa:String):void {
			this._codTipoPessoa = codTipoPessoa;
		}
		
		public function get codTipoPessoa():String {
			return _codTipoPessoa;
		}
		
		public function set codStatusAlerta(codStatusAlerta:String):void {
			this._codStatusAlerta = codStatusAlerta;
		}
		
		public function get codStatusAlerta():String {
			return _codStatusAlerta;
		}
		
		public function set cPF_CNPJ(cPF_CNPJ:String):void {
			this._cPF_CNPJ = cPF_CNPJ;
		}
		
		public function get cPF_CNPJ():String {
			return _cPF_CNPJ;
		}
		
		public function set isBeneficiariosSicoob(isBeneficiariosSicoob:Boolean):void {
			this._isBeneficiariosSicoob = isBeneficiariosSicoob;
		}
		
		public function get isBeneficiariosSicoob():Boolean {
			return _isBeneficiariosSicoob;
		}
		
	}
	
}