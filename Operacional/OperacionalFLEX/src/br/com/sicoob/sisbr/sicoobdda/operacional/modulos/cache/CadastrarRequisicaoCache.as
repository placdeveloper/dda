package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.cache
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RequisicaoCacheVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.ServidorDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;

	registerClassAlias("RegistroVO", RegistroVO);
	
	public class CadastrarRequisicaoCache extends CadastrarRequisicaoCacheView {
		
		private var _listaServidor:ArrayCollection;
		private var _refazerPesquisa:Function = null
		public function CadastrarRequisicaoCache(listaServidor:ArrayCollection, refazerPesquisa:Function) {
			_listaServidor = listaServidor;
			_refazerPesquisa = refazerPesquisa;
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			cbServidor.dataProvider = _listaServidor;
			cbServidor.selectedIndex = 0;
			
			btnInserir.setStyle("icon", ConstantesComum.icoSalvar);
			
			btnInserir.addEventListener(MouseEvent.CLICK, cadastrarRequisicao);
			btnLimpar.addEventListener(MouseEvent.CLICK, limparFiltro);
			
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
		}
		
		private function limparFiltro(e:MouseEvent):void {
			cbServidor.selectedIndex = 0;
			cbCache.selectedIndex = 0;
		}
		
		private function cadastrarRequisicao(e:MouseEvent):void {
			if (dadosValidos()) {
				var metodo:String = "cadastrarRequisicaoCache";
				
				var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MONITORACAO_CACHE, metodo);
				servico.addEventListener(ResultEvent.RESULT, retornoCadastro);
				servico.mensagemEspera = "Cadastrando Requisição Cache...";
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados["vo"] = montarRequisicaoCache();
				
				servico.cadastrarRequisicaoCache(dto);
			}
		}
		
		private function dadosValidos():Boolean {
			if (!cbCache.selectedItem) {
				MensagensComum.exibirMensagemAlerta("O campo Cache é obrigatório.", cbCache);
				return false;
			}
			return true;
		}
		
		private function montarRequisicaoCache():RequisicaoCacheVO {
			var requisicao:RequisicaoCacheVO = new RequisicaoCacheVO();
			if (cbServidor.selectedItem) {
				requisicao.servidorDDADestino = cbServidor.selectedItem as ServidorDDAVO;
			}
			
			
			requisicao.descCache = cbCache.selectedItem.data;
			
			requisicao.dataHoraRequisicao = DateTimeBase.getDateTime(new Date());
			
			return requisicao;
		}
		
		private function retornoCadastro(e:ResultEvent):void {
			_refazerPesquisa();
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(Mensagens.MSG056, "Requisição Cache"), fechar);
		}
		
		private function fechar(e:Event):void {
			fecharJanela();
		}
	}
}