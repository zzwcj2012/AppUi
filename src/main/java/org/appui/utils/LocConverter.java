package appui.utils;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class LocConverter implements Converter{
	@Override//定义转换器能转换的JavaBean类型
	public boolean canConvert(Class type)
	{
		return type.equals(Loc.class);
	}
	@Override//把对象序列化成XML或Json
	public void marshal(Object value, HierarchicalStreamWriter writer, 
			MarshallingContext context)
	{
		Loc loc= (Loc) value;
		writer.addAttribute("type", loc.getType());
		writer.addAttribute("timeout", loc.getTimeout());
		writer.addAttribute("value", loc.getValue());
        writer.setValue(loc.getName());
      
	}
	@Override
	public Object unmarshal(HierarchicalStreamReader arg0, UnmarshallingContext arg1) {
		// TODO Auto-generated method stub
		return null;
	}



}
