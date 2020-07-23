package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes {
	
	import flash.events.KeyboardEvent;
	import flash.events.TextEvent;
	import flash.ui.Keyboard;
	
	import mx.controls.TextInput;
	import mx.controls.ToolTip;
	import mx.events.FlexEvent;
	import mx.events.ValidationResultEvent;
	import mx.formatters.NumberBaseRoundType;
	import mx.managers.ToolTipManager;
	import mx.validators.CurrencyValidator;
	import mx.validators.EmailValidator;
	import mx.validators.Validator;
	
	import br.com.bancoob.componentes.IPermissaoHabilitar;
	import br.com.bancoob.dto.ResultadoValidacaoDTO;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.IValidavel;
	import br.com.bancoob.util.StringUtils;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.NumeroUtil;
	
	public class TextoCobranca extends TextInput implements IValidavel, IPermissaoHabilitar {
		
		public static const CASAS_DECIMAIS_ARREDONDAR:String = NumberBaseRoundType.NEAREST;
		public static const CASAS_DECIMAIS_TRUNCAR:String = NumberBaseRoundType.NONE;
		
		public static var TEXTO:int = 0;
		public static var NUMERICO:int = 1;
		
		private var _aceitaNulos:Boolean = false;
		private var valorOriginal:String;
		private var _navegarComEnter:Boolean = false;
		private var _permitirHabilitar:Boolean = true;
		private var _travarDigitacaoCasasDecimais:Boolean = true;
		private var _arredondamento:String = CASAS_DECIMAIS_TRUNCAR;
		private var _permitirValoreNegativos:Boolean = true;
		private var _somenteMaiusculas:Boolean = true;
		private var formato:String = "";
		private var _tipoEntrada:int = 0;
		private var _valor:Number = 0;
		private var _casasDecimais:int = 0;
		private var _agruparDigitos:Boolean = true;
		private var _valorMinimo:Number;
		private var _valorMaximo:Number;
		
		protected var _validarObrigatorio:Boolean = false;
		protected var _validarEmail:Boolean = false;
		protected var _validarMensagem:String = "";
		protected var _validarMensagemMaximo:String = "";
		protected var _validarMensagemMinimo:String = "";
		protected var valid:Validator = null;
		protected var validEmail:EmailValidator = null;
		protected var _validarMinimo:CurrencyValidator = null;
		
		private var tip:ToolTip;
		
		/**
		 * Override da propriedade text 
		 */		    
		public override function get text():String{
			return super.text;
		}
		
		public override function set text(vlr:String):void{
			valorOriginal = vlr;
			super.text = vlr;
		}
		
		/**
		 * Permite entrada somente de letra maiúsculas 
		 * 
		 * @default true
		 */		
		public function get somenteMaiusculas():Boolean {
			return _somenteMaiusculas;
		}
		public function set somenteMaiusculas(vlr:Boolean):void {
			_somenteMaiusculas = vlr;
		}
		
		/**
		 * Permite entrada de valore negativos
		 * 
		 * @default false
		 */		
		public function get permitirValoreNegativos():Boolean {
			return _permitirValoreNegativos;
		}
		public function set permitirValoreNegativos(vlr:Boolean):void {
			_permitirValoreNegativos = vlr;
			
			if(_tipoEntrada == TextoCobranca.NUMERICO){
				this.restrict = (vlr)?"0123456789.,\\-":"0123456789.,\\";	
			}
		}
		
		/**
		 * Informa se o componente pode receber valores em branco(nulo) 
		 * 
		 * @default false
		 */				
		public function set aceitaNulos(value:Boolean):void {
			_aceitaNulos = value;
		}
		
		public function get aceitaNulos():Boolean {
			return _aceitaNulos;
		}
		
		/**
		 * Formato para o valor digitado, este formato é aplicado ao perder o foco do campo 
		 * 
		 * @default ""
		 */		
		public function get Formato():String {
			return formato;
		}
		public function set Formato(vlr:String):void {
			formato = vlr;
		}
		
		/**
		 * Tipo de entrada do campo usar:0 - texto, 1 - numérico 
		 * 
		 * @default 0
		 */		
		public function get tipoEntrada():int {
			return _tipoEntrada;
		}
		public function set tipoEntrada(vlr:int):void {
			_tipoEntrada = vlr;
			
			if(_tipoEntrada == TextoCobranca.NUMERICO) { //realiza tramentos para tipo de campo numérico
				this.restrict = "0123456789.,\\-"; //restringe números, vírgula, ponto e traço (para valores negativos)
				this.setStyle("textAlign", "right"); //alinhamento à direita
			}else
			{
				if(!this.restrict)
					this.restrict = "^'"; //retira restrição para tipo de entrada como texto
			}
		}
		
		/**
		 * Valor numérico passado quando o tipo de campo é numérico 
		 * 
		 * @default 0
		 */		
		
		public function get valor():Number {
			return pegaValor();
		}
		public function set valor(vlr:Number):void {
			_valor = vlr;
			this.text = FormataNumero.formata(_valor, _casasDecimais, _agruparDigitos, _arredondamento);
		}
		
		
		/**
		 * Número de casas decimais para valor digitado 
		 * 
		 * @default 0
		 */		
		public function get casasDecimais():int{
			return _casasDecimais;
		}
		public function set casasDecimais(valor:int):void{
			_casasDecimais = valor;
		}
		
		/**
		 * Determina se é necessário agrupar dígitos na formatação do valor digitado 
		 * 
		 * @default true
		 */		
		public function get agruparDigitos():Boolean {
			return _agruparDigitos;
		}
		public function set agruparDigitos(vlr:Boolean):void {
			_agruparDigitos = vlr;
		}
		
		/**
		 * Determina se campo é requerido 
		 * 
		 * @default false
		 */		
		public function get validarObrigatorio():Boolean{
			return _validarObrigatorio;
		}
		public function set validarObrigatorio(vlr:Boolean):void{
			_validarObrigatorio = vlr;
			
			validar(); //chama validador
		}
		
		/**
		 * Determina se é necessário validar campo em formato de email 
		 * 
		 * @default false
		 */	
		public function get validarEmail():Boolean{
			return _validarEmail;
		}
		public function set validarEmail(vlr:Boolean):void{
			_validarEmail = vlr;
		}
		
		/**
		 * Mensagem de exibidação da validação 
		 * 
		 * @default ""
		 */	
		public function get validarMensagem():String{
			return _validarMensagem;
		}
		public function set validarMensagem(vlr:String):void{
			_validarMensagem = vlr;
			
			validar(); //chama validador
			
			if(_validarEmail) //se for email
				validacaoEmail(); //chama validador de email
		}
		
		public function get validarMensagemMaximo():String{
			return _validarMensagemMaximo;
		}
		
		public function set validarMensagemMaximo(vlr:String):void{
			_validarMensagemMaximo = vlr;
		}
		
		public function get validarMensagemMinimo():String{
			return _validarMensagemMinimo;
		}
		
		public function set validarMensagemMinimo(vlr:String):void{
			_validarMensagemMinimo = vlr;
		}
		
		/**
		 * Valor mínimo que o componente pode receber 
		 * 
		 * @default null
		 */	
		public function get valorMinimo():Number {
			return _valorMinimo;
		}
		public function set valorMinimo(vlr:Number):void {
			_valorMinimo = vlr;
		}
		
		/**
		 * Valor máximo que o componente pode receber 
		 * 
		 * @default null
		 */	
		public function get valorMaximo():Number {
			return _valorMaximo;
		}
		public function set valorMaximo(vlr:Number):void {
			_valorMaximo = vlr;
		}
		
		/**
		 * habilita o componente a passar para o próximo foco apertando a tecla Enter 
		 * 
		 * @default false
		 */	
		public function get navegarComEnter():Boolean {
			return _navegarComEnter;
		}
		public function set navegarComEnter(vlr:Boolean):void {
			_navegarComEnter = vlr;
		}
		
		/**
		 * limita a digitação das casas decimais configurada na propriedade casasDecimais 
		 * 
		 * @default false
		 */	
		public function get travarDigitacaoCasasDecimais():Boolean {
			return _travarDigitacaoCasasDecimais;
		}
		public function set travarDigitacaoCasasDecimais(vlr:Boolean):void {
			_travarDigitacaoCasasDecimais = vlr;
		}
		
		
		/**
		 * habilita o arredondamento do valor digitado 
		 * 
		 * @default false
		 */		
		public function get arredondamento():String {
			return _arredondamento;
		}
		public function set arredondamento(vlr:String):void {
			_arredondamento = vlr;
		}
		
		/**
		 * Informa se o componente pode ser habilitado para edição,
		 * caso seja false, a propriedade enabled é desativada 
		 *
		 * @default true
		 */					
		public function get permitirHabilitar():Boolean {
			return _permitirHabilitar;
		}
		
		public function set permitirHabilitar(vlr:Boolean):void {
			_permitirHabilitar = vlr;
		}
		
		/**
		 * Permite habilitar/desabilitar o componente para edição, quando for permitida
		 * através da propriedade permitirHabilitar
		 * 
		 */		
		public override function get enabled():Boolean{
			return super.enabled;
		}
		
		public override function set enabled(vlr:Boolean):void {
			
			if(vlr && !_permitirHabilitar)
				return;
			
			super.enabled = vlr;
		}
		
		/**
		 *  Construtor.
		 */		
		function TextoCobranca(){
			//validações
			super();
			this.addEventListener("change", validHandler);
			this.addEventListener("focusOut", validHandler_focusOut);
			this.addEventListener("focusIn", validHandler_focusIn);			
			this.addEventListener("creationComplete", validHandlerInit);
			this.addEventListener(TextEvent.TEXT_INPUT, interceptChar); //trata valor digitado
			this.addEventListener(KeyboardEvent.KEY_DOWN, interceptChar2); //trata valor digitado
			
			this.styleName = "texto";
			
			if(!this.restrict)
			{
				this.restrict = "^'";
			}
		}
		
		/**
		 *  remove formatação do campo
		 */				
		private function retiraFormato(txt:String):String{
			var txtTemp:String ;
			
			txtTemp = StringUtils.replace(txt, "\-","");
			return txtTemp;
		}
		
		/**
		 *  tratamento para o campo ao receber foco
		 */		
		protected function validHandler_focusIn(obj:Object):void{
			if (this.formato != "") {
				this.text = valorOriginal;
			}
		}
		
		/**
		 *  chama validador
		 */	
		private function validHandler(obj:Object):void{
			validar();
			
			if(_tipoEntrada == TextoCobranca.TEXTO)
				if(_somenteMaiusculas && !_validarEmail && !this.displayAsPassword)
					this.text = this.text.toUpperCase();
			
			if(_validarEmail)
				validacaoEmail(); //só chama validador de email caso seja do tipo de validação de email e não seja campo numérico
		}		
		
		/**
		 *  tratamento para o campo ao perder foco
		 */		
		protected function validHandler_focusOut(obj:Object):void {
			validar(); //chama validador
			
			destroyTip();
			
			if(_tipoEntrada == TextoCobranca.NUMERICO) { //se for tipo numérico
				var vlrPrev:Number = pegaValor(); // atualiza valor com o texto digitado
				
				// Valida o valor mínimo permitido e após continua o processo para formatar o valor entrado pelo usuário 
				validarCampoNumericoMenorQuePermitido(vlrPrev);
				
				_valor = vlrPrev;
				
				if(formato == "" || formato == null)
					formatarNumero(); //se não houver formato determinado manda formatar como valor numérico padrão
				else {
					if (!aceitaNulos || this.text.length > 0) {
						if(this.text.length == 0) {
							this.text = "";
						} else {
							this.text = FormatUtil.formataValor(_valor, formato); //caso tenha algum formato determinado manda aplicar
						}
					} else {
						if (this.text.length == 0) {
							this.text = "";
						}
					}
				}
			} else {
				valorOriginal = retiraFormato(this.text);
				
				if(_validarEmail)
					validacaoEmail(); //caso seja determinado para validar email chama validador
				
				if(formato != "" && formato != null) {
					if (!aceitaNulos || this.text.length > 0) {
						if(this.text.length == 0)
						{
							this.text = "";	
						}
						else
						{						
							this.text = FormatUtil.formataValor(valorOriginal, formato); //caso tenha algum formato determinado manda aplicar
						}
					}				
				}
			}
		}
		
		private function validarCampoNumericoMenorQuePermitido(vlrPrev:Number):Boolean {
			if (isNaN(_valorMinimo)) {
				_validarMinimo = null;
				return false;
			}
			
			if(_validarMinimo == null) {
				_validarMinimo = new CurrencyValidator();
			}
			
			_validarMinimo.thousandsSeparator = ".";
			_validarMinimo.decimalSeparator = ",";
			_validarMinimo.precision = casasDecimais;
			_validarMinimo.minValue = _valorMinimo;
			
			_validarMinimo.required = "";
			_validarMinimo.lowerThanMinError = (validarMensagemMinimo == "" ?
				"O valor mínimo permitido é " + NumeroUtil.formatarNumeroDecimalParaMoeda(_valorMinimo, casasDecimais) : validarMensagemMinimo);
			_validarMinimo.source = this;
			_validarMinimo.property = "text";
			
			return !(_validarMinimo.validate().type == ValidationResultEvent.INVALID);
		}
		
		/**
		 *  pega valor digitado no campo texto e transforma em valor numérico
		 */	
		protected function pegaValor():Number {
			var str:String = this.text;
			var patAgr:RegExp;
			
			if(formato == null || formato == "") {
				patAgr = /\./g; //expressão para localizar pontos (.)
				
				str = str.replace(patAgr, "").replace(",", "."); //localiza pontos e retira e localiza a vírgula e troca por ponto(.)
			}else{
				patAgr = /\-/g; //expressão para localizar traços
				str = str.replace(patAgr, ""); //
				
				patAgr = /\//g; //expressão para localizar barras
				str = str.replace(patAgr, ""); //
				
				patAgr = /\./g; //expressão para localizar barras
				str = str.replace(patAgr, ""); //
			}
			
			return Number(str);
		}
		
		/**
		 *  dispara validador inicial
		 */	
		private function validHandlerInit(obj:Object):void {
			if(_tipoEntrada == TextoCobranca.NUMERICO) //realiza tramentos para tipo de campo numérico
			{	
				formatarNumero();
				this.restrict = (_permitirValoreNegativos)?"0123456789.,\\-":"0123456789.,\\"; //restringe números, vírgula, ponto e traço (para valores negativos)
			}
			
			validar();
			
			if(_navegarComEnter)
				this.addEventListener(FlexEvent.ENTER, passarfoco);
		}
		
		/**
		 * Método override setFocus(), seleciona todo o conteúdo do texto
		 */		
		public override function setFocus():void
		{
			super.setFocus();
			
			this.selectionBeginIndex = 0;
			this.selectionEndIndex = this.text.length;	
		}
		
		private function passarfoco(evt:FlexEvent):void {
			evt.preventDefault();
			
			if (focusManager) {
				focusManager.setFocus(focusManager.getNextFocusManagerComponent());
				focusManager.showFocus();
			}
		}
		
		/**
		 * Função que realiza validação
		 * 
		 * @return Retorna <code>true</code> caso o campo esteja preenchido ou <code>false</code> caso o campo não esteja preenchido
		 */
		public function validar():Boolean{
			if(valid == null) {
				valid = new Validator(); //cria validador
			}
			
			valid.required = _validarObrigatorio; //determina se é requerido
			valid.requiredFieldError = (_validarMensagem == "" ?
				"Campo de preenchimento obrigatório!" :
				_validarMensagem); //determina mensagem
			valid.source = this; //seta campo para validar
			valid.property = "text"; //propriedade a verificar
			
			if (valid.validate().type == ValidationResultEvent.INVALID) //efetua validação
				return false;
			else
				return true;
		}
		
		/**
		 * Função que realiza validação de email
		 * 
		 * @return Retorna <code>true</code> caso o email seja válido ou <code>false</code> caso o campo não seja válido
		 */
		public function validacaoEmail():Boolean {
			
			if(validEmail == null) {
				validEmail = new EmailValidator(); //cria validador
			}
			
			validEmail.required = _validarObrigatorio; //verifica se é requerido
			
			validEmail.requiredFieldError = (_validarMensagem == "" ?
				"Campo de preenchimento obrigatório!" :
				_validarMensagem); //determina se mensagem de requerido
			
			validEmail.invalidCharError = 
				validEmail.invalidDomainError =
				validEmail.invalidIPDomainError = 
				validEmail.invalidPeriodsInDomainError = 
				validEmail.missingAtSignError = 
				validEmail.missingPeriodInDomainError = 
				validEmail.missingUsernameError = 
				validEmail.tooManyAtSignsError = (_validarMensagem == "" ?
					"Email inválido!" :
					_validarMensagem); //mensagem de validação de email
			validEmail.source = this; //campo a validar
			validEmail.property = "text"; //propriedade a verificar
			
			if (validEmail.validate().type == ValidationResultEvent.INVALID) //efetua validação
				return false;
			else
				return true;
		}
		
		private function interceptChar2(event:KeyboardEvent):void{
			if(_tipoEntrada == NUMERICO && (event.keyCode == Keyboard.DELETE || event.keyCode == Keyboard.BACKSPACE)) {
				var strValor:String;
				
				if (text.length > 0) {
					if (this.selectionBeginIndex != this.selectionEndIndex) {
						strValor = text.substr(0, this.selectionBeginIndex) + text.substr(this.selectionEndIndex, text.length);
					} else if (event.keyCode == Keyboard.DELETE) {
						strValor = text.substr(0, this.selectionBeginIndex) + text.substr(this.selectionEndIndex + 1, text.length);
					} else {
						strValor = text.substr(0, this.selectionBeginIndex - 1) + text.substr(this.selectionEndIndex, text.length);
					}
				} else {
					return;
				}
				
				var valor:Number = NumeroUtil.converterMoedaParaNumeroDecimal(strValor);
				
				if(valor > valorMaximo) {
					validarCampoNumericoMaiorQuePermitido(valor)
					event.preventDefault();
					return;
				} else {
					destroyTip();
				}
			}
		}
		
		/**
		 *  Efetua validações de para o item digitado
		 */	
		private function interceptChar(event:TextEvent):void{
			destroyTip();
			
			var input:String = event.text; //pega item digitado
			
			if(input == "") { //Firefox pega a tecla ALT como um CHAR digitado
				event.preventDefault();
				return;
			}
			
			if(_tipoEntrada == NUMERICO) {
				// verifica se vai permitir digitar maior quantidade de casas decimais do que o configurado
				if(_travarDigitacaoCasasDecimais && _casasDecimais > 0 && this.selectionBeginIndex == this.selectionEndIndex) {
					//pega local do separador
					var indSeparadorDecimal:int = this.text.indexOf(",");
					
					//verifica se existe um separador digitado
					if(indSeparadorDecimal != -1 && this.selectionBeginIndex > indSeparadorDecimal) {
						//calcula a quantidade de casas decimais existentes para travar
						if(((this.text.length - 1) - indSeparadorDecimal) == _casasDecimais){
							event.preventDefault();
							return;
						}
					}
				}
				
				if(input == "," || input == ".") { //verifica se foi uma vírgula digitada				
					if(_casasDecimais == 0){ //caso o campo não tenha casas decimais cancela item digitado
						event.preventDefault();
						return;
					}
					
					//se todo o texto estiver selecionado para a validação por aqui
					if(this.selectionBeginIndex == 0 && this.selectionEndIndex == this.text.length){
						event.preventDefault();
						this.text = "0,";
						this.selectionBeginIndex = 2; //posiciona o cursor no final
						this.selectionEndIndex = 2;
						
						return;
					}
					
					if(this.text == ""){ // caso o campo esteja vazio adiciona um 0 antes da vírgula
						event.preventDefault();//cancela digitação da vírgula
						this.text = "0,"; //monta a vírgula com um zero
						this.selectionBeginIndex = 2; //posiciona o cursor no final
						this.selectionEndIndex = 2;
						return;
					}
					
					if(this.text.indexOf(",") != -1){ //caso já exista uma vírgula digitada cancela a digitação de uma segunda vírgula
						event.preventDefault();
						return;
					}
					
					if(input == "."){
						event.preventDefault();
						var strP1:String = this.text.substr(0, this.selectionBeginIndex);
						var strP2:String = this.text.substr(this.selectionEndIndex);
						
						var posTemp:int = this.selectionBeginIndex + 1;
						
						this.text = strP1 + "," + strP2;
						
						this.selectionBeginIndex = posTemp;
						this.selectionEndIndex = posTemp;
						
						return;
					}
				} else if(input == "-") { //verifica sinal de negativo digitado
					if(this.text.indexOf("-") >= 0){ //caso já exista um sinal digitado cancela digitação do segundo
						event.preventDefault();
						return;
					}
					if(this.selectionBeginIndex != 0){ //caso o sinal não esteja no primeito item cancela digitação
						event.preventDefault();
						return;
					}
				} else {
					var strValor:String;
					
					if (text.length > 0) {
						strValor = text.substr(0, this.selectionBeginIndex) + input + text.substr(this.selectionEndIndex, text.length);
					} else {
						strValor = input;
					}
					
					var valor:Number = NumeroUtil.converterMoedaParaNumeroDecimal(strValor);
					
					if(valor > valorMaximo) {
						validarCampoNumericoMaiorQuePermitido(valor)
						event.preventDefault();
						return;
					} else if (maxChars != 0 && valor.toString().length > maxChars) {
						event.preventDefault();
						return;
					} else if (isNaN(valor)) {
						event.preventDefault();
						return;
					}
					
					// Define o valor
					text = valor.toString();
				}
			} else {
				// ignora caracteres de controle
				var codigo:Number = input.charCodeAt();
				
				if(isNaN(codigo) || (codigo > -1 && codigo < 31) || codigo == 127) {
					event.preventDefault();
				}
			}
		}
		
		private function destroyTip():void {
			if (tip) {
				ToolTipManager.destroyToolTip(tip);
				tip = null;
			}
		}    
		
		private function validarCampoNumericoMaiorQuePermitido(valor:Number):void {
			var mensagem:String = 
				(_validarMensagemMaximo == "" ? "O valor máximo permitido é " + NumeroUtil.formatarNumeroDecimalParaMoeda(_valorMaximo, casasDecimais) : _validarMensagemMaximo);
			
			if (tip) {
				destroyTip();
			}
			
			tip = ToolTipManager.createToolTip(mensagem, getBounds(root).x + width + length, getBounds(root).y - 2, "errorTipRight", this) as ToolTip;
		}
		
		/**
		 * Aplica formatação no valor
		 */	
		protected function formatarNumero():void{
			if(this.text != "")
				this.text = FormataNumero.formata(_valor, _casasDecimais, _agruparDigitos, _arredondamento);
		}
		
		//*****************************
		// Métodos herdados: IValidavel
		//*****************************
		
		/**
		 * Método chamado para realizar a validação do componente
		 * 
		 * @return Retorna um ResultadoValidacaoDTO, com  as propriedades preenchidas
		 * 
		 * @see			br.com.bancoob.dto.ResultadoValidacaoDTO
		 */
		public function realizarValidacao():ResultadoValidacaoDTO {
			var resultado:ResultadoValidacaoDTO = new ResultadoValidacaoDTO();
			
			if (!validar()) {
				resultado.adicionarMensagem(this.errorString);
				resultado.valido = false;
				resultado.campoFoco = this;
			}
			
			return resultado;
		}
		
		/**
		 * Verificar se e Numero
		 */	
		protected function ehNumero(valor:String):Boolean{
			
			var retorno : Boolean = false;
			if (!isNaN(Number(valor))) {
				retorno = true;
			}
			return retorno;
		}
	}
}