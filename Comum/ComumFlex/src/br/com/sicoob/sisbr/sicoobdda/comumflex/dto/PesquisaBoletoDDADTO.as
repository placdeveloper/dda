package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PesquisaBoletoDDADTO", PesquisaBoletoDDADTO);
	
	public class PesquisaBoletoDDADTO
	{
		
		private var _numBanco:String;
		
		private var _numCpfCnpjBeneficiario:String;
		private var _numCpfCnpjPagador:String;
		private var _numCpfCnpjPagadorAgregado:String;
		private var _numCpfCnpjTerceiro:String;
		
		private var _numCodigoBarra:String;
		private var _numLinhaDigitavel:String;
		
		private var _dataVencimentoInicial:IDateTime;
		private var _dataVencimentoFinal:IDateTime;
		private var _dataRegistroDDAInicial:IDateTime;
		private var _dataRegistroDDAFinal:IDateTime;
		
		private var _numSeuNumero:String;
		private var _codSituacaoBoleto:String;
		
		private var _dataProcessamento:IDateTime;
		private var _dataHoraEntrada:IDateTime;
		private var _dataVencimento:IDateTime;
		private var _valorBoleto:Number;
		
		private var _descSituacaoBoletoCIP:String;
		private var _descSituacaoBoletoSicoob:String;
		private var _numCooperativa:String;
		
		private var _descTipoPagador:String;
		
		
		public function PesquisaBoletoDDADTO()
		{
		}
		
		public function get numBanco():String {
			return _numBanco;
		}
		
		public function set numBanco(numBanco:String):void {
			this._numBanco = numBanco;
		}
		
		public function get numCpfCnpjBeneficiario():String {
			return _numCpfCnpjBeneficiario;
		}
		
		public function set numCpfCnpjBeneficiario(numCpfCnpjBeneficiario:String):void {
			this._numCpfCnpjBeneficiario = numCpfCnpjBeneficiario;
		}
		
		public function get numCpfCnpjPagador():String {
			return _numCpfCnpjPagador;
		}
		
		public function set numCpfCnpjPagador(numCpfCnpjPagador:String):void {
			this._numCpfCnpjPagador = numCpfCnpjPagador;
		}
		
		public function get numCpfCnpjPagadorAgregado():String {
			return _numCpfCnpjPagadorAgregado;
		}
		
		public function set numCpfCnpjPagadorAgregado(numCpfCnpjPagadorAgregado:String):void {
			this._numCpfCnpjPagadorAgregado = numCpfCnpjPagadorAgregado;
		}
		
		public function get numCpfCnpjTerceiro():String {
			return _numCpfCnpjTerceiro;
		}
		
		public function set numCpfCnpjTerceiro(numCpfCnpjTerceiro:String):void {
			this._numCpfCnpjTerceiro = numCpfCnpjTerceiro;
		}
		
		public function get numCodigoBarra():String {
			return _numCodigoBarra;
		}
		
		public function set numCodigoBarra(numCodigoBarra:String):void {
			this._numCodigoBarra = numCodigoBarra;
		}
		
		public function get numLinhaDigitavel():String {
			return _numLinhaDigitavel;
		}
		
		public function set numLinhaDigitavel(numLinhaDigitavel:String):void {
			this._numLinhaDigitavel = numLinhaDigitavel;
		}
		
		public function get dataVencimentoInicial():IDateTime {
			return _dataVencimentoInicial;
		}
		
		public function set dataVencimentoInicial(dataVencimentoInicial:IDateTime):void {
			this._dataVencimentoInicial = dataVencimentoInicial;
		}
		
		public function get dataVencimentoFinal():IDateTime {
			return _dataVencimentoFinal;
		}
		
		public function set dataVencimentoFinal(dataVencimentoFinal:IDateTime):void {
			this._dataVencimentoFinal = dataVencimentoFinal;
		}
		
		public function get dataRegistroDDAInicial():IDateTime {
			return _dataRegistroDDAInicial;
		}
		
		public function set dataRegistroDDAInicial(dataRegistroDDAInicial:IDateTime):void {
			this._dataRegistroDDAInicial = dataRegistroDDAInicial;
		}
		
		public function get dataRegistroDDAFinal():IDateTime {
			return _dataRegistroDDAFinal;
		}
		
		public function set dataRegistroDDAFinal(dataRegistroDDAFinal:IDateTime):void {
			this._dataRegistroDDAFinal = dataRegistroDDAFinal;
		}
		
		
		public function get numSeuNumero():String {
			return _numSeuNumero;
		}
		
		public function set numSeuNumero(numSeuNumero:String):void {
			this._numSeuNumero = numSeuNumero;
		}
		
		public function get codSituacaoBoleto():String {
			return _codSituacaoBoleto;
		}
		
		public function set codSituacaoBoleto(codSituacaoBoleto:String):void {
			this._codSituacaoBoleto = codSituacaoBoleto;
		}
		
		public function get dataProcessamento():IDateTime {
			return _dataProcessamento;
		}
		
		public function set dataProcessamento(dataProcessamento:IDateTime):void {
			this._dataProcessamento = dataProcessamento;
		}
		
		public function get dataHoraEntrada():IDateTime {
			return _dataHoraEntrada;
		}
		
		public function set dataHoraEntrada(dataHoraEntrada:IDateTime):void {
			this._dataHoraEntrada = dataHoraEntrada;
		}
		
		public function get dataVencimento():IDateTime {
			return _dataVencimento;
		}
		
		public function set dataVencimento(dataVencimento:IDateTime):void {
			this._dataVencimento = dataVencimento;
		}
		
		public function get valorBoleto():Number {
			return _valorBoleto;
		}
		
		public function set valorBoleto(valorBoleto:Number):void {
			this._valorBoleto = valorBoleto;
		}
		
		public function get descSituacaoBoletoCIP():String {
			return _descSituacaoBoletoCIP;
		}
		
		public function set descSituacaoBoletoCIP(descSituacaoBoletoCIP:String):void {
			this._descSituacaoBoletoCIP = descSituacaoBoletoCIP;
		}
		
		public function get descSituacaoBoletoSicoob():String {
			return _descSituacaoBoletoSicoob;
		}
		
		public function set descSituacaoBoletoSicoob(descSituacaoBoletoSicoob:String):void {
			this._descSituacaoBoletoSicoob = descSituacaoBoletoSicoob;
		}
		
		public function get numCooperativa():String {
			return _numCooperativa;
		}
		
		public function set numCooperativa(numCooperativa:String):void {
			this._numCooperativa = numCooperativa;
		}
		
		public function get descTipoPagador():String {
			return _descTipoPagador;
		}
		
		public function set descTipoPagador(descTipoPagador:String):void {
			this._descTipoPagador = descTipoPagador;
		}
	}
}