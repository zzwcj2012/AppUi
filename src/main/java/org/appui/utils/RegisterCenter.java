package appui.utils;

import java.util.HashMap;
import java.util.Map;
import appui.pageObject.Login;


/*对象池*/
public class RegisterCenter {

	public static Map<String, Object> OBJ_POOLS = new HashMap<String, Object>();
	static{
        OBJ_POOLS.put(ElementAction.class.getName(), new ElementAction());
        OBJ_POOLS.put(Assertion.class.getName(), new Assertion());
        OBJ_POOLS.put(Login.class.getName(), new Login());
       
    }
}
