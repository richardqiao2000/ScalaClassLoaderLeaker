package org.richardqiao.memory

import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class DataInvationHandler extends InvocationHandler{
    private var obj: Object = null
    def this(obj: Object) {
      this()
      this.obj = obj;
    }
    override def invoke(proxy: Any, m: Method, args: Array[Object]): Object = {
      var result: Object = null;
      try{
      	result = m.invoke(obj, args);
	    }catch{
	      case e1: InvocationTargetException => throw e1
	      case e2: Exception => throw e2
	    }
        
        return result;
    }
}
