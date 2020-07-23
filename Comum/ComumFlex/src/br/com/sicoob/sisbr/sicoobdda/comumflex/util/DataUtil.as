package br.com.sicoob.sisbr.sicoobdda.comumflex.util {
	import mx.formatters.DateFormatter;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataData;

	public class DataUtil {

		private static var formataDt:DateFormatter = new DateFormatter();
		
		/**
	 	 * Efetua a soma de dias em uma data.
	 	 */
		public static function somarDiasData(aDate:Date, aDias:int):Date {
			var millisecondsPerDay:int = 1000 * 60 * 60 * 24;
			return new Date(aDate.getTime() + (aDias * millisecondsPerDay));
		}
		
		/**
	 	 * Retorna a data informada com a hora atual.
		 * 
		 * Utilizado para informar a data de movimento e ter a hora atual.
	 	 */
		public static function dataComHoraAtual(data:Date):Date {
			var dataAgora:Date = new Date();
			return new Date(data.fullYear, data.month, data.date, dataAgora.hours, dataAgora.minutes, dataAgora.seconds);
		}
		
		/**
		 * Utilizado para comparar duas datas.
		 * Retorna Number:
		 * 	-> 0, datas iguais;
		 *  -> 1, data1 maior que data2;
		 *  -> -1, data1 menor que data2;
		 */
		public static function compareData(data1:Date, data2:Date):Number {
			if(data1.getTime() == data2.getTime()) {
				return 0;
			} else if(data1.getTime() > data2.getTime()) {
				return 1;
			} else {
				return -1;
			}
		}
		
		/**
		 * Utilizado para comparar duas datas com hh:mm:ss.
		 * Retorna Number:
		 * 	-> 0, datas iguais;
		 *  -> 1, data1 maior que data2;
		 *  -> -1, data1 menor que data2;
		 */
		public static function compareDataTime(data1:IDateTime, data2:IDateTime):Number {
			if(data1.data.getTime() == data2.data.getTime()) {
				return 0;
			} else if(data1.data.time > data2.data.time) {
				return 1;
			} else {
				return -1;
			}
		}
		
		/**
		 * Utilizado para validar o range de datas informado.
		 */
		public static function validaRangeData(data1:Date, data2:Date, qtdDias:Number):Boolean {
			var retornoCompareData:Number = compareData(data1, data2);
			var dataAux:Date;
			if(retornoCompareData == 0) {
				return true;
			}
			if(retornoCompareData == 1) {
				dataAux = somarDiasData(data2, qtdDias);
				if(compareData(dataAux, data1) == 1) {
					return true;
				} else {
					return false;
				}
			} else {
				dataAux = somarDiasData(data1, qtdDias);
				if(compareData(dataAux, data2) == 1) {
					return true;
				} else {
					return false;
				}
			}			
		}

		/**
		 * Transforma uma data do formato UTC para o locale corrente
		 */
		public static function getData(aDataUTC: Date): Date {
			if (aDataUTC == null) {
				return null;
			}
			
			return new Date(aDataUTC.fullYearUTC, aDataUTC.monthUTC, 
				aDataUTC.dateUTC, aDataUTC.hoursUTC - 3, aDataUTC.minutesUTC, 
				aDataUTC.secondsUTC, aDataUTC.millisecondsUTC);
		}
		
		/**
		 * Tranforma uma data do locale corrente para o formato UTC
		 */		
		public static function getDataUTC(aData: Date): Date {
			if (aData == null) {
				return null;
			}
			
			var sDate:Date = new Date(Date.UTC(aData.fullYear, aData.month, aData.date, 
				aData.hours + 3, aData.minutes, aData.seconds, aData.milliseconds));
			return sDate;
		}
		
		/**
		 * Retorna a data formatada com data e hora 
		 */
		public static function formataDataHora(aData: Date): String {
			if (aData == null) {
				return null;
			}
			return FormataData.formataDataHora(aData,"dd/MM/yyyy JJ:NN:SS");
		}
		
		/**
		 * Tranforma uma date 00:00:00 - 23:59:59
		 */	
		public static function formataHora(data:Date, mascara:String = "") : String
		{
			if(data != null)
			{
				if(data.getFullYear() > 1)
				{
					if(mascara == "" || mascara == null)
					{
						formataDt.formatString = "JJ:NN:SS";
					}
					else
					{
						formataDt.formatString = mascara.toUpperCase();
					}
					return formataDt.format(data);
				}
				return "";
			}
			return "";
		}
	}
}