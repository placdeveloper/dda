package br.com.sicoob.sisbr.sicoobdda.comumflex.util {
	
	import mx.managers.PopUpManager;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	
	public class MensagensComum {
		
		/**
		 * OBS: Apenas as mensagens genéricas
		 */
		public static const MSG001:String = "<<STRING>> salvo com sucesso.";
		public static const MSG002:String = "<<STRING>> salva com sucesso."; // MSG017
		
		public static const MSG003:String = "<<STRING>> alterado com sucesso."; // MSG002
		public static const MSG004:String = "<<STRING>> alterada com sucesso."; // MSG006
		
		public static const MSG005:String = "Deseja realmente excluir o(s) <<STRING>> selecionado(s)?"; // MSG005
		public static const MSG006:String = "Deseja realmente excluir a(s) <<STRING>> selecionada(s)?";
		public static const MSG007:String = "Deseja realmente excluir o <<STRING>> selecionado?"; // MSG014
		public static const MSG008:String = "Deseja realmente excluir a <<STRING>> selecionada?"; // MSG015
		
		public static const MSG009:String = "<<STRING>> já cadastrado.";
		public static const MSG010:String = "<<STRING>> já cadastrada."; // MSG008
		
		public static const MSG011:String = "Nenhum <<STRING>> foi selecionado."; // MSG007
		public static const MSG012:String = "Nenhuma <<STRING>> foi selecionada."; // MSG018
		
		public static const MSG013:String = "O filtro precisa conter pelo menos <<STRING>> letras."; // MSG010
		public static const MSG014:String = "O filtro precisa ser informado."; // MSG011
		
		public static const MSG015:String = "A <<STRING>> informada deve ser maior que <<STRING>>."; // MSG016
		public static const MSG016:String = "O <<STRING>> informado deve ser maior que <<STRING>>.";
		
		public static const MSG017:String = "O campo <<STRING>> é de preenchimento obrigatório."; // MSG013

		public static const MSG018:String = "Nenhum <<STRING>> encontrado.";
		
		public static const MSG019:String = "<<STRING>> não cadastrado na Base de Dados.";
		
		public static const MSG020:String = "Nenhum <<STRING>> encontrado.\nDeseja inserir um novo <<STRING>>?";
		
		public static const MSG021:String = "A data <<STRING>>inicial deve ser preenchida.";
		public static const MSG022:String = "A data <<STRING>>final deve ser preenchida.";
		public static const MSG023:String = "A data <<STRING>>final deve ser maior que a data inicial.";
		public static const MSG024:String = "O período não pode ser superior a <<STRING>> dias.";
		public static const MSG025:String = "É obrigatorio informar o periodo para pesquisa.";
		
		public static const MSG026:String = "Mensagem(s) tratada(s) com sucesso.";
		
		public static const MSG027:String = "<<STRING>> excluído com sucesso.";
		public static const MSG028:String = "<<STRING>> excluída com sucesso.";
		
		public static const MSG029:String = "Deseja realmente excluir as <<STRING>> selecionadas?";
		
		public static const MSG030:String = "<<STRING>> inválido.";
		public static const MSG031:String = "A hora final deve ser superior à hora inicial.";
		
		public static const MSG032:String = "Você deve escolher um tipo de consulta.";
		public static const MSG033:String = "Os dois campos de número identificador boleto precisam ser preenchidos.";
		public static const MSG034:String = "Número identificador boleto final menor que o inicial.";
		
		public static const MSG035:String = "Tamanho de <<STRING>> inválido! Preencha com <<STRING>> números.";
		public static const MSG036:String = "Você deve escolher o domínio do boleto.";
		
		public static const MSG037:String = "Data de Vencimento não deve ser superior a <<STRING>> dias.";
		public static const MSG038:String = "Data de Registro DDA não deve ser superior a <<STRING>> dias.";
		
		public static const MSG039:String = "O boleto não foi encontrado em nossa base. Deseja realizar a consulta na base da CIP? Em alguns instantes poderá verificar o resultado.";
		
		public static const MSG040:String = "Consulta na base da CIP feita com Sucesso.";
		
		public static const MSG041:String = "Nenhuma <<STRING>> encontrada.";
		
		public function MensagensComum() {}
		
		/**
		 * Formata a mensagem.
		 * <br>
		 * <br>
		 * <br><b>Data:</b>	    07/10/2011
		 * <br><b>Versão:</b>	1.0
		 * <br>
	     * <br><b>Histórico de alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
		 * @param codMensagem: String
		 * 			Código da mensagem a ser exibida.
		 * @param ... arrayArgumentos
		 * 			Parametros a serem inseridos ma mensagem.
		 * @return retorno: String
		 * 			Mensagem formatada.
		 */		 		
        public static function formatar(codMensagem: String, ... arrayArgumentos): String {
    		var retorno: String = null;
    		
    		for(var i: int = 0; i < arrayArgumentos.length; i++){
    			var argumento:String = String(arrayArgumentos[i]);
    			
    			if(retorno == null){
    				retorno = codMensagem.replace("<<STRING>>", argumento);
    			}else{
    				retorno = retorno.replace("<<STRING>>", argumento);   		  			
    			}
    		}
    		return retorno;
        }		
        
		/**
	     * Exibe uma mensagem de Sucesso.
	     * <br>
		 * <br>
		 * <br><b>Data:</b>	    07/10/2011
		 * <br><b>Versão:</b>	1.0
		 * <br>
	     * <br><b>Histórico de alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
		 * @param mensagem: String
		 * 			Mensagem a ser exibida.
	     */		        
        public static function exibirMensagemSucesso(mensagem: String, retorno:Function = null): void {
			Alerta.show(mensagem, "Sucesso", Alerta.ALERTA_INFORMACAO, null, retorno, null, Alerta.ALERTA_OK);
        }	        	
		
		/**
	     * Exibe uma mensagem de erro.
	     * <br>
		 * <br>
		 * <br><b>Data:</b>	    07/10/2011
		 * <br><b>Versão:</b>	1.0
		 * <br>
	     * <br><b>Histórico de alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
		 * @param mensagem: String
		 * 			Mensagem a ser exibida.
		 * @param campoFoco: Object = null
		 * 			Componente que irá receber o foco.
	     */
        public static function exibirMensagemErro(mensagem: String, campoFoco: Object = null): void {
			Alerta.show(mensagem, "ERRO DE VALIDAÇÃO", Alerta.ALERTA_ERRO, campoFoco, null, null, Alerta.ALERTA_OK);
        }
        
		/**
	     * Exibe uma mensagem de alerta para avisar que uma operação não poderá ser realizada.
	     * <br>
		 * <br>
		 * <br><b>Data:</b>	    07/10/2011
		 * <br><b>Versão:</b>	1.0
		 * <br>
	     * <br><b>Histórico de alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
		 * @param mensagem: String
		 * 			Mensagem a ser exibida.
	     */		        
        public static function exibirMensagemAlerta(mensagem: String, campoFoco: Object = null): void {
			Alerta.show(mensagem, "Atenção", Alerta.ALERTA_OK, campoFoco, null, null, Alerta.ALERTA_OK);	
        }
		
		/**
	     * Exibe uma mensagem de Informação.
	     * <br>
		 * <br>
		 * <br><b>Data:</b>	    07/10/2011
		 * <br><b>Versão:</b>	1.0
		 * <br>
	     * <br><b>Histórico de alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
		 * @param mensagem: String
		 * 			Mensagem a ser exibida.
	     */		        
        public static function exibirMensagemInformacao(mensagem: String): void {
			Alerta.show(mensagem, "Informação", Alerta.ALERTA_INFORMACAO);	  
        }
        
		/**
	     * Exibe uma mensagem de Confirmação.
	     * <br>
		 * <br>
		 * <br><b>Data:</b>	    07/10/2011
		 * <br><b>Versão:</b>	1.0
		 * <br>
	     * <br><b>Histórico de alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
		 * @param mensagem: String
		 * 			Mensagem a ser exibida.
		 * @param campoFoco: Object = null
		 * 			Componente que irá receber o foco.
		 * @param funcaoRetorno: Function=null
		 * 			Função.
		 * @param funcaoRetorno2: Function=null
		 * 			Função.
		 * @param funcaoRetorno3: Function=null
		 * 			Função.
	     */		        
        public static function exibirMensagemConfirmacao(mensagem: String, campoFoco: Object=null, funcaoRetorno: Function=null, funcaoRetorno2: Function=null, funcaoRetorno3: Function=null): void {
			Alerta.show(mensagem, "Confirmação", Alerta.ALERTA_PERGUNTA, campoFoco, funcaoRetorno, funcaoRetorno2, Alerta.ALERTA_NAO, funcaoRetorno3);	
        }
		
		public static function criarAlerta(msg:String, titulo:String, tipoAlerta:int, funcaoRetorno:Function = null):Alerta {
			
			//verifica bloqueio se existir um ele remove
			if (MostraCursor.bloqueio) {
				MostraCursor.removeBusyCursor();
			}
			
			//cria alerta
			var msgAlerta:Alerta = new Alerta();
			
			//seta atributos
			msgAlerta.mensagem = msg;
			msgAlerta.tituloAlerta = titulo;
			msgAlerta.tpAlerta = tipoAlerta;
			msgAlerta.funcRetorno = funcaoRetorno;
			
			/*var janelaExterna:Object = Janela.obterJanelaExternaAberta();
			
			if (janelaExterna) {
				//exibe alerta 
				PopUpManager.addPopUp(msgAlerta, Sprite(janelaExterna),true,PopUpManagerChildList.POPUP);
			} else {
				//exibe alerta 
				PopUpManager.addPopUp(msgAlerta, Sprite(Application.application),true, PopUpManagerChildList.POPUP);
			}*/
			PopUpManager.centerPopUp(msgAlerta);
			return msgAlerta;
		}
		
	}
}