package br.com.sicoob.sisbr.sicoobdda.comumflex.vo {

	import flash.net.registerClassAlias;
	

	registerClassAlias("br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDATextoInfoVO", BoletoDDATextoInfoVO);

	public class BoletoDDATextoInfoVO {

		private var _id:Number;
		
		private var _boletoDDA:BoletoDDAVO;
		
		private var _descTextoInformativo:String;
		

		public function set id(id:Number):void {
			this._id = id;
		}

		public function get id():Number {
			return _id;
		}
		
		public function set boletoDDA(boletoDDA:BoletoDDAVO):void {
			this._boletoDDA = boletoDDA;
		}
		
		public function get boletoDDA():BoletoDDAVO {
			return _boletoDDA;
		}
		
		public function set descTextoInformativo(descTextoInformativo:String):void {
			this._descTextoInformativo = descTextoInformativo;
		}
		
		public function get descTextoInformativo():String {
			return _descTextoInformativo;
		}
	}
}