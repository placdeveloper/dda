package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import mx.collections.ArrayCollection;

	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoTarifavelDDAVO", EventoTarifavelDDAVO);

	public class EventoTarifavelDDAVO {

		private var _codEventoTarifavel:Number;
		private var _bolAtivo:Boolean;
		private var _bolTarifavel:Boolean;
		private var _descEventoTarifavelExtrato:String;
		private var _descEventoTarifavelManual:String;
		private var _idInstituicao:Number;
		private var _idTipoHistoricoLancamentoCCO:Number;
		private var _idUsuario:String;
		private var _numLoteLancamentoCCO:Number;
		private var _listaEventoTarifavelDDATarifa:ArrayCollection;
		private var _listaEventoConsolidadoDDA:ArrayCollection;
		private var _tipoEventoTarifavel:TipoEventoTarifavelVO;
		private var _tipoOperacaoLog:TipoOperacaoLogVO;
		private var _tipoServicoTarifavel:TipoServicoTarifavelVO;

		public function set codEventoTarifavel(codEventoTarifavel:Number):void {
			this._codEventoTarifavel = codEventoTarifavel;
		}

		public function get codEventoTarifavel():Number {
			return _codEventoTarifavel;
		}

		public function set bolAtivo(bolAtivo:Boolean):void {
			this._bolAtivo = bolAtivo;
		}

		public function get bolAtivo():Boolean {
			return _bolAtivo;
		}

		public function set bolTarifavel(bolTarifavel:Boolean):void {
			this._bolTarifavel = bolTarifavel;
		}

		public function get bolTarifavel():Boolean {
			return _bolTarifavel;
		}

		public function set descEventoTarifavelExtrato(descEventoTarifavelExtrato:String):void {
			this._descEventoTarifavelExtrato = descEventoTarifavelExtrato;
		}

		public function get descEventoTarifavelExtrato():String {
			return _descEventoTarifavelExtrato;
		}

		public function set descEventoTarifavelManual(descEventoTarifavelManual:String):void {
			this._descEventoTarifavelManual = descEventoTarifavelManual;
		}

		public function get descEventoTarifavelManual():String {
			return _descEventoTarifavelManual;
		}

		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}

		public function get idInstituicao():Number {
			return _idInstituicao;
		}

		public function set idTipoHistoricoLancamentoCCO(idTipoHistoricoLancamentoCCO:Number):void {
			this._idTipoHistoricoLancamentoCCO = idTipoHistoricoLancamentoCCO;
		}

		public function get idTipoHistoricoLancamentoCCO():Number {
			return _idTipoHistoricoLancamentoCCO;
		}

		public function set idUsuario(idUsuario:String):void {
			this._idUsuario = idUsuario;
		}

		public function get idUsuario():String {
			return _idUsuario;
		}

		public function set numLoteLancamentoCCO(numLoteLancamentoCCO:Number):void {
			this._numLoteLancamentoCCO = numLoteLancamentoCCO;
		}

		public function get numLoteLancamentoCCO():Number {
			return _numLoteLancamentoCCO;
		}

		public function set listaEventoTarifavelDDATarifa(listaEventoTarifavelDDATarifa:ArrayCollection):void {
			this._listaEventoTarifavelDDATarifa = listaEventoTarifavelDDATarifa;
		}

		public function get listaEventoTarifavelDDATarifa():ArrayCollection {
			return _listaEventoTarifavelDDATarifa;
		}

		public function set listaEventoConsolidadoDDA(listaEventoConsolidadoDDA:ArrayCollection):void {
			this._listaEventoConsolidadoDDA = listaEventoConsolidadoDDA;
		}

		public function get listaEventoConsolidadoDDA():ArrayCollection {
			return _listaEventoConsolidadoDDA;
		}

		public function set tipoEventoTarifavel(tipoEventoTarifavel:TipoEventoTarifavelVO):void {
			this._tipoEventoTarifavel = tipoEventoTarifavel;
		}

		public function get tipoEventoTarifavel():TipoEventoTarifavelVO {
			return _tipoEventoTarifavel;
		}

		public function set tipoOperacaoLog(tipoOperacaoLog:TipoOperacaoLogVO):void {
			this._tipoOperacaoLog = tipoOperacaoLog;
		}

		public function get tipoOperacaoLog():TipoOperacaoLogVO {
			return _tipoOperacaoLog;
		}

		public function set tipoServicoTarifavel(tipoServicoTarifavel:TipoServicoTarifavelVO):void {
			this._tipoServicoTarifavel = tipoServicoTarifavel;
		}

		public function get tipoServicoTarifavel():TipoServicoTarifavelVO {
			return _tipoServicoTarifavel;
		}

	}
}