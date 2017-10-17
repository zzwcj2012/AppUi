package appui.pageObject;
import java.io.IOException;
import java.io.InputStream;

import appui.pageObjectConfig.PageObjectAutoCode;
import appui.utils.BaseAction;
import appui.utils.ElementAction;
import appui.utils.Locator;
public class GuildPage extends BaseAction {
	//用于eclipse工程内运行查找对象库文件路径
	private String path="src/ObjectLibrary.xml";
	ElementAction action=new ElementAction();
	Locator locator=new Locator();
	public  GuildPage(){
		//工程内读取对象库文件
		setXmlObjectPath(path);
		getLocatorMap();
		
	}
	public Locator 开始页() throws IOException
	{
		Locator locator=getLocator("开始页");
		return locator;
	}
	
	public Locator 立即体验() throws IOException
	{
		Locator locator=getLocator("立即体验");
		return locator;
	}
	
	public Locator 立即更新() throws IOException
	{
		Locator locator=getLocator("立即更新");
		return locator;
	}
	
	public Locator 关闭更新() throws IOException
	{
		Locator locator=getLocator("关闭更新");
		return locator;
	}
	
	public  int startX()throws IOException
	{ 
		
		return locator.getWidth()* 9/ 10;
	}
	
	public  int endX()throws IOException
	{ 
		
		Locator locator=new Locator();	
		return locator.getWidth()/ 10;
	}
	
	public  int startY()throws IOException
	{ 
		
		Locator locator=new Locator();	
		return locator.getHeight() / 2;
	}
	
	public  int endY()throws IOException
	{ 
		
		Locator locator=new Locator();	
		return locator.getHeight() / 2;
	}

}
