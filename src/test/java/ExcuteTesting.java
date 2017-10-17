
import appui.action.LoginAction;
import appui.pageObject.GuildPage;
import appui.pageObject.Login;
import appui.utils.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
public class ExcuteTesting extends TestBaseCase {	
	 ElementAction action=new  ElementAction();
	Locator locator=new Locator();
	Login login=new Login();
	Assertion assertion=new Assertion();
	GuildPage guildPage=new GuildPage();
	TestBaseCase testcase=new  TestBaseCase();
	String lijigengxin="com.tongcheng.android:id/btn_dialog_right";
	String lijiguanbi="com.tongcheng.android:id/imgbtn_guanbi";


	/**
	@BeforeClass
	public  void  beforeclass() throws Exception
	{
				
		if(assertion.idExsit(login.立即更新()))
		{
			//action.click(exe.getLocator("立即更新"));
			action.click(login.关闭更新());
		}
	}
	**/
	@Test(description = "测试执行")
	 public  void login() throws Exception {
		action.sleep(15);
		if(assertion.isIdExsit(driver,lijiguanbi))
		{
			driver.findElementById(lijiguanbi).click();
		}
		
		else
		{
			log.info("不需要更新");
		}

			
		
		Executor e = new Executor();
		String filePath="D:/bindcard.xls";
    	int rows=ExcelReadUtil.getExcelRows(filePath);
    	log.info("共有"+rows+"行");
    	for(int i=1;i<rows;i++)
    	{
    		e.executor(i);
    	}
    	Iterator iterator = assertion.assertInfolList.iterator();
    	while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    	log.info("测试用例执行完毕");
    	log.info("成功"+assertion.correctIndex+"条");
    	log.info("失败"+assertion.errorIndex+"条");
		
	}
	
	public static void main(String args[])
	{
		ExcuteTesting excuteTesting=new ExcuteTesting();
		try {
			excuteTesting.login();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
