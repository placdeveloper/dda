package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.varredura.abas
{
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;

	public class ListaArquivoVarredura extends ListaArquivoVarreduraView {
		
		private var _modoArqSemGEN0015:Boolean = false;
		
		public function ListaArquivoVarredura() {
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			colId.visible = colTipoArquivo.visible = colTipoMensagem.visible = colDataMovimento.visible = !_modoArqSemGEN0015;
		}
		
		public function set modoArqSemGEN0015(modoArqSemGEN0015:Boolean):void {
			_modoArqSemGEN0015 = modoArqSemGEN0015;
		}
		
		public function set dataProvider(lista:ArrayCollection):void {
			gridDetalhamento.dataProvider = lista;
		}
	}
}