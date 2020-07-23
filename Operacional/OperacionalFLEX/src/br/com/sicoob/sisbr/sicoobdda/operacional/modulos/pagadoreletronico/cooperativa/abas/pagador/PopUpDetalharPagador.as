package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.pagadoreletronico.cooperativa.abas.pagador
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.ArquivoRecebidoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.PagadorDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
		
	registerClassAlias("RegistroVO", RegistroVO);
	public class PopUpDetalharPagador extends PopUpDetalharPagadorView
	{
		public static const DETALHAMENTO_DO_REGISTRO:String = "Detalhamento Registro";
		public static const ALTERAR_SITUACAO_DO_REGISTRO:String = "Alterar Registro";
		
		private var _numCpfCnpjPagador:String;
		private var _arquivoRecebidoDto:ArquivoRecebidoDTO;

		private var _btnFechar:Botao;
		private var _btnDescriptografar:Botao = new Botao();
		private var _btnGravarDetalhes:Botao = new Botao();
		private var _btnProcessar:Botao = new Botao();		
		
		private var _msgSucesso:Alerta;
		private var _msgAlerta:Alerta;
		
		private static const OPT_TODOS:Number = 1;
		private static const OPT_PROCESSADOS:Number = 1;
		private static const OPT_NAO_PROCESSADOS:Number =0;
		
		private var _funcaoRetorno:Function;
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpDetalharPagador(numCpfCnpjPagador:String, funcaoRetorno:Function) {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this._numCpfCnpjPagador = numCpfCnpjPagador;
			this._funcaoRetorno = funcaoRetorno;
		}
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected function init(event:FlexEvent):void {
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btnImprimir.addEventListener(MouseEvent.CLICK, relatorioDetalhadoPagador);
			iniciarBotoes();
			carregarCampos();
		}
		
		//--------------------------------------------------------------------------
		//  Inicializar botões.
		//--------------------------------------------------------------------------
		private function iniciarBotoes():void {
			this.btnImprimir.setStyle("icon", ConstantesComum.icoImprimir);
			this.btnFechar.setStyle('icon', ConstantesComum.icoFechar);
		}
		
		//--------------------------------------------------------------------------
		//  Carregar dados dos campos.
		//--------------------------------------------------------------------------
		private function carregarCampos():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.numCpfCnpj = this._numCpfCnpjPagador;
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_PAGADOR_ELETRONICO, "obterDetalharPagador");
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterDetalharPagador(dto);
		}
		
		private function retornoConsulta(resultEvent:ResultEvent):void {
			var pagadorDto:PagadorDTO = resultEvent.result.dados.pagador as PagadorDTO;
			if(pagadorDto){
				txtNomePagador.text = pagadorDto.nomePessoa;
				txtSituaçãoCip.text = pagadorDto.bolPagadorEletronico ? "Ativo":"Inativo";
				txtDataAdesao.text = FormataData.formataData(pagadorDto.dataAdesaoDDA.data,"dd/MM/yyyy");
				txtQtdIfAdesao.text =  pagadorDto.qtdAdesaoDDA.toString();
				txtPagadorEletronicoSicoob.text = descTxtNomeIfAderente(pagadorDto);
				txtCpfCnpj.text = FormatUtil.formataCPFCNPJ(pagadorDto.numCpfCnpj);
				if(pagadorDto.listaAgencia){
					this.listaContaCorrente.dataProvider =  pagadorDto.listaAgencia;
				}
					
				this.listaAgregados.dataProvider = pagadorDto.listaPagadorAgregado == null ? null : pagadorDto.listaPagadorAgregado;
			}
			
		}
		
		private function descTxtNomeIfAderente(pagadorDto:PagadorDTO):String{
			if(pagadorDto.qtdAdesaoDDA == 1 && pagadorDto.bolPagadorEletronicoSicoob){
				return "Sicoob";
			}else if(pagadorDto.qtdAdesaoDDA > 1 && pagadorDto.bolPagadorEletronicoSicoob){
				return "Sicoob e outros bancos";
			} else if(pagadorDto.qtdAdesaoDDA >= 1 && pagadorDto.bolPagadorEletronico){
				return "Outros bancos";
			}else{
				return "";
			}
		}
		
		//--------------------------------------------------------------------------
		//  Abre a Janela de Edição.
		//--------------------------------------------------------------------------
		private function relatorioDetalhadoPagador(e:Event):void {
			var textoTratado:String = this.txtCpfCnpj.text.replace(/[^0-9]/g,'');
			var numCpfCnpj:String = StringUtils.trim(textoTratado) == "" ? null : textoTratado;
			
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_DETALHADO_PAGADOR_ELETRONICODDA, numCpfCnpj, null, PreImpressao.FORMATO_PDF);
		}
		
		//--------------------------------------------------------------------------
		//  Instanci DTO de Consulta para Paginação.
		//--------------------------------------------------------------------------
		private function instanciarDtoConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		//--------------------------------------------------------------------------
		//  Fecha a janela.
		//--------------------------------------------------------------------------
		private function fechar(obj:Object = null):void {
			super.fecharJanela();
		}
	}
}