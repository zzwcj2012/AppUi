package appui.utils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("node")
public class Loc {
	  private String type;
	  private String value;
	  private String timeout;
	  private String name;
	
	  public Loc(String type,String timeout,String value,String name)
	  {
		  this.type=type;
		  this.value=value;
		  this.name=name;
		  this.timeout=timeout;
	  }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
