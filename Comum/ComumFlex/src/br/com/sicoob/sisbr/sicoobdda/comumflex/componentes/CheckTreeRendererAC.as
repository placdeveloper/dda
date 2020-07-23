package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes{
	
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ICollectionView;
	import mx.collections.IViewCursor;
	import mx.controls.CheckBox;
	import mx.controls.Tree;
	import mx.controls.treeClasses.ITreeDataDescriptor;
	import mx.controls.treeClasses.TreeItemRenderer;
	import mx.controls.treeClasses.TreeListData;
	import mx.core.IFactory;
	
	public class CheckTreeRendererAC extends TreeItemRenderer implements IFactory{
		
		protected var myCheckBox:CheckBox;
		
		public function CheckTreeRendererAC(){
			super();
			this.mouseEnabled = false;
			this.disclosureIcon = null;
			this.icon = null;
			this.setStyle("verticalAlign", "middle");
		}
		
		private function toggleParents(item:Object, tree:Tree, state:String):void {
			if (item == null){
				return;
			}
			else{
				item.state = state;
				// tree.openItems = item; tree.openItems = null;
				toggleParents(tree.getParentItem(item), tree, getState(tree, tree.getParentItem(item)));
			}
		}
		
		private function toggleChildren (item:Object, tree:Tree, state:String):void{
			if(item == null){return;}
			item.state = state;
			var treeData:ITreeDataDescriptor = tree.dataDescriptor;
			if(treeData.hasChildren(item)){
				var children:ICollectionView = treeData.getChildren (item);
				var cursor:IViewCursor = children.createCursor();
				while (!cursor.afterLast){
					toggleChildren(cursor.current, tree, state);
					cursor.moveNext();
				}
			}
		}
		
		private function getState(tree:Tree, parent:Object):String{
			var noChecks:int = 0;
			var noCats:int = 0;
			var noUnChecks:int = 0;
			if(parent != null){
				var treeData:ITreeDataDescriptor = tree.dataDescriptor;
				var cursor:IViewCursor = treeData.getChildren(parent).createCursor();
				while(!cursor.afterLast){
					if(cursor.current.state == CheckBoxTreeEnum.CHECKED){
						noChecks++;
					}
					else if(cursor.current.state == CheckBoxTreeEnum.UNCHECKED){
						noUnChecks++
					}
					else{
						noCats++;
					}
					cursor.moveNext();
				}
			}
			if((noChecks > 0 && noUnChecks > 0) || (noCats > 0)){
				return CheckBoxTreeEnum.PARTIALLY_CHECKED;
			}
			else if(noChecks > 0) {
				return CheckBoxTreeEnum.CHECKED;
			}
			else{
				return CheckBoxTreeEnum.UNCHECKED;
			}
		}
		
		private function checkBoxToggleHandler(event:MouseEvent):void {            
			if(data){
				var myListData:TreeListData = TreeListData(this.listData);
				var selectedNode:Object = myListData.item;
				var tree:Tree = Tree(myListData.owner);                
				var toggle:Boolean = myCheckBox.selected;
				if(toggle){
					toggleChildren(data, tree, CheckBoxTreeEnum.CHECKED);
				}
				else {
					toggleChildren(data, tree, CheckBoxTreeEnum.UNCHECKED);
				}
				var parent:Object = tree.getParentItem(data);
				toggleParents(parent, tree, getState(tree, parent));
				
				var lista:ArrayCollection = tree.dataProvider as ArrayCollection;
				
				// Após realizar todo os processor é necessário atualizar a lista para que os dados da tree sejam atualizados na tela, caso contrário
				// não é feito o check/uncheck corretamente.   
				for (var i:int = 0; i < lista.length; i++) {
					lista.itemUpdated(lista.getItemAt(i));
				}
			}
		}
		
		private function imageToggleHandler(event:MouseEvent):void {
			myCheckBox.selected = !myCheckBox.selected;
			checkBoxToggleHandler(event);
		}
		
		override protected function createChildren():void {
			super.createChildren();
			myCheckBox = new CheckBox;
			myCheckBox.setStyle("verticalAlign", "middle");
			myCheckBox.addEventListener(MouseEvent.CLICK, checkBoxToggleHandler);            
			addChild(myCheckBox);            
			myCheckBox.setStyle("paddingTop", 15);
		}    
		
		private function setCheckState (checkBox:CheckBox, value:Object, state:String):void{
			switch(state){
				case CheckBoxTreeEnum.CHECKED:
					checkBox.selected = true;
					break;
				case CheckBoxTreeEnum.UNCHECKED:
					checkBox.selected = false;
					break;
				case CheckBoxTreeEnum.PARTIALLY_CHECKED:
					checkBox.selected = false;
					break;
			}
		}
		
		override public function set data(value:Object):void {
			if (value != null) {
				super.data = value;
				var _tree:Tree = Tree(this.parent.parent);
				setCheckState(myCheckBox, value, value.state);
			}
		}
		
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void {
			super.updateDisplayList(unscaledWidth, unscaledHeight);
			if(super.data){
				if (super.icon != null){
					myCheckBox.x = super.icon.x;
					myCheckBox.y = 2;
					super.icon.x = myCheckBox.x + myCheckBox.width + 17;
					super.label.x = super.icon.x + super.icon.width + 3;
					myCheckBox.setStyle("borderColor", 0x666666);
				}
				else{
					myCheckBox.x = super.label.x;
					myCheckBox.y = 2;
					super.label.x = myCheckBox.x + myCheckBox.width + 17;
					myCheckBox.setStyle("borderColor", 0x666666);
				}
				if(data.state == CheckBoxTreeEnum.PARTIALLY_CHECKED){
					myCheckBox.setStyle("borderColor", 0xFF4545);
				}
				else{
					myCheckBox.setStyle("borderColor", 0x666666);
				}
				myCheckBox.visible = data.flagVisibledCheckBox;
				if(!data.flagVisibledCheckBox){
					myCheckBox.x = myCheckBox.x - 20;
				}
			}
		}
		
		override protected function commitProperties():void
		{
			if(listData != null){
				super.commitProperties();
			}
		}
		
		override protected function measure():void{
			if(listData != null && label != null){
				super.measure();
			}
		}
		
		public function newInstance():*{
			return new CheckTreeRendererAC;
		}
	}    
}
