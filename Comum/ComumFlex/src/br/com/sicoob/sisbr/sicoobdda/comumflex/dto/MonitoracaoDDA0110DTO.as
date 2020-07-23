package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDDA0110DTO", MonitoracaoDDA0110DTO);

	public class MonitoracaoDDA0110DTO {

		private var _dda0110Sucesso:Number;
		private var _dda0110Rejeitada:Number;
		private var _dda0110SucessoTotal:Number;
		private var _dda0110RejeitadaTotal:Number;

		private var _dataHoraAtualizacao:IDateTime;
		private var _dataHoraAfericao:String;
		private var _horaAfericao:String;
		
		private var _parametroTempoAtualizacao:Number;
		private var _percentualAceitavelSLA:Number;
		
		private var _bolConsultaCipHabilitada:Boolean;
		private var _bolContingenciaHabilitadaAutomatica:Boolean;
		private var _bolContingenciaHabilitadaManual:Boolean;
		
		public function set dda0110Sucesso(dda0110Sucesso:Number):void {
			this._dda0110Sucesso = dda0110Sucesso;
		}

		public function get dda0110Sucesso():Number {
			return _dda0110Sucesso;
		}

		public function set dda0110Rejeitada(dda0110Rejeitada:Number):void {
			this._dda0110Rejeitada = dda0110Rejeitada;
		}

		public function get dda0110Rejeitada():Number {
			return _dda0110Rejeitada;
		}

		public function set dda0110SucessoTotal(dda0110SucessoTotal:Number):void {
			this._dda0110SucessoTotal = dda0110SucessoTotal;
		}

		public function get dda0110SucessoTotal():Number {
			return _dda0110SucessoTotal;
		}

		public function set dda0110RejeitadaTotal(dda0110RejeitadaTotal:Number):void {
			this._dda0110RejeitadaTotal = dda0110RejeitadaTotal;
		}

		public function get dda0110RejeitadaTotal():Number {
			return _dda0110RejeitadaTotal;
		}

		public function set dataHoraAtualizacao(dataHoraAtualizacao:IDateTime):void {
			this._dataHoraAtualizacao = dataHoraAtualizacao;
		}

		public function get dataHoraAtualizacao():IDateTime {
			return _dataHoraAtualizacao;
		}

		public function set dataHoraAfericao(dataHoraAfericao:String):void {
			this._dataHoraAfericao = dataHoraAfericao;
		}
		
		public function get dataHoraAfericao():String {
			return _dataHoraAfericao;
		}
		
		public function set horaAfericao(horaAfericao:String):void {
			this._horaAfericao = horaAfericao;
		}
		
		public function get horaAfericao():String {
			return _horaAfericao;
		}
		
		public function set parametroTempoAtualizacao(parametroTempoAtualizacao:Number):void {
			this._parametroTempoAtualizacao = parametroTempoAtualizacao;
		}

		public function get parametroTempoAtualizacao():Number {
			return _parametroTempoAtualizacao;
		}

		public function set percentualAceitavelSLA(percentualAceitavelSLA:Number):void {
			this._percentualAceitavelSLA = percentualAceitavelSLA;
		}

		public function get percentualAceitavelSLA():Number {
			return _percentualAceitavelSLA;
		}

		public function set bolConsultaCipHabilitada(bolConsultaCipHabilitada:Boolean):void {
			this._bolConsultaCipHabilitada = bolConsultaCipHabilitada;
		}
		
		public function get bolConsultaCipHabilitada():Boolean {
			return _bolConsultaCipHabilitada;
		}

		public function set bolContingenciaHabilitadaAutomatica(bolContingenciaHabilitadaAutomatica):void {
			this._bolContingenciaHabilitadaAutomatica = bolContingenciaHabilitadaAutomatica;
		}
		
		public function get bolContingenciaHabilitadaAutomatica():Boolean {
			return _bolContingenciaHabilitadaAutomatica;
		}
		
		public function set bolContingenciaHabilitadaManual(bolContingenciaHabilitadaManual):void {
			this._bolContingenciaHabilitadaManual = bolContingenciaHabilitadaManual;
		}
		
		public function get bolContingenciaHabilitadaManual():Boolean {
			return _bolContingenciaHabilitadaManual;
		}
	}
}