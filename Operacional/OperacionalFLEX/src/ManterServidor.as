package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.JanelaCobranca;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.ServidorDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.servidor.DetServidorDDA;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.servidor.ManterServidorView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class ManterServidor extends ManterServidorView {
		
		
		public function ManterServidor() {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			obterServidor();
			
			btnAlterar.addEventListener(MouseEvent.CLICK, alterarServidor);
			
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			
			filtro.btnProcura.addEventListener(MouseEvent.CLICK, pesquisarServidorDDA);
			filtro.btnLimpar.addEventListener(MouseEvent.CLICK, limparPesquisa);
			
			filtro.cbStatus.selectedIndex = 0;
			gridServidor.addEventListener(ListEvent.ITEM_CLICK, habilitarAlteracao);
		}
		
		private function pesquisarServidorDDA(e:MouseEvent):void {
			var metodo:String = "pesquisarServidorDDA";
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_SERVIDOR_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoPesquisa);
			servico.mensagemEspera = "Pesquisando Servidor DDA...";
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["vo"] = montarFiltroPesquisa();
			
			servico.pesquisarServidorDDA(dto);
		}
		
		private function habilitarAlteracao(e:Event):void {
			if (gridServidor.selectedItem) {
				btnAlterar.enabled = true;
			} else {
				btnAlterar.enabled = false;
			}
		}
		
		private function obterServidor():void {
			var metodo:String = "listarServidorDDA";
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_SERVIDOR_DDA, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoListarServidorDDA);
			servico.mensagemEspera = "Buscando definições do componente...";
			
			servico.listarServidorDDA();
		}
		
		private function montarFiltroPesquisa():ServidorDDAVO {
			var servidor:ServidorDDAVO = new ServidorDDAVO();
			if (filtro.cbServidor.selectedItem) {
				servidor.codServidorDDA = filtro.cbServidor.selectedItem.codServidorDDA;
			}
			if (filtro.cbPerfil.selectedItem) {
				servidor.descPerfil = filtro.cbPerfil.selectedItem.label;
			}
			
			servidor.bolAtivo = filtro.cbStatus.selectedItem.data ? true : false;
			return servidor;
		}
		
		private function resultadoPesquisa(e:ResultEvent):void {
			var lista:ArrayCollection = e.result.dados.lista as ArrayCollection;
			if (lista.length) {
				gridServidor.dataProvider = lista; 
			} else {
				MensagensComum.exibirMensagemAlerta(Mensagens.MSG076);
				limparGrid();
			}
		}
		
		private function resultadoListarServidorDDA(e:ResultEvent):void {
			var lista:ArrayCollection = e.result.dados.lista as ArrayCollection;
			if (lista.length) {
				filtro.cbServidor.dataProvider = lista;
			} else {
				btnAlterar.enabled = false;
			}
		}
		
		private function alterarServidor(e:MouseEvent):void {
			if (gridServidor.selectedItem) {
				var detServidor:DetServidorDDA = new DetServidorDDA(gridServidor.selectedItem as ServidorDDAVO);
				JanelaCobranca.abrirJanela(this, detServidor, "DETALHE SERVIDOR DDA", true, limparPesquisa);
			} else {
				MensagensComum.exibirMensagemAlerta(MensagensComum.formatar(Mensagens.MSG011, "Servidor"));
			}
		}
		
		private function limparPesquisa(e:Event):void {
			filtro.cbServidor.selectedIndex = 0;
			filtro.cbPerfil.selectedIndex = 0;
			filtro.cbStatus.selectedIndex = 0;
			
			limparGrid();
		}
		
		private function limparGrid():void {
			gridServidor.dataProvider = null;
			btnAlterar.enabled = false;
		}
		
		private function fechar(e:MouseEvent):void {
			this.fecharJanela();
		}
		
	}
}