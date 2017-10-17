package appui.action;


import appui.pageObject.Login;
import appui.utils.ElementAction;
import appui.utils.TestBaseCase;

import java.io.IOException;


public class LoginAction extends TestBaseCase {
    public  LoginAction(String username,String password) throws IOException {
        ElementAction action=new ElementAction();
   
      
        action.sleep(1);
        
    }
}
