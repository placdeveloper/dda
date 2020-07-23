package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes
{
	import flash.events.FocusEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.campos.CampoHora;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.sicoob.sisbr.sicoobdda.comumflex.util.MensagensComum;

	public class DataHora extends DataHoraView {
		
		private var _dataHora:IDateTime;
		private var _data:Date;
		private var _hora:String = "";
		
		public function DataHora() {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
			
		private function init(e:FlexEvent):void {
			campoHora.tipoHora = CampoHora.TIPO_HORA_DIA;
			campoHora.addEventListener(FocusEvent.FOCUS_OUT, horaInserida);
			campoData.addEventListener(FocusEvent.FOCUS_OUT, dataInserida);
		}
		
		public function get dateTime():IDateTime {
			return _dataHora;
		}

		private function horaInserida(e:FocusEvent):void {
			if (!campoHora.realizarValidacao().valido) {
				MensagensComum.exibirMensagemErro("Formato de hora inválido!", campoHora);
			} else {
				horaSelecionada = campoHora.text;
				if (_hora.length == 8 && !campoData.selectedDate) {
					dataSelecionada = new Date();
				}
			}
		}
		
		public function limpar():void {
			if (campoData) {
				dataSelecionada = null;
			}
			if (campoHora) {
				horaSelecionada = "";
			}
		}
		
		private function dataInserida(e:FocusEvent):void {
			if (!campoData.selectedDate) {
				return;
			}else if (campoData.selectedDate) {
				dataSelecionada = campoData.selectedDate;
			}else if (_hora.length == 8 && !campoData.selectedDate) {
				dataSelecionada = new Date();
			}
			
		}
		
		public function set dataSelecionada(data:Date):void {
			_data = campoData.selectedDate = data;
			preparaDateTime();
		}
		
		public function get dataSelecionada():Date {
			return _data;
		}
		
		public function set horaSelecionada(hora:String):void {
			_hora = campoHora.text = hora;
			preparaDateTime();
		}
		
		public function preparaDateTime():void {
			if (_data) {
				_dataHora = DateTimeBase.getDateTime(_data);
				addHora();
			} else {
				_dataHora = null;
			}
		}
		
		private function addHora():void {
			if (_dataHora && _hora && _hora.length == 8) {
				var horas:Array = _hora.split(":");
				_dataHora.data.hours = horas[0];
				_dataHora.data.minutes = horas[1];
				_dataHora.data.seconds = horas[2];
			}
		}
		
		public function get horaSelecionada():String {
			return _hora;
		}
		
		public function validar():Boolean {
			var dataValido:Boolean = campoData.realizarValidacao().valido;
			var horaValido:Boolean = campoHora.realizarValidacao().valido;
			return dataValido && horaValido;
		}
		
		public function listaMensgensValidacaoData():ArrayCollection {
			return campoData.realizarValidacao().mensagens;
		}
	}
}