package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.visualizarateiotarifa.abas.rateio
{
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.VisualizaRateioTarifaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.NumeroUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class AbaDadosRateio extends AbaDadosRateioView
	{
	
		public var _funcaoAtualizar:Function;
//		public var _visualRateioTarifaDtoTemp:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO();
		public var _listaDadosRateioTemp:ArrayCollection = new ArrayCollection();
		public var _totalTemp:VisualizaRateioTarifaDTO = new VisualizaRateioTarifaDTO();
		
		public function AbaDadosRateio()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			iniciarComponentes();
		}
		
		private function iniciarComponentes():void {
			iniciarEventos();
			carregarFiltro();
		}
		
		//--------------------------------------------------------------------------
		//  Carrega filtros.
		//--------------------------------------------------------------------------
		private function carregarFiltro(evt:Event = null):void{
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MANTER_EVENTO_TARIFAVEL, "listaEventoTarifavelDDA");
			servico.addEventListener(ResultEvent.RESULT, retornoFiltro);
			servico.listaEventoTarifavelDDA();
			
		}
		private function iniciarEventos():void {


		}
		
		public function retornoFiltro(event:ResultEvent):void{
			this.cmbEventoTarifavel.dataProvider = event.result.dados["lista"];
		}
		
		public function totalizadores(visualRateioTarifaDTO:VisualizaRateioTarifaDTO):void {
			if(visualRateioTarifaDTO.totalDadosRateio){
				lblQtdApuradoBancoob.text = visualRateioTarifaDTO.totalDadosRateio.qtdApuradoBancoob == 0 ? "" : FormatUtil.formataValor(visualRateioTarifaDTO.totalDadosRateio.qtdApuradoBancoob, "###.###.###");
				lblValorApuradoBancoob.text = new String("R$ "+NumeroUtil.converterNumeroDecimalParaMoeda(visualRateioTarifaDTO.totalDadosRateio.valorApuradoBancoob));
				lblQtdApuradoCIP.text = visualRateioTarifaDTO.totalDadosRateio.qtdApuradoCIP == 0 ? "" : FormatUtil.formataValor(visualRateioTarifaDTO.totalDadosRateio.qtdApuradoCIP, "###.###.###");
				lblValorTarifaCIP.text = new String("R$ "+NumeroUtil.converterNumeroDecimalParaMoeda(visualRateioTarifaDTO.totalDadosRateio.valorApuradoCIP));
				lblDifQtdValor.text = new String(visualRateioTarifaDTO.totalDadosRateio.difQuantidade == 0 ? "" : FormatUtil.formataValor(visualRateioTarifaDTO.totalDadosRateio.difQuantidade, "###.###.###") + "/ R$" + NumeroUtil.converterNumeroDecimalParaMoeda(visualRateioTarifaDTO.totalDadosRateio.difValor));
				lblValorRateioCoop.text = new String("R$ "+NumeroUtil.converterNumeroDecimalParaMoeda(visualRateioTarifaDTO.totalDadosRateio.valorRateioCoop));
			}
		}
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
		
	}
}