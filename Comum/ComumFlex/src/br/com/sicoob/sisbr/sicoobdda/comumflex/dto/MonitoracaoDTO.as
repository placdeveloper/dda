package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDTO", MonitoracaoDTO);

	public class MonitoracaoDTO {

		private var _monitoracaoDDA0110:MonitoracaoDDA0110DTO;
		private var _monitoracaoDemaisMensagens:MonitoracaoDemaisMensagensDTO;
		private var _monitoracaoArqRemessa:MonitoracaoArqRemessaDTO;
		private var _monitoracaoArqVarredura:MonitoracaoArqVarreduraDTO;

		public function set monitoracaoDDA0110(monitoracaoDDA0110:MonitoracaoDDA0110DTO):void {
			this._monitoracaoDDA0110 = monitoracaoDDA0110;
		}

		public function get monitoracaoDDA0110():MonitoracaoDDA0110DTO {
			return _monitoracaoDDA0110;
		}
		
		public function set monitoracaoDemaisMensagens(monitoracaoDemaisMensagens:MonitoracaoDemaisMensagensDTO):void {
			this._monitoracaoDemaisMensagens = monitoracaoDemaisMensagens;
		}
		
		public function get monitoracaoDemaisMensagens():MonitoracaoDemaisMensagensDTO {
			return _monitoracaoDemaisMensagens;
		}


		public function set monitoracaoArqRemessa(monitoracaoArqRemessa:MonitoracaoArqRemessaDTO):void {
			this._monitoracaoArqRemessa = monitoracaoArqRemessa;
		}

		public function get monitoracaoArqRemessa():MonitoracaoArqRemessaDTO {
			return _monitoracaoArqRemessa;
		}

		public function set monitoracaoArqVarredura(monitoracaoArqVarredura:MonitoracaoArqVarreduraDTO):void {
			this._monitoracaoArqVarredura = monitoracaoArqVarredura;
		}

		public function get monitoracaoArqVarredura():MonitoracaoArqVarreduraDTO {
			return _monitoracaoArqVarredura;
		}

	}
}