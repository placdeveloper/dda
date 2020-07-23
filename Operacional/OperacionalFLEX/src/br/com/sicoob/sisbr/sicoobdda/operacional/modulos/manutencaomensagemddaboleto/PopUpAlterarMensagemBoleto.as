package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.manutencaomensagemddaboleto
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MensagemDDABoletoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MensagemDDABoletoDescontoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MensagemDDABoletoFiltroDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.MensagemDDABoletoGrupoCalculoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ConstantesComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.CpfCnpjUtils;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.Funcoes;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.NumeroUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.ObjectUtil;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	import br.com.sicoob.sisbr.sicoobdda.operacional.modulos.manutencaomensagemddaboleto.PopUpAlterarMensagemBoletoView;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Constantes;
	import br.com.sicoob.sisbr.sicoobdda.operacional.util.Mensagens;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class PopUpAlterarMensagemBoleto extends PopUpAlterarMensagemBoletoView
	{
		
		private var _pessoaFisica:int = 1;
		private var _pessoaJuridica:int = 2;

		private const _PERCENTUAL:String = "Percentual";

		private const _VALOR_MINIMO:String = "Valor Mínimo";
		
		private var _mensagemDDABoleto:MensagemDDABoletoDTO;
		private var _idLogReceArquivoDDA:Number;
		private var _situacaoAtual:String = new String();
		private var _atualizaPesquisa:Function;
		private var _listaSituacao:ArrayCollection;
		private var _idMensagemDDA:Number;
		private var _dtoMerge:MensagemDDABoletoDTO;
		private var _filtrosCombo:MensagemDDABoletoFiltroDTO;
		
		//--------------------------------------------------------------------------
		//  Construtor inclusão.
		//--------------------------------------------------------------------------
		public function PopUpAlterarMensagemBoleto(idMensagemDDA:Number, funcaoRetorno:Function, filtrosCombo:MensagemDDABoletoFiltroDTO) {
			super();
			this._idMensagemDDA = idMensagemDDA;
			this._atualizaPesquisa = funcaoRetorno;
			this._filtrosCombo = filtrosCombo;
		}
		
		
		//--------------------------------------------------------------------------
		//  Inicializa componentes.
		//--------------------------------------------------------------------------
		protected override function init(event:FlexEvent):void {
			super.init(event);
			this.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			this.btnFechar.setStyle("icon", ConstantesComum.icoFechar);
			this.btnConfirmar.addEventListener(MouseEvent.CLICK, alterarMensagemDDABoleto);
			this.btnCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			carregarCombos();
			carregarCampos();
		}
		//VOLTAR
		private function carregarCombos():void {
			this.txtIndValorMinimo.dataProvider = ConstantesComum.IND_VALOR_PERCENTUAL;
			this.txtIndValorMaximo.dataProvider = ConstantesComum.IND_VALOR_MAXIMO_PERCENTUAL;
			this.txtBolTitNegociado.dataProvider = ConstantesComum.SIM_NAO;
			this.txtBolBloqueioPgto.dataProvider = ConstantesComum.SIM_NAO;
			this.txtBolPgtoParcial.dataProvider = ConstantesComum.SIM_NAO;
			this.cmbAutorizacaoValorDivergente.dataProvider = _filtrosCombo.listaAutorizacaoValorDivergente;
			this.dadosDesconto1.cmbTipo.dataProvider = _filtrosCombo.listaTipoDesconto;			
			this.dadosDesconto2.cmbTipo.dataProvider = _filtrosCombo.listaTipoDesconto;		
			this.dadosDesconto3.cmbTipo.dataProvider = _filtrosCombo.listaTipoDesconto;
			this.dadosJuroMora.cmbTipo.dataProvider = _filtrosCombo.listaTipoJuros;
			this.dadosMulta.cmbTipo.dataProvider = _filtrosCombo.listaTipoMulta;
			this.cmbCodTipoPagto.dataProvider = _filtrosCombo.listaTipoModeloPagamento;
			this.cmbEspecieDoc.dataProvider = _filtrosCombo.listaEspecieDocumento;
			this.cmbTpModeloCalc.dataProvider = _filtrosCombo.listaTipoModeloCalculo;
			this.cmbIdCarteira.dataProvider = _filtrosCombo.listaIdCarteira;
			this.panelTipoPessoaAvalista.cbTipoPessoaAvalista.dataProvider = _filtrosCombo.listaTipoPessoaDDAAvalista;
		}

		//--------------------------------------------------------------------------
		//  Carregar dados dos campos.
		//--------------------------------------------------------------------------
		private function carregarCampos():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MANUTENCAO_MENSAGEMDDABOLETO, "obterMensagemDDABoletoPorId");
			servico.mensagemEspera = "Obtendo dados da Mensagem...";
			servico.mensagemErro = "Erro inesperado ao tentar obter dados da Mensagem...";
			servico.addEventListener(ResultEvent.RESULT, retornoConsulta);
			servico.obterMensagemDDABoletoPorId(_idMensagemDDA);
		}
		
		private function retornoConsulta(resultEvent:ResultEvent):void {
			_mensagemDDABoleto = resultEvent.result.dados.dto as MensagemDDABoletoDTO;
			carregarDadosDetalharMensagem();
		}
		
		private function retornarComboSimNao(obj:Object):String {
			if(obj!=null) {
				var item:String = obj.data;
				return item.toUpperCase() == "1" ? "S" : "N";
			} else {
				return null;				
			}
		}
		
		private function retornarDataObject(obj:Object):String {
			return (obj != null ? obj.data : null);
		}
		
		private function retornarLabelObject(obj:Object):String {
			return (obj != null ? obj.label : null);
		}
		
		private function montarDtoAlteracao():MensagemDDABoletoDTO {
			_dtoMerge = new MensagemDDABoletoDTO;
			_dtoMerge.idMensagem = _idMensagemDDA;
			//DADOS BENEFICIÁRIO
			_dtoMerge.codTipoPessoaBeneficiario = retornarDataObject(this.panelTipoPessoaBeneficiario.cbTipoPessoa.selectedItem);
			if(isPessoaFisica(_dtoMerge.codTipoPessoaBeneficiario)) {
				_dtoMerge.numCnpjCpfBeneficiario = this.panelTipoPessoaBeneficiario.txtCpf.text;
			} else {
				_dtoMerge.numCnpjCpfBeneficiario = this.panelTipoPessoaBeneficiario.txtCnpj.text;
			}
			_dtoMerge.nomeBeneficiario = this.dadosBeneficiario.txtNome.text;
			_dtoMerge.nomeFantasiaBeneficiario = this.dadosBeneficiario.txtNomeFantasia.text;
			_dtoMerge.descLogradouroBeneficiario = this.dadosBeneficiario.txtLogradouro.text;
			_dtoMerge.descCidadeBeneficiario = this.dadosBeneficiario.txtCidade.text;
			_dtoMerge.ufBeneficiario = retornarLabelObject(this.dadosBeneficiario.cmbUf.selectedItem);
			_dtoMerge.numCepBeneficiario = this.dadosBeneficiario.txtCep.text;
			//DADOS BENEFICIÁRIO FINAL	
			_dtoMerge.codTipoPessoaBeneficiarioFinal = retornarDataObject(this.panelTipoPessoaBeneficiarioFinal.cbTipoPessoa.selectedItem);
			if(isPessoaFisica(_dtoMerge.codTipoPessoaBeneficiarioFinal)) {
				_dtoMerge.numCnpjCpfBeneficiarioFinal = this.panelTipoPessoaBeneficiarioFinal.txtCpf.text;
			} else {
				_dtoMerge.numCnpjCpfBeneficiarioFinal = this.panelTipoPessoaBeneficiarioFinal.txtCnpj.text;
			}
			_dtoMerge.nomeBeneficiarioFinal = this.dadosBeneficiarioFinal.txtNome.text;
			_dtoMerge.nomeFantasiaBeneficiarioFinal = this.dadosBeneficiarioFinal.txtNomeFantasia.text;
			//DADOS DO PAGADOR
			_dtoMerge.codTipoPessoaPagador = retornarDataObject(this.panelTipoPessoaPagador.cbTipoPessoa.selectedItem);
			if(isPessoaFisica(_dtoMerge.codTipoPessoaPagador)) {
				_dtoMerge.numCnpjCpfPagador = this.panelTipoPessoaPagador.txtCpf.text;
			} else {
				_dtoMerge.numCnpjCpfPagador = this.panelTipoPessoaPagador.txtCnpj.text;
			}
			_dtoMerge.nomePagador = this.dadosPagador.txtNome.text;
			_dtoMerge.nomeFantasiaPagador = this.dadosPagador.txtNomeFantasia.text;
			_dtoMerge.descLogradouroPagador = this.dadosPagador.txtLogradouro.text;
			_dtoMerge.desCidadePagador = this.dadosPagador.txtCidade.text;
			_dtoMerge.ufPagador = retornarLabelObject(this.dadosPagador.cmbUf.selectedItem);
			_dtoMerge.numCepPagador = this.dadosPagador.txtCep.text;
			//DADOS SACADOR AVALISTA
			_dtoMerge.codTipoPessoaAvalista = (this.panelTipoPessoaAvalista.cbTipoPessoaAvalista.selectedItem != null ? this.panelTipoPessoaAvalista.cbTipoPessoaAvalista.selectedItem.codTipoPessoaDDAAvalista : null);
			_dtoMerge.numCnpjCpfAvalista = this.panelTipoPessoaAvalista.retornoValor();
			_dtoMerge.nomeAvalista = this.dadosAvalista.txtNome.text;
			//DADOS DO BOLETO
			_dtoMerge.idCarteira = (cmbIdCarteira.selectedItem != null ? cmbIdCarteira.selectedItem.idCarteiraCip : null);
			_dtoMerge.codMoeda = this.txtCodMoeda.text;
			_dtoMerge.idOrgaoMoeda = this.txtOrgaoMoeda.valor;
			_dtoMerge.numNossoNumero = this.txtNossoNumero.text;
			_dtoMerge.numCodigoDeBarras = this.txtCodBarras.text;
			_dtoMerge.numLinhaDigitavel = this.txtLinhaDigitavel.text;
			_dtoMerge.dataVencimento = DateTimeBase.getDateTime(this.txtDataVencimento.selectedDate);
			_dtoMerge.valorDoBoleto = NumeroUtil.converterMoedaParaNumeroDecimal(this.txtValorBoleto.text);
			_dtoMerge.dataEmissao = DateTimeBase.getDateTime(this.txtDataEmissao.selectedDate);
			_dtoMerge.numDocumento = this.txtNumDocumento.text;
			_dtoMerge.dataLimitePgto = DateTimeBase.getDateTime(this.txtDataLimitePgto.selectedDate);
			_dtoMerge.diasDeProtesto = this.txtDiasProtesto.valor;
			_dtoMerge.valorAbatimento = this.txtValorAbatimento.valor;
			_dtoMerge.indValorMinimo = retornarDataObject(this.txtIndValorMinimo.selectedItem);
			_dtoMerge.valorMinimo = this.txtValorMinimo.valor;
			_dtoMerge.indValorMaximo = retornarDataObject(this.txtIndValorMaximo.selectedItem);
			_dtoMerge.valorMaximo = this.txtValorMaximo.valor;
			_dtoMerge.numeroParcela = this.txtNumeroParcela.valor;
			_dtoMerge.qtdTotalParcelas = this.txtQtdTotalParcelas.valor;
			_dtoMerge.bolTituloNegociado = retornarComboSimNao(this.txtBolTitNegociado.selectedItem);
			_dtoMerge.bolBloqueioPagamento = retornarComboSimNao(this.txtBolBloqueioPgto.selectedItem);
			_dtoMerge.bolPagamentoParcial = retornarComboSimNao(this.txtBolPgtoParcial.selectedItem);
			_dtoMerge.qtdPagamentoParcial = this.txtQtdPgtoParcial.valor;
			_dtoMerge.tipoModeloDeCalculo = (this.cmbTpModeloCalc.selectedItem != null ? this.cmbTpModeloCalc.selectedItem.codTipoModeloCalculo : null);
			_dtoMerge.codAutorizacaoValorDivergente = (this.cmbAutorizacaoValorDivergente.selectedItem != null ? this.cmbAutorizacaoValorDivergente.selectedItem.codAutorizacaoValorDivergente : null);
			_dtoMerge.idEspecieDocumento = (this.cmbEspecieDoc.selectedItem != null ? this.cmbEspecieDoc.selectedItem.idEspecieDocumentoCip : null);
			_dtoMerge.codTipoPagamento = (this.cmbCodTipoPagto.selectedItem != null ? this.cmbCodTipoPagto.selectedItem.codMeioPagamento : null);
			_dtoMerge.numRefAtualCadBoleto = this.txtNumRefAtualBoleto.valor;
			_dtoMerge.numSeqAtualCadBoleto = this.txtNumSeqAtualBoleto.valor;
			receberDadosDesconto();
			receberDadosJuros();
			receberDadosMulta();
			receberDadosGrupoCalculo();
			
			return _dtoMerge;
		}
		
		private function alterarMensagemDDABoleto(event:MouseEvent):void {
			montarDtoAlteracao();
			if(!validarMensagemDDABoleto()) {
				return;
			}
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.dto = _dtoMerge;
			
			var servico:ServicoJava = Funcoes.obterServico(Constantes.SERVICO_MANUTENCAO_MENSAGEMDDABOLETO, "alterarMensagemDDABoleto");
			servico.mensagemEspera = "Alterando dados de MensagemDDABoleto...";
			servico.mensagemErro = "Erro ao tentar alterar MensagemDDABoleto...";
			servico.addEventListener(ResultEvent.RESULT, retornarAlterarMensagemDDABoleto);
			servico.alterarMensagemDDABoleto(dto);
		}
		
		private function validarMensagemDDABoleto():Boolean {
			if(!validarDadosBeneficiario()) {
				return false;
			} else if(!validarDadosBeneficiarioFinal()) {
				return false;
			} else if(!validarDadosPagador()) {
				return false;
			} 
			/*else if(!validarDadosAvalista()) {
				return false;
			} */
			else if(!validarDadosBoleto()) {
				return false;
			} else if(!validarDadosDesconto()) {
				return false;
			} else if(!validarDadosJurosMulta(_dtoMerge.codTipoJuros, _dtoMerge.valorJuros, "Juros")) {
				return false;
			} else if(!validarDadosJurosMulta(_dtoMerge.codTipoMulta, _dtoMerge.valorMulta, "Multa")) {
				return false;
			} else {
				return true;
			}
		}
		
		//RN Campos Obrigatórios Dados Juros
		private function validarDadosJurosMulta(codTipo:Number, valor:Number, dados:String):Boolean {
			
			return true;
		}
		
		//RN Campos Obrigatórios Dados Desconto
		private function validarDadosDesconto():Boolean {
			//Cod. Tipo Desconto e Valor Desconto: Se preencher UM DOS DOIS, o outro se torna OBRIGATÓRIO.
			if(!ObjectUtil.isNullOrEmptyList(_dtoMerge.listDadosDesconto)) {
				for each(var obj:MensagemDDABoletoDescontoDTO in _dtoMerge.listDadosDesconto) {
					if(!ObjectUtil.isNull(obj)) {
						if((obj.codTipoDesconto != null) && (isNaN(obj.valorDesconto))) {
								MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG017, "Valor Desconto"));
									return false;
						}
						
						if((!isNaN(obj.valorDesconto)) && (obj.codTipoDesconto == null)) {
								MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG017, "Cod. Tipo Desconto"));
								return false;
						}
					}
				}
			}
			return true;
		}
		
		private function validarDadosBeneficiarioFinal():Boolean {
			if((!ObjectUtil.isNullOrBlank(_dtoMerge.codTipoPessoaBeneficiarioFinal))
				&& (!ObjectUtil.isNullOrBlank(_dtoMerge.numCnpjCpfBeneficiarioFinal))) {
					if(!CpfCnpjUtils.isTipoIgualDocumento(_dtoMerge.codTipoPessoaBeneficiarioFinal, _dtoMerge.numCnpjCpfBeneficiarioFinal)) {
						MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG030, "CPF/CNPJ Beneficiário Final"));
						return false;
					}
			}
			return true;
		}
		
		private function validarDadosAvalista():Boolean {
			if((!ObjectUtil.isNullOrBlank(_dtoMerge.codTipoPessoaAvalista))
				&& (!ObjectUtil.isNullOrBlank(_dtoMerge.numCnpjCpfAvalista))) {
				if(!CpfCnpjUtils.isTipoIgualDocumento(_dtoMerge.codTipoPessoaAvalista, _dtoMerge.numCnpjCpfAvalista)) {
					MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG030, "CPF/CNPJ Avalista"));
					return false;
				}
			}
			return true;
		}
		
		//RN12
		private function validarDadosBeneficiario():Boolean {
			if(ObjectUtil.isNullOrBlank(_dtoMerge.codTipoPessoaBeneficiario)) {
				MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG017, "Tipo Pessoa Beneficiário"));
				return false;
			}
			if(ObjectUtil.isNullOrBlank(_dtoMerge.numCnpjCpfBeneficiario)) {
				MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG017, "CPF/CNPJ Beneficiário"));
				return false;
			}
			validarTipoIgualDocumento(_dtoMerge.codTipoPessoaBeneficiario, _dtoMerge.numCnpjCpfBeneficiario, "Beneficiário");
			if(ObjectUtil.isNullOrBlank(_dtoMerge.nomeBeneficiario)) {
				MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG017, "Nome/Razão Social Beneficiário"));
				return false;
			}
			return true;
		}
		
		//RN13
		private function validarDadosPagador():Boolean {
			if(ObjectUtil.isNullOrBlank(_dtoMerge.codTipoPessoaPagador)) {
				MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG017, "Tipo Pessoa Pagador"));
				return false;
			}
			if(ObjectUtil.isNullOrBlank(_dtoMerge.numCnpjCpfPagador)) {
				MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG017, "CPF/CNPJ Pagador"));
				return false;
			}
			validarTipoIgualDocumento(_dtoMerge.codTipoPessoaPagador, _dtoMerge.numCnpjCpfPagador, "Pagador");
			if(ObjectUtil.isNullOrBlank(_dtoMerge.nomePagador)) {
				MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG017, "Nome/Razão Social Pagador"));
				return false;
			}
			
			return true;
		}
		
		private function validarTipoIgualDocumento(tipo:String, doc:String, pessoa:String):Boolean {
			if(!ObjectUtil.isNullOrBlank(tipo)) {
				if(!CpfCnpjUtils.isTipoIgualDocumento(tipo, doc)) {
					MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG030, "CPF/CNPJ de "+pessoa));
					return false;
				}
			}
			return true;
		}
		
		private function validarDadosBoleto():Boolean {
			if(ObjectUtil.isNullOrBlank(_dtoMerge.numCodigoDeBarras)) {
				MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG017, "Num. Código de Barras"));
				return false;
			} else if(StringUtils.trim(_dtoMerge.numCodigoDeBarras).length != ConstantesComum.LENGTH_COD_DE_BARRAS) {
				MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG035, "Num. Código de Barras", ConstantesComum.LENGTH_COD_DE_BARRAS));
				return false;
			} else if(ObjectUtil.isNullOrBlank(_dtoMerge.numLinhaDigitavel)) {
				MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG017, "Linha Digitável"));
				return false;
			} else if(StringUtils.trim(_dtoMerge.numLinhaDigitavel).length != ConstantesComum.LENGTH_LINHA_DIGITAVEL) {
				MensagensComum.exibirMensagemErro(MensagensComum.formatar(MensagensComum.MSG035, "Linha Digitável", ConstantesComum.LENGTH_LINHA_DIGITAVEL));
				return false;
			}
			return true;
		}
		
		private function retornarAlterarMensagemDDABoleto(event:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso("Dados alterados com sucesso.", fecharMsgSucesso);
		}
		
		private function fecharMsgSucesso(event:Event):void {
			if(_atualizaPesquisa != null){
				this._atualizaPesquisa();
			}
			super.fecharJanela();
		}
		
		private function receberDadosGrupoCalculo():void {
			//DADOS 1
			var grupoCalc1:MensagemDDABoletoGrupoCalculoDTO = new MensagemDDABoletoGrupoCalculoDTO;
			grupoCalc1.dataValidadeCalculo = DateTimeBase.getDateTime(this.dadosGrupoCalculo1.txtDtValidadeCalc.selectedDate);
			grupoCalc1.juros = this.dadosGrupoCalculo1.txtJuros.valor;
			grupoCalc1.multa = this.dadosGrupoCalculo1.txtMulta.valor;
			grupoCalc1.desconto = this.dadosGrupoCalculo1.txtDesconto.valor;
			grupoCalc1.valorTotalCobrado = this.dadosGrupoCalculo1.txtVlrTotalCobr.valor;
			grupoCalc1.idMensagemDDABoletoGrupoCalculo = this.dadosGrupoCalculo1.hiddenIdObject.valor;
		
			//DADOS 2
			var grupoCalc2:MensagemDDABoletoGrupoCalculoDTO = new MensagemDDABoletoGrupoCalculoDTO;
			grupoCalc2.dataValidadeCalculo = DateTimeBase.getDateTime(this.dadosGrupoCalculo2.txtDtValidadeCalc.selectedDate);
			grupoCalc2.juros = this.dadosGrupoCalculo2.txtJuros.valor;
			grupoCalc2.multa = this.dadosGrupoCalculo2.txtMulta.valor;
			grupoCalc2.desconto = this.dadosGrupoCalculo2.txtDesconto.valor;
			grupoCalc2.valorTotalCobrado = this.dadosGrupoCalculo2.txtVlrTotalCobr.valor;
			grupoCalc2.idMensagemDDABoletoGrupoCalculo = this.dadosGrupoCalculo2.hiddenIdObject.valor;
			
			//DADOS 3
			var grupoCalc3:MensagemDDABoletoGrupoCalculoDTO = new MensagemDDABoletoGrupoCalculoDTO;
			grupoCalc3.dataValidadeCalculo = DateTimeBase.getDateTime(this.dadosGrupoCalculo3.txtDtValidadeCalc.selectedDate);
			grupoCalc3.juros = this.dadosGrupoCalculo3.txtJuros.valor;
			grupoCalc3.multa = this.dadosGrupoCalculo3.txtMulta.valor;
			grupoCalc3.desconto = this.dadosGrupoCalculo3.txtDesconto.valor;
			grupoCalc3.valorTotalCobrado = this.dadosGrupoCalculo3.txtVlrTotalCobr.valor;
			grupoCalc3.idMensagemDDABoletoGrupoCalculo = this.dadosGrupoCalculo3.hiddenIdObject.valor;
			
			//DADOS 4
			var grupoCalc4:MensagemDDABoletoGrupoCalculoDTO = new MensagemDDABoletoGrupoCalculoDTO;
			grupoCalc4.dataValidadeCalculo = DateTimeBase.getDateTime(this.dadosGrupoCalculo4.txtDtValidadeCalc.selectedDate);
			grupoCalc4.juros = this.dadosGrupoCalculo4.txtJuros.valor;
			grupoCalc4.multa = this.dadosGrupoCalculo4.txtMulta.valor;
			grupoCalc4.desconto = this.dadosGrupoCalculo4.txtDesconto.valor;
			grupoCalc4.valorTotalCobrado = this.dadosGrupoCalculo4.txtVlrTotalCobr.valor;
			grupoCalc4.idMensagemDDABoletoGrupoCalculo = this.dadosGrupoCalculo4.hiddenIdObject.valor;
			
			//DADOS 5
			var grupoCalc5:MensagemDDABoletoGrupoCalculoDTO = new MensagemDDABoletoGrupoCalculoDTO;
			grupoCalc5.dataValidadeCalculo = DateTimeBase.getDateTime(this.dadosGrupoCalculo5.txtDtValidadeCalc.selectedDate);
			grupoCalc5.juros = this.dadosGrupoCalculo5.txtJuros.valor;
			grupoCalc5.multa = this.dadosGrupoCalculo5.txtMulta.valor;
			grupoCalc5.desconto = this.dadosGrupoCalculo5.txtDesconto.valor;
			grupoCalc5.valorTotalCobrado = this.dadosGrupoCalculo5.txtVlrTotalCobr.valor;
			grupoCalc5.idMensagemDDABoletoGrupoCalculo = this.dadosGrupoCalculo5.hiddenIdObject.valor;
			
			//INCLUINDO
			_dtoMerge.listDadosGrupoCalculo = new ArrayCollection;
			_dtoMerge.listDadosGrupoCalculo.addItem(grupoCalc1);
			_dtoMerge.listDadosGrupoCalculo.addItem(grupoCalc2);
			_dtoMerge.listDadosGrupoCalculo.addItem(grupoCalc3);
			_dtoMerge.listDadosGrupoCalculo.addItem(grupoCalc4);
			_dtoMerge.listDadosGrupoCalculo.addItem(grupoCalc5);
		}
		
		private function receberDadosJuros():void {
			_dtoMerge.idJuros = this.dadosJuroMora.hiddenIdObject.valor;
			_dtoMerge.codTipoJuros = (this.dadosJuroMora.cmbTipo.selectedItem != null ? this.dadosJuroMora.cmbTipo.selectedItem.id : null);
			_dtoMerge.dataJuros = DateTimeBase.getDateTime(this.dadosJuroMora.txtData.selectedDate);
			_dtoMerge.valorJuros = this.dadosJuroMora.txtValor.valor;
			
		}
		
		private function receberDadosMulta():void {
			_dtoMerge.idMulta = this.dadosMulta.hiddenIdObject.valor;
			_dtoMerge.codTipoMulta = (this.dadosMulta.cmbTipo.selectedItem != null ? this.dadosMulta.cmbTipo.selectedItem.id : null);
			_dtoMerge.dataMulta = DateTimeBase.getDateTime(this.dadosMulta.txtData.selectedDate);
			_dtoMerge.valorMulta = this.dadosMulta.txtValor.valor;
			
		}
		
		private function receberDadosDesconto():void {
			//DESCONTO 1
			var msgDesc1:MensagemDDABoletoDescontoDTO = new MensagemDDABoletoDescontoDTO;
			msgDesc1.idMensagemDDABoletoDesconto = this.dadosDesconto1.hiddenIdObject.valor;
			msgDesc1.codTipoDesconto = (this.dadosDesconto1.cmbTipo.selectedItem != null ? this.dadosDesconto1.cmbTipo.selectedItem.codTipoDesconto : null);
			msgDesc1.dataDesconto = DateTimeBase.getDateTime(this.dadosDesconto1.txtData.selectedDate);
			msgDesc1.valorDesconto = this.dadosDesconto1.txtValor.valor; 

			//DESCONTO 2
			var msgDesc2:MensagemDDABoletoDescontoDTO = new MensagemDDABoletoDescontoDTO;
			msgDesc2.idMensagemDDABoletoDesconto = this.dadosDesconto2.hiddenIdObject.valor;
			msgDesc2.codTipoDesconto = (this.dadosDesconto2.cmbTipo.selectedItem != null ? this.dadosDesconto2.cmbTipo.selectedItem.codTipoDesconto : null)
			msgDesc2.dataDesconto = DateTimeBase.getDateTime(this.dadosDesconto2.txtData.selectedDate);
			msgDesc2.valorDesconto = this.dadosDesconto2.txtValor.valor; 
	
			//DESCONTO 3
			var msgDesc3:MensagemDDABoletoDescontoDTO = new MensagemDDABoletoDescontoDTO;
			msgDesc3.idMensagemDDABoletoDesconto = this.dadosDesconto3.hiddenIdObject.valor;
			msgDesc3.codTipoDesconto = (this.dadosDesconto3.cmbTipo.selectedItem != null ? this.dadosDesconto3.cmbTipo.selectedItem.codTipoDesconto : null)
			msgDesc3.dataDesconto = DateTimeBase.getDateTime(this.dadosDesconto3.txtData.selectedDate);
			msgDesc3.valorDesconto = this.dadosDesconto3.txtValor.valor; 
		
			//INCLUINDO
			_dtoMerge.listDadosDesconto = new ArrayCollection;
			_dtoMerge.listDadosDesconto.addItem(msgDesc1);
			_dtoMerge.listDadosDesconto.addItem(msgDesc2);
			_dtoMerge.listDadosDesconto.addItem(msgDesc3);			
		}
		
		private function carregarDadosDetalharMensagem():void {
			this.txtIdMensagemDDA.text = String(_mensagemDDABoleto.idMensagem);
			//DADOS BENEFICIÁRIO
			this.panelTipoPessoaBeneficiario.cbTipoPessoa.procuraItemData(_mensagemDDABoleto.codTipoPessoaBeneficiario);
			if(isPessoaFisica(_mensagemDDABoleto.codTipoPessoaBeneficiario)) {
				this.panelTipoPessoaBeneficiario.txtCpf.text = _mensagemDDABoleto.numCnpjCpfBeneficiario;
				this.panelTipoPessoaBeneficiario.labelCPF.visible = true;
				this.panelTipoPessoaBeneficiario.txtCpf.visible = true;
			} else {
				this.panelTipoPessoaBeneficiario.txtCnpj.text = _mensagemDDABoleto.numCnpjCpfBeneficiario;
				this.panelTipoPessoaBeneficiario.labelCNPJ.visible = true;
				this.panelTipoPessoaBeneficiario.txtCnpj.visible = true;
			}
			this.dadosBeneficiario.txtNome.text = _mensagemDDABoleto.nomeBeneficiario;
			this.dadosBeneficiario.txtNomeFantasia.text = _mensagemDDABoleto.nomeFantasiaBeneficiario;
			this.dadosBeneficiario.txtLogradouro.text = _mensagemDDABoleto.descLogradouroBeneficiario;
			this.dadosBeneficiario.txtCidade.text = _mensagemDDABoleto.descCidadeBeneficiario;
			this.dadosBeneficiario.cmbUf.procuraItemPorNome(_mensagemDDABoleto.ufBeneficiario, "label");
			this.dadosBeneficiario.txtCep.text = _mensagemDDABoleto.numCepBeneficiario;
			//DADOS BENEFICIÁRIO FINAL
			this.panelTipoPessoaBeneficiarioFinal.cbTipoPessoa.procuraItemData(_mensagemDDABoleto.codTipoPessoaBeneficiarioFinal);
			if(isPessoaFisica(_mensagemDDABoleto.codTipoPessoaBeneficiarioFinal)) {
				this.panelTipoPessoaBeneficiarioFinal.txtCpf.text = _mensagemDDABoleto.numCnpjCpfBeneficiarioFinal;
				this.panelTipoPessoaBeneficiarioFinal.labelCPF.visible = true;
				this.panelTipoPessoaBeneficiarioFinal.txtCpf.visible = true;
			} else {
				this.panelTipoPessoaBeneficiarioFinal.txtCnpj.text = _mensagemDDABoleto.numCnpjCpfBeneficiarioFinal;
				this.panelTipoPessoaBeneficiarioFinal.txtCnpj.visible = true;
				this.panelTipoPessoaBeneficiarioFinal.labelCNPJ.visible = true;
			}
			this.dadosBeneficiarioFinal.txtNome.text = _mensagemDDABoleto.nomeBeneficiarioFinal;
			this.dadosBeneficiarioFinal.txtNomeFantasia.text = _mensagemDDABoleto.nomeFantasiaBeneficiarioFinal;
			//DADOS DO PAGADOR
			this.panelTipoPessoaPagador.cbTipoPessoa.procuraItemData(_mensagemDDABoleto.codTipoPessoaPagador);
			if(isPessoaFisica(_mensagemDDABoleto.codTipoPessoaPagador)) {
				this.panelTipoPessoaPagador.txtCpf.text = _mensagemDDABoleto.numCnpjCpfPagador;
				this.panelTipoPessoaPagador.labelCPF.visible = true;
				this.panelTipoPessoaPagador.txtCpf.visible = true;
			} else {
				this.panelTipoPessoaPagador.txtCnpj.text = _mensagemDDABoleto.numCnpjCpfPagador;
				this.panelTipoPessoaPagador.txtCnpj.visible = true;
				this.panelTipoPessoaPagador.labelCNPJ.visible = true;
			}
			this.dadosPagador.txtNome.text = _mensagemDDABoleto.nomePagador;
			this.dadosPagador.txtNomeFantasia.text = _mensagemDDABoleto.nomeFantasiaPagador;
			this.dadosPagador.txtLogradouro.text = _mensagemDDABoleto.descLogradouroPagador;
			this.dadosPagador.txtCidade.text = _mensagemDDABoleto.desCidadePagador;
			this.dadosPagador.cmbUf.procuraItemPorNome(_mensagemDDABoleto.ufPagador, "label");
			this.dadosPagador.txtCep.text = _mensagemDDABoleto.numCepPagador;
			//DADOS DO AVALISTA
			this.panelTipoPessoaAvalista.cbTipoPessoaAvalista.procuraItemPorNome(_mensagemDDABoleto.codTipoPessoaAvalista, "codTipoPessoaDDAAvalista");
			this.panelTipoPessoaAvalista.selecionarTipoDocumento(_mensagemDDABoleto.codTipoPessoaAvalista, _mensagemDDABoleto.numCnpjCpfAvalista);
			this.dadosAvalista.txtNome.text = _mensagemDDABoleto.nomeAvalista;
			//DADOS DO BOLETO
			this.cmbIdCarteira.procuraItemPorNome(_mensagemDDABoleto.idCarteira, "idCarteiraCip");
			this.txtCodMoeda.text = _mensagemDDABoleto.codMoeda;
			this.txtOrgaoMoeda.text = String(_mensagemDDABoleto.idOrgaoMoeda);
			this.txtNossoNumero.text = _mensagemDDABoleto.numNossoNumero;
			this.txtCodBarras.text = _mensagemDDABoleto.numCodigoDeBarras;
			this.txtLinhaDigitavel.text = _mensagemDDABoleto.numLinhaDigitavel;
			this.txtDataVencimento.selectedDate = retornarData(_mensagemDDABoleto.dataVencimento);
			this.txtValorBoleto.valor = _mensagemDDABoleto.valorDoBoleto;
			this.txtDataEmissao.selectedDate = retornarData(_mensagemDDABoleto.dataEmissao);
			this.txtNumDocumento.text = _mensagemDDABoleto.numDocumento;
			this.txtDataLimitePgto.selectedDate = retornarData(_mensagemDDABoleto.dataLimitePgto);
			this.txtDiasProtesto.text = String(_mensagemDDABoleto.diasDeProtesto);
			this.txtValorAbatimento.valor = _mensagemDDABoleto.valorAbatimento;
			this.txtIndValorMinimo.procuraItemPorNome(retornarIndicador(_mensagemDDABoleto.indValorMinimo), "label");
			this.txtValorMinimo.valor = _mensagemDDABoleto.valorMinimo;
			this.txtIndValorMaximo.procuraItemPorNome(retornarIndicador(_mensagemDDABoleto.indValorMaximo),"label");
			this.txtValorMaximo.valor = _mensagemDDABoleto.valorMaximo;
			this.txtNumeroParcela.text = String(_mensagemDDABoleto.numeroParcela);
			this.txtQtdTotalParcelas.text = String(_mensagemDDABoleto.qtdTotalParcelas);
			this.txtBolTitNegociado.procuraItemPorNome(retornarSimNao(_mensagemDDABoleto.bolTituloNegociado), "label");
			this.txtBolBloqueioPgto.procuraItemPorNome(retornarSimNao(_mensagemDDABoleto.bolBloqueioPagamento), "label");
			this.txtBolPgtoParcial.procuraItemPorNome(retornarSimNao(_mensagemDDABoleto.bolPagamentoParcial),"label");
			this.txtQtdPgtoParcial.text = String(_mensagemDDABoleto.qtdPagamentoParcial);
			this.cmbTpModeloCalc.procuraItemPorNome(_mensagemDDABoleto.tipoModeloDeCalculo, "codTipoModeloCalculo");
			this.cmbAutorizacaoValorDivergente.procuraItemPorNome(_mensagemDDABoleto.codAutorizacaoValorDivergente, "codAutorizacaoValorDivergente");
			this.cmbEspecieDoc.procuraItemPorNome(_mensagemDDABoleto.idEspecieDocumento, "idEspecieDocumentoCip");
			this.cmbCodTipoPagto.procuraItemPorNome(_mensagemDDABoleto.codTipoPagamento, "codMeioPagamento");
			this.txtNumRefAtualBoleto.text = String(_mensagemDDABoleto.numRefAtualCadBoleto);
			this.txtNumSeqAtualBoleto.text = String(_mensagemDDABoleto.numSeqAtualCadBoleto);
			//DADOS DESCONTO
			exibirDadosDesconto();
			//DADOS JUROS
			this.dadosJuroMora.hiddenIdObject.valor = _mensagemDDABoleto.idJuros;
			this.dadosJuroMora.cmbTipo.procuraItemPorNome(_mensagemDDABoleto.codTipoJuros, "id");
			this.dadosJuroMora.txtData.selectedDate = (!ObjectUtil.isNull(_mensagemDDABoleto.dataJuros) ? _mensagemDDABoleto.dataJuros.data : null);
			this.dadosJuroMora.txtValor.valor = _mensagemDDABoleto.valorJuros;
			//DADOS MULTA
			this.dadosMulta.hiddenIdObject.valor = _mensagemDDABoleto.idMulta;
			this.dadosMulta.cmbTipo.procuraItemPorNome(_mensagemDDABoleto.codTipoMulta, "id");
			this.dadosMulta.txtData.selectedDate = retornarData(_mensagemDDABoleto.dataMulta);
			this.dadosMulta.txtValor.valor = _mensagemDDABoleto.valorMulta;
			//DADOS GRUPO DE CÁLCULO
			exibirDadosGrupoCalculo();
		}
		
		private function retornarData(data:IDateTime):Date {
			if(!ObjectUtil.isNull(data)) {
				return data.data;
			} else {
				return null;
			}
		}
		
		private function exibirDadosDesconto():void {
			var indexFor:int = 0;
			if(!ObjectUtil.isNullOrEmptyList(_mensagemDDABoleto.listDadosDesconto)) {
			var list:ArrayCollection = _mensagemDDABoleto.listDadosDesconto;
				for each (var obj:Object in list) {
					indexFor++;
					var msgDesc:MensagemDDABoletoDescontoDTO = new MensagemDDABoletoDescontoDTO;
					msgDesc = obj as MensagemDDABoletoDescontoDTO;
					if(indexFor == 1) {
						this.dadosDesconto1.hiddenIdObject.valor = 	obj.idMensagemDDABoletoDesconto;
						this.dadosDesconto1.cmbTipo.procuraItemPorNome((ObjectUtil.isNull(obj.codTipoDesconto) ? null : obj.codTipoDesconto), "codTipoDesconto");
						this.dadosDesconto1.txtData.selectedDate = retornarData(obj.dataDesconto);
						this.dadosDesconto1.txtValor.valor = obj.valorDesconto;
					} else if (indexFor == 2) {
						this.dadosDesconto2.hiddenIdObject.valor = obj.idMensagemDDABoletoDesconto;
						this.dadosDesconto2.cmbTipo.procuraItemPorNome((ObjectUtil.isNull(obj.codTipoDesconto) ? null : obj.codTipoDesconto), "codTipoDesconto");
						this.dadosDesconto2.txtData.selectedDate = retornarData(obj.dataDesconto);
						this.dadosDesconto2.txtValor.valor = obj.valorDesconto;
					} else if (indexFor == 3) {
						this.dadosDesconto3.hiddenIdObject.valor = obj.idMensagemDDABoletoDesconto;
						this.dadosDesconto3.cmbTipo.procuraItemPorNome((ObjectUtil.isNull(obj.codTipoDesconto) ? null : obj.codTipoDesconto), "codTipoDesconto");
						this.dadosDesconto3.txtData.selectedDate = retornarData(obj.dataDesconto);
						this.dadosDesconto3.txtValor.valor = obj.valorDesconto;
					} else {
						indexFor = 0;
						break;
					}
				}	
			} 
		}
		
		private function exibirDadosGrupoCalculo():void {
			var indexFor:int = 0;
			if(!ObjectUtil.isNullOrEmptyList(_mensagemDDABoleto.listDadosGrupoCalculo)) {
			var list:ArrayCollection = _mensagemDDABoleto.listDadosGrupoCalculo;
				for each (var obj:Object in list) {
					indexFor++;
					var msgGp:MensagemDDABoletoGrupoCalculoDTO = obj as MensagemDDABoletoGrupoCalculoDTO;
					if(indexFor == 1) {
						this.dadosGrupoCalculo1.hiddenIdObject.valor = msgGp.idMensagemDDABoletoGrupoCalculo;
						this.dadosGrupoCalculo1.txtDtValidadeCalc.selectedDate = retornarData(msgGp.dataValidadeCalculo);
						this.dadosGrupoCalculo1.txtJuros.valor = msgGp.juros;
						this.dadosGrupoCalculo1.txtMulta.valor = msgGp.multa;
						this.dadosGrupoCalculo1.txtDesconto.valor = msgGp.desconto;
						this.dadosGrupoCalculo1.txtVlrTotalCobr.valor = msgGp.valorTotalCobrado;
					} else if (indexFor == 2) {
						this.dadosGrupoCalculo2.hiddenIdObject.valor = msgGp.idMensagemDDABoletoGrupoCalculo;
						this.dadosGrupoCalculo2.txtDtValidadeCalc.selectedDate = retornarData(msgGp.dataValidadeCalculo);
						this.dadosGrupoCalculo2.txtJuros.valor = msgGp.juros;
						this.dadosGrupoCalculo2.txtMulta.valor = msgGp.multa;
						this.dadosGrupoCalculo2.txtDesconto.valor = msgGp.desconto;
						this.dadosGrupoCalculo2.txtVlrTotalCobr.valor = msgGp.valorTotalCobrado;
					} else if (indexFor == 3) {
						this.dadosGrupoCalculo3.hiddenIdObject.valor = msgGp.idMensagemDDABoletoGrupoCalculo;
						this.dadosGrupoCalculo3.txtDtValidadeCalc.selectedDate = retornarData(msgGp.dataValidadeCalculo);
						this.dadosGrupoCalculo3.txtJuros.valor = msgGp.juros;
						this.dadosGrupoCalculo3.txtMulta.valor = msgGp.multa;
						this.dadosGrupoCalculo3.txtDesconto.valor = msgGp.desconto;
						this.dadosGrupoCalculo3.txtVlrTotalCobr.valor = msgGp.valorTotalCobrado;
					} else if (indexFor == 4) {
						this.dadosGrupoCalculo4.hiddenIdObject.valor = msgGp.idMensagemDDABoletoGrupoCalculo;
						this.dadosGrupoCalculo4.txtDtValidadeCalc.selectedDate = retornarData(msgGp.dataValidadeCalculo);
						this.dadosGrupoCalculo4.txtJuros.valor = msgGp.juros;
						this.dadosGrupoCalculo4.txtMulta.valor = msgGp.multa;
						this.dadosGrupoCalculo4.txtDesconto.valor = msgGp.desconto;
						this.dadosGrupoCalculo4.txtVlrTotalCobr.valor = msgGp.valorTotalCobrado;
					} else if (indexFor == 5) {
						this.dadosGrupoCalculo5.hiddenIdObject.valor = msgGp.idMensagemDDABoletoGrupoCalculo;
						this.dadosGrupoCalculo5.txtDtValidadeCalc.selectedDate = retornarData(msgGp.dataValidadeCalculo);
						this.dadosGrupoCalculo5.txtJuros.valor = msgGp.juros;
						this.dadosGrupoCalculo5.txtMulta.valor = msgGp.multa;
						this.dadosGrupoCalculo5.txtDesconto.valor = msgGp.desconto;
						this.dadosGrupoCalculo5.txtVlrTotalCobr.valor = msgGp.valorTotalCobrado;
					} else {
						indexFor == 0;
						break;
					}
				}
			}
		}

		private function retornarSimNao(param:String):String {
			return ((param.toUpperCase() == "S" || param == "1") ? ConstantesComum.SIM : ConstantesComum.NAO);
		}
		
		private function retornarIndicador(param:String):String {
			var ret:String = null;
			if(param != null && param != "") {
				ret = (param.toUpperCase() =="P"? _PERCENTUAL : _VALOR_MINIMO);
			} 
			return ret;	
		}
		
		private function isPessoaFisica(codTipoPessoa:String):Boolean {
			return codTipoPessoa == "F";
		}	
		
		private function retornoAlterarMensagemDDABoleto(e:ResultEvent):void {
			MensagensComum.exibirMensagemSucesso(Mensagens.MSG071, fecharMsgSucesso);
		}
		
		//--------------------------------------------------------------------------
		//  Fechar.
		//--------------------------------------------------------------------------
		private function fechar(event:Event):void {
			super.fecharJanela();
		}
		
		//--------------------------------------------------------------------------
		//  Cancelar.
		//--------------------------------------------------------------------------
		private function cancelar(event:Event):void {
			carregarDadosDetalharMensagem();
		}
		
		
	}
}


