package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import br.com.bancoob.tipos.IDateTime;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comum.fachada.dto.MensagemDDABoletoGrupoCalculoDTO", MensagemDDABoletoGrupoCalculoDTO);

	public class MensagemDDABoletoGrupoCalculoDTO {

		private var _idMensagemDDABoletoGrupoCalculo:Number;
		private var _juros:Number;
		private var _multa:Number;
		private var _desconto:Number;
		private var _valorTotalCobrado:Number;
		private var _dataValidadeCalculo:IDateTime;

		public function set idMensagemDDABoletoGrupoCalculo(idMensagemDDABoletoGrupoCalculo:Number):void {
			this._idMensagemDDABoletoGrupoCalculo = idMensagemDDABoletoGrupoCalculo;
		}

		public function get idMensagemDDABoletoGrupoCalculo():Number {
			return _idMensagemDDABoletoGrupoCalculo;
		}

		public function set juros(juros:Number):void {
			this._juros = juros;
		}

		public function get juros():Number {
			return _juros;
		}

		public function set multa(multa:Number):void {
			this._multa = multa;
		}

		public function get multa():Number {
			return _multa;
		}

		public function set desconto(desconto:Number):void {
			this._desconto = desconto;
		}

		public function get desconto():Number {
			return _desconto;
		}

		public function set valorTotalCobrado(valorTotalCobrado:Number):void {
			this._valorTotalCobrado = valorTotalCobrado;
		}

		public function get valorTotalCobrado():Number {
			return _valorTotalCobrado;
		}

		public function set dataValidadeCalculo(dataValidadeCalculo:IDateTime):void {
			this._dataValidadeCalculo = dataValidadeCalculo;
		}

		public function get dataValidadeCalculo():IDateTime {
			return _dataValidadeCalculo;
		}

	}
}