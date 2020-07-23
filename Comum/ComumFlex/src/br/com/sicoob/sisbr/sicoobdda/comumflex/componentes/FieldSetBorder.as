package br.com.sicoob.sisbr.sicoobdda.comumflex.componentes
{
	import flash.display.Graphics;
	import flash.geom.Point;
	
	import mx.core.EdgeMetrics;
	import mx.core.UITextField;
	import mx.skins.RectangularBorder;

	public class FieldSetBorder extends RectangularBorder
	{
	    protected var bm:EdgeMetrics;

	    override public function get borderMetrics ():EdgeMetrics
	    {
	        if (!bm)
	        {
	        	var radius:Number = getStyle("cornerRadius");
	        	var borderTop:Number = getStyle("borderThickness");
	        	var borderThickness:Number = getStyle("borderThickness");

	        	if (parent && parent is FieldSet)
	        	{
	        		var textHeight:Number = FieldSet(parent).titleTextField.getExplicitOrMeasuredHeight();

	        		if (borderTop < textHeight || borderTop < radius)
	        			borderTop = Math.max(textHeight, radius);
	        	}

	            bm = new EdgeMetrics(borderThickness, borderTop, borderThickness, borderThickness);
	        }

	        return bm;
	    }

	    protected var textField:UITextField;

	    protected function get title ():UITextField
	    {
	    	if (!textField)
	    	{
	    		if (parent && parent is FieldSet)
	    			textField = FieldSet(parent).titleTextField;
	    	}

	    	return textField;
	    }

		override protected function updateDisplayList (unscaledWidth:Number, unscaledHeight:Number):void
	    {
	        super.updateDisplayList(unscaledWidth, unscaledHeight);

			if (!title)
				return;
			
			var backgroundAlpha:Number = getStyle("backgroundAlpha");
			var backgroundColor:uint = getStyle("backgroundColor");
			var borderAlpha:Number = getStyle("borderAlpha");
			var borderColor:uint = getStyle("borderColor");
			var borderThickness:Number = getStyle("borderThickness");
			var radius:Number = getStyle("cornerRadius");
			var titleGap:Number = getStyle("titleGap");

			if (isNaN(titleGap))
				titleGap = 0;

			var top:Number = title.y + title.getExplicitOrMeasuredHeight() / 2;

			var startX:Number = Math.max(title.x - titleGap,
				titleGap + radius);
			var pt00:Point = new Point(startX, top);

			// Canto superior esquerdo
			var pt01:Point = new Point(radius, top);
			var pt02:Point = new Point(0, top);
			var pt03:Point = new Point(0, top + radius);

			// Canto inferior esquerdo
			var pt04:Point = new Point(0, height - radius);
			var pt05:Point = new Point(0, height);
			var pt06:Point = new Point(radius, height);

			// Canto inferior direito
			var pt07:Point = new Point(width - radius, height);
			var pt08:Point = new Point(width, height);
			var pt09:Point = new Point(width, height - radius);

			// Canto superior direito
			var pt10:Point = new Point(width, top + radius);
			var pt11:Point = new Point(width, top);
			var pt12:Point = new Point(width - radius, top);

			var endX:Number = Math.min(title.x + title.getExplicitOrMeasuredWidth() + titleGap,
				width - radius - titleGap);
			var pt13:Point = new Point(endX, top);

			var g:Graphics = graphics;
			g.clear();

			g.lineStyle(borderThickness, borderColor, borderAlpha, true);
			g.moveTo(pt00.x, pt00.y);
			g.beginFill(backgroundColor, backgroundAlpha);
			g.lineTo(pt01.x, pt01.y);
			g.curveTo(pt02.x, pt02.y, pt03.x, pt03.y);
			g.lineTo(pt04.x, pt04.y);
			g.curveTo(pt05.x, pt05.y, pt06.x, pt06.y);
			g.lineTo(pt07.x, pt07.y);
			g.curveTo(pt08.x, pt08.y, pt09.x, pt09.y);
			g.lineTo(pt10.x, pt10.y);
			g.curveTo(pt11.x, pt11.y, pt12.x, pt12.y);
			g.lineTo(pt13.x, pt13.y);
			g.lineStyle(0, 0, 0.0);
			g.lineTo(pt00.x, pt00.y);
			g.endFill();
	    }
	}
}