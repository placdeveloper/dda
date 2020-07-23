package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoArqVarreduraDTO", MonitoracaoArqVarreduraDTO);

	public class MonitoracaoArqVarreduraDTO {

		private var _arqDisponivel:Number;
		private var _gen0015SemArq:Number;
		private var _arqSemGEN0015:Number;
		private var _arqEmProcessamento:Number;
		private var _dataHoraAtualizacao:IDateTime;
		private var _parametroTempoAtualizacao:Number;
		private var _alertaArqDisponivel:Number;
		private var _alertaGEN0015SemArq:Number;
		private var _alertaArqSemGEN0015:Number;
		private var _alertaArqEmProcessamento:Number;
		private var _bolAlertaGEN0015SemArq:Boolean;
		private var _listaArqDisponivel:ArrayCollection;
		private var _listaGEN0015SemArq:ArrayCollection;
		private var _listaArqSemGEN0015:ArrayCollection;
		private var _listaArqEmProcessamento:ArrayCollection;

		public function set arqDisponivel(arqDisponivel:Number):void {
			this._arqDisponivel = arqDisponivel;
		}

		public function get arqDisponivel():Number {
			return _arqDisponivel;
		}

		public function set gen0015SemArq(gen0015SemArq:Number):void {
			this._gen0015SemArq = gen0015SemArq;
		}

		public function get gen0015SemArq():Number {
			return _gen0015SemArq;
		}

		public function set arqSemGEN0015(arqSemGEN0015:Number):void {
			this._arqSemGEN0015 = arqSemGEN0015;
		}

		public function get arqSemGEN0015():Number {
			return _arqSemGEN0015;
		}

		public function set arqEmProcessamento(arqEmProcessamento:Number):void {
			this._arqEmProcessamento = arqEmProcessamento;
		}

		public function get arqEmProcessamento():Number {
			return _arqEmProcessamento;
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

		public function set alertaArqDisponivel(alertaArqDisponivel:Number):void {
			this._alertaArqDisponivel = alertaArqDisponivel;
		}

		public function get alertaArqDisponivel():Number {
			return _alertaArqDisponivel;
		}

		public function set alertaGEN0015SemArq(alertaGEN0015SemArq:Number):void {
			this._alertaGEN0015SemArq = alertaGEN0015SemArq;
		}

		public function get alertaGEN0015SemArq():Number {
			return _alertaGEN0015SemArq;
		}

		public function set alertaArqSemGEN0015(alertaArqSemGEN0015:Number):void {
			this._alertaArqSemGEN0015 = alertaArqSemGEN0015;
		}

		public function get alertaArqSemGEN0015():Number {
			return _alertaArqSemGEN0015;
		}

		public function set alertaArqEmProcessamento(alertaArqEmProcessamento:Number):void {
			this._alertaArqEmProcessamento = alertaArqEmProcessamento;
		}

		public function get alertaArqEmProcessamento():Number {
			return _alertaArqEmProcessamento;
		}
		
		public function set bolAlertaGEN0015SemArq(bolAlertaGEN0015SemArq:Boolean):void {
			this._bolAlertaGEN0015SemArq = bolAlertaGEN0015SemArq;
		}
		
		public function get bolAlertaGEN0015SemArq():Boolean {
			return _bolAlertaGEN0015SemArq;
		}
		
		public function set listaArqDisponivel(listaArqDisponivel:ArrayCollection):void {
			this._listaArqDisponivel = listaArqDisponivel;
		}
		
		public function get listaArqDisponivel():ArrayCollection {
			return _listaArqDisponivel;
		}
		
		public function set listaGEN0015SemArq(listaGEN0015SemArq:ArrayCollection):void {
			this._listaGEN0015SemArq = listaGEN0015SemArq;
		}
		
		public function get listaGEN0015SemArq():ArrayCollection {
			return _listaGEN0015SemArq;
		}
		
		public function set listaArqSemGEN0015(listaArqSemGEN0015:ArrayCollection):void {
			this._listaArqSemGEN0015 = listaArqSemGEN0015;
		}
		
		public function get listaArqSemGEN0015():ArrayCollection {
			return _listaArqSemGEN0015;
		}
		
		public function set listaArqEmProcessamento(listaArqEmProcessamento:ArrayCollection):void {
			this._listaArqEmProcessamento = listaArqEmProcessamento;
		}
		
		public function get listaArqEmProcessamento():ArrayCollection {
			return _listaArqEmProcessamento;
		}
		
	}
}