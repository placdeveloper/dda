package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.plataforma.atendimento
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.IValidarAbertura;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.TelaPlataformaAtendimentoCustomizado;
	import br.com.bancoob.sisbr.eventos.EventValidacaoAbertura;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.PagadorDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.TermoPagadorDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class DDATelaPlataformaAtendimentoBase extends TelaPlataformaAtendimentoCustomizado implements IValidarAbertura {
		
		private var servicoPessoa:ServicoJava = new ServicoJava();
		private var _destinoVO:DestinoVO;
		private var _destinoCAPES:DestinoVO;
		private var _pessoaPlataformaVO:PessoaPlataformaVO = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
		
		private var _pagador:PagadorDTO;
		private var _contratoDDA:TermoPagadorDTO;
		
		
		private static const DESTINO_SERVICO_DDA:String = "servicosJavaSDDA";
		private static const DESTINO_CAPES:String = "servicosJavaCapes";
		
		public function DDATelaPlataformaAtendimentoBase() {
			super();           

			carregarServicoPessoa("obterDadosPagador", obterPagadorDDA_onResult);
			PortalModel.portal.obterDefinicoesDestino(DESTINO_SERVICO_DDA, configurarDestino);
			PortalModel.portal.obterDefinicoesDestino(DESTINO_CAPES, configurarDestinoCAPES);
		}       
		
		public function carregarServicoPessoa(metodoDestino:String, funcaoRetorno:Function):void {
			servicoPessoa = Funcoes.obterServico(Constantes.SERVICO_PAGADOR_ELETRONICO, metodoDestino); 
			servicoPessoa.mensagemEspera = "Carregando dados...";
			servicoPessoa.addEventListener(ResultEvent.RESULT, funcaoRetorno);
			servicoPessoa.addEventListener(FaultEvent.FAULT, validarAbertura_onError);
		}
		//--------------------------------------------------------------------------
		//  Métodos da interface.
		//--------------------------------------------------------------------------
		public function validarAbertura(params:Object=null):void {
			this.dispatchEvent(new EventValidacaoAbertura(EventValidacaoAbertura.EVENTO_VALIDACAO_OK));
		}
		
		public function validarAbertura_onResult(evt:ResultEvent):void {
			this.dispatchEvent(new EventValidacaoAbertura(EventValidacaoAbertura.EVENTO_VALIDACAO_OK));
		}
		
		public function validarAbertura_onError(evt:FaultEvent):void {
			this.dispatchEvent(new EventValidacaoAbertura(EventValidacaoAbertura.EVENTO_VALIDACAO_ERRO));
		}
		
		private function obterPagadorDDA_onResult(event:ResultEvent):void {
			_pagador = event.result.dados.pagador;
			_pagador.numCpfCnpj = _pessoaPlataformaVO.cpfCnpj;
			_pagador.idInstituicao = _pessoaPlataformaVO.idInstituicao;
			_pagador.nomePessoa = _pessoaPlataformaVO.nomePessoa;
			this.dispatchEvent(new Event(Modulo.REGISTRO_CARREGADO));
		}
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------
		private function configurarDestino(destino:DestinoVO):void {
			servicoPessoa.configurarDestino(destino);
			_destinoVO = destino;
			obterDadosPagador();
		}
		
		private function configurarDestinoCAPES(destino:DestinoVO):void {
			_destinoCAPES = destino;
		}  
		
		/** Função que deve ser sobrescrita para carregar os serviços desejados. */
		protected function configurarDestinosServicos(destinoVO:DestinoVO):void{
		}
		
		protected function obterDadosPagador(e:MouseEvent = null): void {
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["numCpfCnpj"] = _pessoaPlataformaVO.cpfCnpj;
			dto.dados["idInstituicao"] = _pessoaPlataformaVO.idInstituicao;
			servicoPessoa.obterDadosPagador(dto);
		}
		
		protected function get pagador():PagadorDTO {
			return _pagador;
		}
		
		protected function destinoCAPES():DestinoVO {
			return _destinoCAPES;
		}
		
		protected function destinoDDA():DestinoVO {
			return _destinoVO;
		}
		
		protected function pessoaPlataforma():PessoaPlataformaVO {
			return _pessoaPlataformaVO;
		}
		
	}
}