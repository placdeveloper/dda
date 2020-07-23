package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import mx.collections.ArrayCollection;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TratamentoPendenciaErroDTO", TratamentoPendenciaErroDTO);

	public class TratamentoPendenciaErroDTO {

		private var _listaPendencia:ArrayCollection;
		private var _listaErroAgrupado:ArrayCollection;
		private var _listaErroProcessamentoCip:ArrayCollection;

		public function set listaPendencia(listaPendencia:ArrayCollection):void {
			this._listaPendencia = listaPendencia;
		}

		public function get listaPendencia():ArrayCollection {
			return _listaPendencia;
		}

		public function set listaErroAgrupado(listaErroAgrupado:ArrayCollection):void {
			this._listaErroAgrupado = listaErroAgrupado;
		}

		public function get listaErroAgrupado():ArrayCollection {
			return _listaErroAgrupado;
		}

		public function set listaErroProcessamentoCip(listaErroProcessamentoCip:ArrayCollection):void {
			this._listaErroProcessamentoCip = listaErroProcessamentoCip;
		}

		public function get listaErroProcessamentoCip():ArrayCollection {
			return _listaErroProcessamentoCip;
		}

	}
}