package appui.utils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import appui.pageObject.Login;

import io.appium.java_client.android.AndroidDriver;

public class Executor {
	 Login login=new Login();
	 SaveToXml save=new SaveToXml();
	 Assertion assertion=new Assertion();
	 ElementAction act=new ElementAction(); 
	 private Log log=new Log(this.getClass());
	 public static AndroidDriver driver;
     
 public void executor(int row) throws Exception{
        //String filePath="src/main/resources/data/bindcard.xls";
	    String filePath="D:/bindcard.xls";
        /** 读取第一行内容**/
        Object[][] elements=ExcelReadUtil.case_data_excel(0, 0, 0,0,filePath); 
        
        /** 读取actions内容**/
        Object[][] actions=ExcelReadUtil.case_data_excel(0,1, row,0,filePath);
       
		 /**action与excel2长度相同**/
        for (int i=0;i<actions.length;i++)
		{
        	Object[] element=elements[i];
			Object[] action=actions[i];	
						
			 /**从第二行开始执行测试用例**/
			String test_case_name=(String) action[0];
			log.info("开始执行测试用例---"+test_case_name);
			for (int j=1;j<action.length;j++)
			{
				if(action[j].equals("----"))
				{
					log.info("不执行");
					act.sleep(1);
				}

				else if(!((String) action[j]).contains("=")) //action长度为1，反射action(xpath)
					{
					
					Map<String, String> keyMethod1 = KeywordReflect.KEYWORD_POOLS.get(action[j]);//反射action
					System.out.println(keyMethod1);
					log.info("读取action内容："+action[j]);
					if(keyMethod1==null)
					{
						log.error("无法映射关键字，请检查用例");
						this.driver.quit();
						ElementAction actionClose=new ElementAction();
						log.info("关闭appium server");
						actionClose.executeCmd("taskkill /im cmd.exe");
						log.info("-------------结束测试，并关闭退出driver及appium server-------------");
					}
				
					else
					{
						
						String className1 = keyMethod1.get("class");		        
						String methodName1 = keyMethod1.get("method");
						log.info("方法名:"+methodName1);
						Object obj1 = RegisterCenter.OBJ_POOLS.get(className1);
						Method method1 = this.getMethod(methodName1, obj1);
					
						if(((String) element[j]).isEmpty())  //第一行为空，反射action()，一般为swipe()
						{
							try{
					
								method1.invoke(obj1);
								} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
									e.printStackTrace();
								  }
						}
					
	
						else  //第一行内容不为空
						{

							String[] names = ((String) element[j]).split("\\="); //拆分元素栏，names.length为3
							String name=names[0];
							log.info("name:"+name);
							String type=names[1];
							log.info("type:"+type);
							String xpath=names[2];
							log.info("element:"+element);
							Locator argSec=login.getLocator(name);
							log.info("参数:"+argSec);	
													
							try{
							
								method1.invoke(obj1,argSec);
								} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								e.printStackTrace();
								}
							
						
						  }
						}
					}
					else                        //反射action(xpath,value)
					{
							String[] action_value=((String) action[j]).split("\\="); //拆分动作栏
							String value=action_value[1];
							Map<String, String> keyMethod2 = KeywordReflect.KEYWORD_POOLS.get(action_value[0]);//反射action
							String className2 = keyMethod2.get("class");		        
							String methodName2 = keyMethod2.get("method");
							log.info("方法名:"+methodName2);
							Object obj2 = RegisterCenter.OBJ_POOLS.get(className2);
							Method method2 = this.getMethod(methodName2, obj2);
							
							String[] names = ((String) element[j]).split("\\="); //拆分元素栏
								String name=names[0];
								log.info("name:"+name);
								String type=names[1];
								log.info("type:"+type);
								String xpath=names[2];
								log.info("element:"+element);
								Locator argSec=login.getLocator(name);
							log.info("参数:"+argSec);	
							try{
								
									 method2.invoke(obj2,argSec,value);
	                    		 } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	                    			e.printStackTrace();
	                    	}
						}		
						
					}
				}
			}
       
		
      
  
       
     
    private Method getMethod(String methodName, Object obj) {
        try {	
            Method[] methods = obj.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().equals(methodName)) {
                    return m;
                }
            }
        } catch (SecurityException e) {
            return null;
        }
        return null;
    }
     
    
    public static void main(String[] args) throws Exception 
    {
    	String filePath="D:/bindcard.xls";
    	int rows=ExcelReadUtil.getExcelRows(filePath);
        Executor e = new Executor();
        e.executor(rows);
     }
 
}
