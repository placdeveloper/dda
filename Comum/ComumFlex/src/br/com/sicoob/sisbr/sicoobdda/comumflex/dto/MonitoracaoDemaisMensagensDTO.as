package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {


	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDemaisMensagensDTO", MonitoracaoDemaisMensagensDTO);

	public class MonitoracaoDemaisMensagensDTO {

		private var _msgEnviar:Number;
		private var _msgSemRetornoSSPB:Number;
		private var _msgSemRetornoCIP:Number;
		private var _msgRetornoErro:Number;
		private var _alertaEnviar:Number;
		private var _alertaSemRetornoSSPB:Number;
		private var _alertaSemRetornoCIP:Number;
		private var _alertaRetornoErro:Number;
		private var _dataHoraAtualizacao:IDateTime;
		private var _parametroTempoAtualizacao:Number;
		private var _bolAlertaBloqueioMotorEnvio:Boolean;
		private var _bolAlertaGradeHoraria:Boolean;
		private var _bolAlertaErroProtocolo:Boolean;
		private var _listaDetalhamento:ArrayCollection;
		private var _listaErro:ArrayCollection;

		public function set msgEnviar(msgEnviar:Number):void {
			this._msgEnviar = msgEnviar;
		}

		public function get msgEnviar():Number {
			return _msgEnviar;
		}

		public function set msgSemRetornoSSPB(msgSemRetornoSSPB:Number):void {
			this._msgSemRetornoSSPB = msgSemRetornoSSPB;
		}

		public function get msgSemRetornoSSPB():Number {
			return _msgSemRetornoSSPB;
		}

		public function set msgSemRetornoCIP(msgSemRetornoCIP:Number):void {
			this._msgSemRetornoCIP = msgSemRetornoCIP;
		}

		public function get msgSemRetornoCIP():Number {
			return _msgSemRetornoCIP;
		}

		public function set msgRetornoErro(msgRetornoErro:Number):void {
			this._msgRetornoErro = msgRetornoErro;
		}

		public function get msgRetornoErro():Number {
			return _msgRetornoErro;
		}

		public function set alertaEnviar(alertaEnviar:Number):void {
			this._alertaEnviar = alertaEnviar;
		}

		public function get alertaEnviar():Number {
			return _alertaEnviar;
		}

		public function set alertaSemRetornoSSPB(alertaSemRetornoSSPB:Number):void {
			this._alertaSemRetornoSSPB = alertaSemRetornoSSPB;
		}

		public function get alertaSemRetornoSSPB():Number {
			return _alertaSemRetornoSSPB;
		}

		public function set alertaSemRetornoCIP(alertaSemRetornoCIP:Number):void {
			this._alertaSemRetornoCIP = alertaSemRetornoCIP;
		}

		public function get alertaSemRetornoCIP():Number {
			return _alertaSemRetornoCIP;
		}

		public function set alertaRetornoErro(alertaRetornoErro:Number):void {
			this._alertaRetornoErro = alertaRetornoErro;
		}

		public function get alertaRetornoErro():Number {
			return _alertaRetornoErro;
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