package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.titulo
{
	import mx.core.IToolTip;
	
	import br.com.bancoob.util.FormataData;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoArqRemessaDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoArqVarreduraDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoDDA0110DTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MonitoracaoDemaisMensagensDTO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.remessa.tooltip.TooltipArquivoRemessa;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.arquivos.varredura.tooltip.TooltipArquivoVarredura;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.dda0110.tooltip.TooltipDDA0110;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.monitoracao.mensagens.demaismensagens.tooltip.TooltipDemaisMensagens;
	
	public class TituloMonitoracao extends TituloMonitoracaoView {
		
		private const ATUALIZACAO:String = "Última Atualização: ";
		
		public static const MODO_DDA0110:int = 0;
		public static const MODO_DEMAIS_MSG:int = 1;
		public static const MODO_ARQ_REMESSA:int = 2;
		public static const MODO_ARQ_VARREDURA:int = 3;
		
		private var _modo:int;
		private var _monitoracaoDTO:Object;
		
		public function TituloMonitoracao(modo:int) {
			_modo = modo;
			init();
		}
		
		private function init():void {
			switch(_modo) {
				case MODO_DDA0110: {
					_titulo = "Registros por MQ - DDA0110";
					_toolTipCustom = new TooltipDDA0110();
					break;
				}
				case MODO_DEMAIS_MSG: {
					_titulo = "Registros por MQ - Demais Mensagens";
					_toolTipCustom = new TooltipDemaisMensagens();
					break;
				}
				case MODO_ARQ_REMESSA: {
					_titulo = "Registros por Arquivo de Remessa";
					_toolTipCustom = new TooltipArquivoRemessa();
					break;
				}
				default: {
					_titulo = "Registros por Arquivos Recebidos";
					_toolTipCustom = new TooltipArquivoVarredura();
					break;
				}
			}
		}
		
		private function inserirParametros():void {
			switch(_modo) {
				case MODO_DDA0110: {
					(_toolTipCustom as TooltipDDA0110).monitoracaoDDA0110 = _monitoracaoDTO as MonitoracaoDDA0110DTO;
					break;
				}
				case MODO_DEMAIS_MSG: {
					(_toolTipCustom as TooltipDemaisMensagens).monitoracaoDemaisMensagens = _monitoracaoDTO as MonitoracaoDemaisMensagensDTO;
					break;
				}
				case MODO_ARQ_REMESSA: {
					(_toolTipCustom as TooltipArquivoRemessa).monitoracaoArqRemessa = _monitoracaoDTO as MonitoracaoArqRemessaDTO;
					break;
				}
				default: {
					(_toolTipCustom as TooltipArquivoVarredura).monitoracaoArqVarredura = _monitoracaoDTO as MonitoracaoArqVarreduraDTO;
					break;
				}
			}
		}
		
		public function get toolTipCustom():IToolTip {
			return _toolTipCustom;
		}
		
		public function set atualizacao(dateTimeAtualizacao:Date):void {
			_atualizacao = ATUALIZACAO + FormataData.formataDataHora(dateTimeAtualizacao);
		}
		
		public function set monitoracaoDTO(monitoracaoDTO:Object):void {
			_monitoracaoDTO = monitoracaoDTO;
			if (_monitoracaoDTO) {
				inserirParametros();
			}
		}
	}
}