package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.CadastroGen0014DTO", CadastroGen0014DTO);

	public class CadastroGen0014DTO {

		private var _codTipoSolBeneficiario:String;
		private var _codSituacaoBeneficiario:String;
		private var _codTipoSolPagador:String;
		private var _codTipoSolBoleto:String;
		private var _codTipoBoletoConsultado:String;

		public function set codTipoSolBeneficiario(codTipoSolBeneficiario:String):void {
			this._codTipoSolBeneficiario = codTipoSolBeneficiario;
		}

		public function get codTipoSolBeneficiario():String {
			return _codTipoSolBeneficiario;
		}

		public function set codSituacaoBeneficiario(codSituacaoBeneficiario:String):void {
			this._codSituacaoBeneficiario = codSituacaoBeneficiario;
		}

		public function get codSituacaoBeneficiario():String {
			return _codSituacaoBeneficiario;
		}

		public function set codTipoSolPagador(codTipoSolPagador:String):void {
			this._codTipoSolPagador = codTipoSolPagador;
		}

		public function get codTipoSolPagador():String {
			return _codTipoSolPagador;
		}

		public function set codTipoSolBoleto(codTipoSolBoleto:String):void {
			this._codTipoSolBoleto = codTipoSolBoleto;
		}

		public function get codTipoSolBoleto():String {
			return _codTipoSolBoleto;
		}

		public function set codTipoBoletoConsultado(codTipoBoletoConsultado:String):void {
			this._codTipoBoletoConsultado = codTipoBoletoConsultado;
		}

		public function get codTipoBoletoConsultado():String {
			return _codTipoBoletoConsultado;
		}

	}
}