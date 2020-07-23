package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.servidor
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.ServidorDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;

	public class DetServidorDDA extends DetServidorDDAView {
		
		private var _servidorDDA:ServidorDDAVO;
		
		public function DetServidorDDA(servidorDDA:ServidorDDAVO) {
			_servidorDDA = servidorDDA;
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(e:FlexEvent):void {
			carregarDados();
			
			btnSalvar.addEventListener(MouseEvent.CLICK, salvarAlteracao);
			btnSalvar.setStyle("icon", ConstantesComum.icoSalvar);
			
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			
			btnLimpar.addEventListener(MouseEvent.CLICK, limpar);
		}
		
		private function carregarDados():void {
			txtServidor.text = _servidorDDA.codServidorDDA;
			txtDataCadastro.text = FormataData.formataDataHora(_servidorDDA.dataHoraCadastro.data);
			cbPerfil.selectedIndex = obterPerfilSelecionado();
			cbStatus.selectedIndex = _servidorDDA.bolAtivo ? 0 : 1;
		}
		
		private function obterPerfilSelecionado():Number {
			switch(_servidorDDA.descPerfil) {
				case "BACKOFFICE": {
					return 1;
				}
				case "FRONTOFFICE": {
					return 2;
				}
				case "PROCESSAMENTO": {
					return 3;
				}
				default: {
					return 0;
				}
			}
		}
		
		private function salvarAlteracao(e:MouseEvent):void {
			if (dadosValidos()) {
				var metodo:String = "alterarSevidorDDA";
				
				var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_SERVIDOR_DDA, metodo);
				servico.addEventListener(ResultEvent.RESULT, resultadoAlterarServidor);
				servico.mensagemEspera = "Gravando alterações...";
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados["vo"] = montarServidorDDA();
				
				
				servico.alterarSevidorDDA(dto);
			}
		}
		
		private function dadosValidos():Boolean {
			if (!cbPerfil.selectedItem) {
				MensagensComum.exibirMensagemAlerta("O campo Perfil é obrigatório.", cbPerfil);
				return false;
			}
			return true; 
		}
		
		private function montarServidorDDA():ServidorDDAVO {
			_servidorDDA.descPerfil = cbPerfil.selectedItem.label;
			_servidorDDA.bolAtivo = cbStatus.selectedItem.data ? true : false;
			return _servidorDDA;
		}
		
		private function resultadoAlterarServidor(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(MensagensComum.formatar(Mensagens.MSG057, "Servidor"), fechar);
		}
		
		private function limpar(e:MouseEvent):void {
			carregarDados();
		}
		
		private function fechar(e:Event):void {
			fecharJanela();
		}
	}
	
}