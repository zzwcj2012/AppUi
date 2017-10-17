package appui.utils;

import java.util.List;

public class Page {
	private List<Loc> locator;
	private String pagename;
	public Page(String pagename,List<Loc> locator)
	{
		this.pagename=pagename;
		this.locator=locator;	
	}

}
