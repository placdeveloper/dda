package br.com.sicoob.sisbr.sicoobdda.operacional.modulos.pagador
{
	
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.controls.CheckBox;
	
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.dto.PagadorAgregadoDTO;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.vo.RegistroVO;
	
	registerClassAlias("RegistroVO", RegistroVO);
	
	public class AbaPrincipal extends AbaPrincipalView {
		
		private var _checkBoxHeaderTodos:CheckBox;
		private var _listaAgregadoDto:ArrayCollection;
		
		[Bindable]
		public var selecionarTodos:Boolean = false;
		
		/**
		 *  Construtor.
		 */
		public function AbaPrincipal()	{
			super();
			
		}
		
		public function configurarDestinosServicos(destino:DestinoVO):void{
			this.destino = destino;
		}	
		
		//--------------------------------------------------------------------------
		//  Métodos das interfaces
		//--------------------------------------------------------------------------
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};
		
		
		public function tratarSelecionar(dados:Object, selecionado:Boolean):void {
			dados.selecionado = selecionado;
			
			if (!selecionado) {
				selecionarTodos = false;
				
				if (_checkBoxHeaderTodos) {
					_checkBoxHeaderTodos.selected = false;
				}
			} else if (!verificaSeTodosItensSelecionados()) {
				selecionarTodos = true;
				
				if (_checkBoxHeaderTodos) {
					_checkBoxHeaderTodos.selected = true;
				}
			}
		}
		
		public function tratarSelecionarTodos():void {
			selecionarTodos = !selecionarTodos;
			
			for each(var agregado:PagadorAgregadoDTO in  _listaAgregadoDto) {
				agregado.selecionado = selecionarTodos;
				_listaAgregadoDto.itemUpdated(agregado);
			}
		}
		
		private function verificaSeTodosItensSelecionados():Boolean {
			for each(var agregado:PagadorAgregadoDTO in  gridAgregados.dataProvider) {
				if(!agregado.selecionado) {
					return true;
				}
			}
			return false;
		}
		
		public function definirHeaderCheckBox(cb:Object):void {
			if (cb is CheckBox) {
				_checkBoxHeaderTodos = (cb as CheckBox);
			}
		}
		
	}
}
