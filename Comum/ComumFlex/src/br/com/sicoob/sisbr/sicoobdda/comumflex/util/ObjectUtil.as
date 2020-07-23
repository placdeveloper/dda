package br.com.sicoob.sisbr.sicoobdda.comumflex.util
{
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.util.StringUtils;

	public class ObjectUtil
	{
		public function ObjectUtil()
		{
		}
		
		public static function isNullOrBlank(param:String):Boolean {
			var ret:String = null;
			if(param) {
			 	ret = StringUtils.trim(param);
			}
			return !(ret && ret.length > 0);
		}
		
		public static function isNullOrEmpty(param:Number):Boolean {
			return !(param && param >= 0);
		}
		
		public static function isNull(param:Object):Boolean {
			return param == null;
		}
		
		public static function isNullOrEmptyList(param:ArrayCollection):Boolean {
			return !(param && param.length > 0);
		}
		
	}
}