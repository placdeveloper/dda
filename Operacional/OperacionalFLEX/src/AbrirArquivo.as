package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.FileReference;
	import flash.net.URLRequest;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.AbrirArquivoRetornoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.monitoracaocip.AbrirArquivoView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.AbrirArquivoRetornoDTO", AbrirArquivoRetornoDTO);

	public class AbrirArquivo extends AbrirArquivoView
	{
		
		private var urlRequeste:URLRequest = new URLRequest();
		private var fileReference:FileReference = new FileReference();
		
		public function AbrirArquivo() {
			super();
		}
		
		protected override function init(event:FlexEvent):void{
		
			super.init(event);
			//configureListeners(fileReference);
			btnDescriptografar.addEventListener(MouseEvent.CLICK, efetuaRequisicao);
			btnFechar.addEventListener(MouseEvent.CLICK,fechar);
			btnDescriptografar.enabled = false;
		}
		
		
		private function efetuaRequisicao(evt:Event = null):void {
			var metodo:String = "abrirArquivoCIP";
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["listaArquivos"] = tiNomeArquivo.text;
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_PROCESSADOR_ARQUIVO_CIP, metodo);
			servico.addEventListener(ResultEvent.RESULT, concluirTarefaDeAbertura);
			servico.mensagemEspera = "Descriptografando arquivos...";
			servico.abrirArquivoCIP(dto);
		}
		
		private function fechar(event:Event):void{
			super.fecharJanela();
		}
		
		
		private function concluirTarefaDeAbertura(resultEvent:ResultEvent):void{
			tiNomeArquivo.text = "";
			var abrirArquivoRetorno:AbrirArquivoRetornoDTO = resultEvent.result.dados.retorno as AbrirArquivoRetornoDTO;
			
			if(abrirArquivoRetorno.retorno){
				MensagensComum.exibirMensagemSucesso("Arquivos descriptografados com sucesso.");
				
			}else{
				MensagensComum.exibirMensagemErro("Erro ao descriptografar o arquivo " + abrirArquivoRetorno.nomeArquivo, btnDescriptografar);
			}
			
			btnDescriptografar.enabled = false;
			
		}
			
	}
}