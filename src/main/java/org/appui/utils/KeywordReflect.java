package appui.utils;

import java.util.HashMap;
import java.util.Map;
import appui.pageObject.Login;

/*映射表*/
public class KeywordReflect {
	  public static Map<String, Map<String, String>> KEYWORD_POOLS = new HashMap<String, Map<String, String>>();
	     
	    static{
		        
	        KEYWORD_POOLS.put("click", KeywordReflect.methodInfo(ElementAction.class.getName(), "click"));
	        KEYWORD_POOLS.put("sleep", KeywordReflect.methodInfo(ElementAction.class.getName(), "sleep"));
	        KEYWORD_POOLS.put("input", KeywordReflect.methodInfo(ElementAction.class.getName(), "type"));
	        KEYWORD_POOLS.put("leftswipe", KeywordReflect.methodInfo(ElementAction.class.getName(), "leftSwipe"));
	        KEYWORD_POOLS.put("VerifyId", KeywordReflect.methodInfo(Assertion.class.getName(), "VerityIdPresent"));
	        KEYWORD_POOLS.put("----", KeywordReflect.methodInfo(ElementAction.class.getName(), "sleep"));
	        KEYWORD_POOLS.put("verify", KeywordReflect.methodInfo(Assertion.class.getName(), "VerityElementPresent"));
	      
	    }
	     
	    public static Map<String, String> methodInfo(String className, String methodName){
	        Map<String, String> methodInfo = new HashMap<String, String>();
	        methodInfo.put("class", className);
	        methodInfo.put("method", methodName);
	        return methodInfo;
	    }

}
