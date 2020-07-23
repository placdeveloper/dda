package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.boleto
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.componentes.relatorio.RelatorioDDA;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesRelatorios;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.NumeroUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.BoletoDDAVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoDescontoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoJurosVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.TipoMultaVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	
	registerClassAlias("RegistroVO", RegistroVO);
	public class DetalhamentoBoleto extends DetalhamentoBoletoView 
	{
		private var numCodigoBarra:String;
		private var codSituacaoBoleto:Number;
		private var relUtil:RelatorioUtil = RelatorioUtil.create();
		private var boletoDDAVO:BoletoDDAVO;

		public function DetalhamentoBoleto(modo:int = MODO_VISUALIZACAO,  numCodigoBarra:String = null, codSituacaoBoleto:Number = 0)
		{
			super();
			
			this.numCodigoBarra = numCodigoBarra;
			this.codSituacaoBoleto = codSituacaoBoleto;
			this.modo = modo;
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			addEventListener(EVENTO_FECHAR_FORMULARIO_CADASTRO, fechar);
			btnImprimir.addEventListener(MouseEvent.CLICK, imprimirRelatorio);
		}
		
		private function imprimirRelatorio(event:MouseEvent):void{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.numCodigoBarra = this.numCodigoBarra;
			dto.dados.codSituacaoBoleto =  this.codSituacaoBoleto;
			dto.dados.boletoDDAVO =  this.boletoDDAVO;
			
			RelatorioDDA.getInstance().gerarRelatorio(ConstantesRelatorios.RELATORIO_DETALHE_BOLETO, null, null, PreImpressao.FORMATO_PDF, dto);
		}
		
		protected override function preencherCampos():void {
			if(this.modo == MODO_VISUALIZACAO){
				obterBoletoDDA();
				btnImprimir.enabled = true;
				btnImprimir.setStyle("icon", ConstantesComum.icoImprimir);
			}
		}
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(evt:Event):void {
			fecharJanela();
		}
		
		/**
		 * Buscar o BoletoDDA pelo codigoBarra
		 */
		private function obterBoletoDDA():void{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			dto.dados.numCodigoBarra = this.numCodigoBarra;
			dto.dados.codSituacaoBoleto = this.codSituacaoBoleto;
			
			var metodo:String = "obterBoletoDDA";
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_CONSULTA_BOLETO, metodo);
			servico.addEventListener(ResultEvent.RESULT, resultadoObterBoletoDDA);
			
			servico.obterBoletoDDA(dto);
		}
		
		private function resultadoObterBoletoDDA(evt:ResultEvent):void{
			boletoDDAVO = evt.result.dados.boletoDDAVO as BoletoDDAVO;
			
			if (!boletoDDAVO) {
				MensagensComum.exibirMensagemErro("Boleto não encontrado");
				fecharJanela();
				return;
			}
			
			getabaDadosBasicoBoleto(boletoDDAVO);
			getabaDadosComplementaresBoleto(boletoDDAVO);
			getAbaDadosBeneficiarioSacador(boletoDDAVO);
			
			this.abaBaixas.gridBaixaOperacional.dataProvider = boletoDDAVO.listaBoletoDDABaixaOper;
			this.abaBaixas.gridBaixaEfetiva.dataProvider = boletoDDAVO.listaBoletoDDABaixaEfet;
		}
		
		private function getabaDadosBasicoBoleto (boletoDDAVO:BoletoDDAVO):void{
			this.abaDadosBasicoBoleto.txtBanco.text = boletoDDAVO.getDescricaoBanco;
			this.abaDadosBasicoBoleto.txtBanco.toolTip = boletoDDAVO.getDescricaoBanco;
			this.abaDadosBasicoBoleto.nrCarteira.text = boletoDDAVO.getDescricaoCarteira;
			this.abaDadosBasicoBoleto.nrCodigoMoeda.text = boletoDDAVO.codMoeda;
			this.abaDadosBasicoBoleto.txtSeuNumero.text = boletoDDAVO.numDocumento;
			if(boletoDDAVO.getDescricaoSituacaoBoleto != null && boletoDDAVO.getDescricaoSituacaoBoleto.length > 32) {
				this.abaDadosBasicoBoleto.txtSituacaoBoleto.text = retornarStringParte01(boletoDDAVO.getDescricaoSituacaoBoleto, 32);	
				this.abaDadosBasicoBoleto.txtSituacaoBoleto2.text = retornarStringParte02(boletoDDAVO.getDescricaoSituacaoBoleto, 32);	
				this.abaDadosBasicoBoleto.txtSituacaoBoleto2.toolTip = boletoDDAVO.getDescricaoSituacaoBoleto;	
			} else {
				this.abaDadosBasicoBoleto.txtSituacaoBoleto.text = boletoDDAVO.getDescricaoSituacaoBoleto;				
			}
			this.abaDadosBasicoBoleto.txtSituacaoBoleto.toolTip = boletoDDAVO.getDescricaoSituacaoBoleto;
			this.abaDadosBasicoBoleto.txtNossoNumero.text = boletoDDAVO.numNossoNumero;
			this.abaDadosBasicoBoleto.txtCodigoBarras.text = boletoDDAVO.numCodigoBarra;
			this.abaDadosBasicoBoleto.txtLinhaDigitavel.text = boletoDDAVO.numLinhaDigitavel;
			this.abaDadosBasicoBoleto.dtVencimento.text = FormataData.formataData(new Date(boletoDDAVO.dataVencimento.data));
			this.abaDadosBasicoBoleto.vlrBoleto.text = NumeroUtil.formatarNumeroDecimalParaMoeda(boletoDDAVO.valorBoleto);
			this.abaDadosBasicoBoleto.dtEmissao.text = boletoDDAVO.dataEmissao == null ? null :FormataData.formataData(new Date(boletoDDAVO.dataEmissao.data));
			if(boletoDDAVO.getDescricaoSitBoletoPagamento != null && boletoDDAVO.getDescricaoSitBoletoPagamento.length > 50) {
				this.abaDadosBasicoBoleto.txtSituacaoBoletoPagamento.text = retornarStringParte01(boletoDDAVO.getDescricaoSitBoletoPagamento, 50);
				this.abaDadosBasicoBoleto.txtSituacaoBoletoPagamento2.text = retornarStringParte02(boletoDDAVO.getDescricaoSitBoletoPagamento, 50);
				this.abaDadosBasicoBoleto.txtSituacaoBoletoPagamento2.toolTip = boletoDDAVO.getDescricaoSitBoletoPagamento;
			} else {
				this.abaDadosBasicoBoleto.txtSituacaoBoletoPagamento.text = boletoDDAVO.getDescricaoSitBoletoPagamento;
			}
			this.abaDadosBasicoBoleto.txtSituacaoBoletoPagamento.toolTip = boletoDDAVO.getDescricaoSitBoletoPagamento;
			this.abaDadosBasicoBoleto.dtLimitePgto.text = FormataData.formataData(new Date(boletoDDAVO.dataLimitePagamento.data));
			this.abaDadosBasicoBoleto.txtDiasProtesto.text = NumeroUtil.converterNumberParaString(boletoDDAVO.numDiasProtesto);
			this.abaDadosBasicoBoleto.vlrAbatimento.text = NumeroUtil.formatarNumeroDecimalParaMoeda(boletoDDAVO.valorAbatimento);
			this.abaDadosBasicoBoleto.indrValorMinimo.text = boletoDDAVO.codIndicadorValorMinimo  == "P" ? ConstantesComum.NOME_PERCENTUAL : boletoDDAVO.codIndicadorValorMinimo  == "V" ? ConstantesComum.NOME_VALOR : "";
			this.abaDadosBasicoBoleto.vlrMinimo.text = boletoDDAVO.codIndicadorValorMinimo  == "P" ? NumeroUtil.converterNumberParaString(boletoDDAVO.valorMinimo) + "%" : boletoDDAVO.codIndicadorValorMinimo  == "V" ? NumeroUtil.formatarNumeroDecimalParaMoeda(boletoDDAVO.valorMinimo) : "";
			this.abaDadosBasicoBoleto.indrValorMaximo.text = boletoDDAVO.codIndicadorValorMaximo == "P" ? ConstantesComum.NOME_PERCENTUAL : boletoDDAVO.codIndicadorValorMaximo == "V" ? ConstantesComum.NOME_VALOR : "";
			this.abaDadosBasicoBoleto.vlrMaximo.text = boletoDDAVO.codIndicadorValorMaximo == "P" ?  NumeroUtil.converterNumberParaString(boletoDDAVO.valorMaximo) + "%" : boletoDDAVO.codIndicadorValorMaximo == "V" ? NumeroUtil.formatarNumeroDecimalParaMoeda(boletoDDAVO.valorMaximo) : "";
			this.abaDadosBasicoBoleto.nrParcela.text = NumeroUtil.converterNumberParaString(boletoDDAVO.numParcela);
			this.abaDadosBasicoBoleto.qtdTotalParcelas.text = NumeroUtil.converterNumberParaString(boletoDDAVO.qtdTotalParcela);
			this.abaDadosBasicoBoleto.bolTitNegociado.text = boletoDDAVO.bolTituloNegociado == 1 ? ConstantesComum.SIM :  ConstantesComum.NAO;
			this.abaDadosBasicoBoleto.bolBloqueioPgto.text = boletoDDAVO.bolBloqueioPagamento == 1 ? ConstantesComum.SIM :  ConstantesComum.NAO;
			this.abaDadosBasicoBoleto.bolPgtoParcial.text = boletoDDAVO.bolPagamentoParcial == 1 ? ConstantesComum.SIM : ConstantesComum.NAO;
			this.abaDadosBasicoBoleto.qtdPgtoParcial.text = NumeroUtil.converterNumberParaString(boletoDDAVO.qtdPagamentoParcial);
			if(boletoDDAVO.getDescricaoModeloCalculo != null && boletoDDAVO.getDescricaoModeloCalculo.length > 50) {
				this.abaDadosBasicoBoleto.tpModeloCal.text = retornarStringParte01(boletoDDAVO.getDescricaoModeloCalculo, 50);
				this.abaDadosBasicoBoleto.tpModeloCal2.text = retornarStringParte02(boletoDDAVO.getDescricaoModeloCalculo, 50);		
				this.abaDadosBasicoBoleto.tpModeloCal2.toolTip = boletoDDAVO.getDescricaoModeloCalculo;			
			} else {
				this.abaDadosBasicoBoleto.tpModeloCal.text = boletoDDAVO.getDescricaoModeloCalculo;
			}
			this.abaDadosBasicoBoleto.tpModeloCal.toolTip = boletoDDAVO.getDescricaoModeloCalculo;
			this.abaDadosBasicoBoleto.txtAutorizaValorDivergente.text = boletoDDAVO.getDescricaoAutorizacaoValorDivergente;
			this.abaDadosBasicoBoleto.txtAutorizaValorDivergente.toolTip = boletoDDAVO.getDescricaoAutorizacaoValorDivergente;
			this.abaDadosBasicoBoleto.txtEspecieDocumento.text =  boletoDDAVO.getDescricaoEspecie;
			this.abaDadosBasicoBoleto.codTipoPgto.text = boletoDDAVO.getDescricaoTipoPagamento;
			this.abaDadosBasicoBoleto.numRefAtualBoleto.text =  boletoDDAVO.numRefAtualCadBoletoStr;
			this.abaDadosBasicoBoleto.numSeqAtualBoleto.text = NumeroUtil.converterNumberParaString(boletoDDAVO.numSeqAtualCadBoleto);
			this.abaDadosBasicoBoleto.numIdentBoleto.text = boletoDDAVO.numIdentificadorBoletoCipStr;
		}
		
		private function getAbaDadosBeneficiarioSacador(boletoDDAVO:BoletoDDAVO):void{
			this.abaBeneficiarioSacador.txtTpPessoaBeneficiario.text = boletoDDAVO.codTipoPessoaBeneficiario == "F" ? ConstantesComum.PESSOA_FISICA : ConstantesComum.PESSOA_JURIDICA;
			this.abaBeneficiarioSacador.txtCnpfCnpjBeneficiario.text = FormatUtil.formataCPFCNPJ(boletoDDAVO.numCpfCnpjBeneficiario);
			this.abaBeneficiarioSacador.txtNomeBeneficiario.text = boletoDDAVO.nomeBeneficiario;
			this.abaBeneficiarioSacador.txtNomeFantasiaBeneficiario.text =  boletoDDAVO.nomeFantasiaBeneficiario;
			this.abaBeneficiarioSacador.txtLogradouroBeneficiario.text = boletoDDAVO.descLogradouroBeneficiario;
			this.abaBeneficiarioSacador.txtCidadeBeneficiario.text = boletoDDAVO.descCidadeBeneficiario;
			this.abaBeneficiarioSacador.txtUfBeneficiario.text = boletoDDAVO.siglaUfBeneficiario;
			this.abaBeneficiarioSacador.txtCepBeneficiario.text = NumeroUtil.formatarCEP(boletoDDAVO.numCepBeneficiario);
			
			this.abaBeneficiarioSacador.txtTpPessoaPagador.text = boletoDDAVO.codTipoPessoaPagador == "F" ? ConstantesComum.PESSOA_FISICA : ConstantesComum.PESSOA_JURIDICA;
			this.abaBeneficiarioSacador.txtCnpfCnpjPagador.text = FormatUtil.formataCPFCNPJ(boletoDDAVO.numCpfCnpjPagador);
			this.abaBeneficiarioSacador.txtNomePagador.text = boletoDDAVO.nomePagador;
			this.abaBeneficiarioSacador.txtNomeFantasiaPagador.text =  boletoDDAVO.nomeFantasiaPagador;
			this.abaBeneficiarioSacador.txtLogradouroPagador.text = boletoDDAVO.descLogradouroPagador;
			this.abaBeneficiarioSacador.txtCidadePagador.text = boletoDDAVO.descCidadePagador;
			this.abaBeneficiarioSacador.txtUfPagador.text = boletoDDAVO.siglaUfPagador;
			this.abaBeneficiarioSacador.txtCepPagador.text = NumeroUtil.formatarCEP(boletoDDAVO.numCepPagador);
			
			this.abaBeneficiarioSacador.txtTpPessoaSacador.text = boletoDDAVO.getDescricaSacadorAvalista;
			this.abaBeneficiarioSacador.txtCnpfCnpjSacador.text = boletoDDAVO.numCpfCnpjAvalista != null && (boletoDDAVO.numCpfCnpjAvalista.length == 11 || boletoDDAVO.numCpfCnpjAvalista.length == 14) ?FormatUtil.formataCPFCNPJ(boletoDDAVO.numCpfCnpjAvalista) : null;
			this.abaBeneficiarioSacador.txtNomeSacador.text = boletoDDAVO.nomeAvalista;
		}
		
		private function getabaDadosComplementaresBoleto (boletoDDAVO:BoletoDDAVO):void{
			populaDesconto(boletoDDAVO, 0);
			populaDesconto(boletoDDAVO, 1);
			populaDesconto(boletoDDAVO, 2);
			
			this.abaDadosComplementaresBoleto.tpJuros.text = boletoDDAVO.getDescricaoJuros;
			this.abaDadosComplementaresBoleto.tpJuros.toolTip = boletoDDAVO.getDescricaoJuros;
			this.abaDadosComplementaresBoleto.dtJuros.text =  boletoDDAVO.tipoJuros.id == TipoJurosVO.ISENTO ? null : FormataData.formataData(new Date(boletoDDAVO.dataJuros.data));
			this.abaDadosComplementaresBoleto.vlrJuros.text = getValorJuros(boletoDDAVO.tipoJuros.id, boletoDDAVO.valorPercentualJuros);
	
			this.abaDadosComplementaresBoleto.tpMulta.text = boletoDDAVO.getDescricaoMulta;
			this.abaDadosComplementaresBoleto.dtMulta.text = boletoDDAVO.tipoMulta.id == TipoMultaVO.ISENTO ? null : FormataData.formataData(new Date(boletoDDAVO.dataMulta.data));
			this.abaDadosComplementaresBoleto.vlrMulta.text = getValorMulta(boletoDDAVO.tipoMulta.id, boletoDDAVO.valorPercentualMulta);
		
			this.abaDadosComplementaresBoleto.bolAceite.text = boletoDDAVO.bolAceite == 1 ? ConstantesComum.SIM : ConstantesComum.NAO;
			if(boletoDDAVO.dataHoraSituacaoAceite != null){
				this.abaDadosComplementaresBoleto.dtHoraSituacaoAceite.text =FormataData.formataDataHora(new Date(boletoDDAVO.dataHoraSituacaoAceite.data));
			}
			
			this.abaDadosComplementaresBoleto.gridTerceiroAutorizado.dataProvider = boletoDDAVO.listaBoletoDDATerceiroAutorizado;
			
			this.abaDadosComplementaresBoleto.gridPagadorAgregado.dataProvider = boletoDDAVO.listaPagadorDDAAgregado;
		
			this.abaDadosComplementaresBoleto.txtMensagem.text = boletoDDAVO.descTextoInformativoSemEspaco;
		}
		
		private function populaDesconto (boletoDDAVO:BoletoDDAVO, posicao:Number):void{
			if(posicao == 0 && boletoDDAVO.tipoDesconto1 != null){
				this.abaDadosComplementaresBoleto.tpDesconto1.text =  boletoDDAVO.tipoDesconto1.codTipoDesconto == "0" ? ConstantesComum.TIPO_DESCONTO_ISENTO : boletoDDAVO.descricaoDesconto1;
				this.abaDadosComplementaresBoleto.tpDesconto1.toolTip =  boletoDDAVO.descricaoDesconto1;
				this.abaDadosComplementaresBoleto.dtDesconto1.text = boletoDDAVO.dataDesconto1 == null && boletoDDAVO.tipoDesconto1.codTipoDesconto == "0"  ? "" : boletoDDAVO.dataDesconto1 == null ? FormataData.formataData(new Date(boletoDDAVO.dataVencimento.data)) : FormataData.formataData(new Date(boletoDDAVO.dataDesconto1.data));
				this.abaDadosComplementaresBoleto.vlrDesconto1.text = getValorDesconto1(boletoDDAVO);
			}else if(posicao == 1 && boletoDDAVO.tipoDesconto2  != null){
				this.abaDadosComplementaresBoleto.tpDesconto2.text =  boletoDDAVO.tipoDesconto2.codTipoDesconto == "0" ? ConstantesComum.TIPO_DESCONTO_ISENTO : boletoDDAVO.descricaoDesconto2;
				this.abaDadosComplementaresBoleto.tpDesconto2.toolTip =  boletoDDAVO.descricaoDesconto2;
				this.abaDadosComplementaresBoleto.dtDesconto2.text =  boletoDDAVO.dataDesconto2 == null && boletoDDAVO.tipoDesconto2.codTipoDesconto == "0"  ? "" : boletoDDAVO.dataDesconto2 == null ? FormataData.formataData(new Date(boletoDDAVO.dataVencimento.data)) : FormataData.formataData(new Date(boletoDDAVO.dataDesconto2.data));
				this.abaDadosComplementaresBoleto.vlrDesconto2.text = getValorDesconto2(boletoDDAVO);
			}else if(posicao == 2 && boletoDDAVO.tipoDesconto3 != null){
				this.abaDadosComplementaresBoleto.tpDesconto3.text =  boletoDDAVO.tipoDesconto3.codTipoDesconto == "0" ? ConstantesComum.TIPO_DESCONTO_ISENTO : boletoDDAVO.descricaoDesconto3;
				this.abaDadosComplementaresBoleto.tpDesconto3.toolTip =  boletoDDAVO.descricaoDesconto3;
				this.abaDadosComplementaresBoleto.dtDesconto3.text =  boletoDDAVO.dataDesconto3 == null && boletoDDAVO.tipoDesconto3.codTipoDesconto == "0"  ? "" : boletoDDAVO.dataDesconto3 == null ? FormataData.formataData(new Date(boletoDDAVO.dataVencimento.data)) : FormataData.formataData(new Date(boletoDDAVO.dataDesconto3.data));
				this.abaDadosComplementaresBoleto.vlrDesconto3.text = getValorDesconto3(boletoDDAVO);
			}else if(boletoDDAVO.tipoDesconto1 == null && boletoDDAVO.tipoDesconto2 == null && boletoDDAVO.tipoDesconto3 == null){
				this.abaDadosComplementaresBoleto.tpDesconto1.text = ConstantesComum.TIPO_DESCONTO_ISENTO;
			}else if(this.abaDadosComplementaresBoleto.tpDesconto2.text == ""){
				this.abaDadosComplementaresBoleto.tpDesconto2.text = ConstantesComum.TIPO_DESCONTO_ISENTO;
			}else if (this.abaDadosComplementaresBoleto.tpDesconto3.text == ""){
				this.abaDadosComplementaresBoleto.tpDesconto3.text = ConstantesComum.TIPO_DESCONTO_ISENTO;
			}
		}
		
		private function getValorDesconto1 (boletoDDAVO:BoletoDDAVO):String{
			if(boletoDDAVO.tipoDesconto1.codTipoDesconto == TipoDescontoVO.VALOR_FIXO_ATE_A_DATA_INFORMADA
				|| boletoDDAVO.tipoDesconto1.codTipoDesconto == TipoDescontoVO.VALOR_POR_ANTECIPACAO_DIA_CORRIDO
				|| boletoDDAVO.tipoDesconto1.codTipoDesconto == TipoDescontoVO.VALOR_POR_ANTECIPACAO_DIA_UTIL
				|| boletoDDAVO.tipoDesconto1.codTipoDesconto == TipoDescontoVO.ISENTO){
				return boletoDDAVO.valorPercentualDesconto1 == 0 ? "" : NumeroUtil.formatarNumeroDecimalParaMoeda( boletoDDAVO.valorPercentualDesconto1);
			}else{
				return NumeroUtil.converterNumberParaString(boletoDDAVO.valorPercentualDesconto1) + "%";
			}
		}
		
		private function getValorDesconto2 (boletoDDAVO:BoletoDDAVO):String{
			if(boletoDDAVO.tipoDesconto2.codTipoDesconto == TipoDescontoVO.VALOR_FIXO_ATE_A_DATA_INFORMADA
				|| boletoDDAVO.tipoDesconto2.codTipoDesconto == TipoDescontoVO.VALOR_POR_ANTECIPACAO_DIA_CORRIDO
				|| boletoDDAVO.tipoDesconto2.codTipoDesconto == TipoDescontoVO.VALOR_POR_ANTECIPACAO_DIA_UTIL
				|| boletoDDAVO.tipoDesconto2.codTipoDesconto == TipoDescontoVO.ISENTO){
				return boletoDDAVO.valorPercentualDesconto2 == 0 ? "" : NumeroUtil.formatarNumeroDecimalParaMoeda( boletoDDAVO.valorPercentualDesconto2);
			}else{
				return NumeroUtil.converterNumberParaString(boletoDDAVO.valorPercentualDesconto2) + "%";
			}
		}
		
		private function getValorDesconto3 (boletoDDAVO:BoletoDDAVO):String{
			if(boletoDDAVO.tipoDesconto3.codTipoDesconto == TipoDescontoVO.VALOR_FIXO_ATE_A_DATA_INFORMADA
				|| boletoDDAVO.tipoDesconto3.codTipoDesconto == TipoDescontoVO.VALOR_POR_ANTECIPACAO_DIA_CORRIDO
				|| boletoDDAVO.tipoDesconto3.codTipoDesconto == TipoDescontoVO.VALOR_POR_ANTECIPACAO_DIA_UTIL
				|| boletoDDAVO.tipoDesconto3.codTipoDesconto == TipoDescontoVO.ISENTO){
				return boletoDDAVO.valorPercentualDesconto3 == 0 ? "" : NumeroUtil.formatarNumeroDecimalParaMoeda( boletoDDAVO.valorPercentualDesconto3);
			}else{
				return NumeroUtil.converterNumberParaString(boletoDDAVO.valorPercentualDesconto3) + "%";
			}
		}
		
		private function retornarStringParte01(param:String, size:Number):String {
			var count:Number = param.length;
			var position:Number = 0;
			for (var i:int = size; i < param.length; i++) {
				if(param.substring(i, i+1) == " ") {
					position = i+1;
					break;
				}
			}
			return param.substring(0, position);
		}
		
		private function retornarStringParte02(param:String, size:Number):String {
			return param.substring(retornarStringParte01(param, size).length, param.length);
		}
		
		private function getValorJuros (codTipoJuros:Number,valorPercentualJuros:Number ):String{
			if(codTipoJuros == TipoJurosVO.VALOR_DIAS_CORRIDOS
				|| codTipoJuros == TipoJurosVO.ISENTO
				|| codTipoJuros == TipoJurosVO.VALOR_DIA_UTIL){
				return NumeroUtil.formatarNumeroDecimalParaMoeda(valorPercentualJuros);
			}else{
				return NumeroUtil.converterNumberParaString(valorPercentualJuros) + "%";
			}
		}
		
		private function getValorMulta (codTipoMulta:Number,valorPercentualMulta:Number ):String{
			if(codTipoMulta == TipoMultaVO.VALOR_FIXO
				|| codTipoMulta == TipoMultaVO.ISENTO){
				return NumeroUtil.formatarNumeroDecimalParaMoeda(valorPercentualMulta);
			}else{
				return NumeroUtil.converterNumberParaString(valorPercentualMulta) + "%";
			}
		}
		
	}
}