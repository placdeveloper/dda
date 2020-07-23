package br.com.sicoob.sisbr.sicoobdda.comumflex.dto
{
	import mx.collections.ArrayCollection;
	
	public class TreeSelecaoCheckCobrancaDTO
	{
		private var _label:String = "";
		private var _state:String = "";
		private var _object:Object = null;
		private var _parent:Object = null;
		private var _children:ArrayCollection = null;
		private var _id:String;
		private var _flagVisibledCheckBox:Boolean = true;
		
		public function TreeSelecaoCheckCobrancaDTO() { }
		
		public function get label(): String{
			return _label;
		}
		public function set label(label:String): void{
			this._label = label;
		}
		public function get id(): String{
			return _id;
		}
		public function set id(id:String): void{
			this._id = id;
		}
		public function get state(): String{
			return _state;
		}
		public function set state( state:String): void{
			this._state = state;
		}
		public function get object(): Object{
			return _object;
		}
		public function set object( obj:Object): void{
			this._object = obj;
		}
		public function get parent(): Object{
			return _parent;
		}
		public function set parent( parent:Object): void{
			this._parent = parent;
		}
		public function get children(): ArrayCollection{
			return _children;
		}
		public function set children( children:ArrayCollection): void{
			this._children = children;
		}
		
		public function get flagVisibledCheckBox():Boolean{
			return _flagVisibledCheckBox;
		}
		public function set flagVisibledCheckBox(flagVisibledCheckBox:Boolean): void{
			this._flagVisibledCheckBox = flagVisibledCheckBox;
		}
	}
}