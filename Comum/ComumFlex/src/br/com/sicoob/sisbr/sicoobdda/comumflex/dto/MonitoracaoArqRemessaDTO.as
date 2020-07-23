package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoArqRemessaDTO", MonitoracaoArqRemessaDTO);

	public class MonitoracaoArqRemessaDTO {

		private var _arqEnviar:Number;
		private var _arqSemProtocolo:Number;
		private var _arqSemRetornoCIP:Number;
		private var _arqProcessadoErro:Number;
		private var _dataHoraAtualizacao:IDateTime;
		private var _parametroTempoAtualizacao:Number;
		private var _alertaEnviar:Number;
		private var _alertaSemProtocoloCIP:Number;
		private var _alertaSemRetornoCIP:Number;
		private var _alertaProcessadoErro:Number;
		private var _bolAlertaBloqueioMotorEnvio:Boolean;
		private var _bolAlertaGradeHoraria:Boolean;
		private var _bolAlertaErroProtocolo:Boolean;
		private var _listaDetalhamento:ArrayCollection;
		private var _listaErro:ArrayCollection;

		public function set arqEnviar(arqEnviar:Number):void {
			this._arqEnviar = arqEnviar;
		}

		public function get arqEnviar():Number {
			return _arqEnviar;
		}

		public function set arqSemProtocolo(arqSemProtocolo:Number):void {
			this._arqSemProtocolo = arqSemProtocolo;
		}

		public function get arqSemProtocolo():Number {
			return _arqSemProtocolo;
		}

		public function set arqSemRetornoCIP(arqSemRetornoCIP:Number):void {
			this._arqSemRetornoCIP = arqSemRetornoCIP;
		}

		public function get arqSemRetornoCIP():Number {
			return _arqSemRetornoCIP;
		}

		public function set arqProcessadoErro(arqProcessadoErro:Number):void {
			this._arqProcessadoErro = arqProcessadoErro;
		}

		public function get arqProcessadoErro():Number {
			return _arqProcessadoErro;
		}

		public function set dataHoraAtualizacao(dataHoraAtualizacao:IDateTime):void {
			this._dataHoraAtualizacao = dataHoraAtualizacao;
		}

		public function get dataHoraAtualizacao():IDateTime {
			return _dataHoraAtualizacao;
		}

		public function set parametroTempoAtualizacao(parametroTempoAtualizacao:Number):void {
			this._parametroTempoAtualizacao = parametroTempoAtualizacao;
		}

		public function get parametroTempoAtualizacao():Number {
			return _parametroTempoAtualizacao;
		}

		public function set alertaEnviar(alertaEnviar:Number):void {
			this._alertaEnviar = alertaEnviar;
		}

		public function get alertaEnviar():Number {
			return _alertaEnviar;
		}

		public function set alertaSemProtocoloCIP(alertaSemProtocoloCIP:Number):void {
			this._alertaSemProtocoloCIP = alertaSemProtocoloCIP;
		}

		public function get alertaSemProtocoloCIP():Number {
			return _alertaSemProtocoloCIP;
		}

		public function set alertaSemRetornoCIP(alertaSemRetornoCIP:Number):void {
			this._alertaSemRetornoCIP = alertaSemRetornoCIP;
		}

		public function get alertaSemRetornoCIP():Number {
			return _alertaSemRetornoCIP;
		}

		public function set alertaProcessadoErro(alertaProcessadoErro:Number):void {
			this._alertaProcessadoErro = alertaProcessadoErro;
		}

		public function get alertaProcessadoErro():Number {
			return _alertaProcessadoErro;
		}

		public function set bolAlertaBloqueioMotorEnvio(bolAlertaBloqueioMotorEnvio:Boolean):void {
			this._bolAlertaBloqueioMotorEnvio = bolAlertaBloqueioMotorEnvio;
		}
		
		public function get bolAlertaBloqueioMotorEnvio():Boolean {
			return _bolAlertaBloqueioMotorEnvio;
		}
		
		public function set bolAlertaGradeHoraria(bolAlertaGradeHoraria:Boolean):void {
			this._bolAlertaGradeHoraria = bolAlertaGradeHoraria;
		}
		
		public function get bolAlertaGradeHoraria():Boolean {
			return _bolAlertaGradeHoraria;
		}
		
		public function set bolAlertaErroProtocolo(bolAlertaErroProtocolo:Boolean):void {
			this._bolAlertaErroProtocolo = bolAlertaErroProtocolo;
		}
		
		public function get bolAlertaErroProtocolo():Boolean {
			return _bolAlertaErroProtocolo;
		}
		
		public function set listaDetalhamento(listaDetalhamento:ArrayCollection):void {
			this._listaDetalhamento = listaDetalhamento;
		}
		
		public function get listaDetalhamento():ArrayCollection {
			return _listaDetalhamento;
		}
		
		public function set listaErro(listaErro:ArrayCollection):void {
			this._listaErro = listaErro;
		}
		
		public function get listaErro():ArrayCollection {
			return _listaErro;
		}
		
	}
}