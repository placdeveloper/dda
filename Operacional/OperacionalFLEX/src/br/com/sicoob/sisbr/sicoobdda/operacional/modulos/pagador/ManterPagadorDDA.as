package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.pagador
{
	
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.controls.CheckBox;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.HistoricoPagadorEletronicoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.PagadorAgregadoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.PagadorDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class ManterPagadorDDA extends ManterPagadorDDAView implements ITelaBasePlataformaAtendimento {
		
		private var _checkBoxHeaderTodos:CheckBox;
		private var _listaAgregadoDto:ArrayCollection;
		private var _numCpfCnpjAgregado:String;
		
		private var _codTermoContratoDDA:Number = 1;
		private var _codTermoAdesaoDDA:Number = 11;
		private var _codTermoCancelarDDA:Number = 12;
		private var _codTermoIncluirAgregado:Number = 21;
		private var _codTermoExcluirAgregado:Number = 22;
		
		public static const OPERACAO_ADESAO:String = "solicitarAdesao";
		public static const OPERACAO_CANCELAR_ADESAO:String = "solicitarCancelamentoAdesao";
		public static const OPERACAO_INCLUIR_AGREGADO:String = "solicitarInclusaoPagadorAgregado";
		public static const OPERACAO_EXCLUIR_AGREGADO:String = "solicitarExclusaoPagadorAgregado";
		
		public var servicoAdesao:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_PAGADOR_ELETRONICO, OPERACAO_ADESAO);
		public var servicoCancelarAdesao:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_PAGADOR_ELETRONICO, OPERACAO_CANCELAR_ADESAO);
		public var servicoIncluirAgregado:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_PAGADOR_ELETRONICO, OPERACAO_INCLUIR_AGREGADO);
		public var servicoExcluirAgregado:ServicoJava  = Funcoes.obterServico(Constantes.SERVICO_PAGADOR_ELETRONICO, OPERACAO_EXCLUIR_AGREGADO);
		
		
		private var _pagador:PagadorDTO;
		
		private var _funcaoAtualizar:Function;
		
		/**
		 *  Construtor.
		 */
		public function ManterPagadorDDA(funcaoAtualizar:Function = null)	{
			super();
			
			servicoAdesao.addEventListener(ResultEvent.RESULT, retornoMensagemAdesao_onResult);
			servicoCancelarAdesao.addEventListener(ResultEvent.RESULT, retornoMensagemCancelarAdesao_onResult);
			servicoIncluirAgregado.addEventListener(ResultEvent.RESULT, retornoMensagemIncluirAgregado_onResult);
			servicoExcluirAgregado.addEventListener(ResultEvent.RESULT, retornoMensagemExcluir_onResult);
			servicoAdesao.addEventListener(FaultEvent.FAULT, retornoMensagem_onFault);
			servicoCancelarAdesao.addEventListener(FaultEvent.FAULT, retornoMensagem_onFault);
			servicoIncluirAgregado.addEventListener(FaultEvent.FAULT, retornoMensagem_onFault);
			servicoExcluirAgregado.addEventListener(FaultEvent.FAULT, retornoMensagem_onFault);
		}
		
		public function configurarDestinosServicos(destino:DestinoVO):void{
			this.destino = destino;
			servicoAdesao.configurarDestino(this.destino);
			servicoCancelarAdesao.configurarDestino(this.destino);
			servicoIncluirAgregado.configurarDestino(this.destino);
			servicoExcluirAgregado.configurarDestino(this.destino);
		}	
		
		//--------------------------------------------------------------------------
		//  Métodos das interfaces
		//--------------------------------------------------------------------------
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};
		
		public function preencherCampos(e:Event = null):void {
			if (_pagador) {
				labelPagagorDDA.text = _pagador.bolPagadorEletronico ? "SIM" : "NÃO";
				abaPrincipal.gridCCO.dataProvider = _pagador.listaNumCCO;
				abaPrincipal.gridAgregados.dataProvider = _listaAgregadoDto = _pagador.listaPagadorAgregado;
				abaPrincipal.gridMensagensPendentes.dataProvider = _pagador.listaMensagemPendente;
				abaReimpressao.gridHistPagadorEletronico.dataProvider = _pagador.listaHistPagadorEletronico;
			} else {
				labelPagagorDDA.text = "NÃO";
			}
		}	
		
		public function enviarMensagemAdesao(e:Event):void {
			servicoAdesao.solicitarAdesao(obterRequisicaoPagadorDto());
		}
		
		public function enviarMensagemCancelarAdesao(e:Event):void {
			servicoCancelarAdesao.solicitarCancelamentoAdesao(obterRequisicaoPagadorDto())
		}
		
		public function enviarMensagemExcluirAgregado(e:Event):void {
			var dto: RequisicaoReqDTO = obterRequisicaoDto();
			dto.dados["pagadorAgregado"] = getPagadorAgregadoDTO();
			servicoExcluirAgregado.solicitarExclusaoPagadorAgregado(dto);
		}
		
		public function enviarMensagemIncluirAgregado(e:Event):void {
			var dto: RequisicaoReqDTO = obterRequisicaoDto();
			dto.dados["pagadorAgregado"] = new PagadorAgregadoDTO(_numCpfCnpjAgregado);
			servicoIncluirAgregado.solicitarInclusaoPagadorAgregado(dto);
		}
		
		private function obterRequisicaoDto():RequisicaoReqDTO {
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();                                                                  
			dto.dados["numCpfCnpj"] = _pagador.numCpfCnpj;
			dto.dados["idInstituicao"] = _pagador.idInstituicao;
			return dto;
		}
		private function obterRequisicaoPagadorDto():RequisicaoReqDTO {
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["pagadorDTO"] = _pagador;
			return dto;
		}
		
		private function getPagadorAgregadoDTO():PagadorAgregadoDTO {
			return abaPrincipal.gridAgregados.selectedItem as PagadorAgregadoDTO;
		}
		
		private function geraListaPagadorAgregado():ArrayCollection {
			var lista:ArrayCollection = new ArrayCollection();
			for each(var agregado:PagadorAgregadoDTO in  abaPrincipal.gridAgregados.dataProvider) {
				if(agregado.selecionado) {
					lista.addItem(agregado);
				}
			}
			return lista;
		}
		
		private function retornoMensagem_onResult(evt:ResultEvent):void {
			var p:PagadorDTO = evt.result.dados.pagador;
			_pagador.bolPagadorEletronico = p.bolPagadorEletronico;
			_pagador.listaMensagemPendente = p.listaMensagemPendente;
			_pagador.listaNumCCO = p.listaNumCCO;
			_pagador.listaPagadorAgregado = p.listaPagadorAgregado;
			_pagador.listaHistPagadorEletronico = p.listaHistPagadorEletronico;
		}
		
		private function retornoMensagemAdesao_onResult(evt:ResultEvent):void {
			retornoMensagem_onResult(evt);	
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG059, preencherCampos);
			imprimirTermoAdesaoDDA(servicoAdesao.destino);
		}
		
		private function retornoMensagemCancelarAdesao_onResult(evt:ResultEvent):void {
			retornoMensagem_onResult(evt);	
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG059, preencherCampos);
			imprimirTermoCancelarAdesaoDDA(servicoCancelarAdesao.destino);
		}
		
		private function retornoMensagemIncluirAgregado_onResult(evt:ResultEvent):void {
			retornoMensagem_onResult(evt);	
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG059, preencherCampos);
			imprimirTermoIncluirAgregado(servicoIncluirAgregado.destino);
		}
		
		private function retornoMensagemExcluir_onResult(evt:ResultEvent):void {
			retornoMensagem_onResult(evt);	
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG059, preencherCampos);
			imprimirTermoExcluirAgregado(servicoExcluirAgregado.destino);
		}
		
		private function retornoMensagem_onFault(evt:FaultEvent):void {
			if (_funcaoAtualizar != null) {
				_funcaoAtualizar();
			}
		}
		
		public function verificaSeHaAlgumItemSelecionado():Boolean {
			var retorno:Boolean = false;
				if(abaPrincipal.gridAgregados.selectedItem) {
					retorno = true;
				}
			return retorno;
		}
		
		public function definirHeaderCheckBox(cb:Object):void {
			if (cb is CheckBox) {
				_checkBoxHeaderTodos = (cb as CheckBox);
			}
		}
		
		public function set pagador(pagador:PagadorDTO):void {
			_pagador = pagador;
			preencherCampos();
		}
		
		public function set numCpfCnpjAgregado(numCpfCnpjAgregado:String):void {
			_numCpfCnpjAgregado = numCpfCnpjAgregado;
		}
		//--------------------------------------------------------------------------
		//  Métodos de Impressão
		//--------------------------------------------------------------------------
		public function imprimirTermoContrato(destino:DestinoVO):void{			
			var historicoDTO:HistoricoPagadorEletronicoDTO = new HistoricoPagadorEletronicoDTO();
			var pagadorDto:PagadorDTO = this._pagador;
			historicoDTO.codTipoTermoPagador = this._codTermoContratoDDA;
			historicoDTO.nomePagador = pagadorDto.nomePessoa;
			historicoDTO.numCpfCnpjPagador = pagadorDto.numCpfCnpj;
			
			imprimir(historicoDTO);
		}
		/**
		 * Os dados de impressão são preenchidos por um evento da abaReimpressao
		 * caso um item do historico não seja selecionado não tera ação de impressão.
		 */
		public function imprimirTermoPagadorEletronico(destino:DestinoVO):void{
			if(abaReimpressao.dadosImpressao){
				imprimir(abaReimpressao.dadosImpressao);
			}else{
				MensagensComum.exibirMensagemInformacao("Selecione um termo do histórico");
			}
			
		}
		
		public function imprimirTermoAdesaoDDA(destino:DestinoVO):void{
			var historicoDTO:HistoricoPagadorEletronicoDTO = new HistoricoPagadorEletronicoDTO();
			var pagadorDto:PagadorDTO = this._pagador;
			historicoDTO.codTipoTermoPagador = this._codTermoAdesaoDDA;
			historicoDTO.nomePagador = pagadorDto.nomePessoa;
			historicoDTO.numCpfCnpjPagador = pagadorDto.numCpfCnpj;
			
			imprimir(historicoDTO);
		}
		
		public function imprimirTermoCancelarAdesaoDDA(destino:DestinoVO):void{
			var historicoDTO:HistoricoPagadorEletronicoDTO = new HistoricoPagadorEletronicoDTO();
			var pagadorDto:PagadorDTO = this._pagador;
			historicoDTO.codTipoTermoPagador = this._codTermoCancelarDDA;
			historicoDTO.nomePagador = pagadorDto.nomePessoa;
			historicoDTO.numCpfCnpjPagador = pagadorDto.numCpfCnpj;
			
			imprimir(historicoDTO);
		}
		
		public function imprimirTermoIncluirAgregado(destino:DestinoVO):void{
			var historicoDTO:HistoricoPagadorEletronicoDTO = new HistoricoPagadorEletronicoDTO();
			historicoDTO.codTipoTermoPagador = this._codTermoIncluirAgregado;
			historicoDTO.nomePagador = this._pagador.nomePessoa;
			historicoDTO.numCpfCnpjPagador = this._pagador.numCpfCnpj;
			historicoDTO.numCpfCnpjAgregado = this._pagador.listaHistPagadorEletronico.getItemAt(0).numCpfCnpjAgregado;
			historicoDTO.nomeAgregado = this._pagador.listaHistPagadorEletronico.getItemAt(0).nomeAgregado;

			imprimir(historicoDTO);
		}
		
		public function imprimirTermoExcluirAgregado(destino:DestinoVO):void{
			var historicoDTO:HistoricoPagadorEletronicoDTO = new HistoricoPagadorEletronicoDTO();
			historicoDTO.codTipoTermoPagador = this._codTermoExcluirAgregado;
			historicoDTO.nomePagador = this._pagador.nomePessoa;
			historicoDTO.numCpfCnpjPagador = this._pagador.numCpfCnpj;
			historicoDTO.numCpfCnpjAgregado = this._pagador.listaHistPagadorEletronico.getItemAt(0).numCpfCnpjAgregado;
			historicoDTO.nomeAgregado = this._pagador.listaHistPagadorEletronico.getItemAt(0).nomeAgregado;

			imprimir(historicoDTO);
			
		}
		
		private function imprimir(historicoDTO:HistoricoPagadorEletronicoDTO):void{
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_TERMO_CONTRATO_DDA, historicoDTO, null, PreImpressao.FORMATO_PDF, null, destino);
		}
		
		public function cancelarSelecao():void {
			if(this._pagador){
				abaReimpressao.gridHistPagadorEletronico.dataProvider = this._pagador.listaHistPagadorEletronico;
			}
		}
		
	}
}
