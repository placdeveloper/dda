package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ArquivoRecebidoDTO", ArquivoRecebidoDTO);
	public class ArquivoRecebidoDTO
	{
		private var _idLogRecebimentoArqDDA:Number;
		private var _idLogDetRecebimentoArqDDA:Number;
		private var _dataHoraArquivo:IDateTime;
		private var _descNomeArquivoRecebido:String;
		private var _descTipoArquivo:String;
		private var _codTipoErroCip:String;
		private var _idLogEnvioArqDDA:Number;
		private var _dataMovimento:IDateTime;
		private var _qtdRegistros:Number;
		
		private var _codTipoMensagem:String;
		private var _codTipoArquivo:String;
		private var _codSituacaoProcessamentoArquivo:Number;
		private var _descSituacaoProcessamentoArquivo:String;
		
		private var _listaTipoArquivo:ArrayCollection;
		private var _listaTipoMensagem:ArrayCollection;
		private var _listaSituacaoArquivo:ArrayCollection;
		private var _listaLogDetRecebimentoArquivoDDA:ArrayCollection;
		
		private var _dataArquivoInicio:IDateTime;
		private var _dataArquivoFim:IDateTime;
		
		private var _bolProcessado:Boolean;
		private var _bolTodos:Boolean;
		
		public function ArquivoRecebidoDTO(){}
		
		public function get bolTodos():Boolean {
			return _bolTodos;
		}
		
		public function set bolTodos(bolTodos:Boolean):void {
			this._bolTodos = bolTodos;
		}
		
		public function get bolProcessado():Boolean {
			return _bolProcessado;
		}
		
		public function set bolProcessado(bolProcessado:Boolean):void {
			this._bolProcessado = bolProcessado;
		}
		
		public function get idLogRecebimentoArqDDA():Number {
			return _idLogRecebimentoArqDDA;
		}
		
		public function set idLogRecebimentoArqDDA(idLogRecebimentoArqDDA:Number):void {
			this._idLogRecebimentoArqDDA = idLogRecebimentoArqDDA;
		}
		
		public function get codTipoArquivo():String {
			return _codTipoArquivo;
		}
		
		public function set codTipoArquivo(codTipoArquivo:String):void {
			this._codTipoArquivo = codTipoArquivo;
		}
		
		
		public function get dataHoraArquivo():IDateTime {
			return _dataHoraArquivo;
		}
		
		public function set dataHoraArquivo(dataHoraArquivo:IDateTime):void {
			this._dataHoraArquivo = dataHoraArquivo;
		}
		
		public function get descTipoArquivo():String {
			return _descTipoArquivo;
		}
		
		public function set descTipoArquivo(descTipoArquivo:String):void {
			this._descTipoArquivo = descTipoArquivo;
		}
		
		public function get codTipoMensagem():String {
			return _codTipoMensagem;
		}
		
		public function set codTipoMensagem(codTipoMensagem:String):void {
			this._codTipoMensagem = codTipoMensagem;
		}
		
		public function get codTipoErroCip():String {
			return _codTipoErroCip;
		}
		
		public function set codTipoErroCip(codTipoErroCip:String):void {
			this._codTipoErroCip = codTipoErroCip;
		}
		
		public function get idLogEnvioArqDDA():Number {
			return _idLogEnvioArqDDA;
		}
		
		public function set idLogEnvioArqDDA(idLogEnvioArqDDA:Number):void {
			this._idLogEnvioArqDDA = idLogEnvioArqDDA;
		}
		
		public function get dataMovimento():IDateTime {
			return _dataMovimento;
		}
		
		public function set dataMovimento(dataMovimento:IDateTime):void {
			this._dataMovimento = dataMovimento;
		}
		
		public function get qtdRegistros():Number {
			return _qtdRegistros;
		}
		
		public function set qtdRegistros(qtdRegistros:Number):void {
			this._qtdRegistros = qtdRegistros;
		}
		
		public function get descNomeArquivoRecebido():String {
			return _descNomeArquivoRecebido;
		}
		
		public function set descNomeArquivoRecebido(descNomeArquivoRecebido:String):void {
			this._descNomeArquivoRecebido = descNomeArquivoRecebido;
		}
		
		public function get codSituacaoProcessamentoArquivo():Number {
			return _codSituacaoProcessamentoArquivo;
		}
		
		public function set codSituacaoProcessamentoArquivo(codSituacaoProcessamentoArquivo:Number):void {
			this._codSituacaoProcessamentoArquivo = codSituacaoProcessamentoArquivo;
		}
		
		public function get listaTipoArquivo():ArrayCollection {
			return _listaTipoArquivo;
		}
		
		public function set listaTipoArquivo(listaTipoArquivo:ArrayCollection):void {
			this._listaTipoArquivo = listaTipoArquivo;
		}
		
		public function get listaTipoMensagem():ArrayCollection {
			return _listaTipoMensagem;
		}
		
		public function set listaTipoMensagem(listaTipoMensagem:ArrayCollection):void {
			this._listaTipoMensagem = listaTipoMensagem;
		}
		
		public function get listaSituacaoArquivo():ArrayCollection {
			return _listaSituacaoArquivo;
		}
		
		public function set listaSituacaoArquivo(listaSituacaoArquivo:ArrayCollection):void {
			this._listaSituacaoArquivo = listaSituacaoArquivo;
		}

		public function get dataArquivoInicio():IDateTime {
			return _dataArquivoInicio;
		}
		
		public function set dataArquivoInicio(dataArquivoInicio:IDateTime):void {
			this._dataArquivoInicio = dataArquivoInicio;
		}
		
		public function get dataArquivoFim():IDateTime {
			return _dataArquivoFim;
		}
		
		public function set dataArquivoFim(dataArquivoFim:IDateTime):void {
			this._dataArquivoFim = dataArquivoFim;
		}
		
		public function get descSituacaoProcessamentoArquivo():String {
			return _descSituacaoProcessamentoArquivo;
		}
		
		public function set descSituacaoProcessamentoArquivo(descSituacaoProcessamentoArquivo:String):void {
			this._descSituacaoProcessamentoArquivo = descSituacaoProcessamentoArquivo;
		}
		public function get listaLogDetRecebimentoArquivoDDA():ArrayCollection {
			return _listaLogDetRecebimentoArquivoDDA;
		}
		
		public function set listaLogDetRecebimentoArquivoDDA(listaLogDetRecebimentoArquivoDDA:ArrayCollection):void {
			this._listaLogDetRecebimentoArquivoDDA = listaLogDetRecebimentoArquivoDDA;
		}
		
		public function get idLogDetRecebimentoArqDDA():Number {
			return _idLogDetRecebimentoArqDDA;
		}
		
		public function set idLogDetRecebimentoArqDDA(idLogDetRecebimentoArqDDA:Number):void {
			this._idLogDetRecebimentoArqDDA = idLogDetRecebimentoArqDDA;
		}
		
	}
}