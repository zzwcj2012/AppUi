package appui.utils;
import com.sun.media.jfxmedia.logging.Logger;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.converters.Converter; 
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveToXml {
	public static void main(String args[]) throws Exception {
		convert();
    } 
	
	public static void convert() throws Exception {
		
		
        //目标对象
		 String timeout="3";
		 String filePath="D:/bindcard.xls";
		 String pagename="appui.pageObject.Login";
		 String n="Element";
		 Object[][] excel=ExcelReadUtil.case_data_excel(0, 0, 0,1,filePath);
		 List<Loc> loc=new ArrayList<Loc>();
		 for (int i=0;i<excel.length;i++)
			{
				Object[] excel2=excel[i];
				for (int j=0;j<excel2.length;j++)
				{
					if(!((String) excel2[j]).isEmpty()&&((String) excel2[j]).contains("="))
					{
						String[] names = ((String) excel2[j]).split("\\=");
						String name=names[0];
						String type=names[1];
						String element=names[2];
						//String value=names[3];
						
		        /**loc初始化，顺序为type,timeout,element,name**/						
						Loc loc1=new Loc(type,timeout,element,name);				 
						loc.add(loc1);						
					}
			   }
			}
		
		Page page=new Page(pagename,loc);
		 Map map=new Map(page);
		 XStream xStream = new XStream();
		 xStream.registerConverter(new LocConverter());
		 xStream.addImplicitCollection(Page.class, "locator");
		 
		

	        /**************    设置类别名   ****************/
		 
	       xStream.alias("MAP",appui.utils.Map.class);
		   xStream.alias("locator", appui.utils.Loc.class);
		   xStream.useAttributeFor(Page.class,"pagename");
	       output(1, xStream,map);

	        /*************  设置类成员的别名 ***************/
	       // xStream.aliasField("Locator", org.appui.utils.Page.class, "locator");
	       // xStream.aliasField("Page", org.appui.utils.Map.class, "page");

	        /*******  设置类成员为xml一个元素上的属性 *******/
	       // xStream.useAttributeFor(Page.class, "locator");
	        /*************  设置属性的别名 ***************/
	        //xStream.aliasAttribute(org.appui.utils.Page.class, "locator", "Locator");
        
    }

    public static void output(int i, XStream xStream, Object obj) throws IOException {
        String xml = xStream.toXML(obj);
        System.out.println(xml + "\n");
        FileWriter fw=new FileWriter("src\\ObjectLibrary.xml");
        fw.write(xml);
        fw.close();
      
    } 

}
