package appui.pageObject;
import java.io.IOException;
import appui.utils.BaseAction;
import appui.utils.Locator;
public class Login extends BaseAction{
	//用于eclipse工程内运行查找对象库文件路径
	private String path="src/ObjectLibrary.xml";
	
	public   Login() {
		//工程内读取对象库文件
		setXmlObjectPath(path);
		getLocatorMap();
		}
	
	
	
	 
	
	public long threeSec() throws IOException
	{
		return 3;
	}
	
	public  int myX()throws IOException
	{ 
		
		Locator locator=new Locator();	
		return locator.getWidth()*9/ 10;
	}
	
	public  int myY()throws IOException
	{ 
		
		Locator locator=new Locator();	
		return locator.getHeight()/ 10;
	}
	
	
	
	
	
}
