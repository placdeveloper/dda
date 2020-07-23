package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.pagador
{
	
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.controls.CheckBox;
	import mx.events.FlexEvent;
	
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.HistoricoPagadorEletronicoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class AbaReimpressao extends AbaReimpressaoView {
		
		private var _checkBoxHeaderTodos:CheckBox;
		private var _listaAgregadoDto:ArrayCollection;
		
		private var _dadosImpressao:HistoricoPagadorEletronicoDTO;
	
		/**
		 *  Construtor.
		 */
		public function AbaReimpressao()	{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		protected function init(event:FlexEvent):void {
			gridHistPagadorEletronico.addEventListener(MouseEvent.CLICK, tratarSelecionar);
		}
		//--------------------------------------------------------------------------
		//  Métodos das interfaces
		//--------------------------------------------------------------------------
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};
		
		public function tratarSelecionar(e:MouseEvent):void {
			dadosImpressao = gridHistPagadorEletronico.selectedItem as HistoricoPagadorEletronicoDTO ;
		}
		//--------------------------------------------------------------------------
		//  Carraga dados para impressão ao selecionar Item da Grid
		//--------------------------------------------------------------------------
		public function set dadosImpressao(dadosImpressao:HistoricoPagadorEletronicoDTO):void {
			this._dadosImpressao = dadosImpressao;
		}
		
		public function get dadosImpressao():HistoricoPagadorEletronicoDTO {
			return _dadosImpressao;
		}
	}
}
