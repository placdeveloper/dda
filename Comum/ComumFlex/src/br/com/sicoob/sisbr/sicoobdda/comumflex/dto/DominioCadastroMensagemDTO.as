package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import mx.collections.ArrayCollection;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.DominioCadastroMensagemDTO", DominioCadastroMensagemDTO);

	public class DominioCadastroMensagemDTO {

		private var _listaSituacaoBoleto:ArrayCollection;
		private var _listaTipoBoletoConsultado:ArrayCollection;

		public function set listaSituacaoBoleto(listaSituacaoBoleto:ArrayCollection):void {
			this._listaSituacaoBoleto = listaSituacaoBoleto;
		}

		public function get listaSituacaoBoleto():ArrayCollection {
			return _listaSituacaoBoleto;
		}

		public function set listaTipoBoletoConsultado(listaTipoBoletoConsultado:ArrayCollection):void {
			this._listaTipoBoletoConsultado = listaTipoBoletoConsultado;
		}

		public function get listaTipoBoletoConsultado():ArrayCollection {
			return _listaTipoBoletoConsultado;
		}

	}
}