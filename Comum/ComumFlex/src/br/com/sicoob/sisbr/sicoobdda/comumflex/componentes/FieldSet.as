package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes
{
	import flash.events.Event;
	import flash.geom.Point;
	
	import mx.containers.Box;
	import mx.core.UITextField;
	import mx.styles.CSSStyleDeclaration;
	import mx.styles.StyleManager;

	[Style(name="titleAlign", type="String", enumeration="left,center,right", inherit="no")]

	[Style(name="titleGap", type="Number", inherit="no")]

	[Style(name="titlePlacement", type="String", enumeration="top", inherit="no")]

	[Style(name="titleStyleName", type="String", inherit="no")]

	public class FieldSet extends Box
	{

		private var _truncIfNecessary : Boolean = true;
		
		private static var defaultStylesInitialized:Boolean = setDefaultStyles();

		private static function setDefaultStyles ():Boolean
		{
			var s:CSSStyleDeclaration = StyleManager.getStyleDeclaration('FieldSet');
			if (!s)
				s = new CSSStyleDeclaration();
			
			if (!s.getStyle("titleStyleName"))
			{
				var tsn:CSSStyleDeclaration = new CSSStyleDeclaration();
				tsn.setStyle("fontWeight", "bold");
				
				s.setStyle("titleStyleName", tsn);
			}
			
			if (!s.getStyle("borderStyle"))
				s.setStyle("borderStyle", "solid");
			
			if (!s.getStyle("borderSkin"))
				s.setStyle("borderSkin", FieldSetBorder);
			
			if (!s.getStyle("backgroundColor"))
				s.setStyle("backgroundColor", 0xFFFFFF);
			
			if (!s.getStyle("backgroundAlpha"))
				s.setStyle("backgroundAlpha", 0.0);
			
			if (!s.getStyle("paddingLeft"))
				s.setStyle("paddingLeft", 2);
			
			if (!s.getStyle("paddingRight"))
				s.setStyle("paddingRight", 2);
			
			if (!s.getStyle("paddingTop"))
				s.setStyle("paddingTop", 2);
			
			if (!s.getStyle("paddingBottom"))
				s.setStyle("paddingBottom", 2);
			
			if (!s.getStyle("titleAlign"))
				s.setStyle("titleAlign", "left");
			
			if (!s.getStyle("titleGap"))
				s.setStyle("titleGap", 2);
			
			if (!s.getStyle("titlePlacement"))
				s.setStyle("titlePlacement", "top");

			StyleManager.setStyleDeclaration('FieldSet', s, true);

			return true;
		}

		protected var titleStyleNameChanged:Boolean = false;

		override public function styleChanged (styleProp:String):void
		{
			super.styleChanged(styleProp);

			var allStyles:Boolean = !styleProp || styleProp == "styleName";
			if (allStyles || styleProp == "titleAlign" || styleProp == "titleGap")
				titlePtChanged = true;

			if (allStyles || styleProp == "titleStyleName")
				titleStyleNameChanged = true;

			invalidateDisplayList();
		}

		override protected function createChildren ():void
		{
			super.createChildren();

			if (!textField)
			{
				textField = new UITextField();
				textField.mouseEnabled = false;
				textField.text = title;
				textField.styleName = getStyle("titleStyleName");

				rawChildren.addChild(textField);
			}
		}

		override protected function commitProperties ():void
		{
			super.commitProperties();

			if (titleTextChanged && textField)
			{
				textField.text = title;
				titleTextChanged = false;
			}
		}
		
		/**
		 * 
		 * @return 
		 * 
		 */
		public function get truncIfNecessary() : Boolean {
			return _truncIfNecessary;
		}
		
		/**
		 * 
		 * @param trunc
		 * 
		 */
		public function set truncIfNecessary(trunc : Boolean) : void {
			this._truncIfNecessary = trunc;
		}
		
		override protected function layoutChrome (unscaledWidth:Number, unscaledHeight:Number):void
		{
			super.layoutChrome(unscaledWidth, unscaledHeight);
			
			if (titleStyleNameChanged)
			{
				textField.styleName = getStyle('titleStyleName');
				titleStyleNameChanged = false;
				titlePtChanged = true;
			}

			if (titlePtChanged)
			{
				var pt:Point = getTitlePoint();
				var minX:Number = getStyle("cornerRadius") + getStyle("titleGap") + 5;
				var maxW:Number = getExplicitOrMeasuredWidth() - 2 * minX + 5;
				if ((pt.x <= minX || textField.getExplicitOrMeasuredWidth() >= maxW) && truncIfNecessary) {
					pt.x = minX;

					textField.setActualSize(maxW, textField.getExplicitOrMeasuredHeight());
					textField.toolTip = new String(textField.text);					
					textField.truncateToFit();
				}
				else
					textField.setActualSize(textField.getExplicitOrMeasuredWidth(), textField.getExplicitOrMeasuredHeight());

				textField.move(pt.x, pt.y);

				titlePtChanged = false;
			}
		}
		
		protected var titlePtChanged:Boolean = false;
		
		protected function getTitlePoint ():Point
		{
			var pt:Point = new Point();

			if (!textField)
				return pt;

			var nx:Number = 0;
					var ny:Number = 0;

			var ta:String = getStyle("titleAlign");
			var tg:Number = getStyle("titleGap");
			var cr:Number = getStyle("cornerRadius");

			switch (ta)
			{
				case "right":
				{
					nx = width - cr - borderMetrics.right - tg - textField.getExplicitOrMeasuredWidth() - 5;
					break;
				}

				case "center":
				{
					nx = (width - textField.getExplicitOrMeasuredWidth()) / 2;
					break;
				}

				case "left":
				default:
				{
					nx = cr + borderMetrics.left + tg + 5;
					break;
				}
			}

			if (pt.x != nx)
				pt.x = nx;

			return pt;
		}

		protected var titleText:String = "";

		protected var titleTextChanged:Boolean = false;

		[Bindable("titleChanged")]
		public function get title ():String
		{
				return titleText;
		}

		public function set title (value:String):void
		{
			if (titleText != value)
			{
				titleText = value;
				titleTextChanged = true;
				titlePtChanged = true;

				invalidateProperties();
				invalidateDisplayList();

				dispatchEvent(new Event("titleChanged"));
			}
		}

		protected var textField:UITextField;

		public function get titleTextField ():UITextField
		{
					return textField;
		}
	}
}