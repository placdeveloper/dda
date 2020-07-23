package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.FiltroRequisicaoCacheDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RequisicaoCacheVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cache.CadastrarRequisicaoCache;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cache.MonitoracaoCacheView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;

	registerClassAlias("RegistroVO", RegistroVO);
	
	public class MonitoracaoCache extends MonitoracaoCacheView {
		
		private var _listaServidor:ArrayCollection = new ArrayCollection();
		
		public function MonitoracaoCache() {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected function init(evento:FlexEvent): void {
			obterServidor();
			
			filtro.btnProcura.addEventListener(MouseEvent.CLICK, pesquisar);
			filtro.btnLimpar.addEventListener(MouseEvent.CLICK, limparPesquisa);
			gridRequisicao.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, filtrarAtualizacaoCache);
			
			btnCadastrar.addEventListener(MouseEvent.CLICK, abrirJanelaCadastroRequisicao);
			btnCadastrar.enabled = true;
			
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
		}
		
		private function pesquisar(e:MouseEvent = null):void {
			var metodo:String = "pesquisarRequisicaoCache";			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO_CACHE, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoListarRequisicao);
			servico.mensagemEspera = "Pesquisando Requisição Cache...";
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["dto"] = montarFiltroPesquisa();			
			servico.pesquisarRequisicaoCache(dto);
		}
		
		private function resultadoListarRequisicao(e:ResultEvent):void {
			var lista:ArrayCollection = e.result.dados.lista as ArrayCollection;
			if (lista.length) {
				gridRequisicao.dataProvider = lista; 
			} else {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG041, "Requisição"));
				limparGrid();
			}
		}
		
		private function montarFiltroPesquisa():FiltroRequisicaoCacheDTO {
			var filtroDTO:FiltroRequisicaoCacheDTO = new FiltroRequisicaoCacheDTO();
			if (filtro.cbServidor.selectedItem) {
				filtroDTO.codServidorDestino = filtro.cbServidor.selectedItem.codServidorDDA;
			}
			if (filtro.cbPerfil.selectedItem) {
				filtroDTO.descPerfilDestino = filtro.cbPerfil.selectedItem.label;
			}
			if (filtro.cbCache.selectedItem) {
				filtroDTO.descCache = filtro.cbCache.selectedItem.data;
			}
			filtroDTO.dataRequisicao =  DateTimeBase.getDateTime(filtro.dataRequisicao.selectedDate);
			return filtroDTO;
		}
		
		private function obterServidor():void {
			var metodo:String = "listarServidorAtivo";
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_SERVIDOR_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoListarServidorDDA);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.listarServidorAtivo();
		}
		
		private function resultadoListarServidorDDA(e:ResultEvent):void {
			filtro.cbServidor.dataProvider = _listaServidor = e.result.dados.lista as ArrayCollection;
		}
		
		
		private function filtrarAtualizacaoCache(e:ListEvent):void {
			var metodo:String = "listarAtualizacaoCache";
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO_CACHE, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoListarAtualizacao);
			servico.mensagemEspera = "Listando Atualização Cache...";
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["vo"] = e.target.selectedItem as RequisicaoCacheVO;
			
			servico.listarAtualizacaoCache(dto);
		}
		
		private function resultadoListarAtualizacao(e:ResultEvent):void {
			var lista:ArrayCollection = e.result.dados.lista as ArrayCollection
			if (lista.length) {
				gridAtualizacao.dataProvider  = lista; 
			} else {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(MensagensComum.MSG041, "Atualização"));
			}
		}
		
		private function abrirJanelaCadastroRequisicao(e:MouseEvent):void {
			var cadastroRequisicao:CadastrarRequisicaoCache = new CadastrarRequisicaoCache(_listaServidor,pesquisar);
			JanelaCobranca.abrirJanela(this, cadastroRequisicao, "CADASTRO REQUISIÇÃO CACHE", true);
		}
		
		private function limparPesquisa(e:MouseEvent):void {
			filtro.cbCache.selectedIndex = 0;
			filtro.cbServidor.selectedIndex = 0;
			filtro.cbPerfil.selectedIndex = 0;
			filtro.dataRequisicao.selectedDate = new Date();
			limparGrid();
		}
		
		private function limparGrid(e:Event = null):void {
			gridAtualizacao.dataProvider = null;
			gridRequisicao.dataProvider = null;
		}
		
		private function fechar(e:Event):void {
			fecharJanela();
		}
	}
}