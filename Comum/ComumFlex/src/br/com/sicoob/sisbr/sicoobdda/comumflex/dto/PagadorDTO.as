package br.com.sicoob.sisbr.sicoobdda.comumflex.dto {

	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;
	
	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PagadorDTO", PagadorDTO);

	public class PagadorDTO {

		private var _bolPagadorEletronico:Boolean;
		private var _listaNumCCO:ArrayCollection;
		private var _listaPagadorAgregado:ArrayCollection;
		private var _listaMensagemPendente:ArrayCollection;
		private var _listaHistPagadorEletronico:ArrayCollection;
		
		private var _numCpfCnpj:String;
		private var _nomePessoa:String;
		private var _idInstituicao:Number;
		
		private var _idPagador:Number;
		private var _cooperativa:String;
		private var _descSituacaoCIP:String;
		private var _qtdAdesaoDDA:Number;
		private var _dataAdesaoDDA:IDateTime;
		private var _bolPagadorEletronicoSicoob:Boolean;
		private var _qtdAgregado:Number;
		private var _numAgencia:Number;
		private var _listaAgencia:ArrayCollection;
		
		private var _idSingular:Number;
		
		private var _numDDDResidencial:String;
		private var _numTelefoneResidencial:String;
		private var _numDDDCelular:String;
		private var _numTelefoneCelular:String;
		private var _descLogradouro:String;
		private var _descComplemento:String;
		private var _descNumero:String;
		private var _nomeBairro:String;
		private var _nomeLocalidade:String;
		private var _siglaUF:String;
		private var _codCep:String;
		private var _numCpfCnpjAgregado:String;
		private var _nomePessoaAgregado:String;

		
		

		public function PagadorDTO(bolPagadorEletronico:Boolean = false) {
			this._bolPagadorEletronico = bolPagadorEletronico;
		}
		
		public function set bolPagadorEletronico(bolPagadorEletronico:Boolean):void {
			this._bolPagadorEletronico = bolPagadorEletronico;
		}

		public function get bolPagadorEletronico():Boolean {
			return _bolPagadorEletronico;
		}

		public function set listaNumCCO(listaNumCCO:ArrayCollection):void {
			this._listaNumCCO = listaNumCCO;
		}

		public function get listaNumCCO():ArrayCollection {
			return _listaNumCCO;
		}
		
		public function set listaPagadorAgregado(listaPagadorAgregado:ArrayCollection):void {
			this._listaPagadorAgregado = listaPagadorAgregado;
		}
		
		public function get listaPagadorAgregado():ArrayCollection {
			return _listaPagadorAgregado;
		}

		public function set listaMensagemPendente(listaMensagemPendente:ArrayCollection):void {
			this._listaMensagemPendente = listaMensagemPendente;
		}

		public function get listaMensagemPendente():ArrayCollection {
			return _listaMensagemPendente;
		}

		public function set numCpfCnpj(numCpfCnpj:String):void {
			this._numCpfCnpj = numCpfCnpj;
		}
		
		public function get numCpfCnpj():String {
			return _numCpfCnpj;
		}
		
		public function set idInstituicao(idInstituicao:Number):void {
			this._idInstituicao = idInstituicao;
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set listaHistPagadorEletronico(listaHistPagadorEletronico:ArrayCollection):void {
			this._listaHistPagadorEletronico = listaHistPagadorEletronico;
		}
		
		public function get listaHistPagadorEletronico():ArrayCollection {
			return _listaHistPagadorEletronico;
		}
		
		public function set nomePessoa(nomePessoa:String):void {
			this._nomePessoa = nomePessoa;
		}
		
		public function get nomePessoa():String {
			return _nomePessoa;
		}
		
		public function set cooperativa(cooperativa:String):void {
			this._cooperativa = cooperativa;
		}
		
		public function get cooperativa():String {
			return _cooperativa;
		}
		
		public function set descSituacaoCIP(descSituacaoCIP:String):void {
			this._descSituacaoCIP = descSituacaoCIP;
		}
		
		public function get descSituacaoCIP():String {
			return _descSituacaoCIP;
		}
		
		public function set bolPagadorEletronicoSicoob(bolPagadorEletronicoSicoob:Boolean):void {
			this._bolPagadorEletronicoSicoob = bolPagadorEletronicoSicoob;
		}
		
		public function get bolPagadorEletronicoSicoob():Boolean {
			return _bolPagadorEletronicoSicoob;
		}
		public function set qtdAgregado(qtdAgregado:Number):void {
			this._qtdAgregado = qtdAgregado;
		}
		
		public function get qtdAgregado():Number {
			return _qtdAgregado;
		}
		
		public function set qtdAdesaoDDA(qtdAdesaoDDA:Number):void {
			this._qtdAdesaoDDA = qtdAdesaoDDA;
		}
		
		public function get qtdAdesaoDDA():Number {
			return _qtdAdesaoDDA;
		}
		
		public function set dataAdesaoDDA(dataAdesaoDDA:IDateTime):void {
			this._dataAdesaoDDA = dataAdesaoDDA;
		}
		
		public function get dataAdesaoDDA():IDateTime {
			return _dataAdesaoDDA;
		}
		public function set numAgencia(numAgencia:Number):void {
			this._numAgencia = numAgencia;
		}
		
		public function get numAgencia():Number {
			return _numAgencia;
		}
		
		public function set idPagador(idPagador:Number):void {
			this._idPagador = idPagador;
		}
		
		public function get idPagador():Number {
			return _idPagador;
		}
		
		public function set idSingular(idSingular:Number):void {
			this._idSingular = idSingular;
		}
		
		public function get idSingular():Number {
			return _idSingular;
		}
		
		public function set listaAgencia(listaAgencia:ArrayCollection):void {
			this._listaAgencia = listaAgencia;
		}
		
		public function get listaAgencia():ArrayCollection {
			return _listaAgencia;
		}
		
		public function set numDDDResidencial(numDDDResidencial:String):void {
			this._numDDDResidencial = numDDDResidencial;
		}
		
		public function get numDDDResidencial():String {
			return _numDDDResidencial;
		}
		
		public function set numTelefoneResidencial(numTelefoneResidencial:String):void {
			this._numTelefoneResidencial = numTelefoneResidencial;
		}
		
		public function get numTelefoneResidencial():String {
			return _numTelefoneResidencial;
		}
		
		public function set numDDDCelular(numDDDCelular:String):void {
			this._numDDDCelular = numDDDCelular;
		}
		
		public function get numDDDCelular():String {
			return _numDDDCelular;
		}
		
		public function set numTelefoneCelular(numTelefoneCelular:String):void {
			this._numTelefoneCelular = numTelefoneCelular;
		}
		
		public function get numTelefoneCelular():String {
			return _numTelefoneCelular;
		}
		
		public function set descLogradouro(descLogradouro:String):void {
			this._descLogradouro = descLogradouro;
		}
		
		public function get descLogradouro():String {
			return _descLogradouro;
		}
		
		public function set descComplemento(descComplemento:String):void {
			this._descComplemento = descComplemento;
		}
		
		public function get descComplemento():String {
			return _descComplemento;
		}
		
		public function set descNumero(descNumero:String):void {
			this._descNumero = descNumero;
		}
		
		public function get descNumero():String {
			return _descNumero;
		}
		
		public function set nomeBairro(nomeBairro:String):void {
			this._nomeBairro = nomeBairro;
		}
		
		public function get nomeBairro():String {
			return _nomeBairro;
		}
		
		public function set nomeLocalidade(nomeLocalidade:String):void {
			this._nomeLocalidade = nomeLocalidade;
		}
		
		public function get nomeLocalidade():String {
			return _nomeLocalidade;
		}
		
		public function set siglaUF(siglaUF:String):void {
			this._siglaUF = siglaUF;
		}
		
		public function get siglaUF():String {
			return _siglaUF;
		}
		
		
		public function set codCep(codCep:String):void {
			this._codCep = codCep;
		}
		
		public function get codCep():String {
			return _codCep;
		}
		
		public function set numCpfCnpjAgregado(numCpfCnpjAgregado:String):void {
			this._numCpfCnpjAgregado = numCpfCnpjAgregado;
		}
		
		public function get numCpfCnpjAgregado():String {
			return _numCpfCnpjAgregado;
		}
		
		public function set nomePessoaAgregado(nomePessoaAgregado:String):void {
			this._nomePessoaAgregado = nomePessoaAgregado;
		}
		
		public function get nomePessoaAgregado():String {
			return _nomePessoaAgregado;
		}
		
	}
}